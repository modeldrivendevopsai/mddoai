import type { CSSProperties } from "react"
import type { StageId } from "@/orchestrator/types"

// PIM has no real backend stage yet (ai/orchestrator's STAGES is
// docs/psm/atl/acceleo/generation), so it's rendered as a fixed,
// permanently "not yet implemented" node, never wired to real state.
const DISPLAY_STAGES: { id: StageId | "pim"; label: string }[] = [
  { id: "docs", label: "Docs" },
  { id: "pim", label: "PIM" },
  { id: "psm", label: "PSM" },
  { id: "atl", label: "ATL" },
  { id: "acceleo", label: "Acceleo" },
  { id: "generation", label: "Generation + Test" },
]

const REAL_STAGE_ORDER: StageId[] = ["docs", "psm", "atl", "acceleo", "generation"]

type NodeStatus = "pending" | "generating" | "reviewing" | "done" | "unavailable"

function statusFor(stageId: StageId | "pim", currentStage: StageId | null, busy: boolean): NodeStatus {
  if (stageId === "pim") return "unavailable"
  if (currentStage === null) return "done"
  const currentIndex = REAL_STAGE_ORDER.indexOf(currentStage)
  const thisIndex = REAL_STAGE_ORDER.indexOf(stageId)
  if (thisIndex < currentIndex) return "done"
  if (thisIndex === currentIndex) return busy ? "generating" : "reviewing"
  return "pending"
}

// Matches the real wireframe's own stepper node colors (mddoai-ui-wireframe-v3.html,
// e.g. frame "c4: ATL stage generating" / "c5: ATL check failed"): done=green,
// generating=blue, reviewing=yellow, pending=gray. Restyled onto the design
// system's own semantic tokens rather than the wireframe's literal hex values.
const NODE_STYLES: Record<NodeStatus, CSSProperties> = {
  pending: { background: "var(--surface-sunken)", color: "var(--text-muted)", border: "1px solid var(--border-default)" },
  generating: { background: "var(--info-100)", color: "var(--text-strong)", border: "1px solid var(--info-500)" },
  reviewing: { background: "var(--warning-100)", color: "var(--text-strong)", border: "1px solid var(--warning-500)" },
  done: { background: "var(--success-100)", color: "var(--text-strong)", border: "1px solid var(--success-500)" },
  unavailable: { background: "var(--surface-sunken)", color: "var(--text-faint)", border: "1px dashed var(--border-subtle)" },
}

function labelFor(label: string, status: NodeStatus): string {
  if (status === "generating") return `${label} : generating…`
  if (status === "reviewing") return `${label} : reviewing`
  return label
}

export function Stepper({ currentStage, busy }: { currentStage: StageId | null; busy: boolean }) {
  return (
    <ol
      style={{
        display: "flex",
        flexWrap: "wrap",
        alignItems: "center",
        justifyContent: "center",
        gap: "var(--space-2)",
        listStyle: "none",
        margin: 0,
        padding: 0,
      }}
    >
      {DISPLAY_STAGES.map((stage, i) => {
        const status = statusFor(stage.id, currentStage, busy)
        return (
          <li key={stage.id} style={{ display: "flex", alignItems: "center", gap: "var(--space-2)" }}>
            <StepperNode label={labelFor(stage.label, status)} title={stage.label} status={status} />
            {i < DISPLAY_STAGES.length - 1 && (
              <span style={{ color: "var(--text-muted)", fontSize: "var(--text-sm)" }}>→</span>
            )}
          </li>
        )
      })}
    </ol>
  )
}

function StepperNode({ label, title, status }: { label: string; title: string; status: NodeStatus }) {
  return (
    <div
      title={status === "unavailable" ? `${title} (not yet implemented)` : title}
      style={{
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
        minWidth: 90,
        padding: "var(--space-1) var(--space-3)",
        borderRadius: "var(--radius-pill)",
        fontFamily: "var(--font-sans)",
        fontSize: "var(--text-xs)",
        fontWeight: "var(--weight-medium)",
        whiteSpace: "nowrap",
        ...NODE_STYLES[status],
      }}
    >
      {label}
    </div>
  )
}
