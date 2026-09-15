import { useEffect, useState } from "react"
import { Button } from "../Button"
import { StatusPill } from "../StatusPill"
import { SHIPPED_DEFAULT_VERSION } from "./types"
import type { Attachment, ChangedAttachment, PromptConfig, PromptDiff, PromptDiffLine } from "./types"

interface VersionHistoryProps {
  onLoadHistory: () => Promise<string[]>
  onDiffVersions: (versionA: string, versionB: string) => Promise<PromptDiff>
  onRestoreVersion: (version: string) => Promise<PromptConfig>
  onRestored: (config: PromptConfig) => void
  // The version id this document's own current draft was loaded from or
  // last saved as (see PromptBuilder's own config._version) - defaults to
  // SHIPPED_DEFAULT_VERSION, since a config nobody has ever saved yet is
  // exactly the shipped default. Marks that one row "Current" below,
  // answering "which version am I actually looking at right now" directly
  // instead of leaving a human to guess from a bare timestamp list.
  currentVersion?: string
  readOnly?: boolean
}

// A real saved version's id is an ISO-sortable UTC timestamp plus a random
// suffix (see generation_toolkit.prompt_config.storage.save_config) - a
// human comparing "an hour ago" against a raw microsecond-precision UTC
// string shouldn't have to do that math themselves. Parses just the
// timestamp prefix and renders it in the browser's own local time; falls
// back to the raw id when it doesn't parse (SHIPPED_DEFAULT_VERSION, or any
// id shape this doesn't recognize), so nothing here can hide a real id a
// caller (Restore, Diff) still needs underneath - see the row's own
// title={version} for that raw id.
function versionLabel(version: string): string {
  if (version === SHIPPED_DEFAULT_VERSION) return "Original shipped prompt"
  const match = version.match(/^(\d{4})(\d{2})(\d{2})T(\d{2})(\d{2})(\d{2})/)
  if (!match) return version
  const [, year, month, day, hour, minute, second] = match
  const parsed = new Date(Date.UTC(+year, +month - 1, +day, +hour, +minute, +second))
  if (Number.isNaN(parsed.getTime())) return version
  return parsed.toLocaleString(undefined, { dateStyle: "medium", timeStyle: "short" })
}

const listItemStyle = {
  display: "flex",
  alignItems: "center",
  gap: "var(--space-2)",
  fontFamily: "var(--font-mono)",
  fontSize: "var(--text-2xs)",
  color: "var(--text-body)",
} as const

function VersionRow({
  version,
  isCurrent,
  selectedA,
  selectedB,
  onSelectA,
  onSelectB,
  onRestore,
  readOnly,
}: {
  version: string
  isCurrent: boolean
  selectedA: string
  selectedB: string
  onSelectA: () => void
  onSelectB: () => void
  onRestore: () => void
  readOnly: boolean
}) {
  return (
    <li style={listItemStyle}>
      <input
        type="radio"
        name="version-a"
        checked={selectedA === version}
        onChange={onSelectA}
        aria-label={`Select ${versionLabel(version)} as diff base`}
      />
      <input
        type="radio"
        name="version-b"
        checked={selectedB === version}
        onChange={onSelectB}
        aria-label={`Select ${versionLabel(version)} as diff target`}
      />
      <span style={{ flex: 1, overflow: "hidden", textOverflow: "ellipsis" }} title={version}>
        {versionLabel(version)}
      </span>
      {isCurrent && <StatusPill variant="info">Current</StatusPill>}
      {/* Restoring the version you're already on is always a no-op (see
          history.restore_version's own docstring) - offering the button
          here would just be a click that visibly does nothing. */}
      {!readOnly && !isCurrent && (
        <Button variant="ghost" size="sm" onClick={onRestore}>
          Restore
        </Button>
      )}
    </li>
  )
}

function attachmentLabel(attachment: Attachment): string {
  return `${attachment.name} (${attachment.type})`
}

const diffLineStyle = (op: PromptDiffLine["op"]) =>
  ({
    added: { background: "var(--success-100)", color: "var(--text-strong)" },
    removed: { background: "var(--danger-100)", color: "var(--text-strong)" },
    unchanged: { color: "var(--text-muted)" },
  })[op]

