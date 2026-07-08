# chat-ui

Chat interface for MDDOAI. Users describe an integration goal; the orchestrator walks them through clarification turns and returns a result.

See [CLAUDE.md](./CLAUDE.md) for the full product and design spec.

## Stack

| Purpose | Tool | Docs |
|---|---|---|
| Build tool / dev server | [Vite](https://vite.dev/) | [Guide](https://vite.dev/guide/) |
| UI framework | [React 19](https://react.dev/) + TypeScript | [React docs](https://react.dev/learn) · [TS handbook](https://www.typescriptlang.org/docs/handbook/intro.html) |
| Styling | [Tailwind CSS v4](https://tailwindcss.com/) | [Docs](https://tailwindcss.com/docs) |
| Component foundation | [shadcn/ui](https://ui.shadcn.com/) | [Docs](https://ui.shadcn.com/docs) |
| Chat components | [prompt-kit](https://www.prompt-kit.com/) | [Docs](https://www.prompt-kit.com/docs) |
| Testing | [Vitest](https://vitest.dev/) | [Docs](https://vitest.dev/guide/) |
| Reverse proxy / static server | [Caddy](https://caddyserver.com/) (separate gateway service, see `ai/README.md`) | [Docs](https://caddyserver.com/docs/) |

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

The whole app is one screen: [src/screens/ConversationScreen.tsx](./src/screens/ConversationScreen.tsx). There is no router or multi-page structure.

Where things live:

- **UI and conversation logic** `src/screens/ConversationScreen.tsx`
- **Component primitives** `src/components/ui/` (shadcn/ui + prompt-kit, generated)
- **Backend contract** `src/services/orchestratorService.ts` is the only file that talks to the network — see it and `src/types/index.ts` for the current request/response shape.
- **Design tokens** `src/index.css` (`--bg`, `--surface`, `--accent`, etc., mapped onto shadcn's CSS variable names)

The `@/` import alias points at `src/` (configured in `vite.config.ts` and `tsconfig.app.json`).

Chat history is stored in `localStorage` under the key `mddoai-conversation`, so a page refresh resumes the same conversation.

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

[orchestratorService.test.ts](./src/services/orchestratorService.test.ts) covers the request/response contract with `fetch` mocked — see the file for current cases.

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

chat-ui's Dockerfile only builds the static assets — a shared `caddy` gateway service (`ai/Caddyfile`) serves them and proxies `/api/*` to `ai-layer`. Run the whole stack from `ai/`:

```bash
cd ..
docker compose up --build
```

Served at [http://localhost:8080](http://localhost:8080). Stop with `docker compose down`.

## Project structure

Everything lives under `src/`: `screens/ConversationScreen.tsx` is the whole app, `services/orchestratorService.ts` is the only file that calls the backend, `components/ui/` holds generated shadcn/ui + prompt-kit primitives, and `types/index.ts` has the shared types. The `Dockerfile` here only builds the static assets — see `ai/README.md` for how the full stack fits together.
