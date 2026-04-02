# Prompt 02 — Extend pim2psm ATL Transformation for Node.js

## Purpose of this File

This prompt continues from Prompt 01. It tests whether an LLM can extend
`pim2gitlabmodel.atl` rules to handle a Node.js PIM input — using only
the ATL constructs and patterns already demonstrated in the file.

---

## Task

You are given the full `pim2gitlabmodel.atl` transformation. It currently handles
Python (Docker-based) and Gradle projects.

You are given the **Node.js PIM model** (below) as input to this transformation.

Your task is to **write the ATL modifications** — only the helpers and rules that
need to change — so the transformation correctly produces a GitLab PSM for the
Node.js PIM input.

---

## STRICT CONSTRAINT — ATL constructs only

You must only use ATL language constructs, rule types, helper signatures, and OCL
expressions that are already demonstrated somewhere in this ATL file.

Do not invent new ATL syntax, new rule structures, or new OCL operations that have
no counterpart in the existing file. Every construct you write must have a visible
precedent in the code below.

---

## Existing ATL: pim2gitlabmodel.atl

```atl
-- @nsURI PIM=pimMM=http://www.mddoai.com/mddoai/metamodel/pim
-- @nsURI GitLabMM=gitlabMM=http://www.mddoai.com/mddoai/metamodel/gitlab

module pim2gitlabmodel;
create OUT : GitLabMM from IN : PIM;

-- Main pipeline transformation
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

-- Create a variable
lazy rule createVariable {
    from
        a : PIM!Assignment
    to
        var : GitLabMM!Variable (
            name <- a.key.name,
            value <- a.value.toGitLabString()
        )
}

-- Create variables for the pipeline
lazy rule createVariables {
    from
        assignments : Sequence(PIM!Assignment)
    to
        vars : GitLabMM!Variables (
            variables <- assignments->collect(a | thisModule.createVariable(a))
        )
}

-- Transform PIM Job to GitLab Job
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

-- Map Job steps to GitLab script
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

-- GitLab BeforeScript
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

lazy rule createOnly {
    from
        dummy : OclAny
    to
        only : GitLabMM!Only (
            branches <- Sequence{thisModule.createOnlyBranch('main')}
        )
}

lazy rule createOnlyBranch {
    from
        branchName : String
    to
        branch : GitLabMM!OnlyBranches (
            branch <- branchName
        )
}

lazy rule createDependencies {
    from
        job : PIM!Job
    to
        deps : GitLabMM!Dependencies (
            dependencies <-
                if job.name='healthCheck' then Sequence{thisModule.createDependency('build')}
                else if job.name='push' then Sequence{thisModule.createDependency('healthCheck')}
                else Sequence{}
                endif
                endif
        )
}

lazy rule createDependency {
    from
        depName : String
    to
        dep : GitLabMM!Dependency (
            dependency <- depName
        )
}

helper def : hasArtifacts(job : PIM!Job, pipeline : PIM!Pipeline) : Boolean =
    (job.id = 'build' or job.id = 'unitTest') and
    thisModule.isGradleProject(pipeline);

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

lazy rule createCache {
    from
        steps : Sequence(PIM!Step)
    to
        cache : GitLabMM!Cache (
            key <- let cacheSteps : Sequence(PIM!Cache) =
                       steps->select(s | s.oclIsTypeOf(PIM!Cache))
                   in
                   if cacheSteps->notEmpty() then
                       thisModule.createCacheKey(cacheSteps->first().cacheName.toGitLabString())
                   else
                       OclUndefined
                   endif,
            paths <- let cacheSteps : Sequence(PIM!Cache) =
                         steps->select(s | s.oclIsTypeOf(PIM!Cache))
                     in
                     if cacheSteps->notEmpty() then
                         cacheSteps->first().paths->collect(p | p.toGitLabString())
                     else
                         Sequence{}
                     endif
        )
}

lazy rule createCacheKey {
    from
        keyName : String
    to
        key : GitLabMM!Key (
            name <- keyName
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

helper def : getWhenForJob(job : PIM!Job) : String =
    if job.name='unitTest' then 'always'
    else OclUndefined
    endif;

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

## Input: Node.js PIM Model

This is the PIM produced by the extended `swarch2pim.atl` for the `nodejs-express-api`
SWArch model. Use it as the transformation input.

Key differences from the Python PIM:
- `unitTest` agent image is `node:18-alpine` (not `python:3.12-slim`)
- There is a `lintCheck` job — the Python example had none (`lint_check` was empty there)
- No Gradle command appears anywhere in the job steps

```xml
<?xml version="1.0" encoding="ASCII"?>
<xmi:XMI xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xmlns:pimMM="http://www.mddoai.com/mddoai/metamodel/pim">
  <pimMM:Pipeline name="nodejs-express-api" jobStreams="build unitTest lintCheck healthCheck push">
    <environmentVariables>
      <key name="IMAGE_NAME"/>
      <value xsi:type="pimMM:StringLiteral" value="${CI_REGISTRY_IMAGE}/nodejs-express-api-image"/>
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
  <pimMM:ScriptJob name="unitTest" id="unitTest">
    <agent xsi:type="pimMM:LinuxAgent">
      <container>
        <image xsi:type="pimMM:StringLiteral" value="node:18-alpine"/>
      </container>
    </agent>
    <steps xsi:type="pimMM:Command">
      <program xsi:type="pimMM:StringLiteral" value="npm test"/>
    </steps>
  </pimMM:ScriptJob>
  <pimMM:ScriptJob name="lintCheck" id="lintCheck">
    <steps xsi:type="pimMM:Command">
      <program xsi:type="pimMM:StringLiteral" value="npm run lint"/>
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
</xmi:XMI>
```

---

## Specific Challenges in this ATL

The following helpers in `pim2gitlabmodel.atl` need to be extended for Node.js.
Identify all of them and explain your changes.

### Challenge 1 — `isGradleProject` detection

The existing helper checks for the string `'gradle'` in command strings.
The Node.js detection equivalent must inspect the PIM job steps and/or agent images
visible in the PIM above — the PIM has no language attribute, only command strings
and image names.

### Challenge 2 — `createBeforeScript`

Currently produces one of two sequences:
- Gradle project → Gradle home export commands
- Otherwise → `python3 -m pip install --break-system-packages -r requirements.txt`

Extend this to handle Node.js projects as a distinct branch.

### Challenge 3 — `getStageForJob` (verify)

Check whether this helper needs any changes for Node.js.
If it does not, explain why it is already correct as-is.

---

## Expected Output

Show only the **ATL helpers and rules that need to change**.
Do not rewrite the full file. For each modified helper or rule:
- State the name of the helper/rule being changed
- Show the full updated version of that helper/rule
- Explain in one sentence what gap it fixes
