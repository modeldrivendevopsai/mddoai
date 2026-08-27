import type { HTMLAttributes } from 'react';
import './panel.css';

/**
 * Panel — reusable card surface with an optional context tint.
 * tone: 'neutral' | 'pipeline' | 'platform' — extend this list in
 * panel.css if a new context tint is ever needed; never inline a
 * background color on a Panel instance.
 */
export type PanelTone = 'neutral' | 'pipeline' | 'platform';

interface PanelProps extends HTMLAttributes<HTMLDivElement> {
  tone?: PanelTone;
}

export function Panel({ tone = 'neutral', className = '', children, ...rest }: PanelProps) {
  return (
    <div className={`mdd-panel mdd-panel--${tone} ${className}`} {...rest}>
      {children}
    </div>
  );
}
