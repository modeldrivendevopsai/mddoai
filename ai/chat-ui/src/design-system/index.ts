/**
 * MDDOAI Design System — barrel export.
 * Every page/component should import primitives from here
 * (`import { Button, Panel } from '../design-system'`), never reach into
 * `design-system/components/*` directly. This is the one place to repoint
 * if/when the official mddoai-design-system skill package is added to the
 * repo — swap these re-exports for the real package and nothing else in
 * the app needs to change.
 */
export { Button } from './components/Button';
export type { ButtonVariant } from './components/Button';
export { Icon } from './components/Icon';
export type { IconName } from './components/Icon';
export { Tabs } from './components/Tabs';
export type { TabItem } from './components/Tabs';
export { StatusPill } from './components/StatusPill';
export type { StatusPillVariant } from './components/StatusPill';
export { Panel } from './components/Panel';
export type { PanelTone } from './components/Panel';
