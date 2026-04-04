# MDDOAI Transformation Extension Prompt

## How to Use This File

This is a self-contained prompt for testing LLMs on ATL transformation rule generation.
To run an experiment:
1. Copy everything from "BEGIN PROMPT" to "END PROMPT" into your AI of choice
2. Swap in any SWArch model in the "NEW INPUT" section
3. Compare outputs across models against the same reference chain

The prompt gives the AI three layers of context:
- **Metamodel schemas** — the full type system both transformations operate on (all classes, attributes, cardinalities). This is what enables complex experiments: the AI can only generate ATL for `Matrix`, `ScheduledTrigger`, `ConditionalStep`, etc. if it knows those types exist.
- **ATL source** — the transformation rules themselves
- **Worked example** — a concrete end-to-end trace (swarch → PIM → PSM → YAML) so the AI sees what the transformations actually produce

---

<!-- BEGIN PROMPT -->

## Pipeline Overview

This project transforms software architecture descriptions into GitLab CI/CD pipelines
through two sequential model-to-model transformations, written in ATL:

```
SWArch model (.swarch)
    │
    ▼  swarch2pim.atl
PIM model (.pimmm)
    │
    ▼  pim2gitlabmodel.atl
GitLab PSM model (.gitlabmm)
    │
    ▼  Acceleo templates
.gitlab-ci.yml
```

The SWArch model describes what the software is (language, build system, test commands).
The PIM captures a platform-independent pipeline structure (jobs, steps, agents, dependencies).
The GitLab PSM maps that structure to GitLab CI/CD specifics (stages, needs, images, before_script).

---

## Metamodel Schemas

These describe the full type systems that the ATL rules operate on.
The worked example below only uses a subset of these types — knowing the full schema
is what allows generating ATL for more complex cases (triggers, matrix builds, retries, etc.).

### SWArch Metamodel (input to swarch2pim.atl)

One class. All attributes are strings.

```
Class SoftwareArchitecture
  Attribute name         : EString   -- project name
  Attribute type         : EString   -- framework | jar | docker-image
  Attribute build_system : EString   -- docker | gradle | maven
  Attribute language     : EString   -- python | node | javascript | java
  Attribute unit_test    : EString   -- command/path, or "" to omit unitTest job
  Attribute health_check : EString   -- command/path, or "" to omit healthCheck job
  Attribute lint_check   : EString   -- command/path, or "" to omit lintCheck job
```

### PIM Metamodel (output of swarch2pim.atl, input to pim2gitlabmodel.atl)

