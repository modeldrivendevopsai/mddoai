import React from 'react';

/**
 * Callout — an inline note box for docs & product copy.
 * Tone sets the accent colour; keep the left rule thin (no heavy colour-block cards).
 */
export function Callout({ tone = 'info', title, children, icon = null }) {
  const map = {
    info: { fg: 'var(--info-500)', bg: '#f3f7fd', bd: '#cfe0f6' },
    tip:  { fg: 'var(--brand)', bg: 'var(--brand-faint)', bd: 'var(--border-brand)' },
    warning: { fg: '#9a6800', bg: 'var(--warning-100)', bd: '#ecd9a3' },
    danger: { fg: 'var(--danger-500)', bg: 'var(--danger-100)', bd: '#f3c4c0' },
  };
  const s = map[tone] || map.info;
  return (
    <div
      style={{
        display: 'flex',
        gap: 12,
        padding: '14px 16px',
        background: s.bg,
        border: `1px solid ${s.bd}`,
        borderRadius: 'var(--radius-md)',
        fontFamily: 'var(--font-sans)',
      }}
    >
      {icon && <div style={{ color: s.fg, flex: 'none', marginTop: 1 }}>{icon}</div>}
      <div style={{ minWidth: 0 }}>
        {title && (
          <div style={{ fontWeight: 600, fontSize: 14, color: 'var(--text-strong)', marginBottom: children ? 4 : 0 }}>
            {title}
          </div>
        )}
        {children && (
          <div style={{ fontSize: 13.5, lineHeight: 1.6, color: 'var(--text-body)' }}>{children}</div>
        )}
      </div>
    </div>
  );
}
