// @vitest-environment jsdom
//
// The screen of one platform: where it came from, which file it writes, and
// the three files it is made of. Only the data boundary and the visual
// primitives are mocked: the primitives have their own tests inside
// design-system, and its own copy of react (a symlinked file: dependency)
// would load a second React instance under Vitest.
import type { ReactNode } from "react"
import { cleanup, fireEvent, render, screen } from "@testing-library/react"
import { afterEach, beforeEach, describe, expect, it, vi } from "vitest"
import type { PlatformDetail } from "orchestrator-types"
import PlatformScreen from "./PlatformScreen"
import * as orchestrator from "@/services/orchestrator.service"

vi.mock("design-system", () => ({
  Button: ({ children, onClick }: { children?: ReactNode; onClick?: () => void }) => (
    <button onClick={onClick}>{children}</button>
  ),
  Icon: () => null,
  Tabs: ({
    items,
    activeId,
    onChange,
  }: {
    items: { id: string; label: string }[]
    activeId: string
    onChange: (id: string) => void
  }) => (
    <div role="tablist">
      {items.map((item) => (
        <button key={item.id} role="tab" aria-selected={item.id === activeId} onClick={() => onChange(item.id)}>
          {item.label}
        </button>
      ))}
    </div>
  ),
  CodeBlock: ({ code, title }: { code: string; title?: string }) => <pre aria-label={title}>{code}</pre>,
}))

vi.mock("@/services/orchestrator.service", () => ({ getPlatform: vi.fn() }))

afterEach(cleanup)

const TWO_DAYS_AGO = new Date(Date.now() - 2 * 86400 * 1000).toISOString()

const detail = (over: Partial<PlatformDetail> = {}): PlatformDetail => ({
  id: "woodpecker-ci",
  name: "Woodpecker CI",
  output_filename: ".woodpecker.yaml",
  output_model_name: "WoodMM",
  version: "1.1.0",
  versions: ["1.0.0", "1.1.0"],
  published_versions: ["1.0.0", "1.1.0"],
  published: true,
  source_run_id: "run-7",
  published_at: TWO_DAYS_AGO,
  files: { metamodel: "<ecore/>", transformation: "-- atl", template: "[mtl/]" },
  ...over,
})

beforeEach(() => {
  vi.resetAllMocks()
  vi.mocked(orchestrator.getPlatform).mockResolvedValue(detail())
})

function renderScreen(
  props: {
    version?: string
    onBack?: () => void
    onVersionChange?: (v: string) => void
    onShowLatest?: () => void
    onOpenRun?: (id: string) => void
  } = {}
) {
  const element = (platformId = "woodpecker-ci", version = props.version) => (
    <PlatformScreen
      platformId={platformId}
      version={version}
      onBack={props.onBack ?? (() => undefined)}
      onVersionChange={props.onVersionChange ?? (() => undefined)}
      onShowLatest={props.onShowLatest ?? (() => undefined)}
      onOpenRun={props.onOpenRun ?? (() => undefined)}
    />
  )
  const view = render(element())
  return {
    ...view,
    rerenderWith: (next: { platformId?: string; version?: string }) => view.rerender(element(next.platformId, next.version)),
  }
}

