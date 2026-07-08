import React from 'react';

/**
 * CodeBlock — the dark CLI/terminal surface. Commands are first-class content in
 * the MDDOAI brand, so code always reads as a terminal even on light pages.
 *
 * `lines` may be strings or { text, kind } where kind ∈ prompt|comment|output|flag.
 * Or pass a plain `code` string. Optional title bar with a copy affordance.
 */
export function CodeBlock({ code, lines, title = null, lang = 'bash', copyable = true }) {
  const [copied, setCopied] = React.useState(false);
  const rows = lines || String(code || '').split('\n').map((text) => ({ text }));
  const plain = rows.map((r) => (typeof r === 'string' ? r : r.text)).join('\n');

  const copy = () => {
    try { navigator.clipboard.writeText(plain); } catch (e) {}
    setCopied(true);
    setTimeout(() => setCopied(false), 1400);
  };

  const colorFor = (kind) => ({
    prompt: 'var(--violet-400)',
    comment: '#6f6c86',
    output: '#8f8caa',
    flag: '#7fd6a8',
    string: '#7fd6a8',
  }[kind] || '#d6d3e8');

  return (
    <div
      style={{
        background: 'var(--surface-code)',
        border: '1px solid rgba(255,255,255,0.07)',
        borderRadius: 'var(--radius-md)',
        overflow: 'hidden',
        fontFamily: 'var(--font-mono)',
      }}
    >
      <div
        style={{
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'space-between',
          padding: '8px 12px 8px 14px',
          borderBottom: '1px solid rgba(255,255,255,0.07)',
        }}
      >
        <div style={{ display: 'flex', alignItems: 'center', gap: 8 }}>
          <span style={{ display: 'flex', gap: 6 }}>
            <span style={{ width: 10, height: 10, borderRadius: '50%', background: '#46435a' }} />
            <span style={{ width: 10, height: 10, borderRadius: '50%', background: '#46435a' }} />
            <span style={{ width: 10, height: 10, borderRadius: '50%', background: '#46435a' }} />
          </span>
          {title && <span style={{ fontSize: 12, color: '#9b98b5', marginLeft: 4 }}>{title}</span>}
          {!title && <span style={{ fontSize: 11, color: '#6f6c86', marginLeft: 4 }}>{lang}</span>}
        </div>
        {copyable && (
          <button
            onClick={copy}
            style={{
              appearance: 'none',
              background: copied ? 'rgba(127,214,168,0.14)' : 'rgba(255,255,255,0.06)',
              border: '1px solid rgba(255,255,255,0.09)',
              color: copied ? '#7fd6a8' : '#b9b6d0',
              fontFamily: 'var(--font-mono)',
              fontSize: 11,
              padding: '3px 9px',
              borderRadius: 'var(--radius-sm)',
              cursor: 'pointer',
              transition: 'all var(--duration-fast) var(--ease-out)',
            }}
          >
            {copied ? 'copied' : 'copy'}
          </button>
        )}
      </div>
      <pre style={{ margin: 0, padding: '14px 16px', overflowX: 'auto', fontSize: 13, lineHeight: 1.75 }}>
        {rows.map((r, i) => {
          const row = typeof r === 'string' ? { text: r } : r;
          return (
            <div key={i} style={{ color: colorFor(row.kind), whiteSpace: 'pre' }}>
              {row.kind === 'prompt' || row.prompt ? <span style={{ color: 'var(--violet-400)', userSelect: 'none' }}>$ </span> : null}
              {row.text}
            </div>
          );
        })}
      </pre>
    </div>
  );
}
