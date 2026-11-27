import { useEffect, useRef, useState } from "react"
import { AttemptChart } from "../AttemptChart"
import type { ChartAttempt } from "../AttemptChart"
import { AttemptDetail } from "./AttemptDetail"
import type { AttemptDetailData, AttemptsBrowserProps, ManifestEntry } from "./types"

export type { AttemptArtifact, AttemptDetailData, AttemptsBrowserProps, ManifestEntry } from "./types"

// The other reusable piece the modular prompt builder needs: every real
// attempt already lands on disk (integration_runner's own persist_attempt),
// this is just the first place anything actually reads that tree back for
// a human. Not stage-specific: `stage` is a manifest-style input, the same
// component works for any stage once its own actions.py/routes wire these
// same two callbacks.
export function AttemptsBrowser({
  runId,
  stage,
  reloadKey,
  onLoadManifest,
  onLoadAttempt,
  onRestoreConfigFromAttempt,
}: AttemptsBrowserProps) {
  const [entries, setEntries] = useState<ManifestEntry[]>([])
  const [selectedAttemptN, setSelectedAttemptN] = useState<number | null>(null)
  const [detail, setDetail] = useState<AttemptDetailData | null>(null)
  // Why the list of drafts could not be loaded, and why the files of the open draft could not. They
  // are kept apart, so loading the list again does not clear a message about the draft.
  const [listError, setListError] = useState<string | null>(null)
  const [detailError, setDetailError] = useState<string | null>(null)

  // What the browser is showing the attempts of. An answer for a stage or run it has since
  // left is dropped, and the selection and the entries of the old one are cleared.
  const scope = `${runId}/${stage}`
  const scopeRef = useRef(scope)
  useEffect(() => {
    scopeRef.current = scope
    setEntries([])
    setSelectedAttemptN(null)
    setDetail(null)
    setListError(null)
    setDetailError(null)
  }, [scope])

  useEffect(() => {
    let cancelled = false
    onLoadManifest(runId)
      .then((all) => {
        if (cancelled) return
        setEntries(all.filter((entry) => entry.stage === stage))
        setListError(null)
      })
      .catch((e) => {
        if (!cancelled) setListError(e instanceof Error ? e.message : "Could not load drafts.")
      })
    return () => {
      cancelled = true
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [runId, stage, reloadKey])

  // The attempt the person asked for last, so a slow answer for an earlier one cannot open over it.
  const askedRef = useRef<number | null>(null)

  const select = async (attemptN: number) => {
    // Clicking the already-open draft again closes it, the same
    // click-to-expand/click-to-collapse gesture every other expandable
    // piece of this document uses - a real attempt's own detail is real
    // content taking up space, not something a human is stuck viewing
    // once opened.
    if (selectedAttemptN === attemptN) {
      askedRef.current = null
      setSelectedAttemptN(null)
      setDetail(null)
      setDetailError(null)
      return
    }
    askedRef.current = attemptN
    setSelectedAttemptN(attemptN)
    setDetail(null)
    setDetailError(null)
    const asked = scopeRef.current
    try {
      const loaded = await onLoadAttempt(runId, stage, `attempt_${attemptN}`)
      if (scopeRef.current === asked && askedRef.current === attemptN) setDetail(loaded)
    } catch (e) {
      if (scopeRef.current === asked && askedRef.current === attemptN) {
        setDetailError(e instanceof Error ? e.message : "Could not load that draft.")
      }
    }
  }

  // Oldest on the left, so time reads left to right.
  const ordered = [...entries].sort((a, b) => a.attempt_n - b.attempt_n)
  const attempts: ChartAttempt[] = ordered.map((entry) => ({
    number: entry.attempt_n,
    rounds: typeof entry.rounds === "number" ? entry.rounds : null,
    given: entry.given,
    learned: entry.learned,
    promoted: entry.promoted,
    outcome: entry.valid ? "passed" : "failed",
    mock: entry.mock,
  }))
  const selectedIndex = ordered.findIndex((entry) => entry.attempt_n === selectedAttemptN)

  const error = listError ?? detailError

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
        Drafts
      </h3>
      {error && (
        <p style={{ fontFamily: "var(--font-sans)", fontSize: "var(--text-xs)", color: "var(--danger-text)", margin: 0 }}>{error}</p>
      )}
      {entries.length === 0 ? (
        <p style={{ fontFamily: "var(--font-sans)", fontSize: "var(--text-xs)", color: "var(--text-muted)", margin: 0 }}>
          No drafts yet.
        </p>
      ) : (
        <AttemptChart attempts={attempts} selected={selectedIndex === -1 ? null : selectedIndex} onSelect={(index) => select(ordered[index].attempt_n)} />
      )}
      {detail && <AttemptDetail detail={detail} onRestoreConfigFromAttempt={onRestoreConfigFromAttempt} />}
    </div>
  )
}
