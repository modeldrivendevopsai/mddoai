/**
 */
package com.mddoai.metamodel.bambooMM.util;

import com.mddoai.metamodel.bambooMM.*;

import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.mddoai.metamodel.bambooMM.BambooPackage
 * @generated
 */
public class BambooAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static BambooPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BambooAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = BambooPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject) object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BambooSwitch<Adapter> modelSwitch = new BambooSwitch<Adapter>() {
		@Override
		public Adapter casePlan(Plan object) {
			return createPlanAdapter();
		}

		@Override
		public Adapter caseStage(Stage object) {
			return createStageAdapter();
		}

		@Override
		public Adapter caseJob(Job object) {
			return createJobAdapter();
		}

		@Override
		public Adapter caseTask(Task object) {
			return createTaskAdapter();
		}

		@Override
		public Adapter caseScriptTask(ScriptTask object) {
			return createScriptTaskAdapter();
		}

		@Override
		public Adapter caseCleanTask(CleanTask object) {
			return createCleanTaskAdapter();
		}

		@Override
		public Adapter caseCheckoutTask(CheckoutTask object) {
			return createCheckoutTaskAdapter();
		}

		@Override
		public Adapter caseMavenTask(MavenTask object) {
			return createMavenTaskAdapter();
		}

		@Override
		public Adapter caseInjectVariablesTask(InjectVariablesTask object) {
			return createInjectVariablesTaskAdapter();
		}

		@Override
		public Adapter caseTestParserTask(TestParserTask object) {
			return createTestParserTaskAdapter();
		}

		@Override
		public Adapter caseArtifactDownloadTask(ArtifactDownloadTask object) {
			return createArtifactDownloadTaskAdapter();
		}

		@Override
		public Adapter caseArtifactDownloadItem(ArtifactDownloadItem object) {
			return createArtifactDownloadItemAdapter();
		}

		@Override
		public Adapter caseAnyTask(AnyTask object) {
			return createAnyTaskAdapter();
		}

		@Override
		public Adapter caseTaskCondition(TaskCondition object) {
			return createTaskConditionAdapter();
		}

		@Override
		public Adapter caseTrigger(Trigger object) {
			return createTriggerAdapter();
		}

		@Override
		public Adapter casePollingTrigger(PollingTrigger object) {
			return createPollingTriggerAdapter();
		}

		@Override
		public Adapter caseCronTrigger(CronTrigger object) {
			return createCronTriggerAdapter();
		}

		@Override
		public Adapter caseRemoteTrigger(RemoteTrigger object) {
			return createRemoteTriggerAdapter();
		}

		@Override
		public Adapter caseAfterDeploymentTrigger(AfterDeploymentTrigger object) {
			return createAfterDeploymentTriggerAdapter();
		}

		@Override
		public Adapter caseBuildSuccessTrigger(BuildSuccessTrigger object) {
			return createBuildSuccessTriggerAdapter();
		}

		@Override
		public Adapter caseStageSuccessTrigger(StageSuccessTrigger object) {
			return createStageSuccessTriggerAdapter();
		}

		@Override
		public Adapter caseEnvironmentSuccessTrigger(EnvironmentSuccessTrigger object) {
			return createEnvironmentSuccessTriggerAdapter();
		}

		@Override
		public Adapter caseScheduledDeploymentTrigger(ScheduledDeploymentTrigger object) {
			return createScheduledDeploymentTriggerAdapter();
		}

		@Override
		public Adapter caseTriggerCondition(TriggerCondition object) {
			return createTriggerConditionAdapter();
		}

		@Override
		public Adapter caseGreenPlanCondition(GreenPlanCondition object) {
			return createGreenPlanConditionAdapter();
		}

		@Override
		public Adapter caseAllOtherCondition(AllOtherCondition object) {
			return createAllOtherConditionAdapter();
		}

		@Override
		public Adapter caseRepository(Repository object) {
			return createRepositoryAdapter();
		}

		@Override
		public Adapter caseLinkedRepository(LinkedRepository object) {
			return createLinkedRepositoryAdapter();
		}

		@Override
		public Adapter caseGitRepository(GitRepository object) {
			return createGitRepositoryAdapter();
		}

		@Override
		public Adapter caseBitbucketCloudRepository(BitbucketCloudRepository object) {
			return createBitbucketCloudRepositoryAdapter();
		}

