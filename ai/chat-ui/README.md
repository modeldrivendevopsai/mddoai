# chat-ui

Chat interface for MDDOAI. Users describe an integration goal; the orchestrator walks them through clarification turns and returns a result.

See [CLAUDE.md](./Claude.md) for the full product and design spec.

## Stack

| Purpose | Tool | Docs |
|---|---|---|
| Build tool / dev server | [Vite](https://vite.dev/) | [Guide](https://vite.dev/guide/) |
| UI framework | [React 19](https://react.dev/) + TypeScript | [React docs](https://react.dev/learn) · [TS handbook](https://www.typescriptlang.org/docs/handbook/intro.html) |
| Styling | [Tailwind CSS v4](https://tailwindcss.com/) | [Docs](https://tailwindcss.com/docs) |
| Component foundation | [shadcn/ui](https://ui.shadcn.com/) | [Docs](https://ui.shadcn.com/docs) |
| Chat components | [prompt-kit](https://www.prompt-kit.com/) | [Docs](https://www.prompt-kit.com/docs) |
| Testing | [Vitest](https://vitest.dev/) | [Docs](https://vitest.dev/guide/) |
| Production server | [Caddy](https://caddyserver.com/) (in Docker) | [Docs](https://caddyserver.com/docs/) |

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

The whole app is one screen: [src/screens/ConversationScreen.tsx](./src/screens/ConversationScreen.tsx). There is no router, no sidebar navigation, and no multi-page structure.

Where things live:

- **UI and conversation logic** `src/screens/ConversationScreen.tsx`
- **Component primitives** `src/components/ui/` (shadcn/ui + prompt-kit, generated)
- **Orchestrator contract** `src/services/orchestratorService.ts` exports `sendMessage(message: string): Promise<OrchestratorResponse>`. This is the only file that changes when the real Python AI layer replaces the mock.
- **Mock responses** `src/mock/orchestrator.json` controls what the mock says and how many clarification turns there are.
- **Shared types** `src/types/index.ts`
- **Design tokens** `src/index.css` (`--bg`, `--surface`, `--accent`, etc., mapped onto shadcn's CSS variable names)

The `@/` import alias points at `src/` (configured in `vite.config.ts` and `tsconfig.app.json`).

Chat history is stored in `localStorage` under the key `mddoai-conversation`, so a page refresh resumes the same conversation. The mock orchestrator's turn counter lives in memory only and resets on reload.

### Adding components

```bash
# shadcn/ui
npx shadcn@latest add <component>

# prompt-kit
npx shadcn@latest add "https://prompt-kit.com/c/<component>.json"
```

Both write into `src/components/ui/`.

## Test

```bash
npm run test         # one-shot run
npm run test:watch   # watch mode
```

[orchestratorService.test.ts](./src/services/orchestratorService.test.ts) covers the 3-turn clarification flow and post-completion clamping.

## Lint and typecheck

```bash
npm run lint
npx tsc -b
```

## Build

```bash
npm run build      # outputs to dist/
npm run preview    # serve the production build locally
```

## Docker

```bash
docker compose up --build
```

Served at [http://localhost:8080](http://localhost:8080). Stop with `docker compose down`.

## Project structure

```
chat-ui/
  src/
    components/ui/        shadcn/ui + prompt-kit components (generated)
    screens/
      ConversationScreen.tsx   the entire app
    mock/
      orchestrator.json        mock turn-by-turn responses
    services/
      orchestratorService.ts   sendMessage() — swap point for the real API
    types/
      index.ts                 Message, OrchestratorResponse
    App.tsx
    main.tsx
    index.css                  design tokens and theme
  Dockerfile
  docker-compose.yml
  Caddyfile
```
