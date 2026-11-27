import { useId } from 'react';
import { AttemptChart } from 'design-system';
import type { ChartAttempt, ChartOutcome } from 'design-system';
import { STAGE_LABELS } from 'orchestrator-types';
import type { CallOutcome, StageCall, StageEvolution } from 'orchestrator-types';

const OUTCOME_CHART: Record<CallOutcome, ChartOutcome> = {
  valid: 'passed',
  invalid: 'failed',
  failed: 'error',
  done: 'none',
};

// A call that raised is an error, unless the stage recorded an attempt for it: that is how a result
// that failed its check ends for the stages that raise on one, and it is a failed check.
function outcomeOf(call: StageCall): ChartOutcome {
  return call.outcome === 'failed' && call.attempt_n !== null ? 'failed' : OUTCOME_CHART[call.outcome];
}

function chartOf(calls: StageCall[]): ChartAttempt[] {
  return calls.map((call) => ({
    number: call.attempt_n,
    rounds: call.rounds,
    given: call.given,
    learned: call.learned,
    promoted: call.promoted,
    outcome: outcomeOf(call),
    mock: call.mock,
  }));
}

/**
 * One stage of a run: its drafts as a row of boxes, oldest on the left, so a reader sees
 * the tries fall (or not) as the stage is given more. A chip on a box or an arrow opens the
 * rules behind its number. Rules added since the last draft, which no draft has started
 * with, hang off the end of the row.
 */
export function StageAttempts({ stage }: { stage: StageEvolution }) {
  const headingId = useId();
  const label = STAGE_LABELS[stage.stage];
  return (
    <section className="mdd-platform-evolution__stage" aria-labelledby={headingId}>
      <h3 id={headingId} className="mdd-platform-evolution__stage-name">
        {label}
      </h3>
      <AttemptChart attempts={chartOf(stage.calls)} waiting={stage.waiting} label={label} />
    </section>
  );
}
