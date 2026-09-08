import type {
  AttemptDetail,
  BrokenReference,
  DocsOptions,
  EventsResponse,
  ManifestEntry,
  MessageResponse,
  PresetMetadata,
  Provider,
  PromptConfig,
  PromptDiff,
  PromptPreview,
  RerunOverrides,
  ResetResponse,
  ResumeResponse,
  ReviewResponse,
  RunSummary,
  StageId,
  StartedResponse,
} from "orchestrator-types"

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

// --- Modular prompt builder + attempts browser (see ai/orchestrator's own
// routes/prompt_config.py, routes/attempts.py, both thin proxies down to
// integration_runner then psm_agent, the service that actually owns this
// data) -----------------------------------------------------------------

export async function getPromptConfig(name: string, preset: string): Promise<PromptConfig> {
  const res = await fetch(`/orchestrator-api/psm/prompt-config/${name}/${preset}`)
  if (!res.ok) throw await errorFor("Prompt config", res)
  return res.json()
}

export async function savePromptConfig(name: string, preset: string, config: PromptConfig): Promise<PromptConfig> {
  const res = await fetch(`/orchestrator-api/psm/prompt-config/${name}/${preset}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(config),
  })
  if (!res.ok) throw await errorFor("Save prompt config", res)
  return res.json()
}

export async function listPromptPresets(name: string): Promise<PresetMetadata[]> {
  const res = await fetch(`/orchestrator-api/psm/prompt-config/${name}/presets`)
  if (!res.ok) throw await errorFor("Prompt presets", res)
  return (await res.json()).presets
}

export async function previewPromptConfig(name: string, preset: string): Promise<PromptPreview> {
  const res = await fetch(`/orchestrator-api/psm/prompt-config/${name}/${preset}/preview`, { method: "POST" })
  if (!res.ok) throw await errorFor("Prompt preview", res)
  return res.json()
}

export async function listAvailableFiles(): Promise<string[]> {
  const res = await fetch("/orchestrator-api/psm/available-files")
  if (!res.ok) throw await errorFor("Available files", res)
  return (await res.json()).files
}

export async function getPromptConfigHistory(name: string, preset: string): Promise<string[]> {
  const res = await fetch(`/orchestrator-api/psm/prompt-config/${name}/${preset}/history`)
  if (!res.ok) throw await errorFor("Prompt history", res)
  return (await res.json()).versions
}

export async function diffPromptConfigVersions(
  name: string,
  preset: string,
  versionA: string,
  versionB: string
): Promise<PromptDiff> {
  const params = new URLSearchParams({ a: versionA, b: versionB })
  const res = await fetch(`/orchestrator-api/psm/prompt-config/${name}/${preset}/diff?${params}`)
  if (!res.ok) throw await errorFor("Prompt diff", res)
  return res.json()
}

export async function restorePromptConfigVersion(name: string, preset: string, version: string): Promise<PromptConfig> {
  const res = await fetch(`/orchestrator-api/psm/prompt-config/${name}/${preset}/restore/${version}`, {
    method: "POST",
  })
  if (!res.ok) throw await errorFor("Restore prompt version", res)
  return res.json()
}

export async function revertPromptConfig(name: string, preset: string): Promise<PromptConfig> {
  const res = await fetch(`/orchestrator-api/psm/prompt-config/${name}/${preset}/revert`, { method: "POST" })
  if (!res.ok) throw await errorFor("Revert prompt config", res)
  return res.json()
}

export async function promoteConfigToDefault(name: string, preset: string): Promise<PromptConfig> {
  const res = await fetch(`/orchestrator-api/psm/prompt-config/${name}/${preset}/promote-to-default`, {
    method: "POST",
  })
  if (!res.ok) throw await errorFor("Promote config to default", res)
  return res.json()
}

export async function checkPromptReferences(name: string, preset: string): Promise<BrokenReference[]> {
  const res = await fetch(`/orchestrator-api/psm/prompt-config/${name}/${preset}/check-references`)
  if (!res.ok) throw await errorFor("Check prompt references", res)
  return (await res.json()).broken
}

export async function addLearnedConstraints(name: string, preset: string, constraints: string[]): Promise<PromptConfig> {
  const res = await fetch(`/orchestrator-api/psm/prompt-config/${name}/${preset}/learned-constraints`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ constraints }),
  })
  if (!res.ok) throw await errorFor("Add learned constraints", res)
  return res.json()
}

export async function removeLearnedConstraint(name: string, preset: string, constraint: string): Promise<PromptConfig> {
  const res = await fetch(`/orchestrator-api/psm/prompt-config/${name}/${preset}/learned-constraints`, {
    method: "DELETE",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ constraint }),
  })
  if (!res.ok) throw await errorFor("Remove learned constraint", res)
  return res.json()
}

// Run-aware (see stages/psm/actions.py's own promote_constraints): no
// name/preset here, the backend infers both from the current run's own
// latest, real, successfully-validated result.
export async function promoteConstraints(constraints: string[]): Promise<PromptConfig> {
  const res = await fetch("/orchestrator-api/psm/promote-constraints", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ constraints }),
  })
  if (!res.ok) throw await errorFor("Promote constraints", res)
  return res.json()
}

export async function getRunManifest(runId: string): Promise<ManifestEntry[]> {
  const res = await fetch(`/orchestrator-api/runs/${runId}/manifest`)
  if (!res.ok) throw await errorFor("Run manifest", res)
  return (await res.json()).attempts
}

export async function getAttempt(runId: string, stage: string, attempt: string): Promise<AttemptDetail> {
  const res = await fetch(`/orchestrator-api/runs/${runId}/${stage}/${attempt}`)
  if (!res.ok) throw await errorFor("Attempt", res)
  return res.json()
}
