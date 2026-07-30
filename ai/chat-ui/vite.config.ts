import path from 'node:path'
import { defineConfig } from 'vitest/config'
import react from '@vitejs/plugin-react'
import tailwindcss from '@tailwindcss/vite'

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
      // Docker Desktop's bind mount (docker-compose.yml's ./chat-ui:/app)
      // doesn't reliably deliver native filesystem change events from a
      // Windows host into the Linux container, so Vite's default watcher
      // (chokidar's native fs.watch) can silently never fire, no HMR update
      // ever reaches the browser even though the file on disk is current.
      // Polling works around that at the cost of a bit of CPU.
      usePolling: true,
      interval: 300,
    },
    proxy: {
      '/api': {
        // docker-compose.yml sets this to ai-layer:8000 (Docker service name);
        // the fallback here is for running npm run dev outside Docker.
        target: process.env.VITE_API_PROXY_TARGET || 'http://localhost:8000',
        rewrite: (path) => path.replace(/^\/api/, ''),
      },
      '/orchestrator-api': {
        // ai/orchestrator is internal-only (expose, not ports, in
        // docker-compose.yml), same reasoning as /api above.
        target: process.env.VITE_ORCHESTRATOR_PROXY_TARGET || 'http://localhost:8001',
        rewrite: (path) => path.replace(/^\/orchestrator-api/, ''),
      },
    },
  },
  test: {
    environment: 'node',
  },
})
