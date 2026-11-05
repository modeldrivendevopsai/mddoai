import type { PanelTone } from 'design-system';

/**
 * Start-screen option cards, the words that explain how they connect, and the
 * copy of the list under them, as data. Copy edits or a reordered/added third
 * option happen here, not in the components.
 */
export interface StartOption {
  id: string;
  tone: Exclude<PanelTone, 'neutral'>;
  title: string;
  description: string;
  cta: string;
}

export const START_OPTIONS: StartOption[] = [
  {
    id: 'generate-pipeline',
    tone: 'pipeline',
    title: 'Generate a CI/CD pipeline',
    description: 'From your architecture file.',
    cta: 'Open',
  },
  {
    id: 'add-update-platform',
    tone: 'platform',
    title: 'Add or update a CI/CD platform',
    description: 'From its documentation.',
    cta: 'Open',
  },
];

// What the picture of the two options says, and all it says: the first screen
// is read at a glance. The two arrows are the two ways round the cycle: `lead`
// names what happened, `then` what to do next.
export const CYCLE_COPY = {
  gapFound: { lead: 'Gap in the pipeline', then: 'Update the platform' },
  platformFixed: { lead: 'Platform fixed', then: 'Generate again' },
} as const;

// How many pipelines the start screen lists. It is a reminder of recent work,
// not the full list: the pipelines screen has that (a design choice, not a
// measured limit).
export const RECENT_PIPELINES_LIMIT = 5;

// The list under the picture follows the sidebar's tab: the platforms on the
// Platforms tab, the latest pipelines on the Pipelines tab.
export const START_LISTS = {
  platforms: { title: 'Supported CI/CD platforms' },
  pipelines: {
    title: 'Latest pipelines',
    loading: 'Loading your pipelines…',
    empty: 'Nothing generated yet. Open "Generate a CI/CD pipeline" to make your first one.',
    retry: 'Try again',
  },
} as const;
