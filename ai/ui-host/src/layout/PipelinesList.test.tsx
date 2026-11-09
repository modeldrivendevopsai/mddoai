// @vitest-environment jsdom
import { act, cleanup, fireEvent, render, screen } from "@testing-library/react"
import { afterEach, beforeEach, describe, expect, it, vi } from "vitest"
import { PipelinesList } from "./PipelinesList"
import { SIDEBAR_POLL_INTERVAL_MS } from "../config/sidebar.config"
import * as service from "../services/generator.service"
import type { PipelineSummary } from "../services/generator.service"

vi.mock("../services/generator.service", () => ({ listPipelines: vi.fn() }))

const ROWS: PipelineSummary[] = [
  {
    id: "p2",
    swarch_name: "payments.swarch",
    source_path: "/repos/payments.swarch",
    platforms: [{ id: "gitlab", name: "GitLab CI" }],
    outputs: [".gitlab-ci.yml"],
    generated_at: "2026-11-03T10:00:00Z",
  },
  {
    id: "p1",
    swarch_name: "shop.swarch",
    source_path: null,
    platforms: [
      { id: "gitlab", name: "GitLab CI" },
      { id: "bamboo", name: "Bamboo" },
    ],
    outputs: [".gitlab-ci.yml", "bamboo-specs/bamboo.yaml"],
    generated_at: "2026-11-02T10:00:00Z",
  },
]

beforeEach(() => {
  vi.mocked(service.listPipelines).mockReset()
})
afterEach(() => {
  cleanup()
  vi.useRealTimers()
})

describe("the sidebar's generated pipelines", () => {
  it("lists each pipeline with the platforms it was made for and the files that came out", async () => {
    vi.mocked(service.listPipelines).mockResolvedValue(ROWS)
    render(<PipelinesList openId={undefined} onOpen={() => undefined} />)

    expect(await screen.findByText("payments.swarch")).toBeTruthy()
    expect(screen.getByText("GitLab CI · .gitlab-ci.yml")).toBeTruthy()
    expect(screen.getByText("GitLab CI, Bamboo · .gitlab-ci.yml, bamboo-specs/bamboo.yaml")).toBeTruthy()
  })

  it("opens a pipeline when its row is chosen, leaving where it goes to the caller", async () => {
    vi.mocked(service.listPipelines).mockResolvedValue(ROWS)
    const onOpen = vi.fn()
    render(<PipelinesList openId={undefined} onOpen={onOpen} />)

    fireEvent.click(await screen.findByRole("button", { name: /shop\.swarch/ }))

    expect(onOpen).toHaveBeenCalledWith("p1")
  })

  it("marks the pipeline that is open", async () => {
    vi.mocked(service.listPipelines).mockResolvedValue(ROWS)
    render(<PipelinesList openId="p1" onOpen={() => undefined} />)

    expect((await screen.findByRole("button", { name: /shop\.swarch/ })).getAttribute("aria-current")).toBe("true")
    expect(screen.getByRole("button", { name: /payments\.swarch/ }).getAttribute("aria-current")).toBeNull()
  })

  it("says so when nothing has been generated yet", async () => {
    vi.mocked(service.listPipelines).mockResolvedValue([])
    render(<PipelinesList openId={undefined} onOpen={() => undefined} />)

    expect(await screen.findByText("Nothing generated yet")).toBeTruthy()
  })

  it("says so when the list cannot be loaded", async () => {
    vi.mocked(service.listPipelines).mockRejectedValue(new Error("down"))
    render(<PipelinesList openId={undefined} onOpen={() => undefined} />)

    expect(await screen.findByText("Could not load your pipelines")).toBeTruthy()
  })

  it("recovers once the list can be loaded again", async () => {
    vi.useFakeTimers()
    vi.mocked(service.listPipelines).mockRejectedValueOnce(new Error("down")).mockResolvedValue(ROWS)
    render(<PipelinesList openId={undefined} onOpen={() => undefined} />)
    await act(async () => undefined)
    expect(screen.getByText("Could not load your pipelines")).toBeTruthy()

    await act(async () => {
      await vi.advanceTimersByTimeAsync(SIDEBAR_POLL_INTERVAL_MS)
    })

    expect(screen.getByText("payments.swarch")).toBeTruthy()
    expect(screen.queryByText("Could not load your pipelines")).toBeNull()
  })

  it("stops refreshing once it is gone", async () => {
    vi.useFakeTimers()
    vi.mocked(service.listPipelines).mockResolvedValue(ROWS)
    const { unmount } = render(<PipelinesList openId={undefined} onOpen={() => undefined} />)
    await act(async () => undefined)
    const callsWhileShown = vi.mocked(service.listPipelines).mock.calls.length

    unmount()
    await vi.advanceTimersByTimeAsync(SIDEBAR_POLL_INTERVAL_MS * 3)

    expect(vi.mocked(service.listPipelines).mock.calls.length).toBe(callsWhileShown)
  })

  it("tells the whole story of a row in its tooltip, since the row itself is cut short", async () => {
    vi.mocked(service.listPipelines).mockResolvedValue(ROWS)
    render(<PipelinesList openId={undefined} onOpen={() => undefined} />)

    const row = await screen.findByRole("button", { name: /shop\.swarch/ })

    expect(row.getAttribute("title")).toContain("shop.swarch\nGitLab CI, Bamboo · .gitlab-ci.yml, bamboo-specs/bamboo.yaml")
  })

  it("picks up a pipeline generated meanwhile, and keeps the list when a refresh fails", async () => {
    vi.useFakeTimers()
    vi.mocked(service.listPipelines).mockResolvedValueOnce([]).mockResolvedValueOnce(ROWS).mockRejectedValue(new Error("down"))
    render(<PipelinesList openId={undefined} onOpen={() => undefined} />)
    await act(async () => undefined)
    expect(screen.getByText("Nothing generated yet")).toBeTruthy()

    await act(async () => {
      await vi.advanceTimersByTimeAsync(SIDEBAR_POLL_INTERVAL_MS)
    })
    expect(screen.getByText("payments.swarch")).toBeTruthy()

    await act(async () => {
      await vi.advanceTimersByTimeAsync(SIDEBAR_POLL_INTERVAL_MS)
    })
    expect(screen.getByText("payments.swarch")).toBeTruthy()
    expect(screen.queryByText("Could not load your pipelines")).toBeNull()
  })
})
