import { Fragment, useLayoutEffect, useRef, useState } from 'react';
import { Icon } from './Icon';
import type { IconName } from './Icon';
import { RulesDialog } from './RulesDialog';
import { CHECK_WORDS, MOCK_WORDS, count, describe, dialogOf, learnedWords, permanentWords, typedWords } from './chartAttempt';
import type { ChartAttempt, ChartOutcome, Open } from './chartAttempt';
import type { CorrectionItem } from './corrections';
import './attempt-chart.css';

export type { ChartAttempt, ChartOutcome } from './chartAttempt';

/**
 * AttemptChart: how a stage's drafts went, as one row read left to right. A draft is a box,
 * green when its check passed and red when it failed: its tries, a small chip with the number
 * of fixes it learned from its own failed checks (a wrench), an infinity chip when a person
 * made rules permanent after it, and a tick or a cross. An arrow joins each draft to the next.
 * A rule a person typed between two drafts sits on the arrow as a person icon with its number,
 * since a person can only add one then. Rules typed since the last draft, which no draft has
 * started with, hang off a dashed arrow after it.
 * No sentence is drawn: the words are on hover, and pressing a chip opens a dialog with the
 * rules behind its number, numbered and copyable.
 */
interface AttemptChartProps {
  attempts: ChartAttempt[];
  // Rules typed since the last draft ended, which no draft has started with yet.
  waiting?: CorrectionItem[];
  selected?: number | null;
  // When given, a draft's box is a button that selects it, as the stage panels need.
  onSelect?: (index: number) => void;
  // Names the stage in the title of the rules dialog.
  label?: string;
}

// Icon sizes in px: the small one beside a number or a mark.
const SMALL_ICON = 14;
const CHIP_ICON = 13;

// The mark at the end of a draft's box. A draft nothing checked has none.
const MARKS: Partial<Record<ChartOutcome, { name: IconName; strokeWidth: number }>> = {
  passed: { name: 'Check', strokeWidth: 3 },
  failed: { name: 'X', strokeWidth: 3 },
  error: { name: 'TriangleAlert', strokeWidth: 2.5 },
};

function Mark({ outcome }: { outcome: ChartOutcome }) {
  const drawn = MARKS[outcome];
  if (!drawn) return null;
  return (
    <span className={`mdd-attempt-chart__mark mdd-attempt-chart__mark--${outcome}`} title={CHECK_WORDS[outcome]}>
      <Icon name={drawn.name} size={SMALL_ICON} strokeWidth={drawn.strokeWidth} />
    </span>
  );
}

type ChipKind = 'learned' | 'typed' | 'permanent';

const CHIPS: Record<ChipKind, { icon: IconName; words: (n: number) => string }> = {
  learned: { icon: 'Wrench', words: learnedWords },
  typed: { icon: 'User', words: typedWords },
  permanent: { icon: 'Infinity', words: permanentWords },
};

interface ChipProps {
  kind: ChipKind;
  count: number;
  onOpen: () => void;
}

// A number of rules with its icon: a wrench for the ones a failed check taught, a person for the
// ones a person typed, an infinity mark for the ones a person made permanent. Pressing it opens
// the rules.
function Chip({ kind, count: n, onOpen }: ChipProps) {
  const words = `${CHIPS[kind].words(n)}. Press to read ${n === 1 ? 'it' : 'them'}.`;
  return (
    <button type="button" className={`mdd-attempt-chart__chip is-${kind}`} title={words} aria-label={words} onClick={onOpen}>
      <Icon name={CHIPS[kind].icon} size={CHIP_ICON} strokeWidth={2.2} />
      {n}
    </button>
  );
}

interface DraftProps {
  attempt: ChartAttempt;
  isSelected: boolean;
  onSelect?: () => void;
  onOpenLearned: () => void;
  onOpenPermanent: () => void;
}

