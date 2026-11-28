// @vitest-environment jsdom
//
// Regression test: "Make permanent" must not submit the same
// constraints twice for a rapid double-click before the first request's own
// promise resolves - see PromoteConstraintsAction.tsx's own `promoting` guard.
import { cleanup, fireEvent, render, screen } from "@testing-library/react"
import { afterEach, describe, expect, it, vi } from "vitest"
import { PromoteConstraintsAction } from "./PromoteConstraintsAction"

afterEach(cleanup)

describe("PromoteConstraintsAction", () => {
  it("disables Make permanent while a promote request is still in flight", async () => {
    let resolvePromote: (() => void) | undefined
    const onPromote = vi.fn(
      () =>
        new Promise<string[]>((resolve) => {
          resolvePromote = () => resolve([])
        })
    )

    render(<PromoteConstraintsAction initialConstraintsBlock="- Fix: use camelCase" onPromote={onPromote} />)

    fireEvent.click(await screen.findByText("Make permanent"))
    const confirmButton = await screen.findByText("Make permanent")

    fireEvent.click(confirmButton)
    // A disabled native button doesn't fire click at all - a second,
    // rapid click while the first request is still pending must be a no-op.
    fireEvent.click(screen.getByText("Making permanent…"))

    resolvePromote?.()
    await screen.findByText("Make permanent")

    expect(onPromote).toHaveBeenCalledTimes(1)
  })
})
