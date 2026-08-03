import { SelectField } from "./FormField"
import type { OrchestratorNote } from "./types"
import type { Provider } from "@/types"

interface OrchestratorPanelProps {
  notes: OrchestratorNote[]
  providers: Provider[]
  selectedModel: string
  onModelChange: (model: string) => void
}

export function OrchestratorPanel({
  notes,
  providers,
  selectedModel,
  onModelChange,
}: OrchestratorPanelProps) {
  return (
    <div
      className="flex flex-1 flex-col justify-between"
      style={{
        background: "var(--surface-card)",
        border: "1px solid var(--border-subtle)",
        borderRadius: "var(--radius-lg)",
        boxShadow: "var(--shadow-sm)",
        padding: 20,
      }}
    >
      <div className="flex flex-col" style={{ gap: 14 }}>
        <span
          style={{
            fontFamily: "var(--font-display)",
            fontSize: 15,
            fontWeight: 700,
            color: "var(--text-strong)",
          }}
        >
          Chat
        </span>
        {notes.map((note) => (
          <div key={note.id} className="flex flex-col" style={{ gap: 2 }}>
            <span
              style={{
                fontFamily: "var(--font-sans)",
                fontSize: 10.5,
                fontWeight: 700,
                color: "var(--text-muted)",
                letterSpacing: "0.03em",
              }}
            >
              Orchestrator
            </span>
            <span
              style={{
                fontFamily: "var(--font-sans)",
                fontSize: 13,
                lineHeight: 1.55,
                color: "var(--text-body)",
              }}
            >
              {note.text}
            </span>
          </div>
        ))}
      </div>

      <div className="flex flex-col" style={{ gap: 8, marginTop: 16 }}>
        {providers.length > 0 && (
          <div className="flex justify-end">
            <SelectField value={selectedModel} onChange={onModelChange} ariaLabel="Select model">
              <option value="auto">Auto</option>
              {providers.map((p) => (
                <option key={p.name} value={p.name} disabled={!p.available}>
                  {p.name} ({p.tier}){!p.available ? " — no API key set" : ""}
                </option>
              ))}
            </SelectField>
          </div>
        )}
        <div
          className="flex items-center justify-between"
          style={{
            padding: "9px 14px",
            border: "1px solid var(--border-default)",
            borderRadius: "var(--radius-md)",
            background: "var(--surface-card)",
          }}
        >
          <span style={{ fontFamily: "var(--font-sans)", fontSize: 13, color: "var(--text-faint)" }}>
            Nudge the Orchestrator..
          </span>
          <div
            aria-hidden
            className="flex items-center justify-center"
            style={{
              width: 22,
              height: 22,
              borderRadius: "50%",
              background: "var(--surface-sunken)",
            }}
          />
        </div>
      </div>
    </div>
  )
}
