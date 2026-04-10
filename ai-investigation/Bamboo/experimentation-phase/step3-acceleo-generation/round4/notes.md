# Round 4 — Notes (fill in after run)

## Run date
<!-- fill in -->

## Context given
- `context/gitlabMM.ecore`
- `context/gha_generate.mtl`
- `context/gitlab-ci-docs.md`
- All Round 3 constraints retained
- Added: EMF enum `toString()` returns literal value (lowercase), not enum name

## Bugs to watch for
- `pull_policy: always` on every image (enum literal comparison)
- `when: on_success` on every job (enum literal comparison)
- `def` as parameter name (should stay fixed)
- `[comment @main/]` outside template body (should stay fixed)
