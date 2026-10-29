// @vitest-environment jsdom
//
// Renders the real screen, hooks and panel components through jsdom; only the
// network boundary (generator.service) and the visual primitives are mocked, so
// what's under test is the screen's own behaviour: what unlocks Generate and
// when, what each failure shows, how a path, a browse and a saved pipeline
// load, and that a slow answer for a replaced file never lands on the new one.
import type { ReactNode } from "react"
import { act, cleanup, fireEvent, render, screen, waitFor, within } from "@testing-library/react"
import { afterEach, beforeEach, describe, expect, it, vi } from "vitest"
import GenerateScreen from "./GenerateScreen"
import * as service from "@/services/generator.service"
import type {
  GenerateResponse,
  GenerationResult,
  Inspection,
  PipelineRecord,
  PipelineValidation,
} from "@/services/generator.service"

// The shared visual primitives have their own tests inside design-system;
// stubbing them here keeps this file about the screen's behaviour, and sidesteps
// design-system's own copy of react (a symlinked file: dependency) loading a
// second React instance under Vitest.
vi.mock("design-system", () => ({
  Button: ({
    children,
    onClick,
    disabled,
    ...rest
  }: {
    children?: ReactNode
    onClick?: () => void
    disabled?: boolean
    "aria-label"?: string
  }) => (
    <button onClick={onClick} disabled={disabled} aria-label={rest["aria-label"]}>
      {children}
    </button>
  ),
  StatusPill: ({ children }: { children: ReactNode }) => <span>{children}</span>,
  CodeBlock: ({ code, title }: { code: string; title?: string }) => <pre aria-label={title}>{code}</pre>,
  Icon: () => null,
}))

vi.mock("@/services/generator.service", async (importOriginal) => {
  const actual = await importOriginal<typeof import("@/services/generator.service")>()
  return {
    ...actual,
    getPlatforms: vi.fn(),
    inspectModel: vi.fn(),
    generatePipelines: vi.fn(),
    validatePipeline: vi.fn(),
    getPipeline: vi.fn(),
    browseWorkspace: vi.fn(),
    readWorkspaceFile: vi.fn(),
  }
})

afterEach(cleanup)

const GITLAB = { id: "gitlab", name: "GitLab CI", output_filename: ".gitlab-ci.yml", source: "engine" as const, version: null }
const BAMBOO = { id: "bamboo", name: "Bamboo", output_filename: "bamboo-specs/bamboo.yaml", source: "published" as const, version: 1 }

const VALID: Inspection = {
  element: "SoftwareArchitecture",
  attributes: [
    { name: "name", value: "framework-chatbot" },
    { name: "build_system", value: "docker" },
    { name: "lint_check", value: "" },
  ],
  model_validation: { valid: true, issues: [] },
}

const INVALID: Inspection = {
  element: "SoftwareArchitecture",
  attributes: [{ name: "name", value: "x" }],
  model_validation: {
    valid: false,
    issues: [{ severity: "ERROR", message: "The required feature 'language' of 'SoftwareArchitecture' must be set" }],
  },
}

const GITLAB_RESULT: GenerationResult = {
  platform: { id: "gitlab", name: "GitLab CI" },
  output_filename: ".gitlab-ci.yml",
  pipeline: "stages:\n  - build\n",
  intermediates: [{ id: "pim", title: "Platform-independent model", filename: "PipelinePIM.pimmm", content: "<pim/>" }],
  duration_ms: 1500,
  saved_to: "/ci-output/.gitlab-ci.yml",
  replaced: false,
  save_error: null,
  error: null,
}

const RESPONSE: GenerateResponse = { pipeline_id: "p1", results: [GITLAB_RESULT] }

const PASSING: PipelineValidation = {
  valid: true,
  checks: [
    { id: "yaml", title: "The file is valid YAML", status: "pass", details: [] },
    { id: "schema", title: "The settings follow GitLab's CI format", status: "pass", details: [] },
  ],
}

const SAVED: PipelineRecord = {
  id: "p1",
  swarch_name: "app.swarch",
  source_path: "/repos/app.swarch",
  platforms: [{ id: "gitlab", name: "GitLab CI" }],
  outputs: [".gitlab-ci.yml"],
  generated_at: "2026-01-01T10:00:00+00:00",
  content: "<swarch/>",
  save_to: "./out/",
  results: [GITLAB_RESULT],
}

function uploadFile(name: string, content = "<swarch/>") {
  const input = document.querySelector('input[type="file"]') as HTMLInputElement
  fireEvent.change(input, { target: { files: [new File([content], name, { type: "text/xml" })] } })
}

const pathField = () => screen.getByLabelText("Input path for SWArch file") as HTMLInputElement
const saveField = () => screen.getByLabelText("Save output to") as HTMLInputElement
const generateButton = () => screen.getByRole("button", { name: /^Generat(e|ing…)$/ }) as HTMLButtonElement
const checkbox = (name: string) => screen.getByRole("checkbox", { name }) as HTMLInputElement

async function pickGitlab() {
  fireEvent.click(await screen.findByRole("checkbox", { name: "GitLab CI" }))
}

async function loadValidModelAndGenerate() {
  uploadFile("app.swarch")
  await screen.findByText("This model is valid")
  await pickGitlab()
  fireEvent.click(generateButton())
}

function renderScreen(props: { pipelineId?: string; onBack?: () => void } = {}) {
  return render(<GenerateScreen onBack={props.onBack ?? (() => undefined)} pipelineId={props.pipelineId} />)
}

beforeEach(() => {
  // The module mocks above are created once, so call history and one-shot
  // implementations would otherwise carry over from the previous test.
  vi.resetAllMocks()
  vi.mocked(service.getPlatforms).mockResolvedValue([GITLAB])
  // Unless a test says otherwise, a path in the workspace names no file: a
  // test that needs a file to be there has to say so.
  vi.mocked(service.readWorkspaceFile).mockRejectedValue(
    new service.GeneratorError("There is no file at that path in the workspace.", 404)
  )
})

