# UC1: Onboarding — requirements checklist

Task given to every approach: "Create a GitLab CI/CD pipeline for a new
chatbot implementation that builds a Docker image, runs its unit tests,
health-checks the built image, and pushes it to the registry."

Derived directly from `reference/output.gitlab-ci.yml` (see README for its
provenance). Each item below is graded independently; "pass" for a trial
means every item passes.

1. The output is syntactically valid YAML (parses without error).
2. Defines exactly four stages, in this order: `build`, `unitTest`,
   `healthCheck`, `push`.
3. A `build` job runs `docker build -t <tag> .` (or an equivalent single
   docker-build command) using the pipeline's own build-time image tag.
4. A `unitTest` job runs on a Python image (`python:3.12-slim` or a
   compatible Python 3.12 image) and runs a real unit-test command
   (e.g. `python3 test/run_unit_tests.py` or equivalent).
5. A `healthCheck` job runs on a curl-capable image (e.g.
   `curlimages/curl:latest`) and runs a health-check script or command,
   and only runs after `build` (an explicit `needs`/dependency on `build`,
   or stage ordering that GitLab enforces the same way).
6. A `push` job logs into the container registry and pushes the built
   image, and only runs after `healthCheck` (an explicit
   `needs`/dependency on `healthCheck`, or equivalent stage ordering).
7. Default agent/image for the pipeline is a real Docker-capable image
   (e.g. `docker:latest`).
8. Uses pipeline-level variables for the image name/tag rather than
   repeating a literal image string in every job.

Deliberately out of scope for this pass (no live CI infra, see README):
whether the pipeline actually runs on GitLab.com and the built image
actually passes a real health check.
