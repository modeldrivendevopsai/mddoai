import * as React from 'react';

export interface BadgeProps {
  children?: React.ReactNode;
  /** @default "neutral" */
  variant?: 'neutral' | 'brand' | 'solid' | 'outline' | 'success';
  /** @default "md" */
  size?: 'sm' | 'md';
}

/** Small monospace label for versions, counts, and categories. */
export function Badge(props: BadgeProps): JSX.Element;
