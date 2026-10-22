const BACKEND_ORIGIN = "https://gerenciamento-gastos-obra.onrender.com"

function getBaseUrl() {
  const configuredBaseUrl = process.env.NEXT_PUBLIC_API_URL?.trim()

  if (!configuredBaseUrl) {
    throw new Error("NEXT_PUBLIC_API_URL não está configurada")
  }

  const withProtocol = /^https?:\/\//i.test(configuredBaseUrl)
    ? configuredBaseUrl
    : `https://${configuredBaseUrl}`
  const parsedUrl = new URL(withProtocol)

  if (parsedUrl.hostname !== "gerenciamento-gastos-obra.onrender.com") {
    throw new Error(
      `NEXT_PUBLIC_API_URL inválida. Use exatamente ${BACKEND_ORIGIN}`,
    )
  }

  return BACKEND_ORIGIN
}

export type Entity = {
  id: number
  nome: string
}

export type Gasto = {
  id: number
  descricao: string
  valor: number
  data: string
  status: number
  obra?: Entity
  pagador?: Entity
  fornecedor?: Entity
  tipoGasto?: Entity
}

async function requestJson<T>(path: string, init: RequestInit): Promise<T> {
  const response = await fetch(`${getBaseUrl()}${path}`, { ...init, headers: { Accept: "application/json", "Content-Type": "application/json", ...init.headers } })
  const contentType = response.headers.get("content-type") ?? ""
  const body = contentType.includes("application/json") ? await response.json() : await response.text()
  if (!response.ok) throw new Error(`Erro ${response.status} ao consultar ${path}`)
  return body as T
}

async function getCollection<T>(path: string): Promise<T[]> {
  const response = await fetch(`${getBaseUrl()}${path}`, {
    cache: "no-store",
    headers: { Accept: "application/json" },
  })

  const contentType = response.headers.get("content-type") ?? ""
  const body = contentType.includes("application/json") ? await response.json() : await response.text()

  if (!response.ok) {
    const detail = typeof body === "string" ? body : body?.message
    throw new Error(`Erro ${response.status} ao consultar ${path}${detail ? `: ${detail}` : ""}`)
  }

  if (!Array.isArray(body)) {
    throw new Error(`Resposta inválida em ${path}: era esperada uma lista`)
  }

  return body as T[]
}

export const api = {
  gastos: () => getCollection<Gasto>("/gastos"),
  criarGasto: (gasto: Omit<Gasto, "id" | "obra" | "pagador" | "fornecedor" | "tipoGasto">) => requestJson<Gasto>("/gastos", { method: "POST", body: JSON.stringify(gasto) }),
  obras: () => getCollection<Entity>("/obras"),
  fornecedores: () => getCollection<Entity>("/fornecedores"),
  tipos: () => getCollection<Entity>("/tipo-gastos"),
  pagadores: () => getCollection<Entity>("/pagadores"),
}
