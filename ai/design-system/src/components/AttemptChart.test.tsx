// @vitest-environment jsdom
import { cleanup, fireEvent, render, screen, within } from "@testing-library/react"
import { afterEach, beforeEach, describe, expect, it, vi } from "vitest"
import { AttemptChart } from "./AttemptChart"
import type { ChartAttempt } from "./AttemptChart"

afterEach(cleanup)

const attempt = (over: Partial<ChartAttempt> = {}): ChartAttempt => ({
  number: 1,
  rounds: 5,
  outcome: "passed",
  ...over,
})

const rule = (text: string, learned = true) => ({ text, learned })

const drafts = (root: HTMLElement) => Array.from(root.querySelectorAll<HTMLElement>(".mdd-attempt-chart__draft"))
const links = (root: HTMLElement) => Array.from(root.querySelectorAll<HTMLElement>(".mdd-attempt-chart__link"))
const triesOf = (draft: Element) => draft.querySelector(".mdd-attempt-chart__tries")?.textContent

// Two drafts as a stage goes: the first learned three fixes and failed, a person typed a rule,
// and the second learned one fix and passed.
const STORY: ChartAttempt[] = [
  attempt({ number: 1, rounds: 11, outcome: "failed", learned: [rule("Fix: a"), rule("Fix: b"), rule("Fix: c")] }),
  attempt({ number: 2, rounds: 2, given: [rule("never use def", false)], learned: [rule("Fix: d")] }),
]

describe("AttemptChart: the row", () => {
  it("draws one box per draft, oldest first, each with its tries", () => {
    const { container } = render(<AttemptChart attempts={[attempt({ rounds: 7 }), attempt({ number: 2, rounds: 4 }), attempt({ number: 3, rounds: 1 })]} />)

    expect(drafts(container).map(triesOf)).toEqual(["7", "4", "1"])
  })

  it("joins the boxes with an arrow each, and none after the last", () => {
    const { container } = render(<AttemptChart attempts={[attempt(), attempt({ number: 2 }), attempt({ number: 3 })]} />)

    expect(links(container)).toHaveLength(2)
    expect(container.querySelectorAll(".mdd-attempt-chart__head")).toHaveLength(2)
  })

  it("draws no draft number and no sentence: the row is boxes, arrows and chips", () => {
    const { container } = render(<AttemptChart attempts={STORY} />)

    expect(container.textContent).not.toContain("#")
    expect(container.querySelectorAll("p")).toHaveLength(0)
    expect(screen.queryByRole("dialog")).toBeNull()
  })

  it("tints each box by whether its check passed, failed, stopped on an error, or was not checked", () => {
    const { container } = render(
      <AttemptChart attempts={[attempt({ outcome: "passed" }), attempt({ outcome: "failed" }), attempt({ outcome: "error" }), attempt({ outcome: "none" })]} />
    )

    expect(drafts(container).map((d) => d.className.match(/is-(passed|failed|error|none)/)?.[1])).toEqual(["passed", "failed", "error", "none"])
  })

  it("ends a box with a tick, a cross or a warning, and nothing for a draft nothing checked", () => {
    const { container } = render(
      <AttemptChart attempts={[attempt({ outcome: "passed" }), attempt({ outcome: "failed" }), attempt({ outcome: "error" }), attempt({ outcome: "none" })]} />
    )

    const marks = drafts(container).map((d) => d.querySelector(".mdd-attempt-chart__mark svg")?.getAttribute("class")?.match(/lucide-([\w-]+)/)?.[1])
    expect(marks).toEqual(["check", "x", "triangle-alert", undefined])
  })

  it("keeps a dashed edge and a flask for a mock draft, since it called no model", () => {
    const { container } = render(<AttemptChart attempts={[attempt({ mock: true })]} />)

    expect(drafts(container)[0].classList.contains("is-mock")).toBe(true)
    expect(container.querySelector(".mdd-attempt-chart__mock")?.getAttribute("title")).toBe("mock: no model called")
  })

  it("shows the draft's number where there are no tries to show, for a stage with no loop", () => {
    const { container } = render(<AttemptChart attempts={[attempt({ number: 2, rounds: null })]} />)

    expect(triesOf(drafts(container)[0])).toBe("#2")
  })

  it("renders nothing for no drafts and nothing waiting", () => {
    const { container } = render(<AttemptChart attempts={[]} />)

    expect(container.firstChild).toBeNull()
  })
})

