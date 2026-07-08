# UI Kit — Documentation Site

The MDDOAI documentation reader: a faithful construction of the brand's docs
surface, built from the repo's README copy, command set, and the official mark.
(No shipped docs site was reachable at build time — see the root README *Sources*
note. Re-derive against `mddoai-web` if it becomes available.)

## Screens / parts
- **Topbar** (`DocsTopbar.jsx`) — blurred sticky bar: logo + version Badge + section
  nav + GitHub/registry icons + Get-started CTA.
- **Sidebar** (`DocsSidebar.jsx`) — grouped navigation (Get started / Transformations
  / Operate) with a search field; active item gets the violet left-rail + tint.
- **Body** (`DocsBody.jsx`) — the article: breadcrumb, title + read-time Badge,
  prose, `Callout`s, dark `CodeBlock`s, a reference table, a `StatusPill` result,
  and prev/next pager.
- **On this page** (`DocsOnThisPage.jsx`) — right rail anchor list + page actions.

## How it's wired
`index.html` loads React + Babel + Lucide + the compiled design-system bundle
(`../../_ds_bundle.js`), then each `*.jsx` as a `text/babel` script. Each file
attaches its component(s) to `window`; the final inline script composes the
three-column reader layout. All primitives (Badge, Icon, Callout, CodeBlock,
StatusPill, Button) come from the bundle — `window.MDDOAIDesignSystem_0a843c`.

## Interactions
- Sidebar items select the active page (title + breadcrumb update).
- On-this-page anchors highlight on click.
- `CodeBlock` copy buttons copy to clipboard.

## Notes / fidelity
- Icons are **Lucide** (CDN) — documented brand substitution.
- The three-column docs layout, terminal-as-content code blocks, and CI-state
  `StatusPill`s are derived from the brand foundations, not copied from a live page.
