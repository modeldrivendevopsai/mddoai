import { describe, expect, it } from "vitest"
import type { OrchestratorEvent } from "orchestrator-types"
import { constraintsForStage } from "./stageEvents"

function constraintAdded(stage: OrchestratorEvent["stage"], constraint: string): OrchestratorEvent {
  return { type: "constraint_added", stage, timestamp: 0, data: { constraint } }
}

describe("constraintsForStage", () => {
  it("returns constraints for the given stage, oldest first", () => {
    const events = [
      constraintAdded("acceleo", "Never use `def` as a template parameter name"),
      constraintAdded("acceleo", "Fix: token \"context\" is invalid"),
    ]

    expect(constraintsForStage(events, "acceleo")).toEqual([
      "Never use `def` as a template parameter name",
      "Fix: token \"context\" is invalid",
    ])
  })

  it("ignores constraints recorded for a different stage", () => {
    const events = [constraintAdded("atl", "Use kebab-case job names"), constraintAdded("acceleo", "Fix: bad thing")]

    expect(constraintsForStage(events, "acceleo")).toEqual(["Fix: bad thing"])
  })

  it("ignores non-constraint_added events", () => {
    const events: OrchestratorEvent[] = [
      { type: "call_started", stage: "acceleo", timestamp: 0, data: {} },
      constraintAdded("acceleo", "Fix: bad thing"),
    ]

    expect(constraintsForStage(events, "acceleo")).toEqual(["Fix: bad thing"])
  })

  it("returns an empty array when nothing was recorded", () => {
    expect(constraintsForStage([], "acceleo")).toEqual([])
  })
})
