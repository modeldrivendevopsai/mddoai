# CLAUDE.md — chat-ui

## Project Overview

This is the chat interface for the MDDOAI project — a system that generates CI/CD pipeline configurations from software architecture models. The UI is the entry point for user interaction. It lives inside the `mddoai` monorepo at `ai/chat-ui/`.

All AI-related work lives under `mddoai/ai/` — separate from the existing Java/Eclipse codebase at the repo root. This folder will eventually also contain `ai-layer/` (the Python FastAPI service handling the model router and provider wrapper). For now this CLAUDE.md only covers `chat-ui/`.

The existing backend is a Java/Eclipse MDE engine. The Python FastAPI AI layer (`ai/ai-layer/`, not yet built) will sit between this UI and the Java backend. For now, all orchestrator responses are mocked from a local JSON file.

This is a vanilla, single-conversation chat interface. Keep it simple — no sidebars, no multi-section dashboards, no platform lists. That structure may be added later once the product direction is decided. For now, build a generic, story-based chat: the user talks, the orchestrator responds, the conversation flows linearly from top to bottom.

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
- **Caddy** — serves the built app in Docker
- **Vitest** — testing

---

## Architecture

```
React Chat UI (this folder)
    ↓ sendMessage()
orchestratorService.ts     ← only file that changes when real API is ready
    ↓
Mock JSON (dev) / Python FastAPI AI Layer (later) / Java MDDOAI Backend (existing)
```

The chat UI never talks to the Java backend directly. When the Python AI layer is built (#254, #213), only `orchestratorService.ts` changes — nothing else in the UI.

---

## Folder Structure

```
mddoai/
  ai/
    chat-ui/                    ← this project
      src/
        components/
          ui/                    ← shadcn/ui components (auto-generated)
        screens/
          ConversationScreen.tsx ← the entire app — one chat thread
        mock/
          orchestrator.json      ← mock DB for orchestrator responses
        services/
          orchestratorService.ts ← sendMessage(message)
        types/
          index.ts                ← Message, OrchestratorResponse types
        App.tsx
        main.tsx
      Dockerfile
      docker-compose.yml
      Caddyfile
      package.json
    ai-layer/                   ← not yet built (#254, #213) — service wrapper + router
  src/                         ← existing Java/Eclipse code, do not touch
```

Only create files inside `mddoai/ai/chat-ui/`. Do not create or modify anything in `ai/ai-layer/` or the repo root.

---

## Types

```ts
// types/index.ts

export interface Message {
  id: string;
  role: "user" | "assistant";
  content: string;
  timestamp: number;
}

export interface OrchestratorResponse {
  message: string;
  status: "pending" | "complete";
}
```

`orchestratorService.ts` exports `sendMessage(message: string): Promise<OrchestratorResponse>`. This is the contract the real API must satisfy later — keep it stable.

---

## Mock Orchestrator (`orchestrator.json`)

Simulate a multi-turn clarification flow:

```json
{
  "turns": [
    {
      "turn": 1,
      "message": "Can you clarify which CI/CD stages are in scope?"
    },
    {
      "turn": 2,
      "message": "Do you need rollback support on deployment failure?"
    },
    {
      "turn": 3,
      "status": "complete",
      "message": "Thank you. Your request has been queued for processing."
    }
  ]
}
```

`orchestratorService.ts` reads turns sequentially. Each call to `sendMessage()` returns the next turn as an `OrchestratorResponse`. Design it so the mock can be replaced with a real API call later by only changing the internals of this file — the return type contract stays the same.

---

## State & History

- Chat history: React `useState`
- Persisted to `localStorage` so it survives a page refresh
- No sidebar, no separate "sessions" list, no backend storage at this stage

---

## Design System

**Aesthetic:** Sharp, minimal, developer-tool. Reference: Linear, Vercel, Raycast.

### CSS Variables (`index.css`)

```css
:root {
  --bg: #0a0a0a;
  --surface: #111111;
  --border: #222222;
  --text: #f5f5f5;
  --muted: #737373;
  --accent: #00bfa5;
  --user-msg: #1a1a1a;
}
```

### Typography

- Font: **Inter** (Google Fonts)
- Base: 14px, line-height 1.6
- Monospace (`JetBrains Mono`) for any technical content or code in messages

### Layout

```
┌─────────────────────────────┐
│  header — title only, 48px  │
├─────────────────────────────┤
│                             │
│   conversation (scrollable) │
│                             │
├─────────────────────────────┤
│   input bar (pinned bottom) │
└─────────────────────────────┘
```

### Rules

- `border-radius` 4px max — sharp corners
- Borders not shadows: `border: 1px solid var(--border)`
- Input bar: pinned bottom, top border only
- User messages: right-aligned, `var(--user-msg)` background
- Assistant messages: left-aligned, no background, text only
- Timestamps: `var(--muted)`, hover only
- Send button: icon only, no label

### Do NOT

- Use gradients
- Use purple, indigo, or blue accents
- Use glow or pulse animations
- Use pill-shaped message bubbles
- Use drop shadows
- Use white backgrounds
- Use emojis anywhere in the UI
- Use robot icons or AI iconography
- Add a sidebar, tabs, or multiple sections — single conversation thread only

---

## Docker

Multi-stage build: Node builds the app, Caddy serves `/dist`. `Dockerfile`, `Caddyfile`, and `docker-compose.yml` all live inside `chat-ui/`.

Run: `docker compose up --build`

---

## What This Is Not

- Not a generic chatbot template, but also not a multi-phase dashboard — just a focused conversation UI
- Not connected to any LLM directly
- Not production yet — mock mode is the current state
- Not yet decided: how platform integration history, pipeline history, or multi-session support will work. Don't build for this — it adds complexity for decisions not yet made
