# Round 1 — GHA PSM metamodel + Azure DevOps documentation

## Context given

- `context/GHA.ecore` — GitHub Actions PSM metamodel from ACICDTrip (structural template)
- `context/azure-devops-docs.md` — Azure Pipelines YAML schema reference

## Context NOT given

- Any ATL transformation
- Any example `azure-pipelines.yml` output

## Constraints pre-loaded from prior experiments

All three constraints below are carried forward from the Bamboo Step 1 experiment.

---

## BEGIN PROMPT

You are working on a Model-Driven Engineering (MDE) project.
The goal is to generate an Azure DevOps Pipelines PSM metamodel in EMF Ecore format.

A PSM (Platform-Specific Model) metamodel defines the types needed to represent
a CI/CD pipeline configuration as a model instance, which can then be serialized
to the actual CI/CD YAML format.

---

## Reference: GitHub Actions PSM metamodel

This is a real PSM metamodel for GitHub Actions, written in EMF Ecore.
Use it as a structural reference for the EMF format and how CI/CD concepts
map to EClass hierarchies with containment references.

```xml
[PASTE context/GHA.ecore HERE]
```

---

## Azure DevOps Pipelines YAML — official reference

This defines the Azure-specific constructs your metamodel must cover.
Use Azure Pipelines terminology (pipeline, stages, jobs, steps, pool, task, trigger, variables).

```
[PASTE context/azure-devops-docs.md HERE]
```

---

## Constraints

1. **EEnum `name=` must be a valid Java identifier** — no hyphens, dots, or spaces.
   Use camelCase (e.g. `pullRequest`, `onSuccess`, `runOnce`).
   This applies to ALL `eLiterals name=` values without exception.

2. **EEnum `literal=` holds the exact YAML string** — this may contain hyphens
   (e.g. `literal="pull-request"`, `literal="on-success"`).
   Always set `literal=` explicitly when the YAML value differs from the `name=`.

3. **`defaultValueLiteral` must exactly match a declared `literal=` value** (case-sensitive).
   If no safe default exists, omit `defaultValueLiteral` entirely.

---

## Task

Generate `azuredevopsMM.ecore` — an EMF Ecore metamodel for Azure DevOps Pipelines,
covering the constructs described in the Azure reference above.

Use:
- `name="azuredevopsMM"`
- `nsURI="http://www.mddoai.com/mddoai/metamodel/azuredevops"`
- `nsPrefix="azuredevopsMM"`

Output only the XML content of the `.ecore` file, starting with `<?xml ...>`.
Do not add any explanation.

## END PROMPT
