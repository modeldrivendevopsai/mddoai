import { useState } from "react"
import type { DragEvent } from "react"
import { Button } from "../Button"
import { Icon } from "../Icon"

interface LearnedConstraintsListProps {
  constraints: string[]
  readOnly?: boolean
  onAdd: (constraint: string) => void
  onRemove: (constraint: string) => void
  // Pure local reorder - like attachment reordering, this only updates
  // config.learned_constraints in memory, persisted by the existing Save
  // button, never a network call of its own. Deliberately NOT wired
  // through onAdd/onRemove's own backend endpoints: those two reload the
  // config fresh from disk before writing (see
  // generation_toolkit.prompt_config.learned_constraints), so calling them
  // for a reorder would silently drop the very reorder it's meant to
  // persist the next time either fires.
  onReorder: (constraints: string[]) => void
}

// The permanent-constraints section: a config's own learned_constraints
// list, applied to every future run of this preset (see
// generation_toolkit.prompt_config.learned_constraints) - distinct enough
// from the rest of the builder (its own add/remove actions, its own input
// field) to earn its own file, the same one-concern-per-file split every
// other piece of this component group already has.
export function LearnedConstraintsList({ constraints, readOnly = false, onAdd, onRemove, onReorder }: LearnedConstraintsListProps) {
  const [newConstraint, setNewConstraint] = useState("")
  const [draggedIndex, setDraggedIndex] = useState<number | null>(null)
  const [dropIndex, setDropIndex] = useState<number | null>(null)

  const add = () => {
    if (!newConstraint.trim()) return
    onAdd(newConstraint.trim())
    setNewConstraint("")
  }

  const clearDrag = () => {
    setDraggedIndex(null)
    setDropIndex(null)
  }

  const handleDragOver = (event: DragEvent<HTMLLIElement>, index: number) => {
    event.preventDefault()
    const rect = event.currentTarget.getBoundingClientRect()
    setDropIndex(event.clientY < rect.top + rect.height / 2 ? index : index + 1)
  }

  const handleDrop = (event: DragEvent<HTMLLIElement>) => {
    event.preventDefault()
    if (draggedIndex === null || dropIndex === null || dropIndex === draggedIndex) {
      clearDrag()
      return
    }
    const copy = [...constraints]
    const [moved] = copy.splice(draggedIndex, 1)
    copy.splice(dropIndex > draggedIndex ? dropIndex - 1 : dropIndex, 0, moved)
    onReorder(copy)
    clearDrag()
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
      <ul style={{ margin: 0, padding: 0, listStyle: "none", display: "flex", flexDirection: "column" }}>
        {constraints.map((constraint, index) => (
          <li
            key={index}
            onDragOver={(e) => handleDragOver(e, index)}
            onDrop={handleDrop}
            style={{
              display: "flex",
              alignItems: "center",
              gap: "var(--space-2)",
              padding: "var(--space-1) 0",
              fontFamily: "var(--font-sans)",
              fontSize: "var(--text-xs)",
              color: "var(--text-body)",
              position: "relative",
              borderTop: dropIndex === index ? "2px solid var(--brand)" : "2px solid transparent",
              borderBottom:
                dropIndex === constraints.length && index === constraints.length - 1
                  ? "2px solid var(--brand)"
                  : "2px solid transparent",
            }}
          >
            {!readOnly && (
              <span
                draggable
                onDragStart={() => setDraggedIndex(index)}
                onDragEnd={clearDrag}
                style={{ cursor: "grab", display: "flex", flexShrink: 0, color: "var(--text-faint)" }}
                aria-label="Drag to reorder"
              >
                <Icon name="GripVertical" size={12} />
              </span>
            )}
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