describe("GenerateScreen: the empty screen", () => {
  it("opens with the wireframe's fields, nothing chosen, and Generate waiting for a file", async () => {
    renderScreen()

    expect(await screen.findByRole("checkbox", { name: "GitLab CI" })).toBeTruthy()
    expect(checkbox("GitLab CI").checked).toBe(false)
    expect(screen.getByText(/select one or more/)).toBeTruthy()
    expect(pathField().value).toBe("")
    expect(saveField().value).toBe("./ci-output/")
    expect(screen.getByText("Result will appear here once generated.")).toBeTruthy()
    expect(generateButton().disabled).toBe(true)
    expect(screen.getByText("Load an architecture file first.")).toBeTruthy()
    expect(screen.queryByText("✓ File loaded")).toBeNull()
  })

  it("goes back to the pipelines list from the header", async () => {
    const onBack = vi.fn()
    renderScreen({ onBack })

    fireEvent.click(screen.getByRole("button", { name: /Generate CI\/CD Pipeline/ }))

    expect(onBack).toHaveBeenCalledTimes(1)
  })
})

describe("GenerateScreen: loading a model", () => {
  it("shows what an uploaded model says in plain words and that it is loaded", async () => {
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    renderScreen()

    uploadFile("app.swarch")

    expect(await screen.findByText("This model is valid")).toBeTruthy()
    expect(screen.getByText("✓ File loaded")).toBeTruthy()
    expect(pathField().value).toBe("app.swarch")
    expect(screen.getByText("Found a software architecture model.")).toBeTruthy()
    expect(screen.getByText("Build system")).toBeTruthy()
    expect(screen.getByText("docker")).toBeTruthy()
    expect(screen.getByText("Not set")).toBeTruthy()
  })

  it("keeps Generate locked until a platform is chosen, and says so", async () => {
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    renderScreen()

    uploadFile("app.swarch")
    await screen.findByText("This model is valid")

    expect(generateButton().disabled).toBe(true)
    expect(screen.getByText("Select at least one platform.")).toBeTruthy()

    await pickGitlab()

    expect(generateButton().disabled).toBe(false)
    expect(screen.queryByText("Select at least one platform.")).toBeNull()
  })

  it("keeps Generate locked, and says why, for an invalid model", async () => {
    vi.mocked(service.inspectModel).mockResolvedValue(INVALID)
    renderScreen()

    uploadFile("bad.swarch")
    await pickGitlab()

    expect(await screen.findByText("1 problem to fix")).toBeTruthy()
    expect(screen.getByText("The required feature 'language' of 'SoftwareArchitecture' must be set")).toBeTruthy()
    expect(screen.getByText("Fix the problems found in your file first.")).toBeTruthy()
    expect(generateButton().disabled).toBe(true)
  })

  it("shows the real error when the file can't be checked", async () => {
    vi.mocked(service.inspectModel).mockRejectedValue(
      new service.GeneratorError("The model checker is unavailable, try again shortly.", 502)
    )
    renderScreen()

    uploadFile("app.swarch")

    expect(await screen.findByText("The model checker is unavailable, try again shortly.")).toBeTruthy()
    expect(generateButton().disabled).toBe(true)
  })

  it("says so for an empty file instead of asking the backend", async () => {
    renderScreen()

    uploadFile("empty.swarch", "")

    expect(await screen.findByText("This file is empty.")).toBeTruthy()
    expect(service.inspectModel).not.toHaveBeenCalled()
    expect(screen.queryByText("✓ File loaded")).toBeNull()
  })

  it("drops a slow answer for a file that has since been replaced", async () => {
    let resolveSlow: (value: Inspection) => void = () => undefined
    vi.mocked(service.inspectModel)
      .mockImplementationOnce(() => new Promise((resolve) => { resolveSlow = resolve }))
      .mockResolvedValueOnce(INVALID)
    renderScreen()

    uploadFile("first.swarch")
    // Its inspection must really be in flight before the file is replaced.
    await waitFor(() => expect(service.inspectModel).toHaveBeenCalledTimes(1))
    uploadFile("second.swarch")
    expect(await screen.findByText("1 problem to fix")).toBeTruthy()

    // The first file's (valid) answer arrives late and must not unlock anything.
    resolveSlow(VALID)
    // Let the late answer land before looking: asserting on the very next tick
    // would pass whether or not it was (wrongly) applied.
    await act(async () => undefined)
    expect(screen.queryByText("This model is valid")).toBeNull()
    expect(screen.getByText("1 problem to fix")).toBeTruthy()
  })
})

