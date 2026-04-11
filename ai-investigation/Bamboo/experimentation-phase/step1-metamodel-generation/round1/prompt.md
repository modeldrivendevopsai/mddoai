# Round 1 — GHA PSM metamodel + Bamboo documentation

## Context given

- `context/GHA.ecore` — GitHub Actions PSM metamodel from ACICDTrip (structural template)
- `context/bamboo-docs.md` — Bamboo Specs v10.0.2 official reference

## Context NOT given

- Any ATL transformation
- Any example `bamboo.yaml` output

## Rationale

Round 1 — no constraints. Tests what GHA.ecore structure + Bamboo docs produces.

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
Use it as a structural reference for the EMF format and how CI/CD concepts
map to EClass hierarchies with containment references.

```xml
[PASTE context/GHA.ecore HERE]
```

---

## Bamboo CI/CD YAML — official reference

This defines the Bamboo-specific constructs your metamodel must cover.
Use terminology strictly from the Bamboo documentation provided above.

```
[PASTE context/bamboo-docs.md HERE]
```

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
