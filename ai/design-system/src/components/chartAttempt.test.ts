import { describe, expect, it } from "vitest"
import { describe as wordsOf, dialogOf } from "./chartAttempt"
import type { ChartAttempt } from "./chartAttempt"

const attempt = (over: Partial<ChartAttempt> = {}): ChartAttempt => ({ number: 1, rounds: 3, outcome: "passed", ...over })

describe("describe", () => {
  it("reads every number a draft draws, in the order the box draws them, ending with the check", () => {
    const words = wordsOf(
      attempt({ number: 2, rounds: 1, given: [{ text: "a" }, { text: "b" }], learned: [{ text: "c", learned: true }], promoted: ["a"], mock: true })
    )

    expect(words).toBe("Draft 2 · 1 try · 2 typed by a person · 1 learned from a failed check · 1 made permanent · check passed · mock: no model called")
  })

  it("leaves out what a draft does not have, and the number when it is not known", () => {
    expect(wordsOf(attempt({ number: null, rounds: null, outcome: "none" }))).toBe("Draft · not checked")
  })
})

describe("dialogOf", () => {
  const drafts = [
    attempt({ number: 1, learned: [{ text: "Fix: one", learned: true }] }),
    attempt({ number: 2, given: [{ text: "typed", learned: false }], promoted: ["typed", "Fix: one"] }),
  ]

  it("is nothing for a draft that is no longer there, as when the drafts changed while a chip was open", () => {
    expect(dialogOf({ kind: "learned", index: 5 }, drafts, [])).toBeNull()
  })

  it("names the stage in the title when it is given one, and not otherwise", () => {
    expect(dialogOf({ kind: "learned", index: 0 }, drafts, [], "ATL")?.title).toBe("ATL · Fixes learned in draft 1")
    expect(dialogOf({ kind: "learned", index: 0 }, drafts, [])?.title).toBe("Fixes learned in draft 1")
  })

  it("says this draft when the draft has no number", () => {
    expect(dialogOf({ kind: "given", index: 0 }, [attempt({ number: null, given: [{ text: "x" }] })], [])?.title).toBe("Rules typed before this draft")
  })

  it("marks only the rules of the draft that a person made permanent after it", () => {
    const dialog = dialogOf({ kind: "given", index: 1 }, drafts, [])

    expect(dialog?.items).toEqual([{ text: "typed", learned: false, permanent: true }])
    expect(dialogOf({ kind: "learned", index: 0 }, drafts, [])?.items).toEqual([{ text: "Fix: one", learned: true, permanent: false }])
  })

  it("lists what was made permanent with where each rule came from, found among the stage's rules, and typed for one nobody listed", () => {
    const dialog = dialogOf({ kind: "permanent", index: 1 }, [...drafts.slice(0, 1), { ...drafts[1], promoted: ["Fix: one", "typed", "straight into the list"] }], [])

    expect(dialog?.items.map((item) => [item.text, item.learned])).toEqual([
      ["Fix: one", true],
      ["typed", false],
      ["straight into the list", false],
    ])
  })

  it("passes the waiting rules through as they are", () => {
    const waiting = [{ text: "next", learned: false }]

    expect(dialogOf({ kind: "waiting" }, [], waiting)?.items).toBe(waiting)
  })
})
