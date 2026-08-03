import type { CSSProperties } from "react"
import { STAGES } from "@/orchestrator/types"
import type { StageId } from "@/orchestrator/types"

// Drives entirely off the backend's own real STAGES list, same as
// types.ts. PIM used to be a fixed, permanently "not yet implemented" node
// hardcoded here (ai/orchestrator's STAGES had no "pim" entry); now that
// pim_agent is a real stage agent like psm/atl/acceleo, it needs no special
// case, it's just another entry in STAGES.
const LABELS: Record<StageId, string> = {
  docs: "Docs",
  pim: "PIM",
  psm: "PSM",
  atl: "ATL",
  acceleo: "Acceleo",
  generation: "Generation + Test",
}

type NodeStatus = "pending" | "generating" | "reviewing" | "done"

function statusFor(stageId: StageId, currentStage: StageId | null, busy: boolean): NodeStatus {
  if (currentStage === null) return "done"
  const currentIndex = STAGES.indexOf(currentStage)
  const thisIndex = STAGES.indexOf(stageId)
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
}

function labelFor(label: string, status: NodeStatus): string {
  if (status === "generating") return `${label} : generating…`
  if (status === "reviewing") return `${label} : reviewing`
  return label
}

interface StepperProps {
  currentStage: StageId | null
  busy: boolean
  // Which stage (if any) the human has clicked to inspect, separate from
  // currentStage (the real pending stage). Only relevant when onSelectStage
  // is given.
  selectedStage?: StageId | null
  // Omit to render plain, non-interactive nodes (e.g. before a run has
  // started, when there's no real per-stage data yet to show). A stage with
  // no data of its own ("pending") never becomes clickable even when this
  // is given, there's nothing real to inspect.
  onSelectStage?: (stageId: StageId) => void
}

export function Stepper({ currentStage, busy, selectedStage = null, onSelectStage }: StepperProps) {
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
      {STAGES.map((stageId, i) => {
        const status = statusFor(stageId, currentStage, busy)
        const clickable = Boolean(onSelectStage) && status !== "pending"
        return (
          <li key={stageId} style={{ display: "flex", alignItems: "center", gap: "var(--space-2)" }}>
            <StepperNode
              label={labelFor(LABELS[stageId], status)}
              title={LABELS[stageId]}
              status={status}
              selected={stageId === selectedStage}
              onClick={clickable ? () => onSelectStage?.(stageId) : undefined}
            />
            {i < STAGES.length - 1 && (
              <span style={{ color: "var(--text-muted)", fontSize: "var(--text-sm)" }}>→</span>
            )}
          </li>
        )
      })}
    </ol>
  )
}

function StepperNode({
  label,
  title,
  status,
  selected,
  onClick,
}: {
  label: string
  title: string
  status: NodeStatus
  selected: boolean
  onClick?: () => void
}) {
  const style: CSSProperties = {
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
    ...(onClick ? { cursor: "pointer" } : {}),
    ...(selected ? { boxShadow: "var(--ring-brand)" } : {}),
  }

  if (!onClick) {
    return (
      <div title={title} style={style}>
        {label}
      </div>
    )
  }

  return (
    <button
      type="button"
      title={`View ${title}'s real result`}
      onClick={onClick}
      style={{ ...style, border: style.border ?? "none", font: "inherit" }}
    >
      {label}
    </button>
  )
}
