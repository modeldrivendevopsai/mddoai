/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.util;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.*;

import java.util.Map;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage
 * @generated
 */
public class AzuredevopsMMSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static AzuredevopsMMPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AzuredevopsMMSwitch() {
		if (modelPackage == null) {
			modelPackage = AzuredevopsMMPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
		case AzuredevopsMMPackage.PIPELINE: {
			Pipeline pipeline = (Pipeline) theEObject;
			T result = casePipeline(pipeline);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.EXTENDS: {
			Extends extends_ = (Extends) theEObject;
			T result = caseExtends(extends_);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.STAGE_ELEMENT: {
			StageElement stageElement = (StageElement) theEObject;
			T result = caseStageElement(stageElement);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.STAGE: {
			Stage stage = (Stage) theEObject;
			T result = caseStage(stage);
			if (result == null)
				result = caseStageElement(stage);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.STAGE_TEMPLATE: {
			StageTemplate stageTemplate = (StageTemplate) theEObject;
			T result = caseStageTemplate(stageTemplate);
			if (result == null)
				result = caseStageElement(stageTemplate);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.JOB_ELEMENT: {
			JobElement jobElement = (JobElement) theEObject;
			T result = caseJobElement(jobElement);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.JOB: {
			Job job = (Job) theEObject;
			T result = caseJob(job);
			if (result == null)
				result = caseJobElement(job);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.DEPLOYMENT_JOB: {
			DeploymentJob deploymentJob = (DeploymentJob) theEObject;
			T result = caseDeploymentJob(deploymentJob);
			if (result == null)
				result = caseJobElement(deploymentJob);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.JOB_TEMPLATE: {
			JobTemplate jobTemplate = (JobTemplate) theEObject;
			T result = caseJobTemplate(jobTemplate);
			if (result == null)
				result = caseJobElement(jobTemplate);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.ENVIRONMENT: {
			Environment environment = (Environment) theEObject;
			T result = caseEnvironment(environment);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.DEPLOYMENT_STRATEGY: {
			DeploymentStrategy deploymentStrategy = (DeploymentStrategy) theEObject;
			T result = caseDeploymentStrategy(deploymentStrategy);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.RUN_ONCE_STRATEGY: {
			RunOnceStrategy runOnceStrategy = (RunOnceStrategy) theEObject;
			T result = caseRunOnceStrategy(runOnceStrategy);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.ROLLING_STRATEGY: {
			RollingStrategy rollingStrategy = (RollingStrategy) theEObject;
			T result = caseRollingStrategy(rollingStrategy);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.CANARY_STRATEGY: {
			CanaryStrategy canaryStrategy = (CanaryStrategy) theEObject;
			T result = caseCanaryStrategy(canaryStrategy);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.DEPLOY_HOOK: {
			DeployHook deployHook = (DeployHook) theEObject;
			T result = caseDeployHook(deployHook);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.ON_SUCCESS_OR_FAILURE_HOOK: {
			OnSuccessOrFailureHook onSuccessOrFailureHook = (OnSuccessOrFailureHook) theEObject;
			T result = caseOnSuccessOrFailureHook(onSuccessOrFailureHook);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.JOB_STRATEGY: {
			JobStrategy jobStrategy = (JobStrategy) theEObject;
			T result = caseJobStrategy(jobStrategy);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.MATRIX_ENTRY: {
			@SuppressWarnings("unchecked")
			Map.Entry<String, EMap<String, String>> matrixEntry = (Map.Entry<String, EMap<String, String>>) theEObject;
			T result = caseMatrixEntry(matrixEntry);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.MATRIX_VARIABLE: {
			@SuppressWarnings("unchecked")
			Map.Entry<String, String> matrixVariable = (Map.Entry<String, String>) theEObject;
			T result = caseMatrixVariable(matrixVariable);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.JOB_USES: {
			JobUses jobUses = (JobUses) theEObject;
			T result = caseJobUses(jobUses);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.POOL: {
			Pool pool = (Pool) theEObject;
			T result = casePool(pool);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.WORKSPACE: {
			Workspace workspace = (Workspace) theEObject;
			T result = caseWorkspace(workspace);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.CONTAINER_REFERENCE: {
			ContainerReference containerReference = (ContainerReference) theEObject;
			T result = caseContainerReference(containerReference);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.CONTAINER_ALIAS: {
			ContainerAlias containerAlias = (ContainerAlias) theEObject;
			T result = caseContainerAlias(containerAlias);
			if (result == null)
				result = caseContainerReference(containerAlias);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.CONTAINER_IMAGE: {
			ContainerImage containerImage = (ContainerImage) theEObject;
			T result = caseContainerImage(containerImage);
			if (result == null)
				result = caseContainerReference(containerImage);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.MOUNT_READ_ONLY: {
			MountReadOnly mountReadOnly = (MountReadOnly) theEObject;
			T result = caseMountReadOnly(mountReadOnly);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.SERVICE_ENTRY: {
			@SuppressWarnings("unchecked")
			Map.Entry<String, String> serviceEntry = (Map.Entry<String, String>) theEObject;
			T result = caseServiceEntry(serviceEntry);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.STEP: {
			Step step = (Step) theEObject;
			T result = caseStep(step);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.TASK_STEP: {
			TaskStep taskStep = (TaskStep) theEObject;
			T result = caseTaskStep(taskStep);
			if (result == null)
				result = caseStep(taskStep);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.SCRIPT_STEP: {
			ScriptStep scriptStep = (ScriptStep) theEObject;
			T result = caseScriptStep(scriptStep);
			if (result == null)
				result = caseStep(scriptStep);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.BASH_STEP: {
			BashStep bashStep = (BashStep) theEObject;
			T result = caseBashStep(bashStep);
			if (result == null)
				result = caseStep(bashStep);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.POWERSHELL_STEP: {
			PowershellStep powershellStep = (PowershellStep) theEObject;
			T result = casePowershellStep(powershellStep);
			if (result == null)
				result = caseStep(powershellStep);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.PWSH_STEP: {
			PwshStep pwshStep = (PwshStep) theEObject;
			T result = casePwshStep(pwshStep);
			if (result == null)
				result = caseStep(pwshStep);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.CHECKOUT_STEP: {
			CheckoutStep checkoutStep = (CheckoutStep) theEObject;
			T result = caseCheckoutStep(checkoutStep);
			if (result == null)
				result = caseStep(checkoutStep);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.DOWNLOAD_STEP: {
			DownloadStep downloadStep = (DownloadStep) theEObject;
			T result = caseDownloadStep(downloadStep);
			if (result == null)
				result = caseStep(downloadStep);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.DOWNLOAD_BUILD_STEP: {
			DownloadBuildStep downloadBuildStep = (DownloadBuildStep) theEObject;
			T result = caseDownloadBuildStep(downloadBuildStep);
			if (result == null)
				result = caseStep(downloadBuildStep);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.GET_PACKAGE_STEP: {
			GetPackageStep getPackageStep = (GetPackageStep) theEObject;
			T result = caseGetPackageStep(getPackageStep);
			if (result == null)
				result = caseStep(getPackageStep);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.PUBLISH_STEP: {
			PublishStep publishStep = (PublishStep) theEObject;
			T result = casePublishStep(publishStep);
			if (result == null)
				result = caseStep(publishStep);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.REVIEW_APP_STEP: {
			ReviewAppStep reviewAppStep = (ReviewAppStep) theEObject;
			T result = caseReviewAppStep(reviewAppStep);
			if (result == null)
				result = caseStep(reviewAppStep);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.STEP_TEMPLATE: {
			StepTemplate stepTemplate = (StepTemplate) theEObject;
			T result = caseStepTemplate(stepTemplate);
			if (result == null)
				result = caseStep(stepTemplate);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.STEP_TARGET: {
			StepTarget stepTarget = (StepTarget) theEObject;
			T result = caseStepTarget(stepTarget);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.SETTABLE_VARIABLES: {
			SettableVariables settableVariables = (SettableVariables) theEObject;
			T result = caseSettableVariables(settableVariables);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.TRIGGER: {
			Trigger trigger = (Trigger) theEObject;
			T result = caseTrigger(trigger);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.PR_TRIGGER: {
			PrTrigger prTrigger = (PrTrigger) theEObject;
			T result = casePrTrigger(prTrigger);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.CRON_SCHEDULE: {
			CronSchedule cronSchedule = (CronSchedule) theEObject;
			T result = caseCronSchedule(cronSchedule);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.INCLUDE_EXCLUDE_FILTERS: {
			IncludeExcludeFilters includeExcludeFilters = (IncludeExcludeFilters) theEObject;
			T result = caseIncludeExcludeFilters(includeExcludeFilters);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.PARAMETER: {
			Parameter parameter = (Parameter) theEObject;
			T result = caseParameter(parameter);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.TEMPLATE_PARAMETER_ASSIGNMENT: {
			@SuppressWarnings("unchecked")
			Map.Entry<String, String> templateParameterAssignment = (Map.Entry<String, String>) theEObject;
			T result = caseTemplateParameterAssignment(templateParameterAssignment);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.VARIABLE_DEFINITION: {
			VariableDefinition variableDefinition = (VariableDefinition) theEObject;
			T result = caseVariableDefinition(variableDefinition);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.VARIABLE_NAME: {
			VariableName variableName = (VariableName) theEObject;
			T result = caseVariableName(variableName);
			if (result == null)
				result = caseVariableDefinition(variableName);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.VARIABLE_GROUP: {
			VariableGroup variableGroup = (VariableGroup) theEObject;
			T result = caseVariableGroup(variableGroup);
			if (result == null)
				result = caseVariableDefinition(variableGroup);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.VARIABLE_TEMPLATE: {
			VariableTemplate variableTemplate = (VariableTemplate) theEObject;
			T result = caseVariableTemplate(variableTemplate);
			if (result == null)
				result = caseVariableDefinition(variableTemplate);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.RESOURCES: {
			Resources resources = (Resources) theEObject;
			T result = caseResources(resources);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.BUILD_RESOURCE: {
			BuildResource buildResource = (BuildResource) theEObject;
			T result = caseBuildResource(buildResource);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.CONTAINER_RESOURCE: {
			ContainerResource containerResource = (ContainerResource) theEObject;
			T result = caseContainerResource(containerResource);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.CONTAINER_RESOURCE_TRIGGER: {
			ContainerResourceTrigger containerResourceTrigger = (ContainerResourceTrigger) theEObject;
			T result = caseContainerResourceTrigger(containerResourceTrigger);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.PIPELINE_RESOURCE: {
			PipelineResource pipelineResource = (PipelineResource) theEObject;
			T result = casePipelineResource(pipelineResource);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.PIPELINE_RESOURCE_TRIGGER: {
			PipelineResourceTrigger pipelineResourceTrigger = (PipelineResourceTrigger) theEObject;
			T result = casePipelineResourceTrigger(pipelineResourceTrigger);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.REPOSITORY_RESOURCE: {
			RepositoryResource repositoryResource = (RepositoryResource) theEObject;
			T result = caseRepositoryResource(repositoryResource);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.WEBHOOK_RESOURCE: {
			WebhookResource webhookResource = (WebhookResource) theEObject;
			T result = caseWebhookResource(webhookResource);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.WEBHOOK_FILTER: {
			WebhookFilter webhookFilter = (WebhookFilter) theEObject;
			T result = caseWebhookFilter(webhookFilter);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.PACKAGE_RESOURCE: {
			PackageResource packageResource = (PackageResource) theEObject;
			T result = casePackageResource(packageResource);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.ENV_ENTRY: {
			@SuppressWarnings("unchecked")
			Map.Entry<String, String> envEntry = (Map.Entry<String, String>) theEObject;
			T result = caseEnvEntry(envEntry);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.INPUT_ENTRY: {
			@SuppressWarnings("unchecked")
			Map.Entry<String, String> inputEntry = (Map.Entry<String, String>) theEObject;
			T result = caseInputEntry(inputEntry);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case AzuredevopsMMPackage.TEMPLATE_CONTEXT: {
			TemplateContext templateContext = (TemplateContext) theEObject;
			T result = caseTemplateContext(templateContext);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		default:
			return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Pipeline</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Pipeline</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePipeline(Pipeline object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Extends</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Extends</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExtends(Extends object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stage Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stage Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStageElement(StageElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stage</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stage</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStage(Stage object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stage Template</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stage Template</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStageTemplate(StageTemplate object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Job Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Job Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseJobElement(JobElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Job</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Job</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseJob(Job object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Deployment Job</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Deployment Job</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDeploymentJob(DeploymentJob object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Job Template</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Job Template</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseJobTemplate(JobTemplate object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Environment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Environment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnvironment(Environment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Deployment Strategy</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Deployment Strategy</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDeploymentStrategy(DeploymentStrategy object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Run Once Strategy</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Run Once Strategy</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRunOnceStrategy(RunOnceStrategy object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Rolling Strategy</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Rolling Strategy</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRollingStrategy(RollingStrategy object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Canary Strategy</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Canary Strategy</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCanaryStrategy(CanaryStrategy object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Deploy Hook</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Deploy Hook</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDeployHook(DeployHook object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>On Success Or Failure Hook</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>On Success Or Failure Hook</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOnSuccessOrFailureHook(OnSuccessOrFailureHook object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Job Strategy</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Job Strategy</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseJobStrategy(JobStrategy object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Matrix Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Matrix Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMatrixEntry(Map.Entry<String, EMap<String, String>> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Matrix Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Matrix Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMatrixVariable(Map.Entry<String, String> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Job Uses</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Job Uses</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseJobUses(JobUses object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Pool</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Pool</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePool(Pool object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Workspace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Workspace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWorkspace(Workspace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Container Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContainerReference(ContainerReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Container Alias</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container Alias</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContainerAlias(ContainerAlias object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Container Image</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container Image</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContainerImage(ContainerImage object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Mount Read Only</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Mount Read Only</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMountReadOnly(MountReadOnly object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Service Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Service Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseServiceEntry(Map.Entry<String, String> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Step</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Step</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStep(Step object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Task Step</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Task Step</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTaskStep(TaskStep object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Script Step</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Script Step</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseScriptStep(ScriptStep object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Bash Step</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Bash Step</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBashStep(BashStep object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Powershell Step</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Powershell Step</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePowershellStep(PowershellStep object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Pwsh Step</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Pwsh Step</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePwshStep(PwshStep object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Checkout Step</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Checkout Step</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCheckoutStep(CheckoutStep object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Download Step</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Download Step</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDownloadStep(DownloadStep object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Download Build Step</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Download Build Step</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDownloadBuildStep(DownloadBuildStep object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Get Package Step</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Get Package Step</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGetPackageStep(GetPackageStep object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Publish Step</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Publish Step</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePublishStep(PublishStep object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Review App Step</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Review App Step</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReviewAppStep(ReviewAppStep object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Step Template</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Step Template</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStepTemplate(StepTemplate object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Step Target</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Step Target</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStepTarget(StepTarget object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Settable Variables</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Settable Variables</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSettableVariables(SettableVariables object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Trigger</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTrigger(Trigger object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Pr Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Pr Trigger</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePrTrigger(PrTrigger object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Cron Schedule</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Cron Schedule</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCronSchedule(CronSchedule object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Include Exclude Filters</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Include Exclude Filters</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIncludeExcludeFilters(IncludeExcludeFilters object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParameter(Parameter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Template Parameter Assignment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Template Parameter Assignment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTemplateParameterAssignment(Map.Entry<String, String> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Variable Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Variable Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVariableDefinition(VariableDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Variable Name</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Variable Name</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVariableName(VariableName object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Variable Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Variable Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVariableGroup(VariableGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Variable Template</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Variable Template</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVariableTemplate(VariableTemplate object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resources</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resources</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResources(Resources object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Build Resource</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Build Resource</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBuildResource(BuildResource object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Container Resource</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container Resource</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContainerResource(ContainerResource object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Container Resource Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container Resource Trigger</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContainerResourceTrigger(ContainerResourceTrigger object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Pipeline Resource</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Pipeline Resource</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePipelineResource(PipelineResource object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Pipeline Resource Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Pipeline Resource Trigger</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePipelineResourceTrigger(PipelineResourceTrigger object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Repository Resource</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Repository Resource</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRepositoryResource(RepositoryResource object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Webhook Resource</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Webhook Resource</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWebhookResource(WebhookResource object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Webhook Filter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Webhook Filter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWebhookFilter(WebhookFilter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Package Resource</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Package Resource</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePackageResource(PackageResource object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Env Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Env Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnvEntry(Map.Entry<String, String> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Input Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Input Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInputEntry(Map.Entry<String, String> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Template Context</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Template Context</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTemplateContext(TemplateContext object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //AzuredevopsMMSwitch
