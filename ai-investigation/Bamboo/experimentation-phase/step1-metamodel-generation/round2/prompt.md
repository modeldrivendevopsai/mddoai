# Round 2 — GHA PSM metamodel + pimMM + Bamboo documentation + enum-default guard

## Why Round 2
Round 1 generated a valid-looking metamodel but failed Eclipse EMF validation on enum default literals.
This round keeps the same context and adds strict constraints so enum defaults are always legal.

---

<!-- BEGIN PROMPT -->

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

## Platform-independent CI/CD metamodel (PIM)

This defines the platform-independent CI/CD concepts your metamodel must be able to represent.
Every concept in this metamodel should have a corresponding representation in your Bamboo PSM.

```xml
[PASTE context/pimMM.ecore HERE]
```

---

## Bamboo CI/CD YAML — official reference

This defines the Bamboo-specific constructs your metamodel must cover.
Use Bamboo terminology (Plan, Stage, Job, Task, Agent, Trigger, Artifact, Variable).

```
[PASTE context/bamboo-docs.md HERE]
```

---

## Critical constraints (Round 2)

1. For every enum-typed attribute (`eType="#//SomeEnum"`) with a `defaultValueLiteral`,
   the default must exactly match one of that enum's `eLiterals literal="..."` values.
2. Do not use enum constant names as defaults when literal text differs.
3. Matching is case-sensitive and punctuation-sensitive (hyphens, underscores, lowercase).
4. If unsure about an enum default, omit `defaultValueLiteral` instead of inventing one.

Examples of valid literal forms:
- `REPOSITORY_SCOPE`: `global`, `project`
- `RELEASE_APPROVAL`: `not-broken`, `approved`, `none`

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

<!-- END PROMPT -->
