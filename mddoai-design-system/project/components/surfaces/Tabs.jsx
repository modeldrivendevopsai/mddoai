import React from 'react';

/**
 * Tabs — horizontal segmented navigation with an animated underline.
 */
export function Tabs({ tabs = [], value, defaultValue, onChange }) {
  const [internal, setInternal] = React.useState(defaultValue ?? (tabs[0] && tabs[0].id));
  const active = value !== undefined ? value : internal;
  const select = (id) => {
    if (value === undefined) setInternal(id);
    onChange && onChange(id);
  };
  return (
    <div
      style={{
        display: 'flex',
        gap: 4,
        borderBottom: '1px solid var(--border-subtle)',
        fontFamily: 'var(--font-sans)',
      }}
    >
      {tabs.map((t) => {
        const on = t.id === active;
        return (
          <button
            key={t.id}
            onClick={() => select(t.id)}
            style={{
              position: 'relative',
              appearance: 'none',
              background: 'transparent',
              border: 'none',
              cursor: 'pointer',
              padding: '10px 14px',
              marginBottom: -1,
              fontFamily: 'inherit',
              fontSize: 14,
              fontWeight: on ? 600 : 500,
              color: on ? 'var(--text-strong)' : 'var(--text-muted)',
              borderBottom: `2px solid ${on ? 'var(--brand)' : 'transparent'}`,
              transition: 'color var(--duration-fast) var(--ease-out), border-color var(--duration-fast) var(--ease-out)',
              display: 'inline-flex',
              alignItems: 'center',
              gap: 7,
            }}
          >
            {t.icon}
            {t.label}
            {t.count !== undefined && (
              <span style={{ fontFamily: 'var(--font-mono)', fontSize: 11, color: 'var(--text-faint)' }}>{t.count}</span>
            )}
          </button>
        );
      })}
    </div>
  );
}
