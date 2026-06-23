# TeamCity Kotlin DSL — CI/CD Pipeline Concepts for PSM Metamodel

This document summarizes the key TeamCity Kotlin DSL pipeline concepts relevant to modeling a CI/CD platform-specific metamodel (PSM). All information is based on the official JetBrains TeamCity documentation.

---

## 1. Pipeline Structure (Project)

TeamCity organizes CI/CD configuration hierarchically. The top-level entity is a **Project**, which contains build configurations, templates, VCS roots, parameters, and subprojects.

- Entry point: `settings.kts` in the `.teamcity` directory.
- Projects can be nested (subprojects).
- A project references its build configurations (BuildType), VCS roots, and templates.

```kotlin
project {
    buildType(Compile)
    buildType(Test)
    buildType(Deploy)
}
```

### Key Properties
| Property | Description |
|---|---|
| `id` | Unique identifier for the project |
| `name` | Display name |
| `description` | Optional description |
| `buildType` | Contained build configurations |
| `template` | Reusable configuration templates |
| `vcsRoot` | Version control root definitions |
| `subProject` | Nested child projects |
| `params` | Project-level parameters (inherited by children) |
| `buildTypesOrder` | Explicit ordering of build configurations |

---

## 2. Build Configurations (BuildType)

A **BuildType** is the central unit of work in TeamCity — equivalent to a "job" in other CI/CD systems. It defines what to build, how to build it, and when to trigger it.

```kotlin
object MyBuild : BuildType({
    id("MyBuild")
    name = "My Build"
    description = "Compiles and tests the project"

    vcs { root(MyVcsRoot) }
    steps { /* ... */ }
    triggers { /* ... */ }
    dependencies { /* ... */ }
    features { /* ... */ }
    params { /* ... */ }
    requirements { /* ... */ }

    artifactRules = "build/output/**/*.jar => artifacts"
    buildNumberPattern = "1.0.%build.counter%"
    maxRunningBuilds = 3
})
```

### Key Properties
| Property | Description |
|---|---|
| `id` | Unique identifier (string) |
| `name` | Display name |
| `description` | Optional description |
| `buildNumberPattern` | Pattern for build numbering (supports `%build.counter%`, `%build.vcs.number%`) |
| `artifactRules` | Rules for publishing build artifacts |
| `maxRunningBuilds` | Limit on concurrent executions (0 = unlimited) |
| `allowExternalStatus` | Enable status widget for external pages |
| `enablePersonalBuilds` | Allow developer personal builds |
| `type` | Regular or composite/deployment build |

---

## 3. Build Steps

Steps define the actual work executed sequentially on a build agent. Each step has a runner type.

```kotlin
steps {
    script {
        name = "Run tests"
        id = "runTests"
        scriptContent = "npm test"
        executionMode = BuildStep.ExecutionMode.DEFAULT
    }
    maven {
        name = "Build"
        goals = "clean package"
        jdkHome = "%env.JDK_21%"
    }
    gradle {
        name = "Gradle Build"
        tasks = "clean build"
    }
    dockerCommand {
        name = "Build Image"
        commandType = build {
            source = file { path = "Dockerfile" }
        }
    }
}
```

### Runner Types (Step Types)
| Runner | DSL Method | Description |
|---|---|---|
| Command Line | `script {}` | Executes shell/batch scripts |
| Maven | `maven {}` | Runs Maven goals |
| Gradle | `gradle {}` | Runs Gradle tasks |
| .NET | `dotnetBuild {}`, `dotnetTest {}` | .NET CLI operations |
| Docker | `dockerCommand {}` | Docker build/push/other |
| PowerShell | `powerShell {}` | PowerShell scripts |
| Python | `python {}` | Python script execution |
| Xcode | `xcode {}` | Xcode project builds |
| NAnt | `nant {}` | NAnt build scripts |
| Node.js | `nodeJS {}` | Node.js script execution |

