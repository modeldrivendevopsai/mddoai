# Round 1 — PIM metamodel + GitLab PSM metamodel + existing ATL example

## Context given
- `context/pimMM.ecore` — source metamodel
- `context/gitlabMM.ecore` — target metamodel (generated in Step 1)
- `context/pim2gitlabmodel.atl` — existing ATL for the same source, different target

## Rationale
The existing ATL already maps `pimMM` to a GitLab PSM. The target class names
have changed (e.g. `GitlabRule` → `Rule`, `Script` flattened into `Job.script`).
The LLM must write a new ATL targeting the Step 1 metamodel class names.

---

<!-- BEGIN PROMPT -->

You are working on a Model-Driven Engineering (MDE) project.

The project transforms a platform-independent pipeline model (PIM) into a
GitLab CI/CD pipeline model (PSM) using an ATL model-to-model transformation.
The PSM is then serialized to `.gitlab-ci.yml` by an Acceleo template.

---

## Source metamodel — PIM

```xml
[PASTE context/pimMM.ecore HERE]
```

---

## Target metamodel — GitLab PSM

```xml
[PASTE context/gitlabMM.ecore HERE]
```

---

## Reference ATL transformation

This is an existing ATL transformation for the same source metamodel (PIM),
but targeting a different version of the GitLab PSM with different class names.
Use it as a structural and syntactic reference — the mapping logic is the same,
only the target class names must match the new metamodel above.

```atl
[PASTE context/pim2gitlabmodel.atl HERE]
```

---

## Task

Generate `pim2gitlab.atl` — a new ATL transformation that maps PIM model
instances to GitLab PSM model instances using the target metamodel above.

Key mappings to implement:
- `PIM!Pipeline` → `gitlabMM!Pipeline` (stages, jobs, variables, workflow)
- `PIM!ScriptJob` → `gitlabMM!Job` (script as flat string list, not Script class)
- `PIM!LinuxAgent` → `gitlabMM!Image`
- `PIM!Trigger` subtypes → `gitlabMM!Workflow` rules
- `PIM!Matrix` → `gitlabMM!Parallel` + `gitlabMM!MatrixEntry`
- `PIM!DockerContainer` (services) → `gitlabMM!Service`
- `PIM!maxAttempts` → `gitlabMM!Retry`
- `PIM!Job.ifCondition` → `gitlabMM!Rule`

Use:
- `-- @nsURI PIM=pimMM=http://www.mddoai.com/mddoai/metamodel/pim`
- `-- @nsURI GitLabMM=gitlabMM=http://www.mddoai.com/mddoai/metamodel/gitlab`
- `module pim2gitlab;`
- `create OUT : GitLabMM from IN : PIM;`

Output only the ATL file content. Do not add any explanation.

<!-- END PROMPT -->
