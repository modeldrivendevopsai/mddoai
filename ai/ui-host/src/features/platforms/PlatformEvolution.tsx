import { useId } from 'react';
import type { ReactNode } from 'react';
import { Button, Icon } from 'design-system';
import { STAGE_LABELS } from 'orchestrator-types';
import { PLATFORM_EVOLUTION_COPY } from '../../config/platforms.config';
import { LoadError, LoadHint } from './PlatformsFeedback';
import { StageAttempts } from './StageAttempts';
import { useRunEvolution } from './useRunEvolution';
import './platform-evolution.css';

interface PlatformEvolutionProps {
  runId: string;
  // Opens a chat by its run id, for the one this chat was forked from.
  onOpenRun: (runId: string) => void;
}

/**
 * How the chat that made a platform went: a row per stage, with a box for each draft,
 * so a reader sees a stage get faster as it is given what earlier drafts learned. It loads its own data by the run's id, so a platform's screen needs to know
 * only which run made it. A chat forked from an earlier one says so and opens that chat,
 * since the stages it reused ran there. Read only: nothing here changes the run.
 */
export function PlatformEvolution({ runId, onOpenRun }: PlatformEvolutionProps) {
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
  } else if (evolution.stages.length === 0 && !evolution.forked_from) {
    body = <LoadHint>{copy.empty}</LoadHint>;
  } else {
    const source = evolution.forked_from?.run_id ?? null;
    body = (
      <>
        {evolution.forked_from && (
          <div className="mdd-platform-evolution__fork">
            <Icon name="GitFork" size={14} />
            <span>{copy.forked(evolution.forked_from.stage ? STAGE_LABELS[evolution.forked_from.stage] : null)}</span>
            {source && (
              <Button variant="secondary" size="sm" onClick={() => onOpenRun(source)}>
                {copy.openSource}
              </Button>
            )}
          </div>
        )}
        <div className="mdd-platform-evolution__stages">
          {evolution.stages.map((stage) => (
            <StageAttempts key={stage.stage} stage={stage} />
          ))}
        </div>
      </>
    );
  }

  return (
    <section className="mdd-platform-evolution" aria-labelledby={headingId}>
      <h2 id={headingId} className="mdd-platform-evolution__title">
        {copy.title}
      </h2>
      {body}
    </section>
  );
}
