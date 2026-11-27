// @vitest-environment jsdom
//
// Regression test: AttemptDetail keeps its own local "restored" state (see
// AttemptDetail.tsx's own restore()/setRestored). That state must never
// leak onto a newly selected attempt. It doesn't today because select()
// itself sets `detail` to null synchronously before loading the next
// attempt (see index.tsx), which unmounts AttemptDetail entirely between
// selections and so already discards its local state - the first test guards
// that sequencing, since removing the null-out (e.g. for a smoother
// loading transition) would silently reintroduce the leak.
import { cleanup, fireEvent, render, screen, waitFor, within } from "@testing-library/react"
import { afterEach, describe, expect, it, vi } from "vitest"
import { AttemptsBrowser } from "./index"
import type { AttemptDetailData, ManifestEntry } from "./types"

afterEach(cleanup)

const ENTRIES: ManifestEntry[] = [
  { run_id: "run-1", stage: "psm", attempt_n: 1, valid: true, timestamp: "2026-01-01T00:00:00Z" },
  { run_id: "run-1", stage: "psm", attempt_n: 2, valid: true, timestamp: "2026-01-01T00:05:00Z" },
]

function detailFor(attemptN: number): AttemptDetailData {
  return { artifact: null, result: {}, prompt: null, prompt_version: `version-${attemptN}` }
}

const column = (n: number) => screen.findByRole("button", { name: new RegExp(`^Draft ${n}\\b`) })

function renderBrowser(over: Partial<React.ComponentProps<typeof AttemptsBrowser>> = {}) {
  return render(
    <AttemptsBrowser
      runId="run-1"
      stage="psm"
      onLoadManifest={() => Promise.resolve(ENTRIES)}
      onLoadAttempt={() => Promise.resolve(detailFor(1))}
      {...over}
    />
  )
}

