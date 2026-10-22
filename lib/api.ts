function getBaseUrl() {
  const configuredBaseUrl = process.env.NEXT_PUBLIC_API_URL?.trim()

  if (!configuredBaseUrl) {
    throw new Error("NEXT_PUBLIC_API_URL não está configurada")
  }

  return configuredBaseUrl.replace(/\/$/, "")
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
  obras: () => getCollection<Entity>("/obras"),
  fornecedores: () => getCollection<Entity>("/fornecedores"),
  tipos: () => getCollection<Entity>("/tipo-gastos"),
  pagadores: () => getCollection<Entity>("/pagadores"),
}
