import { useEffect, useState } from "react"
import { Button } from "../Button"
import { AttachmentList } from "./AttachmentList"
import { JsonView } from "./JsonView"
import { LearnedConstraintsList } from "./LearnedConstraintsList"
import { PresetPicker } from "./PresetPicker"
import { PreviewPane } from "./PreviewPane"
import { SystemPromptEditor } from "./SystemPromptEditor"
import { VersionHistory } from "./VersionHistory"
import type { BrokenReference, PromptBuilderProps, PromptConfig } from "./types"

export type {
  Attachment,
  AttachmentType,
  BrokenReference,
  PresetInfo,
  PromptBuilderCallbacks,
  PromptBuilderManifest,
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
export function PromptBuilder({ manifest, callbacks, readOnly = false }: PromptBuilderProps) {
  const [preset, setPreset] = useState("default")
  const [config, setConfig] = useState<PromptConfig | null>(null)
  const [availableFiles, setAvailableFiles] = useState<string[] | undefined>(undefined)
  const [broken, setBroken] = useState<BrokenReference[]>([])
  const [saving, setSaving] = useState(false)
  const [saveError, setSaveError] = useState<string | null>(null)
  const [loadError, setLoadError] = useState<string | null>(null)

  useEffect(() => {
    let cancelled = false
    setLoadError(null)
    callbacks
      .onLoad(preset)
      .then((loaded) => {
        if (!cancelled) setConfig(loaded)
      })
      .catch((e) => {
        if (!cancelled) setLoadError(e instanceof Error ? e.message : "Could not load prompt.")
      })
    callbacks
      .onCheckReferences(preset)
      .then((result) => {
        if (!cancelled) setBroken(result)
      })
      .catch(() => undefined)
    return () => {
      cancelled = true
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [preset])

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
      const saved = await callbacks.onSave(preset, config)
      setConfig(saved)
      setBroken(await callbacks.onCheckReferences(preset))
    } catch (e) {
      setSaveError(e instanceof Error ? e.message : "Save failed.")
    } finally {
      setSaving(false)
    }
  }

  const revert = async () => setConfig(await callbacks.onRevertToDefault(preset))
  const promoteToDefault = async () => {
    await callbacks.onPromoteToDefault(preset)
  }

  const addConstraint = async (constraint: string) => setConfig(await callbacks.onAddLearnedConstraints(preset, [constraint]))
  const removeConstraint = async (constraint: string) => setConfig(await callbacks.onRemoveLearnedConstraint(preset, constraint))

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

      {manifest.supportsPresets && callbacks.onListPresets && (
        <PresetPicker activePreset={preset} onListPresets={callbacks.onListPresets} onChange={setPreset} />
      )}

      <SystemPromptEditor
        value={config.system_prompt}
        readOnly={readOnly}
        onChange={(value) => setConfig({ ...config, system_prompt: value })}
      />

      <AttachmentList
        attachments={config.attachments}
        attachmentTypes={manifest.attachmentTypes}
        contextKeyOptions={manifest.contextKeyOptions}
        availableFiles={availableFiles}
        broken={broken}
        readOnly={readOnly}
        onChange={(attachments) => setConfig({ ...config, attachments })}
      />

      <LearnedConstraintsList
        constraints={config.learned_constraints ?? []}
        readOnly={readOnly}
        onAdd={addConstraint}
        onRemove={removeConstraint}
      />

      <PreviewPane onPreview={() => callbacks.onPreview(preset)} />
      <JsonView config={config} />
      <VersionHistory
        onLoadHistory={() => callbacks.onLoadHistory(preset)}
        onDiffVersions={(versionA, versionB) => callbacks.onDiffVersions(preset, versionA, versionB)}
        onRestoreVersion={(version) => callbacks.onRestoreVersion(preset, version)}
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
