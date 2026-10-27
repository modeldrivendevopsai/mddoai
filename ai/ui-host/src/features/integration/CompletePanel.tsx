import { useEffect, useId, useState } from "react"
import { Button } from "design-system"
import type { PublishDefaults, PublishedPlatform } from "orchestrator-types"

interface CompletePanelProps {
  // Publishing always acts on the backend's current run, so a finished run
  // opened from history must not offer it.
  canPublish: boolean
  loadDefaults: () => Promise<PublishDefaults>
  onPublish: (name: string, outputFilename: string) => Promise<PublishedPlatform>
}

const panelStyle: React.CSSProperties = {
  display: "flex",
  flexDirection: "column",
  gap: "var(--space-3)",
  height: "100%",
  padding: "var(--space-4)",
  background: "var(--brand-faint)",
  border: "1px solid var(--border-default)",
  borderRadius: "var(--radius-md)",
  boxSizing: "border-box",
  minHeight: 0,
  overflow: "auto",
  fontFamily: "var(--font-sans)",
}

const textStyle: React.CSSProperties = { margin: 0, color: "var(--text-muted)", fontSize: 14 }

// The longest name and output file path the backend accepts.
const NAME_MAX_CHARS = 100
const OUTPUT_FILENAME_MAX_CHARS = 255

// The design system's text input, as a plain styled <input> with its label,
// the same way the docs stage's start form builds it.
function Field({ label, value, placeholder, maxLength, onChange, disabled }: {
  label: string
  value: string
  placeholder: string
  maxLength: number
  onChange: (value: string) => void
  disabled: boolean
}) {
  const id = useId()
  return (
    <div style={{ display: "flex", flexDirection: "column", gap: 6 }}>
      <label htmlFor={id} style={{ fontSize: 13, fontWeight: 600, color: "var(--text-strong)" }}>
        {label}
      </label>
      <input
        id={id}
        type="text"
        value={value}
        placeholder={placeholder}
        maxLength={maxLength}
        disabled={disabled}
        onChange={(event) => onChange(event.target.value)}
        style={{
          height: 40,
          padding: "0 12px",
          background: disabled ? "var(--surface-sunken)" : "var(--surface-card)",
          border: "1px solid var(--border-default)",
          borderRadius: "var(--radius-md)",
          fontFamily: "var(--font-sans)",
          fontSize: 14,
          color: "var(--text-strong)",
        }}
      />
    </div>
  )
}

/**
 * "Nothing left to review": the whole run is approved. Offers to publish the
 * platform it built to the pipeline generator, so pipelines can be generated
 * for it next to the built-in platforms.
 */
export function CompletePanel({ canPublish, loadDefaults, onPublish }: CompletePanelProps) {
  const [name, setName] = useState("")
  const [outputFilename, setOutputFilename] = useState("")
  const [publishing, setPublishing] = useState(false)
  const [published, setPublished] = useState<PublishedPlatform | null>(null)
  const [error, setError] = useState<string | null>(null)

  // Fills the form with what a publish would use anyway, once.
  useEffect(() => {
    if (!canPublish) return
    let current = true
    loadDefaults()
      .then((defaults) => {
        if (!current) return
        setName((value) => value || defaults.name || "")
        setOutputFilename((value) => value || defaults.output_filename || "")
      })
      .catch(() => undefined)
    return () => {
      current = false
    }
  }, [canPublish, loadDefaults])

  async function handlePublish() {
    setPublishing(true)
    setError(null)
    try {
      setPublished(await onPublish(name.trim(), outputFilename.trim()))
    } catch (err) {
      setError(err instanceof Error && err.message ? err.message : "Could not publish this platform.")
    } finally {
      setPublishing(false)
    }
  }

  return (
    <div style={panelStyle}>
      <p style={textStyle}>Integration complete. All stages approved.</p>
      <h2 style={{ margin: 0, fontFamily: "var(--font-display)", fontSize: "var(--text-md)", color: "var(--text-strong)" }}>
        Publish to the pipeline generator
      </h2>
      {canPublish ? (
        <>
          <p style={textStyle}>
            Makes this platform available in Generate a CI/CD pipeline, next to the built-in platforms. Publishing
            again adds a new version.
          </p>
          <Field
            label="Platform name"
            value={name}
            placeholder="Woodpecker CI"
            maxLength={NAME_MAX_CHARS}
            onChange={setName}
            disabled={publishing}
          />
          <Field
            label="Pipeline file it generates"
            value={outputFilename}
            placeholder=".woodpecker/pipeline.yaml"
            maxLength={OUTPUT_FILENAME_MAX_CHARS}
            onChange={setOutputFilename}
            disabled={publishing}
          />
          <div>
            <Button
              variant="primary"
              size="sm"
              disabled={publishing || !name.trim() || !outputFilename.trim()}
              onClick={() => void handlePublish()}
            >
              {publishing ? "Publishing…" : "Publish"}
            </Button>
          </div>
          {published && (
            <p role="status" style={{ ...textStyle, color: "var(--text-strong)" }}>
              Published as {published.name} v{published.version}. It's now available in Generate a CI/CD pipeline.
            </p>
          )}
          {error && (
            <p role="alert" style={{ ...textStyle, color: "var(--danger-strong)" }}>
              {error}
            </p>
          )}
        </>
      ) : (
        <p style={textStyle}>This isn't the current run. Resume it to publish its platform.</p>
      )}
    </div>
  )
}
