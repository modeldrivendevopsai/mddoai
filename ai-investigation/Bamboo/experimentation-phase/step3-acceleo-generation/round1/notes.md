# Round 1 — Notes

## Run date
2026-04-11

## Context given
- `context/bambooMM.ecore`
- `context/gha_generate.mtl`
- `context/bamboo-docs.md`
- Constraints pre-applied from GitLab learnings: `def` reserved, `@main` inside body, enum `toString()` returns literal

---

## Errors found
YAML indentation bug — all `tasks` and `requirements` list items emitted at column 0 instead of indented under their parent key.

---

## Root cause
`generateTaskIndented` and `generateRequirement` both have `post(trim())` which strips all leading whitespace from the output, including the indent String passed as parameter. The indent parameter is therefore ineffective.

---

## Compilable / runs without error?
Yes — compiled and ran without errors. Output file generated.

---

## Key findings
- Pre-applied GitLab constraints effective: no `def` keyword issues, `@main` correctly placed, no enum toString issues
- YAML structure mostly correct (plan header, variables, stages, job names/keys)
- Bug: `post(trim())` on indented sub-templates removes leading spaces → invalid YAML
- Fix for Round 2: remove `post(trim())` from `generateTaskIndented` and `generateRequirement`, or ensure indent is applied after trim