```
Class PipelineBlock
  Attribute name               : EString
  Reference agent              : Agent
  Reference inputs             : Input<<0..*>>
  Reference outputs            : Output<<0..*>>
  Reference environmentVariables : Assignment<<0..*>>
  Attribute timeoutMinutes     : EIntegerObject
  Reference workingDirectory   : Expression
  Reference shell              : Expression

Class Pipeline -> PipelineBlock
  Reference triggers   : Trigger<<0..*>>
  Reference jobStreams  : Job<<0..*>>

Class Job -> PipelineBlock
  Attribute id         : EString<<1..1>>
  Reference ifCondition : Expression
  Reference services   : DockerContainer<<0..*>>
  Reference matrix     : Matrix
  Reference previous   : Job<<0..*>>
  Reference next       : Job<<0..*>>
  Attribute maxAttempts : EIntegerObject
  Attribute allowFailure : EBooleanObject

Class ScriptJob -> Job
  Reference steps : Step<<0..*>>

Class PipelineCallJob -> Job
  Reference pipelinePath : Expression<<1..1>>
  Reference args         : Assignment<<0..*>>

Class Agent
Class CustomAgent -> Agent
  Reference labels : Expression<<0..*>>
Class PresetAgent -> Agent
  Reference image : Expression
Class LinuxAgent -> PresetAgent
  Reference container : DockerContainer
Class WindowsAgent -> PresetAgent
Class MacOSAgent -> PresetAgent
  Reference xcode : Expression

Class DockerContainer
  Attribute label              : EString
  Reference image              : Expression<<1..1>>
  Reference environmentVariables : Assignment<<0..*>>
  Reference volumes            : Expression<<0..*>>
  Reference ports              : Expression<<0..*>>
  Attribute options            : EString
  Reference registryUsername   : Expression
  Reference registryPassword   : Expression
  Reference network            : Expression

Class Trigger
  Attribute branchGlobs : EString<<0..*>>
Class PushTrigger -> Trigger
  Attribute tagGlobs : EString<<0..*>>
Class PullRequestTrigger -> Trigger
Class ManualTrigger -> Trigger
Class ScheduledTrigger -> Trigger
  Attribute crons : EString<<1..*>>

Class Matrix
  Reference axes     : MatrixAxis<<0..*>>
  Reference includes : MatrixCombination<<0..*>>
  Reference excludes : MatrixCombination<<0..*>>
  Attribute failFast : EBooleanObject
Class MatrixAxis
  Reference name  : VariableDeclaration<<1..1>>
  Reference cells : Expression<<1..*>>
Class MatrixCombination
  Reference combinationEntries : Assignment<<0..*>>

Class Step
Class ConditionalStep -> Step
  Reference ifCondition : Expression<<1..1>>
  Reference thenRun     : Step<<1..*>>
  Reference elseRun     : Step<<0..*>>
Class NonConditionalStep -> Step
  Attribute id           : EString
  Attribute name         : EString
  Reference environmentVariables : Assignment<<0..*>>
  Attribute allowFailure : EBooleanObject
  Reference workingDirectory : Expression
  Reference timeoutMinutes   : Expression
Class Command -> NonConditionalStep
  Reference program : Expression<<1..1>>
  Reference shell   : Expression
Class Cache -> NonConditionalStep
  Reference cacheName : Expression<<1..1>>
  Reference keys      : Expression<<1..*>>
  Reference paths     : Expression<<1..*>>
  Attribute store     : CACHE_MODE<<1..1>>   -- STORE | LOAD | BOTH
Class Artifact -> NonConditionalStep
  Reference artifactName   : Expression
  Reference includePaths   : Expression<<0..*>>
  Reference excludePaths   : Expression<<0..*>>
  Reference retentionTime  : Expression
  Attribute store          : EBoolean<<1..1>>

Class VariableDeclaration
  Attribute name : EString<<1..1>>
Class Assignment -> Expression
  Reference key   : VariableDeclaration<<1..1>>
  Reference value : Expression<<1..1>>

Class Expression
Class StringLiteral -> Expression
  Attribute value : EString<<1..1>>
Class IntegerLiteral -> Expression
  Attribute value : EIntegerObject<<1..1>>
Class VariableReference -> Expression
  Reference reference : VariableDeclaration<<1..1>>
Class EqualityOp -> Expression
  Reference lhs : Expression<<1..1>>
  Reference rhs : Expression<<1..1>>
  Attribute op  : EQUALITY_OPS   -- EQUALS | NOT_EQUALS
```

### GitLab PSM Metamodel (output of pim2gitlabmodel.atl)

```
Class Pipeline
  Attribute stages   : EString<<0..*>>
  Reference jobs     : Job<<1..*>>
  Reference variables : Variables
  Reference workflow : Workflow
  Reference default  : Default

Class Job
  Attribute name          : EString<<1..1>>
  Attribute stage         : EString<<1..1>>
  Reference script        : Script
  Reference beforeScript  : BeforeScript
  Reference afterScript   : AfterScript
  Reference tags          : Tags
  Reference image         : Image
  Reference artifacts     : Artifact
  Reference cache         : Cache
  Reference rules         : GitlabRule<<0..*>>
  Reference needs         : Need<<0..*>>
  Reference services      : Service<<0..*>>
  Reference variables     : Variables
  Reference retry         : Retry
  Reference parallel      : Parallel
  Reference environment   : Environment
  Reference dependencies  : Dependencies
  Attribute when          : EString
  Attribute allowFailure  : EBoolean
  Attribute timeout       : EString
  Attribute interruptible : EBoolean
  Attribute resourceGroup : EString

Class Script
  Reference commands : Command<<1..*>>
Class BeforeScript
  Reference commands : Command<<1..*>>
Class AfterScript
  Reference commands : Command<<0..*>>
Class Command
  Attribute command : EString<<1..1>>

Class Image
  Attribute name        : EString<<1..1>>
  Attribute entrypoint  : EString<<0..*>>
  Attribute pullPolicy  : EString<<0..*>>
  Reference docker      : Docker
  Reference kubernetes  : Kubernetes

Class Variables
  Reference variables : Variable<<0..*>>
Class Variable
  Attribute name        : EString<<1..1>>
  Attribute value       : EString<<1..1>>
  Attribute description : EString
  Attribute expand      : EBoolean

Class GitlabRule
  Attribute gitlabIf    : EString
  Attribute when        : EString
  Attribute changes     : EString<<0..*>>
  Attribute exists      : EString<<0..*>>
  Attribute allowFailure : EBoolean
  Reference variables   : Variables

Class Workflow
  Reference rules : GitlabRule<<0..*>>

Class Need
  Attribute job      : EString
  Attribute artifacts : EBoolean
  Attribute project  : EString
  Attribute optional : EBoolean

Class Retry
  Attribute max  : EInt
  Attribute when : EString<<0..*>>

Class Parallel
  Attribute count : EIntegerObject
  Reference matrix : Matrix
Class Matrix
  Reference axes : Axis<<0..*>>
Class Axis
  Attribute name   : EString
  Attribute values : EString<<0..*>>

Class Tags
  Reference tags : Tag<<1..*>>
Class Tag
  Attribute tag : EString<<1..1>>

Class Artifact
  Attribute when     : EString
  Attribute name     : EString
  Attribute expireIn : EString
  Reference reports  : Report
  Reference paths    : Path<<0..*>>
Class Report
  Attribute junit         : EString<<1..1>>
  Attribute coverageReport : EString
  Attribute dotenv        : EString
  Attribute cobertura     : EString
Class Path
  Attribute path : EString<<1..1>>

Class Cache
  Reference key      : Key
  Attribute paths    : EString<<0..*>>
  Attribute when     : EString
  Attribute untracked : EBoolean
Class Key
  Attribute name    : EString
  Attribute files   : EString<<0..*>>
  Attribute prefix  : EString

Class Service
  Attribute name      : EString<<1..1>>
  Attribute alias     : EString
  Reference commands  : Command<<0..*>>

Class Environment
  Attribute name      : EString<<1..1>>
  Attribute url       : EString
  Attribute onStop    : EString
  Attribute action    : EString
  Attribute autoStopIn : EString

Class Default
  Reference image        : Image
  Reference beforeScript : BeforeScript
  Reference afterScript  : AfterScript
  Reference tags         : Tags
  Reference cache        : Cache
  Reference retry        : Retry
  Attribute timeout      : EString
  Attribute interruptible : EBoolean
```

