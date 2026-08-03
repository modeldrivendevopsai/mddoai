export interface Provider {
  name: string
  tier: string
  available: boolean
}

export async function getProviders(): Promise<Provider[]> {
  const res = await fetch("/api/providers")
  if (!res.ok) {
    throw new Error(`Providers request failed: ${res.status}`)
  }
  return res.json()
}
