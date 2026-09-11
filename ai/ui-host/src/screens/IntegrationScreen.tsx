import { lazy, useEffect, useState } from "react"
import { useSearchParams } from "react-router-dom"
import { useIntegration } from "@/hooks/useIntegration"
import { STAGE_PANELS } from "@/features/integration/stages/registry"
import { RemoteBoundary } from "@/federated/RemoteBoundary"
import { Button } from "design-system"
import { latestCallResult, originalDocsInput } from "@/features/integration/stageEvents"
import type { StageId } from "orchestrator-types"

// Stepper, ChatColumn, and the docs stage's start form are each their own
// Module Federation remote too (ui-remote-stepper, ui-remote-chat,
// ui-remote-stage-docs — see ai/docker-compose.yml), same lazy/federated
// pattern as STAGE_PANELS in registry.ts, and same reason: React.lazy needs
// a default export, each remote exposes a named one instead.
const Stepper = lazy(() => import("uiRemoteStepper/Stepper").then((m) => ({ default: m.Stepper })))
const ChatColumn = lazy(() => import("uiRemoteChat/ChatColumn").then((m) => ({ default: m.ChatColumn })))
const DocsStartForm = lazy(() =>
  import("uiRemoteStageDocs/DocsStartForm").then((m) => ({ default: m.DocsStartForm }))
)

// "Nothing left to review" is a screen-level state (the whole run finished),
// not any one stage's concern — kept here rather than duplicated across
// every stage panel's own ui-remote-stage-* package.
function CompletePanel() {
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
      <p style={{ color: "var(--text-muted)", fontFamily: "var(--font-sans)" }}>
        Integration complete. All stages approved.
      </p>
    </div>
  )
}

// A past, non-current run that never got a platform name/URL submitted —
// e.g. created by Restart or "Add a new platform" and then abandoned before
// filling the form. Real, possible state, not a bug on its own. Shown
// instead of DocsStartForm: that form's onStart always acts on whichever
// run is actually current on the backend, never "this specific old slot",
// so rendering a live, submittable form here would let a read-only history
// view silently hijack and replace the real current run.
function NoStagesRanPanel() {
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
      <p style={{ color: "var(--text-muted)", fontFamily: "var(--font-sans)" }}>
        No stages were run for this session.
      </p>
    </div>
  )
}

