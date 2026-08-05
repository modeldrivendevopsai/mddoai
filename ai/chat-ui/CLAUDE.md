# CLAUDE.md — chat-ui

## Project Overview

This is the UI for MDDOAI, a system that generates CI/CD pipeline configurations from software architecture models. It lives inside the `mddoai` monorepo at `ai/chat-ui/`.

All AI-related work lives under `mddoai/ai/`, separate from the existing Java/Eclipse codebase at the repo root. This CLAUDE.md only covers `chat-ui/`, see `ai/README.md` for how this service fits into the rest of the stack.

The app is a router-based SPA with a persistent shell, `AppShell` (sidebar + top bar), wrapping two screens:

- **`StartScreen`** (`/`, the home route) is a landing page offering two entry points, both of which route to `/pipeline` — there's only one real screen to land on, the two cards represent two conceptual ways of getting there (matching the wireframe), not two different destinations.
- **`OrchestratorScreen`** (`/pipeline`) is the pipeline dashboard: a human starts a run by entering a platform name, its real documentation URL, and any advanced retrieval options, then watches the run's 6-stage progress on a stepper, reviews each stage's output (approve, reject with a correction, or retry), and can nudge the Orchestrator with a free-form message at any point. This is also the app's one real "add/update a CI/CD platform" flow — its first stage (`docs`) is a real crawl. An optional `?run=<run_id>` query param (set when a sidebar session row is clicked, see Session history below) views a past run instead of the live one, read-only.

---

## Behaviour

1. A human enters a platform name and its real documentation URL, optionally expanding "Advanced" for retrieval's own real retry/steer levers (a hint, exclude URLs, max pages, max depth, force refresh — see `ai/retrieval/README.md`), plus a "Mock" checkbox, and starts a run.
2. The `docs` stage runs for real: `ai/orchestrator` calls `ai/retrieval`'s real crawl, slow enough on a fresh (non-cached) fetch to make local iteration painful. Checking "Mock" skips the crawl entirely — `ai/orchestrator`'s `docs_agent` returns canned placeholder output instead, so the rest of the pipeline can be exercised quickly during local dev.
3. The human reviews each stage's output in turn: **Approve** (advances and starts the next stage), **Reject** with a correction (records it, stays on the same stage), or **Retry** (reruns the current stage, folding in any recorded correction).
4. The Orchestrator narrates what's happening in the chat column after every real action, automatically.
5. The human can also type a free-form message ("the ATL output is wrong, use kebab-case names") into the "Nudge the Orchestrator.." input; an LLM decides which of the actions above it maps to.
6. This repeats until the last stage (`generation`) is approved.

---

## Stack

- **Vite + React + TypeScript**, no Next.js, no SSR
- **`react-router-dom`** for the shell/routing described above
- **shadcn/ui** — component foundation for generic primitives (`src/components/ui/`)
- **Tailwind CSS** — styling for generic layout; the design system's own tokens (see Design System below) are plain CSS custom properties, not part of Tailwind's theme
- **Vitest** — testing

---

## Architecture

`AppShell` (`src/layout/AppShell.tsx`, plus `Sidebar.tsx`/`TopBar.tsx`) is the permanent navigation frame, mounted once in `App.tsx` around whichever screen the current route resolves to. Network calls go through `src/services/`, never directly from a component:

- `src/services/orchestratorPipelineService.ts` — the one real API client for every `ai/orchestrator` operation (starting a run, reviewing/retrying a stage, reading live and past events, run history, nudging, changing the model), hits `/orchestrator-api/*` (see `vite.config.ts`'s dev proxy; `ai/orchestrator` is internal-only in `docker-compose.yml`). This is the only service that talks to a backend agent — `ai/orchestrator` is itself the only thing that talks to `ai-layer` or `ai/retrieval` (see `ai/README.md`), chat-ui never calls either directly.
- `src/services/sessions.service.ts` — real session history, backed by `ai/orchestrator`'s run-history endpoint (in-memory, for the life of that process; see Session history below).
- `src/services/platforms.service.ts` — mock data backing `StartScreen`'s supported-platforms table; not yet backed by a real API.

`src/types/orchestrator.ts` mirrors `ai/orchestrator`'s real REST contract, stage list, event shape, and run-history shape, and is the single source of truth for that contract on this side; see `ai/orchestrator/README.md` for the backend's own description of it. Lives under `src/types/`, not `src/orchestrator/`, so it doesn't collide with `src/components/orchestrator/` below (the screen's own UI components — a different "orchestrator").

