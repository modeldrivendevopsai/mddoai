# Round 1 — Bamboo PSM + GHA Acceleo example + Bamboo documentation

## Context given
- `context/bambooMM.ecore` — source metamodel (Bamboo PSM, generated in Step 1 Round 3)
- `context/gha_generate.mtl` — ACICDTrip Acceleo example (GHA), for Acceleo syntax reference only
- `context/bamboo-docs.md` — Bamboo CI/CD YAML reference, for output format
- All GitLab Acceleo learnings pre-applied as constraints

---

## BEGIN PROMPT

You are working on a Model-Driven Engineering (MDE) project.

The goal is to write an Acceleo model-to-text template that takes a Bamboo CI/CD
PSM model and generates a valid `bamboo.yaml` file.

---

## Source metamodel — Bamboo PSM

```xml
[PASTE context/bambooMM.ecore HERE]
```

---

## Bamboo CI/CD YAML reference

This defines the YAML output format. Use it to know which YAML keys correspond
to which metamodel classes and attributes.

```
[PASTE context/bamboo-docs.md HERE]
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
Acceleo and will cause a compile error. Use descriptive names such as `planDef`,
`bambooDefault`, or `planElement` instead.

**`[comment @main/]` must be the first line inside the template body**, not before
the template declaration. Correct placement:
```
[template public generateElement(plan : Plan)]
[comment @main/]
[file ('bamboo.yaml', false, 'UTF-8')]
```

**Do not use `oclIsUndefined()` to guard EMF enum attributes.**
EMF enum attributes always return a default literal even when not set.
Compare against the default literal string to decide whether to omit output.

**EMF enum `toString()` returns the literal value, not the enum name.**
In the ecore, each enum literal has a `name` and a `literal`. Acceleo's `toString()`
returns the `literal` (the serialization string), which may differ in case from the name.
For example, if an enum has `name="ALWAYS" literal="always"` — then
`attr.toString()` returns `'always'`, not `'ALWAYS'`.
Always compare against the lowercase literal string from the ecore.
Check the ecore `literal=` attributes for the exact string to compare against.

**Ensure correct YAML indentation throughout.**
Bamboo YAML uses 2-space indentation. Every YAML key must be indented relative
to its parent. Use the `indent` parameter consistently in sub-templates.

---

## Task

Generate `generate.mtl` — an Acceleo template that takes a `bambooMM::Plan`
and outputs a `bamboo.yaml` file.

Use:
- `[module generate('http://www.mddoai.com/mddoai/metamodel/bamboo')/]`
- The main template must be named `generateElement` and annotated with `[comment @main/]`
  as the first line inside the template body
- The main template takes a `Plan` element and writes to `bamboo.yaml`

Output only the Acceleo file content. Do not add any explanation.

## END PROMPT
