// Local, self-contained shapes for this component group. Deliberately NOT
// imported from `orchestrator-types`: design-system stays a pure UI kit
// with zero backend/domain-type awareness (matching Tabs's own "contains
// no knowledge of what the tabs represent, callers own that"), the same
// principle applied to a bigger component. These structurally match
// generation_toolkit.prompt_config's real shapes today, but a stage panel
// that depends on both this package and a backend-specific one is what
// adapts between the two, not this package.

export type AttachmentType = "text" | "file" | "context"

export interface Attachment {
  id: string
  name: string
  type: AttachmentType
  content?: string
  path?: string
  key?: string
}

export interface PromptConfig {
  system_prompt: string
  attachments: Attachment[]
  learned_constraints?: string[]
  label?: string | null
  platform_hints?: string[]
  _version?: string
}

export interface PresetInfo {
  id: string
  label: string
  platform_hints: string[]
}

export interface PromptDiff {
  system_prompt_changed: boolean
  attachments_added: string[]
  attachments_removed: string[]
  attachments_changed: string[]
}

export interface PromptPreview {
  system_prompt: string
  user_content: string
}

export interface BrokenReference {
  id: string
  error: string
}

// Which platform's own prompt is being edited (a slug, "gitlab", or
// "default") plus what a human-editable label/hints picker offers.
export interface PromptBuilderManifest {
  // The stage's own mode string ("generation", "comparison", ...) - never
  // shown to a human, just what identifies which real prompt this is to
  // every callback below.
  name: string
  // Human-facing label for this prompt as a whole, e.g. "PSM generation
  // prompt".
  label: string
  attachmentTypes: AttachmentType[]
  contextKeyOptions: { key: string; label: string }[]
  supportsPresets: boolean
}

export interface PromptBuilderCallbacks {
  onLoad: (preset: string) => Promise<PromptConfig>
  onSave: (preset: string, config: PromptConfig) => Promise<PromptConfig>
  onListPresets?: () => Promise<PresetInfo[]>
  onPreview: (preset: string) => Promise<PromptPreview>
  onListAvailableFiles?: () => Promise<string[]>
  onLoadHistory: (preset: string) => Promise<string[]>
  onDiffVersions: (preset: string, versionA: string, versionB: string) => Promise<PromptDiff>
  onRestoreVersion: (preset: string, version: string) => Promise<PromptConfig>
  onRevertToDefault: (preset: string) => Promise<PromptConfig>
  onPromoteToDefault: (preset: string) => Promise<PromptConfig>
  onCheckReferences: (preset: string) => Promise<BrokenReference[]>
  onAddLearnedConstraints: (preset: string, constraints: string[]) => Promise<PromptConfig>
  onRemoveLearnedConstraint: (preset: string, constraint: string) => Promise<PromptConfig>
}

export interface PromptBuilderProps {
  manifest: PromptBuilderManifest
  callbacks: PromptBuilderCallbacks
  readOnly?: boolean
}
