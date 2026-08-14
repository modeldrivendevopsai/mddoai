import type { ComponentType } from "react"
import type { StageId } from "@/types/orchestrator"
import type { StagePanelProps } from "./StagePanelProps"
import { DocsStagePanel } from "./docs/DocsStagePanel"
import { SerializationStagePanel } from "./serialization/SerializationStagePanel"
import { PimStagePanel } from "./pim/PimStagePanel"
import { PsmStagePanel } from "./psm/PsmStagePanel"
import { AtlStagePanel } from "./atl/AtlStagePanel"
import { AcceleoStagePanel } from "./acceleo/AcceleoStagePanel"
import { GenerationStagePanel } from "./generation/GenerationStagePanel"

// The one place that knows all seven stage panels exist together — none of
// the panels themselves import this or each other, so grabbing e.g.
// PsmStagePanel alone and dropping it into a different screen needs no
// change here. IntegrationScreen looks up STAGE_PANELS[stage] instead of a
// hardcoded if/switch chain over StageId. docs/ has a third file
// (DocsStartForm) the other stages don't — that's the docs stage's
// pre-run input form, not part of this registry (see IntegrationScreen's
// own !started branch, which renders it directly).
export const STAGE_PANELS: Record<StageId, ComponentType<StagePanelProps>> = {
  docs: DocsStagePanel,
  serialization: SerializationStagePanel,
  pim: PimStagePanel,
  psm: PsmStagePanel,
  atl: AtlStagePanel,
  acceleo: AcceleoStagePanel,
  generation: GenerationStagePanel,
}
