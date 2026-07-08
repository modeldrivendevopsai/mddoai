import * as React from 'react';

export interface ButtonProps {
  children?: React.ReactNode;
  /** Visual intent. @default "primary" */
  variant?: 'primary' | 'secondary' | 'ghost' | 'gradient' | 'danger';
  /** @default "md" */
  size?: 'sm' | 'md' | 'lg';
  disabled?: boolean;
  /** Element rendered before the label (e.g. a Lucide icon). */
  iconLeft?: React.ReactNode;
  /** Element rendered after the label. */
  iconRight?: React.ReactNode;
  /** Stretch to fill the container width. @default false */
  full?: boolean;
  type?: 'button' | 'submit' | 'reset';
  onClick?: (e: React.MouseEvent<HTMLButtonElement>) => void;
}

/**
 * Primary action button for MDDOAI surfaces.
 * @startingPoint section="Actions" subtitle="Button variants & sizes" viewport="700x150"
 */
export function Button(props: ButtonProps): JSX.Element;
