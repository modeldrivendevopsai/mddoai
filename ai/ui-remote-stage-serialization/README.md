# ui-remote-stage-serialization

A Module Federation remote: its own container, its own port (5177), exposing one piece of
`ai/ui-host`'s integration screen for that host to load at runtime as a federated import, never a
source import. See `ai/ui-host/CLAUDE.md`'s Docker section for the shared Module Federation
mechanics every `ui-remote-*` package follows (why the browser fetches this package's
`remoteEntry.js` directly rather than through `ui-host`'s own proxy, the `VITE_REMOTE_*_URL` env
var wiring, why there's no `depends_on` between this package and `ui-host`), and `ai/CLAUDE.md`'s
folder-boundaries section for the `ui-` naming convention.

## What this exposes

`SerializationStagePanel` — the serialization stage's own output review.

## Type sharing

This package depends on `orchestrator-types` (`ai/orchestrator-types`) via an ordinary local
`"file:../orchestrator-types"` npm dependency, the same mechanism as the `design-system` dependency
below, for the type-only REST/event contract every `ui-remote-*` package and `ui-host` need. Zero
runtime cost (types erase at compile time). This replaced an earlier hand-synced copy of these types
per package — `@module-federation/vite`'s own automatic type-generation plugin failed in this
environment (see `vite.config.ts`'s own `dts: false` comment), and hand-syncing had already caused
real drift, see `ai/orchestrator-types/README.md`.

Contract used here: `StageId`, `STAGES`, `OrchestratorEvent`, `OrchestratorEventType`.

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

Serves this remote alone at [http://localhost:5177](http://localhost:5177) — visiting that
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
[http://localhost:5177](http://localhost:5177). See `ai/ui-host/CLAUDE.md`'s Docker section
for the full Module Federation topology and why this needs a real published port rather than an
internal-only one.
