# Round 6 — Notes

## Run date
2026-04-07

## Context given
- `context/pimMM.ecore`
- `context/gitlabMM.ecore` (from Step 1)
- `context/cicd2gha.atl`
- All Round 5 constraints retained — moved to AFTER the ATL reference (recency bias test)

## Single variable changed
Constraint placement only. Content identical to Round 5.

---

## Result: CLEAN FIRST RUN — no syntax errors, no runtime errors

---

## Previous bugs — status in Round 6

### Reserved keyword as target variable name — ELIMINATED ✓
No reserved keywords used as target variable names.
Moving constraints to after the ATL reference fixed this immediately.
Confirms recency bias was the cause of the regression across Rounds 1, 3, and 5.

### `and` as boolean OCL operator — ELIMINATED ✓
Held from Round 4.

### `oclAsType()` — ELIMINATED ✓
Held from Round 5.

### `->max()` and other unsupported collection operations — ELIMINATED ✓
Held from Round 3.

---

## New bugs found

None.

---

## Output quality

Compared to Round 4/5, stage naming improved:
- Rounds 4/5: depth-based stage names (`stage-1`, `stage-2`, `stage-3`) in arbitrary order
- Round 6: job ID-based stage names (`build`, `unitTest`, `healthCheck`, `push`) in correct topological order

All mappings correct:
- Pipeline defaults (docker:latest image) ✓
- Variables with CI variable references ✓
- Job scripts correctly extracted from Command steps ✓
- Job images correctly mapped ✓
- Needs correctly mapped (healthCheck→build, push→healthCheck) ✓

---

## Key finding

Placing constraints **after** the 754-line ATL reference (immediately before the task)
eliminated the reserved keyword regression that persisted through Rounds 1, 3, and 5
despite an explicit instruction being present. Recency bias was the root cause.

This is a concrete, measurable finding for the paper: in long-context prompts,
instruction placement relative to the generation point matters more than instruction
content alone.

---

## Compiles in Eclipse ATL?
Yes — no syntax errors.

## Runs successfully?
Yes — clean first run, correct XMI output produced.
