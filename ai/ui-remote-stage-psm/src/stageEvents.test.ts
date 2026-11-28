import { describe, expect, it } from "vitest"
import type { OrchestratorEvent } from "orchestrator-types"
import { platformDescriptionFromEvents } from "./stageEvents"

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
