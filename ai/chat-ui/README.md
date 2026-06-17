# chat-ui

The chat interface for MDDOAI — a single-thread conversation UI where a user
describes an integration goal and an orchestrator (currently mocked) walks
them through clarification turns to a final result.

See [CLAUDE.md](./Claude.md) for the full product/design spec this project
implements.

## Stack

| Purpose | Tool | Docs |
|---|---|---|
| Build tool / dev server | [Vite](https://vite.dev/) | [Guide](https://vite.dev/guide/) |
| UI framework | [React 19](https://react.dev/) + TypeScript | [React docs](https://react.dev/learn) · [TS handbook](https://www.typescriptlang.org/docs/handbook/intro.html) |
| Styling | [Tailwind CSS v4](https://tailwindcss.com/) | [Docs](https://tailwindcss.com/docs) |
| Component foundation | [shadcn/ui](https://ui.shadcn.com/) | [Docs](https://ui.shadcn.com/docs) |
| Chat-specific components | [prompt-kit](https://www.prompt-kit.com/) | [Docs](https://www.prompt-kit.com/docs) |
| Testing | [Vitest](https://vitest.dev/) | [Docs](https://vitest.dev/guide/) |
| Production server | [Caddy](https://caddyserver.com/) (in Docker) | [Docs](https://caddyserver.com/docs/) |

> `shadcn-chat` was the originally specified chat component library but is
> no longer maintained — prompt-kit replaces it. It installs through the
> same `shadcn` registry CLI and has no Vercel AI SDK coupling, so it fits
> this project's plain `sendMessage()` / `useState` architecture cleanly.

## Prerequisites

- [Node.js](https://nodejs.org/) 22+ and npm
- [Docker](https://www.docker.com/) (only needed for the containerized run)

## Getting started

```bash
npm install
npm run dev
```

Open the URL Vite prints (default [http://localhost:5173](http://localhost:5173)).

## Develop

The whole app is one screen: [src/screens/ConversationScreen.tsx](./src/screens/ConversationScreen.tsx).
There's no router, no sidebar, no multi-page navigation — see
[CLAUDE.md](./Claude.md) before adding any.

Where things live:

- **UI / conversation logic** → `src/screens/ConversationScreen.tsx`
- **Message bubble / send box primitives** → `src/components/ui/` (shadcn/ui + prompt-kit, mostly generated — edit with care, see [Adding more components](#adding-more-shadcnui--prompt-kit-components) below)
- **Orchestrator contract** → `src/services/orchestratorService.ts` — exports `sendMessage(message: string): Promise<OrchestratorResponse>`. This is the **only file that changes** when the real Python AI layer replaces the mock.
- **Mock conversation data** → `src/mock/orchestrator.json` — edit this to change what the mock orchestrator says, or how many clarification turns there are.
- **Shared types** → `src/types/index.ts`
- **Design tokens / theme** → `src/index.css` (`--bg`, `--surface`, `--accent`, etc., mapped onto shadcn's `--background` / `--primary` / ... tokens)

The `@/` import alias points at `src/` (configured in `vite.config.ts` and
`tsconfig.app.json`).

Chat history is kept in `localStorage` under the key `mddoai-conversation`,
so a page refresh resumes the same conversation. The mock orchestrator's
turn counter, however, lives in memory only and resets on reload — that's a
known limitation of mock mode, not something to fix.

### Adding more shadcn/ui or prompt-kit components

```bash
# shadcn/ui
npx shadcn@latest add <component>

# prompt-kit (installs through the same shadcn registry mechanism)
npx shadcn@latest add "https://prompt-kit.com/c/<component>.json"
```

Both write into `src/components/ui/` using the aliases in `components.json`.

## Test

```bash
npm run test         # one-shot run (CI mode)
npm run test:watch   # watch mode while developing
```

[orchestratorService.test.ts](./src/services/orchestratorService.test.ts)
exercises the mock's 3-turn clarification flow end-to-end (two pending
turns, then a `status: "complete"` turn).

## Lint & typecheck

```bash
npm run lint
npx tsc -b
```

## Build

```bash
npm run build      # outputs to dist/
npm run preview    # serve the production build locally for a sanity check
```

## Run in Docker (Caddy)

Multi-stage build: Node builds the static app, Caddy serves `dist/`.

```bash
docker compose up --build
```

The app is served at [http://localhost:8080](http://localhost:8080) (see
`docker-compose.yml` for the port mapping; `Caddyfile` controls how it's
served). Stop it with:

```bash
docker compose down
```

## Project structure

```
chat-ui/
  src/
    components/ui/        shadcn/ui + prompt-kit components (mostly generated)
    screens/
      ConversationScreen.tsx   the entire app — one chat thread
    mock/
      orchestrator.json        mock turn-by-turn orchestrator responses
    services/
      orchestratorService.ts   sendMessage() — swap point for the real API
    types/
      index.ts                 Message, OrchestratorResponse
    App.tsx
    main.tsx
    index.css                  design tokens / theme
  Dockerfile
  docker-compose.yml
  Caddyfile
```
