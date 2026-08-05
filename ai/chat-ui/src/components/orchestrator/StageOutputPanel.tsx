import { useState } from "react"
import type { ReactNode } from "react"
import type { OrchestratorEvent, StageId } from "@/types/orchestrator"
import { Button } from "@/design-system"
import { CodeBlock } from "./CodeBlock"

interface StageOutputPanelProps {
  currentStage: StageId | null
  busy: boolean
  latestResult: OrchestratorEvent | null
  onApprove: (stage: StageId) => void
  onRetry: (stage: StageId, correction?: string) => void
  // True while viewing a past (non-current) run from the sidebar's history —
  // approve/retry would silently act on the live run instead of the one on
  // screen, so both are disabled.
  readOnly?: boolean
}

export function StageOutputPanel({
  currentStage,
  busy,
  latestResult,
  onApprove,
  onRetry,
  readOnly = false,
}: StageOutputPanelProps) {
  const [correction, setCorrection] = useState("")

  if (!currentStage) {
    return (
      <Panel>
        <p style={{ color: "var(--text-muted)", fontFamily: "var(--font-sans)" }}>
          Pipeline complete. All stages approved.
        </p>
      </Panel>
    )
  }

  const failed = latestResult?.type === "call_failed"
  const hasResult = latestResult !== null
  const output = failed
    ? String(latestResult?.data?.error ?? "Stage failed.")
    : String(latestResult?.data?.output ?? "")

  return (
    <Panel>
      <h2
        style={{
          fontFamily: "var(--font-display)",
          fontSize: "var(--text-md)",
          fontWeight: "var(--weight-bold)",
          color: "var(--text-strong)",
          margin: 0,
          textTransform: "capitalize",
        }}
      >
        {currentStage} stage output
      </h2>

      {/* Matches Callout.jsx's real "danger" tone exactly: bg danger-100,
          border #f3c4c0 (not the fully-saturated danger-500), radius-md,
          14px/16px padding, these are the source's own literal values, not
          token substitutions. */}
      {failed && (
        <div
          style={{
            display: "flex",
            gap: 12,
            padding: "14px 16px",
            background: "var(--danger-100)",
            border: "1px solid #f3c4c0",
            borderRadius: "var(--radius-md)",
            fontFamily: "var(--font-sans)",
          }}
        >
          <div style={{ fontWeight: 600, fontSize: 14, color: "var(--text-strong)" }}>Automated check: FAILED</div>
        </div>
      )}

      <CodeBlock
        code={busy ? "Generating…" : hasResult ? output : "No output yet."}
        title={`${currentStage} output`}
        lang={currentStage}
      />

      <div>
        {/* Real wireframe text is "Curate the helper prompt for this stage"
            (frame "c5: ATL check failed"), where the field is pre-filled with
            the actual prompt that was used, editable in place. We can't
            faithfully do that: ai/orchestrator doesn't store or expose "the
            literal prompt used" anywhere, agents build it from context + the
            constraints list, there's no single retrievable prompt string to
            pre-fill with. This is the real, honest equivalent: an empty
            field for a new correction, recorded via the same
            add-constraint-then-retry mechanism the backend actually has. */}
        <p
          style={{
            fontFamily: "var(--font-sans)",
            fontSize: "var(--text-xs)",
            fontWeight: "var(--weight-bold)",
            color: "var(--text-strong)",
            margin: "0 0 var(--space-1)",
          }}
        >
          Curate the helper prompt for this stage
        </p>
        <textarea
          className="orch-field"
          value={correction}
          onChange={(e) => setCorrection(e.target.value)}
          placeholder="Describe what should change"
          rows={2}
          disabled={readOnly}
          style={{
            width: "100%",
            resize: "none",
            boxSizing: "border-box",
            border: "1px solid var(--border-default)",
            borderRadius: "var(--radius-md)",
            padding: "var(--space-2) var(--space-3)",
            fontFamily: "var(--font-sans)",
            fontSize: "var(--text-sm)",
            background: "var(--surface-card)",
            color: "var(--text-body)",
          }}
        />
      </div>

      <div style={{ display: "flex", gap: "var(--space-2)" }}>
        <Button
          variant="primary"
          size="sm"
          disabled={busy || !hasResult || failed || readOnly}
          onClick={() => onApprove(currentStage)}
        >
          Approve
        </Button>
        <Button
          variant="secondary"
          size="sm"
          disabled={busy || readOnly}
          onClick={() => {
            onRetry(currentStage, correction.trim() || undefined)
            setCorrection("")
          }}
        >
          Retry this stage
        </Button>
      </div>
    </Panel>
  )
}

function Panel({ children }: { children: ReactNode }) {
  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        gap: "var(--space-3)",
        height: "100%",
        padding: "var(--space-4)",
        // Wireframe's d1 screen fills both the form panel and the chat
        // panel with the same #FAF7FF. --brand-faint is the closest token
        // match, see ChatColumn's matching panel.
        background: "var(--brand-faint)",
        border: "1px solid var(--border-default)",
        borderRadius: "var(--radius-md)",
        boxSizing: "border-box",
        minHeight: 0,
      }}
    >
      {children}
    </div>
  )
}
