import type { OrchestratorEvent } from "orchestrator-types"

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
