import { useEffect, useRef, useState } from "react"
import type { Attachment, BrokenReference, PromptConfig } from "./types"

// A "file" attachment with no path picked yet, or a "context" attachment
// with no key picked yet - the exact shape defaultAttachment (see
// PromptDocument.tsx) gives a brand-new one before a human has actually
// filled it in. generation_toolkit.attachments.resolve.resolve_attachments
// rejects either as unresolvable, so saving one is a guaranteed, useless
// 400 - real for a config a human genuinely finished editing, but routine
// for the ordinary few seconds between clicking "Add file reference" and
// actually picking a file. Scheduling below skips a debounced save
// entirely while this is true, instead of firing a save doomed to fail.
function hasUnresolvableAttachment(attachments: Attachment[]): boolean {
  return attachments.some(
    (a) => (a.type === "file" && !a.path) || (a.type === "context" && !a.key)
  )
}

// How long to wait after the last edit before actually saving - long
// enough that steady typing doesn't fire a save on every short pause,
// short enough that a real pause reads as "done for now" without feeling
// slow to persist.
const SAVE_DEBOUNCE_MS = 1500

// How long to wait before silently retrying a failed auto-save that looks
// transient (a network failure, or a 5xx) - long enough not to hammer a
// backend that's genuinely struggling, short enough that a real blip
// heals itself well within a normal editing session with no action from
// the human. A save that fails with a 4xx (a genuinely broken attachment
// reference, say) never retries at all - see isRetryable below - since
// the exact same request would just fail again forever.
const SAVE_RETRY_DELAY_MS = 5000

// A rejection carrying a numeric "status" (see ui-host's own
// orchestrator.service.ts errorFor, which tags every thrown Error this
// way) came from a real HTTP response: a 4xx means the request itself is
// wrong (a broken attachment reference, an invalid context key) and will
// fail identically forever, so only a missing status (a network-level
// failure, no response at all) or a 5xx (the backend's own problem, likely
// transient) is worth retrying automatically.
function isRetryable(e: unknown): boolean {
  const status = e && typeof e === "object" && "status" in e ? (e as { status?: unknown }).status : undefined
  return typeof status !== "number" || status >= 500
}

export interface AutoSaveCallbacks {
  onSave: (config: PromptConfig, options?: { keepalive?: boolean }) => Promise<PromptConfig>
  onCheckReferences: () => Promise<BrokenReference[]>
}

