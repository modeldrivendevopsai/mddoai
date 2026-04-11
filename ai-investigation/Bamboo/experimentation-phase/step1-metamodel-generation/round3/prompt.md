# Round 3 — GHA PSM metamodel + Bamboo documentation + enum guards

## Why Round 3

Round 2 failed Eclipse EMF validation because EEnum `name=` attributes used hyphenated
strings (e.g. `for-new-branch`) which are not valid Java identifiers.

This round adds an explicit constraint separating `name=` (Java identifier) from `literal=` (YAML string).

---

## BEGIN PROMPT

You are working on a Model-Driven Engineering (MDE) project.
The goal is to generate a Bamboo CI/CD PSM metamodel in EMF Ecore format.

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

## Bamboo CI/CD YAML — official reference

This defines the Bamboo-specific constructs your metamodel must cover.
Use Bamboo terminology (Plan, Stage, Job, Task, Agent, Trigger, Artifact, Variable).

```
[PASTE context/bamboo-docs.md HERE]
```

---

## Constraints

1. **EEnum `name=` must be a valid Java identifier** — no hyphens, dots, or spaces.
   Use camelCase (e.g. `forNewBranch`, `planFailed`, `notBroken`).
   This applies to ALL `eLiterals name=` values without exception.

2. **EEnum `literal=` holds the exact YAML string** — this can contain hyphens
   (e.g. `literal="for-new-branch"`, `literal="not-broken"`).
   Always set `literal=` explicitly when the YAML value differs from the `name=`.

3. **`defaultValueLiteral` must exactly match a declared `literal=` value** (case-sensitive).
   If no safe default exists, omit `defaultValueLiteral` entirely.

---

## Task

Generate `bambooMM.ecore` — an EMF Ecore metamodel for Bamboo CI/CD pipelines,
covering the constructs described in the Bamboo reference above and mapping
to the PIM concepts where applicable.

Use:
- `name="bambooMM"`
- `nsURI="http://www.mddoai.com/mddoai/metamodel/bamboo"`
- `nsPrefix="bambooMM"`

Output only the XML content of the `.ecore` file, starting with `<?xml ...>`.
Do not add any explanation.

## END PROMPT