### Step Properties
| Property | Description |
|---|---|
| `name` | Display name |
| `id` | Unique step identifier (used for status tracking) |
| `executionMode` | When to run: `DEFAULT` (only if successful), `RUN_ON_FAILURE`, `ALWAYS`, `RUN_ONLY_IF_PREVIOUS_STEP_FAILED` |
| `conditions` | Additional execution conditions |
| `enabled` | Whether the step is active |

### Execution Modes
- **DEFAULT** — Only if build status is successful (server-side check)
- **RUN_ON_FAILURE** — Even if some previous steps failed
- **ALWAYS** — Even if build stop command was issued
- **RUN_ONLY_IF_PREVIOUS_STEP_FAILED** — Only if build status is failed (for cleanup/rollback)
- **RUN_IF_ALL_PREVIOUS_SUCCEEDED** — Agent-side only validation

---

## 4. Triggers

Triggers define when builds are automatically started.

```kotlin
triggers {
    vcs {
        branchFilter = "+:*"
        quietPeriodMode = VcsTrigger.QuietPeriodMode.USE_DEFAULT
        perCheckinTriggering = true
        groupCheckinsByCommitter = true
        enableQueueOptimization = true
        triggerRules = "+:root=${MyVcsRoot.id}:**"
    }
    schedule {
        schedulingPolicy = cron { cronExpression = "0 0 2 * * ?" }
        branchFilter = "+:<default>"
        triggerBuild = always()
        withPendingChangesOnly = true
    }
    finishBuildTrigger {
        buildType = "${UpstreamBuild.id}"
        successfulOnly = true
        branchFilter = "+:<default>"
    }
    retryBuild {
        delaySeconds = 60
        attempts = 3
    }
}
```

### Trigger Types
| Type | DSL Method | Description |
|---|---|---|
| VCS Trigger | `vcs {}` | Triggers on VCS changes; supports quiet period, per-checkin triggering, trigger rules, branch filters |
| Schedule Trigger | `schedule {}` | Triggers at specified times; supports cron expressions, daily/weekly modes, pending changes check |
| Finish Build Trigger | `finishBuildTrigger {}` | Triggers when another build configuration finishes |
| Retry Build Trigger | `retryBuild {}` | Retries failed builds with configurable delay and max attempts |
| Branch Remote Run | `branchRemoteRunTrigger {}` | Triggers personal builds on branch changes (Git/Mercurial) |
| Maven Artifact Dependency | `mavenArtifact {}` | Triggers on Maven artifact changes |
| Maven Snapshot Dependency | `mavenSnapshot {}` | Triggers on Maven snapshot changes |
| NuGet Dependency | `nuGetDependency {}` | Triggers on NuGet package updates |

### Common Trigger Properties
| Property | Description |
|---|---|
| `branchFilter` | `+|-:<branch_pattern>` rules to filter branches |
| `triggerRules` | `+|-:VCS_username:VCS_root_id:path_pattern` rules |
| `enabled` | Whether the trigger is active |

---

## 5. Dependencies

TeamCity supports two types of dependencies that together form **build chains**.

### 5.1 Snapshot Dependencies

Snapshot dependencies create build chains where upstream builds must complete before downstream builds start. They ensure revision synchronization across the chain.

```kotlin
dependencies {
    snapshot(CompileBuild) {
        onDependencyFailure = FailureAction.FAIL_TO_START
        onDependencyCancel = FailureAction.CANCEL
        reuseBuilds = ReuseBuilds.SUCCESSFUL
        runOnSameAgent = false
        enforceCleanCheckout = false
        synchronizeRevisions = true
    }
}
```

#### Snapshot Dependency Properties
| Property | Description |
|---|---|
| `onDependencyFailure` | Action when dependency fails: `FAIL_TO_START`, `CANCEL`, `RUN`, `IGNORE` |
| `onDependencyCancel` | Action when dependency is cancelled |
| `reuseBuilds` | Reuse policy: `SUCCESSFUL`, `ANY`, `NO` |
| `runOnSameAgent` | Force execution on the same agent |
| `enforceCleanCheckout` | Force clean checkout in dependency |
| `synchronizeRevisions` | Use same source revision across chain |