function Draft({ attempt, isSelected, onSelect, onOpenLearned, onOpenPermanent }: DraftProps) {
  const words = describe(attempt);
  const learned = attempt.learned?.length ?? 0;
  const permanent = attempt.promoted?.length ?? 0;
  const classes = ['mdd-attempt-chart__draft', `is-${attempt.outcome}`];
  if (attempt.mock) classes.push('is-mock');
  if (isSelected) classes.push('is-selected');
  return (
    <div className={classes.join(' ')} title={words} {...(onSelect ? {} : { role: 'group', 'aria-label': words })}>
      {onSelect && <button type="button" className="mdd-attempt-chart__select" aria-pressed={isSelected} aria-label={words} onClick={onSelect} />}
      {attempt.rounds !== null ? (
        <span className="mdd-attempt-chart__tries" title={count(attempt.rounds, 'try', 'tries')}>
          <Icon name="Repeat" size={SMALL_ICON} />
          {attempt.rounds}
        </span>
      ) : (
        // A stage with no loop has no tries to show, so the draft's number stands in.
        <span className="mdd-attempt-chart__tries">{attempt.number === null ? '·' : `#${attempt.number}`}</span>
      )}
      {learned > 0 && <Chip kind="learned" count={learned} onOpen={onOpenLearned} />}
      {permanent > 0 && <Chip kind="permanent" count={permanent} onOpen={onOpenPermanent} />}
      {attempt.mock && (
        <span className="mdd-attempt-chart__mock" title={MOCK_WORDS}>
          <Icon name="FlaskConical" size={SMALL_ICON} strokeWidth={2.2} />
        </span>
      )}
      <Mark outcome={attempt.outcome} />
    </div>
  );
}

// The arrow from one draft to the next, with a chip on it when a person typed rules between
// them. After the last draft it is dashed and has no head: rules typed since then wait there.
function Link({ typed, waiting = false, onOpen }: { typed: number; waiting?: boolean; onOpen: () => void }) {
  return (
    <span className={`mdd-attempt-chart__link${waiting ? ' is-waiting' : ''}`}>
      <span className="mdd-attempt-chart__stub" />
      {typed > 0 && <Chip kind="typed" count={typed} onOpen={onOpen} />}
      {typed > 0 && !waiting && <span className="mdd-attempt-chart__stub" />}
      {!waiting && <span className="mdd-attempt-chart__head" />}
    </span>
  );
}

export function AttemptChart({ attempts, waiting = [], selected = null, onSelect, label }: AttemptChartProps) {
  const [open, setOpen] = useState<Open | null>(null);
  const rowRef = useRef<HTMLDivElement>(null);
  // A row too wide for its space opens at its newest end, and goes back there when a draft or a
  // waiting rule is added or removed. Otherwise it stays where the person scrolled it.
  useLayoutEffect(() => {
    const row = rowRef.current;
    if (row) row.scrollLeft = row.scrollWidth;
  }, [attempts.length, waiting.length]);
  if (attempts.length === 0 && waiting.length === 0) return null;
  const dialog = open ? dialogOf(open, attempts, waiting, label) : null;
  return (
    <div className="mdd-attempt-chart">
      {/* The row scrolls sideways when there are many drafts, so the dialog is drawn outside it. */}
      <div className="mdd-attempt-chart__row" role="group" aria-label="Drafts" ref={rowRef}>
        {attempts.map((attempt, index) => {
          const typed = attempt.given?.length ?? 0;
          return (
            <Fragment key={index}>
              {/* The arrow into a draft: between two drafts always, and before the first only when something was typed. */}
              {(index > 0 || typed > 0) && <Link typed={typed} onOpen={() => setOpen({ kind: 'given', index })} />}
              <Draft
                attempt={attempt}
                isSelected={selected === index}
                onSelect={onSelect && (() => onSelect(index))}
                onOpenLearned={() => setOpen({ kind: 'learned', index })}
                onOpenPermanent={() => setOpen({ kind: 'permanent', index })}
              />
            </Fragment>
          );
        })}
        {waiting.length > 0 && <Link typed={waiting.length} waiting onOpen={() => setOpen({ kind: 'waiting' })} />}
      </div>
      {dialog && dialog.items.length > 0 && <RulesDialog title={dialog.title} note={dialog.note} items={dialog.items} onClose={() => setOpen(null)} />}
    </div>
  );
}
