import type { CSSProperties } from "react"
import { Icon } from "../Icon"
import type { IconName } from "../Icon"

// The compact "embed" look shared by every non-text attachment reference
// (a "file" or "context" chip): an icon square, a title, and a one-line
// subtitle, in a bordered card that doesn't stretch to the document's full
// width - the real ai-research prompt.md files reference a file by name
// inline, never paste its content into the document, and this chip is the
// same idea generalized to any attachment that has no prose of its own to
// show. FileAttachment.tsx's FileChip and ContextAttachment.tsx's ContextChip
// are both thin wrappers around this, differing only in icon and subtitle
// text - genuinely the same visual shell, not two coincidentally-similar
// ones, so it's shared here rather than duplicated per attachment type.
export function AttachmentChip({
  icon,
  title,
  subtitle,
  brokenError,
  expanded,
  onToggle,
}: {
  icon: IconName
  title: string
  subtitle: string
  brokenError?: string
  expanded: boolean
  onToggle: () => void
}) {
  return (
    <div onClick={onToggle} style={chipStyle(expanded, brokenError)}>
      <div style={chipIconStyle}>
        <Icon name={icon} size={14} style={{ color: "var(--brand)" }} />
      </div>
      <div style={{ display: "flex", flexDirection: "column", overflow: "hidden", minWidth: 0 }}>
        <span style={chipTitleStyle}>{title || "(untitled)"}</span>
        <span style={chipSubtitleStyle}>{subtitle}</span>
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
