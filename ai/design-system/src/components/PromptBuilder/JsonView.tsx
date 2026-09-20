import { useState } from "react"
import { Button } from "../Button"
import { CodeBlock } from "../../CodeBlock"
import type { PromptConfig } from "./types"

interface JsonViewProps {
  config: PromptConfig
}

// Raw-JSON toggle over the current draft (not a saved version) - useful to
// copy the exact shape out for debugging or for pasting into research
// notes, matching the real ai-research prompts' own markdown-documented
// format.
export function JsonView({ config }: JsonViewProps) {
  const [visible, setVisible] = useState(false)

  return (
    <div style={{ display: "flex", flexDirection: "column", gap: "var(--space-2)" }}>
      <Button variant="ghost" size="sm" onClick={() => setVisible((v) => !v)}>
        {visible ? "Hide raw JSON" : "View raw JSON"}
      </Button>
      {visible && <CodeBlock code={JSON.stringify(config, null, 2)} title="config.json" lang="json" />}
    </div>
  )
}
