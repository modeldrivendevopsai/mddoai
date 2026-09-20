import { useState } from "react"
import { Button } from "../Button"
import { CodeBlock } from "../../CodeBlock"
import type { AttemptDetailData } from "./types"

interface AttemptDetailProps {
  detail: AttemptDetailData
  onRestoreConfigFromAttempt?: (promptVersion: string) => Promise<void>
}

// One attempt's real persisted files: its own artifact, its validator
// result, and (when this stage has a real, config-driven prompt, only psm
// today) the exact prompt that produced it, with a real "restore this
// config version" action wired straight to PromptBuilder's own version
// history when the caller has both mounted together.
export function AttemptDetail({ detail, onRestoreConfigFromAttempt }: AttemptDetailProps) {
  const [restoring, setRestoring] = useState(false)
  const [restored, setRestored] = useState(false)

  const restore = async () => {
    if (!detail.prompt_version || !onRestoreConfigFromAttempt) return
    setRestoring(true)
    try {
      await onRestoreConfigFromAttempt(detail.prompt_version)
      setRestored(true)
    } finally {
      setRestoring(false)
    }
  }

  return (
    <div style={{ display: "flex", flexDirection: "column", gap: "var(--space-2)" }}>
      {detail.artifact && <CodeBlock code={detail.artifact.content} title={detail.artifact.filename} lang="text" />}
      <CodeBlock code={JSON.stringify(detail.result, null, 2)} title="result.json" lang="json" />
      {detail.prompt && <CodeBlock code={JSON.stringify(detail.prompt, null, 2)} title="prompt used" lang="json" />}
      {detail.prompt_version && onRestoreConfigFromAttempt && (
        <Button variant="secondary" size="sm" onClick={restore} disabled={restoring || restored}>
          {restored ? "Restored" : restoring ? "Restoring…" : "Restore the config that produced this"}
        </Button>
      )}
    </div>
  )
}
