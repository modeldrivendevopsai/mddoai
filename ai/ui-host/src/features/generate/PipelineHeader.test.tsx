// @vitest-environment jsdom
import { cleanup, fireEvent, render, screen } from "@testing-library/react"
import { afterEach, describe, expect, it, vi } from "vitest"
import { InputHeader, PipelinesHeader } from "./PipelineHeader"

// design-system is stubbed, as in the screen tests, which explain why.
// The stubbed icon is an svg, so where it sits in the button can be checked.
vi.mock("design-system", () => ({ Icon: () => <svg aria-hidden="true" /> }))

afterEach(cleanup)

// Both headers are "<arrow> Title" and the title is the way back, so going
// back looks and works the same on every pipeline screen.
describe.each([
  ["the pipelines list", PipelinesHeader, "CI/CD Pipelines", "Back to the start screen"],
  ["the generate screen", InputHeader, "Generate CI/CD Pipeline", "Back to your pipelines"],
])("the header of %s", (_name, Header, title, hint) => {
  it("is a back button with the arrow on the left of the title", () => {
    const onBack = vi.fn()
    render(<Header onBack={onBack} />)

    const back = screen.getByRole("button", { name: title })
    expect(back.firstElementChild?.tagName.toLowerCase()).toBe("svg")
    expect(back.textContent).toBe(title)
    expect(back.getAttribute("title")).toBe(hint)

    fireEvent.click(back)
    expect(onBack).toHaveBeenCalledTimes(1)
  })
})
