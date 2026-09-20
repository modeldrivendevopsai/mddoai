import { AttachmentChip } from "./AttachmentChip"
import { fieldLabelStyle, inputStyle } from "./fieldStyles"
import type { Attachment } from "./types"

// The collapsed, always-visible shape "auto-filled data" (a "context"-type
// attachment, the UI's own human-facing name for it) takes in the document:
// there's no file on disk to name, just which real pipeline output this
// slot resolves to, live, whenever a real call or a preview needs it (see
// StagePanelProps' own manifest.contextKeyOptions).
export function ContextChip({
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
      icon="Zap"
      title={attachment.name}
      subtitle="Auto-filled live"
      brokenError={brokenError}
      expanded={expanded}
      onToggle={onToggle}
    />
  )
}

// Everything specific to a "context" attachment's own expanded picker:
// which real pipeline output fills this slot. Rendered by DocumentBlock.tsx
// alongside the shared "Name" field and AttachmentPreview it owns for
// every non-text attachment type.
export function ContextAttachmentFields({
  attachment,
  contextKeyOptions,
  readOnly,
  onChange,
}: {
  attachment: Attachment
  contextKeyOptions: { key: string; label: string }[]
  readOnly: boolean
  onChange: (attachment: Attachment) => void
}) {
  return (
    <label style={fieldLabelStyle}>
      Auto-filled from this pipeline, resolved live whenever you preview or generate
      <select
        className="orch-field"
        value={attachment.key ?? ""}
        disabled={readOnly}
        onChange={(e) => onChange({ ...attachment, key: e.target.value })}
        style={inputStyle}
      >
        <option value="" disabled>
          Select what real value fills this slot
        </option>
        {contextKeyOptions.map((option) => (
          <option key={option.key} value={option.key}>
            {option.label}
          </option>
        ))}
      </select>
    </label>
  )
}
