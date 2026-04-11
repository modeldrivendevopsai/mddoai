/**
 */
package com.mddoai.metamodel.bambooMM.impl;

import com.mddoai.metamodel.bambooMM.*;

import java.util.Map;

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
public class BambooFactoryImpl extends EFactoryImpl implements BambooFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BambooFactory init() {
		try {
			BambooFactory theBambooFactory = (BambooFactory) EPackage.Registry.INSTANCE
					.getEFactory(BambooPackage.eNS_URI);
			if (theBambooFactory != null) {
				return theBambooFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new BambooFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BambooFactoryImpl() {
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
		case BambooPackage.PLAN:
			return createPlan();
		case BambooPackage.STAGE:
			return createStage();
		case BambooPackage.JOB:
			return createJob();
		case BambooPackage.SCRIPT_TASK:
			return createScriptTask();
		case BambooPackage.CLEAN_TASK:
			return createCleanTask();
		case BambooPackage.CHECKOUT_TASK:
			return createCheckoutTask();
		case BambooPackage.MAVEN_TASK:
			return createMavenTask();
		case BambooPackage.INJECT_VARIABLES_TASK:
			return createInjectVariablesTask();
		case BambooPackage.TEST_PARSER_TASK:
			return createTestParserTask();
		case BambooPackage.ARTIFACT_DOWNLOAD_TASK:
			return createArtifactDownloadTask();
		case BambooPackage.ARTIFACT_DOWNLOAD_ITEM:
			return createArtifactDownloadItem();
		case BambooPackage.ANY_TASK:
			return createAnyTask();
		case BambooPackage.TASK_CONDITION:
			return createTaskCondition();
		case BambooPackage.POLLING_TRIGGER:
			return createPollingTrigger();
		case BambooPackage.CRON_TRIGGER:
			return createCronTrigger();
		case BambooPackage.REMOTE_TRIGGER:
			return createRemoteTrigger();
		case BambooPackage.AFTER_DEPLOYMENT_TRIGGER:
			return createAfterDeploymentTrigger();
		case BambooPackage.BUILD_SUCCESS_TRIGGER:
			return createBuildSuccessTrigger();
		case BambooPackage.STAGE_SUCCESS_TRIGGER:
			return createStageSuccessTrigger();
		case BambooPackage.ENVIRONMENT_SUCCESS_TRIGGER:
			return createEnvironmentSuccessTrigger();
		case BambooPackage.SCHEDULED_DEPLOYMENT_TRIGGER:
			return createScheduledDeploymentTrigger();
		case BambooPackage.GREEN_PLAN_CONDITION:
			return createGreenPlanCondition();
		case BambooPackage.ALL_OTHER_CONDITION:
			return createAllOtherCondition();
		case BambooPackage.LINKED_REPOSITORY:
			return createLinkedRepository();
		case BambooPackage.GIT_REPOSITORY:
			return createGitRepository();
		case BambooPackage.BITBUCKET_CLOUD_REPOSITORY:
			return createBitbucketCloudRepository();
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY:
			return createBitbucketServerRepository();
		case BambooPackage.GITHUB_REPOSITORY:
			return createGithubRepository();
		case BambooPackage.ANY_VCS_REPOSITORY:
			return createAnyVcsRepository();
		case BambooPackage.CHANGE_DETECTION:
			return createChangeDetection();
		case BambooPackage.QUIET_PERIOD:
			return createQuietPeriod();
		case BambooPackage.ARTIFACT:
			return createArtifact();
		case BambooPackage.ARTIFACT_SUBSCRIPTION:
			return createArtifactSubscription();
		case BambooPackage.REQUIREMENT:
			return createRequirement();
		case BambooPackage.VARIABLE_ASSIGNMENT:
			return (EObject) createVariableAssignment();
		case BambooPackage.LABEL:
			return createLabel();
		case BambooPackage.NOTIFICATION:
			return createNotification();
		case BambooPackage.USER_RECIPIENT:
			return createUserRecipient();
		case BambooPackage.GROUP_RECIPIENT:
			return createGroupRecipient();
		case BambooPackage.EMAIL_RECIPIENT:
			return createEmailRecipient();
		case BambooPackage.ROLE_RECIPIENT:
			return createRoleRecipient();
		case BambooPackage.WEBHOOK_RECIPIENT:
			return createWebhookRecipient();
		case BambooPackage.NOTIFICATION_EVENT:
			return createNotificationEvent();
		case BambooPackage.BRANCH_MANAGEMENT:
			return createBranchManagement();
		case BambooPackage.BRANCH_DELETE:
			return createBranchDelete();
		case BambooPackage.BRANCH_INTEGRATION:
			return createBranchIntegration();
		case BambooPackage.BRANCH_OVERRIDE:
			return createBranchOverride();
		case BambooPackage.BRANCH_SPECIFIC_CONFIG:
			return createBranchSpecificConfig();
		case BambooPackage.DEPENDENCIES:
			return createDependencies();
		case BambooPackage.DOCKER_CONFIG:
			return createDockerConfig();
		case BambooPackage.OTHER_CONFIG:
			return createOtherConfig();
		case BambooPackage.DEPLOYMENT_PROJECT:
			return createDeploymentProject();
		case BambooPackage.RELEASE_NAMING:
			return createReleaseNaming();
		case BambooPackage.ENVIRONMENT:
			return createEnvironment();
		case BambooPackage.PLAN_PERMISSION_ENTRY:
			return createPlanPermissionEntry();
		case BambooPackage.DEPLOYMENT_PERMISSION_ENTRY:
			return createDeploymentPermissionEntry();
		case BambooPackage.ENVIRONMENT_PERMISSION_ENTRY:
			return createEnvironmentPermissionEntry();
		case BambooPackage.NAMED_ENVIRONMENT_PERMISSION:
			return createNamedEnvironmentPermission();
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
		case BambooPackage.VARIABLE_SCOPE:
			return createVARIABLE_SCOPEFromString(eDataType, initialValue);
		case BambooPackage.TEST_PARSER_TYPE:
			return createTEST_PARSER_TYPEFromString(eDataType, initialValue);
		case BambooPackage.REPOSITORY_SCOPE:
			return createREPOSITORY_SCOPEFromString(eDataType, initialValue);
		case BambooPackage.FILE_FILTER_TYPE:
			return createFILE_FILTER_TYPEFromString(eDataType, initialValue);
		case BambooPackage.BRANCH_CREATE_STRATEGY:
			return createBRANCH_CREATE_STRATEGYFromString(eDataType, initialValue);
		case BambooPackage.BLOCK_STRATEGY:
			return createBLOCK_STRATEGYFromString(eDataType, initialValue);
		case BambooPackage.NOTIFICATION_EVENT_TYPE:
			return createNOTIFICATION_EVENT_TYPEFromString(eDataType, initialValue);
		case BambooPackage.NOTIFICATION_ROLE:
			return createNOTIFICATION_ROLEFromString(eDataType, initialValue);
		case BambooPackage.PERMISSION_ROLE:
			return createPERMISSION_ROLEFromString(eDataType, initialValue);
		case BambooPackage.PERMISSION_TYPE:
			return createPERMISSION_TYPEFromString(eDataType, initialValue);
		case BambooPackage.RELEASE_APPROVAL:
			return createRELEASE_APPROVALFromString(eDataType, initialValue);
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
		case BambooPackage.VARIABLE_SCOPE:
			return convertVARIABLE_SCOPEToString(eDataType, instanceValue);
		case BambooPackage.TEST_PARSER_TYPE:
			return convertTEST_PARSER_TYPEToString(eDataType, instanceValue);
		case BambooPackage.REPOSITORY_SCOPE:
			return convertREPOSITORY_SCOPEToString(eDataType, instanceValue);
		case BambooPackage.FILE_FILTER_TYPE:
			return convertFILE_FILTER_TYPEToString(eDataType, instanceValue);
		case BambooPackage.BRANCH_CREATE_STRATEGY:
			return convertBRANCH_CREATE_STRATEGYToString(eDataType, instanceValue);
		case BambooPackage.BLOCK_STRATEGY:
			return convertBLOCK_STRATEGYToString(eDataType, instanceValue);
		case BambooPackage.NOTIFICATION_EVENT_TYPE:
			return convertNOTIFICATION_EVENT_TYPEToString(eDataType, instanceValue);
		case BambooPackage.NOTIFICATION_ROLE:
			return convertNOTIFICATION_ROLEToString(eDataType, instanceValue);
		case BambooPackage.PERMISSION_ROLE:
			return convertPERMISSION_ROLEToString(eDataType, instanceValue);
		case BambooPackage.PERMISSION_TYPE:
			return convertPERMISSION_TYPEToString(eDataType, instanceValue);
		case BambooPackage.RELEASE_APPROVAL:
			return convertRELEASE_APPROVALToString(eDataType, instanceValue);
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
	public Plan createPlan() {
		PlanImpl plan = new PlanImpl();
		return plan;
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
	public ScriptTask createScriptTask() {
		ScriptTaskImpl scriptTask = new ScriptTaskImpl();
		return scriptTask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CleanTask createCleanTask() {
		CleanTaskImpl cleanTask = new CleanTaskImpl();
		return cleanTask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CheckoutTask createCheckoutTask() {
		CheckoutTaskImpl checkoutTask = new CheckoutTaskImpl();
		return checkoutTask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MavenTask createMavenTask() {
		MavenTaskImpl mavenTask = new MavenTaskImpl();
		return mavenTask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InjectVariablesTask createInjectVariablesTask() {
		InjectVariablesTaskImpl injectVariablesTask = new InjectVariablesTaskImpl();
		return injectVariablesTask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TestParserTask createTestParserTask() {
		TestParserTaskImpl testParserTask = new TestParserTaskImpl();
		return testParserTask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ArtifactDownloadTask createArtifactDownloadTask() {
		ArtifactDownloadTaskImpl artifactDownloadTask = new ArtifactDownloadTaskImpl();
		return artifactDownloadTask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ArtifactDownloadItem createArtifactDownloadItem() {
		ArtifactDownloadItemImpl artifactDownloadItem = new ArtifactDownloadItemImpl();
		return artifactDownloadItem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AnyTask createAnyTask() {
		AnyTaskImpl anyTask = new AnyTaskImpl();
		return anyTask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TaskCondition createTaskCondition() {
		TaskConditionImpl taskCondition = new TaskConditionImpl();
		return taskCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PollingTrigger createPollingTrigger() {
		PollingTriggerImpl pollingTrigger = new PollingTriggerImpl();
		return pollingTrigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CronTrigger createCronTrigger() {
		CronTriggerImpl cronTrigger = new CronTriggerImpl();
		return cronTrigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RemoteTrigger createRemoteTrigger() {
		RemoteTriggerImpl remoteTrigger = new RemoteTriggerImpl();
		return remoteTrigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AfterDeploymentTrigger createAfterDeploymentTrigger() {
		AfterDeploymentTriggerImpl afterDeploymentTrigger = new AfterDeploymentTriggerImpl();
		return afterDeploymentTrigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BuildSuccessTrigger createBuildSuccessTrigger() {
		BuildSuccessTriggerImpl buildSuccessTrigger = new BuildSuccessTriggerImpl();
		return buildSuccessTrigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StageSuccessTrigger createStageSuccessTrigger() {
		StageSuccessTriggerImpl stageSuccessTrigger = new StageSuccessTriggerImpl();
		return stageSuccessTrigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EnvironmentSuccessTrigger createEnvironmentSuccessTrigger() {
		EnvironmentSuccessTriggerImpl environmentSuccessTrigger = new EnvironmentSuccessTriggerImpl();
		return environmentSuccessTrigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScheduledDeploymentTrigger createScheduledDeploymentTrigger() {
		ScheduledDeploymentTriggerImpl scheduledDeploymentTrigger = new ScheduledDeploymentTriggerImpl();
		return scheduledDeploymentTrigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GreenPlanCondition createGreenPlanCondition() {
		GreenPlanConditionImpl greenPlanCondition = new GreenPlanConditionImpl();
		return greenPlanCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AllOtherCondition createAllOtherCondition() {
		AllOtherConditionImpl allOtherCondition = new AllOtherConditionImpl();
		return allOtherCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LinkedRepository createLinkedRepository() {
		LinkedRepositoryImpl linkedRepository = new LinkedRepositoryImpl();
		return linkedRepository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GitRepository createGitRepository() {
		GitRepositoryImpl gitRepository = new GitRepositoryImpl();
		return gitRepository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BitbucketCloudRepository createBitbucketCloudRepository() {
		BitbucketCloudRepositoryImpl bitbucketCloudRepository = new BitbucketCloudRepositoryImpl();
		return bitbucketCloudRepository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BitbucketServerRepository createBitbucketServerRepository() {
		BitbucketServerRepositoryImpl bitbucketServerRepository = new BitbucketServerRepositoryImpl();
		return bitbucketServerRepository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GithubRepository createGithubRepository() {
		GithubRepositoryImpl githubRepository = new GithubRepositoryImpl();
		return githubRepository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AnyVcsRepository createAnyVcsRepository() {
		AnyVcsRepositoryImpl anyVcsRepository = new AnyVcsRepositoryImpl();
		return anyVcsRepository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ChangeDetection createChangeDetection() {
		ChangeDetectionImpl changeDetection = new ChangeDetectionImpl();
		return changeDetection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public QuietPeriod createQuietPeriod() {
		QuietPeriodImpl quietPeriod = new QuietPeriodImpl();
		return quietPeriod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Artifact createArtifact() {
		ArtifactImpl artifact = new ArtifactImpl();
		return artifact;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ArtifactSubscription createArtifactSubscription() {
		ArtifactSubscriptionImpl artifactSubscription = new ArtifactSubscriptionImpl();
		return artifactSubscription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Requirement createRequirement() {
		RequirementImpl requirement = new RequirementImpl();
		return requirement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, String> createVariableAssignment() {
		VariableAssignmentImpl variableAssignment = new VariableAssignmentImpl();
		return variableAssignment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Label createLabel() {
		LabelImpl label = new LabelImpl();
		return label;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Notification createNotification() {
		NotificationImpl notification = new NotificationImpl();
		return notification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public UserRecipient createUserRecipient() {
		UserRecipientImpl userRecipient = new UserRecipientImpl();
		return userRecipient;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GroupRecipient createGroupRecipient() {
		GroupRecipientImpl groupRecipient = new GroupRecipientImpl();
		return groupRecipient;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EmailRecipient createEmailRecipient() {
		EmailRecipientImpl emailRecipient = new EmailRecipientImpl();
		return emailRecipient;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RoleRecipient createRoleRecipient() {
		RoleRecipientImpl roleRecipient = new RoleRecipientImpl();
		return roleRecipient;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WebhookRecipient createWebhookRecipient() {
		WebhookRecipientImpl webhookRecipient = new WebhookRecipientImpl();
		return webhookRecipient;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationEvent createNotificationEvent() {
		NotificationEventImpl notificationEvent = new NotificationEventImpl();
		return notificationEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BranchManagement createBranchManagement() {
		BranchManagementImpl branchManagement = new BranchManagementImpl();
		return branchManagement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BranchDelete createBranchDelete() {
		BranchDeleteImpl branchDelete = new BranchDeleteImpl();
		return branchDelete;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BranchIntegration createBranchIntegration() {
		BranchIntegrationImpl branchIntegration = new BranchIntegrationImpl();
		return branchIntegration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BranchOverride createBranchOverride() {
		BranchOverrideImpl branchOverride = new BranchOverrideImpl();
		return branchOverride;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BranchSpecificConfig createBranchSpecificConfig() {
		BranchSpecificConfigImpl branchSpecificConfig = new BranchSpecificConfigImpl();
		return branchSpecificConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Dependencies createDependencies() {
		DependenciesImpl dependencies = new DependenciesImpl();
		return dependencies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DockerConfig createDockerConfig() {
		DockerConfigImpl dockerConfig = new DockerConfigImpl();
		return dockerConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OtherConfig createOtherConfig() {
		OtherConfigImpl otherConfig = new OtherConfigImpl();
		return otherConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DeploymentProject createDeploymentProject() {
		DeploymentProjectImpl deploymentProject = new DeploymentProjectImpl();
		return deploymentProject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ReleaseNaming createReleaseNaming() {
		ReleaseNamingImpl releaseNaming = new ReleaseNamingImpl();
		return releaseNaming;
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
	public PlanPermissionEntry createPlanPermissionEntry() {
		PlanPermissionEntryImpl planPermissionEntry = new PlanPermissionEntryImpl();
		return planPermissionEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DeploymentPermissionEntry createDeploymentPermissionEntry() {
		DeploymentPermissionEntryImpl deploymentPermissionEntry = new DeploymentPermissionEntryImpl();
		return deploymentPermissionEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EnvironmentPermissionEntry createEnvironmentPermissionEntry() {
		EnvironmentPermissionEntryImpl environmentPermissionEntry = new EnvironmentPermissionEntryImpl();
		return environmentPermissionEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NamedEnvironmentPermission createNamedEnvironmentPermission() {
		NamedEnvironmentPermissionImpl namedEnvironmentPermission = new NamedEnvironmentPermissionImpl();
		return namedEnvironmentPermission;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VARIABLE_SCOPE createVARIABLE_SCOPEFromString(EDataType eDataType, String initialValue) {
		VARIABLE_SCOPE result = VARIABLE_SCOPE.get(initialValue);
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
	public String convertVARIABLE_SCOPEToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TEST_PARSER_TYPE createTEST_PARSER_TYPEFromString(EDataType eDataType, String initialValue) {
		TEST_PARSER_TYPE result = TEST_PARSER_TYPE.get(initialValue);
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
	public String convertTEST_PARSER_TYPEToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public REPOSITORY_SCOPE createREPOSITORY_SCOPEFromString(EDataType eDataType, String initialValue) {
		REPOSITORY_SCOPE result = REPOSITORY_SCOPE.get(initialValue);
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
	public String convertREPOSITORY_SCOPEToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FILE_FILTER_TYPE createFILE_FILTER_TYPEFromString(EDataType eDataType, String initialValue) {
		FILE_FILTER_TYPE result = FILE_FILTER_TYPE.get(initialValue);
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
	public String convertFILE_FILTER_TYPEToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BRANCH_CREATE_STRATEGY createBRANCH_CREATE_STRATEGYFromString(EDataType eDataType, String initialValue) {
		BRANCH_CREATE_STRATEGY result = BRANCH_CREATE_STRATEGY.get(initialValue);
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
	public String convertBRANCH_CREATE_STRATEGYToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BLOCK_STRATEGY createBLOCK_STRATEGYFromString(EDataType eDataType, String initialValue) {
		BLOCK_STRATEGY result = BLOCK_STRATEGY.get(initialValue);
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
	public String convertBLOCK_STRATEGYToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NOTIFICATION_EVENT_TYPE createNOTIFICATION_EVENT_TYPEFromString(EDataType eDataType, String initialValue) {
		NOTIFICATION_EVENT_TYPE result = NOTIFICATION_EVENT_TYPE.get(initialValue);
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
	public String convertNOTIFICATION_EVENT_TYPEToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NOTIFICATION_ROLE createNOTIFICATION_ROLEFromString(EDataType eDataType, String initialValue) {
		NOTIFICATION_ROLE result = NOTIFICATION_ROLE.get(initialValue);
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
	public String convertNOTIFICATION_ROLEToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PERMISSION_ROLE createPERMISSION_ROLEFromString(EDataType eDataType, String initialValue) {
		PERMISSION_ROLE result = PERMISSION_ROLE.get(initialValue);
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
	public String convertPERMISSION_ROLEToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PERMISSION_TYPE createPERMISSION_TYPEFromString(EDataType eDataType, String initialValue) {
		PERMISSION_TYPE result = PERMISSION_TYPE.get(initialValue);
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
	public String convertPERMISSION_TYPEToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RELEASE_APPROVAL createRELEASE_APPROVALFromString(EDataType eDataType, String initialValue) {
		RELEASE_APPROVAL result = RELEASE_APPROVAL.get(initialValue);
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
	public String convertRELEASE_APPROVALToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BambooPackage getBambooPackage() {
		return (BambooPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static BambooPackage getPackage() {
		return BambooPackage.eINSTANCE;
	}

} //BambooFactoryImpl
