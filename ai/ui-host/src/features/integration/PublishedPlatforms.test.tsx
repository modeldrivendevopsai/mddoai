// @vitest-environment jsdom
//
// The list of platforms published from a run, with its remove buttons. The
// design system's Button is stubbed as a plain button, as in the screen tests.
import type { ReactNode } from "react"
import { describe, it, expect, vi, afterEach } from "vitest"
import { render, screen, fireEvent, cleanup, waitFor } from "@testing-library/react"
import type { PlatformSummary } from "orchestrator-types"
import { PublishedPlatforms } from "./PublishedPlatforms"

vi.mock("design-system", () => ({
  Button: ({ children, onClick, disabled }: { children?: ReactNode; onClick?: () => void; disabled?: boolean }) => (
    <button onClick={onClick} disabled={disabled}>
      {children}
    </button>
  ),
}))

afterEach(cleanup)

// Only what this list reads is given; the rest is the same for every platform here.
const summary = (fields: Omit<PlatformSummary, "output_model_name" | "source_run_id" | "published_at">): PlatformSummary => ({
  output_model_name: "Model",
  source_run_id: fields.published ? "run1" : null,
  published_at: fields.published ? "2026-11-01T00:00:00Z" : null,
  ...fields,
})

const SHIPPED = summary({
  id: "gitlab", name: "GitLab CI", output_filename: ".gitlab-ci.yml", version: "1.0.0", versions: ["1.0.0"], published_versions: [], published: false,
})
const WOODPECKER = summary({
  id: "woodpecker-ci", name: "Woodpecker CI", output_filename: "w.yaml", version: "1.1.0", versions: ["1.0.0", "1.1.0"], published_versions: ["1.0.0", "1.1.0"], published: true,
})

function renderList(overrides: Partial<Parameters<typeof PublishedPlatforms>[0]> = {}) {
  const props = {
    loadPlatforms: vi.fn().mockResolvedValue([SHIPPED, WOODPECKER]),
    onPurge: vi.fn().mockResolvedValue([]),
    refreshKey: 0,
    ...overrides,
  }
  const view = render(<PublishedPlatforms {...props} />)
  return { props, view }
}

