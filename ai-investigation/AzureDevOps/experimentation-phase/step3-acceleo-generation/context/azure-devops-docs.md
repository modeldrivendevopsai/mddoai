# Azure Pipelines YAML Schema Reference

Tell us about your PDF experience.
YAML schema reference for Azure Pipelines
The YAML schema reference for Azure Pipelines is a detailed reference for YAML pipelines that
lists all supported YAML syntax and their available options.
To create a YAML pipeline, start with the pipeline definition. For more information about
building YAML pipelines, see Customize your pipeline.
The YAML schema reference does not cover tasks. For more information about tasks, see the
Azure Pipelines tasks index.
pipeline
A pipeline is one or more stages that describe a CI/CD process. The pipeline definition contains
the documentation for root level properties like name .
extends
Extends a pipeline using a template.
jobs
Specifies the jobs that make up the work of a stage.
jobs.deployment
A deployment job is a special type of job. It's a collection of steps to run sequentially against
the environment.
jobs.deployment.environment
Target environment name and optionally a resource name to record the deployment history;
format: environment-name.resource-name.
jobs.deployment.strategy
Execution strategy for this deployment.
jobs.deployment.strategy.canary
Canary Deployment strategy.
jobs.deployment.strategy.rolling
Rolling Deployment strategy.
jobs.deployment.strategy.runOnce
RunOnce Deployment strategy.
Definitions
jobs.job
A job is a collection of steps run by an agent or on a server.
jobs.job.container
Container resource name.
jobs.job.strategy
Execution strategy for this job.
jobs.job.uses
Any resources required by this job that are not already referenced.
jobs.template
A set of jobs defined in a template.
parameters
Specifies the runtime parameters passed to a pipeline.
parameters.parameter
Pipeline template parameters.
pool
Which pool to use for a job of the pipeline.
pool.demands
Demands (for a private pool).
pr
Pull request trigger.
resources
Resources specifies builds, repositories, pipelines, and other resources used by the pipeline.
resources.builds
List of build resources referenced by the pipeline.
resources.builds.build
A build resource used to reference artifacts from a run.
resources.containers
List of container images.
resources.containers.container
A container resource used to reference a container image.
resources.containers.container.trigger
Specify none to disable, true to trigger on all image tags, or use the full syntax as described in
the following examples.
resources.packages
List of package resources.
resources.packages.package
A package resource used to reference a NuGet or npm GitHub package.
resources.pipelines
List of pipeline resources.
resources.pipelines.pipeline
A pipeline resource.
resources.pipelines.pipeline.trigger
Specify none to disable, true to include all branches, or use the full syntax as described in the
following examples.
resources.pipelines.pipeline.trigger.branches
Branches to include or exclude for triggering a run.
resources.repositories
List of repository resources.
resources.repositories.repository
A repository resource is used to reference an additional repository in your pipeline.
resources.webhooks
List of webhooks.
resources.webhooks.webhook
A webhook resource enables you to integrate your pipeline with an external service to
automate the workflow.
resources.webhooks.webhook.filters
List of trigger filters.
resources.webhooks.webhook.filters.filter
Webhook resource trigger filter.
schedules
The schedules list specifies the scheduled triggers for the pipeline.
schedules.cron
A scheduled trigger specifies a schedule on which branches are built.
stages
Stages are a collection of related jobs.
stages.stage
A stage is a collection of related jobs.
stages.template
You can define a set of stages in one file and use it multiple times in other files.
steps
Steps are a linear sequence of operations that make up a job.
steps.bash
Runs a script in Bash on Windows, macOS, and Linux.
steps.checkout
Configure how the pipeline checks out source code.
steps.download
Downloads artifacts associated with the current run or from another Azure Pipeline that is
associated as a pipeline resource.
steps.downloadBuild
Downloads build artifacts.
steps.getPackage
Downloads a package from a package management feed in Azure Artifacts or Azure DevOps
Server.
steps.powershell
Runs a script using either Windows PowerShell (on Windows) or pwsh (Linux and macOS).
steps.publish
Publishes (uploads) a file or folder as a pipeline artifact that other jobs and pipelines can
consume.
steps.pwsh
Runs a script in PowerShell Core on Windows, macOS, and Linux.
steps.reviewApp
Downloads creates a resource dynamically under a deploy phase provider.
steps.script
Runs a script using cmd.exe on Windows and Bash on other platforms.
steps.task
Runs a task.
steps.template
Define a set of steps in one file and use it multiple times in another file.
target
Tasks run in an execution context, which is either the agent host or a container.
target.settableVariables
Restrictions on which variables that can be set.
trigger
Continuous integration (push) trigger.
variables
Define variables using name/value pairs.
variables.group
Reference variables from a variable group.
variables.name
Define variables using name and full syntax.
variables.template
Define variables in a template.
deployHook
Used to run steps that deploy your application.
includeExcludeFilters
Lists of items to include or exclude.
Supporting definitions
７ Note
Supporting definitions are not intended for use directly in a pipeline. Supporting
definitions are used only as part of other definitions, and are included here for reference.
includeExcludeStringFilters
Items to include or exclude.
mountReadOnly
Volumes to mount read-only, the default is all false.
onFailureHook
Used to run steps for rollback actions or clean-up.
onSuccessHook
Used to run steps for rollback actions or clean-up.
onSuccessOrFailureHook
Used to run steps for rollback actions or clean-up.
postRouteTrafficHook
Used to run the steps after the traffic is routed. Typically, these tasks monitor the health of the
updated version for defined interval.
preDeployHook
Used to run steps that initialize resources before application deployment starts.
routeTrafficHook
Used to run steps that serve the traffic to the updated version.
workspace
Workspace options on the agent.
The YAML schema reference is a detailed reference guide to Azure Pipelines YAML pipelines. It
includes a catalog of all supported YAML capabilities and the available options.
Here are the syntax conventions used in the YAML schema reference.
To the left of :  is a literal keyword used in pipeline definitions.
To the right of :  is a data type. The data type can be a primitive type like string or a
reference to a rich structure defined elsewhere in this reference.
The notation [  datatype ]  indicates an array of the mentioned definition type. For
instance, [ string ]  is an array of strings.
The notation {  datatype :  datatype }  indicates a mapping of one data type to another.
For instance, { string: string }  is a mapping of strings to strings.
YAML schema documentation conventions
The symbol |  indicates there are multiple data types available for the keyword. For
instance, job | template  means either a job definition or a template reference is allowed.
Azure DevOps Server 2020.1 YAML schema reference
Azure DevOps Server 2020 YAML schema reference
Azure DevOps Server 2019.1 YAML schema reference
Azure DevOps Server 2019 YAML schema reference
This reference covers the schema of an Azure Pipelines YAML file. To learn the basics of YAML,
see Learn YAML in Y Minutes
. Azure Pipelines doesn't support all YAML features.
Unsupported features include anchors, complex keys, and sets. Also, unlike standard YAML,
Azure Pipelines depends on seeing stage , job , task , or a task shortcut like script  as the first
key in a mapping.
Last updated on 04/02/2026
Previous versions
See also
pipeline definition
ﾃ
Summarize this article for me
A pipeline is one or more stages that describe a CI/CD process.
Implementation
Description
pipeline: stages
Pipeline with stages.
pipeline: extends
Pipeline that extends a template.
pipeline: jobs
Pipeline with jobs and one implicit stage.
pipeline: steps
Pipeline with steps and one implicit job.
A pipeline is one or more stages that describe a CI/CD process. Stages are the major divisions
in a pipeline. The stages "Build this app," "Run these tests," and "Deploy to preproduction" are
good examples.
A stage is one or more jobs, which are units of work assignable to the same machine. You can
arrange both stages and jobs into dependency graphs. Examples include "Run this stage before
that one" and "This job depends on the output of that job."
A job is a linear series of steps. Steps can be tasks, scripts, or references to external templates.
This hierarchy is reflected in the structure of a YAML file like:
YAML
Implementations
ﾉ
Expand table
Remarks
- Pipeline
  - Stage A
    - Job 1
      - Step 1.1
      - Step 1.2
      - ...
    - Job 2
      - Step 2.1
      - Step 2.2
      - ...
Simple pipelines don't require all of these levels. For example, in a single-job build, you can
omit the containers for stages and jobs because there are only steps. And because many
options shown in this article aren't required and have good defaults, your YAML definitions are
unlikely to include all of them.
If you have a single stage, you can omit the stages  keyword and directly specify the jobs
keyword:
YAML
If you have a single stage and a single job, you can omit the stages  and jobs  keywords and
directly specify the steps keyword:
YAML
Use the name  property to configure the pipeline run number. For more information, see
Configure run or build numbers.
Pipeline with stages.
YAML
  - Stage B
    - ...
# ... other pipeline-level keywords
jobs: [ job | template ]
# ... other pipeline-level keywords
steps: [ script | bash | pwsh | powershell | checkout | task | template | ... ]
pipeline: stages
stages: [ stage | template ] # Required. Stages are groups of jobs that can run 
without human intervention.
pool: string | pool # Pool where jobs in this pipeline will run unless otherwise 
specified.
name: string # Pipeline run number.
appendCommitMessageToRunName: boolean # Append the commit message to the build 
number. The default is true.
trigger: none | trigger | [ string ] # Continuous integration triggers.
parameters: [ parameter ] # Pipeline template parameters.
pr: none | pr | [ string ] # Pull request triggers.
schedules: [ cron ] # Scheduled triggers.
resources: # Containers and repositories used in the build.
  builds: [ build ] # List of build resources referenced by the pipeline.
stages  stages. Required.
Stages are groups of jobs that can run without human intervention.
pool  pool.
Pool where jobs in this pipeline will run unless otherwise specified.
name  string.
Pipeline run number.
appendCommitMessageToRunName  boolean.
Append the commit message to the build number. The default is true.
trigger  trigger.
Continuous integration triggers.
parameters  parameters.
Pipeline template parameters.
pr  pr.
Pull request triggers.
schedules  schedules.
Scheduled triggers.
resources  resources.
Containers and repositories used in the build.
variables  variables.
Variables for this pipeline.
lockBehavior  string.
Behavior lock requests from this stage should exhibit in relation to other exclusive lock
requests. sequential | runLatest.
  containers: [ container ] # List of container images.
  pipelines: [ pipeline ] # List of pipeline resources.
  repositories: [ repository ] # List of repository resources.
  webhooks: [ webhook ] # List of webhooks.
  packages: [ package ] # List of package resources.
variables: variables | [ variable ] # Variables for this pipeline.
lockBehavior: sequential | runLatest # Behavior lock requests from this stage 
should exhibit in relation to other exclusive lock requests.
Properties
YAML
Pipeline that extends a template.
YAML
Examples
trigger:
- main
pool: 
  vmImage: ubuntu-latest
stages:
- stage: CI
  jobs:
  - job: CIWork
    steps:
    - script: "Do CI work"
- stage: Test
  jobs:
  - job: TestWork
    steps:
    - script: "Do test work"
pipeline: extends
extends: # Required. Extends a template.
  template: string # The template referenced by the pipeline to extend.
  parameters: # Parameters used in the extend.
pool: string | pool # Pool where jobs in this pipeline will run unless otherwise 
specified.
name: string # Pipeline run number.
appendCommitMessageToRunName: boolean # Append the commit message to the build 
number. The default is true.
trigger: none | trigger | [ string ] # Continuous integration triggers.
parameters: [ parameter ] # Pipeline template parameters.
pr: none | pr | [ string ] # Pull request triggers.
schedules: [ cron ] # Scheduled triggers.
resources: # Containers and repositories used in the build.
  builds: [ build ] # List of build resources referenced by the pipeline.
  containers: [ container ] # List of container images.
  pipelines: [ pipeline ] # List of pipeline resources.
  repositories: [ repository ] # List of repository resources.
  webhooks: [ webhook ] # List of webhooks.
  packages: [ package ] # List of package resources.
variables: variables | [ variable ] # Variables for this pipeline.
extends  extends. Required.
Extends a template.
pool  pool.
Pool where jobs in this pipeline will run unless otherwise specified.
name  string.
Pipeline run number.
appendCommitMessageToRunName  boolean.
Append the commit message to the build number. The default is true.
trigger  trigger.
Continuous integration triggers.
parameters  parameters.
Pipeline template parameters.
pr  pr.
Pull request triggers.
schedules  schedules.
Scheduled triggers.
resources  resources.
Containers and repositories used in the build.
variables  variables.
Variables for this pipeline.
lockBehavior  string.
Behavior lock requests from this stage should exhibit in relation to other exclusive lock
requests. sequential | runLatest.
Pipeline with jobs and one implicit stage.
lockBehavior: sequential | runLatest # Behavior lock requests from this stage 
should exhibit in relation to other exclusive lock requests.
Properties
pipeline: jobs
YAML
jobs  jobs. Required.
Jobs represent units of work which can be assigned to a single agent or server.
pool  pool.
Pool where jobs in this pipeline will run unless otherwise specified.
name  string.
Pipeline run number.
appendCommitMessageToRunName  boolean.
Append the commit message to the build number. The default is true.
trigger  trigger.
Continuous integration triggers.
parameters  parameters.
Pipeline template parameters.
pr  pr.
Pull request triggers.
jobs: [ job | deployment | template ] # Required. Jobs represent units of work 
which can be assigned to a single agent or server.
pool: string | pool # Pool where jobs in this pipeline will run unless otherwise 
specified.
name: string # Pipeline run number.
appendCommitMessageToRunName: boolean # Append the commit message to the build 
number. The default is true.
trigger: none | trigger | [ string ] # Continuous integration triggers.
parameters: [ parameter ] # Pipeline template parameters.
pr: none | pr | [ string ] # Pull request triggers.
schedules: [ cron ] # Scheduled triggers.
resources: # Containers and repositories used in the build.
  builds: [ build ] # List of build resources referenced by the pipeline.
  containers: [ container ] # List of container images.
  pipelines: [ pipeline ] # List of pipeline resources.
  repositories: [ repository ] # List of repository resources.
  webhooks: [ webhook ] # List of webhooks.
  packages: [ package ] # List of package resources.
variables: variables | [ variable ] # Variables for this pipeline.
lockBehavior: sequential | runLatest # Behavior lock requests from this stage 
should exhibit in relation to other exclusive lock requests.
Properties
schedules  schedules.
Scheduled triggers.
resources  resources.
Containers and repositories used in the build.
variables  variables.
Variables for this pipeline.
lockBehavior  string.
Behavior lock requests from this stage should exhibit in relation to other exclusive lock
requests. sequential | runLatest.
YAML
Pipeline with steps and one implicit job.
YAML
Examples
trigger:
- main
pool: 
  vmImage: ubuntu-latest
jobs:
- job: PreWork
  steps:
  - script: "Do pre-work"
- job: PostWork
  pool: windows-latest
  steps:
  - script: "Do post-work using a different hosted image"
pipeline: steps
steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # Required. A list of 
steps to run in this job.
strategy: strategy # Execution strategy for this job.
continueOnError: string # Continue running even on failure?
pool: string | pool # Pool where jobs in this pipeline will run unless otherwise 
specified.
container: string | container # Container resource name.
steps  steps. Required.
A list of steps to run in this job.
strategy  jobs.job.strategy.
Execution strategy for this job.
continueOnError  string.
Continue running even on failure?
pool  pool.
Pool where jobs in this pipeline will run unless otherwise specified.
container  jobs.job.container.
Container resource name.
services  string dictionary.
Container resources to run as a service container.
workspace  workspace.
Workspace options on the agent.
For more information about workspaces, including clean options, see the workspace topic in
Jobs.
services: # Container resources to run as a service container.
  string: string # Name/value pairs
workspace: # Workspace options on the agent.
  clean: outputs | resources | all # What to clean up before the job runs.
name: string # Pipeline run number.
appendCommitMessageToRunName: boolean # Append the commit message to the build 
number. The default is true.
trigger: none | trigger | [ string ] # Continuous integration triggers.
parameters: [ parameter ] # Pipeline template parameters.
pr: none | pr | [ string ] # Pull request triggers.
schedules: [ cron ] # Scheduled triggers.
resources: # Containers and repositories used in the build.
  builds: [ build ] # List of build resources referenced by the pipeline.
  containers: [ container ] # List of container images.
  pipelines: [ pipeline ] # List of pipeline resources.
  repositories: [ repository ] # List of repository resources.
  webhooks: [ webhook ] # List of webhooks.
  packages: [ package ] # List of package resources.
variables: variables | [ variable ] # Variables for this pipeline.
lockBehavior: sequential | runLatest # Behavior lock requests from this stage 
should exhibit in relation to other exclusive lock requests.
Properties
name  string.
Pipeline run number.
appendCommitMessageToRunName  boolean.
Append the commit message to the build number. The default is true.
trigger  trigger.
Continuous integration triggers.
parameters  parameters.
Pipeline template parameters.
pr  pr.
Pull request triggers.
schedules  schedules.
Scheduled triggers.
resources  resources.
Containers and repositories used in the build.
variables  variables.
Variables for this pipeline.
lockBehavior  string.
Behavior lock requests from this stage should exhibit in relation to other exclusive lock
requests. sequential | runLatest.
YAML
Pipelines with multiple jobs
Examples
trigger:
- main
pool: 
  vmImage: ubuntu-latest
steps:
- script: "Hello world!"
See also
Triggers
Variables
Build number formats
Last updated on 03/02/2026
extends definition
ﾃ
Summarize this article for me
Extend a pipeline using a template.
YAML
Definitions that reference this definition: pipeline
template  string.
The template referenced by the pipeline to extend.
parameters  template parameters.
Parameters used in the extend.
The extends  keyword allows a pipeline to use another pipeline (or template) as a base. This is
useful for:
Reusing pipeline structure: Create a base pipeline that multiple projects can extend
Enforcing standards: Define required stages, jobs, or steps that all pipelines must include
Reducing duplication: Share common configuration across pipelines
Template inheritance: Build pipelines from templates with parameters
A pipeline can extend a single template. The template file should be a complete, valid pipeline
structure. When you use extends , your pipeline YAML becomes a template that references the
base template and optionally overrides parameters.
extends : Creates a pipeline that inherits from a template. Used at the root level of a
pipeline.
include : Imports template content directly. Used at specific points within a pipeline.
extends:
  template: string # The template referenced by the pipeline to extend.
  parameters: # Parameters used in the extend.
Properties
Usage
Extends vs includes
The simplest way to use extends  is to have a pipeline inherit stages and jobs from a base
template.
YAML
YAML
In this example, the pipeline in azure-pipelines.yml  extends the base template to inherit the
stages and jobs. The trigger  and pool  are defined in the consuming pipeline, not in the
template.
You can pass parameters to a template when extending it:
YAML
Examples
Basic extends example
# File: base-pipeline.yml
# This is the base template that other pipelines extend
stages:
- stage: Build
  jobs:
  - job: BuildJob
    steps:
    - script: echo Building...
      displayName: 'Build step'
# File: azure-pipelines.yml
# This pipeline extends the base template
trigger:
  - main
pool:
  vmImage: 'ubuntu-latest'
extends:
  template: base-pipeline.yml
Extends with parameters
# File: base-pipeline.yml
parameters:
- name: buildConfiguration
  type: string
YAML
In this example, the template defines parameters and stages, while the consuming pipeline
defines trigger  and pool  alongside the extends  keyword.
You can use extends  to inherit multiple stages from a template. This is useful for teams that
want a consistent pipeline structure across projects.
YAML
  default: 'Debug'
stages:
- stage: Build
  jobs:
  - job: BuildJob
    steps:
    - script: echo Building with configuration ${{ parameters.buildConfiguration }}
      displayName: 'Build step'
# File: azure-pipelines.yml
trigger:
  - main
pool:
  vmImage: 'ubuntu-latest'
extends:
  template: base-pipeline.yml
  parameters:
    buildConfiguration: 'Release'
Multi-stage pipeline with extends
# File: multi-stage-template.yml
# This template defines a three-stage pipeline structure
stages:
- stage: Build
  displayName: 'Build Stage'
  jobs:
  - job: BuildJob
    displayName: 'Build Job'
    steps:
    - script: npm install
      displayName: 'Install dependencies'
    - script: npm run build
      displayName: 'Build application'
- stage: Test
  displayName: 'Test Stage'
YAML
In this example, the pipeline extends the template to inherit all three stages (Build, Test, and
Deploy) with their dependencies and job definitions. The trigger  and pool  are defined in the
consuming pipeline.
Templates and their parameters are turned into constants before the pipeline runs. Template
parameters provide type safety to input parameters. In this example, the template start.yml
defines the parameter buildSteps , which is then used in azure-pipelines.yml . If a buildStep
gets passed with a script step, then it's rejected and the pipeline build fails.
YAML
  dependsOn: Build
  jobs:
  - job: TestJob
    displayName: 'Run Tests'
    steps:
    - script: npm run test
      displayName: 'Run unit tests'
    - script: npm run lint
      displayName: 'Run linter'
- stage: Deploy
  displayName: 'Deploy Stage'
  dependsOn: Test
  jobs:
  - job: DeployJob
    displayName: 'Deploy Job'
    steps:
    - script: npm run deploy
      displayName: 'Deploy to production'
# File: azure-pipelines.yml
trigger:
  - main
pool:
  vmImage: 'ubuntu-latest'
extends:
  template: multi-stage-template.yml
Type-safe parameters with extends
# File: start.yml
parameters:
- name: buildSteps # the name of the parameter is buildSteps
  type: stepList # data type is StepList
YAML
Template types & usage
  default: [] # default value of buildSteps
stages:
- stage: secure_buildstage
  pool:
    vmImage: windows-latest
  jobs:
  - job: secure_buildjob
    steps:
    - script: echo This happens before code 
      displayName: 'Base: Pre-build'
    - script: echo Building
      displayName: 'Base: Build'
    - ${{ each step in parameters.buildSteps }}:
      - ${{ each pair in step }}:
          ${{ if ne(pair.value, 'CmdLine@2') }}:
            ${{ pair.key }}: ${{ pair.value }}       
          ${{ if eq(pair.value, 'CmdLine@2') }}: 
            # Step is rejected by raising a YAML syntax error: Unexpected value 
'CmdLine@2'
            '${{ pair.value }}': error         
    - script: echo This happens after code
      displayName: 'Base: Signing'
# File: azure-pipelines.yml
trigger:
- main
extends:
  template: start.yml
  parameters:
    buildSteps:  
      - bash: echo Test #Passes
        displayName: succeed
      - bash: echo "Test"
        displayName: succeed
      # Step is rejected by raising a YAML syntax error: Unexpected value 
'CmdLine@2'
      - task: CmdLine@2
        inputs:
          script: echo "Script Test"
      # Step is rejected by raising a YAML syntax error: Unexpected value 
'CmdLine@2'
      - script: echo "Script Test"
See also
Security through templates
） Note: The author created this article with assistance from AI. Learn more
Last updated on 03/02/2026
jobs definition
ﾃ
Summarize this article for me
Specifies the jobs that make up the work of a stage.
YAML
Definitions that reference this definition: pipeline, stages.stage
Type
Description
jobs.job
A job is a collection of steps run by an agent or on a server.
jobs.deployment
A deployment job is a special type of job. It's a collection of steps to run sequentially
against the environment.
jobs.template
A set of jobs defined in a template.
A job is a collection of steps run by an agent or on a server. Jobs can run conditionally and
might depend on earlier jobs.
YAML
jobs: [ job | deployment | template ] # Specifies the jobs that make up the work of 
a stage.
List types
ﾉ
Expand table
Remarks
７ Note
If you have only one stage and one job, you can use single-job syntax as a shorter way to
describe the steps to run.
Examples
jobs:
- job: MyJob
For more information about uses , see Limit job authorization scope to referenced Azure
DevOps repositories. For more information about workspaces, including clean options,
see the workspace topic in Jobs.
Learn more about variables, steps, pools, and server jobs.
Last updated on 03/02/2026
  displayName: My First Job
  continueOnError: true
  workspace:
    clean: outputs
  steps:
  - script: echo My first job
