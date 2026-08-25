// Ambient type declarations for every Module Federation remote import
// specifier this app uses (see vite.config.ts's federation() remotes map
// for where each name comes from and registry.ts / IntegrationScreen.tsx
// for where these are actually imported). @module-federation/vite has a
// dts plugin that can generate these automatically from a remote's own
// source, not used yet (see any remote's vite.config.ts's own dts: false
// comment) — these are hand-written instead, referencing the real,
// shared orchestrator-types package for the prop shapes themselves rather
// than duplicating them here too.

declare module "uiRemoteChat/ChatColumn" {
  import type { ComponentType } from "react"
  import type { OrchestratorEvent, Provider } from "orchestrator-types"

  export interface ChatColumnProps {
    events: OrchestratorEvent[]
    busy: boolean
    model: string | null
    providers: Provider[]
    onSend: (message: string) => void
    onModelChange: (model?: string) => void
    readOnly?: boolean
  }

  export const ChatColumn: ComponentType<ChatColumnProps>
}

declare module "uiRemoteStepper/Stepper" {
  import type { ComponentType } from "react"
  import type { StageId } from "orchestrator-types"

  export interface StepperProps {
    currentStage: StageId | null
    busy: boolean
    started: boolean
    selectedStage?: StageId | null
    onSelectStage?: (stageId: StageId) => void
  }

  export const Stepper: ComponentType<StepperProps>
}

declare module "uiRemoteStageDocs/DocsStagePanel" {
  import type { ComponentType } from "react"
  import type { StagePanelProps } from "orchestrator-types"
  export const DocsStagePanel: ComponentType<StagePanelProps>
}

declare module "uiRemoteStageDocs/DocsStartForm" {
  import type { DocsOptions } from "orchestrator-types"

  export interface DocsStartFormProps {
    onStart: (platformName: string, documentationUrl: string, docsOptions?: DocsOptions) => void
  }

  export function DocsStartForm(props: DocsStartFormProps): React.JSX.Element
}

declare module "uiRemoteStageSerialization/SerializationStagePanel" {
  import type { ComponentType } from "react"
  import type { StagePanelProps } from "orchestrator-types"
  export const SerializationStagePanel: ComponentType<StagePanelProps>
}

declare module "uiRemoteStagePim/PimStagePanel" {
  import type { ComponentType } from "react"
  import type { StagePanelProps } from "orchestrator-types"
  export const PimStagePanel: ComponentType<StagePanelProps>
}

declare module "uiRemoteStagePsm/PsmStagePanel" {
  import type { ComponentType } from "react"
  import type { StagePanelProps } from "orchestrator-types"
  export const PsmStagePanel: ComponentType<StagePanelProps>
}

declare module "uiRemoteStageAtl/AtlStagePanel" {
  import type { ComponentType } from "react"
  import type { StagePanelProps } from "orchestrator-types"
  export const AtlStagePanel: ComponentType<StagePanelProps>
}

declare module "uiRemoteStageAcceleo/AcceleoStagePanel" {
  import type { ComponentType } from "react"
  import type { StagePanelProps } from "orchestrator-types"
  export const AcceleoStagePanel: ComponentType<StagePanelProps>
}

declare module "uiRemoteStageGeneration/GenerationStagePanel" {
  import type { ComponentType } from "react"
  import type { StagePanelProps } from "orchestrator-types"
  export const GenerationStagePanel: ComponentType<StagePanelProps>
}
