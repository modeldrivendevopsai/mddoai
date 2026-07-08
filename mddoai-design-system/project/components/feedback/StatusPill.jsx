import React from 'react';

/**
 * StatusPill — CI/CD pipeline state chip. The brand maps semantic colours to
 * pipeline states: passed / running / failed / pending / info / skipped.
 */
export function StatusPill({ status = 'passed', children, size = 'md' }) {
  const map = {
    passed:  { bg: 'var(--success-100)', fg: 'var(--success-500)', dot: 'var(--success-500)', label: 'passed' },
    running: { bg: 'var(--warning-100)', fg: '#9a6800', dot: 'var(--warning-500)', label: 'running', pulse: true },
    pending: { bg: 'var(--neutral-100)', fg: 'var(--neutral-600)', dot: 'var(--neutral-400)', label: 'pending' },
    failed:  { bg: 'var(--danger-100)', fg: 'var(--danger-500)', dot: 'var(--danger-500)', label: 'failed' },
    info:    { bg: 'var(--info-100)', fg: 'var(--info-500)', dot: 'var(--info-500)', label: 'info' },
    skipped: { bg: 'var(--neutral-100)', fg: 'var(--neutral-500)', dot: 'var(--neutral-300)', label: 'skipped' },
  };
  const s = map[status] || map.passed;
  const dims = size === 'sm'
    ? { fontSize: 11, padding: '2px 8px', dot: 6 }
    : { fontSize: 12, padding: '3px 11px', dot: 7 };

  return (
    <span
      style={{
        display: 'inline-flex',
        alignItems: 'center',
        gap: 6,
        fontFamily: 'var(--font-mono)',
        fontSize: dims.fontSize,
        fontWeight: 600,
        letterSpacing: '0.01em',
        padding: dims.padding,
        borderRadius: 'var(--radius-pill)',
        background: s.bg,
        color: s.fg,
        lineHeight: 1.3,
      }}
    >
      <span
        style={{
          width: dims.dot,
          height: dims.dot,
          borderRadius: '50%',
          background: s.dot,
          flex: 'none',
          boxShadow: s.pulse ? `0 0 0 0 ${s.dot}` : 'none',
          animation: s.pulse ? 'mddoai-pulse 1.6s var(--ease-out) infinite' : 'none',
        }}
      />
      {children || s.label}
      <style>{`@keyframes mddoai-pulse {
        0% { box-shadow: 0 0 0 0 rgba(207,138,0,0.45); }
        70% { box-shadow: 0 0 0 5px rgba(207,138,0,0); }
        100% { box-shadow: 0 0 0 0 rgba(207,138,0,0); }
      }`}</style>
    </span>
  );
}
