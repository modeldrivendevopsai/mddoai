// @vitest-environment jsdom
//
// How a platform was built: a row per stage, a box for each draft. Only the data boundary and
// the button are stubbed. The button has its own tests inside design-system, whose own copy of
// react (a symlinked file: dependency) would load a second React instance under Vitest. The
// chart and its dialog are the real ones, with only their icons stood in for, which keeps what
// they draw under test.
import type { ReactNode } from "react"
import { cleanup, fireEvent, render, screen, within } from "@testing-library/react"
import { afterEach, beforeEach, describe, expect, it, vi } from "vitest"
import type { RunEvolution, StageCall, StageEvolution } from "orchestrator-types"
import { PlatformEvolution } from "./PlatformEvolution"
import * as orchestrator from "@/services/orchestrator.service"

// The icons come from design-system's own copy of lucide-react, which loads its own copy of
// react and breaks hooks under test, so each icon is stood in for by an element that names it.
vi.mock("../../../../design-system/src/components/Icon", () => ({
  Icon: ({ name }: { name: string }) => <span data-icon={name} />,
}))

vi.mock("design-system", async () => {
  const actual = await vi.importActual<typeof import("design-system")>("design-system")
  return {
    ...actual,
    Button: ({ children, onClick }: { children?: ReactNode; onClick?: () => void }) => (
      <button onClick={onClick}>{children}</button>
    ),
  }
})

vi.mock("@/services/orchestrator.service", () => ({ getRunEvolution: vi.fn() }))

afterEach(cleanup)

const call = (over: Partial<StageCall> = {}): StageCall => ({
  attempt_n: 1,
  outcome: "valid",
  rounds: 1,
  mock: false,
  ran_with: 0,
  given: [],
  learned: [],
  promoted: [],
  ...over,
})

const stage = (over: Partial<StageEvolution> = {}): StageEvolution => ({
  stage: "atl",
  calls: [],
  waiting: [],
  ...over,
})

// Three attempts of ATL that get quicker, as they are given more.
const ATL = stage({
  calls: [
    call({ attempt_n: 1, outcome: "invalid", rounds: 7, ran_with: 0, learned: [{ text: "Fix: dangling reference", learned: true }, { text: "Fix: unknown feature", learned: true }] }),
    call({ attempt_n: 2, outcome: "invalid", rounds: 5, ran_with: 2, given: [{ text: "Name the root rule Main", learned: false }], learned: [{ text: "Fix: missing helper", learned: true }] }),
    call({ attempt_n: 3, outcome: "valid", rounds: 1, ran_with: 4, promoted: ["Name the root rule Main"] }),
  ],
})

const evolution = (over: Partial<RunEvolution> = {}): RunEvolution => ({
  run_id: "run-7",
  forked_from: null,
  stages: [ATL],
  ...over,
})

const onOpenRun = vi.fn()

const tries = (root: HTMLElement) => Array.from(root.querySelectorAll(".mdd-attempt-chart__tries"), (e) => e.textContent)
const boxes = (root: HTMLElement) => Array.from(root.querySelectorAll<HTMLElement>(".mdd-attempt-chart__draft"))

beforeEach(() => {
  vi.resetAllMocks()
  vi.mocked(orchestrator.getRunEvolution).mockResolvedValue(evolution())
})

