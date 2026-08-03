import { describe, expect, it, vi, afterEach } from "vitest"
import { fetchDocumentation } from "./retrievalService"

afterEach(() => {
  vi.restoreAllMocks()
})

describe("retrievalService", () => {
  it("posts to /retrieval-api/fetch with just a url when no options are given", async () => {
    const mockFetch = vi.fn().mockResolvedValue({
      ok: true,
      json: async () => ({ seed_url: "https://docs.example.com/ci/", pages: [], meta: {} }),
    })
    vi.stubGlobal("fetch", mockFetch)

    await fetchDocumentation("https://docs.example.com/ci/")

    expect(mockFetch).toHaveBeenCalledWith("/retrieval-api/fetch", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        url: "https://docs.example.com/ci/",
        force_refresh: false,
        hint: null,
        exclude_urls: null,
        model: null,
      }),
    })
  })

  it("forwards advanced options in retrieval's own field names", async () => {
    const mockFetch = vi.fn().mockResolvedValue({
      ok: true,
      json: async () => ({ seed_url: "https://docs.example.com/ci/", pages: [], meta: {} }),
    })
    vi.stubGlobal("fetch", mockFetch)

    await fetchDocumentation("https://docs.example.com/ci/", {
      maxPages: 5,
      maxDepth: 2,
      forceRefresh: true,
      hint: "prioritize triggers",
      excludeUrls: ["https://docs.example.com/ci/old/"],
      model: "claude",
    })

    const sentBody = JSON.parse(mockFetch.mock.calls[0][1].body)
    expect(sentBody).toEqual({
      url: "https://docs.example.com/ci/",
      max_pages: 5,
      max_depth: 2,
      force_refresh: true,
      hint: "prioritize triggers",
      exclude_urls: ["https://docs.example.com/ci/old/"],
      model: "claude",
    })
  })

  it("returns the parsed retrieval result on success", async () => {
    const result = {
      seed_url: "https://docs.example.com/ci/",
      pages: [{ url: "https://docs.example.com/ci/", success: true, status_code: 200, markdown: "# CI", links: [] }],
      meta: { confidence: 0.9, pages_crawled: 1, depth_reached: 0, pending_links: [] },
    }
    vi.stubGlobal("fetch", vi.fn().mockResolvedValue({ ok: true, json: async () => result }))

    await expect(fetchDocumentation("https://docs.example.com/ci/")).resolves.toEqual(result)
  })

  it("throws the backend's detail message on a non-ok response", async () => {
    vi.stubGlobal(
      "fetch",
      vi.fn().mockResolvedValue({
        ok: false,
        status: 422,
        json: async () => ({ detail: "url must be a valid URL" }),
      })
    )

    await expect(fetchDocumentation("not-a-url")).rejects.toThrow("url must be a valid URL")
  })

  it("falls back to an HTTP status message when the error body isn't JSON", async () => {
    vi.stubGlobal(
      "fetch",
      vi.fn().mockResolvedValue({
        ok: false,
        status: 502,
        json: async () => {
          throw new Error("not json")
        },
      })
    )

    await expect(fetchDocumentation("https://docs.example.com/ci/")).rejects.toThrow("HTTP 502")
  })
})
