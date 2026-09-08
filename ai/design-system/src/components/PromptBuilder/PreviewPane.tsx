import { useState } from "react"
import { Button } from "../Button"
import { CodeBlock } from "../../CodeBlock"
import type { PromptPreview } from "./types"

interface PreviewPaneProps {
  onPreview: () => Promise<PromptPreview>
}

// Static preview: the exact text a real call would send the LLM, computed
// server-side from the real render function, without spending a real call.
// Fetched on demand (not automatically on every keystroke) since it's a
// real request each time.
export function PreviewPane({ onPreview }: PreviewPaneProps) {
  const [preview, setPreview] = useState<PromptPreview | null>(null)
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState<string | null>(null)

  const load = async () => {
    setLoading(true)
    setError(null)
    try {
      setPreview(await onPreview())
    } catch (e) {
      setError(e instanceof Error ? e.message : "Preview failed.")
    } finally {
      setLoading(false)
    }
  }

  return (
    <div style={{ display: "flex", flexDirection: "column", gap: "var(--space-2)" }}>
      <Button variant="secondary" size="sm" onClick={load} disabled={loading}>
        {loading ? "Loading preview…" : "Preview exact prompt text"}
      </Button>
      {error && <p style={{ fontFamily: "var(--font-sans)", fontSize: "var(--text-xs)", color: "var(--danger-500)", margin: 0 }}>{error}</p>}
      {preview && (
        <>
          <CodeBlock code={preview.system_prompt} title="system message" lang="text" />
          <CodeBlock code={preview.user_content} title="user message" lang="text" />
        </>
      )}
    </div>
  )
}
