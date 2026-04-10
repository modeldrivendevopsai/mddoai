# Round 2 — Notes

## Run date
2026-04-06

## Context given
- `context/pimMM.ecore`
- `context/gitlabMM.ecore` (from Step 1)
- `context/cicd2gha.atl`
- Added instruction: treat `cicd2gha.atl` as the **single source of ATL syntax knowledge**

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
| `GitLabMM!Default` | ✗ — not generated |

14/15 — one omission. `GitLabMM!Default` is absent: `Pipeline2Pipeline` does not produce a
`defaults` binding. Pipeline-level agent and timeout are not propagated.

---

## Round 1 bugs — status in Round 2

### Bug 1 — `def` as target variable name
**ELIMINATED.** No target variable named `def` anywhere in the output.
The `single source of truth` instruction worked for this class of error.

### Bug 2 — Non-short-circuit `and` in `->select()`
**ELIMINATED from `->select()`.** The `stepsToArtifacts` helper now uses chained selects
with `oclAsType()`:
```atl
steps->select(s | s.oclIsTypeOf(PIM!Artifact))
    ->collect(s | s.oclAsType(PIM!Artifact))
    ->select(a | a.store)
```
No `and` appears inside any `->select()` expression.

---

## New bugs found

### 3. `->max()` not supported — confirmed runtime crash

```atl
helper context PIM!Job def : depth() : Integer =
    if self.previous->isEmpty() then
        1
    else
        self.previous->collect(prev | prev.depth())->max() + 1
    endif;
```

ATL's EMF VM does not implement `->max()` on `Sequence(Integer)`.
Crashes with: `Operation not found: Sequence {1}.max()`

`->max()` does not appear anywhere in `cicd2gha.atl`.
The depth-based stage computation is entirely LLM-invented logic — not in the reference.

Fix: replace with `->iterate()` using an explicit max accumulator (the only aggregation
pattern available in the reference).

Not present in `cicd2gha.atl` — LLM-introduced from general OCL/programming knowledge.

### 4. Non-short-circuit `and` in `if` conditions — latent bug (3 occurrences, did not trigger)

```atl
-- line 152
if not self.allowFailure.oclIsUndefined() and self.allowFailure then

-- lines 470, 515
if not j.maxAttempts.oclIsUndefined() and j.maxAttempts > 1 then
```

`allowFailure` is `EBooleanObject` and `maxAttempts` is `EIntegerObject` — both nullable.
ATL evaluates both sides of `and` regardless. Did not crash in the test run because
`maxAttempts` had a value (1) and `allowFailure` was null but the left guard evaluated false,
keeping the right side from causing an observable failure in this data set.
Still unsafe for any model instance where these fields are null.

`cicd2gha.atl` never uses `and` as an OCL boolean operator in `if` conditions.
All nullable guards in the reference use separate nested `if` expressions.

### 5. Missing `@nsURI` double-alias form — carried over from Round 1 Bug 3
```
-- @nsURI PIM=http://...       (generated)
-- @nsURI PIM=pimMM=http://... (required by prompt)
```
Did not block compilation or runtime in this setup.

---

## Improvements over Round 1

| Area | Round 1 | Round 2 |
|------|---------|---------|
| Type casting | Direct attribute access after `oclIsTypeOf` | `oclAsType()` cast before access |
| Polymorphism | `if/else if` chains in helpers | Context helpers (`toScriptLines`, `toWorkflowRules`) |
| Stage naming | `job.id` used as stage name | Depth-based stages (`stage-1`, `stage-2`) |
| `PIM!Input` | Not handled | Mapped to `GitLabMM!Variable` |
| `ConditionalStep.elseRun` | Not handled | Mapped to `else` branch in script |
| Agent null guard | Outer `oclIsUndefined` check before lazy rule | Guard inside `agentToImage` helper |

---

## Key finding

The `single source of truth` instruction eliminated both blocking bugs from Round 1.
The new blocking bug (`->max()`) and the latent `and` bugs share the same root cause:
the LLM uses OCL operations and constructs that are not in the reference file.

`cicd2gha.atl` contains no `->max()`, no `->sortedBy()`, and no `and` as an OCL
boolean operator. All three were introduced from the LLM's general knowledge despite
the instruction to follow only the reference.

Round 3 should strengthen the instruction to explicitly restrict OCL operations
to those present in the reference file.

---

## Compiles in Eclipse ATL?
Yes — no reserved keyword issues.

## Runs successfully?
No — crashes at `depth()->max()` before any output is produced.