describe("GenerateScreen: a path in the workspace", () => {
  const FILE = { filename: "a.swarch", path: "/repos/a.swarch", content: "<swarch/>" }

  it("loads the file a typed path names when Enter is pressed", async () => {
    vi.mocked(service.readWorkspaceFile).mockResolvedValue(FILE)
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    renderScreen()

    fireEvent.change(pathField(), { target: { value: "./repos/a.swarch" } })
    fireEvent.keyDown(pathField(), { key: "Enter" })

    expect(await screen.findByText("This model is valid")).toBeTruthy()
    expect(service.readWorkspaceFile).toHaveBeenCalledWith("./repos/a.swarch")
    expect(service.inspectModel).toHaveBeenCalledWith("a.swarch", "<swarch/>")
    // The field now shows where the workspace says the file is.
    expect(pathField().value).toBe("/repos/a.swarch")
    expect(screen.getByText("✓ File loaded")).toBeTruthy()
  })

  it("loads when the field is left, but not again for the same text", async () => {
    vi.mocked(service.readWorkspaceFile).mockResolvedValue(FILE)
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    renderScreen()

    fireEvent.change(pathField(), { target: { value: "/repos/a.swarch" } })
    fireEvent.blur(pathField())
    await screen.findByText("This model is valid")
    fireEvent.blur(pathField())
    fireEvent.keyDown(pathField(), { key: "Enter" })

    expect(service.readWorkspaceFile).toHaveBeenCalledTimes(1)
  })

  it("does not load anything for an empty field", () => {
    renderScreen()

    fireEvent.blur(pathField())
    fireEvent.keyDown(pathField(), { key: "Enter" })

    expect(service.readWorkspaceFile).not.toHaveBeenCalled()
  })

  it("shows the workspace's own message when the path is wrong, and keeps what was typed", async () => {
    vi.mocked(service.readWorkspaceFile).mockRejectedValue(
      new service.GeneratorError("There is no file at that path in the workspace.", 404)
    )
    renderScreen()

    fireEvent.change(pathField(), { target: { value: "/nope.swarch" } })
    fireEvent.keyDown(pathField(), { key: "Enter" })

    expect(await screen.findByText("There is no file at that path in the workspace.")).toBeTruthy()
    expect(pathField().value).toBe("/nope.swarch")
    expect(screen.queryByText("✓ File loaded")).toBeNull()
    expect(service.inspectModel).not.toHaveBeenCalled()
  })

  it("replaces a loaded file, and what was generated from it, when another path is entered", async () => {
    vi.mocked(service.readWorkspaceFile).mockResolvedValue(FILE)
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    vi.mocked(service.generatePipelines).mockResolvedValue(RESPONSE)
    renderScreen()
    await loadValidModelAndGenerate()
    await screen.findByText(/pipeline generated in/)

    fireEvent.change(pathField(), { target: { value: "/repos/a.swarch" } })
    fireEvent.keyDown(pathField(), { key: "Enter" })

    await waitFor(() => expect(screen.queryByText(/pipeline generated in/)).toBeNull())
    expect(screen.getByText("Result will appear here once generated.")).toBeTruthy()
  })

  it("drops a slow read for a path that has since been replaced", async () => {
    let resolveSlow: (value: typeof FILE) => void = () => undefined
    vi.mocked(service.readWorkspaceFile).mockImplementationOnce(() => new Promise((resolve) => { resolveSlow = resolve }))
    vi.mocked(service.inspectModel).mockResolvedValue(INVALID)
    renderScreen()

    fireEvent.change(pathField(), { target: { value: "/slow.swarch" } })
    fireEvent.keyDown(pathField(), { key: "Enter" })
    await waitFor(() => expect(service.readWorkspaceFile).toHaveBeenCalledTimes(1))
    uploadFile("second.swarch")
    expect(await screen.findByText("1 problem to fix")).toBeTruthy()

    resolveSlow(FILE)
    await act(async () => undefined)

    expect(service.inspectModel).toHaveBeenCalledTimes(1)
    expect(pathField().value).toBe("second.swarch")
  })
})

describe("GenerateScreen: browsing the workspace", () => {
  const ROOT = {
    path: "/",
    parent: null,
    entries: [
      { name: "repos", kind: "dir" as const },
      { name: "top.swarch", kind: "file" as const },
    ],
    truncated: false,
    root_label: "the MDDOAI repository",
  }
  const REPOS = {
    path: "/repos",
    parent: "/",
    entries: [{ name: "a.swarch", kind: "file" as const }],
    truncated: false,
    root_label: "the MDDOAI repository",
  }

  it("opens a dialog listing the workspace and loads the file that is clicked", async () => {
    vi.mocked(service.browseWorkspace).mockImplementation(async (path) => (path === "/repos" ? REPOS : ROOT))
    vi.mocked(service.readWorkspaceFile).mockResolvedValue({ filename: "a.swarch", path: "/repos/a.swarch", content: "<swarch/>" })
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    renderScreen()

    fireEvent.click(screen.getAllByRole("button", { name: "Browse" })[0])
    const dialog = await screen.findByRole("dialog", { name: "Choose an architecture file" })
    fireEvent.click(await within(dialog).findByRole("button", { name: "repos" }))
    fireEvent.click(await within(dialog).findByRole("button", { name: "a.swarch" }))

    expect(await screen.findByText("This model is valid")).toBeTruthy()
    expect(service.readWorkspaceFile).toHaveBeenCalledWith("/repos/a.swarch")
    expect(service.browseWorkspace).toHaveBeenCalledWith("/", false)
    expect(screen.queryByRole("dialog")).toBeNull()
  })

  it("says which folder it is showing files from, and where else a file can come from", async () => {
    vi.mocked(service.browseWorkspace).mockImplementation(async (path) => (path === "/repos" ? REPOS : ROOT))
    renderScreen()

    fireEvent.click(screen.getAllByRole("button", { name: "Browse" })[0])
    const dialog = await screen.findByRole("dialog", { name: "Choose an architecture file" })

    expect(
      await within(dialog).findByText(
        "Showing .swarch files in the MDDOAI repository. To use a file from somewhere else, close this and choose Upload."
      )
    ).toBeTruthy()
    expect(within(dialog).getByText("Folder /")).toBeTruthy()
  })

  it("can go back up a folder", async () => {
    vi.mocked(service.browseWorkspace).mockImplementation(async (path) => (path === "/repos" ? REPOS : ROOT))
    renderScreen()

    fireEvent.click(screen.getAllByRole("button", { name: "Browse" })[0])
    const dialog = await screen.findByRole("dialog")
    fireEvent.click(await within(dialog).findByRole("button", { name: "repos" }))
    fireEvent.click(await within(dialog).findByRole("button", { name: "Up one folder" }))

    expect(await within(dialog).findByRole("button", { name: "top.swarch" })).toBeTruthy()
  })

  it("lists folders only, and fills the save folder with the one chosen", async () => {
    vi.mocked(service.browseWorkspace).mockImplementation(async (path) =>
      path === "/repos" ? { ...REPOS, entries: [] } : { ...ROOT, entries: [{ name: "repos", kind: "dir" as const }] }
    )
    renderScreen()

    fireEvent.click(screen.getAllByRole("button", { name: "Browse" })[1])
    const dialog = await screen.findByRole("dialog", { name: "Choose a folder to save into" })
    expect(await within(dialog).findByText("Saves go into a folder inside the MDDOAI repository.")).toBeTruthy()
    fireEvent.click(await within(dialog).findByRole("button", { name: "repos" }))
    await within(dialog).findByText("No folders here.")
    fireEvent.click(within(dialog).getByRole("button", { name: "Select this folder" }))

    expect(saveField().value).toBe("/repos")
    expect(service.browseWorkspace).toHaveBeenCalledWith("/", true)
    expect(screen.queryByRole("dialog")).toBeNull()
  })

  it("shows the workspace's own message when a folder cannot be listed", async () => {
    vi.mocked(service.browseWorkspace).mockRejectedValue(new service.GeneratorError("The workspace folder is not available.", 503))
    renderScreen()

    fireEvent.click(screen.getAllByRole("button", { name: "Browse" })[0])

    expect(await screen.findByText("The workspace folder is not available.")).toBeTruthy()
  })

  it("closes with Escape and from the close button, loading nothing", async () => {
    vi.mocked(service.browseWorkspace).mockResolvedValue(ROOT)
    renderScreen()

    fireEvent.click(screen.getAllByRole("button", { name: "Browse" })[0])
    fireEvent.keyDown(await screen.findByRole("dialog"), { key: "Escape" })
    expect(screen.queryByRole("dialog")).toBeNull()

    fireEvent.click(screen.getAllByRole("button", { name: "Browse" })[0])
    fireEvent.click(await screen.findByRole("button", { name: "Close" }))
    expect(screen.queryByRole("dialog")).toBeNull()
    expect(service.readWorkspaceFile).not.toHaveBeenCalled()
  })
})

