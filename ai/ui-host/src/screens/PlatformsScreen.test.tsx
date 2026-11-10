// @vitest-environment jsdom
//
// The first screen of the platforms mode: the platforms MDDOAI can generate
// for, each opening its own screen, with a button to add one. Only the data
// boundary and the visual primitives are mocked: the primitives have their own
// tests inside design-system, and its own copy of react (a symlinked file:
// dependency) would load a second React instance under Vitest.
import type { ReactNode } from "react"
import { cleanup, fireEvent, render, screen } from "@testing-library/react"
import { afterEach, beforeEach, describe, expect, it, vi } from "vitest"
import type { PlatformSummary } from "orchestrator-types"
import PlatformsScreen from "./PlatformsScreen"
import * as orchestrator from "@/services/orchestrator.service"

vi.mock("design-system", () => ({
  Button: ({ children, onClick }: { children?: ReactNode; onClick?: () => void }) => (
    <button onClick={onClick}>{children}</button>
  ),
  Icon: () => null,
}))

vi.mock("@/services/orchestrator.service", () => ({ getPlatforms: vi.fn() }))

afterEach(cleanup)

const platform = (id: string, name: string, published_at: string | null): PlatformSummary => ({
  id,
  name,
  output_filename: `${id}.yml`,
  output_model_name: "Model",
  version: "1.2.0",
  versions: ["1.0.0", "1.2.0"],
  published_versions: published_at ? ["1.2.0"] : [],
  published: published_at !== null,
  source_run_id: published_at ? "run1" : null,
  published_at,
})

const TWO_DAYS_AGO = new Date(Date.now() - 2 * 86400 * 1000).toISOString()

beforeEach(() => {
  vi.resetAllMocks()
  vi.mocked(orchestrator.getPlatforms).mockResolvedValue([
    platform("gitlab", "GitLab CI", null),
    platform("woodpecker-ci", "Woodpecker CI", TWO_DAYS_AGO),
  ])
})

function renderScreen(handlers: { onBack?: () => void; onNew?: () => void; onOpen?: (id: string) => void } = {}) {
  return render(
    <PlatformsScreen
      onBack={handlers.onBack ?? (() => undefined)}
      onNew={handlers.onNew ?? (() => undefined)}
      onOpen={handlers.onOpen ?? (() => undefined)}
    />
  )
}

describe("PlatformsScreen", () => {
  it("lists the real platforms with their version, where they came from and the file they write", async () => {
    renderScreen()

    expect(screen.getByRole("heading", { name: "Supported platforms" })).toBeTruthy()
    expect(await screen.findByText("GitLab CI")).toBeTruthy()
    expect(screen.getByText("Woodpecker CI")).toBeTruthy()
    expect(screen.getAllByText("1.2.0")).toHaveLength(2)
    expect(screen.getByText("Included with MDDOAI")).toBeTruthy()
    expect(screen.getByText("Published 2 days ago")).toBeTruthy()
    expect(screen.getByText("woodpecker-ci.yml")).toBeTruthy()
  })

  it("shows nothing that is made up: no status it has no data for, and no link that does nothing", async () => {
    renderScreen()
    await screen.findByText("GitLab CI")

    expect(screen.queryByRole("link")).toBeNull()
    expect(screen.queryByText("view breakdown")).toBeNull()
    expect(screen.queryByText(/In progress|Needs attention|Bamboo|TeamCity/)).toBeNull()
  })

  it("opens a platform when its row is pressed, by mouse or by its name button", async () => {
    const onOpen = vi.fn()
    renderScreen({ onOpen })

    fireEvent.click((await screen.findByText("woodpecker-ci.yml")).closest("tr") as HTMLElement)
    fireEvent.click(screen.getByRole("button", { name: "Open GitLab CI" }))

    expect(onOpen).toHaveBeenNthCalledWith(1, "woodpecker-ci")
    expect(onOpen).toHaveBeenNthCalledWith(2, "gitlab")
  })

  it("does not open a platform when the press ends a text selection inside its row", async () => {
    const onOpen = vi.fn()
    renderScreen({ onOpen })
    const row = (await screen.findByText("woodpecker-ci.yml")).closest("tr") as HTMLElement
    const range = document.createRange()
    range.selectNodeContents(row)
    window.getSelection()?.removeAllRanges()
    window.getSelection()?.addRange(range)

    fireEvent.click(row)

    expect(onOpen).not.toHaveBeenCalled()
    window.getSelection()?.removeAllRanges()
  })

  it("opens a platform from its name button even when text is selected in the row", async () => {
    const onOpen = vi.fn()
    renderScreen({ onOpen })
    const row = (await screen.findByText("woodpecker-ci.yml")).closest("tr") as HTMLElement
    const range = document.createRange()
    range.selectNodeContents(row)
    window.getSelection()?.removeAllRanges()
    window.getSelection()?.addRange(range)

    fireEvent.click(screen.getByRole("button", { name: "Open Woodpecker CI" }))

    // Once, not twice: the button opens it and the row does not open it again.
    expect(onOpen).toHaveBeenCalledTimes(1)
    expect(onOpen).toHaveBeenCalledWith("woodpecker-ci")
    window.getSelection()?.removeAllRanges()
  })

  it("says it is loading, and then that there is no platform yet", async () => {
    vi.mocked(orchestrator.getPlatforms).mockResolvedValue([])
    renderScreen()

    expect(screen.getByText("Loading the platforms…")).toBeTruthy()
    expect(await screen.findByText(/No platform yet/)).toBeTruthy()
  })

  it("says why the list could not load and tries again when asked", async () => {
    vi.mocked(orchestrator.getPlatforms)
      .mockRejectedValueOnce(new Error("The platforms cannot be reached right now, try again shortly."))
      .mockResolvedValueOnce([platform("gitlab", "GitLab CI", null)])
    renderScreen()

    expect((await screen.findByRole("alert")).textContent).toContain("cannot be reached right now")
    fireEvent.click(screen.getByRole("button", { name: "Try again" }))

    expect(await screen.findByText("GitLab CI")).toBeTruthy()
    expect(screen.queryByRole("alert")).toBeNull()
  })

  it("starts a new platform, and goes back to the start screen", async () => {
    const onNew = vi.fn()
    const onBack = vi.fn()
    renderScreen({ onNew, onBack })
    await screen.findByText("GitLab CI")

    fireEvent.click(screen.getByRole("button", { name: "+ New platform" }))
    fireEvent.click(screen.getByRole("button", { name: "CI/CD Platforms" }))

    expect(onNew).toHaveBeenCalledTimes(1)
    expect(onBack).toHaveBeenCalledTimes(1)
  })
})
