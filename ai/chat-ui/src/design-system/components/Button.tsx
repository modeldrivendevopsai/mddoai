import { useState } from 'react';
import type { ButtonHTMLAttributes, CSSProperties, ReactNode } from 'react';
import { Icon } from './Icon';
import type { IconName } from './Icon';

export type ButtonVariant = 'primary' | 'secondary' | 'ghost' | 'gradient' | 'danger';
type ButtonSize = 'sm' | 'md' | 'lg';

// Verbatim port of mddoai-design-system/project/components/actions/Button.jsx
// (same size/variant maps, same hover/active/transition behavior) — the
// design system is Claude-Design HTML/JSX prototypes, not an importable
// package, so this is copied in rather than depended on. `icon` is this
// app's own small addition (not in the source component) for icon+label
// buttons like the sidebar's actions, rendered via the shared Icon wrapper.
const SIZES: Record<ButtonSize, { padding: string; height: number; fontSize: number; gap: number; radius: string }> = {
  sm: { padding: '0 12px', height: 32, fontSize: 13, gap: 6, radius: 'var(--radius-sm)' },
  md: { padding: '0 16px', height: 40, fontSize: 14, gap: 8, radius: 'var(--radius-md)' },
  lg: { padding: '0 22px', height: 48, fontSize: 16, gap: 8, radius: 'var(--radius-md)' },
};

interface ButtonProps extends Omit<ButtonHTMLAttributes<HTMLButtonElement>, 'type'> {
  children?: ReactNode;
  variant?: ButtonVariant;
  size?: ButtonSize;
  full?: boolean;
  icon?: IconName;
  type?: 'button' | 'submit';
}

export function Button({
  children,
  variant = 'primary',
  size = 'md',
  disabled = false,
  full = false,
  icon,
  type = 'button',
  onClick,
  style,
  ...rest
}: ButtonProps) {
  const [hover, setHover] = useState(false);
  const [active, setActive] = useState(false);
  const s = SIZES[size];

  const variants: Record<ButtonVariant, CSSProperties> = {
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
      // #c93b34 (hover only) is the source's own literal value, not a
      // token substitution — mddoai-design-system/project/components/
      // actions/Button.jsx hardcodes this exact hex too, no darker-danger
      // token exists to reference. color is --on-brand (not a literal),
      // it duplicates #fff exactly and is already the token every other
      // colored variant's text uses.
      background: hover ? '#c93b34' : 'var(--danger-500)',
      color: 'var(--on-brand)',
      boxShadow: active ? 'none' : 'var(--shadow-xs)',
    },
  };

  return (
    <button
      type={type}
      disabled={disabled}
      onClick={onClick}
      onMouseEnter={() => setHover(true)}
      onMouseLeave={() => {
        setHover(false);
        setActive(false);
      }}
      onMouseDown={() => setActive(true)}
      onMouseUp={() => setActive(false)}
      style={{
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
        transition:
          'background var(--duration-fast) var(--ease-out), box-shadow var(--duration-fast) var(--ease-out), transform var(--duration-fast) var(--ease-out), color var(--duration-fast) var(--ease-out)',
        transform: active && !disabled ? 'translateY(1px)' : 'translateY(0)',
        whiteSpace: 'nowrap',
        userSelect: 'none',
        ...variants[variant],
        ...style,
      }}
      {...rest}
    >
      {/* A long full-width label (e.g. the sidebar's "Copy an existing
          platform") has nowhere to go: whiteSpace:nowrap above means the
          text can't wrap, so without flexShrink:0 here the flex layout
          shrinks the icon instead of the text to make room — visibly
          smaller icons on longer-label buttons. The label gets its own
          minWidth:0 span so it truncates with an ellipsis instead, the icon
          never does. */}
      {icon && <Icon name={icon} size={size === 'sm' ? 13 : 14} style={{ flexShrink: 0 }} />}
      {/* lineHeight: 'normal' here, not the button's own lineHeight: 1 —
          overflow:hidden (needed for the ellipsis) clips to the line box,
          and a lineHeight of 1 leaves no room for descenders (the tails on
          "p"/"y"), cutting them off. The button's overall height is still
          the fixed s.height above; this only affects the text's own line
          box, centered within it via alignItems:center. */}
      <span style={{ overflow: 'hidden', textOverflow: 'ellipsis', minWidth: 0, lineHeight: 'normal' }}>
        {children}
      </span>
    </button>
  );
}