See also
jobs.deployment definition
ﾃ
Summarize this article for me
A deployment job is a special type of job. It's a collection of steps to run sequentially against
the environment.
YAML
Definitions that reference this definition: jobs
deployment  string. Required as first property.
Name of the deployment job, A-Z, a-z, 0-9, and underscore. The word deploy is a keyword and
is unsupported as the deployment name.
jobs:
- deployment: string # Required as first property. Name of the deployment job, A-Z, 
a-z, 0-9, and underscore. The word deploy is a keyword and is unsupported as the 
deployment name.
  displayName: string # Human-readable name for the deployment.
  dependsOn: string | [ string ] # Any jobs which must complete before this one.
  condition: string # Evaluate this condition expression to determine whether to 
run this deployment.
  continueOnError: string # Continue running even on failure?
  timeoutInMinutes: string # Time to wait for this job to complete before the 
server kills it.
  cancelTimeoutInMinutes: string # Time to wait for the job to cancel before 
forcibly terminating it.
  variables: variables | [ variable ] # Deployment-specific variables.
  pool: string | pool # Pool where this job will run.
  environment: string | environment # Target environment name and optionally a 
resource name to record the deployment history; format: environment-name.resource-
name.
  strategy: strategy # Execution strategy for this deployment.
  workspace: # Workspace options on the agent.
    clean: outputs | resources | all # What to clean up before the job runs.
  uses: # Any resources required by this job that are not already referenced.
    repositories: [ string ] # Repository references.
    pools: [ string ] # Pool references.
  container: string | container # Container resource name.
  services: # Container resources to run as a service container.
    string: string # Name/value pairs
  templateContext: # Deployment related information passed from a pipeline when 
extending a template.
Properties
displayName  string.
Human-readable name for the deployment.
dependsOn  string | string list.
Any jobs which must complete before this one.
condition  string.
Evaluate this condition expression to determine whether to run this deployment.
continueOnError  string.
Continue running even on failure?
timeoutInMinutes  string.
Time to wait for this job to complete before the server kills it.
cancelTimeoutInMinutes  string.
Time to wait for the job to cancel before forcibly terminating it.
variables  variables.
Deployment-specific variables.
pool  pool.
Pool where this job will run.
environment  jobs.deployment.environment.
Target environment name and optionally a resource name to record the deployment history;
format: environment-name.resource-name.
strategy  jobs.deployment.strategy.
Execution strategy for this deployment.
workspace  workspace.
Workspace options on the agent.
For more information about workspaces, including clean options, see the workspace topic in
Jobs.
uses  jobs.job.uses.
Specifies resources required by this job that are not already referenced elsewhere in the
pipeline, for example by a checkout step or a repository resource. For more information about
uses , see Limit job authorization scope and "uses" statement for pre-declaring resources.
container  jobs.job.container.
Container resource name.
services  string dictionary.
Container resources to run as a service container.
templateContext  templateContext.
Deployment related information passed from a pipeline when extending a template. See
remarks for more information. For more information about templateContext , see Extended
YAML Pipelines templates can now be passed context information for stages, jobs, and
deployments and Templates - Use templateContext to pass properties to templates.
In YAML pipelines, the pipelines team recommends that you put your deployment steps in a
deployment job.
For more information about templateContext , see Extended YAML Pipelines templates can now
be passed context information for stages, jobs, and deployments and Templates - Use
templateContext to pass properties to templates.
YAML
Last updated on 03/02/2026
Remarks
Examples
jobs:
  # track deployments on the environment
- deployment: DeployWeb
  displayName: deploy Web App
  pool:
    vmImage: ubuntu-latest
  # creates an environment if it doesn't exist
  environment: 'smarthotel-dev'
  strategy:
    # default deployment strategy, more coming...
    runOnce:
      deploy:
        steps:
        - script: echo my first deployment
jobs.deployment.environment definition
ﾃ
Summarize this article for me
The environment  keyword specifies the environment or its resource that is targeted by a
deployment job of the pipeline.
Definitions that reference this definition: jobs.deployment
Implementation
Description
environment: string
Deployment job with environment
name.
environment: name, resourceName, resourceId, resourceType,
tags
Full syntax for complete control.
An environment also holds information about the deployment strategy for running the steps
defined inside the job.
You can reduce the deployment target's scope to a particular resource within the environment
as shown here:
YAML
Implementations
ﾉ
Expand table
Remarks
environment: 'smarthotel-dev.bookings'
strategy:
  runOnce:
    deploy:
      steps:
      - task: KubernetesManifest@0
        displayName: Deploy to Kubernetes cluster
        inputs:
          action: deploy
          namespace: $(k8sNamespace)
          manifests: $(System.ArtifactsDirectory)/manifests/*
          imagePullSecrets: $(imagePullSecret)
          containers: $(containerRegistry)/$(imageRepository):$(tag)
          # value for kubernetesServiceConnection input automatically passed down 
to task by environment.resource input
To specify an environment by name without using any additional properties, use the following
syntax.
YAML
environment  string.
Deployment job with environment name.
YAML
To configure environment properties in addition to the name, use the full syntax.
YAML
name  string.
Name of environment.
environment: string
environment: string # Deployment job with environment name.
Examples
environment: environmentName.resourceName
strategy:                 # deployment strategy
  runOnce:              # default strategy
    deploy:
      steps:
      - script: echo Hello world
environment: name, resourceName, resourceId,
resourceType, tags
environment:
  name: string # Name of environment.
  resourceName: string # Name of resource.
  resourceId: string # Id of resource.
  resourceType: string # Type of environment resource.
  tags: string # List of tag filters.
Properties
resourceName  string.
Name of resource.
resourceId  string.
Id of resource.
resourceType  string.
Type of environment resource.
tags  string.
List of tag filters.
The full syntax is:
YAML
If you specify an environment or one of its resources but don't need to specify other
properties, you can shorten the syntax to:
YAML
You can reduce the deployment target's scope to a particular resource within the environment
as shown here:
Examples
environment:            # create environment and/or record deployments
  name: string          # name of the environment to run this job on.
  resourceName: string  # name of the resource in the environment to record the 
deployments against
  resourceId: number    # resource identifier
  resourceType: string  # type of the resource you want to target. Supported types 
- virtualMachine, Kubernetes
  tags: string          # comma separated tag names to filter the resources in the 
environment
strategy:               # deployment strategy
  runOnce:              # default strategy
    deploy:
      steps:
      - script: echo Hello world
environment: environmentName.resourceName
strategy:         # deployment strategy
  runOnce:        # default strategy
    deploy:
      steps:
      - script: echo Hello world
YAML
Create and target an environment
Last updated on 03/02/2026
environment: 'smarthotel-dev.bookings'
strategy:
  runOnce:
    deploy:
      steps:
      - task: KubernetesManifest@0
        displayName: Deploy to Kubernetes cluster
        inputs:
          action: deploy
          namespace: $(k8sNamespace)
          manifests: $(System.ArtifactsDirectory)/manifests/*
          imagePullSecrets: $(imagePullSecret)
          containers: $(containerRegistry)/$(imageRepository):$(tag)
          # value for kubernetesServiceConnection input automatically passed down 
to task by environment.resource input
See also
jobs.deployment.strategy definition
ﾃ
Summarize this article for me
A deployment strategy enables you to configure how the update is delivered.
Definitions that reference this definition: jobs.deployment
Implementation
Description
strategy: runOnce
Run once deployment strategy.
strategy: rolling
Rolling deployment strategy.
strategy: canary
Canary deployment strategy.
When you're deploying application updates, it's important that the technique you use to
deliver the update will:
Enable initialization.
Deploy the update.
Route traffic to the updated version.
Test the updated version after routing traffic.
In case of failure, run steps to restore to the last known good version.
We achieve this by using lifecycle hooks that can run steps during deployment. Each of the
lifecycle hooks resolves into an agent job or a server job (or a container or validation job in the
future), depending on the pool attribute. By default, the lifecycle hooks will inherit the pool
specified by the deployment job.
Deployment jobs use the $(Pipeline.Workspace) system variable.
If you are using self-hosted agents, you can use the workspace clean options to clean your
deployment workspace.
YAML
Implementations
ﾉ
Expand table
Remarks
The runOnce deployment strategy rolls out changes by executing each of its steps one time.
YAML
  jobs:
  - deployment: deploy
    pool:
      vmImage: ubuntu-latest
      workspace:
        clean: all
    environment: staging
strategy: runOnce
strategy:
  runOnce: # RunOnce Deployment strategy.
    preDeploy: # Pre deploy hook for runOnce deployment strategy.
      steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
      pool: string | pool # Pool where pre deploy steps will run.
    deploy: # Deploy hook for runOnce deployment strategy.
      steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
      pool: string | pool # Pool where deploy steps will run.
    routeTraffic: # Route traffic hook for runOnce deployment strategy.
      steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
      pool: string | pool # Pool where route traffic steps will run.
    postRouteTraffic: # Post route traffic hook for runOnce deployment strategy.
      steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
      pool: string | pool # Pool where post route traffic steps will run.
    on: # On success or failure hook for runOnce deployment strategy.
      failure: # Runs on failure of any step.
        steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
        pool: string | pool # Pool where post on failure steps will run.
      success: # Runs on success of all of the steps.
        steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
        pool: string | pool # Pool where on success steps will run.
Properties
runOnce  jobs.deployment.strategy.runOnce.
RunOnce Deployment strategy.
runOnce  is the simplest deployment strategy wherein all the lifecycle hooks, namely preDeploy
deploy , routeTraffic , and postRouteTraffic , are executed once. Then, either on: success  or
on: failure  is executed.
A rolling deployment replaces instances of the previous version of an application with
instances of the new version of the application on a fixed set of virtual machines (rolling set) in
each iteration.
YAML
Remarks
strategy: rolling
strategy:
  rolling: # Rolling Deployment strategy.
    maxParallel: string # Maximum number of jobs running in parallel.
    preDeploy: # Pre deploy hook for rolling deployment strategy.
      steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
      pool: string | pool # Pool where pre deploy steps will run.
    deploy: # Deploy hook for rolling deployment strategy.
      steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
      pool: string | pool # Pool where deploy steps will run.
    routeTraffic: # Route traffic hook for rolling deployment strategy.
      steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
      pool: string | pool # Pool where route traffic steps will run.
    postRouteTraffic: # Post route traffic hook for rolling deployment strategy.
      steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
      pool: string | pool # Pool where post route traffic steps will run.
    on: # On success or failure hook for rolling deployment strategy.
      failure: # Runs on failure of any step.
        steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
        pool: string | pool # Pool where post on failure steps will run.
      success: # Runs on success of all of the steps.
        steps: [ task | script | powershell | pwsh | bash | checkout | download | 
rolling  jobs.deployment.strategy.rolling.
Rolling Deployment strategy.
Canary deployment strategy rolls out changes to a small subset of servers.
YAML
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
        pool: string | pool # Pool where on success steps will run.
Properties
strategy: canary
strategy:
  canary: # Canary Deployment strategy.
    increments: [ string ] # Maximum batch size for deployment.
    preDeploy: # Pre deploy hook for canary deployment strategy.
      steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
      pool: string | pool # Pool where pre deploy steps will run.
    deploy: # Deploy hook for canary deployment strategy.
      steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
      pool: string | pool # Pool where deploy steps will run.
    routeTraffic: # Route traffic hook for canary deployment strategy.
      steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
      pool: string | pool # Pool where route traffic steps will run.
    postRouteTraffic: # Post route traffic hook for canary deployment strategy.
      steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
      pool: string | pool # Pool where post route traffic steps will run.
    on: # On success or failure hook for canary deployment strategy.
      failure: # Runs on failure of any step.
        steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
        pool: string | pool # Pool where post on failure steps will run.
      success: # Runs on success of all of the steps.
        steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
        pool: string | pool # Pool where on success steps will run.
canary  jobs.deployment.strategy.canary.
Canary Deployment strategy.
Canary deployment strategy is an advanced deployment strategy that helps mitigate the risk
involved in rolling out new versions of applications. By using this strategy, you can roll out the
changes to a small subset of servers first. As you gain more confidence in the new version, you
can release it to more servers in your infrastructure and route more traffic to it.
Deployment jobs
Last updated on 03/02/2026
Properties
Remarks
See also
jobs.deployment.strategy.canary definition
ﾃ
Summarize this article for me
Canary deployment strategy rolls out changes to a small subset of servers.
YAML
Definitions that reference this definition: jobs.deployment.strategy
increments  string list.
Maximum batch size for deployment.
canary:
  increments: [ string ] # Maximum batch size for deployment.
  preDeploy: # Pre deploy hook for canary deployment strategy.
    steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
    pool: string | pool # Pool where pre deploy steps will run.
  deploy: # Deploy hook for canary deployment strategy.
    steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
    pool: string | pool # Pool where deploy steps will run.
  routeTraffic: # Route traffic hook for canary deployment strategy.
    steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
    pool: string | pool # Pool where route traffic steps will run.
  postRouteTraffic: # Post route traffic hook for canary deployment strategy.
    steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
    pool: string | pool # Pool where post route traffic steps will run.
  on: # On success or failure hook for canary deployment strategy.
    failure: # Runs on failure of any step.
      steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
      pool: string | pool # Pool where post on failure steps will run.
    success: # Runs on success of all of the steps.
      steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
      pool: string | pool # Pool where on success steps will run.
Properties
preDeploy  preDeployHook.
Pre deploy hook for canary deployment strategy.
deploy  deployHook.
Deploy hook for canary deployment strategy.
routeTraffic  routeTrafficHook.
Route traffic hook for canary deployment strategy.
postRouteTraffic  postRouteTrafficHook.
Post route traffic hook for canary deployment strategy.
on  onSuccessOrFailureHook.
On success or failure hook for canary deployment strategy.
Canary deployment strategy is an advanced deployment strategy that helps mitigate the risk
involved in rolling out new versions of applications. By using this strategy, you can roll out the
changes to a small subset of servers first. As you gain more confidence in the new version, you
can release it to more servers in your infrastructure and route more traffic to it.
Canary deployment strategy supports the preDeploy  lifecycle hook (executed once) and
iterates with the deploy , routeTraffic , and postRouteTraffic  lifecycle hooks. It then exits with
either the success  or failure  hook.
The following variables are available in this strategy:
strategy.name : Name of the strategy. For example, canary.
strategy.action : The action to be performed on the Kubernetes cluster. For example, deploy,
promote, or reject.
strategy.increment : The increment value used in the current interaction. This variable is
available only in deploy , routeTraffic , and postRouteTraffic  lifecycle hooks.
preDeploy : Used to run steps that initialize resources before application deployment starts.
deploy : Used to run steps that deploy your application. Download artifact task will be auto
injected only in the deploy  hook for deployment jobs. To stop downloading artifacts, use -
download: none  or choose specific artifacts to download by specifying Download Pipeline
Artifact task.
Remarks
Descriptions of lifecycle hooks
routeTraffic : Used to run steps that serve the traffic to the updated version.
postRouteTraffic : Used to run the steps after the traffic is routed. Typically, these tasks
monitor the health of the updated version for defined interval.
on: failure  or on: success : Used to run steps for rollback actions or clean-up.
In the following example, the canary strategy for AKS will first deploy the changes with 10
percent pods, followed by 20 percent, while monitoring the health during postRouteTraffic . If
all goes well, it will promote to 100 percent.
YAML
Examples
jobs: 
- deployment: 
  environment: smarthotel-dev.bookings
  pool: 
    name: smarthotel-devPool
  strategy:                  
    canary:      
      increments: [10,20]  
      preDeploy:                                     
        steps:           
        - script: initialize, cleanup....   
      deploy:             
        steps: 
        - script: echo deploy updates... 
        - task: KubernetesManifest@0 
          inputs: 
            action: $(strategy.action)       
            namespace: 'default' 
            strategy: $(strategy.name) 
            percentage: $(strategy.increment) 
            manifests: 'manifest.yml' 
      postRouteTraffic: 
        pool: server 
        steps:           
        - script: echo monitor application health...   
      on: 
        failure: 
          steps: 
          - script: echo clean-up, rollback...   
        success: 
          steps: 
          - script: echo checks passed, notify... 
Deployment jobs
Last updated on 03/02/2026
See also
jobs.deployment.strategy.rolling definition
ﾃ
Summarize this article for me
A rolling deployment replaces instances of the previous version of an application with
instances of the new version of the application on a fixed set of virtual machines (rolling set) in
each iteration.
YAML
Definitions that reference this definition: jobs.deployment.strategy
rolling:
  maxParallel: string # Maximum number of jobs running in parallel.
  preDeploy: # Pre deploy hook for rolling deployment strategy.
    steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
    pool: string | pool # Pool where pre deploy steps will run.
  deploy: # Deploy hook for rolling deployment strategy.
    steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
    pool: string | pool # Pool where deploy steps will run.
  routeTraffic: # Route traffic hook for rolling deployment strategy.
    steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
    pool: string | pool # Pool where route traffic steps will run.
  postRouteTraffic: # Post route traffic hook for rolling deployment strategy.
    steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
    pool: string | pool # Pool where post route traffic steps will run.
  on: # On success or failure hook for rolling deployment strategy.
    failure: # Runs on failure of any step.
      steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
      pool: string | pool # Pool where post on failure steps will run.
    success: # Runs on success of all of the steps.
      steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
      pool: string | pool # Pool where on success steps will run.
Properties
maxParallel  string.
Maximum number of jobs running in parallel.
preDeploy  preDeployHook.
Pre deploy hook for rolling deployment strategy.
deploy  deployHook.
Deploy hook for rolling deployment strategy.
routeTraffic  routeTrafficHook.
Route traffic hook for rolling deployment strategy.
postRouteTraffic  postRouteTrafficHook.
Post route traffic hook for rolling deployment strategy.
on  onSuccessOrFailureHook.
On success or failure hook for rolling deployment strategy.
Azure Pipelines currently only support the rolling strategy to VM resources.
For example, a rolling deployment typically waits for deployments on each set of virtual
machines to complete before proceeding to the next set of deployments. You could do a
health check after each iteration and if a significant issue occurs, the rolling deployment can be
stopped.
Rolling deployments can be configured by specifying the keyword rolling:  under the
strategy:  node. The strategy.name  variable is available in this strategy block, which takes the
name of the strategy. In this case, rolling.
All the lifecycle hooks are supported and lifecycle hook jobs are created to run on each VM.
preDeploy , deploy , routeTraffic , and postRouteTraffic  are executed once per batch size
defined by maxParallel . Then, either on: success  or on: failure  is executed.
With maxParallel: <# or % of VMs> , you can control the number/percentage of virtual
machine targets to deploy to in parallel. This ensures that the app is running on these
machines and is capable of handling requests while the deployment is taking place on the rest
of the machines, which reduces overall downtime.
Remarks
７ Note
preDeploy : Used to run steps that initialize resources before application deployment starts.
deploy : Used to run steps that deploy your application. Download artifact task will be auto
injected only in the deploy  hook for deployment jobs. To stop downloading artifacts, use -
download: none  or choose specific artifacts to download by specifying Download Pipeline
Artifact task.
routeTraffic : Used to run steps that serve the traffic to the updated version.
postRouteTraffic : Used to run the steps after the traffic is routed. Typically, these tasks
monitor the health of the updated version for defined interval.
on: failure  or on: success : Used to run steps for rollback actions or clean-up.
The following rolling strategy example for VMs updates up to five targets in each iteration.
maxParallel  will determine the number of targets that can be deployed to, in parallel. The
selection accounts for absolute number or percentage of targets that must remain available at
any time excluding the targets that are being deployed to. It is also used to determine the
success and failure conditions during deployment.
YAML
There are a few known gaps in this feature. For example, when you retry a stage, it will re-
run the deployment on all VMs not just failed targets.
Descriptions of lifecycle hooks
Examples
jobs: 
- deployment: VMDeploy
  displayName: web
  environment:
    name: smarthotel-dev
    resourceType: VirtualMachine
  strategy:
    rolling:
      maxParallel: 5  #for percentages, mention as x%
      preDeploy:
        steps:
        - download: current
          artifact: drop
        - script: echo initialize, cleanup, backup, install certs
      deploy:
        steps:
Deployment jobs
Last updated on 03/02/2026
        - task: IISWebAppDeploymentOnMachineGroup@0
          displayName: 'Deploy application to Website'
          inputs:
            WebSiteName: 'Default Web Site'
            Package: '$(Pipeline.Workspace)/drop/**/*.zip'
      routeTraffic:
        steps:
        - script: echo routing traffic
      postRouteTraffic:
        steps:
        - script: echo health check post-route traffic
      on:
        failure:
          steps:
          - script: echo Restore from backup! This is on failure
        success:
          steps:
          - script: echo Notify! This is on success
See also
jobs.deployment.strategy.runOnce
definition
ﾃ
Summarize this article for me
The runOnce deployment strategy rolls out changes by executing each of its steps one time.
YAML
Definitions that reference this definition: jobs.deployment.strategy
preDeploy  preDeployHook.
Pre deploy hook for runOnce deployment strategy.
runOnce:
  preDeploy: # Pre deploy hook for runOnce deployment strategy.
    steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
    pool: string | pool # Pool where pre deploy steps will run.
  deploy: # Deploy hook for runOnce deployment strategy.
    steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
    pool: string | pool # Pool where deploy steps will run.
  routeTraffic: # Route traffic hook for runOnce deployment strategy.
    steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
    pool: string | pool # Pool where route traffic steps will run.
  postRouteTraffic: # Post route traffic hook for runOnce deployment strategy.
    steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
    pool: string | pool # Pool where post route traffic steps will run.
  on: # On success or failure hook for runOnce deployment strategy.
    failure: # Runs on failure of any step.
      steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
      pool: string | pool # Pool where post on failure steps will run.
    success: # Runs on success of all of the steps.
      steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
      pool: string | pool # Pool where on success steps will run.
Properties
deploy  deployHook.
Deploy hook for runOnce deployment strategy.
routeTraffic  routeTrafficHook.
Route traffic hook for runOnce deployment strategy.
postRouteTraffic  postRouteTrafficHook.
Post route traffic hook for runOnce deployment strategy.
on  onSuccessOrFailureHook.
On success or failure hook for runOnce deployment strategy.
runOnce  is the simplest deployment strategy wherein all the lifecycle hooks, namely preDeploy
deploy , routeTraffic , and postRouteTraffic , are executed once. Then, either on:  success  or
on:  failure  is executed.
preDeploy : Used to run steps that initialize resources before application deployment starts.
deploy : Used to run steps that deploy your application. Download artifact task will be auto
injected only in the deploy  hook for deployment jobs. To stop downloading artifacts, use -
download: none  or choose specific artifacts to download by specifying Download Pipeline
Artifact task.
routeTraffic : Used to run steps that serve the traffic to the updated version.
postRouteTraffic : Used to run the steps after the traffic is routed. Typically, these tasks
monitor the health of the updated version for defined interval.
on: failure  or on: success : Used to run steps for rollback actions or clean-up.
The following example YAML snippet showcases a simple use of a deployment job by using the
runOnce  deployment strategy. The example includes a checkout step.
YAML
Remarks
Descriptions of lifecycle hooks
Examples
With each run of this job, deployment history is recorded against the smarthotel-dev
environment.
The next example demonstrates how a pipeline can refer both an environment and a resource
to be used as the target for a deployment job.
YAML
jobs:
  # Track deployments on the environment.
