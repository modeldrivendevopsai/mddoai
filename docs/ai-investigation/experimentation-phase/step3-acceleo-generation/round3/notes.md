# Round 3 — Notes (fill in after run)

## Run date
<!-- fill in -->

## Context given
- `context/gitlabMM.ecore`
- `context/gha_generate.mtl`
- `context/gitlab-ci-docs.md`
- All Round 2 constraints retained
- Added: `def` named explicitly as forbidden parameter name
- Added: `[comment @main/]` must be first line inside template body
- Added: variables must be serialized inline
## Bugs to watch for
- `def` as parameter name (must stay fixed now it's named explicitly)
- `[comment @main/]` outside template body
- Variables value on separate line
- Any remaining indentation issues
