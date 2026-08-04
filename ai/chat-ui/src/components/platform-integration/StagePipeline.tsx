import type { PipelineStage, StageStatus } from "./types"

// Mirrors mddoai-design-system/project/components/feedback/StatusPill.jsx's
// semantic color mapping (success/warning/danger/info), using the shared
// design-system tokens.
const STATUS_STYLES: Record<
  StageStatus,
  { bg: string; border: string; text: string }
> = {
  idle: {
    bg: "var(--surface-sunken)",
    border: "var(--border-subtle)",
    text: "var(--text-muted)",
  },
  active: {
    bg: "var(--info-100)",
    border: "var(--info-500)",
    text: "var(--info-500)",
  },
  reviewing: {
    bg: "var(--warning-100)",
    border: "var(--warning-500)",
    text: "#9a6800",
  },
  done: {
    bg: "var(--success-100)",
    border: "var(--success-500)",
    text: "var(--success-500)",
  },
  error: {
    bg: "var(--danger-100)",
    border: "var(--danger-500)",
    text: "var(--danger-500)",
  },
}

interface StagePipelineProps {
  stages: PipelineStage[]
}

export function StagePipeline({ stages }: StagePipelineProps) {
  return (
    <div
      className="flex flex-wrap items-center"
      style={{ gap: 8, padding: "16px 24px", borderBottom: "1px solid var(--border-subtle)" }}
    >
      {stages.map((stage, index) => {
        const style = STATUS_STYLES[stage.status]
        return (
          <div key={stage.key} className="flex items-center" style={{ gap: 8 }}>
            <span
              style={{
                minWidth: 96,
                textAlign: "center",
                padding: "7px 16px",
                borderRadius: "var(--radius-pill)",
                fontFamily: "var(--font-sans)",
                fontSize: 12.5,
                fontWeight: 600,
                background: style.bg,
                border: `1px solid ${style.border}`,
                color: style.text,
                whiteSpace: "nowrap",
              }}
            >
              {stage.label}
            </span>
            {index < stages.length - 1 && (
              <span style={{ color: "var(--text-faint)", fontSize: 14 }}>→</span>
            )}
          </div>
        )
      })}
    </div>
  )
}
