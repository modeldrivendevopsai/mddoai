# ui-remote-stage-docs

A Module Federation remote: its own container, its own port (5176), exposing one piece of
`ai/ui-host`'s integration screen for that host to load at runtime as a federated import, never a
source import. See `ai/ui-host/CLAUDE.md`'s Docker section for the shared Module Federation
mechanics every `ui-remote-*` package follows (why the browser fetches this package's
`remoteEntry.js` directly rather than through `ui-host`'s own proxy, the `VITE_REMOTE_*_URL` env
var wiring, why there's no `depends_on` between this package and `ui-host`), and `ai/CLAUDE.md`'s
folder-boundaries section for the `ui-` naming convention.

## What this exposes

`DocsStagePanel` (the docs stage's output review) and `DocsStartForm` (its own `FormField.tsx` primitives, internal to this package) — the one thing shown *before* any run exists, since almost every field it collects is the docs stage's own real retrieval input. The one asymmetric remote: every other stage exposes a single panel, this one exposes two.

## Type sharing

This package doesn't share a build-time dependency on `ui-host`'s own TypeScript types (each
`ui-remote-*` package is compiled independently) — `src/types/orchestrator.ts` and (for stage
panels) `src/types/StagePanelProps.ts` are synced copies of `ai/ui-host`'s own real source of
truth, kept in sync by hand. Zero runtime cost (types erase at compile time); this is a deliberate,
simple choice over `@module-federation/vite`'s own automatic type-generation plugin, which failed
in this environment (see `vite.config.ts`'s own `dts: false` comment) — revisit that plugin later
if hand-syncing these ever proves to be a real problem, don't add that infra pre-emptively.

Contract used here: `StageId`, `STAGES`, `OrchestratorEvent`, `OrchestratorEventType`, plus its own `DocsOptions` (mirrors `ai/ui-host/src/services/orchestrator.service.ts`'s own type, this package doesn't have that services layer available).

## Design system

Depends on `design-system` (`ai/design-system`) via an ordinary local `"file:../design-system"`
npm dependency, bundled into this package's own build at build time — not a second Module
Federation remote. See `ai/design-system/README.md` for why, and its own Windows-symlink npm
install caveat.

## Develop

```bash
npm install
npm run dev
```

Serves this remote alone at [http://localhost:5176](http://localhost:5176) — visiting that
URL directly loads a plain placeholder page (`index.html`/`src/main.tsx`), not a real UI; the
actual runtime consumer is `ai/ui-host`, loading this package's exposed component(s) into its own
page. That placeholder exists only because a plain `vite build` needs an HTML entry point.

## Build

```bash
npm run build
```

## Docker

`docker compose up --build` from `ai/` runs this as a hot-reloading dev server (not this folder's
`Dockerfile`, which builds static assets for an actual deployment target later), published at
[http://localhost:5176](http://localhost:5176). See `ai/ui-host/CLAUDE.md`'s Docker section
for the full Module Federation topology and why this needs a real published port rather than an
internal-only one.
