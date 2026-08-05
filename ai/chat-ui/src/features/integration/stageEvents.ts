import type { OrchestratorEvent, StageId } from "@/types/orchestrator"

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
