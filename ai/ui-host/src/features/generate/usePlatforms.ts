import { useCallback, useEffect, useState } from 'react';
import { getPlatforms } from '@/services/generator.service';
import type { GeneratorPlatform } from '@/services/generator.service';
import { toStepError } from './stepError';
import type { StepError } from './stepError';

/**
 * The CI/CD platforms the backend can generate for. A failed load keeps its
 * reason and can be retried, so a backend that was still starting up is not a
 * dead end.
 */
export function usePlatforms() {
  const [platforms, setPlatforms] = useState<GeneratorPlatform[]>([]);
  const [error, setError] = useState<StepError | null>(null);

  // State is set only when the answer arrives, never while the request is
  // being started, so starting it from an effect is safe.
  const load = useCallback(() => {
    getPlatforms()
      .then((list) => {
        setPlatforms(list);
        setError(null);
      })
      .catch((err) => setError(toStepError(err, 'Could not load the platform list.')));
  }, []);

  useEffect(() => {
    load();
  }, [load]);

  const retry = useCallback(() => {
    setError(null);
    load();
  }, [load]);

  return { platforms, error, retry };
}
