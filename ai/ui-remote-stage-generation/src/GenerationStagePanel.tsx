import { useState, type ReactNode } from "react"
import { Button, CodeBlock, StageInfoNote, StatusPill } from "design-system"
import "design-system/integration.css"
import type { OrchestratorEvent, StagePanelProps } from "orchestrator-types"
import { STAGES } from "orchestrator-types"
import type { StageId } from "orchestrator-types"

// Every real stage before generation itself - the real, legitimate set of
// places a generation failure can actually trace back to, since generation
// just runs whatever ATL/Acceleo the earlier stages already produced
// against the real sample PIM instance: the real bug could be in that
// transformation/template, but just as easily in an earlier target
// metamodel or the platform's own fetched docs.
const FORKABLE_STAGES = STAGES.filter((stage) => stage !== "generation")

// generation's own real inputs (atl_output/acceleo_output/psm_output) only
// ever appear in this stage's OWN call_started event's data (run_stage()
// records the full enriched context there, see pipeline.py's own
// _run_stage_worker) - call_completed/call_failed only ever carry
// output/error, never what it actually ran against. latestResult (built
// from those two only, see stageEvents.ts's own latestCallResult) has
// nowhere to get this from, so this stage's own panel reads the full raw
// events list it already receives instead, the one real place this data
// exists. Without this, a real execution failure shows only the error, an
// otherwise unreadable "some real subprocess for some real reason" black
// box - this is what actually ran, so it's what a human needs to
// understand why.
function latestGenerationInputs(events: OrchestratorEvent[]): { atl: string; acceleo: string; psm: string } | null {
  for (let i = events.length - 1; i >= 0; i--) {
    const event = events[i]
    if (event.stage === "generation" && event.type === "call_started") {
      const data = event.data ?? {}
      return {
        atl: String(data.atl_output ?? ""),
        acceleo: String(data.acceleo_output ?? ""),
        psm: String(data.psm_output ?? ""),
      }
    }
  }
  return null
}

function GenerationInputs({ events }: { events: OrchestratorEvent[] }) {
  const inputs = latestGenerationInputs(events)
  if (!inputs) return null
  return (
    <details>
      <summary
        style={{
          cursor: "pointer",
          fontFamily: "var(--font-sans)",
          fontSize: 13,
          fontWeight: 600,
          color: "var(--text-strong)",
        }}
      >
        What generation actually ran
      </summary>
      <div style={{ display: "flex", flexDirection: "column", gap: "var(--space-2)", marginTop: "var(--space-2)" }}>
        <CodeBlock code={inputs.atl} title="ATL transformation used" lang="atl" />
        <CodeBlock code={inputs.acceleo} title="Acceleo template used" lang="acceleo" />
        <CodeBlock code={inputs.psm} title="Target platform metamodel used" lang="ecore" />
      </div>
    </details>
  )
}

// The joined "generation output" CodeBlock below already shows the final
// text a caller gets back, but that's not everything this stage's own real
// execution actually produced: gen_stage() also runs the real intermediate
// PIM->PSM step (psm_instance) before Acceleo ever sees it, and a real
// Acceleo template can legitimately generate more than one real file
// (generated_files) - both only ever appear in call_completed's own data
// (see stages/generation/agent.py's own (output, extra) return), never in
// the joined output text alone. Without this, a human debugging a
// correct-looking joined output has no way to see the real per-file
// boundaries or the real model ATL execution actually derived along the way.
//
// Also read on a real call_failed: when Acceleo fails after ATL already
// succeeded, gen_stage() attaches the real psm_instance ATL produced to
// the raised exception's own "extra" data (see pipeline.py's own
// run_stage_async worker and this stage's own agent.py) precisely so a
// human looking at the failure here - not digging through the real
// attempt directory on disk - can still see the real, valid model that
// proves the bug is in Acceleo's own template, not further back.
function latestGenerationOutputs(
  latestResult: OrchestratorEvent | null,
): { psmInstance: string | null; generatedFiles: Record<string, string> | null } | null {
  if (!latestResult || (latestResult.type !== "call_completed" && latestResult.type !== "call_failed")) return null
  const data = latestResult.data ?? {}
  const psmInstance = typeof data.psm_instance === "string" ? data.psm_instance : null
  const generatedFiles =
    data.generated_files && typeof data.generated_files === "object"
      ? (data.generated_files as Record<string, string>)
      : null
  if (psmInstance === null && generatedFiles === null) return null
  return { psmInstance, generatedFiles }
}

