// @vitest-environment jsdom
//
// What generating leaves behind when its wait is cut short: a cancelled wait
// shows neither an error nor results, and a newer generation keeps its own
// busy state.
import { act, cleanup, renderHook } from "@testing-library/react"
import { afterEach, describe, expect, it, vi } from "vitest"
import * as service from "@/services/generator.service"
import type { GenerateResponse, GeneratorPlatform } from "@/services/generator.service"
import { useGeneration } from "./useGeneration"
import { useStaleGuard } from "./useStaleGuard"

vi.mock("@/services/generator.service")

const PLATFORMS = [{ id: "gitlab", name: "GitLab CI", output_filename: ".gitlab-ci.yml" }] as GeneratorPlatform[]
const SOURCE = { filename: "app.swarch", content: "<x/>", path: null }

// A generation that waits until the test settles it, rejecting like the real
// service does when its signal is aborted.
function heldGeneration() {
  let finish: (response: GenerateResponse) => void = () => {}
  vi.mocked(service.generatePipelines).mockImplementationOnce(
    (_request, signal) =>
      new Promise((resolve, reject) => {
        finish = resolve
        signal?.addEventListener("abort", () => reject(signal.reason), { once: true })
      })
  )
  return { finish: (response: GenerateResponse) => finish(response) }
}

function renderGeneration() {
  const { result } = renderHook(() => {
    const guard = useStaleGuard()
    return useGeneration({ guard, source: SOURCE, platforms: PLATFORMS })
  })
  act(() => result.current.toggle("gitlab"))
  return result
}

afterEach(() => {
  cleanup()
  vi.resetAllMocks()
})

describe("useGeneration", () => {
  it("shows neither an error nor results when its wait is cancelled", async () => {
    heldGeneration()
    const result = renderGeneration()

    let generating: Promise<void> = Promise.resolve()
    act(() => {
      generating = result.current.generate()
    })
    expect(result.current.generating).toBe(true)

    await act(async () => {
      result.current.reset()
      await generating
    })

    expect(result.current.generateError).toBeNull()
    expect(result.current.results).toBeNull()
    expect(result.current.generating).toBe(false)
  })

  it("keeps a newer generation busy when the one it replaced ends", async () => {
    heldGeneration()
    const second = heldGeneration()
    const result = renderGeneration()

    let first: Promise<void> = Promise.resolve()
    act(() => {
      first = result.current.generate()
    })
    let next: Promise<void> = Promise.resolve()
    await act(async () => {
      next = result.current.generate()
      await first
    })
    expect(result.current.generating).toBe(true)

    await act(async () => {
      second.finish({ pipeline_id: "p2", results: [] })
      await next
    })
    expect(result.current.generating).toBe(false)
    expect(result.current.pipelineId).toBe("p2")
  })
})
