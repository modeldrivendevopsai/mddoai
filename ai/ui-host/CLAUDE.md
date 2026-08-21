# CLAUDE.md — ui-host

## Project Overview

This is the UI host for MDDOAI, a system that generates CI/CD pipeline configurations from software architecture models. It lives inside the `mddoai` monorepo at `ai/ui-host/`.

All AI-related work lives under `mddoai/ai/`, separate from the existing Java/Eclipse codebase at the repo root. This CLAUDE.md only covers `ui-host/`, see `ai/README.md` for how this service fits into the rest of the stack, and `ai/CLAUDE.md`'s folder-boundaries section for the `ui-` naming convention shared with the `ui-remote-*` packages this host will compose with once they exist.

`ui-host` is a Module Federation host: it owns routing, shell layout, and all real state/backend
calls; independently-liftable UI sections (a pipeline stage's own panel, the chat column, the
stepper) are meant to become their own `ui-remote-*` packages, each its own container, consumed
here via a federated import rather than a source import. That split is a real, ongoing piece of
work (see the repo's issue tracker for "Update the UI to separate docker containers"), landing one
piece at a time — check each stage/section's own doc note below for whether it's local to this
package today or already lifted out.

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

`src/types/orchestrator.ts` mirrors `ai/orchestrator`'s real REST contract, stage list, event shape, and run-history shape, and is the single source of truth for that contract on this side; see `ai/orchestrator/README.md` for the backend's own description of it.

`src/hooks/useIntegration.ts` takes an optional `runId`. With no `runId` it polls the live run's events on an interval and exposes the derived state plus every real mutating action `IntegrationScreen` needs — corrections are folded into retry, there's no separate reject action. With a `runId` for a past (non-current) run, it fetches that run's frozen event log once (no polling) and reports back that the run isn't current, which `IntegrationScreen` uses to switch from the live interactive view to a read-only one. No local/simulated state, every action is a real call.

See `ai/CLAUDE.md` for cross-service folder boundaries.

---

## Session history

The sidebar's "Open sessions" list (`src/layout/SessionsList.tsx`) is real data: `sessions.service.ts` calls `ai/orchestrator`'s `GET /runs`, which lists every run that backend process has seen, newest first — in-memory only, gone on a backend restart, no database. Polled every 4s rather than fetched once per tab-switch: which run is current, and the list itself, both change from actions taken entirely on `IntegrationScreen` (starting, approving, resetting, resuming), so the sidebar needs to notice those on its own, not just whenever its own tab happens to get clicked.

Clicking a row navigates to `/integration?run=<run_id>`, which `IntegrationScreen` reads and passes to `useIntegration(runId)`. The live run is always `is_current: true`; any other run is read-only history (`isCurrent: false`): every mutating action is disabled, replaced by "Back to current run" (clears the query param, no backend call) and "Resume this run" (`ai/orchestrator`'s `POST /resume/{run_id}`, makes that run current again, picking up exactly where it left off — the counterpart to Restart, which replaces the current run with a blank one instead of reviving an existing one). A past run that never got past the input form (e.g. created by Restart or "Add a new platform" and then abandoned) renders neither the stage panels nor the live `DocsStartForm` — that form's submit always acts on whatever's actually current, rendering it for a non-current run would let a read-only view silently hijack the real live run — a plain "No stages were run for this session" message instead.

---

## The stage stepper

`Stepper.tsx` (in `src/features/integration/`) renders directly off `ai/orchestrator`'s real `STAGES` list (mirrored in `@/types/orchestrator`), per the real wireframe, no per-stage special-casing for the stepper nodes themselves — see Design System below for how each stage's own output panel is deliberately *not* generic the same way.

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

`src/features/` holds everything built on top of those shared tokens, one folder per real concern, neither of which imports from the other — `IntegrationScreen.tsx` itself is the only place that wires them together:

- `src/features/chat/` — `ChatColumn`, the Orchestrator's own chat log, nudge input, and model picker. This is "the Orchestrator" in the UI: the chat persona/controller, not the whole 7-stage screen.
- `src/features/integration/` — the stepper and the seven stage panels, all still local to this
  package today (candidates for their own `ui-remote-*` package, not yet lifted out):
  - `Stepper.tsx`, `stageEvents.ts`, `integration.css` at the top level. `CodeBlock.tsx` (the
    panels' shared output-display primitive) now lives in the `design-system` package instead,
    imported from there alongside every other shared primitive — it moved there not because it's
    stage-specific, but because multiple otherwise-unrelated consumers need the exact same one,
    not their own copy, the same reason anything else in that package lives there.
  - `stages/registry.ts` and `stages/StagePanelProps.ts` — cross-stage plumbing (see below).
  - `stages/{docs,serialization,pim,psm,atl,acceleo,generation}/` — **one subfolder per real `StageId`**, each holding that stage's own `XStagePanel.tsx`. `docs/` also holds `DocsStartForm.tsx` and `FormField.tsx` (explained below) — real and intentional, not a leftover: `docs` is the one stage with a real, extra input-collection need the other six don't share. Nothing about this structure requires every stage folder to stay the same size; each is free to grow whatever files its own stage's real needs require.

Each `XStagePanel.tsx` is deliberately its own component, not one generic component parameterized by `StageId`. It renders both that stage's active state (Approve/Retry, shown when it's the live pending stage) and its read-only viewed state (shown when a past instance of that stage is selected via the Stepper), controlled by which of `onApprove`/`onRetry` vs. `onBack` the caller passes (see `StagePanelProps.ts`, the shared type-only contract every panel implements). Today all seven render near-identically, that's expected, not a mistake: the point is each stage's file can diverge on its own — a different output shape, a different review UI — as MDDOAI's real per-stage backend prompts diverge, without touching the others or reintroducing a shared switch statement. None of the seven panels import each other, `stages/registry.ts` is the only file that knows all seven exist together (it maps `StageId → component`), so grabbing e.g. `PsmStagePanel.tsx` alone and dropping it into a different screen needs no other change. `IntegrationScreen.tsx` looks a stage up via that registry rather than hardcoding an if/switch chain.

`docs/DocsStartForm.tsx` is the one thing shown *before* any run exists (`IntegrationScreen`'s `!started` branch renders it directly, bypassing the registry — there's no "started" state for the registry to look up yet). It isn't a generic "start a run" form: almost every field it collects (documentation URL, hint, exclude URLs, max pages/depth, force refresh, mock) is literally the docs stage's own real input, read straight out of the pipeline's request body on the backend. The one field that isn't docs-specific is the platform name (`platform_description`) — the docs stage itself never reads it, but every later stage falls back to it, so it's collected once here rather than re-asked per stage. It's filed next to `DocsStagePanel.tsx` (its output-review counterpart) rather than merged into one component, since input-collection and output-review are different enough shapes that cramming both into one function would mean that one function doing noticeably more than every other stage's panel.

- `src/features/integration/stages/docs/FormField.tsx` — `TextField`/`NumberField`/`TextAreaField`, `DocsStartForm.tsx`'s own form field primitives.

Component styling throughout `src/features/` uses inline `style={{ ... }}` referencing `var(--...)` tokens directly, not Tailwind utility classes — see Stack above, this app doesn't use Tailwind for layout.

---

## Docker

`docker compose up --build` from `ai/` runs this as a hot-reloading dev server (not the static-build `Dockerfile` in this folder, that's for an actual deployment target later), published at `http://localhost:5173`. Proxies `/orchestrator-api` to `ai/orchestrator` by its Compose service name; `orchestrator` is internal-only. See `ai/README.md` for the full topology.

Build context is `ai/`, not this folder alone — `package.json`'s `design-system` dependency is a
sibling package (a local `file:` path), and Docker can't `COPY` from outside its build context, so
the Dockerfile copies both packages in, keeping them siblings in the image the same way they are
on disk (see the Dockerfile's own comment). `docker-compose.yml` bind-mounts `ai/design-system`
into the running container at the same path the image build baked its `node_modules` symlink to
point at, so a live edit to that package's source reaches this dev server the same way an edit to
this package's own source does, no rebuild needed.

---

## What This Is Not

- Not a generic chatbot template. Every action maps to a real `ai/orchestrator` operation, there's no conversation independent of the run.
- Not connected to `ai-layer` or `ai/retrieval` directly from this app; always through `ai/orchestrator`.
- Not yet decided: real (persistent, cross-restart) session history. Today's session history (see Session history above) is deliberately in-memory-only, scoped to one backend process's lifetime — don't build a database/persistence layer for this ahead of a real decision. `StartScreen`'s supported-platforms table is still mock data (`platforms.service.ts`), unrelated to session history.
- Not built: "Generate a CI/CD pipeline" (`StartScreen`'s other card, and the sidebar's "New pipeline" action) — both are rendered disabled with a "Coming soon" label rather than silently landing on `IntegrationScreen`, which is a different, real mode. When this is built, it needs its own screen/route and its own backend, not a graft onto `src/features/integration/`.
