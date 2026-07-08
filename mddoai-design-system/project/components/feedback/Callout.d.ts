import * as React from 'react';

export interface CalloutProps {
  /** @default "info" */
  tone?: 'info' | 'tip' | 'warning' | 'danger';
  title?: React.ReactNode;
  children?: React.ReactNode;
  /** Optional leading icon node (e.g. <Icon name="info" />). */
  icon?: React.ReactNode;
}

/** Inline note box for documentation and product copy. */
export function Callout(props: CalloutProps): JSX.Element;
