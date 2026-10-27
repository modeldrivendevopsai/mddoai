import path from 'node:path'
import { defineConfig } from 'vitest/config'
import react from '@vitejs/plugin-react'
import { federation } from '@module-federation/vite'

// Where the *browser* loads each remote's remoteEntry.js from, at runtime,
// after this page has loaded. Read when the app is built: docker-compose.yml
// passes a path on this app's own origin (/remotes/<name>), which ui-host's
// proxy forwards to that remote's container. The localhost:<port> default is
// each remote's own `npm run dev` server, for running outside Docker.
function remote(envVar: string, defaultPort: number, name: string) {
  return {
    type: 'module' as const,
    name,
    entry: `${process.env[envVar] || `http://localhost:${defaultPort}`}/remoteEntry.js`,
  }
}

// The pipeline generator's write endpoints the "Generate a CI/CD pipeline"
// mode calls. Any other write through the dev server's proxy gets a 404.
// ui-host's nginx configuration applies the same rule in Docker.
const GENERATOR_WRITES_FROM_BROWSER = new Set([
  '/generator-api/inspect',
  '/generator-api/generate',
  '/generator-api/validate',
])

// https://vite.dev/config/
export default defineConfig({
  plugins: [
    react(),
    federation({
      name: 'uiHost',
      remotes: {
        uiRemoteChat: remote('VITE_REMOTE_CHAT_URL', 5174, 'uiRemoteChat'),
        uiRemoteStepper: remote('VITE_REMOTE_STEPPER_URL', 5175, 'uiRemoteStepper'),
        uiRemoteStageDocs: remote('VITE_REMOTE_STAGE_DOCS_URL', 5176, 'uiRemoteStageDocs'),
        uiRemoteStageSerialization: remote('VITE_REMOTE_STAGE_SERIALIZATION_URL', 5177, 'uiRemoteStageSerialization'),
        uiRemoteStagePim: remote('VITE_REMOTE_STAGE_PIM_URL', 5178, 'uiRemoteStagePim'),
        uiRemoteStagePsm: remote('VITE_REMOTE_STAGE_PSM_URL', 5179, 'uiRemoteStagePsm'),
        uiRemoteStageAtl: remote('VITE_REMOTE_STAGE_ATL_URL', 5180, 'uiRemoteStageAtl'),
        uiRemoteStageAcceleo: remote('VITE_REMOTE_STAGE_ACCELEO_URL', 5181, 'uiRemoteStageAcceleo'),
        uiRemoteStageGeneration: remote('VITE_REMOTE_STAGE_GENERATION_URL', 5182, 'uiRemoteStageGeneration'),
      },
      shared: ['react', 'react-dom'],
      // Manual ambient .d.ts declarations (src/federated/remotes.d.ts) are
      // the type-sharing approach here, not this plugin's own dts
      // consumption — which also failed in this environment for every
      // remote ("Failed to download types archive... @mf-types.zip", since
      // each remote's own dts generation is disabled too, see each
      // ui-remote-*/vite.config.ts's own comment) and is non-fatal but
      // noisy without this.
      dts: false,
    }),
  ],
  build: {
    // Required by @module-federation/vite's own federated-build format —
    // see https://module-federation.io/integrations/build-tool/vite.html.
    target: 'esnext',
    modulePreload: false,
  },
  resolve: {
    alias: {
      '@': path.resolve(__dirname, './src'),
    },
    // design-system is consumed via a local "file:" dependency (a symlink),
    // and npm installs its own copy of react into design-system/node_modules
    // to satisfy its peerDependency, separate from this copy. Without
    // dedupe, the app ends up running two react module instances at once,
    // which breaks hooks (e.g. lucide-react's useContext call resolving
    // against the wrong instance) with "Cannot read properties of null
    // (reading 'useContext')". See https://vite.dev/config/shared-options.html#resolve-dedupe.
    dedupe: ['react', 'react-dom'],
  },
  server: {
    fs: {
      // design-system is a sibling directory of this project (see
      // resolve.dedupe's own comment above for the same file:-dependency
      // layout), not nested under this project's own node_modules - Vite's
      // default filesystem allow-list only covers this project's own root,
      // so serving a file that only exists under design-system's own
      // node_modules (its @fontsource-variable font files, imported by
      // design-system/src/tokens.css) 403s without this. Resolved by name
      // relative to this file, not hardcoded to one checkout's absolute
      // layout.
      allow: [path.resolve(__dirname), path.resolve(__dirname, '..', 'design-system')],
    },
    proxy: {
      '/orchestrator-api': {
        // For `npm run dev` outside Docker; in Docker, ui-host's nginx
        // configuration proxies instead. The platform-integration mode's
        // calls go through this proxy; orchestrator itself is what talks to
        // ai-layer/retrieval (see ai/README.md).
        target: process.env.VITE_ORCHESTRATOR_PROXY_TARGET || 'http://localhost:8001',
        rewrite: (path) => path.replace(/^\/orchestrator-api/, ''),
      },
      // The "Generate a CI/CD pipeline" mode's own backend (ai/pipeline_generator),
      // a separate service from orchestrator: that mode has no pipeline run,
      // chat, or stages, so it isn't reached through orchestrator.
      '/generator-api': {
        target: process.env.VITE_GENERATOR_PROXY_TARGET || 'http://localhost:8100',
        rewrite: (path) => path.replace(/^\/generator-api/, ''),
        // Reads pass through; a write only when it is one this mode makes.
        // Publishing a platform (POST /platforms) stores code that is run on
        // every later generation, so only integration_runner may do it, from
        // inside the Compose network, for a run whose stages were approved.
        // Matched on the raw path, so an encoded spelling is refused too.
        bypass: (req) => {
          if (req.method === 'GET' || req.method === 'HEAD') return undefined
          const path = (req.url ?? '').split('?')[0]
          return GENERATOR_WRITES_FROM_BROWSER.has(path) ? undefined : false
        },
      },
    },
  },
  test: {
    environment: 'node',
  },
})
