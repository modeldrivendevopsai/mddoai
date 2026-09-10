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
// real request each time. `visible` is separate from the fetched `preview`
// itself: once loaded, a human can hide it again without losing the fetch
// (no refetch needed just to look again), and can still force a fresh
// fetch (content only changes after a save) via the same button.
export function PreviewPane({ onPreview }: PreviewPaneProps) {
  const [preview, setPreview] = useState<PromptPreview | null>(null)
  const [visible, setVisible] = useState(false)
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState<string | null>(null)

  const load = async () => {
    setLoading(true)
    setError(null)
    try {
      setPreview(await onPreview())
      setVisible(true)
    } catch (e) {
      setError(e instanceof Error ? e.message : "Preview failed.")
    } finally {
      setLoading(false)
    }
  }

  return (
    <div style={{ display: "flex", flexDirection: "column", gap: "var(--space-2)" }}>
      <div style={{ display: "flex", gap: "var(--space-2)" }}>
        <Button variant="secondary" size="sm" onClick={load} disabled={loading}>
          {loading ? "Loading preview…" : visible ? "Refresh preview" : "Preview exact prompt text"}
        </Button>
        {visible && (
          <Button variant="ghost" size="sm" onClick={() => setVisible(false)}>
            Hide preview
          </Button>
        )}
      </div>
      {error && <p style={{ fontFamily: "var(--font-sans)", fontSize: "var(--text-xs)", color: "var(--danger-500)", margin: 0 }}>{error}</p>}
      {visible && preview && (
        <>
          <CodeBlock code={preview.system_prompt} title="system message" lang="text" />
          <CodeBlock code={preview.user_content} title="user message" lang="text" />
        </>
      )}
    </div>
  )
}