describe("GenerateScreen: generating", () => {
  it("sends the model, platforms, source and save folder, and shows the pipeline", async () => {
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    vi.mocked(service.generatePipelines).mockResolvedValue(RESPONSE)
    renderScreen()

    await loadValidModelAndGenerate()

    expect(await screen.findByText(/GitLab CI pipeline generated in 1\.5 s/)).toBeTruthy()
    expect(service.generatePipelines).toHaveBeenCalledWith(
      {
        filename: "app.swarch",
        content: "<swarch/>",
        platforms: ["gitlab"],
        source_path: null,
        save_to: "./ci-output/",
      },
      expect.any(AbortSignal)
    )
    expect(screen.getByLabelText(".gitlab-ci.yml").textContent).toBe("stages:\n  - build\n")
    expect(screen.getByText("Saved to /ci-output/.gitlab-ci.yml")).toBeTruthy()
  })

  it("tells the service where a path-loaded model came from", async () => {
    vi.mocked(service.readWorkspaceFile).mockResolvedValue({ filename: "a.swarch", path: "/repos/a.swarch", content: "<swarch/>" })
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    vi.mocked(service.generatePipelines).mockResolvedValue(RESPONSE)
    renderScreen()

    fireEvent.change(pathField(), { target: { value: "/repos/a.swarch" } })
    fireEvent.keyDown(pathField(), { key: "Enter" })
    await screen.findByText("This model is valid")
    await pickGitlab()
    fireEvent.click(generateButton())

    await screen.findByText(/pipeline generated in/)
    expect(vi.mocked(service.generatePipelines).mock.calls[0][0].source_path).toBe("/repos/a.swarch")
  })

  it("saves nothing when the save folder is emptied", async () => {
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    vi.mocked(service.generatePipelines).mockResolvedValue(RESPONSE)
    renderScreen()
    fireEvent.change(saveField(), { target: { value: "  " } })

    await loadValidModelAndGenerate()

    await screen.findByText(/pipeline generated in/)
    expect(vi.mocked(service.generatePipelines).mock.calls[0][0].save_to).toBeNull()
  })

  it("shows the running message and locks the inputs while generating", async () => {
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    vi.mocked(service.generatePipelines).mockImplementation(() => new Promise(() => undefined))
    renderScreen()

    await loadValidModelAndGenerate()

    expect(await screen.findByText("Running the existing GitLab CI transformation chain…")).toBeTruthy()
    expect(generateButton().textContent).toBe("Generating…")
    expect(generateButton().disabled).toBe(true)
    expect(pathField().disabled).toBe(true)
    expect(saveField().disabled).toBe(true)
    for (const button of screen.getAllByRole("button", { name: "Browse" })) {
      expect((button as HTMLButtonElement).disabled).toBe(true)
    }
    // Disabled through its fieldset, so the property on the box itself stays false.
    expect(checkbox("GitLab CI").matches(":disabled")).toBe(true)
  })

  it("generates for several platforms at once and shows one result for each", async () => {
    vi.mocked(service.getPlatforms).mockResolvedValue([GITLAB, BAMBOO])
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    vi.mocked(service.generatePipelines).mockResolvedValue({
      pipeline_id: "p1",
      results: [
        GITLAB_RESULT,
        { ...GITLAB_RESULT, platform: { id: "bamboo", name: "Bamboo" }, output_filename: "bamboo.yaml", pipeline: "plan: x\n", saved_to: null },
      ],
    })
    renderScreen()

    uploadFile("app.swarch")
    await screen.findByText("This model is valid")
    fireEvent.click(await screen.findByRole("checkbox", { name: "GitLab CI" }))
    fireEvent.click(screen.getByRole("checkbox", { name: "Bamboo" }))
    fireEvent.click(generateButton())

    expect(await screen.findByText(/Bamboo pipeline generated/)).toBeTruthy()
    expect(screen.getByText(/GitLab CI pipeline generated/)).toBeTruthy()
    expect(vi.mocked(service.generatePipelines).mock.calls[0][0].platforms).toEqual(["gitlab", "bamboo"])
  })

  it("names every chosen platform while they are being generated", async () => {
    vi.mocked(service.getPlatforms).mockResolvedValue([GITLAB, BAMBOO])
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    vi.mocked(service.generatePipelines).mockImplementation(() => new Promise(() => undefined))
    renderScreen()

    uploadFile("app.swarch")
    await screen.findByText("This model is valid")
    fireEvent.click(await screen.findByRole("checkbox", { name: "GitLab CI" }))
    fireEvent.click(screen.getByRole("checkbox", { name: "Bamboo" }))
    fireEvent.click(generateButton())

    expect(await screen.findByText("Running the existing GitLab CI and Bamboo transformation chain…")).toBeTruthy()
  })

  it("reports a platform that failed without losing the one that worked", async () => {
    vi.mocked(service.getPlatforms).mockResolvedValue([GITLAB, BAMBOO])
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    vi.mocked(service.generatePipelines).mockResolvedValue({
      pipeline_id: "p1",
      results: [
        GITLAB_RESULT,
        { ...GITLAB_RESULT, platform: { id: "bamboo", name: "Bamboo" }, pipeline: null, intermediates: [], duration_ms: null, saved_to: null, error: "The pipeline generator failed. Check the service logs for details." },
      ],
    })
    renderScreen()

    uploadFile("app.swarch")
    await screen.findByText("This model is valid")
    fireEvent.click(await screen.findByRole("checkbox", { name: "GitLab CI" }))
    fireEvent.click(screen.getByRole("checkbox", { name: "Bamboo" }))
    fireEvent.click(generateButton())

    expect(await screen.findByText("Bamboo pipeline could not be generated")).toBeTruthy()
    expect(screen.getByText("The pipeline generator failed. Check the service logs for details.")).toBeTruthy()
    expect(screen.getByText(/GitLab CI pipeline generated/)).toBeTruthy()
  })

  it("says when a pipeline could not be saved but still shows it", async () => {
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    vi.mocked(service.generatePipelines).mockResolvedValue({
      pipeline_id: "p1",
      results: [{ ...GITLAB_RESULT, saved_to: null, save_error: "That path is outside the workspace folder." }],
    })
    renderScreen()

    await loadValidModelAndGenerate()

    expect(await screen.findByText(/Could not save the file: That path is outside the workspace folder\./)).toBeTruthy()
    expect(screen.getByLabelText(".gitlab-ci.yml")).toBeTruthy()
  })

  it("shows why generation was refused when the backend rejects the model", async () => {
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    vi.mocked(service.generatePipelines).mockRejectedValue(
      new service.GeneratorError("The model is not valid, so no pipeline was generated.", 422, [
        { severity: "ERROR", message: "Required value missing" },
      ])
    )
    renderScreen()

    await loadValidModelAndGenerate()

    expect(await screen.findByText("The model is not valid, so no pipeline was generated.")).toBeTruthy()
    expect(screen.getByText("Required value missing")).toBeTruthy()
  })

  it("downloads the pipeline without the leading dot and says what to rename it to", async () => {
    // Browsers can't be relied on to keep a leading dot, so the offered name
    // is always dotless and the screen tells the user the real one.
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    vi.mocked(service.generatePipelines).mockResolvedValue(RESPONSE)
    const createObjectURL = vi.fn(() => "blob:pipeline")
    vi.stubGlobal("URL", Object.assign(URL, { createObjectURL, revokeObjectURL: vi.fn() }))
    const saved: string[] = []
    vi.spyOn(HTMLAnchorElement.prototype, "click").mockImplementation(function (this: HTMLAnchorElement) {
      saved.push(this.download)
    })
    renderScreen()

    await loadValidModelAndGenerate()
    fireEvent.click(await screen.findByRole("button", { name: "Download .gitlab-ci.yml" }))

    expect(saved).toEqual(["gitlab-ci.yml"])
    expect(screen.getByText(/needs this file to be named \.gitlab-ci\.yml/)).toBeTruthy()
  })

  it("discards the generated pipeline when a different file is loaded", async () => {
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    vi.mocked(service.generatePipelines).mockResolvedValue(RESPONSE)
    renderScreen()

    await loadValidModelAndGenerate()
    await screen.findByText(/pipeline generated in/)

    uploadFile("two.swarch")

    await waitFor(() => expect(screen.queryByText(/pipeline generated in/)).toBeNull())
    expect(screen.getByText("Result will appear here once generated.")).toBeTruthy()
  })

  it("keeps the chosen platforms and save folder when a different file is loaded", async () => {
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    renderScreen()
    uploadFile("one.swarch")
    await screen.findByText("This model is valid")
    await pickGitlab()
    fireEvent.change(saveField(), { target: { value: "./elsewhere/" } })

    uploadFile("two.swarch")

    await waitFor(() => expect(service.inspectModel).toHaveBeenCalledTimes(2))
    expect(checkbox("GitLab CI").checked).toBe(true)
    expect(saveField().value).toBe("./elsewhere/")
  })

  it("can generate again for a new file that replaced one mid-generation", async () => {
    // The replaced file's request never settles, so nothing but the new load
    // can clear the busy state it left behind.
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    vi.mocked(service.generatePipelines).mockImplementationOnce(() => new Promise(() => undefined))
    renderScreen()

    await loadValidModelAndGenerate()
    await screen.findByRole("button", { name: "Generating…" })
    uploadFile("two.swarch")

    await waitFor(() => expect(generateButton().disabled).toBe(false))
    expect(generateButton().textContent).toBe("Generate")
  })
})

