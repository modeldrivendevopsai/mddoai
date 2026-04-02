# Prompt 01 — Extend swarch2pim ATL Transformation for Node.js

## Purpose of this File

This prompt tests whether an LLM can generate correct ATL transformation rules
by reading an existing ATL file and extending it for a new case — using only
the ATL constructs, patterns, and conventions already demonstrated in the file.

---

## Task

You are given the full `swarch2pim.atl` transformation module and a new SWArch
input model for a Node.js project.

The ATL currently handles Python and Gradle projects. Your task is to **write the
ATL modifications** — helpers and lazy rules that need to change — so the transformation
correctly handles `language="node"` and `language="javascript"` inputs.

---

## STRICT CONSTRAINT — ATL constructs only

You must only use ATL language constructs, rule types, helper signatures, and OCL
expressions that are already demonstrated somewhere in this ATL file.

Do not invent new ATL syntax, new rule structures, or new OCL operations that have
no counterpart in the existing file. Every construct you write must have a visible
precedent in the code below.

---

## Existing ATL: swarch2pim.atl

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

-- Helper: Determine mandatory jobs based on type
helper def: getMandatoryJobs(type : String) : Sequence(String) =
  if type = 'framework' then
    Sequence {'build', 'push'}
  else if type = 'jar' then
    Sequence {'build'}
  else
    Sequence {}
  endif
  endif;

-- Helper: Append element if condition true
helper def: appendIf(seq : Sequence(String), cond : Boolean, elem : String) : Sequence(String) =
  if cond then seq->append(elem)
  else seq
  endif;

-- Helper: Create ScriptJob for a stage
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

-- Determine which agent/image to use for each stage
helper context SWArchMM!SoftwareArchitecture def: getAgentForStage(stage : String) : PIMMM!Agent =
  if stage = 'unitTest' then
    if self.language = 'python' then
      thisModule.createLinuxAgent('python:3.12-slim')
    else if self.language = 'node' or self.language = 'javascript' then
      thisModule.createLinuxAgent('node:18-alpine')
    else
      OclUndefined  -- Use default runner
    endif
    endif
  else if stage = 'healthCheck' then
    thisModule.createLinuxAgent('curlimages/curl:latest')
  else
    OclUndefined  -- Use default runner
  endif
  endif;

-- Create LinuxAgent
lazy rule createLinuxAgent {
  from
    imageName : String
  to
    agent : PIMMM!LinuxAgent (
      container <- thisModule.createDockerContainer(imageName)
    )
}

-- Create DockerContainer
lazy rule createDockerContainer {
  from
    imageName : String
  to
    container : PIMMM!DockerContainer (
      image <- thisModule.createStringLiteral(imageName)
    )
}

-- Helper: Map a SWArch stage to Commands
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

-- Create String Literal object
lazy rule createStringLiteral {
  from
    source : String
  to
    target : PIMMM!StringLiteral (
      value <- source
    )
}

-- Create Command object
lazy rule createCommand {
  from
    source : String
  to
    target : PIMMM!Command (
      program <- thisModule.createStringLiteral(source)
    )
}

-- Helper: Create jobs for all stages
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

-- Create Assignment (Variable = Value)
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

-- Create Variable Declaration
lazy rule createVariableDeclaration {
  from
    source : String
  to
    target : PIMMM!VariableDeclaration (
      name <- source
    )
}
```

---

## Input: New SWArch Model (nodejs-express-api)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<swarch:SoftwareArchitecture xmi:version="2.0"
  xmlns:xmi="http://www.omg.org/XMI"
  xmlns:swarch="http://www.mddoai.com/mddoai/metamodel/swarch"
  name="nodejs-express-api"
  type="framework"
  build_system="docker"
  language="node"
  unit_test="npm test"
  health_check="./scripts/health_check.sh"
  lint_check="npm run lint"/>
```

Attribute walkthrough — tracing through the existing ATL for this input:
- `type="framework"` → `getMandatoryJobs` returns `{'build', 'push'}`
- `build_system="docker"` → `getDefaultAgent` returns `OclUndefined` (no pipeline-level agent)
- `language="node"` → `getAgentForStage('unitTest')` already returns `node:18-alpine` — **but `getCommands('unitTest')` has no node branch, it falls through to the Python `else`**
- `unit_test="npm test"` → non-empty, non-`'true'` → hits the `else` in `getCommands('unitTest')`
- `health_check="./scripts/health_check.sh"` → healthCheck job created, uses `self.health_check` directly
- `lint_check="npm run lint"` → non-empty → lintCheck job created

---

## Reference: Python PIM Output (structural reference)

This is the XMI that the existing ATL produces for the Python input model.
It shows what PIM structure the ATL generates — use it to understand
the shape of output each rule produces.

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

---

## Expected Output

Show only the **ATL helpers and rules that need to change**.
Do not rewrite the full file. For each modified helper or rule:
- State the name of the helper/rule being changed
- Show the full updated version of that helper/rule
- Explain in one sentence what gap it fixes
