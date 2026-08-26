import type { OrchestratorEvent, StageId } from "@/types/orchestrator"
import type { DocsOptions } from "@/services/orchestrator.service"

// The most recent completed/failed result for a given stage — a stage can
// be retried, so "latest" (not "first") is the one worth showing. Called by
// IntegrationScreen, which computes each stage panel's latestResult prop
// from this (both the active pending stage's and a viewed past stage's).
export function latestCallResult(events: OrchestratorEvent[], stage: StageId | null): OrchestratorEvent | null {
  if (!stage) return null
  for (let i = events.length - 1; i >= 0; i--) {
    const event = events[i]
    if (event.stage === stage && (event.type === "call_completed" || event.type === "call_failed")) {
      return event
    }
  }
  return null
}

// Every correction recorded for a given stage so far, oldest first — the
// real record behind that stage's constraint-driven retries, not just the
// one currently sitting in the (uncommitted) correction textarea. Used by
// PsmStagePanel's Constraints tab to show what's already been applied.
export function constraintsForStage(events: OrchestratorEvent[], stage: StageId): string[] {
  return events
    .filter((e) => e.stage === stage && e.type === "constraint_added")
    .map((e) => e.data?.constraint)
    .filter((c): c is string => typeof c === "string")
}

// The very first docs call_started event's data — the real
// platform_description/seed_url/docsOptions this run was actually started
// with. A retry reuses last_context server-side rather than firing a new
// call_started with fresh data, so this first event stays the one
// authoritative record of what the run is actually for, for as long as the
// run exists. Used by Restart to re-run the same platform from scratch,
// as opposed to "Add a new platform", which really does want a blank form
// (see IntegrationScreen's handleRestart vs. its ?new=1 handling).
export function originalDocsInput(
  events: OrchestratorEvent[]
): { platformDescription: string; seedUrl: string; docsOptions: DocsOptions } | null {
  const started = events.find((e) => e.stage === "docs" && e.type === "call_started")
  const data = started?.data
  if (!data || typeof data.platform_description !== "string" || typeof data.seed_url !== "string") {
    return null
  }
  const docsOptions: DocsOptions = {}
  if (typeof data.hint === "string") docsOptions.hint = data.hint
  if (Array.isArray(data.exclude_urls)) docsOptions.exclude_urls = data.exclude_urls as string[]
  if (typeof data.max_pages === "number") docsOptions.max_pages = data.max_pages
  if (typeof data.max_depth === "number") docsOptions.max_depth = data.max_depth
  if (typeof data.force_refresh === "boolean") docsOptions.force_refresh = data.force_refresh
  if (typeof data.mock === "boolean") docsOptions.mock = data.mock
  return { platformDescription: data.platform_description, seedUrl: data.seed_url, docsOptions }
}
