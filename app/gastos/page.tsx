"use client"

import { useEffect, useMemo, useState } from "react"
import { Building2, ChevronDown, CircleHelp, FileText, LayoutDashboard, Plus, Receipt, Settings, Users, Wallet, X } from "lucide-react"
import { api, type CreateGasto, type Entity, type Gasto } from "@/lib/api"

const statusLabel: Record<number, string> = { 50: "Pendente", 51: "Pago", 52: "Cancelado" }
const statusClass: Record<number, string> = { 50: "pending", 51: "paid", 52: "cancelled" }
const today = () => { const d = new Date(); return `${d.getFullYear()}-${String(d.getMonth() + 1).padStart(2, "0")}-${String(d.getDate()).padStart(2, "0")}` }

type Form = { descricao: string; valor: string; data: string; status: string; obraId: string; fornecedorId: string; tipoGastoId: string; pagadorId: string }
const emptyForm = (): Form => ({ descricao: "", valor: "", data: today(), status: "50", obraId: "", fornecedorId: "", tipoGastoId: "", pagadorId: "" })

export default function GastosPage() {
  const [gastos, setGastos] = useState<Gasto[]>([])
  const [obras, setObras] = useState<Entity[]>([])
  const [fornecedores, setFornecedores] = useState<Entity[]>([])
  const [tipos, setTipos] = useState<Entity[]>([])
  const [pagadores, setPagadores] = useState<Entity[]>([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState("")
  const [search, setSearch] = useState("")
  const [open, setOpen] = useState(false)
  const [form, setForm] = useState<Form>(emptyForm)
  const [formError, setFormError] = useState("")
  const [saving, setSaving] = useState(false)

  useEffect(() => {
    Promise.all([api.gastos(), api.obras(), api.fornecedores(), api.tipos(), api.pagadores()])
      .then(([g, o, f, t, p]) => { setGastos(g); setObras(o); setFornecedores(f); setTipos(t); setPagadores(p) })
      .catch((reason) => setError(reason instanceof Error ? reason.message : "Não foi possível carregar os gastos."))
      .finally(() => setLoading(false))
  }, [])

  const filtered = useMemo(() => gastos.filter((g) => `${g.descricao} ${g.obra?.nome ?? ""} ${g.fornecedor?.nome ?? ""}`.toLowerCase().includes(search.toLowerCase())), [gastos, search])
  const total = gastos.filter(g => g.status !== 52).reduce((sum, g) => sum + (g.valor || 0), 0)

  async function save(event: React.FormEvent<HTMLFormElement>) {
    event.preventDefault(); setFormError("")
    const value = Number(form.valor.replace(",", "."))
    if (!form.descricao.trim() || !Number.isFinite(value) || value <= 0 || !form.obraId || !form.fornecedorId || !form.tipoGastoId || !form.pagadorId || !form.data || form.data > today()) { setFormError("Preencha todos os campos com valores válidos."); return }
    const payload: CreateGasto = { descricao: form.descricao.trim(), valor: value, data: form.data, status: Number(form.status), obra: { id: Number(form.obraId) }, fornecedor: { id: Number(form.fornecedorId) }, tipoGasto: { id: Number(form.tipoGastoId) }, pagador: { id: Number(form.pagadorId) } }
    setSaving(true)
    try { const created = await api.criarGasto(payload); setGastos(current => [created, ...current]); setOpen(false); setForm(emptyForm()) } catch (reason) { setFormError(reason instanceof Error ? reason.message : "Não foi possível cadastrar o gasto.") } finally { setSaving(false) }
  }

  return <div className="shell"><Sidebar /><main className="main"><header className="topbar"><div className="title"><div className="title-icon"><Receipt size={20} /></div><h1>Gastos</h1></div><div className="actions"><button type="button" className="button primary" onClick={() => { setFormError(""); setOpen(true) }}><Plus size={14} /> Novo gasto</button></div></header><div className="tabs"><a href="/">Visão geral</a><a className="active" href="/gastos">Gastos</a><a href="/pagamentos">Pagamentos</a><a href="/relatorios">Relatórios</a></div><section className="content"><div className="page-heading"><div><h2>Lançamentos de gastos</h2><p>Consulte e cadastre os gastos reais das obras.</p></div><div className="summary"><span>Total não cancelado</span><strong>{formatCurrency(total)}</strong></div></div><div className="toolbar"><input className="select" aria-label="Buscar gastos" placeholder="Buscar por descrição, obra ou fornecedor" value={search} onChange={e => setSearch(e.target.value)} /></div>{error && <div className="error" role="alert">{error}</div>}<div className="panel"><div className="panel-title"><strong>Todos os gastos</strong><span>{filtered.length} registros</span></div><div className="table-wrap"><table><thead><tr><th>Descrição</th><th>Obra</th><th>Fornecedor</th><th>Tipo</th><th>Data</th><th>Valor</th><th>Status</th></tr></thead><tbody>{loading ? <tr><td colSpan={7}>Carregando gastos...</td></tr> : filtered.length === 0 ? <tr><td colSpan={7}>Nenhum gasto encontrado.</td></tr> : filtered.map(g => <tr key={g.id}><td>{g.descricao}</td><td>{g.obra?.nome ?? "—"}</td><td>{g.fornecedor?.nome ?? "—"}</td><td>{g.tipoGasto?.nome ?? "—"}</td><td>{formatDate(g.data)}</td><td>{formatCurrency(g.valor)}</td><td><span className={`badge ${statusClass[g.status] ?? ""}`}>{statusLabel[g.status] ?? `Status ${g.status}`}</span></td></tr>)}</tbody></table></div></div></section></main>{open && <ExpenseDialog form={form} setForm={setForm} saving={saving} error={formError} options={{ obras, fornecedores, tipos, pagadores }} onClose={() => setOpen(false)} onSubmit={save} />}</div>
}

function Sidebar() { return <aside className="sidebar"><div className="brand"><div className="brand-mark">OG</div><div><strong>ObraGestão</strong><small>Gestão de obras</small></div></div><div className="site"><div><strong>Obra atual</strong><small>Projeto em andamento</small></div><ChevronDown size={14} /></div><nav className="nav"><a href="/"><LayoutDashboard size={16} /> Dashboard</a><a href="/obras"><Building2 size={16} /> Obras</a><a href="/gastos" className="active"><Receipt size={16} /> Gastos</a><a href="/pagamentos"><Wallet size={16} /> Pagamentos</a><a href="/fornecedores"><Users size={16} /> Fornecedores</a><a href="/relatorios"><FileText size={16} /> Relatórios</a><div className="nav-section">Configurações</div><a href="/preferencias"><Settings size={16} /> Preferências</a><a href="/ajuda"><CircleHelp size={16} /> Ajuda e suporte</a></nav></aside> }

function ExpenseDialog({ form, setForm, saving, error, options, onClose, onSubmit }: { form: Form; setForm: (form: Form) => void; saving: boolean; error: string; options: { obras: Entity[]; fornecedores: Entity[]; tipos: Entity[]; pagadores: Entity[] }; onClose: () => void; onSubmit: (event: React.FormEvent<HTMLFormElement>) => void }) { const select = (label: string, key: keyof Form, items: Entity[]) => <label>{label}<select className="select" required value={form[key]} onChange={e => setForm({ ...form, [key]: e.target.value })}><option value="">Selecione</option>{items.map(item => <option key={item.id} value={item.id}>{item.nome}</option>)}</select></label>; return <div className="modal-backdrop"><form className="modal" onSubmit={onSubmit}><div className="modal-header"><h2>Novo gasto</h2><button type="button" className="button" onClick={onClose} aria-label="Fechar"><X size={16} /></button></div><label>Descrição<input className="select" required value={form.descricao} onChange={e => setForm({ ...form, descricao: e.target.value })} /></label><div className="form-grid"><label>Valor<input className="select" required inputMode="decimal" value={form.valor} onChange={e => setForm({ ...form, valor: e.target.value })} /></label><label>Data<input className="select" required type="date" max={today()} value={form.data} onChange={e => setForm({ ...form, data: e.target.value })} /></label></div>{select("Obra", "obraId", options.obras)}{select("Fornecedor", "fornecedorId", options.fornecedores)}{select("Tipo de gasto", "tipoGastoId", options.tipos)}{select("Pagador", "pagadorId", options.pagadores)}<label>Status<select className="select" value={form.status} onChange={e => setForm({ ...form, status: e.target.value })}><option value="50">Pendente</option><option value="51">Pago</option><option value="52">Cancelado</option></select></label>{error && <div className="error" role="alert">{error}</div>}<div className="modal-actions"><button type="button" className="button" onClick={onClose}>Cancelar</button><button type="submit" className="button primary" disabled={saving}>{saving ? "Salvando..." : "Salvar gasto"}</button></div></form></div> }

function formatCurrency(value: number) { return new Intl.NumberFormat("pt-BR", { style: "currency", currency: "BRL" }).format(value) }
function formatDate(value: string) { return new Intl.DateTimeFormat("pt-BR", { timeZone: "UTC" }).format(new Date(`${value}T12:00:00Z`)) }
