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
}
