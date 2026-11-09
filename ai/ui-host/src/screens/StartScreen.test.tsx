// @vitest-environment jsdom
//
// The start screen: the picture of how the two options connect, and the list
// under it that follows the sidebar's tab. Only the network boundary and the
// visual primitives are mocked: the primitives have their own tests inside
// design-system, and its own copy of react (a symlinked file: dependency)
// would load a second React instance under Vitest.
import { Fragment, useLayoutEffect } from "react"
import type { ReactNode } from "react"
import { cleanup, fireEvent, render, screen, within } from "@testing-library/react"
import { afterEach, beforeEach, describe, expect, it, vi } from "vitest"
import { StartScreen } from "./StartScreen"
import { useSidebarTab } from "@/layout/sidebarTab"
import { SidebarTabProvider } from "@/layout/SidebarTabProvider"
import * as generator from "@/services/generator.service"
import type { PipelineSummary } from "@/services/generator.service"
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

vi.mock("@/services/generator.service", async (importOriginal) => {
  const actual = await importOriginal<typeof import("@/services/generator.service")>()
  return { ...actual, listPipelines: vi.fn() }
})
vi.mock("@/services/platforms.service", () => ({ getPlatforms: vi.fn() }))

afterEach(cleanup)

const hoursAgo = (hours: number) => new Date(Date.now() - hours * 3600 * 1000).toISOString()

const pipeline = (n: number): PipelineSummary => ({
  id: `p${n}`,
  swarch_name: `model-${n}.swarch`,
  source_path: null,
  platforms: [{ id: "gitlab", name: "GitLab CI" }],
  outputs: [".gitlab-ci.yml"],
  generated_at: hoursAgo(n),
})

const PLATFORMS: platformsService.Platform[] = [
  { id: "a", name: "GitLab CI v1.7", status: "success", statusLabel: "Ready" },
  { id: "b", name: "Bamboo v2.1.2", status: "warning", statusLabel: "In progress", resumable: true },
]

// Stands in for the sidebar's two tabs.
function TabButtons() {
  const { setActiveTab } = useSidebarTab()
  return (
    <>
      <button onClick={() => setActiveTab("platform")}>show platforms tab</button>
      <button onClick={() => setActiveTab("pipeline")}>show pipelines tab</button>
    </>
  )
}

// Turns the Platforms tab on before its children are first shown, as when the
// start screen is opened while that tab is already on.
function PlatformsTabAlreadyOn({ children }: { children: ReactNode }) {
  const { activeTab, setActiveTab } = useSidebarTab()
  useLayoutEffect(() => setActiveTab("platform"), [setActiveTab])
  return activeTab === "platform" ? children : null
}

function renderStart(
  handlers: { onSelectOption?: (id: string) => void; onOpenPipeline?: (id: string) => void } = {},
  { platformsTabOn = false } = {}
) {
  const Wrap = platformsTabOn ? PlatformsTabAlreadyOn : Fragment
  return render(
    <SidebarTabProvider>
      <TabButtons />
      <Wrap>
        <StartScreen
          onSelectOption={handlers.onSelectOption}
          onOpenPipeline={handlers.onOpenPipeline ?? (() => undefined)}
        />
      </Wrap>
    </SidebarTabProvider>
  )
}

beforeEach(() => {
  vi.resetAllMocks()
  vi.mocked(generator.listPipelines).mockResolvedValue([pipeline(2), pipeline(5)])
  vi.mocked(platformsService.getPlatforms).mockResolvedValue(PLATFORMS)
})

describe("the picture of the two options", () => {
  it("says what each arrow means in a few words, with no paragraph to read", () => {
    renderStart()

    expect(screen.getByText("Gap in the pipeline")).toBeTruthy()
    expect(screen.getByText("Update the platform")).toBeTruthy()
    expect(screen.getByText("Platform fixed")).toBeTruthy()
    expect(screen.getByText("Generate again")).toBeTruthy()
    expect(screen.queryByText(/How they connect/)).toBeNull()
  })

  it("gives each option one short line, in plain words", () => {
    renderStart()

    expect(screen.getByText("From your architecture file.")).toBeTruthy()
    expect(screen.getByText("From its documentation.")).toBeTruthy()
    expect(screen.queryByText(/SWArch/)).toBeNull()
  })

  it("opens each option with a button called Open, never Start", () => {
    const onSelectOption = vi.fn()
    renderStart({ onSelectOption })

    expect(screen.queryByRole("button", { name: "Start" })).toBeNull()
    const open = screen.getAllByRole("button", { name: "Open" })
    expect(open).toHaveLength(2)
    fireEvent.click(open[0])
    fireEvent.click(open[1])

    expect(onSelectOption).toHaveBeenNthCalledWith(1, "generate-pipeline")
    expect(onSelectOption).toHaveBeenNthCalledWith(2, "add-update-platform")
  })
})

