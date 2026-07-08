import React from 'react';

/**
 * Badge — a small label for counts, versions, and categories.
 * Quieter than StatusPill; not tied to pipeline state.
 */
export function Badge({ children, variant = 'neutral', size = 'md' }) {
  const map = {
    neutral: { bg: 'var(--neutral-100)', fg: 'var(--neutral-600)', bd: 'var(--border-subtle)' },
    brand:   { bg: 'var(--brand-faint)', fg: 'var(--text-brand)', bd: 'var(--border-brand)' },
    solid:   { bg: 'var(--brand)', fg: 'var(--on-brand)', bd: 'transparent' },
    outline: { bg: 'transparent', fg: 'var(--text-body)', bd: 'var(--border-default)' },
    success: { bg: 'var(--success-100)', fg: 'var(--success-500)', bd: 'transparent' },
  };
  const s = map[variant] || map.neutral;
  const dims = size === 'sm' ? { fontSize: 10, padding: '1px 7px' } : { fontSize: 11, padding: '2px 9px' };
  return (
    <span
      style={{
        display: 'inline-flex',
        alignItems: 'center',
        fontFamily: 'var(--font-mono)',
        fontSize: dims.fontSize,
        fontWeight: 600,
        letterSpacing: '0.02em',
        padding: dims.padding,
        borderRadius: 'var(--radius-pill)',
        background: s.bg,
        color: s.fg,
        border: `1px solid ${s.bd}`,
        lineHeight: 1.4,
        whiteSpace: 'nowrap',
      }}
    >
      {children}
    </span>
  );
}
