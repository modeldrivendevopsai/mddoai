# Round 2 — + PIM metamodel

## Context given
- `context/GHA.ecore` — GitHub Actions PSM metamodel (structural template)
- `context/gitlab-ci-docs.md` — GitLab CI/CD keyword reference
- `context/pimMM.ecore` — platform-independent pipeline model (the transformation source)

## Rationale
Adds the PIM metamodel. The LLM can now see what the GitLab metamodel must be
bridgeable FROM — which PIM concepts need a GitLab counterpart and which don't.

If Round 1 produced GitHub-specific noise (Permission, ConcurrencyGroup, etc.),
add the following guard to the GHA.ecore section in this prompt:
"Use this for EMF format only. Do not port GitHub-specific concepts:
Permission, ConcurrencyGroup, GitHubContext, built-in function classes,
EVENTS/WEBHOOK_ACTIVITY_TYPES/PERMISSION_SCOPES enums — these have no equivalent in GitLab."
Document whether adding this guard was necessary.

---

<!-- BEGIN PROMPT -->

You are working on a Model-Driven Engineering (MDE) project.
The goal is to generate a GitLab CI/CD PSM metamodel in EMF Ecore format.

The metamodel sits in a transformation chain:

```
PIM model (pimMM)
    │
    ▼  [ATL transformation — to be generated later]
GitLab PSM model (gitlabMM)   ← YOU ARE GENERATING THIS
    │
    ▼  [Acceleo template]
.gitlab-ci.yml
```

---

## PIM Metamodel — what the transformation maps FROM

Every significant concept in the GitLab PSM must have a corresponding source
in the PIM. Use this to ensure the metamodel is bridgeable:
- `PIM!Pipeline` → `gitlabMM!Pipeline`
- `PIM!ScriptJob` + steps → `gitlabMM!Job` + script commands
- `PIM!LinuxAgent` + container → `gitlabMM!Image`
- `PIM!Trigger` subtypes → `gitlabMM!Workflow` rules
- `PIM!Matrix` → `gitlabMM!Parallel` + `Matrix` + `Axis`
- `PIM!DockerContainer` (services) → `gitlabMM!Service`
- `PIM!maxAttempts` → `gitlabMM!Retry`

```xml
[PASTE context/pimMM.ecore HERE]
```

---

## Reference: GitHub Actions PSM metamodel (structural template only)

Use ONLY for EMF format and structural patterns.
Do NOT port GitHub-specific concepts (Permission, ConcurrencyGroup,
GitHubContext, built-in function classes, EVENTS/WEBHOOK enums).

```xml
[PASTE context/GHA.ecore HERE]
```

---

## GitLab CI/CD YAML — keyword reference

[PASTE context/gitlab-ci-docs.md HERE]

---

## Task

Generate `gitlabMM.ecore` — an EMF Ecore GitLab CI/CD PSM metamodel
that is bridgeable from the PIM metamodel above.
Cover the constructs described in the GitLab keyword reference.

Use:
- `name="gitlabMM"`
- `nsURI="http://www.mddoai.com/mddoai/metamodel/gitlab"`
- `nsPrefix="gitlabMM"`

Output only the XML content of the `.ecore` file, starting with `<?xml ...>`.
Do not add any explanation.

<!-- END PROMPT -->
