import React from 'react';

/**
 * Input — single-line text field. Mono variant for command/path entry.
 */
export function Input({
  value,
  onChange,
  placeholder = '',
  label = null,
  hint = null,
  error = null,
  disabled = false,
  mono = false,
  iconLeft = null,
  type = 'text',
  full = true,
  ...rest
}) {
  const [focus, setFocus] = React.useState(false);
  const borderColor = error
    ? 'var(--danger-500)'
    : focus
      ? 'var(--brand)'
      : 'var(--border-default)';

  return (
    <label style={{ display: full ? 'block' : 'inline-block', width: full ? '100%' : 'auto', fontFamily: 'var(--font-sans)' }}>
      {label && (
        <span style={{ display: 'block', fontSize: 13, fontWeight: 600, color: 'var(--text-strong)', marginBottom: 6 }}>
          {label}
        </span>
      )}
      <span
        style={{
          display: 'flex',
          alignItems: 'center',
          gap: 8,
          height: 40,
          padding: '0 12px',
          background: disabled ? 'var(--surface-sunken)' : 'var(--surface-card)',
          border: `1px solid ${borderColor}`,
          borderRadius: 'var(--radius-md)',
          boxShadow: focus ? 'var(--ring-brand)' : 'none',
          transition: 'border-color var(--duration-fast) var(--ease-out), box-shadow var(--duration-fast) var(--ease-out)',
        }}
      >
        {iconLeft && <span style={{ color: 'var(--text-faint)', display: 'inline-flex', flex: 'none' }}>{iconLeft}</span>}
        <input
          type={type}
          value={value}
          onChange={onChange}
          placeholder={placeholder}
          disabled={disabled}
          onFocus={() => setFocus(true)}
          onBlur={() => setFocus(false)}
          style={{
            flex: 1,
            minWidth: 0,
            border: 'none',
            outline: 'none',
            background: 'transparent',
            fontFamily: mono ? 'var(--font-mono)' : 'var(--font-sans)',
            fontSize: mono ? 13 : 14,
            color: 'var(--text-strong)',
          }}
          {...rest}
        />
      </span>
      {(hint || error) && (
        <span style={{ display: 'block', fontSize: 12, marginTop: 6, color: error ? 'var(--danger-500)' : 'var(--text-muted)' }}>
          {error || hint}
        </span>
      )}
    </label>
  );
}
