# Round 2 — Notes

## Run date
2026-04-08

## Context given
- `context/gitlabMM.ecore`
- `context/gha_generate.mtl`
- `context/gitlab-ci-docs.md`
- Added: reserved keyword constraint (placed after reference)
- Added: do not use `oclIsUndefined()` for EMF enum attributes
- Added: correct YAML indentation throughout
- Added: main template must be named `generateElement`

---

## Round 1 bugs — status in Round 2

### `def` reserved keyword — NOT FIXED
Still used as template parameter name in `generateDefault(def : Default)`.
Compile error — fixed manually again.
The constraint "template parameter names must not be Acceleo reserved keywords"
was not specific enough. The word `def` needs to be named explicitly.

### `pull_policy: always` on every image — ELIMINATED ✓
Enum comparison against string `'ALWAYS'` used to skip default. Worked correctly.

### `when: on_success` on every job — ELIMINATED ✓
Same pattern. Only non-default enum values now serialized.

### Variables indentation — PARTIALLY FIXED
`variables:` key now present and indented. But value is on a separate line:
```yaml
variables:
  IMAGE_NAME:
    ${CI_REGISTRY_IMAGE}/framework-chatbot-image   ← should be inline
```
Should be: `IMAGE_NAME: "${CI_REGISTRY_IMAGE}/framework-chatbot-image"`

### `needs` indentation — FIXED ✓
Items correctly indented. Shorthand form `- build` is valid GitLab CI/CD syntax,
not a bug. Both `- build` and `- job: build` are accepted.

---

## New bugs found

### `[comment @main/]` placed outside template body
Generated as:
```
[comment @main /]
[template public generateElement(pipeline : Pipeline)]
```
Must be **inside** the template body as its first line:
```
[template public generateElement(pipeline : Pipeline)]
[comment @main /]
```
Fixed manually. Causes "no model elements match" error at runtime.

### `default` block image over-indented
```yaml
default:
    image:        ← 4 spaces, should be 2
      name: ...   ← 6 spaces, should be 4
```

---

## Compiles in Eclipse Acceleo?
No — `def` keyword error again. Fixed manually.

## Runs successfully?
Yes — after manual fixes. Output structurally valid but has indentation
and variable formatting issues.
