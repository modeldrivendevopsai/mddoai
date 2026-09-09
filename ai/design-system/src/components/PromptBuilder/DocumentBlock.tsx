import { useLayoutEffect, useRef, useState } from "react"
import type { CSSProperties, DragEvent, ReactNode } from "react"
import { Button } from "../Button"
import { Icon } from "../Icon"
import { CodeBlock } from "../../CodeBlock"
import { estimateTokens } from "./tokenEstimate"
import type { Attachment } from "./types"

interface DocumentBlockProps {
  attachment: Attachment
  isFirst: boolean
  isLast: boolean
  readOnly?: boolean
  brokenError?: string
  availableFiles?: string[]
  contextKeyOptions: { key: string; label: string }[]
  // Real resolved content for a "file"/"context" attachment (the exact
  // text build_prompt() would fold into the assembled prompt), fetched on
  // demand when the block is expanded - undefined means "not fetched (or
  // this attachment doesn't resolve to anything) yet," not "empty."
  onPreviewAttachment?: (id: string) => Promise<string | undefined>
  // Which edge of this block a drag is currently hovering, so
  // PromptDocument's own insertion point can be shown right where it would
  // land - null when no drag is over this block at all.
  dropEdge: "before" | "after" | null
  onChange: (attachment: Attachment) => void
  onRemove: () => void
  onMoveUp: () => void
  onMoveDown: () => void
  onDragStart: () => void
  onDragEnd: () => void
  // event.currentTarget's own bounding rect decides "before" vs "after" -
  // computed here (this block owns its own layout), reported up rather than
  // recomputed in the document, which has no easy access to this element.
  onHoverEdge: (edge: "before" | "after") => void
  onDrop: (event: DragEvent<HTMLDivElement>) => void
}

