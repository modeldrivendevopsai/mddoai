// @vitest-environment jsdom
//
// The hook against a stubbed orchestrator.service: what the chat sees while a
// message is answered in the background, and that a run switched by that
// answer replaces the log instead of being appended to it.
import { act, cleanup, renderHook, waitFor } from "@testing-library/react"
import type { EventsResponse, OrchestratorEvent } from "orchestrator-types"
import { afterEach, beforeEach, describe, expect, it, vi } from "vitest"
import * as service from "@/services/orchestrator.service"
import { useIntegration } from "./useIntegration"

vi.mock("@/services/orchestrator.service")

const POLL_INTERVAL_MS = 1500

function event(text: string): OrchestratorEvent {
  return { type: "message", stage: null, text, timestamp: 0 }
}

// What GET /events would answer; each test changes it as the backend would.
let server: Omit<EventsResponse, "events"> & { events: OrchestratorEvent[] }

beforeEach(() => {
  server = {
    run_id: "run-a",
    events: [event("a1"), event("a2"), event("a3")],
    current_stage: "psm",
    busy: false,
    replying: false,
    model: null,
    is_current: true,
  }
  vi.mocked(service.getEvents).mockImplementation(async (since = 0) => ({
    ...server,
    events: server.events.slice(since),
  }))
  vi.mocked(service.getProviders).mockResolvedValue([])
  vi.mocked(service.getStageMetadata).mockResolvedValue({ stages: [], details: {} } as never)
  vi.mocked(service.sendMessage).mockResolvedValue({ status: "accepted" })
})

afterEach(() => {
  cleanup()
  vi.useRealTimers()
  vi.resetAllMocks()
})

describe("useIntegration", () => {
  it("replaces the log when a chat answer switched to a new run", async () => {
    const { result } = renderHook(() => useIntegration())
    await waitFor(() => expect(result.current.events).toHaveLength(3))

    // The answer's start_pipeline made a new run with fewer events than the
    // old one, so an incremental slice of it would come back empty.
    server = { ...server, run_id: "run-b", events: [event("b1"), event("b2")] }
    await act(() => result.current.sendMessage("start over for GitLab"))

    expect(result.current.events.map((e) => e.text)).toEqual(["b1", "b2"])
    expect(result.current.viewedRunId).toBe("run-b")
  })

  it("keeps polling a finished run until the reply arrives, then stops", async () => {
    vi.useFakeTimers()
    server = { ...server, current_stage: null, replying: true }
    const { result } = renderHook(() => useIntegration())
    await act(() => vi.advanceTimersByTimeAsync(0))
    expect(result.current.replying).toBe(true)

    server = { ...server, replying: false, events: [...server.events, event("the reply")] }
    await act(() => vi.advanceTimersByTimeAsync(POLL_INTERVAL_MS))
    expect(result.current.events.at(-1)?.text).toBe("the reply")
    expect(result.current.replying).toBe(false)

    const calls = vi.mocked(service.getEvents).mock.calls.length
    await act(() => vi.advanceTimersByTimeAsync(POLL_INTERVAL_MS * 3))
    expect(vi.mocked(service.getEvents).mock.calls.length).toBe(calls)
  })

  it("marks the chat as replying while sending, and frees it if sending fails", async () => {
    let fail: (reason: Error) => void = () => {}
    vi.mocked(service.sendMessage).mockReturnValue(new Promise((_, reject) => (fail = reject)))
    const { result } = renderHook(() => useIntegration())
    await waitFor(() => expect(result.current.events).toHaveLength(3))

    let sending: Promise<void> = Promise.resolve()
    act(() => {
      sending = result.current.sendMessage("hello")
    })
    expect(result.current.replying).toBe(true)

    await act(async () => {
      fail(new Error("The previous message is still being answered."))
      await sending
    })
    expect(result.current.replying).toBe(false)
    expect(result.current.error).toBe("The previous message is still being answered.")
  })
})