// Debounced auto-save for the current draft: no manual Save button, an
// edit persists itself a short pause after the human stops typing, the
// same way Google Docs etc. do. Pulled out of PromptBuilder's own render
// body into its own hook so that component stays focused on rendering the
// document, not also owning every concern of how a draft actually reaches
// the backend (scheduling, retrying, not overlapping two requests, and
// flushing a still-pending save before the page or this component itself
// goes away).
export function useAutoSave(
  config: PromptConfig | null,
  setConfig: (updater: (current: PromptConfig | null) => PromptConfig | null) => void,
  callbacks: AutoSaveCallbacks,
  onBrokenReferences: (broken: BrokenReference[]) => void
) {
  const [saving, setSaving] = useState(false)
  const [saveError, setSaveError] = useState<string | null>(null)
  // True while the draft has an attachment that isn't filled in yet (see
  // hasUnresolvableAttachment) - the scheduling effect below skips saving
  // entirely in that case, so this exists purely so PromptBuilder can show
  // a calm "not saved yet, finish this attachment" instead of leaving the
  // human staring at a stale "Saving…"/"All changes saved" with no
  // indication anything is actually pending.
  const [incomplete, setIncomplete] = useState(false)

  // The attachments signature (not learned_constraints - see the
  // scheduling effect below for why) as of the last known-good state on
  // the backend: whatever onLoad/restore most recently confirmed, or
  // whatever a save most recently confirmed. The scheduling effect below
  // only ever starts the debounce timer when the current draft's own
  // signature no longer matches this, so loading or restoring a config
  // never itself triggers a pointless immediate re-save of the exact same
  // content.
  const lastSavedSignatureRef = useRef<string | null>(null)
  const saveTimerRef = useRef<ReturnType<typeof setTimeout> | null>(null)
  // Whether an onSave request is currently in flight - guards against a
  // second one starting before the first resolves. Without this, a
  // slower first response landing after a faster second one would
  // silently overwrite what the second one just persisted, both in this
  // component's own state and in the backend's own version history.
  // While one is in flight, a newer edit just records itself in
  // pendingSaveRef below instead of firing its own request.
  const savingRef = useRef(false)
  // Set (to the options the deferred call should use) when an edit
  // arrives while a save is already in flight - consumed the moment that
  // save's own `.finally` runs, which fires the real deferred save with
  // those exact options, so a flush that had to wait behind an ordinary
  // save still asks for keepalive once its turn actually comes.
  const pendingSaveRef = useRef<{ keepalive: boolean } | null>(null)
  // A failed save schedules its own retry here, cleared and superseded
  // the moment any other save (the normal debounce, the retry itself, or
  // a flush) starts - so at most one retry is ever outstanding.
  const retryTimerRef = useRef<ReturnType<typeof setTimeout> | null>(null)
  // Mirrors `config` every render so the retry timer, the pending-save
  // follow-up, and the unload/unmount flush below can always read the
  // actual latest draft, not whatever draft happened to be in scope when
  // their own closure was created.
  const configRef = useRef(config)
  configRef.current = config
  // False once this component has unmounted. runSave below still starts
  // the actual save request either way - a flush fired on unmount still
  // gets sent - it just stops touching React state it can no longer
  // safely update afterward.
  const mountedRef = useRef(true)

  // The actual auto-save request, shared by the debounce effect below,
  // its own failure retry, a save that arrives while another is already
  // in flight, and the unload/unmount flush effect further down - one
  // place that decides both whether a save is even needed right now (has
  // anything changed since the last known-saved signature) and whether
  // one can start right now (nothing already in flight).
  const runSave = (options: { keepalive?: boolean } = {}) => {
    const current = configRef.current
    if (!current) return
    // Guards this path too, not just the scheduling effect below: a retry,
    // a queued pending-save, or the unmount/unload flush can all reach
    // runSave well after it was first scheduled, by which point the draft
    // it now reads off configRef could have become unresolvable (or, the
    // reverse, resolvable) in the meantime.
    if (hasUnresolvableAttachment(current.attachments)) return
    const signature = JSON.stringify(current.attachments)
    if (signature === lastSavedSignatureRef.current) return
    if (savingRef.current) {
      pendingSaveRef.current = { keepalive: options.keepalive === true }
      return
    }
    if (retryTimerRef.current) {
      clearTimeout(retryTimerRef.current)
      retryTimerRef.current = null
    }
    savingRef.current = true
    if (mountedRef.current) {
      setSaving(true)
      setSaveError(null)
    }
    callbacks
      .onSave(current, options.keepalive ? { keepalive: true } : undefined)
      .then(async (saved) => {
        // Marks the exact snapshot that was actually sent as saved, not
        // whatever the response happens to echo back - `current` is that
        // snapshot, captured before this request went out, so comparing
        // against it doesn't depend on the backend echoing attachments
        // back with byte-for-byte identical JSON key order, which nothing
        // about a cross-language HTTP round trip actually guarantees.
        lastSavedSignatureRef.current = signature
        if (!mountedRef.current) return
        setConfig((c) => c && { ...c, _version: saved._version })
        onBrokenReferences(await callbacks.onCheckReferences())
      })
      .catch((e) => {
        if (!mountedRef.current) return
        setSaveError(e instanceof Error ? e.message : "Save failed.")
        if (isRetryable(e)) {
          retryTimerRef.current = setTimeout(() => runSaveRef.current(), SAVE_RETRY_DELAY_MS)
        }
      })
      .finally(() => {
        savingRef.current = false
        if (mountedRef.current) setSaving(false)
        // Checked regardless of mounted state: a flush that arrived while
        // this save was in flight (see the unmount/unload effect below)
        // still deserves to actually fire once this save is out of the
        // way, even though nothing here can display it any more.
        if (pendingSaveRef.current) {
          const next = pendingSaveRef.current
          pendingSaveRef.current = null
          runSaveRef.current(next)
        }
      })
  }
  // Mirrors `runSave` every render, the same reasoning as configRef above
  // - the unload/unmount effect only ever runs its setup once (empty
  // deps, so it fires on the very first render and never again), so
  // calling `runSave` directly from inside it would permanently pin that
  // first render's own closure (and whichever `callbacks` prop was
  // current then) instead of always reaching the latest one.
  const runSaveRef = useRef(runSave)
  runSaveRef.current = runSave

  // Schedules (or reschedules) the debounced save whenever the draft's
  // own attachments actually change. Deliberately depends on
  // `config?.attachments` alone, not the whole `config` object: a
  // learned_constraints-only change (add/remove/promote/reorder, see
  // PromptBuilder's own mergeLearnedConstraints) produces a new `config`
  // identity with the very same `attachments` array reference, so keying
  // on that reference instead means a constraint action taken while an
  // attachments edit's own save is still pending doesn't reset that
  // pending save's countdown for no reason.
  useEffect(() => {
    if (!config) return
    if (saveTimerRef.current) clearTimeout(saveTimerRef.current)
    // A freshly added, not-yet-filled-in attachment (an empty file path or
    // context key) can never resolve - see hasUnresolvableAttachment -
    // skip scheduling a save doomed to fail instead of firing one only to
    // show the human a scary "request failed" for what's really just the
    // ordinary few seconds before they finish picking a value.
    if (hasUnresolvableAttachment(config.attachments)) {
      setIncomplete(true)
      // A prior failed save's error would otherwise sit there unchanged,
      // showing both it and the calm "not saved yet" message together -
      // this state genuinely supersedes it: there's nothing failing right
      // now, saving just hasn't started because there's nothing valid yet.
      setSaveError(null)
      return
    }
    setIncomplete(false)
    const signature = JSON.stringify(config.attachments)
    if (signature === lastSavedSignatureRef.current) return
    saveTimerRef.current = setTimeout(() => runSaveRef.current(), SAVE_DEBOUNCE_MS)
    return () => {
      if (saveTimerRef.current) clearTimeout(saveTimerRef.current)
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [config?.attachments])

  // Flushes a still-pending debounced save instead of losing it: a real
  // browser tab close/refresh/navigation fires pagehide/beforeunload
  // before this component ever gets to unmount normally, while switching
  // to a different stage panel inside the app itself only unmounts this
  // component, with neither browser event firing - covering both means an
  // edit made in the last SAVE_DEBOUNCE_MS before leaving, either way,
  // still reaches the backend instead of silently vanishing. `keepalive`
  // asks the fetch itself to outlive the page (see ui-host's own
  // savePromptConfig) - restricted to exactly this flush, not every
  // ordinary save, since a keepalive request is capped at 64KB by the
  // browser and an everyday save shouldn't risk hitting that. runSave is
  // already a no-op when nothing actually changed since the last save, so
  // calling it unconditionally here is safe even when there's nothing to
  // flush.
  useEffect(() => {
    mountedRef.current = true
    const flush = () => {
      if (!saveTimerRef.current) return
      clearTimeout(saveTimerRef.current)
      saveTimerRef.current = null
      runSaveRef.current({ keepalive: true })
    }
    window.addEventListener("pagehide", flush)
    window.addEventListener("beforeunload", flush)
    return () => {
      mountedRef.current = false
      window.removeEventListener("pagehide", flush)
      window.removeEventListener("beforeunload", flush)
      if (retryTimerRef.current) clearTimeout(retryTimerRef.current)
      flush()
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [])

  // Marks `attachments` as already saved as of right now - called after a
  // fresh load or a restore, both of which hand back a config that's
  // already exactly what the backend has, so the scheduling effect above
  // shouldn't treat that as a fresh, unsaved edit and immediately
  // re-save the exact thing that was just loaded or restored.
  const markSaved = (attachments: Attachment[]) => {
    lastSavedSignatureRef.current = JSON.stringify(attachments)
  }

  return { saving, saveError, incomplete, markSaved }
}
