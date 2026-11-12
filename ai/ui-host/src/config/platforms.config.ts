/**
 * The copy of the platforms screens, as data. It is the platforms' counterpart
 * of the copy of the pipelines screens.
 */
export const PLATFORMS_COPY = {
  title: 'CI/CD Platforms',
  backHint: 'Back to the start screen',
  newPlatform: '+ New platform',
  listTitle: 'Supported platforms',
  // A row is pressed anywhere to open it; this is what a screen reader hears for its name button.
  openPlatform: (name: string) => `Open ${name}`,
  columns: {
    platform: 'Platform',
    version: 'Version',
    source: 'Source',
    outputFile: 'Output file',
  },
  source: {
    included: 'Included with MDDOAI',
    published: (ago: string) => `Published ${ago}`,
    // The link to the chat that made a platform. Many rows carry it, so each is named for its platform.
    openChat: 'Open chat',
    openChatOf: (name: string) => `Open the chat that made ${name}`,
  },
  loading: 'Loading the platforms…',
  loadFailed: 'Could not load the platforms.',
  empty: 'No platform yet. Choose "+ New platform" to add one.',
  retry: 'Try again',
} as const;

/** The screen of one platform: what it is made of, and where it came from. */
export const PLATFORM_VIEW_COPY = {
  // The header names the platform once it has loaded; this stands in until then.
  title: 'Platform',
  backHint: 'Back to the platforms',
  loading: 'Loading the platform…',
  loadFailed: 'Could not load the platform.',
  retry: 'Try again',
  version: 'Version',
  latest: (version: string) => `${version} (latest)`,
  outputFile: 'Writes',
  included: 'Included with MDDOAI. No chat made it.',
  published: (ago: string) => `Published ${ago}.`,
  openRun: 'Open the chat that made it',
  showLatest: 'Show the latest version',
  // One tab per file a platform is made of. The hint says, in plain words, what
  // the file does, since the names alone mean little to someone new to model-driven work.
  files: [
    {
      id: 'metamodel',
      label: 'Metamodel',
      fileName: 'metamodel.ecore',
      hint: 'The concepts of the platform and how they connect, as an Ecore metamodel.',
    },
    {
      id: 'transformation',
      label: 'Transformation',
      fileName: 'transformation.atl',
      hint: 'ATL rules that turn the platform-independent model into a model of this platform.',
    },
    {
      id: 'template',
      label: 'Template',
      fileName: 'template.mtl',
      hint: 'An Acceleo template that writes the pipeline file from that model.',
    },
  ],
} as const;