describe("AttemptChart: the chips", () => {
  it("shows what a draft learned as a wrench chip with its count, and none for a draft that learned nothing", () => {
    const { container } = render(<AttemptChart attempts={STORY.concat(attempt({ number: 3, rounds: 1 }))} />)

    const chips = drafts(container).map((d) => d.querySelector(".mdd-attempt-chart__chip")?.textContent ?? null)
    expect(chips).toEqual(["3", "1", null])
    expect(drafts(container)[0].querySelector(".mdd-attempt-chart__chip .lucide-wrench")).not.toBeNull()
  })

  it("shows the rules a person typed between two drafts as a person chip on the arrow, and nothing on an arrow with none", () => {
    const { container } = render(<AttemptChart attempts={STORY.concat(attempt({ number: 3, rounds: 1 }))} />)

    const onArrows = links(container).map((l) => l.querySelector(".mdd-attempt-chart__chip")?.textContent ?? null)
    expect(onArrows).toEqual(["1", null])
    expect(links(container)[0].querySelector(".lucide-user")).not.toBeNull()
  })

  it("puts an arrow with a chip before the first draft when a person typed something before it", () => {
    const { container } = render(<AttemptChart attempts={[attempt({ given: [rule("early", false)] })]} />)

    expect(links(container)).toHaveLength(1)
    expect(links(container)[0].nextElementSibling?.classList.contains("mdd-attempt-chart__draft")).toBe(true)
  })

  it("says in words on hover what each number counts", () => {
    const { container } = render(<AttemptChart attempts={STORY} />)

    expect(screen.getByTitle("11 tries")).toBeTruthy()
    expect(screen.getByTitle("3 learned from failed checks. Press to read them.")).toBeTruthy()
    expect(screen.getByTitle("1 learned from a failed check. Press to read it.")).toBeTruthy()
    expect(screen.getByTitle("1 typed by a person. Press to read it.")).toBeTruthy()
    expect(drafts(container)[1].getAttribute("title")).toBe("Draft 2 · 2 tries · 1 typed by a person · 1 learned from a failed check · check passed")
  })

  it("shows rules a person made permanent after a draft as an infinity chip, only on that draft", () => {
    const { container } = render(<AttemptChart attempts={[attempt({ promoted: ["keep this", "and this"] }), attempt({ number: 2 })]} />)

    expect(drafts(container).map((d) => d.querySelector(".mdd-attempt-chart__chip.is-permanent")?.textContent ?? null)).toEqual(["2", null])
    expect(container.querySelector(".mdd-attempt-chart__chip .lucide-infinity")).not.toBeNull()
  })

  it("hangs rules typed since the last draft off a dashed arrow with no head, so no draft is shown to have them", () => {
    const { container } = render(<AttemptChart attempts={[attempt()]} waiting={[rule("next time", false)]} />)

    const waiting = container.querySelector(".mdd-attempt-chart__link.is-waiting")
    expect(waiting?.querySelector(".mdd-attempt-chart__chip")?.textContent).toBe("1")
    expect(waiting?.querySelector(".mdd-attempt-chart__head")).toBeNull()
  })

  it("draws only the waiting chip for a stage that has no draft yet", () => {
    const { container } = render(<AttemptChart attempts={[]} waiting={[rule("next time", false)]} />)

    expect(drafts(container)).toHaveLength(0)
    expect(screen.getByRole("button", { name: /typed by a person/ })).toBeTruthy()
  })
})

