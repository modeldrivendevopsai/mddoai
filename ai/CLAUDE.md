# CLAUDE.md — ai/

All AI-related work for MDDOAI (Model-Driven DevOps AI) lives under this folder, separate from the Java/Eclipse engine at the repo root.

## Folder boundaries

- Work on `chat-ui/` (the React frontend) only touches files inside `ai/chat-ui/`.
- Work on `ai-layer/` (the FastAPI backend) only touches files inside `ai/ai-layer/`.
- Neither touches the Java/Eclipse code at the repo root.
- Shared infrastructure that spans both services (the Caddy gateway config, the combined `docker-compose.yml`) lives directly in `ai/`, not nested inside either service.

See [ai/README.md](./README.md) for how the services fit together and how to run the full stack. See each service's own `CLAUDE.md`/`README.md` for service-specific conventions (`chat-ui/CLAUDE.md` has the frontend's design system and behavior spec; `ai-layer/README.md` has the backend's API and provider setup).

## Design System Skill

When building or updating **user interfaces, components, or visual prototypes**, use the [MDDOAI Design System skill](/mddoai-design-system/project/SKILL.md).

**Invoke with**: `/mddoai-design`

**Use when**:
- Building React components that need MDDOAI branding
- Creating prototypes, mockups, or throwaway visual assets
- Implementing production UI that follows brand guidelines
- Designing pages, layouts, or interactive surfaces
- Working with MDDOAI colors, typography, spacing, or assets

**What it provides**:
- Official brand colors, typography, and spacing tokens
- Reusable React primitives (Button, Icon, Badge, Card, Input, Tabs, CodeBlock, etc.)
- Ready-to-use UI kits for docs and marketing surfaces
- Asset library (logos, fonts, icons)
- Styling guidance and component examples

**Key brand rules**:
- Brand hue: violet `#684aeb` → `#a45eed` (use gradient sparingly)
- Type: Space Grotesk (display), IBM Plex Sans (body), IBM Plex Mono (code)
- Voice: second-person imperative, sentence case, **MDDOAI** always capitals, no emoji
- Commands are first-class content — show in dark terminal CodeBlock surface
- Semantic colors map to CI states: green=passed, amber=running, red=failed, blue=info

## Working with the Skill

The skill is self-guided:
1. Invoke `/mddoai-design`
2. Describe what you want to build or design
3. The skill will generate HTML artifacts for prototypes or guide production code implementation
4. For production: copy assets and follow brand rules
5. For prototypes: use static HTML files for review

See [mddoai-design-system/project/README.md](/mddoai-design-system/project/README.md) for full component documentation, color specs, and usage examples.
