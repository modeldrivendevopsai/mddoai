# Round 3 — Notes

## Run date
2026-04-06

## Context given
- `context/pimMM.ecore`
- `context/gitlabMM.ecore` (from Step 1)
- `context/cicd2gha.atl`
- Strengthened instruction: do not use your own knowledge of ATL/OCL syntax
- Added: only use OCL collection operations present in the reference file

---

## Class name correctness

| ATL reference | In gitlabMM.ecore? |
|--------------|-------------------|
| `GitLabMM!Pipeline` | ✓ |
| `GitLabMM!Workflow` | ✓ |
| `GitLabMM!Variable` | ✓ |
| `GitLabMM!Job` | ✓ |
| `GitLabMM!Image` | ✓ |
| `GitLabMM!Service` | ✓ |
| `GitLabMM!Need` | ✓ |
| `GitLabMM!Rule` | ✓ |
| `GitLabMM!Artifacts` | ✓ |
| `GitLabMM!Cache` | ✓ |
| `GitLabMM!CacheKey` | ✓ |
| `GitLabMM!Parallel` | ✓ |
| `GitLabMM!MatrixEntry` | ✓ |
| `GitLabMM!Retry` | ✓ |

14/14 referenced — `GitLabMM!Default` still absent (same omission as Round 2).

---

## Previous bugs — status in Round 3

### Bug 1 — `def` as target variable name
**ELIMINATED** — `def` no longer appears.
**REGRESSED to a new keyword** — `rule` is now used as a target variable name in
`Trigger2Rule` (line 43) and `Condition2Rule` (line 396):
```atl
to
    rule : GitLabMM!Rule(   -- 'rule' is a reserved ATL keyword
```
`rule` is the keyword that opens every transformation rule. Causes the same
syntax error class as the `def` bug from Round 1.
**Not present in `cicd2gha.atl`** — LLM-introduced from general naming intuition.

### Bug 2 — Non-short-circuit `and` in `->select()` — REGRESSED
**Was fixed in Round 2, back in Round 3.** The `collectArtifacts` rule uses `and`
inside `->select()` in four places (lines 235, 247, 254, 262):
```atl
pimJob.steps->select(s | s.oclIsTypeOf(PIM!Artifact) and s.store)
```
`and` evaluates `s.store` on every step type including `PIM!Command`, which has
no `store` feature. Crashes with:
`Feature store does not exist on Command`

`and` as a boolean OCL operator does not appear in `cicd2gha.atl`.
Round 3's "only use OCL collection operations from the reference" did not prevent
this — `and` is a boolean operator, not a collection operation, so the LLM
did not apply the restriction to it.

### Bug 3 (Round 2 latent) — `and` in `if` conditions — still present
```atl
if not artStep.oclIsUndefined() and not artStep.artifactName.oclIsUndefined() then
```
Same latent non-short-circuit risk as Round 2. Did not crash in this test run
because the condition tree evaluated safely with available test data.

---

## Bug fixed in Round 3

### `->max()` — ELIMINATED ✓
No `->max()`, `->min()`, or `->sortedBy()` anywhere in the output.
The "only use OCL collection operations present in the reference" instruction
worked exactly as intended for this class of error.

The stage computation no longer uses depth-based recursion; it uses:
```atl
thisModule.getAllJobs(jobStreams)
    ->collect(j | if not j.name.oclIsUndefined() then j.name else j.id endif)
    ->asSet()->asSequence()
```

---

## Key finding

The restriction on OCL collection operations was correctly understood and applied —
`->max()` is gone across all three rounds of the `and` regression.

The `and` regression reveals a gap in the instruction's scope: "collection operations"
was interpreted as distinct from "boolean operators". The LLM fixed `->max()` but
reverted `and` in `->select()` which Round 2 had eliminated.

The reserved keyword bug also mutated: `def` → `rule`. The LLM understands that
one specific word caused a problem but doesn't know the full set of ATL reserved words.

At this point three rounds have run without directly naming either pitfall.
Round 4 is the point where explicitly stating both constraints is scientifically
justified by the accumulated evidence.

---

## Compiles in Eclipse ATL?
No — `rule` used as target variable name (syntax error). Fixed manually.

## Runs successfully?
No — crashes at `collectArtifacts` line 235 (`and s.store` on mixed step types).
