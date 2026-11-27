// Local, self-contained shapes for this component group - same reasoning
// as PromptBuilder/types.ts: design-system stays a pure UI kit with no
// backend/domain-type awareness, a stage panel that depends on both this
// package and a backend-specific one adapts between the two.

import type { CorrectionItem } from "../corrections"

export interface ManifestEntry {
  run_id: string
  stage: string
  attempt_n: number
  valid: boolean
  timestamp: string
  // What the run's events say about the call that made the attempt. Each is absent for
  // an attempt whose call did not say. `ran_with` is how many corrections the stage had
  // when the attempt began, `given` those added before it and `learned` those added while
  // it ran, and `promoted` the texts a person made permanent after it.
  rounds?: number | null
  ran_with?: number
  given?: CorrectionItem[]
  learned?: CorrectionItem[]
  promoted?: string[]
  mock?: boolean
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
  // The attempts are loaded again when this changes, so a caller that has just made a
  // new one (a retry) can have it listed without remounting the browser.
  reloadKey?: string | number
  onLoadManifest: (runId: string) => Promise<ManifestEntry[]>
  onLoadAttempt: (runId: string, stage: string, attempt: string) => Promise<AttemptDetailData>
  // Wires a selected attempt's own prompt_version back into
  // PromptBuilder's own onRestoreVersion, when the caller has both
  // components mounted together and that version still exists in
  // history - optional since a caller showing attempts for a stage with
  // no real prompt config yet has nothing to restore.
  onRestoreConfigFromAttempt?: (promptVersion: string) => Promise<void>
}
