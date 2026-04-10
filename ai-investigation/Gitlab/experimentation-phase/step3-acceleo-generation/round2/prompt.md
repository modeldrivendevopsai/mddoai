# Round 2 — Same context + reserved keyword constraint + output quality fixes

## Why Round 2

Round 1 compiled with one manual fix (`def` → `defs` parameter rename) and ran
successfully end-to-end. Four output quality issues found:

1. `def` reserved keyword as template parameter name — compile error
2. `pull_policy: always` on every image — enum default serialized when not set in model
3. Variables block indentation missing — `IMAGE_NAME: ...` not indented under `variables:`
4. `needs` items not indented under job — indent parameter not applied
5. `when: on_success` on every job — enum default serialized when not set in model

Root cause of issues 2 and 5: EMF enum attributes are never `oclIsUndefined()` —
they always return a default literal even when not explicitly set. `oclIsUndefined()`
checks do not guard against unset enums.

Round 2 adds the reserved keyword constraint (proven effective in ATL experiments)
and two new constraints for enum handling and indentation.
Constraints placed after the reference file to counter recency bias (ATL Round 6 finding).

---

## &lt;BEGIN PROMPT&gt;

You are working on a Model-Driven Engineering (MDE) project.

The goal is to write an Acceleo model-to-text template that takes a GitLab CI/CD PSM model and generates a valid `.gitlab-ci.yml` file.

---

## Source metamodel — GitLab PSM

```xml
[PASTE context/gitlabMM.ecore HERE]
```

---

## GitLab CI/CD keyword reference

This defines the YAML output format. Use it to know which YAML keys correspond to which metamodel classes and attributes.

```
[PASTE context/gitlab-ci-docs.md HERE]
```

---

## Acceleo syntax reference — ACICDTrip GHA template

This is a real Acceleo M2T template from a related project that generates GitHub Actions YAML from a GHA PSM model.
Use it as a reference for Acceleo syntax and structure only: how to write templates, queries, for loops, if conditions, and file generation.
The output format and metamodel mapping must come from the two sources above, not this example.

```
[PASTE context/gha_generate.mtl HERE]
```

---

## Constraints

**Template parameter names must not be Acceleo reserved keywords.**
In the reference file, parameters always use descriptive names that do not clash with language keywords. Follow this convention exactly.

**Do not use `oclIsUndefined()` to guard EMF enum attributes.**
EMF enum attributes are never undefined — they always return a default literal even when not explicitly set in the model. Only output an enum attribute value if the model element explicitly carries a non-default value, or omit the YAML key entirely when the attribute is not meaningful.

**Ensure correct YAML indentation throughout.**
Every YAML key must be indented relative to its parent. List items under a key must be consistently indented. String concatenation for indentation must produce the correct number of spaces at every nesting level.

---

## Task

Generate `generate.mtl` — an Acceleo template that takes a `gitlabMM::Pipeline`
and outputs a `.gitlab-ci.yml` file.

Use:

- `[module generate('http://www.mddoai.com/mddoai/metamodel/gitlab')/]`
- The main template must be named `generateElement` and annotated with `[comment @main/]`
- The main template takes a `Pipeline` element and writes to `gitlab-ci.yml`

Output only the Acceleo file content. Do not add any explanation.

## &lt;END PROMPT&gt;
