import type { SessionType } from '../services/sessions.service';
import type { IconName } from 'design-system';

/**
 * Sidebar navigation config. Adding/renaming/reordering a tab or an action
 * button is a data change here, not a component edit.
 */
export interface SidebarTab {
  id: SessionType;
  label: string;
}

export interface SidebarAction {
  id: string;
  label: string;
  icon: IconName;
  // True for an action with no real backend behind it yet — rendered
  // disabled rather than silently landing on the "Add/Update a CI/CD
  // Platform" flow, a different, real mode. See startOptions.config.ts's
  // matching comingSoon flag for the StartScreen card equivalent.
  comingSoon?: boolean;
}

export const SIDEBAR_TABS: SidebarTab[] = [
  { id: 'pipeline', label: 'Pipelines' },
  { id: 'platform', label: 'Platforms' },
];

export const SIDEBAR_ACTIONS: Record<SessionType, SidebarAction[]> = {
  pipeline: [{ id: 'new-pipeline', label: 'New pipeline', icon: 'Plus', comingSoon: true }],
  platform: [
    { id: 'add-platform', label: 'Add a new platform', icon: 'Plus' },
    { id: 'copy-platform', label: 'Copy a platform', icon: 'Plus' },
  ],
};
