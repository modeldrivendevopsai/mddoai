# Round 2 — Fix single-quote embedding compile error

## Why Round 2

Round 1 produced a compile error:
`mismatched input '''' expecting SEMI` at line 27, column 17.

Root cause: LLM attempted to embed a single quote inside an ATL string literal
using the `''''` escape pattern (e.g. `'variables[''' + name + ''']'`).
ATL's parser cannot resolve the token boundaries in this pattern and fails.
ATL does not support embedding literal single-quote characters in string
expressions this way.

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

5. **No embedded single quotes in string literals** — do not use `''''` or any
   pattern that embeds a literal single-quote character inside an ATL string
   expression. ATL's parser cannot resolve the token boundaries and will fail
   with `mismatched input '''' expecting SEMI`. Restructure any expression that
   would require a `'` character in the output string to avoid it entirely.

6. **Constraint placement** — these constraints apply to the entire output.
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
