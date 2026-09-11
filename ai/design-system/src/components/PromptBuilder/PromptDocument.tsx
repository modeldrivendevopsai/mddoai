import { useState } from "react"
import type { DragEvent } from "react"
import { Button } from "../Button"
import { DocumentBlock } from "./DocumentBlock"
import { LearnedConstraintsList } from "./LearnedConstraintsList"
import type { Attachment, AttachmentType, BrokenReference } from "./types"

interface PromptDocumentProps {
  attachments: Attachment[]
  attachmentTypes: AttachmentType[]
  contextKeyOptions: { key: string; label: string }[]
  availableFiles?: string[]
  broken: BrokenReference[]
  readOnly?: boolean
  // Real backend upload (see routes/uploads.py) - when given, a dropped OS
  // file is saved for real and becomes a "file" attachment referencing it.
  // Without it, a dropped file still becomes a "text" attachment (its
  // content copied in client-side), the only option before a caller has a
  // real upload endpoint behind it.
  onUploadFile?: (file: File) => Promise<string>
  // Real resolved content for one "file"/"context" attachment, by id - see
  // DocumentBlock's own AttachmentPreview for where this is actually used.
  onPreviewAttachment?: (id: string) => Promise<string | undefined>
  onAttachmentsChange: (attachments: Attachment[]) => void
  // Rendered as this same document's own trailing section, not a
  // separately-styled panel below it: the real assembled prompt
  // (prompt_builder.build_prompt) always appends constraints as the very
  // last part of the real user message, after every attachment, so this
  // is where they visually belong too - the one part of "the real prompt"
  // that isn't itself an attachment (a permanent, promoted lesson applying
  // to every future run, not one block's own content).
  learnedConstraints: string[]
  onAddConstraint: (constraint: string) => void
  onRemoveConstraint: (constraint: string) => void
  onReorderConstraints: (constraints: string[]) => void
}

let _nextLocalId = 1
function newLocalId(): string {
  return `attachment_${Date.now()}_${_nextLocalId++}`
}

function defaultAttachment(type: AttachmentType): Attachment {
  const id = newLocalId()
  if (type === "text") return { id, name: "New text", type, content: "" }
  if (type === "file") return { id, name: "New file reference", type, path: "" }
  return { id, name: "New pipeline value", type, key: "" }
}

