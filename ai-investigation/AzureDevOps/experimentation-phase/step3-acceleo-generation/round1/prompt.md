# Round 1 — Azure DevOps PSM metamodel + GHA Acceleo reference + Azure DevOps docs

## Context given
- `context/azuredevopsMM.ecore` — target metamodel (generated in Step 1)
- `context/gha_generate.mtl` — ACICDTrip GHA Acceleo template (syntax reference only)
- `context/azure-devops-docs.md` — Azure Pipelines YAML schema reference

## Constraints pre-loaded
All 5 Acceleo constraints accumulated from GitLab (4 rounds) and Bamboo (2 rounds).
Includes recency bias fix: constraints placed AFTER the reference MTL file.

---

## BEGIN PROMPT

You are working on a Model-Driven Engineering (MDE) project.

The goal is to write an Acceleo model-to-text template that takes an Azure DevOps
Pipelines PSM model and generates a valid `azure-pipelines.yml` file.

---

## Source metamodel — Azure DevOps PSM

```xml
[PASTE context/azuredevopsMM.ecore HERE]
```

---

## Azure Pipelines YAML reference

```
[PASTE context/azure-devops-docs.md HERE]
```

---

## Acceleo syntax reference — ACICDTrip GHA template

This is a real Acceleo template from a related project.
Use it as a reference for Acceleo module structure, query syntax, file blocks,
and template composition patterns only.
The output structure must follow the Azure Pipelines YAML reference above.

```
[PASTE context/gha_generate.mtl HERE]
```

---

## Constraints

These constraints are mandatory. Violations cause compile or runtime errors.
Read the full list after writing the template and before finalising output.

1. **Never use `def` as a template parameter name.** It is a reserved keyword in
   Acceleo. Use descriptive names such as `planDef`, `jobDef`, `stepDef` instead.

2. **`[comment @main/]` must be the first line inside the template body**, not
   before the template declaration.

3. **EMF enum `toString()` returns the literal value, not the enum name.**
   Always compare against the lowercase literal string from the ecore `literal=`
   attribute. Example: compare against `'always'` not `'ALWAYS'`.

4. **Do not use `oclIsUndefined()` to guard EMF enum attributes.**
   EMF enum attributes always return a default literal even when not set.
   Compare against the default literal string to decide whether to omit output.

5. **Do not use `post(trim())` on any template that receives an `indent` parameter
   or that is called from inside an indented block.**
   `post(trim())` strips ALL leading whitespace from the output, removing any
   indentation the calling template expects.

---

## Task

Generate `generate.mtl` — an Acceleo template that takes an `azuredevopsMM::Pipeline`
and outputs an `azure-pipelines.yml` file.

Use:
- `[module generate('http://www.mddoai.com/mddoai/metamodel/azuredevops')/]`
- The main template must be named `generateElement` and annotated with `[comment @main/]`
  as the first line inside the template body
- The main template takes a `Pipeline` element and writes to `azure-pipelines.yml`

Output only the Acceleo file content. Do not add any explanation.

## END PROMPT
