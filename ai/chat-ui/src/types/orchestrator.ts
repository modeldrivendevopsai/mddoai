// Mirrors ai/orchestrator's real REST contract (see ai/orchestrator/main.py
// and README.md) exactly, this is the single source of truth for that
// contract on the frontend side.

export const STAGES = ["docs", "serialization", "pim", "psm", "atl", "acceleo", "generation"] as const
export type StageId = (typeof STAGES)[number]

export type OrchestratorEventType =
  | "call_started"
  | "call_completed"
  | "call_failed"
  | "review_approved"
  | "review_rejected"
  | "user_message"
  | "message"

export interface OrchestratorEvent {
  type: OrchestratorEventType
  stage: StageId | null
  timestamp: number
  // Structured events (call_started/call_completed/call_failed/review_*/
  // user_message) carry "data"; narration/reply events carry "text" instead,
  // plus "model" naming which LLM produced that reply (null for a purely
  // structured event, or when narration itself failed, see orchestrator.py's
  // record_event()).
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
}

// One entry per run this backend process has seen (in-memory only, see
// orchestrator.py's list_runs()) — the sidebar's session list. is_current
// is false for anything but the live run; those are read-only history.
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

export type ReviewResponse = StartedResponse | CompleteResponse | RerunStatusResponse

export interface RerunOverrides {
  seed_url?: string
  hint?: string
  exclude_urls?: string[]
  max_pages?: number
  max_depth?: number
  force_refresh?: boolean
  // Skips the real crawl, docs_agent returns canned placeholder output
  // instead (see ai/orchestrator/stage_agents.py) — for local dev, so a run
  // doesn't have to wait on a real crawl every time.
  mock?: boolean
}

export interface NudgeStep {
  tool: string
  arguments: Record<string, unknown>
  result: unknown
}

export interface NudgeResponse {
  tool_called: string | null
  result: unknown
  message?: string
  steps?: NudgeStep[]
}

export interface Provider {
  name: string
  tier: string
  available: boolean
}
