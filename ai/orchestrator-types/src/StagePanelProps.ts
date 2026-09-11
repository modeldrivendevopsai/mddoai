import type {
  AttemptDetail,
  BrokenReference,
  ManifestEntry,
  OrchestratorEvent,
  PresetMetadata,
  PromptConfig,
  PromptDiff,
  PromptPreview,
  StageDetail,
} from "./orchestrator"

// Shared prop contract every per-stage panel (PsmStagePanel, AtlStagePanel,
// etc.) implements — a type-only contract, not a shared component. Each
// stage panel decides for itself, from these same props, whether it's
// rendering as the live pending stage (onApprove/onRetry given) or a past
// stage being viewed read-only via the Stepper (onBack given instead).
export interface StagePanelProps {
  busy: boolean
  latestResult: OrchestratorEvent | null
  // This run's full event log. Most panels don't need it (latestResult
  // already has everything they show) — PsmStagePanel is the one that does,
  // to list every constraint_added event recorded for "psm" alongside the
  // prompt viewer.
  events: OrchestratorEvent[]
  // The real run currently being shown (live or a past one via the
  // Stepper) — see EventsResponse's own run_id. AttemptsBrowser needs this
  // to know which run's own on-disk attempt tree to read.
  runId: string | null
  // Present only when this stage is the live pending one.
  onApprove?: () => void
  onRetry?: (correction?: string) => void
  // Present only when viewing this stage historically (past, non-current).
  onBack?: () => void
  readOnly?: boolean
  // This stage's real, plain-language shape (input/output/real), sourced
  // from the backend's own real stage metadata (see GET /stages) rather
  // than hardcoded per panel. Null while stage metadata hasn't loaded yet.
  stageDetail?: StageDetail | null

  // --- Modular prompt builder (design-system's PromptBuilder/
  // AttemptsBrowser) — all optional, matching onBack?'s own precedent: a
  // stage panel with no real, editable prompt config yet (generation today)
  // simply never calls these. psm, atl, and acceleo all reuse this same
  // set, each with its own manifest and its own backend prompt module
  // behind it, rather than each stage threading its own separate contract.
  onLoadPromptConfig?: (name: string, preset: string) => Promise<PromptConfig>
  onSavePromptConfig?: (name: string, preset: string, config: PromptConfig) => Promise<PromptConfig>
  onListPresets?: (name: string) => Promise<PresetMetadata[]>
  onPreviewPromptConfig?: (name: string, preset: string) => Promise<PromptPreview>
  onListAvailableFiles?: () => Promise<string[]>
  // Real backend upload (routes/uploads.py) - a dropped OS file is saved
  // for real and becomes a real "file" attachment referencing it, not a
  // client-side-only text copy.
  onUploadAttachmentFile?: (file: File) => Promise<string>
  // psm_flow.run()'s own real routing decision (generation vs. knowledge
  // mode) for a given platform description, exposed read-only so a panel
  // can show which one a real run would actually take before it happens.
  onResolvePsmMode?: (platformDescription: string) => Promise<{ mode: string; metamodel_path: string | null }>
  onLoadPromptHistory?: (name: string, preset: string) => Promise<string[]>
  onDiffPromptVersions?: (name: string, preset: string, versionA: string, versionB: string) => Promise<PromptDiff>
  onRestorePromptVersion?: (name: string, preset: string, version: string) => Promise<PromptConfig>
  onRevertPromptConfig?: (name: string, preset: string) => Promise<PromptConfig>
  onPromoteConfigToDefault?: (name: string, preset: string) => Promise<PromptConfig>
  onCheckPromptReferences?: (name: string, preset: string) => Promise<BrokenReference[]>
  onAddLearnedConstraints?: (name: string, preset: string, constraints: string[]) => Promise<PromptConfig>
  onRemoveLearnedConstraint?: (name: string, preset: string, constraint: string) => Promise<PromptConfig>
  // Run-aware (see stages/psm/actions.py's own promote_constraints): no
  // name/preset here, the backend infers both from the current run's own
  // latest, real, successfully-validated result.
  onPromoteConstraints?: (constraints: string[]) => Promise<PromptConfig>

  // --- Attempts browser (design-system's AttemptsBrowser) --------------
  onLoadManifest?: (runId: string) => Promise<ManifestEntry[]>
  onLoadAttempt?: (runId: string, stage: string, attempt: string) => Promise<AttemptDetail>
}