### 5.2 Artifact Dependencies

Artifact dependencies allow a build to download files produced by another build.

```kotlin
dependencies {
    artifacts(PackageBuild) {
        buildRule = lastSuccessful()
        artifactRules = "dist/*.zip => libs"
        cleanDestination = true
    }
}
```

#### Artifact Dependency Properties
| Property | Description |
|---|---|
| `buildRule` | Which build to take artifacts from: `lastSuccessful()`, `lastPinned()`, `lastFinished()`, `build(number)`, `tag(tagName)` |
| `artifactRules` | Pattern: `[+:\|-:\|?:]SourcePath[!ArchivePath][=>DestinationPath]` |
| `cleanDestination` | Clean destination directory before downloading |

### 5.3 Build Chains with sequential/parallel

The `sequential {}` and `parallel {}` blocks automatically establish snapshot dependencies.

```kotlin
project {
    sequential {
        buildType(Compile)
        parallel {
            buildType(TestLinux)
            buildType(TestWindows)
            buildType(TestMacOS)
        }
        buildType(Package)
        buildType(Deploy)
    }
}
```

This creates: `Compile → [TestLinux, TestWindows, TestMacOS] → Package → Deploy`.

---

## 6. Artifacts

Artifacts are files produced by a build and stored on the TeamCity server for later use.

### Publishing Artifacts

Defined via `artifactRules` on a BuildType:

```kotlin
object MyBuild : BuildType({
    artifactRules = """
        build/libs/*.jar => jars
        build/reports/** => reports.zip
        -:build/reports/tmp/**
    """.trimIndent()
})
```

### Artifact Rules Syntax
```
[+:|-:]SourcePath[!ArchivePath] [=> DestinationPath]
```

- `+:` — include (default)
- `-:` — exclude
- `?:` — optional (build proceeds even if artifact not found)
- `=>` — redirect to target directory or archive (`.zip`, `.7z`, `.tar.gz`)
- Supports `*` and `**` wildcards

### Downloading Artifacts (via Artifact Dependencies)
Configured in the `dependencies` block using `artifacts()` (see Section 5.2).

---

## 7. Parameters

TeamCity parameters are key-value pairs configurable at project, build configuration, or agent level.

### Parameter Types

| Type | Prefix | Description |
|---|---|---|
| Configuration Parameter | (none) | Internal TC settings; not passed to build process |
| System Property | `system.` | Passed to build runners as tool-specific variables |
| Environment Variable | `env.` | Passed as OS environment variables to build steps |

```kotlin
params {
    param("config.version", "2.1")
    param("system.gradle.opts", "-Xmx512m")
    param("env.NODE_ENV", "production")
    password("env.SECRET_KEY", "credentialsJSON:abc-123-def")
    select("deploy.target", "staging", options = listOf("staging", "production"))
    text("release.notes", "", allowEmpty = true)
    checkbox("run.integration.tests", "true", checked = "true", unchecked = "false")
}
```

### Typed Parameters
| Type | Description |
|---|---|
| `param()` | Plain text parameter |
| `password()` | Masked/encrypted sensitive value |
| `text()` | Text with validation (regex, non-empty) |
| `select()` | Dropdown selection from predefined options |
| `checkbox()` | Boolean checkbox with checked/unchecked values |

### Parameter Inheritance
Parameters defined at the project level are inherited by all build configurations within that project. Lower-level definitions override inherited values.

### Parameter Priority (highest to lowest)
1. Enforced template values
2. Custom build run dialog values
3. Build configuration settings
4. Parent project settings
5. Regular template values
6. Agent `buildAgent.properties` values
7. Agent-reported values
8. Predefined server values

### Dynamic Parameters
Build scripts can set parameters at runtime via service messages:
```
##teamcity[setParameter name='foo' value='bar']
```

---

## 8. Agent Requirements

Agent requirements specify which build agents are compatible with a build configuration. Multiple requirements combine with AND logic.

