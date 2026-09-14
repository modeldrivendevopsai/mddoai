// @vitest-environment jsdom
//
// Exercises the real, non-obvious concurrency behavior useAutoSave exists
// for - debouncing, never overlapping two save requests, retrying only a
// transient failure, and flushing a still-pending save (with keepalive) on
// unmount - against real React state and real timers, not a description of
// what the code is supposed to do. Real timers (not vi.useFakeTimers): the
// debounce/retry delays are only a couple of seconds, and interleaving fake
// timers with the real promise microtask queue used throughout this hook
// is a much more fragile way to get the same coverage.
import { act, renderHook, waitFor } from "@testing-library/react"
import { useState } from "react"
import { afterEach, describe, expect, it, vi } from "vitest"
import type { AutoSaveCallbacks } from "./useAutoSave"
import { useAutoSave } from "./useAutoSave"
import type { BrokenReference, PromptConfig } from "./types"

const BASE: PromptConfig = { attachments: [{ id: "a", name: "A", type: "text", content: "one" }] }

function withContent(config: PromptConfig, content: string): PromptConfig {
  return { ...config, attachments: [{ ...config.attachments[0], content }] }
}

function deferred<T>() {
  let resolve!: (value: T) => void
  let reject!: (reason?: unknown) => void
  const promise = new Promise<T>((res, rej) => {
    resolve = res
    reject = rej
  })
  return { promise, resolve, reject }
}

// A rejection shaped like ui-host's own errorFor: a real Error carrying the
// HTTP status as a plain property, which is exactly what isRetryable below
// (inside useAutoSave itself) keys off of.
function httpError(status: number, message = "failed"): Error {
  return Object.assign(new Error(message), { status })
}

const wait = (ms: number) => new Promise((resolve) => setTimeout(resolve, ms))

function useHarness(initial: PromptConfig, onSave: AutoSaveCallbacks["onSave"]) {
  const [config, setConfig] = useState<PromptConfig | null>(initial)
  const onCheckReferences = () => Promise.resolve([] as BrokenReference[])
  const onBroken: (broken: BrokenReference[]) => void = () => undefined
  const autoSave = useAutoSave(config, setConfig, { onSave, onCheckReferences }, onBroken)
  return { config, setConfig, ...autoSave }
}

