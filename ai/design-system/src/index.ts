/**
 * MDDOAI Design System — barrel export.
 * Every consumer (the host and every federated remote) should import
 * primitives from here (`import { Button, Panel } from 'design-system'`),
 * never reach into `design-system/src/components/*` directly. This is the
 * one place to repoint if/when the official mddoai-design-system skill
 * package is added to the repo — swap these re-exports for the real
 * package and nothing else in any consumer needs to change.
 */
export { Button } from './components/Button';
export type { ButtonVariant } from './components/Button';
export { Icon } from './components/Icon';
export type { IconName } from './components/Icon';
export { Tabs } from './components/Tabs';
export type { TabItem } from './components/Tabs';
export { StatusPill } from './components/StatusPill';
export type { StatusPillVariant } from './components/StatusPill';
export { StageInfoNote } from './components/StageInfoNote';
export { PromoteConstraintsAction } from './components/PromoteConstraintsAction';
export type { PromoteConstraintsActionProps } from './components/PromoteConstraintsAction';
export { Panel } from './components/Panel';
export type { PanelTone } from './components/Panel';
export { CodeBlock } from './CodeBlock';
export { PromptBuilder } from './components/PromptBuilder';
export type {
  Attachment,
  AttachmentType,
  BrokenReference,
  PromptBuilderCallbacks,
  PromptBuilderManifest,
  PromptBuilderProps,
  PromptConfig,
  PromptDiff,
  PromptPreview,
} from './components/PromptBuilder';
export { AttemptsBrowser } from './components/AttemptsBrowser';
export type {
  AttemptArtifact,
  AttemptDetailData,
  AttemptsBrowserProps,
  ManifestEntry,
} from './components/AttemptsBrowser';
