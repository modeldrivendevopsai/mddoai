import { useEffect, useState } from "react"
import { AttemptDetail } from "./AttemptDetail"
import { ManifestList } from "./ManifestList"
import type { AttemptDetailData, AttemptsBrowserProps, ManifestEntry } from "./types"

export type { AttemptArtifact, AttemptDetailData, AttemptsBrowserProps, ManifestEntry } from "./types"

// The other reusable piece the modular prompt builder needs: every real
// attempt already lands on disk (integration_runner's own persist_attempt),
// this is just the first place anything actually reads that tree back for
// a human. Not stage-specific: `stage` is a manifest-style input, the same
// component works for any stage once its own actions.py/routes wire these
// same two callbacks.
export function AttemptsBrowser({ runId, stage, onLoadManifest, onLoadAttempt, onRestoreConfigFromAttempt }: AttemptsBrowserProps) {
  const [entries, setEntries] = useState<ManifestEntry[]>([])
  const [selectedAttemptN, setSelectedAttemptN] = useState<number | null>(null)
  const [detail, setDetail] = useState<AttemptDetailData | null>(null)
  const [error, setError] = useState<string | null>(null)

  useEffect(() => {
    let cancelled = false
    onLoadManifest(runId)
      .then((all) => {
        if (!cancelled) setEntries(all.filter((entry) => entry.stage === stage))
      })
      .catch((e) => {
        if (!cancelled) setError(e instanceof Error ? e.message : "Could not load attempts.")
      })
    return () => {
      cancelled = true
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [runId, stage])

  const select = async (attemptN: number) => {
    // Clicking the already-open attempt again closes it, the same
    // click-to-expand/click-to-collapse gesture every other expandable
    // piece of this document uses - a real attempt's own detail is real
    // content taking up space, not something a human is stuck viewing
    // once opened.
    if (selectedAttemptN === attemptN) {
      setSelectedAttemptN(null)
      setDetail(null)
      setError(null)
      return
    }
    setSelectedAttemptN(attemptN)
    setDetail(null)
    setError(null)
    try {
      setDetail(await onLoadAttempt(runId, stage, `attempt_${attemptN}`))
    } catch (e) {
      setError(e instanceof Error ? e.message : "Could not load that attempt.")
    }
  }

  return (
    <div style={{ display: "flex", flexDirection: "column", gap: "var(--space-2)" }}>
      <h3
        style={{
          fontFamily: "var(--font-display)",
          fontSize: "var(--text-sm)",
          fontWeight: "var(--weight-bold)",
          color: "var(--text-strong)",
          margin: 0,
        }}
      >
        Attempt history
      </h3>
      {error && (
        <p style={{ fontFamily: "var(--font-sans)", fontSize: "var(--text-xs)", color: "var(--danger-500)", margin: 0 }}>{error}</p>
      )}
      <ManifestList entries={entries} selectedAttemptN={selectedAttemptN} onSelect={select} />
      {detail && <AttemptDetail detail={detail} onRestoreConfigFromAttempt={onRestoreConfigFromAttempt} />}
    </div>
  )
}