function DiffLines({ lines }: { lines: PromptDiffLine[] }) {
  return (
    <pre
      style={{
        margin: 0,
        padding: "var(--space-2)",
        borderRadius: "var(--radius-sm)",
        background: "var(--surface-sunken)",
        fontFamily: "var(--font-mono)",
        fontSize: "var(--text-2xs)",
        whiteSpace: "pre-wrap",
        overflow: "auto",
        // A real system prompt can run to hundreds of lines - without a cap
        // a one-line edit renders the whole document's worth of "unchanged"
        // rows, pushing the actual change off screen. 40vh keeps a few dozen
        // lines visible before scrolling, an engineering guess, not a
        // measured value.
        maxHeight: "40vh",
      }}
    >
      {lines.map((line, i) => (
        <div key={i} style={{ ...diffLineStyle(line.op), padding: "0 var(--space-1)" }}>
          {line.op === "added" ? "+ " : line.op === "removed" ? "- " : "  "}
          {line.text}
        </div>
      ))}
    </pre>
  )
}

function ChangedAttachmentDiff({ entry }: { entry: ChangedAttachment }) {
  const renamed = entry.before.name !== entry.after.name
  // content_diff always has one entry per line even when nothing in it
  // actually changed (a "name"-only change still diffs the same,
  // unchanged text against itself) - only an added/removed line means the
  // meaningful field itself actually differs, real content to show, not
  // just a name edit dressed up as a full grey "unchanged" block.
  const hasContentChange = entry.content_diff.some((line) => line.op !== "unchanged")

  return (
    <div style={{ display: "flex", flexDirection: "column", gap: "var(--space-1)" }}>
      <span style={{ fontFamily: "var(--font-sans)", fontSize: "var(--text-xs)", fontWeight: "var(--weight-bold)", color: "var(--text-strong)" }}>
        {attachmentLabel(entry.after)}
      </span>
      {renamed && (
        <p style={{ fontFamily: "var(--font-sans)", fontSize: "var(--text-2xs)", color: "var(--text-muted)", margin: 0 }}>
          Renamed from "{entry.before.name}" to "{entry.after.name}"
        </p>
      )}
      {hasContentChange && <DiffLines lines={entry.content_diff} />}
      {/* Neither a rename nor a real content change - some other field
          differs (e.g. its type), too exotic to justify its own rendering
          here rather than the raw before/after this falls back to. */}
      {!renamed && !hasContentChange && (
        <p style={{ fontFamily: "var(--font-sans)", fontSize: "var(--text-2xs)", color: "var(--text-muted)", margin: 0 }}>
          Changed: {JSON.stringify(entry.before)} → {JSON.stringify(entry.after)}
        </p>
      )}
    </div>
  )
}

// The pinned Current/Original rows, the collapsed older-versions toggle,
// and the diff picker beneath them - split out of VersionHistory's own
// render body purely to keep that function itself short, no behavior of
// its own beyond what VersionHistory already decided (pinning, ordering).
function VersionList({
  versions,
  currentVersion,
  selectedA,
  selectedB,
  onSelectA,
  onSelectB,
  onRestore,
  readOnly,
  showOlderVersions,
  onToggleOlderVersions,
  onRunDiff,
}: {
  versions: string[]
  currentVersion: string
  selectedA: string
  selectedB: string
  onSelectA: (version: string) => void
  onSelectB: (version: string) => void
  onRestore: (version: string) => void
  readOnly: boolean
  showOlderVersions: boolean
  onToggleOlderVersions: () => void
  onRunDiff: () => void
}) {
  // Current first (where you are right now), then the shipped original
  // right below it - deduped to one row when nothing's ever been saved, so
  // Current already IS the shipped default.
  const pinnedIds = Array.from(new Set([currentVersion, SHIPPED_DEFAULT_VERSION]))
  const pinned = pinnedIds.filter((id) => versions.includes(id))
  const older = versions.filter((v) => !pinnedIds.includes(v))
  const rowProps = (version: string) => ({
    version,
    isCurrent: version === currentVersion,
    selectedA,
    selectedB,
    onSelectA: () => onSelectA(version),
    onSelectB: () => onSelectB(version),
    onRestore: () => onRestore(version),
    readOnly,
  })

  return (
    <>
      <p style={{ fontFamily: "var(--font-sans)", fontSize: "var(--text-2xs)", color: "var(--text-muted)", margin: 0 }}>
        Pick a base version (left column) and a target version (right column) below, then diff them.
      </p>

      <ul style={{ margin: 0, padding: 0, listStyle: "none", display: "flex", flexDirection: "column", gap: "var(--space-1)" }}>
        {pinned.map((version) => (
          <VersionRow key={version} {...rowProps(version)} />
        ))}
      </ul>

      {older.length > 0 && (
        <Button variant="ghost" size="sm" onClick={onToggleOlderVersions}>
          {showOlderVersions ? "Hide older versions" : `Show ${older.length} older version${older.length === 1 ? "" : "s"}`}
        </Button>
      )}

      {showOlderVersions && older.length > 0 && (
        <ul style={{ margin: 0, padding: 0, listStyle: "none", display: "flex", flexDirection: "column", gap: "var(--space-1)" }}>
          {older.map((version) => (
            <VersionRow key={version} {...rowProps(version)} />
          ))}
        </ul>
      )}

      <Button variant="secondary" size="sm" disabled={!selectedA || !selectedB} onClick={onRunDiff}>
        Diff selected versions
      </Button>
    </>
  )
}

