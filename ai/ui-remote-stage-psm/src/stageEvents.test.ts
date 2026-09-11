import { describe, expect, it } from "vitest"
import type { OrchestratorEvent } from "orchestrator-types"
import { constraintsForStage, platformDescriptionFromEvents } from "./stageEvents"

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

describe("platformDescriptionFromEvents", () => {
  it("reads platform_description off the docs stage's own first call_started event", () => {
    const events: OrchestratorEvent[] = [
      { type: "call_started", stage: "docs", timestamp: 0, data: { platform_description: "GitLab CI", seed_url: "https://x" } },
    ]

    expect(platformDescriptionFromEvents(events)).toBe("GitLab CI")
  })

  it("ignores a call_started event for a different stage", () => {
    const events: OrchestratorEvent[] = [
      { type: "call_started", stage: "psm", timestamp: 0, data: { platform_description: "not this one" } },
    ]

    expect(platformDescriptionFromEvents(events)).toBeNull()
  })

  it("returns null when no run has started yet", () => {
    expect(platformDescriptionFromEvents([])).toBeNull()
  })
})