describe("the list under the picture", () => {
  it("shows the latest pipelines while the Pipelines tab is on, and not the platforms", async () => {
    renderStart()

    expect(screen.getByRole("heading", { name: "Latest pipelines" })).toBeTruthy()
    expect(await screen.findByText("model-2.swarch")).toBeTruthy()
    expect(screen.getByText("model-5.swarch")).toBeTruthy()
    expect(screen.queryByRole("heading", { name: "Supported CI/CD platforms" })).toBeNull()
    expect(screen.queryByText("GitLab CI v1.7")).toBeNull()
    expect(platformsService.getPlatforms).not.toHaveBeenCalled()
  })

  it("shows the supported platforms while the Platforms tab is on, and not the pipelines", async () => {
    renderStart({}, { platformsTabOn: true })

    expect(screen.getByRole("heading", { name: "Supported CI/CD platforms" })).toBeTruthy()
    expect(await screen.findByText("GitLab CI v1.7")).toBeTruthy()
    expect(screen.getByText("Bamboo v2.1.2")).toBeTruthy()
    // Status and name only, with no link that does nothing.
    expect(screen.queryByRole("link")).toBeNull()
    expect(screen.queryByRole("heading", { name: "Latest pipelines" })).toBeNull()
    expect(screen.queryByText("model-2.swarch")).toBeNull()
    expect(generator.listPipelines).not.toHaveBeenCalled()
  })

  it("follows the tab back and forth", async () => {
    renderStart()
    fireEvent.click(screen.getByText("show platforms tab"))
    await screen.findByText("GitLab CI v1.7")

    fireEvent.click(screen.getByText("show pipelines tab"))

    expect(await screen.findByText("model-2.swarch")).toBeTruthy()
    expect(screen.queryByText("GitLab CI v1.7")).toBeNull()
  })

  it("lists the model, the platforms and how long ago, newest first", async () => {
    renderStart()

    const rows = await screen.findAllByRole("button", { name: /model-/ })
    expect(rows).toHaveLength(2)
    expect(within(rows[0]).getByText("model-2.swarch")).toBeTruthy()
    expect(within(rows[0]).getByText("GitLab CI")).toBeTruthy()
    expect(within(rows[0]).getByText("2 hours ago")).toBeTruthy()
    expect(within(rows[1]).getByText("5 hours ago")).toBeTruthy()
  })

  it("stops at the five latest pipelines", async () => {
    vi.mocked(generator.listPipelines).mockResolvedValue([1, 2, 3, 4, 5, 6, 7].map(pipeline))
    renderStart()

    expect(await screen.findAllByRole("button", { name: /model-/ })).toHaveLength(5)
    expect(screen.queryByText("model-6.swarch")).toBeNull()
  })

  it("opens a pipeline when its row is pressed", async () => {
    const onOpenPipeline = vi.fn()
    renderStart({ onOpenPipeline })

    fireEvent.click(await screen.findByRole("button", { name: /model-5\.swarch/ }))

    expect(onOpenPipeline).toHaveBeenCalledTimes(1)
    expect(onOpenPipeline).toHaveBeenCalledWith("p5")
  })

  it("says it is loading, then that nothing has been generated yet", async () => {
    vi.mocked(generator.listPipelines).mockResolvedValue([])
    renderStart()

    expect(screen.getByText("Loading your pipelines…")).toBeTruthy()
    expect(await screen.findByText(/Nothing generated yet/)).toBeTruthy()
  })

  it("shows the real reason the pipelines could not be loaded, and tries again on request", async () => {
    vi.mocked(generator.listPipelines)
      .mockRejectedValueOnce(new generator.GeneratorError("Pipelines request failed: 500", 500))
      .mockResolvedValueOnce([pipeline(2)])
    renderStart()

    expect(await screen.findByText("Pipelines request failed: 500")).toBeTruthy()
    fireEvent.click(screen.getByRole("button", { name: "Try again" }))

    expect(await screen.findByText("model-2.swarch")).toBeTruthy()
    expect(screen.queryByText("Pipelines request failed: 500")).toBeNull()
  })
})
