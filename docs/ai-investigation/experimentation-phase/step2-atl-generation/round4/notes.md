# Round 4 — Notes

## Run date
2026-04-06

## Context given
- `context/pimMM.ecore`
- `context/gitlabMM.ecore` (from Step 1)
- `context/cicd2gha.atl`
- All Round 3 instructions retained
- Added: target variable names must not be ATL reserved keywords
- Added: never use `and` as a boolean OCL operator; use nested `if` instead

---

## Previous bugs — status in Round 4

### Reserved keyword as target variable name — ELIMINATED ✓
No `def`, `rule`, or any other ATL reserved word used as a target variable name.
The explicit instruction worked.

### `and` as boolean OCL operator — ELIMINATED ✓
No `and` in any `->select()` lambda or `if` condition.
The explicit instruction worked. Nested `if` is used consistently for nullable guards.

### `->max()` — still eliminated ✓
Stage computation uses `getMaxDepth` with `->iterate()` and an explicit max accumulator:
```atl
->iterate(d; mx : Integer = depth | if d > mx then d else mx endif)
```
No unsupported collection operations.

---

## New bug found

### `oclAsType()` on direct model element reference — runtime crash

```atl
helper def : Step2ScriptLines(step : PIM!Step) : Sequence(String) =
    if step.oclIsTypeOf(PIM!Command) then
        Sequence{step.oclAsType(PIM!Command).program.expressionToString()}
    else if step.oclIsTypeOf(PIM!Plugin) then
        Sequence{'# plugin: ' + step.oclAsType(PIM!Plugin).pluginName + ...}
    ...
```

ATL's EMF VM does not support `oclAsType()` called directly on a model element
with a metamodel type argument. The VM resolves `PIM!Command` to a Java
`EClassImpl` object and finds no handler for `oclAsType(EClassImpl)`.

Crashes with:
`Operation not found: IN!<unnamed>.oclAsType(org.eclipse.emf.ecore.impl.EClassImpl)`

`oclAsType()` does not appear anywhere in `cicd2gha.atl`. The reference instead
uses two patterns for type-specific attribute access:
1. **Context helpers** — `helper context PIM!SomeType def : ...` (dispatches polymorphically)
2. **Abstract rule + extends** — `abstract rule A`, `rule B extends A`

Round 4 correctly used context helpers for `expressionToString` and `agent2ImageHelper`,
but reverted to `oclAsType()` for `Step2ScriptLines`, which is not a rule context
and cannot use abstract rules. The correct fix is context helpers.

Not present in `cicd2gha.atl` — LLM-introduced from general OCL knowledge.

---

## Notable improvements in Round 4

- `abstract rule Job2Job` + `rule ScriptJob2Job extends Job2Job` — follows the exact
  pattern from `cicd2gha.atl` (`abstract rule Job2Job`, `rule ScriptJob2ScriptJob extends Job2Job`)
- `defaults` binding restored in `Pipeline2Pipeline` (was missing in Rounds 2–3)
- Context helpers for `expressionToString` and `agent2ImageHelper` follow the polymorphic
  dispatch pattern from `cicd2gha.atl` correctly
- `or` used in `if not pipeline.agent.oclIsUndefined() or ...` — valid, `or` IS present
  in `cicd2gha.atl` (lines 14 and 122), and is safe here since `oclIsUndefined()` can
  be called on null references without crashing

---

## Key finding

Round 4 confirms that explicit, named instructions work:
both the reserved keyword instruction and the `and` instruction had immediate effect.

The `oclAsType()` bug reveals the same pattern as all previous bugs: a valid OCL construct
that is absent from `cicd2gha.atl` but in common use in other OCL environments.
Three out of four rounds of bugs were OCL operations not present in the reference.

Round 5 should name `oclAsType()` explicitly and point to context helpers as the
correct alternative.

---

## Compiles in Eclipse ATL?
Yes — no syntax errors.

## Runs successfully?
No — crashes at `Step2ScriptLines` line 317: `oclAsType()` not supported by ATL EMF VM.

## Manual fix applied
Replaced `helper def : Step2ScriptLines` (which used `oclAsType()`) with context helpers
following the `cicd2gha.atl` polymorphic dispatch pattern:
```atl
helper context PIM!Step def : toScriptLines() : Sequence(String) = Sequence{'# unknown step'};
helper context PIM!Command def : toScriptLines() : Sequence(String) = Sequence{self.program.expressionToString()};
helper context PIM!Plugin def : toScriptLines() : Sequence(String) = ...;
-- etc.
```
Call site updated to `pimJob.steps->collect(step | step.toScriptLines())->flatten()`.
Fix confirmed: transformation runs successfully and produces correct XMI output.
