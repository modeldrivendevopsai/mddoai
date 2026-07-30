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

There is no router or multi-page structure; screens live under `src/screens/`, currently just [ConversationScreen.tsx](./src/screens/ConversationScreen.tsx).

Where things live:

- **UI and conversation logic** `src/screens/ConversationScreen.tsx`
- **Component primitives** `src/components/ui/` (shadcn/ui + prompt-kit, generated)
- **Backend contract** Network calls go through `src/services/` (currently `orchestratorService.ts`); see it and `src/types/index.ts` for the current request/response shape.
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

`docker compose up --build` from `ai/` runs this as a hot-reloading dev server (`npm run dev -- --host 0.0.0.0`, source volume-mounted in), published at `http://localhost:5173`, proxying `/api/*` to `ai-layer` by its Docker Compose service name. Not the `Dockerfile` in this folder, that one builds static assets only (`npm run build`, no server) and is meant for an actual deployment target later, not local dev. See `ai/README.md` for the full compose setup.

## Project structure

Everything lives under `src/`: `screens/` holds the app's screens, `services/` holds the files that call the backend, `components/ui/` holds generated shadcn/ui + prompt-kit primitives, and `types/` holds the shared types. See `ai/README.md` for how the full stack fits together.