```kotlin
requirements {
    exists("docker.server.version")
    equals("teamcity.agent.jvm.os.name", "Linux")
    startsWith("teamcity.agent.jvm.os.name", "Windows")
    contains("system.agent.name", "deploy")
    matches("teamcity.agent.name", "agent-\\d+")
    doesNotContain("teamcity.agent.name", "legacy")
    doesNotEqual("teamcity.agent.jvm.os.arch", "x86")
    doesNotExist("disabled.feature")
    noLessThan("teamcity.agent.hardware.memorySizeMb", "4096")
    noMoreThan("system.cpu.count", "8")
}
```

### Requirement Condition Types
| Condition | Description |
|---|---|
| `equals` | Parameter equals exact value |
| `doesNotEqual` | Parameter does not equal value |
| `contains` | Parameter contains substring |
| `doesNotContain` | Parameter does not contain substring |
| `startsWith` | Parameter starts with value |
| `endsWith` | Parameter ends with value |
| `matches` | Parameter matches regex |
| `doesNotMatch` | Parameter does not match regex |
| `exists` | Parameter is defined (not null) |
| `doesNotExist` | Parameter is not defined |
| `noLessThan` | Numeric comparison (>=) |
| `noMoreThan` | Numeric comparison (<=) |

### Requirement Sources
- **Explicit** — manually configured in `requirements {}` block
- **Implicit** — auto-generated from build step types (e.g., Docker steps require `docker.server.version`)
- **Parameter-derived** — from `%agent.property%` references in build steps

---

## 9. VCS Roots

VCS roots define connections to version control repositories.

```kotlin
object MyGitRoot : GitVcsRoot({
    id("MyGitRoot")
    name = "My Git Repository"
    url = "https://github.com/org/repo.git"
    branch = "refs/heads/main"
    branchSpec = "+:refs/heads/*"
    authMethod = password {
        userName = "git"
        password = "credentialsJSON:abc-123"
    }
    checkoutSubmodules = GitVcsRoot.CheckoutSubmodules.CHECKOUT
    userNameStyle = GitVcsRoot.UserNameStyle.FULL
})
```

### VCS Root Types
| Type | DSL Class | Description |
|---|---|---|
| Git | `GitVcsRoot` | Git repositories |
| Subversion | `SvnVcsRoot` | SVN repositories |
| Perforce | `PerforceVcsRoot` | Perforce Helix |
| Mercurial | `HgVcsRoot` | Mercurial repositories |
| Azure DevOps | `TfsVcsRoot` | Azure DevOps/TFS |
| CVS | `CvsVcsRoot` | CVS repositories |

### Common VCS Root Properties
| Property | Description |
|---|---|
| `url` | Repository URL |
| `branch` | Default branch reference |
| `branchSpec` | Branch specification patterns for monitoring |
| `authMethod` | Authentication (password, SSH key, token, anonymous) |
| `checkoutRules` | Include/exclude path mappings |
| `pollInterval` | How often to check for changes (seconds) |
| `userNameStyle` | How committer names are displayed |

### Attaching VCS Roots to Builds
```kotlin
object MyBuild : BuildType({
    vcs {
        root(MyGitRoot)
        root(AnotherRoot, "+:src => subdir")  // with checkout rules
        checkoutMode = CheckoutMode.ON_AGENT
        cleanCheckout = true
        checkoutDir = "custom-checkout-dir"
    }
})
```

---

## 10. Build Features

Build features add auxiliary functionality to build configurations.

