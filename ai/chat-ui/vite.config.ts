import path from 'node:path'
import { setDefaultResultOrder } from 'node:dns'
import { defineConfig } from 'vitest/config'
import react from '@vitejs/plugin-react'
import tailwindcss from '@tailwindcss/vite'

// Node 18+ defaults DNS lookups to 'verbatim' order, which can return an AAAA
// (IPv6) result first for a Docker Compose service name that only has an A
// record, making the dev-server proxy fail fast instead of falling back to
// IPv4. Force IPv4-first so proxying to Docker service names is reliable.
setDefaultResultOrder('ipv4first')

// https://vite.dev/config/
export default defineConfig({
  plugins: [react(), tailwindcss()],
  resolve: {
    alias: {
      '@': path.resolve(__dirname, './src'),
    },
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
      '/api': {
        // docker-compose.yml sets this to ai-layer:8000 (Docker service name);
        // the fallback here is for running npm run dev outside Docker.
        target: process.env.VITE_API_PROXY_TARGET || 'http://localhost:8000',
        rewrite: (path) => path.replace(/^\/api/, ''),
      },
      '/retrieval-api': {
        // docker-compose.yml sets this to retrieval:8010 (Docker service name);
        // the fallback here is for running npm run dev outside Docker.
        target: process.env.VITE_RETRIEVAL_PROXY_TARGET || 'http://localhost:8010',
        rewrite: (path) => path.replace(/^\/retrieval-api/, ''),
      },
    },
  },
  test: {
    environment: 'node',
  },
})