- deployment: DeployWeb
  displayName: deploy Web App
  pool:
    vmImage: ubuntu-latest
  # Creates an environment if it doesn't exist.
  environment: 'smarthotel-dev'
  strategy:
    runOnce:
      deploy:
        steps:
        - checkout: self 
        - script: echo my first deployment
７ Note
It's also possible to create an environment with empty resources and use that as an
abstract shell to record deployment history, as shown in the previous example.
jobs:
- deployment: DeployWeb
  displayName: deploy Web App
  pool:
    vmImage: ubuntu-latest
  # Records deployment against bookings resource - Kubernetes namespace.
  environment: 'smarthotel-dev.bookings'
  strategy: 
    runOnce:
      deploy:
        steps:
          # No need to explicitly pass the connection details.
        - task: KubernetesManifest@0
          displayName: Deploy to Kubernetes cluster
          inputs:
            action: deploy
            namespace: $(k8sNamespace)
            manifests: |
              $(System.ArtifactsDirectory)/manifests/*
            imagePullSecrets: |
This approach has the following benefits:
Records deployment history on a specific resource within the environment, as opposed to
recording the history on all resources within the environment.
Steps in the deployment job automatically inherit the connection details of the resource
(in this case, a Kubernetes namespace, smarthotel-dev.bookings ), because the
deployment job is linked to the environment. This is useful in the cases where the same
connection detail is set for multiple steps of the job.
Deployment jobs
Last updated on 03/02/2026
              $(imagePullSecret)
            containers: |
              $(containerRegistry)/$(imageRepository):$(tag)
See also
jobs.job definition
ﾃ
Summarize this article for me
A job is a collection of steps run by an agent or on a server.
YAML
Definitions that reference this definition: jobs
job  string. Required as first property.
ID of the job. Acceptable values: Valid names may only contain alphanumeric characters and '_'
and may not start with a number.
displayName  string.
Human-readable name for the job.
jobs:
- job: string # Required as first property. ID of the job.
  displayName: string # Human-readable name for the job.
  dependsOn: string | [ string ] # Any jobs which must complete before this one.
  condition: string # Evaluate this condition expression to determine whether to 
run this job.
  continueOnError: string # Continue running even on failure?
  timeoutInMinutes: string # Time to wait for this job to complete before the 
server kills it.
  cancelTimeoutInMinutes: string # Time to wait for the job to cancel before 
forcibly terminating it.
  variables: variables | [ variable ] # Job-specific variables.
  strategy: strategy # Execution strategy for this job.
  pool: string | pool # Pool where this job will run.
  container: string | container # Container resource name.
  services: # Container resources to run as a service container.
    string: string # Name/value pairs
  workspace: # Workspace options on the agent.
    clean: outputs | resources | all # What to clean up before the job runs.
  uses: # Any resources required by this job that are not already referenced.
    repositories: [ string ] # Repository references.
    pools: [ string ] # Pool references.
  steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
  templateContext: # Job related information passed from a pipeline when extending 
a template.
Properties
dependsOn  string | string list.
Any jobs which must complete before this one.
condition  string.
Evaluate this condition expression to determine whether to run this job.
continueOnError  string.
Continue running even on failure?
timeoutInMinutes  string.
Time to wait for this job to complete before the server kills it.
cancelTimeoutInMinutes  string.
Time to wait for the job to cancel before forcibly terminating it.
variables  variables.
Job-specific variables.
strategy  jobs.job.strategy.
Execution strategy for this job.
pool  pool.
Pool where this job will run.
container  jobs.job.container.
Container resource name.
services  string dictionary.
Container resources to run as a service container.
workspace  workspace.
Workspace options on the agent. For more information about workspaces, including clean
options, see the workspace topic in Jobs.
uses  jobs.job.uses.
Specifies resources required by this job that are not already referenced elsewhere in the
pipeline, for example by a checkout step or a repository resource. For more information about
uses , see Limit job authorization scope and "uses" statement for pre-declaring resources.
steps  steps.
A list of steps to run.
templateContext  templateContext.
Job related information passed from a pipeline when extending a template. See remarks for
more information. For more information about templateContext , see Extended YAML Pipelines
templates can now be passed context information for stages, jobs, and deployments and
Templates - Use templateContext to pass properties to templates.
The default timeoutInMinutes  is set to 60 minutes. For more information, see Timeouts.
Jobs can run conditionally and might depend on earlier jobs.
For more information about templateContext , see Extended YAML Pipelines templates can now
be passed context information for stages, jobs, and deployments and Templates - Use
templateContext to pass properties to templates.
YAML
For more information about uses , see Limit job authorization scope to referenced Azure
DevOps repositories.
For more information about workspaces, including clean options, see the workspace topic
in Jobs.
Learn more about variables, steps, pools, and server jobs.
Remarks
７ Note
If you have only one stage and one job, you can use single-job syntax as a shorter way to
describe the steps to run.
Examples
jobs:
- job: MyJob
  displayName: My First Job
  continueOnError: true
  workspace:
    clean: outputs
  steps:
  - script: echo My first job
See also
Last updated on 03/02/2026
jobs.job.container definition
ﾃ
Summarize this article for me
Container jobs allow you to run jobs on a container instead of the agent host.
Definitions that reference this definition: pipeline, jobs.job, jobs.deployment
Implementation
Description
container: string
Specify job container by alias.
container: image
Specify job container using image tag and options.
Specify job container by alias.
YAML
container  string.
Specify job container by alias.
The alias can be the name of an image, or it can be a reference to a container resource.
The following example fetches the ubuntu image tagged 18.04 from Docker Hub
 and then
starts the container. When the printenv  command runs, it happens inside the ubuntu:18.04
container.
YAML
Implementations
ﾉ
Expand table
container: string
container: string # Specify job container by alias.
Remarks
Examples
Specify job container using image tag and options.
YAML
image  string. Required.
Container image tag.
endpoint  string.
ID of the service endpoint connecting to a private container registry.
env  string dictionary.
Variables to map into the container's environment.
mapDockerSocket  boolean.
Set this flag to false to force the agent not to setup the /var/run/docker.sock volume on
container jobs.
pool:
  vmImage: 'ubuntu-18.04'
container: ubuntu:18.04
steps:
- script: printenv
container: image
container:
  image: string # Required. Container image tag.
  endpoint: string # ID of the service endpoint connecting to a private container 
registry.
  env: # Variables to map into the container's environment.
    string: string # Name/value pairs
  mapDockerSocket: boolean # Set this flag to false to force the agent not to setup 
the /var/run/docker.sock volume on container jobs.
  options: string # Options to pass into container host.
  ports: [ string ] # Ports to expose on the container.
  volumes: [ string ] # Volumes to mount on the container.
  mountReadOnly: # Volumes to mount read-only, the default is all false.
    work: boolean # Mount the work directory as readonly.
    externals: boolean # Mount the externals directory as readonly.
    tools: boolean # Mount the tools directory as readonly.
    tasks: boolean # Mount the tasks directory as readonly.
Properties
options  string.
Options to pass into container host.
ports  string list.
Ports to expose on the container.
volumes  string list.
Volumes to mount on the container.
mountReadOnly  mountReadOnly.
Volumes to mount read-only, the default is all false.
Use options  to configure container startup.
YAML
In the following example, the containers are defined in the resources section. Each container is
then referenced later, by referring to its assigned alias.
YAML
Examples
container:
  image: ubuntu:18.04
  options: --hostname container-test --ip 192.168.0.1
steps:
- script: echo hello
resources:
  containers:
  - container: u14
    image: ubuntu:14.04
  - container: u16
    image: ubuntu:16.04
  - container: u18
    image: ubuntu:18.04
jobs:
- job: RunInContainer
  pool:
    vmImage: 'ubuntu-18.04'
  strategy:
    matrix:
Define container jobs
Define resources
Last updated on 03/02/2026
      ubuntu14:
        containerResource: u14
      ubuntu16:
        containerResource: u16
      ubuntu18:
        containerResource: u18
  container: $[ variables['containerResource'] ]
  steps:
  - script: printenv
See also
jobs.job.strategy definition
ﾃ
Summarize this article for me
Execution strategy for this job.
Definitions that reference this definition: pipeline, jobs.job
Implementation
Description
strategy: matrix, maxParallel
Matrix job strategy.
strategy: parallel
Parallel job strategy.
To access variables from a job in a subsequent job, see Set a multi-job output variable.
Use of a matrix generates copies of a job, each with different input. These copies are useful for
testing against different configurations or platform versions.
YAML
matrix  { string1: { string2: string3 }.
Matrix defining the job strategy; see the following examples.
maxParallel  string.
Maximum number of jobs running in parallel.
Implementations
ﾉ
Expand table
Remarks
strategy: matrix, maxParallel
strategy:
  matrix: # Matrix defining the job strategy; see the following examples.
    { string1: { string2: string3 }
  maxParallel: string # Maximum number of jobs running in parallel.
Properties
YAML
For each occurrence of string1 in the matrix, a copy of the job is generated. The name string1 is
the copy's name and is appended to the name of the job. For each occurrence of string2, a
variable called string2 with the value string3 is available to the job.
The optional maxParallel  keyword specifies the maximum number of simultaneous matrix legs
to run at once.
If maxParallel  is unspecified or set to 0, no limit is applied.
This example uses a matrix  job strategy to build on multiple platforms.
YAML
Remarks
strategy:
  matrix: { string1: { string2: string3 } }
  maxParallel: number
７ Note
Matrix configuration names must contain only basic Latin alphabet letters (A-Z and a-z),
digits (0-9), and underscores ( _ ). They must start with a letter. Also, their length must be
100 characters or fewer.
７ Note
The matrix  syntax doesn't support automatic job scaling but you can implement similar
functionality using the each  keyword. For an example, see expressions.
Examples
Build on multiple platforms
# Build NodeJS Express app using Azure Pipelines
# https://learn.microsoft.com/azure/devops/pipelines/ecosystems/javascript
strategy:
  matrix:
    linux:
      imageName: 'ubuntu-latest'
This pipeline uses script to run in each platform's integral script interpreter: Bash on macOS
and Linux, CMD on Windows. See multi-platform scripts to learn more.
The following example builds on both a self-hosted agent and a Microsoft-hosted agent, by
specifying both a vmImage  and a Pool  variable, like the following example. For the hosted
agent, specify Azure Pipelines  as the pool name, and for self-hosted agents, leave the
vmImage  blank. The blank vmImage  for the self-hosted agent may result in some unusual entries
in the logs but they won't affect the pipeline.
yml
    mac:
      imageName: 'macOS-latest'
    windows:
      imageName: 'windows-latest'
pool:
  vmImage: $(imageName)
steps:
- task: NodeTool@0
  inputs:
    versionSpec: '8.x'
- script: |
    npm install
    npm test
- task: PublishTestResults@2
  inputs:
    testResultsFiles: '**/TEST-RESULTS.xml'
    testRunTitle: 'Test results for JavaScript'
- task: PublishCodeCoverageResults@1
  inputs: 
    codeCoverageTool: Cobertura
    summaryFileLocation: '$(System.DefaultWorkingDirectory)/**/*coverage.xml'
    reportDirectory: '$(System.DefaultWorkingDirectory)/**/coverage'
- task: ArchiveFiles@2
  inputs:
    rootFolderOrFile: '$(System.DefaultWorkingDirectory)'
    includeRootFolder: false
- task: PublishBuildArtifacts@1
Build on multiple platforms using self-hosted and Microsoft-hosted
agents
YAML
This matrix creates three jobs: "Build Python35," "Build Python36," and "Build Python37."
Within each job, a variable named PYTHON_VERSION is available. In "Build Python35," the
variable is set to "3.5". It's likewise set to "3.6" in "Build Python36." Only two jobs run
simultaneously.
The parallel job strategy specifies how many duplicates of a job should run.
YAML
strategy:
  matrix:
    microsofthosted:
      poolName: Azure Pipelines
      vmImage: ubuntu-latest
    selfhosted:
      poolName: FabrikamPool
      vmImage:
pool:
  name: $(poolName)
  vmImage: $(vmImage)
steps:
- checkout: none
- script: echo test
Build using different Python versions
jobs:
- job: Build
  strategy:
    matrix:
      Python35:
        PYTHON_VERSION: '3.5'
      Python36:
        PYTHON_VERSION: '3.6'
      Python37:
        PYTHON_VERSION: '3.7'
    maxParallel: 2
strategy: parallel
strategy:
  parallel: string # Run the job this many times.
parallel  string.
Run the job this many times.
The parallel job strategy is useful for slicing up a large test matrix. The Visual Studio Test task
understands how to divide the test load across the number of scheduled jobs.
YAML
Last updated on 03/02/2026
Properties
Remarks
Examples
jobs:
- job: SliceItFourWays
  strategy:
    parallel: 4
jobs.job.uses definition
ﾃ
Summarize this article for me
Specifies resources required by a job that are not already referenced elsewhere in the pipeline,
for example by a checkout step or a repository resource. For more information about uses , see
Limit job authorization scope and "uses" statement for pre-declaring resources.
YAML
Definitions that reference this definition: jobs.job, jobs.deployment
repositories  string list.
Specifies Azure Repos repositories required by a job that are not already referenced elsewhere
in the pipeline, for example by a checkout step or a repository resource. For more information,
see Limit job authorization scope and "uses" statement for pre-declaring resources.
pools  string list.
Specifies pools required by this job, typically when using a matrix job strategy. For more
information, see "uses" statement for pre-declaring resources.
Last updated on 03/02/2026
uses:
  repositories: [ string ] # Repository references.
  pools: [ string ] # Pool references.
Properties
jobs.template definition
ﾃ
Summarize this article for me
A set of jobs defined in a template.
YAML
Definitions that reference this definition: jobs
template  string. Required as first property.
Reference to a template for this deployment.
parameters  template parameters.
Parameters used in a deployment template.
You can define a set of jobs in one file and use it multiple times in other files. See templates for
more about working with job templates.
In the main pipeline:
YAML
In the included template:
YAML
jobs:
- template: string # Required as first property. Reference to a template for this 
deployment.
  parameters: # Parameters used in a deployment template.
Properties
Remarks
Examples
- template: string # name of template to include
  parameters: { string: any } # provided parameters
parameters: { string: any } # expected parameters
In this example, a single job is repeated on three platforms. The job itself is specified only once.
YAML
YAML
See templates for more about working with job templates.
jobs: [ job ]
# File: jobs/build.yml
parameters:
  name: ''
  pool: ''
  sign: false
jobs:
- job: ${{ parameters.name }}
  pool: ${{ parameters.pool }}
  steps:
  - script: npm install
  - script: npm test
  - ${{ if eq(parameters.sign, 'true') }}:
    - script: sign
# File: azure-pipelines.yml
jobs:
- template: jobs/build.yml  # Template reference
  parameters:
    name: macOS
    pool:
      vmImage: macOS-latest
- template: jobs/build.yml  # Template reference
  parameters:
    name: Linux
    pool:
      vmImage: ubuntu-latest
- template: jobs/build.yml  # Template reference
  parameters:
    name: Windows
    pool:
      vmImage: windows-latest
    sign: true  # Extra step on Windows only
See also
Last updated on 03/02/2026
pipeline.parameters definition
ﾃ
Summarize this article for me
The parameters list specifies the runtime parameters passed to a pipeline.
YAML
Definitions that reference this definition: pipeline
Type
Description
parameters.parameter
Pipeline template parameters.
parameters.parameter
See templates for more about working with templates.
Last updated on 03/02/2026
parameters: [ parameter ] # Specifies the runtime parameters passed to a pipeline.
List types
ﾉ
Expand table
See also
pipeline.parameters.parameter definition
ﾃ
Summarize this article for me
A parameter represents a value passed to a pipeline. Parameters must include a default value.
YAML
Definitions that reference this definition: parameters
name  string. Required as first property.
displayName  string.
Human-readable name for the parameter.
type  string.
See types.
default  parameters.
Default value; if no default, then the parameter MUST be given by the user at runtime.
values  string list.
Allowed list of values (for some data types).
The type  and name  fields are required when defining parameters. Learn more about parameter
data types.
YAML
parameters:
- name: string # Required as first property.
  displayName: string # Human-readable name for the parameter.
  type: string
  default: string | parameters | [ parameters ] # Default value; if no default, 
then the parameter MUST be given by the user at runtime.
  values: [ string ] # Allowed list of values (for some data types).
Properties
Remarks
parameters:
- name: string          # name of the parameter; required
  type: enum            # see the enum data types in the following section
  default: any          # default value; if no default, then the parameter MUST be 
The type  value must be one of the enum  members from the following table.
Data type
Notes
string
string
number
may be restricted to values: , otherwise any number-like string is accepted
boolean
true  or false
object
any YAML structure
step
a single step
stepList
sequence of steps
job
a single job
jobList
sequence of jobs
deployment
a single deployment job
deploymentList
sequence of deployment jobs
stage
a single stage
stageList
sequence of stages
The step, stepList, job, jobList, deployment, deploymentList, stage, and stageList data types all
use standard YAML schema format. This example includes string, number, boolean, object,
step, and stepList.
YAML
given by the user at runtime
  values: [ string ]    # allowed list of values (for some data types)
Types
ﾉ
Expand table
parameters:
  - name: myString
    type: string
    default: a string
  - name: myMultiString
    type: string
    default: default
    values:
YAML
      - default
      - ubuntu
  - name: myNumber
    type: number
    default: 2
    values:
      - 1
      - 2
      - 4
      - 8
      - 16
  - name: myBoolean
    type: boolean
    default: true
  - name: myObject
    type: object
    default:
      foo: FOO
      bar: BAR
      things:
        - one
        - two
        - three
      nested:
        one: apple
        two: pear
        count: 3
  - name: myStep
    type: step
    default:
      script: echo my step
  - name: mySteplist
    type: stepList
    default:
      - script: echo step one
      - script: echo step two
trigger: none
jobs: 
  - job: stepList
    steps: ${{ parameters.mySteplist }}
  - job: myStep
    steps:
      - ${{ parameters.myStep }}
Examples
# File: azure-pipelines.yml
parameters:
Use parameters to extend a template. In this example, the pipeline using the template supplies
the values to fill into the template.
YAML
YAML
Use templates to define parameters and then pass those parameters to a pipeline.
YAML
- name: image
  displayName: Pool Image
  type: string
  default: ubuntu-latest
  values:
    - windows-latest
    - ubuntu-latest
    - macOS-latest
trigger: none
jobs:
  - job: build
    displayName: build
    pool: 
      vmImage: ${{ parameters.image }}
    steps:
      - script: echo The image parameter is ${{ parameters.image }}
# File: simple-param.yml
parameters:
- name: yesNo # name of param; required
  type: boolean # data type of param; required
  default: false
steps:
- script: echo ${{ parameters.yesNo }}
# File: azure-pipelines.yml
trigger:
- main
extends:
    template: simple-param.yml
    parameters:
        yesNo: false 
# File: template.yml 
parameters:
YAML
See templates to learn more about working with templates.
Last updated on 03/02/2026
  - name: environment
    type: string
    default: 'production'
jobs:
- job: Deploy
  displayName: 'Deploy to ${{ parameters.environment }}'
  pool:
    vmImage: 'ubuntu-latest'
  steps:
  - script: echo "Deploying to ${{ parameters.environment }}"
    displayName: 'Deploy Step'
# File: azure-pipelines.yml
trigger:
- main
pool:
  vmImage: 'ubuntu-latest'
jobs:
- template: template.yml
  parameters:
    environment: 'staging'
See also
pool definition
ﾃ
Summarize this article for me
The pool  keyword specifies which pool to use for a job of the pipeline. A pool  specification
also holds information about the job's strategy for running.
Definitions that reference this definition: pipeline, stages.stage, jobs.job, jobs.deployment,
preDeployHook, deployHook, routeTrafficHook, postRouteTrafficHook, onFailureHook,
onSuccessHook
Implementation
Description
pool: string
Specify a private pool by name.
pool: name, demands, vmImage
Full syntax for using demands and Microsoft-hosted pools.
You can specify a pool at the pipeline, stage, or job level.
The pool specified at the lowest level of the hierarchy is used to run the job.
Specify a private pool by name to use for a job of the pipeline.
YAML
pool  string.
Specify a private pool by name.
Use this syntax to specify a private pool by name.
Implementations
ﾉ
Expand table
Remarks
pool: string
pool: string # Specify a private pool by name.
Remarks
To use a private pool with no demands:
YAML
Full syntax for using demands and Microsoft-hosted pools.
YAML
name  string.
Name of a pool.
demands  pool.demands.
Demands (for a private pool).
vmImage  string.
Name of the VM image you want to use; valid only in the Microsoft-hosted pool.
Specify a Microsoft-hosted pool using the vmImage  property.
７ Note
If your pool name has a space in it, enclose the pool name in single quotes, like pool: 'My
pool' .
Examples
pool: MyPool
pool: name, demands, vmImage
pool:
  name: string # Name of a pool.
  demands: string | [ string ] # Demands (for a private pool).
  vmImage: string # Name of the VM image you want to use; valid only in the 
Microsoft-hosted pool.
Properties
Remarks
If your self-hosted agent pool name has a space in it, enclose the pool name in single quotes,
like name: 'My pool' .
To use a Microsoft-hosted pool, omit the name and specify one of the available hosted images:
YAML
You can specify demands for a private pool using the full syntax.
To add a single demand to your YAML build pipeline, add the demands:  line to the pool
section.
YAML
Or if you need to add multiple demands, add one per line.
YAML
Checking for the existence of a capability (exists) and checking for a specific string in a
capability (equals) are the only two supported operations for demands.
The exists operation checks for the presence of a capability with the specific name. The
comparison is not case sensitive.
YAML
Examples
pool:
  vmImage: ubuntu-latest
pool:
  name: Default
  demands: SpecialSoftware # exists check for SpecialSoftware
pool:
  name: MyPool
  demands:
  - myCustomCapability   # exists check for myCustomCapability
  - Agent.Version -equals 2.144.0 # equals check for Agent.Version 2.144.0
Exists operation
pool:
  name: MyPool
The equals operation checks for the existence of a capability, and if present, checks its value
with the specified value. If the capability is not present or the values don't match, the operation
evaluates to false. The comparisons are not case sensitive.
YAML
Self-hosted agents have the following system capabilities with similar names to agent variables,
but they are not variables and don't require variable syntax when checking for exists or equals
in a demand.
Agent.Name
Agent.Version
Agent.ComputerName
Agent.HomeDirectory
Agent.OS
Agent.OSArchitecture
Agent.OSVersion (Windows agents only)
For more information, see Specify demands.
Specify demands
Learn more about conditions and timeouts.
Last updated on 03/02/2026
  demands: myCustomCapability # exists check for myCustomCapability
Equals operation
pool:
  name: MyPool
  demands: Agent.Version -equals 2.144.0 # equals check for Agent.Version 2.144.0
