# Round 1 — Notes

## Run date
<!-- fill in -->

## Context given
- `context/pimMM.ecore`
- `context/gitlabMM.ecore` (from Step 1 round1)
- `context/pim2gitlabmodel.atl`

---

## Key mappings to verify

| PIM concept | Expected ATL target | Correct? |
|-------------|--------------------:|---------|
| `PIM!Pipeline` | `gitlabMM!Pipeline` | |
| `PIM!ScriptJob` | `gitlabMM!Job` | |
| `PIM!LinuxAgent` | `gitlabMM!Image` | |
| `PIM!Trigger` subtypes | `gitlabMM!Workflow` rules | |
| `PIM!Matrix` | `gitlabMM!Parallel` + `MatrixEntry` | |
| `PIM!DockerContainer` | `gitlabMM!Service` | |
| `PIM!maxAttempts` | `gitlabMM!Retry` | |
| `PIM!Job.ifCondition` | `gitlabMM!Rule` | |
| script steps | `Job.script : EString[*]` flat list | |

## Class name errors
<!-- any GitLabMM!ClassName references that don't match gitlabMM.ecore -->

## ATL syntax issues
<!-- missing lazy/unique lazy, wrong tuple syntax, OCL errors -->

## Runs successfully?
<!-- yes / no / not tested -->

## Output XMI correct?
<!-- does the output model reference correct class names from gitlabMM.ecore -->

## Observations
<!-- what did the existing ATL example enable? what did it miss? -->
