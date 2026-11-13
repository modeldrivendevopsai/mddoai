import { useCallback, useEffect, useState } from 'react';
import type { PlatformDetail } from 'orchestrator-types';
import { getPlatform } from '@/services/orchestrator.service';
import { PLATFORM_VIEW_COPY } from '../../config/platforms.config';
import { messageOf } from './messageOf';

// What one answer is about, so an answer for a platform or version the person
// has since left is not mistaken for the one being shown.
interface Answer {
  key: string;
  detail: PlatformDetail | null;
  error: string | null;
}

/**
 * One platform at the version asked for (its latest when none is), with the
 * text of its files. `loading` is true until the answer for exactly this
 * platform and version has arrived. While another version of the platform
 * already on screen loads, `detail` stays that platform, so the screen does not
 * empty and the version picker keeps the keyboard focus.
 */
export function usePlatform(platformId: string, version: string | undefined) {
  const [answer, setAnswer] = useState<Answer | null>(null);
  // Counts the retries, so a retry loads again even though nothing else changed.
  const [attempt, setAttempt] = useState(0);
  const key = `${platformId}\n${version ?? ''}`;

  // State is set only when the answer arrives, never while the request is
  // being started, so starting it from an effect is safe.
  useEffect(() => {
    let current = true;
    getPlatform(platformId, version)
      .then((detail) => {
        if (current) setAnswer({ key, detail, error: null });
      })
      .catch((err) => {
        if (current) setAnswer({ key, detail: null, error: messageOf(err, PLATFORM_VIEW_COPY.loadFailed) });
      });
    return () => {
      current = false;
    };
  }, [platformId, version, key, attempt]);

  const retry = useCallback(() => {
    setAnswer(null);
    setAttempt((count) => count + 1);
  }, []);

  const settled = answer !== null && answer.key === key ? answer : null;
  const onScreen = answer?.detail && answer.detail.id === platformId ? answer.detail : null;
  return { detail: settled ? settled.detail : onScreen, error: settled?.error ?? null, loading: settled === null, retry };
}
