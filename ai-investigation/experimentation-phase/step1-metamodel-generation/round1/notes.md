# Round 1 — Notes

## Run date
2026-04-06

## Context given
- `context/GHA.ecore` (ACICDTrip GitHub Actions metamodel)
- `context/gitlab-ci-docs.md`

---

## Classes generated (20 classes + 6 enums)

Classes: Pipeline, Workflow, Default, Variable, Job, Image, DockerOptions,
KubernetesOptions, Service, Artifacts, ArtifactReports, CoverageReport,
Cache, CacheKey, Need, Rule, Retry, Parallel, MatrixEntry, Environment

Enums: WhenType, ArtifactsWhenType, CacheWhenType, PullPolicy,
EnvironmentAction, RetryWhenType

---

## Differences from existing gitlabMM.ecore

### Renamed classes (same concept, different name)
| Output | Existing | Note |
|--------|----------|------|
| `Artifacts` | `Artifact` | singular vs plural |
| `ArtifactReports` | `Report` | more explicit name |
| `Rule` | `GitlabRule` | generic vs platform-prefixed |
| `DockerOptions` | `Docker` | more descriptive |
| `KubernetesOptions` | `Kubernetes` | more descriptive |
| `CacheKey` | `Key` | more explicit name |

### Wrapper classes collapsed to flat string attributes
The existing metamodel uses dedicated wrapper classes for scripts, tags, and paths.
The output flattened these to `EString[*]` attributes directly on `Job`:

| Existing classes | Output equivalent |
|-----------------|-------------------|
| `Script → Command[*]` | `Job.script : EString[*]` |
| `BeforeScript → Command[*]` | `Job.beforeScript : EString[*]` |
| `AfterScript → Command[*]` | `Job.afterScript : EString[*]` |
| `Tags → Tag[*]` | `Job.tags : EString[*]` |
| `Only → OnlyBranches[*]` | `Job.only : EString[*]` |
| `Dependencies → Dependency[*]` | `Job.dependencies : EString[*]` |
| `Variables` (wrapper) | direct `Variable[*]` list, no wrapper |
| `Path` | `Artifacts.paths : EString[*]` |
| `Matrix → Axis[*]` | `Parallel.matrix → MatrixEntry[*]` (key + values[]) |

Simpler design — removes one level of indirection. The generated ATL in Step 2
will target these flat attributes instead.

### Extra classes not in existing metamodel
- `CoverageReport` — split out from reports, reflects the docs detail
- 6 typed enums for `when`, `pullPolicy`, `action`, `retryWhen` — existing metamodel
  uses plain EString for these. More type-safe, makes them explicit domain values.

---

## Key findings

### No GitHub contamination
No `Permission`, `ConcurrencyGroup`, `GitHubContext`, `EVENTS` enum, or any
other GitHub-specific construct appeared.
Claude's domain knowledge correctly filtered the noisy context without any explicit instruction.

### Flattening pattern
The LLM consistently chose a simpler flat design over the wrapper class pattern.
This is a valid design decision and Step 2 ATL will be written against it.
Worth noting in the paper: LLM tends toward simpler structures when not constrained.

---

## Compilable in Eclipse EMF?
Not yet tested. EMF format looks syntactically correct.
