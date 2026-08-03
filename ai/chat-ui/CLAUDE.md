# CLAUDE.md — chat-ui

## Project Overview

This is the UI for MDDOAI, a system that generates CI/CD pipeline configurations from software architecture models. It lives inside the `mddoai` monorepo at `ai/chat-ui/`.

All AI-related work lives under `mddoai/ai/`, separate from the existing Java/Eclipse codebase at the repo root. This CLAUDE.md only covers `chat-ui/`, see `ai/README.md` for how this service fits into the rest of the stack.

The app is a router-based SPA with a persistent shell, `AppShell` (sidebar + top bar), wrapping three screens:

- **`OrchestratorScreen`** (`/`, the home route) is the pipeline dashboard: a human starts a pipeline run, watches its 6-stage progress on a stepper, reviews each stage's output (approve, reject with a correction, or retry), and can nudge the Orchestrator with a free-form message at any point.
- **`StartScreen`** (`/start`) is a landing page offering two entry points, "Generate a CI/CD Pipeline" (routes to `/`) and "Add or Update a CI/CD Platform" (routes to `/platforms/new`), plus a supported-platforms table.
- **`PlatformIntegrationScreen`** (`/platforms/new`) is the retrieval agent's "Add a CI/CD Platform" flow (documentation input → fetch progress → review), calling the `retrieval` service's `POST /fetch` directly. The retrieval agent is independent, not routed through the Orchestrator.

---

## Behaviour

### OrchestratorScreen (`/`)

1. A human enters a platform description and its real documentation URL, and starts a run.
2. The `docs` stage runs for real (a real crawl via `ai/retrieval`, can take 90+ seconds).
3. The human reviews each stage's output in turn: **Approve** (advances and starts the next stage), **Reject** with a correction (records it, stays on the same stage), or **Retry** (reruns the current stage, folding in any recorded correction).
4. The Orchestrator narrates what's happening in the chat column after every real action, automatically.
5. The human can also type a free-form message ("the ATL output is wrong, use kebab-case names") into the "Nudge the Orchestrator.." input; an LLM decides which of the actions above it maps to.
6. This repeats until the last stage (`generation`) is approved.

### PlatformIntegrationScreen (`/platforms/new`)

1. A human enters a platform name and a documentation URL (or, once wired, uploads a PDF), with optional advanced options (max pages, max depth, a hint, exclude URLs).
2. Submitting calls the `retrieval` service directly, which crawls the documentation, ranks and cleans the content, and returns markdown pages.
3. The Docs stage in that screen's own stage pipeline reflects success, failure, or in-progress state; a failed fetch shows a retry affordance.

---

## Stack

- **Vite + React + TypeScript**, no Next.js, no SSR
- **`react-router-dom`** for the shell/routing described above
- **shadcn/ui** — component foundation for generic primitives (`src/components/ui/`)
- **Tailwind CSS** — styling for generic layout; each screen's own design-system tokens (see Design System below) are plain CSS custom properties, not part of Tailwind's theme
- **Vitest** — testing

---

## Architecture

`AppShell` (`src/layout/AppShell.tsx`, plus `Sidebar.tsx`/`TopBar.tsx`) is the permanent navigation frame, mounted once in `App.tsx` around whichever screen the current route resolves to. Network calls go through `src/services/`, never directly from a component, and each service only ever talks to one independent backend agent, never the Java backend or an LLM provider directly:

