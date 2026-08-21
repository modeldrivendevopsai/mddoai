import type {
  EventsResponse,
  MessageResponse,
  Provider,
  RerunOverrides,
  ResetResponse,
  ResumeResponse,
  ReviewResponse,
  RunSummary,
  StageId,
  StartedResponse,
} from "@/types/orchestrator"

// Talks to ai/orchestrator (a separate internal-only service, see
// vite.config.ts's /orchestrator-api proxy) — never ai-layer directly.
// ai/orchestrator itself is the only thing that talks to ai-layer/retrieval
// (see its own README), this service layer has no other client to defer to.

// FastAPI's HTTPException responses carry a real, useful {"detail": "..."}
// body (e.g. "'psm' is not the current pending stage", or a downstream
// error's own message on /message's 500). Surface that instead of just the
// status code, a bare "request failed: 500" told a real user nothing.
async function errorFor(label: string, res: Response): Promise<Error> {
  try {
    const body = await res.json()
    if (typeof body?.detail === "string") {
      return new Error(body.detail)
    }
  } catch {
    // Body wasn't JSON (or had no "detail"), fall through to the generic message.
  }
  return new Error(`${label} request failed: ${res.status}`)
}

// Same shape as rerunStage's overrides, minus seed_url (that's the required
// seedUrl param below, not an optional override here) — ai/orchestrator's
// docs stage takes these real retrieval parameters at start time too, not
// only on a retry (see main.py's StartRequest).
export type DocsOptions = Omit<RerunOverrides, "seed_url">

export async function startPipeline(
  platformDescription: string,
  seedUrl: string,
  model?: string,
  docsOptions?: DocsOptions
): Promise<StartedResponse> {
  const res = await fetch("/orchestrator-api/start", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      platform_description: platformDescription,
      seed_url: seedUrl,
      model,
      ...docsOptions,
    }),
  })

  if (!res.ok) {
    throw await errorFor("Start", res)
  }

  return res.json()
}

// Replaces the current run with a fresh, blank one (no stage started yet) —
// the empty-state counterpart to startPipeline(), which does the same
// replacement but also kicks off the docs stage immediately. The old run
// isn't deleted, ai/orchestrator's reset_pipeline() keeps it in history.
// 409s (surfaced via errorFor's real detail message) if a stage is
// genuinely still running.
export async function resetPipeline(): Promise<ResetResponse> {
  const res = await fetch("/orchestrator-api/reset", { method: "POST" })

  if (!res.ok) {
    throw await errorFor("Reset", res)
  }

  return res.json()
}

// The counterpart to resetPipeline(): makes a past run current again,
// picking up exactly where it left off (nothing about its progress,
// constraints, or events is replayed or reset). 404s if runId is unknown,
// 409s (both surfaced via errorFor's real detail message) if a stage is
// genuinely still running on whichever run is current right now.
export async function resumeRun(runId: string): Promise<ResumeResponse> {
  const res = await fetch(`/orchestrator-api/resume/${runId}`, { method: "POST" })

  if (!res.ok) {
    throw await errorFor("Resume", res)
  }

  return res.json()
}

export async function getProviders(): Promise<Provider[]> {
  const res = await fetch("/orchestrator-api/providers")

  if (!res.ok) {
    throw await errorFor("Providers", res)
  }

  return res.json()
}

export async function reviewStage(
  stageId: StageId,
  approved: boolean,
  correction?: string
): Promise<ReviewResponse> {
  const res = await fetch(`/orchestrator-api/review/${stageId}`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ approved, correction }),
  })

  if (!res.ok) {
    throw await errorFor("Review", res)
  }

  return res.json()
}

export async function rerunStage(
  stageId: StageId,
  overrides?: RerunOverrides
): Promise<StartedResponse> {
  const res = await fetch(`/orchestrator-api/rerun/${stageId}`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(overrides ? { overrides } : {}),
  })

  if (!res.ok) {
    throw await errorFor("Rerun", res)
  }

  return res.json()
}

// runId omitted (or the current run's own id) polls the live run as normal.
// Any other runId reads that past run's full event log instead — read-only
// history, see ai/orchestrator/README.md's session-history section.
export async function getEvents(sinceIndex = 0, runId?: string): Promise<EventsResponse> {
  const params = new URLSearchParams({ since_index: String(sinceIndex) })
  if (runId) params.set("run_id", runId)
  const res = await fetch(`/orchestrator-api/events?${params}`)

  if (!res.ok) {
    throw await errorFor("Events", res)
  }

  return res.json()
}

// In-memory only (see integration_runner/runs.py's list_runs()) — every
// run this process has seen, newest first, not persisted across a restart.
export async function getRuns(): Promise<RunSummary[]> {
  const res = await fetch("/orchestrator-api/runs")

  if (!res.ok) {
    throw await errorFor("Runs", res)
  }

  return res.json()
}

export async function sendMessage(message: string): Promise<MessageResponse> {
  const res = await fetch("/orchestrator-api/message", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ message }),
  })

  if (!res.ok) {
    throw await errorFor("Message", res)
  }

  return res.json()
}

// Changes the model for the rest of the run, not just what /start chose,
// every subsequent real chat() call (a stage run, a retry, or a chat
// message) picks this up. undefined/omitted means back to ai-layer's own
// automatic routing.
export async function setModel(model?: string): Promise<{ model: string | null }> {
  const res = await fetch("/orchestrator-api/model", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ model: model ?? null }),
  })

  if (!res.ok) {
    throw await errorFor("Model", res)
  }

  return res.json()
}
