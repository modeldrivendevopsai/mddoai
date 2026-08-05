import type { PanelTone } from '../design-system/components/Panel';

/**
 * Start-screen option cards + flow captions, as data. Copy edits or a
 * reordered/added third option happen here, not in StartScreen.tsx.
 */
export interface StartOption {
  id: string;
  tone: Exclude<PanelTone, 'neutral'>;
  title: string;
  description: string;
  cta: string;
  // True for a mode with no real backend behind it yet — the card renders
  // but its Start button is disabled rather than silently landing on the
  // "Add/update a CI/CD platform" flow, which is a different, real mode.
  comingSoon?: boolean;
}

export const START_OPTIONS: StartOption[] = [
  {
    id: 'generate-pipeline',
    tone: 'pipeline',
    title: 'Generate a CI/CD pipeline',
    description:
      'Upload a SWArch file, pick a supported CI/CD platform, get a CI/CD pipeline back.',
    cta: 'Start',
    comingSoon: true,
  },
  {
    id: 'add-update-platform',
    tone: 'platform',
    title: 'Add or update a CI/CD platform',
    description:
      'Describe a CI/CD platform or upload its docs, MDDOAI generates or updates support for it.',
    cta: 'Start',
  },
];

export interface FlowCaptions {
  above: string;
  below: string;
}

export const FLOW_CAPTIONS: FlowCaptions = {
  above: 'gap found in a CI/CD pipeline → escalates to platform work',
  below: 'regenerate the pipeline with it ← platform fixed',
};
