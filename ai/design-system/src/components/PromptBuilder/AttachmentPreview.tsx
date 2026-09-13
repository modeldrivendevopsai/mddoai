import { useState } from "react"
import { Button } from "../Button"
import { CodeBlock } from "../../CodeBlock"

// A "file"/"context" chip's own real resolved content, fetched on demand
// (not automatically for every chip on the page) once its picker is
// expanded - the same real text build_prompt() folds into the assembled
// prompt, not a re-derived summary of it. Shared by both FileAttachment.tsx
// and ContextAttachment.tsx's own expanded fields, since resolving "the
// real content behind this attachment" is identical for either type from
// this component's own point of view.
export function AttachmentPreview({
  attachmentId,
  onPreviewAttachment,
}: {
  attachmentId: string
  onPreviewAttachment: (id: string) => Promise<string | undefined>
}) {
  const [content, setContent] = useState<string | null>(null)
  const [visible, setVisible] = useState(false)
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState<string | null>(null)

  const load = () => {
    setLoading(true)
    setError(null)
    onPreviewAttachment(attachmentId)
      .then((result) => {
        setContent(result ?? "(nothing resolved for this attachment)")
        setVisible(true)
      })
      .catch((e) => setError(e instanceof Error ? e.message : "Preview failed."))
      .finally(() => setLoading(false))
  }

  return (
    <div style={{ display: "flex", flexDirection: "column", gap: "var(--space-2)" }}>
      <div style={{ display: "flex", gap: "var(--space-2)" }}>
        {/* Re-fetches every click, not just the first - the parent's own
            cache (see index.tsx's loadAttachmentPreview) already clears on
            every edit, but a click here is still the only way to force a
            fresh look with no edit in between, e.g. after a file this
            attachment references changed on the backend independently. */}
        <Button variant="ghost" size="sm" onClick={load} disabled={loading}>
          {loading ? "Loading…" : visible ? "Refresh real content" : "Show real content"}
        </Button>
        {visible && (
          <Button variant="ghost" size="sm" onClick={() => setVisible(false)}>
            Hide
          </Button>
        )}
      </div>
      {error && (
        <p style={{ fontFamily: "var(--font-sans)", fontSize: "var(--text-2xs)", color: "var(--danger-500)", margin: 0 }}>
          {error}
        </p>
      )}
      {visible && content !== null && <CodeBlock code={content} title="real resolved content" lang="text" />}
    </div>
  )
}
