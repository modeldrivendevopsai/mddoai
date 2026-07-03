# MDDOAI Project Guide

## Codebase Overview

MDDOAI (Model-Driven DevOps AI) is an enterprise platform for AI-driven infrastructure automation and deployment orchestration. The project includes core services, API routers, and a comprehensive design system.

- **Core**: TypeScript/Node.js backend services and CLI tooling
- **API**: Router and service wrapper patterns for scalable endpoint management
- **Design System**: MDDOAI brand system with React components, tokens, and UI kits

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
