// Mirrors ai/orchestrator's real REST contract (see ai/orchestrator/main.py
// and README.md) exactly, this is the single source of truth for that
// contract on the frontend side — every ui-host and ui-remote-* package
// depends on this package directly rather than keeping its own copy.

export const STAGES = ["docs", "serialization", "pim", "psm", "atl", "acceleo", "generation"] as const
export type StageId = (typeof STAGES)[number]

export type OrchestratorEventType =
  | "call_started"
  | "call_completed"
  | "call_failed"
  | "review_approved"
  | "review_rejected"
  | "constraint_added"
  | "documentation_extended"
  | "constraints_promoted"
  | "tool_called"
  | "user_message"
  | "message"

// The 8 types integration_runner itself reports as raw pipeline facts — a
// real signal that a run has actually started. The other 3 (tool_called,
// user_message, message) are chat-layer-only: send_message() appends them
// for a plain conversational turn too, even when the LLM decides not to
// call any tool, so their presence alone doesn't mean a real run exists.
export const PIPELINE_EVENT_TYPES: readonly OrchestratorEventType[] = [
  "call_started",
  "call_completed",
  "call_failed",
  "review_approved",
  "review_rejected",
  "constraint_added",
  "documentation_extended",
  "constraints_promoted",
]

export interface OrchestratorEvent {
  type: OrchestratorEventType
  stage: StageId | null
  timestamp: number
  // Every structured event type above except "message"/"user_message"
  // carries "data" — the real, complete fact integration_runner (or, for
  // tool_called, orchestrator itself) recorded, not just prose about it.
  // "message"/"user_message" carry "text" instead, plus "model" naming
  // which LLM produced that reply (null for a purely structured event, or
  // when narration itself failed, see chat_log.py's own narration logic).
  data?: Record<string, unknown>
  text?: string
  model?: string | null
}

export interface EventsResponse {
  events: OrchestratorEvent[]
  current_stage: StageId | null
  busy: boolean
  model: string | null
  // False for a past run's frozen event log (see main.py's /events?run_id=),
  // true for the live run — the seam the frontend uses to go read-only.
  is_current: boolean
  // The real run this response describes — always present (integration_runner's
  // own /events always reports it, see chat_log.get_events()), whether this
  // is the live run or a past one being viewed read-only. The attempts
  // browser needs this real id to know which run's own attempt tree to read.
  run_id: string
}

// One entry per run the backend has seen (in-memory only) — the sidebar's
// session list. is_current is false for anything but the live run; those
// are read-only history.
export interface RunSummary {
  run_id: string
  platform_name: string | null
  current_stage: StageId | null
  busy: boolean
  is_current: boolean
}

export interface StartedResponse {
  status: "started"
  stage: StageId
}

export interface CompleteResponse {
  status: "complete"
}

export interface ResetResponse {
  status: "reset"
}

export interface ResumeResponse {
  run_id: string
  current_stage: StageId | null
}

export interface RerunStatusResponse {
  status: "rerun"
  stage: StageId
}

// A stage in pipeline.py's own _REQUIRES_MANUAL_START set (psm today) was
// just advanced into, but deliberately not auto-run — see PsmStagePanel's
// own "no output yet" / Generate button for the real UI consequence of
// this status.
export interface AdvancedPendingResponse {
  status: "advanced_pending"
  stage: StageId
}

export type ReviewResponse = StartedResponse | CompleteResponse | RerunStatusResponse | AdvancedPendingResponse

export interface RerunOverrides {
  seed_url?: string
  hint?: string
  exclude_urls?: string[]
  max_pages?: number
  max_depth?: number
  force_refresh?: boolean
  // Skips the real crawl, the docs stage returns canned placeholder output
  // instead — for local dev, so a run doesn't have to wait on a real crawl
  // every time.
  mock?: boolean
}

export interface MessageStep {
  tool: string
  arguments: Record<string, unknown>
  result: unknown
}

export interface MessageResponse {
  tool_called: string | null
  result: unknown
  message?: string
  steps?: MessageStep[]
}

export interface Provider {
  name: string
  tier: string
  available: boolean
}

// Same shape as rerunStage's overrides, minus seed_url (that's a required,
// separate param at start time, not an optional override) — ai/orchestrator's
// docs stage takes these real retrieval parameters at start time too, not
// only on a retry (see ai/orchestrator's main.py StartRequest).
export type DocsOptions = Omit<RerunOverrides, "seed_url">

// --- Modular prompt builder ------------------------------------------------
// Mirrors generation_toolkit.prompt_config's own real shapes exactly (see
// ai/generation_toolkit/attachments/resolve.py, prompt_config/storage.py,
// prompt_config/presets.py, prompt_config/history.py). Not psm-specific:
// the same shapes apply to any stage with a real, editable prompt config
// once atl/acceleo/generation each get one of their own — "name" is that
// stage's own mode string ("generation", "comparison", ...), "preset" is a
// platform slug or "default".

export type PromptAttachmentType = "text" | "file" | "context"

export interface PromptAttachment {
  id: string
  name: string
  type: PromptAttachmentType
  // Present depending on `type`: "content" for "text", "path" for "file",
  // "key" for "context" - never more than one of the three on a real
  // attachment, but all three stay optional here rather than a discriminated
  // union, matching the plain-dict shape the real backend actually sends.
  content?: string
  path?: string
  key?: string
}

export interface PromptConfig {
  system_prompt: string
  attachments: PromptAttachment[]
  // Present once a config has been saved through the real API at least
  // once (see storage.save_config) - a freshly shipped, never-edited
  // default has neither.
  learned_constraints?: string[]
  label?: string | null
  platform_hints?: string[]
  _version?: string
}

export interface PresetMetadata {
  id: string
  label: string
  platform_hints: string[]
}

export interface PromptDiff {
  system_prompt_changed: boolean
  attachments_added: string[]
  attachments_removed: string[]
  attachments_changed: string[]
}

export interface PromptPreview {
  system_prompt: string
  user_content: string
}

export interface BrokenReference {
  id: string
  error: string
}

// One entry from runs/<run_id>/manifest.json (see
// integration_runner/stages/_validation.py's own _update_manifest) - every
// attempt recorded for a run, across every stage, not just psm's.
export interface ManifestEntry {
  run_id: string
  stage: StageId
  attempt_n: number
  valid: boolean
  timestamp: string
}

export interface AttemptArtifact {
  filename: string
  content: string
}

// One attempt's real persisted files (see
// integration_runner/stages/_attempts_read.py's own read_attempt).
// prompt/prompt_version are null for a stage with no real, config-driven
// prompt yet (every stage besides psm's own generation-mode calls today).
export interface AttemptDetail {
  artifact: AttemptArtifact | null
  result: Record<string, unknown>
  prompt: Record<string, string> | null
  prompt_version: string | null
}
