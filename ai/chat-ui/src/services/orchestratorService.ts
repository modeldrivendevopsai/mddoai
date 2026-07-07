import type { Message, OrchestratorResponse, Provider } from "@/types"

export async function sendMessage(
  messages: Message[],
  model?: string
): Promise<OrchestratorResponse> {
  const payload = messages.map(({ role, content }) => ({ role, content }))

  const res = await fetch("/api/chat", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ messages: payload, model }),
  })

  if (!res.ok) {
    throw new Error(`Chat request failed: ${res.status}`)
  }

  const data = await res.json()
  return {
    message: data.content,
    status: "pending",
    model: data.model,
  }
}

export async function getProviders(): Promise<Provider[]> {
  const res = await fetch("/api/providers")
  if (!res.ok) {
    throw new Error(`Providers request failed: ${res.status}`)
  }
  return res.json()
}
