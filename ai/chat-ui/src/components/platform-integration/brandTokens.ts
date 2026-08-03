import type { CSSProperties } from "react"

// Mirrors mddoai-design-system/project/tokens/{colors,typography,spacing,elevation}.css.
// Scoped to this screen only (not touching the app's global index.css, which currently
// carries an unrelated dark/teal theme) — spread onto a root wrapper's style as CSS
// custom properties so every child can reference var(--token-name) exactly like the
// design system's own components do.
export const brandTokenStyle = {
  // Brand
  "--brand": "#684aeb",
  "--brand-strong": "#5736d6",
  "--brand-subtle": "#e7e1fd",
  "--brand-faint": "#f3f0fe",
  "--on-brand": "#ffffff",

  // Surfaces
  "--surface-page": "#f8f8fb",
  "--surface-card": "#ffffff",
  "--surface-sunken": "#f0f0f5",

  // Text
  "--text-strong": "#181820",
  "--text-body": "#3d3d4c",
  "--text-muted": "#74748d",
  "--text-faint": "#a1a1b8",

  // Borders
  "--border-subtle": "#e4e4ee",
  "--border-default": "#cdcddb",
  "--focus-ring": "#8b70f1",

  // Semantic (CI pipeline states)
  "--success-500": "#15a36a",
  "--success-100": "#e3f6ec",
  "--warning-500": "#cf8a00",
  "--warning-100": "#fbf0d6",
  "--danger-500": "#e0443c",
  "--danger-100": "#fbe5e3",
  "--info-500": "#3a7bd5",
  "--info-100": "#e4eefb",

  // Typography
  "--font-display": "'Space Grotesk Variable', 'IBM Plex Sans Variable', system-ui, sans-serif",
  "--font-sans": "'IBM Plex Sans Variable', system-ui, -apple-system, sans-serif",
  "--font-mono": "'IBM Plex Mono', ui-monospace, 'SF Mono', Menlo, monospace",

  // Spacing / radii
  "--radius-sm": "6px",
  "--radius-md": "10px",
  "--radius-lg": "14px",
  "--radius-pill": "999px",

  // Elevation
  "--shadow-xs": "0 1px 2px rgba(24, 24, 40, 0.06)",
  "--shadow-sm": "0 1px 3px rgba(24, 24, 40, 0.08), 0 1px 2px rgba(24, 24, 40, 0.04)",
  "--shadow-md": "0 4px 12px rgba(24, 24, 40, 0.08), 0 2px 4px rgba(24, 24, 40, 0.05)",
  "--glow-brand": "0 6px 20px rgba(104, 74, 235, 0.35)",
  "--ring-brand": "0 0 0 3px rgba(104, 74, 235, 0.28)",
} as CSSProperties
