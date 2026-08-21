import { lazy } from "react"
import type { ComponentType } from "react"
import type { StageId } from "@/types/orchestrator"
import type { StagePanelProps } from "./StagePanelProps"

// The one place that knows all seven stage panels exist together — none of
// the panels themselves import this or each other, so grabbing e.g.
// PsmStagePanel alone and dropping it into a different screen needs no
// change here. IntegrationScreen looks up STAGE_PANELS[stage] instead of a
// hardcoded if/switch chain over StageId. docs/ has a second export
// (DocsStartForm) the other stages don't — that's the docs stage's
// pre-run input form, not part of this registry (see IntegrationScreen's
// own !started branch, which loads it directly).
//
// Each panel is its own Module Federation remote (ai/ui-remote-stage-*, see
// ai/docker-compose.yml), so this is a lazy, federated import rather than a
// local one — IntegrationScreen renders these inside a <Suspense> boundary.
// React.lazy needs a default export; each remote exposes a named one
// instead (matching every other export in this app), so the `.then`
// adapts it rather than changing that convention just for federation's sake.
export const STAGE_PANELS: Record<StageId, ComponentType<StagePanelProps>> = {
  docs: lazy(() => import("uiRemoteStageDocs/DocsStagePanel").then((m) => ({ default: m.DocsStagePanel }))),
  serialization: lazy(() =>
    import("uiRemoteStageSerialization/SerializationStagePanel").then((m) => ({ default: m.SerializationStagePanel }))
  ),
  pim: lazy(() => import("uiRemoteStagePim/PimStagePanel").then((m) => ({ default: m.PimStagePanel }))),
  psm: lazy(() => import("uiRemoteStagePsm/PsmStagePanel").then((m) => ({ default: m.PsmStagePanel }))),
  atl: lazy(() => import("uiRemoteStageAtl/AtlStagePanel").then((m) => ({ default: m.AtlStagePanel }))),
  acceleo: lazy(() => import("uiRemoteStageAcceleo/AcceleoStagePanel").then((m) => ({ default: m.AcceleoStagePanel }))),
  generation: lazy(() =>
    import("uiRemoteStageGeneration/GenerationStagePanel").then((m) => ({ default: m.GenerationStagePanel }))
  ),
}
