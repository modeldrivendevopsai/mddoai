import { describe, expect, it, vi, afterEach } from "vitest"
import { getProviders } from "./providersService"

afterEach(() => {
  vi.restoreAllMocks()
})

describe("providersService", () => {
  it("fetches /api/providers and returns the parsed list", async () => {
    const mockFetch = vi.fn().mockResolvedValue({
      ok: true,
      json: async () => [{ name: "claude-subscription", tier: "subscription", available: true }],
    })
    vi.stubGlobal("fetch", mockFetch)

    const providers = await getProviders()

    expect(mockFetch).toHaveBeenCalledWith("/api/providers")
    expect(providers).toEqual([
      { name: "claude-subscription", tier: "subscription", available: true },
    ])
  })

  it("throws when the request fails", async () => {
    const mockFetch = vi.fn().mockResolvedValue({ ok: false, status: 500 })
    vi.stubGlobal("fetch", mockFetch)

    await expect(getProviders()).rejects.toThrow("Providers request failed: 500")
  })
})
