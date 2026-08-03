export interface Message {
  id: string
  role: "user" | "assistant"
  content: string
  timestamp: number
  model?: string
}

export interface OrchestratorResponse {
  message: string
  status: "pending" | "complete"
  model?: string
}

export interface Provider {
  name: string
  tier: string
  // False when this provider has no API key configured (see ai-layer's
  // GET /providers) — still listed so a selector can show every provider
  // this deployment knows about, but shouldn't be selectable while false.
  available: boolean
}