// The result of diffing two selected versions - a header naming which two
// versions it's actually showing (see VersionHistory's own diffPair), then
// added/removed/changed attachments. Split out for the same reason
// VersionList above was: keeps VersionHistory's own render body to just
// deciding what to show, not also how to lay each part out.
function DiffResult({ diff, diffPair }: { diff: PromptDiff; diffPair: { a: string; b: string } }) {
  return (
    <div style={{ display: "flex", flexDirection: "column", gap: "var(--space-2)" }}>
      <p style={{ fontFamily: "var(--font-sans)", fontSize: "var(--text-2xs)", color: "var(--text-muted)", margin: 0 }}>
        {versionLabel(diffPair.a)} → {versionLabel(diffPair.b)}
      </p>

      {diff.attachments_added.length === 0 && diff.attachments_removed.length === 0 && diff.attachments_changed.length === 0 && (
        <StatusPill variant="info">Identical</StatusPill>
      )}

      {diff.attachments_added.map((a) => (
        <p
          key={a.id}
          style={{
            fontFamily: "var(--font-mono)",
            fontSize: "var(--text-2xs)",
            // --text-strong on --success-100, not --success-500 (too low
            // contrast at this size) - matches diffLineStyle's own
            // added/removed rows above.
            color: "var(--text-strong)",
            background: "var(--success-100)",
            borderRadius: "var(--radius-sm)",
            padding: "var(--space-1) var(--space-2)",
            margin: 0,
          }}
        >
          + {attachmentLabel(a)}
        </p>
      ))}

      {diff.attachments_removed.map((a) => (
        <p
          key={a.id}
          style={{
            fontFamily: "var(--font-mono)",
            fontSize: "var(--text-2xs)",
            color: "var(--text-strong)",
            background: "var(--danger-100)",
            borderRadius: "var(--radius-sm)",
            padding: "var(--space-1) var(--space-2)",
            margin: 0,
          }}
        >
          - {attachmentLabel(a)}
        </p>
      ))}

      {diff.attachments_changed.map((entry) => (
        <ChangedAttachmentDiff key={entry.id} entry={entry} />
      ))}
    </div>
  )
}

