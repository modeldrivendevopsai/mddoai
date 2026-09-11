import { useEffect, useState } from "react"
import { Button } from "../Button"
import { JsonView } from "./JsonView"
import { PreviewPane } from "./PreviewPane"
import { PromptDocument } from "./PromptDocument"
import { VersionHistory } from "./VersionHistory"
import type { BrokenReference, PromptBuilderProps, PromptConfig } from "./types"

export type {
  Attachment,
  AttachmentType,
  BrokenReference,
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
  const [saving, setSaving] = useState(false)
  const [saveError, setSaveError] = useState<string | null>(null)
  const [loadError, setLoadError] = useState<string | null>(null)
  // Cached from the last onPreview() call (see loadAttachmentPreview
  // below), keyed by attachment id - reused across every chip's own "Show
  // real content" click, rather than re-resolving the whole config on
  // every single click.
  const [previewAttachments, setPreviewAttachments] = useState<Record<string, string> | null>(null)

  useEffect(() => {
    let cancelled = false
    setLoadError(null)
    setPreviewAttachments(null)
    callbacks
      .onLoad()
      .then((loaded) => {
        if (!cancelled) setConfig(loaded)
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

  const save = async () => {
    setSaving(true)
    setSaveError(null)
    try {
      const saved = await callbacks.onSave(config)
      setConfig(saved)
      setBroken(await callbacks.onCheckReferences())
      // A save is the only thing that can change what a real call would
      // resolve (an in-memory, unsaved edit never does, see
      // loadAttachmentPreview's own comment) - without this, a chip
      // previewed once before a save would keep showing its pre-save
      // content forever, since the cache below is otherwise only cleared
      // by a fresh save.
      setPreviewAttachments(null)
    } catch (e) {
      setSaveError(e instanceof Error ? e.message : "Save failed.")
    } finally {
      setSaving(false)
    }
  }

  const revert = async () => setConfig(await callbacks.onRevertToDefault())
  const promoteToDefault = async () => {
    await callbacks.onPromoteToDefault()
  }

  // Only merges learned_constraints (+ the new _version that save stamped)
  // back into local state, never the whole response - the backend's own
  // add/remove both reload the config fresh from disk first (see
  // generation_toolkit.prompt_config.learned_constraints), so a naive
  // setConfig(response) here would silently discard any unsaved edit to
  // system_prompt or attachments the human made in this same sitting,
  // before ever clicking Save.
  const mergeLearnedConstraints = (updated: PromptConfig) =>
    setConfig((current) => current && { ...current, learned_constraints: updated.learned_constraints, _version: updated._version })
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

  // Backed by the same preview endpoint PreviewPane already calls - the
  // first chip expanded in a sitting fetches every attachment's real
  // content in one call, every later chip just reads the cache. May go
  // stale for one save cycle (the same real limitation PreviewPane's own
  // "Preview exact prompt text" button already has) - acceptable since
  // this is a preview, not the source of truth being saved.
  const loadAttachmentPreview = async (id: string): Promise<string | undefined> => {
    if (previewAttachments) return previewAttachments[id]
    const preview = await callbacks.onPreview()
    setPreviewAttachments(preview.attachments)
    return preview.attachments[id]
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

      <PreviewPane onPreview={() => callbacks.onPreview()} />
      <JsonView config={config} />
      <VersionHistory
        onLoadHistory={() => callbacks.onLoadHistory()}
        onDiffVersions={(versionA, versionB) => callbacks.onDiffVersions(versionA, versionB)}
        onRestoreVersion={(version) => callbacks.onRestoreVersion(version)}
        onRestored={setConfig}
        readOnly={readOnly}
      />

      {!readOnly && (
        <div style={{ display: "flex", gap: "var(--space-2)", flexWrap: "wrap" }}>
          <Button variant="primary" size="sm" onClick={save} disabled={saving}>
            {saving ? "Saving…" : "Save"}
          </Button>
          <Button variant="secondary" size="sm" onClick={revert}>
            Revert to default
          </Button>
          <Button variant="ghost" size="sm" onClick={promoteToDefault}>
            Make this the new default
          </Button>
        </div>
      )}
      {saveError && (
        <p style={{ fontFamily: "var(--font-sans)", fontSize: "var(--text-xs)", color: "var(--danger-500)", margin: 0 }}>
          {saveError}
        </p>
      )}
    </div>
  )
}
