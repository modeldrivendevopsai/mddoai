# Round 1 — GitLab PSM + GHA Acceleo example + GitLab documentation

## Context given
- `context/gitlabMM.ecore` — source metamodel (GitLab PSM, generated in Step 1)
- `context/gha_generate.mtl` — ACICDTrip Acceleo example (GHA), for Acceleo syntax reference only
- `context/gitlab-ci-docs.md` — GitLab CI/CD keyword reference, for output format

---

## &lt;BEGIN PROMPT&gt;

You are working on a Model-Driven Engineering (MDE) project.

The goal is to write an Acceleo model-to-text template that takes a GitLab CI/CD
PSM model and generates a valid `.gitlab-ci.yml` file.

---

## Source metamodel — GitLab PSM

```xml
[PASTE context/gitlabMM.ecore HERE]
```

---

## GitLab CI/CD keyword reference

This defines the YAML output format. Use it to know which YAML keys correspond
to which metamodel classes and attributes.

```
[PASTE context/gitlab-ci-docs.md HERE]
```

---

## Acceleo syntax reference — ACICDTrip GHA template

This is a real Acceleo M2T template from a related project that generates
GitHub Actions YAML from a GHA PSM model.
Use it as a reference for Acceleo syntax and structure only: how to write
templates, queries, for loops, if conditions, and file generation.
The output format and metamodel mapping must come from the two sources above,
not this example.

```
[PASTE context/gha_generate.mtl HERE]
```

---

## Task

Generate `generate.mtl` — an Acceleo template that takes a `gitlabMM::Pipeline`
and outputs a `.gitlab-ci.yml` file.

Use:
- `[module generate('http://www.mddoai.com/mddoai/metamodel/gitlab')/]`
- The main template must be annotated with `[comment @main/]`
- The main template takes a `Pipeline` element and writes to `gitlab-ci.yml`

Output only the Acceleo file content. Do not add any explanation.

## &lt;END PROMPT&gt;
