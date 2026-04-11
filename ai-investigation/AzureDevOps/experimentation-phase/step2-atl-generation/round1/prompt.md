# Round 1 — PIM + Azure DevOps PSM + ACICDTrip cicd2gha ATL example

## Context given
- `context/pimMM.ecore` — source metamodel (platform-independent CI/CD)
- `context/azuredevopsMM.ecore` — target metamodel (generated in Step 1)
- `context/cicd2gha.atl` — ACICDTrip ATL example (CICD → GitHub Actions), for ATL syntax reference only

## Constraints pre-loaded
All 6 ATL constraints accumulated from GitLab (6 rounds) and Bamboo (1 round, clean).
Includes recency bias fix: constraints placed AFTER the reference ATL file.

---

## BEGIN PROMPT

You are working on a Model-Driven Engineering (MDE) project.

The goal is to write an ATL model-to-model transformation that maps a
platform-independent pipeline model (PIM) to an Azure DevOps Pipelines PSM model.

---

## Source metamodel — PIM

```xml
[PASTE context/pimMM.ecore HERE]
```

---

## Target metamodel — Azure DevOps PSM

```xml
[PASTE context/azuredevopsMM.ecore HERE]
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

## Constraints

These constraints are mandatory. Violations cause compile or runtime errors.
Read the full list after writing the transformation and before finalising output.

1. **Reserved keywords** — `def`, `rule`, `module`, `create`, `from`, `to`, `using`
   are reserved ATL keywords. Never use any of them as a variable name, parameter
   name, or target element name in any rule or helper.

2. **Boolean operators** — ATL `and` is not short-circuit. If the left operand can
   be undefined, use a nested `if` expression instead of `and`.

3. **OCL operations** — Only use OCL collection operations that appear in the
   reference ATL file above. Do not use `->max()`, `->min()`, or `oclAsType()` —
   these are not supported in the ATL runtime used here.

4. **No `oclAsType()`** — never cast using `oclAsType()`. Use context-specific
   helpers or matched rule bindings instead.

5. **Constraint placement** — these constraints apply to the entire output.
   After completing the transformation, re-read every rule and helper against
   this list before producing the final output.

---

## Task

Generate `pim2azuredevops.atl` — an ATL transformation from `pimMM` to `azuredevopsMM`.

Use:
- `-- @nsURI PIMMM=http://www.mddoai.com/mddoai/metamodel/pim`
- `-- @nsURI AzureDevOpsMM=http://www.mddoai.com/mddoai/metamodel/azuredevops`
- `module pim2azuredevops;`
- `create OUT : AzureDevOpsMM from IN : PIMMM;`

Output only the ATL file content. Do not add any explanation.

## END PROMPT
