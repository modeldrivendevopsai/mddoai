import * as React from 'react';

export interface CardProps {
  children?: React.ReactNode;
  /** Inner padding in px. @default 20 */
  padding?: number;
  /** Lift on hover. @default false */
  hover?: boolean;
  /** Pointer cursor + hover lift (clickable card). @default false */
  interactive?: boolean;
  onClick?: (e: React.MouseEvent<HTMLDivElement>) => void;
  style?: React.CSSProperties;
}

/**
 * Standard surface container.
 * @startingPoint section="Surfaces" subtitle="Card container" viewport="700x200"
 */
export function Card(props: CardProps): JSX.Element;