describe("AttemptChart: the dialog", () => {
  it("opens a numbered list of what a draft learned when its wrench chip is pressed", () => {
    render(<AttemptChart attempts={STORY} label="ATL" />)

    fireEvent.click(screen.getByRole("button", { name: /^3 learned from failed checks/ }))

    const dialog = screen.getByRole("dialog", { name: "ATL · Fixes learned in draft 1" })
    expect(within(dialog).getAllByRole("listitem").map((li) => li.querySelector("p")?.textContent)).toEqual(["Fix: a", "Fix: b", "Fix: c"])
    expect(dialog.querySelector("ol")).not.toBeNull()
  })

  it("opens the rules a person typed before a draft when the chip on the arrow into it is pressed", () => {
    render(<AttemptChart attempts={STORY} />)

    fireEvent.click(screen.getByRole("button", { name: /typed by a person/ }))

    const dialog = screen.getByRole("dialog", { name: "Rules typed before draft 2" })
    expect(within(dialog).getByText("never use def")).toBeTruthy()
    expect(within(dialog).getByText("Typed by a person")).toBeTruthy()
    expect(within(dialog).queryByText("Fix: d")).toBeNull()
  })

  it("opens the rules typed since the last draft from the waiting chip", () => {
    render(<AttemptChart attempts={[attempt()]} waiting={[rule("one", false), rule("two", false)]} />)

    fireEvent.click(screen.getByRole("button", { name: /2 typed by a person/ }))

    const dialog = screen.getByRole("dialog", { name: "Rules typed since the last draft" })
    expect(within(dialog).getAllByRole("listitem")).toHaveLength(2)
    expect(within(dialog).getByText("No draft has started with these yet.")).toBeTruthy()
  })

  it("marks a rule a person made permanent afterwards", () => {
    render(<AttemptChart attempts={[attempt({ learned: [rule("Fix: keep"), rule("Fix: drop")], promoted: ["Fix: keep"] })]} />)

    fireEvent.click(screen.getByRole("button", { name: /learned from failed checks/ }))

    expect(screen.getAllByText("Permanent: used in every draft")).toHaveLength(1)
  })

  it("opens the rules made permanent after a draft, saying where each came from", () => {
    render(
      <AttemptChart
        attempts={[attempt({ given: [rule("typed one", false)], learned: [rule("Fix: learned one")], promoted: ["Fix: learned one", "typed one", "typed straight into the list"] })]}
        label="PSM"
      />
    )

    fireEvent.click(screen.getByRole("button", { name: /^3 made permanent/ }))

    const dialog = screen.getByRole("dialog", { name: "PSM · Rules made permanent after draft 1" })
    const origins = Array.from(dialog.querySelectorAll("li"), (li) => li.querySelectorAll("p")[1]?.textContent)
    expect(origins).toEqual(["Learned from a failed check", "Typed by a person", "Typed by a person"])
    expect(within(dialog).getAllByText("Permanent: used in every draft")).toHaveLength(3)
  })

  it("closes on Escape, its close button, and a press outside it", () => {
    render(<AttemptChart attempts={STORY} />)
    const open = () => fireEvent.click(screen.getByRole("button", { name: /^3 learned/ }))

    open()
    fireEvent.keyDown(document, { key: "Escape" })
    expect(screen.queryByRole("dialog")).toBeNull()

    open()
    fireEvent.click(screen.getByRole("button", { name: "Close" }))
    expect(screen.queryByRole("dialog")).toBeNull()

    open()
    fireEvent.mouseDown(screen.getByRole("dialog").parentElement as HTMLElement)
    expect(screen.queryByRole("dialog")).toBeNull()
  })

  it("keeps the dialog open when something inside it is pressed", () => {
    render(<AttemptChart attempts={STORY} />)
    fireEvent.click(screen.getByRole("button", { name: /^3 learned/ }))

    fireEvent.mouseDown(screen.getByRole("dialog"))

    expect(screen.getByRole("dialog")).toBeTruthy()
  })

  it("is drawn outside the row, which scrolls sideways", () => {
    const { container } = render(<AttemptChart attempts={STORY} />)
    fireEvent.click(screen.getByRole("button", { name: /^3 learned/ }))

    expect(container.querySelector(".mdd-attempt-chart__row")?.contains(screen.getByRole("dialog"))).toBe(false)
  })
})

