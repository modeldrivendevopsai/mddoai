// Local, self-contained shapes for this component group. Deliberately NOT
// imported from `orchestrator-types`: design-system stays a pure UI kit
// with zero backend/domain-type awareness (matching Tabs's own "contains
// no knowledge of what the tabs represent, callers own that"), the same
// principle applied to a bigger component. These structurally match
// generation_toolkit.prompt_config's real shapes today, but a stage panel
// that depends on both this package and a backend-specific one is what
// adapts between the two, not this package.

export type AttachmentType = "text" | "file" | "context"

// The placeholder name a brand-new "file" attachment starts with (see
// PromptDocument.tsx's own defaultAttachment) - shared as one constant
// rather than duplicated as a bare string literal, since DocumentBlock.tsx's
// own upload flow compares against it too (to only replace a still-default
// name, never one the human already typed themselves), and a second,
// independent copy of the same literal is the kind of thing a future rename
// silently stops matching.
export const DEFAULT_FILE_ATTACHMENT_NAME = "New file reference"

// The version id generation_toolkit.prompt_config.history's own
// SHIPPED_DEFAULT_VERSION sentinel uses for the git-committed shipped
// default, folded into the same version-history timeline as its oldest
// entry rather than a separate "revert to default" concept - restoring it
// (VersionHistory.tsx's own Restore button) IS reverting to default, no
// separate button or callback needed.
export const SHIPPED_DEFAULT_VERSION = "shipped"

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
  _version?: string
}

// One real line, from a real line-by-line diff (computed server-side) of
// whichever field actually holds an attachment's meaningful text - never a
// raw diff-markup string a UI would have to re-parse.
export interface PromptDiffLine {
  op: "added" | "removed" | "unchanged"
  text: string
}

export interface ChangedAttachment {
  id: string
  before: Attachment
  after: Attachment
  content_diff: PromptDiffLine[]
}

export interface PromptDiff {
  // The real, full attachment, not just its id, so a UI can show exactly
  // what was added or removed, not only that something was.
  attachments_added: Attachment[]
  attachments_removed: Attachment[]
  // No system_prompt_changed flag: a change to the system-message-role
  // attachment already shows up as its own entry here, the same as any
  // other edited attachment.
  attachments_changed: ChangedAttachment[]
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

// What adding/removing/promoting a learned constraint actually returns
// now - just the real, current list, not a full PromptConfig. Constraints
// live in their own separate, never-reverted store (backend's own
// learned_constraints.py), entirely outside the versioned attachments/text
// a Save/Restore touches, so there's no "_version" or "attachments" for
// one of these actions to plausibly hand back any more.
export interface LearnedConstraintsUpdate {
  learned_constraints: string[]
}

// Which real config is being edited, plus what a human-editable prompt
// document offers.
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
}

export interface PromptBuilderCallbacks {
  onLoad: () => Promise<PromptConfig>
  // `options.keepalive`, when true, asks the underlying request to outlive
  // this page (see useAutoSave's own unload/unmount flush) - only that one
  // flush call ever sets it, since a keepalive request is capped at 64KB by
  // the browser and an everyday save shouldn't risk hitting that.
  onSave: (config: PromptConfig, options?: { keepalive?: boolean }) => Promise<PromptConfig>
  // Resolves exactly the `config` given, not whatever the last Save left
  // on disk - a caller always passes its own current, possibly-unsaved
  // draft, so "preview" and "show real content" (see DocumentBlock.tsx's
  // own AttachmentPreview) reflect what's actually on screen right now.
  onPreview: (config: PromptConfig) => Promise<PromptPreview>
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
  onLoadHistory: () => Promise<string[]>
  onDiffVersions: (versionA: string, versionB: string) => Promise<PromptDiff>
  // "Revert to default" is not a separate callback: the shipped default is
  // just the oldest entry in the same history onLoadHistory returns (see
  // VersionHistory.tsx's own SHIPPED_DEFAULT_VERSION), restored through
  // this exact same call.
  onRestoreVersion: (version: string) => Promise<PromptConfig>
  onCheckReferences: () => Promise<BrokenReference[]>
  onAddLearnedConstraints: (constraints: string[]) => Promise<LearnedConstraintsUpdate>
  onRemoveLearnedConstraint: (constraint: string) => Promise<LearnedConstraintsUpdate>
}

// Promoting one validated run's own live corrections into this same
// config's permanent constraints list - optional, since not every real
// caller has a run to promote from (e.g. no result yet, or the last
// result didn't validate). Rendered inside the permanent-constraints
// section itself, not a separate control elsewhere on the page, since
// that's the one list it actually writes into.
export interface PromptBuilderPromotion {
  // The exact constraint text this run's own latest validated result
  // produced - shown as an editable draft a human confirms or edits
  // before it's actually sent, never applied as-is.
  initialBlock: string
  onPromote: (constraints: string[]) => Promise<LearnedConstraintsUpdate>
}

export interface PromptBuilderProps {
  manifest: PromptBuilderManifest
  callbacks: PromptBuilderCallbacks
  promote?: PromptBuilderPromotion
  readOnly?: boolean
}
