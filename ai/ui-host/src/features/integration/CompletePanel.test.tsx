// @vitest-environment jsdom
//
// The panel shown once every stage is approved, with its publish form. The
// design system's Button is stubbed as a plain button, as in the screen tests.
import type { ReactNode } from "react"
import { describe, it, expect, vi, afterEach } from "vitest"
import { render, screen, fireEvent, cleanup, waitFor } from "@testing-library/react"
import { CompletePanel } from "./CompletePanel"

vi.mock("design-system", () => ({
  Button: ({ children, onClick, disabled }: { children?: ReactNode; onClick?: () => void; disabled?: boolean }) => (
    <button onClick={onClick} disabled={disabled}>
      {children}
    </button>
  ),
}))

afterEach(cleanup)

const DEFAULTS = { name: "Woodpecker CI", output_filename: ".woodpecker.yaml" }
const PUBLISHED = { id: "woodpecker-ci", name: "Woodpecker CI", version: "1.2.0", output_filename: ".woodpecker.yaml" }

function renderPanel(overrides: Partial<Parameters<typeof CompletePanel>[0]> = {}) {
  const props = {
    canPublish: true,
    loadDefaults: vi.fn().mockResolvedValue(DEFAULTS),
    onPublish: vi.fn().mockResolvedValue(PUBLISHED),
    loadPlatforms: vi.fn().mockResolvedValue([]),
    onPurge: vi.fn().mockResolvedValue([]),
    ...overrides,
  }
  render(<CompletePanel {...props} />)
  return props
}

describe("CompletePanel", () => {
  it("fills the form with what a publish would use and publishes it", async () => {
    const props = renderPanel()

    expect(await screen.findByDisplayValue("Woodpecker CI")).toBeTruthy()
    expect(screen.getByDisplayValue(".woodpecker.yaml")).toBeTruthy()
    fireEvent.click(screen.getByRole("button", { name: "Publish" }))

    expect(await screen.findByRole("status")).toHaveProperty(
      "textContent",
      "Published as Woodpecker CI v1.2.0. It's now available in Generate a CI/CD pipeline."
    )
    expect(props.onPublish).toHaveBeenCalledWith("Woodpecker CI", ".woodpecker.yaml", "")
  })

  it("sends what the person typed instead of the defaults", async () => {
    const props = renderPanel()
    await screen.findByDisplayValue("Woodpecker CI")

    fireEvent.change(screen.getByLabelText("Platform name"), { target: { value: "  Woodpecker  " } })
    fireEvent.change(screen.getByLabelText("Pipeline file it generates"), { target: { value: "ci.yml" } })
    fireEvent.click(screen.getByRole("button", { name: "Publish" }))

    await waitFor(() => expect(props.onPublish).toHaveBeenCalledWith("Woodpecker", "ci.yml", ""))
  })

  it("publishes under the version the person chose, without surrounding spaces", async () => {
    const props = renderPanel()
    await screen.findByDisplayValue("Woodpecker CI")

    fireEvent.change(screen.getByLabelText("Version"), { target: { value: " 2.0.0 " } })
    fireEvent.click(screen.getByRole("button", { name: "Publish" }))

    await waitFor(() => expect(props.onPublish).toHaveBeenCalledWith("Woodpecker CI", ".woodpecker.yaml", "2.0.0"))
  })

  it("can publish with no version, which leaves it to the backend", async () => {
    renderPanel()
    await screen.findByDisplayValue("Woodpecker CI")

    expect((screen.getByRole("button", { name: "Publish" }) as HTMLButtonElement).disabled).toBe(false)
    expect((screen.getByLabelText("Version") as HTMLInputElement).value).toBe("")
  })

  it("lists the published platforms again after a publish, so a new version shows up", async () => {
    const loadPlatforms = vi.fn().mockResolvedValue([])
    renderPanel({ loadPlatforms })
    await screen.findByDisplayValue("Woodpecker CI")
    await waitFor(() => expect(loadPlatforms).toHaveBeenCalledTimes(1))

    fireEvent.click(screen.getByRole("button", { name: "Publish" }))

    await waitFor(() => expect(loadPlatforms).toHaveBeenCalledTimes(2))
  })

  it("lists the published platforms even for a run that cannot be published from", async () => {
    const platform = { id: "woodpecker-ci", name: "Woodpecker CI", output_filename: "w.yaml", version: "1.0.0", versions: ["1.0.0"], published: true }
    renderPanel({ canPublish: false, loadPlatforms: vi.fn().mockResolvedValue([platform]) })

    expect(await screen.findByRole("button", { name: "Remove 1.0.0" })).toBeTruthy()
  })

  it("shows the backend's reason when publishing is refused", async () => {
    renderPanel({ onPublish: vi.fn().mockRejectedValue(new Error("'gitlab' is already the platform 'GitLab CI'. Publish under another name.")) })
    await screen.findByDisplayValue("Woodpecker CI")

    fireEvent.click(screen.getByRole("button", { name: "Publish" }))

    expect((await screen.findByRole("alert")).textContent).toContain("already the platform")
  })

  it("cannot publish until both fields have a value, for example when several files were generated", async () => {
    renderPanel({ loadDefaults: vi.fn().mockResolvedValue({ name: "Woodpecker CI", output_filename: null }) })
    await screen.findByDisplayValue("Woodpecker CI")

    expect((screen.getByRole("button", { name: "Publish" }) as HTMLButtonElement).disabled).toBe(true)
  })

  it("does not offer publishing for a finished run opened from history", () => {
    const props = renderPanel({ canPublish: false })

    expect(screen.queryByRole("button", { name: "Publish" })).toBeNull()
    expect(screen.getByText("This isn't the current run. Resume it to publish its platform.")).toBeTruthy()
    expect(props.loadDefaults).not.toHaveBeenCalled()
  })
})