describe("GenerateScreen: validating a pipeline", () => {
  it("validates the generated pipeline for its platform", async () => {
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    vi.mocked(service.generatePipelines).mockResolvedValue(RESPONSE)
    vi.mocked(service.validatePipeline).mockResolvedValue(PASSING)
    renderScreen()

    await loadValidModelAndGenerate()
    fireEvent.click(await screen.findByRole("button", { name: "Validate pipeline" }))

    expect(await screen.findByText("All checks passed")).toBeTruthy()
    expect(service.validatePipeline).toHaveBeenCalledWith("gitlab", GITLAB_RESULT.pipeline)
    expect(screen.getByText("The file is valid YAML")).toBeTruthy()
  })

  it("lists each failed check with what to look at", async () => {
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    vi.mocked(service.generatePipelines).mockResolvedValue(RESPONSE)
    vi.mocked(service.validatePipeline).mockResolvedValue({
      valid: false,
      checks: [
        { id: "yaml", title: "The file is valid YAML", status: "pass", details: [] },
        { id: "schema", title: "The settings follow GitLab's CI format", status: "fail", details: ["build: Additional properties are not allowed ('scrpt' was unexpected)"] },
        { id: "needs", title: "Every job dependency points at a real job", status: "skipped", details: [] },
      ],
    })
    renderScreen()

    await loadValidModelAndGenerate()
    fireEvent.click(await screen.findByRole("button", { name: "Validate pipeline" }))

    expect(await screen.findByText("1 check failed")).toBeTruthy()
    expect(screen.getByText(/'scrpt' was unexpected/)).toBeTruthy()
    expect(screen.getByText("Skipped")).toBeTruthy()
  })

  it("shows the real error when validation itself fails", async () => {
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    vi.mocked(service.generatePipelines).mockResolvedValue(RESPONSE)
    vi.mocked(service.validatePipeline).mockRejectedValue(new service.GeneratorError("Validate request failed: 500", 500))
    renderScreen()

    await loadValidModelAndGenerate()
    fireEvent.click(await screen.findByRole("button", { name: "Validate pipeline" }))

    expect(await screen.findByText("Validate request failed: 500")).toBeTruthy()
  })

  it("does not show the old pipeline's validation after generating again", async () => {
    let resolveValidation: (value: PipelineValidation) => void = () => undefined
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    vi.mocked(service.generatePipelines).mockResolvedValue(RESPONSE)
    vi.mocked(service.validatePipeline).mockImplementationOnce(() => new Promise((resolve) => { resolveValidation = resolve }))
    renderScreen()

    await loadValidModelAndGenerate()
    fireEvent.click(await screen.findByRole("button", { name: "Validate pipeline" }))
    await screen.findByRole("button", { name: "Validating…" })

    fireEvent.click(generateButton())
    await waitFor(() => expect(vi.mocked(service.generatePipelines)).toHaveBeenCalledTimes(2))
    resolveValidation(PASSING)
    // Let the late answer land before looking: asserting on the very next tick
    // would pass whether or not it was (wrongly) applied.
    await act(async () => undefined)

    expect(screen.queryByText("All checks passed")).toBeNull()
    expect(screen.queryByRole("button", { name: "Validating…" })).toBeNull()
  })
})

