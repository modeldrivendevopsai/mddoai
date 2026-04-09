# Round 1 — Notes

## Run date
2026-04-08

## Context given
- `context/gitlabMM.ecore`
- `context/gha_generate.mtl`
- `context/gitlab-ci-docs.md`

---

## Class name correctness

All 19 classes from gitlabMM.ecore referenced correctly:
Pipeline, Workflow, Default, Variable, Job, Image, DockerOptions, KubernetesOptions,
Service, Artifacts, ArtifactReports, CoverageReport, Cache, CacheKey, Need, Rule,
Retry, Parallel, MatrixEntry, Environment — all ✓

---

## Issues found (pre-run analysis)

### 1. Enum `.toString()` — likely wrong YAML values

Used throughout:
```
[job.when.toString()/]         → likely outputs ON_SUCCESS not on_success
[art.when.toString()/]         → likely outputs ON_SUCCESS not on_success
[cache.when.toString()/]       → likely outputs ON_SUCCESS not on_success
[w.toString()/]                → RetryWhenType literals
[image.pullPolicy.toString()/] → PullPolicy literals
[env.action.toString()/]       → EnvironmentAction literals
```

In EMF/Acceleo, `.toString()` on an EEnum value returns the enum name (e.g. `ON_SUCCESS`)
not the `literal` attribute value (e.g. `on_success`). GitLab CI/CD requires lowercase
literals. This will not crash the compiler but will produce invalid YAML values.

Not present in `gha_generate.mtl` — GHA avoids enums by mapping them through
Java `invoke()` service calls. The LLM had no reference pattern for enum serialization.

---

---

## Compile errors found

### 2. `def` reserved keyword as template parameter name — 18 compile errors

```
[template public generateDefault(def : Default) post(trim())]
```

`def` is a reserved keyword in Acceleo (same as ATL). All 18 errors point to lines
inside `generateDefault` where `def.beforeScript`, `def.afterScript`, `def.image` etc.
are accessed — the parser fails on every use of the parameter named `def`.

Not present in `gha_generate.mtl` — no template parameter named `def` anywhere.
LLM-introduced from naming intuition (parameter named after the class `Default`).

Same root cause as ATL Round 1 Bug 1. The `gha_generate.mtl` reference was not
sufficient to prevent this — the constraint about reserved keywords was never stated.

---

## Compiles in Eclipse Acceleo?
No — 18 errors, all `token "def" is invalid`. Fixed manually: renamed parameter `def` → `defs`.
Also renamed main template from `generatePipeline` to `generateElement` to match `Generate.class`.

## Runs successfully?
Yes — after manual fixes. Full chain SWArch → PIM → GitLab PSM → YAML works.

## Output issues (not crashes, but incorrect YAML)

### 3. `pull_policy: always` on every image
`PullPolicy` enum default value (`ALWAYS`) is serialized even when not set in the model.
The template checks `image.pullPolicy.oclIsUndefined()` but EMF enums are never undefined —
they return the default literal. Should check against the default value explicitly or omit.

### 4. Variables block missing indentation
```yaml
variables:       ← correct
IMAGE_NAME: ...  ← should be indented 2 spaces
```
Indentation missing in `generateVariableEntry` template.

### 5. `needs` items not indented under job
```yaml
  needs:
- job: build   ← should be indented 4 spaces
```
The `indent` parameter passed to `generateNeedItem` is not being applied correctly.

### 6. `when: on_success` on every job
Same root cause as `pull_policy` — `WhenType` enum default (`ON_SUCCESS`) serialized
even when not explicitly set on the job in the model.

