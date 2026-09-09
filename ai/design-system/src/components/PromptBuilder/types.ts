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
  // No system_prompt field: the config's own first "text" attachment IS
  // the system message (see generation_toolkit.prompt_config.resolution's
  // own resolve_for_call) - built, edited, and reordered the same way as
  // every other attachment, not a separate field a human can't remove.
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
  // No system_prompt_changed flag: a change to the system-message-role
  // attachment already shows up as its own id in attachments_changed,
  // the same as any other edited attachment.
  attachments_added: string[]
  attachments_removed: string[]
  attachments_changed: string[]
}

export interface PromptPreview {
  system_prompt: string
  user_content: string
  // Each body attachment's own real resolved content, keyed by its id -
  // see DocumentBlock's own AttachmentPreview for where this is consumed.
  attachments: Record<string, string>
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
  // Real file uploads (dropping an OS file onto the document): saves it to
  // a real backend "attachments volume" and returns the safe stored path
  // to use as a new "file" attachment's own `path` - optional, matching
  // onListAvailableFiles' own precedent, since not every real caller of
  // this component group has a backend upload endpoint behind it yet.
  // Without it, a dropped file still becomes a "text" attachment (its
  // content copied in client-side), see PromptDocument.tsx's own
  // insertFilesAt.
  onUploadFile?: (file: File) => Promise<string>
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
