import { useEffect, useState } from "react"
import type { PresetInfo } from "./types"

interface PresetPickerProps {
  activePreset: string
  onListPresets: () => Promise<PresetInfo[]>
  onChange: (preset: string) => void
}

// Lists every real preset (a platform slug, or "default") this prompt has,
// each with its own real label/platform_hints metadata - not the preset's
// bare storage id, see generation_toolkit.prompt_config.presets for why
// matching is data-driven (platform_hints), not identity-driven (the id).
export function PresetPicker({ activePreset, onListPresets, onChange }: PresetPickerProps) {
  const [presets, setPresets] = useState<PresetInfo[] | null>(null)

  useEffect(() => {
    let cancelled = false
    onListPresets().then((result) => {
      if (!cancelled) setPresets(result)
    })
    return () => {
      cancelled = true
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [])

  if (!presets) return null

  return (
    <label style={{ display: "flex", flexDirection: "column", gap: "var(--space-1)" }}>
      <span style={{ fontFamily: "var(--font-sans)", fontSize: "var(--text-xs)", fontWeight: "var(--weight-bold)", color: "var(--text-strong)" }}>
        Preset
      </span>
      <select
        className="orch-field"
        value={activePreset}
        onChange={(e) => onChange(e.target.value)}
        style={{
          border: "1px solid var(--border-default)",
          borderRadius: "var(--radius-md)",
          padding: "var(--space-2) var(--space-3)",
          fontFamily: "var(--font-sans)",
          fontSize: "var(--text-sm)",
          background: "var(--surface-card)",
          color: "var(--text-body)",
        }}
      >
        {presets.map((preset) => (
          <option key={preset.id} value={preset.id}>
            {preset.label}
          </option>
        ))}
      </select>
    </label>
  )
}
