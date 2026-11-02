# ui-host

The UI for MDDOAI. A router-based SPA with a persistent shell (sidebar + top bar) around two
screens: a landing page and a platform-integration dashboard. See [CLAUDE.md](./CLAUDE.md) for
the full product and design spec.

## Stack

| Purpose | Tool | Docs |
|---|---|---|
| Build tool / dev server | [Vite](https://vite.dev/) | [Guide](https://vite.dev/guide/) |
| UI framework | [React 19](https://react.dev/) + TypeScript | [React docs](https://react.dev/learn) · [TS handbook](https://www.typescriptlang.org/docs/handbook/intro.html) |
| Routing | [react-router-dom](https://reactrouter.com/) | [Docs](https://reactrouter.com/en/main) |
| Styling | Inline `style={{ }}` against the `design-system` package's `tokens.css`, plus small per-component `.css` files for the rules inline styles can't express | see [CLAUDE.md](./CLAUDE.md)'s Design System section |
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

- **`/`** — `src/screens/StartScreen.tsx`, the landing page.
- **`/integration`** — `src/screens/IntegrationScreen.tsx`, the platform-integration dashboard.
  This is the one real "add/update a platform" flow: its docs stage is a real crawl.

Where things live:

- **Platform integration dashboard** `src/screens/IntegrationScreen.tsx`, composing the chat
  column, the stepper, and the current pipeline stage's own panel — each its own Module
  Federation remote (`ai/ui-remote-chat`, `ai/ui-remote-stepper`, `ai/ui-remote-stage-*`, loaded
  via `src/features/integration/stages/registry.ts` for the stage panels; see `vite.config.ts`'s
  `federation()` config for the current, exact remotes list), not local source under this app's
  own `src/`. Backed by its own real API client `src/services/orchestrator.service.ts` and polling
  hook `src/hooks/useIntegration.ts`.
- **Landing page** `src/screens/StartScreen.tsx`, sourced from `src/config/startOptions.config.ts`,
  with mock data from `src/services/platforms.service.ts`.
- **Shell** `src/layout/` (`AppShell`, `Sidebar`, `SidebarActions`, `SessionsList`, `TopBar`),
  sourced from `src/config/sidebar.config.ts` and `src/services/sessions.service.ts`.
- **Component primitives** the sibling `design-system` package (`ai/design-system`, not a folder
  under this app's own `src/`), a real local npm dependency, not a copy — see its own README.
- **REST-contract types** the sibling `orchestrator-types` package (`ai/orchestrator-types`), the
  same kind of real local npm dependency as `design-system` — see its own README.
- **Backend contract** All real network calls go through `src/services/orchestrator.service.ts`,
  which only ever talks to `ai/orchestrator` (never the Java backend, `ai-layer`, or
  `ai/retrieval` directly, `ai/orchestrator` itself is the only thing that talks to those, see
  `ai/README.md`).

The `@/` import alias points at `src/` (configured in `vite.config.ts` and `tsconfig.app.json`) —
`design-system` and `orchestrator-types` aren't under `src/` any more, so they're imported by their
bare package names instead.

## Test

```bash
npm run test         # one-shot run
npm run test:watch   # watch mode
```

`src/services/orchestrator.service.test.ts` covers the real API client's request/response
contract with `fetch` mocked, see the file for current cases.

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

`docker compose up --build` in an environment's folder (see `infra/README.md`) builds this app into static files (`npm run build`) and
serves them with nginx, published on the environment's `UI_PORT`, on this machine only. The same
server is the one origin the browser uses for everything else (`nginx/default.conf.template`):

- `/remotes/<name>/` is forwarded to the `ui-remote-<name>` container. Only the remotes listed
  there are forwarded.
- `/orchestrator-api/*` is forwarded to `ai/orchestrator`.

The backend is named by its Compose service name through an environment variable
(`ORCHESTRATOR_UPSTREAM`), filled into the configuration when the container starts. Names are
resolved per request, so this starts even while the backend or a remote is not up yet. See
`ai/README.md` for the full compose setup.

Build context is `ai/`, not this folder alone, see this package's own `CLAUDE.md`'s Docker
section for why (`package.json` depends on the sibling `design-system` and `orchestrator-types`
packages via a local `file:` path).

**A change to the code** here, or in `design-system` or `orchestrator-types`, takes a rebuild:
`docker compose up -d --build ui-host`. There is no hot reloading in Docker. A change to a shared
package also needs a rebuild of every `ui-remote-*` that uses it (`docker compose up -d --build`).

This is also the Module Federation host: it loads the chat column, the stepper, and each pipeline
stage panel at runtime from their own `ui-remote-*` containers (see `ai/README.md`'s services
list). The browser fetches each remote's `remoteEntry.js` from `/remotes/<name>/` on this same
origin. Where each remote lives is set at build time: `docker-compose.yml` passes each
`VITE_REMOTE_*_URL` as a build argument. If a remote's container isn't running, its piece of the
screen shows a small "couldn't load" fallback instead of the real panel/chat/stepper. Everything
else on the page keeps working; see this package's own `CLAUDE.md` for why (`RemoteBoundary`).

## Project structure

The app's code lives under `src/` (and the Docker proxy's configuration under `nginx/`): `screens/` holds the app's screens, `layout/` holds the
persistent shell, `features/integration/` holds the cross-stage plumbing that stays local to this
host (see CLAUDE.md's Design System section for the full breakdown), `federated/` holds the
ambient TypeScript declarations for every federated import, `services/` holds the files that call
the backend or provide mock data, and `hooks/` holds `useIntegration`. Shared component/token
primitives live in the sibling `design-system` package (`ai/design-system`); the shared
REST-contract types live in the sibling `orchestrator-types` package (`ai/orchestrator-types`); the
chat column, the stepper, and every pipeline stage panel live in their own `ai/ui-remote-*`
packages — none of them under this app's own `src/`.
See `ai/README.md` for how the full stack fits together.
