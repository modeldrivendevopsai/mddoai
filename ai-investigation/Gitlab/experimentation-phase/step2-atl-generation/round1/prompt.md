# Round 1 — PIM + GitLab PSM + ACICDTrip cicd2gha ATL example

## Context given
- `context/pimMM.ecore` — source metamodel
- `context/gitlabMM.ecore` — target metamodel (generated in Step 1)
- `context/cicd2gha.atl` — ACICDTrip ATL example (CICD → GitHub Actions), for ATL syntax reference only

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

## Reference ATL example — ACICDTrip CICD to GitHub Actions

This is a real ATL transformation from a related project.
Use it as a reference for ATL syntax and structure only: how to write rules,
lazy rules, helpers, tuple parameters, and OCL expressions.
The mapping logic must come from the two metamodels above, not this example.

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