describe("GenerateScreen: the platform list", () => {
  it("keeps the platform-list error after a file is loaded, and retries on request", async () => {
    vi.mocked(service.getPlatforms)
      .mockRejectedValueOnce(new service.GeneratorError("The platform list is unavailable.", 502))
      .mockResolvedValue([GITLAB])
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    renderScreen()

    uploadFile("app.swarch")
    await screen.findByText("This model is valid")
    expect(await screen.findByText("The platform list is unavailable.")).toBeTruthy()

    fireEvent.click(screen.getByRole("button", { name: "Try again" }))

    expect(await screen.findByRole("checkbox", { name: "GitLab CI" })).toBeTruthy()
    expect(screen.queryByText("The platform list is unavailable.")).toBeNull()
  })
})

describe("GenerateScreen: bringing a saved pipeline back", () => {
  it("restores the model, the settings and the result it was made with", async () => {
    vi.mocked(service.getPipeline).mockResolvedValue(SAVED)
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    renderScreen({ pipelineId: "p1" })

    expect(await screen.findByText(/GitLab CI pipeline generated in 1\.5 s/)).toBeTruthy()
    expect(service.getPipeline).toHaveBeenCalledWith("p1")
    expect(pathField().value).toBe("/repos/app.swarch")
    expect(saveField().value).toBe("./out/")
    expect((await screen.findByRole("checkbox", { name: "GitLab CI" }) as HTMLInputElement).checked).toBe(true)
    expect(screen.getByText("✓ File loaded")).toBeTruthy()
    // Checked again, so a model that has since changed rules is not trusted from the record.
    expect(service.inspectModel).toHaveBeenCalledWith("app.swarch", "<swarch/>")
    expect(await screen.findByText("This model is valid")).toBeTruthy()
  })

  it("can generate again from a restored pipeline", async () => {
    vi.mocked(service.getPipeline).mockResolvedValue(SAVED)
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    vi.mocked(service.generatePipelines).mockResolvedValue(RESPONSE)
    renderScreen({ pipelineId: "p1" })
    await screen.findByText("This model is valid")

    await waitFor(() => expect(generateButton().disabled).toBe(false))
    fireEvent.click(generateButton())

    await waitFor(() => expect(service.generatePipelines).toHaveBeenCalledTimes(1))
    expect(vi.mocked(service.generatePipelines).mock.calls[0][0]).toMatchObject({
      filename: "app.swarch",
      source_path: "/repos/app.swarch",
      platforms: ["gitlab"],
      save_to: "./out/",
    })
  })

  it("shows the real reason when the saved pipeline cannot be opened", async () => {
    vi.mocked(service.getPipeline).mockRejectedValue(new service.GeneratorError("No such pipeline.", 404))
    renderScreen({ pipelineId: "gone" })

    expect(await screen.findByText("No such pipeline.")).toBeTruthy()
    expect(service.inspectModel).not.toHaveBeenCalled()
    expect(screen.getByText("Result will appear here once generated.")).toBeTruthy()
  })

  it("does not ask for a saved pipeline when opened fresh", async () => {
    renderScreen()
    await screen.findByRole("checkbox", { name: "GitLab CI" })

    expect(service.getPipeline).not.toHaveBeenCalled()
  })
})

