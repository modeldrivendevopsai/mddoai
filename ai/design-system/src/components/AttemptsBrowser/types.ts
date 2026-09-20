// Local, self-contained shapes for this component group - same reasoning
// as PromptBuilder/types.ts: design-system stays a pure UI kit with no
// backend/domain-type awareness, a stage panel that depends on both this
// package and a backend-specific one adapts between the two.

export interface ManifestEntry {
  run_id: string
  stage: string
  attempt_n: number
  valid: boolean
  timestamp: string
}

export interface AttemptArtifact {
  filename: string
  content: string
}

export interface AttemptDetailData {
  artifact: AttemptArtifact | null
  result: Record<string, unknown>
  prompt: Record<string, string> | null
  prompt_version: string | null
}

export interface AttemptsBrowserProps {
  runId: string
  stage: string
  onLoadManifest: (runId: string) => Promise<ManifestEntry[]>
  onLoadAttempt: (runId: string, stage: string, attempt: string) => Promise<AttemptDetailData>
  // Wires a selected attempt's own prompt_version back into
  // PromptBuilder's own onRestoreVersion, when the caller has both
  // components mounted together and that version still exists in
  // history - optional since a caller showing attempts for a stage with
  // no real prompt config yet has nothing to restore.
  onRestoreConfigFromAttempt?: (promptVersion: string) => Promise<void>
}
