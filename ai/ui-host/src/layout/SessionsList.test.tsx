// @vitest-environment jsdom
//
// The runs list: rows of one platform told apart, and a delete that is real. Only
// the data boundary and the visual primitives are mocked, as in the sidebar's tests.
import { act, cleanup, fireEvent, render, screen } from "@testing-library/react"
import { MemoryRouter } from "react-router-dom"
import { afterEach, beforeEach, describe, expect, it, vi } from "vitest"
import { SessionsList } from "./SessionsList"
import * as sessions from "../services/sessions.service"
import type { Session } from "../services/sessions.service"

vi.mock("design-system", () => ({ Icon: () => null }))
vi.mock("../services/sessions.service", () => ({ getSessions: vi.fn(), deleteSession: vi.fn() }))

afterEach(() => {
  cleanup()
  vi.useRealTimers()
})

const NOW = Date.now() / 1000

const run = (id: string, over: Partial<Session> = {}): Session => ({
  id,
  name: "Woodpecker CI",
  type: "platform",
  state: "normal",
  stage: "atl",
  startedAt: NOW - 3600,
  ...over,
})

const CURRENT = run("r1", { state: "selected", stage: "psm", startedAt: NOW - 120 })
const OLDER = run("r2", { stage: "atl", startedAt: NOW - 5 * 3600 })
const OLDEST = run("r3", { stage: "generation", startedAt: NOW - 3 * 86400 })

beforeEach(() => {
  vi.resetAllMocks()
  vi.mocked(sessions.getSessions).mockResolvedValue([CURRENT, OLDER, OLDEST])
  vi.mocked(sessions.deleteSession).mockResolvedValue(undefined)
})

const renderList = () =>
  render(
    <MemoryRouter initialEntries={["/"]}>
      <SessionsList activeTab="platform" />
    </MemoryRouter>
  )

describe("the runs list", () => {
  it("tells apart runs that are all of one platform, by where each is and when it began", async () => {
    renderList()

    expect(await screen.findByText("PSM, 2 minutes ago")).toBeTruthy()
    expect(screen.getByText("ATL, 5 hours ago")).toBeTruthy()
    expect(screen.getByText("Generation + Test, 3 days ago")).toBeTruthy()
    expect(screen.getAllByText("Woodpecker CI")).toHaveLength(3)
  })

  it("deletes a run for real, and the row goes only once it is deleted", async () => {
    let finish: () => void = () => undefined
    vi.mocked(sessions.deleteSession).mockReturnValue(new Promise<void>((resolve) => (finish = resolve)))
    renderList()
    await screen.findByText("ATL, 5 hours ago")

    fireEvent.click(screen.getAllByRole("button", { name: "Delete Woodpecker CI" })[0])
    fireEvent.click(screen.getByRole("button", { name: "Delete" }))

    expect(sessions.deleteSession).toHaveBeenCalledWith("r2")
    expect(screen.getAllByText("Woodpecker CI")).toHaveLength(3)
    await act(async () => finish())
    expect(screen.getAllByText("Woodpecker CI")).toHaveLength(2)
    expect(screen.queryByText("ATL, 5 hours ago")).toBeNull()
    expect(screen.getByText("Generation + Test, 3 days ago")).toBeTruthy()
  })

  it("keeps a run the backend refuses to delete, with the reason", async () => {
    vi.mocked(sessions.deleteSession).mockRejectedValue(new Error("Woodpecker CI 1.0.0 was published from this run, so the run is kept."))
    renderList()
    await screen.findByText("ATL, 5 hours ago")

    fireEvent.click(screen.getAllByRole("button", { name: "Delete Woodpecker CI" })[0])
    fireEvent.click(screen.getByRole("button", { name: "Delete" }))

    expect((await screen.findByRole("alert")).textContent).toContain("was published from this run")
    expect(screen.getAllByText("Woodpecker CI")).toHaveLength(3)
  })

  it("does not bring a deleted run back when a poll that began before the delete answers", async () => {
    vi.useFakeTimers()
    // The first poll answers at once; the one after it is slow, and its answer is the old list.
    let answerStalePoll: (list: Session[]) => void = () => undefined
    vi.mocked(sessions.getSessions)
      .mockResolvedValueOnce([CURRENT, OLDER])
      .mockReturnValueOnce(new Promise<Session[]>((resolve) => (answerStalePoll = resolve)))
    renderList()
    await act(async () => {
      await vi.advanceTimersByTimeAsync(0)
    })
    await act(async () => {
      await vi.advanceTimersByTimeAsync(5000)
    })

    fireEvent.click(screen.getByRole("button", { name: "Delete Woodpecker CI" }))
    fireEvent.click(screen.getByRole("button", { name: "Delete" }))
    await act(async () => {
      await vi.advanceTimersByTimeAsync(0)
    })
    expect(screen.queryByText("ATL, 1 hour ago")).toBeNull()
    await act(async () => answerStalePoll([CURRENT, OLDER]))

    expect(screen.queryByText("ATL, 1 hour ago")).toBeNull()
  })
})