describe("AttemptChart: a row wider than its space", () => {
  // jsdom lays nothing out, so the test says how wide the row's content is.
  const CONTENT_WIDTH = 900
  beforeEach(() => {
    Object.defineProperty(HTMLElement.prototype, "scrollWidth", { configurable: true, get: () => CONTENT_WIDTH })
  })
  afterEach(() => {
    Reflect.deleteProperty(HTMLElement.prototype, "scrollWidth")
  })

  const rowOf = (root: HTMLElement) => root.querySelector(".mdd-attempt-chart__row") as HTMLElement

  it("opens at its newest end", () => {
    const { container } = render(<AttemptChart attempts={STORY} />)

    expect(rowOf(container).scrollLeft).toBe(CONTENT_WIDTH)
  })

  it("goes back to the newest end when a draft is added", () => {
    const { container, rerender } = render(<AttemptChart attempts={STORY} />)
    rowOf(container).scrollLeft = 100

    rerender(<AttemptChart attempts={STORY.concat(attempt({ number: 3 }))} />)

    expect(rowOf(container).scrollLeft).toBe(CONTENT_WIDTH)
  })

  it("goes back to the newest end when a rule starts waiting", () => {
    const { container, rerender } = render(<AttemptChart attempts={STORY} />)
    rowOf(container).scrollLeft = 100

    rerender(<AttemptChart attempts={STORY} waiting={[rule("next time", false)]} />)

    expect(rowOf(container).scrollLeft).toBe(CONTENT_WIDTH)
  })

  it("stays where the person scrolled it when nothing is added, as when a draft is selected", () => {
    const onSelect = vi.fn()
    const { container, rerender } = render(<AttemptChart attempts={STORY} onSelect={onSelect} />)
    rowOf(container).scrollLeft = 100

    rerender(<AttemptChart attempts={[...STORY]} selected={0} onSelect={onSelect} />)

    expect(rowOf(container).scrollLeft).toBe(100)
  })

  it("opens at its newest end when the drafts arrive after the chart", () => {
    const { container, rerender } = render(<AttemptChart attempts={[]} />)

    rerender(<AttemptChart attempts={STORY} />)

    expect(rowOf(container).scrollLeft).toBe(CONTENT_WIDTH)
  })
})

describe("AttemptChart: pressing a box", () => {
  it("draws a box that is not a button when nothing was given to select it", () => {
    const { container } = render(<AttemptChart attempts={STORY} />)

    expect(container.querySelector(".mdd-attempt-chart__select")).toBeNull()
    expect(screen.getAllByRole("group", { name: /^Draft / })).toHaveLength(2)
  })

  it("makes each box a button that selects it, marked when selected", () => {
    const onSelect = vi.fn()
    render(<AttemptChart attempts={STORY} selected={1} onSelect={onSelect} />)

    fireEvent.click(screen.getByRole("button", { name: /^Draft 1/ }))

    expect(onSelect).toHaveBeenCalledWith(0)
    expect(screen.getByRole("button", { name: /^Draft 2/ }).getAttribute("aria-pressed")).toBe("true")
    expect(screen.getByRole("button", { name: /^Draft 1/ }).getAttribute("aria-pressed")).toBe("false")
  })

  it("does not select a box when a chip on it is pressed", () => {
    const onSelect = vi.fn()
    render(<AttemptChart attempts={STORY} onSelect={onSelect} />)

    fireEvent.click(screen.getByRole("button", { name: /^3 learned/ }))

    expect(onSelect).not.toHaveBeenCalled()
    expect(screen.getByRole("dialog")).toBeTruthy()
  })
})
