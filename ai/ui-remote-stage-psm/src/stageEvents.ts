import type { OrchestratorEvent, StageId } from "orchestrator-types"

// Every correction recorded for a given stage so far, oldest first — the
// real record behind that stage's constraint-driven retries, not just the
// one currently sitting in the (uncommitted) correction textarea. Local to
// this package (not orchestrator-types, which is scoped to types plus
// STAGES/PIPELINE_EVENT_TYPES, not general utility functions): PsmStagePanel
// is the only consumer today.
export function constraintsForStage(events: OrchestratorEvent[], stage: StageId): string[] {
  return events
    .filter((e) => e.stage === stage && e.type === "constraint_added")
    .map((e) => e.data?.constraint)
    .filter((c): c is string => typeof c === "string")
}
