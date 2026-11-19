import { useId } from 'react';
import type { ReactNode } from 'react';
import { Button } from 'design-system';
import { STAGE_LABELS } from 'orchestrator-types';
import type { RunEvolution } from 'orchestrator-types';
import { LEARNED_FROM_VALIDATION, PLATFORM_EVOLUTION_COPY } from '../../config/platforms.config';
import { timeAgo } from '../generate/format';
import { constraintLog } from './constraintLog';
import type { LoggedConstraint } from './constraintLog';
import { LoadError, LoadHint } from './PlatformsFeedback';
import { useRunEvolution } from './useRunEvolution';
import './platform-evolution.css';

// A constraint's time is epoch seconds. One that cannot be read gives no date, which
// the page leaves out rather than failing on.
function dateOf(seconds: number): Date | null {
  const date = new Date(seconds * 1000);
  return Number.isNaN(date.getTime()) ? null : date;
}

function RoundsTable({ evolution }: { evolution: RunEvolution }) {
  const copy = PLATFORM_EVOLUTION_COPY;
  return (
    <table className="mdd-platform-evolution__table">
      <thead>
        <tr>
          <th scope="col">{copy.columns.stage}</th>
          <th scope="col">{copy.columns.rounds}</th>
          <th scope="col">{copy.columns.corrections}</th>
        </tr>
      </thead>
      <tbody>
        {evolution.stages.map((stage) => (
          <tr key={stage.stage}>
            <th scope="row">{STAGE_LABELS[stage.stage]}</th>
            <td>{stage.rounds}</td>
            <td>{stage.constraints.length}</td>
          </tr>
        ))}
      </tbody>
      <tfoot>
        <tr>
          <th scope="row">{copy.total}</th>
          <td>{evolution.total_rounds}</td>
          <td>{evolution.stages.reduce((count, stage) => count + stage.constraints.length, 0)}</td>
        </tr>
      </tfoot>
    </table>
  );
}

function LogEntry({ entry }: { entry: LoggedConstraint }) {
  const copy = PLATFORM_EVOLUTION_COPY;
  const date = dateOf(entry.timestamp);
  return (
    <details>
      <summary>
        <span className="mdd-platform-evolution__source">
          {entry.source === LEARNED_FROM_VALIDATION ? copy.source.learned : copy.source.typed}
        </span>
        <span className="mdd-platform-evolution__stage">{STAGE_LABELS[entry.stage]}</span>
        {date && (
          <time dateTime={date.toISOString()} title={date.toLocaleString()}>
            {timeAgo(date.toISOString())}
          </time>
        )}
        <span className="mdd-platform-evolution__preview">{entry.text}</span>
      </summary>
      <pre className="mdd-platform-evolution__text">{entry.text}</pre>
    </details>
  );
}

function CorrectionsLog({ evolution }: { evolution: RunEvolution }) {
  const copy = PLATFORM_EVOLUTION_COPY;
  const log = constraintLog(evolution);
  if (log.length === 0) return null;
  return (
    <>
      <h3 className="mdd-platform-evolution__subtitle">{copy.logTitle}</h3>
      <p className="mdd-platform-evolution__hint">{copy.logHint}</p>
      <ol className="mdd-platform-evolution__log">
        {log.map((entry, index) => (
          // Two entries can share a time and a text, so the position is part of the key.
          <li key={`${entry.timestamp}-${index}`}>
            <LogEntry entry={entry} />
          </li>
        ))}
      </ol>
    </>
  );
}

/**
 * How the chat that made a platform went: the rounds each stage took, and the
 * corrections added along the way, oldest first. It loads its own data by the
 * run's id, so a platform's screen needs to know only which run made it. A chat
 * forked from an earlier one says so, since its numbers leave out the stages it
 * reused. Read only: nothing here changes the run.
 */
export function PlatformEvolution({ runId }: { runId: string }) {
  const { evolution, error, retry } = useRunEvolution(runId);
  const copy = PLATFORM_EVOLUTION_COPY;
  const headingId = useId();

  let body: ReactNode;
  if (error) {
    body = (
      <LoadError message={error}>
        <Button variant="secondary" size="sm" onClick={retry}>
          {copy.retry}
        </Button>
      </LoadError>
    );
  } else if (evolution === null) {
    body = <LoadHint busy>{copy.loading}</LoadHint>;
  } else if (evolution.stages.length === 0) {
    body = <LoadHint>{copy.empty}</LoadHint>;
  } else {
    body = (
      <>
        {evolution.forked_from && (
          <p className="mdd-platform-evolution__hint">
            {copy.forked(evolution.forked_from.stage ? STAGE_LABELS[evolution.forked_from.stage] : null)}
          </p>
        )}
        <RoundsTable evolution={evolution} />
        <CorrectionsLog evolution={evolution} />
      </>
    );
  }

  return (
    <section className="mdd-platform-evolution" aria-labelledby={headingId}>
      <h2 id={headingId} className="mdd-platform-evolution__title">
        {copy.title}
      </h2>
      <p className="mdd-platform-evolution__hint">{copy.intro}</p>
      {body}
    </section>
  );
}
