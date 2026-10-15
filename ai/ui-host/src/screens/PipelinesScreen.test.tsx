// @vitest-environment jsdom
//
// The first screen of the generate mode: the pipelines already generated.
// Only the network boundary and the visual primitives are mocked (see
// GenerateScreen.test.tsx for why design-system is stubbed).
import type { ReactNode } from "react"
import { cleanup, fireEvent, render, screen, within } from "@testing-library/react"
import { afterEach, beforeEach, describe, expect, it, vi } from "vitest"
import PipelinesScreen from "./PipelinesScreen"
import * as service from "@/services/generator.service"
import type { PipelineSummary } from "@/services/generator.service"

vi.mock("design-system", () => ({
  Button: ({ children, onClick, disabled }: { children?: ReactNode; onClick?: () => void; disabled?: boolean }) => (
    <button onClick={onClick} disabled={disabled}>
      {children}
    </button>
  ),
  Icon: () => null,
}))

vi.mock("@/services/generator.service", async (importOriginal) => {
  const actual = await importOriginal<typeof import("@/services/generator.service")>()
  return { ...actual, listPipelines: vi.fn() }
})

afterEach(cleanup)

const daysAgo = (days: number) => new Date(Date.now() - days * 86400 * 1000).toISOString()

const ROWS: PipelineSummary[] = [
  {
    id: "p1",
    swarch_name: "hello-java-ci.swarch",
    source_path: "/repos/hello-java-ci/swarch/hello-java-ci.swarch",
    platforms: [{ id: "gitlab", name: "GitLab CI" }],
    outputs: [".gitlab-ci.yml"],
    generated_at: daysAgo(2),
  },
  {
    id: "p2",
    swarch_name: "payments-service.swarch",
    source_path: null,
    platforms: [
      { id: "gitlab", name: "GitLab CI" },
      { id: "bamboo", name: "Bamboo" },
    ],
    outputs: [".gitlab-ci.yml", "bamboo-specs/bamboo.yaml"],
    generated_at: daysAgo(8),
  },
]

function renderScreen(handlers: { onBack?: () => void; onNew?: () => void; onOpen?: (id: string) => void } = {}) {
  return render(
    <PipelinesScreen
      onBack={handlers.onBack ?? (() => undefined)}
      onNew={handlers.onNew ?? (() => undefined)}
      onOpen={handlers.onOpen ?? (() => undefined)}
    />
  )
}

beforeEach(() => {
  vi.resetAllMocks()
})

describe("PipelinesScreen", () => {
  it("lists each pipeline with its model, platforms, files and how long ago it was generated", async () => {
    vi.mocked(service.listPipelines).mockResolvedValue(ROWS)
    renderScreen()

    const table = await screen.findByRole("table")
    const rows = within(table).getAllByRole("row")
    expect(rows).toHaveLength(3)
    const first = within(rows[1])
    expect(first.getByText("hello-java-ci.swarch")).toBeTruthy()
    expect(first.getByText("GitLab CI")).toBeTruthy()
    expect(first.getByText(".gitlab-ci.yml")).toBeTruthy()
    expect(first.getByText("2 days ago")).toBeTruthy()
    const second = within(rows[2])
    expect(second.getByText("GitLab CI, Bamboo")).toBeTruthy()
    expect(second.getByText(".gitlab-ci.yml, bamboo-specs/bamboo.yaml")).toBeTruthy()
    expect(second.getByText("1 week ago")).toBeTruthy()
    expect(screen.getByText("Your pipelines")).toBeTruthy()
  })

  it("names the columns the way the wireframe does", async () => {
    vi.mocked(service.listPipelines).mockResolvedValue(ROWS)
    renderScreen()

    for (const name of ["SWArch input", "Platform(s)", "Output pipeline(s)", "Last generated"]) {
      expect(await screen.findByRole("columnheader", { name })).toBeTruthy()
    }
  })

  it("shows where a model was loaded from as a tooltip, when it was loaded from a path", async () => {
    vi.mocked(service.listPipelines).mockResolvedValue(ROWS)
    renderScreen()

    const name = await screen.findByText("hello-java-ci.swarch")

    expect(name.getAttribute("title")).toBe("/repos/hello-java-ci/swarch/hello-java-ci.swarch")
    expect(screen.getByText("payments-service.swarch").getAttribute("title")).toBeNull()
  })

  it("opens the pipeline whose open link is clicked", async () => {
    vi.mocked(service.listPipelines).mockResolvedValue(ROWS)
    const onOpen = vi.fn()
    renderScreen({ onOpen })

    fireEvent.click(await screen.findByRole("button", { name: "open payments-service.swarch" }))

    expect(onOpen).toHaveBeenCalledWith("p2")
  })

  it("starts a new pipeline and goes back to the start screen", async () => {
    vi.mocked(service.listPipelines).mockResolvedValue(ROWS)
    const onNew = vi.fn()
    const onBack = vi.fn()
    renderScreen({ onNew, onBack })

    fireEvent.click(screen.getByRole("button", { name: "+ New pipeline" }))
    fireEvent.click(screen.getByRole("button", { name: /Start/ }))

    expect(onNew).toHaveBeenCalledTimes(1)
    expect(onBack).toHaveBeenCalledTimes(1)
  })

  it("says it is loading, then that nothing has been generated yet", async () => {
    vi.mocked(service.listPipelines).mockResolvedValue([])
    renderScreen()

    expect(screen.getByText("Loading your pipelines…")).toBeTruthy()
    expect(await screen.findByText(/Nothing generated yet/)).toBeTruthy()
    expect(screen.queryByRole("table")).toBeNull()
    expect(screen.getByRole("button", { name: "+ New pipeline" })).toBeTruthy()
  })

  it("shows the real reason the list could not be loaded, and tries again on request", async () => {
    vi.mocked(service.listPipelines)
      .mockRejectedValueOnce(new service.GeneratorError("Pipelines request failed: 500", 500))
      .mockResolvedValueOnce(ROWS)
    renderScreen()

    expect(await screen.findByText("Pipelines request failed: 500")).toBeTruthy()
    expect(screen.getByRole("button", { name: "+ New pipeline" })).toBeTruthy()
    fireEvent.click(screen.getByRole("button", { name: "Try again" }))

    expect(await screen.findByText("hello-java-ci.swarch")).toBeTruthy()
    expect(screen.queryByText("Pipelines request failed: 500")).toBeNull()
  })
})
