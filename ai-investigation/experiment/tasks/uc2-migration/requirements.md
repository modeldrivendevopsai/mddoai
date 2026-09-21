# UC2: Migration — requirements checklist

Task given to every approach: "The chatbot-app pipeline currently runs on
GitLab. Migrate it to Bamboo: build a Docker image, run its unit tests,
health-check the built image, and push it to the registry, using Bamboo's
own real configuration format."

Derived directly from a real Bamboo reference chain: the real, validated
`pim2bamboo.atl` and `generate.mtl` (see `reference/`, pulled from this
project's own prior real research on the `ai-research` branch, confirmed
working by actually executing both against the same real chatbot PIM
instance used for UC1) produced `reference/output.bamboo.yaml`. Each item
below is graded independently; "pass" means every item passes.

1. The output is syntactically valid YAML (parses without error).
2. Declares a real Bamboo `plan` with a `project-key`/`key` and `name`.
3. Defines a `stages` section listing all four real jobs (build, unit test,
   health check, push) in a real stage/job structure Bamboo understands
   (Bamboo's own real convention: jobs listed under one or more named
   stages, not a flat top-level list the way GitLab does it).
4. A build-role job runs a `docker build` command.
5. A test-role job runs the real unit test command
   (`python3 test/run_unit_tests.py` or equivalent) and specifies a real
   Linux capability/requirement, matching Bamboo's own real
   `requirements: - os.linux` convention for jobs that need it.
6. A health-check-role job runs a real health-check command
   (`./scripts/health_check.sh` or equivalent).
7. A push-role job logs into the registry and pushes the built image.
8. Uses pipeline-level `variables` for the image name/tag rather than
   repeating a literal image string in every job.

Deliberately out of scope for this pass (no live CI infra): whether the
pipeline actually runs on a real Bamboo server and the built image actually
passes a real health check.
