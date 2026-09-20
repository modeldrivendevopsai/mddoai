import { useRef, useState } from "react"
import { Button } from "../Button"
import { AttachmentChip } from "./AttachmentChip"
import { fieldLabelStyle, inputStyle } from "./fieldStyles"
import { DEFAULT_FILE_ATTACHMENT_NAME } from "./types"
import type { Attachment } from "./types"

// The collapsed, always-visible shape a "file" attachment takes in the
// document - click opens the picker below (real file / upload / preview).
export function FileChip({
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
    <AttachmentChip
      icon="FileText"
      title={attachment.name}
      subtitle={attachment.path || "No file selected"}
      brokenError={brokenError}
      expanded={expanded}
      onToggle={onToggle}
    />
  )
}

// Everything specific to a "file" attachment's own expanded picker: which
// real file it points at, either already known to the backend or a fresh
// upload. Rendered by DocumentBlock.tsx alongside the shared "Name" field
// and AttachmentPreview it owns for every non-text attachment type.
export function FileAttachmentFields({
  attachment,
  availableFiles,
  readOnly,
  onUploadFile,
  onChange,
}: {
  attachment: Attachment
  availableFiles?: string[]
  readOnly: boolean
  onUploadFile?: (file: File) => Promise<string>
  onChange: (attachment: Attachment) => void
}) {
  return (
    <>
      <label style={fieldLabelStyle}>
        Real file already known to the backend
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
      {!readOnly && onUploadFile && <FileUploadField attachment={attachment} onUploadFile={onUploadFile} onChange={onChange} />}
      {!readOnly && (
        <p style={{ fontFamily: "var(--font-sans)", fontSize: "var(--text-2xs)", color: "var(--text-muted)", margin: 0 }}>
          You can also drag a file from your computer anywhere onto this document to attach it.
        </p>
      )}
    </>
  )
}

// The real browse/upload control a "file" attachment's picker was
// otherwise missing entirely - the dropdown above only lists files the
// backend already knows about, with no way to add a new one short of
// dragging it onto the document as a whole (easy to miss once the
// document already has other attachments in it, see PromptDocument.tsx's
// own empty-state hint). A native <input type="file"> triggered by a
// visible button, right next to that dropdown, fixes that regardless of
// drag-and-drop.
function FileUploadField({
  attachment,
  onUploadFile,
  onChange,
}: {
  attachment: Attachment
  onUploadFile: (file: File) => Promise<string>
  onChange: (attachment: Attachment) => void
}) {
  const inputRef = useRef<HTMLInputElement>(null)
  // Synced on every render (not just once), read from inside the upload
  // promise's own .then below instead of closing over the `attachment` prop
  // directly - an upload takes a real round trip, and a human can edit the
  // Name field while it's in flight; reading a stale closured `attachment`
  // at resolution time would silently overwrite that concurrent edit with
  // whatever the attachment looked like back when the button was clicked.
  const attachmentRef = useRef(attachment)
  attachmentRef.current = attachment
  const [uploading, setUploading] = useState(false)
  const [error, setError] = useState<string | null>(null)

  const handleFile = (file: File) => {
    setUploading(true)
    setError(null)
    onUploadFile(file)
      .then((path) => {
        const latest = attachmentRef.current
        onChange({
          ...latest,
          path,
          // Only replaces the placeholder default name, never a name the
          // human already typed in themselves.
          name: latest.name === DEFAULT_FILE_ATTACHMENT_NAME ? file.name : latest.name,
        })
      })
      .catch((e) => setError(e instanceof Error ? e.message : "Upload failed."))
      .finally(() => setUploading(false))
  }

  return (
    <div style={{ display: "flex", flexDirection: "column", gap: "var(--space-1)" }}>
      <input
        ref={inputRef}
        type="file"
        style={{ display: "none" }}
        onChange={(e) => {
          const file = e.target.files?.[0]
          e.target.value = ""
          if (file) handleFile(file)
        }}
      />
      <Button variant="secondary" size="sm" icon="Upload" onClick={() => inputRef.current?.click()} disabled={uploading}>
        {uploading ? "Uploading…" : "Browse and upload a new file…"}
      </Button>
      {error && (
        <p style={{ fontFamily: "var(--font-sans)", fontSize: "var(--text-2xs)", color: "var(--danger-500)", margin: 0 }}>{error}</p>
      )}
    </div>
  )
}
