# ui-remote-chat

A Module Federation remote: its own image and container, exposing one piece of
`ai/ui-host`'s integration screen for that host to load at runtime as a federated import, never a
source import. See `ai/ui-host/CLAUDE.md`'s Docker section for the shared Module Federation
mechanics every `ui-remote-*` package follows (how the browser loads this package's
`remoteEntry.js` through `ui-host`'s proxy, the `VITE_REMOTE_*_URL` and `PUBLIC_PATH` wiring,
why there's no `depends_on` between this package and `ui-host`), and `ai/CLAUDE.md`'s
folder-boundaries section for the `ui-` naming convention.

## What this exposes

`ChatColumn` — the Orchestrator's own chat log, nudge input, and model picker. This is "the Orchestrator" in the UI: the chat persona/controller, not the whole 7-stage screen.

## Type sharing

This package depends on `orchestrator-types` (`ai/orchestrator-types`) via an ordinary local
`"file:../orchestrator-types"` npm dependency, the same mechanism as the `design-system` dependency
below, for the type-only REST/event contract every `ui-remote-*` package and `ui-host` need. Zero
runtime cost (types erase at compile time). This replaced an earlier hand-synced copy of these types
per package — `@module-federation/vite`'s own automatic type-generation plugin failed in this
environment (see `vite.config.ts`'s own `dts: false` comment), and hand-syncing had already caused
real drift, see `ai/orchestrator-types/README.md`.

Contract used here: `OrchestratorEvent`, `OrchestratorEventType`, `Provider` (its own model picker's options).

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

Serves this remote alone at [http://localhost:5174](http://localhost:5174) — visiting that
URL directly loads a plain placeholder page (`index.html`/`src/main.tsx`), not a real UI; the
actual runtime consumer is `ai/ui-host`, loading this package's exposed component(s) into its own
page. That placeholder exists only because a plain `vite build` needs an HTML entry point.

## Test

```bash
npm test
```

Runs the component tests in jsdom, a browser-like DOM for Node.

## Build

```bash
npm run build
```

## Docker

`docker compose up --build` from `ai/` builds this package into static files and serves them with
nginx's stock configuration. It publishes no port: the browser loads it through `ai/ui-host`'s
proxy at `/remotes/chat/`, and it is built with that path as its Vite `base` (the
`PUBLIC_PATH` build argument in `docker-compose.yml`), so its own files are requested under it
too. A change here takes `docker compose up -d --build ui-remote-chat`; the page picks it up on
its next load, without rebuilding `ui-host`. See `ai/ui-host/CLAUDE.md`'s Docker section for the
full Module Federation topology.
