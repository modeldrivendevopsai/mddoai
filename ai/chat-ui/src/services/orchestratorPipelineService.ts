import type {
  EventsResponse,
  NudgeResponse,
  Provider,
  RerunOverrides,
  ResetResponse,
  ReviewResponse,
  StageId,
  StartedResponse,
} from "@/orchestrator/types"

// Talks to ai/orchestrator (a separate internal-only service, see
// vite.config.ts's /orchestrator-api proxy) — never ai-layer directly.
// ai/orchestrator itself is the only thing that talks to ai-layer/retrieval
// (see its own README), this service layer has no other client to defer to.

// FastAPI's HTTPException responses carry a real, useful {"detail": "..."}
// body (e.g. "'psm' is not the current pending stage", or a downstream
// error's own message on /nudge's 500). Surface that instead of just the
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

// Discards the current run with no new run to replace it, the empty-state
// counterpart to startPipeline(). 409s (surfaced via errorFor's real detail
// message) if a stage is genuinely still running.
export async function resetPipeline(): Promise<ResetResponse> {
  const res = await fetch("/orchestrator-api/reset", { method: "POST" })

  if (!res.ok) {
    throw await errorFor("Reset", res)
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

export async function getEvents(sinceIndex = 0): Promise<EventsResponse> {
  const res = await fetch(`/orchestrator-api/events?since_index=${sinceIndex}`)

  if (!res.ok) {
    throw await errorFor("Events", res)
  }

  return res.json()
}

export async function nudge(message: string): Promise<NudgeResponse> {
  const res = await fetch("/orchestrator-api/nudge", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ message }),
  })

  if (!res.ok) {
    throw await errorFor("Nudge", res)
  }

  return res.json()
}

// Changes the model for the rest of the run, not just what /start chose,
// every subsequent real chat() call (a stage run, a retry, or a nudge)
// picks this up. undefined/omitted means back to ai-layer's own automatic
// routing.
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
