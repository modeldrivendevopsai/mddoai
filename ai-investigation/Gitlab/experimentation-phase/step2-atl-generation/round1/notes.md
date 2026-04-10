# Round 1 — Notes

## Run date
2026-04-06

## Context given
- `context/pimMM.ecore`
- `context/gitlabMM.ecore` (from Step 1)
- `context/cicd2gha.atl`

---

## Class name correctness

| ATL reference | In gitlabMM.ecore? |
|--------------|-------------------|
| `GitLabMM!Pipeline` | ✓ |
| `GitLabMM!Workflow` | ✓ |
| `GitLabMM!Default` | ✓ |
| `GitLabMM!Job` | ✓ |
| `GitLabMM!Image` | ✓ |
| `GitLabMM!Service` | ✓ |
| `GitLabMM!Variable` | ✓ |
| `GitLabMM!Need` | ✓ |
| `GitLabMM!Rule` | ✓ |
| `GitLabMM!Retry` | ✓ |
| `GitLabMM!Parallel` | ✓ |
| `GitLabMM!MatrixEntry` | ✓ |
| `GitLabMM!Artifacts` | ✓ |
| `GitLabMM!Cache` | ✓ |
| `GitLabMM!CacheKey` | ✓ |

15/15 — all class names correct. Zero hallucinations.

Classes in gitlabMM.ecore not referenced:
`ArtifactReports`, `CoverageReport`, `DockerOptions`, `KubernetesOptions`, `Environment`
— reasonable omissions, optional detail classes.

---

## Bugs found

### 1. Reserved keyword as target variable name — blocked compilation
```atl
lazy rule Pipeline2Default {
    to
        def : GitLabMM!Default(   -- 'def' is a reserved ATL keyword
```
`def` is the keyword used in `helper def :` — cannot be used as a variable name.
Fixed manually by renaming to `glDefault`.
**Not present in cicd2gha.atl** — LLM-introduced bug, not from the example.

### 2. Non-short-circuit `and` in select — runtime crash
```atl
steps->select(s | s.oclIsTypeOf(PIM!Artifact) and s.store = true)
```
ATL's `and` is not short-circuit. Both sides evaluate even when the left is false,
so `.store` is accessed on every step type including `Command`, which has no `store` feature.
Crashes at runtime: `Feature store does not exist on Command`.
Fixed manually by chaining two `->select()` calls.
**Not present in cicd2gha.atl** — LLM-introduced bug, not from the example.

### 3. Missing metamodel alias in @nsURI headers
Output used `-- @nsURI PIM=http://...` instead of `-- @nsURI PIM=pimMM=http://...`.
Did not block compilation or runtime in this setup.

### 4. Dead code in ScriptJob2Job image mapping — cosmetic
```atl
else if not cicdJob.agent.oclIsUndefined() then   -- identical condition, unreachable
```
Harmless copy-paste error.

---

## Key finding for Round 2

Both blocking bugs (1 and 2) are ATL-specific pitfalls that do **not** appear
anywhere in `cicd2gha.atl`. The LLM hallucinated them from general programming
intuition (`def` as a natural variable name, `and` as short-circuit like most languages).

Round 2 should explicitly instruct the LLM to treat `cicd2gha.atl` as the
single source of ATL syntax knowledge and follow its patterns exactly.

---

## What worked well

- All 15 class names match the Step 1 metamodel exactly
- `expressionToString` helper correctly handles all PIM expression subtypes
- `Steps2Script` correctly flattens steps to string list — aligns with Step 1's flat design
- `Matrix2Parallel` → `MatrixAxis2MatrixEntry` maps the new structure correctly
- Enum literals (`#ON_SUCCESS`, `#MANUAL`, `#ALWAYS`) match the enums in gitlabMM.ecore

---

## Compiles in Eclipse ATL?
Yes — after one manual fix (bug 1).

## Runs successfully?
Yes — after one manual fix (bug 2). Output XMI to be verified.
