/**
 */
package com.mddoai.metamodel.bambooMM;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.mddoai.metamodel.bambooMM.BambooPackage
 * @generated
 */
public interface BambooFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BambooFactory eINSTANCE = com.mddoai.metamodel.bambooMM.impl.BambooFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Plan</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Plan</em>'.
	 * @generated
	 */
	Plan createPlan();

	/**
	 * Returns a new object of class '<em>Stage</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Stage</em>'.
	 * @generated
	 */
	Stage createStage();

	/**
	 * Returns a new object of class '<em>Job</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Job</em>'.
	 * @generated
	 */
	Job createJob();

	/**
	 * Returns a new object of class '<em>Script Task</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Script Task</em>'.
	 * @generated
	 */
	ScriptTask createScriptTask();

	/**
	 * Returns a new object of class '<em>Clean Task</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Clean Task</em>'.
	 * @generated
	 */
	CleanTask createCleanTask();

	/**
	 * Returns a new object of class '<em>Checkout Task</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Checkout Task</em>'.
	 * @generated
	 */
	CheckoutTask createCheckoutTask();

	/**
	 * Returns a new object of class '<em>Maven Task</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Maven Task</em>'.
	 * @generated
	 */
	MavenTask createMavenTask();

	/**
	 * Returns a new object of class '<em>Inject Variables Task</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Inject Variables Task</em>'.
	 * @generated
	 */
	InjectVariablesTask createInjectVariablesTask();

	/**
	 * Returns a new object of class '<em>Test Parser Task</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Parser Task</em>'.
	 * @generated
	 */
	TestParserTask createTestParserTask();

	/**
	 * Returns a new object of class '<em>Artifact Download Task</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Artifact Download Task</em>'.
	 * @generated
	 */
	ArtifactDownloadTask createArtifactDownloadTask();

	/**
	 * Returns a new object of class '<em>Artifact Download Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Artifact Download Item</em>'.
	 * @generated
	 */
	ArtifactDownloadItem createArtifactDownloadItem();

	/**
	 * Returns a new object of class '<em>Any Task</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Any Task</em>'.
	 * @generated
	 */
	AnyTask createAnyTask();

	/**
	 * Returns a new object of class '<em>Task Condition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Task Condition</em>'.
	 * @generated
	 */
	TaskCondition createTaskCondition();

	/**
	 * Returns a new object of class '<em>Polling Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Polling Trigger</em>'.
	 * @generated
	 */
	PollingTrigger createPollingTrigger();

	/**
	 * Returns a new object of class '<em>Cron Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Cron Trigger</em>'.
	 * @generated
	 */
	CronTrigger createCronTrigger();

	/**
	 * Returns a new object of class '<em>Remote Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Remote Trigger</em>'.
	 * @generated
	 */
	RemoteTrigger createRemoteTrigger();

	/**
	 * Returns a new object of class '<em>After Deployment Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>After Deployment Trigger</em>'.
	 * @generated
	 */
	AfterDeploymentTrigger createAfterDeploymentTrigger();

	/**
	 * Returns a new object of class '<em>Build Success Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Build Success Trigger</em>'.
	 * @generated
	 */
	BuildSuccessTrigger createBuildSuccessTrigger();

	/**
	 * Returns a new object of class '<em>Stage Success Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Stage Success Trigger</em>'.
	 * @generated
	 */
	StageSuccessTrigger createStageSuccessTrigger();

	/**
	 * Returns a new object of class '<em>Environment Success Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Environment Success Trigger</em>'.
	 * @generated
	 */
	EnvironmentSuccessTrigger createEnvironmentSuccessTrigger();

	/**
	 * Returns a new object of class '<em>Scheduled Deployment Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Scheduled Deployment Trigger</em>'.
	 * @generated
	 */
	ScheduledDeploymentTrigger createScheduledDeploymentTrigger();

	/**
	 * Returns a new object of class '<em>Green Plan Condition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Green Plan Condition</em>'.
	 * @generated
	 */
	GreenPlanCondition createGreenPlanCondition();

	/**
	 * Returns a new object of class '<em>All Other Condition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>All Other Condition</em>'.
	 * @generated
	 */
	AllOtherCondition createAllOtherCondition();

	/**
	 * Returns a new object of class '<em>Linked Repository</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Linked Repository</em>'.
	 * @generated
	 */
	LinkedRepository createLinkedRepository();

	/**
	 * Returns a new object of class '<em>Git Repository</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Git Repository</em>'.
	 * @generated
	 */
	GitRepository createGitRepository();

	/**
	 * Returns a new object of class '<em>Bitbucket Cloud Repository</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Bitbucket Cloud Repository</em>'.
	 * @generated
	 */
	BitbucketCloudRepository createBitbucketCloudRepository();

	/**
	 * Returns a new object of class '<em>Bitbucket Server Repository</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Bitbucket Server Repository</em>'.
	 * @generated
	 */
	BitbucketServerRepository createBitbucketServerRepository();

	/**
	 * Returns a new object of class '<em>Github Repository</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Github Repository</em>'.
	 * @generated
	 */
	GithubRepository createGithubRepository();

	/**
	 * Returns a new object of class '<em>Any Vcs Repository</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Any Vcs Repository</em>'.
	 * @generated
	 */
	AnyVcsRepository createAnyVcsRepository();

	/**
	 * Returns a new object of class '<em>Change Detection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Change Detection</em>'.
	 * @generated
	 */
	ChangeDetection createChangeDetection();

	/**
	 * Returns a new object of class '<em>Quiet Period</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Quiet Period</em>'.
	 * @generated
	 */
	QuietPeriod createQuietPeriod();

	/**
	 * Returns a new object of class '<em>Artifact</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Artifact</em>'.
	 * @generated
	 */
	Artifact createArtifact();

	/**
	 * Returns a new object of class '<em>Artifact Subscription</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Artifact Subscription</em>'.
	 * @generated
	 */
	ArtifactSubscription createArtifactSubscription();

	/**
	 * Returns a new object of class '<em>Requirement</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Requirement</em>'.
	 * @generated
	 */
	Requirement createRequirement();

	/**
	 * Returns a new object of class '<em>Label</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Label</em>'.
	 * @generated
	 */
	Label createLabel();

	/**
	 * Returns a new object of class '<em>Notification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Notification</em>'.
	 * @generated
	 */
	Notification createNotification();

	/**
	 * Returns a new object of class '<em>User Recipient</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>User Recipient</em>'.
	 * @generated
	 */
	UserRecipient createUserRecipient();

	/**
	 * Returns a new object of class '<em>Group Recipient</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Group Recipient</em>'.
	 * @generated
	 */
	GroupRecipient createGroupRecipient();

	/**
	 * Returns a new object of class '<em>Email Recipient</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Email Recipient</em>'.
	 * @generated
	 */
	EmailRecipient createEmailRecipient();

	/**
	 * Returns a new object of class '<em>Role Recipient</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Role Recipient</em>'.
	 * @generated
	 */
	RoleRecipient createRoleRecipient();

	/**
	 * Returns a new object of class '<em>Webhook Recipient</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Webhook Recipient</em>'.
	 * @generated
	 */
	WebhookRecipient createWebhookRecipient();

	/**
	 * Returns a new object of class '<em>Notification Event</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Notification Event</em>'.
	 * @generated
	 */
	NotificationEvent createNotificationEvent();

	/**
	 * Returns a new object of class '<em>Branch Management</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Branch Management</em>'.
	 * @generated
	 */
	BranchManagement createBranchManagement();

	/**
	 * Returns a new object of class '<em>Branch Delete</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Branch Delete</em>'.
	 * @generated
	 */
	BranchDelete createBranchDelete();

	/**
	 * Returns a new object of class '<em>Branch Integration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Branch Integration</em>'.
	 * @generated
	 */
	BranchIntegration createBranchIntegration();

	/**
	 * Returns a new object of class '<em>Branch Override</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Branch Override</em>'.
	 * @generated
	 */
	BranchOverride createBranchOverride();

	/**
	 * Returns a new object of class '<em>Branch Specific Config</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Branch Specific Config</em>'.
	 * @generated
	 */
	BranchSpecificConfig createBranchSpecificConfig();

	/**
	 * Returns a new object of class '<em>Dependencies</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Dependencies</em>'.
	 * @generated
	 */
	Dependencies createDependencies();

	/**
	 * Returns a new object of class '<em>Docker Config</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Docker Config</em>'.
	 * @generated
	 */
	DockerConfig createDockerConfig();

	/**
	 * Returns a new object of class '<em>Other Config</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Other Config</em>'.
	 * @generated
	 */
	OtherConfig createOtherConfig();

	/**
	 * Returns a new object of class '<em>Deployment Project</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Deployment Project</em>'.
	 * @generated
	 */
	DeploymentProject createDeploymentProject();

	/**
	 * Returns a new object of class '<em>Release Naming</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Release Naming</em>'.
	 * @generated
	 */
	ReleaseNaming createReleaseNaming();

	/**
	 * Returns a new object of class '<em>Environment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Environment</em>'.
	 * @generated
	 */
	Environment createEnvironment();

	/**
	 * Returns a new object of class '<em>Plan Permission Entry</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Plan Permission Entry</em>'.
	 * @generated
	 */
	PlanPermissionEntry createPlanPermissionEntry();

	/**
	 * Returns a new object of class '<em>Deployment Permission Entry</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Deployment Permission Entry</em>'.
	 * @generated
	 */
	DeploymentPermissionEntry createDeploymentPermissionEntry();

	/**
	 * Returns a new object of class '<em>Environment Permission Entry</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Environment Permission Entry</em>'.
	 * @generated
	 */
	EnvironmentPermissionEntry createEnvironmentPermissionEntry();

	/**
	 * Returns a new object of class '<em>Named Environment Permission</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Named Environment Permission</em>'.
	 * @generated
	 */
	NamedEnvironmentPermission createNamedEnvironmentPermission();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	BambooPackage getBambooPackage();

} //BambooFactory
