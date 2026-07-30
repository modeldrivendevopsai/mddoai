import { useState } from "react"
import type { ButtonHTMLAttributes } from "react"

// Adapted from mddoai-design-system/project/components/actions/Button.jsx —
// scoped to this screen so it can use the real brand tokens (brandTokens.ts)
// regardless of the app's own shared Button component's teal theme.
interface BrandButtonProps extends ButtonHTMLAttributes<HTMLButtonElement> {
  variant?: "primary" | "secondary"
}

export function BrandButton({
  variant = "primary",
  disabled = false,
  style,
  children,
  ...rest
}: BrandButtonProps) {
  const [hover, setHover] = useState(false)
  const [active, setActive] = useState(false)

  const base: React.CSSProperties = {
    display: "inline-flex",
    alignItems: "center",
    justifyContent: "center",
    gap: 8,
    height: 40,
    padding: "0 16px",
    fontFamily: "var(--font-sans)",
    fontSize: 14,
    fontWeight: 600,
    lineHeight: 1,
    borderRadius: "var(--radius-md)",
    border: "1px solid transparent",
    cursor: disabled ? "not-allowed" : "pointer",
    opacity: disabled ? 0.5 : 1,
    transition: "background 120ms ease-out, box-shadow 120ms ease-out, transform 120ms ease-out",
    transform: active && !disabled ? "translateY(1px)" : "translateY(0)",
    whiteSpace: "nowrap",
  }

  const variants: Record<string, React.CSSProperties> = {
    primary: {
      background: hover && !disabled ? "var(--brand-strong)" : "var(--brand)",
      color: "var(--on-brand)",
      boxShadow: active ? "none" : hover ? "var(--glow-brand)" : "var(--shadow-xs)",
    },
    secondary: {
      background: hover && !disabled ? "var(--surface-sunken)" : "var(--surface-card)",
      color: "var(--text-strong)",
      borderColor: "var(--border-default)",
      boxShadow: active ? "none" : "var(--shadow-xs)",
    },
  }

  return (
    <button
      disabled={disabled}
      onMouseEnter={() => setHover(true)}
      onMouseLeave={() => {
        setHover(false)
        setActive(false)
      }}
      onMouseDown={() => setActive(true)}
      onMouseUp={() => setActive(false)}
      style={{ ...base, ...variants[variant], ...style }}
      {...rest}
    >
      {children}
    </button>
  )
}
