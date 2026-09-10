import { useState } from "react"
import type { ReactNode } from "react"
import { AttemptsBrowser, Button, CodeBlock, PromptBuilder, StageInfoNote, StatusPill } from "design-system"
import type { PromptBuilderManifest, PromptConfig } from "design-system"
import "design-system/integration.css"
import type { StagePanelProps } from "orchestrator-types"
import { PromoteConstraintsAction } from "./PromoteConstraintsAction"
import { constraintsForStage } from "./stageEvents"

// The manifest for atl's own real, config-driven prompt (see
// atl_agent/prompts/generation/default.default.json for the real
// attachment content this loads) - same PromptBuilder component psm and
// acceleo reuse, just this stage's own manifest and its own backend prompt
// module behind it.
const GENERATION_MANIFEST: PromptBuilderManifest = {
  name: "generation",
  label: "ATL generation prompt",
  attachmentTypes: ["text", "file", "context"],
  contextKeyOptions: [
    { key: "pim_ecore", label: "PIM metamodel" },
    { key: "psm_ecore", label: "Target platform PSM metamodel" },
  ],
  supportsPresets: true,
}

// ATL's own stage panel — approve/retry when ATL is the live pending stage
// (onApprove/onRetry given), a read-only "back to current" view when it's a
// past stage being viewed via the Stepper (onBack given instead). Its own
// file, not a shared component parameterized by StageId: ATL's real backend
// output and prompt are free to diverge from the other five stages' own,
// independently, without touching them.
//
// Unlike psm, atl has no generation-vs-knowledge-mode duality: atl_agent's
// real backend (see integration_runner/stages/atl/agent.py) always
// generates fresh, from this run's own real PIM and target-platform PSM
// artifacts, and returns just the exact prompt used plus a validation
// result alongside the plain output string.
export function AtlStagePanel({
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
  onListPresets,
  onPreviewPromptConfig,
  onListAvailableFiles,
  onUploadAttachmentFile,
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

  const failed = latestResult?.type === "call_failed"
  const data = latestResult?.data
  const output = failed
    ? String(data?.error ?? "Stage failed.")
    : String(data?.output ?? (onBack ? "No output recorded for this stage yet." : ""))

  const prompt = (data?.prompt ?? null) as Record<string, string> | null
  const validation = (data?.validation ?? null) as { valid?: boolean } | null
  const rounds = typeof data?.rounds === "number" ? data.rounds : null
  const priorConstraints = constraintsForStage(events, "atl")
  // A real, HTTP-200 result can still have failed validator-agent's own
  // check after exhausting every regenerate round (the request itself
  // didn't fail, `failed` stays false) - this needs its own check, not
  // just folding into `failed` below.
  const invalid = validation?.valid === false
  const canPromote = validation?.valid === true && Boolean(onPromoteConstraints)

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
        manifest={GENERATION_MANIFEST}
        readOnly={readOnly}
        callbacks={{
          onLoad: (preset) => onLoadPromptConfig(GENERATION_MANIFEST.name, preset),
          onSave: (preset, config) => onSavePromptConfig(GENERATION_MANIFEST.name, preset, config as PromptConfig),
          onListPresets: onListPresets ? () => onListPresets(GENERATION_MANIFEST.name) : undefined,
          onPreview: (preset) => onPreviewPromptConfig(GENERATION_MANIFEST.name, preset),
          onListAvailableFiles,
          onUploadFile: onUploadAttachmentFile,
          onLoadHistory: (preset) => onLoadPromptHistory(GENERATION_MANIFEST.name, preset),
          onDiffVersions: (preset, a, b) => onDiffPromptVersions(GENERATION_MANIFEST.name, preset, a, b),
          onRestoreVersion: (preset, version) => onRestorePromptVersion(GENERATION_MANIFEST.name, preset, version),
          onRevertToDefault: (preset) => onRevertPromptConfig(GENERATION_MANIFEST.name, preset),
          onPromoteToDefault: (preset) => onPromoteConfigToDefault(GENERATION_MANIFEST.name, preset),
          onCheckReferences: (preset) => onCheckPromptReferences(GENERATION_MANIFEST.name, preset),
          onAddLearnedConstraints: (preset, constraints) => onAddLearnedConstraints(GENERATION_MANIFEST.name, preset, constraints),
          onRemoveLearnedConstraint: (preset, constraint) => onRemoveLearnedConstraint(GENERATION_MANIFEST.name, preset, constraint),
        }}
      />
    )

  const attemptsBrowser = runId && onLoadManifest && onLoadAttempt && (
    <AttemptsBrowser
      runId={runId}
      stage="atl"
      onLoadManifest={onLoadManifest}
      onLoadAttempt={onLoadAttempt}
      onRestoreConfigFromAttempt={
        onRestorePromptVersion
          ? (version) => onRestorePromptVersion(GENERATION_MANIFEST.name, "default", version).then(() => undefined)
          : undefined
      }
    />
  )

  const promoteConstraints = onPromoteConstraints && (
    <PromoteConstraintsAction
      initialConstraintsBlock={prompt?.constraints ?? ""}
      onPromote={onPromoteConstraints}
    />
  )

  const statusPills = (validation || (rounds !== null && rounds > 1)) && (
    <div style={{ display: "flex", gap: "var(--space-2)", flexWrap: "wrap" }}>
      {validation && (
        <StatusPill variant={validation.valid ? "success" : "danger"}>
          {validation.valid ? "Compiles" : "Failed ATL compilation"}
        </StatusPill>
      )}
      {rounds !== null && rounds > 1 && <StatusPill variant="warning">Self-corrected over {rounds} rounds</StatusPill>}
    </div>
  )

  const priorConstraintsPanel = priorConstraints.length > 0 && (
    <div>
      <p style={labelStyle}>Correction history for this stage</p>
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
          <h2 style={headingStyle}>ATL stage output</h2>
          <Button variant="ghost" size="sm" onClick={onBack}>
            ← Back to current
          </Button>
        </div>
        <StageInfoNote detail={stageDetail} />
        {statusPills}
        <CodeBlock code={output} title="atl output (read-only)" lang="atl" />
        {attemptsBrowser}
        {promptBuilder}
      </Panel>
    )
  }

  const hasResult = latestResult !== null

  return (
    <Panel>
      <h2 style={headingStyle}>ATL stage output</h2>
      <StageInfoNote detail={stageDetail} />

      {/* Matches Callout.jsx's real "danger" tone exactly: bg danger-100,
          border --danger-border (not the fully-saturated danger-500),
          radius-md, 14px/16px padding — see tokens.css's --danger-border
          for why that one's a token this port adds on top of the source. */}
      {(failed || invalid) && (
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
            {failed ? "Automated check: FAILED" : "Automated check: FAILED (doesn't compile)"}
          </div>
        </div>
      )}

      {statusPills}

      {(hasResult || busy) && <CodeBlock code={busy ? "Generating…" : output} title="atl output" lang="atl" />}

      {!hasResult ? (
        // The real "initial screen" before generation ever starts: arriving
        // at atl advances the pipeline but deliberately does not auto-run
        // it (see pipeline.py's own _REQUIRES_MANUAL_START) - a human
        // reviews or edits the prompt here first, then Generate below fires
        // the real first attempt.
        <>
          {promptBuilder ?? (
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
        </>
      ) : (
        <>
          {canPromote && promoteConstraints}
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
          disabled={busy || !hasResult || failed || invalid || readOnly}
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
