# Round 3 — Same context + restrict OCL operations to reference

## Why Round 3

Round 2 introduced one new runtime crash not present in `cicd2gha.atl`:

- `->max()` used in a `depth()` helper — ATL's EMF VM does not implement `->max()`
  on `Sequence(Integer)`. Crashes with `Operation not found: Sequence {1}.max()`.

Additionally, `and` appears as a boolean OCL operator in three `if` conditions
(latent bug — did not crash with the test data but unsafe for null attributes).

Neither `->max()` nor `and` as a boolean operator appears in `cicd2gha.atl`.
Both were introduced from the LLM's general OCL/programming knowledge, despite the
Round 2 instruction to follow only the reference.

Round 3 strengthens the instruction: **only use OCL collection operations and
constructs that are demonstrated in the reference file**.

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

This is the single source of truth for ATL syntax in this task.
Write all rules, helpers, lazy rules, and OCL expressions following the exact patterns used in this file. Do not use your own knowledge of ATL or OCL syntax.

Only use OCL collection operations that appear in this reference file.

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

<!-- END PROMPT -->