---

## Complete Reference Example

The following is a fully worked example of the pipeline for a Python/Docker project.
Use it to understand what each transformation produces.

### Reference Input — SWArch Model

```xml
<?xml version="1.0" encoding="UTF-8"?>
<swarch:SoftwareArchitecture xmi:version="2.0"
  xmlns:xmi="http://www.omg.org/XMI"
  xmlns:swarch="http://www.mddoai.com/mddoai/metamodel/swarch"
  name="framework-chatbot"
  type="framework"
  build_system="docker"
  language="python"
  unit_test="test/run_unit_tests.py"
  health_check="./scripts/health_check.sh"
  lint_check=""/>
```

### Reference Intermediate — PIM (output of swarch2pim.atl)

```xml
<?xml version="1.0" encoding="ASCII"?>
<xmi:XMI xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xmlns:pimMM="http://www.mddoai.com/mddoai/metamodel/pim">
  <pimMM:Pipeline name="framework-chatbot" jobStreams="build unitTest healthCheck push">
    <environmentVariables>
      <key name="IMAGE_NAME"/>
      <value xsi:type="pimMM:StringLiteral" value="${CI_REGISTRY_IMAGE}/framework-chatbot-image"/>
    </environmentVariables>
    <environmentVariables>
      <key name="IMAGE_TAG"/>
      <value xsi:type="pimMM:StringLiteral" value="${IMAGE_NAME}:${IMAGE_VERSION}-${CI_COMMIT_REF_SLUG}"/>
    </environmentVariables>
  </pimMM:Pipeline>
  <pimMM:ScriptJob name="build" id="build" next="healthCheck">
    <steps xsi:type="pimMM:Command">
      <program xsi:type="pimMM:StringLiteral" value="docker build -t ${IMAGE_TAG} ."/>
    </steps>
  </pimMM:ScriptJob>
  <pimMM:ScriptJob name="healthCheck" id="healthCheck" previous="build" next="push">
    <agent xsi:type="pimMM:LinuxAgent">
      <container>
        <image xsi:type="pimMM:StringLiteral" value="curlimages/curl:latest"/>
      </container>
    </agent>
    <steps xsi:type="pimMM:Command">
      <program xsi:type="pimMM:StringLiteral" value="./scripts/health_check.sh"/>
    </steps>
  </pimMM:ScriptJob>
  <pimMM:ScriptJob name="push" id="push" previous="healthCheck">
    <steps xsi:type="pimMM:Command">
      <program xsi:type="pimMM:StringLiteral" value="docker login -u ${CI_REGISTRY_USER} -p ${CI_JOB_TOKEN} ${CI_REGISTRY}"/>
    </steps>
    <steps xsi:type="pimMM:Command">
      <program xsi:type="pimMM:StringLiteral" value="docker push ${IMAGE_TAG}"/>
    </steps>
  </pimMM:ScriptJob>
  <pimMM:ScriptJob name="unitTest" id="unitTest">
    <agent xsi:type="pimMM:LinuxAgent">
      <container>
        <image xsi:type="pimMM:StringLiteral" value="python:3.12-slim"/>
      </container>
    </agent>
    <steps xsi:type="pimMM:Command">
      <program xsi:type="pimMM:StringLiteral" value="python3 test/run_unit_tests.py"/>
    </steps>
  </pimMM:ScriptJob>
</xmi:XMI>
```

