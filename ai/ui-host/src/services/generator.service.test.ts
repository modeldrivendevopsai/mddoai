import { afterEach, describe, expect, it, vi } from "vitest"
import {
  GeneratorError,
  browseWorkspace,
  generatePipelines,
  getPipeline,
  getPlatforms,
  inspectModel,
  listPipelines,
  readWorkspaceFile,
  validatePipeline,
} from "./generator.service"

afterEach(() => vi.restoreAllMocks())

function stubFetch(response: { ok: boolean; status?: number; json: () => Promise<unknown> }) {
  const mock = vi.fn().mockResolvedValue(response)
  vi.stubGlobal("fetch", mock)
  return mock
}

describe("generatorService", () => {
  it("getPlatforms returns the platforms list", async () => {
    const platforms = [{ id: "gitlab", name: "GitLab CI", output_filename: ".gitlab-ci.yml" }]
    const mock = stubFetch({ ok: true, json: async () => ({ platforms }) })

    expect(await getPlatforms()).toEqual(platforms)
    expect(mock).toHaveBeenCalledWith("/generator-api/platforms")
  })

  it("inspectModel posts the filename and content to /inspect", async () => {
    const inspection = { element: "SoftwareArchitecture", attributes: [], model_validation: { valid: true, issues: [] } }
    const mock = stubFetch({ ok: true, json: async () => inspection })

    expect(await inspectModel("app.swarch", "<x/>")).toEqual(inspection)
    expect(mock).toHaveBeenCalledWith("/generator-api/inspect", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ filename: "app.swarch", content: "<x/>" }),
    })
  })

  it("generatePipelines posts the platforms, source path and save folder along with the model", async () => {
    const mock = stubFetch({ ok: true, json: async () => ({ pipeline_id: "p1", results: [] }) })
    const request = {
      filename: "app.swarch",
      content: "<x/>",
      platforms: ["gitlab", "bamboo"],
      source_path: "/repos/app.swarch",
      save_to: "./ci-output/",
    }

    expect(await generatePipelines(request)).toEqual({ pipeline_id: "p1", results: [] })

    expect(mock.mock.calls[0][0]).toBe("/generator-api/generate")
    expect(JSON.parse(mock.mock.calls[0][1].body)).toEqual(request)
  })

  it("validatePipeline posts the platform and the pipeline text", async () => {
    const mock = stubFetch({ ok: true, json: async () => ({ valid: true, checks: [] }) })

    await validatePipeline("gitlab", "stages: []")

    expect(mock.mock.calls[0][0]).toBe("/generator-api/validate")
    expect(JSON.parse(mock.mock.calls[0][1].body)).toEqual({ platform: "gitlab", content: "stages: []" })
  })

  it("listPipelines returns the rows from the wrapper object", async () => {
    const pipelines = [{ id: "p1", swarch_name: "app.swarch" }]
    const mock = stubFetch({ ok: true, json: async () => ({ pipelines }) })

    expect(await listPipelines()).toEqual(pipelines)
    expect(mock).toHaveBeenCalledWith("/generator-api/pipelines")
  })

  it("getPipeline asks for one pipeline by id, encoded", async () => {
    const mock = stubFetch({ ok: true, json: async () => ({ id: "a/b" }) })

    await getPipeline("a/b")

    expect(mock).toHaveBeenCalledWith("/generator-api/pipelines/a%2Fb")
  })

  it("browseWorkspace sends the path and whether to list folders only, encoded", async () => {
    const mock = stubFetch({ ok: true, json: async () => ({ path: "/", parent: null, entries: [], truncated: false }) })

    await browseWorkspace("/my repos/a&b", true)

    const url = new URL(mock.mock.calls[0][0], "http://x")
    expect(url.pathname).toBe("/generator-api/workspace/browse")
    expect(url.searchParams.get("path")).toBe("/my repos/a&b")
    expect(url.searchParams.get("dirs_only")).toBe("true")
  })

  it("readWorkspaceFile sends the path as a query parameter", async () => {
    const mock = stubFetch({ ok: true, json: async () => ({ filename: "a.swarch", path: "/a.swarch", content: "<x/>" }) })

    const file = await readWorkspaceFile("/repos/a.swarch")

    expect(file.content).toBe("<x/>")
    const url = new URL(mock.mock.calls[0][0], "http://x")
    expect(url.pathname).toBe("/generator-api/workspace/file")
    expect(url.searchParams.get("path")).toBe("/repos/a.swarch")
  })

  it("surfaces a plain-string detail as the error message, with the status", async () => {
    stubFetch({ ok: false, status: 502, json: async () => ({ detail: "The model checker is unavailable, try again shortly." }) })

    const error = await inspectModel("a", "b").catch((e) => e)

    expect(error).toBeInstanceOf(GeneratorError)
    expect(error.message).toBe("The model checker is unavailable, try again shortly.")
    expect(error.status).toBe(502)
    expect(error.issues).toEqual([])
  })

  it("surfaces a workspace error from a GET the same way", async () => {
    stubFetch({ ok: false, status: 404, json: async () => ({ detail: "There is no file at that path in the workspace." }) })

    const error = await readWorkspaceFile("/nope").catch((e) => e)

    expect(error).toBeInstanceOf(GeneratorError)
    expect(error.message).toBe("There is no file at that path in the workspace.")
    expect(error.status).toBe(404)
  })

  it("carries the structured issues of a model refused as invalid", async () => {
    const issues = [{ severity: "ERROR", message: "The required feature 'language' of 'SoftwareArchitecture' must be set" }]
    stubFetch({ ok: false, status: 422, json: async () => ({ detail: { message: "The model is not valid.", issues } }) })

    const error = await generatePipelines({
      filename: "a",
      content: "b",
      platforms: ["gitlab"],
      source_path: null,
      save_to: null,
    }).catch((e) => e)

    expect(error.message).toBe("The model is not valid.")
    expect(error.status).toBe(422)
    expect(error.issues).toEqual(issues)
  })

  it("falls back to a generic message when the error body isn't usable", async () => {
    stubFetch({ ok: false, status: 500, json: async () => { throw new Error("not json") } })

    const error = await validatePipeline("gitlab", "x").catch((e) => e)

    expect(error.message).toBe("Validate request failed: 500")
    expect(error.status).toBe(500)
  })
})
