# CLAUDE.md — repo root

## What This Project Is

MDDOAI (Model-Driven DevOps AI) generates CI/CD pipeline configs from software architecture models, without requiring MDE expertise, via two tracks:

- **The MDE engine** (repo root): a Java/Eclipse EMF/ATL/Acceleo transformation chain, `SWArch → PIM → PSM → YAML`. See [README.md](README.md) for build/usage.
- **The AI product** (`ai/`): a chat-based agent system built on top of the same chain. See [ai/README.md](ai/README.md) and [ai/CLAUDE.md](ai/CLAUDE.md).

## Repo Structure

- `main/`, `meta_models/`, `code_generation/`, `designs/`, `feature/`, `update_site/` — the Java/Eclipse MDE engine and its transformation artifacts.
- `ai/` — the chat-ui + ai-layer product, isolated from the Java engine. See `ai/CLAUDE.md` for folder boundaries.
- `mddoai-design-system/` — the on-brand component library and Claude Design skill (`/mddoai-design`). Read its `SKILL.md` before doing UI work.
- `docs/` — misc project docs.

## Agents in `.github/agents/`

- `pr-logic-reviewer` — review a PR's actual logic/diff (`pr=<number>`)
- `pr-description-generator` — write a PR description from the current branch's diff against main
- `coverage-reviewer` — run the Gradle suite, report JaCoCo coverage gaps by class
- `lint-reviewer` — Java formatting/lint issues (naming, method length, magic numbers, nesting)
- `oop-reviewer` — Java OOP design quality (SOLID, code smells, encapsulation)
- `test-fixtures-updater` — after a transformation change, re-run swarch2pim/input1.swarch and update expected + downstream fixtures

## Git Workflow — read before pushing or merging

- **Never force-push `main`, full stop.**
- **Never rebase or force-push any other branch that's already been pushed to origin.** Check first with `git ls-remote --heads origin <branch>`, if that returns nothing, the branch is local-only and rebasing is safe. If it returns a ref, merge instead. This project has already had one real incident where a force-push wiped pushed commits before a stripped-down version got merged into `main`, recovering required a separate revert PR. Don't repeat it.
- **Merge `main` into your feature branch, not the other way around.** Never merge a feature branch directly into `main` outside of a reviewed PR.
- **Keep commit messages short.** One line, no large multi-paragraph bodies.
- **Run `git status` before any destructive command** (`checkout --`, `restore`, `reset --hard`, `clean`) on a path that might have uncommitted work.
- **Confirm before merging PRs**, even on your own branches, unless explicitly told to proceed autonomously.
