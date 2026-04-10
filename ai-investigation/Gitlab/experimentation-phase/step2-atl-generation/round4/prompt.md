# Round 4 — Same context + explicit fix for two persistent bugs

## Why Round 4

Three rounds in, two bugs have persisted despite progressively stronger
general instructions:

1. **Reserved ATL keywords used as target variable names** — Round 1 used `def`,
   Round 3 used `rule`. Both are ATL reserved words that cause a syntax error.
   General instructions about following the reference did not prevent this because
   the LLM does not know which words ATL reserves.

2. **`and` as a boolean OCL operator** — present in all three rounds, in both
   `->select()` lambdas and `if` conditions. ATL `and` is not short-circuit:
   both sides always evaluate. Accessing a typed attribute (e.g. `.store`) on the
   right side of `and` without a prior type check crashes at runtime.
   `cicd2gha.atl` never uses `and` as an OCL boolean operator. Three rounds of
   general instructions did not prevent its use.

Round 4 names both constraints explicitly, justified by three rounds of evidence
that general instructions are insufficient for these specific pitfalls.

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
