import { afterEach, describe, expect, it, vi } from "vitest"
import {
  getEvents,
  getProviders,
  getRuns,
  nudge,
  rerunStage,
  resetPipeline,
  reviewStage,
  setModel,
  startPipeline,
} from "./orchestratorPipelineService"

afterEach(() => vi.restoreAllMocks())

describe("orchestratorPipelineService", () => {
  it("startPipeline posts platform_description/seed_url and returns the started response", async () => {
    const mockFetch = vi.fn().mockResolvedValue({
      ok: true,
      json: async () => ({ status: "started", stage: "docs" }),
    })
    vi.stubGlobal("fetch", mockFetch)

    const result = await startPipeline("TeamCity", "https://example.com/docs")

    expect(mockFetch).toHaveBeenCalledWith("/orchestrator-api/start", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ platform_description: "TeamCity", seed_url: "https://example.com/docs" }),
    })
    expect(result).toEqual({ status: "started", stage: "docs" })
  })

  it("startPipeline includes the chosen model when given", async () => {
    const mockFetch = vi.fn().mockResolvedValue({
      ok: true,
      json: async () => ({ status: "started", stage: "docs" }),
    })
    vi.stubGlobal("fetch", mockFetch)

    await startPipeline("TeamCity", "https://example.com/docs", "gemini-flash")

    expect(mockFetch).toHaveBeenCalledWith("/orchestrator-api/start", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        platform_description: "TeamCity",
        seed_url: "https://example.com/docs",
        model: "gemini-flash",
      }),
    })
  })

  it("startPipeline includes docsOptions when given", async () => {
    const mockFetch = vi.fn().mockResolvedValue({
      ok: true,
      json: async () => ({ status: "started", stage: "docs" }),
    })
    vi.stubGlobal("fetch", mockFetch)

    await startPipeline("TeamCity", "https://example.com/docs", undefined, {
      hint: "focus on syntax",
      exclude_urls: ["https://example.com/blog"],
      max_pages: 5,
      max_depth: 2,
      force_refresh: true,
    })

    expect(mockFetch).toHaveBeenCalledWith("/orchestrator-api/start", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        platform_description: "TeamCity",
        seed_url: "https://example.com/docs",
        model: undefined,
        hint: "focus on syntax",
        exclude_urls: ["https://example.com/blog"],
        max_pages: 5,
        max_depth: 2,
        force_refresh: true,
      }),
    })
  })

  it("getProviders fetches the real provider list", async () => {
    const payload = [{ name: "gemini-flash", tier: "free" }, { name: "claude-subscription", tier: "subscription" }]
    const mockFetch = vi.fn().mockResolvedValue({ ok: true, json: async () => payload })
    vi.stubGlobal("fetch", mockFetch)

    const result = await getProviders()

    expect(mockFetch).toHaveBeenCalledWith("/orchestrator-api/providers")
    expect(result).toEqual(payload)
  })

  it("getProviders throws with the status code on a non-ok response", async () => {
    vi.stubGlobal("fetch", vi.fn().mockResolvedValue({ ok: false, status: 502 }))

    await expect(getProviders()).rejects.toThrow("Providers request failed: 502")
  })

  it("startPipeline throws with the status code on a non-ok response", async () => {
    vi.stubGlobal("fetch", vi.fn().mockResolvedValue({ ok: false, status: 500 }))

    await expect(startPipeline("TeamCity", "https://example.com/docs")).rejects.toThrow(
      "Start request failed: 500"
    )
  })

  it("reviewStage posts approved/correction to the stage-specific URL", async () => {
    const mockFetch = vi.fn().mockResolvedValue({
      ok: true,
      json: async () => ({ status: "started", stage: "atl" }),
    })
    vi.stubGlobal("fetch", mockFetch)

    const result = await reviewStage("psm", true)

    expect(mockFetch).toHaveBeenCalledWith("/orchestrator-api/review/psm", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ approved: true, correction: undefined }),
    })
    expect(result).toEqual({ status: "started", stage: "atl" })
  })

  it("reviewStage throws with the status code on a non-ok response", async () => {
    vi.stubGlobal("fetch", vi.fn().mockResolvedValue({ ok: false, status: 400 }))

    await expect(reviewStage("psm", false, "")).rejects.toThrow("Review request failed: 400")
  })

  it("rerunStage posts an empty body when no overrides are given", async () => {
    const mockFetch = vi.fn().mockResolvedValue({
      ok: true,
      json: async () => ({ status: "started", stage: "psm" }),
    })
    vi.stubGlobal("fetch", mockFetch)

    await rerunStage("psm")

    expect(mockFetch).toHaveBeenCalledWith("/orchestrator-api/rerun/psm", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({}),
    })
  })

  it("rerunStage wraps overrides under an 'overrides' key when given", async () => {
    const mockFetch = vi.fn().mockResolvedValue({
      ok: true,
      json: async () => ({ status: "started", stage: "docs" }),
    })
    vi.stubGlobal("fetch", mockFetch)

    await rerunStage("docs", { seed_url: "https://example.com/correct-docs" })

    expect(mockFetch).toHaveBeenCalledWith("/orchestrator-api/rerun/docs", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ overrides: { seed_url: "https://example.com/correct-docs" } }),
    })
  })

  it("getEvents fetches with since_index and returns the events payload", async () => {
    const payload = { events: [], current_stage: "docs", busy: false }
    const mockFetch = vi.fn().mockResolvedValue({ ok: true, json: async () => payload })
    vi.stubGlobal("fetch", mockFetch)

    const result = await getEvents(3)

    expect(mockFetch).toHaveBeenCalledWith("/orchestrator-api/events?since_index=3")
    expect(result).toEqual(payload)
  })

  it("getEvents defaults since_index to 0", async () => {
    const mockFetch = vi.fn().mockResolvedValue({
      ok: true,
      json: async () => ({ events: [], current_stage: null, busy: false }),
    })
    vi.stubGlobal("fetch", mockFetch)

    await getEvents()

    expect(mockFetch).toHaveBeenCalledWith("/orchestrator-api/events?since_index=0")
  })

  it("getEvents throws with the status code on a non-ok response", async () => {
    vi.stubGlobal("fetch", vi.fn().mockResolvedValue({ ok: false, status: 503 }))

    await expect(getEvents()).rejects.toThrow("Events request failed: 503")
  })

  it("getEvents includes run_id when a runId is given, for a past run's frozen log", async () => {
    const payload = { events: [], current_stage: "docs", busy: false, model: null, is_current: false }
    const mockFetch = vi.fn().mockResolvedValue({ ok: true, json: async () => payload })
    vi.stubGlobal("fetch", mockFetch)

    const result = await getEvents(0, "old-run-id")

    expect(mockFetch).toHaveBeenCalledWith("/orchestrator-api/events?since_index=0&run_id=old-run-id")
    expect(result).toEqual(payload)
  })

  it("getEvents omits run_id when none is given, polling the live run", async () => {
    const mockFetch = vi.fn().mockResolvedValue({
      ok: true,
      json: async () => ({ events: [], current_stage: "docs", busy: false, model: null, is_current: true }),
    })
    vi.stubGlobal("fetch", mockFetch)

    await getEvents(5)

    expect(mockFetch).toHaveBeenCalledWith("/orchestrator-api/events?since_index=5")
  })

  it("getRuns fetches the real run history", async () => {
    const payload = [
      { run_id: "abc123", platform_name: "TeamCity", current_stage: "docs", busy: false, is_current: true },
    ]
    const mockFetch = vi.fn().mockResolvedValue({ ok: true, json: async () => payload })
    vi.stubGlobal("fetch", mockFetch)

    const result = await getRuns()

    expect(mockFetch).toHaveBeenCalledWith("/orchestrator-api/runs")
    expect(result).toEqual(payload)
  })

  it("getRuns throws with the status code on a non-ok response", async () => {
    vi.stubGlobal("fetch", vi.fn().mockResolvedValue({ ok: false, status: 500 }))

    await expect(getRuns()).rejects.toThrow("Runs request failed: 500")
  })

  it("nudge posts the message and returns the reply", async () => {
    const payload = { tool_called: "rerun_stage", result: { status: "started", stage: "psm" } }
    const mockFetch = vi.fn().mockResolvedValue({ ok: true, json: async () => payload })
    vi.stubGlobal("fetch", mockFetch)

    const result = await nudge("redo the psm stage")

    expect(mockFetch).toHaveBeenCalledWith("/orchestrator-api/nudge", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ message: "redo the psm stage" }),
    })
    expect(result).toEqual(payload)
  })

  it("nudge throws with the status code on a non-ok response", async () => {
    vi.stubGlobal("fetch", vi.fn().mockResolvedValue({ ok: false, status: 409 }))

    await expect(nudge("anything")).rejects.toThrow("Nudge request failed: 409")
  })

  it("setModel posts the chosen model", async () => {
    const mockFetch = vi.fn().mockResolvedValue({ ok: true, json: async () => ({ model: "gemini-flash" }) })
    vi.stubGlobal("fetch", mockFetch)

    const result = await setModel("gemini-flash")

    expect(mockFetch).toHaveBeenCalledWith("/orchestrator-api/model", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ model: "gemini-flash" }),
    })
    expect(result).toEqual({ model: "gemini-flash" })
  })

  it("setModel posts null when called with no argument (back to auto)", async () => {
    const mockFetch = vi.fn().mockResolvedValue({ ok: true, json: async () => ({ model: null }) })
    vi.stubGlobal("fetch", mockFetch)

    await setModel()

    expect(mockFetch).toHaveBeenCalledWith("/orchestrator-api/model", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ model: null }),
    })
  })

  it("setModel throws with the status code on a non-ok response", async () => {
    vi.stubGlobal("fetch", vi.fn().mockResolvedValue({ ok: false, status: 500 }))

    await expect(setModel("gemini-flash")).rejects.toThrow("Model request failed: 500")
  })

  it("resetPipeline posts with no body", async () => {
    const mockFetch = vi.fn().mockResolvedValue({ ok: true, json: async () => ({ status: "reset" }) })
    vi.stubGlobal("fetch", mockFetch)

    const result = await resetPipeline()

    expect(mockFetch).toHaveBeenCalledWith("/orchestrator-api/reset", { method: "POST" })
    expect(result).toEqual({ status: "reset" })
  })

  it("resetPipeline throws with the status code on a non-ok response", async () => {
    vi.stubGlobal("fetch", vi.fn().mockResolvedValue({ ok: false, status: 409 }))

    await expect(resetPipeline()).rejects.toThrow("Reset request failed: 409")
  })

  it("surfaces the backend's real error detail instead of a generic status message when one is given", async () => {
    vi.stubGlobal(
      "fetch",
      vi.fn().mockResolvedValue({
        ok: false,
        status: 500,
        json: async () => ({ detail: "all providers exhausted" }),
      })
    )

    await expect(nudge("anything")).rejects.toThrow("all providers exhausted")
  })
})
