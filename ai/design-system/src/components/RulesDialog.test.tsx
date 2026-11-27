// @vitest-environment jsdom
import { act, cleanup, fireEvent, render, screen, within } from "@testing-library/react"
import { afterEach, describe, expect, it, vi } from "vitest"
import { RulesDialog, numbered } from "./RulesDialog"

afterEach(() => {
  cleanup()
  vi.useRealTimers()
  vi.unstubAllGlobals()
})

const ITEMS = [{ text: "Fix: a dangling reference", learned: true }, { text: "never use def", learned: false }, { text: "Fix: kept", learned: true, permanent: true }]

const show = (items = ITEMS) => render(<RulesDialog title="The rules" note="What they are." items={items} onClose={() => undefined} />)

const stubClipboard = (writeText: (text: string) => Promise<void>) => vi.stubGlobal("navigator", { clipboard: { writeText } })

describe("RulesDialog", () => {
  it("lists the rules as a numbered list, in order, with the title and a sentence about them", () => {
    show()

    const dialog = screen.getByRole("dialog", { name: "The rules" })
    expect(within(dialog).getByText("What they are.")).toBeTruthy()
    const list = dialog.querySelector("ol") as HTMLElement
    expect(Array.from(list.querySelectorAll("li"), (li) => li.querySelector("p")?.textContent)).toEqual(["Fix: a dangling reference", "never use def", "Fix: kept"])
  })

  it("says in words where each rule came from, and that a person made one permanent", () => {
    show()

    const lines = Array.from(document.querySelectorAll("li"), (li) => Array.from(li.querySelectorAll("p"), (p) => p.textContent).slice(1))
    expect(lines).toEqual([
      ["Learned from a failed check"],
      ["Typed by a person"],
      ["Learned from a failed check", "Permanent: used in every draft"],
    ])
  })

  it("keeps the whole text of a long rule, as text that can be selected", () => {
    const long = "Fix: " + "x".repeat(600)
    show([{ text: long, learned: true }])

    expect(screen.getByText(long)).toBeTruthy()
  })

  it("lists two rules with the same text as two lines", () => {
    show([{ text: "same" }, { text: "same" }])

    expect(document.querySelectorAll("li")).toHaveLength(2)
  })

  it("writes the numbered text of all the rules to the clipboard when Copy is pressed, and says so", async () => {
    const writeText = vi.fn(() => Promise.resolve())
    stubClipboard(writeText)
    show()

    await act(async () => {
      fireEvent.click(screen.getByRole("button", { name: "Copy" }))
    })

    expect(writeText).toHaveBeenCalledWith("1. Fix: a dangling reference\n2. never use def\n3. Fix: kept")
    expect(screen.getByRole("status").textContent).toBe("Copied")
  })

  it("says it could not copy when the clipboard refuses, and when there is none", async () => {
    stubClipboard(() => Promise.reject(new Error("denied")))
    show()
    await act(async () => {
      fireEvent.click(screen.getByRole("button", { name: "Copy" }))
    })
    expect(screen.getByRole("status").textContent).toBe("Could not copy")

    cleanup()
    vi.stubGlobal("navigator", {})
    show()
    await act(async () => {
      fireEvent.click(screen.getByRole("button", { name: "Copy" }))
    })
    expect(screen.getByRole("status").textContent).toBe("Could not copy")
  })

  it("stops saying it copied after a moment", async () => {
    vi.useFakeTimers()
    stubClipboard(() => Promise.resolve())
    show()

    await act(async () => {
      fireEvent.click(screen.getByRole("button", { name: "Copy" }))
    })
    expect(screen.getByRole("status").textContent).toBe("Copied")
    await act(async () => {
      vi.advanceTimersByTime(2000)
    })

    expect(screen.getByRole("status").textContent).toBe("")
  })

  it("says nothing until Copy is pressed", () => {
    show()

    expect(screen.getByRole("status").textContent).toBe("")
  })
})

describe("numbered", () => {
  it("numbers the rules from one, one to a line, and is empty for none", () => {
    expect(numbered([{ text: "a" }, { text: "b" }])).toBe("1. a\n2. b")
    expect(numbered([])).toBe("")
  })
})
