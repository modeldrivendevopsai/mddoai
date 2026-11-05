// @vitest-environment jsdom
//
// The first screen of the platforms mode: the platforms already supported,
// with a button to add one. Only the data boundary and the visual primitives
// are mocked (see GenerateScreen.test.tsx for why design-system is stubbed).
import type { ReactNode } from "react"
import { cleanup, fireEvent, render, screen } from "@testing-library/react"
import { afterEach, beforeEach, describe, expect, it, vi } from "vitest"
import PlatformsScreen from "./PlatformsScreen"
import * as platformsService from "@/services/platforms.service"

vi.mock("design-system", () => ({
  Button: ({ children, onClick }: { children?: ReactNode; onClick?: () => void }) => (
    <button onClick={onClick}>{children}</button>
  ),
  Panel: ({ children, className }: { children?: ReactNode; className?: string }) => (
    <div className={className}>{children}</div>
  ),
  StatusPill: ({ children }: { children?: ReactNode }) => <span>{children}</span>,
  Icon: () => null,
}))

vi.mock("@/services/platforms.service", () => ({ getPlatforms: vi.fn() }))

afterEach(cleanup)

beforeEach(() => {
  vi.resetAllMocks()
  vi.mocked(platformsService.getPlatforms).mockResolvedValue([
    { id: "a", name: "GitLab CI v1.7", status: "success", statusLabel: "Ready" },
    { id: "b", name: "TeamCity v0.9", status: "danger", statusLabel: "Needs attention" },
  ])
})

function renderScreen(handlers: { onBack?: () => void; onNew?: () => void } = {}) {
  return render(<PlatformsScreen onBack={handlers.onBack ?? (() => undefined)} onNew={handlers.onNew ?? (() => undefined)} />)
}

describe("PlatformsScreen", () => {
  it("lists the supported platforms with their status, as a list and not the editing view", async () => {
    renderScreen()

    expect(screen.getByRole("heading", { name: "Supported platforms" })).toBeTruthy()
    expect(await screen.findByText("GitLab CI v1.7")).toBeTruthy()
    expect(screen.getByText("Ready")).toBeTruthy()
    expect(screen.getByText("TeamCity v0.9")).toBeTruthy()
    expect(screen.getByText("Needs attention")).toBeTruthy()
  })

  it("has no link that does nothing: each row is a status and a name", async () => {
    renderScreen()
    await screen.findByText("GitLab CI v1.7")

    expect(screen.queryByRole("link")).toBeNull()
    expect(screen.queryByText("view breakdown")).toBeNull()
  })

  it("starts a new platform, and goes back to the start screen", async () => {
    const onNew = vi.fn()
    const onBack = vi.fn()
    renderScreen({ onNew, onBack })
    await screen.findByText("GitLab CI v1.7")

    fireEvent.click(screen.getByRole("button", { name: "+ New platform" }))
    fireEvent.click(screen.getByRole("button", { name: "CI/CD Platforms" }))

    expect(onNew).toHaveBeenCalledTimes(1)
    expect(onBack).toHaveBeenCalledTimes(1)
  })
})
