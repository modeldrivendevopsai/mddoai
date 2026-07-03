import * as React from 'react';

export interface StatusPillProps {
  /** Pipeline state. @default "passed" */
  status?: 'passed' | 'running' | 'pending' | 'failed' | 'info' | 'skipped';
  /** Override the default label text. */
  children?: React.ReactNode;
  /** @default "md" */
  size?: 'sm' | 'md';
}

/**
 * CI/CD pipeline status chip.
 * @startingPoint section="Feedback" subtitle="Pipeline status pills" viewport="700x120"
 */
export function StatusPill(props: StatusPillProps): JSX.Element;
