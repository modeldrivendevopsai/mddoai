import { useCallback, useEffect, useState } from 'react';
import type { RunEvolution } from 'orchestrator-types';
import { getRunEvolution } from '@/services/orchestrator.service';
import { PLATFORM_EVOLUTION_COPY } from '../../config/platforms.config';
import { messageOf } from './messageOf';

// What one answer is about, so an answer for a run the screen has since left is
// not mistaken for the one it shows now.
interface Answer {
  runId: string;
  evolution: RunEvolution | null;
  error: string | null;
}

/**
 * What a run took: the rounds each stage spent and the corrections added. A
 * platform that no run made has no run id, so nothing is asked and nothing is
 * loading. `loading` is true until the answer for exactly this run has arrived.
 */
export function useRunEvolution(runId: string | null) {
  const [answer, setAnswer] = useState<Answer | null>(null);
  // Counts the retries, so a retry loads again even though nothing else changed.
  const [attempt, setAttempt] = useState(0);

  // State is set only when the answer arrives, never while the request is
  // being started, so starting it from an effect is safe.
  useEffect(() => {
    if (runId === null) return;
    let current = true;
    getRunEvolution(runId)
      .then((evolution) => {
        if (current) setAnswer({ runId, evolution, error: null });
      })
      .catch((err) => {
        if (current) setAnswer({ runId, evolution: null, error: messageOf(err, PLATFORM_EVOLUTION_COPY.loadFailed) });
      });
    return () => {
      current = false;
    };
  }, [runId, attempt]);

  const retry = useCallback(() => {
    setAnswer(null);
    setAttempt((count) => count + 1);
  }, []);

  const settled = answer !== null && answer.runId === runId ? answer : null;
  return { evolution: settled?.evolution ?? null, error: settled?.error ?? null, loading: runId !== null && settled === null, retry };
}
