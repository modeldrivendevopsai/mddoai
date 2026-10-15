/**
 * "Generate a CI/CD pipeline" screens' defaults, and the copy that follows the
 * approved wireframes, as data. A label that belongs to one component alone
 * (a button's name, a status line) is written beside that component.
 */

// What the upload button's file picker offers; the backend decides what is
// actually valid.
export const MODEL_FILE_ACCEPT = '.swarch';

// Where generated pipeline files are saved, relative to the workspace folder,
// until the person changes it.
export const DEFAULT_SAVE_TO = './ci-output/';

export const GENERATE_COPY = {
  pipelines: {
    title: 'CI/CD Pipelines',
    back: 'Start',
    newPipeline: '+ New pipeline',
    listTitle: 'Your pipelines',
    columns: {
      input: 'SWArch input',
      platforms: 'Platform(s)',
      outputs: 'Output pipeline(s)',
      lastGenerated: 'Last generated',
    },
    open: 'open',
    loading: 'Loading your pipelines…',
    empty: 'Nothing generated yet. Choose "+ New pipeline" to make your first one.',
    retry: 'Try again',
  },
  input: {
    title: 'Generate CI/CD Pipeline',
    inputPath: 'Input path for SWArch file',
    inputPlaceholder: '/path/to/your-architecture.swarch',
    pathTitle: 'A path inside your workspace folder',
    savePlaceholder: 'Leave empty to not save a file',
    fileLoaded: '✓ File loaded',
    browse: 'Browse',
    upload: 'Upload',
    platforms: 'Platform(s)',
    platformsHint: ': select one or more',
    saveTo: 'Save output to',
    generate: 'Generate',
    generating: 'Generating…',
    blocked: {
      noFile: 'Load an architecture file first.',
      invalid: 'Fix the problems found in your file first.',
      noPlatform: 'Select at least one platform.',
    },
  },
  result: {
    placeholder: 'Result will appear here once generated.',
    running: (platformNames: string[]) => `Running the existing ${platformNames.join(' and ')} transformation chain…`,
  },
} as const;
