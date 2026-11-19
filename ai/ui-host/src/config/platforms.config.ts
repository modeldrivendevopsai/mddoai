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
    rounds: 'Rounds',
  },
  // What a round is, on the Rounds header: the column is a bare number.
  roundsHint:
    'Rounds the chat that made the platform spent in all. A round is one try at a stage. Fewer rounds means the stage got it right sooner.',
  // A platform with no chat behind it has no rounds to count.
  noRounds: '–',
  source: {
    // A platform with no publish record has no date to give: it came with the stack.
    none: '–',
    published: (ago: string) => `Published ${ago}`,
    // The button to the chat that made a platform. Many rows carry it, so each is named for its
    // platform, and the name starts with the words the button shows, which is what voice control says.
    openChat: 'Open chat',
    openChatOf: (name: string) => `Open chat for ${name}`,
    // The reason, on the greyed-out button of a platform no chat made.
    noChat: {
      shipped: 'No chat made it',
      unrecorded: 'Published without a recorded chat',
    },
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
  // The reason the list gives for a platform no chat made, as a sentence.
  noChat: `${PLATFORMS_COPY.source.noChat.shipped}.`,
  published: (ago: string) => `Published ${ago}.`,
  openChat: 'Open the chat that made it',
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

/** How the chat that made a platform went: the rounds each stage took and the corrections along the way. */
export const PLATFORM_EVOLUTION_COPY = {
  title: 'Rounds and corrections',
  intro:
    'A round is one try at a stage: the answer is checked and, if it fails, tried again with what was wrong. Fewer rounds means the stage got it right sooner.',
  loading: 'Loading the rounds and corrections…',
  loadFailed: 'Could not load the rounds and corrections.',
  // A chat forked from an earlier one reuses that chat's approved stages, so their rounds are not in its numbers.
  forked: (stage: string | null) =>
    stage
      ? `This chat was forked at ${stage}. The stages before it ran in an earlier chat, so their rounds and corrections are not counted here.`
      : 'This chat was forked from an earlier one. The stages it reused ran in that chat, so their rounds and corrections are not counted here.',
  retry: 'Try again',
  empty: 'The chat that made it ran no stage with rounds and recorded no corrections.',
  columns: { stage: 'Stage', rounds: 'Rounds', corrections: 'Corrections' },
  total: 'In all',
  logTitle: 'Corrections along the way',
  logHint: 'Oldest first. Each is added to the prompt of its stage, so the next round does not repeat the mistake.',
  // A fix the validator's errors produced, against one a person typed.
  source: { learned: 'Fix learned', typed: 'Correction' },
} as const;

// The `source` the runner gives a constraint made from the validator's errors. A
// constraint a person typed has none.
export const LEARNED_FROM_VALIDATION = 'validation';
