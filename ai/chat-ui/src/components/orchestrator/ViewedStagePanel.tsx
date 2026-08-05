import type { OrchestratorEvent, StageId } from "@/types/orchestrator"
import { Button } from "@/design-system"
import { CodeBlock } from "./CodeBlock"

interface ViewedStagePanelProps {
  stage: StageId
  result: OrchestratorEvent | null
  onBack: () => void
}

// Read-only view of a past (non-current) stage's real result, shown when a
// Stepper node is clicked. Deliberately has no Approve/Retry, those only
// make sense for the actual pending stage, see StageOutputPanel for that.
// A standalone, exported component like every other stage-rendering piece
// here (StageOutputPanel, Stepper, CodeBlock) — takes only plain props, no
// hidden dependency on usePipeline or OrchestratorScreen, so it's reusable
// as-is by any parent that supplies its own stage/result/onBack.
export function ViewedStagePanel({ stage, result, onBack }: ViewedStagePanelProps) {
  const failed = result?.type === "call_failed"
  const output = failed
    ? String(result?.data?.error ?? "Stage failed.")
    : String(result?.data?.output ?? "No output recorded for this stage yet.")
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
      <div style={{ display: "flex", alignItems: "center", justifyContent: "space-between" }}>
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
          {stage} stage output
        </h2>
        <Button variant="ghost" size="sm" onClick={onBack}>
          ← Back to current
        </Button>
      </div>
      <CodeBlock code={output} title={`${stage} output (read-only)`} lang={stage} />
    </div>
  )
}
