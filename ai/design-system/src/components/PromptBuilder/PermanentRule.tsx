import type { DragEvent } from "react"
import { Button } from "../Button"
import { Icon } from "../Icon"

// How many lines a closed rule shows of its first sentence: enough to say what the rule is
// about, few enough that a dozen fit in the box (an engineering guess).
const CLOSED_LINES = 2

// What a folded line shows of a rule: its first sentence, with "…" when more follows, so
// the line reads as a title and not as text cut off mid-word. A sentence ends at . ! or ?
// followed by a capital, so "e.g. `def`" does not end one.
export function firstSentence(rule: string): string {
  const end = rule.search(/[.!?](?=\s+[A-Z])/)
  if (end === -1) return rule
  const sentence = rule.slice(0, end + 1)
  return sentence.length < rule.trimEnd().length ? `${sentence} …` : sentence
}

interface PermanentRuleProps {
  text: string
  open: boolean
  readOnly: boolean
  // The drop line drawn above or below this row while another row is dragged over it.
  dropAbove: boolean
  dropBelow: boolean
  onToggle: () => void
  onRemove: () => void
  onDragStart: () => void
  onDragEnd: () => void
  onDragOver: (event: DragEvent<HTMLLIElement>) => void
  onDrop: (event: DragEvent<HTMLLIElement>) => void
}

const DROP_LINE = "2px solid var(--brand)"
const NO_LINE = "2px solid transparent"

/** One permanent rule: a line that opens to its whole text, with a handle to drag it and a button to remove it. */
export function PermanentRule({ text, open, readOnly, dropAbove, dropBelow, onToggle, onRemove, onDragStart, onDragEnd, onDragOver, onDrop }: PermanentRuleProps) {
  return (
    <li
      onDragOver={onDragOver}
      onDrop={onDrop}
      style={{
        display: "flex",
        alignItems: open ? "flex-start" : "center",
        gap: "var(--space-2)",
        padding: "var(--space-1) 0",
        fontFamily: "var(--font-sans)",
        fontSize: "var(--text-xs)",
        color: "var(--text-body)",
        position: "relative",
        borderTop: dropAbove ? DROP_LINE : NO_LINE,
        borderBottom: dropBelow ? DROP_LINE : NO_LINE,
      }}
    >
      {!readOnly && (
        <span
          draggable
          onDragStart={onDragStart}
          onDragEnd={onDragEnd}
          style={{ cursor: "grab", display: "flex", flexShrink: 0, color: "var(--text-faint)" }}
          aria-label="Drag to reorder"
        >
          <Icon name="GripVertical" size={12} />
        </span>
      )}
      <button
        type="button"
        onClick={onToggle}
        aria-expanded={open}
        title={open ? "Show one line" : "Show the whole rule"}
        style={{
          flex: 1,
          minWidth: 0,
          display: "flex",
          alignItems: "baseline",
          gap: "var(--space-2)",
          padding: 0,
          border: 0,
          background: "none",
          cursor: "pointer",
          textAlign: "left",
          font: "inherit",
          color: "inherit",
        }}
      >
        {/* The arrow that says a line opens. */}
        <span aria-hidden style={{ flexShrink: 0, color: "var(--text-muted)" }}>
          {open ? "▾" : "▸"}
        </span>
        <span
          style={{
            flex: 1,
            minWidth: 0,
            overflowWrap: "anywhere",
            // The first sentence cut after a couple of lines, or the whole text as written.
            ...(open
              ? { whiteSpace: "pre-wrap" }
              : { overflow: "hidden", display: "-webkit-box", WebkitBoxOrient: "vertical", WebkitLineClamp: CLOSED_LINES }),
          }}
        >
          {open ? text : firstSentence(text)}
        </span>
      </button>
      {!readOnly && (
        <Button variant="ghost" size="sm" onClick={onRemove}>
          Remove
        </Button>
      )}
    </li>
  )
}
