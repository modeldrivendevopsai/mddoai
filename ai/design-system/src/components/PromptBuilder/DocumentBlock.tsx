import { useState } from "react"
import type { DragEvent } from "react"
import { Button } from "../Button"
import { Icon } from "../Icon"
import { AttachmentPreview } from "./AttachmentPreview"
import { ContextAttachmentFields, ContextChip } from "./ContextAttachment"
import { fieldLabelStyle, inputStyle } from "./fieldStyles"
import { FileAttachmentFields, FileChip } from "./FileAttachment"
import { TextBlock } from "./TextBlock"
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
  // A real backend upload endpoint for a "file" attachment's own picker
  // below - the only way to attach a real OS file was previously
  // drag-and-drop onto the document as a whole, with no browse button
  // anywhere in this picker itself. Optional, same precedent as
  // onPreviewAttachment: not every caller has a real upload endpoint yet.
  onUploadFile?: (file: File) => Promise<string>
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
// is prose a human actually wrote (see TextBlock.tsx), rendered like the
// system prompt itself: a small label, then the content always visible
// right there, no click needed to read it. "file" and "context" attachments
// (see FileAttachment.tsx/ContextAttachment.tsx) have no prose of their own
// to show inline (a real file's raw content, or auto-filled data only known
// at run time), so each renders as a small, non-expanding reference chip -
// the real ai-research prompts reference a file by name inline too, never
// paste it into the document. Every shape shares the identical drag/drop
// mechanics (onDragStart/onHoverEdge/onDrop, computed here since this block
// owns its own bounding rect) and the up/down buttons, kept for
// keyboard/no-drag accessibility. This file itself owns only that shared
// chrome plus the type-agnostic "Name" field, "Detach into editable text"
// button, and the drag insertion line - everything type-specific about a
// "file" or "context" attachment's own expanded picker lives in its own
// file, one concern each, the same split every other file in this folder
// already follows.
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
  onUploadFile,
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
            <FileAttachmentFields
              attachment={attachment}
              availableFiles={availableFiles}
              readOnly={readOnly}
              onUploadFile={onUploadFile}
              onChange={onChange}
            />
          )}

          {attachment.type === "context" && (
            <ContextAttachmentFields attachment={attachment} contextKeyOptions={contextKeyOptions} readOnly={readOnly} onChange={onChange} />
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
