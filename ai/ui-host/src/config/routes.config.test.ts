import { describe, expect, it } from "vitest"
import {
  INTEGRATION_QUERY,
  NEW_PLATFORM_PATH,
  ROUTES,
  integrationRunPath,
  isNewRunRequested,
  pipelinePath,
} from "./routes.config"

// The links write these paths and the screens read them, so each pair is
// checked as one round trip: what a link writes is what the screen recognises.
const queryOf = (path: string) => new URLSearchParams(path.slice(path.indexOf("?") + 1))

describe("routes", () => {
  it("puts a saved pipeline under the pipelines route", () => {
    expect(pipelinePath("abc123").startsWith(`${ROUTES.pipelines}/`)).toBe(true)
    expect(pipelinePath("abc123").endsWith("/abc123")).toBe(true)
  })

  it("asks the integration screen for a blank run with the path the screen recognises", () => {
    expect(NEW_PLATFORM_PATH.startsWith(`${ROUTES.integration}?`)).toBe(true)
    expect(isNewRunRequested(queryOf(NEW_PLATFORM_PATH))).toBe(true)
    expect(isNewRunRequested(new URLSearchParams(""))).toBe(false)
  })

  it("shows a past run with the run id the screen reads back", () => {
    const path = integrationRunPath("run-7")

    expect(path.startsWith(`${ROUTES.integration}?`)).toBe(true)
    expect(queryOf(path).get(INTEGRATION_QUERY.run)).toBe("run-7")
  })

  it("keeps an id with special characters whole, in a path and in a query", () => {
    const awkward = "a&b c#d/e+f"

    expect(queryOf(integrationRunPath(awkward)).get(INTEGRATION_QUERY.run)).toBe(awkward)
    const pipelinePrefix = `${ROUTES.pipelines}/`
    const encodedId = pipelinePath(awkward).slice(pipelinePrefix.length)
    expect(encodedId).not.toContain("/")
    expect(decodeURIComponent(encodedId)).toBe(awkward)
  })
})
