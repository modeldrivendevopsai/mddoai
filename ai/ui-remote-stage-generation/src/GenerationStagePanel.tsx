import type { ReactNode } from "react"
import { Button, CodeBlock, StageInfoNote } from "design-system"
import "design-system/integration.css"
import type { StagePanelProps } from "orchestrator-types"

// Generation's own stage panel, approve/retry when Generation is the live pending stage
// (onApprove/onRetry given), a read-only "back to current" view when it's a
// past stage being viewed via the Stepper (onBack given instead). Its own
// file, not a shared component parameterized by StageId: Generation's real
// backend output and prompt are free to diverge from the other five stages'
// own, independently, without touching them.
export function GenerationStagePanel({
  busy,
  latestResult,
  onApprove,
  onRetry,
  onBack,
  readOnly = false,
  stageDetail = null,
}: StagePanelProps) {
  const failed = latestResult?.type === "call_failed"
  const output = failed
    ? String(latestResult?.data?.error ?? "Stage failed.")
    : String(latestResult?.data?.output ?? (onBack ? "No output recorded for this stage yet." : ""))

  if (onBack) {
    return (
      <Panel>
        <div style={{ display: "flex", alignItems: "center", justifyContent: "space-between" }}>
          <h2 style={headingStyle}>Generation stage output</h2>
          <Button variant="ghost" size="sm" onClick={onBack}>
            ← Back to current
          </Button>
        </div>
        <StageInfoNote detail={stageDetail} />
        <CodeBlock code={output} title="generation output (read-only)" lang="generation" />
      </Panel>
    )
  }

  const hasResult = latestResult !== null

  return (
    <Panel>
      <h2 style={headingStyle}>Generation stage output</h2>
      <StageInfoNote detail={stageDetail} />

      {/* Matches Callout.jsx's real "danger" tone exactly: bg danger-100,
          border --danger-border (not the fully-saturated danger-500),
          radius-md, 14px/16px padding, see tokens.css's --danger-border
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

      <CodeBlock code={busy ? "Generating…" : hasResult ? output : "No output yet."} title="generation output" lang="generation" />

      {/* No correction field here, unlike every earlier stage: this stage
          actually runs the real ATL/Acceleo output against a real model
          instance now (see stages/generation/agent.py), it doesn't call an
          LLM of its own a free-text note could steer - there's no prompt
          left to curate. A wrong result here means the ATL/Acceleo that
          made it this far (already reviewed and approved at their own
          stages) needs a real fix at its own source, not a note appended
          here. "Retry this stage" still re-runs the exact same real
          execution, useful for a transient failure (e.g. execution-agent
          briefly unreachable). */}
      <div style={{ display: "flex", gap: "var(--space-2)" }}>
        <Button
          variant="primary"
          size="sm"
          disabled={busy || !hasResult || failed || readOnly}
          onClick={() => onApprove?.()}
        >
          Approve
        </Button>
        <Button variant="secondary" size="sm" disabled={busy || readOnly} onClick={() => onRetry?.()}>
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
