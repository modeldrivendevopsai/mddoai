// @vitest-environment jsdom
//
// The "← Title" control every screen's header uses. The visual primitives are
// stubbed: they have their own tests inside design-system, and its own copy of
// react (a symlinked file: dependency) would load a second React instance
// under Vitest.
import { cleanup, fireEvent, render, screen } from "@testing-library/react"
import { afterEach, describe, expect, it, vi } from "vitest"
import { BackTitle } from "./BackTitle"

vi.mock("design-system", () => ({
  Icon: ({ name }: { name: string }) => <svg data-icon={name} />,
}))

afterEach(cleanup)

describe("BackTitle", () => {
  it("is a button named by the title, with an arrow, and a hint on hover", () => {
    const { container } = render(<BackTitle title="CI/CD Platforms" hint="Back to the start screen" onBack={() => undefined} />)

    const button = screen.getByRole("button", { name: "CI/CD Platforms" })
    expect(button.getAttribute("title")).toBe("Back to the start screen")
    expect(container.querySelector("[data-icon='ArrowLeft']")).toBeTruthy()
  })

  it("goes back when pressed", () => {
    const onBack = vi.fn()
    render(<BackTitle title="CI/CD Platforms" hint="Back" onBack={onBack} />)

    fireEvent.click(screen.getByRole("button", { name: "CI/CD Platforms" }))

    expect(onBack).toHaveBeenCalledTimes(1)
  })
})
