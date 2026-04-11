# Round 1 — Notes

## Run date
2026-04-11

## Context given
- `context/azuredevopsMM.ecore` (Step 1 Round 2 output)
- `context/gha_generate.mtl` (ACICDTrip GHA Acceleo reference)
- `context/azure-devops-docs.md` (Azure Pipelines YAML reference)
- All Acceleo constraints pre-loaded from GitLab and Bamboo experiments

---

## Errors found
1 compile error before running:
- `token "package" is invalid` at line 509 — `[pkg.package/]` uses `package` which
  is a reserved keyword in Acceleo/Java. Fixed by renaming the EAttribute in the
  metamodel from `package` to `packageName` and updating the template accordingly.
  This was a manual fix to the ecore and mtl, not a new round.

---

## Root cause
The LLM named an EAttribute `package` in the `PackageResource` class. `package` is a
reserved Java keyword and cannot be used as an accessor name in Acceleo templates.
Fixed directly in the ecore and template rather than creating a new round, since
it was a single attribute rename.

---

## Compilable / runs without error?
Yes — after the manual rename, clean compile, clean runtime on all three test cases.

---

## Key findings
- All 5 pre-loaded Acceleo constraints held — no def keyword, @main correct, no oclIsUndefined on enums, no post(trim()) on indented templates
- New issue: reserved Java keyword used as EAttribute name (`package`) — blocked Acceleo compilation
- Fixed directly in ecore + template (not a new round) since it was a trivial rename
- Step 3 complete in 1 round (with one manual fix)
- Matrix generated correctly — strategy.matrix with NODE_VERSION_18/20/22 entries
- Parameters generated correctly — DEPLOY_TARGET and SKIP_E2E blocks
- Services partial — postgres mapped, redis missing
- Cron expression truncation — same ATL bug as Bamboo (t.crons->first() on String returns first char)