Agent variables as system capabilities
See also
pool.demands definition
Demands (for a private pool).
Definitions that reference this definition: pool
Implementation
Description
demands: string
Specify a demand for a private pool.
demands: string list
Specify a list of demands for a private pool.
Use demands to make sure that the capabilities your pipeline needs are present on the agents
that run it. Demands are asserted automatically by tasks or manually by you.
You can check for the presence of a capability (Exists operation) or you can check for a specific
string in a capability (Equals operation). Checking for the existence of a capability (exists) and
checking for a specific string in a capability (equals) are the only two supported operations for
demands.
Some tasks won't run unless one or more demands are met by the agent. For example, the
Visual Studio Build task demands that msbuild  and visualstudio  are installed on the agent.
Implementations
ﾉ
Expand table
Remarks
７ Note
Demands and capabilities are designed for use with self-hosted agents so that jobs can be
matched with an agent that meets the requirements of the job. When using Microsoft-
hosted agents, you select an image for the agent that matches the requirements of the
job, so although it is possible to add capabilities to a Microsoft-hosted agent, you don't
need to use capabilities with Microsoft-hosted agents.
Task demands
Manually entered agent demands
You might need to use self-hosted agents with special capabilities. For example, your pipeline
may require SpecialSoftware on agents in the Default  pool. Or, if you have multiple agents
with different operating systems in the same pool, you may have a pipeline that requires a
Linux agent.
The exists operation checks for the presence of a capability with the specific name. The
comparison is not case sensitive.
YAML
The equals operation checks for the existence of a capability, and if present, checks its value
with the specified value. If the capability is not present or the values don't match, the operation
evaluates to false. The comparisons are not case sensitive.
YAML
Self-hosted agents have the following system capabilities with similar names to agent variables,
but they are not variables and don't require variable syntax when checking for exists or equals
in a demand.
Agent.Name
Agent.Version
Agent.ComputerName
Agent.HomeDirectory
Agent.OS
Agent.OSArchitecture
Agent.OSVersion (Windows agents only)
Exists operation
pool:
  name: MyPool
  demands: myCustomCapability # exists check for myCustomCapability
Equals operation
pool:
  name: MyPool
  demands: Agent.Version -equals 2.144.0 # equals check for Agent.Version 2.144.0
Agent variables as system capabilities
Specify a demand for a private pool.
YAML
demands  string.
Specify a demand for a private pool.
To add a single demand to your YAML build pipeline, add the demands:  line to the pool
section.
YAML
Specify a list of demands for a private pool.
YAML
Type
Description
string
Specify a list of demands for a private pool.
demands: string
demands: string # Specify a demand for a private pool.
Examples
pool:
  name: Default
  demands: SpecialSoftware # exists check for SpecialSoftware
demands: string list
demands: [ string ] # Specify a list of demands for a private pool.
List types
ﾉ
Expand table
Examples
To specify multiple demands, add one per line.
YAML
Agent capabilities
Last updated on 04/02/2026
pool:
  name: MyPool
  demands:
  - myCustomCapability   # exists check for myCustomCapability
  - Agent.Version -equals 2.144.0 # equals check for Agent.Version 2.144.0
See also
pr definition
ﾃ
Summarize this article for me
A pull request trigger specifies which branches cause a pull request build to run.
Definitions that reference this definition: pipeline
Implementation
Description
pr: none
Disable pull request triggers.
pr: string list
List of branches that trigger a run.
pr: autoCancel, branches, paths, drafts
Full syntax for complete control.
If you specify no pull request trigger, pull requests to any branch trigger a build.
There are three distinct syntax options for the pr  keyword: a list of branches to include, a way
to disable PR triggers, and the full syntax for complete control.
If you specify an exclude  clause without an include  clause for branches  or paths , it is
equivalent to specifying *  in the include  clause.
Disable pull request triggers.
YAML
Implementations
ﾉ
Expand table
Remarks
） Important
YAML PR triggers are supported only in GitHub and Bitbucket Cloud. If you use Azure
Repos Git, you can configure a branch policy for build validation to trigger your build
pipeline for validation.
pr: none
pr  string. Allowed values: none.
Disable pull request triggers.
Disablement syntax:
YAML
List of branches that trigger a run.
YAML
Type
Description
string
List of branches that trigger a run.
The list syntax specifies a list of branches which trigger a run when a pull request is raised or a
push is made to the source branch of a raised pull request.
List syntax:
YAML
pr: none # Disable pull request triggers.
Examples
pr: none # will disable PR builds (but not CI builds)
pr: string list
pr: [ string ] # List of branches that trigger a run.
List types
ﾉ
Expand table
Remarks
Examples
Use the full syntax when you need full control of the pull request trigger.
YAML
autoCancel  boolean.
Whether to cancel running PR builds when a new commit lands in the branch. Default: true.
branches  includeExcludeFilters.
Branch names to include or exclude for triggering a run.
paths  includeExcludeFilters.
File paths to include or exclude for triggering a run.
drafts  boolean.
Whether to start a run when a draft PR is created. Default: true.
Full syntax:
YAML
pr:
- main
- develop
pr: autoCancel, branches, paths, drafts
pr:
  autoCancel: boolean # Whether to cancel running PR builds when a new commit lands 
in the branch. Default: true.
  branches: # Branch names to include or exclude for triggering a run.
    include: [ string ] # List of items to include.
    exclude: [ string ] # List of items to exclude.
  paths: # File paths to include or exclude for triggering a run.
    include: [ string ] # List of items to include.
    exclude: [ string ] # List of items to exclude.
  drafts: boolean # Whether to start a run when a draft PR is created. Default: 
true.
Properties
Examples
pr:
  branches:
