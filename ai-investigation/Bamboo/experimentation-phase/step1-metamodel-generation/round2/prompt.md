# Round 2 — GHA PSM metamodel + Bamboo documentation + enum-default guard

## Why Round 2

Round 1 generated a valid-looking metamodel but failed Eclipse EMF validation on enum default literals.
This round keeps the same context and adds strict constraints so enum defaults are always legal.

---

## &lt;BEGIN PROMPT&gt;

You are working on a Model-Driven Engineering (MDE) project.
The goal is to generate a Bamboo CI/CD PSM metamodel in EMF Ecore format.

A PSM (Platform-Specific Model) metamodel defines the types needed to represent
a CI/CD pipeline configuration as a model instance, which can then be serialized
to the actual CI/CD YAML format.

---

## Reference: GitHub Actions PSM metamodel

This is a real PSM metamodel for GitHub Actions, written in EMF Ecore.
Use it as a structural reference for Ecore syntax and class/reference organization.

```xml
[PASTE context/GHA.ecore HERE]
```

---

## Bamboo CI/CD YAML — official reference

This defines the Bamboo-specific constructs your metamodel must cover.
Use terminology strictly from the Bamboo documentation provided below.

```
[PASTE context/bamboo-docs.md HERE]
```

---

## Constraints

1. For any enum-typed attribute, `defaultValueLiteral` must exactly match one declared
   enum literal value (case-sensitive and punctuation-sensitive).
2. Do not use enum constant names as defaults when they differ from literal values.
3. If a correct enum default cannot be derived from declared literals, omit
   `defaultValueLiteral`.

---

## Task

Generate `bambooMM.ecore` — an EMF Ecore metamodel for Bamboo CI/CD pipelines,
covering the constructs described in the Bamboo reference above.

Use:

- `name="bambooMM"`
- `nsURI="http://www.mddoai.com/mddoai/metamodel/bamboo"`
- `nsPrefix="bambooMM"`

Output only the XML content of the `.ecore` file, starting with `<?xml ...>`.
Do not add any explanation.

## &lt;END PROMPT&gt;
