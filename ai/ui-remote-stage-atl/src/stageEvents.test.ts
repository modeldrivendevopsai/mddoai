import { describe, expect, it } from "vitest"
import type { OrchestratorEvent } from "orchestrator-types"
import { constraintsForStage } from "./stageEvents"

function constraintAdded(stage: OrchestratorEvent["stage"], constraint: string): OrchestratorEvent {
  return { type: "constraint_added", stage, timestamp: 0, data: { constraint } }
}

describe("constraintsForStage", () => {
  it("returns constraints for the given stage, oldest first", () => {
    const events = [constraintAdded("atl", "Use kebab-case job names"), constraintAdded("atl", "Fix: missing type mapping")]

    expect(constraintsForStage(events, "atl")).toEqual(["Use kebab-case job names", "Fix: missing type mapping"])
  })

  it("ignores constraints recorded for a different stage", () => {
    const events = [constraintAdded("psm", "Use camelCase"), constraintAdded("atl", "Fix: bad thing")]

    expect(constraintsForStage(events, "atl")).toEqual(["Fix: bad thing"])
  })

  it("ignores non-constraint_added events", () => {
    const events: OrchestratorEvent[] = [
      { type: "call_started", stage: "atl", timestamp: 0, data: {} },
      constraintAdded("atl", "Fix: bad thing"),
    ]

    expect(constraintsForStage(events, "atl")).toEqual(["Fix: bad thing"])
  })

  it("returns an empty array when nothing was recorded", () => {
    expect(constraintsForStage([], "atl")).toEqual([])
  })
})
