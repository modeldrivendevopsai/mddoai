import type { OrchestratorResponse } from "@/types"

const history: { role: string; content: string }[] = []

export async function sendMessage(message: string): Promise<OrchestratorResponse> {
  history.push({ role: "user", content: message })

  const response = await fetch("/api/chat", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ messages: history }),
  })

  if (!response.ok) {
    throw new Error(`AI layer error: ${response.status}`)
  }

  const data = await response.json() as { content: string; model: string }
  history.push({ role: "assistant", content: data.content })

  return {
    message: data.content,
    status: "pending",
  }
}

export function resetTurnIndex(): void {
  history.length = 0
}
