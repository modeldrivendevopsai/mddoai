import type { ConstraintLogEntry, RunEvolution, StageId } from 'orchestrator-types';

export interface LoggedConstraint extends ConstraintLogEntry {
  stage: StageId;
}

/**
 * Every constraint a run added, across its stages, oldest first: the order
 * things were learned in. Constraints added in the same instant keep the order
 * their stage and the run gave them.
 */
export function constraintLog(evolution: RunEvolution): LoggedConstraint[] {
  return evolution.stages
    .flatMap((stage) => stage.constraints.map((constraint) => ({ ...constraint, stage: stage.stage })))
    .sort((a, b) => a.timestamp - b.timestamp);
}
