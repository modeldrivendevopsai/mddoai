import { useCallback, useEffect, useState } from 'react';
import { listPipelines } from '@/services/generator.service';
import type { PipelineSummary } from '@/services/generator.service';
import { toStepError } from './stepError';
import type { StepError } from './stepError';

/** The pipelines generated so far, newest first, with a retry for a failed load. */
export function usePipelines() {
  // null until the first answer, so "nothing yet" and "not loaded yet" differ.
  const [pipelines, setPipelines] = useState<PipelineSummary[] | null>(null);
  const [error, setError] = useState<StepError | null>(null);

  // State is set only when the answer arrives, never while the request is
  // being started, so starting it from an effect is safe.
  const load = useCallback(() => {
    listPipelines()
      .then((list) => {
        setPipelines(list);
        setError(null);
      })
      .catch((err) => setError(toStepError(err, 'Could not load your pipelines.')));
  }, []);

  useEffect(() => {
    load();
  }, [load]);

  const retry = useCallback(() => {
    setError(null);
    load();
  }, [load]);

  return { pipelines, error, retry };
}
