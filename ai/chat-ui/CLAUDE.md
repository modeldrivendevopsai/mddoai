# CLAUDE.md — chat-ui

## Project Overview

This is the UI for MDDOAI, a system that generates CI/CD pipeline configurations from software architecture models. It lives inside the `mddoai` monorepo at `ai/chat-ui/`.

All AI-related work lives under `mddoai/ai/`, separate from the existing Java/Eclipse codebase at the repo root. This CLAUDE.md only covers `chat-ui/`, see `ai/README.md` for how this service fits into the rest of the stack.

The app is a router-based SPA with a persistent shell, `AppShell` (sidebar + top bar), wrapping two screens:

- **`OrchestratorScreen`** (`/`, the home route) is the pipeline dashboard: a human starts a run by entering a platform name, its real documentation URL, and any advanced retrieval options, then watches the run's 6-stage progress on a stepper, reviews each stage's output (approve, reject with a correction, or retry), and can nudge the Orchestrator with a free-form message at any point. This is also the app's one real "add/update a CI/CD platform" flow — its first stage (`docs`) is a real crawl.
- **`StartScreen`** (`/start`) is a landing page offering two entry points, both of which route to `/` — there's only one real screen to land on, the two cards represent two conceptual ways of getting there (matching the wireframe), not two different destinations.

---

## Behaviour

1. A human enters a platform name and its real documentation URL, optionally expanding "Advanced" for retrieval's own real retry/steer levers (a hint, exclude URLs, max pages, max depth, force refresh — see `ai/retrieval/README.md`), and starts a run.
2. The `docs` stage runs for real: `ai/orchestrator` calls `ai/retrieval`'s real crawl, which can take 90+ seconds on a fresh (non-cached) fetch.
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

- `src/services/orchestratorPipelineService.ts` — the one real API client (`startPipeline`, `reviewStage`, `rerunStage`, `getEvents`, `nudge`), hits `/orchestrator-api/*` (see `vite.config.ts`'s dev proxy; `ai/orchestrator` is internal-only in `docker-compose.yml`). This is the only service that talks to a backend agent — `ai/orchestrator` is itself the only thing that talks to `ai-layer` or `ai/retrieval` (see `ai/README.md`), chat-ui never calls either directly.
- `src/services/platforms.service.ts` / `sessions.service.ts` — mock data backing `StartScreen`'s supported-platforms table and sidebar session list; not yet backed by a real API.

`src/orchestrator/types.ts` mirrors `ai/orchestrator`'s real REST contract (`STAGES`, `OrchestratorEvent`, response shapes) and is the single source of truth for that contract on this side; see `ai/orchestrator/README.md` for the backend's own description of it.

`src/hooks/usePipeline.ts` polls `GET /events` (1.5s interval) and exposes the derived pipeline state (`events`, `currentStage`, `busy`, `started`) plus the real actions (`start`, `approve`, `reject`, `retry`, `sendNudge`) for `OrchestratorScreen`. No local/simulated state, every action is a real call.

See `ai/CLAUDE.md` for cross-service folder boundaries.

---

## The stage stepper

Six stages, per the real wireframe: `Docs → PIM → PSM → ATL → Acceleo → Generation`, matching `ai/orchestrator`'s real `STAGES` exactly. `Stepper.tsx` (in `src/components/orchestrator/`) renders directly off that list (`@/orchestrator/types`'s `STAGES`), no per-stage special-casing.

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
- Not yet decided: how platform integration history, pipeline history, or multi-session support will work. `StartScreen`'s sidebar session list and platform table are currently mock data (`platforms.service.ts`, `sessions.service.ts`). Don't build backing storage for this ahead of a real decision.