`src/hooks/usePipeline.ts` takes an optional `runId`. With no `runId` it polls the live run's events on an interval and exposes the derived pipeline state plus the real pipeline actions (approve/retry a stage, nudge, change model, restart) for `OrchestratorScreen` — corrections are folded into retry, there's no separate reject action. With a `runId` for a past (non-current) run, it fetches that run's frozen event log once (no polling) and reports back that the run isn't current, which `OrchestratorScreen` uses to disable Approve/Retry/Nudge. No local/simulated state, every action is a real call.

See `ai/CLAUDE.md` for cross-service folder boundaries.

---

## Session history

The sidebar's "Open sessions" list (`src/layout/SessionsList.tsx`) is real data: `sessions.service.ts` calls `ai/orchestrator`'s `GET /runs`, which lists every run that backend process has seen, newest first — in-memory only, gone on a backend restart, no database. Clicking a row navigates to `/pipeline?run=<run_id>`, which `OrchestratorScreen` reads and passes to `usePipeline(runId)`. The live run is always `is_current: true`; any other run is read-only history (`isCurrent: false`), shown with Approve/Retry/Nudge/Restart disabled and a "back to current run" link that clears the query param.

---

## The stage stepper

Six stages, per the real wireframe: `Docs → PIM → PSM → ATL → Acceleo → Generation`, matching `ai/orchestrator`'s real `STAGES` exactly. `Stepper.tsx` (in `src/components/orchestrator/`) renders directly off that list (`@/types/orchestrator`'s `STAGES`), no per-stage special-casing.

---

## Design System

`src/design-system/` is the single, shared component/token library for the whole app (tokens.css, Button, Panel, Tabs, StatusPill, Icon), ported verbatim (values only) from the real MDDOAI Design System reference (`mddoai-design-system/project/`, a set of Claude-Design HTML/JSX prototypes to copy from, not an installable package). Violet brand (`--brand`, `#684aeb`), Space Grotesk for display/headings, IBM Plex Sans for body, IBM Plex Mono for code/output, light mode only. Fonts are self-hosted via `@fontsource*`/`@fontsource-variable*` packages, not the design system's own remote Google Fonts import.

`src/components/orchestrator/` holds `OrchestratorScreen`-specific components built on top of those shared tokens (`ChatColumn`, `CodeBlock`, `PlatformForm`, `FormField`, `StageOutputPanel`, `Stepper`) — screen-specific widgets, not duplicate primitives. Component styling there uses inline `style={{ ... }}` referencing `var(--...)` tokens directly, not Tailwind utility classes, since none of those tokens are registered in Tailwind's theme. Generic layout (flex, gap) still uses Tailwind where convenient.

---

## Docker

`docker compose up --build` from `ai/` runs this as a hot-reloading dev server (not the static-build `Dockerfile` in this folder, that's for an actual deployment target later), published at `http://localhost:5173`. Proxies `/orchestrator-api` to `ai/orchestrator` by its Compose service name; `orchestrator` is internal-only. See `ai/README.md` for the full topology.

---

## What This Is Not

- Not a generic chatbot template. Every action maps to a real `ai/orchestrator` pipeline operation, there's no conversation independent of the pipeline.
- Not connected to `ai-layer` or `ai/retrieval` directly from this app; always through `ai/orchestrator`.
- Not yet decided: real (persistent, cross-restart) session history. Today's session history (see Session history above) is deliberately in-memory-only, scoped to one backend process's lifetime — don't build a database/persistence layer for this ahead of a real decision. `StartScreen`'s supported-platforms table is still mock data (`platforms.service.ts`), unrelated to session history.
