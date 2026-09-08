import { useState } from "react"
import { Button } from "../Button"
import { StatusPill } from "../StatusPill"
import type { PromptConfig, PromptDiff } from "./types"

interface VersionHistoryProps {
  onLoadHistory: () => Promise<string[]>
  onDiffVersions: (versionA: string, versionB: string) => Promise<PromptDiff>
  onRestoreVersion: (version: string) => Promise<PromptConfig>
  onRestored: (config: PromptConfig) => void
  readOnly?: boolean
}

// Every save (generation_toolkit.prompt_config.storage.save_config) keeps
// an immutable snapshot - this lists them, diffs two selected ones (a real
// structural diff, computed server-side, not a raw text diff), and
// restores one back over the live file.
export function VersionHistory({ onLoadHistory, onDiffVersions, onRestoreVersion, onRestored, readOnly = false }: VersionHistoryProps) {
  const [expanded, setExpanded] = useState(false)
  const [versions, setVersions] = useState<string[] | null>(null)
  const [selectedA, setSelectedA] = useState<string>("")
  const [selectedB, setSelectedB] = useState<string>("")
  const [diff, setDiff] = useState<PromptDiff | null>(null)
  const [error, setError] = useState<string | null>(null)

  const load = async () => {
    setExpanded((e) => !e)
    if (versions === null) {
      try {
        setVersions(await onLoadHistory())
      } catch (e) {
        setError(e instanceof Error ? e.message : "Could not load history.")
      }
    }
  }

  const runDiff = async () => {
    if (!selectedA || !selectedB) return
    setError(null)
    try {
      setDiff(await onDiffVersions(selectedA, selectedB))
    } catch (e) {
      setError(e instanceof Error ? e.message : "Diff failed.")
    }
  }

  const restore = async (version: string) => {
    setError(null)
    try {
      onRestored(await onRestoreVersion(version))
    } catch (e) {
      setError(e instanceof Error ? e.message : "Restore failed.")
    }
  }

  return (
    <div style={{ display: "flex", flexDirection: "column", gap: "var(--space-2)" }}>
      <Button variant="ghost" size="sm" onClick={load}>
        {expanded ? "Hide version history" : "Version history"}
      </Button>

      {expanded && (
        <div style={{ display: "flex", flexDirection: "column", gap: "var(--space-2)" }}>
          {error && <p style={{ fontFamily: "var(--font-sans)", fontSize: "var(--text-xs)", color: "var(--danger-500)", margin: 0 }}>{error}</p>}

          {versions && versions.length === 0 && (
            <p style={{ fontFamily: "var(--font-sans)", fontSize: "var(--text-xs)", color: "var(--text-muted)", margin: 0 }}>
              No saved versions yet.
            </p>
          )}

          {versions && versions.length > 0 && (
            <>
              <ul style={{ margin: 0, padding: 0, listStyle: "none", display: "flex", flexDirection: "column", gap: "var(--space-1)" }}>
                {versions.map((version) => (
                  <li
                    key={version}
                    style={{
                      display: "flex",
                      alignItems: "center",
                      gap: "var(--space-2)",
                      fontFamily: "var(--font-mono)",
                      fontSize: "var(--text-2xs)",
                      color: "var(--text-body)",
                    }}
                  >
                    <input
                      type="radio"
                      name="version-a"
                      checked={selectedA === version}
                      onChange={() => setSelectedA(version)}
                      aria-label={`Select ${version} as diff base`}
                    />
                    <input
                      type="radio"
                      name="version-b"
                      checked={selectedB === version}
                      onChange={() => setSelectedB(version)}
                      aria-label={`Select ${version} as diff target`}
                    />
                    <span style={{ flex: 1, overflow: "hidden", textOverflow: "ellipsis" }}>{version}</span>
                    {!readOnly && (
                      <Button variant="ghost" size="sm" onClick={() => restore(version)}>
                        Restore
                      </Button>
                    )}
                  </li>
                ))}
              </ul>
              <p style={{ fontFamily: "var(--font-sans)", fontSize: "var(--text-2xs)", color: "var(--text-muted)", margin: 0 }}>
                Left column picks the base version, right column the target, for the diff below.
              </p>
              <Button variant="secondary" size="sm" disabled={!selectedA || !selectedB} onClick={runDiff}>
                Diff selected versions
              </Button>
            </>
          )}

          {diff && (
            <div style={{ display: "flex", flexWrap: "wrap", gap: "var(--space-2)" }}>
              <StatusPill variant={diff.system_prompt_changed ? "warning" : "info"}>
                {diff.system_prompt_changed ? "System prompt changed" : "System prompt unchanged"}
              </StatusPill>
              {diff.attachments_added.length > 0 && <StatusPill variant="success">+{diff.attachments_added.length} added</StatusPill>}
              {diff.attachments_removed.length > 0 && <StatusPill variant="danger">-{diff.attachments_removed.length} removed</StatusPill>}
              {diff.attachments_changed.length > 0 && <StatusPill variant="warning">{diff.attachments_changed.length} changed</StatusPill>}
              {diff.attachments_added.length === 0 && diff.attachments_removed.length === 0 && diff.attachments_changed.length === 0 && !diff.system_prompt_changed && (
                <StatusPill variant="info">Identical</StatusPill>
              )}
            </div>
          )}
        </div>
      )}
    </div>
  )
}