// Three real visual shapes, one drag/drop mechanism. A "text" attachment
// is prose a human actually wrote - a heading plus its own explanation,
// exactly like a real ai-research prompt.md section ("Reference: GitHub
// Actions PSM metamodel... Use it as a structural reference...") - so it
// renders like the system prompt itself: a small label, then the content
// always visible right there, no click needed to read it. "file" and
// "context" attachments have no prose of their own to show inline (a real
// file's raw content, or a pipeline value only known at run time), so
// each renders as a small, non-expanding reference chip - the real
// ai-research prompts reference a file by name inline too, never paste it
// into the document. Every shape shares the identical drag/drop mechanics
// (onDragStart/onHoverEdge/onDrop, computed here since this block owns its
// own bounding rect) and the up/down buttons, kept for keyboard/no-drag
// accessibility.
export function DocumentBlock({
  attachment,
  isFirst,
  isLast,
  readOnly = false,
  brokenError,
  availableFiles,
  contextKeyOptions,
  dropEdge,
  onChange,
  onRemove,
  onMoveUp,
  onMoveDown,
  onDragStart,
  onDragEnd,
  onHoverEdge,
  onPreviewAttachment,
  onDrop,
}: DocumentBlockProps) {
  const [pickerOpen, setPickerOpen] = useState(false)

  const handleDragOver = (event: DragEvent<HTMLDivElement>) => {
    event.preventDefault()
    const rect = event.currentTarget.getBoundingClientRect()
    onHoverEdge(event.clientY < rect.top + rect.height / 2 ? "before" : "after")
  }

  const dragHandle = !readOnly && (
    <span
      draggable
      onDragStart={(e) => {
        e.stopPropagation()
        onDragStart()
      }}
      onDragEnd={onDragEnd}
      style={{ cursor: "grab", display: "flex", flexShrink: 0, color: "var(--text-faint)" }}
      aria-label="Drag to reorder"
    >
      <Icon name="GripVertical" size={14} />
    </span>
  )

  const controls = (
    <>
      {brokenError && <Icon name="AlertTriangle" size={14} style={{ color: "var(--danger-500)", flexShrink: 0 }} />}
      {!readOnly && (
        <div style={{ display: "flex", gap: 2, flexShrink: 0 }}>
          <Button variant="ghost" size="sm" disabled={isFirst} onClick={onMoveUp} aria-label="Move up">
            <Icon name="ChevronUp" size={14} />
          </Button>
          <Button variant="ghost" size="sm" disabled={isLast} onClick={onMoveDown} aria-label="Move down">
            <Icon name="ChevronDown" size={14} />
          </Button>
          <Button variant="ghost" size="sm" onClick={onRemove} aria-label="Remove attachment">
            <Icon name="Trash2" size={14} style={{ color: "var(--danger-500)" }} />
          </Button>
        </div>
      )}
    </>
  )

  return (
    <div
      onDragOver={handleDragOver}
      onDrop={onDrop}
      style={{
        borderTop: isFirst ? "none" : "1px solid var(--border-subtle)",
        position: "relative",
        padding: "var(--space-2) 0",
      }}
    >
      {dropEdge === "before" && <InsertionLine edge="top" />}

      {attachment.type === "text" ? (
        <TextBlock
          attachment={attachment}
          readOnly={readOnly}
          isSystemPrompt={isFirst}
          dragHandle={dragHandle}
          controls={controls}
          onChange={onChange}
        />
      ) : (
        <div style={{ display: "flex", alignItems: "center", gap: "var(--space-2)" }}>
          {dragHandle}
          {/* flex: 1 + justifyContent: center, not the chip sitting right
              after the drag handle: this is what keeps the chip centered
              in the row and, just as importantly, pushes the move/remove
              controls all the way to the right edge - the same right
              alignment TextBlock's own controls already get for free from
              its input's own flex: 1. */}
          <div style={{ flex: 1, display: "flex", justifyContent: "center", minWidth: 0 }}>
            {attachment.type === "file" ? (
              <FileChip attachment={attachment} brokenError={brokenError} expanded={pickerOpen} onToggle={() => setPickerOpen((e) => !e)} />
            ) : (
              <ContextChip
                attachment={attachment}
                brokenError={brokenError}
                expanded={pickerOpen}
                onToggle={() => setPickerOpen((e) => !e)}
              />
            )}
          </div>
          {controls}
        </div>
      )}

      {pickerOpen && attachment.type !== "text" && (
        <div
          style={{
            marginTop: "var(--space-2)",
            marginLeft: "var(--space-6)",
            display: "flex",
            flexDirection: "column",
            gap: "var(--space-2)",
          }}
        >
          {brokenError && (
            <div
              style={{
                fontFamily: "var(--font-sans)",
                fontSize: "var(--text-xs)",
                color: "var(--danger-500)",
                background: "var(--danger-100)",
                border: "1px solid var(--danger-border)",
                borderRadius: "var(--radius-sm)",
                padding: "var(--space-2)",
              }}
            >
              {brokenError}
            </div>
          )}

          <label style={fieldLabelStyle}>
            Name
            <input
              className="orch-field"
              type="text"
              value={attachment.name}
              disabled={readOnly}
              onChange={(e) => onChange({ ...attachment, name: e.target.value })}
              style={inputStyle}
            />
          </label>

          {attachment.type === "file" && (
            <label style={fieldLabelStyle}>
              Real file
              {availableFiles ? (
                <select
                  className="orch-field"
                  value={attachment.path ?? ""}
                  disabled={readOnly}
                  onChange={(e) => onChange({ ...attachment, path: e.target.value })}
                  style={inputStyle}
                >
                  <option value="" disabled>
                    Select a real file
                  </option>
                  {availableFiles.map((path) => (
                    <option key={path} value={path}>
                      {path}
                    </option>
                  ))}
                </select>
              ) : (
                <input className="orch-field" type="text" value={attachment.path ?? ""} disabled style={inputStyle} />
              )}
            </label>
          )}

          {attachment.type === "context" && (
            <label style={fieldLabelStyle}>
              Pipeline value
              <select
                className="orch-field"
                value={attachment.key ?? ""}
                disabled={readOnly}
                onChange={(e) => onChange({ ...attachment, key: e.target.value })}
                style={inputStyle}
              >
                <option value="" disabled>
                  Select a real pipeline value
                </option>
                {contextKeyOptions.map((option) => (
                  <option key={option.key} value={option.key}>
                    {option.label}
                  </option>
                ))}
              </select>
            </label>
          )}

          {onPreviewAttachment && <AttachmentPreview attachmentId={attachment.id} onPreviewAttachment={onPreviewAttachment} />}

          {!readOnly && (
            <Button
              variant="secondary"
              size="sm"
              onClick={() => onChange({ id: attachment.id, name: attachment.name, type: "text", content: "" })}
            >
              Detach into editable text
            </Button>
          )}
        </div>
      )}

      {dropEdge === "after" && <InsertionLine edge="bottom" />}
    </div>
  )
}

