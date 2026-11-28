import { useState } from "react"
import { Icon } from "../Icon"
import { AddPermanentRule } from "./AddPermanentRule"
import { PermanentRule } from "./PermanentRule"
import { useRuleReorder } from "./useRuleReorder"

export { firstSentence } from "./PermanentRule"

interface LearnedConstraintsListProps {
  constraints: string[]
  readOnly?: boolean
  onAdd: (constraint: string) => void
  onRemove: (constraint: string) => void
  // A real, separate network call (index.tsx's own reorderConstraints),
  // the same as onAdd/onRemove - constraints live in their own separate,
  // never-reverted store, so a reorder needs its own real persistence, not
  // a local-only update an autosave would never pick up (see
  // generation_toolkit.prompt_config.learned_constraints.reorder_learned_constraints).
  onReorder: (constraints: string[]) => void
}

// The most the list may grow before it scrolls inside its own box. About six rules at
// two lines each: enough to scan, short enough that the rest of the panel stays within a
// screen (an engineering guess).
const MAX_LIST_HEIGHT = "18rem"

// The permanent-rules section (the code calls them learned constraints): a config's own learned_constraints
// list, applied to every future run (see
// generation_toolkit.prompt_config.learned_constraints) - distinct enough
// from the rest of the builder (its own add/remove actions, its own input
// field) to earn its own file, the same one-concern-per-file split every
// other piece of this component group already has.
//
// It is long (a rule can run to a few thousand characters, and there can be
// dozens), so it is folded away until asked for, and open it shows one line per
// rule (its first sentence, up to a couple of lines) in a box that scrolls. A line
// opens to its whole text.
export function LearnedConstraintsList({ constraints, readOnly = false, onAdd, onRemove, onReorder }: LearnedConstraintsListProps) {
  // Which rules show their whole text. By text, not position, so a reorder or a
  // removal does not move the open state onto another rule.
  const [expanded, setExpanded] = useState<Set<string>>(new Set())
  const reorder = useRuleReorder(constraints, onReorder)

  const toggle = (rule: string) => {
    setExpanded((current) => {
      const next = new Set(current)
      if (!next.delete(rule)) next.add(rule)
      return next
    })
  }

  return (
    <details>
      <summary
        title="Permanent rules are used in every draft"
        style={{
          display: "flex",
          alignItems: "center",
          gap: "var(--space-2)",
          cursor: "pointer",
          fontFamily: "var(--font-sans)",
          fontSize: "var(--text-xs)",
          fontWeight: "var(--weight-bold)",
          color: "var(--text-strong)",
        }}
      >
        <span style={{ display: "inline-flex", color: "var(--brand)" }}>
          <Icon name="Infinity" size={13} />
        </span>
        Permanent rules ({constraints.length})
      </summary>
      <div style={{ display: "flex", flexDirection: "column", gap: "var(--space-2)", paddingTop: "var(--space-2)" }}>
        {constraints.length === 0 && (
          <p style={{ fontFamily: "var(--font-sans)", fontSize: "var(--text-xs)", color: "var(--text-muted)", margin: 0 }}>
            None yet.
          </p>
        )}
        <ul
          role="list"
          style={{
            margin: 0,
            padding: 0,
            listStyle: "none",
            display: "flex",
            flexDirection: "column",
            maxHeight: MAX_LIST_HEIGHT,
            overflowY: "auto",
          }}
        >
          {constraints.map((rule, index) => (
            <PermanentRule
              key={index}
              text={rule}
              open={expanded.has(rule)}
              readOnly={readOnly}
              dropAbove={reorder.dropIndex === index}
              dropBelow={reorder.dropIndex === constraints.length && index === constraints.length - 1}
              onToggle={() => toggle(rule)}
              onRemove={() => onRemove(rule)}
              onDragStart={() => reorder.start(index)}
              onDragEnd={reorder.end}
              onDragOver={(event) => reorder.over(event, index)}
              onDrop={reorder.drop}
            />
          ))}
        </ul>
        {!readOnly && <AddPermanentRule onAdd={onAdd} />}
      </div>
    </details>
  )
}
