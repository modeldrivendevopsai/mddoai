import { describe, expect, it } from "vitest"
import type { OrchestratorEvent } from "@/types/orchestrator"
import { constraintsForStage } from "./stageEvents"

function constraintAdded(stage: OrchestratorEvent["stage"], constraint: string): OrchestratorEvent {
  return { type: "constraint_added", stage, timestamp: 0, data: { constraint } }
}

describe("constraintsForStage", () => {
  it("returns constraints for the given stage, oldest first", () => {
    const events = [constraintAdded("psm", "Use camelCase"), constraintAdded("psm", "Fix: missing RetryPolicy")]

    expect(constraintsForStage(events, "psm")).toEqual(["Use camelCase", "Fix: missing RetryPolicy"])
  })

  it("ignores constraints recorded for a different stage", () => {
    const events = [constraintAdded("atl", "Use kebab-case job names"), constraintAdded("psm", "Fix: bad thing")]

    expect(constraintsForStage(events, "psm")).toEqual(["Fix: bad thing"])
  })

  it("ignores non-constraint_added events", () => {
    const events: OrchestratorEvent[] = [
      { type: "call_started", stage: "psm", timestamp: 0, data: {} },
      constraintAdded("psm", "Fix: bad thing"),
    ]

    expect(constraintsForStage(events, "psm")).toEqual(["Fix: bad thing"])
  })

  it("returns an empty array when nothing was recorded", () => {
    expect(constraintsForStage([], "psm")).toEqual([])
  })
})
