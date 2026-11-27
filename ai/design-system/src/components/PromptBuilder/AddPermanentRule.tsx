import { useState } from "react"
import { Button } from "../Button"

/** The field and button that add a permanent rule. A blank entry adds nothing. */
export function AddPermanentRule({ onAdd }: { onAdd: (rule: string) => void }) {
  const [rule, setRule] = useState("")

  const add = () => {
    if (!rule.trim()) return
    onAdd(rule.trim())
    setRule("")
  }

  return (
    <div style={{ display: "flex", gap: "var(--space-2)" }}>
      <input
        className="orch-field"
        type="text"
        value={rule}
        onChange={(e) => setRule(e.target.value)}
        placeholder="Add a permanent rule"
        style={{
          flex: 1,
          border: "1px solid var(--border-default)",
          borderRadius: "var(--radius-md)",
          padding: "var(--space-2) var(--space-3)",
          fontFamily: "var(--font-sans)",
          fontSize: "var(--text-sm)",
          background: "var(--surface-card)",
          color: "var(--text-body)",
        }}
      />
      <Button variant="secondary" size="sm" onClick={add}>
        Add
      </Button>
    </div>
  )
}
