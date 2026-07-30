import { useState } from "react"
import type { CSSProperties, ReactNode } from "react"
import { Button } from "./Button"

interface PlatformFormProps {
  onStart: (platformName: string, documentationUrl: string) => void
}

// Matches Input.jsx's real field spec: height 40, radius-md, border-default,
// focus -> border-brand + ring-brand (via the .orch-field class in
// tokens.css, :focus can't be expressed through inline styles).
const fieldStyle: CSSProperties = {
  width: "100%",
  boxSizing: "border-box",
  height: 40,
  border: "1px solid var(--border-default)",
  borderRadius: "var(--radius-md)",
  padding: "0 var(--space-3)",
  fontFamily: "var(--font-sans)",
  fontSize: "var(--text-sm)",
  color: "var(--text-body)",
  background: "var(--surface-card)",
}

// Matches the wireframe's real "Add a CI/CD Platform" screen (d1: input (new
// platform)): Platform name + Documentation URL fields, "Start Integration"
// button. The wireframe also shows an "or Upload documentation" PDF drop
// zone here, deliberately left out: retrieval's real POST /fetch only
// accepts a URL, there's no file-upload capability to wire that to, and a
// visible-but-fake control would be simulated data, not a real capability.
//
// No model picker here: the model choice lives only in the chat panel
// (ChatColumn), which can change it at any point in the run, not just at
// the start, see ai/orchestrator's Orchestrator.model.
export function PlatformForm({ onStart }: PlatformFormProps) {
  const [platformName, setPlatformName] = useState("")
  const [documentationUrl, setDocumentationUrl] = useState("")
  const canSubmit = platformName.trim() && documentationUrl.trim()

  return (
    <form
      onSubmit={(e) => {
        e.preventDefault()
        if (!canSubmit) return
        onStart(platformName.trim(), documentationUrl.trim())
      }}
      style={{
        display: "flex",
        flexDirection: "column",
        gap: "var(--space-4)",
        height: "100%",
        padding: "var(--space-4)",
        boxSizing: "border-box",
        background: "var(--brand-faint)",
        border: "1px solid var(--border-default)",
        borderRadius: "var(--radius-md)",
      }}
    >
      <Field label="Platform name">
        <input
          className="orch-field"
          value={platformName}
          onChange={(e) => setPlatformName(e.target.value)}
          placeholder="TeamCity v0.9"
          style={fieldStyle}
        />
      </Field>
      <Field label="Documentation URL">
        <input
          className="orch-field"
          value={documentationUrl}
          onChange={(e) => setDocumentationUrl(e.target.value)}
          placeholder="https://www.jetbrains.com/help/teamcity/"
          style={fieldStyle}
        />
      </Field>
      <div style={{ flex: 1 }} />
      <Button type="submit" variant="primary" size="md" disabled={!canSubmit} style={{ alignSelf: "flex-start" }}>
        Start Integration
      </Button>
    </form>
  )
}

function Field({ label, children }: { label: string; children: ReactNode }) {
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
        {label}
      </span>
      {children}
    </label>
  )
}
