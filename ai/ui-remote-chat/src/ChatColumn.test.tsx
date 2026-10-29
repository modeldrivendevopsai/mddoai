// @vitest-environment jsdom
//
// What the chat shows while a message is answered in the background, and
// when answering it failed.
import { cleanup, render, screen } from "@testing-library/react"
import type { OrchestratorEvent } from "orchestrator-types"
import { afterEach, beforeAll, describe, expect, it, vi } from "vitest"
import { ChatColumn } from "./ChatColumn"

function renderChat(props: { events?: OrchestratorEvent[]; replying?: boolean; readOnly?: boolean }) {
  return render(
    <ChatColumn
      events={props.events ?? []}
      busy={false}
      model={null}
      providers={[]}
      onSend={vi.fn()}
      onModelChange={vi.fn()}
      replying={props.replying}
      readOnly={props.readOnly}
    />
  )
}

// jsdom has no layout, so no scrolling; the chat scrolls to new events.
beforeAll(() => {
  Element.prototype.scrollTo = vi.fn()
})

afterEach(cleanup)

describe("ChatColumn", () => {
  it("says the Orchestrator is replying and holds the input until it is done", () => {
    renderChat({ replying: true })

    expect(screen.getByText("Orchestrator is replying…")).toBeTruthy()
    expect((screen.getByRole("textbox") as HTMLTextAreaElement).disabled).toBe(true)
  })

  it("takes input again once the reply is in", () => {
    renderChat({ replying: false })

    expect(screen.queryByText("Orchestrator is replying…")).toBeNull()
    expect((screen.getByRole("textbox") as HTMLTextAreaElement).disabled).toBe(false)
  })

  it("does not show the replying line on a read-only past run", () => {
    renderChat({ replying: true, readOnly: true })

    expect(screen.queryByText("Orchestrator is replying…")).toBeNull()
  })

  it("marks a failed reply with its error", () => {
    const failed: OrchestratorEvent = {
      type: "message_failed",
      stage: "psm",
      timestamp: 0,
      data: { error: "all providers exhausted" },
    }
    renderChat({ events: [failed] })

    expect(screen.getByText("Reply failed: psm")).toBeTruthy()
    expect(screen.getByText(/all providers exhausted/)).toBeTruthy()
  })
})