function GenerationOutputs({ latestResult }: { latestResult: OrchestratorEvent | null }) {
  const outputs = latestGenerationOutputs(latestResult)
  if (!outputs) return null
  const fileEntries = Object.entries(outputs.generatedFiles ?? {})
  // A single generated file is already shown in full in the main
  // "generation output" block above (see gen_stage()'s own (output, extra)
  // return: output IS that one file's content when there's only one) -
  // listing it again here by name would just repeat what's already on
  // screen. Only worth breaking out by name once there's more than one
  // real file to tell apart.
  const showFileBreakdown = fileEntries.length > 1
  if (outputs.psmInstance === null && !showFileBreakdown) return null
  const summaryParts: string[] = []
  if (outputs.psmInstance !== null) summaryParts.push("the intermediate model")
  if (showFileBreakdown) summaryParts.push(`${fileEntries.length} generated files`)
  // "produced" reads wrong on a real failure (ATL succeeded but Acceleo
  // then crashed - see agent.py's own RuntimeError.extra) - the model
  // shown here is real and valid, but the stage as a whole did not finish.
  const heading =
    latestResult?.type === "call_failed"
      ? `What generation produced before failing (${summaryParts.join(" and ")})`
      : `What generation actually produced (${summaryParts.join(" and ")})`
  return (
    <details>
      <summary
        style={{
          cursor: "pointer",
          fontFamily: "var(--font-sans)",
          fontSize: 13,
          fontWeight: 600,
          color: "var(--text-strong)",
        }}
      >
        {heading}
      </summary>
      <div style={{ display: "flex", flexDirection: "column", gap: "var(--space-2)", marginTop: "var(--space-2)" }}>
        {outputs.psmInstance !== null && (
          <CodeBlock code={outputs.psmInstance} title="Intermediate model instance (produced by running the ATL transformation)" lang="xml" />
        )}
        {/* No lang prop: CodeBlock only ever displays lang when title is
            absent (it always has one here), and a real generated file's
            own extension varies by platform (e.g. Woodpecker's real
            ".woodpecker/pipeline.yaml"), so a fixed lang here would be
            inert at best, wrong at worst. */}
        {showFileBreakdown &&
          fileEntries.map(([name, content]) => (
            <CodeBlock key={name} code={content} title={`Generated file: ${name}`} />
          ))}
      </div>
    </details>
  )
}

