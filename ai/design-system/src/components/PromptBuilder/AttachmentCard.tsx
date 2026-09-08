import { useState } from "react"
import { Button } from "../Button"
import { Icon } from "../Icon"
import { estimateTokens } from "./tokenEstimate"
import type { Attachment, AttachmentType } from "./types"

interface AttachmentCardProps {
  attachment: Attachment
  isFirst: boolean
  isLast: boolean
  readOnly?: boolean
  brokenError?: string
  availableFiles?: string[]
  contextKeyOptions: { key: string; label: string }[]
  onChange: (attachment: Attachment) => void
  onRemove: () => void
  onMoveUp: () => void
  onMoveDown: () => void
}

const TYPE_LABELS: Record<AttachmentType, string> = { text: "Text", file: "File", context: "Pipeline value" }

// One card per attachment: collapsed by default (name, type, a rough
// size), expands to view/edit the full content. Reordering is up/down
// buttons rather than full drag physics - equally functional, and stays
// keyboard-accessible without a ghost-image/drop-zone implementation to
// get right; the browser drag-and-drop this app really needs (dropping a
// file onto the list to create a new text attachment) lives in
// AttachmentList.tsx instead, where the drop target actually is.
export function AttachmentCard({
  attachment,
  isFirst,
  isLast,
  readOnly = false,
  brokenError,
  availableFiles,
  contextKeyOptions,
  onChange,
  onRemove,
  onMoveUp,
  onMoveDown,
}: AttachmentCardProps) {
  const [expanded, setExpanded] = useState(false)

  const contentForEstimate = attachment.type === "text" ? attachment.content ?? "" : ""
  const tokenEstimate = estimateTokens(contentForEstimate)

  const detach = () => {
    onChange({ id: attachment.id, name: attachment.name, type: "text", content: "" })
    setExpanded(true)
  }

  return (
    <div
      style={{
        border: `1px solid ${brokenError ? "var(--danger-500)" : "var(--border-default)"}`,
        borderRadius: "var(--radius-md)",
        background: "var(--surface-card)",
        overflow: "hidden",
      }}
    >
      <div
        style={{
          display: "flex",
          alignItems: "center",
          gap: "var(--space-2)",
          padding: "var(--space-2) var(--space-3)",
          cursor: "pointer",
        }}
        onClick={() => setExpanded((e) => !e)}
      >
        <Icon name={expanded ? "ChevronDown" : "ChevronRight"} size={16} style={{ color: "var(--text-muted)", flexShrink: 0 }} />
        <span
          style={{
            fontFamily: "var(--font-sans)",
            fontSize: "var(--text-sm)",
            fontWeight: "var(--weight-semibold)",
            color: "var(--text-strong)",
            overflow: "hidden",
            textOverflow: "ellipsis",
            whiteSpace: "nowrap",
            flex: 1,
          }}
        >
          {attachment.name || "(untitled)"}
        </span>
        <span
          style={{
            fontFamily: "var(--font-mono)",
            fontSize: "var(--text-2xs)",
            color: "var(--text-muted)",
            background: "var(--surface-sunken)",
            padding: "2px 8px",
            borderRadius: "var(--radius-pill)",
            flexShrink: 0,
          }}
        >
          {TYPE_LABELS[attachment.type]}
        </span>
        {attachment.type === "text" && (
          <span style={{ fontFamily: "var(--font-mono)", fontSize: "var(--text-2xs)", color: "var(--text-faint)" }}>
            ~{tokenEstimate} tok
          </span>
        )}
        {brokenError && <Icon name="AlertTriangle" size={14} style={{ color: "var(--danger-500)" }} />}
        {!readOnly && (
          <div style={{ display: "flex", gap: 2 }} onClick={(e) => e.stopPropagation()}>
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
      </div>

      {expanded && (
        <div
          style={{
            padding: "var(--space-3)",
            borderTop: "1px solid var(--border-subtle)",
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

          {attachment.type === "text" && (
            <label style={fieldLabelStyle}>
              Content
              <textarea
                className="orch-field"
                value={attachment.content ?? ""}
                disabled={readOnly}
                onChange={(e) => onChange({ ...attachment, content: e.target.value })}
                rows={6}
                style={{ ...inputStyle, fontFamily: "var(--font-mono)", resize: "vertical" }}
              />
            </label>
          )}

          {attachment.type === "file" && (
            <>
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
              {!readOnly && (
                <Button variant="secondary" size="sm" onClick={detach}>
                  Detach into editable copy
                </Button>
              )}
            </>
          )}

          {attachment.type === "context" && (
            <>
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
              {!readOnly && (
                <Button variant="secondary" size="sm" onClick={detach}>
                  Detach into editable copy
                </Button>
              )}
            </>
          )}
        </div>
      )}
    </div>
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