describe("PlatformScreen", () => {
  it("names the platform in its header, which is the way back to the list", async () => {
    const onBack = vi.fn()
    renderScreen({ onBack })

    fireEvent.click(await screen.findByRole("button", { name: "Woodpecker CI" }))

    expect(onBack).toHaveBeenCalledTimes(1)
  })

  it("shows the output file, and the versions newest first with the latest marked", async () => {
    renderScreen()

    expect(await screen.findByText(".woodpecker.yaml")).toBeTruthy()
    const options = screen.getAllByRole("option").map((option) => option.textContent)
    expect(options).toEqual(["1.1.0 (latest)", "1.0.0"])
    expect((screen.getByLabelText("Version") as HTMLSelectElement).value).toBe("1.1.0")
  })

  it("shows the metamodel first, and each of the other two files on its tab, with what the file does", async () => {
    renderScreen()

    expect((await screen.findByLabelText("metamodel.ecore")).textContent).toBe("<ecore/>")
    expect(screen.getByText(/Ecore metamodel/)).toBeTruthy()

    fireEvent.click(screen.getByRole("tab", { name: "Transformation" }))
    expect(screen.getByLabelText("transformation.atl").textContent).toBe("-- atl")
    expect(screen.getByText(/ATL rules/)).toBeTruthy()

    fireEvent.click(screen.getByRole("tab", { name: "Template" }))
    expect(screen.getByLabelText("template.mtl").textContent).toBe("[mtl/]")
    expect(screen.getByText(/Acceleo template/)).toBeTruthy()
  })

  it("says when a published platform was published, and opens the chat that made it", async () => {
    const onOpenRun = vi.fn()
    renderScreen({ onOpenRun })

    expect(await screen.findByText("Published 2 days ago.")).toBeTruthy()
    fireEvent.click(screen.getByRole("button", { name: "Open the chat that made it" }))

    expect(onOpenRun).toHaveBeenCalledWith("run-7")
  })

  it("says a platform was published even when no run is recorded for it, and offers no run to open", async () => {
    vi.mocked(orchestrator.getPlatform).mockResolvedValue(detail({ source_run_id: null }))
    renderScreen()

    expect(await screen.findByText("Published 2 days ago.")).toBeTruthy()
    expect(screen.queryByText(/Included with MDDOAI/)).toBeNull()
    expect(screen.queryByRole("button", { name: "Open the chat that made it" })).toBeNull()
  })

  it("says a platform that came with the stack has no run, and offers none", async () => {
    vi.mocked(orchestrator.getPlatform).mockResolvedValue(detail({ source_run_id: null, published_at: null, published: false }))
    renderScreen()

    expect(await screen.findByText("Included with MDDOAI. No chat made it.")).toBeTruthy()
    expect(screen.queryByRole("button", { name: "Open the chat that made it" })).toBeNull()
  })

  it("asks for the version the address names, and reports a version the person picks", async () => {
    const onVersionChange = vi.fn()
    vi.mocked(orchestrator.getPlatform).mockResolvedValue(detail({ version: "1.0.0" }))
    renderScreen({ version: "1.0.0", onVersionChange })

    await screen.findByText(".woodpecker.yaml")
    expect(orchestrator.getPlatform).toHaveBeenCalledWith("woodpecker-ci", "1.0.0")
    fireEvent.change(screen.getByLabelText("Version"), { target: { value: "1.1.0" } })

    expect(onVersionChange).toHaveBeenCalledWith("1.1.0")
  })

  it("keeps the tab it is on when the version changes", async () => {
    const { rerenderWith } = renderScreen()
    await screen.findByText(".woodpecker.yaml")
    fireEvent.click(screen.getByRole("tab", { name: "Template" }))
    vi.mocked(orchestrator.getPlatform).mockResolvedValue(
      detail({ version: "1.0.0", files: { metamodel: "<old/>", transformation: "-- old", template: "[old/]" } })
    )

    rerenderWith({ version: "1.0.0" })

    expect((await screen.findByLabelText("template.mtl")).textContent).toBe("[old/]")
  })

  it("keeps the platform and its version picker on screen while another version loads", async () => {
    const { rerenderWith } = renderScreen()
    const picker = await screen.findByLabelText("Version")
    let answer: (value: PlatformDetail) => void = () => undefined
    vi.mocked(orchestrator.getPlatform).mockReturnValue(new Promise((resolve) => (answer = resolve)))

    rerenderWith({ version: "1.0.0" })

    // Same element, so the keyboard focus is still on it, and the old files are still readable.
    expect(screen.getByLabelText("Version")).toBe(picker)
    expect(screen.getByLabelText("metamodel.ecore").textContent).toBe("<ecore/>")
    expect(screen.queryByText("Loading the platform…")).toBeNull()
    expect(picker.closest("[aria-busy]")?.getAttribute("aria-busy")).toBe("true")

    answer(detail({ version: "1.0.0" }))
    await screen.findByText("1.0.0")
    expect(screen.getByLabelText("Version").closest("[aria-busy]")?.getAttribute("aria-busy")).toBe("false")
  })

  it("starts on the first file again when another platform is opened", async () => {
    const { rerenderWith } = renderScreen()
    await screen.findByText(".woodpecker.yaml")
    fireEvent.click(screen.getByRole("tab", { name: "Template" }))
    vi.mocked(orchestrator.getPlatform).mockResolvedValue(detail({ id: "gitlab", name: "GitLab CI" }))

    rerenderWith({ platformId: "gitlab" })

    expect(await screen.findByRole("button", { name: "GitLab CI" })).toBeTruthy()
    expect(screen.getByRole("tab", { name: "Metamodel" }).getAttribute("aria-selected")).toBe("true")
  })

  it("does not show the platform that was on screen when the next one is still loading", async () => {
    const { rerenderWith } = renderScreen()
    await screen.findByText(".woodpecker.yaml")
    vi.mocked(orchestrator.getPlatform).mockReturnValue(new Promise(() => undefined))

    rerenderWith({ platformId: "gitlab" })

    expect(screen.getByText("Loading the platform…")).toBeTruthy()
    expect(screen.queryByText(".woodpecker.yaml")).toBeNull()
  })

  it("offers the latest version when the version asked for is gone, and not otherwise", async () => {
    const onShowLatest = vi.fn()
    vi.mocked(orchestrator.getPlatform).mockRejectedValue(new Error("No platform 'woodpecker-ci' version 1.0.0."))
    const { unmount } = renderScreen({ version: "1.0.0", onShowLatest })

    fireEvent.click(await screen.findByRole("button", { name: "Show the latest version" }))
    expect(onShowLatest).toHaveBeenCalledTimes(1)
    unmount()

    renderScreen()
    await screen.findByRole("alert")
    expect(screen.queryByRole("button", { name: "Show the latest version" })).toBeNull()
  })

  it("ignores a late answer for a version the person has already left", async () => {
    let answerForLatest: (value: PlatformDetail) => void = () => undefined
    vi.mocked(orchestrator.getPlatform).mockImplementation((_id, version) =>
      version
        ? Promise.resolve(detail({ version, files: { metamodel: "<old/>", transformation: "", template: "" } }))
        : new Promise((resolve) => {
            answerForLatest = resolve
          })
    )
    const { rerenderWith } = renderScreen()

    rerenderWith({ version: "1.0.0" })
    expect((await screen.findByLabelText("metamodel.ecore")).textContent).toBe("<old/>")
    answerForLatest(detail())
    await Promise.resolve()

    expect(screen.getByLabelText("metamodel.ecore").textContent).toBe("<old/>")
  })

  it("says it is loading, then why it could not load, and tries again when asked", async () => {
    vi.mocked(orchestrator.getPlatform)
      .mockRejectedValueOnce(new Error("No platform 'woodpecker-ci' version 9.9.9."))
      .mockResolvedValueOnce(detail())
    renderScreen()

    expect(screen.getByText("Loading the platform…")).toBeTruthy()
    expect((await screen.findByRole("alert")).textContent).toContain("No platform 'woodpecker-ci' version 9.9.9.")
    fireEvent.click(screen.getByRole("button", { name: "Try again" }))

    expect(await screen.findByText(".woodpecker.yaml")).toBeTruthy()
    expect(screen.queryByRole("alert")).toBeNull()
  })
})
