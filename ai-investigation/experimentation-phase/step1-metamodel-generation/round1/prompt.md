# Round 1 — GitHub Actions PSM metamodel + GitLab CI/CD documentation

## Context given
- `context/GHA.ecore` — GitHub Actions PSM metamodel from ACICDTrip (structural template)
- `context/gitlab-ci-docs.md` — curated GitLab CI/CD keyword reference

## Context NOT given
- `context/pimMM.ecore` (added in Round 2)
- Any ATL transformation
- Any example `.gitlab-ci.yml` output

## Rationale
Minimal prompt — no negative instructions, no PIM context.
Tests what the structural pattern from GHA.ecore + GitLab docs alone produces.

Two expected outcomes, both valuable:
- LLM ignores GitHub-specific constructs → domain knowledge compensates for noisy context
- LLM carries GitHub noise into output → peer metamodel introduces contamination, measurable delta for Round 2

---

<!-- BEGIN PROMPT -->

You are working on a Model-Driven Engineering (MDE) project.
The goal is to generate a GitLab CI/CD PSM metamodel in EMF Ecore format.

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

## GitLab CI/CD YAML — keyword reference

This defines what concepts your metamodel must cover.
Use this, not the GitHub example above, to determine which classes to create.

[PASTE context/gitlab-ci-docs.md HERE]

---

## Task

Generate `gitlabMM.ecore` — an EMF Ecore metamodel for GitLab CI/CD pipelines,
covering the constructs described in the keyword reference above.

Use:
- `name="gitlabMM"`
- `nsURI="http://www.mddoai.com/mddoai/metamodel/gitlab"`
- `nsPrefix="gitlabMM"`

Output only the XML content of the `.ecore` file, starting with `<?xml ...>`.
Do not add any explanation.

<!-- END PROMPT -->
