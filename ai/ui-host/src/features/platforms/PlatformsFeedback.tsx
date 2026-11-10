import type { ReactNode } from 'react';
import './platforms-feedback.css';

/** What a platforms screen says when a load failed: the reason, then what can be done about it. */
export function LoadError({ message, children }: { message: string; children: ReactNode }) {
  return (
    <div className="mdd-platforms-feedback__message" role="alert">
      <p className="mdd-platforms-feedback__error">{message}</p>
      {children}
    </div>
  );
}

/** A line of quiet text: still loading (`busy`), or nothing to show yet. */
export function LoadHint({ busy = false, children }: { busy?: boolean; children: ReactNode }) {
  return (
    <p className="mdd-platforms-feedback__hint" role={busy ? 'status' : undefined}>
      {children}
    </p>
  );
}