describe("GenerateScreen: failures and what the person is told", () => {
  it("does not leave 'Checking your file…' on screen after the check failed", async () => {
    vi.mocked(service.inspectModel).mockRejectedValue(
      new service.GeneratorError("The model checker is unavailable, try again shortly.", 502)
    )
    renderScreen()

    uploadFile("app.swarch")
    await screen.findByText("The model checker is unavailable, try again shortly.")

    expect(screen.queryByText("Checking your file…")).toBeNull()
  })

  it("says the generator cannot be reached, not the browser's own words", async () => {
    vi.mocked(service.inspectModel).mockRejectedValue(new TypeError("Failed to fetch"))
    renderScreen()

    uploadFile("app.swarch")

    expect(await screen.findByText(/Could not reach the pipeline generator/)).toBeTruthy()
    expect(screen.queryByText("Failed to fetch")).toBeNull()
  })

  it("lets a path that failed to load be tried again with the same text", async () => {
    vi.mocked(service.readWorkspaceFile)
      .mockRejectedValueOnce(new service.GeneratorError("The workspace folder is not available.", 503))
      .mockResolvedValue({ filename: "a.swarch", path: "/a.swarch", content: "<swarch/>" })
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    renderScreen()

    fireEvent.change(pathField(), { target: { value: "/a.swarch" } })
    fireEvent.keyDown(pathField(), { key: "Enter" })
    await screen.findByText("The workspace folder is not available.")
    fireEvent.keyDown(pathField(), { key: "Enter" })

    expect(await screen.findByText("This model is valid")).toBeTruthy()
    expect(service.readWorkspaceFile).toHaveBeenCalledTimes(2)
  })

  it("says when what was generated could not be added to the pipelines list", async () => {
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    vi.mocked(service.generatePipelines).mockResolvedValue({ pipeline_id: null, results: [GITLAB_RESULT] })
    renderScreen()

    await loadValidModelAndGenerate()

    expect(await screen.findByText("This pipeline could not be added to your pipelines list.")).toBeTruthy()
    expect(screen.getByText(/GitLab CI pipeline generated/)).toBeTruthy()
  })

  it("does not say that when nothing was generated at all", async () => {
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    vi.mocked(service.generatePipelines).mockResolvedValue({
      pipeline_id: null,
      results: [{ ...GITLAB_RESULT, pipeline: null, intermediates: [], saved_to: null, error: "The pipeline generator failed. Check the service logs for details." }],
    })
    renderScreen()

    await loadValidModelAndGenerate()

    await screen.findByText("GitLab CI pipeline could not be generated")
    expect(screen.queryByText("This pipeline could not be added to your pipelines list.")).toBeNull()
  })

  it("says when saving replaced a file that was already there", async () => {
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    vi.mocked(service.generatePipelines).mockResolvedValue({
      pipeline_id: "p1",
      results: [{ ...GITLAB_RESULT, replaced: true }],
    })
    renderScreen()

    await loadValidModelAndGenerate()

    expect(await screen.findByText(/Saved to \/ci-output\/\.gitlab-ci\.yml \(replaced the file that was there\)/)).toBeTruthy()
  })
})

describe("GenerateScreen: a saved pipeline, in more detail", () => {
  it("keeps Generate locked when none of the platforms it named are on offer any more", async () => {
    vi.mocked(service.getPlatforms).mockResolvedValue([BAMBOO])
    vi.mocked(service.getPipeline).mockResolvedValue(SAVED)
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    renderScreen({ pipelineId: "p1" })

    await screen.findByText("This model is valid")
    await screen.findByRole("checkbox", { name: "Bamboo" })

    expect(generateButton().disabled).toBe(true)
    expect(screen.getByText("Select at least one platform.")).toBeTruthy()
  })

  it("reads the model again from where it came from, so it is the file as it is now", async () => {
    vi.mocked(service.getPipeline).mockResolvedValue(SAVED)
    vi.mocked(service.readWorkspaceFile).mockResolvedValue({ filename: "app.swarch", path: "/repos/app.swarch", content: "<swarch edited/>" })
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    vi.mocked(service.generatePipelines).mockResolvedValue(RESPONSE)
    renderScreen({ pipelineId: "p1" })

    await screen.findByText("This model is valid")

    expect(service.readWorkspaceFile).toHaveBeenCalledWith("/repos/app.swarch")
    expect(service.inspectModel).toHaveBeenCalledWith("app.swarch", "<swarch edited/>")
    await waitFor(() => expect(generateButton().disabled).toBe(false))
    fireEvent.click(generateButton())
    await waitFor(() => expect(service.generatePipelines).toHaveBeenCalledTimes(1))
    expect(vi.mocked(service.generatePipelines).mock.calls[0][0].content).toBe("<swarch edited/>")
  })

  it("falls back to the copy kept with the pipeline when the file has gone", async () => {
    vi.mocked(service.getPipeline).mockResolvedValue(SAVED)
    vi.mocked(service.readWorkspaceFile).mockRejectedValue(new service.GeneratorError("There is no file at that path in the workspace.", 404))
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    renderScreen({ pipelineId: "p1" })

    expect(await screen.findByText("This model is valid")).toBeTruthy()

    expect(service.inspectModel).toHaveBeenCalledWith("app.swarch", "<swarch/>")
    expect(screen.getByText("✓ File loaded")).toBeTruthy()
    expect(screen.getByText(/GitLab CI pipeline generated/)).toBeTruthy()
  })

  it("does not re-read an uploaded model: it has no path to read from", async () => {
    vi.mocked(service.getPipeline).mockResolvedValue({ ...SAVED, source_path: null })
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    renderScreen({ pipelineId: "p1" })

    await screen.findByText("This model is valid")

    expect(service.readWorkspaceFile).not.toHaveBeenCalled()
  })

  it("drops a saved pipeline that arrives after another file was loaded", async () => {
    let resolveSaved: (value: PipelineRecord) => void = () => undefined
    vi.mocked(service.getPipeline).mockImplementation(() => new Promise((resolve) => { resolveSaved = resolve }))
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    renderScreen({ pipelineId: "p1" })
    await screen.findByText("Opening your pipeline…")

    uploadFile("other.swarch")
    await screen.findByText("This model is valid")
    resolveSaved(SAVED)
    await act(async () => undefined)

    expect(pathField().value).toBe("other.swarch")
    expect(screen.queryByText(/GitLab CI pipeline generated/)).toBeNull()
    // The superseded open has finished, so its "Opening…" must not be left behind.
    expect(screen.queryByText("Opening your pipeline…")).toBeNull()
  })
})

