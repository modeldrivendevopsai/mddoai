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
}

// How often the sidebar's lists refresh. What they show changes from actions
// taken elsewhere (generating a pipeline, starting or approving a run), and
// nothing here would otherwise hear about it. Polled on the same "MVP: poll,
// don't add a websocket" basis as useIntegration's own event polling, but
// slower, since a list is far less latency-sensitive than live events
// (engineering guess: a few seconds).
export const SIDEBAR_POLL_INTERVAL_MS = 4000;

// The generated-pipelines list on the "Pipelines" tab.
export const SIDEBAR_PIPELINES_COPY = {
  label: 'Your pipelines',
  empty: 'Nothing generated yet',
  loadError: 'Could not load your pipelines',
} as const;

export const SIDEBAR_TABS: SidebarTab[] = [
  { id: 'pipeline', label: 'Pipelines' },
  { id: 'platform', label: 'Platforms' },
];

export const SIDEBAR_ACTIONS: Record<SessionType, SidebarAction[]> = {
  pipeline: [{ id: 'new-pipeline', label: 'New pipeline', icon: 'Plus' }],
  platform: [
    { id: 'add-platform', label: 'Add a new platform', icon: 'Plus' },
    { id: 'copy-platform', label: 'Copy a platform', icon: 'Plus' },
  ],
};
