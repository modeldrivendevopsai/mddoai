// @vitest-environment jsdom
//
// How a platform was built: the rounds each stage took and the corrections
// along the way. Only the data boundary and the button are mocked: the button
// has its own tests inside design-system, whose own copy of react (a symlinked
// file: dependency) would load a second React instance under Vitest.
import type { ReactNode } from "react"
import { cleanup, fireEvent, render, screen, within } from "@testing-library/react"
import { afterEach, beforeEach, describe, expect, it, vi } from "vitest"
import type { RunEvolution } from "orchestrator-types"
import { PlatformEvolution } from "./PlatformEvolution"
import * as orchestrator from "@/services/orchestrator.service"

vi.mock("design-system", () => ({
  Button: ({ children, onClick }: { children?: ReactNode; onClick?: () => void }) => (
    <button onClick={onClick}>{children}</button>
  ),
}))

vi.mock("@/services/orchestrator.service", () => ({ getRunEvolution: vi.fn() }))

afterEach(cleanup)

const TWO_DAYS_AGO = Math.floor(Date.now() / 1000) - 2 * 86400
const THREE_DAYS_AGO = TWO_DAYS_AGO - 86400

const evolution = (over: Partial<RunEvolution> = {}): RunEvolution => ({
  run_id: "run-7",
  forked_from: null,
  total_rounds: 5,
  stages: [
    {
      stage: "psm",
      rounds: 2,
      constraints: [{ text: "use the real PIM names", source: "validation", timestamp: TWO_DAYS_AGO }],
    },
    {
      stage: "atl",
      rounds: 3,
      constraints: [{ text: "keep the rule names short", source: null, timestamp: THREE_DAYS_AGO }],
    },
  ],
  ...over,
})

beforeEach(() => {
  vi.resetAllMocks()
  vi.mocked(orchestrator.getRunEvolution).mockResolvedValue(evolution())
})

describe("PlatformEvolution", () => {
  it("asks for the run it was given and says it is loading until the answer comes", async () => {
    render(<PlatformEvolution runId="run-7" />)

    expect(screen.getByText("Loading the rounds and corrections…")).toBeTruthy()
    expect(await screen.findByRole("table")).toBeTruthy()
    expect(orchestrator.getRunEvolution).toHaveBeenCalledWith("run-7")
  })

  it("shows the rounds and corrections of each stage by its name, and the total", async () => {
    render(<PlatformEvolution runId="run-7" />)

    const table = await screen.findByRole("table")
    const rows = within(table)
      .getAllByRole("row")
      .map((row) => Array.from(row.querySelectorAll("th, td"), (cell) => cell.textContent))
    expect(rows).toEqual([
      ["Stage", "Rounds", "Corrections"],
      ["PSM", "2", "1"],
      ["ATL", "3", "1"],
      ["In all", "5", "2"],
    ])
  })

  it("lists the corrections oldest first, telling a learned fix from a typed correction", async () => {
    render(<PlatformEvolution runId="run-7" />)

    await screen.findByRole("table")
    const entries = screen.getAllByRole("listitem")
    expect(entries).toHaveLength(2)
    // The ATL correction is a day older, so it comes first.
    expect(within(entries[0]).getByText("Correction")).toBeTruthy()
    expect(within(entries[0]).getByText("ATL")).toBeTruthy()
    expect(within(entries[0]).getByText("3 days ago")).toBeTruthy()
    expect(within(entries[1]).getByText("Fix learned")).toBeTruthy()
    expect(within(entries[1]).getByText("PSM")).toBeTruthy()
  })

  it("names the stage a forked chat was forked at, and says the stages before it are not counted", async () => {
    vi.mocked(orchestrator.getRunEvolution).mockResolvedValue(evolution({ forked_from: { run_id: "run-1", stage: "atl" } }))
    render(<PlatformEvolution runId="run-7" />)

    expect(await screen.findByText(/forked at ATL/)).toBeTruthy()
    expect(screen.getByText(/not counted here/)).toBeTruthy()
  })

  it("says nothing about forking for a chat that was not forked", async () => {
    render(<PlatformEvolution runId="run-7" />)

    await screen.findByRole("table")
    expect(screen.queryByText(/forked/)).toBeNull()
  })

  it("leaves out the age of a correction whose time cannot be read, instead of failing", async () => {
    vi.mocked(orchestrator.getRunEvolution).mockResolvedValue(
      evolution({ stages: [{ stage: "psm", rounds: 1, constraints: [{ text: "odd one", source: null, timestamp: Number.NaN }] }], total_rounds: 1 })
    )
    render(<PlatformEvolution runId="run-7" />)

    await screen.findByRole("table")
    expect(screen.getAllByText("odd one").length).toBeGreaterThan(0)
    expect(document.querySelector("time")).toBeNull()
  })

  it("is a section named by its own heading, with the corrections log one level below it", async () => {
    render(<PlatformEvolution runId="run-7" />)

    await screen.findByRole("table")
    expect(screen.getByRole("region", { name: "Rounds and corrections" })).toBeTruthy()
    expect(screen.getByRole("heading", { level: 3, name: "Corrections along the way" })).toBeTruthy()
  })

  it("keeps the whole text of a correction behind its line", async () => {
    render(<PlatformEvolution runId="run-7" />)

    await screen.findByRole("table")
    // The text is in the page twice, once as the line's preview and once as the full text.
    expect(screen.getAllByText("use the real PIM names")).toHaveLength(2)
  })

  it("says so when the chat ran no stage with rounds and recorded no corrections", async () => {
    vi.mocked(orchestrator.getRunEvolution).mockResolvedValue(evolution({ stages: [], total_rounds: 0 }))
    render(<PlatformEvolution runId="run-7" />)

    expect(await screen.findByText("The chat that made it ran no stage with rounds and recorded no corrections.")).toBeTruthy()
    expect(screen.queryByRole("table")).toBeNull()
  })

  it("shows the table without a corrections log when there were no corrections", async () => {
    vi.mocked(orchestrator.getRunEvolution).mockResolvedValue(
      evolution({ stages: [{ stage: "psm", rounds: 1, constraints: [] }], total_rounds: 1 })
    )
    render(<PlatformEvolution runId="run-7" />)

    await screen.findByRole("table")
    expect(screen.queryByText("Corrections along the way")).toBeNull()
  })

  it("shows why it could not load, and loads again on a retry", async () => {
    vi.mocked(orchestrator.getRunEvolution).mockRejectedValueOnce(new Error("The runner is down."))
    render(<PlatformEvolution runId="run-7" />)

    expect((await screen.findByRole("alert")).textContent).toContain("The runner is down.")
    fireEvent.click(screen.getByRole("button", { name: "Try again" }))

    expect(await screen.findByRole("table")).toBeTruthy()
    expect(orchestrator.getRunEvolution).toHaveBeenCalledTimes(2)
  })
})
