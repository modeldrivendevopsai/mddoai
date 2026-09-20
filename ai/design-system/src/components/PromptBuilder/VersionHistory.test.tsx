// @vitest-environment jsdom
//
// Real regression coverage for what this file's own review passes actually
// changed: the version list refetching on every open (not just the first)
// and on a currentVersion change while already open, Current/Original
// always pinned above a collapsed "older versions" toggle, and the diff
// view rendering real content instead of bare counts. Renders through
// jsdom + real React (matching useAutoSave.test.ts's own convention in
// this same folder), not a description of what the code is supposed to do.
import { act, cleanup, fireEvent, render, screen } from "@testing-library/react"
import { afterEach, describe, expect, it, vi } from "vitest"
import { VersionHistory } from "./VersionHistory"
import type { PromptConfig, PromptDiff } from "./types"

afterEach(cleanup)

const noopRestore = () => Promise.resolve({ attachments: [] } as PromptConfig)
const noopRestored = () => undefined

async function open() {
  await act(() => fireEvent.click(screen.getByText("Version history")))
}

describe("VersionHistory", () => {
  it("refetches the version list every time the panel is opened, not just the first time", async () => {
    const onLoadHistory = vi.fn().mockResolvedValueOnce(["v1", "shipped"]).mockResolvedValueOnce(["v2", "v1", "shipped"])
    render(
      <VersionHistory
        onLoadHistory={onLoadHistory}
        onDiffVersions={vi.fn()}
        onRestoreVersion={noopRestore}
        onRestored={noopRestored}
      />
    )

    await open()
    await screen.findByText("Original shipped prompt")
    expect(onLoadHistory).toHaveBeenCalledTimes(1)

    await act(() => fireEvent.click(screen.getByText("Hide version history")))
    await open()

    expect(onLoadHistory).toHaveBeenCalledTimes(2)
    // The second load's real difference (an extra "v2") shows up as one
    // more entry to reveal, proving the refetch's own response actually
    // replaced state rather than the first, cached response sticking.
    expect(await screen.findByText("Show 2 older versions")).toBeTruthy()
  })

  it("refetches while already open when currentVersion changes", async () => {
    const onLoadHistory = vi.fn().mockResolvedValueOnce(["v1", "shipped"]).mockResolvedValueOnce(["v2", "v1", "shipped"])
    const { rerender } = render(
      <VersionHistory
        onLoadHistory={onLoadHistory}
        onDiffVersions={vi.fn()}
        onRestoreVersion={noopRestore}
        onRestored={noopRestored}
        currentVersion="v1"
      />
    )

    await open()
    await screen.findByText("v1")
    expect(onLoadHistory).toHaveBeenCalledTimes(1)

    // The panel stays open (expanded state lives in this component, not
    // reset by a parent rerender) while a real edit autosaves a new
    // version - currentVersion moving to it must be enough to refetch on
    // its own, without the human closing and reopening this panel first.
    rerender(
      <VersionHistory
        onLoadHistory={onLoadHistory}
        onDiffVersions={vi.fn()}
        onRestoreVersion={noopRestore}
        onRestored={noopRestored}
        currentVersion="v2"
      />
    )

    expect(await screen.findByText("v2")).toBeTruthy()
    expect(onLoadHistory).toHaveBeenCalledTimes(2)
  })

  it("pins Current and Original above a collapsed toggle for the rest", async () => {
    render(
      <VersionHistory
        onLoadHistory={() => Promise.resolve(["v3", "v2", "v1", "shipped"])}
        onDiffVersions={vi.fn()}
        onRestoreVersion={noopRestore}
        onRestored={noopRestored}
        currentVersion="v2"
      />
    )

    await open()
    // Current (v2) and the shipped original are visible immediately.
    await screen.findByText("v2")
    expect(screen.getByText("Original shipped prompt")).toBeTruthy()
    // v1 and v3 are real, older versions - not dumped in the human's face
    // by default, collapsed behind a toggle naming exactly how many there are.
    expect(screen.queryByText("v1")).toBeNull()
    expect(screen.queryByText("v3")).toBeNull()
    expect(screen.getByText("Show 2 older versions")).toBeTruthy()

    fireEvent.click(screen.getByText("Show 2 older versions"))

    expect(await screen.findByText("v1")).toBeTruthy()
    expect(screen.getByText("v3")).toBeTruthy()
  })

  it("pins Current and Original as one deduped row when nothing has ever been saved", async () => {
    render(
      <VersionHistory
        onLoadHistory={() => Promise.resolve(["shipped"])}
        onDiffVersions={vi.fn()}
        onRestoreVersion={noopRestore}
        onRestored={noopRestored}
      />
    )

    await open()

    const rows = await screen.findAllByRole("listitem")
    expect(rows).toHaveLength(1)
    expect(rows[0].textContent).toContain("Original shipped prompt")
    expect(rows[0].textContent).toContain("Current")
  })

  it("renders a real line-by-line diff for a changed attachment, not just a count", async () => {
    const diff: PromptDiff = {
      attachments_added: [],
      attachments_removed: [],
      attachments_changed: [
        {
          id: "a",
          before: { id: "a", name: "A", type: "text", content: "keep\nold" },
          after: { id: "a", name: "A", type: "text", content: "keep\nnew" },
          content_diff: [
            { op: "unchanged", text: "keep" },
            { op: "removed", text: "old" },
            { op: "added", text: "new" },
          ],
        },
      ],
    }
    render(
      <VersionHistory
        onLoadHistory={() => Promise.resolve(["v1", "shipped"])}
        onDiffVersions={() => Promise.resolve(diff)}
        onRestoreVersion={noopRestore}
        onRestored={noopRestored}
      />
    )

    await open()
    await screen.findByText("Original shipped prompt")
    fireEvent.click(screen.getByText("Show 1 older version"))
    await screen.findByText("v1")
    fireEvent.click(screen.getAllByLabelText(/diff base/)[0])
    fireEvent.click(screen.getAllByLabelText(/diff target/)[1])
    await act(() => fireEvent.click(screen.getByText("Diff selected versions")))

    // Each rendered line carries a leading "- "/"+ "/"  " marker as its own
    // adjacent text node alongside the real text, so check the diff
    // block's own full text content rather than one exact node's text.
    const diffBlock = await screen.findByText("A (text)")
    const container = diffBlock.parentElement!
    expect(container.textContent).toContain("old")
    expect(container.textContent).toContain("new")
    expect(container.textContent).toContain("keep")
  })

  it("shows a rename note, not a misleading all-unchanged diff block, when only the name changed", async () => {
    const diff: PromptDiff = {
      attachments_added: [],
      attachments_removed: [],
      attachments_changed: [
        {
          id: "a",
          before: { id: "a", name: "Old name", type: "text", content: "same" },
          after: { id: "a", name: "New name", type: "text", content: "same" },
          content_diff: [{ op: "unchanged", text: "same" }],
        },
      ],
    }
    render(
      <VersionHistory
        onLoadHistory={() => Promise.resolve(["v1", "shipped"])}
        onDiffVersions={() => Promise.resolve(diff)}
        onRestoreVersion={noopRestore}
        onRestored={noopRestored}
      />
    )

    await open()
    await screen.findByText("Original shipped prompt")
    fireEvent.click(screen.getByText("Show 1 older version"))
    await screen.findByText("v1")
    fireEvent.click(screen.getAllByLabelText(/diff base/)[0])
    fireEvent.click(screen.getAllByLabelText(/diff target/)[1])
    await act(() => fireEvent.click(screen.getByText("Diff selected versions")))

    expect(await screen.findByText('Renamed from "Old name" to "New name"')).toBeTruthy()
    expect(screen.queryByText("same")).toBeNull()
  })

  it("does not offer Restore on the row marked Current, since restoring it is always a no-op", async () => {
    render(
      <VersionHistory
        onLoadHistory={() => Promise.resolve(["v1", "shipped"])}
        onDiffVersions={vi.fn()}
        onRestoreVersion={noopRestore}
        onRestored={noopRestored}
        currentVersion="v1"
      />
    )

    await open()
    await screen.findByText("v1")

    // v1 (Current) and shipped (Original) are both pinned and visible -
    // only shipped's row gets a Restore button.
    expect(screen.getAllByText("Restore")).toHaveLength(1)
  })

  it("asks for confirmation before restoring, and does nothing if declined", async () => {
    const onRestoreVersion = vi.fn().mockResolvedValue({ attachments: [] } as PromptConfig)
    const onRestored = vi.fn()
    const confirmSpy = vi.spyOn(window, "confirm").mockReturnValue(false)

    render(
      <VersionHistory
        onLoadHistory={() => Promise.resolve(["v1", "shipped"])}
        onDiffVersions={vi.fn()}
        onRestoreVersion={onRestoreVersion}
        onRestored={onRestored}
      />
    )

    await open()
    await screen.findByText("Original shipped prompt")
    fireEvent.click(screen.getByText("Show 1 older version"))
    await screen.findByText("v1")

    fireEvent.click(screen.getByText("Restore"))
    expect(confirmSpy).toHaveBeenCalled()
    expect(onRestoreVersion).not.toHaveBeenCalled()

    confirmSpy.mockReturnValue(true)
    await act(() => fireEvent.click(screen.getByText("Restore")))

    expect(onRestoreVersion).toHaveBeenCalledWith("v1")
    expect(onRestored).toHaveBeenCalled()

    confirmSpy.mockRestore()
  })

  it("clears a rendered diff when the base or target selection changes", async () => {
    const diff: PromptDiff = { attachments_added: [], attachments_removed: [], attachments_changed: [] }
    render(
      <VersionHistory
        onLoadHistory={() => Promise.resolve(["v1", "shipped"])}
        onDiffVersions={() => Promise.resolve(diff)}
        onRestoreVersion={noopRestore}
        onRestored={noopRestored}
      />
    )

    await open()
    await screen.findByText("Original shipped prompt")
    fireEvent.click(screen.getByText("Show 1 older version"))
    await screen.findByText("v1")
    fireEvent.click(screen.getAllByLabelText(/diff base/)[0])
    fireEvent.click(screen.getAllByLabelText(/diff target/)[1])
    await act(() => fireEvent.click(screen.getByText("Diff selected versions")))

    expect(await screen.findByText("Identical")).toBeTruthy()

    // A stale diff for a since-abandoned pair must not linger on screen
    // looking like it still describes the current selection.
    fireEvent.click(screen.getAllByLabelText(/diff base/)[1])

    expect(screen.queryByText("Identical")).toBeNull()
  })
})