Learn more about pull request triggers and how to specify them.
Last updated on 03/02/2026
    include:
    - features/*
    exclude:
    - features/experimental/*
  paths:
    exclude:
    - README.md
See also
resources definition
ﾃ
Summarize this article for me
Resources specifies builds, repositories, pipelines, and other resources used by the pipeline.
YAML
Definitions that reference this definition: pipeline
builds  resources.builds.
List of build resources referenced by the pipeline.
containers  resources.containers.
List of container images.
pipelines  resources.pipelines.
List of pipeline resources.
repositories  resources.repositories.
List of repository resources.
webhooks  resources.webhooks.
List of webhooks.
packages  resources.packages.
List of package resources.
Add resources to a pipeline
Last updated on 03/02/2026
resources:
  builds: [ build ] # List of build resources referenced by the pipeline.
  containers: [ container ] # List of container images.
  pipelines: [ pipeline ] # List of pipeline resources.
  repositories: [ repository ] # List of repository resources.
  webhooks: [ webhook ] # List of webhooks.
  packages: [ package ] # List of package resources.
Properties
See also
resources.builds definition
ﾃ
Summarize this article for me
List of build resources referenced by the pipeline.
YAML
Definitions that reference this definition: resources
Type
Description
resources.builds.build
A build resource used to reference artifacts from a run.
Add resources to a pipeline
Last updated on 03/02/2026
builds: [ build ] # List of build resources referenced by the pipeline.
List types
ﾉ
Expand table
See also
resources.builds.build definition
ﾃ
Summarize this article for me
A build resource used to reference artifacts from a run.
YAML
Definitions that reference this definition: resources.builds
build  string. Required as first property.
Alias or name of build artifact. Acceptable values: [-_A-Za-z0-9]*.
type  string. Required.
Name of the artifact type.
connection  string. Required.
Name of the connection. This connection will be used for all the communication related to this
artifact.
source  string. Required.
Name of the source definition/build/job.
version  string.
branch  string.
trigger  string.
When the artifact mentioned in this build resource completes a build, it is allowed to trigger
this pipeline. none | true.
builds:
- build: string # Required as first property. Alias or name of build artifact.
  type: string # Required. Name of the artifact type.
  connection: string # Required. Name of the connection. This connection will be 
used for all the communication related to this artifact.
  source: string # Required. Name of the source definition/build/job.
  version: string
  branch: string
  trigger: none | true # When the artifact mentioned in this build resource 
completes a build, it is allowed to trigger this pipeline.
Properties
If you have an external CI build system that produces artifacts, you can consume artifacts with a
build resource. A build resource can be any external CI systems like Jenkins, TeamCity, CircleCI,
and so on.
YAML
Define resources in YAML
Last updated on 03/02/2026
Remarks
） Important
Triggers are only supported for hosted Jenkins where Azure DevOps has line of sight with
Jenkins server.
Examples
resources:
  builds:
  - build: Spaceworkz
    type: Jenkins
    connection: MyJenkinsServer 
    source: SpaceworkzProj   # name of the jenkins source project
    trigger: true
See also
resources.containers definition
ﾃ
Summarize this article for me
List of container resources referenced by the pipeline.
YAML
Definitions that reference this definition: resources
Type
Description
resources.containers.container
A container resource used to reference a container image.
Add resources to a pipeline
Last updated on 03/02/2026
containers: [ container ] # List of container images.
List types
ﾉ
Expand table
See also
resources.containers.container definition
ﾃ
Summarize this article for me
A container resource references a container image.
YAML
Definitions that reference this definition: resources.containers
container  string. Required as first property.
ID for the container. Acceptable values: [-_A-Za-z0-9]*.
image  string. Required.
Container image tag.
type  string.
Type of the registry like ACR or GCR.
containers:
- container: string # Required as first property. Alias of the container.
  image: string # Required. Container image tag.
  type: string # Type of the registry like ACR or GCR.
  trigger: trigger | none | true # Specify none to disable, true to trigger on all 
image tags, or use the full syntax as described in the following examples.
  azureSubscription: string # Azure subscription (ARM service connection) for 
container registry.
  resourceGroup: string # Resource group for your ACR.
  registry: string # Registry for container images.
  repository: string # Name of the container image repository in ACR.
  localImage: boolean # When true, uses a locally tagged image instead of using 
docker pull to get the image; the default is false.
  endpoint: string # ID of the service endpoint connecting to a private container 
registry.
  env: # Variables to map into the container's environment.
    string: string # Name/value pairs
  mapDockerSocket: boolean # Set this flag to false to force the agent not to setup 
the /var/run/docker.sock volume on container jobs.
  options: string # Options to pass into container host.
  ports: [ string ] # Ports to expose on the container.
  volumes: [ string ] # Volumes to mount on the container.
  mountReadOnly: # Volumes to mount read-only, the default is all false.
    work: boolean # Mount the work directory as readonly.
    externals: boolean # Mount the externals directory as readonly.
    tools: boolean # Mount the tools directory as readonly.
    tasks: boolean # Mount the tasks directory as readonly.
Properties
trigger  resources.containers.container.trigger.
Specify none to disable, true to trigger on all image tags, or use the full syntax as described in
the following examples.
azureSubscription  string.
Azure subscription (ARM service connection) for container registry.
resourceGroup  string.
Resource group for your ACR.
registry  string.
Registry for container images.
repository  string.
Name of the container image repository in ACR.
localImage  boolean.
When true, uses a locally tagged image instead of using docker pull to get the image. The
default is false.
This property is useful only for self-hosted agents where the image is already present on the
agent machine.
endpoint  string.
ID of the service endpoint connecting to a private container registry. Template expressions are
supported.
env  string dictionary.
Variables to map into the container's environment.
mapDockerSocket  boolean.
Set this flag to false to force the agent not to setup the /var/run/docker.sock volume on
container jobs.
options  string.
Options to pass into container host. Template expressions are supported.
ports  string list.
Ports to expose on the container. Template expressions are supported.
volumes  string list.
Volumes to mount on the container. Template expressions are supported.
mountReadOnly  mountReadOnly.
Volumes to mount read-only, the default is all false.
Container jobs let you isolate your tools and dependencies inside a container.
The agent launches an instance of your specified container then runs steps inside it. The
container  keyword lets you specify your container images.
Service containers run alongside a job to provide various dependencies like databases.
Template expressions are supported for endpoint , volumes , ports , and options  properties of a
container resource in a YAML pipeline.
YAML
Define resources in YAML
Last updated on 03/02/2026
Remarks
Examples
resources:
  containers:
  - container: linux
    image: ubuntu:16.04
  - container: windows
    image: myprivate.azurecr.io/windowsservercore:1803
    endpoint: my_acr_connection
  - container: my_service
    image: my_service:tag
    ports:
    - 8080:80 # bind container port 80 to 8080 on the host machine
    - 6379 # bind container port 6379 to a random available port on the host 
machine
    volumes:
    - /src/dir:/dst/dir # mount /src/dir on the host into /dst/dir in the container
See also
resources.containers.container.trigger
definition
ﾃ
Summarize this article for me
Specifies the trigger conditions for a container resource.
Definitions that reference this definition: resources.containers.container
Implementation
Description
trigger: enabled, tags
Specify a list of tags to trigger on.
trigger: none | true
Specify none to disable or true to trigger on all image tags.
Specify none to disable, true to trigger on all image tags, or use the full syntax as described in
the following examples.
Configure which tags trigger a run.
YAML
enabled  boolean.
Whether the trigger is enabled; defaults to true.
Implementations
ﾉ
Expand table
Remarks
trigger: enabled, tags
trigger:
  enabled: boolean # Whether the trigger is enabled; defaults to true.
  tags: includeExcludeStringFilters | [ string ] # Tag names to include or exclude 
for triggering a run.
Properties
tags  includeExcludeStringFilters.
Tag names to include or exclude for triggering a run.
In the following example, the trigger is enabled for tags matching production* . When
specifying a tag, you may use an exact name or a wildcard. For more information, see
wildcards.
YAML
Specify none to disable or true to trigger on all image tags.
YAML
trigger  string. Allowed values: none | true.
Specify none to disable or true to trigger on all image tags.
Specify none  to disable the trigger, or true  to enable. If not specified, the default is none . To
configure triggers based on specific tags, see the following section.
Examples
resources:         
  containers:
  - container: petStore      
    type: ACR  
    azureSubscription: ContosoARMConnection
    resourceGroup: ContosoGroup
    registry: petStoreRegistry
    repository: myPets
    trigger: 
      tags:
        include: 
        - production*
trigger: none | true
trigger: none | true # Specify none to disable or true to trigger on all image 
tags.
Remarks
Examples
YAML
YAML
Define a container resource
Last updated on 03/02/2026
resources:         
  containers:
  - container: petStore      
    type: ACR  
    azureSubscription: ContosoARMConnection
    resourceGroup: ContosoGroup
    registry: petStoreRegistry
    repository: myPets
    trigger: 
      tags: none # Triggers disabled
resources:         
  containers:
  - container: petStore      
    type: ACR  
    azureSubscription: ContosoARMConnection
    resourceGroup: ContosoGroup
    registry: petStoreRegistry
    repository: myPets
    trigger: 
      tags: true # Triggers enabled for all tags
See also
resources.packages definition
ﾃ
Summarize this article for me
List of package resources referenced by the pipeline.
YAML
Definitions that reference this definition: resources
Type
Description
resources.packages.package
A package resource used to reference a NuGet or npm GitHub package.
Add resources to a pipeline
Last updated on 03/02/2026
packages: [ package ] # List of package resources.
List types
ﾉ
Expand table
See also
resources.packages.package definition
ﾃ
Summarize this article for me
You can consume NuGet and npm GitHub packages as a resource in YAML pipelines. When
specifying package resources, set the package as NuGet  or npm .
YAML
Definitions that reference this definition: resources.packages
package  string. Required as first property.
Alias of package artifact. Acceptable values: [-_A-Za-z0-9]*.
type  string. Required.
Type of the package. Ex - NuGet, NPM etc.
connection  string. Required.
Name of the connection. This connection will be used for all the communication related to this
artifact.
name  string. Required.
Name of the package.
version  string.
tag  string.
trigger  string.
Trigger a new pipeline run when a new version of this package is available. none | true.
packages:
- package: string # Required as first property. Alias of package artifact.
  type: string # Required. Type of the package. Ex - NuGet, NPM etc.
  connection: string # Required. Name of the connection. This connection will be 
used for all the communication related to this artifact.
  name: string # Required. Name of the package.
  version: string
  tag: string
  trigger: none | true # Trigger a new pipeline run when a new version of this 
package is available.
Properties
In this example, there is an GitHub service connection named pat-contoso  to a GitHub npm
package named contoso . Learn more about GitHub packages
.
YAML
Add resources to a pipeline
Last updated on 03/02/2026
Examples
resources:
  packages:
    - package: contoso
      type: npm
      connection: pat-contoso
      name: yourname/contoso 
      version: 7.130.88 
      trigger: true
pool:
  vmImage: ubuntu-latest
steps:
- getPackage: contoso 
See also
resources.pipelines definition
ﾃ
Summarize this article for me
List of pipeline resources referenced by the pipeline.
YAML
Definitions that reference this definition: resources
Type
Description
resources.pipelines.pipeline
A pipeline resource.
The resources.pipelines  definition represents a list of pipeline resources. For information and
examples of the syntax for an individual pipeline resource in the resources.pipelines  list, see
resources.pipelines.pipeline.
yml
pipelines: [ pipeline ] # List of pipeline resources.
List types
ﾉ
Expand table
Remarks
Examples
resources:
 pipelines:
   - pipeline: source-pipeline
     source: PipelineTriggerSource
     project: FabrikamFiber
     trigger: true
   - pipeline: other-project-pipeline
     source: PipelineTriggerFromOtherProject
     project: FabrikamRepo
     trigger: true
trigger: none # Only trigger with pipeline resource trigger
pool:
Add resources to a pipeline
Last updated on 03/02/2026
  vmImage: ubuntu-latest
- bash: echo $(resources.pipeline.source-pipeline.projectName)
- bash: printenv | sort
See also
resources.pipelines.pipeline definition
ﾃ
Summarize this article for me
If you have an Azure Pipeline that produces artifacts, your pipeline can consume the artifacts
by defining a pipeline resource. In Azure DevOps Server 2020 and higher, you can also enable
pipeline completion triggers using a pipeline resource.
YAML
Definitions that reference this definition: resources.pipelines
pipeline  string. Required as first property.
ID of the pipeline resource. Acceptable values: [-_A-Za-z0-9]*.
project  string.
Project for the source; defaults to current project.
source  string.
Name of the pipeline that produces the artifact. If the pipeline is contained in a folder, include
the folder name, including the leading \ , for example \security pipelines\security-lib-ci .
This property is not case sensitive and does not need quotes if the name include spaces. The
folder path must be specified if there are multiple pipelines with the same name.
pipelines:
- pipeline: string # Required as first property. ID of the pipeline resource.
  project: string # Project for the source; defaults to current project.
  source: string # Name of the pipeline that produces the artifact.
  version: string # The pipeline run number to pick the artifact, defaults to 
latest pipeline successful across all stages; used only for manual or scheduled 
triggers.
  branch: string # Branch to pick the artifact. Optional; defaults to all branches, 
used only for manual or scheduled triggers.
  tags: [ string ] # List of tags required on the pipeline to pickup default 
artifacts. Optional; used only for manual or scheduled triggers.
  trigger:  # Specify none to disable, true to include all branches, or use the 
full syntax as described in the following examples.
    enabled: boolean # Whether the trigger is enabled; defaults to true.
    branches: branches # Branches to include or exclude for triggering a run.
    stages: [ string ] # List of stages that when matched will trigger the 
pipeline.
    tags: [ string ] # List of tags that when matched will trigger the pipeline.
Properties
version  string.
The pipeline run number to pick the artifact, defaults to latest pipeline successful across all
stages; used only for manual or scheduled triggers.
branch  string.
Branch to pick the artifact. Optional; defaults to all branches, used only for manual or
scheduled triggers.
tags  string list.
List of tags required on the pipeline to pickup default artifacts. Optional; used only for manual
or scheduled triggers.
trigger  resources.pipelines.pipeline.trigger.
Specify none to disable, true to include all branches, or use the full syntax as described in the
following examples.
For more information about stages  and tags  in the pipeline resource trigger, see pipeline-
completion triggers.
There are several ways to define triggers in a pipeline resource. To trigger a run when any run
of the referenced pipeline completes, use trigger: true .
Remarks
７ Note
pipeline:  specifies the name of the pipeline resource. Use the label defined here when
referring to the pipeline resource from other parts of the pipeline, such as when using
pipeline resource variables or downloading artifacts.
Pipeline resource trigger syntax
７ Note
Pipeline completion triggers use the Default branch for manual and scheduled builds
setting to determine which branch's version of a YAML pipeline's branch filters to evaluate
when determining whether to run a pipeline as the result of another pipeline completing.
By default this setting points to the default branch of the repository. For more
information, see Pipeline completion triggers - branch considerations.
YAML
To disable the pipeline resource trigger, specify a value of none .
YAML
To configure branch filters, use the full syntax. Branch filters can be specified as a list of
branches to include, or as a list of branches to include combined with a list of branches to
exclude.
To specify a list of branches to include and exclude, use the following trigger  syntax.
YAML
To specify a list of branches to include, with no excludes, omit the exclude  value, or use the
following syntax to specify the list of branches to include directly following branches .
YAML
resources:
  pipelines:
  - pipeline: source-pipeline
    source: TriggeringPipeline
    trigger: true
resources:
  pipelines:
  - pipeline: source-pipeline
    source: TriggeringPipeline
    trigger: none
resources:
  pipelines:
  - pipeline: source-pipeline
    source: TriggeringPipeline
    trigger:
      branches:
        include:
        - main
        - develop
        - features/*
        exclude:
        - features/experimental/*
resources:
  pipelines:
  - pipeline: source-pipeline
    source: TriggeringPipeline
To filter by stages or tags, use the following trigger  syntax.
YAML
For more information, see Pipeline completion triggers.
In each run, the metadata for a pipeline resource is available to all jobs as the following
predefined variables. These variables are available to your pipeline at runtime, and therefore
can't be used in template expressions, which are evaluated at pipeline compile time.
YAML
    trigger:
      branches:
      - main
      - develop
resources:
  pipelines:
  - pipeline: source-pipeline
    source: TriggeringPipeline
    trigger:
      branches: # Branches to include
      tags: # List of tags that when matched will trigger the pipeline. 
      - release25
      stages: # List of stages that when complete will trigger the pipeline. 
      - build
） Important
When you define a resource trigger, if its pipeline resource is from the same repo as the
current pipeline, triggering follows the same branch and commit on which the event is
raised. But if the pipeline resource is from a different repo, the current pipeline is triggered
on the branch specified by the Default branch for manual and scheduled builds setting.
For more information, see Branch considerations for pipeline completion triggers.
Pipeline resource metadata as predefined variables
resources.pipeline.<Alias>.projectName
resources.pipeline.<Alias>.projectID
resources.pipeline.<Alias>.pipelineName
resources.pipeline.<Alias>.pipelineID
resources.pipeline.<Alias>.runName
resources.pipeline.<Alias>.runID
resources.pipeline.<Alias>.runURI
resources.pipeline.<Alias>.sourceBranch
Replace <Alias>  with the ID of the pipeline resource. For the following pipeline resource, the
variable to access runID  is resources.pipeline.source-pipeline.runID .
YAML
When a pipeline is triggered by one of its pipeline resources, the following variables are set in
addition to the variables in the previous list.
Variable
Value
Build.Reason
ResourceTrigger
Resources.TriggeringAlias
The name of the pipeline resource, such as source-pipeline  from the
previous example.
Resources.TriggeringCategory
pipeline
The following example has two pipeline resources.
yml
resources.pipeline.<Alias>.sourceCommit
resources.pipeline.<Alias>.sourceProvider
resources.pipeline.<Alias>.requestedFor
resources.pipeline.<Alias>.requestedForID
） Important
projectName  is not present in the variables if the pipeline resource does not have a
project  value specified. The project  property is optional for pipeline resources that
reference a pipeline in the same project, but may be specified if desired.
resources:
  pipelines:
  - pipeline: source-pipeline
    source: TriggeringPipeline
ﾉ
Expand table
resources:
 pipelines:
   - pipeline: source-pipeline
     source: PipelineTriggerSource
     project: FabrikamFiber
     trigger: true
   - pipeline: other-project-pipeline
When this pipeline is run, the first bash  task outputs the projectName  of the the pipeline
resource named source-pipeline , which is FabrikamFiber .
The second bash  task outputs all of the environment variables available to the task, including
the pipeline resource variables described in this section. Listing environment variables isn't
typically done in a production pipeline, but it can be useful for troubleshooting. In this example
there are two pipeline resources, and the output contains the following two lines.
You can consume artifacts from a pipeline resource by using a download  task. See the
steps.download keyword.
YAML
     source: PipelineTriggerFromOtherProject
     project: FabrikamRepo
     trigger: true
trigger: none # Only trigger with pipeline resource trigger
pool:
  vmImage: ubuntu-latest
- bash: echo $(resources.pipeline.source-pipeline.projectName)
- bash: printenv | sort
RESOURCES_PIPELINE_OTHER-PROJECT-PIPELINE_PROJECTNAME=FabrikamRepo
RESOURCES_PIPELINE_SOURCE-PIPELINE_PROJECTNAME=FabrikamFiber
７ Note
System and user-defined variables get injected as environment variables for your platform.
When variables convert into environment variables, variable names become uppercase,
and periods turn into underscores. For example, the variable name any.variable  becomes
ANY_VARIABLE .
For more information about using variables and variable syntax, see Understand variable
syntax, Specify conditions, and Expressions.
Examples
resources:
  pipelines:
Add resources to a pipeline
Last updated on 03/02/2026
  - pipeline: MyAppA
    source: MyCIPipelineA
  - pipeline: MyAppB
    source: MyCIPipelineB
    trigger: true
  - pipeline: MyAppC
    project:  DevOpsProject
    source: MyCIPipelineC
    branch: releases/M159
    version: 20190718.2
    trigger:
      branches:
        include:
        - main
        - releases/*
        exclude:
        - users/*
See also
resources.pipelines.pipeline.trigger
definition
ﾃ
Summarize this article for me
Specify none to disable, true to include all branches, or use the full syntax as described in the
following examples.
Definitions that reference this definition: resources.pipelines.pipeline
Implementation
Description
trigger: enabled, branches, stages, tags
Configure pipeline resource triggers using the full syntax.
trigger: none | true
Specify none to disable or true to include all branches.
There are several ways to define triggers in a pipeline resource. To trigger a run when any run
of the referenced pipeline completes, use trigger: true .
YAML
To disable the pipeline resource trigger, specify a value of none .
YAML
Implementations
ﾉ
Expand table
Remarks
resources:
  pipelines:
  - pipeline: source-pipeline
    source: TriggeringPipeline
    trigger: true
resources:
  pipelines:
  - pipeline: source-pipeline
    source: TriggeringPipeline
    trigger: none
To configure branch filters, use the full syntax. Branch filters can be specified as a list of
branches to include, or as a list of branches to include combined with a list of branches to
exclude.
To specify a list of branches to include and exclude, use the following trigger  syntax.
YAML
To specify a list of branches to include, with no excludes, omit the exclude  value, or use the
following syntax to specify the list of branches to include directly following branches .
YAML
To filter by stages or tags, use the following trigger  syntax.
YAML
resources:
  pipelines:
  - pipeline: source-pipeline
    source: TriggeringPipeline
    trigger:
      branches:
        include:
        - main
        - develop
        - features/*
        exclude:
        - features/experimental/*
resources:
  pipelines:
  - pipeline: source-pipeline
    source: TriggeringPipeline
    trigger:
      branches:
      - main
      - develop
resources:
  pipelines:
  - pipeline: source-pipeline
    source: TriggeringPipeline
    trigger:
      branches: # Branches to include
      tags: # List of tags that when matched will trigger the pipeline. 
      - release25
      stages: # List of stages that when complete will trigger the pipeline. 
      - build
For more information, see Pipeline completion triggers.
Configure pipeline resource triggers using the full syntax.
YAML
enabled  boolean.
Whether the trigger is enabled; defaults to true.
branches  resources.pipelines.pipeline.trigger.branches.
Branch names to include or exclude for triggering a run.
stages  string list.
List of stages that when matched will trigger the pipeline.
tags  string list.
List of tags that when matched will trigger the pipeline.
Specify none to disable or true to include all branches.
YAML
） Important
When you define a resource trigger, if its pipeline resource is from the same repo as the
current pipeline, triggering follows the same branch and commit on which the event is
raised. But if the pipeline resource is from a different repo, the current pipeline is triggered
on the branch specified by the Default branch for manual and scheduled builds setting.
For more information, see Branch considerations for pipeline completion triggers.
trigger: enabled, branches, stages, tags
trigger:
  enabled: boolean # Whether the trigger is enabled; defaults to true.
  branches: branches # Branches to include or exclude for triggering a run.
  stages: [ string ] # List of stages that when matched will trigger the pipeline.
  tags: [ string ] # List of tags that when matched will trigger the pipeline.
Properties
trigger: none | true
trigger  string. Allowed values: none | true.
Specify none to disable or true to include all branches.
Last updated on 03/02/2026
trigger: none | true # Specify none to disable or true to include all branches.
resources.pipelines.pipeline.trigger.branche
s definition
ﾃ
Summarize this article for me
Branches to include or exclude for triggering a run using a pipeline resource.
Definitions that reference this definition: resources.pipelines.pipeline.trigger
Implementation
Description
branches: include, exclude
Lists of branches to include and exclude.
branches: string list
List of branches to include.
Lists of branches to include and exclude.
YAML
include  string list.
List of branches to include.
exclude  string list.
List of branches to exclude.
List of branches to include.
Implementations
ﾉ
Expand table
branches: include, exclude
branches:
  include: [ string ] # List of branches to include.
  exclude: [ string ] # List of branches to exclude.
Properties
branches: string list
YAML
Type
Description
string
List of branches to include.
Last updated on 03/02/2026
branches: [ string ] # List of branches to include.
List types
ﾉ
Expand table
resources.repositories definition
ﾃ
Summarize this article for me
List of repository resources referenced by the pipeline.
YAML
Definitions that reference this definition: resources
Type
Description
resources.repositories.repository
A repository resource is used to reference an additional repository in
your pipeline.
Add resources to a pipeline
Last updated on 03/02/2026
repositories: [ repository ] # List of repository resources.
List types
ﾉ
Expand table
See also
resources.repositories.repository definition
ﾃ
Summarize this article for me
The repository  keyword lets you specify an external repository. Use a repository resource to
reference an additional repository in your pipeline.
YAML
Definitions that reference this definition: resources.repositories
repository  string. Required as first property.
Alias for the specified repository. Acceptable values: [-_A-Za-z0-9]*.
endpoint  string.
ID of the service endpoint connecting to this repository.
trigger  trigger.
CI trigger for this repository, no CI trigger if omitted.
name  string.
Repository name. Format depends on 'type'; does not accept variables.
repositories:
- repository: string # Required as first property. Alias for the repository.
  endpoint: string # ID of the service endpoint connecting to this repository.
  trigger: none | trigger | [ string ] # CI trigger for this repository, no CI 
trigger if skipped (only works for Azure Repos).
  name: string # repository name (format depends on 'type'; does not accept 
variables).
  ref: string # ref name to checkout; defaults to 'refs/heads/main'. The branch 
checked out by default whenever the resource trigger fires.
  type: git | github | githubenterprise | bitbucket # Type of repository: git, 
github, githubenterprise, and bitbucket.
Properties
） Important
Repository resource triggers are supported only for Azure Repos Git repositories.
Repository resource triggers don't support batch .
Ensure that your trigger items like paths are indented correctly; incorrect indentation
can cause your trigger to not work as expected.
ref  string.
ref name to checkout; defaults to 'refs/heads/main'. The branch checked out by default
whenever the resource trigger fires. Template expressions are supported.
type  string.
Type of repository: git, github, githubenterprise, and bitbucket.
Template expressions are supported for the ref  property (but not the name  property).
Wildcards are supported in triggers.
If your pipeline has templates in another repository, or if you want to use multi-repo checkout
with a repository that requires a service connection, you must let the system know about that
repository.
Pipelines support the following values for the repository type: git , github , and bitbucket . The
git  type refers to Azure Repos Git repos.
If you specify type: git , the name  value refers to the name of an Azure Repos Git
repository.
If your pipeline is in the same Azure DevOps project as the repository, for example a
repository named tools , you reference it using name: tools .
If your pipeline is in the same Azure DevOps organization as the repository, but in a
different Azure DevOps project, for example a project named ToolsProject , you must
qualify the repository name with the project name: name: ToolsProject/tools .
Remarks
） Important
Repository resource triggers are supported for Azure Repos Git repositories only. For more
information on trigger  syntax, including wildcard support for branches and tags, see
trigger definition and Build Azure Repos Git or TFS Git repositories.
） Important
batch  is not supported in repository resource triggers.
Types
If you specify type: github , the name  value is the full name of the GitHub repo and
includes the user or organization. An example is name: Microsoft/vscode . GitHub repos
require a GitHub service connection for authorization.
If you specify type: bitbucket , the name  value is the full name of the Bitbucket Cloud
repo and includes the user or organization. An example is name: MyBitbucket/vscode .
Bitbucket Cloud repos require a Bitbucket Cloud service connection for authorization.
For more information about these types, see Check out multiple repositories in your pipeline -
Repository resource definition.
In each run, the metadata for a repository resource is available to all jobs in the form of
runtime variables. The <Alias>  is the identifier that you gave for your repository resource.
YAML
The following example has a repository resource with an alias of common , and the repository
resource variables are accessed using resources.repositories.common.* .
YAML
Variables
resources.repositories.<Alias>.name
resources.repositories.<Alias>.ref
resources.repositories.<Alias>.type
resources.repositories.<Alias>.id
resources.repositories.<Alias>.url
resources.repositories.<Alias>.version
resources:
  repositories:
    - repository: common
      type: git
      ref: main
      name: Repo
variables:
  ref: $[ resources.repositories.common.ref ]
  name: $[ resources.repositories.common.name ]
  id: $[ resources.repositories.common.id ]
  type: $[ resources.repositories.common.type ]
  url: $[ resources.repositories.common.url ]
  version: $[ resources.repositories.common.version ]
steps:
- bash: |
YAML
Add resources to a pipeline
Last updated on 03/04/2026
    echo "name = $(name)"
    echo "ref = $(ref)"
    echo "id = $(id)"
    echo "type = $(type)"
    echo "url = $(url)"
    echo "version = $(version)"
Examples
resources:
  repositories:
  - repository: common
    type: github
    name: Contoso/CommonTools
    endpoint: MyContosoServiceConnection
See also
resources.webhooks definition
ﾃ
Summarize this article for me
List of webhook resources referenced by the pipeline.
YAML
Definitions that reference this definition: resources
Type
Description
resources.webhooks.webhook
A webhook resource enables you to integrate your pipeline with an
external service to automate the workflow.
Add resources to a pipeline
Last updated on 03/02/2026
webhooks: [ webhook ] # List of webhooks.
List types
ﾉ
Expand table
See also
resources.webhooks.webhook definition
ﾃ
Summarize this article for me
A webhook resource enables you to integrate your pipeline with an external service to
automate the workflow.
YAML
Definitions that reference this definition: resources.webhooks
webhook  string. Required as first property.
Name of the webhook. Acceptable values: [-_A-Za-z0-9]*. For Azure DevOps webhook, webhook
must always be a WebHook .
connection  string. Required.
Name of the connection. In case of offline webhook this will be the type of Incoming Webhook
otherwise it will be the type of the webhook extension.
type  string.
Name of the webhook extension. Leave this empty if it is an offline webhook.
filters  resources.webhooks.webhook.filters.
List of trigger filters.
You can define your pipeline as follows.
YAML
webhooks:
- webhook: string # Required as first property. Name of the webhook.
  connection: string # Required. Name of the connection. In case of offline webhook 
this will be the type of Incoming Webhook otherwise it will be the type of the 
webhook extension.
  type: string # Name of the webhook extension. Leave this empty if it is an 
offline webhook.
  filters: [ filter ] # List of trigger filters.
Properties
Examples
Basic example
To trigger your pipeline using the webhook, you need to make a POST  request to
https://dev.azure.com/<org_name>/_apis/public/distributedtask/webhooks/<WebHook Name>?
api-version=6.0-preview . The WebHook Name must match that of the Incoming WebHook
Service Connection. This endpoint is publicly available, and no authorization is needed. The
request should have the following body.
JSON
When you access data from the webhook's request body, be mindful that it may lead to
incorrect YAML. For example, if in the previous pipeline, your step reads - script: echo ${{
parameters.WebHook.resource.message }} , and you trigger the pipeline via a webhook, the
pipeline doesn't run. This is because in the process of replacing ${{
parameters.WebHook.resource.message.title }}  with message , which contains the following
JSON, the generated YAML becomes invalid.
JSON
Because the generated YAML becomes invalid, no pipeline run is queued in response.
Webhooks allow anyone to trigger your pipeline, as long as they know the names of your
organization and webhook service connection.
resources:
  webhooks:
    - webhook: WebHook
      connection: IncomingWH
steps:  
- script: echo ${{ parameters.WebHook.resource.message.title }}
{
    "resource": {
        "message": {
            "title": "Hello, world!",
            "subtitle": "I'm using WebHooks!"
        }
    }
}
{
  "title": "Hello, world!",
  "subtitle": "I'm using WebHooks!"
}
Prevent unauthorized pipeline runs
You can prevent unauthorized pipeline runs by defining a secret when creating an Incoming
Webhook service connection. You need to also specify the name of HTTP header that contains
the SHA-1 checksum of the webhook's body.
To verify that an incoming webhook REST API call is authorized, Azure Pipelines computes the
SHA-1 checksum of the request's body using the secret as key. It then compares it to the
checksum passed in the request header. This way, the caller proves they know the secret.
Let's look at an example. Say you configured an Incoming Webhook service connection named
IncomingWH , specified the secret is secret , and that the checksum is sent in the HTTP header
named X-WH-Checksum . Imagine you have a pipeline that defines a Webhook resource.
Say you want to trigger the pipeline using the following request body:
JSON
To do this, you need to make a POST  request to
https://dev.azure.com/<org_name>/_apis/public/distributedtask/webhooks/IncomingWH?api-
version=6.0-preview  and add the X-WH-Checksum  header with the value of
750D33212D3AD4932CC390819050734831A0A94F . You do not need to specify any username &
password or any other type of authentication information.
Azure Pipelines will independently compute the SHA-1 checksum of the body using secret  as
key and will generate the same 750D33212D3AD4932CC390819050734831A0A94F  value. Since the
values match, the call is authorized, and pipeline queueing proceeds.
You compute the value of the X-WH-Checksum  header, in pseudocode, as
SHA1(secret).ComputeHash(requestBody) . You can use .NET's
System.Security.Cryptography.HMACSHA1  class for this purpose.
To prevent validation failures due to new lines or whitespaces, we recommend you send the
body in a minimized form. That is, send
JSON
instead of
JSON
{"resource":{"message":{"title":"Hello, world!","subtitle":"I'm using WebHooks!"}}}
{"resource":{"message":{"title":"Hello, world!","subtitle":"I'm using WebHooks!"}}}
Even though the two JSON objects above represent the same object, they generate different
SHA-1 checksums. This is because SHA-1 is computed on their string representation, which is
different.
Add resources to a pipeline
Last updated on 03/02/2026
{
    "resource": {
        "message": {
            "title": "Hello, world!",
            "subtitle": "I'm using WebHooks!"
        }
    }
}
See also
resources.webhooks.webhook.filters
definition
ﾃ
Summarize this article for me
List of trigger filters.
YAML
Definitions that reference this definition: resources.webhooks.webhook
Type
Description
resources.webhooks.webhook.filters.filter
Webhook resource trigger filter.
Last updated on 03/02/2026
filters: [ filter ] # List of trigger filters.
List types
ﾉ
Expand table
resources.webhooks.webhook.filters.filter
definition
ﾃ
Summarize this article for me
Filters used to customize the triggers for a webhook event.
YAML
Definitions that reference this definition: resources.webhooks.webhook.filters
path  string. Required as first property.
json path to select data from event payload.
value  string. Required.
Expected value for the filter to match.
For subscribing to a webhook event, you need to define a webhook resource in your pipeline
and point it to the Incoming webhook service connection. You can also define additional filters
on the webhook resource based on the JSON payload data to further customize the triggers
for each pipeline, and you can consume the payload data in the form of variables in your jobs.
YAML
filters:
- path: string # Required as first property. json path to select data from event 
payload.
  value: string # Required. Expected value for the filter to match.
Properties
Examples
resources:
  webhooks:
    - webhook: MyWebhookTrigger          ### Webhook alias
      connection: MyWebhookConnection    ### Incoming webhook service connection
      filters:
        - path: repositoryName      ### JSON path in the payload
          value: maven-releases     ### Expected value in the path provided
        - path: action
          value: CREATED
steps:
- task: PowerShell@2
Add resources to a pipeline
Last updated on 03/02/2026
  inputs:
    targetType: 'inline'
    ### JSON payload data is available in the form of ${{ parameters.
<WebhookAlias>.<JSONPath>}}
    script: |
      Write-Host ${{ parameters.MyWebhookTrigger.repositoryName}}
      Write-Host ${{ parameters.MyWebhookTrigger.component.group}}
See also
schedules definition
ﾃ
Summarize this article for me
The schedules list specifies the scheduled triggers for the pipeline.
YAML
Definitions that reference this definition: pipeline
Type
Description
schedules.cron
A scheduled trigger specifies a schedule on which branches are built.
If you specify no scheduled trigger, no scheduled builds occur.
schedules: [ cron ] # The schedules list specifies the scheduled triggers for the 
pipeline.
List types
ﾉ
Expand table
Remarks
） Important
Scheduled triggers defined using the pipeline settings UI take precedence over YAML
scheduled triggers.
If your YAML pipeline has both YAML scheduled triggers and UI defined scheduled
triggers, only the UI defined scheduled triggers are run. To run the YAML defined
scheduled triggers in your YAML pipeline, you must remove the scheduled triggers
defined in the pipeline settings UI. Once all UI scheduled triggers are removed, a push
must be made in order for the YAML scheduled triggers to start being evaluated.
To delete UI scheduled triggers from a YAML pipeline, see UI settings override YAML
scheduled triggers.
See also
schedules.cron
Learn more about scheduled triggers.
Learn more about triggers in general and how to specify them.
Last updated on 03/02/2026
schedules.cron definition
ﾃ
Summarize this article for me
A scheduled trigger specifies a schedule on which branches are built.
YAML
Definitions that reference this definition: schedules
cron  string. Required as first property.
Cron syntax defining a schedule in UTC time.
displayName  string.
Optional friendly name given to a specific schedule.
branches  includeExcludeFilters.
Branch names to include or exclude for triggering a run.
batch  boolean.
The batch  property configures whether to run the pipeline if the previously scheduled run is
in-progress. When batch  is true , a new pipeline run won't start due to the schedule if a
previous pipeline run is still in-progress. The default is false .
The batch  property is affected by the setting of the always  property. When always  is true , the
pipeline runs according to the cron schedule, even when batch  is true  and there is an in-
progress run.
schedules:
- cron: string # Required as first property. Cron syntax defining a schedule in UTC 
time.
  displayName: string # Optional friendly name given to a specific schedule.
  branches: # Branch names to include or exclude for triggering a run.
    include: [ string ] # List of items to include.
    exclude: [ string ] # List of items to exclude.
  batch: boolean # Whether to run the pipeline if the previously scheduled run is 
in-progress; the default is false.
  always: boolean # Whether to always run the pipeline or only if there have been 
source code or pipeline settings changes since the last successful scheduled run. 
The default is false.
Properties
ﾉ
Expand table
Always
Batch
Behavior
false
false
Pipeline runs only if there's a change with respect to the last successful scheduled
pipeline run.
false
true
Pipeline runs only if there's a change with respect to the last successful scheduled
pipeline run, and there's no in-progress scheduled pipeline run.
true
false
Pipeline runs according to the cron schedule.
true
true
Pipeline runs according to the cron schedule even if there is an in-progress run.
always  boolean.
Whether to always run the pipeline or only if there have been source code changes since the
last successful scheduled run; the default is false.
If you specify no scheduled trigger, no scheduled builds occur.
Remarks
７ Note
If you specify an exclude  clause without an include  clause for branches , it is equivalent to
specifying *  in the include  clause.
） Important
Scheduled triggers defined using the pipeline settings UI take precedence over YAML
scheduled triggers.
If your YAML pipeline has both YAML scheduled triggers and UI defined scheduled
triggers, only the UI defined scheduled triggers are run. To run the YAML defined
scheduled triggers in your YAML pipeline, you must remove the scheduled triggers
defined in the pipeline settings UI. Once all UI scheduled triggers are removed, a push
must be made in order for the YAML scheduled triggers to start being evaluated.
To delete UI scheduled triggers from a YAML pipeline, see UI settings override YAML
scheduled triggers.
Build.CronSchedule.DisplayName variable
When a pipeline is running due to a cron scheduled trigger, the pre-defined
Build.CronSchedule.DisplayName  variable contains the displayName  of the cron schedule that
triggered the pipeline run.
Your YAML pipeline may contain multiple cron schedules, and you may want your pipeline to
run different stages or jobs based on which cron schedule runs. For example, you have a
nightly build and a weekly build, and you want to run a certain stage only during the nightly
build. You can use the Build.CronSchedule.DisplayName  variable in a job or stage condition to
determine whether to run that job or stage.
yml
For more examples, see the following Examples section.
The following example defines two schedules.
The first schedule, Daily midnight build, runs a pipeline at midnight every day only if the code
has changed since the last successful scheduled run. It runs the pipeline for main  and all
releases/*  branches, except for those branches under releases/ancient/* .
The second schedule, Weekly Sunday build, runs a pipeline at noon on Sundays for all
releases/*  branches. It does so regardless of whether the code has changed since the last run.
YAML
- stage: stage1
  # Run this stage only when the pipeline is triggered by the 
  # "Daily midnight build" cron schedule
  condition: eq(variables['Build.CronSchedule.DisplayName'], 'Daily midnight 
build')
Examples
schedules:
- cron: '0 0 * * *'
  displayName: Daily midnight build
  branches:
    include:
    - main
    - releases/*
    exclude:
    - releases/ancient/*
- cron: '0 12 * * 0'
  displayName: Weekly Sunday build
  branches:
    include:
To conditionally run a stage or job based on whether it was scheduled by a scheduled trigger,
use the Build.CronSchedule.DisplayName  variable in a condition. In this example, stage1  only
runs if the pipeline was triggered by the Daily midnight build  schedule, and job3  only runs if
the pipeline was triggered by the Weekly Sunday build  schedule.
yml
Learn more about scheduled triggers.
Learn more about triggers in general and how to specify them.
Last updated on 03/02/2026
    - releases/*
  always: true
stages:
- stage: stage1
  # Run this stage only when the pipeline is triggered by the 
  # "Daily midnight build" cron schedule
  condition: eq(variables['Build.CronSchedule.DisplayName'], 'Daily midnight 
build')
  jobs:
  - job: job1
    steps:
    - script: echo Hello from Stage 1 Job 1
- stage: stage2
  dependsOn: [] # Indicate this stage does not depend on the previous stage
  jobs:
  - job: job2
    steps:
    - script: echo Hello from Stage 2 Job 2
  - job: job3 
    # Run this job only when the pipeline is triggered by the 
    # "Weekly Sunday build" cron schedule
    condition: eq(variables['Build.CronSchedule.DisplayName'], 'Weekly Sunday 
build')
    steps:
    - script: echo Hello from Stage 2 Job 3
See also
stages definition
ﾃ
Summarize this article for me
Stages are a collection of related jobs.
YAML
Definitions that reference this definition: pipeline
Type
Description
stages.stage
A stage is a collection of related jobs.
stages.template
You can define a set of stages in one file and use it multiple times in other files.
By default, stages run sequentially. Each stage starts only after the preceding stage is complete
unless otherwise specified via the dependsOn  property.
Use approval checks to manually control when a stage should run. These checks are commonly
used to control deployments to production environments.
Checks are a mechanism available to the resource owner. They control when a stage in a
pipeline consumes a resource. As an owner of a resource like an environment, you can define
checks that are required before a stage that consumes the resource can start.
Currently, manual approval checks are supported on environments. For more information, see
Approvals.
This example runs three stages, one after another. The middle stage runs two jobs in parallel.
YAML
stages: [ stage | template ] # Stages are a collection of related jobs.
List types
ﾉ
Expand table
Remarks
Examples
This example runs two stages in parallel. For brevity, the jobs and steps are omitted.
YAML
Learn more about stages, conditions, and variables.
Last updated on 03/02/2026
stages:
- stage: Build
  jobs:
  - job: BuildJob
    steps:
    - script: echo Building!
- stage: Test
  jobs:
  - job: TestOnWindows
    steps:
    - script: echo Testing on Windows!
  - job: TestOnLinux
    steps:
    - script: echo Testing on Linux!
- stage: Deploy
  jobs:
  - job: Deploy
    steps:
    - script: echo Deploying the code!
stages:
- stage: BuildWin
  displayName: Build for Windows
- stage: BuildMac
  displayName: Build for Mac
  dependsOn: [] # by specifying an empty array, this stage doesn't depend on the 
stage before it
See also
stages.stage definition
ﾃ
Summarize this article for me
Stages are a collection of related jobs. By default, stages run sequentially. Each stage starts only
after the preceding stage is complete unless otherwise specified via the dependsOn  property.
YAML
Definitions that reference this definition: stages
stage  string. Required as first property.
ID of the stage.
displayName  string.
Human-readable name for the stage.
pool  pool.
Pool where jobs in this stage will run unless otherwise specified.
dependsOn  string | string list.
Any stages which must complete before this one. By default stages are run sequentially in the
order defined in the pipeline. Specify dependsOn: []  for a stage if it shouldn't depend on the
previous stage in the pipeline.
stages:
- stage: string # Required as first property. ID of the stage.
  displayName: string # Human-readable name for the stage.
  pool: string | pool # Pool where jobs in this stage will run unless otherwise 
specified.
  dependsOn: string | [ string ] # Any stages which must complete before this one.
  condition: string # Evaluate this condition expression to determine whether to 
run this stage.
  variables: variables | [ variable ] # Stage-specific variables.
  jobs: [ job | deployment | template ] # Jobs which make up the stage.
  lockBehavior: sequential | runLatest # Behavior lock requests from this stage 
should exhibit in relation to other exclusive lock requests.
  trigger: manual | automatic # Stage trigger manual or automatic (default).
  isSkippable: boolean # Setting false prevents the stage from being skipped. By 
default it's always true.
  templateContext: # Stage related information passed from a pipeline when 
extending a template.
Properties
condition  string.
Evaluate this condition expression to determine whether to run this stage.
variables  variables.
Stage-specific variables.
jobs  jobs.
Jobs which make up the stage.
lockBehavior  string.
Behavior lock requests from this stage should exhibit in relation to other exclusive lock
requests. sequential | runLatest.
trigger  string.
Stage trigger manual or automatic (default). manual | automatic.
isSkippable  boolean.
Setting false prevents the stage from being skipped. By default it's always true.
templateContext  templateContext.
Stage related information passed from a pipeline when extending a template. For more
information about templateContext , see Extended YAML Pipelines templates can now be
passed context information for stages, jobs, and deployments and Templates - Use
templateContext to pass properties to templates.
For more information about templateContext , see Extended YAML Pipelines templates can now
be passed context information for stages, jobs, and deployments and Templates - Use
templateContext to pass properties to templates.
Use approval checks to manually control when a stage should run. These checks are commonly
used to control deployments to production environments.
Checks are a mechanism available to the resource owner. They control when a stage in a
pipeline consumes a resource. As an owner of a resource like an environment, you can define
checks that are required before a stage that consumes the resource can start.
Currently, manual approval checks are supported on environments. For more information, see
Approvals.
Remarks
Exclusive lock
In YAML pipelines, checks are used to control the execution of stages on protected resources.
One of the common checks that you can use is an exclusive lock check. This check lets only a
single run from the pipeline proceed. When multiple runs attempt to deploy to an environment
at the same time, the check cancels all the old runs and permits the latest run to be deployed.
You can configure the behavior of the exclusive lock check using the lockBehavior  property,
which has two values:
runLatest  - Only the latest run acquires the lock to the resource. This is the default value
if no lockBehavior  is specified.
sequential  - All runs acquire the lock sequentially to the protected resource.
Canceling old runs is a good approach when your releases are cumulative and contain all the
code changes from previous runs. However, there are some pipelines in which code changes
are not cumulative. By configuring the lockBehavior  property, you can choose to allow all runs
to proceed and deploy sequentially to an environment, or preserve the previous behavior of
canceling old runs and allowing just the latest. A value of sequential  implies that all runs
acquire the lock sequentially to the protected resource. A value of runLatest  implies that only
the latest run acquires the lock to the resource.
To use exclusive lock check with sequential  deployments or runLatest , follow these steps:
1. Enable the exclusive lock check on the environment (or another protected resource).
2. In the YAML file for the pipeline, specify a new property called lockBehavior . This can be
specified for the whole pipeline or for a given stage:
Set on a stage:
YAML
Set on the pipeline:
YAML
stages:
- stage: A
  lockBehavior: sequential
  jobs:
  - job: Job
    steps:
    - script: Hey!
lockBehavior: runLatest
stages:
- stage: A
Some use cases require a pipeline to access a specific resource only once at any given time. To
support this case, we have the exclusive lock check described in the previous section..
A similar situation arises when only one pipeline run should access a stage at any point in time.
For instance, if you have a stage that deploys to an Azure resource group, you may want to
prevent multiple pipeline runs from simultaneously updating the same resource group.
Currently, achieving this requires using a proxy resource, such as an environment, and placing
the exclusive lock check on that environment. This approach can be time-consuming, add
complexity, and increase maintenance efforts.
If you need to ensure only a single pipeline run at a time can access a stage, you can specify
the exclusive lock at the stage level. If you have a stage with an ID and specify its lockBehavior
property, a lock is automatically created for that stage. The sequential behavior remains
consistent for both resource-level and stage-level locks. However, the runLatest  behavior
differs, as it only cancels runLatest  builds for the same branch, not for all branches of the
pipeline.
This example runs three stages, one after another. The middle stage runs two jobs in parallel.
YAML
  jobs:
  - job: Job
    steps:
    - script: Hey!
Exclusive lock at stage level
Examples
stages:
- stage: Build
  jobs:
  - job: BuildJob
    steps:
    - script: echo Building!
- stage: Test
  jobs:
  - job: TestOnWindows
    steps:
    - script: echo Testing on Windows!
  - job: TestOnLinux
    steps:
    - script: echo Testing on Linux!
- stage: Deploy
  jobs:
This example runs two stages in parallel. For brevity, the jobs and steps are omitted.
YAML
Learn more about stages, conditions, and variables.
Last updated on 03/02/2026
  - job: Deploy
    steps:
    - script: echo Deploying the code!
stages:
- stage: BuildWin
  displayName: Build for Windows
- stage: BuildMac
  displayName: Build for Mac
  dependsOn: [] # by specifying an empty array, this stage doesn't depend on the 
stage before it
See also
stages.template definition
ﾃ
Summarize this article for me
You can define a set of stages in one file and use it multiple times in other files.
YAML
Definitions that reference this definition: stages
template  string. Required as first property.
Reference to a template for this stage.
parameters  template parameters.
Parameters used in a stage template.
Reference the stage template in the main pipeline.
YAML
Define the stages in the template.
YAML
stages:
- template: string # Required as first property. Reference to a template for this 
stage.
  parameters: # Parameters used in a stage template.
Properties
Remarks
- template: string # name of template to include
  parameters: { string: any } # provided parameters
parameters: { string: any } # expected parameters
stages: [ stage ]
Examples
In this example, a stage is repeated twice for two different testing regimes. The stage itself is
specified only once.
YAML
YAML
Template types & usage
Security through templates
# File: stages/test.yml
parameters:
  name: ''
  testFile: ''
stages:
- stage: Test_${{ parameters.name }}
  jobs:
  - job: ${{ parameters.name }}_Windows
    pool:
      vmImage: windows-latest
    steps:
    - script: npm install
    - script: npm test -- --file=${{ parameters.testFile }}
  - job: ${{ parameters.name }}_Mac
    pool:
      vmImage: macos-latest
    steps:
    - script: npm install
    - script: npm test -- --file=${{ parameters.testFile }}
# File: azure-pipelines.yml
stages:
- template: stages/test.yml  # Template reference
  parameters:
    name: Mini
    testFile: tests/miniSuite.js
- template: stages/test.yml  # Template reference
  parameters:
    name: Full
    testFile: tests/fullSuite.js
See also
Last updated on 03/02/2026
steps definition
ﾃ
Summarize this article for me
Steps are a linear sequence of operations that make up a job.
YAML
Definitions that reference this definition: pipeline, jobs.job, preDeployHook, deployHook,
routeTrafficHook, postRouteTrafficHook, onFailureHook, onSuccessHook
Type
Description
steps.task
Runs a task.
steps.script
Runs a script using cmd.exe on Windows and Bash on other platforms.
steps.powershell
Runs a script using either Windows PowerShell (on Windows) or pwsh (Linux and
macOS).
steps.pwsh
Runs a script in PowerShell Core on Windows, macOS, and Linux.
steps.bash
Runs a script in Bash on Windows, macOS, and Linux.
steps.checkout
Configure how the pipeline checks out source code.
steps.download
Downloads artifacts associated with the current run or from another Azure
Pipeline that is associated as a pipeline resource.
steps.downloadBuild
Downloads build artifacts.
steps.getPackage
Downloads a package from a package management feed in Azure Artifacts or
Azure DevOps Server.
steps.publish
Publishes (uploads) a file or folder as a pipeline artifact that other jobs and
pipelines can consume.
steps.template
Define a set of steps in one file and use it multiple times in another file.
steps.reviewApp
Downloads creates a resource dynamically under a deploy phase provider.
steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # Steps are a linear 
sequence of operations that make up a job.
List types
ﾉ
Expand table
Each step runs in its own process on an agent and has access to the pipeline workspace on a
local hard drive. This behavior means environment variables aren't preserved between steps
but file system changes are.
All tasks and steps support a set of common properties, such as enabled  and env ,in addition
to their task or step specific properties. For more information on configuring these properties,
see Task control options and Task environment variables.
YAML
Specify jobs in your pipeline
Task types and usage
Last updated on 03/02/2026
Remarks
Examples
steps:
- script: echo This runs in the default shell on any machine
- bash: |
    echo This multiline script always runs in Bash.
    echo Even on Windows machines!
- pwsh: |
    Write-Host "This multiline script always runs in PowerShell Core."
    Write-Host "Even on non-Windows machines!"
See also
steps.bash definition
ﾃ
Summarize this article for me
The bash  step runs a script in Bash on Windows, macOS, and Linux.
YAML
Definitions that reference this definition: steps
bash  string. Required as first property.
An inline script.
failOnStderr  string.
Fail the task if output is sent to Stderr?
workingDirectory  string.
Start the script with this working directory.
condition  string.
Evaluate this condition expression to determine whether to run this task.
continueOnError  boolean.
Continue running even on failure?
displayName  string.
Human-readable name for the task.
steps:
- bash: string # Required as first property. An inline script.
  failOnStderr: string # Fail the task if output is sent to Stderr?
  workingDirectory: string # Start the script with this working directory.
  condition: string # Evaluate this condition expression to determine whether to 
run this task.
  continueOnError: boolean # Continue running even on failure?
  displayName: string # Human-readable name for the task.
  target: string | target # Environment in which to run this task.
  enabled: boolean # Run this task when the job runs?
  env: # Variables to map into the process's environment.
    string: string # Name/value pairs
  name: string # ID of the step.
  timeoutInMinutes: string # Time to wait for this task to complete before the 
server kills it.
  retryCountOnTaskFailure: string # Number of retries if the task fails.
Properties
target  target.
Environment in which to run this task.
enabled  boolean.
Run this task when the job runs?
env  string dictionary.
Variables to map into the process's environment.
name  string.
ID of the step. Acceptable values: [-_A-Za-z0-9]*.
timeoutInMinutes  string.
Time to wait for this task to complete before the server kills it.
retryCountOnTaskFailure  string.
Number of retries if the task fails.
The bash  keyword is a shortcut for the shell script task. The task runs a script in Bash on
Windows, macOS, and Linux.
Learn more about conditions, timeouts, and step targets.
YAML
７ Note
Pipelines may be configured with a job level timeout. If the job level timeout interval
elapses before your step completes, the running job (including your step) is terminated,
even if the step is configured with a longer timeoutInMinutes  interval. For more
information, see Timeouts.
Remarks
Examples
steps:
- bash: |
    which bash
    echo Hello $name
  displayName: Multiline Bash script
If you don't specify a command mode, you can shorten the target  structure to:
YAML
shell script task
Learn more about conditions, timeouts, and step targets
Last updated on 03/02/2026
  env:
    name: Microsoft
- bash:
  target: string  # container name or the word 'host'
See also
steps.checkout definition
ﾃ
Summarize this article for me
Use checkout  to configure how the pipeline checks out source code.
YAML
Definitions that reference this definition: steps
checkout  string. Required as first property.
Configures checkout for the specified repository. Specify self , none , repository name, or
steps:
- checkout: string # Required as first property. Configures checkout for the 
specified repository.
  clean: true | false # If true, run git clean -ffdx followed by git reset --hard 
HEAD before fetching.
  fetchDepth: string # Depth of Git graph to fetch.
  fetchFilter: string # Filter Git history.
  fetchTags: string # Set to 'true' to sync tags when fetching the repo, or 'false' 
to not sync tags. See remarks for the default behavior.
  lfs: string # Set to 'true' to download Git-LFS files. Default is not to download 
them.
  persistCredentials: string # Set to 'true' to leave the OAuth token in the Git 
config after the initial fetch. The default is not to leave it.
  submodules: string # Set to 'true' for a single level of submodules or 
'recursive' to get submodules of submodules. Default is not to fetch submodules.
  path: string # Where to put the repository. The root directory is 
$(Pipeline.Workspace).
  sparseCheckoutDirectories: string # Directories for sparse checkout in cone mode 
and prioritized over sparseCheckoutPatterns if both properties are provided.
  sparseCheckoutPatterns: string # Patterns for sparse checkout in non-cone mode 
that are ignored if sparseCheckoutDirectories is provided.
  workspaceRepo: true | false # When true, use the repository root directory as the 
default working directory for the pipeline. The default is false.
  condition: string # Evaluate this condition expression to determine whether to 
run this task.
  continueOnError: boolean # Continue running even on failure?
  displayName: string # Human-readable name for the task.
  target: string | target # Environment in which to run this task.
  enabled: boolean # Run this task when the job runs?
  env: # Variables to map into the process's environment.
    string: string # Name/value pairs
  name: string # ID of the step.
  timeoutInMinutes: string # Time to wait for this task to complete before the 
server kills it.
  retryCountOnTaskFailure: string # Number of retries if the task fails.
Properties
repository resource. For more information, see Check out multiple repositories in your pipeline.
clean  string.
If true, run git clean -ffdx followed by git reset --hard HEAD before fetching. true | false.
fetchDepth  string.
Depth of Git graph to fetch.
fetchFilter  string.
Use fetchFilter  to filter Git history for partial cloning. The fetchFilter  setting supports
treeless and blobless fetches. For a treeless fetch, specify fetchFilter: tree:0  and to specify a
blobless fetch, specify fetchFilter: blob:none . The default is no filtering.
fetchTags  string.
Set to 'true' to sync tags when fetching the repo, or 'false' to not sync tags. See remarks for the
default behavior.
lfs  string.
Set to 'true' to download Git-LFS files. Default is not to download them.
persistCredentials  string.
Set to 'true' to leave the OAuth token in the Git config after the initial fetch. The default is not
to leave it.
submodules  string.
Set to 'true' for a single level of submodules or 'recursive' to get submodules of submodules.
Default is not to fetch submodules.
path  string.
Where to put the repository. The root directory is $(Pipeline.Workspace). By default this folder
must be under the agent working directory structure. To set a path outside of the agent
working directory, set a pipeline variable named AZP_AGENT_ALLOW_WORK_DIRECTORY_REPOSITORIES
to true, and use the prefix ../  at the start of your checkout path. Supported on agent version
3.230.0 and higher.
sparseCheckoutDirectories  string.
Specify a directory to enable sparse checkout in cone mode using directory matching. Separate
７ Note
If no checkout  step is present, it defaults to self  for jobs.job.step.checkout  and none
for jobs.deployment.steps.checkout .
multiple directories using a space. Supported on agent version 3.253.0/4.253.0 and higher with
Git 2.25 or higher.
yml
If both sparseCheckoutDirectories  and sparseCheckoutPatterns  are set,
sparseCheckoutDirectories  is used and the setting for sparseCheckoutPatterns  is disregarded.
For more information on sparse checkout, see Bring your monorepo down to size with sparse-
checkout
.
sparseCheckoutPatterns  string.
Specify a pattern to enable sparse checkout in non-cone mode using pattern matching.
Separate multiple patterns using a space. Supported on agent version 3.253.0/4.253.0 and
higher with Git 2.25 or higher.
yml
If both sparseCheckoutDirectories  and sparseCheckoutPatterns  are set,
sparseCheckoutDirectories  is used and the setting for sparseCheckoutPatterns  is disregarded.
For more information on sparse checkout, see Bring your monorepo down to size with sparse-
checkout
.
workspaceRepo  string.
When true, use the repository root directory as the default working directory for the pipeline.
The default is false.
condition  string.
Evaluate this condition expression to determine whether to run this task.
continueOnError  boolean.
Continue running even on failure?
displayName  string.
Human-readable name for the task.
- checkout: repo
  sparseCheckoutDirectories: src
- checkout: repo
  sparseCheckoutPatterns: /* !/img
target  target.
Environment in which to run this task.
enabled  boolean.
Run this task when the job runs?
env  string dictionary.
Variables to map into the process's environment.
name  string.
ID of the step. Acceptable values: [-_A-Za-z0-9]*.
timeoutInMinutes  string.
Time to wait for this task to complete before the server kills it.
retryCountOnTaskFailure  string.
Number of retries if the task fails.
Shallow fetch
Clean property
Sync tags
７ Note
Pipelines may be configured with a job level timeout. If the job level timeout interval
elapses before your step completes, the running job (including your step) is terminated,
even if the step is configured with a longer timeoutInMinutes  interval. For more
information, see Timeouts.
Remarks
Shallow fetch
７ Note
In some organizations, new pipelines created after the September 2022 Azure DevOps
sprint 209 update have Shallow fetch enabled by default and configured with a depth of
1. Previously the default was not to shallow fetch.
To check your pipeline, view the Shallow fetch setting in the pipeline settings UI.
To disable shallow fetch, you can perform one of the following two options.
Disable the Shallow fetch option in the pipeline settings UI.
Explicitly set fetchDepth: 0  in your checkout  step.
To configure the fetch depth for a pipeline, you can either set the fetchDepth  property in the
checkout  step, or configure the Shallow fetch setting in the pipeline settings UI.
If the clean  property is unset, then its default value is configured by the clean setting in the UI
settings for YAML pipelines, which is set to true by default. In addition to the cleaning option
available using checkout , you can also configure cleaning in a workspace. For more
information about workspaces and clean options, see the workspace topic in Jobs.
The checkout step uses the --tags  option when fetching the contents of a Git repository. This
causes the server to fetch all tags as well as all objects that are pointed to by those tags. This
increases the time to run the task in a pipeline, particularly if you have a large repository with a
number of tags. Furthermore, the checkout step syncs tags even when you enable the shallow
fetch option, thereby possibly defeating its purpose. To reduce the amount of data fetched or
pulled from a Git repository, Microsoft has added a new option to checkout to control the
behavior of syncing tags. This option is available both in classic and YAML pipelines.
To configure whether to synchronize tags when checking out a repository, you can either set
the fetchTags  property in the checkout  step, or configure the Sync tags setting in the pipeline
settings UI.
７ Note
If you explicitly set fetchDepth  in your checkout  step, that setting takes priority over the
setting configured in the pipeline settings UI. Setting fetchDepth: 0  fetches all history and
overrides the Shallow fetch setting.
Clean property
Sync tags
７ Note
If you explicitly set fetchTags  in your checkout  step, that setting takes priority over the
Sync tags setting configured in the pipeline settings UI.
To configure the setting in YAML, set the fetchTags  property.
YAML
To configure the setting in the pipeline UI, edit your YAML pipeline, and choose More actions,
Triggers, YAML, Get sources, and check or uncheck the Sync tags checkbox. For more
information, see Sync tags.
For existing pipelines created before the release of Azure DevOps sprint 209, released in
September 2022, the default for syncing tags remains the same as the existing behavior
before the Sync tags options was added, which is true .
For new pipelines created after Azure DevOps sprint release 209, the default for syncing
tags is false .
There are three options for checkout . By default, Azure DevOps checks out the current
repository with self  for jobs. When you set none , no repository is checked out. If you specify
another repository, that repository is checked out. To check out a different repository, set it up
as a repository resource first.
YAML
To avoid syncing sources at all:
YAML
steps:
- checkout: self
  fetchTags: true
Default behavior
Examples
# Checkout the current repository
steps:
- checkout: self
# Prevent checking out any source code
steps:
- checkout: none
# Checkout a different repository
steps:
- checkout: my-other-repo
YAML
To check out multiple repositories in your pipeline, use multiple checkout  steps:
YAML
For more information, see Check out multiple repositories in your pipeline.
Supported source repositories
Last updated on 03/02/2026
steps:
- checkout: none
７ Note
If you're running the agent in the Local Service account and want to modify the current
repository by using git operations or loading git submodules, give the proper permissions
to the Project Collection Build Service Accounts user.
- checkout: self
  submodules: true
  persistCredentials: true
- checkout: self
- checkout: git://MyProject/MyRepo
- checkout: MyGitHubRepo # Repo declared in a repository resource
See also
steps.download definition
ﾃ
Summarize this article for me
The download  step downloads artifacts associated with the current run or from another Azure
Pipeline that is associated as a pipeline resource.
YAML
Definitions that reference this definition: steps
download  string. Required as first property.
Specify current, pipeline resource identifier, or none to disable automatic download.
artifact  string.
Artifact name.
patterns  string.
Pattern to download files from artifact.
condition  string.
Evaluate this condition expression to determine whether to run this task.
continueOnError  boolean.
Continue running even on failure?
steps:
- download: string # Required as first property. Specify current, pipeline resource 
identifier, or none to disable automatic download.
  artifact: string # Artifact name.
  patterns: string # Pattern to download files from artifact.
  condition: string # Evaluate this condition expression to determine whether to 
run this task.
  continueOnError: boolean # Continue running even on failure?
  displayName: string # Human-readable name for the task.
  target: string | target # Environment in which to run this task.
  enabled: boolean # Run this task when the job runs?
  env: # Variables to map into the process's environment.
    string: string # Name/value pairs
  name: string # ID of the step.
  timeoutInMinutes: string # Time to wait for this task to complete before the 
server kills it.
  retryCountOnTaskFailure: string # Number of retries if the task fails.
Properties
displayName  string.
Human-readable name for the task.
target  target.
Environment in which to run this task.
enabled  boolean.
Run this task when the job runs?
env  string dictionary.
Variables to map into the process's environment.
name  string.
ID of the step. Acceptable values: [-_A-Za-z0-9]*.
timeoutInMinutes  string.
Time to wait for this task to complete before the server kills it.
retryCountOnTaskFailure  string.
Number of retries if the task fails.
The download  keyword downloads artifact resources to the folder specified in Artifact
download location.
Depending on the type of referenced artifact (or artifacts), download  calls Download Pipeline
Artifacts (for Pipeline artifacts, if your pipeline is running in Azure DevOps Services), Download
Build Artifacts (for build artifacts), or Download artifacts from file share (for file share artifacts).
Artifacts from the current pipeline are downloaded to $(Pipeline.Workspace)/<artifact name> .
７ Note
Pipelines may be configured with a job level timeout. If the job level timeout interval
elapses before your step completes, the running job (including your step) is terminated,
even if the step is configured with a longer timeoutInMinutes  interval. For more
information, see Timeouts.
Remarks
Artifact download location
Artifacts from the associated pipeline resource are downloaded to
$(Pipeline.Workspace)/<pipeline resource identifier>/<artifact name> .
All available artifacts from the current pipeline and from the associated pipeline resources are
automatically downloaded in deployment jobs and made available for your deployment.
To prevent downloads, specify download: none .
YAML
Publish and download pipeline Artifacts
Download Pipeline Artifacts task
Last updated on 03/02/2026
Automatic download in deployment jobs
Examples
steps:
- download: current  # refers to artifacts published by current pipeline
  artifact: WebApp
  patterns: '**/.js'
  displayName: Download artifact WebApp
