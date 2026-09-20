// Shared form-field styling for every expanded attachment picker (the
// "Name" field in DocumentBlock.tsx, plus FileAttachment.tsx's and
// ContextAttachment.tsx's own selects) - one definition rather than the
// same two style objects copied into each file that needs a labeled
// input/select in this look.
export const fieldLabelStyle = {
  display: "flex",
  flexDirection: "column",
  gap: "var(--space-1)",
  fontFamily: "var(--font-sans)",
  fontSize: "var(--text-xs)",
  fontWeight: "var(--weight-semibold)",
  color: "var(--text-strong)",
} as const

export const inputStyle = {
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
