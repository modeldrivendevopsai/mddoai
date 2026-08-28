# CLAUDE.md — ui-host

## Project Overview

This is the UI host for MDDOAI, a system that generates CI/CD pipeline configurations from software architecture models. It lives inside the `mddoai` monorepo at `ai/ui-host/`.

All AI-related work lives under `mddoai/ai/`, separate from the existing Java/Eclipse codebase at the repo root. This CLAUDE.md only covers `ui-host/`, see `ai/README.md` for how this service fits into the rest of the stack, and `ai/CLAUDE.md`'s folder-boundaries section for the `ui-` naming convention shared with the `ui-remote-*` packages below.

`ui-host` is a Module Federation host: it owns routing, shell layout, and all real state/backend
calls. Every independently-liftable UI section (each pipeline stage's own panel, the chat column,
the stepper) is its own `ui-remote-*` package, each its own container, loaded here via a federated
import at runtime rather than a source import — see Architecture below for exactly how, and
`ui-host/src/features/integration/stages/registry.ts` plus `vite.config.ts`'s `federation()`
`remotes` map for the current, exact list of which remote exposes what.

The app is a router-based SPA with a persistent shell, `AppShell` (sidebar + top bar), wrapping two screens:

- **`StartScreen`** (`/`, the home route) is a landing page offering two entry points: "Add or update a CI/CD platform" (real, routes to `/integration`) and "Generate a CI/CD pipeline" (not built yet, disabled — see What This Is Not below).
- **`IntegrationScreen`** (`/integration`) is the platform-integration dashboard: a human starts a run by entering a platform name, its real documentation URL, and any advanced retrieval options, then watches the run's 7-stage progress on a stepper, reviews each stage's output (approve, reject with a correction, or retry), and can nudge the Orchestrator with a free-form message at any point. Its first stage (`docs`) is a real crawl. An optional `?run=<run_id>` query param (set when a sidebar session row is clicked, see Session history below) views a past run instead of the live one, read-only.

