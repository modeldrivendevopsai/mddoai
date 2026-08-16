import { useCallback, useEffect, useRef, useState } from "react"
import {
  getEvents,
  rerunStage,
  resetPipeline,
  resumeRun,
  reviewStage,
  sendMessage as sendMessageApi,
  setModel as setModelApi,
  startPipeline,
} from "@/services/orchestrator.service"
import type { DocsOptions } from "@/services/orchestrator.service"
import type { OrchestratorEvent, StageId } from "@/types/orchestrator"

// Real backend error messages (see orchestrator.service's errorFor())
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

// runId is undefined for the live run (existing behavior, unchanged: polls
// and is fully interactive). A defined runId views a specific run from the
// sidebar's history — GET /events?run_id= reports back whether that run is
// still the current one (is_current), which decides whether this stays
// interactive/polling or goes read-only after a single fetch.
export function useIntegration(runId?: string) {
  const [events, setEvents] = useState<OrchestratorEvent[]>([])
  const [currentStage, setCurrentStage] = useState<StageId | null>(null)
  const [busy, setBusy] = useState(false)
  const [started, setStarted] = useState(false)
  const [model, setModelState] = useState<string | null>(null)
  const [error, setError] = useState<string | null>(null)
  const [isCurrent, setIsCurrent] = useState(true)
  const pollingRef = useRef(false)
  // Mirrors isCurrent for the interval poll below, which can't depend on the
  // state value directly without re-subscribing the effect every tick.
  const isCurrentRef = useRef(true)
  // The real accumulated log. GET /events?since_index=N exists specifically
  // "for incremental polling" (see main.py), so each fetch only asks for
  // what's new since this and gets appended, rather than re-fetching and
  // replacing the whole thing every 1.5s tick.
  const allEventsRef = useRef<OrchestratorEvent[]>([])
  // True once nothing can change without a new explicit action from this
  // hook itself (start/reset, both of which reset this directly): either the
  // pipeline finished (current_stage null) or it never started (no events
  // yet). The recurring interval poll skips its fetch entirely while this is
  // true, an idle tab left open otherwise hits the backend forever for
  // nothing. Action-triggered fetches (approve/retry/send a message/reset)
  // never check this, they always fetch, since they just did something
  // that can legitimately change state.
  const doneRef = useRef(false)

  const applyEvents = useCallback((body: Awaited<ReturnType<typeof getEvents>>, replace: boolean) => {
    allEventsRef.current = replace ? body.events : [...allEventsRef.current, ...body.events]
    setEvents(allEventsRef.current)
    setCurrentStage(body.current_stage)
    setBusy(body.busy)
    setModelState(body.model)
    setIsCurrent(body.is_current)
    isCurrentRef.current = body.is_current
    // current_stage defaults to "docs" (index 0) even on a completely fresh,
    // never-started Orchestrator, it's only ever null once the whole
    // pipeline finishes. events.length is the only real signal that /start
    // has actually been called.
    setStarted(allEventsRef.current.length > 0)
    doneRef.current = !body.busy && (body.current_stage === null || allEventsRef.current.length === 0)
  }, [])

  // Used by the interval poll and by approve/retry, neither of which can
  // ever change which run is current (only /start and /reset do that), so
  // asking for since_index=<what we already have> is always safe.
  const fetchIncremental = useCallback(async () => {
    if (pollingRef.current) return
    pollingRef.current = true
    try {
      applyEvents(await getEvents(allEventsRef.current.length, runId), false)
    } catch {
      // Transient network hiccup, the next poll tick or manual action retries.
    } finally {
      pollingRef.current = false
    }
  }, [applyEvents, runId])

  // Used after start/reset (which already clear allEventsRef themselves) and
  // after sending a chat message, since its tool-calling can itself call
  // start_pipeline (see tools/pipeline_control.py's start_pipeline tool),
  // resetting the backend's own event indices in a way this hook can't
  // predict in advance, there's no run identity yet to detect that safely,
  // so sending a message always does a full refetch rather than risk
  // merging two different runs' events together.
  const fetchFull = useCallback(async () => {
    if (pollingRef.current) return
    pollingRef.current = true
    try {
      applyEvents(await getEvents(0, runId), true)
    } catch {
      // Transient network hiccup, the next poll tick or manual action retries.
    } finally {
      pollingRef.current = false
    }
  }, [applyEvents, runId])

  // The interval poll is defined and called entirely inside this effect
  // (not via the fetch callbacks above) so state updates stay scoped to
  // "an effect synchronizing with an external system on a timer," rather
  // than an effect reaching out to a click-handler's own helper.
  useEffect(() => {
    let cancelled = false
    // Switching which run is viewed (including live -> history or back) is a
    // fresh start: the previous run's accumulated event indices don't apply.
    allEventsRef.current = []
    doneRef.current = false
    isCurrentRef.current = true

    async function poll() {
      if (doneRef.current) return
      // A past, non-current run is a frozen log (see main.py's /events?run_id=
      // — "read-only, no polling loop needed"): once the first fetch confirms
      // that, every later tick is a no-op instead of a wasted request.
      if (runId && !isCurrentRef.current) return
      if (pollingRef.current) return
      pollingRef.current = true
      try {
        const body = await getEvents(allEventsRef.current.length, runId)
        if (!cancelled) applyEvents(body, false)
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
  }, [applyEvents, runId])

  const start = useCallback(
    async (platformDescription: string, seedUrl: string, model?: string, docsOptions?: DocsOptions) => {
      setError(null)
      try {
        await startPipeline(platformDescription, seedUrl, model, docsOptions)
        // The backend just replaced its own Orchestrator (reset_pipeline()),
        // a fresh event log starting again at index 0, drop whatever the
        // previous run had accumulated so the next fetch doesn't merge two
        // different runs' events together.
        allEventsRef.current = []
        doneRef.current = false
        setStarted(true)
        await fetchFull()
      } catch (err) {
        setError(messageFor(err, "Could not start the integration. Is the orchestrator running?"))
      }
    },
    [fetchFull]
  )

  const approve = useCallback(
    async (stageId: StageId) => {
      setError(null)
      try {
        await reviewStage(stageId, true)
        await fetchIncremental()
      } catch (err) {
        setError(messageFor(err, "Could not approve this stage."))
      }
    },
    [fetchIncremental]
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
        await fetchIncremental()
      } catch (err) {
        setError(messageFor(err, "Could not retry this stage."))
      }
    },
    [fetchIncremental]
  )

  const sendMessage = useCallback(
    async (message: string) => {
      setError(null)
      try {
        await sendMessageApi(message)
        await fetchFull()
      } catch (err) {
        setError(messageFor(err, "Could not reach the Orchestrator."))
      }
    },
    [fetchFull]
  )

  // Changes the model for the rest of the run (every subsequent stage run,
  // retry, or chat message), not just what /start chose, callable any
  // time, not only up front. Updates local state optimistically, since
  // /model's own response already confirms it, no need to wait for the
  // next poll tick.
  const changeModel = useCallback(async (nextModel?: string) => {
    setError(null)
    try {
      const result = await setModelApi(nextModel)
      setModelState(result.model)
    } catch (err) {
      setError(messageFor(err, "Could not change the model."))
    }
  }, [])

  // Replaces the current run with a fresh, blank one — the manual escape
  // hatch for "I don't want to wait for/continue this run." The old run
  // isn't deleted, the backend keeps it in history, it just stops being
  // the live/interactive one. 409s while a stage is genuinely busy (same
  // guard /start has), surfaced as a real error rather than silently
  // no-op-ing.
  const reset = useCallback(async () => {
    setError(null)
    try {
      await resetPipeline()
      allEventsRef.current = []
      doneRef.current = false
      await fetchFull()
    } catch (err) {
      setError(messageFor(err, "Could not restart, a stage may still be running."))
    }
  }, [fetchFull])

  // The counterpart to reset: makes this hook's own runId current again
  // instead of replacing it with a blank one. Only meaningful for a past,
  // non-current run — IntegrationScreen only ever renders the button that
  // calls this once isCurrent is already false. No local event/doneRef
  // reset needed like reset() does: this runId's events are already
  // correct as-is, resuming doesn't create a new run, it just flips which
  // one is current, so a plain refetch is enough to pick that up (and,
  // since applyEvents updates isCurrentRef on every fetch, the poll loop
  // above resumes on its own once this run reports is_current: true).
  const resume = useCallback(async () => {
    if (!runId) return
    setError(null)
    try {
      await resumeRun(runId)
      await fetchFull()
    } catch (err) {
      setError(messageFor(err, "Could not resume, another stage may still be running."))
    }
  }, [runId, fetchFull])

  return {
    events,
    currentStage,
    busy,
    started,
    model,
    error,
    isCurrent,
    start,
    approve,
    retry,
    sendMessage,
    changeModel,
    reset,
    resume,
  }
}
