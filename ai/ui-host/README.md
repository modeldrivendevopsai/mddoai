# ui-host

The UI for MDDOAI. A router-based SPA with a persistent shell (sidebar + top bar) around the app's
screens. It has two modes: adding or updating a CI/CD platform, and generating a CI/CD pipeline.
See [CLAUDE.md](./CLAUDE.md) for the full product and design spec.

## Stack

| Purpose | Tool | Docs |
|---|---|---|
| Build tool / dev server | [Vite](https://vite.dev/) | [Guide](https://vite.dev/guide/) |
| UI framework | [React](https://react.dev/) + TypeScript | [React docs](https://react.dev/learn) · [TS handbook](https://www.typescriptlang.org/docs/handbook/intro.html) |
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

Routes live in `App.tsx`, each wrapped in the shared `AppShell` (sidebar + top bar), and
`src/screens/` holds one file per screen. The list of routes is not repeated here, so it cannot go
stale. The app has two modes, and each opens on a list of what already exists: the platforms
already supported, or the pipelines already generated. The start screen offers both. Behind the
platforms list is the platform-integration dashboard (the "add/update a platform" flow, whose docs
stage is a real crawl). Behind the pipelines list is the screen that loads a Software Architecture
file, chooses platforms, generates, validates and downloads.

Where things live:

- **Platform integration dashboard**: its screen is in `src/screens/`. It composes the chat column,
  the stepper and the current stage's own panel. Each of those is its own Module Federation remote
  (an `ai/ui-remote-*` package), not local source under this app's own `src/`. The stage panels
  are loaded through the registry in `src/features/integration/`, and the `federation()` config in
  `vite.config.ts` holds the remotes map. The screen is backed by its own API client in
  `src/services/` and a polling hook in `src/hooks/`.
- **Generate-a-pipeline screens**: in `src/screens/`, with their components under
  `src/features/generate/`, their state hooks next to them and their copy in `src/config/`. They
  are backed by their own API client in `src/services/`, which talks to `ai/pipeline_generator`
  (not `ai/orchestrator`).
- **Start screen and platforms**: components under `src/features/start/` and
  `src/features/platforms/`, copy in `src/config/`. The start screen draws the two modes as a
  cycle, and the list under it follows the sidebar's tab: the latest pipelines on Pipelines, the
  supported platforms on Platforms. That platforms list is shared with the platforms screen and
  uses mock data from the platforms service in `src/services/`.
- **Shell**: `src/layout/` holds the sidebar, its lists and the top bar. They are configured from
  `src/config/` and fed by the clients in `src/services/`: the Platforms tab lists integration
  runs, and the Pipelines tab lists the pipelines already generated. The sidebar's active tab is
  shared state held above the routes, so a screen can follow it.
- **Component primitives**: the sibling `design-system` package (`ai/design-system`, not a folder
  under this app's own `src/`), a real local npm dependency, not a copy. See its own README.
- **REST-contract types**: the sibling `orchestrator-types` package (`ai/orchestrator-types`), the
  same kind of local npm dependency as `design-system`. See its own README.
- **Backend contract**: the platform-integration mode's network calls go through its orchestrator
  client in `src/services/`, which talks to `ai/orchestrator`. ui-host never calls the Java
  backend, `ai-layer` or `ai/retrieval` directly. `ai/orchestrator` is what talks to those (see
  `ai/README.md`). The generate mode's calls go through its own generator client, which talks to
  `ai/pipeline_generator`.

The `@/` import alias points at `src/` (configured in `vite.config.ts` and `tsconfig.app.json`).
`design-system` and `orchestrator-types` are not under `src/`, so they are imported by their bare
package names instead.

## Test

```bash
npm run test         # one-shot run
npm run test:watch   # watch mode
```

Tests sit next to the code they cover, as `*.test.ts` and `*.test.tsx` files. The API clients'
tests mock `fetch` and check each request and response contract.

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

`docker compose up --build` in an environment's folder (see `infra/README.md`) builds this app
into static files (`npm run build`) and serves them with nginx, published on the environment's
`UI_PORT`, on this machine only. The same server is the origin the browser uses for everything
else. `nginx/default.conf.template` lists what it forwards:

- `/remotes/<name>/` goes to the matching `ui-remote-*` container. Only the remotes listed there
  are forwarded.
- Each backend API has its own prefix, which goes to that backend's internal-only service.

Each backend is named by its Compose service name through an `*_UPSTREAM` environment variable,
which is filled into the configuration when the container starts. Names are resolved per request,
so this starts even while a backend or a remote is not up yet. See `ai/README.md` for the full
compose setup.

The build context is `ai/`, not this folder alone. The Docker section of this package's own
`CLAUDE.md` says why: `package.json` depends on the sibling `design-system` and
`orchestrator-types` packages through a local `file:` path.

**A change to the code** here, or in `design-system` or `orchestrator-types`, takes a rebuild:
`docker compose up -d --build ui-host`. There is no hot reloading in Docker. A change to a shared
package also needs a rebuild of every `ui-remote-*` that uses it (`docker compose up -d --build`).

This is also the Module Federation host. It loads the chat column, the stepper and each pipeline
stage panel at runtime from their own `ui-remote-*` containers (see the services list in
`ai/README.md`). The browser fetches each remote's `remoteEntry.js` from `/remotes/<name>/` on this
same origin. Where each remote lives is set at build time: `docker-compose.yml` passes each
`VITE_REMOTE_*_URL` as a build argument. If a remote's container is not running, its piece of the
screen shows a small "couldn't load" fallback instead of the real panel, chat or stepper.
Everything else on the page keeps working. This package's own `CLAUDE.md` says why
(`RemoteBoundary`).

## Project structure

The app's code lives under `src/`, and the Docker proxy's configuration under `nginx/`. `src/` is
organised by role:

- `screens/` holds one file per screen.
- `layout/` holds the persistent shell.
- `features/<area>/` holds the components and hooks that belong to one area of the app.
- `config/` holds copy and settings as data.
- `services/` holds the API clients and the mock data.
- `hooks/` holds hooks that do not live inside a feature folder.
- `federated/` holds the remote boundary and the ambient TypeScript declarations for every
  federated import.

Shared component and token primitives live in the sibling `design-system` package
(`ai/design-system`). The shared REST-contract types live in the sibling `orchestrator-types`
package (`ai/orchestrator-types`). The chat column, the stepper and every pipeline stage panel
live in their own `ai/ui-remote-*` packages. None of them are under this app's own `src/`. See
`ai/README.md` for how the full stack fits together.