describe("PublishedPlatforms", () => {
  it("lists only the platforms published from a run, each with its versions", async () => {
    renderList()

    expect(await screen.findByText("Woodpecker CI")).toBeTruthy()
    expect(screen.getByText("1.0.0")).toBeTruthy()
    expect(screen.getByText("1.1.0")).toBeTruthy()
    expect(screen.queryByText("GitLab CI")).toBeNull()
  })

  it("lists a platform that has some published versions and only those, and offers no remove-all", async () => {
    const mixed = summary({
      id: "mixed", name: "Mixed CI", output_filename: "m.yml", version: "1.2.0",
      versions: ["1.0.0", "1.1.0", "1.2.0"], published_versions: ["1.1.0", "1.2.0"], published: true,
    })
    renderList({ loadPlatforms: vi.fn().mockResolvedValue([mixed]) })

    expect(await screen.findByText("Mixed CI")).toBeTruthy()
    expect(screen.queryByText("1.0.0")).toBeNull()
    expect(screen.getByRole("button", { name: "Remove 1.1.0" })).toBeTruthy()
    expect(screen.getByRole("button", { name: "Remove 1.2.0" })).toBeTruthy()
    expect(screen.queryByRole("button", { name: "Remove all" })).toBeNull()
  })

  it("lists a platform whose latest version came with the stack if an older one was published", async () => {
    const older = summary({
      id: "mixed", name: "Mixed CI", output_filename: "m.yml", version: "2.0.0",
      versions: ["1.0.0", "2.0.0"], published_versions: ["1.0.0"], published: false,
    })
    renderList({ loadPlatforms: vi.fn().mockResolvedValue([older]) })

    expect(await screen.findByRole("button", { name: "Remove 1.0.0" })).toBeTruthy()
    expect(screen.queryByText("2.0.0")).toBeNull()
  })

  it("says so when none was published", async () => {
    renderList({ loadPlatforms: vi.fn().mockResolvedValue([SHIPPED]) })

    expect(await screen.findByText("No platform has been published from a run yet.")).toBeTruthy()
  })

  it("asks before removing a version, and does nothing if the person cancels", async () => {
    const { props } = renderList()
    fireEvent.click(await screen.findByRole("button", { name: "Remove 1.0.0" }))

    expect(screen.getByText("Remove Woodpecker CI 1.0.0? This cannot be undone.")).toBeTruthy()
    fireEvent.click(screen.getByRole("button", { name: "Cancel" }))

    expect(props.onPurge).not.toHaveBeenCalled()
    expect(screen.getByRole("button", { name: "Remove 1.0.0" })).toBeTruthy()
  })

  it("removes one version once confirmed, and lists the platforms again", async () => {
    const loadPlatforms = vi
      .fn()
      .mockResolvedValueOnce([SHIPPED, WOODPECKER])
      .mockResolvedValue([SHIPPED, { ...WOODPECKER, versions: ["1.1.0"], published_versions: ["1.1.0"] }])
    const { props } = renderList({ loadPlatforms })
    fireEvent.click(await screen.findByRole("button", { name: "Remove 1.0.0" }))

    fireEvent.click(screen.getByRole("button", { name: "Remove" }))

    await waitFor(() => expect(props.onPurge).toHaveBeenCalledWith("woodpecker-ci", "1.0.0"))
    await waitFor(() => expect(screen.queryByText("1.0.0")).toBeNull())
    expect(screen.getByText("1.1.0")).toBeTruthy()
  })

  it("removes every version of a platform once confirmed", async () => {
    const { props } = renderList()
    fireEvent.click(await screen.findByRole("button", { name: "Remove all" }))

    expect(screen.getByText("Remove all of Woodpecker CI? This cannot be undone.")).toBeTruthy()
    fireEvent.click(screen.getByRole("button", { name: "Remove" }))

    await waitFor(() => expect(props.onPurge).toHaveBeenCalledWith("woodpecker-ci", undefined))
  })

  it("asks about one thing at a time", async () => {
    renderList()
    fireEvent.click(await screen.findByRole("button", { name: "Remove 1.0.0" }))

    fireEvent.click(screen.getByRole("button", { name: "Remove 1.1.0" }))

    expect(screen.getAllByRole("button", { name: "Remove" })).toHaveLength(1)
    expect(screen.getByText("Remove Woodpecker CI 1.1.0? This cannot be undone.")).toBeTruthy()
    expect(screen.getByRole("button", { name: "Remove 1.0.0" })).toBeTruthy()
  })

  it("shows the backend's reason when a removal is refused, and keeps the list", async () => {
    const { props } = renderList({ onPurge: vi.fn().mockRejectedValue(new Error("it came with the stack")) })
    fireEvent.click(await screen.findByRole("button", { name: "Remove 1.0.0" }))

    fireEvent.click(screen.getByRole("button", { name: "Remove" }))

    expect((await screen.findByRole("alert")).textContent).toBe("it came with the stack")
    expect(props.onPurge).toHaveBeenCalledTimes(1)
    expect(screen.getByText("Woodpecker CI")).toBeTruthy()
  })

  it("shows why the list could not load", async () => {
    renderList({ loadPlatforms: vi.fn().mockRejectedValue(new Error("Platforms request failed (502)")) })

    expect((await screen.findByRole("alert")).textContent).toBe("Platforms request failed (502)")
  })

  it("loads the list again when told a platform was published", async () => {
    const loadPlatforms = vi.fn().mockResolvedValue([WOODPECKER])
    const { view, props } = renderList({ loadPlatforms })
    await screen.findByText("Woodpecker CI")

    view.rerender(<PublishedPlatforms {...props} refreshKey={1} />)

    await waitFor(() => expect(loadPlatforms).toHaveBeenCalledTimes(2))
  })
})
