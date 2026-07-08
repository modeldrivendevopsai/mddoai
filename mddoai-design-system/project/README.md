# MDDOAI Design System

**MDDOAI** — *Model-Driven DevOps AI* — is an open-source tool that transforms
software architecture models into ready-to-run CI/CD pipelines. You feed it a
`.swarch` architecture model; it emits a GitLab `.gitlab-ci.yml`. The engine is
model-driven (EMF / Acceleo / ATL under the hood) and increasingly AI-assisted —
GitHub Copilot agents handle PR review, test generation, and transformation
debugging.

```
Software Architecture Model (.swarch)
            │
            ▼  MDDOAI transformation
            │
GitLab CI/CD Pipeline (.gitlab-ci.yml)
```

The brand is built for **developers and platform/DevOps engineers**: precise,
instructional, command-line-first. The visual identity centres on the mark — a
**DevOps infinity loop** pierced by a **forward arrow** (the transformation) and
crowned by an **AI sparkle**. The palette is a single confident violet against
cool slate neutrals.

This design system packages that identity — colour, type, motion, components, and
full product surfaces — so any agent or designer can produce on-brand MDDOAI work.

---

## Sources

Everything here was reverse-engineered from the public MDDOAI repository. If you
have access, explore these to build more faithful designs:

- **GitHub (engine + brand assets):** https://github.com/modeldrivendevopsai/mddoai
  - `logo/` — official mark (`MDDOAI.svg`, transparent + white 2048px PNGs, favicon)
  - `README.md` — product copy, tone, command vocabulary, Docker/source workflows
  - `docs/agents.md`, `docs/gitlab-integration.md` — feature & integration copy
- **Container registry:** `ghcr.io/modeldrivendevopsai/mddoai/mddoai`
- A `modeldrivendevopsai/mddoai-web` repo was referenced but was **not reachable**
  at build time (no `main`/`master`, or private). The two product surfaces in this
  system (marketing site + docs site) are therefore **faithful constructions** from
  the README's copy, command set, and visual mark — not copies of a shipped web app.
  If the web repo becomes available, re-derive `ui_kits/` against it.

License of source project: **EPL-2.0**.

---

## CONTENT FUNDAMENTALS — how MDDOAI writes

The source README is the canon. The voice is a **calm, expert build engineer**
walking you to a working pipeline with zero ceremony.

- **Person & address:** Second person, imperative. *"Pull the image."* *"Place your
  `.swarch` file in `input/`."* *"Check result."* The reader is *you*; the tool is
  *MDDOAI* or *it*. First person is never used.
- **Casing:** Sentence case for prose and headings. Product name is always all-caps
  **MDDOAI**. Commands, flags, file names, and paths are verbatim in `monospace`.
- **Sentence length:** Short and operational. Many fragments-as-instructions
  ("Fastest way to get started — no build required!"). Outcomes are stated plainly:
  *"Your generated `.gitlab-ci.yml` will be in the `output/` directory!"*
- **Structure over paragraphs:** Heavy use of **numbered steps**, **comparison
  tables**, and **fenced code blocks**. Almost every concept is shown as a runnable
  command, not just described. Decision points are offered as a question + arrow:
  *"Want to try it quickly? → Use Docker."*
