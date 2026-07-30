import type { SessionType } from '../services/sessions.service';
import type { IconName } from '../design-system/components/Icon';

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
}

export const SIDEBAR_TABS: SidebarTab[] = [
  { id: 'pipeline', label: 'Pipelines' },
  { id: 'platform', label: 'Platforms' },
];

export const SIDEBAR_ACTIONS: Record<SessionType, SidebarAction[]> = {
  pipeline: [{ id: 'new-pipeline', label: 'New pipeline', icon: 'Plus' }],
  platform: [
    { id: 'add-platform', label: 'Add a new platform', icon: 'Plus' },
    { id: 'copy-platform', label: 'Copy an existing platform', icon: 'Plus' },
  ],
};
