import type { CorrectionItem } from './corrections';

/**
 * What the attempt chart knows about a draft, and the words it says about one: the shape of a
 * draft, how a draft and each of its numbers read aloud, and what the dialog behind a chip
 * lists. Plain data and strings, so the drawing stays in the component.
 */
export type ChartOutcome = 'passed' | 'failed' | 'error' | 'none';

export interface ChartAttempt {
  // The draft's number, or null when it is not known.
  number: number | null;
  // Tries the draft took, or null for a stage with no generate-and-check loop.
  rounds: number | null;
  // Rules a person typed since the previous draft ended, which the draft started with.
  given?: CorrectionItem[];
  // Rules the draft's own failed checks taught it while it ran.
  learned?: CorrectionItem[];
  // The texts a person made permanent after the draft.
  promoted?: string[];
  outcome: ChartOutcome;
  mock?: boolean;
}

export const CHECK_WORDS: Record<ChartOutcome, string> = {
  passed: 'check passed',
  failed: 'check failed',
  error: 'stopped on an error',
  none: 'not checked',
};
export const MOCK_WORDS = 'mock: no model called';

export function count(n: number, one: string, many: string): string {
  return `${n} ${n === 1 ? one : many}`;
}

export const learnedWords = (n: number) => `${n} learned from ${n === 1 ? 'a failed check' : 'failed checks'}`;
export const typedWords = (n: number) => `${n} typed by a person`;
export const permanentWords = (n: number) => `${n} made permanent`;

/** What a draft says in words: the numbers its box draws. */
export function describe(a: ChartAttempt): string {
  const parts = [a.number === null ? 'Draft' : `Draft ${a.number}`];
  if (a.rounds !== null) parts.push(count(a.rounds, 'try', 'tries'));
  if (a.given?.length) parts.push(typedWords(a.given.length));
  if (a.learned?.length) parts.push(learnedWords(a.learned.length));
  if (a.promoted?.length) parts.push(permanentWords(a.promoted.length));
  parts.push(CHECK_WORDS[a.outcome]);
  if (a.mock) parts.push(MOCK_WORDS);
  return parts.join(' · ');
}

// What a chip opens: a draft's learned rules, the rules a person typed before a draft, the ones
// a person made permanent after it, or the ones typed since the last draft.
export type Open = { kind: 'learned' | 'given' | 'permanent'; index: number } | { kind: 'waiting' };

const draftWords = (number: number | null) => (number === null ? 'this draft' : `draft ${number}`);

// Whether each rule text the stage has had was learned from a failed check, so a rule a person
// made permanent says where it came from. A text nobody listed was typed by a person.
function learnedTexts(attempts: ChartAttempt[], waiting: CorrectionItem[]): Set<string> {
  const all = [...attempts.flatMap((a) => [...(a.given ?? []), ...(a.learned ?? [])]), ...waiting];
  return new Set(all.filter((item) => item.learned).map((item) => item.text));
}

/** What the dialog for a chip says: its title, one sentence about the rules, and the rules. */
export function dialogOf(open: Open, attempts: ChartAttempt[], waiting: CorrectionItem[], label?: string) {
  const prefix = label ? `${label} · ` : '';
  if (open.kind === 'waiting') {
    return {
      title: `${prefix}Rules typed since the last draft`,
      note: 'No draft has started with these yet.',
      items: waiting,
    };
  }
  const attempt = attempts[open.index];
  if (!attempt) return null;
  const made = new Set(attempt.promoted ?? []);
  const mark = (items: CorrectionItem[] = []) => items.map((item) => ({ ...item, permanent: made.has(item.text) }));
  if (open.kind === 'permanent') {
    const learned = learnedTexts(attempts, waiting);
    return {
      title: `${prefix}Rules made permanent after ${draftWords(attempt.number)}`,
      note: 'A person made these permanent. Every later draft starts with them.',
      items: (attempt.promoted ?? []).map((text) => ({ text, learned: learned.has(text), permanent: true })),
    };
  }
  if (open.kind === 'learned') {
    return {
      title: `${prefix}Fixes learned in ${draftWords(attempt.number)}`,
      note: 'Failed checks taught the draft these while it ran. Later drafts start with them.',
      items: mark(attempt.learned),
    };
  }
  return {
    title: `${prefix}Rules typed before ${draftWords(attempt.number)}`,
    note: 'A person typed these after the previous draft ended. The draft started with them.',
    items: mark(attempt.given),
  };
}
