import { useCallback, useEffect, useState } from 'react';
import type { PlatformSummary } from 'orchestrator-types';
import { getPlatforms } from '@/services/orchestrator.service';
import { PLATFORMS_COPY } from '../../config/platforms.config';
import { messageOf } from './messageOf';

/** The platforms, each at its latest version, with a retry for a failed load. */
export function usePlatformList() {
  // null until the first answer, so "nothing yet" and "not loaded yet" differ.
  const [platforms, setPlatforms] = useState<PlatformSummary[] | null>(null);
  const [error, setError] = useState<string | null>(null);

  // State is set only when the answer arrives, never while the request is
  // being started, so starting it from an effect is safe.
  const load = useCallback(() => {
    getPlatforms()
      .then((list) => {
        setPlatforms(list);
        setError(null);
      })
      .catch((err) => setError(messageOf(err, PLATFORMS_COPY.loadFailed)));
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