- download: MyAppA   # downloads artifacts available as part of the pipeline 
resource specified as MyAppA
See also
steps.downloadBuild definition
ﾃ
Summarize this article for me
The downloadBuild  step downloads build artifacts.
YAML
Definitions that reference this definition: steps
downloadBuild  string. Required as first property.
ID for the build resource.
artifact  string.
Artifact name.
path  string.
Path to download the artifact into.
patterns  string.
Downloads the files which matches the patterns.
condition  string.
Evaluate this condition expression to determine whether to run this task.
steps:
- downloadBuild: string # Required as first property. Alias of the build resource.
  artifact: string # Artifact name.
  path: string # Path to download the artifact into.
  patterns: string # Downloads the files which matches the patterns.
  condition: string # Evaluate this condition expression to determine whether to 
run this task.
  continueOnError: boolean # Continue running even on failure?
  displayName: string # Human-readable name for the task.
  target: string | target # Environment in which to run this task.
  enabled: boolean # Run this task when the job runs?
  env: # Variables to map into the process's environment.
    string: string # Name/value pairs
  name: string # ID of the step.
  timeoutInMinutes: string # Time to wait for this task to complete before the 
server kills it.
  retryCountOnTaskFailure: string # Number of retries if the task fails.
Properties
continueOnError  boolean.
Continue running even on failure?
displayName  string.
Human-readable name for the task.
target  target.
Environment in which to run this task.
enabled  boolean.
Run this task when the job runs?
env  string dictionary.
Variables to map into the process's environment.
name  string.
ID of the step. Acceptable values: [-_A-Za-z0-9]*.
timeoutInMinutes  string.
Time to wait for this task to complete before the server kills it.
retryCountOnTaskFailure  string.
Number of retries if the task fails.
The downloadBuild  keyword is a shortcut for the Download Build Artifacts task.
７ Note
Pipelines may be configured with a job level timeout. If the job level timeout interval
elapses before your step completes, the running job (including your step) is terminated,
even if the step is configured with a longer timeoutInMinutes  interval. For more
information, see Timeouts.
Remarks
７ Note
The pipelines team recommends upgrading from downloadBuild  (download build artifacts
task) to download (download pipeline artifacts task) for faster performance.
download
Publish and download pipeline Artifacts
Download Build Artifacts task
Last updated on 03/02/2026
See also
steps.getPackage definition
ﾃ
Summarize this article for me
The getPackage  step downloads a package from a package management feed in Azure
Artifacts or Azure DevOps Server.
YAML
Definitions that reference this definition: steps
getPackage  string. Required as first property.
ID for the package resource.
path  string.
Path to download the package into.
condition  string.
Evaluate this condition expression to determine whether to run this task.
continueOnError  boolean.
Continue running even on failure?
displayName  string.
Human-readable name for the task.
target  target.
Environment in which to run this task.
steps:
- getPackage: string # Required as first property. Alias of the package resource.
  path: string # Path to download the package into.
  condition: string # Evaluate this condition expression to determine whether to 
