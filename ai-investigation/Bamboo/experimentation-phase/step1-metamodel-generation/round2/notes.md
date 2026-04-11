# Round 2 — Notes

## Run date
2026-04-11

## Context given
- `context/GHA.ecore` (ACICDTrip GitHub Actions metamodel)
- `context/pimMM.ecore` (platform-independent CI/CD metamodel)
- `context/bamboo-docs.md` (Bamboo Specs v10.0.2)
- Added constraint: enum `defaultValueLiteral` must match exact EEnum `literal` value

## Round 1 issue targeted
Round 1 failed Eclipse EMF validation due to invalid enum default literals:
- `GLOBAL` used instead of `global` for `REPOSITORY_SCOPE`
- `NOT_BROKEN` used instead of `not-broken` for `RELEASE_APPROVAL`

## Classes generated
- 152 classifier entries (vs 148 in Round 1)
- Permissions model refactored: `PlanPermissions`/`DeploymentPermissions` split into lists of `PlanPermissionEntry`/`DeploymentPermissionEntry`/`EnvironmentPermissionEntry`
- `VARIABLE_SCOPE` enum renamed to `InjectScope`

## Round 2 issue found
Eclipse EMF validation failed — EEnum `name=` attributes used hyphenated strings which are not valid Java identifiers:
- `for-new-branch`, `for-pull-request`, `plan-failed`, `plan-completed`, etc.
- `not-broken`, `view-configuration`, `create-repository`, `logged-in`

EMF requires `name=` to be a valid Java identifier. The `literal=` attribute can retain the hyphenated string for correct YAML output.

Fix applied manually:
- All hyphenated `name=` values converted to camelCase (e.g. `for-new-branch` → `forNewBranch`)
- `literal=` values left unchanged (e.g. `literal="for-new-branch"`)

Post-fix validation: **passes** in Eclipse EMF.

## Compilable in Eclipse EMF?
Yes, after manual fix of hyphenated enum names.

## Key findings
- The defaultValueLiteral constraint from Round 1 was held — no regression there
- New bug: LLM used Bamboo's YAML literal strings directly as Java enum names
- Root cause: LLM correctly read hyphenated literals from Bamboo docs but applied them to `name=` instead of only `literal=`
- Round 3 must explicitly constrain: `name=` must be camelCase Java identifier, `literal=` holds the YAML string
