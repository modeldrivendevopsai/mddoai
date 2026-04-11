/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.*;

import java.util.Map;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AzuredevopsMMFactoryImpl extends EFactoryImpl implements AzuredevopsMMFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AzuredevopsMMFactory init() {
		try {
			AzuredevopsMMFactory theAzuredevopsMMFactory = (AzuredevopsMMFactory) EPackage.Registry.INSTANCE
					.getEFactory(AzuredevopsMMPackage.eNS_URI);
			if (theAzuredevopsMMFactory != null) {
				return theAzuredevopsMMFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new AzuredevopsMMFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AzuredevopsMMFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case AzuredevopsMMPackage.PIPELINE:
			return createPipeline();
		case AzuredevopsMMPackage.EXTENDS:
			return createExtends();
		case AzuredevopsMMPackage.STAGE:
			return createStage();
		case AzuredevopsMMPackage.STAGE_TEMPLATE:
			return createStageTemplate();
		case AzuredevopsMMPackage.JOB:
			return createJob();
		case AzuredevopsMMPackage.DEPLOYMENT_JOB:
			return createDeploymentJob();
		case AzuredevopsMMPackage.JOB_TEMPLATE:
			return createJobTemplate();
		case AzuredevopsMMPackage.ENVIRONMENT:
			return createEnvironment();
		case AzuredevopsMMPackage.DEPLOYMENT_STRATEGY:
			return createDeploymentStrategy();
		case AzuredevopsMMPackage.RUN_ONCE_STRATEGY:
			return createRunOnceStrategy();
		case AzuredevopsMMPackage.ROLLING_STRATEGY:
			return createRollingStrategy();
		case AzuredevopsMMPackage.CANARY_STRATEGY:
			return createCanaryStrategy();
		case AzuredevopsMMPackage.DEPLOY_HOOK:
			return createDeployHook();
		case AzuredevopsMMPackage.ON_SUCCESS_OR_FAILURE_HOOK:
			return createOnSuccessOrFailureHook();
		case AzuredevopsMMPackage.JOB_STRATEGY:
			return createJobStrategy();
		case AzuredevopsMMPackage.MATRIX_ENTRY:
			return (EObject) createMatrixEntry();
		case AzuredevopsMMPackage.MATRIX_VARIABLE:
			return (EObject) createMatrixVariable();
		case AzuredevopsMMPackage.JOB_USES:
			return createJobUses();
		case AzuredevopsMMPackage.POOL:
			return createPool();
		case AzuredevopsMMPackage.WORKSPACE:
			return createWorkspace();
		case AzuredevopsMMPackage.CONTAINER_ALIAS:
			return createContainerAlias();
		case AzuredevopsMMPackage.CONTAINER_IMAGE:
			return createContainerImage();
		case AzuredevopsMMPackage.MOUNT_READ_ONLY:
			return createMountReadOnly();
		case AzuredevopsMMPackage.SERVICE_ENTRY:
			return (EObject) createServiceEntry();
		case AzuredevopsMMPackage.TASK_STEP:
			return createTaskStep();
		case AzuredevopsMMPackage.SCRIPT_STEP:
			return createScriptStep();
		case AzuredevopsMMPackage.BASH_STEP:
			return createBashStep();
		case AzuredevopsMMPackage.POWERSHELL_STEP:
			return createPowershellStep();
		case AzuredevopsMMPackage.PWSH_STEP:
			return createPwshStep();
		case AzuredevopsMMPackage.CHECKOUT_STEP:
			return createCheckoutStep();
		case AzuredevopsMMPackage.DOWNLOAD_STEP:
			return createDownloadStep();
		case AzuredevopsMMPackage.DOWNLOAD_BUILD_STEP:
			return createDownloadBuildStep();
		case AzuredevopsMMPackage.GET_PACKAGE_STEP:
			return createGetPackageStep();
		case AzuredevopsMMPackage.PUBLISH_STEP:
			return createPublishStep();
		case AzuredevopsMMPackage.REVIEW_APP_STEP:
			return createReviewAppStep();
		case AzuredevopsMMPackage.STEP_TEMPLATE:
			return createStepTemplate();
		case AzuredevopsMMPackage.STEP_TARGET:
			return createStepTarget();
		case AzuredevopsMMPackage.SETTABLE_VARIABLES:
			return createSettableVariables();
		case AzuredevopsMMPackage.TRIGGER:
			return createTrigger();
		case AzuredevopsMMPackage.PR_TRIGGER:
			return createPrTrigger();
		case AzuredevopsMMPackage.CRON_SCHEDULE:
			return createCronSchedule();
		case AzuredevopsMMPackage.INCLUDE_EXCLUDE_FILTERS:
			return createIncludeExcludeFilters();
		case AzuredevopsMMPackage.PARAMETER:
			return createParameter();
		case AzuredevopsMMPackage.TEMPLATE_PARAMETER_ASSIGNMENT:
			return (EObject) createTemplateParameterAssignment();
		case AzuredevopsMMPackage.VARIABLE_NAME:
			return createVariableName();
		case AzuredevopsMMPackage.VARIABLE_GROUP:
			return createVariableGroup();
		case AzuredevopsMMPackage.VARIABLE_TEMPLATE:
			return createVariableTemplate();
		case AzuredevopsMMPackage.RESOURCES:
			return createResources();
		case AzuredevopsMMPackage.BUILD_RESOURCE:
			return createBuildResource();
		case AzuredevopsMMPackage.CONTAINER_RESOURCE:
			return createContainerResource();
		case AzuredevopsMMPackage.CONTAINER_RESOURCE_TRIGGER:
			return createContainerResourceTrigger();
		case AzuredevopsMMPackage.PIPELINE_RESOURCE:
			return createPipelineResource();
		case AzuredevopsMMPackage.PIPELINE_RESOURCE_TRIGGER:
			return createPipelineResourceTrigger();
		case AzuredevopsMMPackage.REPOSITORY_RESOURCE:
			return createRepositoryResource();
		case AzuredevopsMMPackage.WEBHOOK_RESOURCE:
			return createWebhookResource();
		case AzuredevopsMMPackage.WEBHOOK_FILTER:
			return createWebhookFilter();
		case AzuredevopsMMPackage.PACKAGE_RESOURCE:
			return createPackageResource();
		case AzuredevopsMMPackage.ENV_ENTRY:
			return (EObject) createEnvEntry();
		case AzuredevopsMMPackage.INPUT_ENTRY:
			return (EObject) createInputEntry();
		case AzuredevopsMMPackage.TEMPLATE_CONTEXT:
			return createTemplateContext();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
		case AzuredevopsMMPackage.LOCK_BEHAVIOR:
			return createLOCK_BEHAVIORFromString(eDataType, initialValue);
		case AzuredevopsMMPackage.STAGE_TRIGGER:
			return createSTAGE_TRIGGERFromString(eDataType, initialValue);
		case AzuredevopsMMPackage.WORKSPACE_CLEAN:
			return createWORKSPACE_CLEANFromString(eDataType, initialValue);
		case AzuredevopsMMPackage.PARAMETER_TYPE:
			return createPARAMETER_TYPEFromString(eDataType, initialValue);
		case AzuredevopsMMPackage.REPOSITORY_TYPE:
			return createREPOSITORY_TYPEFromString(eDataType, initialValue);
		case AzuredevopsMMPackage.TARGET_COMMANDS:
			return createTARGET_COMMANDSFromString(eDataType, initialValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
		case AzuredevopsMMPackage.LOCK_BEHAVIOR:
			return convertLOCK_BEHAVIORToString(eDataType, instanceValue);
		case AzuredevopsMMPackage.STAGE_TRIGGER:
			return convertSTAGE_TRIGGERToString(eDataType, instanceValue);
		case AzuredevopsMMPackage.WORKSPACE_CLEAN:
			return convertWORKSPACE_CLEANToString(eDataType, instanceValue);
		case AzuredevopsMMPackage.PARAMETER_TYPE:
			return convertPARAMETER_TYPEToString(eDataType, instanceValue);
		case AzuredevopsMMPackage.REPOSITORY_TYPE:
			return convertREPOSITORY_TYPEToString(eDataType, instanceValue);
		case AzuredevopsMMPackage.TARGET_COMMANDS:
			return convertTARGET_COMMANDSToString(eDataType, instanceValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Pipeline createPipeline() {
		PipelineImpl pipeline = new PipelineImpl();
		return pipeline;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Extends createExtends() {
		ExtendsImpl extends_ = new ExtendsImpl();
		return extends_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Stage createStage() {
		StageImpl stage = new StageImpl();
		return stage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StageTemplate createStageTemplate() {
		StageTemplateImpl stageTemplate = new StageTemplateImpl();
		return stageTemplate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Job createJob() {
		JobImpl job = new JobImpl();
		return job;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DeploymentJob createDeploymentJob() {
		DeploymentJobImpl deploymentJob = new DeploymentJobImpl();
		return deploymentJob;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public JobTemplate createJobTemplate() {
		JobTemplateImpl jobTemplate = new JobTemplateImpl();
		return jobTemplate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Environment createEnvironment() {
		EnvironmentImpl environment = new EnvironmentImpl();
		return environment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DeploymentStrategy createDeploymentStrategy() {
		DeploymentStrategyImpl deploymentStrategy = new DeploymentStrategyImpl();
		return deploymentStrategy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RunOnceStrategy createRunOnceStrategy() {
		RunOnceStrategyImpl runOnceStrategy = new RunOnceStrategyImpl();
		return runOnceStrategy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RollingStrategy createRollingStrategy() {
		RollingStrategyImpl rollingStrategy = new RollingStrategyImpl();
		return rollingStrategy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CanaryStrategy createCanaryStrategy() {
		CanaryStrategyImpl canaryStrategy = new CanaryStrategyImpl();
		return canaryStrategy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DeployHook createDeployHook() {
		DeployHookImpl deployHook = new DeployHookImpl();
		return deployHook;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OnSuccessOrFailureHook createOnSuccessOrFailureHook() {
		OnSuccessOrFailureHookImpl onSuccessOrFailureHook = new OnSuccessOrFailureHookImpl();
		return onSuccessOrFailureHook;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public JobStrategy createJobStrategy() {
		JobStrategyImpl jobStrategy = new JobStrategyImpl();
		return jobStrategy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, EMap<String, String>> createMatrixEntry() {
		MatrixEntryImpl matrixEntry = new MatrixEntryImpl();
		return matrixEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, String> createMatrixVariable() {
		MatrixVariableImpl matrixVariable = new MatrixVariableImpl();
		return matrixVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public JobUses createJobUses() {
		JobUsesImpl jobUses = new JobUsesImpl();
		return jobUses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Pool createPool() {
		PoolImpl pool = new PoolImpl();
		return pool;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Workspace createWorkspace() {
		WorkspaceImpl workspace = new WorkspaceImpl();
		return workspace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ContainerAlias createContainerAlias() {
		ContainerAliasImpl containerAlias = new ContainerAliasImpl();
		return containerAlias;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ContainerImage createContainerImage() {
		ContainerImageImpl containerImage = new ContainerImageImpl();
		return containerImage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MountReadOnly createMountReadOnly() {
		MountReadOnlyImpl mountReadOnly = new MountReadOnlyImpl();
		return mountReadOnly;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, String> createServiceEntry() {
		ServiceEntryImpl serviceEntry = new ServiceEntryImpl();
		return serviceEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TaskStep createTaskStep() {
		TaskStepImpl taskStep = new TaskStepImpl();
		return taskStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScriptStep createScriptStep() {
		ScriptStepImpl scriptStep = new ScriptStepImpl();
		return scriptStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BashStep createBashStep() {
		BashStepImpl bashStep = new BashStepImpl();
		return bashStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PowershellStep createPowershellStep() {
		PowershellStepImpl powershellStep = new PowershellStepImpl();
		return powershellStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PwshStep createPwshStep() {
		PwshStepImpl pwshStep = new PwshStepImpl();
		return pwshStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CheckoutStep createCheckoutStep() {
		CheckoutStepImpl checkoutStep = new CheckoutStepImpl();
		return checkoutStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DownloadStep createDownloadStep() {
		DownloadStepImpl downloadStep = new DownloadStepImpl();
		return downloadStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DownloadBuildStep createDownloadBuildStep() {
		DownloadBuildStepImpl downloadBuildStep = new DownloadBuildStepImpl();
		return downloadBuildStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GetPackageStep createGetPackageStep() {
		GetPackageStepImpl getPackageStep = new GetPackageStepImpl();
		return getPackageStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PublishStep createPublishStep() {
		PublishStepImpl publishStep = new PublishStepImpl();
		return publishStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ReviewAppStep createReviewAppStep() {
		ReviewAppStepImpl reviewAppStep = new ReviewAppStepImpl();
		return reviewAppStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StepTemplate createStepTemplate() {
		StepTemplateImpl stepTemplate = new StepTemplateImpl();
		return stepTemplate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StepTarget createStepTarget() {
		StepTargetImpl stepTarget = new StepTargetImpl();
		return stepTarget;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SettableVariables createSettableVariables() {
		SettableVariablesImpl settableVariables = new SettableVariablesImpl();
		return settableVariables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Trigger createTrigger() {
		TriggerImpl trigger = new TriggerImpl();
		return trigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PrTrigger createPrTrigger() {
		PrTriggerImpl prTrigger = new PrTriggerImpl();
		return prTrigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CronSchedule createCronSchedule() {
		CronScheduleImpl cronSchedule = new CronScheduleImpl();
		return cronSchedule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IncludeExcludeFilters createIncludeExcludeFilters() {
		IncludeExcludeFiltersImpl includeExcludeFilters = new IncludeExcludeFiltersImpl();
		return includeExcludeFilters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Parameter createParameter() {
		ParameterImpl parameter = new ParameterImpl();
		return parameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, String> createTemplateParameterAssignment() {
		TemplateParameterAssignmentImpl templateParameterAssignment = new TemplateParameterAssignmentImpl();
		return templateParameterAssignment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public VariableName createVariableName() {
		VariableNameImpl variableName = new VariableNameImpl();
		return variableName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public VariableGroup createVariableGroup() {
		VariableGroupImpl variableGroup = new VariableGroupImpl();
		return variableGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public VariableTemplate createVariableTemplate() {
		VariableTemplateImpl variableTemplate = new VariableTemplateImpl();
		return variableTemplate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Resources createResources() {
		ResourcesImpl resources = new ResourcesImpl();
		return resources;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BuildResource createBuildResource() {
		BuildResourceImpl buildResource = new BuildResourceImpl();
		return buildResource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ContainerResource createContainerResource() {
		ContainerResourceImpl containerResource = new ContainerResourceImpl();
		return containerResource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ContainerResourceTrigger createContainerResourceTrigger() {
		ContainerResourceTriggerImpl containerResourceTrigger = new ContainerResourceTriggerImpl();
		return containerResourceTrigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PipelineResource createPipelineResource() {
		PipelineResourceImpl pipelineResource = new PipelineResourceImpl();
		return pipelineResource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PipelineResourceTrigger createPipelineResourceTrigger() {
		PipelineResourceTriggerImpl pipelineResourceTrigger = new PipelineResourceTriggerImpl();
		return pipelineResourceTrigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RepositoryResource createRepositoryResource() {
		RepositoryResourceImpl repositoryResource = new RepositoryResourceImpl();
		return repositoryResource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WebhookResource createWebhookResource() {
		WebhookResourceImpl webhookResource = new WebhookResourceImpl();
		return webhookResource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WebhookFilter createWebhookFilter() {
		WebhookFilterImpl webhookFilter = new WebhookFilterImpl();
		return webhookFilter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PackageResource createPackageResource() {
		PackageResourceImpl packageResource = new PackageResourceImpl();
		return packageResource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, String> createEnvEntry() {
		EnvEntryImpl envEntry = new EnvEntryImpl();
		return envEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, String> createInputEntry() {
		InputEntryImpl inputEntry = new InputEntryImpl();
		return inputEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TemplateContext createTemplateContext() {
		TemplateContextImpl templateContext = new TemplateContextImpl();
		return templateContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LOCK_BEHAVIOR createLOCK_BEHAVIORFromString(EDataType eDataType, String initialValue) {
		LOCK_BEHAVIOR result = LOCK_BEHAVIOR.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException(
					"The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLOCK_BEHAVIORToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public STAGE_TRIGGER createSTAGE_TRIGGERFromString(EDataType eDataType, String initialValue) {
		STAGE_TRIGGER result = STAGE_TRIGGER.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException(
					"The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSTAGE_TRIGGERToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WORKSPACE_CLEAN createWORKSPACE_CLEANFromString(EDataType eDataType, String initialValue) {
		WORKSPACE_CLEAN result = WORKSPACE_CLEAN.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException(
					"The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertWORKSPACE_CLEANToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PARAMETER_TYPE createPARAMETER_TYPEFromString(EDataType eDataType, String initialValue) {
		PARAMETER_TYPE result = PARAMETER_TYPE.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException(
					"The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPARAMETER_TYPEToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public REPOSITORY_TYPE createREPOSITORY_TYPEFromString(EDataType eDataType, String initialValue) {
		REPOSITORY_TYPE result = REPOSITORY_TYPE.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException(
					"The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertREPOSITORY_TYPEToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TARGET_COMMANDS createTARGET_COMMANDSFromString(EDataType eDataType, String initialValue) {
		TARGET_COMMANDS result = TARGET_COMMANDS.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException(
					"The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTARGET_COMMANDSToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AzuredevopsMMPackage getAzuredevopsMMPackage() {
		return (AzuredevopsMMPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static AzuredevopsMMPackage getPackage() {
		return AzuredevopsMMPackage.eINSTANCE;
	}

} //AzuredevopsMMFactoryImpl
