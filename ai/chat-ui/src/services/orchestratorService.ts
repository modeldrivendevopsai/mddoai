import type { Message, OrchestratorResponse } from "@/types"

export async function sendMessage(
  messages: Message[]
): Promise<OrchestratorResponse> {
  const payload = messages.map(({ role, content }) => ({ role, content }))

  const res = await fetch("/api/chat", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ messages: payload }),
  })

  if (!res.ok) {
    throw new Error(`Chat request failed: ${res.status}`)
  }

  const data = await res.json()
  return {
    message: data.content,
    status: "complete",
  }
}
