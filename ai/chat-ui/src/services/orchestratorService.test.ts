import { describe, expect, it, vi, afterEach } from "vitest"
import { sendMessage, resetTurnIndex } from "./orchestratorService"

afterEach(() => {
  resetTurnIndex()
  vi.restoreAllMocks()
})

describe("orchestratorService", () => {
  it("posts message history to /api/chat and returns the response", async () => {
    const mockFetch = vi.fn().mockResolvedValue({
      ok: true,
      json: async () => ({ content: "What stages are in scope?", model: "gemini-flash" }),
    })
    vi.stubGlobal("fetch", mockFetch)

    const result = await sendMessage("I want to integrate with GitHub Actions")

    expect(mockFetch).toHaveBeenCalledWith("/api/chat", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        messages: [{ role: "user", content: "I want to integrate with GitHub Actions" }],
      }),
    })
    expect(result).toEqual({ message: "What stages are in scope?", status: "pending" })
  })

  it("accumulates conversation history across turns", async () => {
    const mockFetch = vi.fn()
      .mockResolvedValueOnce({
        ok: true,
        json: async () => ({ content: "First response", model: "gemini-flash" }),
      })
      .mockResolvedValueOnce({
        ok: true,
        json: async () => ({ content: "Second response", model: "gemini-flash" }),
      })
    vi.stubGlobal("fetch", mockFetch)

    await sendMessage("Hello")
    await sendMessage("Follow up")

    expect(mockFetch).toHaveBeenLastCalledWith("/api/chat", {
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
    await expect(sendMessage("hi")).rejects.toThrow("AI layer error: 500")
  })
})
