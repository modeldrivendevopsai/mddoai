---
name: PR Description Generator
description: "Generates a PR description from the current branch diff against main. Run before opening a PR."
tools: [execute, read]
user-invocable: true
---

## Critical Rules

- NEVER push, create, or modify any PR — description only
- NEVER modify source files
- If `git diff main...HEAD` returns nothing, stop and tell the user there are no changes against main

## Goal

Read the current branch diff against `main` and produce a ready-to-paste PR description
following this project's format. Run this before opening a PR on GitHub.

## File-to-Stage Map

Use this to describe changes in plain English, not just file names:

| Files changed | What to say |
|--------------|-------------|
| `transformations/swarch2pim/swarch2pim.atl` | swarch2pim transformation rule |
| `transformations/swarch2pim/swarch2pim.asm` | swarch2pim compiled ATL (auto-updated, mention only if .atl also changed) |
| `transformations/pim2psm/pim2gitlabmodel.atl` | pim2gitlab transformation rule |
| `code_generation/.../generate.mtl` | Acceleo code generation template |
| `meta_models/` | metamodel definition |
| `main/src/main/java/` | Java application code |
| `main/src/test/resources/testCases/` | test fixture files |
| `.github/agents/` | Copilot agent definitions |
| `.github/workflows/` | CI/CD pipeline |
| `README.md` or `docs/` | documentation |

## Procedure

### Step 1 — Get the Diff

```bash
git diff main...HEAD --stat
git diff main...HEAD
```

If the diff is empty, stop and report: "No changes detected against main."

Also get the branch name and recent commits for context:
```bash
git branch --show-current
git log main..HEAD --oneline
```

### Step 2 — Understand the Changes

Group the changed files by area using the map above. For each group:
- What files changed
- What those files do in the context of this project
- Whether the change looks like a bug fix, new feature, improvement, or refactor

Pay special attention to:
- If `.atl` files changed — describe which transformation stage and what the rule change does
- If `testCases/` files changed — note that fixtures were updated and briefly why
- If both `.atl` and `testCases/` changed together — they are likely related (transformation change + fixture refresh)
- If `.agent.md` files changed — describe which agents were added or updated

### Step 3 — Generate the Description

Follow the format used in this project's existing PRs:

```
## Summary

- **[Type]:** [What changed and why, in plain English using project domain terms]
- **[Type]:** [Next change]
- ...
```

Types to use: `Bug fix`, `New feature`, `Improvement`, `Refactor`, `Tests`, `Docs`, `CI`, `Agents`

Rules for the bullets:
- Use project domain language: ATL, Acceleo, PIM, PSM, swarch, gitlabmm, metamodel
- Be specific about which transformation stage was affected
- If test fixtures were updated, say "Updated test fixtures to reflect [change]" — do not list individual fixture files
- If agents were added or changed, describe what they do
- Keep each bullet to 1–2 sentences max

## Output Format

Paste-ready output only — no preamble, no explanation, just the description block:

---
## Summary

- **[Type]:** ...
- **[Type]:** ...
---

After the description, add one line:
> Branch: `<branch-name>` — `<N>` commit(s) against main
