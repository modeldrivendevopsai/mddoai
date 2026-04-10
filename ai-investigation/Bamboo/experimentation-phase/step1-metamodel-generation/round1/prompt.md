# Round 1 — GHA PSM metamodel + pimMM + Bamboo documentation

## Context given
- `context/GHA.ecore` — GitHub Actions PSM metamodel from ACICDTrip (structural template)
- `context/pimMM.ecore` — platform-independent CI/CD metamodel (concepts to cover)
- `context/bamboo-docs.md` — Bamboo Specs v10.0.2 official reference

## Context NOT given
- Any ATL transformation
- Any example `bamboo.yaml` output

## Rationale
Round 1 — no constraints. Tests what GHA.ecore structure + Bamboo docs + PIM concepts produces.
The PIM is included from Round 1 (unlike GitLab where it was added in Round 2) because
Bamboo terminology diverges more from PIM concepts (Plan vs Pipeline, Task vs Command, Stage mandatory).

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