		@Override
		public Adapter caseBitbucketServerRepository(BitbucketServerRepository object) {
			return createBitbucketServerRepositoryAdapter();
		}

		@Override
		public Adapter caseGithubRepository(GithubRepository object) {
			return createGithubRepositoryAdapter();
		}

		@Override
		public Adapter caseAnyVcsRepository(AnyVcsRepository object) {
			return createAnyVcsRepositoryAdapter();
		}

		@Override
		public Adapter caseChangeDetection(ChangeDetection object) {
			return createChangeDetectionAdapter();
		}

		@Override
		public Adapter caseQuietPeriod(QuietPeriod object) {
			return createQuietPeriodAdapter();
		}

		@Override
		public Adapter caseArtifact(Artifact object) {
			return createArtifactAdapter();
		}

		@Override
		public Adapter caseArtifactSubscription(ArtifactSubscription object) {
			return createArtifactSubscriptionAdapter();
		}

		@Override
		public Adapter caseRequirement(Requirement object) {
			return createRequirementAdapter();
		}

		@Override
		public Adapter caseVariableAssignment(Map.Entry<String, String> object) {
			return createVariableAssignmentAdapter();
		}

		@Override
		public Adapter caseLabel(Label object) {
			return createLabelAdapter();
		}

		@Override
		public Adapter caseNotification(Notification object) {
			return createNotificationAdapter();
		}

		@Override
		public Adapter caseNotificationRecipient(NotificationRecipient object) {
			return createNotificationRecipientAdapter();
		}

		@Override
		public Adapter caseUserRecipient(UserRecipient object) {
			return createUserRecipientAdapter();
		}

		@Override
		public Adapter caseGroupRecipient(GroupRecipient object) {
			return createGroupRecipientAdapter();
		}

		@Override
		public Adapter caseEmailRecipient(EmailRecipient object) {
			return createEmailRecipientAdapter();
		}

		@Override
		public Adapter caseRoleRecipient(RoleRecipient object) {
			return createRoleRecipientAdapter();
		}

		@Override
		public Adapter caseWebhookRecipient(WebhookRecipient object) {
			return createWebhookRecipientAdapter();
		}

		@Override
		public Adapter caseNotificationEvent(NotificationEvent object) {
			return createNotificationEventAdapter();
		}

		@Override
		public Adapter caseBranchManagement(BranchManagement object) {
			return createBranchManagementAdapter();
		}

		@Override
		public Adapter caseBranchDelete(BranchDelete object) {
			return createBranchDeleteAdapter();
		}

		@Override
		public Adapter caseBranchIntegration(BranchIntegration object) {
			return createBranchIntegrationAdapter();
		}

		@Override
		public Adapter caseBranchOverride(BranchOverride object) {
			return createBranchOverrideAdapter();
		}

		@Override
		public Adapter caseBranchSpecificConfig(BranchSpecificConfig object) {
			return createBranchSpecificConfigAdapter();
		}

		@Override
		public Adapter caseDependencies(Dependencies object) {
			return createDependenciesAdapter();
		}

		@Override
		public Adapter caseDockerConfig(DockerConfig object) {
			return createDockerConfigAdapter();
		}

		@Override
		public Adapter caseOtherConfig(OtherConfig object) {
			return createOtherConfigAdapter();
		}

		@Override
		public Adapter caseDeploymentProject(DeploymentProject object) {
			return createDeploymentProjectAdapter();
		}

		@Override
		public Adapter caseReleaseNaming(ReleaseNaming object) {
			return createReleaseNamingAdapter();
		}

		@Override
		public Adapter caseEnvironment(Environment object) {
			return createEnvironmentAdapter();
		}

		@Override
		public Adapter casePermissionEntry(PermissionEntry object) {
			return createPermissionEntryAdapter();
		}

		@Override
		public Adapter casePlanPermissionEntry(PlanPermissionEntry object) {
			return createPlanPermissionEntryAdapter();
		}

		@Override
		public Adapter caseDeploymentPermissionEntry(DeploymentPermissionEntry object) {
			return createDeploymentPermissionEntryAdapter();
		}

		@Override
		public Adapter caseEnvironmentPermissionEntry(EnvironmentPermissionEntry object) {
			return createEnvironmentPermissionEntryAdapter();
		}

