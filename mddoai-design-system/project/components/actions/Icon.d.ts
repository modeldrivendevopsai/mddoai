import * as React from 'react';

export interface IconProps {
  /** Lucide icon name in kebab-case, e.g. "arrow-right", "terminal", "git-branch". */
  name: string;
  /** Square size in px. @default 20 */
  size?: number;
  /** @default 1.9 */
  strokeWidth?: number;
  /** Overrides currentColor. */
  color?: string;
  style?: React.CSSProperties;
}

/** Lucide line-icon wrapper — the MDDOAI icon system. */
export function Icon(props: IconProps): JSX.Element;
