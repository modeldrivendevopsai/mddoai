import { useEffect, useRef, useState } from "react"
import { JsonView } from "./JsonView"
import { PreviewPane } from "./PreviewPane"
import { PromptDocument } from "./PromptDocument"
import { useAutoSave } from "./useAutoSave"
import { VersionHistory } from "./VersionHistory"
import type { BrokenReference, LearnedConstraintsUpdate, PromptBuilderProps, PromptConfig } from "./types"

export type {
  Attachment,
  AttachmentType,
  BrokenReference,
  LearnedConstraintsUpdate,
  PromptBuilderCallbacks,
  PromptBuilderManifest,
  PromptBuilderPromotion,
  PromptBuilderProps,
  PromptConfig,
  PromptDiff,
  PromptPreview,
} from "./types"

// The actual reusable prompt-builder mechanism: manifest + callbacks
// drive everything, this component (and every one under this folder)
// carries no knowledge of psm, atl, or any other specific stage - the same
// component, a different manifest and callback set per caller, exactly
// like the backend's generation_toolkit.prompt_config package is the same
// functions with a different config_dir/context_values per caller.
export function PromptBuilder({ manifest, callbacks, promote, readOnly = false }: PromptBuilderProps) {
  const [config, setConfig] = useState<PromptConfig | null>(null)
  const [availableFiles, setAvailableFiles] = useState<string[] | undefined>(undefined)
  const [broken, setBroken] = useState<BrokenReference[]>([])
  const [loadError, setLoadError] = useState<string | null>(null)
  // A real preview response, tagged with the exact config it was resolved
  // against - not React state, since nothing here is ever rendered
  // directly, only read inside loadAttachmentPreview below. Declared
  // unconditionally up front with every other hook, not after the
  // loading-state early returns below: a hook called on only some renders
  // (e.g. only once `config` has finished loading) breaks React's own
  // "same hooks, same order, every render" rule. Tagging by config, rather
  // than a separate cache + a config-watching clear effect + a separate
  // staleness-check ref, is self-healing on its own: a late response for
  // an old draft can only ever overwrite this with a config that no longer
  // matches the current render's own `config`, so the very next read
  // simply misses and re-fetches - it can never be read back as a false
  // hit for a newer draft.
  const previewCacheRef = useRef<{ config: PromptConfig; attachments: Record<string, string> } | null>(null)
  const { saving, saveError, markSaved } = useAutoSave(config, setConfig, callbacks, setBroken)

  useEffect(() => {
    let cancelled = false
    setLoadError(null)
    previewCacheRef.current = null
    callbacks
      .onLoad()
      .then((loaded) => {
        if (cancelled) return
        markSaved(loaded.attachments)
        setConfig(loaded)
      })
      .catch((e) => {
        if (!cancelled) setLoadError(e instanceof Error ? e.message : "Could not load prompt.")
      })
    callbacks
      .onCheckReferences()
      .then((result) => {
        if (!cancelled) setBroken(result)
      })
      .catch(() => undefined)
    return () => {
      cancelled = true
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [])

  useEffect(() => {
    if (!callbacks.onListAvailableFiles) return
    callbacks.onListAvailableFiles().then(setAvailableFiles).catch(() => undefined)
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [])

  if (loadError) {
    return (
      <p style={{ fontFamily: "var(--font-sans)", fontSize: "var(--text-sm)", color: "var(--danger-500)" }}>{loadError}</p>
    )
  }
  if (!config) {
    return (
      <p style={{ fontFamily: "var(--font-sans)", fontSize: "var(--text-sm)", color: "var(--text-muted)" }}>Loading prompt…</p>
    )
  }

  // Only merges learned_constraints back into local state, never the whole
  // response - add/remove/promote each return just the updated constraints
  // list now (see LearnedConstraintsUpdate), not a full config, since
  // they no longer touch the versioned attachments/text at all. Merging
  // rather than replacing still matters: a naive setConfig(response) would
  // silently discard any attachments edit the human made in this same
  // sitting that hasn't been auto-saved yet.
  const mergeLearnedConstraints = (updated: LearnedConstraintsUpdate) =>
    setConfig((current) => current && { ...current, learned_constraints: updated.learned_constraints })
  const addConstraint = async (constraint: string) => mergeLearnedConstraints(await callbacks.onAddLearnedConstraints([constraint]))
  const removeConstraint = async (constraint: string) => mergeLearnedConstraints(await callbacks.onRemoveLearnedConstraint(constraint))
  // Same merge as add/remove above: a promotion also just changes
  // learned_constraints on this same config, so the promoted correction
  // shows up in the list immediately, in the same section, rather than
  // only after this component happens to remount. Still returns the
  // resolved config (matching the real onPromoteConstraints signature
  // every caller already has), even though the merge above is what this
  // component itself actually cares about.
  const promoteConstraints = promote
    ? async (constraints: string[]) => {
        const updated = await promote.onPromote(constraints)
        mergeLearnedConstraints(updated)
        return updated
      }
    : undefined

  // Backed by the same preview endpoint PreviewPane already calls, resolved
  // against the exact current `config` (attachments and edits included) -
  // the first chip expanded against a given draft fetches every
  // attachment's real content in one call, every later chip on that SAME
  // draft just reads the cache (see previewCacheRef's own comment above for
  // why a plain config-identity tag is enough, with no separate clearing
  // step or staleness check needed).
  const loadAttachmentPreview = async (id: string): Promise<string | undefined> => {
    if (previewCacheRef.current?.config === config) return previewCacheRef.current.attachments[id]
    const preview = await callbacks.onPreview(config)
    previewCacheRef.current = { config, attachments: preview.attachments }
    return preview.attachments[id]
  }

  // A restore (Version History's own Restore button, the shipped default
  // included - see VersionHistory.tsx's own SHIPPED_DEFAULT_VERSION) is
  // already a real save on the backend - marking it caught up here too,
  // not just setConfig(restored) alone, stops the auto-save effect above
  // from treating the resulting config change as a fresh, unsaved edit and
  // immediately re-saving the exact thing that was just restored.
  const handleRestored = (restored: PromptConfig) => {
    markSaved(restored.attachments)
    setConfig(restored)
  }

  return (
    <div style={{ display: "flex", flexDirection: "column", gap: "var(--space-3)" }}>
      <h3
        style={{
          fontFamily: "var(--font-display)",
          fontSize: "var(--text-sm)",
          fontWeight: "var(--weight-bold)",
          color: "var(--text-strong)",
          margin: 0,
        }}
      >
        {manifest.label}
      </h3>

      <PromptDocument
        attachments={config.attachments}
        attachmentTypes={manifest.attachmentTypes}
        contextKeyOptions={manifest.contextKeyOptions}
        availableFiles={availableFiles}
        broken={broken}
        readOnly={readOnly}
        onUploadFile={callbacks.onUploadFile}
        onPreviewAttachment={loadAttachmentPreview}
        onAttachmentsChange={(attachments) => setConfig({ ...config, attachments })}
        learnedConstraints={config.learned_constraints ?? []}
        onAddConstraint={addConstraint}
        onRemoveConstraint={removeConstraint}
        onReorderConstraints={(learned_constraints) => setConfig({ ...config, learned_constraints })}
        promote={promoteConstraints ? { initialBlock: promote!.initialBlock, onPromote: promoteConstraints } : undefined}
      />

      <PreviewPane onPreview={() => callbacks.onPreview(config)} />
      <JsonView config={config} />
      <VersionHistory
        onLoadHistory={() => callbacks.onLoadHistory()}
        onDiffVersions={(versionA, versionB) => callbacks.onDiffVersions(versionA, versionB)}
        onRestoreVersion={(version) => callbacks.onRestoreVersion(version)}
        onRestored={handleRestored}
        readOnly={readOnly}
      />

      {/* No manual Save button: an edit auto-saves itself a short pause
          after you stop typing (see useAutoSave). This is the one place
          that says so, since nothing else on screen would otherwise tell
          you your edits are actually being persisted. */}
      {!readOnly && (
        <p style={{ fontFamily: "var(--font-sans)", fontSize: "var(--text-2xs)", color: "var(--text-muted)", margin: 0 }}>
          {saving ? "Saving…" : "All changes saved automatically"}
        </p>
      )}
      {saveError && (
        <p style={{ fontFamily: "var(--font-sans)", fontSize: "var(--text-xs)", color: "var(--danger-500)", margin: 0 }}>
          {saveError}
        </p>
      )}
    </div>
  )
}
