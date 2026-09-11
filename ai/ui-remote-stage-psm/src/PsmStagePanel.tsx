import { useEffect, useState } from "react"
import type { ReactNode } from "react"
import { AttemptsBrowser, Button, CodeBlock, PromptBuilder, StageInfoNote, StatusPill } from "design-system"
import type { PromptBuilderManifest, PromptConfig } from "design-system"
import "design-system/integration.css"
import type { StagePanelProps } from "orchestrator-types"
import { constraintsForStage, platformDescriptionFromEvents } from "./stageEvents"

interface PsmGap {
  target: string
  description: string
}

// The manifest for psm's own real, config-driven prompt: the same
// PromptBuilder component atl/acceleo also reuse, each with its own
// manifest and its own backend prompt module behind it - this file's only
// real job is this wiring, no prompt-builder logic of its own beyond it.
const GENERATION_MANIFEST: PromptBuilderManifest = {
  name: "generation",
  label: "PSM generation prompt",
  attachmentTypes: ["text", "file", "context"],
  contextKeyOptions: [
    { key: "pim_ecore", label: "PIM artifact" },
    { key: "psm_docs", label: "Target platform documentation" },
  ],
}

const COMPARISON_MANIFEST: PromptBuilderManifest = {
  name: "comparison",
  label: "PSM comparison prompt",
  attachmentTypes: ["text", "context"],
  contextKeyOptions: [
    { key: "psm_metamodel", label: "Existing PSM metamodel" },
    { key: "serialized_docs", label: "Serialized platform documentation" },
  ],
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
// alongside the plain output string: the exact prompt actually used, plus
// either a validation result (Generation Agent) or gap suggestions
// (Knowledge Agent). The prompt itself is now shown via the full
// PromptBuilder (editable, version history) rather than a
// read-only Tabs viewer, and every real past attempt is browsable via
// AttemptsBrowser, not just the latest one.
export function PsmStagePanel({
  busy,
  latestResult,
  events,
  runId,
  onApprove,
  onRetry,
  onBack,
  readOnly = false,
  stageDetail = null,
  onLoadPromptConfig,
  onSavePromptConfig,
  onPreviewPromptConfig,
  onListAvailableFiles,
  onUploadAttachmentFile,
  onResolvePsmMode,
  onLoadPromptHistory,
  onDiffPromptVersions,
  onRestorePromptVersion,
  onRevertPromptConfig,
  onPromoteConfigToDefault,
  onCheckPromptReferences,
  onAddLearnedConstraints,
  onRemoveLearnedConstraint,
  onPromoteConstraints,
  onLoadManifest,
  onLoadAttempt,
}: StagePanelProps) {
  const [correction, setCorrection] = useState("")
  // Which real mode (generation vs. knowledge) THIS platform will actually
  // route to, resolved ahead of time (psm_flow.run()'s own real decision,
  // exposed read-only - see resolvePsmMode's own docstring) rather than
  // guessed - shown only before a real result exists; once one does, the
  // real result's own mode already renders via statusPills below, more
  // authoritative than a prediction.
  const [resolvedMode, setResolvedMode] = useState<{ mode: string; metamodel_path: string | null } | null>(null)
  const platformDescription = platformDescriptionFromEvents(events)

  useEffect(() => {
    if (!onResolvePsmMode || !platformDescription) return
    let cancelled = false
    onResolvePsmMode(platformDescription)
      .then((result) => {
        if (!cancelled) setResolvedMode(result)
      })
      .catch(() => undefined)
    return () => {
      cancelled = true
    }
  }, [onResolvePsmMode, platformDescription])

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
  const canPromote = mode === "generation" && validation?.valid === true && Boolean(onPromoteConstraints)

  // Which real config this result actually used - the knowledge branch
  // always edits "comparison", the generation branch always edits
  // "generation". Defaults to the generation manifest before any result
  // exists yet, since editing that prompt ahead of a brand-new platform's
  // very first attempt is the real point of this screen - a platform that
  // turns out to already have a metamodel just silently routes to
  // comparison mode instead once Generate is clicked.
  const activeManifest = mode === "knowledge" ? COMPARISON_MANIFEST : GENERATION_MANIFEST

  const promptBuilder = onLoadPromptConfig &&
    onSavePromptConfig &&
    onPreviewPromptConfig &&
    onLoadPromptHistory &&
    onDiffPromptVersions &&
    onRestorePromptVersion &&
    onRevertPromptConfig &&
    onPromoteConfigToDefault &&
    onCheckPromptReferences &&
    onAddLearnedConstraints &&
    onRemoveLearnedConstraint && (
      <PromptBuilder
        manifest={activeManifest}
        readOnly={readOnly}
        callbacks={{
          onLoad: () => onLoadPromptConfig(activeManifest.name),
          onSave: (config) => onSavePromptConfig(activeManifest.name, config as PromptConfig),
          onPreview: () => onPreviewPromptConfig(activeManifest.name),
          onListAvailableFiles,
          onUploadFile: onUploadAttachmentFile,
          onLoadHistory: () => onLoadPromptHistory(activeManifest.name),
          onDiffVersions: (a, b) => onDiffPromptVersions(activeManifest.name, a, b),
          onRestoreVersion: (version) => onRestorePromptVersion(activeManifest.name, version),
          onRevertToDefault: () => onRevertPromptConfig(activeManifest.name),
          onPromoteToDefault: () => onPromoteConfigToDefault(activeManifest.name),
          onCheckReferences: () => onCheckPromptReferences(activeManifest.name),
          onAddLearnedConstraints: (constraints) => onAddLearnedConstraints(activeManifest.name, constraints),
          onRemoveLearnedConstraint: (constraint) => onRemoveLearnedConstraint(activeManifest.name, constraint),
        }}
        promote={
          canPromote && onPromoteConstraints
            ? { initialBlock: prompt?.constraints ?? "", onPromote: onPromoteConstraints }
            : undefined
        }
      />
    )

  const attemptsBrowser = runId && onLoadManifest && onLoadAttempt && (
    <AttemptsBrowser
      runId={runId}
      stage="psm"
      onLoadManifest={onLoadManifest}
      onLoadAttempt={onLoadAttempt}
      onRestoreConfigFromAttempt={
        onRestorePromptVersion
          ? (version) => onRestorePromptVersion(activeManifest.name, version).then(() => undefined)
          : undefined
      }
    />
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

  // Real, resolved-ahead-of-time indicator of which mode Generate will
  // actually take, shown only before a real result exists (once one does,
  // statusPills above already shows the real, authoritative mode instead).
  const modeChip = latestResult === null && resolvedMode && (
    <StatusPill
      variant={resolvedMode.mode === "knowledge" ? "info" : "success"}
      title={resolvedMode.metamodel_path ?? undefined}
    >
      {resolvedMode.mode === "knowledge"
        ? `Existing platform — ${resolvedMode.metamodel_path?.split("/").pop() ?? "real metamodel found"}`
        : "New platform — will generate a metamodel"}
    </StatusPill>
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

  const priorConstraintsPanel = priorConstraints.length > 0 && (
    <div>
      <p style={labelStyle}>Corrections tried during this run (not saved permanently)</p>
      <ul style={constraintsListStyle}>
        {priorConstraints.map((c, i) => (
          <li key={i}>{c}</li>
        ))}
      </ul>
    </div>
  )

  if (onBack) {
    return (
      <Panel>
        <div style={{ display: "flex", alignItems: "center", justifyContent: "space-between" }}>
          <h2 style={headingStyle}>PSM stage output</h2>
          <Button variant="ghost" size="sm" onClick={onBack}>
            ← Back to latest stage
          </Button>
        </div>
        <StageInfoNote detail={stageDetail} />
        {statusPills}
        <CodeBlock code={output} title="psm output (read-only)" lang="psm" />
        {gapsPanel}
        {attemptsBrowser}
        {promptBuilder}
      </Panel>
    )
  }

  const hasResult = latestResult !== null

  return (
    <Panel>
      <h2 style={headingStyle}>PSM stage output</h2>
      <StageInfoNote detail={stageDetail} />

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
      {modeChip && <div style={{ display: "flex" }}>{modeChip}</div>}

      {(hasResult || busy) && <CodeBlock code={busy ? "Generating…" : output} title="psm output" lang="psm" />}

      {gapsPanel}

      {!hasResult ? (
        // The real "initial screen" before generation ever starts: arriving
        // at psm advances the pipeline but deliberately does not auto-run
        // it (see pipeline.py's own _REQUIRES_MANUAL_START) - a human
        // reviews or edits the prompt here first, then Generate below fires
        // the real first attempt. No empty "psm output" box above either -
        // there's nothing to show yet, and it only added scroll length.
        <>
          {promptBuilder ?? (
            <div>
              <p style={labelStyle}>Add a correction</p>
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
        </>
      ) : (
        <>
          {attemptsBrowser}
          {promptBuilder}
          {priorConstraintsPanel}
          {!onBack && (
            <div>
              <p style={labelStyle}>Add a one-off correction and retry</p>
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
        </>
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
          {hasResult ? "Retry this stage" : "Generate"}
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
