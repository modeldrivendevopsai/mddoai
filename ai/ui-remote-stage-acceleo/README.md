# ui-remote-stage-acceleo

A Module Federation remote: its own container, its own port (5181), exposing one piece of
`ai/ui-host`'s integration screen for that host to load at runtime as a federated import, never a
source import. See `ai/ui-host/CLAUDE.md`'s Docker section for the shared Module Federation
mechanics every `ui-remote-*` package follows (why the browser fetches this package's
`remoteEntry.js` directly rather than through `ui-host`'s own proxy, the `VITE_REMOTE_*_URL` env
var wiring, why there's no `depends_on` between this package and `ui-host`), and `ai/CLAUDE.md`'s
folder-boundaries section for the `ui-` naming convention.

## What this exposes

`AcceleoStagePanel` — the acceleo stage's own real screen: before a first attempt exists, a real
"initial screen" (`design-system`'s `PromptBuilder`, editing the real, UI-editable prompt config
`ai/acceleo_agent`'s own generation call resolves — see that service's own "Prompt configuration")
with a "Generate" action, since acceleo is one of the pipeline's manual-start stages (see
`ai/integration_runner/README.md`'s "The manual-start pause") — nothing runs until a human
reviews, and possibly edits, that config first. Once a result exists, the usual output/
validation/correction review, plus `design-system`'s `AttemptsBrowser` for every real, persisted
past attempt of this stage. `PromptBuilder` itself offers an "Add this run's corrections to
Permanent constraints" action on a successfully validated generation result, promoting that
attempt's own corrections into the permanent config (gated on a real validated success, never
offered otherwise) - this panel just supplies the prefilled block and the real endpoint.

This panel is the one place Acceleo-specific wiring lives: `PromptBuilder`/`AttemptsBrowser`
themselves carry no Acceleo knowledge at all (see `ai/design-system/README.md`'s "Multi-file
component groups") — this file supplies Acceleo's own manifest and wires every callback straight
to the matching `StagePanelProps` prop, the adapter between a backend-agnostic component group and
this one backend-specific stage.

## Type sharing

This package depends on `orchestrator-types` (`ai/orchestrator-types`) via an ordinary local
`"file:../orchestrator-types"` npm dependency, the same mechanism as the `design-system` dependency
below, for the type-only REST/event contract every `ui-remote-*` package and `ui-host` need. Zero
runtime cost (types erase at compile time). This replaced an earlier hand-synced copy of these types
per package — `@module-federation/vite`'s own automatic type-generation plugin failed in this
environment (see `vite.config.ts`'s own `dts: false` comment), and hand-syncing had already caused
real drift, see `ai/orchestrator-types/README.md`.

Contract used here: `StagePanelProps`, `StageId`, `STAGES`, `OrchestratorEvent`, `OrchestratorEventType`.
The prompt-builder/attempts shapes themselves (`PromptConfig`, `PromptBuilderManifest`, ...) come
from `design-system`, not `orchestrator-types` — that package deliberately stays backend-agnostic
(see its own README).

## Design system

Depends on `design-system` (`ai/design-system`) via an ordinary local `"file:../design-system"`
npm dependency, bundled into this package's own build at build time — not a second Module
Federation remote. See `ai/design-system/README.md` for why, and its own Windows-symlink npm
install caveat. Uses `PromptBuilder` and `AttemptsBrowser` alongside the simpler primitives
(`Button`, `CodeBlock`, `StatusPill`) every other stage panel already uses.

## Develop

```bash
npm install
npm run dev
```

Serves this remote alone at [http://localhost:5181](http://localhost:5181) — visiting that
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
[http://localhost:5181](http://localhost:5181). See `ai/ui-host/CLAUDE.md`'s Docker section
for the full Module Federation topology and why this needs a real published port rather than an
internal-only one.
