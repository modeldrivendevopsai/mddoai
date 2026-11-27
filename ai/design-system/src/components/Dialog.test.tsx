// @vitest-environment jsdom
import { cleanup, fireEvent, render, screen } from "@testing-library/react"
import { useState } from "react"
import { afterEach, describe, expect, it, vi } from "vitest"
import { Dialog } from "./Dialog"

afterEach(cleanup)

describe("Dialog", () => {
  it("is a modal dialog named by its title, with its body and footer", () => {
    render(
      <Dialog title="The title" onClose={() => undefined} footer={<button>Footer action</button>}>
        <p>The body</p>
      </Dialog>
    )

    const dialog = screen.getByRole("dialog", { name: "The title" })
    expect(dialog.getAttribute("aria-modal")).toBe("true")
    expect(screen.getByText("The body")).toBeTruthy()
    expect(screen.getByRole("button", { name: "Footer action" })).toBeTruthy()
  })

  it("draws no footer when it is given none", () => {
    const { container } = render(
      <Dialog title="t" onClose={() => undefined}>
        x
      </Dialog>
    )

    expect(container.querySelector("footer")).toBeNull()
  })

  it("takes keyboard focus when it opens and gives it back to what opened it", () => {
    function Page() {
      const [open, setOpen] = useState(false)
      return (
        <>
          <button onClick={() => setOpen(true)}>Open</button>
          {open && (
            <Dialog title="t" onClose={() => setOpen(false)}>
              x
            </Dialog>
          )}
        </>
      )
    }
    render(<Page />)
    const opener = screen.getByRole("button", { name: "Open" })
    opener.focus()

    fireEvent.click(opener)
    expect(document.activeElement).toBe(screen.getByRole("dialog"))

    fireEvent.keyDown(document, { key: "Escape" })
    expect(screen.queryByRole("dialog")).toBeNull()
    expect(document.activeElement).toBe(opener)
  })

  it("asks to close on Escape, on the close button, and on a press on the backdrop only", () => {
    const onClose = vi.fn()
    render(
      <Dialog title="t" onClose={onClose}>
        x
      </Dialog>
    )

    fireEvent.mouseDown(screen.getByRole("dialog"))
    expect(onClose).not.toHaveBeenCalled()

    fireEvent.keyDown(document, { key: "Escape" })
    fireEvent.click(screen.getByRole("button", { name: "Close" }))
    fireEvent.mouseDown(screen.getByRole("dialog").parentElement as HTMLElement)
    expect(onClose).toHaveBeenCalledTimes(3)
  })

  it("keeps Tab inside: from the last control to the first, and Shift+Tab the other way", () => {
    render(
      <Dialog title="t" onClose={() => undefined} footer={<button>Last</button>}>
        x
      </Dialog>
    )
    const close = screen.getByRole("button", { name: "Close" })
    const last = screen.getByRole("button", { name: "Last" })

    last.focus()
    fireEvent.keyDown(document, { key: "Tab" })
    expect(document.activeElement).toBe(close)

    fireEvent.keyDown(document, { key: "Tab", shiftKey: true })
    expect(document.activeElement).toBe(last)
  })

  it("brings focus back in when it has fallen outside the dialog", () => {
    render(
      <>
        <button>Outside</button>
        <Dialog title="t" onClose={() => undefined}>
          x
        </Dialog>
      </>
    )
    screen.getByRole("button", { name: "Outside" }).focus()

    fireEvent.keyDown(document, { key: "Tab" })

    expect(document.activeElement).toBe(screen.getByRole("button", { name: "Close" }))
  })

  it("stops listening for keys once it is gone", () => {
    const onClose = vi.fn()
    const { unmount } = render(
      <Dialog title="t" onClose={onClose}>
        x
      </Dialog>
    )

    unmount()
    fireEvent.keyDown(document, { key: "Escape" })

    expect(onClose).not.toHaveBeenCalled()
  })
})