Naming note, since it's easy to get backwards: this screen and its supporting code are named around **"integration"**, not "pipeline" — MDDOAI's other, not-yet-built mode is literally called "Generate a CI/CD pipeline" (see `startOptions.config.ts`'s `tone: 'pipeline'`), so naming this screen "pipeline" would collide with that. "Orchestrator" is reserved for the chat persona/controller (`ChatColumn`, and the real backend service `ai/orchestrator`), not the whole 7-stage screen either.

---

## Behaviour

1. A human enters a platform name and its real documentation URL, optionally expanding "Advanced" for retrieval's own real retry/steer levers (a hint, exclude URLs, max pages, max depth, force refresh — see `ai/retrieval/README.md`), plus a "Mock" checkbox, and starts a run.
2. The `docs` stage runs for real: `ai/orchestrator` calls `ai/retrieval`'s real crawl, slow enough on a fresh (non-cached) fetch to make local iteration painful. Checking "Mock" skips the crawl entirely — `ai/orchestrator`'s `docs_agent` returns canned placeholder output instead, so the rest of the integration can be exercised quickly during local dev.
3. The human reviews each stage's output in turn: **Approve** (advances and starts the next stage), **Reject** with a correction (records it, stays on the same stage), or **Retry** (reruns the current stage, folding in any recorded correction).
4. The Orchestrator narrates what's happening in the chat column after every real action, automatically.
5. The human can also type a free-form message ("the ATL output is wrong, use kebab-case names") into the "Nudge the Orchestrator.." input; an LLM decides which of the actions above it maps to.
6. This repeats until the last stage (`generation`) is approved.

---

## Stack

- **Vite + React + TypeScript**, no Next.js, no SSR
- **`react-router-dom`** for the shell/routing described above
- Styling is inline `style={{ ... }}` against the `design-system` package's `tokens.css` `var(--...)` custom properties, plus small per-component `.css` files for the handful of rules (`:focus`, `@keyframes`) inline styles can't express — see Design System below. No Tailwind, no shadcn.
- **Vitest** — testing

---

## Architecture

`AppShell` (`src/layout/AppShell.tsx`, plus `Sidebar.tsx`/`TopBar.tsx`) is the permanent navigation frame, mounted once in `App.tsx` around whichever screen the current route resolves to. Network calls go through `src/services/`, never directly from a component:

- `src/services/orchestrator.service.ts` — the one real API client for every `ai/orchestrator` operation, hits `/orchestrator-api/*` (see `vite.config.ts`'s dev proxy; `ai/orchestrator` is internal-only in `docker-compose.yml`). This is the only service that talks to a backend agent — `ai/orchestrator` is itself the only thing that talks to `ai-layer` or `ai/retrieval` (see `ai/README.md`), ui-host never calls either directly. Its own function names (`startPipeline`, `resetPipeline`, ...) mirror `ai/orchestrator`'s real Python function/endpoint names one-to-one (`start_pipeline()`, `reset_pipeline()`, ...), that's a deliberate exception to the "integration, not pipeline" naming note above — these name the real backend calls being wrapped, not the frontend UI mode.
- `src/services/sessions.service.ts` — real session history, backed by `ai/orchestrator`'s run-history endpoint (in-memory, for the life of that process; see Session history below).
- `src/services/platforms.service.ts` — mock data backing `StartScreen`'s supported-platforms table; not yet backed by a real API.

The `orchestrator-types` package (`ai/orchestrator-types`, a sibling package like `design-system`, see Design System below) mirrors `ai/orchestrator`'s real REST contract, stage list, event shape, and run-history shape, and is the single source of truth for that contract across this host and every `ui-remote-*` package; see `ai/orchestrator/README.md` for the backend's own description of it.

`src/hooks/useIntegration.ts` takes an optional `runId`. With no `runId` it polls the live run's events on an interval and exposes the derived state plus every real mutating action `IntegrationScreen` needs — corrections are folded into retry, there's no separate reject action. With a `runId` for a past (non-current) run, it fetches that run's frozen event log once (no polling) and reports back that the run isn't current, which `IntegrationScreen` uses to switch from the live interactive view to a read-only one. No local/simulated state, every action is a real call. It also owns the one real network call any `ui-remote-*` piece used to make on its own (`getProviders()`, for the chat column's model picker) — every federated remote is purely prop-driven, receiving state and callbacks from this hook the same way a locally-imported component would, never fetching its own backend data. That's a deliberate consistency choice, not something Module Federation requires: a `fetch()` call inside a federated remote's own code still resolves relative URLs against `ui-host`'s page origin, not the remote's own (confirmed against MDN's `fetch()` docs — relative URLs resolve against `document.baseURI`), so a remote calling `/orchestrator-api/*` directly would have worked too.

`IntegrationScreen.tsx` loads `ChatColumn`, `Stepper`, `DocsStartForm`, and each stage panel (via `stages/registry.ts`) as `React.lazy()` federated imports, each wrapped in its own `RemoteBoundary` (`src/federated/RemoteBoundary.tsx`) rather than a bare `<Suspense>` — `Suspense` alone only ever handles a lazy import's *pending* state, never a *rejected* one, and without an error boundary somewhere, React's documented behavior on an uncaught render error is to unmount the entire app, not just the broken piece. `RemoteBoundary` pairs a class-based error boundary with `Suspense` so a dead or unreachable remote degrades to a small inline fallback for just that one piece, everything else keeps working. Its `resetKey` prop remounts the boundary (clearing a previously-caught error) when the identity of what it's showing changes, e.g. switching between stage panels — see `vite.config.ts`'s `federation()` config for the remotes map (each entry's URL is an env var pointing at a browser-resolvable `localhost:xxxx` address, see Docker below for why) and `src/federated/remotes.d.ts` for the hand-written ambient TypeScript declarations each federated import specifier resolves against (this app's chosen alternative to `@module-federation/vite`'s own automatic `dts` type-generation plugin, which failed in this environment, see that plugin's own disabled-with-comment `dts: false` in every `vite.config.ts` it appears in).

See `ai/CLAUDE.md` for cross-service folder boundaries.

---

## Session history

The sidebar's "Open sessions" list (`src/layout/SessionsList.tsx`) is real data: `sessions.service.ts` calls `ai/orchestrator`'s `GET /runs`, which lists every run that backend process has seen, newest first — in-memory only, gone on a backend restart, no database. Polled every 4s rather than fetched once per tab-switch: which run is current, and the list itself, both change from actions taken entirely on `IntegrationScreen` (starting, approving, resetting, resuming), so the sidebar needs to notice those on its own, not just whenever its own tab happens to get clicked.

Clicking a row navigates to `/integration?run=<run_id>`, which `IntegrationScreen` reads and passes to `useIntegration(runId)`. The live run is always `is_current: true`; any other run is read-only history (`isCurrent: false`): every mutating action is disabled, replaced by "Back to current run" (clears the query param, no backend call) and "Resume this run" (`ai/orchestrator`'s `POST /resume/{run_id}`, makes that run current again, picking up exactly where it left off — the counterpart to Restart, which replaces the current run with a blank one instead of reviving an existing one). A past run that never got past the input form (e.g. created by Restart or "Add a new platform" and then abandoned) renders neither the stage panels nor the live `DocsStartForm` — that form's submit always acts on whatever's actually current, rendering it for a non-current run would let a read-only view silently hijack the real live run — a plain "No stages were run for this session" message instead.

---

## The stage stepper

`Stepper.tsx` (its own `ui-remote-stepper` package, loaded here as a federated import) renders directly off `ai/orchestrator`'s real `STAGES` list (imported from the shared `orchestrator-types` package, see that remote's own README), per the real wireframe, no per-stage special-casing for the stepper nodes themselves — see Design System below for how each stage's own output panel is deliberately *not* generic the same way.

---

## Design System

`ai/design-system` (a sibling package, not a folder under this app's own `src/`) is the single,
shared component/token library for the whole app — see its own `src/index.ts` barrel export for
the current, exact list of what it exports, don't rely on any other doc's enumeration of it going
forward. Ported verbatim (values only) from the real MDDOAI Design System reference
(`mddoai-design-system/project/` at the repo root, a set of Claude-Design HTML/JSX prototypes to
copy from, not an installable package). Violet brand (`--brand`, `#684aeb`), Space Grotesk for
display/headings, IBM Plex Sans for body, IBM Plex Mono for code/output, light mode only. Fonts
are self-hosted via `@fontsource*`/`@fontsource-variable*` packages, not the design system's own
remote Google Fonts import — `ibm-plex-mono` imports the `latin-<weight>.css` variant
specifically, not the bare `<weight>.css`, since the unscoped file bundles cyrillic/vietnamese
subsets this English-only app never renders; `ibm-plex-sans`/`space-grotesk` don't offer that
per-subset split for their variable-weight builds, so they stay on the bundled file.

This package lives outside `ui-host` on purpose: it's a real dependency (`"design-system":
"file:../design-system"` in `package.json`, a local npm path dependency, no workspace setup
needed), not a copy, so every future `ui-remote-*` package can depend on the exact same one. See
`ai/design-system/README.md` for why it's a plain dependency and not a Module Federation remote
despite being shared code, and its own npm-local-path Windows-symlink caveat.

`orchestrator-types` (`ai/orchestrator-types`) follows the exact same shape for the REST/event
contract instead of UI components: `"orchestrator-types": "file:../orchestrator-types"` in every
consuming `package.json`, bundled in at build time. Almost entirely type-only (erases at compile
time, zero runtime cost), except `STAGES`/`PIPELINE_EVENT_TYPES`, small real constant arrays a few
consumers need at runtime too, not just as types. `StageId`, `OrchestratorEvent`, `StagePanelProps`, and the rest of the contract used
across this host and every `ui-remote-*` package live there now instead of being hand-copied per
package — see `ai/orchestrator-types/README.md` for why that replaced an earlier hand-synced-copy
approach (it had already drifted once, see that README).

`src/features/` holds what's still local to this host package — the chat column, the stepper, and every per-stage panel each moved out to their own `ui-remote-*` package (see below), so what's left here is cross-cutting plumbing, not UI:

- `src/features/integration/stageEvents.ts` — `latestCallResult`/`originalDocsInput`, pure functions over the real event log `IntegrationScreen.tsx` itself calls, not tied to any one stage.
- `src/features/integration/stages/registry.ts` — maps `StageId → ComponentType<StagePanelProps>` (both types from `orchestrator-types`), each entry a `React.lazy()` federated import into that stage's own `ui-remote-stage-*` package. The one place that knows all seven stages exist together; a stage panel's own package never imports this or another stage's package.
- `src/federated/remotes.d.ts` — ambient TypeScript declarations for every federated import specifier used above and in `IntegrationScreen.tsx` (`uiRemoteChat/ChatColumn`, `uiRemoteStepper/Stepper`, `uiRemoteStageDocs/DocsStagePanel`, etc.), referencing `orchestrator-types` for the prop shapes themselves.

Each stage's own package (`ai/ui-remote-stage-{docs,serialization,pim,psm,atl,acceleo,generation}/`, one per real `StageId`) holds that stage's `XStagePanel.tsx` as its own component, not one generic component parameterized by `StageId`. It renders both that stage's active state (Approve/Retry, shown when it's the live pending stage) and its read-only viewed state (shown when a past instance of that stage is selected via the Stepper), controlled by which of `onApprove`/`onRetry` vs. `onBack` the caller passes. Today all seven render near-identically, that's expected, not a mistake: the point is each stage's file (and now, its own container) can diverge on its own — a different output shape, a different review UI, even its own release cadence — as MDDOAI's real per-stage backend prompts diverge, without touching the others.

`ai/ui-remote-stage-docs/` is the one asymmetric package: it also exposes `DocsStartForm.tsx` (plus its own `FormField.tsx` form-field primitives, internal to that package, not separately exposed), the one thing shown *before* any run exists (`IntegrationScreen`'s `!started` branch loads it directly, bypassing the registry — there's no "started" state for the registry to look up yet). It isn't a generic "start a run" form: almost every field it collects (documentation URL, hint, exclude URLs, max pages/depth, force refresh, mock) is literally the docs stage's own real input, read straight out of the pipeline's request body on the backend. The one field that isn't docs-specific is the platform name (`platform_description`) — the docs stage itself never reads it, but every later stage falls back to it, so it's collected once here rather than re-asked per stage.

`ai/ui-remote-chat/` holds `ChatColumn` — the Orchestrator's own chat log, nudge input, and model picker, "the Orchestrator" in the UI, not the whole 7-stage screen. `ai/ui-remote-stepper/` holds `Stepper` (see The stage stepper below). `CodeBlock.tsx` (the stage panels' shared output-display primitive) lives in the `design-system` package instead of any one remote, imported from there alongside every other shared primitive — it moved there not because it's stage-specific, but because multiple otherwise-unrelated consumers need the exact same one, not their own copy, the same reason anything else in that package lives there.

Component styling throughout `src/features/` and every `ui-remote-*` package uses inline `style={{ ... }}` referencing `var(--...)` tokens directly, not Tailwind utility classes — see Stack above, this app doesn't use Tailwind for layout.

---

## Docker

`docker compose up --build` from `ai/` runs this as a hot-reloading dev server (not the static-build `Dockerfile` in this folder, that's for an actual deployment target later), published at `http://localhost:5173`. Proxies `/orchestrator-api` to `ai/orchestrator` by its Compose service name; `orchestrator` is internal-only. See `ai/README.md` for the full topology.

Build context is `ai/`, not this folder alone — `package.json`'s `design-system` and
`orchestrator-types` dependencies are both sibling packages (local `file:` paths), and Docker can't
`COPY` from outside its build context, so the Dockerfile copies all three packages in, keeping them
siblings in the image the same way they are on disk (see the Dockerfile's own comment).
`docker-compose.yml` bind-mounts `ai/design-system` and `ai/orchestrator-types` into the running
container at the same paths the image build baked its `node_modules` symlinks to point at, so a
live edit to either package's source reaches this dev server the same way an edit to this package's
own source does, no rebuild needed. Every `ui-remote-*` service follows the exact same
Dockerfile/bind-mount shape for the exact same reason.

Loading a `ui-remote-*` piece is not this dev server's own proxy doing the work — the **browser**
fetches each remote's `remoteEntry.js` directly against that remote's own published port
(`vite.config.ts`'s `remote()` helper builds each URL from a `VITE_REMOTE_*_URL` env var,
`docker-compose.yml` sets these to real `localhost:xxxx` addresses, matching each remote's own
`ports:` entry there), after this page has already loaded. That's a real, different mechanism from
`VITE_ORCHESTRATOR_PROXY_TARGET` above (a Docker service name only this dev *server* ever resolves,
never the browser). No `depends_on` between `ui-host` and any `ui-remote-*`: nothing here calls a
remote at container-start time, only the browser does, later, and can retry independently.

---

## What This Is Not

- Not a generic chatbot template. Every action maps to a real `ai/orchestrator` operation, there's no conversation independent of the run.
- Not connected to `ai-layer` or `ai/retrieval` directly from this app; always through `ai/orchestrator`.
- Not yet decided: real (persistent, cross-restart) session history. Today's session history (see Session history above) is deliberately in-memory-only, scoped to one backend process's lifetime — don't build a database/persistence layer for this ahead of a real decision. `StartScreen`'s supported-platforms table is still mock data (`platforms.service.ts`), unrelated to session history.
- Not built: "Generate a CI/CD pipeline" (`StartScreen`'s other card, and the sidebar's "New pipeline" action) — both are rendered disabled with a "Coming soon" label rather than silently landing on `IntegrationScreen`, which is a different, real mode. When this is built, it needs its own screen/route and its own backend, not a graft onto `src/features/integration/`.
- Not deployed to production: this `Dockerfile` (and every `ui-remote-*` package's own) builds real static assets (`npm run build`, `dist/`), but `docker-compose.yml` always overrides `command` to `npm run dev` — there's no static-file-serving stage (nginx, `vite preview`, etc.) and no separate production compose file anywhere in this repo yet. Deliberate MVP scope, not an oversight: local dev works fully via the dev server, and a real production topology (which static-file server, one compose file per environment or a shared one with overrides) is worth deciding for real when there's an actual deployment target, not guessed at ahead of one.
