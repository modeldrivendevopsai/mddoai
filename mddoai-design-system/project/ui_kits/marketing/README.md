# UI Kit — Marketing Site

The MDDOAI product landing page: a faithful construction of the brand's marketing
surface, built from the repo's README copy, command set, and the official mark.
(No shipped marketing site was reachable at build time — see the root README
*Sources* note. Re-derive against `mddoai-web` if it becomes available.)

## Screens / sections
- **Header** (`Header.jsx`) — sticky, transparent → blurred-on-scroll, logo + nav + CTA.
- **Hero** (`Hero.jsx`) — headline with gradient accent, subhead, dual CTA, live
  terminal demo + generated-file card.
- **Pipeline / Transformations / Features** (`Sections.jsx`) — how-it-works 3-step,
  the three transformation-type cards, and the feature grid.
- **Quickstart** (`Quickstart.jsx`) — interactive Docker / source / CI tabs over a
  `CodeBlock`, with a sticky intro + warning callout. Also exports the dark **CTA** band.
- **Footer** (`Footer.jsx`) — dark multi-column footer.

## How it's wired
`index.html` loads React + Babel + Lucide + the compiled design-system bundle
(`../../_ds_bundle.js`), then each `*.jsx` as a `text/babel` script. Each file
attaches its component(s) to `window` (separate Babel scopes don't share scope),
and the final inline script composes `<App>`. All primitives (Button, Icon, Card,
Tabs, CodeBlock, StatusPill, Badge, Callout) come from the bundle —
`window.MDDOAIDesignSystem_0a843c`.

## Interactions
- Header CTA + hero CTA smooth-scroll to the Quickstart section.
- Quickstart tabs swap the command block (Docker / from source / in CI).
- `CodeBlock` copy buttons copy to clipboard.
- Cards lift on hover; buttons show hover/press/focus states.

## Notes / fidelity
- Icons are **Lucide** (CDN) — documented brand substitution.
- The visual system (dark hero glow, gradient headline word, terminal-as-content)
  is derived from the brand foundations, not copied from a live page.
