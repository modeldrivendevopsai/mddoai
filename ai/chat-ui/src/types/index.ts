export interface Message {
  id: string
  role: "user" | "assistant"
  content: string
  timestamp: number
}

export interface OrchestratorResponse {
  message: string
  status: "pending" | "complete"
}
