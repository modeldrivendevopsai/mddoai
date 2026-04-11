/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage
 * @generated
 */
public interface AzuredevopsMMFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AzuredevopsMMFactory eINSTANCE = com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMFactoryImpl
			.init();

	/**
	 * Returns a new object of class '<em>Pipeline</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Pipeline</em>'.
	 * @generated
	 */
	Pipeline createPipeline();

	/**
	 * Returns a new object of class '<em>Extends</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Extends</em>'.
	 * @generated
	 */
	Extends createExtends();

	/**
	 * Returns a new object of class '<em>Stage</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Stage</em>'.
	 * @generated
	 */
	Stage createStage();

	/**
	 * Returns a new object of class '<em>Stage Template</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Stage Template</em>'.
	 * @generated
	 */
	StageTemplate createStageTemplate();

	/**
	 * Returns a new object of class '<em>Job</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Job</em>'.
	 * @generated
	 */
	Job createJob();

	/**
	 * Returns a new object of class '<em>Deployment Job</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Deployment Job</em>'.
	 * @generated
	 */
	DeploymentJob createDeploymentJob();

	/**
	 * Returns a new object of class '<em>Job Template</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Job Template</em>'.
	 * @generated
	 */
	JobTemplate createJobTemplate();

	/**
	 * Returns a new object of class '<em>Environment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Environment</em>'.
	 * @generated
	 */
	Environment createEnvironment();

	/**
	 * Returns a new object of class '<em>Deployment Strategy</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Deployment Strategy</em>'.
	 * @generated
	 */
	DeploymentStrategy createDeploymentStrategy();

	/**
	 * Returns a new object of class '<em>Run Once Strategy</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Run Once Strategy</em>'.
	 * @generated
	 */
	RunOnceStrategy createRunOnceStrategy();

	/**
	 * Returns a new object of class '<em>Rolling Strategy</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Rolling Strategy</em>'.
	 * @generated
	 */
	RollingStrategy createRollingStrategy();

	/**
	 * Returns a new object of class '<em>Canary Strategy</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Canary Strategy</em>'.
	 * @generated
	 */
	CanaryStrategy createCanaryStrategy();

	/**
	 * Returns a new object of class '<em>Deploy Hook</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Deploy Hook</em>'.
	 * @generated
	 */
	DeployHook createDeployHook();

	/**
	 * Returns a new object of class '<em>On Success Or Failure Hook</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>On Success Or Failure Hook</em>'.
	 * @generated
	 */
	OnSuccessOrFailureHook createOnSuccessOrFailureHook();

	/**
	 * Returns a new object of class '<em>Job Strategy</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Job Strategy</em>'.
	 * @generated
	 */
	JobStrategy createJobStrategy();

	/**
	 * Returns a new object of class '<em>Job Uses</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Job Uses</em>'.
	 * @generated
	 */
	JobUses createJobUses();

	/**
	 * Returns a new object of class '<em>Pool</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Pool</em>'.
	 * @generated
	 */
	Pool createPool();

	/**
	 * Returns a new object of class '<em>Workspace</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Workspace</em>'.
	 * @generated
	 */
	Workspace createWorkspace();

	/**
	 * Returns a new object of class '<em>Container Alias</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Container Alias</em>'.
	 * @generated
	 */
	ContainerAlias createContainerAlias();

	/**
	 * Returns a new object of class '<em>Container Image</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Container Image</em>'.
	 * @generated
	 */
	ContainerImage createContainerImage();

	/**
	 * Returns a new object of class '<em>Mount Read Only</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Mount Read Only</em>'.
	 * @generated
	 */
	MountReadOnly createMountReadOnly();

	/**
	 * Returns a new object of class '<em>Task Step</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Task Step</em>'.
	 * @generated
	 */
	TaskStep createTaskStep();

	/**
	 * Returns a new object of class '<em>Script Step</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Script Step</em>'.
	 * @generated
	 */
	ScriptStep createScriptStep();

	/**
	 * Returns a new object of class '<em>Bash Step</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Bash Step</em>'.
	 * @generated
	 */
	BashStep createBashStep();

	/**
	 * Returns a new object of class '<em>Powershell Step</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Powershell Step</em>'.
	 * @generated
	 */
	PowershellStep createPowershellStep();

	/**
	 * Returns a new object of class '<em>Pwsh Step</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Pwsh Step</em>'.
	 * @generated
	 */
	PwshStep createPwshStep();

	/**
	 * Returns a new object of class '<em>Checkout Step</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Checkout Step</em>'.
	 * @generated
	 */
	CheckoutStep createCheckoutStep();

	/**
	 * Returns a new object of class '<em>Download Step</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Download Step</em>'.
	 * @generated
	 */
	DownloadStep createDownloadStep();

	/**
	 * Returns a new object of class '<em>Download Build Step</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Download Build Step</em>'.
	 * @generated
	 */
	DownloadBuildStep createDownloadBuildStep();

	/**
	 * Returns a new object of class '<em>Get Package Step</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Get Package Step</em>'.
	 * @generated
	 */
	GetPackageStep createGetPackageStep();

	/**
	 * Returns a new object of class '<em>Publish Step</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Publish Step</em>'.
	 * @generated
	 */
	PublishStep createPublishStep();

	/**
	 * Returns a new object of class '<em>Review App Step</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Review App Step</em>'.
	 * @generated
	 */
	ReviewAppStep createReviewAppStep();

	/**
	 * Returns a new object of class '<em>Step Template</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Step Template</em>'.
	 * @generated
	 */
	StepTemplate createStepTemplate();

	/**
	 * Returns a new object of class '<em>Step Target</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Step Target</em>'.
	 * @generated
	 */
	StepTarget createStepTarget();

	/**
	 * Returns a new object of class '<em>Settable Variables</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Settable Variables</em>'.
	 * @generated
	 */
	SettableVariables createSettableVariables();

	/**
	 * Returns a new object of class '<em>Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Trigger</em>'.
	 * @generated
	 */
	Trigger createTrigger();

	/**
	 * Returns a new object of class '<em>Pr Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Pr Trigger</em>'.
	 * @generated
	 */
	PrTrigger createPrTrigger();

	/**
	 * Returns a new object of class '<em>Cron Schedule</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Cron Schedule</em>'.
	 * @generated
	 */
	CronSchedule createCronSchedule();

	/**
	 * Returns a new object of class '<em>Include Exclude Filters</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Include Exclude Filters</em>'.
	 * @generated
	 */
	IncludeExcludeFilters createIncludeExcludeFilters();

	/**
	 * Returns a new object of class '<em>Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Parameter</em>'.
	 * @generated
	 */
	Parameter createParameter();

	/**
	 * Returns a new object of class '<em>Variable Name</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Variable Name</em>'.
	 * @generated
	 */
	VariableName createVariableName();

	/**
	 * Returns a new object of class '<em>Variable Group</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Variable Group</em>'.
	 * @generated
	 */
	VariableGroup createVariableGroup();

	/**
	 * Returns a new object of class '<em>Variable Template</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Variable Template</em>'.
	 * @generated
	 */
	VariableTemplate createVariableTemplate();

	/**
	 * Returns a new object of class '<em>Resources</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resources</em>'.
	 * @generated
	 */
	Resources createResources();

	/**
	 * Returns a new object of class '<em>Build Resource</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Build Resource</em>'.
	 * @generated
	 */
	BuildResource createBuildResource();

	/**
	 * Returns a new object of class '<em>Container Resource</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Container Resource</em>'.
	 * @generated
	 */
	ContainerResource createContainerResource();

	/**
	 * Returns a new object of class '<em>Container Resource Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Container Resource Trigger</em>'.
	 * @generated
	 */
	ContainerResourceTrigger createContainerResourceTrigger();

	/**
	 * Returns a new object of class '<em>Pipeline Resource</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Pipeline Resource</em>'.
	 * @generated
	 */
	PipelineResource createPipelineResource();

	/**
	 * Returns a new object of class '<em>Pipeline Resource Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Pipeline Resource Trigger</em>'.
	 * @generated
	 */
	PipelineResourceTrigger createPipelineResourceTrigger();

	/**
	 * Returns a new object of class '<em>Repository Resource</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Repository Resource</em>'.
	 * @generated
	 */
	RepositoryResource createRepositoryResource();

	/**
	 * Returns a new object of class '<em>Webhook Resource</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Webhook Resource</em>'.
	 * @generated
	 */
	WebhookResource createWebhookResource();

	/**
	 * Returns a new object of class '<em>Webhook Filter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Webhook Filter</em>'.
	 * @generated
	 */
	WebhookFilter createWebhookFilter();

	/**
	 * Returns a new object of class '<em>Package Resource</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Package Resource</em>'.
	 * @generated
	 */
	PackageResource createPackageResource();

	/**
	 * Returns a new object of class '<em>Template Context</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Template Context</em>'.
	 * @generated
	 */
	TemplateContext createTemplateContext();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	AzuredevopsMMPackage getAzuredevopsMMPackage();

} //AzuredevopsMMFactory
