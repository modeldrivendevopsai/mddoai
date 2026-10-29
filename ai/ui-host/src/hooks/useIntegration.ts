import { useCallback, useEffect, useRef, useState } from "react"
import {
  forkRun,
  getEvents,
  getProviders,
  getStageMetadata,
  rerunStage,
  resetPipeline,
  resumeRun,
  reviewStage,
  sendMessage as sendMessageApi,
  setModel as setModelApi,
  startPipeline,
} from "@/services/orchestrator.service"
import type { DocsOptions } from "orchestrator-types"
import { PIPELINE_EVENT_TYPES } from "orchestrator-types"
import type { OrchestratorEvent, Provider, StageDetail, StageId } from "orchestrator-types"

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
  const [replying, setReplying] = useState(false)
  const [started, setStarted] = useState(false)
  const [model, setModelState] = useState<string | null>(null)
  // Fetched once, not polled — ChatColumn's own model picker used to fetch
  // this itself; moved here so it stays purely prop-driven like every other
  // federated remote (see ai/ui-remote-chat/README.md).
  const [providers, setProviders] = useState<Provider[]>([])
  // Static, real per-stage input/output/real shape (see
  // integration_runner's STAGE_DETAILS), fetched once like providers below,
  // never per-run: it describes the pipeline itself, not any one run of it.
  const [stageDetails, setStageDetails] = useState<Record<StageId, StageDetail> | null>(null)
  const [error, setError] = useState<string | null>(null)
  const [isCurrent, setIsCurrent] = useState(true)
  // The real run this hook is currently showing, whether live or a past
  // one being viewed read-only — distinct from this hook's own `runId`
  // parameter above (only ever set when viewing a specific past run from
  // the URL; undefined for the live run). AttemptsBrowser needs this real
  // id regardless of which case it is.
  const [viewedRunId, setViewedRunId] = useState<string | null>(null)
  const pollingRef = useRef(false)
  // Set when a fetch is asked for while another is in flight. That one may
  // have been sent before the action that asked, so its answer can be stale:
  // the fetch in flight runs once more when it ends.
  const followUpRef = useRef(false)
  // Mirror isCurrent and replying for the interval poll below, which can't
  // depend on the state values directly without re-subscribing the effect
  // every tick.
  const isCurrentRef = useRef(true)
  const replyingRef = useRef(false)
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
  // The run the accumulated log belongs to. A chat answer can start a new run
  // in the background (the start_pipeline tool), after which an incremental
  // slice would be the new run's events appended to the old run's log.
  const runIdRef = useRef<string | null>(null)

  // Providers don't depend on runId (it's a global, not a per-run list), so
  // this fetches once per mount rather than re-running alongside the
  // runId-keyed polling effect below.
  useEffect(() => {
    getProviders()
      .then(setProviders)
      .catch(() => setProviders([]))
  }, [])

  // Same reasoning as providers above: real, static data describing the
  // pipeline's own stages, fetched once per mount, not tied to runId.
  useEffect(() => {
    getStageMetadata()
      .then((metadata) => setStageDetails(metadata.details))
      .catch(() => setStageDetails(null))
  }, [])

  const applyEvents = useCallback((body: Awaited<ReturnType<typeof getEvents>>, replace: boolean) => {
    allEventsRef.current = replace ? body.events : [...allEventsRef.current, ...body.events]
    runIdRef.current = body.run_id
    setEvents(allEventsRef.current)
    setCurrentStage(body.current_stage)
    setBusy(body.busy)
    setReplying(body.replying)
    replyingRef.current = body.replying
    setModelState(body.model)
    setIsCurrent(body.is_current)
    isCurrentRef.current = body.is_current
    setViewedRunId(body.run_id)
    // current_stage defaults to "docs" (index 0) even on a completely fresh,
    // never-started Orchestrator, it's only ever null once the whole
    // pipeline finishes. A raw pipeline event (PIPELINE_EVENT_TYPES) is the
    // only real signal that /start has actually been called — plain chat
    // events (user_message/message/message_failed/tool_called) can exist with zero real
    // pipeline activity, e.g. the LLM just asking a clarifying question, so
    // events.length alone isn't enough (a false positive here shows the
    // Stepper as "reviewing" and enables Retry for a stage that never ran).
    setStarted(allEventsRef.current.some((e) => PIPELINE_EVENT_TYPES.includes(e.type)))
    doneRef.current =
      !body.busy && !body.replying && (body.current_stage === null || allEventsRef.current.length === 0)
  }, [])

  // What's new since the accumulated log, or the whole log again when the
  // run changed underneath it (see runIdRef). Returns whether it replaced.
  const fetchNewEvents = useCallback(async () => {
    const body = await getEvents(allEventsRef.current.length, runId)
    if (runIdRef.current === null || body.run_id === runIdRef.current) return { body, replace: false }
    return { body: await getEvents(0, runId), replace: true }
  }, [runId])

  // Used by every action. After start/reset/fork, which empty the
  // accumulated log first, this fetches the whole log.
  const fetchIncremental = useCallback(async () => {
    if (pollingRef.current) {
      followUpRef.current = true
      return
    }
    pollingRef.current = true
    try {
      do {
        followUpRef.current = false
        const { body, replace } = await fetchNewEvents()
        // Asked for again while this one was out, so its answer may be
        // stale; the next round applies a fresh one.
        if (!followUpRef.current) applyEvents(body, replace)
      } while (followUpRef.current)
    } catch {
      // Transient network hiccup, the next poll tick or manual action retries.
    } finally {
      pollingRef.current = false
    }
  }, [applyEvents, fetchNewEvents])

  // The interval poll is defined and called entirely inside this effect
  // (not via the fetch callbacks above) so state updates stay scoped to
  // "an effect synchronizing with an external system on a timer," rather
  // than an effect reaching out to a click-handler's own helper.
  useEffect(() => {
    let cancelled = false
    // Switching which run is viewed (including live -> history or back) is a
    // fresh start: the previous run's accumulated event indices don't apply.
    allEventsRef.current = []
    runIdRef.current = null
    doneRef.current = false
    isCurrentRef.current = true
    replyingRef.current = false

    async function poll() {
      if (doneRef.current) return
      // A past, non-current run is a frozen log (see main.py's /events?run_id=
      // — "read-only, no polling loop needed"): once the first fetch confirms
      // that, every later tick is a no-op instead of a wasted request. The
      // exception is a chat answer still coming: when it starts a new run,
      // the run on screen stops being current before the answer lands in it.
      if (runId && !isCurrentRef.current && !replyingRef.current) return
      if (pollingRef.current) return
      pollingRef.current = true
      try {
        do {
          followUpRef.current = false
          const { body, replace } = await fetchNewEvents()
          if (!cancelled && !followUpRef.current) applyEvents(body, replace)
        } while (followUpRef.current && !cancelled)
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
  }, [applyEvents, fetchNewEvents, runId])

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
        await fetchIncremental()
      } catch (err) {
        setError(messageFor(err, "Could not start the integration. Is the orchestrator running?"))
      }
    },
    [fetchIncremental]
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
      // Set before sending, so the input waits from the first keypress. The
      // answer arrives through the events poll, which keeps running until
      // it does.
      setReplying(true)
      try {
        await sendMessageApi(message)
        doneRef.current = false
        await fetchIncremental()
      } catch (err) {
        // A 409 means an earlier message is still being answered (perhaps
        // from another tab): keep waiting for that answer instead.
        if ((err as { status?: number }).status === 409) {
          setError("Your message was not sent: the previous one is still being answered.")
          doneRef.current = false
          await fetchIncremental()
          return
        }
        setReplying(false)
        setError(messageFor(err, "Could not reach the Orchestrator."))
      }
    },
    [fetchIncremental]
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
      await fetchIncremental()
    } catch (err) {
      setError(messageFor(err, "Could not restart, a stage may still be running."))
    }
  }, [fetchIncremental])

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
      await fetchIncremental()
    } catch (err) {
      setError(messageFor(err, "Could not resume, another stage may still be running."))
    }
  }, [runId, fetchIncremental])

  // Starts a genuinely new run seeded with the currently-viewed run's own
  // real output up to (not including) fromStage, then pauses there - the
  // real target for "the problem was actually in an earlier stage." Uses
  // viewedRunId, not this hook's own runId parameter, since the sidebar
  // (SessionsList.tsx) navigates to every session via ?run=<id>, including
  // the current one - runId is just as often defined here as undefined,
  // viewedRunId is the one value that's always whichever run this hook is
  // actually showing right now, live or not.
  //
  // Returns the new run's real id (or undefined on failure) rather than
  // just resolving, unlike every other action here: fork is the only one
  // that hands the caller a DIFFERENT run than whichever one this hook is
  // currently keyed to (runId is a fixed, URL-derived parameter this hook
  // never changes itself - see IntegrationScreen.tsx's own component,
  // which owns the URL). A bare fetchIncremental() here would still ask for
  // *this* hook's own fixed runId, landing back on the now-non-current
  // source run instead of the new one - IntegrationScreen.tsx's own
  // onForkFrom is what actually navigates to the new id, this just reports
  // it. Resets local event tracking exactly like reset() does: the new run
  // has its own separate event history, mixing the old run's accumulated
  // events into it would misattribute them.
  const fork = useCallback(
    async (fromStage: StageId): Promise<string | undefined> => {
      if (!viewedRunId) return undefined
      setError(null)
      try {
        const result = await forkRun(viewedRunId, fromStage)
        allEventsRef.current = []
        doneRef.current = false
        return result.run_id
      } catch (err) {
        setError(messageFor(err, "Could not fork this run."))
        return undefined
      }
    },
    [viewedRunId]
  )

  return {
    events,
    currentStage,
    busy,
    replying,
    started,
    model,
    providers,
    stageDetails,
    error,
    isCurrent,
    viewedRunId,
    start,
    approve,
    retry,
    sendMessage,
    changeModel,
    reset,
    resume,
    fork,
  }
}