### Reference Output — GitLab PSM (output of pim2gitlabmodel.atl)

```xml
<?xml version="1.0" encoding="ASCII"?>
<gitlabMM:Pipeline xmi:version="2.0"
  xmlns:xmi="http://www.omg.org/XMI"
  xmlns:gitlabMM="http://www.mddoai.com/mddoai/metamodel/gitlab">
  <stages>build</stages>
  <stages>unitTest</stages>
  <stages>healthCheck</stages>
  <stages>push</stages>
  <jobs name="build" stage="build">
    <script>
      <commands command="docker build -t ${IMAGE_TAG} ."/>
    </script>
    <tags>
      <tags tag="ai-shared"/>
    </tags>
  </jobs>
  <jobs name="unitTest" stage="unitTest">
    <script>
      <commands command="python3 test/run_unit_tests.py"/>
    </script>
    <beforeScript>
      <commands command="python3 -m pip install --break-system-packages -r requirements.txt"/>
    </beforeScript>
    <tags>
      <tags tag="ai-shared"/>
    </tags>
    <image name="python:3.12-slim"/>
  </jobs>
  <jobs name="healthCheck" stage="healthCheck">
    <script>
      <commands command="./scripts/health_check.sh"/>
    </script>
    <tags>
      <tags tag="ai-shared"/>
    </tags>
    <image name="curlimages/curl:latest"/>
    <needs job="build" artifacts="true"/>
  </jobs>
  <jobs name="push" stage="push">
    <script>
      <commands command="docker login -u ${CI_REGISTRY_USER} -p ${CI_JOB_TOKEN} ${CI_REGISTRY}"/>
      <commands command="docker push ${IMAGE_TAG}"/>
    </script>
    <tags>
      <tags tag="ai-shared"/>
    </tags>
    <needs job="healthCheck" artifacts="true"/>
  </jobs>
  <variables>
    <variables name="IMAGE_NAME" value="${CI_REGISTRY_IMAGE}/framework-chatbot-image"/>
    <variables name="IMAGE_TAG" value="${IMAGE_NAME}:${IMAGE_VERSION}-${CI_COMMIT_REF_SLUG}"/>
  </variables>
  <workflow>
    <rules gitlabIf="$CI_PIPELINE_SOURCE == &quot;parent_pipeline&quot;"/>
    <rules gitlabIf="$CI_COMMIT_BRANCH"/>
  </workflow>
</gitlabMM:Pipeline>
```

### Reference Final Output — .gitlab-ci.yml

```yaml
workflow:
  rules:
    - if: $CI_PIPELINE_SOURCE == "parent_pipeline"
    - if: $CI_COMMIT_BRANCH

stages:
  - build
  - unitTest
  - healthCheck
  - push

variables:
  IMAGE_NAME: ${CI_REGISTRY_IMAGE}/framework-chatbot-image
  IMAGE_TAG: ${IMAGE_NAME}:${IMAGE_VERSION}-${CI_COMMIT_REF_SLUG}

build:
  stage: build
  script:
    - docker build -t ${IMAGE_TAG} .

unitTest:
  stage: unitTest
  image: python:3.12-slim
  before_script:
    - python3 -m pip install --break-system-packages -r requirements.txt
  script:
    - python3 test/run_unit_tests.py

healthCheck:
  stage: healthCheck
  image: curlimages/curl:latest
  script:
    - ./scripts/health_check.sh
  needs:
    - job: "build"

push:
  stage: push
  script:
    - docker login -u ${CI_REGISTRY_USER} -p ${CI_JOB_TOKEN} ${CI_REGISTRY}
    - docker push ${IMAGE_TAG}
  needs:
    - job: "healthCheck"
```

---

## Full ATL Source

### Transformation 1: swarch2pim.atl

