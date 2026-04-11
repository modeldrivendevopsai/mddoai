# Round 2 — Notes

## Run date
2026-04-11

## Context given
- `context/bambooMM.ecore` (Step 1 Round 3 output)
- `context/gha_generate.mtl` (ACICDTrip GHA Acceleo reference)
- `context/bamboo-docs.md` (Bamboo Specs v10.0.2)
- New constraint: do not use `post(trim())` on any template that receives an `indent` parameter
  or is called from inside an indented block

Note: Round 1 output was NOT included in the Round 2 prompt. The LLM regenerated the
template from scratch with the added constraint — same pattern as GitLab experiment.

---

## Errors found
None — compiled clean, ran clean.

---

## Root cause
Round 1 used `post(trim())` on `generateTaskIndented` and `generateRequirement`. Both
templates receive an indent string as parameter and are called from inside indented blocks.
`post(trim())` strips ALL leading whitespace from the emitted text, including the indent
string, so every task and requirement line was output at column 0.

Adding the explicit `post(trim())` constraint to the prompt was sufficient. The LLM did not
regress any other behaviour.

---

## Compilable / runs without error?
Yes — clean compile, clean runtime execution on all three test cases.

---

## Key findings
- All three tests generated valid, correctly-indented `bamboo.yaml` output:
  - **test1-chatbot**: plan, stages, 4 jobs, tasks, requirements, variables — all correct
  - **test2-all-pim**: 11 jobs, polling and cron triggers, cache as `any-task`, artifacts,
    requirements — all correct; cron expression emits `"0"` (known Step 2 ATL issue, not Acceleo)
  - **test3-hello-java**: clean 3-job Gradle pipeline — correct
- The `post(trim())` constraint resolved the indentation bug introduced in Round 1 with
  zero regressions — single-constraint, single-round fix
- Step 3 complete in 2 rounds