describe("PlatformEvolution", () => {
  it("asks for the run it was given and says it is loading until the answer comes", async () => {
    render(<PlatformEvolution runId="run-7" onOpenRun={onOpenRun} />)

    expect(screen.getByText("Loading how it evolved…")).toBeTruthy()
    expect(await screen.findByRole("region", { name: "ATL" })).toBeTruthy()
    expect(orchestrator.getRunEvolution).toHaveBeenCalledWith("run-7")
  })

  it("draws each stage's drafts as a row of boxes, oldest first, so the fall in tries can be read along it", async () => {
    render(<PlatformEvolution runId="run-7" onOpenRun={onOpenRun} />)

    const atl = await screen.findByRole("region", { name: "ATL" })
    expect(tries(atl)).toEqual(["7", "5", "1"])
    // An arrow joins each draft to the next.
    expect(atl.querySelectorAll(".mdd-attempt-chart__link")).toHaveLength(2)
  })

  it("colours each box by whether its check passed", async () => {
    render(<PlatformEvolution runId="run-7" onOpenRun={onOpenRun} />)

    const atl = await screen.findByRole("region", { name: "ATL" })
    expect(boxes(atl).map((box) => box.className.match(/is-(passed|failed|error|none)/)?.[1])).toEqual(["failed", "failed", "passed"])
  })

  it("says in words, on hover, what a box and each number on it draws", async () => {
    render(<PlatformEvolution runId="run-7" onOpenRun={onOpenRun} />)

    const atl = await screen.findByRole("region", { name: "ATL" })
    expect(boxes(atl)[1].getAttribute("title")).toBe("Draft 2 · 5 tries · 1 typed by a person · 1 learned from a failed check · check failed")
    expect(within(atl).getByTitle("7 tries")).toBeTruthy()
    expect(within(atl).getByTitle("2 learned from failed checks. Press to read them.")).toBeTruthy()
    expect(within(atl).getByTitle("1 typed by a person. Press to read it.")).toBeTruthy()
  })

  it("draws no sentence, legend or description: the rows, the stage name and the chips are all it shows", async () => {
    const { container } = render(<PlatformEvolution runId="run-7" onOpenRun={onOpenRun} />)

    await screen.findByRole("region", { name: "ATL" })
    expect(container.querySelectorAll("p")).toHaveLength(0)
    expect(container.querySelector("details")).toBeNull()
    expect(screen.queryByRole("dialog")).toBeNull()
  })

  it("shows what a draft learned as a chip, and the rules a person typed as a chip on the arrow into it", async () => {
    render(<PlatformEvolution runId="run-7" onOpenRun={onOpenRun} />)

    const atl = await screen.findByRole("region", { name: "ATL" })
    // Draft 1 learned 2, draft 2 learned 1, draft 3 learned none; a person typed one rule before draft 2.
    expect(boxes(atl).map((box) => box.querySelector(".mdd-attempt-chart__chip.is-learned")?.textContent ?? null)).toEqual(["2", "1", null])
    expect(Array.from(atl.querySelectorAll(".mdd-attempt-chart__link"), (link) => link.querySelector(".mdd-attempt-chart__chip")?.textContent ?? null)).toEqual(["1", null])
  })

  it("opens a numbered list of the rules behind a chip in a dialog, and closes it again", async () => {
    render(<PlatformEvolution runId="run-7" onOpenRun={onOpenRun} />)
    const atl = await screen.findByRole("region", { name: "ATL" })

    fireEvent.click(within(atl).getByRole("button", { name: /^2 learned from failed checks/ }))

    const dialog = screen.getByRole("dialog", { name: "ATL · Fixes learned in draft 1" })
    expect(Array.from(dialog.querySelectorAll("li"), (li) => li.querySelector("p")?.textContent)).toEqual(["Fix: dangling reference", "Fix: unknown feature"])
    expect(within(dialog).getByRole("button", { name: "Copy" })).toBeTruthy()

    fireEvent.click(within(dialog).getByRole("button", { name: "Close" }))
    expect(screen.queryByRole("dialog")).toBeNull()
  })

  it("opens the rules a person typed before a draft from the chip on the arrow", async () => {
    render(<PlatformEvolution runId="run-7" onOpenRun={onOpenRun} />)
    const atl = await screen.findByRole("region", { name: "ATL" })

    fireEvent.click(within(atl).getByRole("button", { name: /^1 typed by a person/ }))

    const dialog = screen.getByRole("dialog", { name: "ATL · Rules typed before draft 2" })
    expect(within(dialog).getByText("Name the root rule Main")).toBeTruthy()
    // Draft 3's person made it permanent after that draft, which the dialog of draft 3 shows, not this one.
    expect(within(dialog).queryByText("Permanent: used in every draft")).toBeNull()
  })

  it("marks a rule a person made permanent, on a chip of the draft it was made after", async () => {
    render(<PlatformEvolution runId="run-7" onOpenRun={onOpenRun} />)
    const atl = await screen.findByRole("region", { name: "ATL" })

    fireEvent.click(within(atl).getByRole("button", { name: /^1 made permanent/ }))

    const dialog = screen.getByRole("dialog", { name: "ATL · Rules made permanent after draft 3" })
    expect(within(dialog).getByText("Name the root rule Main")).toBeTruthy()
    expect(within(dialog).getByText("Permanent: used in every draft")).toBeTruthy()
  })

  it("lists each stage that ran, in the order given", async () => {
    vi.mocked(orchestrator.getRunEvolution).mockResolvedValue(
      evolution({ stages: [stage({ stage: "psm", calls: [call()] }), ATL] })
    )
    render(<PlatformEvolution runId="run-7" onOpenRun={onOpenRun} />)

    await screen.findByRole("region", { name: "ATL" })
    expect(screen.getAllByRole("heading", { level: 3 }).map((h) => h.textContent)).toEqual(["PSM", "ATL"])
  })

  it("draws a stage with no loop with the draft numbers in place of tries", async () => {
    vi.mocked(orchestrator.getRunEvolution).mockResolvedValue(
      evolution({ stages: [stage({ stage: "pim", calls: [call({ rounds: null }), call({ attempt_n: 2, rounds: null })] })] })
    )
    render(<PlatformEvolution runId="run-7" onOpenRun={onOpenRun} />)

    const pim = await screen.findByRole("region", { name: "PIM" })
    expect(tries(pim)).toEqual(["#1", "#2"])
  })

  it("draws a call that stopped on an error as an amber box with a warning mark", async () => {
    vi.mocked(orchestrator.getRunEvolution).mockResolvedValue(
      evolution({ stages: [stage({ calls: [call({ attempt_n: null, outcome: "failed", rounds: null })] })] })
    )
    const { container } = render(<PlatformEvolution runId="run-7" onOpenRun={onOpenRun} />)

    const atl = await screen.findByRole("region", { name: "ATL" })
    expect(container.querySelector('[data-icon="TriangleAlert"]')).not.toBeNull()
    expect(within(atl).getByRole("group", { name: /stopped on an error/ })).toBeTruthy()
  })

  it("draws a call that raised after recording an attempt as a failed check, since that is how a rejected result ends", async () => {
    vi.mocked(orchestrator.getRunEvolution).mockResolvedValue(
      evolution({ stages: [stage({ stage: "pim", calls: [call({ attempt_n: 1, outcome: "failed", rounds: null })] })] })
    )
    const { container } = render(<PlatformEvolution runId="run-7" onOpenRun={onOpenRun} />)

    const pim = await screen.findByRole("region", { name: "PIM" })
    expect(boxes(pim)[0].classList.contains("is-failed")).toBe(true)
    expect(container.querySelector('[data-icon="X"]')).not.toBeNull()
    expect(container.querySelector('[data-icon="TriangleAlert"]')).toBeNull()
  })

  it("hangs rules nothing has used yet off a dashed arrow after the last draft, and opens them", async () => {
    vi.mocked(orchestrator.getRunEvolution).mockResolvedValue(
      evolution({ stages: [stage({ calls: [call()], waiting: [{ text: "next time", learned: false }] })] })
    )
    const { container } = render(<PlatformEvolution runId="run-7" onOpenRun={onOpenRun} />)

    await screen.findByRole("region", { name: "ATL" })
    expect(container.querySelector(".mdd-attempt-chart__link.is-waiting")).not.toBeNull()
    fireEvent.click(screen.getByRole("button", { name: /^1 typed by a person/ }))
    expect(within(screen.getByRole("dialog")).getByText("next time")).toBeTruthy()
  })

  it("dashes a mock draft's edge and marks it with a flask, since it called no model", async () => {
    vi.mocked(orchestrator.getRunEvolution).mockResolvedValue(
      evolution({ stages: [stage({ calls: [call({ mock: true })] })] })
    )
    const { container } = render(<PlatformEvolution runId="run-7" onOpenRun={onOpenRun} />)

    await screen.findByRole("region", { name: "ATL" })
    expect(container.querySelector(".mdd-attempt-chart__draft.is-mock")).not.toBeNull()
    expect(container.querySelector('[data-icon="FlaskConical"]')).not.toBeNull()
    expect(screen.getByRole("group", { name: /mock: no model called/ })).toBeTruthy()
  })

  it("shows a fork as an icon and a few words, and opens the source chat", async () => {
    vi.mocked(orchestrator.getRunEvolution).mockResolvedValue(evolution({ forked_from: { run_id: "run-1", stage: "atl" } }))
    render(<PlatformEvolution runId="run-7" onOpenRun={onOpenRun} />)

    expect(await screen.findByText("Forked at ATL")).toBeTruthy()
    fireEvent.click(screen.getByRole("button", { name: "Open source chat" }))
    expect(onOpenRun).toHaveBeenCalledWith("run-1")
  })

  it("offers no way to open a source chat when the fork does not say which it was", async () => {
    vi.mocked(orchestrator.getRunEvolution).mockResolvedValue(evolution({ forked_from: { run_id: null, stage: null } }))
    render(<PlatformEvolution runId="run-7" onOpenRun={onOpenRun} />)

    expect(await screen.findByText("Forked")).toBeTruthy()
    expect(screen.queryByRole("button", { name: "Open source chat" })).toBeNull()
  })

  it("shows a forked chat that has not run anything yet, with its fork and no empty message", async () => {
    vi.mocked(orchestrator.getRunEvolution).mockResolvedValue(evolution({ stages: [], forked_from: { run_id: "run-1", stage: "psm" } }))
    render(<PlatformEvolution runId="run-7" onOpenRun={onOpenRun} />)

    expect(await screen.findByText("Forked at PSM")).toBeTruthy()
    expect(screen.queryByText("Nothing was redone.")).toBeNull()
  })

  it("is a section named by its own heading", async () => {
    render(<PlatformEvolution runId="run-7" onOpenRun={onOpenRun} />)

    await screen.findByRole("region", { name: "ATL" })
    expect(screen.getByRole("region", { name: "How it evolved" })).toBeTruthy()
  })

  it("says so in a few words when nothing was redone", async () => {
    vi.mocked(orchestrator.getRunEvolution).mockResolvedValue(evolution({ stages: [] }))
    render(<PlatformEvolution runId="run-7" onOpenRun={onOpenRun} />)

    expect(await screen.findByText("Nothing was redone.")).toBeTruthy()
  })

  it("shows why it could not load, and loads again on a retry", async () => {
    vi.mocked(orchestrator.getRunEvolution).mockRejectedValueOnce(new Error("The runner is down."))
    render(<PlatformEvolution runId="run-7" onOpenRun={onOpenRun} />)

    expect((await screen.findByRole("alert")).textContent).toContain("The runner is down.")
    fireEvent.click(screen.getByRole("button", { name: "Try again" }))

    expect(await screen.findByRole("region", { name: "ATL" })).toBeTruthy()
    expect(orchestrator.getRunEvolution).toHaveBeenCalledTimes(2)
  })

  it("opens only the rules of the stage whose chip was pressed", async () => {
    vi.mocked(orchestrator.getRunEvolution).mockResolvedValue(evolution({ stages: [stage({ stage: "psm", calls: [call({ learned: [{ text: "psm one", learned: true }] })] }), ATL] }))
    render(<PlatformEvolution runId="run-7" onOpenRun={onOpenRun} />)
    const psm = await screen.findByRole("region", { name: "PSM" })

    fireEvent.click(within(psm).getByRole("button", { name: /^1 learned from a failed check/ }))

    expect(within(screen.getByRole("dialog", { name: "PSM · Fixes learned in draft 1" })).getByText("psm one")).toBeTruthy()
    expect(screen.queryByText("Fix: dangling reference")).toBeNull()
  })

  it("does not show the previous run's chart while the next run's answer is on its way", async () => {
    let next: (value: RunEvolution) => void = () => undefined
    vi.mocked(orchestrator.getRunEvolution).mockImplementation((runId: string) =>
      runId === "run-7"
        ? Promise.resolve(evolution())
        : new Promise<RunEvolution>((resolve) => {
            next = resolve
          })
    )
    const { rerender } = render(<PlatformEvolution runId="run-7" onOpenRun={onOpenRun} />)
    await screen.findByRole("region", { name: "ATL" })

    rerender(<PlatformEvolution runId="run-8" onOpenRun={onOpenRun} />)

    expect(screen.queryByRole("region", { name: "ATL" })).toBeNull()
    expect(screen.getByText("Loading how it evolved…")).toBeTruthy()
    next(evolution({ run_id: "run-8", stages: [stage({ stage: "psm", calls: [call()] })] }))
    expect(await screen.findByRole("region", { name: "PSM" })).toBeTruthy()
  })

  it("ignores an answer that arrives after the run it was for was left", async () => {
    let late: (value: RunEvolution) => void = () => undefined
    vi.mocked(orchestrator.getRunEvolution).mockImplementation((runId: string) =>
      runId === "run-7"
        ? new Promise<RunEvolution>((resolve) => {
            late = resolve
          })
        : Promise.resolve(evolution({ run_id: "run-8", stages: [stage({ stage: "psm", calls: [call()] })] }))
    )
    const { rerender } = render(<PlatformEvolution runId="run-7" onOpenRun={onOpenRun} />)

    rerender(<PlatformEvolution runId="run-8" onOpenRun={onOpenRun} />)
    await screen.findByRole("region", { name: "PSM" })
    late(evolution())
    // Let the late answer be handled, then look: it must not have replaced the run on screen.
    await new Promise((resolve) => setTimeout(resolve, 20))

    expect(screen.queryByRole("region", { name: "ATL" })).toBeNull()
    expect(screen.getByRole("region", { name: "PSM" })).toBeTruthy()
  })
})
