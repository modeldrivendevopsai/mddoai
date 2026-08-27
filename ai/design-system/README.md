# design-system

The shared UI-primitive package for MDDOAI's frontend: shared components plus
`tokens.css` (the violet-brand color/spacing/typography custom properties
every component styles against). `src/index.ts`'s barrel export is the
current, authoritative list of what this package exports, don't rely on this
README (or any other doc) to enumerate it, that list changes independently
of this file. Ported verbatim (values only) from the real MDDOAI Design
System reference (`mddoai-design-system/project/` at the repo root — a set
of Claude-Design HTML/JSX prototypes to copy from, not an installable
package).

## Not a deployed service

Unlike every folder under `ai/` that runs as its own container, this one has
no Dockerfile and no port. It's a plain, non-published local npm package
(same category as `ai/clients/` on the Python side: real shared code, not a
service of its own). `ai/ui-host` and every `ai/ui-remote-*` package depend
on it directly via `"design-system": "file:../design-system"` in their own
`package.json`, so it's bundled into each consumer's own build, no runtime
dependency on a live container. See `ai/CLAUDE.md`'s folder-boundaries
section for why: a shared UI kit isn't an independently-owned feature (what
Module Federation is actually for), it's a dependency everything else needs
to render correctly at all, so making it a live remote would turn a handful
of small components into a single point of failure for the whole app.

## Local install caveat (Windows)

`file:` dependencies are npm-native (no workspace setup needed) but are
symlink-based. On Windows, symlink creation can need Developer Mode enabled
or an elevated shell, depending on the npm/Node version — if `npm install`
in a consuming package fails to link this one, that's the first thing to
check, not a real dependency problem.

## Repointing to a real published package later

`src/index.ts`'s own header comment names the plan: if/when an official
`mddoai-design-system` package is published for real, swap this package's
re-exports for that one and no consumer needs to change, they all already
just import from the package name `design-system`.
