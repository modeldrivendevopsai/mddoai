# Step 2 — ATL Transformation Generation

## Goal

Generate `pim2gitlab.atl` — an ATL transformation that maps PIM model instances
to GitLab PSM model instances using the metamodel from Step 1.

## Chain position

```
pimMM  ──▶  pim2gitlab.atl  ──▶  gitlabMM (from Step 1)  ──▶  Acceleo (Step 3)  ──▶  .gitlab-ci.yml
```

## Context files

| File | Purpose |
|------|---------|
| `context/pimMM.ecore` | Source metamodel — what the ATL transforms FROM |
| `context/gitlabMM.ecore` | Target metamodel from Step 1 — what the ATL transforms TO |
| `context/pim2gitlabmodel.atl` | Existing ATL example — structural and syntactic reference |

## Rounds

| Round | Context | What it tests |
|-------|---------|---------------|
| 1 | `pimMM.ecore` + `gitlabMM.ecore` + `pim2gitlabmodel.atl` | Can the LLM write a new ATL given source MM, target MM, and an ATL example for the same source? |

Only one round planned. The existing ATL is a very strong reference —
it covers the same source metamodel and the same conceptual mapping.
The only difference is the target class names.

## Validation

Run the generated ATL on a PIM model instance and check:
- Output XMI references class names from `context/gitlabMM.ecore` (not the old ones)
- Key mappings hold: Pipeline→Pipeline, ScriptJob→Job, LinuxAgent→Image,
  Trigger→Workflow rules, Matrix→Parallel/MatrixEntry, DockerContainer→Service
