import React from 'react';

/**
 * MDDOAI Button — the primary action primitive.
 * Variants map to brand intent; sizes follow the 4px grid.
 */
export function Button({
  children,
  variant = 'primary',
  size = 'md',
  disabled = false,
  iconLeft = null,
  iconRight = null,
  full = false,
  type = 'button',
  onClick,
  ...rest
}) {
  const [hover, setHover] = React.useState(false);
  const [active, setActive] = React.useState(false);

  const sizes = {
    sm: { padding: '0 12px', height: 32, fontSize: 13, gap: 6, radius: 'var(--radius-sm)' },
    md: { padding: '0 16px', height: 40, fontSize: 14, gap: 8, radius: 'var(--radius-md)' },
    lg: { padding: '0 22px', height: 48, fontSize: 16, gap: 8, radius: 'var(--radius-md)' },
  };
  const s = sizes[size] || sizes.md;

  const base = {
    display: 'inline-flex',
    alignItems: 'center',
    justifyContent: 'center',
    gap: s.gap,
    height: s.height,
    padding: s.padding,
    width: full ? '100%' : 'auto',
    fontFamily: 'var(--font-sans)',
    fontSize: s.fontSize,
    fontWeight: 600,
    lineHeight: 1,
    letterSpacing: '-0.005em',
    borderRadius: s.radius,
    border: '1px solid transparent',
    cursor: disabled ? 'not-allowed' : 'pointer',
    opacity: disabled ? 0.5 : 1,
    transition: 'background var(--duration-fast) var(--ease-out), box-shadow var(--duration-fast) var(--ease-out), transform var(--duration-fast) var(--ease-out), color var(--duration-fast) var(--ease-out)',
    transform: active && !disabled ? 'translateY(1px)' : 'translateY(0)',
    whiteSpace: 'nowrap',
    userSelect: 'none',
  };

  const variants = {
    primary: {
      background: hover ? 'var(--brand-strong)' : 'var(--brand)',
      color: 'var(--on-brand)',
      boxShadow: active ? 'none' : hover ? 'var(--glow-brand)' : 'var(--shadow-xs)',
    },
    secondary: {
      background: hover ? 'var(--surface-sunken)' : 'var(--surface-card)',
      color: 'var(--text-strong)',
      borderColor: 'var(--border-default)',
      boxShadow: active ? 'none' : 'var(--shadow-xs)',
    },
    ghost: {
      background: hover ? 'var(--surface-sunken)' : 'transparent',
      color: 'var(--text-body)',
    },
    gradient: {
      background: 'var(--gradient-brand)',
      color: 'var(--on-brand)',
      boxShadow: active ? 'none' : hover ? 'var(--glow-brand)' : 'var(--shadow-sm)',
    },
    danger: {
      background: hover ? '#c93b34' : 'var(--danger-500)',
      color: '#fff',
      boxShadow: active ? 'none' : 'var(--shadow-xs)',
    },
  };

  return (
    <button
      type={type}
      disabled={disabled}
      onClick={onClick}
      onMouseEnter={() => setHover(true)}
      onMouseLeave={() => { setHover(false); setActive(false); }}
      onMouseDown={() => setActive(true)}
      onMouseUp={() => setActive(false)}
      style={{ ...base, ...(variants[variant] || variants.primary) }}
      {...rest}
    >
      {iconLeft}
      {children}
      {iconRight}
    </button>
  );
}
