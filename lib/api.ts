const baseUrl = process.env.NEXT_PUBLIC_API_URL ?? "http://localhost:8080"
export type Entity = { id: number; nome: string }
export type Gasto = { id: number; descricao: string; valor: number; data: string; status: number; obra?: Entity; pagador?: Entity; fornecedor?: Entity; tipoGasto?: Entity }
export async function getCollection<T>(path: string): Promise<T[]> { const response = await fetch(`${baseUrl}${path}`, { cache: "no-store" }); if (!response.ok) throw new Error(`Erro ${response.status}`); return response.json() }
export const api = { gastos: () => getCollection<Gasto>("/gastos"), obras: () => getCollection<Entity>("/obras"), fornecedores: () => getCollection<Entity>("/fornecedores"), tipos: () => getCollection<Entity>("/tipo-gastos"), pagadores: () => getCollection<Entity>("/pagadores") }
