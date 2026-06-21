import React from 'react';

/**
 * Card — the standard surface container. White on a faint slate page,
 * 1px hairline border, soft cool shadow. No coloured left-border accents.
 */
export function Card({
  children,
  padding = 20,
  hover = false,
  interactive = false,
  onClick,
  style = {},
  ...rest
}) {
  const [h, setH] = React.useState(false);
  const lift = (hover || interactive) && h;
  return (
    <div
      onClick={onClick}
      onMouseEnter={() => setH(true)}
      onMouseLeave={() => setH(false)}
      style={{
        background: 'var(--surface-card)',
        border: '1px solid var(--border-subtle)',
        borderRadius: 'var(--radius-lg)',
        boxShadow: lift ? 'var(--shadow-md)' : 'var(--shadow-sm)',
        padding,
        cursor: interactive ? 'pointer' : 'default',
        transform: lift ? 'translateY(-2px)' : 'translateY(0)',
        transition: 'box-shadow var(--duration-base) var(--ease-out), transform var(--duration-base) var(--ease-out), border-color var(--duration-base) var(--ease-out)',
        borderColor: lift ? 'var(--border-default)' : 'var(--border-subtle)',
        ...style,
      }}
      {...rest}
    >
      {children}
    </div>
  );
}
