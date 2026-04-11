# Round 2 — Fix post(trim()) stripping indent on task and requirement templates

## Why Round 2

Round 1 compiled and ran cleanly but produced invalid YAML. All `tasks` and
`requirements` list items were emitted at column 0 instead of being indented
under their parent key.

Root cause: `generateTaskIndented` and `generateRequirement` both declared
`post(trim())` which strips ALL leading whitespace — including the indent
String passed as parameter.

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

```
[PASTE context/bamboo-docs.md HERE]
```

---

## Acceleo syntax reference — ACICDTrip GHA template

```
[PASTE context/gha_generate.mtl HERE]
```

---

## Constraints

**Never use `def` as a template parameter name.** It is a reserved keyword in
Acceleo. Use descriptive names such as `planDef`, `jobDef`, `taskDef` instead.

**`[comment @main/]` must be the first line inside the template body**, not
before the template declaration.

**EMF enum `toString()` returns the literal value, not the enum name.**
Always compare against the lowercase literal string from the ecore `literal=`
attribute.

**Do not use `oclIsUndefined()` to guard EMF enum attributes.**
EMF enum attributes always return a default literal even when not set.
Compare against the default literal string to decide whether to omit output.

**Do not use `post(trim())` on any template that receives an `indent` parameter
or that is called from inside an indented block.**
`post(trim())` strips ALL leading whitespace from the output, removing any
indentation the calling template expects. Either remove `post(trim())` from
those templates entirely, or apply indent after the content is built.

**Ensure correct YAML indentation throughout.**
Bamboo YAML uses 2-space indentation. List items under `tasks:` and
`requirements:` must be indented 4 spaces relative to the job root.

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
