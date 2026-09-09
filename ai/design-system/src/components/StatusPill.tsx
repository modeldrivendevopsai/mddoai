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
  // Native tooltip passthrough - for a pill whose short label doesn't fit
  // the full real detail (e.g. a real file path), not specific to any one
  // consumer.
  title?: string;
}

export function StatusPill({ variant = 'info', children, title }: StatusPillProps) {
  return (
    <span className={`mdd-status-pill mdd-status-pill--${variant}`} title={title}>
      <span className="mdd-status-pill__dot" />
      {children}
    </span>
  );
}
