// @vitest-environment jsdom
//
// A real regression test for the exact failure mode found during PR review
// on ai/ui-host: killing one ui-remote-* container (a rejected
// React.lazy() import) used to crash this entire app to a blank screen,
// since Suspense alone only ever handles the *pending* state, never a
// rejection, and nothing anywhere caught the resulting render error.
// Renders through jsdom + real React (not mocked), so this exercises
// React's actual error-boundary/Suspense interaction for this project's
// real React version, not just an assumption about how they're documented
// to behave.
import { lazy, Suspense } from "react"
import { cleanup, render, screen, waitFor } from "@testing-library/react"
import { afterEach, describe, expect, it, vi } from "vitest"
import { RemoteBoundary } from "./RemoteBoundary"

// vite.config.ts doesn't set test.globals, so @testing-library/react's own
// auto-cleanup (which looks for a global afterEach) never registers —
// without this, each test's rendered DOM would leak into the next one in
// this file.
afterEach(cleanup)

function FailingLazy() {
  const Broken = lazy(() => Promise.reject(new Error("simulated remoteEntry.js fetch failure")))
  return (
    <Suspense fallback={null}>
      <Broken />
    </Suspense>
  )
}

function WorkingComponent() {
  return <div>healthy panel</div>
}

describe("RemoteBoundary", () => {
  it("shows a fallback instead of crashing when a federated import rejects", async () => {
    // console.error is expected here (both React's own logging and this
    // boundary's own componentDidCatch log the caught error) — suppressed
    // so the real assertions below aren't drowned out, not to hide a bug.
    const consoleError = vi.spyOn(console, "error").mockImplementation(() => {})
    try {
      render(
        <RemoteBoundary name="Broken Panel">
          <FailingLazy />
        </RemoteBoundary>
      )

      await waitFor(() => expect(screen.queryByText(/Couldn't load "Broken Panel"/)).not.toBeNull())
    } finally {
      consoleError.mockRestore()
    }
  })

  it("isolates the failure to just the one boundary — a sibling keeps rendering", async () => {
    const consoleError = vi.spyOn(console, "error").mockImplementation(() => {})
    try {
      render(
        <div>
          <RemoteBoundary name="Broken Panel">
            <FailingLazy />
          </RemoteBoundary>
          <RemoteBoundary name="Healthy Panel">
            <WorkingComponent />
          </RemoteBoundary>
        </div>
      )

      await waitFor(() => expect(screen.queryByText(/Couldn't load "Broken Panel"/)).not.toBeNull())
      // The real point of this fix: the healthy sibling never unmounts.
      expect(screen.queryByText("healthy panel")).not.toBeNull()
    } finally {
      consoleError.mockRestore()
    }
  })

  it("renders its children normally when nothing fails", () => {
    render(
      <RemoteBoundary name="Healthy Panel">
        <WorkingComponent />
      </RemoteBoundary>
    )

    expect(screen.queryByText("healthy panel")).not.toBeNull()
  })
})