// The real, single "document" a human builds from scratch: every
// attachment in ONE continuous, ordered flow, matching the real
// ai-research prompt.md files' own shape (a reference/attachment appears
// inline, right where it's used in the document). There's no separate,
// pre-existing "system prompt" field: the first "text" attachment IS the
// system message (see generation_toolkit.prompt_config.resolution's own
// resolve_for_call) - add one like any other block, drag a different text
// block above it to make that one the system message instead. Owns the
// one real drag-and-drop mechanism this needs: reordering an existing
// block (drag its own grip handle) and inserting a dropped OS file at the
// exact hovered position, both computed from the same dropIndex - an
// insertion point in the 0..attachments.length range, tracked here since
// only the document as a whole knows every block's position relative to
// the others.
export function PromptDocument({
  attachments,
  attachmentTypes,
  contextKeyOptions,
  availableFiles,
  broken,
  readOnly = false,
  onUploadFile,
  onPreviewAttachment,
  onAttachmentsChange,
  learnedConstraints,
  onAddConstraint,
  onRemoveConstraint,
  onReorderConstraints,
}: PromptDocumentProps) {
  const [draggedIndex, setDraggedIndex] = useState<number | null>(null)
  const [dropIndex, setDropIndex] = useState<number | null>(null)
  const brokenById = new Map(broken.map((b) => [b.id, b.error]))

  const update = (index: number, next: Attachment) => {
    const copy = [...attachments]
    copy[index] = next
    onAttachmentsChange(copy)
  }

  const remove = (index: number) => onAttachmentsChange(attachments.filter((_, i) => i !== index))

  const move = (index: number, delta: number) => {
    const target = index + delta
    if (target < 0 || target >= attachments.length) return
    const copy = [...attachments]
    ;[copy[index], copy[target]] = [copy[target], copy[index]]
    onAttachmentsChange(copy)
  }

  const add = (type: AttachmentType) => onAttachmentsChange([...attachments, defaultAttachment(type)])

  const clearDrag = () => {
    setDraggedIndex(null)
    setDropIndex(null)
  }

  const insertFilesAt = (files: File[], index: number) => {
    files.forEach((file, offset) => {
      if (onUploadFile) {
        // A real backend upload: the file is actually saved, and becomes a
        // real "file" attachment referencing it, not a client-side-only
        // copy of its content.
        onUploadFile(file).then((path) => {
          const copy = [...attachments]
          copy.splice(index + offset, 0, { id: newLocalId(), name: file.name, type: "file", path })
          onAttachmentsChange(copy)
        })
        return
      }
      const reader = new FileReader()
      reader.onload = () => {
        const content = typeof reader.result === "string" ? reader.result : ""
        const copy = [...attachments]
        copy.splice(index + offset, 0, { id: newLocalId(), name: file.name, type: "text", content })
        onAttachmentsChange(copy)
      }
      reader.readAsText(file)
    })
  }

  const handleDropAt = (event: DragEvent<HTMLDivElement>, fallbackIndex: number) => {
    event.preventDefault()
    const index = dropIndex ?? fallbackIndex
    if (event.dataTransfer.files.length > 0) {
      insertFilesAt(Array.from(event.dataTransfer.files), index)
    } else if (draggedIndex !== null && draggedIndex !== index) {
      const copy = [...attachments]
      const [moved] = copy.splice(draggedIndex, 1)
      copy.splice(index > draggedIndex ? index - 1 : index, 0, moved)
      onAttachmentsChange(copy)
    }
    clearDrag()
  }

  return (
    <div
      style={{
        border: "1px solid var(--border-default)",
        borderRadius: "var(--radius-md)",
        background: "var(--surface-card)",
        padding: "var(--space-3)",
        display: "flex",
        flexDirection: "column",
        minWidth: 0,
        overflowX: "hidden",
      }}
    >
      {attachments.length === 0 && (
        <div
          onDragOver={(e) => {
            e.preventDefault()
            setDropIndex(0)
          }}
          onDrop={(e) => handleDropAt(e, 0)}
          style={{
            padding: "var(--space-3)",
            border: dropIndex === 0 ? "2px dashed var(--brand)" : "2px dashed var(--border-subtle)",
            borderRadius: "var(--radius-md)",
          }}
        >
          <p style={{ fontFamily: "var(--font-sans)", fontSize: "var(--text-xs)", color: "var(--text-muted)", margin: 0 }}>
            Nothing here yet - add a text block below to start writing (the first one becomes the system message), or
            drop a file.
          </p>
        </div>
      )}

      {attachments.map((attachment, index) => {
        // dropIndex is a single insertion point in the 0..length range -
        // rendered as "before" this block when it lands exactly here, or
        // "after" only on the very last block when it's the trailing
        // position (length), so one drop point never draws two lines
        // (this block's "after" and the next block's "before" would
        // otherwise both be true for the same point).
        const dropEdge: "before" | "after" | null =
          dropIndex === null
            ? null
            : dropIndex === index
              ? "before"
              : dropIndex === attachments.length && index === attachments.length - 1
                ? "after"
                : null

        return (
          <DocumentBlock
            key={attachment.id}
            attachment={attachment}
            isFirst={index === 0}
            isLast={index === attachments.length - 1}
            readOnly={readOnly}
            brokenError={brokenById.get(attachment.id)}
            availableFiles={availableFiles}
            contextKeyOptions={contextKeyOptions}
            dropEdge={dropEdge}
            onChange={(next) => update(index, next)}
            onRemove={() => remove(index)}
            onMoveUp={() => move(index, -1)}
            onMoveDown={() => move(index, 1)}
            onDragStart={() => setDraggedIndex(index)}
            onDragEnd={clearDrag}
            onHoverEdge={(edge) => setDropIndex(edge === "before" ? index : index + 1)}
            onPreviewAttachment={onPreviewAttachment}
            onDrop={(e) => handleDropAt(e, index)}
          />
        )
      })}

      {!readOnly && (
        <div
          onDragOver={(e) => {
            e.preventDefault()
            setDropIndex(attachments.length)
          }}
          onDrop={(e) => handleDropAt(e, attachments.length)}
          style={{
            display: "flex",
            gap: "var(--space-2)",
            flexWrap: "wrap",
            paddingTop: "var(--space-3)",
            marginTop: attachments.length > 0 ? "var(--space-2)" : 0,
            borderTop: attachments.length > 0 ? "1px solid var(--border-subtle)" : "none",
          }}
        >
          {attachmentTypes.map((type) => (
            <Button key={type} variant="secondary" size="sm" icon="Plus" onClick={() => add(type)}>
              Add {type === "text" ? "text" : type === "file" ? "file reference" : "pipeline value"}
            </Button>
          ))}
        </div>
      )}

      {/* The document's own real trailing section, not a separate panel:
          the assembled prompt always appends constraints last, after
          every attachment (see prompt_builder.build_prompt), so this is
          where they belong visually too. */}
      <div style={{ paddingTop: "var(--space-3)", marginTop: "var(--space-2)", borderTop: "1px solid var(--border-subtle)" }}>
        <LearnedConstraintsList
          constraints={learnedConstraints}
          readOnly={readOnly}
          onAdd={onAddConstraint}
          onRemove={onRemoveConstraint}
          onReorder={onReorderConstraints}
        />
      </div>
    </div>
  )
}