describe("GenerateScreen: the browse dialog's keyboard and focus", () => {
  const ROOT = {
    path: "/",
    parent: null,
    entries: [
      { name: "repos", kind: "dir" as const },
      { name: "top.swarch", kind: "file" as const },
    ],
    truncated: false,
    root_label: "the MDDOAI repository",
  }
  const REPOS = { path: "/repos", parent: "/", entries: [{ name: "a.swarch", kind: "file" as const }], truncated: false, root_label: "the MDDOAI repository" }

  beforeEach(() => {
    vi.mocked(service.browseWorkspace).mockImplementation(async (path) => (path === "/repos" ? REPOS : ROOT))
  })

  it("still closes with Escape after a folder has been opened", async () => {
    renderScreen()
    fireEvent.click(screen.getAllByRole("button", { name: "Browse" })[0])
    const dialog = await screen.findByRole("dialog")
    fireEvent.click(await within(dialog).findByRole("button", { name: "repos" }))
    await within(dialog).findByRole("button", { name: "a.swarch" })

    // Focus is no longer inside the dialog (the list it was in was replaced):
    // the key is heard by the page.
    fireEvent.keyDown(document.body, { key: "Escape" })

    expect(screen.queryByRole("dialog")).toBeNull()
  })

  it("closes when the dimmed area around it is clicked, but not when the dialog itself is", async () => {
    renderScreen()
    fireEvent.click(screen.getAllByRole("button", { name: "Browse" })[0])
    const dialog = await screen.findByRole("dialog")

    fireEvent.mouseDown(dialog)
    expect(screen.queryByRole("dialog")).not.toBeNull()
    fireEvent.mouseDown(dialog.parentElement as HTMLElement)

    expect(screen.queryByRole("dialog")).toBeNull()
  })

  it("keeps Tab inside the dialog, in both directions", async () => {
    renderScreen()
    fireEvent.click(screen.getAllByRole("button", { name: "Browse" })[0])
    const dialog = await screen.findByRole("dialog")
    const close = within(dialog).getByRole("button", { name: "Close" })
    const last = await within(dialog).findByRole("button", { name: "top.swarch" })

    last.focus()
    fireEvent.keyDown(last, { key: "Tab" })
    expect(document.activeElement).toBe(close)

    fireEvent.keyDown(close, { key: "Tab", shiftKey: true })
    expect(document.activeElement).toBe(last)
  })

  it("brings Tab back into the dialog when focus has fallen out of it", async () => {
    renderScreen()
    fireEvent.click(screen.getAllByRole("button", { name: "Browse" })[0])
    const dialog = await screen.findByRole("dialog")
    await within(dialog).findByRole("button", { name: "repos" })
    ;(document.activeElement as HTMLElement | null)?.blur()

    fireEvent.keyDown(document.body, { key: "Tab" })

    expect(dialog.contains(document.activeElement)).toBe(true)
  })

  it("gives focus back to the button that opened it", async () => {
    renderScreen()
    const opener = screen.getAllByRole("button", { name: "Browse" })[0]
    act(() => opener.focus())
    fireEvent.click(opener)
    await screen.findByRole("dialog")

    fireEvent.keyDown(document.body, { key: "Escape" })

    expect(document.activeElement).toBe(opener)
  })
})

describe("GenerateScreen: trying a path or file again", () => {
  const FILE = { filename: "a.swarch", path: "/a.swarch", content: "<swarch/>" }
  const CHECKER_DOWN = new service.GeneratorError("The model checker is unavailable, try again shortly.", 502)

  it("lets a path whose check failed be tried again with the same text", async () => {
    vi.mocked(service.readWorkspaceFile).mockResolvedValue(FILE)
    vi.mocked(service.inspectModel).mockRejectedValueOnce(CHECKER_DOWN).mockResolvedValue(VALID)
    renderScreen()

    fireEvent.change(pathField(), { target: { value: "/a.swarch" } })
    fireEvent.keyDown(pathField(), { key: "Enter" })
    await screen.findByText("The model checker is unavailable, try again shortly.")
    fireEvent.keyDown(pathField(), { key: "Enter" })

    expect(await screen.findByText("This model is valid")).toBeTruthy()
    expect(service.readWorkspaceFile).toHaveBeenCalledTimes(2)
  })

  it("does not read an uploaded file's name as a path after its check failed", async () => {
    vi.mocked(service.inspectModel).mockRejectedValue(CHECKER_DOWN)
    renderScreen()

    uploadFile("app.swarch")
    await screen.findByText("The model checker is unavailable, try again shortly.")
    fireEvent.keyDown(pathField(), { key: "Enter" })
    fireEvent.blur(pathField())

    expect(service.readWorkspaceFile).not.toHaveBeenCalled()
  })

  it("lets the path still in the field be loaded after an upload was rejected", async () => {
    vi.mocked(service.readWorkspaceFile).mockResolvedValue(FILE)
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    renderScreen()
    fireEvent.change(pathField(), { target: { value: "/a.swarch" } })
    fireEvent.keyDown(pathField(), { key: "Enter" })
    await screen.findByText("This model is valid")

    uploadFile("empty.swarch", "")
    await screen.findByText("This file is empty.")
    fireEvent.keyDown(pathField(), { key: "Enter" })

    expect(await screen.findByText("This model is valid")).toBeTruthy()
    expect(service.readWorkspaceFile).toHaveBeenCalledTimes(2)
  })

  it("shows the saved pipeline from its kept copy when reading the file fails for another reason than it being gone", async () => {
    vi.mocked(service.getPipeline).mockResolvedValue(SAVED)
    vi.mocked(service.readWorkspaceFile).mockRejectedValue(new TypeError("Failed to fetch"))
    vi.mocked(service.inspectModel).mockResolvedValue(VALID)
    renderScreen({ pipelineId: "p1" })

    await screen.findByText("This model is valid")

    expect(service.inspectModel).toHaveBeenCalledWith("app.swarch", "<swarch/>")
  })

  it("reports a bug while reading a saved model as an error instead of hiding it", async () => {
    vi.mocked(service.getPipeline).mockResolvedValue(SAVED)
    vi.mocked(service.readWorkspaceFile).mockRejectedValue(new RangeError("not a request problem"))
    renderScreen({ pipelineId: "p1" })

    expect(await screen.findByText("not a request problem")).toBeTruthy()
    expect(service.inspectModel).not.toHaveBeenCalled()
  })
})
