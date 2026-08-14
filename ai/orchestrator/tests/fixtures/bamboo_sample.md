# Bamboo Specs YAML Reference (excerpt)

A plan defines everything about your continuous integration build process in
Bamboo. Every plan belongs to a project and has a single stage by default, but
can be used to group jobs into multiple stages. Stages process a series of one
or more stages that are run sequentially using the same repository. Each plan
should contain at least one stage, and a stage shall have at least one job.

A job is a single build unit within a plan. One or more jobs can be organized
into one or more stages. The jobs in a stage can all be run at the same time,
in parallel, on multiple agents, if enough Bamboo agents are available. A job
checks out the repository and processes a series of one or more tasks that
are run sequentially on the same agent.

```yaml
---
version: 2
plan:
  project-key: MARS
  key: ROCKET
  name: Build the rockets
stages:
  - Build the rocket stage:
      - Build
Build:
  tasks:
    - script:
        - mkdir -p falcon/red
        - echo wings > falcon/red/wings
        - sleep 1
        - echo 'Built it'
    - test-parser:
        type: junit
        test-results: '**/junit/*.xml'
  requirements:
    - isRocketFuel
  artifacts:
    - name: Red rocket built
      pattern: falcon/red/wings
```

## Triggering builds

Specifies how the build is triggered, and the triggering dependencies between
the plan and other plans in the project. A plan can be triggered by a
repository polling schedule, a scheduled cron-style trigger, or manually by a
user through the Bamboo UI.

```yaml
Default Job:
  triggers:
    - polling:
        period: '180'
```

## Job requirements

Job requirements specify capabilities a Bamboo agent must have in order to
run a given job's tasks, for example a specific operating system or an
installed tool such as Maven or a JDK version.

```yaml
Default Job:
  key: JOB1
  requirements:
    - Operating System: Windows
    - system.p4Executable
```

## Variables

Provides for the definition of plan variables. Plan variables can be
referenced inside task scripts using the `${bamboo.variableName}` syntax, and
can be overridden per-branch or per-execution.

## Artifacts and artifact subscriptions

Artifacts must be shared: true if you wish to use them in other jobs. A job
in a later stage subscribes to the artifact from an earlier job using
artifactSubscriptions.

```yaml
stages:
  - name: Second Stage
    jobs:
      - name: Run More Tests
        artifactSubscriptions:
          - name: Docker Image
            destination: .
```

## Deployment projects and environments

A deployment project takes a completed plan result and deploys it to one or
more environments, such as QA or Production, in a defined release sequence.

```yaml
---
version: 2
deployment:
  name: Deploy Rocket
  source-plan: MARS-ROCKET
release-naming:
  next-version-name: 0.${bamboo.buildNumber}
environments:
  - QA
QA:
  tasks:
    - clean
    - artifact-download:
        destination: /
    - script:
        - echo 'Hello space'
```

## Branch configuration

Bamboo can automatically create a plan branch when a matching VCS branch is
pushed. Branch behaviour, such as whether to merge from a parent branch or
push changes back on a successful build, is configured under
branch-config.

```yaml
branch-config:
  integration:
    merge-from: release
    push-on-success: false
  disable-expiry: true
```

## Notifications and permissions

Specifies notifications of build results, sent to watchers, committers, or a
configured chat integration on build completion or failure. Also specifies
who has permission to view and configure the plan and its jobs.
