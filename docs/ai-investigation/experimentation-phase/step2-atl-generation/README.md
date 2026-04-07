# Step 2 — ATL Transformation Generation

## Goal

Generate `pim2gitlab.atl` — an ATL transformation that maps MDDOAI PIM model
instances to GitLab PSM model instances using the metamodel from Step 1.

## Chain position

```
pimMM  ──▶  pim2gitlab.atl  ──▶  gitlabMM (Step 1)  ──▶  Acceleo (Step 3)  ──▶  .gitlab-ci.yml
```

## Context files

| File | Purpose |
|------|---------|
| `context/pimMM.ecore` | Source metamodel — what the ATL transforms FROM |
| `context/gitlabMM.ecore` | Target metamodel from Step 1 — what the ATL transforms TO |
| `context/cicd2gha.atl` | ACICDTrip's CICD→GHA ATL — ATL syntax and structure reference only |

## Why cicd2gha.atl and not pim2gitlabmodel.atl

`cicd2gha.atl` maps ACICDTrip's CICD metamodel → GHA metamodel.
`pimMM` and `CICD` are different source metamodels, so the mapping logic
cannot be copied directly — the LLM must derive the traversal from `pimMM.ecore`.

This is a more genuine test: the LLM has a real ATL example for structural/syntactic
reference (how to write rules, lazy rules, helpers, tuples) and must combine that
with the two metamodels to produce the correct mapping.

## Rounds

| Round | Context | What it tests |
|-------|---------|---------------|
| 1 | `pimMM` + `gitlabMM` + `cicd2gha.atl` | ATL generation from source MM + target MM + peer ATL syntax example |

Round 2 only if Round 1 fails validation.

## Validation

Run the generated ATL on a PIM model instance and check:
- Output XMI references class names from `context/gitlabMM.ecore`
- Key mappings hold: Pipeline→Pipeline, ScriptJob→Job, LinuxAgent→Image,
  Trigger→Workflow rules, Matrix→Parallel/MatrixEntry, DockerContainer→Service