- **Tone:** Helpful, confident, lightly enthusiastic. The occasional exclamation
  mark ("BUILD SUCCESSFUL", "no build required!") and **bold lead-ins** ("**Fastest
  way to get started**") carry the energy — never hype, never marketing fluff.
- **Emoji:** None. The brand does not use emoji. Energy comes from typography, the
  mark, and the violet — not pictographs.
- **Domain vocabulary (use verbatim):** model-driven, transformation, pipeline,
  PIM / PSM / `.swarch`, metamodel, Acceleo template, `swarch2gitlab`, snapshot vs
  release tag, update site, viewpoint. Three transformation verbs: `swarch2gitlab`,
  `pim2gitlab`, `psm2gitlab`.
- **Microcopy examples:** Button labels are direct verbs — "Pull the image", "Run
  the transformation", "Build from source", "Read the docs". Tags read like CI
  states — *passed, running, failed*.

**Do:** lead with the command. Show the output. Keep it to the point.
**Don't:** add adjectives for their own sake, use emoji, or bury the runnable line
under a paragraph.

---

## VISUAL FOUNDATIONS

### Colour
- **One brand hue: violet.** `--purple-500 #684aeb` is the primary (the loop in the
  mark); `--violet-500 #a45eed` is the lighter accent (the arrow + sparkle). The
  signature **brand gradient** runs `#684aeb → #a45eed` at ~120°. Use it sparingly —
  the mark, one hero accent, a single CTA — not as a wash behind everything.
- **Neutrals are cool slate**, not warm grey — a faint blue cast (`--neutral-50
  #f8f8fb` … `--neutral-950 #0e0d15`). Dark surfaces use the near-black `#0e0d15`
  ink, which makes the violet glow.
- **Semantics map to CI pipeline states:** green = passed, amber = running/pending,
  red = failed, blue = info. This is a deliberate brand-level mapping — reuse it for
  any status UI.
- **Imagery vibe:** cool and technical — code, terminals, pipeline graphs, model
  diagrams. When photographic, lean dark and desaturated so the violet stays the
  only saturated thing on screen.

### Type
- **Display / headings:** **Space Grotesk** — geometric, slightly technical, tight
  tracking (`-0.015em` to `-0.03em`) at large sizes. Carries the "engineered" feel.
- **Body / UI:** **IBM Plex Sans** — humanist, legible, neutral; pairs naturally
  with the mono.
- **Code / CLI:** **IBM Plex Mono** — used liberally, since commands are first-class
  content. Inline code, fenced blocks, file paths, version tags.
- Scale is a 16px-rooted modular set (`--text-2xs`…`--text-6xl`). Eyebrows/labels
  are uppercase Plex Sans with `+0.08em` tracking.

### Space, radius, layout
- **4px base grid** (`--space-1`…`--space-12`). Generous vertical rhythm; sections
  breathe.
- **Radii are modest and technical:** `--radius-sm 6px` / `--radius-md 10px` for
  cards and inputs, `--radius-pill` for status chips and tags. Nothing is fully
  round except pills and avatars. No "blobby" oversized corners.
- Containers cap at `--container-xl 1240px`; gutters are fluid
  (`clamp(1.25rem, 4vw, 3rem)`).

### Surfaces, borders, elevation
- **Cards:** white (`--surface-card`) on a faint slate page (`--surface-page`), a
  1px `--border-subtle` hairline, and a soft cool shadow (`--shadow-sm`/`md`).
  Borders do the structural work; shadows stay quiet. **No coloured left-border
  accent cards** — that pattern is off-brand.
- **Code surfaces** are dark (`--surface-code #16141f`) even on light pages — a CLI
  always reads as a terminal.
- **Shadows** are cool-tinted (`rgba(24,24,40,…)`), never warm/black. Primary
  buttons may carry a **violet glow** (`--glow-brand`) for emphasis.

### Motion & interaction
- **Easing:** `--ease-out cubic-bezier(.22,1,.36,1)` for most enter/exit; a subtle
  `--ease-spring` is available for playful affordances (use rarely). Durations are
  short: `120 / 200 / 360ms`.
- **Hover:** primary buttons darken one step (`--brand` → `--brand-strong`) and lift
  the glow; ghost/secondary lighten their surface (`--surface-sunken`); links shift
  to `--text-brand`. No large scale-ups.
- **Press:** a small `translateY(1px)` settle and slightly reduced shadow — never a
  big shrink.
- **Focus:** a 3px violet ring (`--ring-brand`) — always visible, never removed.
- Prefer fades and short slides; avoid bounces on content, infinite decorative
  loops, and gratuitous parallax.

### Transparency & blur
- Used sparingly: a translucent dark `--surface-overlay` behind modals, and an
  optional blurred sticky header on the marketing site. Body content is opaque.

---

## ICONOGRAPHY

- **Library: Lucide** (https://lucide.dev), linked from CDN. It's the closest match
  to the brand's clean, geometric, **2px-stroke, rounded-join** line style and pairs
  naturally with Space Grotesk + IBM Plex. *(Substitution flagged: the source repo
  ships no UI icon set of its own — only the logo mark — so Lucide is a documented
  stand-in. Swap if the team adopts an official set.)*
- **Style rules:** line icons only (no filled/duotone), 1.75–2px stroke, 20–24px
  default box, `currentColor` so they inherit text colour. Icons are functional and
  monochrome — never multicolour, never as decoration-for-decoration.
- **The mark is not an icon.** `assets/logo/MDDOAI-mark.svg` (and the PNGs) is the
  only brand glyph; don't redraw or recolour it. Use `MDDOAI-white-2048.png` on dark
  surfaces, `MDDOAI-transparent-2048.png` on light.
- **No emoji. No unicode-glyph icons** in product chrome. Status uses the
  semantic-colour pills (passed/running/failed), optionally with a small Lucide
  check / loader / x.

See `assets/` for the logo files and favicon.

---

## Index — what's in this system

**Foundations (root):**
- `styles.css` — the single entry point consumers link. `@import`s everything below.
- `tokens/colors.css` · `tokens/typography.css` · `tokens/spacing.css` ·
  `tokens/elevation.css` — all CSS custom properties (base + semantic aliases).
- `fonts/fonts.css` — `@font-face` / webfont loading (Space Grotesk, IBM Plex Sans
  & Mono via Google Fonts — see *Fonts* below).
- `assets/logo/` — official mark (SVG, transparent + white PNG), favicon.

**Specimen cards** (`foundations/`) — small HTML cards that populate the Design
System tab: colour scales, semantic colours, type specimens, spacing, radii,
shadows, the logo.

**Components** (`components/`) — reusable React primitives, each with `.jsx`,
`.d.ts`, `.prompt.md`, and a card. Button, Badge, StatusPill, Tag, Input, Card,
Tabs, CodeBlock, Callout, and more.

**UI kits** (`ui_kits/`):
- `marketing/` — the MDDOAI product landing page (hero, how-it-works, features,
  Docker quickstart, footer), interactive.
- `docs/` — the documentation reader (sidebar nav, article body, code blocks,
  tables, on-this-page rail).

**Skill:** `SKILL.md` — makes this system usable as a downloadable Agent Skill.

---

## Fonts

⚠️ **Substitution flagged.** The source project ships no brand webfonts. This system
specifies **Space Grotesk** (display) + **IBM Plex Sans** / **IBM Plex Mono** (body
& code) as a considered pairing for the brand's engineered-but-humane feel, loaded
from **Google Fonts** (`fonts/fonts.css`) rather than self-hosted binaries. If the
team has official typefaces, drop the files into `fonts/` and replace the `@import`
with `@font-face` rules — nothing else needs to change.
