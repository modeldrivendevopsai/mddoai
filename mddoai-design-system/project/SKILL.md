---
name: mddoai-design
description: Use this skill to generate well-branded interfaces and assets for MDDOAI (Model-Driven DevOps AI), either for production or throwaway prototypes/mocks/etc. Contains essential design guidelines, colors, type, fonts, assets, and UI kit components for prototyping.
user-invocable: true
---

Read the README.md file within this skill, and explore the other available files.
If creating visual artifacts (slides, mocks, throwaway prototypes, etc), copy assets out and create static HTML files for the user to view. If working on production code, you can copy assets and read the rules here to become an expert in designing with this brand.
If the user invokes this skill without any other guidance, ask them what they want to build or design, ask some questions, and act as an expert designer who outputs HTML artifacts _or_ production code, depending on the need.

## Map
- `README.md` — brand context, content + visual foundations, iconography, full index.
- `styles.css` — single CSS entry point; `@import`s all tokens + fonts. Link this one file.
- `tokens/` — `colors.css`, `typography.css`, `spacing.css`, `elevation.css` (CSS custom properties).
- `fonts/fonts.css` — Space Grotesk (display) + IBM Plex Sans / Mono (body / code), via Google Fonts.
- `assets/logo/` — official mark (SVG + transparent/white PNGs) and favicon.
- `foundations/` — specimen cards (colour, type, spacing, brand).
- `components/` — reusable React primitives (Button, Icon, Badge, StatusPill, Callout, Input, Card, Tabs, CodeBlock). Each has `.jsx`, `.d.ts`, `.prompt.md`.
- `ui_kits/marketing/` and `ui_kits/docs/` — full interactive product surfaces composing the primitives.

## Using components
The compiler bundles components into `_ds_bundle.js` under `window.MDDOAIDesignSystem_0a843c`.
In an HTML file: link `styles.css`, load React + Babel + Lucide + `_ds_bundle.js`, then
`const { Button, CodeBlock } = window.MDDOAIDesignSystem_0a843c;`. See any `ui_kits/*/index.html`
for the exact script wiring. Icons are Lucide (CDN) via the `Icon` component.

## Quick brand rules
- One brand hue: violet `#684aeb` → `#a45eed`. Cool slate neutrals. Use the gradient sparingly.
- Type: Space Grotesk display, IBM Plex Sans body, IBM Plex Mono for all commands/paths.
- Voice: second-person imperative, sentence case, **MDDOAI** always all-caps, commands in mono. No emoji.
- Commands are first-class content — show them in the dark `CodeBlock` terminal surface.
- Semantic colours map to CI states: green=passed, amber=running, red=failed, blue=info.
