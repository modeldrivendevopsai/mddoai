# Round 3 — Explicit fix for def keyword + @main placement + remaining output issues

## Why Round 3

Round 2 ran successfully after two manual fixes and produced mostly correct YAML.
Remaining issues:

1. `def` still used as template parameter — compile error, same as Round 1.
   General "no reserved keywords" instruction insufficient — must name `def` explicitly.
2. `[comment @main/]` placed before the template declaration instead of inside its body.
3. `default` image block over-indented.

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

## Constraints

**Never use `def` as a template parameter name.** It is a reserved keyword in
Acceleo and will cause a compile error. Use descriptive names such as `glDefault`,
`gitlabDefault`, or `defaultBlock` instead.

**`[comment @main/]` must be the first line inside the template body**, not before
the template declaration. Correct placement:
```
[template public generateElement(pipeline : Pipeline)]
[comment @main/]
[file ('gitlab-ci.yml', false, 'UTF-8')]
```

**Do not use `oclIsUndefined()` to guard EMF enum attributes.**
EMF enum attributes always return a default literal even when not set.
Compare against the default literal string to decide whether to omit output.

**Ensure correct YAML indentation throughout.**
Every YAML key must be indented relative to its parent. Use the `indent` parameter
consistently — never hardcode spaces when a template already receives an indent string.

---

## Task

Generate `generate.mtl` — an Acceleo template that takes a `gitlabMM::Pipeline`
and outputs a `.gitlab-ci.yml` file.

Use:
- `[module generate('http://www.mddoai.com/mddoai/metamodel/gitlab')/]`
- The main template must be named `generateElement` and annotated with `[comment @main/]`
  as the first line inside the template body
- The main template takes a `Pipeline` element and writes to `gitlab-ci.yml`

Output only the Acceleo file content. Do not add any explanation.

## &lt;END PROMPT&gt;
