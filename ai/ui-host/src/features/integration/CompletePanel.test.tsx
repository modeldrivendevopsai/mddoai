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

const DEFAULTS = { name: "Woodpecker CI", output_filename: ".woodpecker/pipeline.yaml" }
const PUBLISHED = { id: "woodpecker-ci", name: "Woodpecker CI", version: 2, output_filename: ".woodpecker/pipeline.yaml" }

function renderPanel(overrides: Partial<Parameters<typeof CompletePanel>[0]> = {}) {
  const props = {
    canPublish: true,
    loadDefaults: vi.fn().mockResolvedValue(DEFAULTS),
    onPublish: vi.fn().mockResolvedValue(PUBLISHED),
    ...overrides,
  }
  render(<CompletePanel {...props} />)
  return props
}

describe("CompletePanel", () => {
  it("fills the form with what a publish would use and publishes it", async () => {
    const props = renderPanel()

    expect(await screen.findByDisplayValue("Woodpecker CI")).toBeTruthy()
    expect(screen.getByDisplayValue(".woodpecker/pipeline.yaml")).toBeTruthy()
    fireEvent.click(screen.getByRole("button", { name: "Publish" }))

    expect(await screen.findByRole("status")).toHaveProperty(
      "textContent",
      "Published as Woodpecker CI v2. It's now available in Generate a CI/CD pipeline."
    )
    expect(props.onPublish).toHaveBeenCalledWith("Woodpecker CI", ".woodpecker/pipeline.yaml")
  })

  it("sends what the person typed instead of the defaults", async () => {
    const props = renderPanel()
    await screen.findByDisplayValue("Woodpecker CI")

    fireEvent.change(screen.getByLabelText("Platform name"), { target: { value: "  Woodpecker  " } })
    fireEvent.change(screen.getByLabelText("Pipeline file it generates"), { target: { value: "ci.yml" } })
    fireEvent.click(screen.getByRole("button", { name: "Publish" }))

    await waitFor(() => expect(props.onPublish).toHaveBeenCalledWith("Woodpecker", "ci.yml"))
  })

  it("shows the backend's reason when publishing is refused", async () => {
    renderPanel({ onPublish: vi.fn().mockRejectedValue(new Error("'gitlab' is a built-in platform; publish under another name.")) })
    await screen.findByDisplayValue("Woodpecker CI")

    fireEvent.click(screen.getByRole("button", { name: "Publish" }))

    expect((await screen.findByRole("alert")).textContent).toContain("built-in platform")
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
