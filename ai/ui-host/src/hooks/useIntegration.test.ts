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
      fail(new Error("Network down"))
      await sending
    })
    expect(result.current.replying).toBe(false)
    expect(result.current.error).toBe("Network down")
  })

  it("keeps waiting, and says the message was not sent, when another is still being answered", async () => {
    const busy = Object.assign(new Error("The previous message is still being answered."), { status: 409 })
    vi.mocked(service.sendMessage).mockRejectedValue(busy)
    const { result } = renderHook(() => useIntegration())
    await waitFor(() => expect(result.current.events).toHaveLength(3))

    server = { ...server, replying: true }
    await act(() => result.current.sendMessage("hello"))

    expect(result.current.error).toBe("Your message was not sent: the previous one is still being answered.")
    expect(result.current.replying).toBe(true)
  })

  it("keeps polling a run viewed by id while a reply is coming, even once it is not current", async () => {
    vi.useFakeTimers()
    // The answer's start_pipeline made another run current; this one waits
    // for the answer that is copied into the new run and recorded here.
    server = { ...server, is_current: false, replying: true }
    const { result } = renderHook(() => useIntegration("run-a"))
    await act(() => vi.advanceTimersByTimeAsync(0))

    server = { ...server, replying: false, events: [...server.events, event("the reply")] }
    await act(() => vi.advanceTimersByTimeAsync(POLL_INTERVAL_MS))
    expect(result.current.events.at(-1)?.text).toBe("the reply")

    const calls = vi.mocked(service.getEvents).mock.calls.length
    await act(() => vi.advanceTimersByTimeAsync(POLL_INTERVAL_MS * 3))
    expect(vi.mocked(service.getEvents).mock.calls.length).toBe(calls)
  })

  it("fetches again after a fetch that was already in flight when a message was sent", async () => {
    vi.useFakeTimers()
    const { result } = renderHook(() => useIntegration())
    await act(() => vi.advanceTimersByTimeAsync(0))

    // The next poll is sent before the message and answers late, still
    // saying "not replying"; the fetch the message asked for must not be
    // dropped because that poll was in flight.
    let answerStalePoll: () => void = () => {}
    vi.mocked(service.getEvents).mockImplementationOnce(
      (since = 0) =>
        new Promise((resolve) => {
          const stale = { ...server, events: server.events.slice(since) }
          answerStalePoll = () => resolve(stale)
        })
    )
    await act(() => vi.advanceTimersByTimeAsync(POLL_INTERVAL_MS))

    server = { ...server, replying: true }
    await act(() => result.current.sendMessage("hello"))
    await act(async () => {
      answerStalePoll()
      await vi.advanceTimersByTimeAsync(0)
    })

    expect(result.current.replying).toBe(true)
  })
  it("keeps the chat waiting when a poll answers while the message is still being sent", async () => {
    vi.useFakeTimers()
    let accept: () => void = () => {}
    vi.mocked(service.sendMessage).mockReturnValue(
      new Promise((resolve) => (accept = () => resolve({ status: "accepted" })))
    )
    const { result } = renderHook(() => useIntegration())
    await act(() => vi.advanceTimersByTimeAsync(0))

    act(() => {
      void result.current.sendMessage("hello")
    })
    // The server has not taken the message yet, so a poll still says "not replying".
    await act(() => vi.advanceTimersByTimeAsync(POLL_INTERVAL_MS))
    expect(result.current.replying).toBe(true)

    server = { ...server, replying: true }
    await act(async () => {
      accept()
      await vi.advanceTimersByTimeAsync(0)
    })
    expect(result.current.replying).toBe(true)
  })
})
