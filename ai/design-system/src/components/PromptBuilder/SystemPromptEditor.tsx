interface SystemPromptEditorProps {
  value: string
  readOnly?: boolean
  onChange: (value: string) => void
}

export function SystemPromptEditor({ value, readOnly = false, onChange }: SystemPromptEditorProps) {
  return (
    <label style={{ display: "flex", flexDirection: "column", gap: "var(--space-1)" }}>
      <span
        style={{
          fontFamily: "var(--font-sans)",
          fontSize: "var(--text-xs)",
          fontWeight: "var(--weight-bold)",
          color: "var(--text-strong)",
        }}
      >
        System prompt
      </span>
      <textarea
        className="orch-field"
        value={value}
        disabled={readOnly}
        onChange={(e) => onChange(e.target.value)}
        rows={5}
        style={{
          width: "100%",
          boxSizing: "border-box",
          border: "1px solid var(--border-default)",
          borderRadius: "var(--radius-md)",
          padding: "var(--space-2) var(--space-3)",
          fontFamily: "var(--font-sans)",
          fontSize: "var(--text-sm)",
          background: "var(--surface-card)",
          color: "var(--text-body)",
          resize: "vertical",
        }}
      />
    </label>
  )
}
