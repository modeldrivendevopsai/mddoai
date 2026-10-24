import { useMemo, useRef } from 'react';

/**
 * Tells an async action whether the world moved on while it was waiting.
 * Every action that makes the answers still in flight stale calls next(); an
 * async action remembers the token it started under (next(), or token() when
 * it does not itself invalidate anything) and ignores its own answer once
 * isCurrent(token) is false.
 */
export interface StaleGuard {
  next: () => number;
  token: () => number;
  isCurrent: (token: number) => boolean;
}

export function useStaleGuard(): StaleGuard {
  const counter = useRef(0);
  return useMemo(
    () => ({
      next: () => ++counter.current,
      token: () => counter.current,
      isCurrent: (token: number) => token === counter.current,
    }),
    []
  );
}
