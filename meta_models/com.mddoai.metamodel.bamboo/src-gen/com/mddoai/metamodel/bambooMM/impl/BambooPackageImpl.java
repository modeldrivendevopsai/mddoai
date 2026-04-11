/**
 */
package com.mddoai.metamodel.bambooMM.impl;

import com.mddoai.metamodel.bambooMM.AfterDeploymentTrigger;
import com.mddoai.metamodel.bambooMM.AllOtherCondition;
import com.mddoai.metamodel.bambooMM.AnyTask;
import com.mddoai.metamodel.bambooMM.AnyVcsRepository;
import com.mddoai.metamodel.bambooMM.Artifact;
import com.mddoai.metamodel.bambooMM.ArtifactDownloadItem;
import com.mddoai.metamodel.bambooMM.ArtifactDownloadTask;
import com.mddoai.metamodel.bambooMM.ArtifactSubscription;
import com.mddoai.metamodel.bambooMM.BambooFactory;
import com.mddoai.metamodel.bambooMM.BambooPackage;
import com.mddoai.metamodel.bambooMM.BitbucketCloudRepository;
import com.mddoai.metamodel.bambooMM.BitbucketServerRepository;
import com.mddoai.metamodel.bambooMM.BranchDelete;
import com.mddoai.metamodel.bambooMM.BranchIntegration;
import com.mddoai.metamodel.bambooMM.BranchManagement;
import com.mddoai.metamodel.bambooMM.BranchOverride;
import com.mddoai.metamodel.bambooMM.BranchSpecificConfig;
import com.mddoai.metamodel.bambooMM.BuildSuccessTrigger;
import com.mddoai.metamodel.bambooMM.ChangeDetection;
import com.mddoai.metamodel.bambooMM.CheckoutTask;
import com.mddoai.metamodel.bambooMM.CleanTask;
import com.mddoai.metamodel.bambooMM.CronTrigger;
import com.mddoai.metamodel.bambooMM.Dependencies;
import com.mddoai.metamodel.bambooMM.DeploymentPermissionEntry;
import com.mddoai.metamodel.bambooMM.DeploymentProject;
import com.mddoai.metamodel.bambooMM.DockerConfig;
import com.mddoai.metamodel.bambooMM.EmailRecipient;
import com.mddoai.metamodel.bambooMM.Environment;
import com.mddoai.metamodel.bambooMM.EnvironmentPermissionEntry;
import com.mddoai.metamodel.bambooMM.EnvironmentSuccessTrigger;
import com.mddoai.metamodel.bambooMM.GitRepository;
import com.mddoai.metamodel.bambooMM.GithubRepository;
import com.mddoai.metamodel.bambooMM.GreenPlanCondition;
import com.mddoai.metamodel.bambooMM.GroupRecipient;
import com.mddoai.metamodel.bambooMM.InjectVariablesTask;
import com.mddoai.metamodel.bambooMM.Job;
import com.mddoai.metamodel.bambooMM.Label;
import com.mddoai.metamodel.bambooMM.LinkedRepository;
import com.mddoai.metamodel.bambooMM.MavenTask;
import com.mddoai.metamodel.bambooMM.NamedEnvironmentPermission;
import com.mddoai.metamodel.bambooMM.Notification;
import com.mddoai.metamodel.bambooMM.NotificationEvent;
import com.mddoai.metamodel.bambooMM.NotificationRecipient;
import com.mddoai.metamodel.bambooMM.OtherConfig;
import com.mddoai.metamodel.bambooMM.PermissionEntry;
import com.mddoai.metamodel.bambooMM.Plan;
import com.mddoai.metamodel.bambooMM.PlanPermissionEntry;
import com.mddoai.metamodel.bambooMM.PollingTrigger;
import com.mddoai.metamodel.bambooMM.QuietPeriod;
import com.mddoai.metamodel.bambooMM.ReleaseNaming;
import com.mddoai.metamodel.bambooMM.RemoteTrigger;
import com.mddoai.metamodel.bambooMM.Repository;
import com.mddoai.metamodel.bambooMM.Requirement;
import com.mddoai.metamodel.bambooMM.RoleRecipient;
import com.mddoai.metamodel.bambooMM.ScheduledDeploymentTrigger;
import com.mddoai.metamodel.bambooMM.ScriptTask;
import com.mddoai.metamodel.bambooMM.Stage;
import com.mddoai.metamodel.bambooMM.StageSuccessTrigger;
import com.mddoai.metamodel.bambooMM.Task;
import com.mddoai.metamodel.bambooMM.TaskCondition;
import com.mddoai.metamodel.bambooMM.TestParserTask;
import com.mddoai.metamodel.bambooMM.Trigger;
import com.mddoai.metamodel.bambooMM.TriggerCondition;
import com.mddoai.metamodel.bambooMM.UserRecipient;
import com.mddoai.metamodel.bambooMM.WebhookRecipient;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BambooPackageImpl extends EPackageImpl implements BambooPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass planEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass jobEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass taskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scriptTaskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cleanTaskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass checkoutTaskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mavenTaskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass injectVariablesTaskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testParserTaskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass artifactDownloadTaskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass artifactDownloadItemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass anyTaskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass taskConditionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass triggerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pollingTriggerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cronTriggerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass remoteTriggerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass afterDeploymentTriggerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass buildSuccessTriggerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stageSuccessTriggerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass environmentSuccessTriggerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scheduledDeploymentTriggerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass triggerConditionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass greenPlanConditionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass allOtherConditionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass repositoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass linkedRepositoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass gitRepositoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass bitbucketCloudRepositoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass bitbucketServerRepositoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass githubRepositoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass anyVcsRepositoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass changeDetectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass quietPeriodEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass artifactEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass artifactSubscriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass requirementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variableAssignmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass labelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass notificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass notificationRecipientEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass userRecipientEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass groupRecipientEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass emailRecipientEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass roleRecipientEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass webhookRecipientEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass notificationEventEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass branchManagementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass branchDeleteEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass branchIntegrationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass branchOverrideEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass branchSpecificConfigEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dependenciesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dockerConfigEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass otherConfigEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deploymentProjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass releaseNamingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass environmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass permissionEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass planPermissionEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deploymentPermissionEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass environmentPermissionEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namedEnvironmentPermissionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum variablE_SCOPEEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum tesT_PARSER_TYPEEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum repositorY_SCOPEEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum filE_FILTER_TYPEEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum brancH_CREATE_STRATEGYEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum blocK_STRATEGYEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum notificatioN_EVENT_TYPEEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum notificatioN_ROLEEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum permissioN_ROLEEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum permissioN_TYPEEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum releasE_APPROVALEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private BambooPackageImpl() {
		super(eNS_URI, BambooFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link BambooPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static BambooPackage init() {
		if (isInited)
			return (BambooPackage) EPackage.Registry.INSTANCE.getEPackage(BambooPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredBambooPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		BambooPackageImpl theBambooPackage = registeredBambooPackage instanceof BambooPackageImpl
				? (BambooPackageImpl) registeredBambooPackage
				: new BambooPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theBambooPackage.createPackageContents();

		// Initialize created meta-data
		theBambooPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theBambooPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(BambooPackage.eNS_URI, theBambooPackage);
		return theBambooPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPlan() {
		return planEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPlan_ProjectKey() {
		return (EAttribute) planEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPlan_Key() {
		return (EAttribute) planEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPlan_Name() {
		return (EAttribute) planEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPlan_ServerName() {
		return (EAttribute) planEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPlan_Stages() {
		return (EReference) planEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPlan_Jobs() {
		return (EReference) planEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPlan_Triggers() {
		return (EReference) planEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPlan_Repositories() {
		return (EReference) planEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPlan_Variables() {
		return (EReference) planEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPlan_Notifications() {
		return (EReference) planEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPlan_Labels() {
		return (EReference) planEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPlan_Branches() {
		return (EReference) planEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPlan_Dependencies() {
		return (EReference) planEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPlan_Docker() {
		return (EReference) planEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPlan_BranchOverrides() {
		return (EReference) planEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPlan_Other() {
		return (EReference) planEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPlan_Permissions() {
		return (EReference) planEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStage() {
		return stageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStage_Name() {
		return (EAttribute) stageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStage_Manual() {
		return (EAttribute) stageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStage_FinalStage() {
		return (EAttribute) stageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStage_JobRefs() {
		return (EReference) stageEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getJob() {
		return jobEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getJob_Name() {
		return (EAttribute) jobEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getJob_Key() {
		return (EAttribute) jobEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getJob_Tasks() {
		return (EReference) jobEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getJob_FinalTasks() {
		return (EReference) jobEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getJob_Artifacts() {
		return (EReference) jobEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getJob_ArtifactSubscriptions() {
		return (EReference) jobEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getJob_Requirements() {
		return (EReference) jobEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getJob_Variables() {
		return (EReference) jobEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getJob_Docker() {
		return (EReference) jobEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getJob_Other() {
		return (EReference) jobEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTask() {
		return taskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTask_Conditions() {
		return (EReference) taskEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScriptTask() {
		return scriptTaskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScriptTask_Interpreter() {
		return (EAttribute) scriptTaskEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScriptTask_Scripts() {
		return (EAttribute) scriptTaskEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScriptTask_File() {
		return (EAttribute) scriptTaskEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScriptTask_Argument() {
		return (EAttribute) scriptTaskEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScriptTask_Environment() {
		return (EAttribute) scriptTaskEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScriptTask_WorkingDir() {
		return (EAttribute) scriptTaskEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCleanTask() {
		return cleanTaskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCheckoutTask() {
		return checkoutTaskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCheckoutTask_Repository() {
		return (EAttribute) checkoutTaskEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCheckoutTask_Path() {
		return (EAttribute) checkoutTaskEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCheckoutTask_ForceCleanBuild() {
		return (EAttribute) checkoutTaskEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCheckoutTask_DefaultRepository() {
		return (EAttribute) checkoutTaskEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMavenTask() {
		return mavenTaskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMavenTask_Executable() {
		return (EAttribute) mavenTaskEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMavenTask_Jdk() {
		return (EAttribute) mavenTaskEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMavenTask_Goal() {
		return (EAttribute) mavenTaskEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMavenTask_Tests() {
		return (EAttribute) mavenTaskEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMavenTask_Environment() {
		return (EAttribute) mavenTaskEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMavenTask_WorkingDir() {
		return (EAttribute) mavenTaskEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMavenTask_ProjectFile() {
		return (EAttribute) mavenTaskEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMavenTask_UseReturnCode() {
		return (EAttribute) mavenTaskEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getInjectVariablesTask() {
		return injectVariablesTaskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInjectVariablesTask_File() {
		return (EAttribute) injectVariablesTaskEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInjectVariablesTask_Scope() {
		return (EAttribute) injectVariablesTaskEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInjectVariablesTask_Namespace() {
		return (EAttribute) injectVariablesTaskEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTestParserTask() {
		return testParserTaskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTestParserTask_Type() {
		return (EAttribute) testParserTaskEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTestParserTask_TestResults() {
		return (EAttribute) testParserTaskEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTestParserTask_IgnoreTime() {
		return (EAttribute) testParserTaskEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getArtifactDownloadTask() {
		return artifactDownloadTaskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getArtifactDownloadTask_SourcePlan() {
		return (EAttribute) artifactDownloadTaskEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getArtifactDownloadTask_Destination() {
		return (EAttribute) artifactDownloadTaskEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getArtifactDownloadTask_Artifacts() {
		return (EReference) artifactDownloadTaskEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getArtifactDownloadItem() {
		return artifactDownloadItemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getArtifactDownloadItem_Name() {
		return (EAttribute) artifactDownloadItemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getArtifactDownloadItem_Destination() {
		return (EAttribute) artifactDownloadItemEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAnyTask() {
		return anyTaskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAnyTask_PluginKey() {
		return (EAttribute) anyTaskEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAnyTask_Configuration() {
		return (EReference) anyTaskEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTaskCondition() {
		return taskConditionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTaskCondition_VariableName() {
		return (EAttribute) taskConditionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTaskCondition_VariableValue() {
		return (EAttribute) taskConditionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTrigger() {
		return triggerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPollingTrigger() {
		return pollingTriggerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPollingTrigger_Period() {
		return (EAttribute) pollingTriggerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPollingTrigger_CronExpression() {
		return (EAttribute) pollingTriggerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPollingTrigger_Repositories() {
		return (EAttribute) pollingTriggerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPollingTrigger_Conditions() {
		return (EReference) pollingTriggerEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCronTrigger() {
		return cronTriggerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCronTrigger_Expression() {
		return (EAttribute) cronTriggerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRemoteTrigger() {
		return remoteTriggerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRemoteTrigger_Ip() {
		return (EAttribute) remoteTriggerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAfterDeploymentTrigger() {
		return afterDeploymentTriggerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAfterDeploymentTrigger_DeploymentProject() {
		return (EAttribute) afterDeploymentTriggerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAfterDeploymentTrigger_Environment() {
		return (EAttribute) afterDeploymentTriggerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBuildSuccessTrigger() {
		return buildSuccessTriggerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBuildSuccessTrigger_Branch() {
		return (EAttribute) buildSuccessTriggerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStageSuccessTrigger() {
		return stageSuccessTriggerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStageSuccessTrigger_Stage() {
		return (EAttribute) stageSuccessTriggerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStageSuccessTrigger_Branch() {
		return (EAttribute) stageSuccessTriggerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEnvironmentSuccessTrigger() {
		return environmentSuccessTriggerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEnvironmentSuccessTrigger_Environment() {
		return (EAttribute) environmentSuccessTriggerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScheduledDeploymentTrigger() {
		return scheduledDeploymentTriggerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScheduledDeploymentTrigger_CronExpression() {
		return (EAttribute) scheduledDeploymentTriggerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScheduledDeploymentTrigger_ArtifactBranch() {
		return (EAttribute) scheduledDeploymentTriggerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTriggerCondition() {
		return triggerConditionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getGreenPlanCondition() {
		return greenPlanConditionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGreenPlanCondition_PlanKeys() {
		return (EAttribute) greenPlanConditionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAllOtherCondition() {
		return allOtherConditionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAllOtherCondition_Properties() {
		return (EReference) allOtherConditionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRepository() {
		return repositoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRepository_Name() {
		return (EAttribute) repositoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLinkedRepository() {
		return linkedRepositoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLinkedRepository_Scope() {
		return (EAttribute) linkedRepositoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getGitRepository() {
		return gitRepositoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGitRepository_Url() {
		return (EAttribute) gitRepositoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGitRepository_Branch() {
		return (EAttribute) gitRepositoryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGitRepository_Username() {
		return (EAttribute) gitRepositoryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGitRepository_Password() {
		return (EAttribute) gitRepositoryEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGitRepository_SshKey() {
		return (EAttribute) gitRepositoryEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGitRepository_SshKeyPassphrase() {
		return (EAttribute) gitRepositoryEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGitRepository_SharedCredentials() {
		return (EAttribute) gitRepositoryEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGitRepository_SharedCredentialsScope() {
		return (EAttribute) gitRepositoryEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGitRepository_Lfs() {
		return (EAttribute) gitRepositoryEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGitRepository_UseShallowClones() {
		return (EAttribute) gitRepositoryEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGitRepository_Submodules() {
		return (EAttribute) gitRepositoryEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGitRepository_FetchAll() {
		return (EAttribute) gitRepositoryEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGitRepository_CacheOnAgents() {
		return (EAttribute) gitRepositoryEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGitRepository_VerboseLogs() {
		return (EAttribute) gitRepositoryEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGitRepository_CommandTimeoutMinutes() {
		return (EAttribute) gitRepositoryEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getGitRepository_ChangeDetection() {
		return (EReference) gitRepositoryEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBitbucketCloudRepository() {
		return bitbucketCloudRepositoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBitbucketCloudRepository_Slug() {
		return (EAttribute) bitbucketCloudRepositoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBitbucketServerRepository() {
		return bitbucketServerRepositoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBitbucketServerRepository_Server() {
		return (EAttribute) bitbucketServerRepositoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBitbucketServerRepository_Project() {
		return (EAttribute) bitbucketServerRepositoryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBitbucketServerRepository_Slug() {
		return (EAttribute) bitbucketServerRepositoryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBitbucketServerRepository_CloneUrl() {
		return (EAttribute) bitbucketServerRepositoryEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBitbucketServerRepository_PublicKey() {
		return (EAttribute) bitbucketServerRepositoryEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBitbucketServerRepository_PrivateKey() {
		return (EAttribute) bitbucketServerRepositoryEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBitbucketServerRepository_Branch() {
		return (EAttribute) bitbucketServerRepositoryEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBitbucketServerRepository_Lfs() {
		return (EAttribute) bitbucketServerRepositoryEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBitbucketServerRepository_UseShallowClones() {
		return (EAttribute) bitbucketServerRepositoryEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBitbucketServerRepository_Submodules() {
		return (EAttribute) bitbucketServerRepositoryEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBitbucketServerRepository_CommandTimeoutMinutes() {
		return (EAttribute) bitbucketServerRepositoryEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBitbucketServerRepository_FetchAll() {
		return (EAttribute) bitbucketServerRepositoryEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getGithubRepository() {
		return githubRepositoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGithubRepository_Repository() {
		return (EAttribute) githubRepositoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGithubRepository_User() {
		return (EAttribute) githubRepositoryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAnyVcsRepository() {
		return anyVcsRepositoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAnyVcsRepository_PluginKey() {
		return (EAttribute) anyVcsRepositoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAnyVcsRepository_ServerConfig() {
		return (EReference) anyVcsRepositoryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAnyVcsRepository_BranchConfig() {
		return (EReference) anyVcsRepositoryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getChangeDetection() {
		return changeDetectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getChangeDetection_QuietPeriod() {
		return (EReference) changeDetectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getChangeDetection_ExcludeChangesetPattern() {
		return (EAttribute) changeDetectionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getChangeDetection_FileFilterType() {
		return (EAttribute) changeDetectionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getChangeDetection_FileFilterPattern() {
		return (EAttribute) changeDetectionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getQuietPeriod() {
		return quietPeriodEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getQuietPeriod_QuietPeriodSeconds() {
		return (EAttribute) quietPeriodEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getQuietPeriod_MaxRetries() {
		return (EAttribute) quietPeriodEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getArtifact() {
		return artifactEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getArtifact_Name() {
		return (EAttribute) artifactEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getArtifact_Location() {
		return (EAttribute) artifactEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getArtifact_Pattern() {
		return (EAttribute) artifactEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getArtifact_Required() {
		return (EAttribute) artifactEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getArtifact_Shared() {
		return (EAttribute) artifactEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getArtifact_HttpCompressionOn() {
		return (EAttribute) artifactEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getArtifactSubscription() {
		return artifactSubscriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getArtifactSubscription_Artifact() {
		return (EAttribute) artifactSubscriptionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getArtifactSubscription_Destination() {
		return (EAttribute) artifactSubscriptionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRequirement() {
		return requirementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRequirement_Capability() {
		return (EAttribute) requirementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRequirement_MatchValue() {
		return (EAttribute) requirementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getVariableAssignment() {
		return variableAssignmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getVariableAssignment_Key() {
		return (EAttribute) variableAssignmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getVariableAssignment_Value() {
		return (EAttribute) variableAssignmentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLabel() {
		return labelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLabel_Value() {
		return (EAttribute) labelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNotification() {
		return notificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getNotification_Recipients() {
		return (EReference) notificationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getNotification_Events() {
		return (EReference) notificationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNotificationRecipient() {
		return notificationRecipientEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getUserRecipient() {
		return userRecipientEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getUserRecipient_Users() {
		return (EAttribute) userRecipientEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getGroupRecipient() {
		return groupRecipientEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGroupRecipient_Groups() {
		return (EAttribute) groupRecipientEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEmailRecipient() {
		return emailRecipientEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEmailRecipient_Emails() {
		return (EAttribute) emailRecipientEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRoleRecipient() {
		return roleRecipientEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRoleRecipient_Role() {
		return (EAttribute) roleRecipientEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getWebhookRecipient() {
		return webhookRecipientEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWebhookRecipient_Name() {
		return (EAttribute) webhookRecipientEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWebhookRecipient_Url() {
		return (EAttribute) webhookRecipientEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNotificationEvent() {
		return notificationEventEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getNotificationEvent_Type() {
		return (EAttribute) notificationEventEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getNotificationEvent_Failures() {
		return (EAttribute) notificationEventEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getNotificationEvent_FirstOnly() {
		return (EAttribute) notificationEventEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBranchManagement() {
		return branchManagementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBranchManagement_Create() {
		return (EAttribute) branchManagementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBranchManagement_CreatePattern() {
		return (EAttribute) branchManagementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBranchManagement_AcceptFork() {
		return (EAttribute) branchManagementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getBranchManagement_Delete() {
		return (EReference) branchManagementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getBranchManagement_Integration() {
		return (EReference) branchManagementEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBranchManagement_LinkToJira() {
		return (EAttribute) branchManagementEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBranchDelete() {
		return branchDeleteEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBranchDelete_AfterDeletedDays() {
		return (EAttribute) branchDeleteEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBranchDelete_AfterInactiveDays() {
		return (EAttribute) branchDeleteEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBranchIntegration() {
		return branchIntegrationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBranchIntegration_MergeFrom() {
		return (EAttribute) branchIntegrationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBranchIntegration_MergeTo() {
		return (EAttribute) branchIntegrationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBranchIntegration_PushOnSuccess() {
		return (EAttribute) branchIntegrationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBranchOverride() {
		return branchOverrideEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBranchOverride_BranchPattern() {
		return (EAttribute) branchOverrideEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getBranchOverride_Stages() {
		return (EReference) branchOverrideEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getBranchOverride_Jobs() {
		return (EReference) branchOverrideEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getBranchOverride_Triggers() {
		return (EReference) branchOverrideEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getBranchOverride_Notifications() {
		return (EReference) branchOverrideEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getBranchOverride_Variables() {
		return (EReference) branchOverrideEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getBranchOverride_Labels() {
		return (EReference) branchOverrideEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getBranchOverride_Docker() {
		return (EReference) branchOverrideEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getBranchOverride_BranchConfig() {
		return (EReference) branchOverrideEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBranchSpecificConfig() {
		return branchSpecificConfigEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getBranchSpecificConfig_Integration() {
		return (EReference) branchSpecificConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBranchSpecificConfig_DisableExpiry() {
		return (EAttribute) branchSpecificConfigEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDependencies() {
		return dependenciesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDependencies_RequireAllStagesPassing() {
		return (EAttribute) dependenciesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDependencies_EnabledForBranches() {
		return (EAttribute) dependenciesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDependencies_BlockStrategy() {
		return (EAttribute) dependenciesEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDependencies_Plans() {
		return (EAttribute) dependenciesEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDockerConfig() {
		return dockerConfigEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDockerConfig_Image() {
		return (EAttribute) dockerConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDockerConfig_Volumes() {
		return (EReference) dockerConfigEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDockerConfig_UseDefaultVolumes() {
		return (EAttribute) dockerConfigEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDockerConfig_DockerRunArguments() {
		return (EAttribute) dockerConfigEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getOtherConfig() {
		return otherConfigEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getOtherConfig_ConcurrentBuildPlugin() {
		return (EAttribute) otherConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getOtherConfig_CleanWorkingDir() {
		return (EAttribute) otherConfigEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOtherConfig_AllOtherApps() {
		return (EReference) otherConfigEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDeploymentProject() {
		return deploymentProjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDeploymentProject_Name() {
		return (EAttribute) deploymentProjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDeploymentProject_SourcePlan() {
		return (EAttribute) deploymentProjectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDeploymentProject_ServerName() {
		return (EAttribute) deploymentProjectEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDeploymentProject_ReleaseNaming() {
		return (EReference) deploymentProjectEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDeploymentProject_Environments() {
		return (EReference) deploymentProjectEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDeploymentProject_Permissions() {
		return (EReference) deploymentProjectEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDeploymentProject_DefaultEnvironmentPermissions() {
		return (EReference) deploymentProjectEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDeploymentProject_EnvironmentPermissions() {
		return (EReference) deploymentProjectEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getReleaseNaming() {
		return releaseNamingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReleaseNaming_NextVersionName() {
		return (EAttribute) releaseNamingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReleaseNaming_AppliesToBranches() {
		return (EAttribute) releaseNamingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReleaseNaming_AutoIncrement() {
		return (EAttribute) releaseNamingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReleaseNaming_AutoIncrementVariables() {
		return (EAttribute) releaseNamingEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEnvironment() {
		return environmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEnvironment_Name() {
		return (EAttribute) environmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEnvironment_Tasks() {
		return (EReference) environmentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEnvironment_FinalTasks() {
		return (EReference) environmentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEnvironment_Variables() {
		return (EReference) environmentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEnvironment_Triggers() {
		return (EReference) environmentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEnvironment_Notifications() {
		return (EReference) environmentEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEnvironment_Docker() {
		return (EReference) environmentEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEnvironment_Requirements() {
		return (EReference) environmentEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEnvironment_ReleaseApprovalPrerequisite() {
		return (EAttribute) environmentEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPermissionEntry() {
		return permissionEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPermissionEntry_Users() {
		return (EAttribute) permissionEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPermissionEntry_Groups() {
		return (EAttribute) permissionEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPermissionEntry_Roles() {
		return (EAttribute) permissionEntryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPermissionEntry_Permissions() {
		return (EAttribute) permissionEntryEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPlanPermissionEntry() {
		return planPermissionEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDeploymentPermissionEntry() {
		return deploymentPermissionEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEnvironmentPermissionEntry() {
		return environmentPermissionEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNamedEnvironmentPermission() {
		return namedEnvironmentPermissionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getNamedEnvironmentPermission_EnvironmentName() {
		return (EAttribute) namedEnvironmentPermissionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getNamedEnvironmentPermission_Entries() {
		return (EReference) namedEnvironmentPermissionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getVARIABLE_SCOPE() {
		return variablE_SCOPEEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getTEST_PARSER_TYPE() {
		return tesT_PARSER_TYPEEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getREPOSITORY_SCOPE() {
		return repositorY_SCOPEEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getFILE_FILTER_TYPE() {
		return filE_FILTER_TYPEEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getBRANCH_CREATE_STRATEGY() {
		return brancH_CREATE_STRATEGYEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getBLOCK_STRATEGY() {
		return blocK_STRATEGYEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getNOTIFICATION_EVENT_TYPE() {
		return notificatioN_EVENT_TYPEEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getNOTIFICATION_ROLE() {
		return notificatioN_ROLEEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getPERMISSION_ROLE() {
		return permissioN_ROLEEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getPERMISSION_TYPE() {
		return permissioN_TYPEEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getRELEASE_APPROVAL() {
		return releasE_APPROVALEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BambooFactory getBambooFactory() {
		return (BambooFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		planEClass = createEClass(PLAN);
		createEAttribute(planEClass, PLAN__PROJECT_KEY);
		createEAttribute(planEClass, PLAN__KEY);
		createEAttribute(planEClass, PLAN__NAME);
		createEAttribute(planEClass, PLAN__SERVER_NAME);
		createEReference(planEClass, PLAN__STAGES);
		createEReference(planEClass, PLAN__JOBS);
		createEReference(planEClass, PLAN__TRIGGERS);
		createEReference(planEClass, PLAN__REPOSITORIES);
		createEReference(planEClass, PLAN__VARIABLES);
		createEReference(planEClass, PLAN__NOTIFICATIONS);
		createEReference(planEClass, PLAN__LABELS);
		createEReference(planEClass, PLAN__BRANCHES);
		createEReference(planEClass, PLAN__DEPENDENCIES);
		createEReference(planEClass, PLAN__DOCKER);
		createEReference(planEClass, PLAN__BRANCH_OVERRIDES);
		createEReference(planEClass, PLAN__OTHER);
		createEReference(planEClass, PLAN__PERMISSIONS);

		stageEClass = createEClass(STAGE);
		createEAttribute(stageEClass, STAGE__NAME);
		createEAttribute(stageEClass, STAGE__MANUAL);
		createEAttribute(stageEClass, STAGE__FINAL_STAGE);
		createEReference(stageEClass, STAGE__JOB_REFS);

		jobEClass = createEClass(JOB);
		createEAttribute(jobEClass, JOB__NAME);
		createEAttribute(jobEClass, JOB__KEY);
		createEReference(jobEClass, JOB__TASKS);
		createEReference(jobEClass, JOB__FINAL_TASKS);
		createEReference(jobEClass, JOB__ARTIFACTS);
		createEReference(jobEClass, JOB__ARTIFACT_SUBSCRIPTIONS);
		createEReference(jobEClass, JOB__REQUIREMENTS);
		createEReference(jobEClass, JOB__VARIABLES);
		createEReference(jobEClass, JOB__DOCKER);
		createEReference(jobEClass, JOB__OTHER);

		taskEClass = createEClass(TASK);
		createEReference(taskEClass, TASK__CONDITIONS);

		scriptTaskEClass = createEClass(SCRIPT_TASK);
		createEAttribute(scriptTaskEClass, SCRIPT_TASK__INTERPRETER);
		createEAttribute(scriptTaskEClass, SCRIPT_TASK__SCRIPTS);
		createEAttribute(scriptTaskEClass, SCRIPT_TASK__FILE);
		createEAttribute(scriptTaskEClass, SCRIPT_TASK__ARGUMENT);
		createEAttribute(scriptTaskEClass, SCRIPT_TASK__ENVIRONMENT);
		createEAttribute(scriptTaskEClass, SCRIPT_TASK__WORKING_DIR);

		cleanTaskEClass = createEClass(CLEAN_TASK);

		checkoutTaskEClass = createEClass(CHECKOUT_TASK);
		createEAttribute(checkoutTaskEClass, CHECKOUT_TASK__REPOSITORY);
		createEAttribute(checkoutTaskEClass, CHECKOUT_TASK__PATH);
		createEAttribute(checkoutTaskEClass, CHECKOUT_TASK__FORCE_CLEAN_BUILD);
		createEAttribute(checkoutTaskEClass, CHECKOUT_TASK__DEFAULT_REPOSITORY);

		mavenTaskEClass = createEClass(MAVEN_TASK);
		createEAttribute(mavenTaskEClass, MAVEN_TASK__EXECUTABLE);
		createEAttribute(mavenTaskEClass, MAVEN_TASK__JDK);
		createEAttribute(mavenTaskEClass, MAVEN_TASK__GOAL);
		createEAttribute(mavenTaskEClass, MAVEN_TASK__TESTS);
		createEAttribute(mavenTaskEClass, MAVEN_TASK__ENVIRONMENT);
		createEAttribute(mavenTaskEClass, MAVEN_TASK__WORKING_DIR);
		createEAttribute(mavenTaskEClass, MAVEN_TASK__PROJECT_FILE);
		createEAttribute(mavenTaskEClass, MAVEN_TASK__USE_RETURN_CODE);

		injectVariablesTaskEClass = createEClass(INJECT_VARIABLES_TASK);
		createEAttribute(injectVariablesTaskEClass, INJECT_VARIABLES_TASK__FILE);
		createEAttribute(injectVariablesTaskEClass, INJECT_VARIABLES_TASK__SCOPE);
		createEAttribute(injectVariablesTaskEClass, INJECT_VARIABLES_TASK__NAMESPACE);

		testParserTaskEClass = createEClass(TEST_PARSER_TASK);
		createEAttribute(testParserTaskEClass, TEST_PARSER_TASK__TYPE);
		createEAttribute(testParserTaskEClass, TEST_PARSER_TASK__TEST_RESULTS);
		createEAttribute(testParserTaskEClass, TEST_PARSER_TASK__IGNORE_TIME);

		artifactDownloadTaskEClass = createEClass(ARTIFACT_DOWNLOAD_TASK);
		createEAttribute(artifactDownloadTaskEClass, ARTIFACT_DOWNLOAD_TASK__SOURCE_PLAN);
		createEAttribute(artifactDownloadTaskEClass, ARTIFACT_DOWNLOAD_TASK__DESTINATION);
		createEReference(artifactDownloadTaskEClass, ARTIFACT_DOWNLOAD_TASK__ARTIFACTS);

		artifactDownloadItemEClass = createEClass(ARTIFACT_DOWNLOAD_ITEM);
		createEAttribute(artifactDownloadItemEClass, ARTIFACT_DOWNLOAD_ITEM__NAME);
		createEAttribute(artifactDownloadItemEClass, ARTIFACT_DOWNLOAD_ITEM__DESTINATION);

		anyTaskEClass = createEClass(ANY_TASK);
		createEAttribute(anyTaskEClass, ANY_TASK__PLUGIN_KEY);
		createEReference(anyTaskEClass, ANY_TASK__CONFIGURATION);

		taskConditionEClass = createEClass(TASK_CONDITION);
		createEAttribute(taskConditionEClass, TASK_CONDITION__VARIABLE_NAME);
		createEAttribute(taskConditionEClass, TASK_CONDITION__VARIABLE_VALUE);

		triggerEClass = createEClass(TRIGGER);

		pollingTriggerEClass = createEClass(POLLING_TRIGGER);
		createEAttribute(pollingTriggerEClass, POLLING_TRIGGER__PERIOD);
		createEAttribute(pollingTriggerEClass, POLLING_TRIGGER__CRON_EXPRESSION);
		createEAttribute(pollingTriggerEClass, POLLING_TRIGGER__REPOSITORIES);
		createEReference(pollingTriggerEClass, POLLING_TRIGGER__CONDITIONS);

		cronTriggerEClass = createEClass(CRON_TRIGGER);
		createEAttribute(cronTriggerEClass, CRON_TRIGGER__EXPRESSION);

		remoteTriggerEClass = createEClass(REMOTE_TRIGGER);
		createEAttribute(remoteTriggerEClass, REMOTE_TRIGGER__IP);

		afterDeploymentTriggerEClass = createEClass(AFTER_DEPLOYMENT_TRIGGER);
		createEAttribute(afterDeploymentTriggerEClass, AFTER_DEPLOYMENT_TRIGGER__DEPLOYMENT_PROJECT);
		createEAttribute(afterDeploymentTriggerEClass, AFTER_DEPLOYMENT_TRIGGER__ENVIRONMENT);

		buildSuccessTriggerEClass = createEClass(BUILD_SUCCESS_TRIGGER);
		createEAttribute(buildSuccessTriggerEClass, BUILD_SUCCESS_TRIGGER__BRANCH);

		stageSuccessTriggerEClass = createEClass(STAGE_SUCCESS_TRIGGER);
		createEAttribute(stageSuccessTriggerEClass, STAGE_SUCCESS_TRIGGER__STAGE);
		createEAttribute(stageSuccessTriggerEClass, STAGE_SUCCESS_TRIGGER__BRANCH);

		environmentSuccessTriggerEClass = createEClass(ENVIRONMENT_SUCCESS_TRIGGER);
		createEAttribute(environmentSuccessTriggerEClass, ENVIRONMENT_SUCCESS_TRIGGER__ENVIRONMENT);

		scheduledDeploymentTriggerEClass = createEClass(SCHEDULED_DEPLOYMENT_TRIGGER);
		createEAttribute(scheduledDeploymentTriggerEClass, SCHEDULED_DEPLOYMENT_TRIGGER__CRON_EXPRESSION);
		createEAttribute(scheduledDeploymentTriggerEClass, SCHEDULED_DEPLOYMENT_TRIGGER__ARTIFACT_BRANCH);

		triggerConditionEClass = createEClass(TRIGGER_CONDITION);

		greenPlanConditionEClass = createEClass(GREEN_PLAN_CONDITION);
		createEAttribute(greenPlanConditionEClass, GREEN_PLAN_CONDITION__PLAN_KEYS);

		allOtherConditionEClass = createEClass(ALL_OTHER_CONDITION);
		createEReference(allOtherConditionEClass, ALL_OTHER_CONDITION__PROPERTIES);

		repositoryEClass = createEClass(REPOSITORY);
		createEAttribute(repositoryEClass, REPOSITORY__NAME);

		linkedRepositoryEClass = createEClass(LINKED_REPOSITORY);
		createEAttribute(linkedRepositoryEClass, LINKED_REPOSITORY__SCOPE);

		gitRepositoryEClass = createEClass(GIT_REPOSITORY);
		createEAttribute(gitRepositoryEClass, GIT_REPOSITORY__URL);
		createEAttribute(gitRepositoryEClass, GIT_REPOSITORY__BRANCH);
		createEAttribute(gitRepositoryEClass, GIT_REPOSITORY__USERNAME);
		createEAttribute(gitRepositoryEClass, GIT_REPOSITORY__PASSWORD);
		createEAttribute(gitRepositoryEClass, GIT_REPOSITORY__SSH_KEY);
		createEAttribute(gitRepositoryEClass, GIT_REPOSITORY__SSH_KEY_PASSPHRASE);
		createEAttribute(gitRepositoryEClass, GIT_REPOSITORY__SHARED_CREDENTIALS);
		createEAttribute(gitRepositoryEClass, GIT_REPOSITORY__SHARED_CREDENTIALS_SCOPE);
		createEAttribute(gitRepositoryEClass, GIT_REPOSITORY__LFS);
		createEAttribute(gitRepositoryEClass, GIT_REPOSITORY__USE_SHALLOW_CLONES);
		createEAttribute(gitRepositoryEClass, GIT_REPOSITORY__SUBMODULES);
		createEAttribute(gitRepositoryEClass, GIT_REPOSITORY__FETCH_ALL);
		createEAttribute(gitRepositoryEClass, GIT_REPOSITORY__CACHE_ON_AGENTS);
		createEAttribute(gitRepositoryEClass, GIT_REPOSITORY__VERBOSE_LOGS);
		createEAttribute(gitRepositoryEClass, GIT_REPOSITORY__COMMAND_TIMEOUT_MINUTES);
		createEReference(gitRepositoryEClass, GIT_REPOSITORY__CHANGE_DETECTION);

		bitbucketCloudRepositoryEClass = createEClass(BITBUCKET_CLOUD_REPOSITORY);
		createEAttribute(bitbucketCloudRepositoryEClass, BITBUCKET_CLOUD_REPOSITORY__SLUG);

		bitbucketServerRepositoryEClass = createEClass(BITBUCKET_SERVER_REPOSITORY);
		createEAttribute(bitbucketServerRepositoryEClass, BITBUCKET_SERVER_REPOSITORY__SERVER);
		createEAttribute(bitbucketServerRepositoryEClass, BITBUCKET_SERVER_REPOSITORY__PROJECT);
		createEAttribute(bitbucketServerRepositoryEClass, BITBUCKET_SERVER_REPOSITORY__SLUG);
		createEAttribute(bitbucketServerRepositoryEClass, BITBUCKET_SERVER_REPOSITORY__CLONE_URL);
		createEAttribute(bitbucketServerRepositoryEClass, BITBUCKET_SERVER_REPOSITORY__PUBLIC_KEY);
		createEAttribute(bitbucketServerRepositoryEClass, BITBUCKET_SERVER_REPOSITORY__PRIVATE_KEY);
		createEAttribute(bitbucketServerRepositoryEClass, BITBUCKET_SERVER_REPOSITORY__BRANCH);
		createEAttribute(bitbucketServerRepositoryEClass, BITBUCKET_SERVER_REPOSITORY__LFS);
		createEAttribute(bitbucketServerRepositoryEClass, BITBUCKET_SERVER_REPOSITORY__USE_SHALLOW_CLONES);
		createEAttribute(bitbucketServerRepositoryEClass, BITBUCKET_SERVER_REPOSITORY__SUBMODULES);
		createEAttribute(bitbucketServerRepositoryEClass, BITBUCKET_SERVER_REPOSITORY__COMMAND_TIMEOUT_MINUTES);
		createEAttribute(bitbucketServerRepositoryEClass, BITBUCKET_SERVER_REPOSITORY__FETCH_ALL);

		githubRepositoryEClass = createEClass(GITHUB_REPOSITORY);
		createEAttribute(githubRepositoryEClass, GITHUB_REPOSITORY__REPOSITORY);
		createEAttribute(githubRepositoryEClass, GITHUB_REPOSITORY__USER);

		anyVcsRepositoryEClass = createEClass(ANY_VCS_REPOSITORY);
		createEAttribute(anyVcsRepositoryEClass, ANY_VCS_REPOSITORY__PLUGIN_KEY);
		createEReference(anyVcsRepositoryEClass, ANY_VCS_REPOSITORY__SERVER_CONFIG);
		createEReference(anyVcsRepositoryEClass, ANY_VCS_REPOSITORY__BRANCH_CONFIG);

		changeDetectionEClass = createEClass(CHANGE_DETECTION);
		createEReference(changeDetectionEClass, CHANGE_DETECTION__QUIET_PERIOD);
		createEAttribute(changeDetectionEClass, CHANGE_DETECTION__EXCLUDE_CHANGESET_PATTERN);
		createEAttribute(changeDetectionEClass, CHANGE_DETECTION__FILE_FILTER_TYPE);
		createEAttribute(changeDetectionEClass, CHANGE_DETECTION__FILE_FILTER_PATTERN);

		quietPeriodEClass = createEClass(QUIET_PERIOD);
		createEAttribute(quietPeriodEClass, QUIET_PERIOD__QUIET_PERIOD_SECONDS);
		createEAttribute(quietPeriodEClass, QUIET_PERIOD__MAX_RETRIES);

		artifactEClass = createEClass(ARTIFACT);
		createEAttribute(artifactEClass, ARTIFACT__NAME);
		createEAttribute(artifactEClass, ARTIFACT__LOCATION);
		createEAttribute(artifactEClass, ARTIFACT__PATTERN);
		createEAttribute(artifactEClass, ARTIFACT__REQUIRED);
		createEAttribute(artifactEClass, ARTIFACT__SHARED);
		createEAttribute(artifactEClass, ARTIFACT__HTTP_COMPRESSION_ON);

		artifactSubscriptionEClass = createEClass(ARTIFACT_SUBSCRIPTION);
		createEAttribute(artifactSubscriptionEClass, ARTIFACT_SUBSCRIPTION__ARTIFACT);
		createEAttribute(artifactSubscriptionEClass, ARTIFACT_SUBSCRIPTION__DESTINATION);

		requirementEClass = createEClass(REQUIREMENT);
		createEAttribute(requirementEClass, REQUIREMENT__CAPABILITY);
		createEAttribute(requirementEClass, REQUIREMENT__MATCH_VALUE);

		variableAssignmentEClass = createEClass(VARIABLE_ASSIGNMENT);
		createEAttribute(variableAssignmentEClass, VARIABLE_ASSIGNMENT__KEY);
		createEAttribute(variableAssignmentEClass, VARIABLE_ASSIGNMENT__VALUE);

		labelEClass = createEClass(LABEL);
		createEAttribute(labelEClass, LABEL__VALUE);

		notificationEClass = createEClass(NOTIFICATION);
		createEReference(notificationEClass, NOTIFICATION__RECIPIENTS);
		createEReference(notificationEClass, NOTIFICATION__EVENTS);

		notificationRecipientEClass = createEClass(NOTIFICATION_RECIPIENT);

		userRecipientEClass = createEClass(USER_RECIPIENT);
		createEAttribute(userRecipientEClass, USER_RECIPIENT__USERS);

		groupRecipientEClass = createEClass(GROUP_RECIPIENT);
		createEAttribute(groupRecipientEClass, GROUP_RECIPIENT__GROUPS);

		emailRecipientEClass = createEClass(EMAIL_RECIPIENT);
		createEAttribute(emailRecipientEClass, EMAIL_RECIPIENT__EMAILS);

		roleRecipientEClass = createEClass(ROLE_RECIPIENT);
		createEAttribute(roleRecipientEClass, ROLE_RECIPIENT__ROLE);

		webhookRecipientEClass = createEClass(WEBHOOK_RECIPIENT);
		createEAttribute(webhookRecipientEClass, WEBHOOK_RECIPIENT__NAME);
		createEAttribute(webhookRecipientEClass, WEBHOOK_RECIPIENT__URL);

		notificationEventEClass = createEClass(NOTIFICATION_EVENT);
		createEAttribute(notificationEventEClass, NOTIFICATION_EVENT__TYPE);
		createEAttribute(notificationEventEClass, NOTIFICATION_EVENT__FAILURES);
		createEAttribute(notificationEventEClass, NOTIFICATION_EVENT__FIRST_ONLY);

		branchManagementEClass = createEClass(BRANCH_MANAGEMENT);
		createEAttribute(branchManagementEClass, BRANCH_MANAGEMENT__CREATE);
		createEAttribute(branchManagementEClass, BRANCH_MANAGEMENT__CREATE_PATTERN);
		createEAttribute(branchManagementEClass, BRANCH_MANAGEMENT__ACCEPT_FORK);
		createEReference(branchManagementEClass, BRANCH_MANAGEMENT__DELETE);
		createEReference(branchManagementEClass, BRANCH_MANAGEMENT__INTEGRATION);
		createEAttribute(branchManagementEClass, BRANCH_MANAGEMENT__LINK_TO_JIRA);

		branchDeleteEClass = createEClass(BRANCH_DELETE);
		createEAttribute(branchDeleteEClass, BRANCH_DELETE__AFTER_DELETED_DAYS);
		createEAttribute(branchDeleteEClass, BRANCH_DELETE__AFTER_INACTIVE_DAYS);

		branchIntegrationEClass = createEClass(BRANCH_INTEGRATION);
		createEAttribute(branchIntegrationEClass, BRANCH_INTEGRATION__MERGE_FROM);
		createEAttribute(branchIntegrationEClass, BRANCH_INTEGRATION__MERGE_TO);
		createEAttribute(branchIntegrationEClass, BRANCH_INTEGRATION__PUSH_ON_SUCCESS);

		branchOverrideEClass = createEClass(BRANCH_OVERRIDE);
		createEAttribute(branchOverrideEClass, BRANCH_OVERRIDE__BRANCH_PATTERN);
		createEReference(branchOverrideEClass, BRANCH_OVERRIDE__STAGES);
		createEReference(branchOverrideEClass, BRANCH_OVERRIDE__JOBS);
		createEReference(branchOverrideEClass, BRANCH_OVERRIDE__TRIGGERS);
		createEReference(branchOverrideEClass, BRANCH_OVERRIDE__NOTIFICATIONS);
		createEReference(branchOverrideEClass, BRANCH_OVERRIDE__VARIABLES);
		createEReference(branchOverrideEClass, BRANCH_OVERRIDE__LABELS);
		createEReference(branchOverrideEClass, BRANCH_OVERRIDE__DOCKER);
		createEReference(branchOverrideEClass, BRANCH_OVERRIDE__BRANCH_CONFIG);

		branchSpecificConfigEClass = createEClass(BRANCH_SPECIFIC_CONFIG);
		createEReference(branchSpecificConfigEClass, BRANCH_SPECIFIC_CONFIG__INTEGRATION);
		createEAttribute(branchSpecificConfigEClass, BRANCH_SPECIFIC_CONFIG__DISABLE_EXPIRY);

		dependenciesEClass = createEClass(DEPENDENCIES);
		createEAttribute(dependenciesEClass, DEPENDENCIES__REQUIRE_ALL_STAGES_PASSING);
		createEAttribute(dependenciesEClass, DEPENDENCIES__ENABLED_FOR_BRANCHES);
		createEAttribute(dependenciesEClass, DEPENDENCIES__BLOCK_STRATEGY);
		createEAttribute(dependenciesEClass, DEPENDENCIES__PLANS);

		dockerConfigEClass = createEClass(DOCKER_CONFIG);
		createEAttribute(dockerConfigEClass, DOCKER_CONFIG__IMAGE);
		createEReference(dockerConfigEClass, DOCKER_CONFIG__VOLUMES);
		createEAttribute(dockerConfigEClass, DOCKER_CONFIG__USE_DEFAULT_VOLUMES);
		createEAttribute(dockerConfigEClass, DOCKER_CONFIG__DOCKER_RUN_ARGUMENTS);

		otherConfigEClass = createEClass(OTHER_CONFIG);
		createEAttribute(otherConfigEClass, OTHER_CONFIG__CONCURRENT_BUILD_PLUGIN);
		createEAttribute(otherConfigEClass, OTHER_CONFIG__CLEAN_WORKING_DIR);
		createEReference(otherConfigEClass, OTHER_CONFIG__ALL_OTHER_APPS);

		deploymentProjectEClass = createEClass(DEPLOYMENT_PROJECT);
		createEAttribute(deploymentProjectEClass, DEPLOYMENT_PROJECT__NAME);
		createEAttribute(deploymentProjectEClass, DEPLOYMENT_PROJECT__SOURCE_PLAN);
		createEAttribute(deploymentProjectEClass, DEPLOYMENT_PROJECT__SERVER_NAME);
		createEReference(deploymentProjectEClass, DEPLOYMENT_PROJECT__RELEASE_NAMING);
		createEReference(deploymentProjectEClass, DEPLOYMENT_PROJECT__ENVIRONMENTS);
		createEReference(deploymentProjectEClass, DEPLOYMENT_PROJECT__PERMISSIONS);
		createEReference(deploymentProjectEClass, DEPLOYMENT_PROJECT__DEFAULT_ENVIRONMENT_PERMISSIONS);
		createEReference(deploymentProjectEClass, DEPLOYMENT_PROJECT__ENVIRONMENT_PERMISSIONS);

		releaseNamingEClass = createEClass(RELEASE_NAMING);
		createEAttribute(releaseNamingEClass, RELEASE_NAMING__NEXT_VERSION_NAME);
		createEAttribute(releaseNamingEClass, RELEASE_NAMING__APPLIES_TO_BRANCHES);
		createEAttribute(releaseNamingEClass, RELEASE_NAMING__AUTO_INCREMENT);
		createEAttribute(releaseNamingEClass, RELEASE_NAMING__AUTO_INCREMENT_VARIABLES);

		environmentEClass = createEClass(ENVIRONMENT);
		createEAttribute(environmentEClass, ENVIRONMENT__NAME);
		createEReference(environmentEClass, ENVIRONMENT__TASKS);
		createEReference(environmentEClass, ENVIRONMENT__FINAL_TASKS);
		createEReference(environmentEClass, ENVIRONMENT__VARIABLES);
		createEReference(environmentEClass, ENVIRONMENT__TRIGGERS);
		createEReference(environmentEClass, ENVIRONMENT__NOTIFICATIONS);
		createEReference(environmentEClass, ENVIRONMENT__DOCKER);
		createEReference(environmentEClass, ENVIRONMENT__REQUIREMENTS);
		createEAttribute(environmentEClass, ENVIRONMENT__RELEASE_APPROVAL_PREREQUISITE);

		permissionEntryEClass = createEClass(PERMISSION_ENTRY);
		createEAttribute(permissionEntryEClass, PERMISSION_ENTRY__USERS);
		createEAttribute(permissionEntryEClass, PERMISSION_ENTRY__GROUPS);
		createEAttribute(permissionEntryEClass, PERMISSION_ENTRY__ROLES);
		createEAttribute(permissionEntryEClass, PERMISSION_ENTRY__PERMISSIONS);

		planPermissionEntryEClass = createEClass(PLAN_PERMISSION_ENTRY);

		deploymentPermissionEntryEClass = createEClass(DEPLOYMENT_PERMISSION_ENTRY);

		environmentPermissionEntryEClass = createEClass(ENVIRONMENT_PERMISSION_ENTRY);

		namedEnvironmentPermissionEClass = createEClass(NAMED_ENVIRONMENT_PERMISSION);
		createEAttribute(namedEnvironmentPermissionEClass, NAMED_ENVIRONMENT_PERMISSION__ENVIRONMENT_NAME);
		createEReference(namedEnvironmentPermissionEClass, NAMED_ENVIRONMENT_PERMISSION__ENTRIES);

		// Create enums
		variablE_SCOPEEEnum = createEEnum(VARIABLE_SCOPE);
		tesT_PARSER_TYPEEEnum = createEEnum(TEST_PARSER_TYPE);
		repositorY_SCOPEEEnum = createEEnum(REPOSITORY_SCOPE);
		filE_FILTER_TYPEEEnum = createEEnum(FILE_FILTER_TYPE);
		brancH_CREATE_STRATEGYEEnum = createEEnum(BRANCH_CREATE_STRATEGY);
		blocK_STRATEGYEEnum = createEEnum(BLOCK_STRATEGY);
		notificatioN_EVENT_TYPEEEnum = createEEnum(NOTIFICATION_EVENT_TYPE);
		notificatioN_ROLEEEnum = createEEnum(NOTIFICATION_ROLE);
		permissioN_ROLEEEnum = createEEnum(PERMISSION_ROLE);
		permissioN_TYPEEEnum = createEEnum(PERMISSION_TYPE);
		releasE_APPROVALEEnum = createEEnum(RELEASE_APPROVAL);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		scriptTaskEClass.getESuperTypes().add(this.getTask());
		cleanTaskEClass.getESuperTypes().add(this.getTask());
		checkoutTaskEClass.getESuperTypes().add(this.getTask());
		mavenTaskEClass.getESuperTypes().add(this.getTask());
		injectVariablesTaskEClass.getESuperTypes().add(this.getTask());
		testParserTaskEClass.getESuperTypes().add(this.getTask());
		artifactDownloadTaskEClass.getESuperTypes().add(this.getTask());
		anyTaskEClass.getESuperTypes().add(this.getTask());
		pollingTriggerEClass.getESuperTypes().add(this.getTrigger());
		cronTriggerEClass.getESuperTypes().add(this.getTrigger());
		remoteTriggerEClass.getESuperTypes().add(this.getTrigger());
		afterDeploymentTriggerEClass.getESuperTypes().add(this.getTrigger());
		buildSuccessTriggerEClass.getESuperTypes().add(this.getTrigger());
		stageSuccessTriggerEClass.getESuperTypes().add(this.getTrigger());
		environmentSuccessTriggerEClass.getESuperTypes().add(this.getTrigger());
		scheduledDeploymentTriggerEClass.getESuperTypes().add(this.getTrigger());
		greenPlanConditionEClass.getESuperTypes().add(this.getTriggerCondition());
		allOtherConditionEClass.getESuperTypes().add(this.getTriggerCondition());
		linkedRepositoryEClass.getESuperTypes().add(this.getRepository());
		gitRepositoryEClass.getESuperTypes().add(this.getRepository());
		bitbucketCloudRepositoryEClass.getESuperTypes().add(this.getGitRepository());
		bitbucketServerRepositoryEClass.getESuperTypes().add(this.getRepository());
		githubRepositoryEClass.getESuperTypes().add(this.getGitRepository());
		anyVcsRepositoryEClass.getESuperTypes().add(this.getRepository());
		userRecipientEClass.getESuperTypes().add(this.getNotificationRecipient());
		groupRecipientEClass.getESuperTypes().add(this.getNotificationRecipient());
		emailRecipientEClass.getESuperTypes().add(this.getNotificationRecipient());
		roleRecipientEClass.getESuperTypes().add(this.getNotificationRecipient());
		webhookRecipientEClass.getESuperTypes().add(this.getNotificationRecipient());
		planPermissionEntryEClass.getESuperTypes().add(this.getPermissionEntry());
		deploymentPermissionEntryEClass.getESuperTypes().add(this.getPermissionEntry());
		environmentPermissionEntryEClass.getESuperTypes().add(this.getPermissionEntry());

		// Initialize classes, features, and operations; add parameters
		initEClass(planEClass, Plan.class, "Plan", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPlan_ProjectKey(), ecorePackage.getEString(), "projectKey", null, 1, 1, Plan.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPlan_Key(), ecorePackage.getEString(), "key", null, 1, 1, Plan.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPlan_Name(), ecorePackage.getEString(), "name", null, 1, 1, Plan.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPlan_ServerName(), ecorePackage.getEString(), "serverName", null, 0, 1, Plan.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPlan_Stages(), this.getStage(), null, "stages", null, 1, -1, Plan.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getPlan_Jobs(), this.getJob(), null, "jobs", null, 0, -1, Plan.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getPlan_Triggers(), this.getTrigger(), null, "triggers", null, 0, -1, Plan.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getPlan_Repositories(), this.getRepository(), null, "repositories", null, 0, -1, Plan.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPlan_Variables(), this.getVariableAssignment(), null, "variables", null, 0, -1, Plan.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPlan_Notifications(), this.getNotification(), null, "notifications", null, 0, -1, Plan.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPlan_Labels(), this.getLabel(), null, "labels", null, 0, -1, Plan.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getPlan_Branches(), this.getBranchManagement(), null, "branches", null, 0, 1, Plan.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPlan_Dependencies(), this.getDependencies(), null, "dependencies", null, 0, 1, Plan.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPlan_Docker(), this.getDockerConfig(), null, "docker", null, 0, 1, Plan.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getPlan_BranchOverrides(), this.getBranchOverride(), null, "branchOverrides", null, 0, -1,
				Plan.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPlan_Other(), this.getOtherConfig(), null, "other", null, 0, 1, Plan.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getPlan_Permissions(), this.getPlanPermissionEntry(), null, "permissions", null, 0, -1,
				Plan.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stageEClass, Stage.class, "Stage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStage_Name(), ecorePackage.getEString(), "name", null, 1, 1, Stage.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStage_Manual(), ecorePackage.getEBoolean(), "manual", "false", 0, 1, Stage.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStage_FinalStage(), ecorePackage.getEBoolean(), "finalStage", "false", 0, 1, Stage.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStage_JobRefs(), this.getJob(), null, "jobRefs", null, 1, -1, Stage.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);

		initEClass(jobEClass, Job.class, "Job", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getJob_Name(), ecorePackage.getEString(), "name", null, 1, 1, Job.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getJob_Key(), ecorePackage.getEString(), "key", null, 0, 1, Job.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJob_Tasks(), this.getTask(), null, "tasks", null, 0, -1, Job.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getJob_FinalTasks(), this.getTask(), null, "finalTasks", null, 0, -1, Job.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getJob_Artifacts(), this.getArtifact(), null, "artifacts", null, 0, -1, Job.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getJob_ArtifactSubscriptions(), this.getArtifactSubscription(), null, "artifactSubscriptions",
				null, 0, -1, Job.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJob_Requirements(), this.getRequirement(), null, "requirements", null, 0, -1, Job.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJob_Variables(), this.getVariableAssignment(), null, "variables", null, 0, -1, Job.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJob_Docker(), this.getDockerConfig(), null, "docker", null, 0, 1, Job.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getJob_Other(), this.getOtherConfig(), null, "other", null, 0, 1, Job.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);

		initEClass(taskEClass, Task.class, "Task", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTask_Conditions(), this.getTaskCondition(), null, "conditions", null, 0, -1, Task.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scriptTaskEClass, ScriptTask.class, "ScriptTask", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getScriptTask_Interpreter(), ecorePackage.getEString(), "interpreter", null, 0, 1,
				ScriptTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getScriptTask_Scripts(), ecorePackage.getEString(), "scripts", null, 0, -1, ScriptTask.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScriptTask_File(), ecorePackage.getEString(), "file", null, 0, 1, ScriptTask.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScriptTask_Argument(), ecorePackage.getEString(), "argument", null, 0, 1, ScriptTask.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScriptTask_Environment(), ecorePackage.getEString(), "environment", null, 0, 1,
				ScriptTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getScriptTask_WorkingDir(), ecorePackage.getEString(), "workingDir", null, 0, 1,
				ScriptTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(cleanTaskEClass, CleanTask.class, "CleanTask", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);

		initEClass(checkoutTaskEClass, CheckoutTask.class, "CheckoutTask", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCheckoutTask_Repository(), ecorePackage.getEString(), "repository", null, 0, 1,
				CheckoutTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getCheckoutTask_Path(), ecorePackage.getEString(), "path", null, 0, 1, CheckoutTask.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCheckoutTask_ForceCleanBuild(), ecorePackage.getEBooleanObject(), "forceCleanBuild", null, 0,
				1, CheckoutTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getCheckoutTask_DefaultRepository(), ecorePackage.getEBoolean(), "defaultRepository", "false", 0,
				1, CheckoutTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(mavenTaskEClass, MavenTask.class, "MavenTask", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMavenTask_Executable(), ecorePackage.getEString(), "executable", null, 1, 1, MavenTask.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMavenTask_Jdk(), ecorePackage.getEString(), "jdk", null, 1, 1, MavenTask.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMavenTask_Goal(), ecorePackage.getEString(), "goal", null, 1, 1, MavenTask.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMavenTask_Tests(), ecorePackage.getEString(), "tests", null, 0, 1, MavenTask.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMavenTask_Environment(), ecorePackage.getEString(), "environment", null, 0, 1,
				MavenTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getMavenTask_WorkingDir(), ecorePackage.getEString(), "workingDir", null, 0, 1, MavenTask.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMavenTask_ProjectFile(), ecorePackage.getEString(), "projectFile", null, 0, 1,
				MavenTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getMavenTask_UseReturnCode(), ecorePackage.getEBooleanObject(), "useReturnCode", null, 0, 1,
				MavenTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(injectVariablesTaskEClass, InjectVariablesTask.class, "InjectVariablesTask", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInjectVariablesTask_File(), ecorePackage.getEString(), "file", null, 1, 1,
				InjectVariablesTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInjectVariablesTask_Scope(), this.getVARIABLE_SCOPE(), "scope", null, 0, 1,
				InjectVariablesTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInjectVariablesTask_Namespace(), ecorePackage.getEString(), "namespace", null, 0, 1,
				InjectVariablesTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(testParserTaskEClass, TestParserTask.class, "TestParserTask", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTestParserTask_Type(), this.getTEST_PARSER_TYPE(), "type", null, 1, 1, TestParserTask.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestParserTask_TestResults(), ecorePackage.getEString(), "testResults", null, 0, -1,
				TestParserTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestParserTask_IgnoreTime(), ecorePackage.getEBooleanObject(), "ignoreTime", null, 0, 1,
				TestParserTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(artifactDownloadTaskEClass, ArtifactDownloadTask.class, "ArtifactDownloadTask", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getArtifactDownloadTask_SourcePlan(), ecorePackage.getEString(), "sourcePlan", null, 0, 1,
				ArtifactDownloadTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArtifactDownloadTask_Destination(), ecorePackage.getEString(), "destination", null, 0, 1,
				ArtifactDownloadTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArtifactDownloadTask_Artifacts(), this.getArtifactDownloadItem(), null, "artifacts", null, 0,
				-1, ArtifactDownloadTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(artifactDownloadItemEClass, ArtifactDownloadItem.class, "ArtifactDownloadItem", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getArtifactDownloadItem_Name(), ecorePackage.getEString(), "name", null, 0, 1,
				ArtifactDownloadItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArtifactDownloadItem_Destination(), ecorePackage.getEString(), "destination", null, 0, 1,
				ArtifactDownloadItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(anyTaskEClass, AnyTask.class, "AnyTask", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAnyTask_PluginKey(), ecorePackage.getEString(), "pluginKey", null, 1, 1, AnyTask.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAnyTask_Configuration(), this.getVariableAssignment(), null, "configuration", null, 0, -1,
				AnyTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(taskConditionEClass, TaskCondition.class, "TaskCondition", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTaskCondition_VariableName(), ecorePackage.getEString(), "variableName", null, 1, 1,
				TaskCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getTaskCondition_VariableValue(), ecorePackage.getEString(), "variableValue", null, 1, 1,
				TaskCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(triggerEClass, Trigger.class, "Trigger", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(pollingTriggerEClass, PollingTrigger.class, "PollingTrigger", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPollingTrigger_Period(), ecorePackage.getEIntegerObject(), "period", null, 0, 1,
				PollingTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPollingTrigger_CronExpression(), ecorePackage.getEString(), "cronExpression", null, 0, 1,
				PollingTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPollingTrigger_Repositories(), ecorePackage.getEString(), "repositories", null, 0, -1,
				PollingTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getPollingTrigger_Conditions(), this.getTriggerCondition(), null, "conditions", null, 0, -1,
				PollingTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cronTriggerEClass, CronTrigger.class, "CronTrigger", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCronTrigger_Expression(), ecorePackage.getEString(), "expression", null, 1, 1,
				CronTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(remoteTriggerEClass, RemoteTrigger.class, "RemoteTrigger", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRemoteTrigger_Ip(), ecorePackage.getEString(), "ip", null, 0, 1, RemoteTrigger.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(afterDeploymentTriggerEClass, AfterDeploymentTrigger.class, "AfterDeploymentTrigger", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAfterDeploymentTrigger_DeploymentProject(), ecorePackage.getEString(), "deploymentProject",
				null, 1, 1, AfterDeploymentTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
				!IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAfterDeploymentTrigger_Environment(), ecorePackage.getEString(), "environment", null, 0, 1,
				AfterDeploymentTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(buildSuccessTriggerEClass, BuildSuccessTrigger.class, "BuildSuccessTrigger", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBuildSuccessTrigger_Branch(), ecorePackage.getEString(), "branch", null, 0, 1,
				BuildSuccessTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stageSuccessTriggerEClass, StageSuccessTrigger.class, "StageSuccessTrigger", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStageSuccessTrigger_Stage(), ecorePackage.getEString(), "stage", null, 1, 1,
				StageSuccessTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStageSuccessTrigger_Branch(), ecorePackage.getEString(), "branch", null, 0, 1,
				StageSuccessTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(environmentSuccessTriggerEClass, EnvironmentSuccessTrigger.class, "EnvironmentSuccessTrigger",
				!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEnvironmentSuccessTrigger_Environment(), ecorePackage.getEString(), "environment", null, 1, 1,
				EnvironmentSuccessTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scheduledDeploymentTriggerEClass, ScheduledDeploymentTrigger.class, "ScheduledDeploymentTrigger",
				!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getScheduledDeploymentTrigger_CronExpression(), ecorePackage.getEString(), "cronExpression",
				null, 1, 1, ScheduledDeploymentTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScheduledDeploymentTrigger_ArtifactBranch(), ecorePackage.getEString(), "artifactBranch",
				null, 0, 1, ScheduledDeploymentTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(triggerConditionEClass, TriggerCondition.class, "TriggerCondition", IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);

		initEClass(greenPlanConditionEClass, GreenPlanCondition.class, "GreenPlanCondition", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGreenPlanCondition_PlanKeys(), ecorePackage.getEString(), "planKeys", null, 0, -1,
				GreenPlanCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(allOtherConditionEClass, AllOtherCondition.class, "AllOtherCondition", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAllOtherCondition_Properties(), this.getVariableAssignment(), null, "properties", null, 0, -1,
				AllOtherCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(repositoryEClass, Repository.class, "Repository", IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRepository_Name(), ecorePackage.getEString(), "name", null, 1, 1, Repository.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(linkedRepositoryEClass, LinkedRepository.class, "LinkedRepository", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLinkedRepository_Scope(), this.getREPOSITORY_SCOPE(), "scope", null, 0, 1,
				LinkedRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(gitRepositoryEClass, GitRepository.class, "GitRepository", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGitRepository_Url(), ecorePackage.getEString(), "url", null, 1, 1, GitRepository.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGitRepository_Branch(), ecorePackage.getEString(), "branch", null, 1, 1, GitRepository.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGitRepository_Username(), ecorePackage.getEString(), "username", null, 0, 1,
				GitRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getGitRepository_Password(), ecorePackage.getEString(), "password", null, 0, 1,
				GitRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getGitRepository_SshKey(), ecorePackage.getEString(), "sshKey", null, 0, 1, GitRepository.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGitRepository_SshKeyPassphrase(), ecorePackage.getEString(), "sshKeyPassphrase", null, 0, 1,
				GitRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getGitRepository_SharedCredentials(), ecorePackage.getEString(), "sharedCredentials", null, 0, 1,
				GitRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getGitRepository_SharedCredentialsScope(), this.getREPOSITORY_SCOPE(), "sharedCredentialsScope",
				null, 0, 1, GitRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGitRepository_Lfs(), ecorePackage.getEBooleanObject(), "lfs", null, 0, 1, GitRepository.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGitRepository_UseShallowClones(), ecorePackage.getEBooleanObject(), "useShallowClones", null,
				0, 1, GitRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGitRepository_Submodules(), ecorePackage.getEBooleanObject(), "submodules", null, 0, 1,
				GitRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getGitRepository_FetchAll(), ecorePackage.getEBooleanObject(), "fetchAll", null, 0, 1,
				GitRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getGitRepository_CacheOnAgents(), ecorePackage.getEBooleanObject(), "cacheOnAgents", null, 0, 1,
				GitRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getGitRepository_VerboseLogs(), ecorePackage.getEBooleanObject(), "verboseLogs", null, 0, 1,
				GitRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getGitRepository_CommandTimeoutMinutes(), ecorePackage.getEIntegerObject(),
				"commandTimeoutMinutes", null, 0, 1, GitRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGitRepository_ChangeDetection(), this.getChangeDetection(), null, "changeDetection", null, 0,
				1, GitRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(bitbucketCloudRepositoryEClass, BitbucketCloudRepository.class, "BitbucketCloudRepository",
				!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBitbucketCloudRepository_Slug(), ecorePackage.getEString(), "slug", null, 1, 1,
				BitbucketCloudRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(bitbucketServerRepositoryEClass, BitbucketServerRepository.class, "BitbucketServerRepository",
				!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBitbucketServerRepository_Server(), ecorePackage.getEString(), "server", null, 1, 1,
				BitbucketServerRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBitbucketServerRepository_Project(), ecorePackage.getEString(), "project", null, 1, 1,
				BitbucketServerRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBitbucketServerRepository_Slug(), ecorePackage.getEString(), "slug", null, 1, 1,
				BitbucketServerRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBitbucketServerRepository_CloneUrl(), ecorePackage.getEString(), "cloneUrl", null, 1, 1,
				BitbucketServerRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBitbucketServerRepository_PublicKey(), ecorePackage.getEString(), "publicKey", null, 0, 1,
				BitbucketServerRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBitbucketServerRepository_PrivateKey(), ecorePackage.getEString(), "privateKey", null, 0, 1,
				BitbucketServerRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBitbucketServerRepository_Branch(), ecorePackage.getEString(), "branch", null, 1, 1,
				BitbucketServerRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBitbucketServerRepository_Lfs(), ecorePackage.getEBooleanObject(), "lfs", null, 0, 1,
				BitbucketServerRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBitbucketServerRepository_UseShallowClones(), ecorePackage.getEBooleanObject(),
				"useShallowClones", null, 0, 1, BitbucketServerRepository.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBitbucketServerRepository_Submodules(), ecorePackage.getEBooleanObject(), "submodules", null,
				0, 1, BitbucketServerRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
				!IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBitbucketServerRepository_CommandTimeoutMinutes(), ecorePackage.getEIntegerObject(),
				"commandTimeoutMinutes", null, 0, 1, BitbucketServerRepository.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBitbucketServerRepository_FetchAll(), ecorePackage.getEBooleanObject(), "fetchAll", null, 0,
				1, BitbucketServerRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(githubRepositoryEClass, GithubRepository.class, "GithubRepository", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGithubRepository_Repository(), ecorePackage.getEString(), "repository", null, 1, 1,
				GithubRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getGithubRepository_User(), ecorePackage.getEString(), "user", null, 0, 1,
				GithubRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(anyVcsRepositoryEClass, AnyVcsRepository.class, "AnyVcsRepository", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAnyVcsRepository_PluginKey(), ecorePackage.getEString(), "pluginKey", null, 1, 1,
				AnyVcsRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getAnyVcsRepository_ServerConfig(), this.getVariableAssignment(), null, "serverConfig", null, 0,
				-1, AnyVcsRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAnyVcsRepository_BranchConfig(), this.getVariableAssignment(), null, "branchConfig", null, 0,
				-1, AnyVcsRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(changeDetectionEClass, ChangeDetection.class, "ChangeDetection", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getChangeDetection_QuietPeriod(), this.getQuietPeriod(), null, "quietPeriod", null, 0, 1,
				ChangeDetection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getChangeDetection_ExcludeChangesetPattern(), ecorePackage.getEString(),
				"excludeChangesetPattern", null, 0, 1, ChangeDetection.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getChangeDetection_FileFilterType(), this.getFILE_FILTER_TYPE(), "fileFilterType", null, 0, 1,
				ChangeDetection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getChangeDetection_FileFilterPattern(), ecorePackage.getEString(), "fileFilterPattern", null, 0,
				1, ChangeDetection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(quietPeriodEClass, QuietPeriod.class, "QuietPeriod", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getQuietPeriod_QuietPeriodSeconds(), ecorePackage.getEInt(), "quietPeriodSeconds", null, 1, 1,
				QuietPeriod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getQuietPeriod_MaxRetries(), ecorePackage.getEIntegerObject(), "maxRetries", null, 0, 1,
				QuietPeriod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(artifactEClass, Artifact.class, "Artifact", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getArtifact_Name(), ecorePackage.getEString(), "name", null, 1, 1, Artifact.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArtifact_Location(), ecorePackage.getEString(), "location", null, 0, 1, Artifact.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArtifact_Pattern(), ecorePackage.getEString(), "pattern", null, 1, 1, Artifact.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArtifact_Required(), ecorePackage.getEBoolean(), "required", "false", 0, 1, Artifact.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArtifact_Shared(), ecorePackage.getEBoolean(), "shared", "false", 0, 1, Artifact.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArtifact_HttpCompressionOn(), ecorePackage.getEBooleanObject(), "httpCompressionOn", null, 0,
				1, Artifact.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(artifactSubscriptionEClass, ArtifactSubscription.class, "ArtifactSubscription", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getArtifactSubscription_Artifact(), ecorePackage.getEString(), "artifact", null, 1, 1,
				ArtifactSubscription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArtifactSubscription_Destination(), ecorePackage.getEString(), "destination", null, 0, 1,
				ArtifactSubscription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(requirementEClass, Requirement.class, "Requirement", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRequirement_Capability(), ecorePackage.getEString(), "capability", null, 1, 1,
				Requirement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getRequirement_MatchValue(), ecorePackage.getEString(), "matchValue", null, 0, 1,
				Requirement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(variableAssignmentEClass, Map.Entry.class, "VariableAssignment", !IS_ABSTRACT, !IS_INTERFACE,
				!IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getVariableAssignment_Key(), ecorePackage.getEString(), "key", null, 1, 1, Map.Entry.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getVariableAssignment_Value(), ecorePackage.getEString(), "value", null, 1, 1, Map.Entry.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(labelEClass, Label.class, "Label", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLabel_Value(), ecorePackage.getEString(), "value", null, 1, 1, Label.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(notificationEClass, Notification.class, "Notification", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNotification_Recipients(), this.getNotificationRecipient(), null, "recipients", null, 1, -1,
				Notification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNotification_Events(), this.getNotificationEvent(), null, "events", null, 1, -1,
				Notification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(notificationRecipientEClass, NotificationRecipient.class, "NotificationRecipient", IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(userRecipientEClass, UserRecipient.class, "UserRecipient", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUserRecipient_Users(), ecorePackage.getEString(), "users", null, 0, -1, UserRecipient.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(groupRecipientEClass, GroupRecipient.class, "GroupRecipient", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGroupRecipient_Groups(), ecorePackage.getEString(), "groups", null, 0, -1,
				GroupRecipient.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(emailRecipientEClass, EmailRecipient.class, "EmailRecipient", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEmailRecipient_Emails(), ecorePackage.getEString(), "emails", null, 0, -1,
				EmailRecipient.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(roleRecipientEClass, RoleRecipient.class, "RoleRecipient", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRoleRecipient_Role(), this.getNOTIFICATION_ROLE(), "role", null, 1, 1, RoleRecipient.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(webhookRecipientEClass, WebhookRecipient.class, "WebhookRecipient", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWebhookRecipient_Name(), ecorePackage.getEString(), "name", null, 1, 1,
				WebhookRecipient.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getWebhookRecipient_Url(), ecorePackage.getEString(), "url", null, 1, 1, WebhookRecipient.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(notificationEventEClass, NotificationEvent.class, "NotificationEvent", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNotificationEvent_Type(), this.getNOTIFICATION_EVENT_TYPE(), "type", null, 1, 1,
				NotificationEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getNotificationEvent_Failures(), ecorePackage.getEIntegerObject(), "failures", null, 0, 1,
				NotificationEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getNotificationEvent_FirstOnly(), ecorePackage.getEBooleanObject(), "firstOnly", null, 0, 1,
				NotificationEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(branchManagementEClass, BranchManagement.class, "BranchManagement", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBranchManagement_Create(), this.getBRANCH_CREATE_STRATEGY(), "create", null, 0, 1,
				BranchManagement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getBranchManagement_CreatePattern(), ecorePackage.getEString(), "createPattern", null, 0, 1,
				BranchManagement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getBranchManagement_AcceptFork(), ecorePackage.getEBooleanObject(), "acceptFork", null, 0, 1,
				BranchManagement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getBranchManagement_Delete(), this.getBranchDelete(), null, "delete", null, 0, 1,
				BranchManagement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBranchManagement_Integration(), this.getBranchIntegration(), null, "integration", null, 0, 1,
				BranchManagement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBranchManagement_LinkToJira(), ecorePackage.getEBooleanObject(), "linkToJira", null, 0, 1,
				BranchManagement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(branchDeleteEClass, BranchDelete.class, "BranchDelete", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBranchDelete_AfterDeletedDays(), ecorePackage.getEString(), "afterDeletedDays", null, 0, 1,
				BranchDelete.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getBranchDelete_AfterInactiveDays(), ecorePackage.getEString(), "afterInactiveDays", null, 0, 1,
				BranchDelete.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(branchIntegrationEClass, BranchIntegration.class, "BranchIntegration", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBranchIntegration_MergeFrom(), ecorePackage.getEString(), "mergeFrom", null, 0, 1,
				BranchIntegration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getBranchIntegration_MergeTo(), ecorePackage.getEString(), "mergeTo", null, 0, 1,
				BranchIntegration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getBranchIntegration_PushOnSuccess(), ecorePackage.getEBoolean(), "pushOnSuccess", "false", 0, 1,
				BranchIntegration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(branchOverrideEClass, BranchOverride.class, "BranchOverride", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBranchOverride_BranchPattern(), ecorePackage.getEString(), "branchPattern", null, 1, 1,
				BranchOverride.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getBranchOverride_Stages(), this.getStage(), null, "stages", null, 0, -1, BranchOverride.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBranchOverride_Jobs(), this.getJob(), null, "jobs", null, 0, -1, BranchOverride.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBranchOverride_Triggers(), this.getTrigger(), null, "triggers", null, 0, -1,
				BranchOverride.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBranchOverride_Notifications(), this.getNotification(), null, "notifications", null, 0, -1,
				BranchOverride.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBranchOverride_Variables(), this.getVariableAssignment(), null, "variables", null, 0, -1,
				BranchOverride.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBranchOverride_Labels(), this.getLabel(), null, "labels", null, 0, -1, BranchOverride.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBranchOverride_Docker(), this.getDockerConfig(), null, "docker", null, 0, 1,
				BranchOverride.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBranchOverride_BranchConfig(), this.getBranchSpecificConfig(), null, "branchConfig", null, 0,
				1, BranchOverride.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(branchSpecificConfigEClass, BranchSpecificConfig.class, "BranchSpecificConfig", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBranchSpecificConfig_Integration(), this.getBranchIntegration(), null, "integration", null, 0,
				1, BranchSpecificConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBranchSpecificConfig_DisableExpiry(), ecorePackage.getEBoolean(), "disableExpiry", "false", 0,
				1, BranchSpecificConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dependenciesEClass, Dependencies.class, "Dependencies", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDependencies_RequireAllStagesPassing(), ecorePackage.getEBoolean(), "requireAllStagesPassing",
				"false", 0, 1, Dependencies.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDependencies_EnabledForBranches(), ecorePackage.getEBoolean(), "enabledForBranches", "true",
				0, 1, Dependencies.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getDependencies_BlockStrategy(), this.getBLOCK_STRATEGY(), "blockStrategy", null, 0, 1,
				Dependencies.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getDependencies_Plans(), ecorePackage.getEString(), "plans", null, 0, -1, Dependencies.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dockerConfigEClass, DockerConfig.class, "DockerConfig", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDockerConfig_Image(), ecorePackage.getEString(), "image", null, 1, 1, DockerConfig.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDockerConfig_Volumes(), this.getVariableAssignment(), null, "volumes", null, 0, -1,
				DockerConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDockerConfig_UseDefaultVolumes(), ecorePackage.getEBoolean(), "useDefaultVolumes", "true", 0,
				1, DockerConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getDockerConfig_DockerRunArguments(), ecorePackage.getEString(), "dockerRunArguments", null, 0,
				-1, DockerConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(otherConfigEClass, OtherConfig.class, "OtherConfig", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOtherConfig_ConcurrentBuildPlugin(), ecorePackage.getEIntegerObject(),
				"concurrentBuildPlugin", null, 0, 1, OtherConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOtherConfig_CleanWorkingDir(), ecorePackage.getEBooleanObject(), "cleanWorkingDir", null, 0,
				1, OtherConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getOtherConfig_AllOtherApps(), this.getVariableAssignment(), null, "allOtherApps", null, 0, -1,
				OtherConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(deploymentProjectEClass, DeploymentProject.class, "DeploymentProject", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDeploymentProject_Name(), ecorePackage.getEString(), "name", null, 1, 1,
				DeploymentProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getDeploymentProject_SourcePlan(), ecorePackage.getEString(), "sourcePlan", null, 1, 1,
				DeploymentProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getDeploymentProject_ServerName(), ecorePackage.getEString(), "serverName", null, 0, 1,
				DeploymentProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getDeploymentProject_ReleaseNaming(), this.getReleaseNaming(), null, "releaseNaming", null, 0, 1,
				DeploymentProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDeploymentProject_Environments(), this.getEnvironment(), null, "environments", null, 1, -1,
				DeploymentProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDeploymentProject_Permissions(), this.getDeploymentPermissionEntry(), null, "permissions",
				null, 0, -1, DeploymentProject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDeploymentProject_DefaultEnvironmentPermissions(), this.getEnvironmentPermissionEntry(), null,
				"defaultEnvironmentPermissions", null, 0, -1, DeploymentProject.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDeploymentProject_EnvironmentPermissions(), this.getNamedEnvironmentPermission(), null,
				"environmentPermissions", null, 0, -1, DeploymentProject.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(releaseNamingEClass, ReleaseNaming.class, "ReleaseNaming", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getReleaseNaming_NextVersionName(), ecorePackage.getEString(), "nextVersionName", null, 1, 1,
				ReleaseNaming.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getReleaseNaming_AppliesToBranches(), ecorePackage.getEBooleanObject(), "appliesToBranches",
				null, 0, 1, ReleaseNaming.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReleaseNaming_AutoIncrement(), ecorePackage.getEBooleanObject(), "autoIncrement", null, 0, 1,
				ReleaseNaming.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getReleaseNaming_AutoIncrementVariables(), ecorePackage.getEString(), "autoIncrementVariables",
				null, 0, -1, ReleaseNaming.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(environmentEClass, Environment.class, "Environment", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEnvironment_Name(), ecorePackage.getEString(), "name", null, 1, 1, Environment.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEnvironment_Tasks(), this.getTask(), null, "tasks", null, 0, -1, Environment.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEnvironment_FinalTasks(), this.getTask(), null, "finalTasks", null, 0, -1, Environment.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEnvironment_Variables(), this.getVariableAssignment(), null, "variables", null, 0, -1,
				Environment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEnvironment_Triggers(), this.getTrigger(), null, "triggers", null, 0, -1, Environment.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEnvironment_Notifications(), this.getNotification(), null, "notifications", null, 0, -1,
				Environment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEnvironment_Docker(), this.getDockerConfig(), null, "docker", null, 0, 1, Environment.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEnvironment_Requirements(), this.getRequirement(), null, "requirements", null, 0, -1,
				Environment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEnvironment_ReleaseApprovalPrerequisite(), this.getRELEASE_APPROVAL(),
				"releaseApprovalPrerequisite", null, 0, 1, Environment.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(permissionEntryEClass, PermissionEntry.class, "PermissionEntry", IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPermissionEntry_Users(), ecorePackage.getEString(), "users", null, 0, -1,
				PermissionEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPermissionEntry_Groups(), ecorePackage.getEString(), "groups", null, 0, -1,
				PermissionEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPermissionEntry_Roles(), this.getPERMISSION_ROLE(), "roles", null, 0, -1,
				PermissionEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPermissionEntry_Permissions(), this.getPERMISSION_TYPE(), "permissions", null, 0, -1,
				PermissionEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(planPermissionEntryEClass, PlanPermissionEntry.class, "PlanPermissionEntry", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(deploymentPermissionEntryEClass, DeploymentPermissionEntry.class, "DeploymentPermissionEntry",
				!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(environmentPermissionEntryEClass, EnvironmentPermissionEntry.class, "EnvironmentPermissionEntry",
				!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(namedEnvironmentPermissionEClass, NamedEnvironmentPermission.class, "NamedEnvironmentPermission",
				!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNamedEnvironmentPermission_EnvironmentName(), ecorePackage.getEString(), "environmentName",
				null, 1, 1, NamedEnvironmentPermission.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNamedEnvironmentPermission_Entries(), this.getEnvironmentPermissionEntry(), null, "entries",
				null, 0, -1, NamedEnvironmentPermission.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(variablE_SCOPEEEnum, com.mddoai.metamodel.bambooMM.VARIABLE_SCOPE.class, "VARIABLE_SCOPE");
		addEEnumLiteral(variablE_SCOPEEEnum, com.mddoai.metamodel.bambooMM.VARIABLE_SCOPE.LOCAL);
		addEEnumLiteral(variablE_SCOPEEEnum, com.mddoai.metamodel.bambooMM.VARIABLE_SCOPE.RESULT);

		initEEnum(tesT_PARSER_TYPEEEnum, com.mddoai.metamodel.bambooMM.TEST_PARSER_TYPE.class, "TEST_PARSER_TYPE");
		addEEnumLiteral(tesT_PARSER_TYPEEEnum, com.mddoai.metamodel.bambooMM.TEST_PARSER_TYPE.JUNIT);
		addEEnumLiteral(tesT_PARSER_TYPEEEnum, com.mddoai.metamodel.bambooMM.TEST_PARSER_TYPE.TESTNG);
		addEEnumLiteral(tesT_PARSER_TYPEEEnum, com.mddoai.metamodel.bambooMM.TEST_PARSER_TYPE.MOCHA);
		addEEnumLiteral(tesT_PARSER_TYPEEEnum, com.mddoai.metamodel.bambooMM.TEST_PARSER_TYPE.NUNIT);
		addEEnumLiteral(tesT_PARSER_TYPEEEnum, com.mddoai.metamodel.bambooMM.TEST_PARSER_TYPE.MSTEST);

		initEEnum(repositorY_SCOPEEEnum, com.mddoai.metamodel.bambooMM.REPOSITORY_SCOPE.class, "REPOSITORY_SCOPE");
		addEEnumLiteral(repositorY_SCOPEEEnum, com.mddoai.metamodel.bambooMM.REPOSITORY_SCOPE.GLOBAL);
		addEEnumLiteral(repositorY_SCOPEEEnum, com.mddoai.metamodel.bambooMM.REPOSITORY_SCOPE.PROJECT);

		initEEnum(filE_FILTER_TYPEEEnum, com.mddoai.metamodel.bambooMM.FILE_FILTER_TYPE.class, "FILE_FILTER_TYPE");
		addEEnumLiteral(filE_FILTER_TYPEEEnum, com.mddoai.metamodel.bambooMM.FILE_FILTER_TYPE.INCLUDE_ONLY);
		addEEnumLiteral(filE_FILTER_TYPEEEnum, com.mddoai.metamodel.bambooMM.FILE_FILTER_TYPE.EXCLUDE_ONLY);

		initEEnum(brancH_CREATE_STRATEGYEEnum, com.mddoai.metamodel.bambooMM.BRANCH_CREATE_STRATEGY.class,
				"BRANCH_CREATE_STRATEGY");
		addEEnumLiteral(brancH_CREATE_STRATEGYEEnum,
				com.mddoai.metamodel.bambooMM.BRANCH_CREATE_STRATEGY.FOR_NEW_BRANCH);
		addEEnumLiteral(brancH_CREATE_STRATEGYEEnum,
				com.mddoai.metamodel.bambooMM.BRANCH_CREATE_STRATEGY.FOR_PULL_REQUEST);
		addEEnumLiteral(brancH_CREATE_STRATEGYEEnum, com.mddoai.metamodel.bambooMM.BRANCH_CREATE_STRATEGY.MANUALLY);
		addEEnumLiteral(brancH_CREATE_STRATEGYEEnum, com.mddoai.metamodel.bambooMM.BRANCH_CREATE_STRATEGY.NEVER);

		initEEnum(blocK_STRATEGYEEnum, com.mddoai.metamodel.bambooMM.BLOCK_STRATEGY.class, "BLOCK_STRATEGY");
		addEEnumLiteral(blocK_STRATEGYEEnum, com.mddoai.metamodel.bambooMM.BLOCK_STRATEGY.DONT_BLOCK);
		addEEnumLiteral(blocK_STRATEGYEEnum, com.mddoai.metamodel.bambooMM.BLOCK_STRATEGY.BLOCK_IF_PARENT_IN_PROGRESS);
		addEEnumLiteral(blocK_STRATEGYEEnum,
				com.mddoai.metamodel.bambooMM.BLOCK_STRATEGY.BLOCK_IF_PARENT_HAS_UNBUILT_CHANGES);

		initEEnum(notificatioN_EVENT_TYPEEEnum, com.mddoai.metamodel.bambooMM.NOTIFICATION_EVENT_TYPE.class,
				"NOTIFICATION_EVENT_TYPE");
		addEEnumLiteral(notificatioN_EVENT_TYPEEEnum,
				com.mddoai.metamodel.bambooMM.NOTIFICATION_EVENT_TYPE.PLAN_FAILED);
		addEEnumLiteral(notificatioN_EVENT_TYPEEEnum,
				com.mddoai.metamodel.bambooMM.NOTIFICATION_EVENT_TYPE.PLAN_COMPLETED);
		addEEnumLiteral(notificatioN_EVENT_TYPEEEnum,
				com.mddoai.metamodel.bambooMM.NOTIFICATION_EVENT_TYPE.PLAN_STATUS_CHANGED);
		addEEnumLiteral(notificatioN_EVENT_TYPEEEnum,
				com.mddoai.metamodel.bambooMM.NOTIFICATION_EVENT_TYPE.PLAN_COMMENT_ADDED);
		addEEnumLiteral(notificatioN_EVENT_TYPEEEnum,
				com.mddoai.metamodel.bambooMM.NOTIFICATION_EVENT_TYPE.PLAN_RESPONSIBILITY_CHANGED);
		addEEnumLiteral(notificatioN_EVENT_TYPEEEnum, com.mddoai.metamodel.bambooMM.NOTIFICATION_EVENT_TYPE.JOB_ERROR);
		addEEnumLiteral(notificatioN_EVENT_TYPEEEnum,
				com.mddoai.metamodel.bambooMM.NOTIFICATION_EVENT_TYPE.JOB_COMPLETED);
		addEEnumLiteral(notificatioN_EVENT_TYPEEEnum,
				com.mddoai.metamodel.bambooMM.NOTIFICATION_EVENT_TYPE.JOB_STATUS_CHANGED);
		addEEnumLiteral(notificatioN_EVENT_TYPEEEnum, com.mddoai.metamodel.bambooMM.NOTIFICATION_EVENT_TYPE.JOB_FAILED);
		addEEnumLiteral(notificatioN_EVENT_TYPEEEnum,
				com.mddoai.metamodel.bambooMM.NOTIFICATION_EVENT_TYPE.JOB_FIRST_FAILED);
		addEEnumLiteral(notificatioN_EVENT_TYPEEEnum, com.mddoai.metamodel.bambooMM.NOTIFICATION_EVENT_TYPE.JOB_HUNG);
		addEEnumLiteral(notificatioN_EVENT_TYPEEEnum,
				com.mddoai.metamodel.bambooMM.NOTIFICATION_EVENT_TYPE.JOB_QUEUE_TIMEOUT);
		addEEnumLiteral(notificatioN_EVENT_TYPEEEnum,
				com.mddoai.metamodel.bambooMM.NOTIFICATION_EVENT_TYPE.JOB_QUEUED_WITHOUT_CAPABLE_AGENTS);
		addEEnumLiteral(notificatioN_EVENT_TYPEEEnum,
				com.mddoai.metamodel.bambooMM.NOTIFICATION_EVENT_TYPE.DEPLOYMENT_FAILED);
		addEEnumLiteral(notificatioN_EVENT_TYPEEEnum,
				com.mddoai.metamodel.bambooMM.NOTIFICATION_EVENT_TYPE.DEPLOYMENT_FINISHED);
		addEEnumLiteral(notificatioN_EVENT_TYPEEEnum,
				com.mddoai.metamodel.bambooMM.NOTIFICATION_EVENT_TYPE.DEPLOYMENT_STARTED_AND_FINISHED);

		initEEnum(notificatioN_ROLEEEnum, com.mddoai.metamodel.bambooMM.NOTIFICATION_ROLE.class, "NOTIFICATION_ROLE");
		addEEnumLiteral(notificatioN_ROLEEEnum, com.mddoai.metamodel.bambooMM.NOTIFICATION_ROLE.RESPONSIBLE);
		addEEnumLiteral(notificatioN_ROLEEEnum, com.mddoai.metamodel.bambooMM.NOTIFICATION_ROLE.WATCHERS);
		addEEnumLiteral(notificatioN_ROLEEEnum, com.mddoai.metamodel.bambooMM.NOTIFICATION_ROLE.COMMITTERS);
		addEEnumLiteral(notificatioN_ROLEEEnum, com.mddoai.metamodel.bambooMM.NOTIFICATION_ROLE.LOGGED_IN);
		addEEnumLiteral(notificatioN_ROLEEEnum, com.mddoai.metamodel.bambooMM.NOTIFICATION_ROLE.ANONYMOUS);

		initEEnum(permissioN_ROLEEEnum, com.mddoai.metamodel.bambooMM.PERMISSION_ROLE.class, "PERMISSION_ROLE");
		addEEnumLiteral(permissioN_ROLEEEnum, com.mddoai.metamodel.bambooMM.PERMISSION_ROLE.LOGGED_IN);
		addEEnumLiteral(permissioN_ROLEEEnum, com.mddoai.metamodel.bambooMM.PERMISSION_ROLE.ANONYMOUS);

		initEEnum(permissioN_TYPEEEnum, com.mddoai.metamodel.bambooMM.PERMISSION_TYPE.class, "PERMISSION_TYPE");
		addEEnumLiteral(permissioN_TYPEEEnum, com.mddoai.metamodel.bambooMM.PERMISSION_TYPE.VIEW);
		addEEnumLiteral(permissioN_TYPEEEnum, com.mddoai.metamodel.bambooMM.PERMISSION_TYPE.EDIT);
		addEEnumLiteral(permissioN_TYPEEEnum, com.mddoai.metamodel.bambooMM.PERMISSION_TYPE.BUILD);
		addEEnumLiteral(permissioN_TYPEEEnum, com.mddoai.metamodel.bambooMM.PERMISSION_TYPE.CLONE);
		addEEnumLiteral(permissioN_TYPEEEnum, com.mddoai.metamodel.bambooMM.PERMISSION_TYPE.ADMIN);
		addEEnumLiteral(permissioN_TYPEEEnum, com.mddoai.metamodel.bambooMM.PERMISSION_TYPE.DEPLOY);
		addEEnumLiteral(permissioN_TYPEEEnum, com.mddoai.metamodel.bambooMM.PERMISSION_TYPE.VIEW_CONFIGURATION);
		addEEnumLiteral(permissioN_TYPEEEnum, com.mddoai.metamodel.bambooMM.PERMISSION_TYPE.CREATE_REPOSITORY);

		initEEnum(releasE_APPROVALEEnum, com.mddoai.metamodel.bambooMM.RELEASE_APPROVAL.class, "RELEASE_APPROVAL");
		addEEnumLiteral(releasE_APPROVALEEnum, com.mddoai.metamodel.bambooMM.RELEASE_APPROVAL.NOT_BROKEN);
		addEEnumLiteral(releasE_APPROVALEEnum, com.mddoai.metamodel.bambooMM.RELEASE_APPROVAL.APPROVED);
		addEEnumLiteral(releasE_APPROVALEEnum, com.mddoai.metamodel.bambooMM.RELEASE_APPROVAL.NONE);

		// Create resource
		createResource(eNS_URI);
	}

} //BambooPackageImpl
