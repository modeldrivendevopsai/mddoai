/**
 * The copy of the "Generate a CI/CD pipeline" screens, which follows the
 * approved wireframes, as data. A label that belongs to one component alone
 * (a button's name, a status line) is written beside that component.
 */

// What the upload button's file picker offers; the backend decides what is
// actually valid.
export const MODEL_FILE_ACCEPT = '.swarch';

export const GENERATE_COPY = {
  pipelines: {
    title: 'CI/CD Pipelines',
    backHint: 'Back to the start screen',
    newPipeline: '+ New pipeline',
    listTitle: 'Your pipelines',
    // A row is pressed anywhere to open it; this is what a screen reader hears for its name button.
    openPipeline: (name: string) => `Open ${name}`,
    columns: {
      input: 'SWArch input',
      platforms: 'Platform(s)',
      outputs: 'Output pipeline(s)',
      lastGenerated: 'Last generated',
    },
    loading: 'Loading your pipelines…',
    empty: 'Nothing generated yet. Choose "+ New pipeline" to make your first one.',
    retry: 'Try again',
  },
  input: {
    title: 'Generate CI/CD Pipeline',
    backHint: 'Back to your pipelines',
    inputPath: 'Input path for SWArch file',
    inputPlaceholder: '/path/to/your-architecture.swarch',
    pathTitle: 'A path inside your workspace folder',
    fileLoaded: '✓ File loaded',
    browse: 'Browse',
    upload: 'Upload',
    platforms: 'Platform(s)',
    platformsHint: ': select one or more',
    generate: 'Generate',
    generating: 'Generating…',
    blocked: {
      noFile: 'Load an architecture file first.',
      invalid: 'Fix the problems found in your file first.',
      noPlatform: 'Select at least one platform.',
    },
  },
  validation: {
    checking: 'Checking the pipeline…',
    passed: (total: number) => (total === 1 ? '1 check passed' : `All ${total} checks passed`),
    // Nothing failed, but some checks could not be made: not a pass.
    partial: (passed: number, skipped: number) => `${passed} ${passed === 1 ? 'check' : 'checks'} passed, ${skipped} skipped`,
    // Nothing failed, but not one check could be made.
    notChecked: 'Could not be checked',
    failed: (failed: number, total: number) => `${failed} of ${total} ${total === 1 ? 'check' : 'checks'} failed`,
    unavailable: 'Could not check the pipeline',
    unavailableDetail: 'The pipeline could not be checked.',
    retry: 'Try again',
  },
  result: {
    placeholder: 'Result will appear here once generated.',
    running: (platformNames: string[]) => `Running the existing ${platformNames.join(' and ')} transformation chain…`,
  },
} as const;
