# Round 6 — Same constraints, moved after ATL reference (recency bias test)

## Why Round 6

Round 5 confirmed all constraint content is correct:
- `and` eliminated ✓
- `oclAsType()` eliminated ✓
- `->max()` eliminated ✓

The one remaining regression: `rule` used as a target variable name despite an explicit
instruction not to use reserved keywords. The instruction was present but placed before
754 lines of ATL reference — far from the generation point.

Round 6 tests whether **placement** is the issue. All constraints are identical to Round 5
but moved to after the ATL reference, immediately before the task. Single variable change.

---

<!-- BEGIN PROMPT -->

You are working on a Model-Driven Engineering (MDE) project.

The goal is to write an ATL model-to-model transformation that maps a
platform-independent pipeline model (PIM) to a GitLab CI/CD PSM model.

---

## Source metamodel — PIM

```xml
[PASTE context/pimMM.ecore HERE]
```

---

## Target metamodel — GitLab PSM

```xml
[PASTE context/gitlabMM.ecore HERE]
```

---

## ATL syntax reference — cicd2gha.atl

```atl
[PASTE context/cicd2gha.atl HERE]
```

---

## Constraints

This is the single source of truth for ATL syntax in this task.
Write all rules, helpers, lazy rules, and OCL expressions following the exact patterns used in this file. Do not use your own knowledge of ATL or OCL syntax.

Only use OCL collection operations that appear in this reference file.

**Target variable names in `to` blocks must not be ATL reserved keywords.**
In the reference, target variables always have descriptive names (`workflow`, `ghaJob`, `ghaStep`, `variableAssignment`). Follow this convention exactly.

**Never use `and` as a boolean OCL operator.** The reference file does not use `and`
in any `if` condition or `->select()` lambda. To guard a nullable attribute, always
use a nested `if` expression:
```
if not x.oclIsUndefined() then
    if x.someAttr = ... then ... else ... endif
else
    OclUndefined
endif
```

**Never use `oclAsType()`.** It does not appear in the reference file and is not
supported by the ATL EMF VM. For type-specific attribute access after a type check,
use context helpers — `helper context PIM!SomeType def : ...` — following the
polymorphic dispatch pattern used throughout the reference file.

---

## Task

Generate `pim2gitlab.atl` — an ATL transformation from `pimMM` to `gitlabMM`.

Use:

- `-- @nsURI PIM=pimMM=http://www.mddoai.com/mddoai/metamodel/pim`
- `-- @nsURI GitLabMM=gitlabMM=http://www.mddoai.com/mddoai/metamodel/gitlab`
- `module pim2gitlab;`
- `create OUT : GitLabMM from IN : PIM;`

Output only the ATL file content. Do not add any explanation.

<!-- END PROMPT -->
