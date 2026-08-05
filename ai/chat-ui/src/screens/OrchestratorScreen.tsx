import { useEffect, useState } from "react"
import { useSearchParams } from "react-router-dom"
import { usePipeline } from "@/hooks/usePipeline"
import { Stepper } from "@/components/orchestrator/Stepper"
import { ChatColumn } from "@/components/orchestrator/ChatColumn"
import { StageOutputPanel } from "@/components/orchestrator/StageOutputPanel"
import { ViewedStagePanel } from "@/components/orchestrator/ViewedStagePanel"
import { PlatformForm } from "@/components/orchestrator/PlatformForm"
import { Button } from "@/design-system"
import { latestCallResult } from "@/components/orchestrator/stageEvents"
import type { StageId } from "@/types/orchestrator"
import "@/components/orchestrator/orchestrator.css"

export default function OrchestratorScreen() {
  // A sidebar session row links here with ?run=<run_id> (see
  // SessionsList.tsx). No param means the live run, same as before.
  const [searchParams, setSearchParams] = useSearchParams()
  const runId = searchParams.get("run") ?? undefined
  const {
    events,
    currentStage,
    busy,
    started,
    model,
    error,
    isCurrent,
    start,
    approve,
    retry,
    sendNudge,
    changeModel,
    reset,
  } = usePipeline(runId)

  // "Add a new platform"/"New pipeline" in the sidebar link here with
  // ?new=1 (see App.tsx's useSidebarNavigation) — a real signal to actually
  // discard the current run, not just a route change to a URL that might
  // already be the one we're on. Same destructive action as the Restart
  // button below, same confirmation; skipped when nothing's actually
  // running yet, so clicking it from an already-blank form is silent.
  useEffect(() => {
    if (searchParams.get("new") !== "1") return
    if (!started || window.confirm("Start a new run? This discards the current run and can't be undone.")) {
      if (started) void reset()
    }
    setSearchParams(
      (prev) => {
        const next = new URLSearchParams(prev)
        next.delete("new")
        return next
      },
      { replace: true }
    )
    // Only the URL's "new" flag should ever re-trigger this; started/reset
    // are read at the moment it fires, not watched for changes.
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [searchParams])

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
          {/* Restarting discards the live run, so it's meaningless while
              looking at read-only history — only ever acts on the current
              run either way. Guarded by a confirm() since it's
              unrecoverable. */}
          <Button variant="secondary" size="sm" disabled={!started || busy || !isCurrent} onClick={handleRestart}>
            Restart
          </Button>
        </div>
        <Stepper
          currentStage={currentStage}
          busy={busy}
          started={started}
          selectedStage={viewedStage}
          onSelectStage={started ? setViewedStage : undefined}
        />
      </header>

      {/* Only a defined runId can ever be non-current (the live run always
          reports is_current: true, see usePipeline) — this is genuinely
          frozen history from the sidebar, not the run in progress. */}
      {runId && !isCurrent && (
        <div
          style={{
            margin: "var(--space-3) var(--space-5) 0",
            display: "flex",
            alignItems: "center",
            justifyContent: "space-between",
            gap: "var(--space-3)",
            padding: "10px 16px",
            background: "var(--surface-sunken)",
            border: "1px solid var(--border-default)",
            borderRadius: "var(--radius-md)",
            fontFamily: "var(--font-sans)",
            fontSize: "var(--text-xs)",
            color: "var(--text-muted)",
          }}
        >
          <span>Viewing a past run, read-only.</span>
          <Button variant="ghost" size="sm" onClick={() => setSearchParams({})}>
            Back to current run
          </Button>
        </div>
      )}

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
          <ChatColumn
            events={events}
            busy={busy}
            model={model}
            onSend={sendNudge}
            onModelChange={changeModel}
            readOnly={!isCurrent}
          />
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
                readOnly={!isCurrent}
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
