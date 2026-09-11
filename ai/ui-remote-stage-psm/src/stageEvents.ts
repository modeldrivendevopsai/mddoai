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

// The real platform_description this run was started with - carried on the
// docs stage's own first call_started event (see integration_runner's
// StartRequest), the only place it's recorded; every later stage's own
// context also has it, but the docs stage's is always first and always
// present for a real, started run.
export function platformDescriptionFromEvents(events: OrchestratorEvent[]): string | null {
  const started = events.find((e) => e.stage === "docs" && e.type === "call_started")
  const description = started?.data?.platform_description
  return typeof description === "string" ? description : null
}
