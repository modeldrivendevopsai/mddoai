import { describe, expect, it } from 'vitest';
import type { RunEvolution } from 'orchestrator-types';
import { constraintLog } from './constraintLog';

const evolution: RunEvolution = {
  run_id: 'run-1',
  forked_from: null,
  total_rounds: 6,
  stages: [
    {
      stage: 'psm',
      rounds: 3,
      constraints: [
        { text: 'psm first', source: null, timestamp: 10 },
        { text: 'psm last', source: 'validation', timestamp: 40 },
      ],
    },
    { stage: 'atl', rounds: 3, constraints: [{ text: 'atl between', source: 'validation', timestamp: 25 }] },
  ],
};

describe('constraintLog', () => {
  it('lists every constraint of every stage oldest first, naming its stage', () => {
    expect(constraintLog(evolution).map((entry) => [entry.stage, entry.text])).toEqual([
      ['psm', 'psm first'],
      ['atl', 'atl between'],
      ['psm', 'psm last'],
    ]);
  });

  it('is empty when no stage has a constraint', () => {
    expect(constraintLog({ run_id: 'r', forked_from: null, total_rounds: 1, stages: [{ stage: 'psm', rounds: 1, constraints: [] }] })).toEqual([]);
  });

  it('keeps the given order for constraints added at the same instant', () => {
    const same: RunEvolution = {
      run_id: 'r',
      forked_from: null,
      total_rounds: 0,
      stages: [
        { stage: 'psm', rounds: 0, constraints: [{ text: 'a', source: null, timestamp: 5 }] },
        { stage: 'atl', rounds: 0, constraints: [{ text: 'b', source: null, timestamp: 5 }] },
      ],
    };

    expect(constraintLog(same).map((entry) => entry.text)).toEqual(['a', 'b']);
  });
});