```atl
-- @nsURI SWArchMM=http://www.mddoai.com/mddoai/metamodel/swarch
-- @nsURI PIMMM=http://www.mddoai.com/mddoai/metamodel/pim

module swarch2pim;
create OUT : PIMMM from IN : SWArchMM;

-- Main pipeline creation
rule SWArch2PIMPipeline {
  from
    source : SWArchMM!SoftwareArchitecture
  to
    target : PIMMM!Pipeline (
      name <- source.name,
      agent <- source.getDefaultAgent(),
      jobStreams <- source.defineAllJobs(),
      environmentVariables <- source.createPipelineVariables()
    )
  do {
    thisModule.buildJob <- target.jobStreams->select(j | j.id = 'build')->first();
    thisModule.healthCheckJob <- target.jobStreams->select(j | j.id = 'healthCheck')->first();
    thisModule.pushJob <- target.jobStreams->select(j | j.id = 'push')->first();

    if (not thisModule.healthCheckJob.oclIsUndefined() and not thisModule.buildJob.oclIsUndefined()) {
      thisModule.healthCheckJob.previous <- Sequence{thisModule.buildJob};
    }

    if (not thisModule.pushJob.oclIsUndefined() and not thisModule.healthCheckJob.oclIsUndefined()) {
      thisModule.pushJob.previous <- Sequence{thisModule.healthCheckJob};
    }
  }
}

helper def: getMandatoryJobs(type : String) : Sequence(String) =
  if type = 'framework' then
    Sequence {'build', 'push'}
  else if type = 'jar' then
    Sequence {'build'}
  else
    Sequence {}
  endif
  endif;

helper def: appendIf(seq : Sequence(String), cond : Boolean, elem : String) : Sequence(String) =
  if cond then seq->append(elem)
  else seq
  endif;

lazy rule createScriptJob {
  from
    source : TupleType(pipeline : SWArchMM!SoftwareArchitecture, stage : String)
  to
    target : PIMMM!ScriptJob (
      name <- source.stage,
      id <- source.stage,
      steps <- source.pipeline.getCommands(source.stage),
      agent <- source.pipeline.getAgentForStage(source.stage)
    )
}

helper context SWArchMM!SoftwareArchitecture def: getDefaultAgent() : PIMMM!Agent =
  if self.build_system = 'gradle' then
    thisModule.createLinuxAgent('gradle:jdk21')
  else
    OclUndefined
  endif;

helper context SWArchMM!SoftwareArchitecture def: getAgentForStage(stage : String) : PIMMM!Agent =
  if stage = 'unitTest' then
    if self.language = 'python' then
      thisModule.createLinuxAgent('python:3.12-slim')
    else if self.language = 'node' or self.language = 'javascript' then
      thisModule.createLinuxAgent('node:18-alpine')
    else
      OclUndefined
    endif
    endif
  else if stage = 'healthCheck' then
    thisModule.createLinuxAgent('curlimages/curl:latest')
  else
    OclUndefined
  endif
  endif;

lazy rule createLinuxAgent {
  from
    imageName : String
  to
    agent : PIMMM!LinuxAgent (
      container <- thisModule.createDockerContainer(imageName)
    )
}

lazy rule createDockerContainer {
  from
    imageName : String
  to
    container : PIMMM!DockerContainer (
      image <- thisModule.createStringLiteral(imageName)
    )
}

helper context SWArchMM!SoftwareArchitecture def: getCommands(stage : String) : Sequence(PIMMM!Command) =
  if stage = 'unitTest' then
    if self.build_system = 'gradle' then
      Sequence { thisModule.createCommand('gradle test') }
    else if self.unit_test = 'true' then
      Sequence { thisModule.createCommand('python3 -m pytest') }
    else
      Sequence { thisModule.createCommand('python3 ' + self.unit_test) }
    endif
    endif
  else if stage = 'build' then
    if self.build_system = 'gradle' then
      Sequence { thisModule.createCommand('gradle clean build -x test') }
    else
      Sequence { thisModule.createCommand(self.build_system + ' build -t ${IMAGE_TAG} .') }
    endif
  else if stage = 'healthCheck' then
    Sequence { thisModule.createCommand(self.health_check) }
  else if stage = 'push' then
    Sequence {
      thisModule.createCommand(self.build_system + ' login -u ${CI_REGISTRY_USER} -p ${CI_JOB_TOKEN} ${CI_REGISTRY}'),
      thisModule.createCommand(self.build_system + ' push ${IMAGE_TAG}')
    }
  else if stage = 'lintCheck' then
    if self.build_system = 'gradle' then
      Sequence { thisModule.createCommand('gradle check') }
    else
      Sequence { thisModule.createCommand(self.build_system + ' check') }
    endif
  else
    Sequence {}
  endif
  endif
  endif
  endif
  endif;

lazy rule createStringLiteral {
  from
    source : String
  to
    target : PIMMM!StringLiteral (
      value <- source
    )
}

lazy rule createCommand {
  from
    source : String
  to
    target : PIMMM!Command (
      program <- thisModule.createStringLiteral(source)
    )
}

helper context SWArchMM!SoftwareArchitecture def: defineAllJobs() : Sequence(PIMMM!Job) =
  let stages : Sequence(String) = Sequence{'build','unitTest','lintCheck','healthCheck','push'}->
        select(s | thisModule.getMandatoryJobs(self.type)->includes(s)
                  or (s='unitTest' and not self.unit_test.oclIsUndefined() and self.unit_test<>'')
                  or (s='healthCheck' and not self.health_check.oclIsUndefined() and self.health_check<>'')
                  or (s='lintCheck' and not self.lint_check.oclIsUndefined() and self.lint_check<>'')
        )
  in stages->collect(s | thisModule.createScriptJob(Tuple { pipeline = self, stage = s }));

helper context SWArchMM!SoftwareArchitecture def: createPipelineVariables() : Sequence(PIMMM!Assignment) =
  if self.type = 'framework' or self.type = 'docker-image' then
    Sequence {
      thisModule.createAssignment('IMAGE_NAME', '${CI_REGISTRY_IMAGE}/' + self.name + '-image'),
      thisModule.createAssignment('IMAGE_TAG', '${IMAGE_NAME}:${IMAGE_VERSION}-${CI_COMMIT_REF_SLUG}')
    }
  else
    Sequence {}
  endif;

lazy rule createAssignment {
  from
    key : String,
    value : String
  to
    assignment : PIMMM!Assignment (
      key <- thisModule.createVariableDeclaration(key),
      value <- thisModule.createStringLiteral(value)
    )
}

lazy rule createVariableDeclaration {
  from
    source : String
  to
    target : PIMMM!VariableDeclaration (
      name <- source
    )
}
```

