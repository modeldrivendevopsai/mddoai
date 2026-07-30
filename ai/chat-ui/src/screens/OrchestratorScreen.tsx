import { usePipeline } from "@/hooks/usePipeline"
import { Stepper } from "@/components/orchestrator/Stepper"
import { ChatColumn } from "@/components/orchestrator/ChatColumn"
import { StageOutputPanel } from "@/components/orchestrator/StageOutputPanel"
import { PlatformForm } from "@/components/orchestrator/PlatformForm"
import type { OrchestratorEvent, StageId } from "@/orchestrator/types"
import "@/orchestrator/tokens.css"

function latestCallResult(events: OrchestratorEvent[], stage: StageId | null): OrchestratorEvent | null {
  if (!stage) return null
  for (let i = events.length - 1; i >= 0; i--) {
    const event = events[i]
    if (event.stage === stage && (event.type === "call_completed" || event.type === "call_failed")) {
      return event
    }
  }
  return null
}

export default function OrchestratorScreen() {
  const { events, currentStage, busy, started, model, error, start, approve, retry, sendNudge, changeModel } =
    usePipeline()

  const latestResult = latestCallResult(events, currentStage)

  return (
    <div className="orch-scope" style={{ display: "flex", flexDirection: "column", height: "100vh" }}>
      {/* Header + stepper are always visible, from the very first load (all
          pending) through completion, matching the wireframe's "d1: input
          (new platform)" screen, not just once a run is in progress. */}
      <header
        style={{
          display: "flex",
          flexDirection: "column",
          gap: "var(--space-3)",
          padding: "var(--space-3) var(--space-5)",
          borderBottom: "1px solid var(--border-default)",
          // The real wireframe's "d1: input (new platform)" header is a
          // single flat #F3ECFF, no gradient. --brand-faint (--purple-50,
          // #f3f0fe) is the token-scale match. Scenario A/B/C's "Generate
          // Pipeline" flow (not built here) uses a different flat color,
          // pale blue #E8F0FF, not pink and not a gradient either.
          background: "var(--brand-faint)",
        }}
      >
        <h1
          style={{
            fontFamily: "var(--font-display)",
            fontSize: "var(--text-md)",
            fontWeight: "var(--weight-bold)",
            color: "var(--text-strong)",
            margin: 0,
          }}
        >
          {started ? "Platform Integration" : "Add a CI/CD Platform"}
        </h1>
        <Stepper currentStage={started ? currentStage : "docs"} busy={started && busy} />
      </header>

      {/* Matches Callout.jsx's real "danger" tone exactly: bg danger-100,
          border #f3c4c0, 14px/16px padding, these are the source's own
          literal values, not token substitutions. */}
      {error && (
        <div
          style={{
            margin: "var(--space-3) var(--space-5) 0",
            display: "flex",
            gap: 12,
            padding: "14px 16px",
            background: "var(--danger-100)",
            border: "1px solid #f3c4c0",
            borderRadius: "var(--radius-md)",
            fontFamily: "var(--font-sans)",
            fontSize: 13.5,
            lineHeight: 1.6,
            color: "var(--text-body)",
          }}
        >
          {error}
        </div>
      )}

      <div
        style={{
          flex: 1,
          display: "flex",
          gap: "var(--space-4)",
          padding: "var(--space-4)",
          overflow: "hidden",
          background: "var(--surface-page)",
        }}
      >
        <div style={{ flex: 1, minWidth: 0, overflow: "hidden" }}>
          <ChatColumn events={events} busy={busy} model={model} onSend={sendNudge} onModelChange={changeModel} />
        </div>
        <div style={{ flex: 1, minWidth: 0, overflow: "hidden" }}>
          {started ? (
            <StageOutputPanel
              currentStage={currentStage}
              busy={busy}
              latestResult={latestResult}
              onApprove={approve}
              onRetry={retry}
            />
          ) : (
            <PlatformForm onStart={start} />
          )}
        </div>
      </div>
    </div>
  )
}