// A "text" attachment is prose - a heading plus its own always-visible
// content, no accordion, nothing to click to read it. The name field
// doubles as that heading, edited in place rather than behind a separate
// "expand to rename" step. isSystemPrompt marks the one real structural
// rule this document has (see PromptDocument.tsx's own docstring): the
// FIRST text block is the LLM's system message, everything else becomes
// the user message - dragging a different text block above it changes
// which one plays that role, so this is just a live label, not a fixed
// field.
function TextBlock({
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

// A "file"/"context" chip's own real resolved content, fetched on demand
// (not automatically for every chip on the page) once its picker is
// expanded - the same real text build_prompt() folds into the assembled
// prompt, not a re-derived summary of it.
function AttachmentPreview({
  attachmentId,
  onPreviewAttachment,
}: {
  attachmentId: string
  onPreviewAttachment: (id: string) => Promise<string | undefined>
}) {
  const [content, setContent] = useState<string | null>(null)
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState<string | null>(null)

  const load = () => {
    setLoading(true)
    setError(null)
    onPreviewAttachment(attachmentId)
      .then((result) => setContent(result ?? "(nothing resolved for this attachment)"))
      .catch((e) => setError(e instanceof Error ? e.message : "Preview failed."))
      .finally(() => setLoading(false))
  }

  return (
    <div style={{ display: "flex", flexDirection: "column", gap: "var(--space-2)" }}>
      {/* Stays visible even once content is loaded, not replaced by the
          CodeBlock below - the parent's own cache (see index.tsx's
          loadAttachmentPreview) only clears after a real save, so this is
          the one way a human can force a fresh look mid-session, e.g.
          right after saving a config edit. */}
      <Button variant="ghost" size="sm" onClick={load} disabled={loading}>
        {loading ? "Loading…" : content !== null ? "Refresh real content" : "Show real content"}
      </Button>
      {error && (
        <p style={{ fontFamily: "var(--font-sans)", fontSize: "var(--text-2xs)", color: "var(--danger-500)", margin: 0 }}>
          {error}
        </p>
      )}
      {content !== null && <CodeBlock code={content} title="real resolved content" lang="text" />}
    </div>
  )
}

// Grows to fit its own content, no internal scrollbar and no manual
// resize handle - a long text block (a real docs dump, say) should make
// the whole document taller and let ITS OWN scroll container handle it,
// never trap the human scrolling inside a small nested box. Height is
// measured for real (scrollHeight) rather than estimated from a newline
// count, so a long wrapped line (no "\n" of its own) still grows the box
// correctly, not just a line actually broken with Enter.
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

// The compact "embed" look a real file reference gets: an icon square,
// filename, and its real path, in a bordered card that doesn't stretch to
// the document's full width - the real ai-research prompt.md files
// reference a file by name inline, never paste its content into the
// document. Click opens the "which real file" picker below.
function FileChip({
  attachment,
  brokenError,
  expanded,
  onToggle,
}: {
  attachment: Attachment
  brokenError?: string
  expanded: boolean
  onToggle: () => void
}) {
  return (
    <div onClick={onToggle} style={chipStyle(expanded, brokenError)}>
      <div style={chipIconStyle}>
        <Icon name="FileText" size={14} style={{ color: "var(--brand)" }} />
      </div>
      <div style={{ display: "flex", flexDirection: "column", overflow: "hidden", minWidth: 0 }}>
        <span style={chipTitleStyle}>{attachment.name || "(untitled)"}</span>
        <span style={chipSubtitleStyle}>{attachment.path || "No file selected"}</span>
      </div>
    </div>
  )
}

// Same chip family as FileChip, for a pipeline value: there's no file on
// disk to name, just which real pipeline output this slot resolves to at
// generation time (see StagePanelProps' own manifest.contextKeyOptions).
function ContextChip({
  attachment,
  brokenError,
  expanded,
  onToggle,
}: {
  attachment: Attachment
  brokenError?: string
  expanded: boolean
  onToggle: () => void
}) {
  return (
    <div onClick={onToggle} style={chipStyle(expanded, brokenError)}>
      <div style={chipIconStyle}>
        <Icon name="Zap" size={14} style={{ color: "var(--brand)" }} />
      </div>
      <div style={{ display: "flex", flexDirection: "column", overflow: "hidden", minWidth: 0 }}>
        <span style={chipTitleStyle}>{attachment.name || "(untitled)"}</span>
        <span style={chipSubtitleStyle}>Pipeline value</span>
      </div>
    </div>
  )
}

function chipStyle(expanded: boolean, brokenError?: string): CSSProperties {
  return {
    display: "inline-flex",
    alignItems: "center",
    gap: "var(--space-2)",
    maxWidth: 360,
    border: `1px solid ${brokenError ? "var(--danger-500)" : "var(--border-default)"}`,
    borderRadius: "var(--radius-md)",
    padding: "var(--space-2) var(--space-3)",
    background: expanded ? "var(--surface-sunken)" : "var(--surface-card)",
    cursor: "pointer",
  }
}

const chipIconStyle: CSSProperties = {
  width: 28,
  height: 28,
  flexShrink: 0,
  borderRadius: "var(--radius-sm)",
  background: "var(--brand-faint)",
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
}

const chipTitleStyle: CSSProperties = {
  fontFamily: "var(--font-sans)",
  fontSize: "var(--text-sm)",
  fontWeight: "var(--weight-semibold)",
  color: "var(--text-strong)",
  overflow: "hidden",
  textOverflow: "ellipsis",
  whiteSpace: "nowrap",
}

const chipSubtitleStyle: CSSProperties = {
  fontFamily: "var(--font-mono)",
  fontSize: "var(--text-2xs)",
  color: "var(--text-muted)",
  overflow: "hidden",
  textOverflow: "ellipsis",
  whiteSpace: "nowrap",
}

function InsertionLine({ edge }: { edge: "top" | "bottom" }) {
  return (
    <div
      style={{
        position: "absolute",
        [edge]: -1,
        left: 0,
        right: 0,
        height: 2,
        background: "var(--brand)",
        borderRadius: 1,
        zIndex: 1,
      }}
    />
  )
}

const fieldLabelStyle = {
  display: "flex",
  flexDirection: "column",
  gap: "var(--space-1)",
  fontFamily: "var(--font-sans)",
  fontSize: "var(--text-xs)",
  fontWeight: "var(--weight-semibold)",
  color: "var(--text-strong)",
} as const

const inputStyle = {
  width: "100%",
  boxSizing: "border-box",
  border: "1px solid var(--border-default)",
  borderRadius: "var(--radius-md)",
  padding: "var(--space-2) var(--space-3)",
  fontFamily: "var(--font-sans)",
  fontSize: "var(--text-sm)",
  background: "var(--surface-card)",
  color: "var(--text-body)",
} as const
