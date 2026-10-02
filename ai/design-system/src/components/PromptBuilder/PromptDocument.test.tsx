// @vitest-environment jsdom
//
// Regression test: dropping several files in one gesture must not lose any
// of them. Each dropped file's own upload resolves independently (see
// insertFilesAt in PromptDocument.tsx) - if a later file's callback closed
// over the `attachments` prop as it was when the drop STARTED, an
// earlier-resolving file's own insertion would be silently overwritten by a
// later one finishing its own, stale-based insert.
import { cleanup, fireEvent, render, screen, waitFor } from "@testing-library/react"
import { afterEach, describe, expect, it, vi } from "vitest"
import { PromptDocument } from "./PromptDocument"
import type { Attachment } from "./types"

afterEach(cleanup)

describe("PromptDocument", () => {
  it("keeps every dropped file even when their uploads resolve out of order", async () => {
    const fileOne = new File(["one"], "one.txt", { type: "text/plain" })
    const fileTwo = new File(["two"], "two.txt", { type: "text/plain" })

    let resolveOne: ((path: string) => void) | undefined
    let resolveTwo: ((path: string) => void) | undefined
    const onUploadFile = vi.fn((file: File) => {
      if (file.name === "one.txt") return new Promise<string>((resolve) => (resolveOne = resolve))
      return new Promise<string>((resolve) => (resolveTwo = resolve))
    })

    const changes: Attachment[][] = []
    const onAttachmentsChange = vi.fn((next: Attachment[]) => changes.push(next))

    render(
      <PromptDocument
        attachments={[]}
        attachmentTypes={["text", "file"]}
        contextKeyOptions={[]}
        broken={[]}
        onUploadFile={onUploadFile}
        onAttachmentsChange={onAttachmentsChange}
        learnedConstraints={[]}
        onAddConstraint={() => {}}
        onRemoveConstraint={() => {}}
        onReorderConstraints={() => {}}
      />
    )

    const dropZone = (await screen.findByText(/Nothing here yet/)).parentElement as HTMLElement
    fireEvent.drop(dropZone, { dataTransfer: { files: [fileOne, fileTwo] } })

    // Resolve out of order: the SECOND dropped file's upload finishes first.
    resolveTwo?.("uploads/two.txt")
    await waitFor(() => expect(onAttachmentsChange).toHaveBeenCalledTimes(1))
    resolveOne?.("uploads/one.txt")
    await waitFor(() => expect(onAttachmentsChange).toHaveBeenCalledTimes(2))

    const final = changes[changes.length - 1]
    expect(final.map((a) => a.name).sort()).toEqual(["one.txt", "two.txt"])
  })
})
