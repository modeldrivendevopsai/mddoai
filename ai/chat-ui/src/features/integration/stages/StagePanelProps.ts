import type { OrchestratorEvent } from "@/types/orchestrator"

// Shared prop contract every per-stage panel (PsmStagePanel, AtlStagePanel,
// etc.) implements — a type-only contract, not a shared component. Each
// stage panel decides for itself, from these same props, whether it's
// rendering as the live pending stage (onApprove/onRetry given) or a past
// stage being viewed read-only via the Stepper (onBack given instead).
export interface StagePanelProps {
  busy: boolean
  latestResult: OrchestratorEvent | null
  // Present only when this stage is the live pending one.
  onApprove?: () => void
  onRetry?: (correction?: string) => void
  // Present only when viewing this stage historically (past, non-current).
  onBack?: () => void
  readOnly?: boolean
}