// Generation's own stage panel, approve/retry when Generation is the live pending stage
// (onApprove/onRetry given), a read-only "back to current" view when it's a
// past stage being viewed via the Stepper (onBack given instead). Its own
// file, not a shared component parameterized by StageId: Generation's real
// backend output and prompt are free to diverge from the other five stages'
// own, independently, without touching them.
export function GenerationStagePanel({
  busy,
  latestResult,
  events,
  onApprove,
  onRetry,
  onForkFrom,
  onBack,
  readOnly = false,
  stageDetail = null,
}: StagePanelProps) {
  const failed = latestResult?.type === "call_failed"
  const output = failed
    ? String(latestResult?.data?.error ?? "Stage failed.")
    : String(latestResult?.data?.output ?? (onBack ? "No output recorded for this stage yet." : ""))
  const [forkStage, setForkStage] = useState<StageId>("atl")

  if (onBack) {
    return (
      <Panel>
        <div style={{ display: "flex", alignItems: "center", justifyContent: "space-between" }}>
          <h2 style={headingStyle}>Generation stage output</h2>
          <Button variant="ghost" size="sm" onClick={onBack}>
            ← Back to current
          </Button>
        </div>
        <StageInfoNote detail={stageDetail} />
        <GenerationInputs events={events} />
        <CodeBlock code={output} title="generation output (read-only)" lang="generation" />
        <GenerationOutputs latestResult={latestResult} />
      </Panel>
    )
  }

  const hasResult = latestResult !== null

  return (
    <Panel>
      <h2 style={headingStyle}>Generation stage output</h2>
      <StageInfoNote detail={stageDetail} />
      <GenerationInputs events={events} />

      {/* The one positive "it worked" signal this stage has - it has no
          validation result of its own to show a pass/fail pill for (see
          the "No correction field here" comment below: no LLM call, no
          retry loop), but a real run that reached here without failing
          really did just produce real, deployable CI/CD config. */}
      {!busy && hasResult && !failed && <StatusPill variant="success">Generated successfully</StatusPill>}

      {/* Matches Callout.jsx's real "danger" tone exactly: bg danger-100,
          border --danger-border (not the fully-saturated danger-500),
          radius-md, 14px/16px padding, see tokens.css's --danger-border
          for why that one's a token this port adds on top of the source. */}
      {failed && (
        <div
          style={{
            display: "flex",
            gap: 12,
            padding: "14px 16px",
            background: "var(--danger-100)",
            border: "1px solid var(--danger-border)",
            borderRadius: "var(--radius-md)",
            fontFamily: "var(--font-sans)",
          }}
        >
          {/* Not "Automated check: FAILED" (Atl/Acceleo's own wording, matching
              StagePanel.tsx there) - this stage has no check of its own to
              fail (see the "No correction field here" comment below). What
              actually happened is a real runtime crash of ATL/Acceleo code
              that already passed its own checks at their own stages. */}
          <div style={{ fontWeight: 600, fontSize: 14, color: "var(--text-strong)" }}>Execution failed</div>
        </div>
      )}

      <CodeBlock
        code={
          busy
            ? "Running the real ATL transformation, then Acceleo, against the sample PIM instance…"
            : hasResult
              ? output
              : "No output yet."
        }
        title="generation output"
        lang="generation"
      />
      <GenerationOutputs latestResult={latestResult} />

      {/* No correction field here, unlike every earlier stage: this stage
          actually runs the real ATL/Acceleo output against a real model
          instance now (see stages/generation/agent.py), it doesn't call an
          LLM of its own a free-text note could steer - there's no prompt
          left to curate. A wrong result here means the ATL/Acceleo that
          made it this far (already reviewed and approved at their own
          stages) needs a real fix at its own source, not a note appended
          here. "Retry this stage" still re-runs the exact same real
          execution, useful for a transient failure (e.g. execution-agent
          briefly unreachable); "Go back and fix" below is the real answer
          when the failure isn't transient. */}
      <div style={{ display: "flex", gap: "var(--space-2)" }}>
        <Button
          variant="primary"
          size="sm"
          disabled={busy || !hasResult || failed || readOnly}
          onClick={() => onApprove?.()}
        >
          Approve
        </Button>
        <Button variant="secondary" size="sm" disabled={busy || readOnly} onClick={() => onRetry?.()}>
          Retry this stage
        </Button>
      </div>

      {/* A real execution failure here isn't necessarily generation's own
          fault: it just runs whatever ATL/Acceleo the earlier stages
          already produced, so the real bug can live in any earlier stage's
          real output, a bad transformation, a bad target metamodel, even
          bad fetched docs. "Retry this stage" above only re-runs the exact
          same, still-broken inputs - forking starts a new run that reuses
          everything real up to the chosen stage, then pauses there so a
          real reason can be recorded before it regenerates. */}
      {failed && onForkFrom && !readOnly && (
        <div
          style={{
            display: "flex",
            flexDirection: "column",
            gap: "var(--space-2)",
            padding: "14px 16px",
            background: "var(--surface-sunken)",
            border: "1px solid var(--border-default)",
            borderRadius: "var(--radius-md)",
            fontFamily: "var(--font-sans)",
          }}
        >
          <div style={{ fontWeight: 600, fontSize: 14, color: "var(--text-strong)" }}>Fix an earlier stage instead</div>
          <div style={{ fontSize: 13, color: "var(--text-muted)" }}>
            Starts a new run that reuses every real stage before the one you pick, then pauses there so you can
            add what actually went wrong before it regenerates.
          </div>
          <div style={{ display: "flex", gap: "var(--space-2)", alignItems: "center" }}>
            <select
              value={forkStage}
              onChange={(e) => setForkStage(e.target.value as StageId)}
              style={{
                fontFamily: "var(--font-mono)",
                fontSize: 13,
                padding: "6px 10px",
                borderRadius: "var(--radius-sm)",
                border: "1px solid var(--border-default)",
                background: "var(--surface-card)",
                color: "var(--text-strong)",
              }}
            >
              {FORKABLE_STAGES.map((stage) => (
                <option key={stage} value={stage}>
                  {stage}
                </option>
              ))}
            </select>
            <Button variant="secondary" size="sm" disabled={busy} onClick={() => onForkFrom(forkStage)}>
              Go back and fix {forkStage}
            </Button>
          </div>
        </div>
      )}
    </Panel>
  )
}

const headingStyle = {
  fontFamily: "var(--font-display)",
  fontSize: "var(--text-md)",
  fontWeight: "var(--weight-bold)",
  color: "var(--text-strong)",
  margin: 0,
} as const

function Panel({ children }: { children: ReactNode }) {
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
        // Matches every sibling stage panel's own Panel (Atl/Psm/Docs):
        // IntegrationScreen.tsx's own stage-pane wrapper is deliberately
        // overflow:"hidden", so a panel taller than its allotted height
        // must scroll itself or its own extra content, real ATL/Acceleo
        // output, the failure detail, the fork controls, becomes
        // genuinely unreachable rather than just visually cut off
        // (confirmed for real: this panel had no scroll at all until now).
        overflow: "auto",
      }}
    >
      {children}
    </div>
  )
}
