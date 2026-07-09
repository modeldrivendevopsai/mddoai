# CLAUDE.md — chat-ui

## Project Overview

This is the chat interface for the MDDOAI project — a system that generates CI/CD pipeline configurations from software architecture models. The UI is the entry point for user interaction. It lives inside the `mddoai` monorepo at `ai/chat-ui/`.

All AI-related work lives under `mddoai/ai/`, separate from the existing Java/Eclipse codebase at the repo root. This CLAUDE.md only covers `chat-ui/` — see `ai/README.md` for how this service fits into the rest of the stack.

This is a vanilla, single-conversation chat interface: the user talks, the orchestrator responds, the conversation flows linearly from top to bottom. A slide-out history panel (current conversation preview + a static platform list) is also part of the current UI — see `ConversationScreen.tsx`.

---

## Behaviour

1. User sends a message describing what they want (e.g. a platform documentation URL and an integration goal)
2. Message is passed to the Orchestrator
3. Orchestrator may respond with a clarification request — presented to the user as a normal chat message
4. User responds, conversation continues
5. Loop continues until the Orchestrator signals completion
6. On completion, the final result is shown as the last message in the conversation

That's the whole flow. One conversation, one thread, no extra UI chrome.

---

## Stack

- **Vite + React + TypeScript** — no Next.js, no SSR
- **shadcn/ui + prompt-kit** — component foundation (prompt-kit replaces deprecated shadcn-chat, installs via standard shadcn registry CLI, no AI SDK coupling)
- **Tailwind CSS** — styling
- **Vitest** — testing

---

## Architecture

The entire app is one screen, `src/screens/ConversationScreen.tsx` — no router, no multi-page structure. `src/services/orchestratorService.ts` is the only file that talks to the network, and it only ever talks to the AI layer, never the Java backend or an LLM provider directly. For the full request path (Caddy gateway, ai-layer, provider routing), see `ai/README.md`.

See `ai/CLAUDE.md` for cross-service folder boundaries.

---

## Types

See `src/types/index.ts` for the current `Message` and `OrchestratorResponse` shapes. `orchestratorService.ts` sends the full conversation history (stripped to `{role, content}` pairs) as `POST /api/chat` and maps the AI layer's response onto `OrchestratorResponse` — that contract (full history in, one response out) is what should stay stable, independent of the exact field names.

---

## State & History

- Chat history: React `useState`
- Persisted to `localStorage` so it survives a page refresh
- A slide-out panel shows a preview of the current conversation and a static platform list (`GitLab CI`, `Bamboo`, `Azure DevOps`) — not a multi-session history, no backend storage at this stage

---

## Design System

Follow the MDDOAI Design System for all UI work here — see `ai/CLAUDE.md`. No local brand/color/typography rules in this file; that's the single source of truth.

Layout is header on top, scrollable conversation in the middle, input bar pinned to the bottom. The slide-out history panel is the one exception to "single conversation thread" — no other tabs or extra content sections.

---

## Docker

This service's own Dockerfile only builds the static assets — a separate `caddy` gateway service serves them and proxies API calls to `ai-layer`. Run the whole stack from `ai/`: `docker compose up --build`. See `ai/README.md` for the full topology.

---

## What This Is Not

- Not a generic chatbot template, but also not a multi-phase dashboard — just a focused conversation UI
- Not connected to any LLM directly — always through the AI layer
- Not yet decided: how platform integration history, pipeline history, or multi-session support will work. Don't build for this — it adds complexity for decisions not yet made
