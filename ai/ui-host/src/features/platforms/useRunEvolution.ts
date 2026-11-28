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
 * How a run went: each stage's drafts with their tries and rules. `evolution` and
 * `error` are both null until the answer for exactly this run has arrived.
 */
export function useRunEvolution(runId: string) {
  const [answer, setAnswer] = useState<Answer | null>(null);
  // Counts the reloads, so a retry loads again even though nothing else changed.
  const [reloads, setReloads] = useState(0);

  // State is set only when the answer arrives, never while the request is
  // being started, so starting it from an effect is safe.
  useEffect(() => {
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
  }, [runId, reloads]);

  const retry = useCallback(() => {
    setAnswer(null);
    setReloads((count) => count + 1);
  }, []);

  const settled = answer !== null && answer.runId === runId ? answer : null;
  return { evolution: settled?.evolution ?? null, error: settled?.error ?? null, retry };
}
