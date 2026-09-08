import { useState } from "react"
import { Button } from "../Button"

interface LearnedConstraintsListProps {
  constraints: string[]
  readOnly?: boolean
  onAdd: (constraint: string) => void
  onRemove: (constraint: string) => void
}

// The permanent-constraints section: a config's own learned_constraints
// list, applied to every future run of this preset (see
// generation_toolkit.prompt_config.learned_constraints) - distinct enough
// from the rest of the builder (its own add/remove actions, its own input
// field) to earn its own file, the same one-concern-per-file split every
// other piece of this component group already has.
export function LearnedConstraintsList({ constraints, readOnly = false, onAdd, onRemove }: LearnedConstraintsListProps) {
  const [newConstraint, setNewConstraint] = useState("")

  const add = () => {
    if (!newConstraint.trim()) return
    onAdd(newConstraint.trim())
    setNewConstraint("")
  }

  return (
    <div style={{ display: "flex", flexDirection: "column", gap: "var(--space-2)" }}>
      <span
        style={{
          fontFamily: "var(--font-sans)",
          fontSize: "var(--text-xs)",
          fontWeight: "var(--weight-bold)",
          color: "var(--text-strong)",
        }}
      >
        Permanent constraints (apply to every future run of this preset)
      </span>
      {constraints.length === 0 && (
        <p style={{ fontFamily: "var(--font-sans)", fontSize: "var(--text-xs)", color: "var(--text-muted)", margin: 0 }}>
          None yet.
        </p>
      )}
      <ul style={{ margin: 0, padding: 0, listStyle: "none", display: "flex", flexDirection: "column", gap: "var(--space-1)" }}>
        {constraints.map((constraint) => (
          <li
            key={constraint}
            style={{
              display: "flex",
              alignItems: "center",
              gap: "var(--space-2)",
              fontFamily: "var(--font-sans)",
              fontSize: "var(--text-xs)",
              color: "var(--text-body)",
            }}
          >
            <span style={{ flex: 1 }}>{constraint}</span>
            {!readOnly && (
              <Button variant="ghost" size="sm" onClick={() => onRemove(constraint)}>
                Remove
              </Button>
            )}
          </li>
        ))}
      </ul>
      {!readOnly && (
        <div style={{ display: "flex", gap: "var(--space-2)" }}>
          <input
            className="orch-field"
            type="text"
            value={newConstraint}
            onChange={(e) => setNewConstraint(e.target.value)}
            placeholder="Add a permanent constraint"
            style={{
              flex: 1,
              border: "1px solid var(--border-default)",
              borderRadius: "var(--radius-md)",
              padding: "var(--space-2) var(--space-3)",
              fontFamily: "var(--font-sans)",
              fontSize: "var(--text-sm)",
              background: "var(--surface-card)",
              color: "var(--text-body)",
            }}
          />
          <Button variant="secondary" size="sm" onClick={add}>
            Add
          </Button>
        </div>
      )}
    </div>
  )
}