```kotlin
features {
    dockerSupport {
        loginToRegistry = on {
            dockerRegistryId = "PROJECT_EXT_1"
        }
    }
    commitStatusPublisher {
        vcsRootExtId = "${MyGitRoot.id}"
        publisher = github {
            githubUrl = "https://api.github.com"
            authType = personalToken { token = "credentialsJSON:abc-123" }
        }
    }
    swabra {
        filesCleanup = Swabra.FilesCleanup.AFTER_BUILD
        forceCleanCheckout = true
    }
    pullRequests {
        vcsRootExtId = "${MyGitRoot.id}"
        provider = github {
            authType = token { token = "credentialsJSON:abc-123" }
            filterTargetBranch = "+:refs/heads/main"
            filterAuthorRole = PullRequests.GitHubRoleFilter.MEMBER
        }
    }
    sshAgent {
        teamcitySshKey = "my-deploy-key"
    }
    xmlReport {
        reportType = XmlReport.XmlReportType.JUNIT
        rules = "build/test-results/**/*.xml"
    }
    freeDiskSpace {
        requiredSpace = "1gb"
        failBuild = true
    }
    sharedResources {
        resource = "deploy-slot"
        lockType = SharedResource.LockType.READ_LOCK
    }
    matrixBuild {
        param("os", listOf("linux", "windows", "macos"))
        param("jdk", listOf("11", "17", "21"))
    }
    buildApproval {
        approvalRules = "user:admin"
    }
}
```

### Available Build Features
| Feature | DSL Method | Description |
|---|---|---|
| Docker Support | `dockerSupport {}` | Registry authentication, Docker wrapper |
| Commit Status Publisher | `commitStatusPublisher {}` | Reports build status to VCS hosting |
| Pull Requests | `pullRequests {}` | Build PR branches from GitHub/GitLab/Bitbucket |
| VCS Labeling | `vcsLabeling {}` | Tag VCS revisions on successful builds |
| Swabra | `swabra {}` | Clean build files between builds |
| SSH Agent | `sshAgent {}` | Make SSH keys available during build |
| XML Report Processing | `xmlReport {}` | Parse JUnit/NUnit/etc. test reports |
| File Content Replacer | `replaceContent {}` | Regex-based file content replacement |
| Free Disk Space | `freeDiskSpace {}` | Ensure minimum disk space on agent |
| Shared Resources | `sharedResources {}` | Manage concurrent access to resources |
| Matrix Build | `matrixBuild {}` | Run builds with parameter combinations |
| Build Approval | `buildApproval {}` | Require user approval before builds |
| Automatic Merge | `merge {}` | Auto-merge branches on success |
| Notifications | `notifications {}` | Email/Slack build notifications |
| Build Cache | `buildCache {}` | Reuse files (e.g., npm packages) across builds |
| Golang | `golang {}` | Go test reporting integration |
| Parallel Tests | `parallelTests {}` | Distribute tests across agents |
| Investigations Auto Assigner | `investigationsAutoAssigner {}` | Auto-assign failure investigations |

---

## 11. Templates

Templates define reusable build configuration fragments that other BuildTypes can inherit from.

```kotlin
object MyTemplate : Template({
    id("MyTemplate")
    name = "Standard Build Template"

    params {
        param("env.BUILD_ENV", "production")
    }
    steps {
        script {
            name = "Checkout and Build"
            scriptContent = "make build"
        }
    }
    triggers {
        vcs { branchFilter = "+:*" }
    }
    requirements {
        exists("docker.server.version")
    }
})

object MyBuild : BuildType({
    templates(MyTemplate)
    name = "Concrete Build"
    // override or extend template settings
    params {
        param("env.BUILD_ENV", "staging")
    }
})
```

Templates can define steps, triggers, dependencies, features, parameters, and requirements that are inherited by all build configurations using them. Enforced settings in templates cannot be overridden.

---

## 12. Failure Conditions

TeamCity provides configurable failure conditions beyond step exit codes.

```kotlin
failureConditions {
    executionTimeoutMin = 60
    errorMessage = true
    testFailure = true
    nonZeroExitCode = true
    javaCrash = true

    failOnMetricChange {
        metric = BuildFailureOnMetric.MetricType.TEST_COUNT
        threshold = 10
        units = BuildFailureOnMetric.MetricUnit.PERCENTS
        comparison = BuildFailureOnMetric.MetricComparison.LESS
        compareTo = build { buildRule = lastSuccessful() }
    }
    failOnText {
        conditionType = BuildFailureOnText.ConditionType.CONTAINS
        pattern = "FATAL ERROR"
        failureMessage = "Build log contains fatal error"
        reverse = false
    }
}
```

