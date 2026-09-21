# Approach comparison: S1 / S2 / S3

Compares three ways of producing a CI/CD pipeline (S1: PSM + LLM one-shot,
S2: architecture description + LLM one-shot, S3: MDDOAI's real transformation
chain) on the same fixed tasks. This folder holds the frozen inputs, the
requirements checklist each output is graded against, and one row per trial.

## Inputs

- **S1** gets a REAL, populated PSM model instance, produced by actually
  running the real, hand-authored transformation chain
  (`main.java.Main swarch2gitlab`, which runs the real `swarch2pim.atl` then
  the real `pim2gitlabmodel.atl`), not a bare PSM metamodel/schema.
- **S2** gets the REAL architecture model instance (a real `.swarch` file)
  and nothing else beyond the shared task text: no hand-written architecture
  description, no platform documentation.
- Both S1 and S2 receive the identical task/quality-gate text, so the only
  real difference between them is which model instance they're given.

## Provenance

`tasks/uc1-onboarding/reference/architecture.swarch` is copied unmodified
from this repo's own real, git-committed test fixture,
`main/src/test/resources/testCases/swarch2pim/input1.swarch`.

`tasks/uc1-onboarding/reference/psm_instance.gitlabmm` is a REAL, freshly
generated PSM model instance, produced by running that same `.swarch` file
through the real transformation chain above, not copied from anywhere.

## Human-effort recording

Every trial's "active minutes" and "intervention count" fields are the
agent's own wall-clock time and repair-cycle count, not a human developer's.
This is a disclosed proxy, not a claim about real human usability.

## Scope of "success" without live CI infra

No real CI runner is available. "First-attempt success" and "final success"
mean: the output is syntactically valid YAML, and it satisfies every item in
the task's `requirements.md` checklist by structural inspection. Claims like
"the deployed application passes its smoke test" are out of scope.
