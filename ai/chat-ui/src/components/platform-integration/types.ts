export type PipelineStageKey =
  | "docs"
  | "pim"
  | "psm"
  | "atl"
  | "acceleo"
  | "generation"

export type StageStatus = "idle" | "active" | "reviewing" | "done" | "error"

export interface PipelineStage {
  key: PipelineStageKey
  label: string
  status: StageStatus
}

export type DocsStepState = "input" | "starting" | "reviewing" | "done" | "error"

export interface OrchestratorNote {
  id: string
  text: string
}
