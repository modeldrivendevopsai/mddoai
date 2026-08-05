# chat-ui

The UI for MDDOAI. A router-based SPA with a persistent shell (sidebar + top bar) around two screens: a pipeline dashboard and a landing page. See [CLAUDE.md](./CLAUDE.md) for the full product and design spec.

## Stack

| Purpose | Tool | Docs |
|---|---|---|
| Build tool / dev server | [Vite](https://vite.dev/) | [Guide](https://vite.dev/guide/) |
| UI framework | [React 19](https://react.dev/) + TypeScript | [React docs](https://react.dev/learn) · [TS handbook](https://www.typescriptlang.org/docs/handbook/intro.html) |
| Routing | [react-router-dom](https://reactrouter.com/) | [Docs](https://reactrouter.com/en/main) |
| Styling | [Tailwind CSS v4](https://tailwindcss.com/) | [Docs](https://tailwindcss.com/docs) |
| Component foundation | [shadcn/ui](https://ui.shadcn.com/) | [Docs](https://ui.shadcn.com/docs) |
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

Routes live in `App.tsx`, each wrapped in the shared `AppShell` (sidebar + top bar):

- **`/`** — `src/screens/OrchestratorScreen.tsx`, the pipeline dashboard. This is also the one real "add/update a platform" flow: its docs stage is a real crawl.
- **`/start`** — `src/screens/StartScreen.tsx`, the landing page.

Where things live:

- **Pipeline dashboard** `src/screens/OrchestratorScreen.tsx`, with its supporting components under `src/components/orchestrator/` (`Stepper`, `ChatColumn`, `StageOutputPanel`, `PlatformForm`, `CodeBlock`, `Button`, `FormField`), its own real API client `src/services/orchestratorPipelineService.ts`, polling hook `src/hooks/usePipeline.ts`, and REST-contract types `src/orchestrator/types.ts`.
- **Landing page** `src/screens/StartScreen.tsx`, sourced from `src/config/startOptions.config.ts`, with mock data from `src/services/platforms.service.ts`.
- **Shell** `src/layout/` (`AppShell`, `Sidebar`, `SidebarActions`, `SessionsList`, `TopBar`), sourced from `src/config/sidebar.config.ts` and `src/services/sessions.service.ts`.
- **Component primitives** `src/design-system/` (this app's own port of the real MDDOAI Design System — tokens, Button, Panel, Tabs, StatusPill, Icon) and `src/components/ui/` (shadcn/ui, generated).
- **Backend contract** All real network calls go through `src/services/orchestratorPipelineService.ts`, which only ever talks to `ai/orchestrator` (never the Java backend, `ai-layer`, or `ai/retrieval` directly — `ai/orchestrator` itself is the only thing that talks to those, see `ai/README.md`).

The `@/` import alias points at `src/` (configured in `vite.config.ts` and `tsconfig.app.json`).

### Adding components

```bash
npx shadcn@latest add <component>
```

Writes into `src/components/ui/`.

## Test

```bash
npm run test         # one-shot run
npm run test:watch   # watch mode
```

`src/services/orchestratorPipelineService.test.ts` covers the real API client's request/response contract with `fetch` mocked — see the file for current cases.

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

`docker compose up --build` from `ai/` runs this as a hot-reloading dev server (`npm run dev -- --host 0.0.0.0`, source volume-mounted in), published at `http://localhost:5173`, proxying `/orchestrator-api/*` to `ai/orchestrator` by its Docker Compose service name. Not the `Dockerfile` in this folder, that one builds static assets only (`npm run build`, no server) and is meant for an actual deployment target later, not local dev. See `ai/README.md` for the full compose setup.

**After adding a new npm dependency**, a plain `docker compose up --build` isn't enough: Compose reuses the container's anonymous `node_modules` volume (`docker-compose.yml`'s `/app/node_modules` entry) across recreation by default, so the new package won't actually be there even though the image rebuilt with it. Force a fresh volume with:

```bash
docker compose up -d --build --renew-anon-volumes chat-ui
```

Symptom if you skip this: Vite's overlay reports `Failed to resolve import "..."` for the package you just added, even though `npm ci` clearly succeeded during the image build.

**If an edit to a source file doesn't show up in the browser** (no `[vite] hmr update` line in `docker compose logs chat-ui`, page keeps serving the old version even after a hard refresh): Docker Desktop on Windows doesn't always forward native filesystem change events through the bind mount into the Linux container, so Vite's watcher silently misses the edit. `vite.config.ts` enables polling (`server.watch.usePolling`) whenever `CHOKIDAR_USEPOLLING=true` is set, which `docker-compose.yml` sets for exactly this reason — if you still hit this, confirm that env var made it into the running container (`docker compose exec chat-ui printenv CHOKIDAR_USEPOLLING`).

## Project structure

Everything lives under `src/`: `screens/` holds the app's screens, `layout/` holds the persistent shell, `design-system/` holds this app's shared component/token primitives, `components/orchestrator/` and `components/ui/` hold screen-specific and generated component sets, `services/` holds the files that call the backend or provide mock data, `hooks/` holds `usePipeline`, and `orchestrator/types.ts` holds the shared REST-contract types. See `ai/README.md` for how the full stack fits together.
