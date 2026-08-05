import { useState } from "react"

// Adapted from mddoai-design-system/project/components/forms/Input.jsx —
// plain <input>/<select>/<textarea> elements styled against the shared
// design-system tokens for focus rings, borders, etc.

function fieldChromeStyle(focus: boolean, disabled?: boolean): React.CSSProperties {
  return {
    display: "flex",
    alignItems: "center",
    height: 40,
    padding: "0 12px",
    background: disabled ? "var(--surface-sunken)" : "var(--surface-card)",
    border: `1px solid ${focus ? "var(--brand)" : "var(--border-default)"}`,
    borderRadius: "var(--radius-md)",
    boxShadow: focus ? "var(--ring-brand)" : "none",
    transition: "border-color 120ms ease-out, box-shadow 120ms ease-out",
  }
}

const labelStyle: React.CSSProperties = {
  display: "block",
  fontSize: 13,
  fontWeight: 600,
  color: "var(--text-strong)",
  marginBottom: 6,
  fontFamily: "var(--font-sans)",
}

const inputTextStyle: React.CSSProperties = {
  flex: 1,
  minWidth: 0,
  border: "none",
  outline: "none",
  background: "transparent",
  fontFamily: "var(--font-sans)",
  fontSize: 14,
  color: "var(--text-strong)",
}

export function TextField({
  label,
  value,
  placeholder,
  onChange,
  disabled,
}: {
  label: string
  value: string
  placeholder?: string
  onChange: (value: string) => void
  disabled?: boolean
}) {
  const [focus, setFocus] = useState(false)
  return (
    <label style={{ display: "block" }}>
      <span style={labelStyle}>{label}</span>
      <span style={fieldChromeStyle(focus, disabled)}>
        <input
          type="text"
          value={value}
          placeholder={placeholder}
          disabled={disabled}
          onChange={(e) => onChange(e.target.value)}
          onFocus={() => setFocus(true)}
          onBlur={() => setFocus(false)}
          style={inputTextStyle}
        />
      </span>
    </label>
  )
}

export function NumberField({
  label,
  value,
  placeholder,
  onChange,
  disabled,
}: {
  label: string
  value: string
  placeholder?: string
  onChange: (value: string) => void
  disabled?: boolean
}) {
  const [focus, setFocus] = useState(false)
  return (
    <label style={{ display: "block" }}>
      <span style={labelStyle}>{label}</span>
      <span style={fieldChromeStyle(focus, disabled)}>
        <input
          type="number"
          min={1}
          value={value}
          placeholder={placeholder}
          disabled={disabled}
          onChange={(e) => onChange(e.target.value)}
          onFocus={() => setFocus(true)}
          onBlur={() => setFocus(false)}
          style={inputTextStyle}
        />
      </span>
    </label>
  )
}

export function TextAreaField({
  label,
  value,
  placeholder,
  onChange,
  disabled,
  rows = 2,
}: {
  label: string
  value: string
  placeholder?: string
  onChange: (value: string) => void
  disabled?: boolean
  rows?: number
}) {
  const [focus, setFocus] = useState(false)
  return (
    <label style={{ display: "block" }}>
      <span style={labelStyle}>{label}</span>
      <span style={{ ...fieldChromeStyle(focus, disabled), height: "auto", padding: "8px 12px" }}>
        <textarea
          value={value}
          placeholder={placeholder}
          disabled={disabled}
          rows={rows}
          onChange={(e) => onChange(e.target.value)}
          onFocus={() => setFocus(true)}
          onBlur={() => setFocus(false)}
          style={{ ...inputTextStyle, resize: "vertical" }}
        />
      </span>
    </label>
  )
}

export function SelectField({
  value,
  onChange,
  disabled,
  children,
  ariaLabel,
}: {
  value: string
  onChange: (value: string) => void
  disabled?: boolean
  children: React.ReactNode
  ariaLabel?: string
}) {
  const [focus, setFocus] = useState(false)
  return (
    <span style={{ ...fieldChromeStyle(focus, disabled), height: 32, padding: "0 8px" }}>
      <select
        value={value}
        disabled={disabled}
        aria-label={ariaLabel}
        onChange={(e) => onChange(e.target.value)}
        onFocus={() => setFocus(true)}
        onBlur={() => setFocus(false)}
        style={{ ...inputTextStyle, fontSize: 12, cursor: disabled ? "not-allowed" : "pointer" }}
      >
        {children}
      </select>
    </span>
  )
}
