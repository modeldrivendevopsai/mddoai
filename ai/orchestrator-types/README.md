# orchestrator-types

The shared contract for `ai/orchestrator`'s real REST API: `StageId`, `OrchestratorEvent`,
`StagePanelProps`, and every request/response shape `ai/ui-host` and the `ai/ui-remote-*` packages
need to talk about that contract. `src/orchestrator.ts` is the single source of truth; every
consumer imports from this package directly rather than keeping its own copy.

## Not a deployed service

Like `ai/design-system`, this one has no Dockerfile and no port. It's a plain, non-published local
npm package (same category as `ai/clients/` on the Python side: real shared code, not a service of
its own). `ai/ui-host` and every `ai/ui-remote-*` package depend on it via
`"orchestrator-types": "file:../orchestrator-types"` in their own `package.json`, so it's bundled
into each consumer's own build at build time, with no live container to keep up. Almost everything
here is type-only (erases at compile time); the two exceptions are `STAGES` and
`PIPELINE_EVENT_TYPES`, small real `const` arrays a few consumers need at runtime, not just as
types — still a handful of primitive values, not meaningfully different in cost from any other tiny
shared constant. See `ai/CLAUDE.md`'s folder-boundaries section for why this and
`design-system` both stay plain dependencies rather than Module Federation remotes.

This package replaces an earlier approach where each `ui-remote-*` package hand-synced its own copy
of this contract (kept in sync by hand, since each remote compiles independently and
`@module-federation/vite`'s own `dts` type-generation plugin failed in this environment — see any
`vite.config.ts`'s `dts: false` comment). That copy-per-package approach already drifted once
(`ui-remote-stage-docs`'s copy grew an extra `DocsOptions` type the others didn't have); this
package is the fix, an ordinary build-time dependency instead of a hand-synced duplicate, the exact
mechanism `design-system` already uses for shared UI code.

## Local install caveat (Windows)

`file:` dependencies are npm-native (no workspace setup needed) but are symlink-based. On Windows,
symlink creation can need Developer Mode enabled or an elevated shell, depending on the npm/Node
version — if `npm install` in a consuming package fails to link this one, that's the first thing to
check, not a real dependency problem.
