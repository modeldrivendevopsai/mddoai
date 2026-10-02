// @vitest-environment jsdom
//
// Regression test: AttemptDetail keeps its own local "restored" state (see
// AttemptDetail.tsx's own restore()/setRestored). That state must never
// leak onto a newly selected attempt. It doesn't today because select()
// itself sets `detail` to null synchronously before loading the next
// attempt (see index.tsx), which unmounts AttemptDetail entirely between
// selections and so already discards its local state - this test guards
// that sequencing, since removing the null-out (e.g. for a smoother
// loading transition) would silently reintroduce the leak.
import { cleanup, fireEvent, render, screen } from "@testing-library/react"
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

describe("AttemptsBrowser", () => {
  it("does not leak a previous attempt's 'Restored' state onto a newly selected attempt", async () => {
    const onLoadManifest = vi.fn(() => Promise.resolve(ENTRIES))
    const onLoadAttempt = vi.fn((_runId: string, _stage: string, attempt: string) =>
      Promise.resolve(detailFor(Number(attempt.replace("attempt_", ""))))
    )
    const onRestoreConfigFromAttempt = vi.fn(() => Promise.resolve())

    render(
      <AttemptsBrowser
        runId="run-1"
        stage="psm"
        onLoadManifest={onLoadManifest}
        onLoadAttempt={onLoadAttempt}
        onRestoreConfigFromAttempt={onRestoreConfigFromAttempt}
      />
    )

    fireEvent.click(await screen.findByText("Attempt 1"))
    fireEvent.click(await screen.findByText("Restore the config that produced this"))
    await screen.findByText("Restored")

    // A genuinely different attempt - its own Restore button must start
    // fresh, never inherit attempt 1's own "Restored" click.
    fireEvent.click(await screen.findByText("Attempt 2"))
    await screen.findByText("Restore the config that produced this")
    expect(screen.queryByText("Restored")).toBeNull()
  })
})
