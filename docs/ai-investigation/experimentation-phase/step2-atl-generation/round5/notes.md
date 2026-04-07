# Round 5 — Notes

## Run date
2026-04-07

## Context given
- `context/pimMM.ecore`
- `context/gitlabMM.ecore` (from Step 1)
- `context/cicd2gha.atl`
- All Round 4 instructions retained
- Added: never use `oclAsType()`; use context helpers for type-specific attribute access

---

## Previous bugs — status in Round 5

### Reserved keyword as target variable name — REGRESSED
`rule` used again as a target variable name. Same keyword class as Round 1 (`def`) and
Round 3 (`rule`). Fixed manually.
The instruction to avoid reserved keywords did not prevent the specific word `rule`
from recurring — the LLM appears to not retain which words are reserved across rounds.

### `and` as boolean OCL operator — ELIMINATED ✓
No `and` in `->select()` or `if` conditions. Held from Round 4.

### `oclAsType()` — ELIMINATED ✓
No `oclAsType()` anywhere in the output. Context helpers used throughout.

### `->max()` and other unsupported collection operations — ELIMINATED ✓

---

## New bugs found

None.

---

## Compiles in Eclipse ATL?
No — `rule` used as target variable name (syntax error). Fixed manually.

## Runs successfully?
Yes — after manual fix. Transformation runs and produces correct output.
