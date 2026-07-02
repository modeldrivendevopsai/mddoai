# CLAUDE.md — ai/

All AI-related work lives under `mddoai/ai/`, separate from the existing Java/Eclipse codebase at the repo root.

## Folder boundaries

- Work on `chat-ui/` (the React frontend) only touches files inside `ai/chat-ui/`.
- Work on `ai-layer/` (the FastAPI backend) only touches files inside `ai/ai-layer/`.
- Neither touches the Java/Eclipse code at the repo root.
- Shared infrastructure that spans both services (the Caddy gateway config, the combined `docker-compose.yml`) lives directly in `ai/`, not nested inside either service.

See [ai/README.md](./README.md) for how the services fit together and how to run the full stack. See each service's own `CLAUDE.md`/`README.md` for service-specific conventions (`chat-ui/CLAUDE.md` has the frontend's design system and behavior spec; `ai-layer/README.md` has the backend's API and provider setup).
