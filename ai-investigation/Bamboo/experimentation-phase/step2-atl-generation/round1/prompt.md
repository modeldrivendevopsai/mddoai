# Round 1 — PIM + Bamboo PSM + ACICDTrip cicd2gha ATL example

## Context given
- `context/pimMM.ecore` — source metamodel (platform-independent CI/CD)
- `context/bambooMM.ecore` — target metamodel (generated in Step 1, Round 3)
- `context/cicd2gha.atl` — ACICDTrip ATL example (CICD → GitHub Actions), for ATL syntax reference only

---

## BEGIN PROMPT

You are working on a Model-Driven Engineering (MDE) project.

The goal is to write an ATL model-to-model transformation that maps a
platform-independent pipeline model (PIM) to a Bamboo CI/CD PSM model.

---

## Source metamodel — PIM

```xml
[PASTE context/pimMM.ecore HERE]
```

---

## Target metamodel — Bamboo PSM

```xml
[PASTE context/bambooMM.ecore HERE]
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

These constraints are mandatory. Violations will cause compile or runtime errors.

1. **Reserved keywords** — `def`, `rule`, `module`, `create`, `from`, `to`, `using` are reserved ATL keywords. Never use them as variable names, parameter names, or target element names in rules.

2. **Boolean operators** — ATL `and` is not short-circuit. If the left side can be undefined, use nested `if` expressions instead of `and`.

3. **OCL operations** — Only use OCL collection operations that appear in the reference ATL file above. Do not use `->max()`, `->min()`, or `oclAsType()` — these are not supported in ATL.

4. **Constraint placement** — These constraints apply to the entire output. Read them again after writing the transformation before finalising.

---

## Task

Generate `pim2bamboo.atl` — an ATL transformation from `pimMM` to `bambooMM`.

Use:
- `-- @nsURI PIMMM=http://www.mddoai.com/mddoai/metamodel/pim`
- `-- @nsURI BambooMM=http://www.mddoai.com/mddoai/metamodel/bamboo`
- `module pim2bamboo;`
- `create OUT : BambooMM from IN : PIMMM;`

Output only the ATL file content. Do not add any explanation.

## END PROMPT
