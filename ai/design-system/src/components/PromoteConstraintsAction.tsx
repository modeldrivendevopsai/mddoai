import { useState } from "react"
import { Button } from "./Button"

export interface PromoteConstraintsActionProps {
  // The exact constraint string that produced this validated success,
  // rendered by the backend as a "- one\n- two" bullet block - prefilled
  // into the editable draft below, never sent as-is.
  initialConstraintsBlock: string
  // Promise<unknown>, not Promise<void>: the real onPromoteConstraints prop
  // resolves to the updated PromptConfig (see orchestrator-types), which
  // this component never needs to look at - it just awaits completion -
  // but a caller's own resolved value type must still be assignable here,
  // and Promise<X> is never assignable to Promise<void> for a concrete X.
  onPromote: (constraints: string[]) => Promise<unknown>
}

const labelStyle = {
  fontFamily: "var(--font-sans)",
  fontSize: "var(--text-xs)",
  fontWeight: "var(--weight-bold)",
  color: "var(--text-strong)",
  margin: "0 0 var(--space-1)",
} as const

const textareaStyle = {
  width: "100%",
  resize: "none",
  boxSizing: "border-box",
  border: "1px solid var(--border-default)",
  borderRadius: "var(--radius-md)",
  padding: "var(--space-2) var(--space-3)",
  fontFamily: "var(--font-sans)",
  fontSize: "var(--text-sm)",
  background: "var(--surface-card)",
  color: "var(--text-body)",
} as const

function parseConstraintsBlock(block: string): string[] {
  return block
    .split("\n")
    .map((line) => line.replace(/^-\s*/, "").trim())
    .filter(Boolean)
}

// Adds one validated run's own live corrections to the "Permanent
// constraints" list right below this button: a real, human-confirmed,
// editable-before-confirming action, the backend gating this on a real
// validated success (see the calling stage panel's own canPromote check)
// rather than automatic capture of every typed correction. Generic across
// every stage that offers promotion (psm, atl, acceleo): it carries no
// stage identity of its own, only a prefilled constraints block and a
// promote callback the caller already bound to its own stage's real
// endpoint. Rendered directly above that same list, inside its own
// section, so it reads as an entry point into it, not a separate feature.
export function PromoteConstraintsAction({ initialConstraintsBlock, onPromote }: PromoteConstraintsActionProps) {
  const [draft, setDraft] = useState<string | null>(null)

  if (draft === null) {
    return (
      <Button variant="secondary" size="sm" onClick={() => setDraft(initialConstraintsBlock)}>
        Add this run's corrections to Permanent constraints
      </Button>
    )
  }

  return (
    <div style={{ display: "flex", flexDirection: "column", gap: "var(--space-2)" }}>
      <p style={labelStyle}>
        These corrections produced this validated success — edit before confirming, then they join Permanent
        constraints below and every future run starts with them already applied.
      </p>
      <textarea
        className="orch-field"
        value={draft}
        onChange={(e) => setDraft(e.target.value)}
        rows={3}
        style={textareaStyle}
      />
      <div style={{ display: "flex", gap: "var(--space-2)" }}>
        <Button
          variant="primary"
          size="sm"
          onClick={async () => {
            const constraints = parseConstraintsBlock(draft)
            if (constraints.length > 0) await onPromote(constraints)
            setDraft(null)
          }}
        >
          Confirm & save permanently
        </Button>
        <Button variant="ghost" size="sm" onClick={() => setDraft(null)}>
          Cancel
        </Button>
      </div>
    </div>
  )
}
