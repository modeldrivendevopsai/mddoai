import { useLayoutEffect, useRef } from "react"
import type { CSSProperties, ReactNode } from "react"
import { estimateTokens } from "./tokenEstimate"
import type { Attachment } from "./types"

// A "text" attachment is prose - a heading plus its own always-visible
// content, no accordion, nothing to click to read it. The name field
// doubles as that heading, edited in place rather than behind a separate
// "expand to rename" step. isSystemPrompt marks the one real structural
// rule this document has (see PromptDocument.tsx's own docstring): the
// FIRST text block is the LLM's system message, everything else becomes
// the user message - dragging a different text block above it changes
// which one plays that role, so this is just a live label, not a fixed
// field.
export function TextBlock({
  attachment,
  readOnly,
  isSystemPrompt,
  dragHandle,
  controls,
  onChange,
}: {
  attachment: Attachment
  readOnly: boolean
  isSystemPrompt: boolean
  dragHandle: ReactNode
  controls: ReactNode
  onChange: (attachment: Attachment) => void
}) {
  const tokenEstimate = estimateTokens(attachment.content ?? "")

  return (
    <div style={{ display: "flex", flexDirection: "column", gap: "var(--space-1)" }}>
      <div style={{ display: "flex", alignItems: "center", gap: "var(--space-2)" }}>
        {dragHandle}
        {isSystemPrompt && (
          <span
            style={{
              fontFamily: "var(--font-mono)",
              fontSize: "var(--text-2xs)",
              color: "var(--brand)",
              background: "var(--brand-faint)",
              padding: "2px 8px",
              borderRadius: "var(--radius-pill)",
              flexShrink: 0,
            }}
          >
            System message
          </span>
        )}
        {/* No placeholder text: text-transform: uppercase below applies to
            placeholder text too, which turned a real instructional
            sentence into a shouty, chaotic-looking wall of caps. Truly
            optional means truly blank when empty, not filled with
            styled-as-if-real guidance text. */}
        <input
          type="text"
          value={attachment.name}
          disabled={readOnly}
          onChange={(e) => onChange({ ...attachment, name: e.target.value })}
          title="Optional heading - leave blank for plain connecting text"
          style={{
            flex: 1,
            minWidth: 0,
            border: "none",
            background: "transparent",
            padding: 0,
            fontFamily: "var(--font-sans)",
            fontSize: "var(--text-xs)",
            fontWeight: "var(--weight-semibold)",
            color: "var(--text-faint)",
            textTransform: "uppercase",
            letterSpacing: "0.04em",
          }}
        />
        <span style={{ fontFamily: "var(--font-mono)", fontSize: "var(--text-2xs)", color: "var(--text-faint)", flexShrink: 0 }}>
          ~{tokenEstimate} tok
        </span>
        {controls}
      </div>
      <AutoGrowTextarea
        value={attachment.content ?? ""}
        disabled={readOnly}
        onChange={(value) => onChange({ ...attachment, content: value })}
        placeholder="Write this section's own text - it appears in the assembled prompt exactly here, in this order."
        style={{
          // calc(), not width: 100% - a sibling marginLeft still ADDS to a
          // 100%-of-parent width, pushing the real right edge past the
          // parent by exactly that margin (confirmed live - this was the
          // real cause of this document horizontally overflowing/scrolling).
          width: "calc(100% - var(--space-6))",
          boxSizing: "border-box",
          border: "none",
          padding: 0,
          marginLeft: "var(--space-6)",
          fontFamily: "var(--font-sans)",
          fontSize: "var(--text-sm)",
          background: "transparent",
          color: "var(--text-body)",
        }}
      />
    </div>
  )
}

// Grows to fit its own content, no internal scrollbar and no manual
// resize handle - a long text block (a real docs dump, say) should make
// the whole document taller and let ITS OWN scroll container handle it,
// never trap the human scrolling inside a small nested box. Height is
// measured for real (scrollHeight) rather than estimated from a newline
// count, so a long wrapped line (no "\n" of its own) still grows the box
// correctly, not just a line actually broken with Enter. Private to
// TextBlock - no other attachment type has free-form prose of its own to
// grow around.
function AutoGrowTextarea({
  value,
  disabled,
  onChange,
  placeholder,
  style,
}: {
  value: string
  disabled?: boolean
  onChange: (value: string) => void
  placeholder?: string
  style: CSSProperties
}) {
  const ref = useRef<HTMLTextAreaElement>(null)

  useLayoutEffect(() => {
    const el = ref.current
    if (!el) return
    el.style.height = "auto"
    el.style.height = `${el.scrollHeight}px`
  }, [value])

  return (
    <textarea
      ref={ref}
      className="orch-field"
      value={value}
      disabled={disabled}
      onChange={(e) => onChange(e.target.value)}
      placeholder={placeholder}
      rows={2}
      style={{ ...style, overflow: "hidden", resize: "none" }}
    />
  )
}
