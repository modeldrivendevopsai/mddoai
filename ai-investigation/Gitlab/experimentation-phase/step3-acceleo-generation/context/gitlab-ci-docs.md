# GitLab CI/CD YAML — Key Concepts for Metamodel Generation

Source: https://docs.gitlab.com/ci/yaml/
Curated sections relevant to building an EMF metamodel.

---

## Top-level structure

A `.gitlab-ci.yml` file has these top-level keys:

| Key | Description |
|-----|-------------|
| `stages` | Ordered list of stage names. Jobs in the same stage run in parallel. |
| `workflow` | Controls which pipeline types run (contains `rules`) |
| `default` | Default values applied to all jobs unless overridden |
| `variables` | Pipeline-level CI/CD variable definitions (key-value pairs) |
| Named job entries | Every other top-level key is a job definition |

---

## `workflow`

Controls what types of pipeline are created.

```yaml
workflow:
  rules:
    - if: $CI_COMMIT_BRANCH
    - if: $CI_PIPELINE_SOURCE == "merge_request_event"
```

---

## `default`

Sets default values for all jobs. Supports:

```yaml
default:
  image: ruby:3.0
  before_script:
    - bundle install
  after_script:
    - rm -rf tmp/
  services:
    - postgres:latest
  cache:
    paths: [vendor/]
  tags:
    - shared
  timeout: 1h
  interruptible: true
  retry:
    max: 2
```

---

## `stages`

```yaml
stages:
  - build
  - test
  - deploy
```

---

## `variables` (pipeline-level or job-level)

```yaml
variables:
  DEPLOY_ENV: production
  DEBUG: "false"
```

Each variable has: `name` (key) and `value`. May also have `description` and `expand` (boolean).

---

## Job definition

Every job is a named map. A job can have:

| Key | Type | Description |
|-----|------|-------------|
| `stage` | String | Which stage this job belongs to |
| `image` | String or map | Docker image to run the job in |
| `script` | List of strings | Shell commands to run (required) |
| `before_script` | List of strings | Commands before `script` |
| `after_script` | List of strings | Commands after `script` (always runs) |
| `services` | List | Docker services started alongside the job |
| `variables` | Map | Job-level variables |
| `artifacts` | Map | Files to save after job |
| `cache` | Map | Files to cache between jobs |
| `needs` | List | Jobs this job depends on (DAG, skips stage order) |
| `rules` | List | Conditions controlling if/when the job runs |
| `tags` | List of strings | Runner tags to select |
| `only` / `except` | List | (Legacy) branch/ref filters |
| `when` | String | `on_success` \| `on_failure` \| `always` \| `manual` \| `delayed` \| `never` |
| `allow_failure` | Boolean | If true, pipeline continues even if this job fails |
| `timeout` | String | e.g. `1h 30m` |
| `interruptible` | Boolean | Allow cancellation when newer pipeline starts |
| `resource_group` | String | Prevents concurrent runs of this job |
| `retry` | Integer or map | Auto-retry on failure |
| `parallel` | Integer or map | Run multiple instances in parallel |
| `environment` | String or map | Deployment environment |
| `dependencies` | List | Jobs whose artifacts to download (empty list = download none) |

---

## `image`

```yaml
image:
  name: ruby:3.0
  entrypoint: ["/bin/bash"]
  pull_policy: always      # always | never | if-not-present
  docker:
    platform: linux/amd64
    user: root
  kubernetes:
    user: root
```

---

## `services`

Docker containers started alongside the job.

```yaml
services:
  - name: postgres:14
    alias: db
    entrypoint: ["/bin/sh"]
    variables:
      POSTGRES_DB: mydb
    pull_policy: if-not-present
    docker:
      platform: linux/amd64
```

---

## `artifacts`

Files produced by the job, stored in GitLab.

```yaml
artifacts:
  name: my-artifact
  paths:
    - build/libs/*.jar
    - target/*.jar
  when: always          # on_success | on_failure | always
  expire_in: 1 week
  expose_as: "Build output"
  untracked: false
  exclude:
    - "*.tmp"
  reports:
    junit: build/test-results/**/*.xml
    coverage_report:
      coverage_format: cobertura
      path: coverage.xml
    dotenv: build/vars.env
```

---

## `cache`

```yaml
cache:
  key: $CI_COMMIT_REF_SLUG
  paths:
    - node_modules/
    - .gradle/
  untracked: false
  when: on_success
```

The `key` can also be structured:
```yaml
cache:
  key:
    files:
      - package-lock.json
    prefix: node
```

---

## `needs`

Defines a DAG (directed acyclic graph) of job dependencies, bypassing stage ordering.

```yaml
needs:
  - job: build
    artifacts: true
  - job: lint
    artifacts: false
    optional: true
  - job: cross-project-job
    project: org/other-repo
    ref: main
    artifacts: true
    pipeline: $PARENT_PIPELINE_ID
```

---

## `rules`

Conditions under which the job is added to the pipeline.

```yaml
rules:
  - if: $CI_COMMIT_BRANCH == "main"
    when: on_success
  - if: $CI_PIPELINE_SOURCE == "merge_request_event"
    when: manual
    allow_failure: true
  - changes:
      - src/**/*.js
    when: on_success
  - exists:
      - Dockerfile
```

Each rule can have: `if` (string expression), `when`, `changes` (list), `exists` (list), `allow_failure`, `variables`.

---

## `retry`

```yaml
retry: 2
# or:
retry:
  max: 2
  when:
    - runner_system_failure
    - stuck_or_timeout_failure
```

---

## `parallel`

Run the same job N times in parallel:
```yaml
parallel: 5
```

Or with a matrix (each combination becomes a separate job instance):
```yaml
parallel:
  matrix:
    - PROVIDER: [aws, gcp]
      STACK: [monitoring, app]
```

---

## `environment`

```yaml
environment:
  name: production
  url: https://example.com
  on_stop: stop_production
  action: start             # start | stop | prepare | verify | access
  auto_stop_in: 1 week
  kubernetes:
    namespace: production
```

---

## `tags`

Select a GitLab Runner by tags:
```yaml
tags:
  - docker
  - shared
```

---

## `only` / `except` (legacy, still used)

```yaml
only:
  - main
  - /^release-.*$/
```