- `src/services/orchestratorPipelineService.ts` — the real API client (`startPipeline`, `reviewStage`, `rerunStage`, `getEvents`, `nudge`) for `OrchestratorScreen`, hits `/orchestrator-api/*` (see `vite.config.ts`'s dev proxy; `ai/orchestrator` is internal-only in `docker-compose.yml`).
- `src/services/retrievalService.ts` — calls the `retrieval` service directly via `/retrieval-api/*` for `PlatformIntegrationScreen`; retrieval is its own agent, not routed through the Orchestrator.
- `src/services/platforms.service.ts` / `sessions.service.ts` — mock data backing `StartScreen`'s supported-platforms table and sidebar session list; not yet backed by a real API.

For the full request path (Vite dev proxy, ai-layer, orchestrator, retrieval, provider routing), see `ai/README.md`.

`src/orchestrator/types.ts` mirrors `ai/orchestrator`'s real REST contract (`STAGES`, `OrchestratorEvent`, response shapes) and is the single source of truth for that contract on this side; see `ai/orchestrator/README.md` for the backend's own description of it. `src/components/platform-integration/types.ts` holds `PlatformIntegrationScreen`'s own local UI types (`PipelineStage`, `DocsStepState`, etc.), and `retrievalService.ts` holds the types mirroring retrieval's real `POST /fetch` request/response shape (`RetrievalFetchResult`, `RetrievedPage`, etc.), both scoped to that screen. There is no single shared `src/types/index.ts` — types are screen-scoped.

`src/hooks/usePipeline.ts` polls `GET /events` (1.5s interval) and exposes the derived pipeline state (`events`, `currentStage`, `busy`, `started`) plus the real actions (`start`, `approve`, `reject`, `retry`, `sendNudge`) for `OrchestratorScreen`. No local/simulated state, every action is a real call.

See `ai/CLAUDE.md` for cross-service folder boundaries.

---

## The stage stepper

Six stages, per the real wireframe: `Docs → PIM → PSM → ATL → Acceleo → Generation`, matching `ai/orchestrator`'s real `STAGES` exactly. `Stepper.tsx` (in `src/components/orchestrator/`) renders directly off that list (`@/orchestrator/types`'s `STAGES`), no per-stage special-casing. `PlatformIntegrationScreen`'s own `StagePipeline` component renders the same six-stage shape for that screen's flow.

---

## Design System

Three separate, unshared component/token sets coexist in this codebase, one per screen family:

- **`src/design-system/`** (Button, Panel, Tabs, StatusPill, `tokens.css`) plus `src/layout/` — backs `AppShell` and `StartScreen`.
- **`src/components/platform-integration/`** (BrandButton, FormField, OrchestratorPanel, StagePipeline, `brandTokens.ts`) — backs `PlatformIntegrationScreen`.
- **`src/components/orchestrator/`** (Button, ChatColumn, CodeBlock, PlatformForm, StageOutputPanel, Stepper) plus `src/orchestrator/tokens.css` — backs `OrchestratorScreen`. Ported verbatim (values only) from the real MDDOAI Design System tokens (`mddoai-design-system/project/tokens/*.css`), scoped to a `.orch-scope` wrapper class (not `:root`) so they don't collide with the other two token sets. Violet brand (`--brand`, `#684aeb`), Space Grotesk for display/headings, IBM Plex Sans for body, IBM Plex Mono for code/output, light mode only. Fonts are self-hosted via `@fontsource*`/`@fontsource-variable*` packages, not the design system's own remote Google Fonts import.

Unifying these three into one shared design system is explicitly out of scope for now. Component styling in the Orchestrator screen uses inline `style={{ ... }}` referencing `var(--...)` tokens directly, not Tailwind utility classes, since none of those tokens are registered in Tailwind's theme. Generic layout (flex, gap) still uses Tailwind where convenient across all three.

---

## Docker

`docker compose up --build` from `ai/` runs this as a hot-reloading dev server (not the static-build `Dockerfile` in this folder, that's for an actual deployment target later), published at `http://localhost:5173`. Proxies `/api` to `ai-layer`, `/retrieval-api` to `retrieval`, and `/orchestrator-api` to `ai/orchestrator`, all by their Compose service names; `retrieval` and `orchestrator` are internal-only. See `ai/README.md` for the full topology.

---

## What This Is Not

- Not a generic chatbot template. `OrchestratorScreen`'s every action maps to a real `ai/orchestrator` pipeline operation, there's no conversation independent of the pipeline on that screen.
- Not connected to `ai-layer` or any LLM provider directly from `OrchestratorScreen` or `PlatformIntegrationScreen`; each always goes through its own backend agent (`ai/orchestrator`, `ai/retrieval` respectively).
- Not yet decided: how platform integration history, pipeline history, or multi-session support will work. `StartScreen`'s sidebar session list and platform table are currently mock data (`platforms.service.ts`, `sessions.service.ts`). Don't build backing storage for this ahead of a real decision.
