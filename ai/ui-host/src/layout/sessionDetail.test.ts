import { describe, expect, it } from "vitest"
import { sessionDetail } from "./sessionDetail"
import type { Session } from "../services/sessions.service"

const NOW = new Date("2026-11-11T12:00:00Z")
const secondsAgo = (seconds: number) => NOW.getTime() / 1000 - seconds

const session = (over: Partial<Session>): Session => ({
  id: "r1",
  name: "Woodpecker CI",
  type: "platform",
  state: "normal",
  stage: "psm",
  startedAt: secondsAgo(7200),
  ...over,
})

describe("sessionDetail", () => {
  it("says the stage by the name the stepper uses, then how long ago the run began", () => {
    expect(sessionDetail(session({}), NOW)).toBe("PSM, 2 hours ago")
    expect(sessionDetail(session({ stage: "generation" }), NOW)).toBe("Generation + Test, 2 hours ago")
  })

  it("says Complete for a run that has finished every stage", () => {
    expect(sessionDetail(session({ stage: null }), NOW)).toBe("Complete, 2 hours ago")
  })

  it("gives only the stage for a run nothing has happened on", () => {
    expect(sessionDetail(session({ startedAt: null, stage: "docs" }), NOW)).toBe("Docs")
  })

  it("tells two runs of one platform apart", () => {
    const first = sessionDetail(session({ startedAt: secondsAgo(60) }), NOW)
    const second = sessionDetail(session({ startedAt: secondsAgo(86400 * 3) }), NOW)

    expect(first).not.toBe(second)
  })
})
