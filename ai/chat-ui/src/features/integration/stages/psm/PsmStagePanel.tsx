import { useState } from "react"
import type { ReactNode } from "react"
import { Button, StatusPill, Tabs } from "@/design-system"
import type { TabItem } from "@/design-system"
import { CodeBlock } from "../../CodeBlock"
import { constraintsForStage } from "../../stageEvents"
import "../../integration.css"
import type { StagePanelProps } from "../StagePanelProps"

const PROMPT_TABS: TabItem[] = [
  { id: "pim_ecore", label: "PIM ecore" },
  { id: "psm_docs", label: "PSM docs" },
  { id: "psm_example", label: "PSM example" },
  { id: "constraints", label: "Constraints" },
]

interface PsmGap {
  target: string
  description: string
}

// PSM's own stage panel — approve/retry when PSM is the live pending stage
// (onApprove/onRetry given), a read-only "back to current" view when it's a
// past stage being viewed via the Stepper (onBack given instead). Its own
// file, not a shared component parameterized by StageId: PSM's real backend
// output and prompt are free to diverge from the other five stages' own,
// independently, without touching them.
//
// Unlike every other stage, psm's real backend (see
// integration_runner/stages/psm/agent.py) returns structured extras
// alongside the plain output string: the exact 4-part prompt actually used
// (PIM ecore / PSM docs / PSM example / Constraints), plus either a
// validation result (Generation Agent) or gap suggestions (Knowledge Agent).
// Shown here via the shared Tabs component so a human can see exactly what
// was fed into generation, not just what came out.
export function PsmStagePanel({ busy, latestResult, events, onApprove, onRetry, onBack, readOnly = false }: StagePanelProps) {
  const [correction, setCorrection] = useState("")
  const [activeTab, setActiveTab] = useState<string>("pim_ecore")

  const failed = latestResult?.type === "call_failed"
  const data = latestResult?.data
  const output = failed
    ? String(data?.error ?? "Stage failed.")
    : String(data?.output ?? (onBack ? "No output recorded for this stage yet." : ""))

  const prompt = (data?.prompt ?? null) as Record<string, string> | null
  const mode = typeof data?.mode === "string" ? data.mode : null
  const validation = (data?.validation ?? null) as { valid?: boolean } | null
  const rounds = typeof data?.rounds === "number" ? data.rounds : null
  const gaps: PsmGap[] = Array.isArray(data?.gaps) ? (data.gaps as PsmGap[]) : []
  const priorConstraints = constraintsForStage(events, "psm")
  // A generation result can be a real, HTTP-200 success (mode: "generation")
  // that still failed EMF validation after exhausting every regenerate round
  // — the request itself didn't fail (failed/call_failed stays false), so
  // this needs its own check, not just folding into `failed` below.
  const emfInvalid = mode === "generation" && validation?.valid === false

  const promptViewer = prompt && (
    <div style={{ display: "flex", flexDirection: "column", gap: "var(--space-2)" }}>
      <Tabs items={PROMPT_TABS} activeId={activeTab} onChange={setActiveTab} />
      {activeTab === "constraints" ? (
        <div style={{ display: "flex", flexDirection: "column", gap: "var(--space-2)" }}>
          <CodeBlock
            code={prompt.constraints || "(none — this result didn't need any correction)"}
            title="constraints actually used in this prompt"
            lang="text"
          />
          {priorConstraints.length > 0 && (
            <>
              <p style={labelStyle}>Correction history for this stage</p>
              <ul style={constraintsListStyle}>
                {priorConstraints.map((c, i) => (
                  <li key={i}>{c}</li>
                ))}
              </ul>
            </>
          )}
          {!onBack && (
            <div>
              <p style={labelStyle}>Add a new constraint and retry</p>
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
          )}
        </div>
      ) : (
        <CodeBlock
          code={prompt[activeTab] ?? ""}
          title={PROMPT_TABS.find((t) => t.id === activeTab)?.label}
          lang="text"
        />
      )}
    </div>
  )

  const statusPills = (mode || validation || gaps.length > 0) && (
    <div style={{ display: "flex", gap: "var(--space-2)", flexWrap: "wrap" }}>
      {mode === "knowledge" && <StatusPill variant="info">Existing metamodel — maintenance check</StatusPill>}
      {mode === "generation" && validation && (
        <StatusPill variant={validation.valid ? "success" : "danger"}>
          {validation.valid ? "Loads in EMF" : "Failed EMF validation"}
        </StatusPill>
      )}
      {mode === "generation" && rounds !== null && rounds > 1 && (
        <StatusPill variant="warning">Self-corrected over {rounds} rounds</StatusPill>
      )}
      {gaps.length > 0 && <StatusPill variant="warning">{gaps.length} gap(s) found</StatusPill>}
    </div>
  )

  const gapsPanel = gaps.length > 0 && (
    <div style={{ display: "flex", flexDirection: "column", gap: "var(--space-2)" }}>
      {gaps.map((gap, i) => (
        <div key={i} style={gapStyle}>
          <div style={{ fontWeight: 600, fontSize: 12, color: "var(--text-strong)" }}>
            {gap.target}
          </div>
          <div style={{ fontSize: 11.5, color: "var(--text-body)" }}>{gap.description}</div>
        </div>
      ))}
      <p style={{ fontSize: 11, color: "var(--text-muted)", margin: 0 }}>
        Informational only — doesn't block approval, no automatic .ecore edit.
      </p>
    </div>
  )

  if (onBack) {
    return (
      <Panel>
        <div style={{ display: "flex", alignItems: "center", justifyContent: "space-between" }}>
          <h2 style={headingStyle}>PSM stage output</h2>
          <Button variant="ghost" size="sm" onClick={onBack}>
            ← Back to current
          </Button>
        </div>
        {statusPills}
        <CodeBlock code={output} title="psm output (read-only)" lang="psm" />
        {gapsPanel}
        {promptViewer}
      </Panel>
    )
  }

  const hasResult = latestResult !== null

  return (
    <Panel>
      <h2 style={headingStyle}>PSM stage output</h2>

      {/* Matches Callout.jsx's real "danger" tone exactly: bg danger-100,
          border --danger-border (not the fully-saturated danger-500),
          radius-md, 14px/16px padding — see tokens.css's --danger-border
          for why that one's a token this port adds on top of the source. */}
      {(failed || emfInvalid) && (
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
          <div style={{ fontWeight: 600, fontSize: 14, color: "var(--text-strong)" }}>
            {failed ? "Automated check: FAILED" : "Automated check: FAILED (doesn't load in EMF)"}
          </div>
        </div>
      )}

      {statusPills}

      <CodeBlock code={busy ? "Generating…" : hasResult ? output : "No output yet."} title="psm output" lang="psm" />

      {gapsPanel}

      {promptViewer ?? (
        <div>
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
      )}

      <div style={{ display: "flex", gap: "var(--space-2)" }}>
        <Button
          variant="primary"
          size="sm"
          disabled={busy || !hasResult || failed || emfInvalid || readOnly}
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

const constraintsListStyle = {
  margin: 0,
  paddingLeft: "var(--space-4)",
  fontFamily: "var(--font-sans)",
  fontSize: "var(--text-xs)",
  color: "var(--text-body)",
  display: "flex",
  flexDirection: "column",
  gap: "var(--space-1)",
} as const

const gapStyle = {
  display: "flex",
  flexDirection: "column",
  gap: 2,
  padding: "10px 12px",
  background: "var(--warning-100)",
  border: "1px solid var(--border-default)",
  borderRadius: "var(--radius-md)",
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
        overflow: "auto",
      }}
    >
      {children}
    </div>
  )
}