		@Override
		public Adapter caseNamedEnvironmentPermission(NamedEnvironmentPermission object) {
			return createNamedEnvironmentPermissionAdapter();
		}

		@Override
		public Adapter defaultCase(EObject object) {
			return createEObjectAdapter();
		}
	};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject) target);
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.Plan <em>Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.Plan
	 * @generated
	 */
	public Adapter createPlanAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.Stage <em>Stage</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.Stage
	 * @generated
	 */
	public Adapter createStageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.Job <em>Job</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.Job
	 * @generated
	 */
	public Adapter createJobAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.Task <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.Task
	 * @generated
	 */
	public Adapter createTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.ScriptTask <em>Script Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.ScriptTask
	 * @generated
	 */
	public Adapter createScriptTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.CleanTask <em>Clean Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.CleanTask
	 * @generated
	 */
	public Adapter createCleanTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.CheckoutTask <em>Checkout Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.CheckoutTask
	 * @generated
	 */
	public Adapter createCheckoutTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.MavenTask <em>Maven Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.MavenTask
	 * @generated
	 */
	public Adapter createMavenTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.InjectVariablesTask <em>Inject Variables Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.InjectVariablesTask
	 * @generated
	 */
	public Adapter createInjectVariablesTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.TestParserTask <em>Test Parser Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.TestParserTask
	 * @generated
	 */
	public Adapter createTestParserTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.ArtifactDownloadTask <em>Artifact Download Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.ArtifactDownloadTask
	 * @generated
	 */
	public Adapter createArtifactDownloadTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.ArtifactDownloadItem <em>Artifact Download Item</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.ArtifactDownloadItem
	 * @generated
	 */
	public Adapter createArtifactDownloadItemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.AnyTask <em>Any Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.AnyTask
	 * @generated
	 */
	public Adapter createAnyTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.TaskCondition <em>Task Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.TaskCondition
	 * @generated
	 */
	public Adapter createTaskConditionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.Trigger <em>Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.Trigger
	 * @generated
	 */
	public Adapter createTriggerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.PollingTrigger <em>Polling Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.PollingTrigger
	 * @generated
	 */
	public Adapter createPollingTriggerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.CronTrigger <em>Cron Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.CronTrigger
	 * @generated
	 */
	public Adapter createCronTriggerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.RemoteTrigger <em>Remote Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.RemoteTrigger
	 * @generated
	 */
	public Adapter createRemoteTriggerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.AfterDeploymentTrigger <em>After Deployment Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.AfterDeploymentTrigger
	 * @generated
	 */
	public Adapter createAfterDeploymentTriggerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.BuildSuccessTrigger <em>Build Success Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.BuildSuccessTrigger
	 * @generated
	 */
	public Adapter createBuildSuccessTriggerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.StageSuccessTrigger <em>Stage Success Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.StageSuccessTrigger
	 * @generated
	 */
	public Adapter createStageSuccessTriggerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.EnvironmentSuccessTrigger <em>Environment Success Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.EnvironmentSuccessTrigger
	 * @generated
	 */
	public Adapter createEnvironmentSuccessTriggerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.ScheduledDeploymentTrigger <em>Scheduled Deployment Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.ScheduledDeploymentTrigger
	 * @generated
	 */
	public Adapter createScheduledDeploymentTriggerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.TriggerCondition <em>Trigger Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.TriggerCondition
	 * @generated
	 */
	public Adapter createTriggerConditionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.GreenPlanCondition <em>Green Plan Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.GreenPlanCondition
	 * @generated
	 */
	public Adapter createGreenPlanConditionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.AllOtherCondition <em>All Other Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.AllOtherCondition
	 * @generated
	 */
	public Adapter createAllOtherConditionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.Repository <em>Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.Repository
	 * @generated
	 */
	public Adapter createRepositoryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.LinkedRepository <em>Linked Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.LinkedRepository
	 * @generated
	 */
	public Adapter createLinkedRepositoryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.GitRepository <em>Git Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.GitRepository
	 * @generated
	 */
	public Adapter createGitRepositoryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.BitbucketCloudRepository <em>Bitbucket Cloud Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.BitbucketCloudRepository
	 * @generated
	 */
	public Adapter createBitbucketCloudRepositoryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository <em>Bitbucket Server Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.BitbucketServerRepository
	 * @generated
	 */
	public Adapter createBitbucketServerRepositoryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.GithubRepository <em>Github Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.GithubRepository
	 * @generated
	 */
	public Adapter createGithubRepositoryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.AnyVcsRepository <em>Any Vcs Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.AnyVcsRepository
	 * @generated
	 */
	public Adapter createAnyVcsRepositoryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.ChangeDetection <em>Change Detection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.ChangeDetection
	 * @generated
	 */
	public Adapter createChangeDetectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.QuietPeriod <em>Quiet Period</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.QuietPeriod
	 * @generated
	 */
	public Adapter createQuietPeriodAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.Artifact <em>Artifact</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.Artifact
	 * @generated
	 */
	public Adapter createArtifactAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.ArtifactSubscription <em>Artifact Subscription</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.ArtifactSubscription
	 * @generated
	 */
	public Adapter createArtifactSubscriptionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.Requirement <em>Requirement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.Requirement
	 * @generated
	 */
	public Adapter createRequirementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Variable Assignment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createVariableAssignmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.Label <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.Label
	 * @generated
	 */
	public Adapter createLabelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.Notification <em>Notification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.Notification
	 * @generated
	 */
	public Adapter createNotificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.NotificationRecipient <em>Notification Recipient</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.NotificationRecipient
	 * @generated
	 */
	public Adapter createNotificationRecipientAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.UserRecipient <em>User Recipient</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.UserRecipient
	 * @generated
	 */
	public Adapter createUserRecipientAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.GroupRecipient <em>Group Recipient</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.GroupRecipient
	 * @generated
	 */
	public Adapter createGroupRecipientAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.EmailRecipient <em>Email Recipient</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.EmailRecipient
	 * @generated
	 */
	public Adapter createEmailRecipientAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.RoleRecipient <em>Role Recipient</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.RoleRecipient
	 * @generated
	 */
	public Adapter createRoleRecipientAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.WebhookRecipient <em>Webhook Recipient</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.WebhookRecipient
	 * @generated
	 */
	public Adapter createWebhookRecipientAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.NotificationEvent <em>Notification Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.NotificationEvent
	 * @generated
	 */
	public Adapter createNotificationEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.BranchManagement <em>Branch Management</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.BranchManagement
	 * @generated
	 */
	public Adapter createBranchManagementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.BranchDelete <em>Branch Delete</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.BranchDelete
	 * @generated
	 */
	public Adapter createBranchDeleteAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.BranchIntegration <em>Branch Integration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.BranchIntegration
	 * @generated
	 */
	public Adapter createBranchIntegrationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.BranchOverride <em>Branch Override</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.BranchOverride
	 * @generated
	 */
	public Adapter createBranchOverrideAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.BranchSpecificConfig <em>Branch Specific Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.BranchSpecificConfig
	 * @generated
	 */
	public Adapter createBranchSpecificConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.Dependencies <em>Dependencies</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.Dependencies
	 * @generated
	 */
	public Adapter createDependenciesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.DockerConfig <em>Docker Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.DockerConfig
	 * @generated
	 */
	public Adapter createDockerConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.OtherConfig <em>Other Config</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.OtherConfig
	 * @generated
	 */
	public Adapter createOtherConfigAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.DeploymentProject <em>Deployment Project</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.DeploymentProject
	 * @generated
	 */
	public Adapter createDeploymentProjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.ReleaseNaming <em>Release Naming</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.ReleaseNaming
	 * @generated
	 */
	public Adapter createReleaseNamingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.Environment <em>Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.Environment
	 * @generated
	 */
	public Adapter createEnvironmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.PermissionEntry <em>Permission Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.PermissionEntry
	 * @generated
	 */
	public Adapter createPermissionEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.PlanPermissionEntry <em>Plan Permission Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.PlanPermissionEntry
	 * @generated
	 */
	public Adapter createPlanPermissionEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.DeploymentPermissionEntry <em>Deployment Permission Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.DeploymentPermissionEntry
	 * @generated
	 */
	public Adapter createDeploymentPermissionEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.EnvironmentPermissionEntry <em>Environment Permission Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.EnvironmentPermissionEntry
	 * @generated
	 */
	public Adapter createEnvironmentPermissionEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.bambooMM.NamedEnvironmentPermission <em>Named Environment Permission</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.bambooMM.NamedEnvironmentPermission
	 * @generated
	 */
	public Adapter createNamedEnvironmentPermissionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //BambooAdapterFactory
