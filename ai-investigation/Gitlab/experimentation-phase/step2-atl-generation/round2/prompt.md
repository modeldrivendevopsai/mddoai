# Round 2 — Same context + explicit ATL syntax guidance

## Why Round 2

Round 1 produced two ATL-specific bugs that are not present in `cicd2gha.atl`:

1. `def` used as a target variable name — reserved ATL keyword, blocks compilation
2. `and` used in `->select()` with attribute access — ATL `and` is not short-circuit,
   crashes at runtime when the attribute does not exist on the checked type

Both bugs came from general programming intuition, not from the ATL example.
Round 2 adds an explicit instruction to treat `cicd2gha.atl` as the single
source of ATL syntax knowledge.

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
Write all rules, helpers, lazy rules, and OCL expressions following the exact patterns used in this file. Do not apply knowledge of ATL from any other source.

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
