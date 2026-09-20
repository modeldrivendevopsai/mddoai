import type {
  AttemptDetail,
  BrokenReference,
  LearnedConstraintsUpdate,
  ManifestEntry,
  OrchestratorEvent,
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
  onLoadPromptConfig?: (name: string) => Promise<PromptConfig>
  // `options.keepalive`, when true, asks the underlying request to outlive
  // this page - set only by design-system's own PromptBuilder, for the one
  // save it fires from an unload/unmount flush, never an ordinary edit.
  onSavePromptConfig?: (name: string, config: PromptConfig, options?: { keepalive?: boolean }) => Promise<PromptConfig>
  // `config` is the caller's own current, unsaved draft - resolved exactly
  // as given, not re-loaded from the last saved version on disk, so a
  // preview always reflects what's actually on screen right now.
  onPreviewPromptConfig?: (name: string, config: PromptConfig) => Promise<PromptPreview>
  onListAvailableFiles?: () => Promise<string[]>
  // Real backend upload (routes/uploads.py) - a dropped OS file is saved
  // for real and becomes a real "file" attachment referencing it, not a
  // client-side-only text copy.
  onUploadAttachmentFile?: (file: File) => Promise<string>
  // psm_flow.run()'s own real routing decision (generation vs. knowledge
  // mode) for a given platform description, exposed read-only so a panel
  // can show which one a real run would actually take before it happens.
  onResolvePsmMode?: (platformDescription: string) => Promise<{ mode: string; metamodel_path: string | null }>
  onLoadPromptHistory?: (name: string) => Promise<string[]>
  onDiffPromptVersions?: (name: string, versionA: string, versionB: string) => Promise<PromptDiff>
  // "Revert to default" is not a separate callback: the shipped default is
  // just the oldest entry in the same history onLoadPromptHistory returns,
  // restored through this exact same call with that entry's own version id.
  onRestorePromptVersion?: (name: string, version: string) => Promise<PromptConfig>
  onCheckPromptReferences?: (name: string) => Promise<BrokenReference[]>
  onAddLearnedConstraints?: (name: string, constraints: string[]) => Promise<LearnedConstraintsUpdate>
  onRemoveLearnedConstraint?: (name: string, constraint: string) => Promise<LearnedConstraintsUpdate>
  // Run-aware (see stages/psm/actions.py's own promote_constraints): no
  // name here, the backend infers it from the current run's own latest,
  // real, successfully-validated result.
  onPromoteConstraints?: (constraints: string[]) => Promise<LearnedConstraintsUpdate>

  // --- Attempts browser (design-system's AttemptsBrowser) --------------
  onLoadManifest?: (runId: string) => Promise<ManifestEntry[]>
  onLoadAttempt?: (runId: string, stage: string, attempt: string) => Promise<AttemptDetail>
}
