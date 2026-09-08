import { useState } from "react"
import type { DragEvent } from "react"
import { Button } from "../Button"
import { AttachmentCard } from "./AttachmentCard"
import type { Attachment, AttachmentType, BrokenReference } from "./types"

interface AttachmentListProps {
  attachments: Attachment[]
  attachmentTypes: AttachmentType[]
  contextKeyOptions: { key: string; label: string }[]
  availableFiles?: string[]
  broken: BrokenReference[]
  readOnly?: boolean
  onChange: (attachments: Attachment[]) => void
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

// The list: add/reorder (up/down, see AttachmentCard's own docstring for
// why not drag physics there), and the one real drag-and-drop this app
// needs - dropping a file from the OS onto this list reads it client-side
// (the browser's own File API, no upload endpoint) and adds it as a new
// "text" attachment pre-filled with that file's content, editable and
// renameable after, exactly like a card added by hand. "file"-type
// attachments stay reserved for referencing an already-known real repo
// file via the picker, never an arbitrary upload.
export function AttachmentList({
  attachments,
  attachmentTypes,
  contextKeyOptions,
  availableFiles,
  broken,
  readOnly = false,
  onChange,
}: AttachmentListProps) {
  const [dragOver, setDragOver] = useState(false)
  const brokenById = new Map(broken.map((b) => [b.id, b.error]))

  const update = (index: number, next: Attachment) => {
    const copy = [...attachments]
    copy[index] = next
    onChange(copy)
  }

  const remove = (index: number) => onChange(attachments.filter((_, i) => i !== index))

  const move = (index: number, delta: number) => {
    const target = index + delta
    if (target < 0 || target >= attachments.length) return
    const copy = [...attachments]
    ;[copy[index], copy[target]] = [copy[target], copy[index]]
    onChange(copy)
  }

  const add = (type: AttachmentType) => onChange([...attachments, defaultAttachment(type)])

  const handleDrop = (event: DragEvent<HTMLDivElement>) => {
    event.preventDefault()
    setDragOver(false)
    if (readOnly) return
    const files = Array.from(event.dataTransfer.files)
    for (const file of files) {
      const reader = new FileReader()
      reader.onload = () => {
        const content = typeof reader.result === "string" ? reader.result : ""
        onChange([...attachments, { id: newLocalId(), name: file.name, type: "text", content }])
      }
      reader.readAsText(file)
    }
  }

  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        gap: "var(--space-2)",
        padding: "var(--space-2)",
        border: dragOver ? "2px dashed var(--brand)" : "2px dashed transparent",
        borderRadius: "var(--radius-md)",
        transition: "border-color var(--duration-fast) var(--ease-out)",
      }}
      onDragOver={(e) => {
        e.preventDefault()
        if (!readOnly) setDragOver(true)
      }}
      onDragLeave={() => setDragOver(false)}
      onDrop={handleDrop}
    >
      {attachments.length === 0 && (
        <p style={{ fontFamily: "var(--font-sans)", fontSize: "var(--text-xs)", color: "var(--text-muted)", margin: 0 }}>
          No attachments yet. Add one below, or drop a file here.
        </p>
      )}

      {attachments.map((attachment, index) => (
        <AttachmentCard
          key={attachment.id}
          attachment={attachment}
          isFirst={index === 0}
          isLast={index === attachments.length - 1}
          readOnly={readOnly}
          brokenError={brokenById.get(attachment.id)}
          availableFiles={availableFiles}
          contextKeyOptions={contextKeyOptions}
          onChange={(next) => update(index, next)}
          onRemove={() => remove(index)}
          onMoveUp={() => move(index, -1)}
          onMoveDown={() => move(index, 1)}
        />
      ))}

      {!readOnly && (
        <div style={{ display: "flex", gap: "var(--space-2)", flexWrap: "wrap" }}>
          {attachmentTypes.map((type) => (
            <Button key={type} variant="secondary" size="sm" icon="Plus" onClick={() => add(type)}>
              Add {type === "text" ? "text" : type === "file" ? "file reference" : "pipeline value"}
            </Button>
          ))}
        </div>
      )}
    </div>
  )
}
