# UC3: New rule — requirements checklist

Task given to every approach: "The chatbot-app pipeline on Bamboo already
builds a Docker image, runs its unit tests, health-checks the built image,
and pushes it to the registry. Add a new, mandatory secret-scanning quality
gate: scan the repository for leaked secrets after the health check and
before the image is pushed, using a real secret-scanning tool (e.g.
`gitleaks`). If the scan finds a secret, the pipeline must not proceed to
push the image."

Derived directly from a real, updated reference chain: the real chatbot PIM
instance (see `reference/input.pimmm`) was given a new `secretScan` job,
wired with real `previous`/`next` job-ordering references so it runs after
`healthCheck` and before `push`, then run through the real, validated
`pim2bamboo.atl` (the same real, production ATL used for UC2's migration,
unmodified) to produce `reference/psm_instance.bambooMM`, confirmed for
real to correctly place the new job between `healthCheck` and `push` in
Bamboo's own real stage job order. Each item below is graded
independently; "pass" means every item passes.

1. The output is syntactically valid YAML (parses without error).
2. Every job from the existing pipeline is still present and correct: a
   build job (docker build), a unit-test job (Python unit tests), a
   health-check job (curl-capable image or equivalent), and a push job
   (login + push).
3. A new job runs a real secret-scanning command (e.g. `gitleaks detect`, or
   another real, named secret-scanning tool's own real CLI invocation, not
   a placeholder comment).
4. That secret-scanning job's name comes after the health-check job's name
   in its stage's own `jobs:` order (Bamboo's own real convention: a
   stage's `jobs:` list is itself the ordering mechanism — there is no
   per-job `needs`/`stage` field the way GitLab has one).
5. The push job's name comes after the secret-scanning job's name in its
   stage's own `jobs:` order — this is the real gate: a secret-scan failure
   must block the push, not just run alongside it.
6. Existing functionality is not broken: none of the four original jobs
   were removed, renamed away from their original role, or had their own
   real commands altered.
7. Uses pipeline-level variables for the image name/tag rather than
   repeating a literal image string in every job (same requirement as
   UC1/UC2's own checklists — this pipeline already existed before this
   change and that requirement did not stop applying to it).

Deliberately out of scope for this pass (no live CI infra, see README):
whether the pipeline actually runs on a real Bamboo server, and whether the
scan tool genuinely detects a real, planted dummy secret in a live run.
