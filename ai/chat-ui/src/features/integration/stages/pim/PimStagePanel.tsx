import { useState } from "react"
import type { ReactNode } from "react"
import { Button } from "@/design-system"
import { CodeBlock } from "../../CodeBlock"
import "../../integration.css"
import type { StagePanelProps } from "../StagePanelProps"

// PIM's own stage panel — approve/retry when PIM is the live pending stage
// (onApprove/onRetry given), a read-only "back to current" view when it's a
// past stage being viewed via the Stepper (onBack given instead). Its own
// file, not a shared component parameterized by StageId: PIM's real output
// and prompt (see ai/orchestrator/stage_agents.py's pim_agent) are free to
// diverge from the other five stages without touching them.
export function PimStagePanel({ busy, latestResult, onApprove, onRetry, onBack, readOnly = false }: StagePanelProps) {
  const [correction, setCorrection] = useState("")
  const failed = latestResult?.type === "call_failed"
  const output = failed
    ? String(latestResult?.data?.error ?? "Stage failed.")
    : String(latestResult?.data?.output ?? (onBack ? "No output recorded for this stage yet." : ""))

  if (onBack) {
    return (
      <Panel>
        <div style={{ display: "flex", alignItems: "center", justifyContent: "space-between" }}>
          <h2 style={headingStyle}>PIM stage output</h2>
          <Button variant="ghost" size="sm" onClick={onBack}>
            ← Back to current
          </Button>
        </div>
        <CodeBlock code={output} title="pim output (read-only)" lang="pim" />
      </Panel>
    )
  }

  const hasResult = latestResult !== null

  return (
    <Panel>
      <h2 style={headingStyle}>PIM stage output</h2>

      {/* Matches Callout.jsx's real "danger" tone exactly: bg danger-100,
          border --danger-border (not the fully-saturated danger-500),
          radius-md, 14px/16px padding — see tokens.css's --danger-border
          for why that one's a token this port adds on top of the source. */}
      {failed && (
        <div
          style={{
            display: "flex",
            gap: 12,
            padding: "14px 16px",
            background: "var(--danger-100)",
            border: "1px solid var(--danger-border)",
            borderRadius: "var(--radius-md)",
            fontFamily: "var(--font-sans)",
          }}
        >
          <div style={{ fontWeight: 600, fontSize: 14, color: "var(--text-strong)" }}>Automated check: FAILED</div>
        </div>
      )}

      <CodeBlock code={busy ? "Generating…" : hasResult ? output : "No output yet."} title="pim output" lang="pim" />

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
        <p style={labelStyle}>Curate the helper prompt for this stage</p>
        <textarea
          className="orch-field"
          value={correction}
          onChange={(e) => setCorrection(e.target.value)}
          placeholder="Describe what should change"
          rows={2}
          disabled={readOnly}
          style={textareaStyle}
        />
      </div>

      <div style={{ display: "flex", gap: "var(--space-2)" }}>
        <Button
          variant="primary"
          size="sm"
          disabled={busy || !hasResult || failed || readOnly}
          onClick={() => onApprove?.()}
        >
          Approve
        </Button>
        <Button
          variant="secondary"
          size="sm"
          disabled={busy || readOnly}
          onClick={() => {
            onRetry?.(correction.trim() || undefined)
            setCorrection("")
          }}
        >
          Retry this stage
        </Button>
      </div>
    </Panel>
  )
}

const headingStyle = {
  fontFamily: "var(--font-display)",
  fontSize: "var(--text-md)",
  fontWeight: "var(--weight-bold)",
  color: "var(--text-strong)",
  margin: 0,
} as const

const labelStyle = {
  fontFamily: "var(--font-sans)",
  fontSize: "var(--text-xs)",
  fontWeight: "var(--weight-bold)",
  color: "var(--text-strong)",
  margin: "0 0 var(--space-1)",
} as const

const textareaStyle = {
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
} as const

function Panel({ children }: { children: ReactNode }) {
  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        gap: "var(--space-3)",
        height: "100%",
        padding: "var(--space-4)",
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
