import { useState } from "react"
import { usePipeline } from "@/hooks/usePipeline"
import { Stepper } from "@/components/orchestrator/Stepper"
import { ChatColumn } from "@/components/orchestrator/ChatColumn"
import { StageOutputPanel } from "@/components/orchestrator/StageOutputPanel"
import { PlatformForm } from "@/components/orchestrator/PlatformForm"
import { Button } from "@/design-system"
import { CodeBlock } from "@/components/orchestrator/CodeBlock"
import type { OrchestratorEvent, StageId } from "@/orchestrator/types"
import "@/components/orchestrator/orchestrator.css"

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

// Read-only view of a past (non-current) stage's real result, shown when a
// Stepper node is clicked. Deliberately has no Approve/Retry, those only
// make sense for the actual pending stage, see StageOutputPanel for that.
function ViewedStagePanel({
  stage,
  result,
  onBack,
}: {
  stage: StageId
  result: OrchestratorEvent | null
  onBack: () => void
}) {
  const failed = result?.type === "call_failed"
  const output = failed
    ? String(result?.data?.error ?? "Stage failed.")
    : String(result?.data?.output ?? "No output recorded for this stage yet.")
  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        gap: "var(--space-3)",
        height: "100%",
        padding: "var(--space-4)",
        background: "var(--brand-faint)",
        border: "1px solid var(--border-default)",
        borderRadius: "var(--radius-md)",
        boxSizing: "border-box",
        minHeight: 0,
      }}
    >
      <div style={{ display: "flex", alignItems: "center", justifyContent: "space-between" }}>
        <h2
          style={{
            fontFamily: "var(--font-display)",
            fontSize: "var(--text-md)",
            fontWeight: "var(--weight-bold)",
            color: "var(--text-strong)",
            margin: 0,
            textTransform: "capitalize",
          }}
        >
          {stage} stage output
        </h2>
        <Button variant="ghost" size="sm" onClick={onBack}>
          ← Back to current
        </Button>
      </div>
      <CodeBlock code={output} title={`${stage} output (read-only)`} lang={stage} />
    </div>
  )
}

export default function OrchestratorScreen() {
  const { events, currentStage, busy, started, model, error, start, approve, retry, sendNudge, changeModel, reset } =
    usePipeline()
  // Which stage's real result the human clicked to inspect, separate from
  // currentStage (the real pending one). Snaps back to the live view
  // whenever the pipeline actually advances, so clicking an old stage can
  // never strand you away from the stage you'd need to act on next. Reset
  // directly during render (React's own documented pattern for "adjust
  // state when a prop/value changes") rather than in a useEffect, which
  // would cause an extra, avoidable render pass.
  const [viewedStage, setViewedStage] = useState<StageId | null>(null)
  const [stageForReset, setStageForReset] = useState(currentStage)
  if (currentStage !== stageForReset) {
    setStageForReset(currentStage)
    setViewedStage(null)
  }

  const latestResult = latestCallResult(events, currentStage)

  const handleRestart = () => {
    if (!window.confirm("Restart? This discards the current run and can't be undone.")) return
    void reset()
  }

  return (
    <div style={{ display: "flex", flexDirection: "column", height: "100%" }}>
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
        <div style={{ display: "flex", alignItems: "center", justifyContent: "space-between", gap: "var(--space-3)" }}>
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
          {/* No real run history/persistence exists yet (a restart or a
              fresh /start both discard everything the same way), so this is
              a manual escape hatch, not a "save and come back later"
              button. Guarded by a confirm() since it's unrecoverable. */}
          <Button variant="secondary" size="sm" disabled={!started || busy} onClick={handleRestart}>
            Restart
          </Button>
        </div>
        <Stepper
          currentStage={started ? currentStage : "docs"}
          busy={started && busy}
          selectedStage={viewedStage}
          onSelectStage={started ? setViewedStage : undefined}
        />
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
            viewedStage && viewedStage !== currentStage ? (
              <ViewedStagePanel
                stage={viewedStage}
                result={latestCallResult(events, viewedStage)}
                onBack={() => setViewedStage(null)}
              />
            ) : (
              <StageOutputPanel
                currentStage={currentStage}
                busy={busy}
                latestResult={latestResult}
                onApprove={approve}
                onRetry={retry}
              />
            )
          ) : (
            <PlatformForm
              onStart={(platformName, documentationUrl, docsOptions) =>
                start(platformName, documentationUrl, model ?? undefined, docsOptions)
              }
            />
          )}
        </div>
      </div>
    </div>
  )
}
