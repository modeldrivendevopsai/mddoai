// @vitest-environment jsdom
//
// One run in the sidebar: what it says, and the delete that asks first. Only the
// visual primitives are mocked: the primitives have their own tests inside
// design-system, and its own copy of react (a symlinked file: dependency) would
// load a second React instance under Vitest.
import { cleanup, fireEvent, render, screen, waitFor } from "@testing-library/react"
import { afterEach, describe, expect, it, vi } from "vitest"
import { SessionRow } from "./SessionRow"
import type { Session } from "../services/sessions.service"

vi.mock("design-system", () => ({ Icon: () => null }))

afterEach(cleanup)

const TWO_HOURS_AGO = Date.now() / 1000 - 2 * 3600

const session = (over: Partial<Session> = {}): Session => ({
  id: "r1",
  name: "Woodpecker CI",
  type: "platform",
  state: "normal",
  stage: "psm",
  startedAt: TWO_HOURS_AGO,
  ...over,
})

function renderRow(props: { session?: Session; onOpen?: () => void; onDelete?: () => Promise<void> } = {}) {
  const onOpen = props.onOpen ?? vi.fn()
  const onDelete = props.onDelete ?? vi.fn().mockResolvedValue(undefined)
  render(<SessionRow session={props.session ?? session()} selected={false} onOpen={onOpen} onDelete={onDelete} />)
  return { onOpen, onDelete }
}

const crossFor = (name = "Woodpecker CI") => screen.getByRole("button", { name: `Delete ${name}` })

describe("SessionRow", () => {
  it("says the platform, where the run is and how long ago it began", () => {
    renderRow()

    expect(screen.getByText("Woodpecker CI")).toBeTruthy()
    expect(screen.getByText("PSM, 2 hours ago")).toBeTruthy()
  })

  it("asks before it deletes, and deletes nothing until it is told to", () => {
    const { onDelete } = renderRow()

    fireEvent.click(crossFor())

    expect(screen.getByText("Delete this run for good?")).toBeTruthy()
    expect(onDelete).not.toHaveBeenCalled()
  })

  it("goes back to the row when the person keeps the run", () => {
    const { onDelete } = renderRow()
    fireEvent.click(crossFor())

    fireEvent.click(screen.getByRole("button", { name: "Keep" }))

    expect(onDelete).not.toHaveBeenCalled()
    expect(screen.getByText("PSM, 2 hours ago")).toBeTruthy()
    expect(screen.queryByText("Delete this run for good?")).toBeNull()
  })

  it("deletes the run when the person confirms, once, and holds the buttons while it does", async () => {
    let finish: () => void = () => undefined
    const onDelete = vi.fn(() => new Promise<void>((resolve) => (finish = resolve)))
    renderRow({ onDelete })
    fireEvent.click(crossFor())

    fireEvent.click(screen.getByRole("button", { name: "Delete" }))

    expect(onDelete).toHaveBeenCalledTimes(1)
    expect((screen.getByRole("button", { name: "Delete" }) as HTMLButtonElement).disabled).toBe(true)
    expect((screen.getByRole("button", { name: "Keep" }) as HTMLButtonElement).disabled).toBe(true)
    finish()
  })

  it("shows the backend's reason when the run is refused, and offers the cross again", async () => {
    const reason = "Woodpecker CI 1.0.0 was published from this run, so the run is kept. Remove that platform first."
    renderRow({ onDelete: vi.fn().mockRejectedValue(new Error(reason)) })
    fireEvent.click(crossFor())

    fireEvent.click(screen.getByRole("button", { name: "Delete" }))

    expect((await screen.findByRole("alert")).textContent).toBe(reason)
    await waitFor(() => expect(crossFor()).toBeTruthy())
  })

  it("says something plain when the refusal gives no reason", async () => {
    renderRow({ onDelete: vi.fn().mockRejectedValue(new Error("")) })
    fireEvent.click(crossFor())

    fireEvent.click(screen.getByRole("button", { name: "Delete" }))

    expect((await screen.findByRole("alert")).textContent).toBe("Could not delete the run.")
  })

  it("has no cross on the current run", () => {
    renderRow({ session: session({ state: "selected" }) })

    expect(screen.queryByRole("button", { name: /^Delete / })).toBeNull()
  })

  it("opens the run when the row is pressed, and not when the cross or a confirm button is", () => {
    const { onOpen } = renderRow()

    fireEvent.click(crossFor())
    fireEvent.click(screen.getByRole("button", { name: "Keep" }))
    expect(onOpen).not.toHaveBeenCalled()

    fireEvent.click(screen.getByText("Woodpecker CI"))
    expect(onOpen).toHaveBeenCalledTimes(1)
  })

  it("opens the run on Enter on the row, but not on Enter on a button inside it", () => {
    const { onOpen } = renderRow()

    fireEvent.keyDown(crossFor(), { key: "Enter" })
    expect(onOpen).not.toHaveBeenCalled()

    fireEvent.keyDown(screen.getAllByRole("button")[0], { key: "Enter" })
    expect(onOpen).toHaveBeenCalledTimes(1)
  })
})
