# Round 5 — Same context + explicit fix for oclAsType()

## Why Round 5

Round 4 introduced one new runtime crash not present in `cicd2gha.atl`:

- `oclAsType()` used directly on a source model element to access a typed attribute,
  e.g. `step.oclAsType(PIM!Command).program`. ATL's EMF VM does not support this form.
  Crashes with: `Operation not found: IN!<unnamed>.oclAsType(EClassImpl)`.

`oclAsType()` does not appear anywhere in `cicd2gha.atl`. The reference instead uses
**context helpers** (`helper context PIM!SomeType def : ...`) for type-specific
attribute access — a pattern Round 4 applied correctly for `expressionToString` and
`agent2ImageHelper` but missed for step-to-script conversion.

Round 5 names this constraint explicitly.

---

## &lt;BEGIN PROMPT&gt;

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

```atl
[PASTE context/cicd2gha.atl HERE]
```

---

## Task

Generate `pim2gitlab.atl` — an ATL transformation from `pimMM` to `gitlabMM`.

Use:

- `-- @nsURI PIM=pimMM=http://www.mddoai.com/mddoai/metamodel/pim`
- `-- @nsURI GitLabMM=gitlabMM=http://www.mddoai.com/mddoai/metamodel/gitlab`
- `module pim2gitlab;`
- `create OUT : GitLabMM from IN : PIM;`

Output only the ATL file content. Do not add any explanation.

## &lt;END PROMPT&gt;
