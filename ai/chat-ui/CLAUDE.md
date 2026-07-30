# CLAUDE.md — chat-ui

## Project Overview

This is the UI for MDDOAI, a system that generates CI/CD pipeline configurations from software architecture models. It lives inside the `mddoai` monorepo at `ai/chat-ui/`.

All AI-related work lives under `mddoai/ai/`, separate from the existing Java/Eclipse codebase at the repo root. This CLAUDE.md only covers `chat-ui/` — see `ai/README.md` for how this service fits into the rest of the stack.

This is a single-screen pipeline dashboard, not a generic chat app: a human starts a pipeline run, watches its 6-stage progress on a stepper, reviews each stage's output (approve, reject with a correction, or retry), and can nudge the Orchestrator with a free-form message at any point. Matches the real product wireframe (`mddoai-ui-wireframe-v3.html`, `wireframes` branch): the stage stepper and the "Nudge the Orchestrator.." chat column are one unified view, not two separate screens.

---

## Behaviour

1. A human enters a platform description and its real documentation URL, and starts a run.
2. The `docs` stage runs for real (a real crawl via `ai/retrieval`, can take 90+ seconds).
3. The human reviews each stage's output in turn: **Approve** (advances and starts the next stage), **Reject** with a correction (records it, stays on the same stage), or **Retry** (reruns the current stage, folding in any recorded correction).
4. The Orchestrator narrates what's happening in the chat column after every real action, automatically.
5. The human can also type a free-form message ("the ATL output is wrong, use kebab-case names") into the "Nudge the Orchestrator.." input; an LLM decides which of the actions above it maps to.
6. This repeats until the last stage (`generation`) is approved.

---

## Stack

- **Vite + React + TypeScript** — no Next.js, no SSR, no router (one screen)
- **shadcn/ui** — component foundation for generic primitives (`src/components/ui/`)
- **Tailwind CSS** — styling for generic layout; the Orchestrator screen's own design-system tokens (see below) are plain CSS custom properties, not part of Tailwind's theme
- **Vitest** — testing

---

## Architecture

The entire app is one screen (`src/screens/OrchestratorScreen.tsx`), no router, no multi-page structure. Network calls go through `src/services/`, never directly from a component, and only ever to `ai/orchestrator` (never the Java backend, `ai-layer`, or an LLM provider directly — `ai/orchestrator` itself is the only thing that talks to those).

- `src/services/orchestratorPipelineService.ts` — the real API client (`startPipeline`, `reviewStage`, `rerunStage`, `getEvents`, `nudge`), hits `/orchestrator-api/*` (see `vite.config.ts`'s dev proxy, `ai/orchestrator` is internal-only in `docker-compose.yml`).
- `src/orchestrator/types.ts` — mirrors `ai/orchestrator`'s real REST contract (`STAGES`, `OrchestratorEvent`, response shapes). Single source of truth for that contract on this side; see `ai/orchestrator/README.md` for the backend's own description of it.
- `src/hooks/usePipeline.ts` — polls `GET /events` (1.5s interval) and exposes the derived pipeline state (`events`, `currentStage`, `busy`, `started`) plus the real actions (`start`, `approve`, `reject`, `retry`, `sendNudge`). No local/simulated state, every action is a real call.
- `src/components/orchestrator/` — `Stepper` (the 6-stage progress indicator), `StageOutputPanel` (current stage's output + Approve/Reject/Retry), `ChatColumn` (the event log rendered as messages + the nudge input).

See `ai/CLAUDE.md` for cross-service folder boundaries.

---

## The stage stepper

Six stages, per the real wireframe: `Docs → PIM → PSM → ATL → Acceleo → Generation`, matching `ai/orchestrator`'s real `STAGES` exactly. `Stepper.tsx` renders directly off that list (`@/orchestrator/types`'s `STAGES`), no per-stage special-casing.

---

## Design System

The Orchestrator screen uses the real MDDOAI Design System tokens (`mddoai-design-system/project/tokens/*.css`), ported verbatim into `src/orchestrator/tokens.css`, scoped to a `.orch-scope` wrapper class (not `:root`), so they don't collide with any other token values elsewhere in this app. Violet brand (`--brand`, `#684aeb`), Space Grotesk for display/headings (`--font-display`), IBM Plex Sans for body (`--font-sans`), IBM Plex Mono for code/output (`--font-mono`), light mode (the design system defines no dark variant). Fonts are self-hosted via `@fontsource*` packages (matching this repo's existing convention), not the design system's own remote Google Fonts import.

Component styling uses inline `style={{ ... }}` referencing these `var(--...)` tokens directly, not Tailwind utility classes, since none of these tokens are registered in Tailwind's theme (adding them there risked exactly the cross-contamination `.orch-scope` exists to avoid). Generic layout (flex, gap) still uses Tailwind where convenient.

---

## Docker

`docker compose up --build` from `ai/` runs this as a hot-reloading dev server (not the static-build `Dockerfile` in this folder, that's for an actual deployment target later), published at `http://localhost:5173`. Proxies to `ai-layer` (`/api`) and `ai/orchestrator` (`/orchestrator-api`) by their Compose service names, both internal-only. See `ai/README.md` for the full topology.

---

## What This Is Not

- Not a generic chatbot — every action maps to a real `ai/orchestrator` pipeline operation, there's no conversation independent of the pipeline.
- Not connected to `ai-layer` or any LLM provider directly — always through `ai/orchestrator`.
- Not the full wireframe: the platform list and "Add a CI/CD Platform" pages aren't built, `ai/orchestrator`'s real `/start` takes a platform description and URL directly, there's no "saved platforms" concept server-side to back a list/CRUD screen yet. Don't build those pages ahead of that backend capability existing.