describe("AttemptsBrowser", () => {
  it("does not leak a previous attempt's 'Restored' state onto a newly selected attempt", async () => {
    const onLoadAttempt = vi.fn((_runId: string, _stage: string, attempt: string) =>
      Promise.resolve(detailFor(Number(attempt.replace("attempt_", ""))))
    )

    renderBrowser({ onLoadAttempt, onRestoreConfigFromAttempt: () => Promise.resolve() })

    fireEvent.click(await column(1))
    fireEvent.click(await screen.findByText("Restore the config that produced this"))
    await screen.findByText("Restored")

    // A genuinely different attempt - its own Restore button must start
    // fresh, never inherit attempt 1's own "Restored" click.
    fireEvent.click(await column(2))
    await screen.findByText("Restore the config that produced this")
    expect(screen.queryByText("Restored")).toBeNull()
  })

  it("draws the attempts as a row of boxes, oldest on the left, each with its tries", async () => {
    const entries: ManifestEntry[] = [
      { ...ENTRIES[1], rounds: 5 },
      { ...ENTRIES[0], rounds: 7 },
    ]

    const { container } = renderBrowser({ onLoadManifest: () => Promise.resolve(entries) })

    await column(1)
    expect(Array.from(container.querySelectorAll(".mdd-attempt-chart__tries"), (e) => e.textContent)).toEqual(["7", "5"])
  })

  it("says in words, on hover, what a box draws", async () => {
    renderBrowser({ onLoadManifest: () => Promise.resolve([{ ...ENTRIES[0], rounds: 1, mock: true }, { ...ENTRIES[1], valid: false }]) })

    // The words are on the box the press target covers.
    expect((await column(1)).parentElement?.getAttribute("title")).toBe("Draft 1 · 1 try · check passed · mock: no model called")
    expect((await column(2)).parentElement?.getAttribute("title")).toContain("check failed")
  })

  it("has no text legend or sentence around the chart", async () => {
    const { container } = renderBrowser({ onLoadManifest: () => Promise.resolve([{ ...ENTRIES[0], rounds: 3, ran_with: 0 }]) })

    await column(1)
    expect(container.querySelectorAll("p")).toHaveLength(0)
    expect(container.querySelector("details")).toBeNull()
  })

  it("says so, once, when no attempt is recorded yet", async () => {
    renderBrowser({ onLoadManifest: () => Promise.resolve([]) })

    expect(await screen.findByText("No drafts yet.")).toBeTruthy()
  })

  it("opens the rules behind a chip in a dialog, only those of that chip", async () => {
    const entries: ManifestEntry[] = [
      { ...ENTRIES[0], rounds: 3, learned: [{ text: "learned in the first", learned: true }] },
      { ...ENTRIES[1], rounds: 1, given: [{ text: "typed before the second", learned: false }], promoted: ["typed before the second"] },
    ]

    renderBrowser({ onLoadManifest: () => Promise.resolve(entries) })
    expect(screen.queryByRole("dialog")).toBeNull()
    fireEvent.click(await screen.findByRole("button", { name: /^\d+ typed by a person\. Press to read/ }))

    const dialog = screen.getByRole("dialog")
    expect(within(dialog).getByText("typed before the second")).toBeTruthy()
    // A person made it permanent afterwards, so it says so.
    expect(within(dialog).getByText("Permanent: used in every draft")).toBeTruthy()
    expect(screen.queryByText("learned in the first")).toBeNull()
  })

  it("does not open a draft when a chip on it is pressed", async () => {
    const onLoadAttempt = vi.fn(() => Promise.resolve(detailFor(1)))
    const entries: ManifestEntry[] = [{ ...ENTRIES[0], rounds: 3, learned: [{ text: "learned one", learned: true }] }]
    renderBrowser({ onLoadManifest: () => Promise.resolve(entries), onLoadAttempt })

    fireEvent.click(await screen.findByRole("button", { name: /^\d+ learned from a failed check\. Press to read/ }))

    expect(onLoadAttempt).not.toHaveBeenCalled()
    expect((await column(1)).getAttribute("aria-pressed")).toBe("false")
  })

  it("marks the column that is open", async () => {
    renderBrowser()

    fireEvent.click(await column(2))

    await waitFor(async () => expect((await column(2)).getAttribute("aria-pressed")).toBe("true"))
    expect((await column(1)).getAttribute("aria-pressed")).toBe("false")
  })

  it("loads the attempts again when the reload key changes and keeps the one that is open", async () => {
    const first = [{ ...ENTRIES[0], rounds: 3 }]
    const after = [{ ...ENTRIES[0], rounds: 3 }, { ...ENTRIES[1], rounds: 1 }]
    const onLoadManifest = vi.fn().mockResolvedValueOnce(first).mockResolvedValue(after)
    const onLoadAttempt = vi.fn(() => Promise.resolve(detailFor(1)))
    const { rerender } = render(<AttemptsBrowser runId="run-1" stage="psm" reloadKey={1} onLoadManifest={onLoadManifest} onLoadAttempt={onLoadAttempt} />)
    fireEvent.click(await column(1))
    await waitFor(() => expect(onLoadAttempt).toHaveBeenCalledTimes(1))

    rerender(<AttemptsBrowser runId="run-1" stage="psm" reloadKey={2} onLoadManifest={onLoadManifest} onLoadAttempt={onLoadAttempt} />)

    expect(await column(2)).toBeTruthy()
    expect(onLoadManifest).toHaveBeenCalledTimes(2)
    expect((await column(1)).getAttribute("aria-pressed")).toBe("true")
  })

  it("shows the rules of a draft even when its files cannot be loaded", async () => {
    const entries: ManifestEntry[] = [{ ...ENTRIES[0], rounds: 2, learned: [{ text: "learned one", learned: true }] }]

    renderBrowser({ onLoadManifest: () => Promise.resolve(entries), onLoadAttempt: () => Promise.reject(new Error("files gone")) })
    fireEvent.click(await column(1))
    expect(await screen.findByText("files gone")).toBeTruthy()
    fireEvent.click(screen.getByRole("button", { name: /^\d+ learned from a failed check\. Press to read/ }))

    expect(within(screen.getByRole("dialog")).getByText("learned one")).toBeTruthy()
  })

  it("keeps the message about a draft's files when the list of drafts is loaded again", async () => {
    const onLoadManifest = vi.fn().mockResolvedValue([{ ...ENTRIES[0], rounds: 2 }])
    const onLoadAttempt = () => Promise.reject(new Error("files gone"))
    const { rerender } = render(<AttemptsBrowser runId="run-1" stage="psm" reloadKey={1} onLoadManifest={onLoadManifest} onLoadAttempt={onLoadAttempt} />)
    fireEvent.click(await column(1))
    expect(await screen.findByText("files gone")).toBeTruthy()

    rerender(<AttemptsBrowser runId="run-1" stage="psm" reloadKey={2} onLoadManifest={onLoadManifest} onLoadAttempt={onLoadAttempt} />)

    await waitFor(() => expect(onLoadManifest).toHaveBeenCalledTimes(2))
    expect(screen.getByText("files gone")).toBeTruthy()
  })

  it("opens the draft asked for last, not an earlier one whose files arrive later", async () => {
    let finishFirst: (detail: AttemptDetailData) => void = () => undefined
    const first = new Promise<AttemptDetailData>((resolve) => {
      finishFirst = resolve
    })
    const onLoadAttempt = vi.fn((_r: string, _s: string, attempt: string) =>
      attempt === "attempt_1" ? first : Promise.resolve({ ...detailFor(2), prompt_version: "of-two" })
    )
    renderBrowser({ onLoadAttempt, onRestoreConfigFromAttempt: () => Promise.resolve() })

    fireEvent.click(await column(1))
    fireEvent.click(await column(2))
    await screen.findByText("Restore the config that produced this")
    finishFirst({ ...detailFor(1), prompt_version: "of-one", artifact: { filename: "late.txt", content: "from the first draft" } })
    await new Promise((resolve) => setTimeout(resolve, 20))

    expect(screen.queryByText("late.txt")).toBeNull()
    expect((await column(2)).getAttribute("aria-pressed")).toBe("true")
  })

  it("shows nothing for a draft that was closed before its files arrived", async () => {
    let finish: (detail: AttemptDetailData) => void = () => undefined
    const slow = new Promise<AttemptDetailData>((resolve) => {
      finish = resolve
    })
    renderBrowser({ onLoadAttempt: () => slow, onRestoreConfigFromAttempt: () => Promise.resolve() })

    fireEvent.click(await column(1))
    fireEvent.click(await column(1))
    finish({ ...detailFor(1), artifact: { filename: "late.txt", content: "x" } })
    await new Promise((resolve) => setTimeout(resolve, 20))

    expect(screen.queryByText("late.txt")).toBeNull()
  })

  it("clears an old error once a reload succeeds", async () => {
    const onLoadManifest = vi.fn().mockRejectedValueOnce(new Error("The runner is down.")).mockResolvedValue(ENTRIES)
    const onLoadAttempt = () => Promise.resolve(detailFor(1))
    const { rerender } = render(<AttemptsBrowser runId="run-1" stage="psm" reloadKey={1} onLoadManifest={onLoadManifest} onLoadAttempt={onLoadAttempt} />)
    expect(await screen.findByText("The runner is down.")).toBeTruthy()

    rerender(<AttemptsBrowser runId="run-1" stage="psm" reloadKey={2} onLoadManifest={onLoadManifest} onLoadAttempt={onLoadAttempt} />)

    await column(1)
    expect(screen.queryByText("The runner is down.")).toBeNull()
  })

  it("drops the open attempt and its entries when the run changes, instead of mixing the two runs", async () => {
    const runA: ManifestEntry[] = [{ ...ENTRIES[0], run_id: "run-A", rounds: 3, learned: [{ text: "from run A", learned: true }] }]
    const runB: ManifestEntry[] = [{ ...ENTRIES[1], run_id: "run-B", rounds: 9 }]
    const onLoadManifest = vi.fn((runId: string) => Promise.resolve(runId === "run-A" ? runA : runB))
    const onLoadAttempt = (runId: string) => Promise.resolve({ ...detailFor(1), prompt_version: `of-${runId}` })
    const { rerender } = render(<AttemptsBrowser runId="run-A" stage="psm" onLoadManifest={onLoadManifest} onLoadAttempt={onLoadAttempt} />)
    fireEvent.click(await column(1))
    fireEvent.click(await screen.findByRole("button", { name: /^\d+ learned from a failed check\. Press to read/ }))
    await screen.findAllByText("from run A")

    rerender(<AttemptsBrowser runId="run-B" stage="psm" onLoadManifest={onLoadManifest} onLoadAttempt={onLoadAttempt} />)

    expect(await column(2)).toBeTruthy()
    expect(screen.queryAllByText("from run A")).toHaveLength(0)
    expect(screen.queryByRole("button", { name: /^Draft 1\b/ })).toBeNull()
  })

  it("ignores an attempt that finished loading after the run it belonged to was left", async () => {
    let finish: (detail: AttemptDetailData) => void = () => undefined
    const slow = new Promise<AttemptDetailData>((resolve) => {
      finish = resolve
    })
    const runA: ManifestEntry[] = [{ ...ENTRIES[0], run_id: "run-A", rounds: 3 }]
    const onLoadManifest = vi.fn((runId: string) => Promise.resolve(runId === "run-A" ? runA : []))
    const { rerender } = render(<AttemptsBrowser runId="run-A" stage="psm" onLoadManifest={onLoadManifest} onLoadAttempt={() => slow} onRestoreConfigFromAttempt={() => Promise.resolve()} />)
    fireEvent.click(await column(1))

    rerender(<AttemptsBrowser runId="run-B" stage="psm" onLoadManifest={onLoadManifest} onLoadAttempt={() => slow} onRestoreConfigFromAttempt={() => Promise.resolve()} />)
    await screen.findByText("No drafts yet.")
    finish(detailFor(1))
    // Let the late answer be handled, then look: it must not have opened an attempt of the new run.
    await new Promise((resolve) => setTimeout(resolve, 20))

    expect(screen.queryByText("Restore the config that produced this")).toBeNull()
  })

  it("ignores a list of drafts that arrived after the run it belonged to was left", async () => {
    let finish: (entries: ManifestEntry[]) => void = () => undefined
    const slowRunA = new Promise<ManifestEntry[]>((resolve) => {
      finish = resolve
    })
    const runB: ManifestEntry[] = [{ ...ENTRIES[1], run_id: "run-B", rounds: 9 }]
    const onLoadManifest = vi.fn((runId: string) => (runId === "run-A" ? slowRunA : Promise.resolve(runB)))
    const onLoadAttempt = () => Promise.resolve(detailFor(1))
    const { rerender } = render(<AttemptsBrowser runId="run-A" stage="psm" onLoadManifest={onLoadManifest} onLoadAttempt={onLoadAttempt} />)

    rerender(<AttemptsBrowser runId="run-B" stage="psm" onLoadManifest={onLoadManifest} onLoadAttempt={onLoadAttempt} />)
    expect(await column(2)).toBeTruthy()
    finish([{ ...ENTRIES[0], run_id: "run-A", rounds: 3 }])
    // Let the late answer be handled, then look: run A's draft must not have joined run B's.
    await new Promise((resolve) => setTimeout(resolve, 20))

    expect(screen.queryByRole("button", { name: /^Draft 1\b/ })).toBeNull()
  })
})
