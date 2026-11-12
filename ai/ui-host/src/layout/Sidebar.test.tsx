// @vitest-environment jsdom
import type { ReactNode } from "react"
import { cleanup, fireEvent, render, screen } from "@testing-library/react"
import { MemoryRouter } from "react-router-dom"
import { afterEach, beforeEach, describe, expect, it, vi } from "vitest"
import { Sidebar } from "./Sidebar"
import { useSidebarTab } from "./sidebarTab"
import { SidebarTabProvider } from "./SidebarTabProvider"
import * as generator from "../services/generator.service"
import * as sessions from "../services/sessions.service"

// design-system is stubbed, as in the screen tests, which explain why.
vi.mock("design-system", () => ({
  Button: ({ children, onClick }: { children?: ReactNode; onClick?: () => void }) => (
    <button onClick={onClick}>{children}</button>
  ),
  Icon: () => null,
  Tabs: ({
    items,
    onChange,
  }: {
    items: { id: string; label: string }[]
    activeId: string
    onChange: (id: string) => void
  }) => (
    <div>
      {items.map((item) => (
        <button key={item.id} onClick={() => onChange(item.id)}>
          {item.label}
        </button>
      ))}
    </div>
  ),
}))
vi.mock("../services/generator.service", () => ({ listPipelines: vi.fn() }))
vi.mock("../services/sessions.service", () => ({ getSessions: vi.fn(), deleteSession: vi.fn() }))

const PIPELINE = {
  id: "p1",
  swarch_name: "shop.swarch",
  source_path: null,
  platforms: [{ id: "gitlab", name: "GitLab CI" }],
  outputs: [".gitlab-ci.yml"],
  generated_at: "2026-11-03T10:00:00Z",
}

beforeEach(() => {
  vi.mocked(generator.listPipelines).mockResolvedValue([PIPELINE])
  vi.mocked(sessions.getSessions).mockResolvedValue([])
})
afterEach(cleanup)

// Shows the tab the sidebar has shared, as a screen that follows it would read it.
function TabProbe() {
  return <p data-testid="shared-tab">{useSidebarTab().activeTab}</p>
}

const renderSidebar = (onOpenPipeline = vi.fn(), openPipelineId?: string, path = "/") =>
  render(
    <MemoryRouter initialEntries={[path]}>
      <SidebarTabProvider>
        <Sidebar onOpenPipeline={onOpenPipeline} openPipelineId={openPipelineId} />
        <TabProbe />
      </SidebarTabProvider>
    </MemoryRouter>
  )

describe("the sidebar's tabs", () => {
  it("lists the generated pipelines under Pipelines, and hands a chosen one to its caller", async () => {
    const onOpenPipeline = vi.fn()
    renderSidebar(onOpenPipeline)

    fireEvent.click(await screen.findByRole("button", { name: /shop\.swarch/ }))

    expect(onOpenPipeline).toHaveBeenCalledWith("p1")
  })

  it("marks the pipeline that is open", async () => {
    renderSidebar(vi.fn(), "p1")

    expect((await screen.findByRole("button", { name: /shop\.swarch/ })).getAttribute("aria-current")).toBe("true")
  })

  it("lists the platform runs under Platforms instead", async () => {
    renderSidebar()
    await screen.findByText("shop.swarch")

    fireEvent.click(screen.getByRole("button", { name: "Platforms" }))

    expect(await screen.findByText("Open sessions")).toBeTruthy()
    expect(screen.queryByText("shop.swarch")).toBeNull()
  })

  it("shares the tab that is on, so a screen can follow it", async () => {
    renderSidebar()
    await screen.findByText("shop.swarch")
    expect(screen.getByTestId("shared-tab").textContent).toBe("pipeline")

    fireEvent.click(screen.getByRole("button", { name: "Platforms" }))
    expect(screen.getByTestId("shared-tab").textContent).toBe("platform")

    fireEvent.click(screen.getByRole("button", { name: "Pipelines" }))
    expect(screen.getByTestId("shared-tab").textContent).toBe("pipeline")
  })

  it.each([
    ["/platforms", "platform"],
    ["/integration", "platform"],
    ["/generate", "pipeline"],
    ["/generate/p1", "pipeline"],
  ])("switches to the tab of %s when a screen is opened there", async (path, tab) => {
    renderSidebar(vi.fn(), undefined, path)
    await screen.findByTestId("shared-tab")

    expect(screen.getByTestId("shared-tab").textContent).toBe(tab)
  })
})

describe("which open session is highlighted", () => {
  const RUNS = [
    { id: "r1", name: "Woodpecker CI", type: "platform" as const, state: "selected" as const, stage: "psm" as const, startedAt: null },
    { id: "r2", name: "GitHub Actions", type: "platform" as const, state: "normal" as const, stage: "atl" as const, startedAt: null },
  ]
  const isOnScreen = (name: string) => screen.getByRole("button", { name }).getAttribute("aria-current") === "true"

  beforeEach(() => {
    vi.mocked(sessions.getSessions).mockResolvedValue(RUNS)
  })

  it("is the run named in the address, even when another run is the current one", async () => {
    renderSidebar(vi.fn(), undefined, "/integration?run=r2")
    await screen.findByText("GitHub Actions")

    expect(isOnScreen("GitHub Actions")).toBe(true)
    expect(isOnScreen("Woodpecker CI")).toBe(false)
  })

  it("is the current run when the address names none", async () => {
    renderSidebar(vi.fn(), undefined, "/integration")
    await screen.findByText("GitHub Actions")

    expect(isOnScreen("Woodpecker CI")).toBe(true)
    expect(isOnScreen("GitHub Actions")).toBe(false)
  })

  it("is none while a blank run is on screen", async () => {
    renderSidebar(vi.fn(), undefined, "/integration?new=1")
    await screen.findByText("GitHub Actions")

    expect(isOnScreen("Woodpecker CI")).toBe(false)
    expect(isOnScreen("GitHub Actions")).toBe(false)
  })

  it("moves to the row that is opened", async () => {
    renderSidebar(vi.fn(), undefined, "/integration")
    fireEvent.click(await screen.findByText("GitHub Actions"))

    expect(isOnScreen("GitHub Actions")).toBe(true)
    expect(isOnScreen("Woodpecker CI")).toBe(false)
  })
})