export default function IntegrationScreen() {
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
    providers,
    error,
    isCurrent,
    start,
    approve,
    retry,
    sendMessage,
    changeModel,
    reset,
    resume,
  } = useIntegration(runId)

  // "Add a new platform"/"New pipeline" in the sidebar link here with
  // ?new=1 (see App.tsx's useSidebarNavigation) — a real signal to actually
  // reset the current run, not just a route change to a URL that might
  // already be the one we're on. Deliberately a blank reset(), not
  // handleRestart's "re-run the same platform" below: "Add a new platform"
  // means a different platform, an empty DocsStartForm is the right result.
  // No confirm(): the current run isn't deleted, it stays viewable and
  // resumable as history (the backend keeps it, and POST /resume/{run_id}
  // can bring it back), it just stops being the live/interactive one until
  // resumed. Skipped when nothing's actually running yet, so clicking it
  // from an already-blank form is silent.
  useEffect(() => {
    if (searchParams.get("new") !== "1") return
    if (started) void reset()
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

  // Restart re-runs the SAME platform from scratch (same platform name/URL/
  // docs options, re-crawled from stage 0) — different from "Add a new
  // platform" above, which really does want a blank form for a different
  // platform. originalDocsInput reads the real input back out of this run's
  // own first docs event rather than a blank reset(), since that's the
  // whole point of "restart": redo this, not clear it. Falls back to a
  // no-op if that data isn't there for some reason (can't restart what was
  // never really started), rather than silently degrading into a blank
  // reset the human didn't ask for.
  const handleRestart = () => {
    const original = originalDocsInput(events)
    if (!original) return
    void start(original.platformDescription, original.seedUrl, model ?? undefined, original.docsOptions)
  }

  const handleResume = () => void resume()

  // Picks which of the six independent stage panels to render, and in which
  // mode (active vs. viewed-read-only) — see @/features/integration/stages/
  // registry.ts. Kept here, not pushed into a shared component, since it's
  // wiring specific to this screen's own state (viewedStage/currentStage/
  // approve/retry), not a reusable stage-panel concern.
  // RemoteBoundary wraps only the two branches that actually resolve a
  // federated React.lazy() import (ViewedPanel/ActivePanel) — CompletePanel
  // is a plain local component that can never reject one, so it's returned
  // unwrapped here, the same way NoStagesRanPanel is below.
  function renderStagePane() {
    if (viewedStage && viewedStage !== currentStage) {
      const ViewedPanel = STAGE_PANELS[viewedStage]
      return (
        <RemoteBoundary name={`${viewedStage} stage panel`} resetKey={viewedStage}>
          <ViewedPanel
            busy={false}
            latestResult={latestCallResult(events, viewedStage)}
            events={events}
            onBack={() => setViewedStage(null)}
            readOnly
          />
        </RemoteBoundary>
      )
    }
    if (!currentStage) {
      return <CompletePanel />
    }
    const ActivePanel = STAGE_PANELS[currentStage]
    return (
      <RemoteBoundary name={`${currentStage} stage panel`} resetKey={currentStage}>
        <ActivePanel
          busy={busy}
          latestResult={latestResult}
          events={events}
          onApprove={() => approve(currentStage)}
          onRetry={(correction) => retry(currentStage, correction)}
          readOnly={!isCurrent}
        />
      </RemoteBoundary>
    )
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
            Platform integration
          </h1>
          {/* Restarting moves the live run to read-only history rather than
              deleting it, so it's meaningless while already looking at
              read-only history — only ever acts on the current run either
              way. No confirm(): Resume (in the read-only banner below) can
              always bring it back, so
              this was never actually a one-way action worth interrupting
              for. */}
          <Button variant="secondary" size="sm" disabled={!started || busy || !isCurrent} onClick={handleRestart}>
            Restart
          </Button>
        </div>
        <RemoteBoundary name="Stepper">
          <Stepper
            currentStage={currentStage}
            busy={busy}
            started={started}
            selectedStage={viewedStage}
            onSelectStage={started ? setViewedStage : undefined}
          />
        </RemoteBoundary>
      </header>

      {/* Only a defined runId can ever be non-current (the live run always
          reports is_current: true, see useIntegration) — this is genuinely
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
          <div style={{ display: "flex", gap: "var(--space-2)" }}>
            {/* No disabled={busy} guard here: busy on this hook instance is
                the viewed (non-current) run's own busy state, always false
                for history — it says nothing about whether the actually-live
                run is mid-stage. The real guard is server-side (see
                main.py's resume_endpoint, 409 while the live run is busy),
                surfaced through the normal error path if it fires. */}
            <Button variant="secondary" size="sm" onClick={handleResume}>
              Resume this run
            </Button>
            <Button variant="ghost" size="sm" onClick={() => setSearchParams({})}>
              Back to current run
            </Button>
          </div>
        </div>
      )}

      {/* Matches Callout.jsx's real "danger" tone exactly: bg danger-100,
          border --danger-border, 14px/16px padding — see tokens.css's
          --danger-border for why that one's a token this port adds on top
          of the source. */}
      {error && (
        <div
          style={{
            margin: "var(--space-3) var(--space-5) 0",
            display: "flex",
            gap: 12,
            padding: "14px 16px",
            background: "var(--danger-100)",
            border: "1px solid var(--danger-border)",
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
          <RemoteBoundary name="Chat">
            <ChatColumn
              events={events}
              busy={busy}
              model={model}
              providers={providers}
              onSend={sendMessage}
              onModelChange={changeModel}
              readOnly={!isCurrent}
            />
          </RemoteBoundary>
        </div>
        <div style={{ flex: 1, minWidth: 0, overflow: "hidden" }}>
          {started ? (
            renderStagePane()
          ) : isCurrent ? (
            <RemoteBoundary name="Docs start form">
              <DocsStartForm
                onStart={(platformName, documentationUrl, docsOptions) =>
                  start(platformName, documentationUrl, model ?? undefined, docsOptions)
                }
              />
            </RemoteBoundary>
          ) : (
            <NoStagesRanPanel />
          )}
        </div>
      </div>
    </div>
  )
}