// Every save (generation_toolkit.prompt_config.storage.save_config) keeps
// an immutable snapshot - this lists them, diffs two selected ones (a real
// structural, line-by-line diff, computed server-side, not a raw text diff
// of the whole file), and restores one back over the live file. Current and
// the git-committed shipped original (see SHIPPED_DEFAULT_VERSION) are
// always pinned above the rest, in that order, deduped to one row when
// nothing's ever been saved: those two answer "where am I, and what did
// this ship as" - the two things a human opens this for almost every time
// - without making them dig through however many real saves a long editing
// session has piled up first. "Reverting to default" is just Restore on
// the pinned original row, not a separate button or concept.
export function VersionHistory({
  onLoadHistory,
  onDiffVersions,
  onRestoreVersion,
  onRestored,
  currentVersion = SHIPPED_DEFAULT_VERSION,
  readOnly = false,
}: VersionHistoryProps) {
  const [expanded, setExpanded] = useState(false)
  const [versions, setVersions] = useState<string[] | null>(null)
  const [selectedA, setSelectedA] = useState<string>("")
  const [selectedB, setSelectedB] = useState<string>("")
  const [diff, setDiff] = useState<PromptDiff | null>(null)
  // Which two versions actually produced `diff` above - set together with
  // it, so the rendered result can say what it's a diff OF, and cleared as
  // soon as either radio selection changes so a stale diff for a since-
  // abandoned pair can never be mistaken for one covering the current picks.
  const [diffPair, setDiffPair] = useState<{ a: string; b: string } | null>(null)
  const [error, setError] = useState<string | null>(null)
  // Everything but the two pinned rows below starts collapsed - a config
  // edited over a long session can have dozens of real versions, and
  // "what am I looking at right now, and what did it ship as" (Current,
  // Original) answers what a human actually opens this for almost every
  // time, without dumping the entire real history in their face by default.
  const [showOlderVersions, setShowOlderVersions] = useState(false)

  // Refetches every time this list is actually visible and whenever
  // currentVersion changes while it already is, so a closed-then-reopened
  // panel always shows every real version, including the exact one an
  // edit you just made autosaved into existence, and a version created
  // while this panel is already open (autosave firing while you're
  // watching it) shows up live rather than only on your next manual close
  // and reopen. `cancelled` guards a fast second fetch (currentVersion
  // changing again before the first request lands) from having its older
  // response overwrite the newer one.
  useEffect(() => {
    if (!expanded) return
    let cancelled = false
    setError(null)
    onLoadHistory()
      .then((loaded) => {
        if (!cancelled) setVersions(loaded)
      })
      .catch((e) => {
        if (!cancelled) setError(e instanceof Error ? e.message : "Could not load history.")
      })
    return () => {
      cancelled = true
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [expanded, currentVersion])

  const runDiff = async () => {
    if (!selectedA || !selectedB) return
    setError(null)
    try {
      const result = await onDiffVersions(selectedA, selectedB)
      setDiff(result)
      setDiffPair({ a: selectedA, b: selectedB })
    } catch (e) {
      setError(e instanceof Error ? e.message : "Diff failed.")
    }
  }

  const selectDiffBase = (version: string) => {
    setSelectedA(version)
    setDiff(null)
    setDiffPair(null)
  }

  const selectDiffTarget = (version: string) => {
    setSelectedB(version)
    setDiff(null)
    setDiffPair(null)
  }

  const restore = async (version: string) => {
    if (!window.confirm(`Replace the current draft with "${versionLabel(version)}"? Your current draft stays in history and can be restored back.`)) {
      return
    }
    setError(null)
    try {
      onRestored(await onRestoreVersion(version))
    } catch (e) {
      setError(e instanceof Error ? e.message : "Restore failed.")
    }
  }

  return (
    <div style={{ display: "flex", flexDirection: "column", gap: "var(--space-2)" }}>
      <Button variant="ghost" size="sm" onClick={() => setExpanded((e) => !e)}>
        {expanded ? "Hide version history" : "Version history"}
      </Button>

      {expanded && (
        <div style={{ display: "flex", flexDirection: "column", gap: "var(--space-2)" }}>
          {error && <p style={{ fontFamily: "var(--font-sans)", fontSize: "var(--text-xs)", color: "var(--danger-500)", margin: 0 }}>{error}</p>}

          {versions === null && !error && (
            <p style={{ fontFamily: "var(--font-sans)", fontSize: "var(--text-xs)", color: "var(--text-muted)", margin: 0 }}>
              Loading version history…
            </p>
          )}

          {versions && versions.length > 0 && (
            <VersionList
              versions={versions}
              currentVersion={currentVersion}
              selectedA={selectedA}
              selectedB={selectedB}
              onSelectA={selectDiffBase}
              onSelectB={selectDiffTarget}
              onRestore={restore}
              readOnly={readOnly}
              showOlderVersions={showOlderVersions}
              onToggleOlderVersions={() => setShowOlderVersions((s) => !s)}
              onRunDiff={runDiff}
            />
          )}

          {diff && diffPair && <DiffResult diff={diff} diffPair={diffPair} />}
        </div>
      )}
    </div>
  )
}
