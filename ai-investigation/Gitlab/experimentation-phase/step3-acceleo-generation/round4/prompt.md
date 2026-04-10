# Round 4 — Fix enum literal vs name mismatch in toString() comparisons

## Why Round 4

Round 3 compiled cleanly and fixed `def` and `@main` placement. Two bugs remain:

1. `pull_policy: always` emitted on every image — enum `toString()` comparison uses wrong case.
2. `when: on_success` emitted on every job — same root cause.

Root cause: in Acceleo, calling `toString()` on an EMF enum attribute returns the
**literal** value (the `literal=` attribute in the ecore), not the enum **name**.
The ecore defines enums like `name="ALWAYS" literal="always"` — so `toString()`
returns `'always'`, not `'ALWAYS'`. Comparisons against uppercase names are always true.

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

**EMF enum `toString()` returns the literal value, not the enum name.**
In the ecore, each enum literal has a `name` and a `literal`. Acceleo's `toString()`
returns the `literal` (the serialization string), which may differ in case from the name.
For example, `PullPolicy` has `name="ALWAYS" literal="always"` — so
`img.pullPolicy.toString()` returns `'always'`, not `'ALWAYS'`.
Always compare against the lowercase literal string, not the uppercase enum name:
- Use `img.pullPolicy.toString() <> 'always'` to skip the default pull policy.
- Use `j.when.toString() <> 'on_success'` to skip the default when value.
Check the ecore `literal=` attributes for the exact string to compare against.

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
