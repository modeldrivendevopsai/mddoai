import type { ReactNode } from 'react';
import './status-pill.css';

/**
 * StatusPill — dot + label, colored per the brand's CI-state mapping:
 * success = passed, warning = running/in progress, danger = failed,
 * info = neutral/informational. Any screen showing pipeline/platform/test
 * status should reuse this rather than re-implementing colored dots.
 */
export type StatusPillVariant = 'success' | 'warning' | 'danger' | 'info';

interface StatusPillProps {
  variant?: StatusPillVariant;
  children: ReactNode;
}

export function StatusPill({ variant = 'info', children }: StatusPillProps) {
  return (
    <span className={`mdd-status-pill mdd-status-pill--${variant}`}>
      <span className="mdd-status-pill__dot" />
      {children}
    </span>
  );
}