### Failure Condition Types
| Condition | Description |
|---|---|
| `executionTimeoutMin` | Maximum execution time in minutes |
| `nonZeroExitCode` | Fail on non-zero process exit code |
| `testFailure` | Fail if tests fail |
| `errorMessage` | Fail if error messages detected in log |
| `javaCrash` | Fail on JVM crash |
| `failOnMetricChange {}` | Fail if a metric changes beyond threshold |
| `failOnText {}` | Fail if build log contains/matches specific text |

---

## 13. Summary of Key DSL Class Hierarchy

```
Project
├── BuildType (build configuration)
│   ├── steps: BuildSteps
│   │   ├── ScriptBuildStep (command line)
│   │   ├── MavenBuildStep
│   │   ├── GradleBuildStep
│   │   ├── DockerCommandStep
│   │   ├── DotnetBuildStep
│   │   ├── PowerShellStep
│   │   └── ... (other runners)
│   ├── triggers: Triggers
│   │   ├── VcsTrigger
│   │   ├── ScheduleTrigger
│   │   ├── FinishBuildTrigger
│   │   ├── RetryBuildTrigger
│   │   └── ... (other triggers)
│   ├── dependencies: Dependencies
│   │   ├── SnapshotDependency
│   │   └── ArtifactDependency
│   ├── features: BuildFeatures
│   │   ├── DockerSupport
│   │   ├── CommitStatusPublisher
│   │   ├── Swabra
│   │   └── ... (other features)
│   ├── requirements: Requirements
│   │   └── Requirement (condition + parameter + value)
│   ├── params: ParametrizedWithType
│   │   ├── param (configuration parameter)
│   │   ├── password
│   │   ├── text
│   │   ├── select
│   │   └── checkbox
│   ├── vcs: VcsSettings
│   │   └── VcsRoot references
│   ├── failureConditions: FailureConditions
│   └── artifactRules: String
├── Template (reusable configuration)
│   └── (same structure as BuildType)
├── VcsRoot
│   ├── GitVcsRoot
│   ├── SvnVcsRoot
│   ├── PerforceVcsRoot
│   └── ... (other VCS types)
├── params: project-level parameters
└── subProject: nested Projects
```

---

## 14. Mapping to PIM Metamodel Concepts

| PIM Concept | TeamCity Equivalent | Notes |
|---|---|---|
| Pipeline | Project + BuildType chain | A project with build configurations linked via dependencies |
| Job (ScriptJob) | BuildType | A build configuration with steps |
| Job (PipelineCallJob) | Template reference or subproject | Reusing configurations via templates |
| Step (Command) | ScriptBuildStep (`script {}`) | Command line execution |
| Step (Plugin) | Specific runner steps (Maven, Gradle, Docker, etc.) | Each runner is a specialized step type |
| Step (Cache) | Build Cache feature (`buildCache {}`) | Handled as a build feature, not a step |
| Step (Artifact) | `artifactRules` + ArtifactDependency | Publishing via rules, downloading via dependencies |
| Step (Checkout) | VCS settings (`vcs {}`) | Automatic; configured at build level, not as a step |
| Trigger (Push) | VcsTrigger | Triggers on VCS changes |
| Trigger (PullRequest) | Pull Requests feature + VcsTrigger | Combined feature + trigger |
| Trigger (Scheduled) | ScheduleTrigger | Cron or daily/weekly scheduling |
| Trigger (Manual) | (default — no trigger) | Builds can always be triggered manually |
| Agent | Agent requirements | Requirements filter compatible agents |
| Dependencies (previous/next) | SnapshotDependency or `sequential{}`/`parallel{}` | Build chains |
| Matrix | Matrix Build feature | Parameter combinations via build feature |
| Environment Variables | `params { param("env.*", ...) }` | `env.` prefixed parameters |
| Parameters / Inputs | `params {}` with typed parameters | text, select, checkbox, password types |
| Docker Container | Docker build feature / `dockerCommand {}` step | Docker support as feature + step |
