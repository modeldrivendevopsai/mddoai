import { describe, expect, it, vi, afterEach } from "vitest"
import { sendMessage } from "./orchestratorService"
import type { Message } from "@/types"

afterEach(() => {
  vi.restoreAllMocks()
})

const msg = (role: "user" | "assistant", content: string): Message => ({
  id: "test-id",
  role,
  content,
  timestamp: 0,
})

describe("orchestratorService", () => {
  it("posts message history to /api/chat and returns the response", async () => {
    const mockFetch = vi.fn().mockResolvedValue({
      ok: true,
      json: async () => ({ content: "What stages are in scope?", model: "gemini-flash" }),
    })
    vi.stubGlobal("fetch", mockFetch)

    const messages = [msg("user", "I want to integrate with GitHub Actions")]
    const result = await sendMessage(messages)

    expect(mockFetch).toHaveBeenCalledWith("/api/chat", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        messages: [{ role: "user", content: "I want to integrate with GitHub Actions" }],
      }),
    })
    expect(result).toEqual({ message: "What stages are in scope?", status: "complete" })
  })

  it("strips id and timestamp fields before sending", async () => {
    const mockFetch = vi.fn().mockResolvedValue({
      ok: true,
      json: async () => ({ content: "Second response", model: "gemini-flash" }),
    })
    vi.stubGlobal("fetch", mockFetch)

    const messages = [
      msg("user", "Hello"),
      msg("assistant", "First response"),
      msg("user", "Follow up"),
    ]
    await sendMessage(messages)

    expect(mockFetch).toHaveBeenCalledWith("/api/chat", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        messages: [
          { role: "user", content: "Hello" },
          { role: "assistant", content: "First response" },
          { role: "user", content: "Follow up" },
        ],
      }),
    })
  })

  it("throws on non-ok response", async () => {
    vi.stubGlobal("fetch", vi.fn().mockResolvedValue({ ok: false, status: 500 }))
    await expect(sendMessage([msg("user", "hi")])).rejects.toThrow("Chat request failed: 500")
  })
})
