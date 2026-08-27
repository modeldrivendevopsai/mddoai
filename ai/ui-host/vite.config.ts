import path from 'node:path'
import { setDefaultResultOrder } from 'node:dns'
import { defineConfig } from 'vitest/config'
import react from '@vitejs/plugin-react'
import { federation } from '@module-federation/vite'

// Node 18+ defaults DNS lookups to 'verbatim' order, which can return an AAAA
// (IPv6) result first for a Docker Compose service name that only has an A
// record, making the dev-server proxy fail fast instead of falling back to
// IPv4. Force IPv4-first so proxying to Docker service names is reliable.
setDefaultResultOrder('ipv4first')

// A remote's URL here has to be one the *browser* can resolve, unlike
// VITE_ORCHESTRATOR_PROXY_TARGET below (a Docker Compose service name the
// dev *server* resolves for its own server-side proxy). Module Federation
// works the other way around: the browser itself fetches each remote's
// remoteEntry.js directly against that remote's own origin, after this
// page has already loaded, so every remote needs a real published
// localhost:xxxx port, matching docker-compose.yml's own ports: entries.
function remote(envVar: string, defaultPort: number, name: string) {
  return {
    type: 'module' as const,
    name,
    entry: `${process.env[envVar] || `http://localhost:${defaultPort}`}/remoteEntry.js`,
  }
}

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
    watch: {
      // Docker Desktop on Windows doesn't reliably forward native filesystem
      // change events from a bind-mounted host directory into the Linux
      // container, so Vite's file watcher can silently stop picking up edits
      // (no "[vite] hmr update" log line, page just serves stale code).
      // Polling works regardless of how the mount forwards events; only
      // enabled via CHOKIDAR_USEPOLLING (set for the Docker Compose service in
      // docker-compose.yml), so `npm run dev` on the host keeps cheap native
      // watching.
      usePolling: process.env.CHOKIDAR_USEPOLLING === 'true',
      interval: 300,
    },
    proxy: {
      '/orchestrator-api': {
        // docker-compose.yml sets this to orchestrator:8001 (Docker service
        // name); the fallback here is for running npm run dev outside
        // Docker. ai/orchestrator is internal-only (expose, not ports, in
        // docker-compose.yml) — every real call ui-host makes goes through
        // this one proxy now, orchestrator itself is the only thing that
        // talks to ai-layer/retrieval (see ai/README.md).
        target: process.env.VITE_ORCHESTRATOR_PROXY_TARGET || 'http://localhost:8001',
        rewrite: (path) => path.replace(/^\/orchestrator-api/, ''),
      },
    },
  },
  test: {
    environment: 'node',
  },
})
