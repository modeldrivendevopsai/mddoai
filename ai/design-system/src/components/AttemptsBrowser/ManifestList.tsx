import { StatusPill } from "../StatusPill"
import type { ManifestEntry } from "./types"

interface ManifestListProps {
  entries: ManifestEntry[]
  selectedAttemptN: number | null
  onSelect: (attemptN: number) => void
}

// Every attempt recorded for this stage, newest first, each a real,
// on-disk record (runs/<run_id>/manifest.json) - not just the latest
// in-memory event, so a human can review a past attempt after a page
// reload or after several retries.
export function ManifestList({ entries, selectedAttemptN, onSelect }: ManifestListProps) {
  const sorted = [...entries].sort((a, b) => b.attempt_n - a.attempt_n)

  if (sorted.length === 0) {
    return (
      <p style={{ fontFamily: "var(--font-sans)", fontSize: "var(--text-xs)", color: "var(--text-muted)", margin: 0 }}>
        No attempts recorded yet.
      </p>
    )
  }

  return (
    <ul style={{ margin: 0, padding: 0, listStyle: "none", display: "flex", flexDirection: "column", gap: "var(--space-1)" }}>
      {sorted.map((entry) => (
        <li key={entry.attempt_n}>
          <button
            onClick={() => onSelect(entry.attempt_n)}
            style={{
              display: "flex",
              alignItems: "center",
              justifyContent: "space-between",
              width: "100%",
              boxSizing: "border-box",
              padding: "var(--space-2) var(--space-3)",
              border: "1px solid",
              borderColor: selectedAttemptN === entry.attempt_n ? "var(--border-brand)" : "var(--border-default)",
              background: selectedAttemptN === entry.attempt_n ? "var(--brand-faint)" : "var(--surface-card)",
              borderRadius: "var(--radius-md)",
              cursor: "pointer",
              fontFamily: "var(--font-sans)",
              fontSize: "var(--text-xs)",
              color: "var(--text-strong)",
              textAlign: "left",
            }}
          >
            <span>Attempt {entry.attempt_n}</span>
            <span style={{ display: "flex", alignItems: "center", gap: "var(--space-2)" }}>
              <span style={{ color: "var(--text-muted)", fontSize: "var(--text-2xs)" }}>
                {new Date(entry.timestamp).toLocaleString()}
              </span>
              <StatusPill variant={entry.valid ? "success" : "danger"}>{entry.valid ? "Valid" : "Failed"}</StatusPill>
            </span>
          </button>
        </li>
      ))}
    </ul>
  )
}