run this task.
  continueOnError: boolean # Continue running even on failure?
  displayName: string # Human-readable name for the task.
  target: string | target # Environment in which to run this task.
  enabled: boolean # Run this task when the job runs?
  env: # Variables to map into the process's environment.
    string: string # Name/value pairs
  name: string # ID of the step.
  timeoutInMinutes: string # Time to wait for this task to complete before the 
server kills it.
  retryCountOnTaskFailure: string # Number of retries if the task fails.
Properties
enabled  boolean.
Run this task when the job runs?
env  string dictionary.
Variables to map into the process's environment.
name  string.
ID of the step. Acceptable values: [-_A-Za-z0-9]*.
timeoutInMinutes  string.
Time to wait for this task to complete before the server kills it.
retryCountOnTaskFailure  string.
Number of retries if the task fails.
The getPackage  keyword is a shortcut for the Download Package task.
Download Package task
Last updated on 03/02/2026
７ Note
Pipelines may be configured with a job level timeout. If the job level timeout interval
elapses before your step completes, the running job (including your step) is terminated,
even if the step is configured with a longer timeoutInMinutes  interval. For more
information, see Timeouts.
Remarks
See also
steps.powershell definition
ﾃ
Summarize this article for me
The powershell  step runs a script using either Windows PowerShell (on Windows) or pwsh
(Linux and macOS).
YAML
Definitions that reference this definition: steps
powershell  string. Required as first property.
Inline PowerShell script.
errorActionPreference  string.
Unless otherwise specified, the error action preference defaults to the value stop. See the
following section for more information.
failOnStderr  string.
Fail the task if output is sent to Stderr?
ignoreLASTEXITCODE  string.
Check the final exit code of the script to determine whether the step succeeded?
steps:
- powershell: string # Required as first property. Inline PowerShell script.
  errorActionPreference: string # Unless otherwise specified, the error action 
preference defaults to the value stop. See the following section for more 
information.
  failOnStderr: string # Fail the task if output is sent to Stderr?
  ignoreLASTEXITCODE: string # Check the final exit code of the script to determine 
whether the step succeeded?
  workingDirectory: string # Start the script with this working directory.
  condition: string # Evaluate this condition expression to determine whether to 
run this task.
  continueOnError: boolean # Continue running even on failure?
  displayName: string # Human-readable name for the task.
  target: string | target # Environment in which to run this task.
  enabled: boolean # Run this task when the job runs?
  env: # Variables to map into the process's environment.
    string: string # Name/value pairs
  name: string # ID of the step.
  timeoutInMinutes: string # Time to wait for this task to complete before the 
server kills it.
  retryCountOnTaskFailure: string # Number of retries if the task fails.
Properties
workingDirectory  string.
Start the script with this working directory.
condition  string.
Evaluate this condition expression to determine whether to run this task.
continueOnError  boolean.
Continue running even on failure?
displayName  string.
Human-readable name for the task.
target  target.
Environment in which to run this task.
enabled  boolean.
Run this task when the job runs?
env  string dictionary.
Variables to map into the process's environment.
name  string.
ID of the step. Acceptable values: [-_A-Za-z0-9]*.
timeoutInMinutes  string.
Time to wait for this task to complete before the server kills it.
retryCountOnTaskFailure  string.
Number of retries if the task fails.
The powershell  keyword is a shortcut for the PowerShell task. The task runs a script using
either Windows PowerShell (on Windows) or pwsh  (Linux and macOS).
７ Note
Pipelines may be configured with a job level timeout. If the job level timeout interval
elapses before your step completes, the running job (including your step) is terminated,
even if the step is configured with a longer timeoutInMinutes  interval. For more
information, see Timeouts.
Remarks
Each PowerShell session lasts only for the duration of the job in which it runs. Tasks that
depend on what has been bootstrapped must be in the same job as the bootstrap.
Learn more about conditions and timeouts.
Unless otherwise specified, the error action preference defaults to the value stop , and the line
$ErrorActionPreference = 'stop'  is prepended to the top of your script.
When the error action preference is set to stop, errors cause PowerShell to terminate the task
and return a nonzero exit code. The task is also marked as Failed.
YAML
YAML
The last exit code returned from your script is checked by default. A nonzero code indicates a
step failure, in which case the system appends your script with:
if ((Test-Path -LiteralPath variable:\LASTEXITCODE)) { exit $LASTEXITCODE }
If you don't want this behavior, specify ignoreLASTEXITCODE: true .
YAML
YAML
Error action preference
errorActionPreference: stop | continue | silentlyContinue
steps:
- powershell: |
    Write-Error 'Uh oh, an error occurred'
    Write-Host 'Trying again...'
  displayName: Error action preference
  errorActionPreference: continue
Ignore last exit code
ignoreLASTEXITCODE: boolean
steps:
- powershell: git nosuchcommand
Learn more about conditions and timeouts.
YAML
PowerShell task
Learn more about conditions and timeouts
Last updated on 03/02/2026
  displayName: Ignore last exit code
  ignoreLASTEXITCODE: true
Examples
steps:
- powershell: Write-Host Hello $Env:name
  displayName: Say hello
  name: firstStep
  workingDirectory: $(build.sourcesDirectory)
  failOnStderr: true
  env:
    name: Microsoft
See also
steps.publish definition
ﾃ
Summarize this article for me
The publish  keyword publishes (uploads) a file or folder as a pipeline artifact that other jobs
and pipelines can consume.
YAML
Definitions that reference this definition: steps
publish  string. Required as first property.
The publish step is a shortcut for the PublishPipelineArtifact@1 task. The task publishes
(uploads) a file or folder as a pipeline artifact that other jobs and pipelines can consume.
artifact  string.
Artifact name.
condition  string.
Evaluate this condition expression to determine whether to run this task.
continueOnError  boolean.
Continue running even on failure?
displayName  string.
Human-readable name for the task.
steps:
- publish: string # Required as first property. The publish step is a shortcut for 
the PublishPipelineArtifact@1 task. The task publishes (uploads) a file or folder 
as a pipeline artifact that other jobs and pipelines can consume.
  artifact: string # Artifact name.
  condition: string # Evaluate this condition expression to determine whether to 
run this task.
  continueOnError: boolean # Continue running even on failure?
  displayName: string # Human-readable name for the task.
  target: string | target # Environment in which to run this task.
  enabled: boolean # Run this task when the job runs?
  env: # Variables to map into the process's environment.
    string: string # Name/value pairs
  name: string # ID of the step.
  timeoutInMinutes: string # Time to wait for this task to complete before the 
server kills it.
  retryCountOnTaskFailure: string # Number of retries if the task fails.
Properties
target  target.
Environment in which to run this task.
enabled  boolean.
Run this task when the job runs?
env  string dictionary.
Variables to map into the process's environment.
name  string.
ID of the step. Acceptable values: [-_A-Za-z0-9]*.
timeoutInMinutes  string.
Time to wait for this task to complete before the server kills it.
retryCountOnTaskFailure  string.
Number of retries if the task fails.
The publish  keyword is a shortcut for the Publish Pipeline Artifact task.
Learn more about publishing artifacts.
７ Note
Pipelines may be configured with a job level timeout. If the job level timeout interval
elapses before your step completes, the running job (including your step) is terminated,
even if the step is configured with a longer timeoutInMinutes  interval. For more
information, see Timeouts.
Remarks
） Important
The publish  step is supported on Azure DevOps Services only. If you use it on Azure
DevOps Server, you'll receive an error message similar to Pipeline Artifact Task is not
supported in on-premises. Please use Build Artifact Task instead.  Use Publish Build
Artifacts if you're using Azure DevOps Server.
Examples
YAML
Publish Pipeline Artifact task
Publishing artifacts
Last updated on 03/02/2026
steps:
- publish: $(Build.SourcesDirectory)/build
  artifact: WebApp
  displayName: Publish artifact WebApp
See also
steps.pwsh definition
ﾃ
Summarize this article for me
The pwsh  step runs a script in PowerShell Core on Windows, macOS, and Linux.
YAML
Definitions that reference this definition: steps
pwsh  string. Required as first property.
Inline PowerShell script.
errorActionPreference  string.
Unless otherwise specified, the error action preference defaults to the value stop. See the
following section for more information.
failOnStderr  string.
Fail the task if output is sent to Stderr?
ignoreLASTEXITCODE  string.
Check the final exit code of the script to determine whether the step succeeded?
steps:
- pwsh: string # Required as first property. Inline PowerShell script.
  errorActionPreference: string # Unless otherwise specified, the error action 
preference defaults to the value stop. See the following section for more 
information.
  failOnStderr: string # Fail the task if output is sent to Stderr?
  ignoreLASTEXITCODE: string # Check the final exit code of the script to determine 
whether the step succeeded?
  workingDirectory: string # Start the script with this working directory.
  condition: string # Evaluate this condition expression to determine whether to 
run this task.
  continueOnError: boolean # Continue running even on failure?
  displayName: string # Human-readable name for the task.
  target: string | target # Environment in which to run this task.
  enabled: boolean # Run this task when the job runs?
  env: # Variables to map into the process's environment.
    string: string # Name/value pairs
  name: string # ID of the step.
  timeoutInMinutes: string # Time to wait for this task to complete before the 
server kills it.
  retryCountOnTaskFailure: string # Number of retries if the task fails.
Properties
workingDirectory  string.
Start the script with this working directory.
condition  string.
Evaluate this condition expression to determine whether to run this task.
continueOnError  boolean.
Continue running even on failure?
displayName  string.
Human-readable name for the task.
target  target.
Environment in which to run this task.
enabled  boolean.
Run this task when the job runs?
env  string dictionary.
Variables to map into the process's environment.
name  string.
ID of the step. Acceptable values: [-_A-Za-z0-9]*.
timeoutInMinutes  string.
Time to wait for this task to complete before the server kills it.
retryCountOnTaskFailure  string.
Number of retries if the task fails.
The pwsh  keyword is a shortcut for the PowerShell task when that task's pwsh value is set to
true. The task runs a script in PowerShell Core on Windows, macOS, and Linux.
７ Note
Pipelines may be configured with a job level timeout. If the job level timeout interval
elapses before your step completes, the running job (including your step) is terminated,
even if the step is configured with a longer timeoutInMinutes  interval. For more
information, see Timeouts.
Remarks
Learn more about conditions and timeouts.
Each PowerShell session lasts only for the duration of the job in which it runs. Tasks that
depend on what has been bootstrapped must be in the same job as the bootstrap.
YAML
PowerShell task
Learn more about conditions and timeouts
Last updated on 03/02/2026
Examples
steps:
- pwsh: Write-Host Hello $($env:name)
  displayName: Say hello
  name: firstStep
  workingDirectory: $(build.sourcesDirectory)
  failOnStderr: true
  env:
    name: Microsoft
See also
steps.reviewApp definition
ﾃ
Summarize this article for me
The reviewApp  step deploys every pull request from your Git repository to a dynamic
environment resource.
YAML
Definitions that reference this definition: steps
reviewApp  string. Required as first property.
Use this task under deploy phase provider to create a resource dynamically.
condition  string.
Evaluate this condition expression to determine whether to run this task.
continueOnError  boolean.
Continue running even on failure?
displayName  string.
Human-readable name for the task.
target  target.
Environment in which to run this task.
enabled  boolean.
Run this task when the job runs?
steps:
- reviewApp: string # Required as first property. Use this task under deploy phase 
provider to create a resource dynamically.
  condition: string # Evaluate this condition expression to determine whether to 
run this task.
  continueOnError: boolean # Continue running even on failure?
  displayName: string # Human-readable name for the task.
  target: string | target # Environment in which to run this task.
  enabled: boolean # Run this task when the job runs?
  env: # Variables to map into the process's environment.
    string: string # Name/value pairs
  name: string # ID of the step.
  timeoutInMinutes: string # Time to wait for this task to complete before the 
server kills it.
  retryCountOnTaskFailure: string # Number of retries if the task fails.
Properties
env  string dictionary.
Variables to map into the process's environment.
name  string.
ID of the step. Acceptable values: [-_A-Za-z0-9]*.
timeoutInMinutes  string.
Time to wait for this task to complete before the server kills it.
retryCountOnTaskFailure  string.
Number of retries if the task fails.
The reviewApp  keyword is a shortcut for the Review App task.
ReviewApp  deploys every pull request from your Git repository to a dynamic environment
resource. Reviewers can see how those changes look as well as work with other dependent
services before they’re merged into the main branch and deployed to production. This will
make it easy for you to create and manage reviewApp resources and benefit from all the
traceability and diagnosis capability of the environment features. By using the reviewApp
keyword, you can create a clone of a resource (dynamically create a new resource based on an
existing resource in an environment) and add the new resource to the environment.
For more information, see Kubernetes resource - Set up Review App and What’s new in Azure
DevOps Sprint 160
.
The following is a sample YAML snippet of using reviewApp under environments.
YAML
７ Note
Pipelines may be configured with a job level timeout. If the job level timeout interval
elapses before your step completes, the running job (including your step) is terminated,
even if the step is configured with a longer timeoutInMinutes  interval. For more
information, see Timeouts.
Remarks
Examples
Review App task
What’s new in Azure DevOps Sprint 160
Kubernetes resource - Set up Review App
Last updated on 03/02/2026
jobs:
- deployment:
  environment: 
     name: smarthotel-dev      
     resourceName: $(System.PullRequest.PullRequestId) 
  pool:
    name: 'ubuntu-latest'
  strategy:                 
    runOnce:            
      pre-deploy: 
        steps:       
        - reviewApp: MainNamespace
See also
steps.script definition
ﾃ
Summarize this article for me
The script  step runs a script using cmd.exe on Windows and Bash on other platforms.
YAML
Definitions that reference this definition: steps
script  string. Required as first property.
An inline script.
failOnStderr  string.
Fail the task if output is sent to Stderr?
workingDirectory  string.
Start the script with this working directory.
condition  string.
Evaluate this condition expression to determine whether to run this task.
continueOnError  boolean.
Continue running even on failure?
displayName  string.
Human-readable name for the task.
steps:
- script: string # Required as first property. An inline script.
  failOnStderr: string # Fail the task if output is sent to Stderr?
  workingDirectory: string # Start the script with this working directory.
  condition: string # Evaluate this condition expression to determine whether to 
run this task.
  continueOnError: boolean # Continue running even on failure?
  displayName: string # Human-readable name for the task.
  target: string | target # Environment in which to run this task.
  enabled: boolean # Run this task when the job runs?
  env: # Variables to map into the process's environment.
    string: string # Name/value pairs
  name: string # ID of the step.
  timeoutInMinutes: string # Time to wait for this task to complete before the 
server kills it.
  retryCountOnTaskFailure: string # Number of retries if the task fails.
Properties
target  target.
Environment in which to run this task.
enabled  boolean.
Run this task when the job runs?
env  string dictionary.
Variables to map into the process's environment.
name  string.
ID of the step. Acceptable values: [-_A-Za-z0-9]*.
timeoutInMinutes  string.
Time to wait for this task to complete before the server kills it.
retryCountOnTaskFailure  string.
Number of retries if the task fails.
The script  keyword is a shortcut for the command-line task. The task runs a script using
cmd.exe on Windows and Bash on other platforms.
Learn more about conditions, timeouts, and step targets.
If you don't specify a command mode, you can shorten the target  structure to:
YAML
７ Note
Pipelines may be configured with a job level timeout. If the job level timeout interval
elapses before your step completes, the running job (including your step) is terminated,
even if the step is configured with a longer timeoutInMinutes  interval. For more
information, see Timeouts.
Remarks
Examples
- script:
  target: string  # container name or the word 'host'
YAML
command-line task
Last updated on 03/02/2026
steps:
- script: echo Hello world!
  displayName: Say hello
See also
steps.task definition
ﾃ
Summarize this article for me
A task  step runs a task.
All tasks support the following set of common properties.
YAML
Definitions that reference this definition: steps
task  string. Required as first property.
Name of the task to run.
inputs  string dictionary.
Inputs for the task.
condition  string.
Evaluate this condition expression to determine whether to run this task.
continueOnError  boolean.
Continue running even on failure?
displayName  string.
Human-readable name for the task.
steps:
- task: string # Required as first property. Name of the task to run.
  inputs: # Inputs for the task.
    string: string # Name/value pairs
  condition: string # Evaluate this condition expression to determine whether to 
run this task.
  continueOnError: boolean # Continue running even on failure?
  displayName: string # Human-readable name for the task.
  target: string | target # Environment in which to run this task.
  enabled: boolean # Run this task when the job runs?
  env: # Variables to map into the process's environment.
    string: string # Name/value pairs
  name: string # ID of the step.
  timeoutInMinutes: integer # Time to wait for this task to complete before the 
server kills it.
  retryCountOnTaskFailure: integer # Number of retries if the task fails.
Properties
target  target.
Environment in which to run this task.
enabled  boolean.
Run this task when the job runs?
env  string dictionary.
Variables to map into the process's environment.
name  string.
ID of the step. Acceptable values: [-_A-Za-z0-9]*.
timeoutInMinutes  integer.
Time to wait for this task to complete before the server kills it. For example, to configure a 10
minute timeout, use timeoutInMinutes: 10 .
retryCountOnTaskFailure  integer.
Number of retries if the task fails. On Azure DevOps Server 2022, 2022.1, and 2022.2, retries are
supported only on agent jobs. For more information, see Azure DevOps service update
November 16, 2021 - Automatic retries for a task and Azure DevOps service update June 14,
2025 - Retries for server tasks.
Tasks are the building blocks of a pipeline. There's a catalog of tasks available to choose from.
If you don't specify a command mode, you can shorten the target  structure to:
YAML
７ Note
Pipelines may be configured with a job level timeout. If the job level timeout interval
elapses before your step completes, the running job (including your step) is terminated,
even if the step is configured with a longer timeoutInMinutes  interval. For more
information, see Timeouts.
Remarks
- task:
  target: string  # container name or the word 'host'
Common task properties
All tasks support a set of common properties in addition to name  and inputs . For a list of
common task properties, see the preceding Properties section. For more information on
configuring these properties, see Task control options and Task environment variables.
Learn more about conditions, timeouts, and step targets.
YAML
Tasks
Catalog of tasks
Last updated on 03/02/2026
Examples
steps:
- task: VSBuild@1
  displayName: Build
  timeoutInMinutes: 120
  inputs:
    solution: '**\*.sln'
See also
steps.template definition
ﾃ
Summarize this article for me
Define a set of steps in one file and use it multiple times in another file.
YAML
Definitions that reference this definition: steps
template  string. Required as first property.
Reference to a template for this step.
parameters  template parameters.
Parameters used in a step template.
In the main pipeline:
YAML
In the included template:
YAML
YAML
steps:
- template: string # Required as first property. Reference to a template for this 
step.
  parameters: # Parameters used in a step template.
Properties
Examples
steps:
- template: string  # reference to template
  parameters: { string: any } # provided parameters
