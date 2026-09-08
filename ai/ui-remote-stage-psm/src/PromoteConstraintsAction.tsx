import { useState } from "react"
import { Button } from "design-system"

interface PromoteConstraintsActionProps {
  // The exact constraint string that produced this validated success (see
  // generation_toolkit/prompt_builder.py's build_prompt, which renders
  // constraints as a "- one\n- two" bullet block) - prefilled into the
  // editable draft below, never sent as-is.
  initialConstraintsBlock: string
  onPromote: (constraints: string[]) => Promise<void>
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

// "Save these corrections for future runs": a real, human-confirmed,
// editable-before-confirming action promoting one successful attempt's own
// constraints into psm_agent's permanent config (see
// integration_runner/stages/psm/actions.py's own promote_constraints for
// the real gate this fronts) - only ever offered on a validated generation
// success (see PsmStagePanel's own canPromote check), never automatic
// capture of every typed correction. Its own file: a self-contained
// concern (its own local draft state, its own confirm/cancel actions)
// PSM-specific enough not to belong in design-system's own generic
// PromptBuilder/AttemptsBrowser groups.
export function PromoteConstraintsAction({ initialConstraintsBlock, onPromote }: PromoteConstraintsActionProps) {
  const [draft, setDraft] = useState<string | null>(null)

  if (draft === null) {
    return (
      <Button variant="secondary" size="sm" onClick={() => setDraft(initialConstraintsBlock)}>
        Save these corrections for future runs
      </Button>
    )
  }

  return (
    <div style={{ display: "flex", flexDirection: "column", gap: "var(--space-2)" }}>
      <p style={labelStyle}>
        These constraints produced this validated success — edit before confirming, then every future run of this
        preset starts with them already applied.
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