### Transformation 2: pim2gitlabmodel.atl

```atl
-- @nsURI PIM=pimMM=http://www.mddoai.com/mddoai/metamodel/pim
-- @nsURI GitLabMM=gitlabMM=http://www.mddoai.com/mddoai/metamodel/gitlab

module pim2gitlabmodel;
create OUT : GitLabMM from IN : PIM;

rule Pipeline2GitlabPipeline {
    from
        source : PIM!Pipeline
    to
        target : GitLabMM!Pipeline (
            workflow <- thisModule.createWorkflow(source.triggers),
            stages <- source.jobStreams->collect(j | thisModule.getStageForJob(j, source))->asOrderedSet(),
            jobs <- source.jobStreams->collect(j | thisModule.createJob(Tuple{job = j, pipeline = source})),
            variables <- if source.environmentVariables->notEmpty() then
                            thisModule.createVariables(source.environmentVariables)
                         else
                            OclUndefined
                         endif,
            default <- if source.agent.oclIsUndefined() then
                          OclUndefined
                       else
                          thisModule.createDefault(Tuple{agent = source.agent, pipeline = source})
                       endif
        )
}

lazy rule createVariable {
    from
        a : PIM!Assignment
    to
        var : GitLabMM!Variable (
            name <- a.key.name,
            value <- a.value.toGitLabString()
        )
}

lazy rule createVariables {
    from
        assignments : Sequence(PIM!Assignment)
    to
        vars : GitLabMM!Variables (
            variables <- assignments->collect(a | thisModule.createVariable(a))
        )
}

lazy rule createJob {
    from
        source : TupleType(job : PIM!Job, pipeline : PIM!Pipeline)
    to
        target : GitLabMM!Job (
            name <- source.job.id,
            stage <- thisModule.getStageForJob(source.job, source.pipeline),
            image <- if source.job.agent.oclIsUndefined() then
                        OclUndefined
                     else
                        thisModule.createImage(source.job.agent)
                     endif,
            tags <- if source.job.agent.oclIsUndefined() then
                        thisModule.createTags()
                     else
                        thisModule.createTags(source.job.agent)
                     endif,
            services <- if source.job.services->notEmpty() then
                           source.job.services->collect(s | thisModule.createService(s))
                        else
                           Sequence{}
                        endif,
            needs <- if source.job.previous->notEmpty() then
                        source.job.previous->collect(p | thisModule.createNeed(p.id))
                     else
                        Sequence{}
                     endif,
            variables <- if source.job.environmentVariables->notEmpty() then
                            thisModule.createVariables(source.job.environmentVariables)
                         else
                            OclUndefined
                         endif,
            allowFailure <- source.job.allowFailure,
            timeout <- if source.job.timeoutMinutes.oclIsUndefined() then
                          OclUndefined
                       else
                          source.job.timeoutMinutes.toString() + 'm'
                       endif,
            retry <- if source.job.maxAttempts.oclIsUndefined() or source.job.maxAttempts <= 1 then
                        OclUndefined
                     else
                        thisModule.createRetry(source.job.maxAttempts - 1)
                     endif,
            parallel <- if source.job.matrix.oclIsUndefined() then
                           OclUndefined
                        else
                           thisModule.createParallel(source.job.matrix)
                        endif,
            rules <- if source.job.ifCondition.oclIsUndefined() then
                        Sequence{}
                     else
                        Sequence{thisModule.createRule(source.job.ifCondition)}
                     endif,
            script <- if source.job.oclIsTypeOf(PIM!ScriptJob) then
                        thisModule.createScript(source.job)
                      else
                        OclUndefined
                      endif,
            beforeScript <- if source.job.id = 'unitTest' then
                               if thisModule.isGradleProject(source.pipeline) then
                                  OclUndefined
                               else
                                  thisModule.createBeforeScript(source.pipeline)
                               endif
                            else
                               OclUndefined
                            endif,
            artifacts <- if thisModule.hasArtifacts(source.job, source.pipeline) then
                            thisModule.createArtifacts(source.job)
                         else
                            OclUndefined
                         endif
        )
}

lazy rule createScript {
    from
        job : PIM!ScriptJob
    to
        script : GitLabMM!Script (
            commands <- job.steps
                            ->select(s | s.oclIsTypeOf(PIM!Command))
                            ->collect(s | thisModule.createCommand(s.program.toGitLabString()))
        )
}

lazy rule createCommand {
    from
        cmdString : String
    to
        cmd : GitLabMM!Command (
            command <- cmdString
        )
}

lazy rule createBeforeScript {
    from
        pipeline : PIM!Pipeline
    to
        beforeScript : GitLabMM!BeforeScript (
            commands <- if thisModule.isGradleProject(pipeline) then
               Sequence{
                   thisModule.createCommand('GRADLE_USER_HOME="$(pwd)/.gradle"'),
                   thisModule.createCommand('export GRADLE_USER_HOME')
               }
            else
               Sequence{thisModule.createCommand('python3 -m pip install --break-system-packages -r requirements.txt')}
            endif
        )
}

lazy rule createTags {
    from
        dummy : OclAny
    to
        tags : GitLabMM!Tags (
            tags <- Sequence{thisModule.createTag('ai-shared')}
        )
}

lazy rule createTag {
    from
        tagName : String
    to
        tag : GitLabMM!Tag (
            tag <- tagName
        )
}

lazy rule createNeed {
    from
        jobName : String
    to
        need : GitLabMM!Need (
            job <- jobName,
            artifacts <- true
        )
}

lazy rule createRetry {
    from
        maxRetries : Integer
    to
        retry : GitLabMM!Retry (
            max <- maxRetries
        )
}

lazy rule createParallel {
    from
        matrix : PIM!Matrix
    to
        parallel : GitLabMM!Parallel (
            matrix <- thisModule.createMatrix(matrix)
        )
}

lazy rule createMatrix {
    from
        matrix : PIM!Matrix
    to
        gitlabMatrix : GitLabMM!Matrix (
            axes <- matrix.axes->collect(a | thisModule.createAxis(a))
        )
}

lazy rule createAxis {
    from
        axis : PIM!MatrixAxis
    to
        gitlabAxis : GitLabMM!Axis (
            name <- axis.name.name,
            values <- axis.cells->collect(c | c.toGitLabString())
        )
}

lazy rule createImage {
    from
        agent : PIM!LinuxAgent
    to
        image : GitLabMM!Image (
            name <- agent.container.image.toGitLabString()
        )
}

lazy rule createDefault {
    from
        source : TupleType(agent : PIM!LinuxAgent, pipeline : PIM!Pipeline)
    to
        default : GitLabMM!Default (
            image <- thisModule.createImage(source.agent),
            beforeScript <- thisModule.createBeforeScript(source.pipeline)
        )
}

lazy rule createWorkflow {
    from
        triggers : Sequence(PIM!Trigger)
    to
        workflow : GitLabMM!Workflow (
            rules <- if triggers->isEmpty() then
                        Sequence{
                            thisModule.createParentPipelineRule(),
                            thisModule.createBranchRule()
                        }
                     else
                        Sequence{
                            thisModule.createParentPipelineRule(),
                            thisModule.createBranchRule()
                        }->union(
                            triggers->collect(t | thisModule.createTriggerRule(t))
                        )
                     endif
        )
}

lazy rule createParentPipelineRule {
    from
        dummy : String
    to
        gitlabRule : GitLabMM!GitlabRule (
            gitlabIf <- '$CI_PIPELINE_SOURCE == "parent_pipeline"'
        )
}

lazy rule createBranchRule {
    from
        dummy : String
    to
        gitlabRule : GitLabMM!GitlabRule (
            gitlabIf <- '$CI_COMMIT_BRANCH'
        )
}

lazy rule createTriggerRule {
    from
        trigger : PIM!Trigger
    to
        gitlabRule : GitLabMM!GitlabRule (
            gitlabIf <- if trigger.oclIsTypeOf(PIM!PushTrigger) then
                          if trigger.branchGlobs->notEmpty() then
                              '$CI_COMMIT_BRANCH == "' + trigger.branchGlobs->first() + '"'
                          else
                              OclUndefined
                          endif
                       else if trigger.oclIsTypeOf(PIM!PullRequestTrigger) then
                          '$CI_PIPELINE_SOURCE == "merge_request_event"'
                       else if trigger.oclIsTypeOf(PIM!ScheduledTrigger) then
                          '$CI_PIPELINE_SOURCE == "schedule"'
                       else
                          OclUndefined
                       endif
                       endif
                       endif,
            when <- if trigger.oclIsTypeOf(PIM!ManualTrigger) then
                       'manual'
                    else
                       OclUndefined
                    endif
        )
}

lazy rule createRule {
    from
        condition : PIM!Expression
    to
        gitlabRule : GitLabMM!GitlabRule (
            gitlabIf <- condition.toGitLabString(),
            when <- 'on_success'
        )
}

lazy rule createService {
    from
        container : PIM!DockerContainer
    to
        service : GitLabMM!Service (
            name <- container.image.toGitLabString(),
            alias <- if not container.label.oclIsUndefined() then
                        container.label
                     else
                        OclUndefined
                     endif
        )
}

lazy rule createArtifacts {
    from
        job : PIM!Job
    to
        artifacts : GitLabMM!Artifact (
            when <- if job.id='unitTest' then 'always' else OclUndefined endif,
            paths <- if job.id='build' then Sequence{thisModule.createPath('build/libs/*.jar')}
                     else if job.id='unitTest' then Sequence{thisModule.createPath('build/test-results/**/*.xml')}
                     else Sequence{}
                     endif
                     endif,
            reports <- if job.id='unitTest' then thisModule.createReports() else OclUndefined endif
        )
}

lazy rule createReports {
    from
        dummy : OclAny
    to
        report : GitLabMM!Report (
            junit <- 'build/test-results/test/**/TEST-*.xml'
        )
}

lazy rule createPath {
    from
        p : String
    to
        path : GitLabMM!Path (
            path <- p
        )
}

helper def : hasArtifacts(job : PIM!Job, pipeline : PIM!Pipeline) : Boolean =
    (job.id = 'build' or job.id = 'unitTest') and
    thisModule.isGradleProject(pipeline);

helper def : isGradleProject(pipeline : PIM!Pipeline) : Boolean =
    pipeline.jobStreams->exists(j |
        j.oclIsTypeOf(PIM!ScriptJob) and
        j.steps->exists(s |
            s.oclIsTypeOf(PIM!Command) and
            s.program.toGitLabString().indexOf('gradle') >= 0));

helper def : getStageForJob(job : PIM!Job, pipeline : PIM!Pipeline) : String =
    if thisModule.isGradleProject(pipeline) then
        if job.id = 'unitTest' or job.id = 'lintCheck' then
            'test'
        else
            job.id
        endif
    else
        job.id
    endif;

helper def : getWhenForJob(job : PIM!Job) : String =
    if job.name='unitTest' then 'always'
    else OclUndefined
    endif;

helper context PIM!Expression def : toGitLabString() : String = 'UNKNOWN';
helper context PIM!StringLiteral def : toGitLabString() : String = self.value;
helper context PIM!IntegerLiteral def : toGitLabString() : String = self.value.toString();
helper context PIM!VariableReference def : toGitLabString() : String = '$' + self.reference.name;
helper context PIM!EqualityOp def : toGitLabString() : String =
    self.lhs.toGitLabString() +
    (if self.op = #"==" then ' == ' else ' != ' endif) +
    self.rhs.toGitLabString();
```

