import { useCallback, useEffect, useRef, useState } from "react"
import {
  getEvents,
  nudge as nudgeApi,
  rerunStage,
  reviewStage,
  setModel as setModelApi,
  startPipeline,
} from "@/services/orchestratorPipelineService"
import type { OrchestratorEvent, StageId } from "@/orchestrator/types"

// Real backend error messages (see orchestratorPipelineService's errorFor())
// are actually useful, e.g. "'psm' is not the current pending stage" or a
// downstream failure's own message, show them instead of a made-up generic
// one that throws that information away.
function messageFor(err: unknown, fallback: string): string {
  return err instanceof Error && err.message ? err.message : fallback
}

// Real client + polling, no local/simulated state: every action here is a
// real call to ai/orchestrator, this hook just tracks what GET /events
// reports back. Polls at a fixed interval while a run is in progress, per
// the design's decision to poll rather than open a websocket for MVP.
const POLL_INTERVAL_MS = 1500

export function usePipeline() {
  const [events, setEvents] = useState<OrchestratorEvent[]>([])
  const [currentStage, setCurrentStage] = useState<StageId | null>(null)
  const [busy, setBusy] = useState(false)
  const [started, setStarted] = useState(false)
  const [model, setModelState] = useState<string | null>(null)
  const [error, setError] = useState<string | null>(null)
  const pollingRef = useRef(false)

  const applyEvents = useCallback((body: Awaited<ReturnType<typeof getEvents>>) => {
    setEvents(body.events)
    setCurrentStage(body.current_stage)
    setBusy(body.busy)
    setModelState(body.model)
    // current_stage defaults to "docs" (index 0) even on a completely fresh,
    // never-started Orchestrator, it's only ever null once the whole
    // pipeline finishes. events.length is the only real signal that /start
    // has actually been called.
    setStarted(body.events.length > 0)
  }, [])

  // Action handlers (start/approve/reject/retry/sendNudge, all triggered by a
  // click, never by an effect) call this directly afterward to reflect the
  // real result right away, instead of waiting for the next poll tick.
  const refresh = useCallback(async () => {
    if (pollingRef.current) return
    pollingRef.current = true
    try {
      applyEvents(await getEvents(0))
    } catch {
      // Transient network hiccup, the next poll tick or manual action retries.
    } finally {
      pollingRef.current = false
    }
  }, [applyEvents])

  // The interval poll is defined and called entirely inside this effect
  // (not via the `refresh` callback above) so state updates stay scoped to
  // "an effect synchronizing with an external system on a timer," rather
  // than an effect reaching out to a click-handler's own helper.
  useEffect(() => {
    let cancelled = false

    async function poll() {
      if (pollingRef.current) return
      pollingRef.current = true
      try {
        const body = await getEvents(0)
        if (!cancelled) applyEvents(body)
      } catch {
        // Transient network hiccup, the next tick retries.
      } finally {
        pollingRef.current = false
      }
    }

    void poll()
    const id = setInterval(() => void poll(), POLL_INTERVAL_MS)
    return () => {
      cancelled = true
      clearInterval(id)
    }
  }, [applyEvents])

  const start = useCallback(
    async (platformDescription: string, seedUrl: string, model?: string) => {
      setError(null)
      try {
        await startPipeline(platformDescription, seedUrl, model)
        setStarted(true)
        await refresh()
      } catch (err) {
        setError(messageFor(err, "Could not start the pipeline. Is the orchestrator running?"))
      }
    },
    [refresh]
  )

  const approve = useCallback(
    async (stageId: StageId) => {
      setError(null)
      try {
        await reviewStage(stageId, true)
        await refresh()
      } catch (err) {
        setError(messageFor(err, "Could not approve this stage."))
      }
    },
    [refresh]
  )

  // Matches the wireframe's actual gesture: curate a correction, then retry,
  // one action, not a separate Reject step. A correction is recorded as a
  // rejection first (ai/orchestrator's review endpoint, which doesn't itself
  // rerun anything), then the rerun folds it in automatically, since
  // run_stage() always reads the live constraints, not a snapshot.
  const retry = useCallback(
    async (stageId: StageId, correction?: string) => {
      setError(null)
      try {
        if (correction) {
          await reviewStage(stageId, false, correction)
        }
        await rerunStage(stageId)
        await refresh()
      } catch (err) {
        setError(messageFor(err, "Could not retry this stage."))
      }
    },
    [refresh]
  )

  const sendNudge = useCallback(
    async (message: string) => {
      setError(null)
      try {
        await nudgeApi(message)
        await refresh()
      } catch (err) {
        setError(messageFor(err, "Could not reach the Orchestrator."))
      }
    },
    [refresh]
  )

  // Changes the model for the rest of the run (every subsequent stage run,
  // retry, or nudge), not just what /start chose, callable any time, not
  // only up front. Updates local state optimistically, since /model's own
  // response already confirms it, no need to wait for the next poll tick.
  const changeModel = useCallback(async (nextModel?: string) => {
    setError(null)
    try {
      const result = await setModelApi(nextModel)
      setModelState(result.model)
    } catch (err) {
      setError(messageFor(err, "Could not change the model."))
    }
  }, [])

  return { events, currentStage, busy, started, model, error, start, approve, retry, sendNudge, changeModel }
}