parameters: { string: any } # expected parameters
steps: [ script | bash | pwsh | powershell | checkout | task | templateReference ]
# File: steps/build.yml
YAML
See templates for more about working with templates.
Last updated on 03/02/2026
steps:
- script: npm install
- script: npm test
# File: azure-pipelines.yml
jobs:
- job: macOS
  pool:
    vmImage: macOS-latest
  steps:
  - template: steps/build.yml # Template reference
- job: Linux
  pool:
    vmImage: ubuntu-latest
  steps:
  - template: steps/build.yml # Template reference
- job: Windows
  pool:
    vmImage: windows-latest
  steps:
  - template: steps/build.yml # Template reference
  - script: sign              # Extra step on Windows only
See also
target definition
ﾃ
Summarize this article for me
Tasks run in an execution context, which is either the agent host or a container.
Definitions that reference this definition: steps.task, steps.script, steps.powershell, steps.pwsh,
steps.bash, steps.checkout, steps.download, steps.downloadBuild, steps.getPackage,
steps.publish, steps.reviewApp
Implementation
Description
target: string
Environment in which to run this step or task.
target: container, commands,
settableVariables
Configure step target with environment, and allowed list of
commands and variables.
An individual step may override its context by specifying a target , and optionally configure a
container, commands, and settable variables.
Specify a step target by name.
YAML
target  string.
Available options are the word host  to target the agent host plus any containers defined in the
pipeline.
Implementations
ﾉ
Expand table
Remarks
target: string
target: string # Environment in which to run this step or task.
target: container, commands, settableVariables
Configure step target using a container name, commands, and settable variables.
YAML
container  string.
Container to target (or 'host' for host machine).
commands  string.
Set of allowed logging commands ('any' or 'restricted'). any | restricted.
settableVariables  target.settableVariables.
Restrictions on which variables that can be set.
You don't need to configure all of these properties when configuring a step target. If not
specified, the default value for container  is host , the default value of commands  is any , and the
default value for settableVariables  allows all variables to be set by a step.
Azure Pipelines supports running jobs either in containers or on the agent host. Previously, an
entire job was set to one of those two targets. Now, individual steps (tasks or scripts) can run
on the target you choose. Steps may also target other containers, so a pipeline could run each
step in a specialized, purpose-built container.
Containers can act as isolation boundaries, preventing code from making unexpected changes
on the host machine. The way steps communicate with and access services from the agent is
target:
  container: string # Container to target (or 'host' for host machine).
  commands: any | restricted # Set of allowed logging commands ('any' or 
'restricted').
  settableVariables: none | [ string ] # Restrictions on which variables that can 
be set.
Properties
Remarks
Step targeting and command isolation
７ Note
This feature is in public preview. If you have any feedback or questions about this feature,
let us know in the Developer Community
.
not affected by isolating steps in a container. Therefore, we're also introducing a command
restriction mode which you can use with step targets. Setting commands  to restricted  will
restrict the services a step can request from the agent. It will no longer be able to attach logs,
upload artifacts, and certain other operations.
The following example shows running steps on the host in a job container, and in another
container.
YAML
Task types & usage - step target
Last updated on 03/02/2026
Examples
resources:
  containers:
  - container: python
    image: python:3.8
  - container: node
    image: node:13.2
jobs:
- job: example
  container: python
  steps:
  - script: echo Running in the job container
  - script: echo Running on the host
    target: host
  - script: echo Running in another container, in restricted commands mode
    target:
      container: node
      commands: restricted
See also
target.settableVariables definition
ﾃ
Summarize this article for me
Restrictions on which variables that can be set by a step.
Definitions that reference this definition: target
Implementation
Description
settableVariables: none
Disable a step from setting any variables.
settableVariables: string list
Restrict variable setting to a list of allowed variables.
You can disable setting all variables for a step, or restrict the settable variables to a list. If the
settableVariables  property is not set, the default allows all variables to be set by a step.
Disable a step from setting any variables.
YAML
settableVariables  string. Allowed values: none.
Disable a step from setting any variables.
YAML
Implementations
ﾉ
Expand table
Remarks
settableVariables: none
settableVariables: none # Disable a step from setting any variables.
Examples
steps:
- script: echo This is a step
Restrict a step from setting any variables not in the specified list.
YAML
Type
Description
string
Restrict variable setting to a list of allowed variables.
In the following example, the bash  step can only set the value of the sauce  variable. When the
pipeline runs, the secretSauce  variable is not set, and a warning is displayed on the pipeline
run page.
YAML
  target:
    settableVariables: none
settableVariables: string list
settableVariables: [ string ] # Restrict variable setting to a list of allowed 
variables.
List types
ﾉ
Expand table
Examples
steps:
  - bash: |
      echo "##vso[task.setvariable variable=sauce;]crushed tomatoes"
      echo "##vso[task.setvariable variable=secretSauce;]crushed tomatoes with 
garlic"
    target:
     settableVariables:
      - sauce
    name: SetVars
  - bash: 
      echo "Sauce is $(sauce)"
      echo "secretSauce is $(secretSauce)"
    name: OutputVars
See also
Configure settable variables for steps
Last updated on 03/02/2026
trigger definition
ﾃ
Summarize this article for me
A push trigger specifies which branches cause a continuous integration build to run.
Definitions that reference this definition: pipeline, resources.repositories.repository
Implementation
Description
trigger: none
Disable CI triggers.
trigger: string list
List of branches that trigger a run.
trigger: batch, branches, paths, tags
Full syntax for complete control.
For more information about using triggers with a specific repository type, see Supported
source repositories.
YAML pipelines are configured by default with a CI trigger on all branches, unless the Disable
implied YAML CI trigger setting is enabled. The Disable implied YAML CI trigger setting can be
configured at the organization level or at the project level. When the Disable implied YAML CI
trigger setting is enabled, CI triggers for YAML pipelines are not enabled if the YAML pipeline
doesn't have a trigger  section. The default value for Disable implied YAML CI trigger is
false .
There are three distinct syntax options for the trigger  keyword: a list of branches to include, a
way to disable CI triggers, and the full syntax for complete control.
If you specify an exclude  clause without an include  clause for branches , tags , or paths , it is
equivalent to specifying *  in the include  clause.
Disable CI triggers.
Implementations
ﾉ
Expand table
Remarks
trigger: none
YAML
trigger  string. Allowed values: none.
Disable CI triggers.
YAML
List of branches that trigger a run.
YAML
Type
Description
string
List of branches that trigger a run.
Pushing a commit to branches specified in the list trigger a run. In addition to specifying
branch names in the branches  lists, you can also configure triggers when a tag is pushed by
using the following format:
YAML
trigger: none # Disable CI triggers.
Examples
trigger: none # will disable CI builds entirely
trigger: string list
trigger: [ string ] # List of branches that trigger a run.
List types
ﾉ
Expand table
Remarks
trigger:
- refs/tags/{tagname}
For more information on tags, choose your repository type in Supported repositories, and go
to the CI triggers section.
YAML
Use the full syntax control for full control over the CI trigger.
YAML
batch  boolean.
Whether to batch changes per branch.
branches  includeExcludeFilters.
Branch names to include or exclude for triggering a run.
paths  includeExcludeFilters.
File paths to include or exclude for triggering a run.
tags  includeExcludeFilters.
Tag names to include or exclude for triggering a run.
Examples
trigger:
- main
- develop
trigger: batch, branches, paths, tags
trigger:
  batch: boolean # Whether to batch changes per branch.
  branches: # Branch names to include or exclude for triggering a run.
    include: [ string ] # List of items to include.
    exclude: [ string ] # List of items to exclude.
  paths: # File paths to include or exclude for triggering a run.
    include: [ string ] # List of items to include.
    exclude: [ string ] # List of items to exclude.
  tags: # Tag names to include or exclude for triggering a run.
    include: [ string ] # List of items to include.
    exclude: [ string ] # List of items to exclude.
Properties
If you have many team members uploading changes often, you may want to reduce the
number of runs you start. If you set batch  to true , when a pipeline is running, the system waits
until the run is completed, then starts another run with all changes that have not yet been built.
By default, batch  is false .
When specifying a branch, tag, or path, you may use an exact name or a wildcard. For more
information, see wildcards.
For more information, see Triggers - CI triggers and choose your repository type.
YAML
YAML
YAML
Remarks
） Important
batch  is not supported in repository resource triggers.
Examples
# Build every branch except for main
trigger:
  branches:
    include:
    - '*' # Must enclose in '' since * is a reserved YAML character
    exclude:
    - main
# specific branch build with batching
trigger:
  batch: true
  branches:
    include:
    - main
trigger:
  batch: true
  branches:
    include:
    - features/*
    exclude:
Learn more about triggers and how to specify them.
Last updated on 03/02/2026
    - features/experimental/*
  paths:
    exclude:
    - README.md
See also
variables definition
ﾃ
Summarize this article for me
Define variables using name/value pairs.
Definitions that reference this definition: pipeline, stages.stage, jobs.job, jobs.deployment
Implementation
Description
variables: string dictionary
Define variables using name/value pairs.
variables: variable list
Define variables by name, variable group, or in a template.
The variables  keyword uses two syntax forms: variable list and mapping (string dictionary).
In mapping syntax, all keys are variable names and their values are variable values. To use
variable templates, you must use list syntax. List syntax requires you to specify whether you're
mentioning a variable ( name ), a variable group ( group ), or a template ( template ).
You can't use list and mapping variables in the same variables section, but you can combine
name , group , and template  when using list syntax.
You can specify variables at the pipeline, stage, or job level.
Define variables using name/value pairs.
YAML
None.
Implementations
ﾉ
Expand table
Remarks
variables: string dictionary
variables:
  string: string # Name/value pairs
For a simple set of hard-coded variables, use this mapping syntax:
YAML
Variables defined at different scopes:
YAML
Define variables by name, variable group, or in a template.
YAML
Type
Description
variables.name
Define variables using name and full syntax.
Examples
variables: { string: string }
variables:      # pipeline-level
  MY_VAR: 'my value'
  ANOTHER_VAR: 'another value'
stages:
- stage: Build
  variables:    # stage-level
    STAGE_VAR: 'that happened'
  jobs:
  - job: FirstJob
    variables:  # job-level
      JOB_VAR: 'a job var'
    steps:
    - script: echo $(MY_VAR) $(STAGE_VAR) $(JOB_VAR)
variables: variable list
variables: [ name | group | template ] # Define variables by name, variable group, 
or in a template.
List types
ﾉ
Expand table
Type
Description
variables.group
Reference variables from a variable group.
variables.template
Define variables in a template.
To include variable groups, switch to this sequence syntax:
YAML
You can repeat name / value  pairs and group .
Variables can also be set as read only to enhance security.
YAML
You can also include variables from templates.
Sequence syntax:
YAML
Add & use variable groups
Define variables
Examples
variables:
- name: string  # name of a variable
  value: string # value of the variable
- group: string # name of a variable group
variables:
- name: myReadOnlyVar
  value: myValue
  readonly: true
variables:
- name: MY_VARIABLE           # hard-coded value
  value: some value
- group: my-variable-group-1  # variable group
- group: my-variable-group-2  # another variable group
See also
Last updated on 03/02/2026
variables.group definition
ﾃ
Summarize this article for me
Reference variables from a variable group.
YAML
Definitions that reference this definition: variables
group  string. Required as first property.
Variable group name.
YAML
Use a variable group and variables defined in the pipeline.
YAML
Add & use variable groups
Define variables
Last updated on 03/02/2026
variables:
- group: string # Required as first property. Variable group name.
Properties
Examples
variables:
- group: my-variable-group
variables:
- group: my-variable-group
- name: my-bare-variable
  value: 'value of my-bare-variable'
See also
variables.name definition
ﾃ
Summarize this article for me
Define variables using name and full syntax.
YAML
Definitions that reference this definition: variables
name  string. Required as first property.
Variable name.
value  string.
Variable value.
readonly  boolean.
Whether a YAML variable is read-only; default is false.
If you want to reference a variable group and define variables in the same variables section,
you must use define your variables using name and full syntax.
YAML
variables:
- name: string # Required as first property. Variable name.
  value: string # Variable value.
  readonly: boolean # Whether a YAML variable is read-only; default is false.
Properties
Remarks
Examples
variables:
- name: one
  value: initialValue
- name: two
  value: value2
See also
Add & use variable groups
Define variables
Last updated on 03/02/2026
variables.template definition
ﾃ
Summarize this article for me
You can define a set of variables in one file and use it multiple times in other files.
YAML
Definitions that reference this definition: variables
template  string. Required as first property.
Template file with variables.
parameters  template parameters.
Parameters to map into the template.
In this example, a set of variables is repeated across multiple pipelines. The variables are
specified only once.
YAML
YAML
variables:
- template: string # Required as first property. Template file with variables.
  parameters: # Parameters to map into the template.
Properties
Examples
# File: variables/build.yml
variables:
- name: vmImage
  value: vs2017-win2016
- name: arch
  value: x64
- name: config
  value: debug
# File: component-x-pipeline.yml
variables:
- template: variables/build.yml  # Template reference
pool:
  vmImage: ${{ variables.vmImage }}
YAML
Template usage reference
Template parameters
Define variables
Last updated on 03/02/2026
steps:
- script: build x ${{ variables.arch }} ${{ variables.config }}
# File: component-y-pipeline.yml
variables:
- template: variables/build.yml  # Template reference
pool:
  vmImage: ${{ variables.vmImage }}
steps:
- script: build y ${{ variables.arch }} ${{ variables.config }}
See also
boolean definition
ﾃ
Summarize this article for me
Represents a boolean value in a pipeline.
YAML
boolean  string. Allowed values: true | y | yes | on | false | n | no | off.
Azure pipelines uses any of the previous string values to represent a boolean value in a
pipeline.
Definitions that reference this definition: pipeline, trigger, pr, jobs.job.container, stages.stage,
schedules.cron, variables.name, mountReadOnly, steps.task, steps.script, steps.powershell,
steps.pwsh, steps.bash, steps.checkout, steps.download, steps.downloadBuild,
steps.getPackage, steps.publish, steps.reviewApp, resources.containers.container,
resources.containers.container.trigger, resources.pipelines.pipeline.trigger
Last updated on 03/02/2026
boolean: string # true | y | yes | on | false | n | no | off. 
７ Note
This definition is a supporting definition and is not intended for use directly in a pipeline.
This article provides the YAML syntax for this supporting type, but does not show usage
examples. For more information and examples for using this supporting type, see the
following Definitions that reference this definition articles.
deployHook definition
ﾃ
Summarize this article for me
Used to run steps that deploy your application.
YAML
Definitions that reference this definition: jobs.deployment.strategy.runOnce,
jobs.deployment.strategy.rolling, jobs.deployment.strategy.canary
steps  steps.
A list of steps to run.
pool  pool.
Pool where deploy steps will run.
Last updated on 03/02/2026
deployHook:
  steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
  pool: string | pool # Pool where deploy steps will run.
７ Note
This definition is a supporting definition and is not intended for use directly in a pipeline.
This article provides the YAML syntax for this supporting type, but does not show usage
examples. For more information and examples for using this supporting type, see the
following Definitions that reference this definition articles.
Properties
includeExcludeFilters definition
ﾃ
Summarize this article for me
Lists of items to include or exclude for trigger events.
YAML
Definitions that reference this definition: trigger, pr, schedules.cron
include  string list.
List of items to include.
yml
You can also specify the include  list on a single line using the following format.
yml
For example, to specify a list of branches to match in a pr  trigger, use the following syntax.
yml
includeExcludeFilters:
  include: [ string ] # List of items to include.
  exclude: [ string ] # List of items to exclude.
７ Note
This definition is a supporting definition and is not intended for use directly in a pipeline.
This article provides the YAML syntax for this supporting type, but does not show usage
examples. For more information and examples for using this supporting type, see the
following Definitions that reference this definition articles.
Properties
include:
- item1
- item2
include: [ item1, item2 ]
You can also specify the includes  list on a single line using the following format.
yml
exclude  string list.
List of items to exclude.
yml
You can also specify the exclude  list on a single line using the following format.
yml
The includeExcludeFilters  definition is a supporting definition and is not intended for use
directly in a pipeline; instead it is used to provide the structure of different properties in the
definitions listed at the top of the article.
For example, includeExcludeFilters  is the type that defines how the pr.branches  section is
structured. See the pr implementations Full syntax for complete control section for the pr
properties that use includeExcludeFilters .
YAML
pr:
  branches:
    include:
    - main
    - features/*
pr:
  branches:
    include: [ main, features/* ]
exclude:
- item1
- item2
exclude: [ item1, item2 ]
Remarks
pr:
  branches: # Branch names to include or exclude for triggering a run.
Last updated on 03/02/2026
    include: [ string ] # List of items to include.
    exclude: [ string ] # List of items to exclude.
includeExcludeStringFilters definition
ﾃ
Summarize this article for me
Items to include or exclude.
Definitions that reference this definition: resources.containers.container.trigger
Implementation
Description
includeExcludeStringFilters: string list
List of items to include.
includeExcludeStringFilters: include, exclude
Lists of items to include and exclude.
List of items to include.
YAML
Type
Description
string
List of items to include.
７ Note
This definition is a supporting definition and is not intended for use directly in a pipeline.
This article provides the YAML syntax for this supporting type, but does not show usage
examples. For more information and examples for using this supporting type, see the
following Definitions that reference this definition articles.
Implementations
ﾉ
Expand table
includeExcludeStringFilters: string list
includeExcludeStringFilters: [ string ] # List of items to include.
List types
ﾉ
Expand table
Lists of items to include and exclude.
YAML
include  string list.
exclude  string list.
Last updated on 03/02/2026
includeExcludeStringFilters: include, exclude
includeExcludeStringFilters:
  include: [ string ]
  exclude: [ string ]
Properties
mountReadOnly definition
ﾃ
Summarize this article for me
Volumes to mount read-only, the default is all false.
YAML
Definitions that reference this definition: jobs.job.container, resources.containers.container
work  boolean.
Mount the work directory as readonly.
externals  boolean.
Mount the externals directory as readonly.
tools  boolean.
Mount the tools directory as readonly.
tasks  boolean.
Mount the tasks directory as readonly.
Last updated on 03/02/2026
mountReadOnly:
  work: boolean # Mount the work directory as readonly.
  externals: boolean # Mount the externals directory as readonly.
  tools: boolean # Mount the tools directory as readonly.
  tasks: boolean # Mount the tasks directory as readonly.
７ Note
This definition is a supporting definition and is not intended for use directly in a pipeline.
This article provides the YAML syntax for this supporting type, but does not show usage
examples. For more information and examples for using this supporting type, see the
following Definitions that reference this definition articles.
Properties
onFailureHook definition
ﾃ
Summarize this article for me
Used to run steps for rollback actions or clean-up.
YAML
Definitions that reference this definition: onSuccessOrFailureHook
steps  steps.
A list of steps to run.
pool  pool.
Pool where post on failure steps will run.
Last updated on 03/02/2026
onFailureHook:
  steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
  pool: string | pool # Pool where post on failure steps will run.
７ Note
This definition is a supporting definition and is not intended for use directly in a pipeline.
This article provides the YAML syntax for this supporting type, but does not show usage
examples. For more information and examples for using this supporting type, see the
following Definitions that reference this definition articles.
Properties
onSuccessHook definition
ﾃ
Summarize this article for me
Used to run steps for rollback actions or clean-up.
YAML
Definitions that reference this definition: onSuccessOrFailureHook
steps  steps.
A list of steps to run.
pool  pool.
Pool where on success steps will run.
Last updated on 03/02/2026
onSuccessHook:
  steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
  pool: string | pool # Pool where on success steps will run.
７ Note
This definition is a supporting definition and is not intended for use directly in a pipeline.
This article provides the YAML syntax for this supporting type, but does not show usage
examples. For more information and examples for using this supporting type, see the
following Definitions that reference this definition articles.
Properties
onSuccessOrFailureHook definition
ﾃ
Summarize this article for me
Used to run steps for rollback actions or clean-up.
YAML
Definitions that reference this definition: jobs.deployment.strategy.runOnce,
jobs.deployment.strategy.rolling, jobs.deployment.strategy.canary
failure  onFailureHook.
Runs on failure of any step.
success  onSuccessHook.
Runs on success of all of the steps.
Last updated on 03/02/2026
onSuccessOrFailureHook:
  failure: # Runs on failure of any step.
    steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
    pool: string | pool # Pool where post on failure steps will run.
  success: # Runs on success of all of the steps.
    steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
    pool: string | pool # Pool where on success steps will run.
７ Note
This definition is a supporting definition and is not intended for use directly in a pipeline.
This article provides the YAML syntax for this supporting type, but does not show usage
examples. For more information and examples for using this supporting type, see the
following Definitions that reference this definition articles.
Properties
postRouteTrafficHook definition
ﾃ
Summarize this article for me
Used to run the steps after the traffic is routed. Typically, these tasks monitor the health of the
updated version for defined interval.
YAML
Definitions that reference this definition: jobs.deployment.strategy.runOnce,
jobs.deployment.strategy.rolling, jobs.deployment.strategy.canary
steps  steps.
A list of steps to run.
pool  pool.
Pool where post route traffic steps will run.
Last updated on 03/02/2026
postRouteTrafficHook:
  steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
  pool: string | pool # Pool where post route traffic steps will run.
７ Note
This definition is a supporting definition and is not intended for use directly in a pipeline.
This article provides the YAML syntax for this supporting type, but does not show usage
examples. For more information and examples for using this supporting type, see the
following Definitions that reference this definition articles.
Properties
preDeployHook definition
ﾃ
Summarize this article for me
Used to run steps that initialize resources before application deployment starts.
YAML
Definitions that reference this definition: jobs.deployment.strategy.runOnce,
jobs.deployment.strategy.rolling, jobs.deployment.strategy.canary
steps  steps.
A list of steps to run.
pool  pool.
Pool where pre deploy steps will run.
Last updated on 03/02/2026
preDeployHook:
  steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
  pool: string | pool # Pool where pre deploy steps will run.
７ Note
This definition is a supporting definition and is not intended for use directly in a pipeline.
This article provides the YAML syntax for this supporting type, but does not show usage
examples. For more information and examples for using this supporting type, see the
following Definitions that reference this definition articles.
Properties
routeTrafficHook definition
ﾃ
Summarize this article for me
Used to run steps that serve the traffic to the updated version.
YAML
Definitions that reference this definition: jobs.deployment.strategy.runOnce,
jobs.deployment.strategy.rolling, jobs.deployment.strategy.canary
steps  steps.
A list of steps to run.
pool  pool.
Pool where route traffic steps will run.
Last updated on 03/02/2026
routeTrafficHook:
  steps: [ task | script | powershell | pwsh | bash | checkout | download | 
downloadBuild | getPackage | publish | template | reviewApp ] # A list of steps to 
run.
  pool: string | pool # Pool where route traffic steps will run.
７ Note
This definition is a supporting definition and is not intended for use directly in a pipeline.
This article provides the YAML syntax for this supporting type, but does not show usage
examples. For more information and examples for using this supporting type, see the
following Definitions that reference this definition articles.
Properties
workspace definition
ﾃ
Summarize this article for me
Workspace options on the agent.
YAML
Definitions that reference this definition: pipeline, jobs.job, jobs.deployment
clean  string.
Which parts of the workspace should be cleaned before the run. Valid values are outputs ,
resources , or all .
For more information about workspaces, including clean options, see the workspace topic in
Jobs.
Last updated on 03/02/2026
workspace:
  clean: outputs | resources | all # What to clean up before the job runs.
７ Note
This definition is a supporting definition and is not intended for use directly in a pipeline.
This article provides the YAML syntax for this supporting type, but does not show usage
examples. For more information and examples for using this supporting type, see the
following Definitions that reference this definition articles.
Properties
