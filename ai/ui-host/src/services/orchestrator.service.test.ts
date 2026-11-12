import { afterEach, describe, expect, it, vi } from "vitest"
import {
  deleteRun,
  getEvents,
  getPlatforms,
  getProviders,
  getRuns,
  previewPromptConfig,
  publishPlatform,
  purgePlatform,
  rerunStage,
  resetPipeline,
  resumeRun,
  reviewStage,
  sendMessage,
  setModel,
  startPipeline,
} from "./orchestrator.service"

afterEach(() => vi.restoreAllMocks())

describe("orchestratorService", () => {
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

  it("deleteRun deletes the run by id, and escapes what it puts in the path", async () => {
    const mockFetch = vi.fn().mockResolvedValue({ ok: true, json: async () => ({ deleted: "a/b" }) })
    vi.stubGlobal("fetch", mockFetch)

    await deleteRun("a/b")

    expect(mockFetch).toHaveBeenCalledWith("/orchestrator-api/runs/a%2Fb", { method: "DELETE" })
  })

  it("deleteRun throws the backend's reason, with the status, when it refuses", async () => {
    vi.stubGlobal(
      "fetch",
      vi.fn().mockResolvedValue({ ok: false, status: 409, json: async () => ({ detail: "That is the current run." }) })
    )

    await expect(deleteRun("r1")).rejects.toMatchObject({ message: "That is the current run.", status: 409 })
  })

  it("getRuns fetches the real run history", async () => {
    const payload = [
      { run_id: "abc123", platform_name: "TeamCity", current_stage: "docs", busy: false, is_current: true, started_at: 1762862400 },
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

  it("sendMessage posts the message and returns the acceptance", async () => {
    const payload = { status: "accepted" }
    const mockFetch = vi.fn().mockResolvedValue({ ok: true, json: async () => payload })
    vi.stubGlobal("fetch", mockFetch)

    const result = await sendMessage("redo the psm stage")

    expect(mockFetch).toHaveBeenCalledWith("/orchestrator-api/message", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ message: "redo the psm stage" }),
    })
    expect(result).toEqual(payload)
  })

  it("sendMessage throws with the status code on a non-ok response", async () => {
    vi.stubGlobal("fetch", vi.fn().mockResolvedValue({ ok: false, status: 409 }))

    await expect(sendMessage("anything")).rejects.toThrow("Message request failed: 409")
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

  it("resumeRun posts to the run-specific URL with no body", async () => {
    const mockFetch = vi.fn().mockResolvedValue({
      ok: true,
      json: async () => ({ run_id: "old-run-id", current_stage: "psm" }),
    })
    vi.stubGlobal("fetch", mockFetch)

    const result = await resumeRun("old-run-id")

    expect(mockFetch).toHaveBeenCalledWith("/orchestrator-api/resume/old-run-id", { method: "POST" })
    expect(result).toEqual({ run_id: "old-run-id", current_stage: "psm" })
  })

  it("resumeRun throws with the status code on a non-ok response", async () => {
    vi.stubGlobal("fetch", vi.fn().mockResolvedValue({ ok: false, status: 404 }))

    await expect(resumeRun("no-such-run")).rejects.toThrow("Resume request failed: 404")
  })

  it("previewPromptConfig sends the given draft config as the request body", async () => {
    const draft = { attachments: [{ id: "a", name: "n", type: "text" as const, content: "hi" }] }
    const payload = { system_prompt: "hi", user_content: "", attachments: {} }
    const mockFetch = vi.fn().mockResolvedValue({ ok: true, json: async () => payload })
    vi.stubGlobal("fetch", mockFetch)

    const result = await previewPromptConfig("psm", "generation", draft)

    expect(mockFetch).toHaveBeenCalledWith("/orchestrator-api/psm/prompt-config/generation/preview", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(draft),
    })
    expect(result).toEqual(payload)
  })

  it("previewPromptConfig throws with the status code on a non-ok response", async () => {
    vi.stubGlobal("fetch", vi.fn().mockResolvedValue({ ok: false, status: 500 }))

    await expect(previewPromptConfig("psm", "generation", { attachments: [] })).rejects.toThrow(
      "Prompt preview request failed: 500"
    )
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

    await expect(sendMessage("anything")).rejects.toThrow("all providers exhausted")
  })

  it("publishPlatform sends the version, and null for anything left empty", async () => {
    const published = { id: "woodpecker-ci", name: "Woodpecker CI", version: "1.2.0", output_filename: "w.yaml" }
    const mockFetch = vi.fn().mockResolvedValue({ ok: true, json: async () => published })
    vi.stubGlobal("fetch", mockFetch)

    const result = await publishPlatform("Woodpecker CI", "w.yaml", "run1", "1.2.0")
    await publishPlatform("", "", null)

    expect(result).toEqual(published)
    expect(JSON.parse(mockFetch.mock.calls[0][1].body)).toEqual({
      name: "Woodpecker CI", output_filename: "w.yaml", version: "1.2.0", run_id: "run1",
    })
    expect(JSON.parse(mockFetch.mock.calls[1][1].body)).toEqual({
      name: null, output_filename: null, version: null, run_id: null,
    })
  })

  it("publishPlatform surfaces the backend's reason, such as a version that exists", async () => {
    vi.stubGlobal(
      "fetch",
      vi.fn().mockResolvedValue({
        ok: false,
        status: 400,
        json: async () => ({ detail: "version 1.0.0 already exists, and a version is never changed" }),
      })
    )

    await expect(publishPlatform("X", "x.yml", "run1", "1.0.0")).rejects.toThrow("already exists")
  })

  it("getPlatforms returns the list from the platforms key", async () => {
    const platform = { id: "woodpecker-ci", name: "Woodpecker CI", output_filename: "w.yaml", version: "1.0.0", versions: ["1.0.0"], published_versions: ["1.0.0"], published: true }
    const mockFetch = vi.fn().mockResolvedValue({ ok: true, json: async () => ({ platforms: [platform] }) })
    vi.stubGlobal("fetch", mockFetch)

    expect(await getPlatforms()).toEqual([platform])
    expect(mockFetch).toHaveBeenCalledWith("/orchestrator-api/platforms")
  })

  it("getPlatforms throws with the status code on a non-ok response", async () => {
    vi.stubGlobal("fetch", vi.fn().mockResolvedValue({ ok: false, status: 502 }))

    await expect(getPlatforms()).rejects.toThrow("Platforms request failed: 502")
  })

  it("purgePlatform deletes one version, or a whole platform when no version is given", async () => {
    const mockFetch = vi
      .fn()
      .mockResolvedValueOnce({ ok: true, json: async () => ({ purged: ["1.0.0"] }) })
      .mockResolvedValueOnce({ ok: true, json: async () => ({ purged: ["1.0.0", "1.1.0"] }) })
    vi.stubGlobal("fetch", mockFetch)

    expect(await purgePlatform("woodpecker-ci", "1.0.0")).toEqual(["1.0.0"])
    expect(await purgePlatform("woodpecker-ci")).toEqual(["1.0.0", "1.1.0"])

    expect(mockFetch).toHaveBeenNthCalledWith(1, "/orchestrator-api/platforms/woodpecker-ci/versions/1.0.0", { method: "DELETE" })
    expect(mockFetch).toHaveBeenNthCalledWith(2, "/orchestrator-api/platforms/woodpecker-ci", { method: "DELETE" })
  })

  it("purgePlatform escapes what it puts in the path", async () => {
    const mockFetch = vi.fn().mockResolvedValue({ ok: true, json: async () => ({ purged: [] }) })
    vi.stubGlobal("fetch", mockFetch)

    await purgePlatform("a/b", "1.0.0?x")

    expect(mockFetch.mock.calls[0][0]).toBe("/orchestrator-api/platforms/a%2Fb/versions/1.0.0%3Fx")
  })

  it("purgePlatform surfaces the backend's reason, such as a platform that came with the stack", async () => {
    vi.stubGlobal(
      "fetch",
      vi.fn().mockResolvedValue({ ok: false, status: 409, json: async () => ({ detail: "it came with the stack" }) })
    )

    await expect(purgePlatform("gitlab")).rejects.toThrow("it came with the stack")
  })
})