---

## NEW INPUT — SWArch Model

<!-- Paste the contents of any experiment input file here.
     Each experiment is in its own folder alongside this prompt:

       experiment1/input.swarch  —  Node.js Express (new language, low complexity)
       experiment2/input.swarch  —  Go Microservice (fully unsupported language, medium)
       experiment3/input.swarch  —  Java/Maven JAR  (new build system, medium-high)
       experiment4/input.swarch  —  Python + Triggers + Retry (inference, very high)

     Read the XML comments in each file for a full description of what ATL gaps
     that experiment exposes before running the prompt.
-->

```xml
PASTE EXPERIMENT INPUT HERE
```

---

## Task

Given the SWArch model above and the transformation rules provided:

1. Trace through both ATL files and identify every helper or rule that does not
   correctly handle the provided input.

2. For each gap found, write the corrected or extended version of that helper/rule.
   Show only the changed helpers/rules — do not rewrite unchanged ones.
   For each change, state in one sentence what gap it fixes.

3. Show what the final `.gitlab-ci.yml` would look like for this input,
   following the structure of the reference output above.

---

## Constraint

Use only ATL language constructs — rule types, helper forms, OCL operations —
that are already demonstrated in the ATL source above. Do not introduce ATL
syntax patterns or constructs that have no precedent in those files.

<!-- END PROMPT -->
