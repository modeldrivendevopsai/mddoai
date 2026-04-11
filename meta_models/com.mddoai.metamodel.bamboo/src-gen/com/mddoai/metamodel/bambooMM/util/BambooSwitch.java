/**
 */
package com.mddoai.metamodel.bambooMM.util;

import com.mddoai.metamodel.bambooMM.*;

import java.util.Map;

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
 * @see com.mddoai.metamodel.bambooMM.BambooPackage
 * @generated
 */
public class BambooSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static BambooPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BambooSwitch() {
		if (modelPackage == null) {
			modelPackage = BambooPackage.eINSTANCE;
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
		case BambooPackage.PLAN: {
			Plan plan = (Plan) theEObject;
			T result = casePlan(plan);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.STAGE: {
			Stage stage = (Stage) theEObject;
			T result = caseStage(stage);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.JOB: {
			Job job = (Job) theEObject;
			T result = caseJob(job);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.TASK: {
			Task task = (Task) theEObject;
			T result = caseTask(task);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.SCRIPT_TASK: {
			ScriptTask scriptTask = (ScriptTask) theEObject;
			T result = caseScriptTask(scriptTask);
			if (result == null)
				result = caseTask(scriptTask);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.CLEAN_TASK: {
			CleanTask cleanTask = (CleanTask) theEObject;
			T result = caseCleanTask(cleanTask);
			if (result == null)
				result = caseTask(cleanTask);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.CHECKOUT_TASK: {
			CheckoutTask checkoutTask = (CheckoutTask) theEObject;
			T result = caseCheckoutTask(checkoutTask);
			if (result == null)
				result = caseTask(checkoutTask);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.MAVEN_TASK: {
			MavenTask mavenTask = (MavenTask) theEObject;
			T result = caseMavenTask(mavenTask);
			if (result == null)
				result = caseTask(mavenTask);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.INJECT_VARIABLES_TASK: {
			InjectVariablesTask injectVariablesTask = (InjectVariablesTask) theEObject;
			T result = caseInjectVariablesTask(injectVariablesTask);
			if (result == null)
				result = caseTask(injectVariablesTask);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.TEST_PARSER_TASK: {
			TestParserTask testParserTask = (TestParserTask) theEObject;
			T result = caseTestParserTask(testParserTask);
			if (result == null)
				result = caseTask(testParserTask);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.ARTIFACT_DOWNLOAD_TASK: {
			ArtifactDownloadTask artifactDownloadTask = (ArtifactDownloadTask) theEObject;
			T result = caseArtifactDownloadTask(artifactDownloadTask);
			if (result == null)
				result = caseTask(artifactDownloadTask);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.ARTIFACT_DOWNLOAD_ITEM: {
			ArtifactDownloadItem artifactDownloadItem = (ArtifactDownloadItem) theEObject;
			T result = caseArtifactDownloadItem(artifactDownloadItem);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.ANY_TASK: {
			AnyTask anyTask = (AnyTask) theEObject;
			T result = caseAnyTask(anyTask);
			if (result == null)
				result = caseTask(anyTask);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.TASK_CONDITION: {
			TaskCondition taskCondition = (TaskCondition) theEObject;
			T result = caseTaskCondition(taskCondition);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.TRIGGER: {
			Trigger trigger = (Trigger) theEObject;
			T result = caseTrigger(trigger);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.POLLING_TRIGGER: {
			PollingTrigger pollingTrigger = (PollingTrigger) theEObject;
			T result = casePollingTrigger(pollingTrigger);
			if (result == null)
				result = caseTrigger(pollingTrigger);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.CRON_TRIGGER: {
			CronTrigger cronTrigger = (CronTrigger) theEObject;
			T result = caseCronTrigger(cronTrigger);
			if (result == null)
				result = caseTrigger(cronTrigger);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.REMOTE_TRIGGER: {
			RemoteTrigger remoteTrigger = (RemoteTrigger) theEObject;
			T result = caseRemoteTrigger(remoteTrigger);
			if (result == null)
				result = caseTrigger(remoteTrigger);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.AFTER_DEPLOYMENT_TRIGGER: {
			AfterDeploymentTrigger afterDeploymentTrigger = (AfterDeploymentTrigger) theEObject;
			T result = caseAfterDeploymentTrigger(afterDeploymentTrigger);
			if (result == null)
				result = caseTrigger(afterDeploymentTrigger);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.BUILD_SUCCESS_TRIGGER: {
			BuildSuccessTrigger buildSuccessTrigger = (BuildSuccessTrigger) theEObject;
			T result = caseBuildSuccessTrigger(buildSuccessTrigger);
			if (result == null)
				result = caseTrigger(buildSuccessTrigger);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.STAGE_SUCCESS_TRIGGER: {
			StageSuccessTrigger stageSuccessTrigger = (StageSuccessTrigger) theEObject;
			T result = caseStageSuccessTrigger(stageSuccessTrigger);
			if (result == null)
				result = caseTrigger(stageSuccessTrigger);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.ENVIRONMENT_SUCCESS_TRIGGER: {
			EnvironmentSuccessTrigger environmentSuccessTrigger = (EnvironmentSuccessTrigger) theEObject;
			T result = caseEnvironmentSuccessTrigger(environmentSuccessTrigger);
			if (result == null)
				result = caseTrigger(environmentSuccessTrigger);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.SCHEDULED_DEPLOYMENT_TRIGGER: {
			ScheduledDeploymentTrigger scheduledDeploymentTrigger = (ScheduledDeploymentTrigger) theEObject;
			T result = caseScheduledDeploymentTrigger(scheduledDeploymentTrigger);
			if (result == null)
				result = caseTrigger(scheduledDeploymentTrigger);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.TRIGGER_CONDITION: {
			TriggerCondition triggerCondition = (TriggerCondition) theEObject;
			T result = caseTriggerCondition(triggerCondition);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.GREEN_PLAN_CONDITION: {
			GreenPlanCondition greenPlanCondition = (GreenPlanCondition) theEObject;
			T result = caseGreenPlanCondition(greenPlanCondition);
			if (result == null)
				result = caseTriggerCondition(greenPlanCondition);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.ALL_OTHER_CONDITION: {
			AllOtherCondition allOtherCondition = (AllOtherCondition) theEObject;
			T result = caseAllOtherCondition(allOtherCondition);
			if (result == null)
				result = caseTriggerCondition(allOtherCondition);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.REPOSITORY: {
			Repository repository = (Repository) theEObject;
			T result = caseRepository(repository);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.LINKED_REPOSITORY: {
			LinkedRepository linkedRepository = (LinkedRepository) theEObject;
			T result = caseLinkedRepository(linkedRepository);
			if (result == null)
				result = caseRepository(linkedRepository);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.GIT_REPOSITORY: {
			GitRepository gitRepository = (GitRepository) theEObject;
			T result = caseGitRepository(gitRepository);
			if (result == null)
				result = caseRepository(gitRepository);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.BITBUCKET_CLOUD_REPOSITORY: {
			BitbucketCloudRepository bitbucketCloudRepository = (BitbucketCloudRepository) theEObject;
			T result = caseBitbucketCloudRepository(bitbucketCloudRepository);
			if (result == null)
				result = caseGitRepository(bitbucketCloudRepository);
			if (result == null)
				result = caseRepository(bitbucketCloudRepository);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY: {
			BitbucketServerRepository bitbucketServerRepository = (BitbucketServerRepository) theEObject;
			T result = caseBitbucketServerRepository(bitbucketServerRepository);
			if (result == null)
				result = caseRepository(bitbucketServerRepository);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.GITHUB_REPOSITORY: {
			GithubRepository githubRepository = (GithubRepository) theEObject;
			T result = caseGithubRepository(githubRepository);
			if (result == null)
				result = caseGitRepository(githubRepository);
			if (result == null)
				result = caseRepository(githubRepository);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.ANY_VCS_REPOSITORY: {
			AnyVcsRepository anyVcsRepository = (AnyVcsRepository) theEObject;
			T result = caseAnyVcsRepository(anyVcsRepository);
			if (result == null)
				result = caseRepository(anyVcsRepository);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.CHANGE_DETECTION: {
			ChangeDetection changeDetection = (ChangeDetection) theEObject;
			T result = caseChangeDetection(changeDetection);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.QUIET_PERIOD: {
			QuietPeriod quietPeriod = (QuietPeriod) theEObject;
			T result = caseQuietPeriod(quietPeriod);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.ARTIFACT: {
			Artifact artifact = (Artifact) theEObject;
			T result = caseArtifact(artifact);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.ARTIFACT_SUBSCRIPTION: {
			ArtifactSubscription artifactSubscription = (ArtifactSubscription) theEObject;
			T result = caseArtifactSubscription(artifactSubscription);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.REQUIREMENT: {
			Requirement requirement = (Requirement) theEObject;
			T result = caseRequirement(requirement);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.VARIABLE_ASSIGNMENT: {
			@SuppressWarnings("unchecked")
			Map.Entry<String, String> variableAssignment = (Map.Entry<String, String>) theEObject;
			T result = caseVariableAssignment(variableAssignment);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.LABEL: {
			Label label = (Label) theEObject;
			T result = caseLabel(label);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.NOTIFICATION: {
			Notification notification = (Notification) theEObject;
			T result = caseNotification(notification);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.NOTIFICATION_RECIPIENT: {
			NotificationRecipient notificationRecipient = (NotificationRecipient) theEObject;
			T result = caseNotificationRecipient(notificationRecipient);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.USER_RECIPIENT: {
			UserRecipient userRecipient = (UserRecipient) theEObject;
			T result = caseUserRecipient(userRecipient);
			if (result == null)
				result = caseNotificationRecipient(userRecipient);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.GROUP_RECIPIENT: {
			GroupRecipient groupRecipient = (GroupRecipient) theEObject;
			T result = caseGroupRecipient(groupRecipient);
			if (result == null)
				result = caseNotificationRecipient(groupRecipient);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.EMAIL_RECIPIENT: {
			EmailRecipient emailRecipient = (EmailRecipient) theEObject;
			T result = caseEmailRecipient(emailRecipient);
			if (result == null)
				result = caseNotificationRecipient(emailRecipient);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.ROLE_RECIPIENT: {
			RoleRecipient roleRecipient = (RoleRecipient) theEObject;
			T result = caseRoleRecipient(roleRecipient);
			if (result == null)
				result = caseNotificationRecipient(roleRecipient);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.WEBHOOK_RECIPIENT: {
			WebhookRecipient webhookRecipient = (WebhookRecipient) theEObject;
			T result = caseWebhookRecipient(webhookRecipient);
			if (result == null)
				result = caseNotificationRecipient(webhookRecipient);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.NOTIFICATION_EVENT: {
			NotificationEvent notificationEvent = (NotificationEvent) theEObject;
			T result = caseNotificationEvent(notificationEvent);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.BRANCH_MANAGEMENT: {
			BranchManagement branchManagement = (BranchManagement) theEObject;
			T result = caseBranchManagement(branchManagement);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.BRANCH_DELETE: {
			BranchDelete branchDelete = (BranchDelete) theEObject;
			T result = caseBranchDelete(branchDelete);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.BRANCH_INTEGRATION: {
			BranchIntegration branchIntegration = (BranchIntegration) theEObject;
			T result = caseBranchIntegration(branchIntegration);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.BRANCH_OVERRIDE: {
			BranchOverride branchOverride = (BranchOverride) theEObject;
			T result = caseBranchOverride(branchOverride);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.BRANCH_SPECIFIC_CONFIG: {
			BranchSpecificConfig branchSpecificConfig = (BranchSpecificConfig) theEObject;
			T result = caseBranchSpecificConfig(branchSpecificConfig);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.DEPENDENCIES: {
			Dependencies dependencies = (Dependencies) theEObject;
			T result = caseDependencies(dependencies);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.DOCKER_CONFIG: {
			DockerConfig dockerConfig = (DockerConfig) theEObject;
			T result = caseDockerConfig(dockerConfig);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.OTHER_CONFIG: {
			OtherConfig otherConfig = (OtherConfig) theEObject;
			T result = caseOtherConfig(otherConfig);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.DEPLOYMENT_PROJECT: {
			DeploymentProject deploymentProject = (DeploymentProject) theEObject;
			T result = caseDeploymentProject(deploymentProject);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.RELEASE_NAMING: {
			ReleaseNaming releaseNaming = (ReleaseNaming) theEObject;
			T result = caseReleaseNaming(releaseNaming);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.ENVIRONMENT: {
			Environment environment = (Environment) theEObject;
			T result = caseEnvironment(environment);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.PERMISSION_ENTRY: {
			PermissionEntry permissionEntry = (PermissionEntry) theEObject;
			T result = casePermissionEntry(permissionEntry);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.PLAN_PERMISSION_ENTRY: {
			PlanPermissionEntry planPermissionEntry = (PlanPermissionEntry) theEObject;
			T result = casePlanPermissionEntry(planPermissionEntry);
			if (result == null)
				result = casePermissionEntry(planPermissionEntry);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.DEPLOYMENT_PERMISSION_ENTRY: {
			DeploymentPermissionEntry deploymentPermissionEntry = (DeploymentPermissionEntry) theEObject;
			T result = caseDeploymentPermissionEntry(deploymentPermissionEntry);
			if (result == null)
				result = casePermissionEntry(deploymentPermissionEntry);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.ENVIRONMENT_PERMISSION_ENTRY: {
			EnvironmentPermissionEntry environmentPermissionEntry = (EnvironmentPermissionEntry) theEObject;
			T result = caseEnvironmentPermissionEntry(environmentPermissionEntry);
			if (result == null)
				result = casePermissionEntry(environmentPermissionEntry);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case BambooPackage.NAMED_ENVIRONMENT_PERMISSION: {
			NamedEnvironmentPermission namedEnvironmentPermission = (NamedEnvironmentPermission) theEObject;
			T result = caseNamedEnvironmentPermission(namedEnvironmentPermission);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		default:
			return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Plan</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Plan</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePlan(Plan object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Task</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Task</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTask(Task object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Script Task</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Script Task</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseScriptTask(ScriptTask object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Clean Task</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Clean Task</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCleanTask(CleanTask object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Checkout Task</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Checkout Task</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCheckoutTask(CheckoutTask object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Maven Task</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Maven Task</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMavenTask(MavenTask object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Inject Variables Task</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Inject Variables Task</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInjectVariablesTask(InjectVariablesTask object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Parser Task</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Parser Task</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestParserTask(TestParserTask object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Artifact Download Task</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Artifact Download Task</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArtifactDownloadTask(ArtifactDownloadTask object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Artifact Download Item</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Artifact Download Item</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArtifactDownloadItem(ArtifactDownloadItem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Any Task</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Any Task</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAnyTask(AnyTask object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Task Condition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Task Condition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTaskCondition(TaskCondition object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Polling Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Polling Trigger</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePollingTrigger(PollingTrigger object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Cron Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Cron Trigger</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCronTrigger(CronTrigger object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Remote Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Remote Trigger</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRemoteTrigger(RemoteTrigger object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>After Deployment Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>After Deployment Trigger</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAfterDeploymentTrigger(AfterDeploymentTrigger object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Build Success Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Build Success Trigger</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBuildSuccessTrigger(BuildSuccessTrigger object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stage Success Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stage Success Trigger</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStageSuccessTrigger(StageSuccessTrigger object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Environment Success Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Environment Success Trigger</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnvironmentSuccessTrigger(EnvironmentSuccessTrigger object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Scheduled Deployment Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Scheduled Deployment Trigger</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseScheduledDeploymentTrigger(ScheduledDeploymentTrigger object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Trigger Condition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Trigger Condition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTriggerCondition(TriggerCondition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Green Plan Condition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Green Plan Condition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGreenPlanCondition(GreenPlanCondition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>All Other Condition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>All Other Condition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAllOtherCondition(AllOtherCondition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Repository</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Repository</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRepository(Repository object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Linked Repository</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Linked Repository</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLinkedRepository(LinkedRepository object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Git Repository</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Git Repository</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGitRepository(GitRepository object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Bitbucket Cloud Repository</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Bitbucket Cloud Repository</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBitbucketCloudRepository(BitbucketCloudRepository object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Bitbucket Server Repository</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Bitbucket Server Repository</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBitbucketServerRepository(BitbucketServerRepository object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Github Repository</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Github Repository</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGithubRepository(GithubRepository object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Any Vcs Repository</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Any Vcs Repository</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAnyVcsRepository(AnyVcsRepository object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Change Detection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Change Detection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseChangeDetection(ChangeDetection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Quiet Period</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Quiet Period</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseQuietPeriod(QuietPeriod object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Artifact</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Artifact</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArtifact(Artifact object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Artifact Subscription</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Artifact Subscription</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArtifactSubscription(ArtifactSubscription object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Requirement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Requirement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRequirement(Requirement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Variable Assignment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Variable Assignment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVariableAssignment(Map.Entry<String, String> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Label</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Label</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLabel(Label object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Notification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Notification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNotification(Notification object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Notification Recipient</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Notification Recipient</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNotificationRecipient(NotificationRecipient object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>User Recipient</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User Recipient</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUserRecipient(UserRecipient object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Group Recipient</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Group Recipient</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGroupRecipient(GroupRecipient object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Email Recipient</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Email Recipient</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEmailRecipient(EmailRecipient object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Role Recipient</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Role Recipient</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRoleRecipient(RoleRecipient object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Webhook Recipient</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Webhook Recipient</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWebhookRecipient(WebhookRecipient object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Notification Event</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Notification Event</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNotificationEvent(NotificationEvent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Branch Management</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Branch Management</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBranchManagement(BranchManagement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Branch Delete</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Branch Delete</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBranchDelete(BranchDelete object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Branch Integration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Branch Integration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBranchIntegration(BranchIntegration object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Branch Override</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Branch Override</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBranchOverride(BranchOverride object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Branch Specific Config</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Branch Specific Config</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBranchSpecificConfig(BranchSpecificConfig object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dependencies</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dependencies</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDependencies(Dependencies object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Docker Config</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Docker Config</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDockerConfig(DockerConfig object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Other Config</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Other Config</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOtherConfig(OtherConfig object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Deployment Project</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Deployment Project</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDeploymentProject(DeploymentProject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Release Naming</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Release Naming</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReleaseNaming(ReleaseNaming object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Permission Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Permission Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePermissionEntry(PermissionEntry object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Plan Permission Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Plan Permission Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePlanPermissionEntry(PlanPermissionEntry object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Deployment Permission Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Deployment Permission Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDeploymentPermissionEntry(DeploymentPermissionEntry object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Environment Permission Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Environment Permission Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnvironmentPermissionEntry(EnvironmentPermissionEntry object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Environment Permission</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Environment Permission</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedEnvironmentPermission(NamedEnvironmentPermission object) {
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

} //BambooSwitch