describe("useAutoSave", () => {
  afterEach(() => vi.restoreAllMocks())

  it("saves 1.5s after the last edit, not immediately", async () => {
    const onSave = vi.fn().mockResolvedValue({ ...BASE, _version: "v1" })
    const { result } = renderHook(() => useHarness(BASE, onSave))

    act(() => result.current.setConfig(() => withContent(BASE, "edited")))
    expect(onSave).not.toHaveBeenCalled()

    await wait(1700)

    expect(onSave).toHaveBeenCalledTimes(1)
    expect(onSave.mock.calls[0][0].attachments[0].content).toBe("edited")
  }, 10000)

  it("never lets a second save overlap the first - a later edit waits its turn", async () => {
    const first = deferred<PromptConfig>()
    const onSave = vi.fn().mockReturnValueOnce(first.promise).mockResolvedValue({ ...BASE, _version: "v2" })
    const { result } = renderHook(() => useHarness(BASE, onSave))

    act(() => result.current.setConfig(() => withContent(BASE, "v2")))
    await wait(1700)
    expect(onSave).toHaveBeenCalledTimes(1) // first save now in flight, deliberately unresolved

    act(() => result.current.setConfig(() => withContent(BASE, "v3")))
    await wait(1700)
    expect(onSave).toHaveBeenCalledTimes(1) // still just the one in-flight request, no overlapping second one

    await act(async () => {
      first.resolve({ ...BASE, _version: "v1" })
      await first.promise
    })

    await waitFor(() => expect(onSave).toHaveBeenCalledTimes(2))
    expect(onSave.mock.calls[1][0].attachments[0].content).toBe("v3")
  }, 10000)

  it("retries a failed save automatically only when the failure looks transient", async () => {
    const onSaveTransient = vi.fn().mockRejectedValueOnce(httpError(500)).mockResolvedValue({ ...BASE, _version: "v1" })
    const { result: transient } = renderHook(() => useHarness(BASE, onSaveTransient))

    act(() => transient.current.setConfig(() => withContent(BASE, "edited")))
    await wait(1700)
    expect(onSaveTransient).toHaveBeenCalledTimes(1)
    await waitFor(() => expect(transient.current.saveError).toBeTruthy())

    await wait(5300)
    expect(onSaveTransient).toHaveBeenCalledTimes(2) // a 500 retries automatically

    const onSavePermanent = vi.fn().mockRejectedValue(httpError(400, "bad attachment reference"))
    const { result: permanent } = renderHook(() => useHarness(BASE, onSavePermanent))

    act(() => permanent.current.setConfig(() => withContent(BASE, "edited")))
    await wait(1700)
    expect(onSavePermanent).toHaveBeenCalledTimes(1)
    await waitFor(() => expect(permanent.current.saveError).toBeTruthy())

    await wait(5300)
    expect(onSavePermanent).toHaveBeenCalledTimes(1) // a 400 never retries - it would just fail identically forever
  }, 20000)

  it("flushes a still-pending debounced save, with keepalive, when the component unmounts", () => {
    const onSave = vi.fn().mockResolvedValue({ ...BASE, _version: "v1" })
    const { result, unmount } = renderHook(() => useHarness(BASE, onSave))

    act(() => result.current.setConfig(() => withContent(BASE, "edited")))
    expect(onSave).not.toHaveBeenCalled() // debounce timer still pending, nothing sent yet

    unmount()

    expect(onSave).toHaveBeenCalledTimes(1)
    expect(onSave.mock.calls[0][1]).toEqual({ keepalive: true })
  })

  it("still fires a save that was queued behind an in-flight one, even after the component unmounts", async () => {
    const first = deferred<PromptConfig>()
    const onSave = vi.fn().mockReturnValueOnce(first.promise).mockResolvedValue({ ...BASE, _version: "v2" })
    const { result, unmount } = renderHook(() => useHarness(BASE, onSave))

    act(() => result.current.setConfig(() => withContent(BASE, "v2")))
    await wait(1700)
    expect(onSave).toHaveBeenCalledTimes(1) // first save in flight, deliberately unresolved

    act(() => result.current.setConfig(() => withContent(BASE, "v3")))
    await wait(1700) // the second edit's own debounce elapses while the first save is still in flight, queuing it

    unmount() // the queued save from the second edit must survive this, not be silently dropped

    await act(async () => {
      first.resolve({ ...BASE, _version: "v1" })
      await first.promise
    })

    await waitFor(() => expect(onSave).toHaveBeenCalledTimes(2))
    expect(onSave.mock.calls[1][0].attachments[0].content).toBe("v3")
  }, 10000)

  it("never saves a freshly added file attachment until a path is actually picked", async () => {
    const onSave = vi.fn().mockResolvedValue({ ...BASE, _version: "v1" })
    const withEmptyFile: PromptConfig = {
      attachments: [...BASE.attachments, { id: "b", name: "New file reference", type: "file", path: "" }],
    }
    const { result } = renderHook(() => useHarness(BASE, onSave))

    act(() => result.current.setConfig(() => withEmptyFile))
    await wait(1700)

    expect(onSave).not.toHaveBeenCalled() // an empty path can never resolve, saving it would just 400
    expect(result.current.incomplete).toBe(true)

    act(() =>
      result.current.setConfig((current) => ({
        attachments: current!.attachments.map((a) => (a.id === "b" ? { ...a, path: "real/file.ecore" } : a)),
      }))
    )
    await wait(1700)

    expect(onSave).toHaveBeenCalledTimes(1)
    expect(result.current.incomplete).toBe(false)
  }, 10000)

  it("never saves a freshly added auto-filled-data attachment until a key is actually picked", async () => {
    const onSave = vi.fn().mockResolvedValue({ ...BASE, _version: "v1" })
    const withEmptyContext: PromptConfig = {
      attachments: [...BASE.attachments, { id: "b", name: "New auto-filled data", type: "context", key: "" }],
    }
    const { result } = renderHook(() => useHarness(BASE, onSave))

    act(() => result.current.setConfig(() => withEmptyContext))
    await wait(1700)

    expect(onSave).not.toHaveBeenCalled()
    expect(result.current.incomplete).toBe(true)
  }, 10000)
})
