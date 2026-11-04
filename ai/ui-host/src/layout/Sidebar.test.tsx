// @vitest-environment jsdom
import type { ReactNode } from "react"
import { cleanup, fireEvent, render, screen } from "@testing-library/react"
import { MemoryRouter } from "react-router-dom"
import { afterEach, beforeEach, describe, expect, it, vi } from "vitest"
import { Sidebar } from "./Sidebar"
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
vi.mock("../services/sessions.service", () => ({ getSessions: vi.fn(), closeSession: vi.fn() }))

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

const renderSidebar = (onOpenPipeline = vi.fn(), openPipelineId?: string) =>
  render(
    <MemoryRouter>
      <Sidebar onOpenPipeline={onOpenPipeline} openPipelineId={openPipelineId} />
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
})
