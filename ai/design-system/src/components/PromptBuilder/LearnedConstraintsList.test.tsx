// @vitest-environment jsdom
import { cleanup, fireEvent, render, screen } from "@testing-library/react"
import { afterEach, describe, expect, it, vi } from "vitest"
import { LearnedConstraintsList, firstSentence } from "./LearnedConstraintsList"

afterEach(cleanup)

const LONG = "Target variable names must never be reserved keywords. Use descriptive names instead. " + "x".repeat(2000)

function renderList(over: Partial<React.ComponentProps<typeof LearnedConstraintsList>> = {}) {
  const props = {
    constraints: ["first", "second", LONG],
    onAdd: vi.fn(),
    onRemove: vi.fn(),
    onReorder: vi.fn(),
    ...over,
  }
  return { ...render(<LearnedConstraintsList {...props} />), props }
}

describe("LearnedConstraintsList", () => {
  it("is folded away by default and says how many rules it holds", () => {
    const { container } = renderList()

    expect(screen.getByText("Permanent rules (3)")).toBeTruthy()
    expect(container.querySelector("details")?.hasAttribute("open")).toBe(false)
    // What it means is on hover, not in a sentence.
    expect(container.querySelector("summary")?.getAttribute("title")).toBe("Permanent rules are used in every draft")
  })

  it("shows each constraint on one line, in a box that scrolls instead of growing with the list", () => {
    renderList()

    const list = screen.getByRole("list", { hidden: true })
    expect(list.style.overflowY).toBe("auto")
    expect(list.style.maxHeight).not.toBe("")
    const text = screen.getByText("first")
    // Closed, a line is cut after a couple of lines instead of growing to its whole text.
    expect(text.style.overflow).toBe("hidden")
    expect(text.style.whiteSpace).not.toBe("pre-wrap")
    expect(text.closest("button")?.getAttribute("aria-expanded")).toBe("false")
  })

  it("opens one constraint to its whole text when it is pressed, and back to its first sentence when pressed again", () => {
    renderList()
    const button = screen.getByText(/^Target variable names must never be reserved keywords\./).closest("button") as HTMLButtonElement

    fireEvent.click(button)
    expect(button.getAttribute("aria-expanded")).toBe("true")
    expect(screen.getByText(LONG).style.whiteSpace).toBe("pre-wrap")
    expect(screen.getByText("first").closest("button")?.getAttribute("aria-expanded")).toBe("false")

    fireEvent.click(button)
    expect(button.getAttribute("aria-expanded")).toBe("false")
    expect(screen.getByText("Target variable names must never be reserved keywords. …").style.overflow).toBe("hidden")
  })

  it("shows an arrow on each line that says it opens, and turns it when open", () => {
    renderList()
    const button = screen.getByText("first").closest("button") as HTMLButtonElement
    expect(button.textContent).toContain("▸")

    fireEvent.click(button)

    expect(button.textContent).toContain("▾")
  })

  it("has no explanatory paragraph, only the fold, the lines and the controls", () => {
    const { container } = renderList()

    expect(container.querySelectorAll("p")).toHaveLength(0)
  })

  it("keeps a constraint open when the list is reordered, by its text and not its position", () => {
    const { rerender, props } = renderList()
    fireEvent.click(screen.getByText("second"))

    rerender(<LearnedConstraintsList {...props} constraints={["second", "first", LONG]} />)

    expect(screen.getByText("second").closest("button")?.getAttribute("aria-expanded")).toBe("true")
    expect(screen.getByText("first").closest("button")?.getAttribute("aria-expanded")).toBe("false")
  })

  it("says so, with a count of none, when there are no permanent rules yet", () => {
    renderList({ constraints: [] })

    expect(screen.getByText("Permanent rules (0)")).toBeTruthy()
    expect(screen.getByText("None yet.")).toBeTruthy()
  })

  it("adds a trimmed constraint and removes the one asked for", () => {
    const { props } = renderList()

    fireEvent.change(screen.getByPlaceholderText("Add a permanent rule"), { target: { value: "  keep names short  " } })
    fireEvent.click(screen.getByText("Add"))
    expect(props.onAdd).toHaveBeenCalledWith("keep names short")

    fireEvent.click(screen.getAllByText("Remove")[1])
    expect(props.onRemove).toHaveBeenCalledWith("second")
  })

  it("offers no way to change the list when it is read only", () => {
    renderList({ readOnly: true })

    expect(screen.queryByText("Remove")).toBeNull()
    expect(screen.queryByPlaceholderText("Add a permanent rule")).toBeNull()
    expect(screen.queryByLabelText("Drag to reorder")).toBeNull()
  })
})

describe("firstSentence", () => {
  it("is the first sentence, with an ellipsis when more follows", () => {
    expect(firstSentence("Never use `and` here. It is not short-circuit. Use a nested if.")).toBe("Never use `and` here. …")
  })

  it("is the whole text when it is one sentence, with or without a final period", () => {
    expect(firstSentence("Keep names short.")).toBe("Keep names short.")
    expect(firstSentence("Keep names short")).toBe("Keep names short")
  })

  it("does not end a sentence at an abbreviation inside a parenthesis", () => {
    expect(firstSentence("Names must not be keywords (e.g. `def`, `rule`). Use descriptive ones.")).toBe(
      "Names must not be keywords (e.g. `def`, `rule`). …"
    )
  })

  it("does not cut at a period that is followed by a lower case word", () => {
    expect(firstSentence("Use the v1.2 form, not the old one.")).toBe("Use the v1.2 form, not the old one.")
  })
})
