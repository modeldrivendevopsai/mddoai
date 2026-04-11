/**
 */
package com.mddoai.metamodel.bambooMM;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.mddoai.metamodel.bambooMM.BambooFactory
 * @model kind="package"
 * @generated
 */
public interface BambooPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "bambooMM";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.mddoai.com/mddoai/metamodel/bamboo";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "bambooMM";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BambooPackage eINSTANCE = com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.PlanImpl <em>Plan</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.PlanImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getPlan()
	 * @generated
	 */
	int PLAN = 0;

	/**
	 * The feature id for the '<em><b>Project Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN__PROJECT_KEY = 0;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN__KEY = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN__NAME = 2;

	/**
	 * The feature id for the '<em><b>Server Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN__SERVER_NAME = 3;

	/**
	 * The feature id for the '<em><b>Stages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN__STAGES = 4;

	/**
	 * The feature id for the '<em><b>Jobs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN__JOBS = 5;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN__TRIGGERS = 6;

	/**
	 * The feature id for the '<em><b>Repositories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN__REPOSITORIES = 7;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN__VARIABLES = 8;

	/**
	 * The feature id for the '<em><b>Notifications</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN__NOTIFICATIONS = 9;

	/**
	 * The feature id for the '<em><b>Labels</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN__LABELS = 10;

	/**
	 * The feature id for the '<em><b>Branches</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN__BRANCHES = 11;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN__DEPENDENCIES = 12;

	/**
	 * The feature id for the '<em><b>Docker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN__DOCKER = 13;

	/**
	 * The feature id for the '<em><b>Branch Overrides</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN__BRANCH_OVERRIDES = 14;

	/**
	 * The feature id for the '<em><b>Other</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN__OTHER = 15;

	/**
	 * The feature id for the '<em><b>Permissions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN__PERMISSIONS = 16;

	/**
	 * The number of structural features of the '<em>Plan</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN_FEATURE_COUNT = 17;

	/**
	 * The number of operations of the '<em>Plan</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.StageImpl <em>Stage</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.StageImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getStage()
	 * @generated
	 */
	int STAGE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Manual</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE__MANUAL = 1;

	/**
	 * The feature id for the '<em><b>Final Stage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE__FINAL_STAGE = 2;

	/**
	 * The feature id for the '<em><b>Job Refs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE__JOB_REFS = 3;

	/**
	 * The number of structural features of the '<em>Stage</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Stage</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.JobImpl <em>Job</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.JobImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getJob()
	 * @generated
	 */
	int JOB = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__NAME = 0;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__KEY = 1;

	/**
	 * The feature id for the '<em><b>Tasks</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__TASKS = 2;

	/**
	 * The feature id for the '<em><b>Final Tasks</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__FINAL_TASKS = 3;

	/**
	 * The feature id for the '<em><b>Artifacts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__ARTIFACTS = 4;

	/**
	 * The feature id for the '<em><b>Artifact Subscriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__ARTIFACT_SUBSCRIPTIONS = 5;

	/**
	 * The feature id for the '<em><b>Requirements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__REQUIREMENTS = 6;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__VARIABLES = 7;

	/**
	 * The feature id for the '<em><b>Docker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__DOCKER = 8;

	/**
	 * The feature id for the '<em><b>Other</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__OTHER = 9;

	/**
	 * The number of structural features of the '<em>Job</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB_FEATURE_COUNT = 10;

	/**
	 * The number of operations of the '<em>Job</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.TaskImpl <em>Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.TaskImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getTask()
	 * @generated
	 */
	int TASK = 3;

	/**
	 * The feature id for the '<em><b>Conditions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__CONDITIONS = 0;

	/**
	 * The number of structural features of the '<em>Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.ScriptTaskImpl <em>Script Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.ScriptTaskImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getScriptTask()
	 * @generated
	 */
	int SCRIPT_TASK = 4;

	/**
	 * The feature id for the '<em><b>Conditions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_TASK__CONDITIONS = TASK__CONDITIONS;

	/**
	 * The feature id for the '<em><b>Interpreter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_TASK__INTERPRETER = TASK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Scripts</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_TASK__SCRIPTS = TASK_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_TASK__FILE = TASK_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Argument</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_TASK__ARGUMENT = TASK_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Environment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_TASK__ENVIRONMENT = TASK_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Working Dir</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_TASK__WORKING_DIR = TASK_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Script Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_TASK_FEATURE_COUNT = TASK_FEATURE_COUNT + 6;

	/**
	 * The number of operations of the '<em>Script Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_TASK_OPERATION_COUNT = TASK_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.CleanTaskImpl <em>Clean Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.CleanTaskImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getCleanTask()
	 * @generated
	 */
	int CLEAN_TASK = 5;

	/**
	 * The feature id for the '<em><b>Conditions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLEAN_TASK__CONDITIONS = TASK__CONDITIONS;

	/**
	 * The number of structural features of the '<em>Clean Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLEAN_TASK_FEATURE_COUNT = TASK_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Clean Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLEAN_TASK_OPERATION_COUNT = TASK_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.CheckoutTaskImpl <em>Checkout Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.CheckoutTaskImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getCheckoutTask()
	 * @generated
	 */
	int CHECKOUT_TASK = 6;

	/**
	 * The feature id for the '<em><b>Conditions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT_TASK__CONDITIONS = TASK__CONDITIONS;

	/**
	 * The feature id for the '<em><b>Repository</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT_TASK__REPOSITORY = TASK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT_TASK__PATH = TASK_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Force Clean Build</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT_TASK__FORCE_CLEAN_BUILD = TASK_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Default Repository</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT_TASK__DEFAULT_REPOSITORY = TASK_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Checkout Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT_TASK_FEATURE_COUNT = TASK_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Checkout Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT_TASK_OPERATION_COUNT = TASK_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.MavenTaskImpl <em>Maven Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.MavenTaskImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getMavenTask()
	 * @generated
	 */
	int MAVEN_TASK = 7;

	/**
	 * The feature id for the '<em><b>Conditions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAVEN_TASK__CONDITIONS = TASK__CONDITIONS;

	/**
	 * The feature id for the '<em><b>Executable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAVEN_TASK__EXECUTABLE = TASK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Jdk</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAVEN_TASK__JDK = TASK_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Goal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAVEN_TASK__GOAL = TASK_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Tests</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAVEN_TASK__TESTS = TASK_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Environment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAVEN_TASK__ENVIRONMENT = TASK_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Working Dir</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAVEN_TASK__WORKING_DIR = TASK_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Project File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAVEN_TASK__PROJECT_FILE = TASK_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Use Return Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAVEN_TASK__USE_RETURN_CODE = TASK_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Maven Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAVEN_TASK_FEATURE_COUNT = TASK_FEATURE_COUNT + 8;

	/**
	 * The number of operations of the '<em>Maven Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAVEN_TASK_OPERATION_COUNT = TASK_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.InjectVariablesTaskImpl <em>Inject Variables Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.InjectVariablesTaskImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getInjectVariablesTask()
	 * @generated
	 */
	int INJECT_VARIABLES_TASK = 8;

	/**
	 * The feature id for the '<em><b>Conditions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INJECT_VARIABLES_TASK__CONDITIONS = TASK__CONDITIONS;

	/**
	 * The feature id for the '<em><b>File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INJECT_VARIABLES_TASK__FILE = TASK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Scope</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INJECT_VARIABLES_TASK__SCOPE = TASK_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INJECT_VARIABLES_TASK__NAMESPACE = TASK_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Inject Variables Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INJECT_VARIABLES_TASK_FEATURE_COUNT = TASK_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Inject Variables Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INJECT_VARIABLES_TASK_OPERATION_COUNT = TASK_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.TestParserTaskImpl <em>Test Parser Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.TestParserTaskImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getTestParserTask()
	 * @generated
	 */
	int TEST_PARSER_TASK = 9;

	/**
	 * The feature id for the '<em><b>Conditions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_PARSER_TASK__CONDITIONS = TASK__CONDITIONS;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_PARSER_TASK__TYPE = TASK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Test Results</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_PARSER_TASK__TEST_RESULTS = TASK_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Ignore Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_PARSER_TASK__IGNORE_TIME = TASK_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Test Parser Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_PARSER_TASK_FEATURE_COUNT = TASK_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Test Parser Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_PARSER_TASK_OPERATION_COUNT = TASK_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.ArtifactDownloadTaskImpl <em>Artifact Download Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.ArtifactDownloadTaskImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getArtifactDownloadTask()
	 * @generated
	 */
	int ARTIFACT_DOWNLOAD_TASK = 10;

	/**
	 * The feature id for the '<em><b>Conditions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DOWNLOAD_TASK__CONDITIONS = TASK__CONDITIONS;

	/**
	 * The feature id for the '<em><b>Source Plan</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DOWNLOAD_TASK__SOURCE_PLAN = TASK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Destination</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DOWNLOAD_TASK__DESTINATION = TASK_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Artifacts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DOWNLOAD_TASK__ARTIFACTS = TASK_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Artifact Download Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DOWNLOAD_TASK_FEATURE_COUNT = TASK_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Artifact Download Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DOWNLOAD_TASK_OPERATION_COUNT = TASK_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.ArtifactDownloadItemImpl <em>Artifact Download Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.ArtifactDownloadItemImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getArtifactDownloadItem()
	 * @generated
	 */
	int ARTIFACT_DOWNLOAD_ITEM = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DOWNLOAD_ITEM__NAME = 0;

	/**
	 * The feature id for the '<em><b>Destination</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DOWNLOAD_ITEM__DESTINATION = 1;

	/**
	 * The number of structural features of the '<em>Artifact Download Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DOWNLOAD_ITEM_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Artifact Download Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_DOWNLOAD_ITEM_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.AnyTaskImpl <em>Any Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.AnyTaskImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getAnyTask()
	 * @generated
	 */
	int ANY_TASK = 12;

	/**
	 * The feature id for the '<em><b>Conditions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANY_TASK__CONDITIONS = TASK__CONDITIONS;

	/**
	 * The feature id for the '<em><b>Plugin Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANY_TASK__PLUGIN_KEY = TASK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Configuration</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANY_TASK__CONFIGURATION = TASK_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Any Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANY_TASK_FEATURE_COUNT = TASK_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Any Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANY_TASK_OPERATION_COUNT = TASK_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.TaskConditionImpl <em>Task Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.TaskConditionImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getTaskCondition()
	 * @generated
	 */
	int TASK_CONDITION = 13;

	/**
	 * The feature id for the '<em><b>Variable Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_CONDITION__VARIABLE_NAME = 0;

	/**
	 * The feature id for the '<em><b>Variable Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_CONDITION__VARIABLE_VALUE = 1;

	/**
	 * The number of structural features of the '<em>Task Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_CONDITION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Task Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_CONDITION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.TriggerImpl <em>Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.TriggerImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getTrigger()
	 * @generated
	 */
	int TRIGGER = 14;

	/**
	 * The number of structural features of the '<em>Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.PollingTriggerImpl <em>Polling Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.PollingTriggerImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getPollingTrigger()
	 * @generated
	 */
	int POLLING_TRIGGER = 15;

	/**
	 * The feature id for the '<em><b>Period</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLLING_TRIGGER__PERIOD = TRIGGER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Cron Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLLING_TRIGGER__CRON_EXPRESSION = TRIGGER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Repositories</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLLING_TRIGGER__REPOSITORIES = TRIGGER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Conditions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLLING_TRIGGER__CONDITIONS = TRIGGER_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Polling Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLLING_TRIGGER_FEATURE_COUNT = TRIGGER_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Polling Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLLING_TRIGGER_OPERATION_COUNT = TRIGGER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.CronTriggerImpl <em>Cron Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.CronTriggerImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getCronTrigger()
	 * @generated
	 */
	int CRON_TRIGGER = 16;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRON_TRIGGER__EXPRESSION = TRIGGER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Cron Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRON_TRIGGER_FEATURE_COUNT = TRIGGER_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Cron Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRON_TRIGGER_OPERATION_COUNT = TRIGGER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.RemoteTriggerImpl <em>Remote Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.RemoteTriggerImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getRemoteTrigger()
	 * @generated
	 */
	int REMOTE_TRIGGER = 17;

	/**
	 * The feature id for the '<em><b>Ip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOTE_TRIGGER__IP = TRIGGER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Remote Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOTE_TRIGGER_FEATURE_COUNT = TRIGGER_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Remote Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOTE_TRIGGER_OPERATION_COUNT = TRIGGER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.AfterDeploymentTriggerImpl <em>After Deployment Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.AfterDeploymentTriggerImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getAfterDeploymentTrigger()
	 * @generated
	 */
	int AFTER_DEPLOYMENT_TRIGGER = 18;

	/**
	 * The feature id for the '<em><b>Deployment Project</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AFTER_DEPLOYMENT_TRIGGER__DEPLOYMENT_PROJECT = TRIGGER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Environment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AFTER_DEPLOYMENT_TRIGGER__ENVIRONMENT = TRIGGER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>After Deployment Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AFTER_DEPLOYMENT_TRIGGER_FEATURE_COUNT = TRIGGER_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>After Deployment Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AFTER_DEPLOYMENT_TRIGGER_OPERATION_COUNT = TRIGGER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.BuildSuccessTriggerImpl <em>Build Success Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.BuildSuccessTriggerImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getBuildSuccessTrigger()
	 * @generated
	 */
	int BUILD_SUCCESS_TRIGGER = 19;

	/**
	 * The feature id for the '<em><b>Branch</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILD_SUCCESS_TRIGGER__BRANCH = TRIGGER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Build Success Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILD_SUCCESS_TRIGGER_FEATURE_COUNT = TRIGGER_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Build Success Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILD_SUCCESS_TRIGGER_OPERATION_COUNT = TRIGGER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.StageSuccessTriggerImpl <em>Stage Success Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.StageSuccessTriggerImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getStageSuccessTrigger()
	 * @generated
	 */
	int STAGE_SUCCESS_TRIGGER = 20;

	/**
	 * The feature id for the '<em><b>Stage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE_SUCCESS_TRIGGER__STAGE = TRIGGER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Branch</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE_SUCCESS_TRIGGER__BRANCH = TRIGGER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Stage Success Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE_SUCCESS_TRIGGER_FEATURE_COUNT = TRIGGER_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Stage Success Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE_SUCCESS_TRIGGER_OPERATION_COUNT = TRIGGER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.EnvironmentSuccessTriggerImpl <em>Environment Success Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.EnvironmentSuccessTriggerImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getEnvironmentSuccessTrigger()
	 * @generated
	 */
	int ENVIRONMENT_SUCCESS_TRIGGER = 21;

	/**
	 * The feature id for the '<em><b>Environment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_SUCCESS_TRIGGER__ENVIRONMENT = TRIGGER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Environment Success Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_SUCCESS_TRIGGER_FEATURE_COUNT = TRIGGER_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Environment Success Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_SUCCESS_TRIGGER_OPERATION_COUNT = TRIGGER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.ScheduledDeploymentTriggerImpl <em>Scheduled Deployment Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.ScheduledDeploymentTriggerImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getScheduledDeploymentTrigger()
	 * @generated
	 */
	int SCHEDULED_DEPLOYMENT_TRIGGER = 22;

	/**
	 * The feature id for the '<em><b>Cron Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULED_DEPLOYMENT_TRIGGER__CRON_EXPRESSION = TRIGGER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Artifact Branch</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULED_DEPLOYMENT_TRIGGER__ARTIFACT_BRANCH = TRIGGER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Scheduled Deployment Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULED_DEPLOYMENT_TRIGGER_FEATURE_COUNT = TRIGGER_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Scheduled Deployment Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULED_DEPLOYMENT_TRIGGER_OPERATION_COUNT = TRIGGER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.TriggerConditionImpl <em>Trigger Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.TriggerConditionImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getTriggerCondition()
	 * @generated
	 */
	int TRIGGER_CONDITION = 23;

	/**
	 * The number of structural features of the '<em>Trigger Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_CONDITION_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Trigger Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_CONDITION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.GreenPlanConditionImpl <em>Green Plan Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.GreenPlanConditionImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getGreenPlanCondition()
	 * @generated
	 */
	int GREEN_PLAN_CONDITION = 24;

	/**
	 * The feature id for the '<em><b>Plan Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GREEN_PLAN_CONDITION__PLAN_KEYS = TRIGGER_CONDITION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Green Plan Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GREEN_PLAN_CONDITION_FEATURE_COUNT = TRIGGER_CONDITION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Green Plan Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GREEN_PLAN_CONDITION_OPERATION_COUNT = TRIGGER_CONDITION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.AllOtherConditionImpl <em>All Other Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.AllOtherConditionImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getAllOtherCondition()
	 * @generated
	 */
	int ALL_OTHER_CONDITION = 25;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_OTHER_CONDITION__PROPERTIES = TRIGGER_CONDITION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>All Other Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_OTHER_CONDITION_FEATURE_COUNT = TRIGGER_CONDITION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>All Other Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_OTHER_CONDITION_OPERATION_COUNT = TRIGGER_CONDITION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.RepositoryImpl <em>Repository</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.RepositoryImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getRepository()
	 * @generated
	 */
	int REPOSITORY = 26;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY__NAME = 0;

	/**
	 * The number of structural features of the '<em>Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.LinkedRepositoryImpl <em>Linked Repository</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.LinkedRepositoryImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getLinkedRepository()
	 * @generated
	 */
	int LINKED_REPOSITORY = 27;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINKED_REPOSITORY__NAME = REPOSITORY__NAME;

	/**
	 * The feature id for the '<em><b>Scope</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINKED_REPOSITORY__SCOPE = REPOSITORY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Linked Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINKED_REPOSITORY_FEATURE_COUNT = REPOSITORY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Linked Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINKED_REPOSITORY_OPERATION_COUNT = REPOSITORY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.GitRepositoryImpl <em>Git Repository</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.GitRepositoryImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getGitRepository()
	 * @generated
	 */
	int GIT_REPOSITORY = 28;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_REPOSITORY__NAME = REPOSITORY__NAME;

	/**
	 * The feature id for the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_REPOSITORY__URL = REPOSITORY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Branch</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_REPOSITORY__BRANCH = REPOSITORY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Username</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_REPOSITORY__USERNAME = REPOSITORY_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_REPOSITORY__PASSWORD = REPOSITORY_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Ssh Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_REPOSITORY__SSH_KEY = REPOSITORY_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Ssh Key Passphrase</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_REPOSITORY__SSH_KEY_PASSPHRASE = REPOSITORY_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Shared Credentials</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_REPOSITORY__SHARED_CREDENTIALS = REPOSITORY_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Shared Credentials Scope</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_REPOSITORY__SHARED_CREDENTIALS_SCOPE = REPOSITORY_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Lfs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_REPOSITORY__LFS = REPOSITORY_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Use Shallow Clones</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_REPOSITORY__USE_SHALLOW_CLONES = REPOSITORY_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Submodules</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_REPOSITORY__SUBMODULES = REPOSITORY_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Fetch All</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_REPOSITORY__FETCH_ALL = REPOSITORY_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Cache On Agents</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_REPOSITORY__CACHE_ON_AGENTS = REPOSITORY_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Verbose Logs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_REPOSITORY__VERBOSE_LOGS = REPOSITORY_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Command Timeout Minutes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_REPOSITORY__COMMAND_TIMEOUT_MINUTES = REPOSITORY_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Change Detection</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_REPOSITORY__CHANGE_DETECTION = REPOSITORY_FEATURE_COUNT + 15;

	/**
	 * The number of structural features of the '<em>Git Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_REPOSITORY_FEATURE_COUNT = REPOSITORY_FEATURE_COUNT + 16;

	/**
	 * The number of operations of the '<em>Git Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_REPOSITORY_OPERATION_COUNT = REPOSITORY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.BitbucketCloudRepositoryImpl <em>Bitbucket Cloud Repository</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.BitbucketCloudRepositoryImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getBitbucketCloudRepository()
	 * @generated
	 */
	int BITBUCKET_CLOUD_REPOSITORY = 29;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BITBUCKET_CLOUD_REPOSITORY__NAME = GIT_REPOSITORY__NAME;

	/**
	 * The feature id for the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BITBUCKET_CLOUD_REPOSITORY__URL = GIT_REPOSITORY__URL;

	/**
	 * The feature id for the '<em><b>Branch</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BITBUCKET_CLOUD_REPOSITORY__BRANCH = GIT_REPOSITORY__BRANCH;

	/**
	 * The feature id for the '<em><b>Username</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BITBUCKET_CLOUD_REPOSITORY__USERNAME = GIT_REPOSITORY__USERNAME;

	/**
	 * The feature id for the '<em><b>Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BITBUCKET_CLOUD_REPOSITORY__PASSWORD = GIT_REPOSITORY__PASSWORD;

	/**
	 * The feature id for the '<em><b>Ssh Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BITBUCKET_CLOUD_REPOSITORY__SSH_KEY = GIT_REPOSITORY__SSH_KEY;

	/**
	 * The feature id for the '<em><b>Ssh Key Passphrase</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BITBUCKET_CLOUD_REPOSITORY__SSH_KEY_PASSPHRASE = GIT_REPOSITORY__SSH_KEY_PASSPHRASE;

	/**
	 * The feature id for the '<em><b>Shared Credentials</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BITBUCKET_CLOUD_REPOSITORY__SHARED_CREDENTIALS = GIT_REPOSITORY__SHARED_CREDENTIALS;

	/**
	 * The feature id for the '<em><b>Shared Credentials Scope</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BITBUCKET_CLOUD_REPOSITORY__SHARED_CREDENTIALS_SCOPE = GIT_REPOSITORY__SHARED_CREDENTIALS_SCOPE;

	/**
	 * The feature id for the '<em><b>Lfs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BITBUCKET_CLOUD_REPOSITORY__LFS = GIT_REPOSITORY__LFS;

	/**
	 * The feature id for the '<em><b>Use Shallow Clones</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BITBUCKET_CLOUD_REPOSITORY__USE_SHALLOW_CLONES = GIT_REPOSITORY__USE_SHALLOW_CLONES;

	/**
	 * The feature id for the '<em><b>Submodules</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BITBUCKET_CLOUD_REPOSITORY__SUBMODULES = GIT_REPOSITORY__SUBMODULES;

	/**
	 * The feature id for the '<em><b>Fetch All</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BITBUCKET_CLOUD_REPOSITORY__FETCH_ALL = GIT_REPOSITORY__FETCH_ALL;

	/**
	 * The feature id for the '<em><b>Cache On Agents</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BITBUCKET_CLOUD_REPOSITORY__CACHE_ON_AGENTS = GIT_REPOSITORY__CACHE_ON_AGENTS;

	/**
	 * The feature id for the '<em><b>Verbose Logs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BITBUCKET_CLOUD_REPOSITORY__VERBOSE_LOGS = GIT_REPOSITORY__VERBOSE_LOGS;

	/**
	 * The feature id for the '<em><b>Command Timeout Minutes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BITBUCKET_CLOUD_REPOSITORY__COMMAND_TIMEOUT_MINUTES = GIT_REPOSITORY__COMMAND_TIMEOUT_MINUTES;

	/**
	 * The feature id for the '<em><b>Change Detection</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BITBUCKET_CLOUD_REPOSITORY__CHANGE_DETECTION = GIT_REPOSITORY__CHANGE_DETECTION;

	/**
	 * The feature id for the '<em><b>Slug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BITBUCKET_CLOUD_REPOSITORY__SLUG = GIT_REPOSITORY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Bitbucket Cloud Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BITBUCKET_CLOUD_REPOSITORY_FEATURE_COUNT = GIT_REPOSITORY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Bitbucket Cloud Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BITBUCKET_CLOUD_REPOSITORY_OPERATION_COUNT = GIT_REPOSITORY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.BitbucketServerRepositoryImpl <em>Bitbucket Server Repository</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.BitbucketServerRepositoryImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getBitbucketServerRepository()
	 * @generated
	 */
	int BITBUCKET_SERVER_REPOSITORY = 30;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BITBUCKET_SERVER_REPOSITORY__NAME = REPOSITORY__NAME;

	/**
	 * The feature id for the '<em><b>Server</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BITBUCKET_SERVER_REPOSITORY__SERVER = REPOSITORY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Project</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BITBUCKET_SERVER_REPOSITORY__PROJECT = REPOSITORY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Slug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BITBUCKET_SERVER_REPOSITORY__SLUG = REPOSITORY_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Clone Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BITBUCKET_SERVER_REPOSITORY__CLONE_URL = REPOSITORY_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Public Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BITBUCKET_SERVER_REPOSITORY__PUBLIC_KEY = REPOSITORY_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Private Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BITBUCKET_SERVER_REPOSITORY__PRIVATE_KEY = REPOSITORY_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Branch</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BITBUCKET_SERVER_REPOSITORY__BRANCH = REPOSITORY_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Lfs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BITBUCKET_SERVER_REPOSITORY__LFS = REPOSITORY_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Use Shallow Clones</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BITBUCKET_SERVER_REPOSITORY__USE_SHALLOW_CLONES = REPOSITORY_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Submodules</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BITBUCKET_SERVER_REPOSITORY__SUBMODULES = REPOSITORY_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Command Timeout Minutes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BITBUCKET_SERVER_REPOSITORY__COMMAND_TIMEOUT_MINUTES = REPOSITORY_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Fetch All</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BITBUCKET_SERVER_REPOSITORY__FETCH_ALL = REPOSITORY_FEATURE_COUNT + 11;

	/**
	 * The number of structural features of the '<em>Bitbucket Server Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BITBUCKET_SERVER_REPOSITORY_FEATURE_COUNT = REPOSITORY_FEATURE_COUNT + 12;

	/**
	 * The number of operations of the '<em>Bitbucket Server Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BITBUCKET_SERVER_REPOSITORY_OPERATION_COUNT = REPOSITORY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.GithubRepositoryImpl <em>Github Repository</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.GithubRepositoryImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getGithubRepository()
	 * @generated
	 */
	int GITHUB_REPOSITORY = 31;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GITHUB_REPOSITORY__NAME = GIT_REPOSITORY__NAME;

	/**
	 * The feature id for the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GITHUB_REPOSITORY__URL = GIT_REPOSITORY__URL;

	/**
	 * The feature id for the '<em><b>Branch</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GITHUB_REPOSITORY__BRANCH = GIT_REPOSITORY__BRANCH;

	/**
	 * The feature id for the '<em><b>Username</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GITHUB_REPOSITORY__USERNAME = GIT_REPOSITORY__USERNAME;

	/**
	 * The feature id for the '<em><b>Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GITHUB_REPOSITORY__PASSWORD = GIT_REPOSITORY__PASSWORD;

	/**
	 * The feature id for the '<em><b>Ssh Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GITHUB_REPOSITORY__SSH_KEY = GIT_REPOSITORY__SSH_KEY;

	/**
	 * The feature id for the '<em><b>Ssh Key Passphrase</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GITHUB_REPOSITORY__SSH_KEY_PASSPHRASE = GIT_REPOSITORY__SSH_KEY_PASSPHRASE;

	/**
	 * The feature id for the '<em><b>Shared Credentials</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GITHUB_REPOSITORY__SHARED_CREDENTIALS = GIT_REPOSITORY__SHARED_CREDENTIALS;

	/**
	 * The feature id for the '<em><b>Shared Credentials Scope</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GITHUB_REPOSITORY__SHARED_CREDENTIALS_SCOPE = GIT_REPOSITORY__SHARED_CREDENTIALS_SCOPE;

	/**
	 * The feature id for the '<em><b>Lfs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GITHUB_REPOSITORY__LFS = GIT_REPOSITORY__LFS;

	/**
	 * The feature id for the '<em><b>Use Shallow Clones</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GITHUB_REPOSITORY__USE_SHALLOW_CLONES = GIT_REPOSITORY__USE_SHALLOW_CLONES;

	/**
	 * The feature id for the '<em><b>Submodules</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GITHUB_REPOSITORY__SUBMODULES = GIT_REPOSITORY__SUBMODULES;

	/**
	 * The feature id for the '<em><b>Fetch All</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GITHUB_REPOSITORY__FETCH_ALL = GIT_REPOSITORY__FETCH_ALL;

	/**
	 * The feature id for the '<em><b>Cache On Agents</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GITHUB_REPOSITORY__CACHE_ON_AGENTS = GIT_REPOSITORY__CACHE_ON_AGENTS;

	/**
	 * The feature id for the '<em><b>Verbose Logs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GITHUB_REPOSITORY__VERBOSE_LOGS = GIT_REPOSITORY__VERBOSE_LOGS;

	/**
	 * The feature id for the '<em><b>Command Timeout Minutes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GITHUB_REPOSITORY__COMMAND_TIMEOUT_MINUTES = GIT_REPOSITORY__COMMAND_TIMEOUT_MINUTES;

	/**
	 * The feature id for the '<em><b>Change Detection</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GITHUB_REPOSITORY__CHANGE_DETECTION = GIT_REPOSITORY__CHANGE_DETECTION;

	/**
	 * The feature id for the '<em><b>Repository</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GITHUB_REPOSITORY__REPOSITORY = GIT_REPOSITORY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GITHUB_REPOSITORY__USER = GIT_REPOSITORY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Github Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GITHUB_REPOSITORY_FEATURE_COUNT = GIT_REPOSITORY_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Github Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GITHUB_REPOSITORY_OPERATION_COUNT = GIT_REPOSITORY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.AnyVcsRepositoryImpl <em>Any Vcs Repository</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.AnyVcsRepositoryImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getAnyVcsRepository()
	 * @generated
	 */
	int ANY_VCS_REPOSITORY = 32;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANY_VCS_REPOSITORY__NAME = REPOSITORY__NAME;

	/**
	 * The feature id for the '<em><b>Plugin Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANY_VCS_REPOSITORY__PLUGIN_KEY = REPOSITORY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Server Config</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANY_VCS_REPOSITORY__SERVER_CONFIG = REPOSITORY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Branch Config</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANY_VCS_REPOSITORY__BRANCH_CONFIG = REPOSITORY_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Any Vcs Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANY_VCS_REPOSITORY_FEATURE_COUNT = REPOSITORY_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Any Vcs Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANY_VCS_REPOSITORY_OPERATION_COUNT = REPOSITORY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.ChangeDetectionImpl <em>Change Detection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.ChangeDetectionImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getChangeDetection()
	 * @generated
	 */
	int CHANGE_DETECTION = 33;

	/**
	 * The feature id for the '<em><b>Quiet Period</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_DETECTION__QUIET_PERIOD = 0;

	/**
	 * The feature id for the '<em><b>Exclude Changeset Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_DETECTION__EXCLUDE_CHANGESET_PATTERN = 1;

	/**
	 * The feature id for the '<em><b>File Filter Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_DETECTION__FILE_FILTER_TYPE = 2;

	/**
	 * The feature id for the '<em><b>File Filter Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_DETECTION__FILE_FILTER_PATTERN = 3;

	/**
	 * The number of structural features of the '<em>Change Detection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_DETECTION_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Change Detection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_DETECTION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.QuietPeriodImpl <em>Quiet Period</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.QuietPeriodImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getQuietPeriod()
	 * @generated
	 */
	int QUIET_PERIOD = 34;

	/**
	 * The feature id for the '<em><b>Quiet Period Seconds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUIET_PERIOD__QUIET_PERIOD_SECONDS = 0;

	/**
	 * The feature id for the '<em><b>Max Retries</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUIET_PERIOD__MAX_RETRIES = 1;

	/**
	 * The number of structural features of the '<em>Quiet Period</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUIET_PERIOD_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Quiet Period</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUIET_PERIOD_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.ArtifactImpl <em>Artifact</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.ArtifactImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getArtifact()
	 * @generated
	 */
	int ARTIFACT = 35;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__LOCATION = 1;

	/**
	 * The feature id for the '<em><b>Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__PATTERN = 2;

	/**
	 * The feature id for the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__REQUIRED = 3;

	/**
	 * The feature id for the '<em><b>Shared</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__SHARED = 4;

	/**
	 * The feature id for the '<em><b>Http Compression On</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__HTTP_COMPRESSION_ON = 5;

	/**
	 * The number of structural features of the '<em>Artifact</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Artifact</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.ArtifactSubscriptionImpl <em>Artifact Subscription</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.ArtifactSubscriptionImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getArtifactSubscription()
	 * @generated
	 */
	int ARTIFACT_SUBSCRIPTION = 36;

	/**
	 * The feature id for the '<em><b>Artifact</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_SUBSCRIPTION__ARTIFACT = 0;

	/**
	 * The feature id for the '<em><b>Destination</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_SUBSCRIPTION__DESTINATION = 1;

	/**
	 * The number of structural features of the '<em>Artifact Subscription</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_SUBSCRIPTION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Artifact Subscription</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_SUBSCRIPTION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.RequirementImpl <em>Requirement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.RequirementImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getRequirement()
	 * @generated
	 */
	int REQUIREMENT = 37;

	/**
	 * The feature id for the '<em><b>Capability</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__CAPABILITY = 0;

	/**
	 * The feature id for the '<em><b>Match Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__MATCH_VALUE = 1;

	/**
	 * The number of structural features of the '<em>Requirement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Requirement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.VariableAssignmentImpl <em>Variable Assignment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.VariableAssignmentImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getVariableAssignment()
	 * @generated
	 */
	int VARIABLE_ASSIGNMENT = 38;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_ASSIGNMENT__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_ASSIGNMENT__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Variable Assignment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_ASSIGNMENT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Variable Assignment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_ASSIGNMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.LabelImpl <em>Label</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.LabelImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getLabel()
	 * @generated
	 */
	int LABEL = 39;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__VALUE = 0;

	/**
	 * The number of structural features of the '<em>Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.NotificationImpl <em>Notification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.NotificationImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getNotification()
	 * @generated
	 */
	int NOTIFICATION = 40;

	/**
	 * The feature id for the '<em><b>Recipients</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTIFICATION__RECIPIENTS = 0;

	/**
	 * The feature id for the '<em><b>Events</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTIFICATION__EVENTS = 1;

	/**
	 * The number of structural features of the '<em>Notification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTIFICATION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Notification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTIFICATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.NotificationRecipientImpl <em>Notification Recipient</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.NotificationRecipientImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getNotificationRecipient()
	 * @generated
	 */
	int NOTIFICATION_RECIPIENT = 41;

	/**
	 * The number of structural features of the '<em>Notification Recipient</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTIFICATION_RECIPIENT_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Notification Recipient</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTIFICATION_RECIPIENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.UserRecipientImpl <em>User Recipient</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.UserRecipientImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getUserRecipient()
	 * @generated
	 */
	int USER_RECIPIENT = 42;

	/**
	 * The feature id for the '<em><b>Users</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_RECIPIENT__USERS = NOTIFICATION_RECIPIENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>User Recipient</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_RECIPIENT_FEATURE_COUNT = NOTIFICATION_RECIPIENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>User Recipient</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_RECIPIENT_OPERATION_COUNT = NOTIFICATION_RECIPIENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.GroupRecipientImpl <em>Group Recipient</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.GroupRecipientImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getGroupRecipient()
	 * @generated
	 */
	int GROUP_RECIPIENT = 43;

	/**
	 * The feature id for the '<em><b>Groups</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_RECIPIENT__GROUPS = NOTIFICATION_RECIPIENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Group Recipient</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_RECIPIENT_FEATURE_COUNT = NOTIFICATION_RECIPIENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Group Recipient</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_RECIPIENT_OPERATION_COUNT = NOTIFICATION_RECIPIENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.EmailRecipientImpl <em>Email Recipient</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.EmailRecipientImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getEmailRecipient()
	 * @generated
	 */
	int EMAIL_RECIPIENT = 44;

	/**
	 * The feature id for the '<em><b>Emails</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_RECIPIENT__EMAILS = NOTIFICATION_RECIPIENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Email Recipient</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_RECIPIENT_FEATURE_COUNT = NOTIFICATION_RECIPIENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Email Recipient</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_RECIPIENT_OPERATION_COUNT = NOTIFICATION_RECIPIENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.RoleRecipientImpl <em>Role Recipient</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.RoleRecipientImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getRoleRecipient()
	 * @generated
	 */
	int ROLE_RECIPIENT = 45;

	/**
	 * The feature id for the '<em><b>Role</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_RECIPIENT__ROLE = NOTIFICATION_RECIPIENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Role Recipient</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_RECIPIENT_FEATURE_COUNT = NOTIFICATION_RECIPIENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Role Recipient</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_RECIPIENT_OPERATION_COUNT = NOTIFICATION_RECIPIENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.WebhookRecipientImpl <em>Webhook Recipient</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.WebhookRecipientImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getWebhookRecipient()
	 * @generated
	 */
	int WEBHOOK_RECIPIENT = 46;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEBHOOK_RECIPIENT__NAME = NOTIFICATION_RECIPIENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEBHOOK_RECIPIENT__URL = NOTIFICATION_RECIPIENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Webhook Recipient</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEBHOOK_RECIPIENT_FEATURE_COUNT = NOTIFICATION_RECIPIENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Webhook Recipient</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEBHOOK_RECIPIENT_OPERATION_COUNT = NOTIFICATION_RECIPIENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.NotificationEventImpl <em>Notification Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.NotificationEventImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getNotificationEvent()
	 * @generated
	 */
	int NOTIFICATION_EVENT = 47;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTIFICATION_EVENT__TYPE = 0;

	/**
	 * The feature id for the '<em><b>Failures</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTIFICATION_EVENT__FAILURES = 1;

	/**
	 * The feature id for the '<em><b>First Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTIFICATION_EVENT__FIRST_ONLY = 2;

	/**
	 * The number of structural features of the '<em>Notification Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTIFICATION_EVENT_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Notification Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTIFICATION_EVENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.BranchManagementImpl <em>Branch Management</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.BranchManagementImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getBranchManagement()
	 * @generated
	 */
	int BRANCH_MANAGEMENT = 48;

	/**
	 * The feature id for the '<em><b>Create</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BRANCH_MANAGEMENT__CREATE = 0;

	/**
	 * The feature id for the '<em><b>Create Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BRANCH_MANAGEMENT__CREATE_PATTERN = 1;

	/**
	 * The feature id for the '<em><b>Accept Fork</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BRANCH_MANAGEMENT__ACCEPT_FORK = 2;

	/**
	 * The feature id for the '<em><b>Delete</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BRANCH_MANAGEMENT__DELETE = 3;

	/**
	 * The feature id for the '<em><b>Integration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BRANCH_MANAGEMENT__INTEGRATION = 4;

	/**
	 * The feature id for the '<em><b>Link To Jira</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BRANCH_MANAGEMENT__LINK_TO_JIRA = 5;

	/**
	 * The number of structural features of the '<em>Branch Management</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BRANCH_MANAGEMENT_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Branch Management</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BRANCH_MANAGEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.BranchDeleteImpl <em>Branch Delete</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.BranchDeleteImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getBranchDelete()
	 * @generated
	 */
	int BRANCH_DELETE = 49;

	/**
	 * The feature id for the '<em><b>After Deleted Days</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BRANCH_DELETE__AFTER_DELETED_DAYS = 0;

	/**
	 * The feature id for the '<em><b>After Inactive Days</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BRANCH_DELETE__AFTER_INACTIVE_DAYS = 1;

	/**
	 * The number of structural features of the '<em>Branch Delete</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BRANCH_DELETE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Branch Delete</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BRANCH_DELETE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.BranchIntegrationImpl <em>Branch Integration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.BranchIntegrationImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getBranchIntegration()
	 * @generated
	 */
	int BRANCH_INTEGRATION = 50;

	/**
	 * The feature id for the '<em><b>Merge From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BRANCH_INTEGRATION__MERGE_FROM = 0;

	/**
	 * The feature id for the '<em><b>Merge To</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BRANCH_INTEGRATION__MERGE_TO = 1;

	/**
	 * The feature id for the '<em><b>Push On Success</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BRANCH_INTEGRATION__PUSH_ON_SUCCESS = 2;

	/**
	 * The number of structural features of the '<em>Branch Integration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BRANCH_INTEGRATION_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Branch Integration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BRANCH_INTEGRATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.BranchOverrideImpl <em>Branch Override</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.BranchOverrideImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getBranchOverride()
	 * @generated
	 */
	int BRANCH_OVERRIDE = 51;

	/**
	 * The feature id for the '<em><b>Branch Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BRANCH_OVERRIDE__BRANCH_PATTERN = 0;

	/**
	 * The feature id for the '<em><b>Stages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BRANCH_OVERRIDE__STAGES = 1;

	/**
	 * The feature id for the '<em><b>Jobs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BRANCH_OVERRIDE__JOBS = 2;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BRANCH_OVERRIDE__TRIGGERS = 3;

	/**
	 * The feature id for the '<em><b>Notifications</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BRANCH_OVERRIDE__NOTIFICATIONS = 4;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BRANCH_OVERRIDE__VARIABLES = 5;

	/**
	 * The feature id for the '<em><b>Labels</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BRANCH_OVERRIDE__LABELS = 6;

	/**
	 * The feature id for the '<em><b>Docker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BRANCH_OVERRIDE__DOCKER = 7;

	/**
	 * The feature id for the '<em><b>Branch Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BRANCH_OVERRIDE__BRANCH_CONFIG = 8;

	/**
	 * The number of structural features of the '<em>Branch Override</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BRANCH_OVERRIDE_FEATURE_COUNT = 9;

	/**
	 * The number of operations of the '<em>Branch Override</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BRANCH_OVERRIDE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.BranchSpecificConfigImpl <em>Branch Specific Config</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.BranchSpecificConfigImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getBranchSpecificConfig()
	 * @generated
	 */
	int BRANCH_SPECIFIC_CONFIG = 52;

	/**
	 * The feature id for the '<em><b>Integration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BRANCH_SPECIFIC_CONFIG__INTEGRATION = 0;

	/**
	 * The feature id for the '<em><b>Disable Expiry</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BRANCH_SPECIFIC_CONFIG__DISABLE_EXPIRY = 1;

	/**
	 * The number of structural features of the '<em>Branch Specific Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BRANCH_SPECIFIC_CONFIG_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Branch Specific Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BRANCH_SPECIFIC_CONFIG_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.DependenciesImpl <em>Dependencies</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.DependenciesImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getDependencies()
	 * @generated
	 */
	int DEPENDENCIES = 53;

	/**
	 * The feature id for the '<em><b>Require All Stages Passing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPENDENCIES__REQUIRE_ALL_STAGES_PASSING = 0;

	/**
	 * The feature id for the '<em><b>Enabled For Branches</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPENDENCIES__ENABLED_FOR_BRANCHES = 1;

	/**
	 * The feature id for the '<em><b>Block Strategy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPENDENCIES__BLOCK_STRATEGY = 2;

	/**
	 * The feature id for the '<em><b>Plans</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPENDENCIES__PLANS = 3;

	/**
	 * The number of structural features of the '<em>Dependencies</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPENDENCIES_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Dependencies</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPENDENCIES_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.DockerConfigImpl <em>Docker Config</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.DockerConfigImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getDockerConfig()
	 * @generated
	 */
	int DOCKER_CONFIG = 54;

	/**
	 * The feature id for the '<em><b>Image</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCKER_CONFIG__IMAGE = 0;

	/**
	 * The feature id for the '<em><b>Volumes</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCKER_CONFIG__VOLUMES = 1;

	/**
	 * The feature id for the '<em><b>Use Default Volumes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCKER_CONFIG__USE_DEFAULT_VOLUMES = 2;

	/**
	 * The feature id for the '<em><b>Docker Run Arguments</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCKER_CONFIG__DOCKER_RUN_ARGUMENTS = 3;

	/**
	 * The number of structural features of the '<em>Docker Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCKER_CONFIG_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Docker Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCKER_CONFIG_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.OtherConfigImpl <em>Other Config</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.OtherConfigImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getOtherConfig()
	 * @generated
	 */
	int OTHER_CONFIG = 55;

	/**
	 * The feature id for the '<em><b>Concurrent Build Plugin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OTHER_CONFIG__CONCURRENT_BUILD_PLUGIN = 0;

	/**
	 * The feature id for the '<em><b>Clean Working Dir</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OTHER_CONFIG__CLEAN_WORKING_DIR = 1;

	/**
	 * The feature id for the '<em><b>All Other Apps</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OTHER_CONFIG__ALL_OTHER_APPS = 2;

	/**
	 * The number of structural features of the '<em>Other Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OTHER_CONFIG_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Other Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OTHER_CONFIG_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.DeploymentProjectImpl <em>Deployment Project</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.DeploymentProjectImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getDeploymentProject()
	 * @generated
	 */
	int DEPLOYMENT_PROJECT = 56;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_PROJECT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Source Plan</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_PROJECT__SOURCE_PLAN = 1;

	/**
	 * The feature id for the '<em><b>Server Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_PROJECT__SERVER_NAME = 2;

	/**
	 * The feature id for the '<em><b>Release Naming</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_PROJECT__RELEASE_NAMING = 3;

	/**
	 * The feature id for the '<em><b>Environments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_PROJECT__ENVIRONMENTS = 4;

	/**
	 * The feature id for the '<em><b>Permissions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_PROJECT__PERMISSIONS = 5;

	/**
	 * The feature id for the '<em><b>Default Environment Permissions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_PROJECT__DEFAULT_ENVIRONMENT_PERMISSIONS = 6;

	/**
	 * The feature id for the '<em><b>Environment Permissions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_PROJECT__ENVIRONMENT_PERMISSIONS = 7;

	/**
	 * The number of structural features of the '<em>Deployment Project</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_PROJECT_FEATURE_COUNT = 8;

	/**
	 * The number of operations of the '<em>Deployment Project</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_PROJECT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.ReleaseNamingImpl <em>Release Naming</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.ReleaseNamingImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getReleaseNaming()
	 * @generated
	 */
	int RELEASE_NAMING = 57;

	/**
	 * The feature id for the '<em><b>Next Version Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELEASE_NAMING__NEXT_VERSION_NAME = 0;

	/**
	 * The feature id for the '<em><b>Applies To Branches</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELEASE_NAMING__APPLIES_TO_BRANCHES = 1;

	/**
	 * The feature id for the '<em><b>Auto Increment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELEASE_NAMING__AUTO_INCREMENT = 2;

	/**
	 * The feature id for the '<em><b>Auto Increment Variables</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELEASE_NAMING__AUTO_INCREMENT_VARIABLES = 3;

	/**
	 * The number of structural features of the '<em>Release Naming</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELEASE_NAMING_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Release Naming</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELEASE_NAMING_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.EnvironmentImpl <em>Environment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.EnvironmentImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getEnvironment()
	 * @generated
	 */
	int ENVIRONMENT = 58;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Tasks</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT__TASKS = 1;

	/**
	 * The feature id for the '<em><b>Final Tasks</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT__FINAL_TASKS = 2;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT__VARIABLES = 3;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT__TRIGGERS = 4;

	/**
	 * The feature id for the '<em><b>Notifications</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT__NOTIFICATIONS = 5;

	/**
	 * The feature id for the '<em><b>Docker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT__DOCKER = 6;

	/**
	 * The feature id for the '<em><b>Requirements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT__REQUIREMENTS = 7;

	/**
	 * The feature id for the '<em><b>Release Approval Prerequisite</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT__RELEASE_APPROVAL_PREREQUISITE = 8;

	/**
	 * The number of structural features of the '<em>Environment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_FEATURE_COUNT = 9;

	/**
	 * The number of operations of the '<em>Environment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.PermissionEntryImpl <em>Permission Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.PermissionEntryImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getPermissionEntry()
	 * @generated
	 */
	int PERMISSION_ENTRY = 59;

	/**
	 * The feature id for the '<em><b>Users</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERMISSION_ENTRY__USERS = 0;

	/**
	 * The feature id for the '<em><b>Groups</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERMISSION_ENTRY__GROUPS = 1;

	/**
	 * The feature id for the '<em><b>Roles</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERMISSION_ENTRY__ROLES = 2;

	/**
	 * The feature id for the '<em><b>Permissions</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERMISSION_ENTRY__PERMISSIONS = 3;

	/**
	 * The number of structural features of the '<em>Permission Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERMISSION_ENTRY_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Permission Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERMISSION_ENTRY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.PlanPermissionEntryImpl <em>Plan Permission Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.PlanPermissionEntryImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getPlanPermissionEntry()
	 * @generated
	 */
	int PLAN_PERMISSION_ENTRY = 60;

	/**
	 * The feature id for the '<em><b>Users</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN_PERMISSION_ENTRY__USERS = PERMISSION_ENTRY__USERS;

	/**
	 * The feature id for the '<em><b>Groups</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN_PERMISSION_ENTRY__GROUPS = PERMISSION_ENTRY__GROUPS;

	/**
	 * The feature id for the '<em><b>Roles</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN_PERMISSION_ENTRY__ROLES = PERMISSION_ENTRY__ROLES;

	/**
	 * The feature id for the '<em><b>Permissions</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN_PERMISSION_ENTRY__PERMISSIONS = PERMISSION_ENTRY__PERMISSIONS;

	/**
	 * The number of structural features of the '<em>Plan Permission Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN_PERMISSION_ENTRY_FEATURE_COUNT = PERMISSION_ENTRY_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Plan Permission Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAN_PERMISSION_ENTRY_OPERATION_COUNT = PERMISSION_ENTRY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.DeploymentPermissionEntryImpl <em>Deployment Permission Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.DeploymentPermissionEntryImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getDeploymentPermissionEntry()
	 * @generated
	 */
	int DEPLOYMENT_PERMISSION_ENTRY = 61;

	/**
	 * The feature id for the '<em><b>Users</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_PERMISSION_ENTRY__USERS = PERMISSION_ENTRY__USERS;

	/**
	 * The feature id for the '<em><b>Groups</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_PERMISSION_ENTRY__GROUPS = PERMISSION_ENTRY__GROUPS;

	/**
	 * The feature id for the '<em><b>Roles</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_PERMISSION_ENTRY__ROLES = PERMISSION_ENTRY__ROLES;

	/**
	 * The feature id for the '<em><b>Permissions</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_PERMISSION_ENTRY__PERMISSIONS = PERMISSION_ENTRY__PERMISSIONS;

	/**
	 * The number of structural features of the '<em>Deployment Permission Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_PERMISSION_ENTRY_FEATURE_COUNT = PERMISSION_ENTRY_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Deployment Permission Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_PERMISSION_ENTRY_OPERATION_COUNT = PERMISSION_ENTRY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.EnvironmentPermissionEntryImpl <em>Environment Permission Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.EnvironmentPermissionEntryImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getEnvironmentPermissionEntry()
	 * @generated
	 */
	int ENVIRONMENT_PERMISSION_ENTRY = 62;

	/**
	 * The feature id for the '<em><b>Users</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_PERMISSION_ENTRY__USERS = PERMISSION_ENTRY__USERS;

	/**
	 * The feature id for the '<em><b>Groups</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_PERMISSION_ENTRY__GROUPS = PERMISSION_ENTRY__GROUPS;

	/**
	 * The feature id for the '<em><b>Roles</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_PERMISSION_ENTRY__ROLES = PERMISSION_ENTRY__ROLES;

	/**
	 * The feature id for the '<em><b>Permissions</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_PERMISSION_ENTRY__PERMISSIONS = PERMISSION_ENTRY__PERMISSIONS;

	/**
	 * The number of structural features of the '<em>Environment Permission Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_PERMISSION_ENTRY_FEATURE_COUNT = PERMISSION_ENTRY_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Environment Permission Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_PERMISSION_ENTRY_OPERATION_COUNT = PERMISSION_ENTRY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.impl.NamedEnvironmentPermissionImpl <em>Named Environment Permission</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.impl.NamedEnvironmentPermissionImpl
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getNamedEnvironmentPermission()
	 * @generated
	 */
	int NAMED_ENVIRONMENT_PERMISSION = 63;

	/**
	 * The feature id for the '<em><b>Environment Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ENVIRONMENT_PERMISSION__ENVIRONMENT_NAME = 0;

	/**
	 * The feature id for the '<em><b>Entries</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ENVIRONMENT_PERMISSION__ENTRIES = 1;

	/**
	 * The number of structural features of the '<em>Named Environment Permission</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ENVIRONMENT_PERMISSION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Named Environment Permission</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ENVIRONMENT_PERMISSION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.VARIABLE_SCOPE <em>VARIABLE SCOPE</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.VARIABLE_SCOPE
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getVARIABLE_SCOPE()
	 * @generated
	 */
	int VARIABLE_SCOPE = 64;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.TEST_PARSER_TYPE <em>TEST PARSER TYPE</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.TEST_PARSER_TYPE
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getTEST_PARSER_TYPE()
	 * @generated
	 */
	int TEST_PARSER_TYPE = 65;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.REPOSITORY_SCOPE <em>REPOSITORY SCOPE</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.REPOSITORY_SCOPE
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getREPOSITORY_SCOPE()
	 * @generated
	 */
	int REPOSITORY_SCOPE = 66;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.FILE_FILTER_TYPE <em>FILE FILTER TYPE</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.FILE_FILTER_TYPE
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getFILE_FILTER_TYPE()
	 * @generated
	 */
	int FILE_FILTER_TYPE = 67;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.BRANCH_CREATE_STRATEGY <em>BRANCH CREATE STRATEGY</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.BRANCH_CREATE_STRATEGY
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getBRANCH_CREATE_STRATEGY()
	 * @generated
	 */
	int BRANCH_CREATE_STRATEGY = 68;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.BLOCK_STRATEGY <em>BLOCK STRATEGY</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.BLOCK_STRATEGY
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getBLOCK_STRATEGY()
	 * @generated
	 */
	int BLOCK_STRATEGY = 69;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.NOTIFICATION_EVENT_TYPE <em>NOTIFICATION EVENT TYPE</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.NOTIFICATION_EVENT_TYPE
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getNOTIFICATION_EVENT_TYPE()
	 * @generated
	 */
	int NOTIFICATION_EVENT_TYPE = 70;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.NOTIFICATION_ROLE <em>NOTIFICATION ROLE</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.NOTIFICATION_ROLE
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getNOTIFICATION_ROLE()
	 * @generated
	 */
	int NOTIFICATION_ROLE = 71;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.PERMISSION_ROLE <em>PERMISSION ROLE</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.PERMISSION_ROLE
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getPERMISSION_ROLE()
	 * @generated
	 */
	int PERMISSION_ROLE = 72;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.PERMISSION_TYPE <em>PERMISSION TYPE</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.PERMISSION_TYPE
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getPERMISSION_TYPE()
	 * @generated
	 */
	int PERMISSION_TYPE = 73;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.bambooMM.RELEASE_APPROVAL <em>RELEASE APPROVAL</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.bambooMM.RELEASE_APPROVAL
	 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getRELEASE_APPROVAL()
	 * @generated
	 */
	int RELEASE_APPROVAL = 74;

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.Plan <em>Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Plan</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Plan
	 * @generated
	 */
	EClass getPlan();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.Plan#getProjectKey <em>Project Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Project Key</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Plan#getProjectKey()
	 * @see #getPlan()
	 * @generated
	 */
	EAttribute getPlan_ProjectKey();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.Plan#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Plan#getKey()
	 * @see #getPlan()
	 * @generated
	 */
	EAttribute getPlan_Key();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.Plan#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Plan#getName()
	 * @see #getPlan()
	 * @generated
	 */
	EAttribute getPlan_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.Plan#getServerName <em>Server Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Server Name</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Plan#getServerName()
	 * @see #getPlan()
	 * @generated
	 */
	EAttribute getPlan_ServerName();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.bambooMM.Plan#getStages <em>Stages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Stages</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Plan#getStages()
	 * @see #getPlan()
	 * @generated
	 */
	EReference getPlan_Stages();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.bambooMM.Plan#getJobs <em>Jobs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Jobs</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Plan#getJobs()
	 * @see #getPlan()
	 * @generated
	 */
	EReference getPlan_Jobs();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.bambooMM.Plan#getTriggers <em>Triggers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Triggers</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Plan#getTriggers()
	 * @see #getPlan()
	 * @generated
	 */
	EReference getPlan_Triggers();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.bambooMM.Plan#getRepositories <em>Repositories</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Repositories</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Plan#getRepositories()
	 * @see #getPlan()
	 * @generated
	 */
	EReference getPlan_Repositories();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.bambooMM.Plan#getVariables <em>Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Variables</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Plan#getVariables()
	 * @see #getPlan()
	 * @generated
	 */
	EReference getPlan_Variables();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.bambooMM.Plan#getNotifications <em>Notifications</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Notifications</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Plan#getNotifications()
	 * @see #getPlan()
	 * @generated
	 */
	EReference getPlan_Notifications();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.bambooMM.Plan#getLabels <em>Labels</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Labels</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Plan#getLabels()
	 * @see #getPlan()
	 * @generated
	 */
	EReference getPlan_Labels();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.bambooMM.Plan#getBranches <em>Branches</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Branches</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Plan#getBranches()
	 * @see #getPlan()
	 * @generated
	 */
	EReference getPlan_Branches();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.bambooMM.Plan#getDependencies <em>Dependencies</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Dependencies</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Plan#getDependencies()
	 * @see #getPlan()
	 * @generated
	 */
	EReference getPlan_Dependencies();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.bambooMM.Plan#getDocker <em>Docker</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Docker</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Plan#getDocker()
	 * @see #getPlan()
	 * @generated
	 */
	EReference getPlan_Docker();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.bambooMM.Plan#getBranchOverrides <em>Branch Overrides</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Branch Overrides</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Plan#getBranchOverrides()
	 * @see #getPlan()
	 * @generated
	 */
	EReference getPlan_BranchOverrides();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.bambooMM.Plan#getOther <em>Other</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Other</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Plan#getOther()
	 * @see #getPlan()
	 * @generated
	 */
	EReference getPlan_Other();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.bambooMM.Plan#getPermissions <em>Permissions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Permissions</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Plan#getPermissions()
	 * @see #getPlan()
	 * @generated
	 */
	EReference getPlan_Permissions();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.Stage <em>Stage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stage</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Stage
	 * @generated
	 */
	EClass getStage();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.Stage#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Stage#getName()
	 * @see #getStage()
	 * @generated
	 */
	EAttribute getStage_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.Stage#isManual <em>Manual</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Manual</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Stage#isManual()
	 * @see #getStage()
	 * @generated
	 */
	EAttribute getStage_Manual();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.Stage#isFinalStage <em>Final Stage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Final Stage</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Stage#isFinalStage()
	 * @see #getStage()
	 * @generated
	 */
	EAttribute getStage_FinalStage();

	/**
	 * Returns the meta object for the reference list '{@link com.mddoai.metamodel.bambooMM.Stage#getJobRefs <em>Job Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Job Refs</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Stage#getJobRefs()
	 * @see #getStage()
	 * @generated
	 */
	EReference getStage_JobRefs();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.Job <em>Job</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Job</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Job
	 * @generated
	 */
	EClass getJob();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.Job#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Job#getName()
	 * @see #getJob()
	 * @generated
	 */
	EAttribute getJob_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.Job#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Job#getKey()
	 * @see #getJob()
	 * @generated
	 */
	EAttribute getJob_Key();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.bambooMM.Job#getTasks <em>Tasks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tasks</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Job#getTasks()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Tasks();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.bambooMM.Job#getFinalTasks <em>Final Tasks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Final Tasks</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Job#getFinalTasks()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_FinalTasks();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.bambooMM.Job#getArtifacts <em>Artifacts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Artifacts</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Job#getArtifacts()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Artifacts();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.bambooMM.Job#getArtifactSubscriptions <em>Artifact Subscriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Artifact Subscriptions</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Job#getArtifactSubscriptions()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_ArtifactSubscriptions();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.bambooMM.Job#getRequirements <em>Requirements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Requirements</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Job#getRequirements()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Requirements();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.bambooMM.Job#getVariables <em>Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Variables</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Job#getVariables()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Variables();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.bambooMM.Job#getDocker <em>Docker</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Docker</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Job#getDocker()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Docker();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.bambooMM.Job#getOther <em>Other</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Other</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Job#getOther()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Other();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.Task <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Task</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Task
	 * @generated
	 */
	EClass getTask();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.bambooMM.Task#getConditions <em>Conditions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Conditions</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Task#getConditions()
	 * @see #getTask()
	 * @generated
	 */
	EReference getTask_Conditions();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.ScriptTask <em>Script Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Script Task</em>'.
	 * @see com.mddoai.metamodel.bambooMM.ScriptTask
	 * @generated
	 */
	EClass getScriptTask();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.ScriptTask#getInterpreter <em>Interpreter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Interpreter</em>'.
	 * @see com.mddoai.metamodel.bambooMM.ScriptTask#getInterpreter()
	 * @see #getScriptTask()
	 * @generated
	 */
	EAttribute getScriptTask_Interpreter();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.bambooMM.ScriptTask#getScripts <em>Scripts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Scripts</em>'.
	 * @see com.mddoai.metamodel.bambooMM.ScriptTask#getScripts()
	 * @see #getScriptTask()
	 * @generated
	 */
	EAttribute getScriptTask_Scripts();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.ScriptTask#getFile <em>File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File</em>'.
	 * @see com.mddoai.metamodel.bambooMM.ScriptTask#getFile()
	 * @see #getScriptTask()
	 * @generated
	 */
	EAttribute getScriptTask_File();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.ScriptTask#getArgument <em>Argument</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Argument</em>'.
	 * @see com.mddoai.metamodel.bambooMM.ScriptTask#getArgument()
	 * @see #getScriptTask()
	 * @generated
	 */
	EAttribute getScriptTask_Argument();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.ScriptTask#getEnvironment <em>Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Environment</em>'.
	 * @see com.mddoai.metamodel.bambooMM.ScriptTask#getEnvironment()
	 * @see #getScriptTask()
	 * @generated
	 */
	EAttribute getScriptTask_Environment();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.ScriptTask#getWorkingDir <em>Working Dir</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Working Dir</em>'.
	 * @see com.mddoai.metamodel.bambooMM.ScriptTask#getWorkingDir()
	 * @see #getScriptTask()
	 * @generated
	 */
	EAttribute getScriptTask_WorkingDir();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.CleanTask <em>Clean Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Clean Task</em>'.
	 * @see com.mddoai.metamodel.bambooMM.CleanTask
	 * @generated
	 */
	EClass getCleanTask();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.CheckoutTask <em>Checkout Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Checkout Task</em>'.
	 * @see com.mddoai.metamodel.bambooMM.CheckoutTask
	 * @generated
	 */
	EClass getCheckoutTask();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.CheckoutTask#getRepository <em>Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Repository</em>'.
	 * @see com.mddoai.metamodel.bambooMM.CheckoutTask#getRepository()
	 * @see #getCheckoutTask()
	 * @generated
	 */
	EAttribute getCheckoutTask_Repository();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.CheckoutTask#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Path</em>'.
	 * @see com.mddoai.metamodel.bambooMM.CheckoutTask#getPath()
	 * @see #getCheckoutTask()
	 * @generated
	 */
	EAttribute getCheckoutTask_Path();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.CheckoutTask#getForceCleanBuild <em>Force Clean Build</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Force Clean Build</em>'.
	 * @see com.mddoai.metamodel.bambooMM.CheckoutTask#getForceCleanBuild()
	 * @see #getCheckoutTask()
	 * @generated
	 */
	EAttribute getCheckoutTask_ForceCleanBuild();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.CheckoutTask#isDefaultRepository <em>Default Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Repository</em>'.
	 * @see com.mddoai.metamodel.bambooMM.CheckoutTask#isDefaultRepository()
	 * @see #getCheckoutTask()
	 * @generated
	 */
	EAttribute getCheckoutTask_DefaultRepository();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.MavenTask <em>Maven Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Maven Task</em>'.
	 * @see com.mddoai.metamodel.bambooMM.MavenTask
	 * @generated
	 */
	EClass getMavenTask();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.MavenTask#getExecutable <em>Executable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Executable</em>'.
	 * @see com.mddoai.metamodel.bambooMM.MavenTask#getExecutable()
	 * @see #getMavenTask()
	 * @generated
	 */
	EAttribute getMavenTask_Executable();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.MavenTask#getJdk <em>Jdk</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Jdk</em>'.
	 * @see com.mddoai.metamodel.bambooMM.MavenTask#getJdk()
	 * @see #getMavenTask()
	 * @generated
	 */
	EAttribute getMavenTask_Jdk();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.MavenTask#getGoal <em>Goal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Goal</em>'.
	 * @see com.mddoai.metamodel.bambooMM.MavenTask#getGoal()
	 * @see #getMavenTask()
	 * @generated
	 */
	EAttribute getMavenTask_Goal();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.MavenTask#getTests <em>Tests</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tests</em>'.
	 * @see com.mddoai.metamodel.bambooMM.MavenTask#getTests()
	 * @see #getMavenTask()
	 * @generated
	 */
	EAttribute getMavenTask_Tests();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.MavenTask#getEnvironment <em>Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Environment</em>'.
	 * @see com.mddoai.metamodel.bambooMM.MavenTask#getEnvironment()
	 * @see #getMavenTask()
	 * @generated
	 */
	EAttribute getMavenTask_Environment();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.MavenTask#getWorkingDir <em>Working Dir</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Working Dir</em>'.
	 * @see com.mddoai.metamodel.bambooMM.MavenTask#getWorkingDir()
	 * @see #getMavenTask()
	 * @generated
	 */
	EAttribute getMavenTask_WorkingDir();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.MavenTask#getProjectFile <em>Project File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Project File</em>'.
	 * @see com.mddoai.metamodel.bambooMM.MavenTask#getProjectFile()
	 * @see #getMavenTask()
	 * @generated
	 */
	EAttribute getMavenTask_ProjectFile();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.MavenTask#getUseReturnCode <em>Use Return Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Use Return Code</em>'.
	 * @see com.mddoai.metamodel.bambooMM.MavenTask#getUseReturnCode()
	 * @see #getMavenTask()
	 * @generated
	 */
	EAttribute getMavenTask_UseReturnCode();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.InjectVariablesTask <em>Inject Variables Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Inject Variables Task</em>'.
	 * @see com.mddoai.metamodel.bambooMM.InjectVariablesTask
	 * @generated
	 */
	EClass getInjectVariablesTask();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.InjectVariablesTask#getFile <em>File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File</em>'.
	 * @see com.mddoai.metamodel.bambooMM.InjectVariablesTask#getFile()
	 * @see #getInjectVariablesTask()
	 * @generated
	 */
	EAttribute getInjectVariablesTask_File();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.InjectVariablesTask#getScope <em>Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scope</em>'.
	 * @see com.mddoai.metamodel.bambooMM.InjectVariablesTask#getScope()
	 * @see #getInjectVariablesTask()
	 * @generated
	 */
	EAttribute getInjectVariablesTask_Scope();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.InjectVariablesTask#getNamespace <em>Namespace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Namespace</em>'.
	 * @see com.mddoai.metamodel.bambooMM.InjectVariablesTask#getNamespace()
	 * @see #getInjectVariablesTask()
	 * @generated
	 */
	EAttribute getInjectVariablesTask_Namespace();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.TestParserTask <em>Test Parser Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Parser Task</em>'.
	 * @see com.mddoai.metamodel.bambooMM.TestParserTask
	 * @generated
	 */
	EClass getTestParserTask();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.TestParserTask#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see com.mddoai.metamodel.bambooMM.TestParserTask#getType()
	 * @see #getTestParserTask()
	 * @generated
	 */
	EAttribute getTestParserTask_Type();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.bambooMM.TestParserTask#getTestResults <em>Test Results</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Test Results</em>'.
	 * @see com.mddoai.metamodel.bambooMM.TestParserTask#getTestResults()
	 * @see #getTestParserTask()
	 * @generated
	 */
	EAttribute getTestParserTask_TestResults();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.TestParserTask#getIgnoreTime <em>Ignore Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ignore Time</em>'.
	 * @see com.mddoai.metamodel.bambooMM.TestParserTask#getIgnoreTime()
	 * @see #getTestParserTask()
	 * @generated
	 */
	EAttribute getTestParserTask_IgnoreTime();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.ArtifactDownloadTask <em>Artifact Download Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Artifact Download Task</em>'.
	 * @see com.mddoai.metamodel.bambooMM.ArtifactDownloadTask
	 * @generated
	 */
	EClass getArtifactDownloadTask();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.ArtifactDownloadTask#getSourcePlan <em>Source Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source Plan</em>'.
	 * @see com.mddoai.metamodel.bambooMM.ArtifactDownloadTask#getSourcePlan()
	 * @see #getArtifactDownloadTask()
	 * @generated
	 */
	EAttribute getArtifactDownloadTask_SourcePlan();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.ArtifactDownloadTask#getDestination <em>Destination</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Destination</em>'.
	 * @see com.mddoai.metamodel.bambooMM.ArtifactDownloadTask#getDestination()
	 * @see #getArtifactDownloadTask()
	 * @generated
	 */
	EAttribute getArtifactDownloadTask_Destination();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.bambooMM.ArtifactDownloadTask#getArtifacts <em>Artifacts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Artifacts</em>'.
	 * @see com.mddoai.metamodel.bambooMM.ArtifactDownloadTask#getArtifacts()
	 * @see #getArtifactDownloadTask()
	 * @generated
	 */
	EReference getArtifactDownloadTask_Artifacts();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.ArtifactDownloadItem <em>Artifact Download Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Artifact Download Item</em>'.
	 * @see com.mddoai.metamodel.bambooMM.ArtifactDownloadItem
	 * @generated
	 */
	EClass getArtifactDownloadItem();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.ArtifactDownloadItem#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.mddoai.metamodel.bambooMM.ArtifactDownloadItem#getName()
	 * @see #getArtifactDownloadItem()
	 * @generated
	 */
	EAttribute getArtifactDownloadItem_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.ArtifactDownloadItem#getDestination <em>Destination</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Destination</em>'.
	 * @see com.mddoai.metamodel.bambooMM.ArtifactDownloadItem#getDestination()
	 * @see #getArtifactDownloadItem()
	 * @generated
	 */
	EAttribute getArtifactDownloadItem_Destination();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.AnyTask <em>Any Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Any Task</em>'.
	 * @see com.mddoai.metamodel.bambooMM.AnyTask
	 * @generated
	 */
	EClass getAnyTask();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.AnyTask#getPluginKey <em>Plugin Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Plugin Key</em>'.
	 * @see com.mddoai.metamodel.bambooMM.AnyTask#getPluginKey()
	 * @see #getAnyTask()
	 * @generated
	 */
	EAttribute getAnyTask_PluginKey();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.bambooMM.AnyTask#getConfiguration <em>Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Configuration</em>'.
	 * @see com.mddoai.metamodel.bambooMM.AnyTask#getConfiguration()
	 * @see #getAnyTask()
	 * @generated
	 */
	EReference getAnyTask_Configuration();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.TaskCondition <em>Task Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Task Condition</em>'.
	 * @see com.mddoai.metamodel.bambooMM.TaskCondition
	 * @generated
	 */
	EClass getTaskCondition();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.TaskCondition#getVariableName <em>Variable Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Variable Name</em>'.
	 * @see com.mddoai.metamodel.bambooMM.TaskCondition#getVariableName()
	 * @see #getTaskCondition()
	 * @generated
	 */
	EAttribute getTaskCondition_VariableName();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.TaskCondition#getVariableValue <em>Variable Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Variable Value</em>'.
	 * @see com.mddoai.metamodel.bambooMM.TaskCondition#getVariableValue()
	 * @see #getTaskCondition()
	 * @generated
	 */
	EAttribute getTaskCondition_VariableValue();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.Trigger <em>Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Trigger</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Trigger
	 * @generated
	 */
	EClass getTrigger();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.PollingTrigger <em>Polling Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Polling Trigger</em>'.
	 * @see com.mddoai.metamodel.bambooMM.PollingTrigger
	 * @generated
	 */
	EClass getPollingTrigger();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.PollingTrigger#getPeriod <em>Period</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Period</em>'.
	 * @see com.mddoai.metamodel.bambooMM.PollingTrigger#getPeriod()
	 * @see #getPollingTrigger()
	 * @generated
	 */
	EAttribute getPollingTrigger_Period();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.PollingTrigger#getCronExpression <em>Cron Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cron Expression</em>'.
	 * @see com.mddoai.metamodel.bambooMM.PollingTrigger#getCronExpression()
	 * @see #getPollingTrigger()
	 * @generated
	 */
	EAttribute getPollingTrigger_CronExpression();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.bambooMM.PollingTrigger#getRepositories <em>Repositories</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Repositories</em>'.
	 * @see com.mddoai.metamodel.bambooMM.PollingTrigger#getRepositories()
	 * @see #getPollingTrigger()
	 * @generated
	 */
	EAttribute getPollingTrigger_Repositories();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.bambooMM.PollingTrigger#getConditions <em>Conditions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Conditions</em>'.
	 * @see com.mddoai.metamodel.bambooMM.PollingTrigger#getConditions()
	 * @see #getPollingTrigger()
	 * @generated
	 */
	EReference getPollingTrigger_Conditions();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.CronTrigger <em>Cron Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cron Trigger</em>'.
	 * @see com.mddoai.metamodel.bambooMM.CronTrigger
	 * @generated
	 */
	EClass getCronTrigger();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.CronTrigger#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Expression</em>'.
	 * @see com.mddoai.metamodel.bambooMM.CronTrigger#getExpression()
	 * @see #getCronTrigger()
	 * @generated
	 */
	EAttribute getCronTrigger_Expression();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.RemoteTrigger <em>Remote Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Remote Trigger</em>'.
	 * @see com.mddoai.metamodel.bambooMM.RemoteTrigger
	 * @generated
	 */
	EClass getRemoteTrigger();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.RemoteTrigger#getIp <em>Ip</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ip</em>'.
	 * @see com.mddoai.metamodel.bambooMM.RemoteTrigger#getIp()
	 * @see #getRemoteTrigger()
	 * @generated
	 */
	EAttribute getRemoteTrigger_Ip();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.AfterDeploymentTrigger <em>After Deployment Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>After Deployment Trigger</em>'.
	 * @see com.mddoai.metamodel.bambooMM.AfterDeploymentTrigger
	 * @generated
	 */
	EClass getAfterDeploymentTrigger();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.AfterDeploymentTrigger#getDeploymentProject <em>Deployment Project</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Deployment Project</em>'.
	 * @see com.mddoai.metamodel.bambooMM.AfterDeploymentTrigger#getDeploymentProject()
	 * @see #getAfterDeploymentTrigger()
	 * @generated
	 */
	EAttribute getAfterDeploymentTrigger_DeploymentProject();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.AfterDeploymentTrigger#getEnvironment <em>Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Environment</em>'.
	 * @see com.mddoai.metamodel.bambooMM.AfterDeploymentTrigger#getEnvironment()
	 * @see #getAfterDeploymentTrigger()
	 * @generated
	 */
	EAttribute getAfterDeploymentTrigger_Environment();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.BuildSuccessTrigger <em>Build Success Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Build Success Trigger</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BuildSuccessTrigger
	 * @generated
	 */
	EClass getBuildSuccessTrigger();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.BuildSuccessTrigger#getBranch <em>Branch</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Branch</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BuildSuccessTrigger#getBranch()
	 * @see #getBuildSuccessTrigger()
	 * @generated
	 */
	EAttribute getBuildSuccessTrigger_Branch();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.StageSuccessTrigger <em>Stage Success Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stage Success Trigger</em>'.
	 * @see com.mddoai.metamodel.bambooMM.StageSuccessTrigger
	 * @generated
	 */
	EClass getStageSuccessTrigger();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.StageSuccessTrigger#getStage <em>Stage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stage</em>'.
	 * @see com.mddoai.metamodel.bambooMM.StageSuccessTrigger#getStage()
	 * @see #getStageSuccessTrigger()
	 * @generated
	 */
	EAttribute getStageSuccessTrigger_Stage();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.StageSuccessTrigger#getBranch <em>Branch</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Branch</em>'.
	 * @see com.mddoai.metamodel.bambooMM.StageSuccessTrigger#getBranch()
	 * @see #getStageSuccessTrigger()
	 * @generated
	 */
	EAttribute getStageSuccessTrigger_Branch();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.EnvironmentSuccessTrigger <em>Environment Success Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Environment Success Trigger</em>'.
	 * @see com.mddoai.metamodel.bambooMM.EnvironmentSuccessTrigger
	 * @generated
	 */
	EClass getEnvironmentSuccessTrigger();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.EnvironmentSuccessTrigger#getEnvironment <em>Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Environment</em>'.
	 * @see com.mddoai.metamodel.bambooMM.EnvironmentSuccessTrigger#getEnvironment()
	 * @see #getEnvironmentSuccessTrigger()
	 * @generated
	 */
	EAttribute getEnvironmentSuccessTrigger_Environment();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.ScheduledDeploymentTrigger <em>Scheduled Deployment Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Scheduled Deployment Trigger</em>'.
	 * @see com.mddoai.metamodel.bambooMM.ScheduledDeploymentTrigger
	 * @generated
	 */
	EClass getScheduledDeploymentTrigger();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.ScheduledDeploymentTrigger#getCronExpression <em>Cron Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cron Expression</em>'.
	 * @see com.mddoai.metamodel.bambooMM.ScheduledDeploymentTrigger#getCronExpression()
	 * @see #getScheduledDeploymentTrigger()
	 * @generated
	 */
	EAttribute getScheduledDeploymentTrigger_CronExpression();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.ScheduledDeploymentTrigger#getArtifactBranch <em>Artifact Branch</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Artifact Branch</em>'.
	 * @see com.mddoai.metamodel.bambooMM.ScheduledDeploymentTrigger#getArtifactBranch()
	 * @see #getScheduledDeploymentTrigger()
	 * @generated
	 */
	EAttribute getScheduledDeploymentTrigger_ArtifactBranch();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.TriggerCondition <em>Trigger Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Trigger Condition</em>'.
	 * @see com.mddoai.metamodel.bambooMM.TriggerCondition
	 * @generated
	 */
	EClass getTriggerCondition();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.GreenPlanCondition <em>Green Plan Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Green Plan Condition</em>'.
	 * @see com.mddoai.metamodel.bambooMM.GreenPlanCondition
	 * @generated
	 */
	EClass getGreenPlanCondition();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.bambooMM.GreenPlanCondition#getPlanKeys <em>Plan Keys</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Plan Keys</em>'.
	 * @see com.mddoai.metamodel.bambooMM.GreenPlanCondition#getPlanKeys()
	 * @see #getGreenPlanCondition()
	 * @generated
	 */
	EAttribute getGreenPlanCondition_PlanKeys();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.AllOtherCondition <em>All Other Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>All Other Condition</em>'.
	 * @see com.mddoai.metamodel.bambooMM.AllOtherCondition
	 * @generated
	 */
	EClass getAllOtherCondition();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.bambooMM.AllOtherCondition#getProperties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Properties</em>'.
	 * @see com.mddoai.metamodel.bambooMM.AllOtherCondition#getProperties()
	 * @see #getAllOtherCondition()
	 * @generated
	 */
	EReference getAllOtherCondition_Properties();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.Repository <em>Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Repository</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Repository
	 * @generated
	 */
	EClass getRepository();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.Repository#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Repository#getName()
	 * @see #getRepository()
	 * @generated
	 */
	EAttribute getRepository_Name();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.LinkedRepository <em>Linked Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Linked Repository</em>'.
	 * @see com.mddoai.metamodel.bambooMM.LinkedRepository
	 * @generated
	 */
	EClass getLinkedRepository();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.LinkedRepository#getScope <em>Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scope</em>'.
	 * @see com.mddoai.metamodel.bambooMM.LinkedRepository#getScope()
	 * @see #getLinkedRepository()
	 * @generated
	 */
	EAttribute getLinkedRepository_Scope();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.GitRepository <em>Git Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Git Repository</em>'.
	 * @see com.mddoai.metamodel.bambooMM.GitRepository
	 * @generated
	 */
	EClass getGitRepository();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.GitRepository#getUrl <em>Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Url</em>'.
	 * @see com.mddoai.metamodel.bambooMM.GitRepository#getUrl()
	 * @see #getGitRepository()
	 * @generated
	 */
	EAttribute getGitRepository_Url();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.GitRepository#getBranch <em>Branch</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Branch</em>'.
	 * @see com.mddoai.metamodel.bambooMM.GitRepository#getBranch()
	 * @see #getGitRepository()
	 * @generated
	 */
	EAttribute getGitRepository_Branch();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.GitRepository#getUsername <em>Username</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Username</em>'.
	 * @see com.mddoai.metamodel.bambooMM.GitRepository#getUsername()
	 * @see #getGitRepository()
	 * @generated
	 */
	EAttribute getGitRepository_Username();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.GitRepository#getPassword <em>Password</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Password</em>'.
	 * @see com.mddoai.metamodel.bambooMM.GitRepository#getPassword()
	 * @see #getGitRepository()
	 * @generated
	 */
	EAttribute getGitRepository_Password();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.GitRepository#getSshKey <em>Ssh Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ssh Key</em>'.
	 * @see com.mddoai.metamodel.bambooMM.GitRepository#getSshKey()
	 * @see #getGitRepository()
	 * @generated
	 */
	EAttribute getGitRepository_SshKey();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.GitRepository#getSshKeyPassphrase <em>Ssh Key Passphrase</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ssh Key Passphrase</em>'.
	 * @see com.mddoai.metamodel.bambooMM.GitRepository#getSshKeyPassphrase()
	 * @see #getGitRepository()
	 * @generated
	 */
	EAttribute getGitRepository_SshKeyPassphrase();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.GitRepository#getSharedCredentials <em>Shared Credentials</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Shared Credentials</em>'.
	 * @see com.mddoai.metamodel.bambooMM.GitRepository#getSharedCredentials()
	 * @see #getGitRepository()
	 * @generated
	 */
	EAttribute getGitRepository_SharedCredentials();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.GitRepository#getSharedCredentialsScope <em>Shared Credentials Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Shared Credentials Scope</em>'.
	 * @see com.mddoai.metamodel.bambooMM.GitRepository#getSharedCredentialsScope()
	 * @see #getGitRepository()
	 * @generated
	 */
	EAttribute getGitRepository_SharedCredentialsScope();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.GitRepository#getLfs <em>Lfs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lfs</em>'.
	 * @see com.mddoai.metamodel.bambooMM.GitRepository#getLfs()
	 * @see #getGitRepository()
	 * @generated
	 */
	EAttribute getGitRepository_Lfs();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.GitRepository#getUseShallowClones <em>Use Shallow Clones</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Use Shallow Clones</em>'.
	 * @see com.mddoai.metamodel.bambooMM.GitRepository#getUseShallowClones()
	 * @see #getGitRepository()
	 * @generated
	 */
	EAttribute getGitRepository_UseShallowClones();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.GitRepository#getSubmodules <em>Submodules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Submodules</em>'.
	 * @see com.mddoai.metamodel.bambooMM.GitRepository#getSubmodules()
	 * @see #getGitRepository()
	 * @generated
	 */
	EAttribute getGitRepository_Submodules();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.GitRepository#getFetchAll <em>Fetch All</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fetch All</em>'.
	 * @see com.mddoai.metamodel.bambooMM.GitRepository#getFetchAll()
	 * @see #getGitRepository()
	 * @generated
	 */
	EAttribute getGitRepository_FetchAll();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.GitRepository#getCacheOnAgents <em>Cache On Agents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cache On Agents</em>'.
	 * @see com.mddoai.metamodel.bambooMM.GitRepository#getCacheOnAgents()
	 * @see #getGitRepository()
	 * @generated
	 */
	EAttribute getGitRepository_CacheOnAgents();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.GitRepository#getVerboseLogs <em>Verbose Logs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Verbose Logs</em>'.
	 * @see com.mddoai.metamodel.bambooMM.GitRepository#getVerboseLogs()
	 * @see #getGitRepository()
	 * @generated
	 */
	EAttribute getGitRepository_VerboseLogs();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.GitRepository#getCommandTimeoutMinutes <em>Command Timeout Minutes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Command Timeout Minutes</em>'.
	 * @see com.mddoai.metamodel.bambooMM.GitRepository#getCommandTimeoutMinutes()
	 * @see #getGitRepository()
	 * @generated
	 */
	EAttribute getGitRepository_CommandTimeoutMinutes();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.bambooMM.GitRepository#getChangeDetection <em>Change Detection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Change Detection</em>'.
	 * @see com.mddoai.metamodel.bambooMM.GitRepository#getChangeDetection()
	 * @see #getGitRepository()
	 * @generated
	 */
	EReference getGitRepository_ChangeDetection();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.BitbucketCloudRepository <em>Bitbucket Cloud Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Bitbucket Cloud Repository</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BitbucketCloudRepository
	 * @generated
	 */
	EClass getBitbucketCloudRepository();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.BitbucketCloudRepository#getSlug <em>Slug</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Slug</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BitbucketCloudRepository#getSlug()
	 * @see #getBitbucketCloudRepository()
	 * @generated
	 */
	EAttribute getBitbucketCloudRepository_Slug();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository <em>Bitbucket Server Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Bitbucket Server Repository</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BitbucketServerRepository
	 * @generated
	 */
	EClass getBitbucketServerRepository();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getServer <em>Server</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Server</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getServer()
	 * @see #getBitbucketServerRepository()
	 * @generated
	 */
	EAttribute getBitbucketServerRepository_Server();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getProject <em>Project</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Project</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getProject()
	 * @see #getBitbucketServerRepository()
	 * @generated
	 */
	EAttribute getBitbucketServerRepository_Project();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getSlug <em>Slug</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Slug</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getSlug()
	 * @see #getBitbucketServerRepository()
	 * @generated
	 */
	EAttribute getBitbucketServerRepository_Slug();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getCloneUrl <em>Clone Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Clone Url</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getCloneUrl()
	 * @see #getBitbucketServerRepository()
	 * @generated
	 */
	EAttribute getBitbucketServerRepository_CloneUrl();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getPublicKey <em>Public Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Public Key</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getPublicKey()
	 * @see #getBitbucketServerRepository()
	 * @generated
	 */
	EAttribute getBitbucketServerRepository_PublicKey();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getPrivateKey <em>Private Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Private Key</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getPrivateKey()
	 * @see #getBitbucketServerRepository()
	 * @generated
	 */
	EAttribute getBitbucketServerRepository_PrivateKey();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getBranch <em>Branch</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Branch</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getBranch()
	 * @see #getBitbucketServerRepository()
	 * @generated
	 */
	EAttribute getBitbucketServerRepository_Branch();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getLfs <em>Lfs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lfs</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getLfs()
	 * @see #getBitbucketServerRepository()
	 * @generated
	 */
	EAttribute getBitbucketServerRepository_Lfs();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getUseShallowClones <em>Use Shallow Clones</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Use Shallow Clones</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getUseShallowClones()
	 * @see #getBitbucketServerRepository()
	 * @generated
	 */
	EAttribute getBitbucketServerRepository_UseShallowClones();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getSubmodules <em>Submodules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Submodules</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getSubmodules()
	 * @see #getBitbucketServerRepository()
	 * @generated
	 */
	EAttribute getBitbucketServerRepository_Submodules();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getCommandTimeoutMinutes <em>Command Timeout Minutes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Command Timeout Minutes</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getCommandTimeoutMinutes()
	 * @see #getBitbucketServerRepository()
	 * @generated
	 */
	EAttribute getBitbucketServerRepository_CommandTimeoutMinutes();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getFetchAll <em>Fetch All</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fetch All</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getFetchAll()
	 * @see #getBitbucketServerRepository()
	 * @generated
	 */
	EAttribute getBitbucketServerRepository_FetchAll();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.GithubRepository <em>Github Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Github Repository</em>'.
	 * @see com.mddoai.metamodel.bambooMM.GithubRepository
	 * @generated
	 */
	EClass getGithubRepository();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.GithubRepository#getRepository <em>Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Repository</em>'.
	 * @see com.mddoai.metamodel.bambooMM.GithubRepository#getRepository()
	 * @see #getGithubRepository()
	 * @generated
	 */
	EAttribute getGithubRepository_Repository();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.GithubRepository#getUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User</em>'.
	 * @see com.mddoai.metamodel.bambooMM.GithubRepository#getUser()
	 * @see #getGithubRepository()
	 * @generated
	 */
	EAttribute getGithubRepository_User();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.AnyVcsRepository <em>Any Vcs Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Any Vcs Repository</em>'.
	 * @see com.mddoai.metamodel.bambooMM.AnyVcsRepository
	 * @generated
	 */
	EClass getAnyVcsRepository();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.AnyVcsRepository#getPluginKey <em>Plugin Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Plugin Key</em>'.
	 * @see com.mddoai.metamodel.bambooMM.AnyVcsRepository#getPluginKey()
	 * @see #getAnyVcsRepository()
	 * @generated
	 */
	EAttribute getAnyVcsRepository_PluginKey();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.bambooMM.AnyVcsRepository#getServerConfig <em>Server Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Server Config</em>'.
	 * @see com.mddoai.metamodel.bambooMM.AnyVcsRepository#getServerConfig()
	 * @see #getAnyVcsRepository()
	 * @generated
	 */
	EReference getAnyVcsRepository_ServerConfig();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.bambooMM.AnyVcsRepository#getBranchConfig <em>Branch Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Branch Config</em>'.
	 * @see com.mddoai.metamodel.bambooMM.AnyVcsRepository#getBranchConfig()
	 * @see #getAnyVcsRepository()
	 * @generated
	 */
	EReference getAnyVcsRepository_BranchConfig();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.ChangeDetection <em>Change Detection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Change Detection</em>'.
	 * @see com.mddoai.metamodel.bambooMM.ChangeDetection
	 * @generated
	 */
	EClass getChangeDetection();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.bambooMM.ChangeDetection#getQuietPeriod <em>Quiet Period</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Quiet Period</em>'.
	 * @see com.mddoai.metamodel.bambooMM.ChangeDetection#getQuietPeriod()
	 * @see #getChangeDetection()
	 * @generated
	 */
	EReference getChangeDetection_QuietPeriod();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.ChangeDetection#getExcludeChangesetPattern <em>Exclude Changeset Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Exclude Changeset Pattern</em>'.
	 * @see com.mddoai.metamodel.bambooMM.ChangeDetection#getExcludeChangesetPattern()
	 * @see #getChangeDetection()
	 * @generated
	 */
	EAttribute getChangeDetection_ExcludeChangesetPattern();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.ChangeDetection#getFileFilterType <em>File Filter Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File Filter Type</em>'.
	 * @see com.mddoai.metamodel.bambooMM.ChangeDetection#getFileFilterType()
	 * @see #getChangeDetection()
	 * @generated
	 */
	EAttribute getChangeDetection_FileFilterType();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.ChangeDetection#getFileFilterPattern <em>File Filter Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File Filter Pattern</em>'.
	 * @see com.mddoai.metamodel.bambooMM.ChangeDetection#getFileFilterPattern()
	 * @see #getChangeDetection()
	 * @generated
	 */
	EAttribute getChangeDetection_FileFilterPattern();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.QuietPeriod <em>Quiet Period</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Quiet Period</em>'.
	 * @see com.mddoai.metamodel.bambooMM.QuietPeriod
	 * @generated
	 */
	EClass getQuietPeriod();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.QuietPeriod#getQuietPeriodSeconds <em>Quiet Period Seconds</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Quiet Period Seconds</em>'.
	 * @see com.mddoai.metamodel.bambooMM.QuietPeriod#getQuietPeriodSeconds()
	 * @see #getQuietPeriod()
	 * @generated
	 */
	EAttribute getQuietPeriod_QuietPeriodSeconds();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.QuietPeriod#getMaxRetries <em>Max Retries</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Retries</em>'.
	 * @see com.mddoai.metamodel.bambooMM.QuietPeriod#getMaxRetries()
	 * @see #getQuietPeriod()
	 * @generated
	 */
	EAttribute getQuietPeriod_MaxRetries();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.Artifact <em>Artifact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Artifact</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Artifact
	 * @generated
	 */
	EClass getArtifact();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.Artifact#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Artifact#getName()
	 * @see #getArtifact()
	 * @generated
	 */
	EAttribute getArtifact_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.Artifact#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Location</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Artifact#getLocation()
	 * @see #getArtifact()
	 * @generated
	 */
	EAttribute getArtifact_Location();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.Artifact#getPattern <em>Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pattern</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Artifact#getPattern()
	 * @see #getArtifact()
	 * @generated
	 */
	EAttribute getArtifact_Pattern();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.Artifact#isRequired <em>Required</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Required</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Artifact#isRequired()
	 * @see #getArtifact()
	 * @generated
	 */
	EAttribute getArtifact_Required();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.Artifact#isShared <em>Shared</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Shared</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Artifact#isShared()
	 * @see #getArtifact()
	 * @generated
	 */
	EAttribute getArtifact_Shared();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.Artifact#getHttpCompressionOn <em>Http Compression On</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Http Compression On</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Artifact#getHttpCompressionOn()
	 * @see #getArtifact()
	 * @generated
	 */
	EAttribute getArtifact_HttpCompressionOn();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.ArtifactSubscription <em>Artifact Subscription</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Artifact Subscription</em>'.
	 * @see com.mddoai.metamodel.bambooMM.ArtifactSubscription
	 * @generated
	 */
	EClass getArtifactSubscription();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.ArtifactSubscription#getArtifact <em>Artifact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Artifact</em>'.
	 * @see com.mddoai.metamodel.bambooMM.ArtifactSubscription#getArtifact()
	 * @see #getArtifactSubscription()
	 * @generated
	 */
	EAttribute getArtifactSubscription_Artifact();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.ArtifactSubscription#getDestination <em>Destination</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Destination</em>'.
	 * @see com.mddoai.metamodel.bambooMM.ArtifactSubscription#getDestination()
	 * @see #getArtifactSubscription()
	 * @generated
	 */
	EAttribute getArtifactSubscription_Destination();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.Requirement <em>Requirement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Requirement</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Requirement
	 * @generated
	 */
	EClass getRequirement();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.Requirement#getCapability <em>Capability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Capability</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Requirement#getCapability()
	 * @see #getRequirement()
	 * @generated
	 */
	EAttribute getRequirement_Capability();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.Requirement#getMatchValue <em>Match Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Match Value</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Requirement#getMatchValue()
	 * @see #getRequirement()
	 * @generated
	 */
	EAttribute getRequirement_MatchValue();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Variable Assignment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable Assignment</em>'.
	 * @see java.util.Map.Entry
	 * @model keyId="true" keyDataType="org.eclipse.emf.ecore.EString" keyRequired="true"
	 *        valueDataType="org.eclipse.emf.ecore.EString" valueRequired="true"
	 * @generated
	 */
	EClass getVariableAssignment();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getVariableAssignment()
	 * @generated
	 */
	EAttribute getVariableAssignment_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getVariableAssignment()
	 * @generated
	 */
	EAttribute getVariableAssignment_Value();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.Label <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Label</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Label
	 * @generated
	 */
	EClass getLabel();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.Label#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Label#getValue()
	 * @see #getLabel()
	 * @generated
	 */
	EAttribute getLabel_Value();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.Notification <em>Notification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Notification</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Notification
	 * @generated
	 */
	EClass getNotification();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.bambooMM.Notification#getRecipients <em>Recipients</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Recipients</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Notification#getRecipients()
	 * @see #getNotification()
	 * @generated
	 */
	EReference getNotification_Recipients();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.bambooMM.Notification#getEvents <em>Events</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Events</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Notification#getEvents()
	 * @see #getNotification()
	 * @generated
	 */
	EReference getNotification_Events();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.NotificationRecipient <em>Notification Recipient</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Notification Recipient</em>'.
	 * @see com.mddoai.metamodel.bambooMM.NotificationRecipient
	 * @generated
	 */
	EClass getNotificationRecipient();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.UserRecipient <em>User Recipient</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Recipient</em>'.
	 * @see com.mddoai.metamodel.bambooMM.UserRecipient
	 * @generated
	 */
	EClass getUserRecipient();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.bambooMM.UserRecipient#getUsers <em>Users</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Users</em>'.
	 * @see com.mddoai.metamodel.bambooMM.UserRecipient#getUsers()
	 * @see #getUserRecipient()
	 * @generated
	 */
	EAttribute getUserRecipient_Users();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.GroupRecipient <em>Group Recipient</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Group Recipient</em>'.
	 * @see com.mddoai.metamodel.bambooMM.GroupRecipient
	 * @generated
	 */
	EClass getGroupRecipient();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.bambooMM.GroupRecipient#getGroups <em>Groups</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Groups</em>'.
	 * @see com.mddoai.metamodel.bambooMM.GroupRecipient#getGroups()
	 * @see #getGroupRecipient()
	 * @generated
	 */
	EAttribute getGroupRecipient_Groups();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.EmailRecipient <em>Email Recipient</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Email Recipient</em>'.
	 * @see com.mddoai.metamodel.bambooMM.EmailRecipient
	 * @generated
	 */
	EClass getEmailRecipient();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.bambooMM.EmailRecipient#getEmails <em>Emails</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Emails</em>'.
	 * @see com.mddoai.metamodel.bambooMM.EmailRecipient#getEmails()
	 * @see #getEmailRecipient()
	 * @generated
	 */
	EAttribute getEmailRecipient_Emails();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.RoleRecipient <em>Role Recipient</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Role Recipient</em>'.
	 * @see com.mddoai.metamodel.bambooMM.RoleRecipient
	 * @generated
	 */
	EClass getRoleRecipient();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.RoleRecipient#getRole <em>Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Role</em>'.
	 * @see com.mddoai.metamodel.bambooMM.RoleRecipient#getRole()
	 * @see #getRoleRecipient()
	 * @generated
	 */
	EAttribute getRoleRecipient_Role();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.WebhookRecipient <em>Webhook Recipient</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Webhook Recipient</em>'.
	 * @see com.mddoai.metamodel.bambooMM.WebhookRecipient
	 * @generated
	 */
	EClass getWebhookRecipient();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.WebhookRecipient#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.mddoai.metamodel.bambooMM.WebhookRecipient#getName()
	 * @see #getWebhookRecipient()
	 * @generated
	 */
	EAttribute getWebhookRecipient_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.WebhookRecipient#getUrl <em>Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Url</em>'.
	 * @see com.mddoai.metamodel.bambooMM.WebhookRecipient#getUrl()
	 * @see #getWebhookRecipient()
	 * @generated
	 */
	EAttribute getWebhookRecipient_Url();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.NotificationEvent <em>Notification Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Notification Event</em>'.
	 * @see com.mddoai.metamodel.bambooMM.NotificationEvent
	 * @generated
	 */
	EClass getNotificationEvent();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.NotificationEvent#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see com.mddoai.metamodel.bambooMM.NotificationEvent#getType()
	 * @see #getNotificationEvent()
	 * @generated
	 */
	EAttribute getNotificationEvent_Type();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.NotificationEvent#getFailures <em>Failures</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Failures</em>'.
	 * @see com.mddoai.metamodel.bambooMM.NotificationEvent#getFailures()
	 * @see #getNotificationEvent()
	 * @generated
	 */
	EAttribute getNotificationEvent_Failures();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.NotificationEvent#getFirstOnly <em>First Only</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>First Only</em>'.
	 * @see com.mddoai.metamodel.bambooMM.NotificationEvent#getFirstOnly()
	 * @see #getNotificationEvent()
	 * @generated
	 */
	EAttribute getNotificationEvent_FirstOnly();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.BranchManagement <em>Branch Management</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Branch Management</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BranchManagement
	 * @generated
	 */
	EClass getBranchManagement();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.BranchManagement#getCreate <em>Create</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Create</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BranchManagement#getCreate()
	 * @see #getBranchManagement()
	 * @generated
	 */
	EAttribute getBranchManagement_Create();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.BranchManagement#getCreatePattern <em>Create Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Create Pattern</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BranchManagement#getCreatePattern()
	 * @see #getBranchManagement()
	 * @generated
	 */
	EAttribute getBranchManagement_CreatePattern();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.BranchManagement#getAcceptFork <em>Accept Fork</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Accept Fork</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BranchManagement#getAcceptFork()
	 * @see #getBranchManagement()
	 * @generated
	 */
	EAttribute getBranchManagement_AcceptFork();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.bambooMM.BranchManagement#getDelete <em>Delete</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Delete</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BranchManagement#getDelete()
	 * @see #getBranchManagement()
	 * @generated
	 */
	EReference getBranchManagement_Delete();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.bambooMM.BranchManagement#getIntegration <em>Integration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Integration</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BranchManagement#getIntegration()
	 * @see #getBranchManagement()
	 * @generated
	 */
	EReference getBranchManagement_Integration();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.BranchManagement#getLinkToJira <em>Link To Jira</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Link To Jira</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BranchManagement#getLinkToJira()
	 * @see #getBranchManagement()
	 * @generated
	 */
	EAttribute getBranchManagement_LinkToJira();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.BranchDelete <em>Branch Delete</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Branch Delete</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BranchDelete
	 * @generated
	 */
	EClass getBranchDelete();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.BranchDelete#getAfterDeletedDays <em>After Deleted Days</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>After Deleted Days</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BranchDelete#getAfterDeletedDays()
	 * @see #getBranchDelete()
	 * @generated
	 */
	EAttribute getBranchDelete_AfterDeletedDays();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.BranchDelete#getAfterInactiveDays <em>After Inactive Days</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>After Inactive Days</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BranchDelete#getAfterInactiveDays()
	 * @see #getBranchDelete()
	 * @generated
	 */
	EAttribute getBranchDelete_AfterInactiveDays();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.BranchIntegration <em>Branch Integration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Branch Integration</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BranchIntegration
	 * @generated
	 */
	EClass getBranchIntegration();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.BranchIntegration#getMergeFrom <em>Merge From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Merge From</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BranchIntegration#getMergeFrom()
	 * @see #getBranchIntegration()
	 * @generated
	 */
	EAttribute getBranchIntegration_MergeFrom();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.BranchIntegration#getMergeTo <em>Merge To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Merge To</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BranchIntegration#getMergeTo()
	 * @see #getBranchIntegration()
	 * @generated
	 */
	EAttribute getBranchIntegration_MergeTo();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.BranchIntegration#isPushOnSuccess <em>Push On Success</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Push On Success</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BranchIntegration#isPushOnSuccess()
	 * @see #getBranchIntegration()
	 * @generated
	 */
	EAttribute getBranchIntegration_PushOnSuccess();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.BranchOverride <em>Branch Override</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Branch Override</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BranchOverride
	 * @generated
	 */
	EClass getBranchOverride();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.BranchOverride#getBranchPattern <em>Branch Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Branch Pattern</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BranchOverride#getBranchPattern()
	 * @see #getBranchOverride()
	 * @generated
	 */
	EAttribute getBranchOverride_BranchPattern();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.bambooMM.BranchOverride#getStages <em>Stages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Stages</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BranchOverride#getStages()
	 * @see #getBranchOverride()
	 * @generated
	 */
	EReference getBranchOverride_Stages();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.bambooMM.BranchOverride#getJobs <em>Jobs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Jobs</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BranchOverride#getJobs()
	 * @see #getBranchOverride()
	 * @generated
	 */
	EReference getBranchOverride_Jobs();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.bambooMM.BranchOverride#getTriggers <em>Triggers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Triggers</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BranchOverride#getTriggers()
	 * @see #getBranchOverride()
	 * @generated
	 */
	EReference getBranchOverride_Triggers();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.bambooMM.BranchOverride#getNotifications <em>Notifications</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Notifications</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BranchOverride#getNotifications()
	 * @see #getBranchOverride()
	 * @generated
	 */
	EReference getBranchOverride_Notifications();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.bambooMM.BranchOverride#getVariables <em>Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Variables</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BranchOverride#getVariables()
	 * @see #getBranchOverride()
	 * @generated
	 */
	EReference getBranchOverride_Variables();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.bambooMM.BranchOverride#getLabels <em>Labels</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Labels</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BranchOverride#getLabels()
	 * @see #getBranchOverride()
	 * @generated
	 */
	EReference getBranchOverride_Labels();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.bambooMM.BranchOverride#getDocker <em>Docker</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Docker</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BranchOverride#getDocker()
	 * @see #getBranchOverride()
	 * @generated
	 */
	EReference getBranchOverride_Docker();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.bambooMM.BranchOverride#getBranchConfig <em>Branch Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Branch Config</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BranchOverride#getBranchConfig()
	 * @see #getBranchOverride()
	 * @generated
	 */
	EReference getBranchOverride_BranchConfig();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.BranchSpecificConfig <em>Branch Specific Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Branch Specific Config</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BranchSpecificConfig
	 * @generated
	 */
	EClass getBranchSpecificConfig();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.bambooMM.BranchSpecificConfig#getIntegration <em>Integration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Integration</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BranchSpecificConfig#getIntegration()
	 * @see #getBranchSpecificConfig()
	 * @generated
	 */
	EReference getBranchSpecificConfig_Integration();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.BranchSpecificConfig#isDisableExpiry <em>Disable Expiry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Disable Expiry</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BranchSpecificConfig#isDisableExpiry()
	 * @see #getBranchSpecificConfig()
	 * @generated
	 */
	EAttribute getBranchSpecificConfig_DisableExpiry();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.Dependencies <em>Dependencies</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dependencies</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Dependencies
	 * @generated
	 */
	EClass getDependencies();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.Dependencies#isRequireAllStagesPassing <em>Require All Stages Passing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Require All Stages Passing</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Dependencies#isRequireAllStagesPassing()
	 * @see #getDependencies()
	 * @generated
	 */
	EAttribute getDependencies_RequireAllStagesPassing();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.Dependencies#isEnabledForBranches <em>Enabled For Branches</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enabled For Branches</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Dependencies#isEnabledForBranches()
	 * @see #getDependencies()
	 * @generated
	 */
	EAttribute getDependencies_EnabledForBranches();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.Dependencies#getBlockStrategy <em>Block Strategy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Block Strategy</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Dependencies#getBlockStrategy()
	 * @see #getDependencies()
	 * @generated
	 */
	EAttribute getDependencies_BlockStrategy();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.bambooMM.Dependencies#getPlans <em>Plans</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Plans</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Dependencies#getPlans()
	 * @see #getDependencies()
	 * @generated
	 */
	EAttribute getDependencies_Plans();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.DockerConfig <em>Docker Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Docker Config</em>'.
	 * @see com.mddoai.metamodel.bambooMM.DockerConfig
	 * @generated
	 */
	EClass getDockerConfig();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.DockerConfig#getImage <em>Image</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Image</em>'.
	 * @see com.mddoai.metamodel.bambooMM.DockerConfig#getImage()
	 * @see #getDockerConfig()
	 * @generated
	 */
	EAttribute getDockerConfig_Image();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.bambooMM.DockerConfig#getVolumes <em>Volumes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Volumes</em>'.
	 * @see com.mddoai.metamodel.bambooMM.DockerConfig#getVolumes()
	 * @see #getDockerConfig()
	 * @generated
	 */
	EReference getDockerConfig_Volumes();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.DockerConfig#isUseDefaultVolumes <em>Use Default Volumes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Use Default Volumes</em>'.
	 * @see com.mddoai.metamodel.bambooMM.DockerConfig#isUseDefaultVolumes()
	 * @see #getDockerConfig()
	 * @generated
	 */
	EAttribute getDockerConfig_UseDefaultVolumes();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.bambooMM.DockerConfig#getDockerRunArguments <em>Docker Run Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Docker Run Arguments</em>'.
	 * @see com.mddoai.metamodel.bambooMM.DockerConfig#getDockerRunArguments()
	 * @see #getDockerConfig()
	 * @generated
	 */
	EAttribute getDockerConfig_DockerRunArguments();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.OtherConfig <em>Other Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Other Config</em>'.
	 * @see com.mddoai.metamodel.bambooMM.OtherConfig
	 * @generated
	 */
	EClass getOtherConfig();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.OtherConfig#getConcurrentBuildPlugin <em>Concurrent Build Plugin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Concurrent Build Plugin</em>'.
	 * @see com.mddoai.metamodel.bambooMM.OtherConfig#getConcurrentBuildPlugin()
	 * @see #getOtherConfig()
	 * @generated
	 */
	EAttribute getOtherConfig_ConcurrentBuildPlugin();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.OtherConfig#getCleanWorkingDir <em>Clean Working Dir</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Clean Working Dir</em>'.
	 * @see com.mddoai.metamodel.bambooMM.OtherConfig#getCleanWorkingDir()
	 * @see #getOtherConfig()
	 * @generated
	 */
	EAttribute getOtherConfig_CleanWorkingDir();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.bambooMM.OtherConfig#getAllOtherApps <em>All Other Apps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>All Other Apps</em>'.
	 * @see com.mddoai.metamodel.bambooMM.OtherConfig#getAllOtherApps()
	 * @see #getOtherConfig()
	 * @generated
	 */
	EReference getOtherConfig_AllOtherApps();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.DeploymentProject <em>Deployment Project</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Deployment Project</em>'.
	 * @see com.mddoai.metamodel.bambooMM.DeploymentProject
	 * @generated
	 */
	EClass getDeploymentProject();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.DeploymentProject#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.mddoai.metamodel.bambooMM.DeploymentProject#getName()
	 * @see #getDeploymentProject()
	 * @generated
	 */
	EAttribute getDeploymentProject_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.DeploymentProject#getSourcePlan <em>Source Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source Plan</em>'.
	 * @see com.mddoai.metamodel.bambooMM.DeploymentProject#getSourcePlan()
	 * @see #getDeploymentProject()
	 * @generated
	 */
	EAttribute getDeploymentProject_SourcePlan();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.DeploymentProject#getServerName <em>Server Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Server Name</em>'.
	 * @see com.mddoai.metamodel.bambooMM.DeploymentProject#getServerName()
	 * @see #getDeploymentProject()
	 * @generated
	 */
	EAttribute getDeploymentProject_ServerName();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.bambooMM.DeploymentProject#getReleaseNaming <em>Release Naming</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Release Naming</em>'.
	 * @see com.mddoai.metamodel.bambooMM.DeploymentProject#getReleaseNaming()
	 * @see #getDeploymentProject()
	 * @generated
	 */
	EReference getDeploymentProject_ReleaseNaming();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.bambooMM.DeploymentProject#getEnvironments <em>Environments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Environments</em>'.
	 * @see com.mddoai.metamodel.bambooMM.DeploymentProject#getEnvironments()
	 * @see #getDeploymentProject()
	 * @generated
	 */
	EReference getDeploymentProject_Environments();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.bambooMM.DeploymentProject#getPermissions <em>Permissions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Permissions</em>'.
	 * @see com.mddoai.metamodel.bambooMM.DeploymentProject#getPermissions()
	 * @see #getDeploymentProject()
	 * @generated
	 */
	EReference getDeploymentProject_Permissions();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.bambooMM.DeploymentProject#getDefaultEnvironmentPermissions <em>Default Environment Permissions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Default Environment Permissions</em>'.
	 * @see com.mddoai.metamodel.bambooMM.DeploymentProject#getDefaultEnvironmentPermissions()
	 * @see #getDeploymentProject()
	 * @generated
	 */
	EReference getDeploymentProject_DefaultEnvironmentPermissions();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.bambooMM.DeploymentProject#getEnvironmentPermissions <em>Environment Permissions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Environment Permissions</em>'.
	 * @see com.mddoai.metamodel.bambooMM.DeploymentProject#getEnvironmentPermissions()
	 * @see #getDeploymentProject()
	 * @generated
	 */
	EReference getDeploymentProject_EnvironmentPermissions();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.ReleaseNaming <em>Release Naming</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Release Naming</em>'.
	 * @see com.mddoai.metamodel.bambooMM.ReleaseNaming
	 * @generated
	 */
	EClass getReleaseNaming();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.ReleaseNaming#getNextVersionName <em>Next Version Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Next Version Name</em>'.
	 * @see com.mddoai.metamodel.bambooMM.ReleaseNaming#getNextVersionName()
	 * @see #getReleaseNaming()
	 * @generated
	 */
	EAttribute getReleaseNaming_NextVersionName();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.ReleaseNaming#getAppliesToBranches <em>Applies To Branches</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Applies To Branches</em>'.
	 * @see com.mddoai.metamodel.bambooMM.ReleaseNaming#getAppliesToBranches()
	 * @see #getReleaseNaming()
	 * @generated
	 */
	EAttribute getReleaseNaming_AppliesToBranches();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.ReleaseNaming#getAutoIncrement <em>Auto Increment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Auto Increment</em>'.
	 * @see com.mddoai.metamodel.bambooMM.ReleaseNaming#getAutoIncrement()
	 * @see #getReleaseNaming()
	 * @generated
	 */
	EAttribute getReleaseNaming_AutoIncrement();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.bambooMM.ReleaseNaming#getAutoIncrementVariables <em>Auto Increment Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Auto Increment Variables</em>'.
	 * @see com.mddoai.metamodel.bambooMM.ReleaseNaming#getAutoIncrementVariables()
	 * @see #getReleaseNaming()
	 * @generated
	 */
	EAttribute getReleaseNaming_AutoIncrementVariables();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.Environment <em>Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Environment</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Environment
	 * @generated
	 */
	EClass getEnvironment();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.Environment#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Environment#getName()
	 * @see #getEnvironment()
	 * @generated
	 */
	EAttribute getEnvironment_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.bambooMM.Environment#getTasks <em>Tasks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tasks</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Environment#getTasks()
	 * @see #getEnvironment()
	 * @generated
	 */
	EReference getEnvironment_Tasks();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.bambooMM.Environment#getFinalTasks <em>Final Tasks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Final Tasks</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Environment#getFinalTasks()
	 * @see #getEnvironment()
	 * @generated
	 */
	EReference getEnvironment_FinalTasks();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.bambooMM.Environment#getVariables <em>Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Variables</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Environment#getVariables()
	 * @see #getEnvironment()
	 * @generated
	 */
	EReference getEnvironment_Variables();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.bambooMM.Environment#getTriggers <em>Triggers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Triggers</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Environment#getTriggers()
	 * @see #getEnvironment()
	 * @generated
	 */
	EReference getEnvironment_Triggers();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.bambooMM.Environment#getNotifications <em>Notifications</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Notifications</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Environment#getNotifications()
	 * @see #getEnvironment()
	 * @generated
	 */
	EReference getEnvironment_Notifications();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.bambooMM.Environment#getDocker <em>Docker</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Docker</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Environment#getDocker()
	 * @see #getEnvironment()
	 * @generated
	 */
	EReference getEnvironment_Docker();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.bambooMM.Environment#getRequirements <em>Requirements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Requirements</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Environment#getRequirements()
	 * @see #getEnvironment()
	 * @generated
	 */
	EReference getEnvironment_Requirements();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.Environment#getReleaseApprovalPrerequisite <em>Release Approval Prerequisite</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Release Approval Prerequisite</em>'.
	 * @see com.mddoai.metamodel.bambooMM.Environment#getReleaseApprovalPrerequisite()
	 * @see #getEnvironment()
	 * @generated
	 */
	EAttribute getEnvironment_ReleaseApprovalPrerequisite();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.PermissionEntry <em>Permission Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Permission Entry</em>'.
	 * @see com.mddoai.metamodel.bambooMM.PermissionEntry
	 * @generated
	 */
	EClass getPermissionEntry();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.bambooMM.PermissionEntry#getUsers <em>Users</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Users</em>'.
	 * @see com.mddoai.metamodel.bambooMM.PermissionEntry#getUsers()
	 * @see #getPermissionEntry()
	 * @generated
	 */
	EAttribute getPermissionEntry_Users();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.bambooMM.PermissionEntry#getGroups <em>Groups</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Groups</em>'.
	 * @see com.mddoai.metamodel.bambooMM.PermissionEntry#getGroups()
	 * @see #getPermissionEntry()
	 * @generated
	 */
	EAttribute getPermissionEntry_Groups();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.bambooMM.PermissionEntry#getRoles <em>Roles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Roles</em>'.
	 * @see com.mddoai.metamodel.bambooMM.PermissionEntry#getRoles()
	 * @see #getPermissionEntry()
	 * @generated
	 */
	EAttribute getPermissionEntry_Roles();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.bambooMM.PermissionEntry#getPermissions <em>Permissions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Permissions</em>'.
	 * @see com.mddoai.metamodel.bambooMM.PermissionEntry#getPermissions()
	 * @see #getPermissionEntry()
	 * @generated
	 */
	EAttribute getPermissionEntry_Permissions();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.PlanPermissionEntry <em>Plan Permission Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Plan Permission Entry</em>'.
	 * @see com.mddoai.metamodel.bambooMM.PlanPermissionEntry
	 * @generated
	 */
	EClass getPlanPermissionEntry();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.DeploymentPermissionEntry <em>Deployment Permission Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Deployment Permission Entry</em>'.
	 * @see com.mddoai.metamodel.bambooMM.DeploymentPermissionEntry
	 * @generated
	 */
	EClass getDeploymentPermissionEntry();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.EnvironmentPermissionEntry <em>Environment Permission Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Environment Permission Entry</em>'.
	 * @see com.mddoai.metamodel.bambooMM.EnvironmentPermissionEntry
	 * @generated
	 */
	EClass getEnvironmentPermissionEntry();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.bambooMM.NamedEnvironmentPermission <em>Named Environment Permission</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named Environment Permission</em>'.
	 * @see com.mddoai.metamodel.bambooMM.NamedEnvironmentPermission
	 * @generated
	 */
	EClass getNamedEnvironmentPermission();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.bambooMM.NamedEnvironmentPermission#getEnvironmentName <em>Environment Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Environment Name</em>'.
	 * @see com.mddoai.metamodel.bambooMM.NamedEnvironmentPermission#getEnvironmentName()
	 * @see #getNamedEnvironmentPermission()
	 * @generated
	 */
	EAttribute getNamedEnvironmentPermission_EnvironmentName();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.bambooMM.NamedEnvironmentPermission#getEntries <em>Entries</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Entries</em>'.
	 * @see com.mddoai.metamodel.bambooMM.NamedEnvironmentPermission#getEntries()
	 * @see #getNamedEnvironmentPermission()
	 * @generated
	 */
	EReference getNamedEnvironmentPermission_Entries();

	/**
	 * Returns the meta object for enum '{@link com.mddoai.metamodel.bambooMM.VARIABLE_SCOPE <em>VARIABLE SCOPE</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>VARIABLE SCOPE</em>'.
	 * @see com.mddoai.metamodel.bambooMM.VARIABLE_SCOPE
	 * @generated
	 */
	EEnum getVARIABLE_SCOPE();

	/**
	 * Returns the meta object for enum '{@link com.mddoai.metamodel.bambooMM.TEST_PARSER_TYPE <em>TEST PARSER TYPE</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>TEST PARSER TYPE</em>'.
	 * @see com.mddoai.metamodel.bambooMM.TEST_PARSER_TYPE
	 * @generated
	 */
	EEnum getTEST_PARSER_TYPE();

	/**
	 * Returns the meta object for enum '{@link com.mddoai.metamodel.bambooMM.REPOSITORY_SCOPE <em>REPOSITORY SCOPE</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>REPOSITORY SCOPE</em>'.
	 * @see com.mddoai.metamodel.bambooMM.REPOSITORY_SCOPE
	 * @generated
	 */
	EEnum getREPOSITORY_SCOPE();

	/**
	 * Returns the meta object for enum '{@link com.mddoai.metamodel.bambooMM.FILE_FILTER_TYPE <em>FILE FILTER TYPE</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>FILE FILTER TYPE</em>'.
	 * @see com.mddoai.metamodel.bambooMM.FILE_FILTER_TYPE
	 * @generated
	 */
	EEnum getFILE_FILTER_TYPE();

	/**
	 * Returns the meta object for enum '{@link com.mddoai.metamodel.bambooMM.BRANCH_CREATE_STRATEGY <em>BRANCH CREATE STRATEGY</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>BRANCH CREATE STRATEGY</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BRANCH_CREATE_STRATEGY
	 * @generated
	 */
	EEnum getBRANCH_CREATE_STRATEGY();

	/**
	 * Returns the meta object for enum '{@link com.mddoai.metamodel.bambooMM.BLOCK_STRATEGY <em>BLOCK STRATEGY</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>BLOCK STRATEGY</em>'.
	 * @see com.mddoai.metamodel.bambooMM.BLOCK_STRATEGY
	 * @generated
	 */
	EEnum getBLOCK_STRATEGY();

	/**
	 * Returns the meta object for enum '{@link com.mddoai.metamodel.bambooMM.NOTIFICATION_EVENT_TYPE <em>NOTIFICATION EVENT TYPE</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>NOTIFICATION EVENT TYPE</em>'.
	 * @see com.mddoai.metamodel.bambooMM.NOTIFICATION_EVENT_TYPE
	 * @generated
	 */
	EEnum getNOTIFICATION_EVENT_TYPE();

	/**
	 * Returns the meta object for enum '{@link com.mddoai.metamodel.bambooMM.NOTIFICATION_ROLE <em>NOTIFICATION ROLE</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>NOTIFICATION ROLE</em>'.
	 * @see com.mddoai.metamodel.bambooMM.NOTIFICATION_ROLE
	 * @generated
	 */
	EEnum getNOTIFICATION_ROLE();

	/**
	 * Returns the meta object for enum '{@link com.mddoai.metamodel.bambooMM.PERMISSION_ROLE <em>PERMISSION ROLE</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>PERMISSION ROLE</em>'.
	 * @see com.mddoai.metamodel.bambooMM.PERMISSION_ROLE
	 * @generated
	 */
	EEnum getPERMISSION_ROLE();

	/**
	 * Returns the meta object for enum '{@link com.mddoai.metamodel.bambooMM.PERMISSION_TYPE <em>PERMISSION TYPE</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>PERMISSION TYPE</em>'.
	 * @see com.mddoai.metamodel.bambooMM.PERMISSION_TYPE
	 * @generated
	 */
	EEnum getPERMISSION_TYPE();

	/**
	 * Returns the meta object for enum '{@link com.mddoai.metamodel.bambooMM.RELEASE_APPROVAL <em>RELEASE APPROVAL</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>RELEASE APPROVAL</em>'.
	 * @see com.mddoai.metamodel.bambooMM.RELEASE_APPROVAL
	 * @generated
	 */
	EEnum getRELEASE_APPROVAL();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	BambooFactory getBambooFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.PlanImpl <em>Plan</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.PlanImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getPlan()
		 * @generated
		 */
		EClass PLAN = eINSTANCE.getPlan();

		/**
		 * The meta object literal for the '<em><b>Project Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLAN__PROJECT_KEY = eINSTANCE.getPlan_ProjectKey();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLAN__KEY = eINSTANCE.getPlan_Key();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLAN__NAME = eINSTANCE.getPlan_Name();

		/**
		 * The meta object literal for the '<em><b>Server Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLAN__SERVER_NAME = eINSTANCE.getPlan_ServerName();

		/**
		 * The meta object literal for the '<em><b>Stages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLAN__STAGES = eINSTANCE.getPlan_Stages();

		/**
		 * The meta object literal for the '<em><b>Jobs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLAN__JOBS = eINSTANCE.getPlan_Jobs();

		/**
		 * The meta object literal for the '<em><b>Triggers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLAN__TRIGGERS = eINSTANCE.getPlan_Triggers();

		/**
		 * The meta object literal for the '<em><b>Repositories</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLAN__REPOSITORIES = eINSTANCE.getPlan_Repositories();

		/**
		 * The meta object literal for the '<em><b>Variables</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLAN__VARIABLES = eINSTANCE.getPlan_Variables();

		/**
		 * The meta object literal for the '<em><b>Notifications</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLAN__NOTIFICATIONS = eINSTANCE.getPlan_Notifications();

		/**
		 * The meta object literal for the '<em><b>Labels</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLAN__LABELS = eINSTANCE.getPlan_Labels();

		/**
		 * The meta object literal for the '<em><b>Branches</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLAN__BRANCHES = eINSTANCE.getPlan_Branches();

		/**
		 * The meta object literal for the '<em><b>Dependencies</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLAN__DEPENDENCIES = eINSTANCE.getPlan_Dependencies();

		/**
		 * The meta object literal for the '<em><b>Docker</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLAN__DOCKER = eINSTANCE.getPlan_Docker();

		/**
		 * The meta object literal for the '<em><b>Branch Overrides</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLAN__BRANCH_OVERRIDES = eINSTANCE.getPlan_BranchOverrides();

		/**
		 * The meta object literal for the '<em><b>Other</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLAN__OTHER = eINSTANCE.getPlan_Other();

		/**
		 * The meta object literal for the '<em><b>Permissions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLAN__PERMISSIONS = eINSTANCE.getPlan_Permissions();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.StageImpl <em>Stage</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.StageImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getStage()
		 * @generated
		 */
		EClass STAGE = eINSTANCE.getStage();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STAGE__NAME = eINSTANCE.getStage_Name();

		/**
		 * The meta object literal for the '<em><b>Manual</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STAGE__MANUAL = eINSTANCE.getStage_Manual();

		/**
		 * The meta object literal for the '<em><b>Final Stage</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STAGE__FINAL_STAGE = eINSTANCE.getStage_FinalStage();

		/**
		 * The meta object literal for the '<em><b>Job Refs</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STAGE__JOB_REFS = eINSTANCE.getStage_JobRefs();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.JobImpl <em>Job</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.JobImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getJob()
		 * @generated
		 */
		EClass JOB = eINSTANCE.getJob();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOB__NAME = eINSTANCE.getJob_Name();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOB__KEY = eINSTANCE.getJob_Key();

		/**
		 * The meta object literal for the '<em><b>Tasks</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__TASKS = eINSTANCE.getJob_Tasks();

		/**
		 * The meta object literal for the '<em><b>Final Tasks</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__FINAL_TASKS = eINSTANCE.getJob_FinalTasks();

		/**
		 * The meta object literal for the '<em><b>Artifacts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__ARTIFACTS = eINSTANCE.getJob_Artifacts();

		/**
		 * The meta object literal for the '<em><b>Artifact Subscriptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__ARTIFACT_SUBSCRIPTIONS = eINSTANCE.getJob_ArtifactSubscriptions();

		/**
		 * The meta object literal for the '<em><b>Requirements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__REQUIREMENTS = eINSTANCE.getJob_Requirements();

		/**
		 * The meta object literal for the '<em><b>Variables</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__VARIABLES = eINSTANCE.getJob_Variables();

		/**
		 * The meta object literal for the '<em><b>Docker</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__DOCKER = eINSTANCE.getJob_Docker();

		/**
		 * The meta object literal for the '<em><b>Other</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__OTHER = eINSTANCE.getJob_Other();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.TaskImpl <em>Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.TaskImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getTask()
		 * @generated
		 */
		EClass TASK = eINSTANCE.getTask();

		/**
		 * The meta object literal for the '<em><b>Conditions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK__CONDITIONS = eINSTANCE.getTask_Conditions();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.ScriptTaskImpl <em>Script Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.ScriptTaskImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getScriptTask()
		 * @generated
		 */
		EClass SCRIPT_TASK = eINSTANCE.getScriptTask();

		/**
		 * The meta object literal for the '<em><b>Interpreter</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCRIPT_TASK__INTERPRETER = eINSTANCE.getScriptTask_Interpreter();

		/**
		 * The meta object literal for the '<em><b>Scripts</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCRIPT_TASK__SCRIPTS = eINSTANCE.getScriptTask_Scripts();

		/**
		 * The meta object literal for the '<em><b>File</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCRIPT_TASK__FILE = eINSTANCE.getScriptTask_File();

		/**
		 * The meta object literal for the '<em><b>Argument</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCRIPT_TASK__ARGUMENT = eINSTANCE.getScriptTask_Argument();

		/**
		 * The meta object literal for the '<em><b>Environment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCRIPT_TASK__ENVIRONMENT = eINSTANCE.getScriptTask_Environment();

		/**
		 * The meta object literal for the '<em><b>Working Dir</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCRIPT_TASK__WORKING_DIR = eINSTANCE.getScriptTask_WorkingDir();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.CleanTaskImpl <em>Clean Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.CleanTaskImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getCleanTask()
		 * @generated
		 */
		EClass CLEAN_TASK = eINSTANCE.getCleanTask();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.CheckoutTaskImpl <em>Checkout Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.CheckoutTaskImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getCheckoutTask()
		 * @generated
		 */
		EClass CHECKOUT_TASK = eINSTANCE.getCheckoutTask();

		/**
		 * The meta object literal for the '<em><b>Repository</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHECKOUT_TASK__REPOSITORY = eINSTANCE.getCheckoutTask_Repository();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHECKOUT_TASK__PATH = eINSTANCE.getCheckoutTask_Path();

		/**
		 * The meta object literal for the '<em><b>Force Clean Build</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHECKOUT_TASK__FORCE_CLEAN_BUILD = eINSTANCE.getCheckoutTask_ForceCleanBuild();

		/**
		 * The meta object literal for the '<em><b>Default Repository</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHECKOUT_TASK__DEFAULT_REPOSITORY = eINSTANCE.getCheckoutTask_DefaultRepository();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.MavenTaskImpl <em>Maven Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.MavenTaskImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getMavenTask()
		 * @generated
		 */
		EClass MAVEN_TASK = eINSTANCE.getMavenTask();

		/**
		 * The meta object literal for the '<em><b>Executable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAVEN_TASK__EXECUTABLE = eINSTANCE.getMavenTask_Executable();

		/**
		 * The meta object literal for the '<em><b>Jdk</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAVEN_TASK__JDK = eINSTANCE.getMavenTask_Jdk();

		/**
		 * The meta object literal for the '<em><b>Goal</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAVEN_TASK__GOAL = eINSTANCE.getMavenTask_Goal();

		/**
		 * The meta object literal for the '<em><b>Tests</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAVEN_TASK__TESTS = eINSTANCE.getMavenTask_Tests();

		/**
		 * The meta object literal for the '<em><b>Environment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAVEN_TASK__ENVIRONMENT = eINSTANCE.getMavenTask_Environment();

		/**
		 * The meta object literal for the '<em><b>Working Dir</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAVEN_TASK__WORKING_DIR = eINSTANCE.getMavenTask_WorkingDir();

		/**
		 * The meta object literal for the '<em><b>Project File</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAVEN_TASK__PROJECT_FILE = eINSTANCE.getMavenTask_ProjectFile();

		/**
		 * The meta object literal for the '<em><b>Use Return Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAVEN_TASK__USE_RETURN_CODE = eINSTANCE.getMavenTask_UseReturnCode();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.InjectVariablesTaskImpl <em>Inject Variables Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.InjectVariablesTaskImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getInjectVariablesTask()
		 * @generated
		 */
		EClass INJECT_VARIABLES_TASK = eINSTANCE.getInjectVariablesTask();

		/**
		 * The meta object literal for the '<em><b>File</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INJECT_VARIABLES_TASK__FILE = eINSTANCE.getInjectVariablesTask_File();

		/**
		 * The meta object literal for the '<em><b>Scope</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INJECT_VARIABLES_TASK__SCOPE = eINSTANCE.getInjectVariablesTask_Scope();

		/**
		 * The meta object literal for the '<em><b>Namespace</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INJECT_VARIABLES_TASK__NAMESPACE = eINSTANCE.getInjectVariablesTask_Namespace();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.TestParserTaskImpl <em>Test Parser Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.TestParserTaskImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getTestParserTask()
		 * @generated
		 */
		EClass TEST_PARSER_TASK = eINSTANCE.getTestParserTask();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_PARSER_TASK__TYPE = eINSTANCE.getTestParserTask_Type();

		/**
		 * The meta object literal for the '<em><b>Test Results</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_PARSER_TASK__TEST_RESULTS = eINSTANCE.getTestParserTask_TestResults();

		/**
		 * The meta object literal for the '<em><b>Ignore Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_PARSER_TASK__IGNORE_TIME = eINSTANCE.getTestParserTask_IgnoreTime();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.ArtifactDownloadTaskImpl <em>Artifact Download Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.ArtifactDownloadTaskImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getArtifactDownloadTask()
		 * @generated
		 */
		EClass ARTIFACT_DOWNLOAD_TASK = eINSTANCE.getArtifactDownloadTask();

		/**
		 * The meta object literal for the '<em><b>Source Plan</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT_DOWNLOAD_TASK__SOURCE_PLAN = eINSTANCE.getArtifactDownloadTask_SourcePlan();

		/**
		 * The meta object literal for the '<em><b>Destination</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT_DOWNLOAD_TASK__DESTINATION = eINSTANCE.getArtifactDownloadTask_Destination();

		/**
		 * The meta object literal for the '<em><b>Artifacts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARTIFACT_DOWNLOAD_TASK__ARTIFACTS = eINSTANCE.getArtifactDownloadTask_Artifacts();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.ArtifactDownloadItemImpl <em>Artifact Download Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.ArtifactDownloadItemImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getArtifactDownloadItem()
		 * @generated
		 */
		EClass ARTIFACT_DOWNLOAD_ITEM = eINSTANCE.getArtifactDownloadItem();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT_DOWNLOAD_ITEM__NAME = eINSTANCE.getArtifactDownloadItem_Name();

		/**
		 * The meta object literal for the '<em><b>Destination</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT_DOWNLOAD_ITEM__DESTINATION = eINSTANCE.getArtifactDownloadItem_Destination();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.AnyTaskImpl <em>Any Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.AnyTaskImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getAnyTask()
		 * @generated
		 */
		EClass ANY_TASK = eINSTANCE.getAnyTask();

		/**
		 * The meta object literal for the '<em><b>Plugin Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ANY_TASK__PLUGIN_KEY = eINSTANCE.getAnyTask_PluginKey();

		/**
		 * The meta object literal for the '<em><b>Configuration</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANY_TASK__CONFIGURATION = eINSTANCE.getAnyTask_Configuration();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.TaskConditionImpl <em>Task Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.TaskConditionImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getTaskCondition()
		 * @generated
		 */
		EClass TASK_CONDITION = eINSTANCE.getTaskCondition();

		/**
		 * The meta object literal for the '<em><b>Variable Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_CONDITION__VARIABLE_NAME = eINSTANCE.getTaskCondition_VariableName();

		/**
		 * The meta object literal for the '<em><b>Variable Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_CONDITION__VARIABLE_VALUE = eINSTANCE.getTaskCondition_VariableValue();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.TriggerImpl <em>Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.TriggerImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getTrigger()
		 * @generated
		 */
		EClass TRIGGER = eINSTANCE.getTrigger();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.PollingTriggerImpl <em>Polling Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.PollingTriggerImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getPollingTrigger()
		 * @generated
		 */
		EClass POLLING_TRIGGER = eINSTANCE.getPollingTrigger();

		/**
		 * The meta object literal for the '<em><b>Period</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POLLING_TRIGGER__PERIOD = eINSTANCE.getPollingTrigger_Period();

		/**
		 * The meta object literal for the '<em><b>Cron Expression</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POLLING_TRIGGER__CRON_EXPRESSION = eINSTANCE.getPollingTrigger_CronExpression();

		/**
		 * The meta object literal for the '<em><b>Repositories</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POLLING_TRIGGER__REPOSITORIES = eINSTANCE.getPollingTrigger_Repositories();

		/**
		 * The meta object literal for the '<em><b>Conditions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference POLLING_TRIGGER__CONDITIONS = eINSTANCE.getPollingTrigger_Conditions();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.CronTriggerImpl <em>Cron Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.CronTriggerImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getCronTrigger()
		 * @generated
		 */
		EClass CRON_TRIGGER = eINSTANCE.getCronTrigger();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CRON_TRIGGER__EXPRESSION = eINSTANCE.getCronTrigger_Expression();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.RemoteTriggerImpl <em>Remote Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.RemoteTriggerImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getRemoteTrigger()
		 * @generated
		 */
		EClass REMOTE_TRIGGER = eINSTANCE.getRemoteTrigger();

		/**
		 * The meta object literal for the '<em><b>Ip</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REMOTE_TRIGGER__IP = eINSTANCE.getRemoteTrigger_Ip();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.AfterDeploymentTriggerImpl <em>After Deployment Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.AfterDeploymentTriggerImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getAfterDeploymentTrigger()
		 * @generated
		 */
		EClass AFTER_DEPLOYMENT_TRIGGER = eINSTANCE.getAfterDeploymentTrigger();

		/**
		 * The meta object literal for the '<em><b>Deployment Project</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AFTER_DEPLOYMENT_TRIGGER__DEPLOYMENT_PROJECT = eINSTANCE
				.getAfterDeploymentTrigger_DeploymentProject();

		/**
		 * The meta object literal for the '<em><b>Environment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AFTER_DEPLOYMENT_TRIGGER__ENVIRONMENT = eINSTANCE.getAfterDeploymentTrigger_Environment();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.BuildSuccessTriggerImpl <em>Build Success Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.BuildSuccessTriggerImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getBuildSuccessTrigger()
		 * @generated
		 */
		EClass BUILD_SUCCESS_TRIGGER = eINSTANCE.getBuildSuccessTrigger();

		/**
		 * The meta object literal for the '<em><b>Branch</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUILD_SUCCESS_TRIGGER__BRANCH = eINSTANCE.getBuildSuccessTrigger_Branch();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.StageSuccessTriggerImpl <em>Stage Success Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.StageSuccessTriggerImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getStageSuccessTrigger()
		 * @generated
		 */
		EClass STAGE_SUCCESS_TRIGGER = eINSTANCE.getStageSuccessTrigger();

		/**
		 * The meta object literal for the '<em><b>Stage</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STAGE_SUCCESS_TRIGGER__STAGE = eINSTANCE.getStageSuccessTrigger_Stage();

		/**
		 * The meta object literal for the '<em><b>Branch</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STAGE_SUCCESS_TRIGGER__BRANCH = eINSTANCE.getStageSuccessTrigger_Branch();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.EnvironmentSuccessTriggerImpl <em>Environment Success Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.EnvironmentSuccessTriggerImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getEnvironmentSuccessTrigger()
		 * @generated
		 */
		EClass ENVIRONMENT_SUCCESS_TRIGGER = eINSTANCE.getEnvironmentSuccessTrigger();

		/**
		 * The meta object literal for the '<em><b>Environment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENVIRONMENT_SUCCESS_TRIGGER__ENVIRONMENT = eINSTANCE.getEnvironmentSuccessTrigger_Environment();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.ScheduledDeploymentTriggerImpl <em>Scheduled Deployment Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.ScheduledDeploymentTriggerImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getScheduledDeploymentTrigger()
		 * @generated
		 */
		EClass SCHEDULED_DEPLOYMENT_TRIGGER = eINSTANCE.getScheduledDeploymentTrigger();

		/**
		 * The meta object literal for the '<em><b>Cron Expression</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCHEDULED_DEPLOYMENT_TRIGGER__CRON_EXPRESSION = eINSTANCE
				.getScheduledDeploymentTrigger_CronExpression();

		/**
		 * The meta object literal for the '<em><b>Artifact Branch</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCHEDULED_DEPLOYMENT_TRIGGER__ARTIFACT_BRANCH = eINSTANCE
				.getScheduledDeploymentTrigger_ArtifactBranch();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.TriggerConditionImpl <em>Trigger Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.TriggerConditionImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getTriggerCondition()
		 * @generated
		 */
		EClass TRIGGER_CONDITION = eINSTANCE.getTriggerCondition();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.GreenPlanConditionImpl <em>Green Plan Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.GreenPlanConditionImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getGreenPlanCondition()
		 * @generated
		 */
		EClass GREEN_PLAN_CONDITION = eINSTANCE.getGreenPlanCondition();

		/**
		 * The meta object literal for the '<em><b>Plan Keys</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GREEN_PLAN_CONDITION__PLAN_KEYS = eINSTANCE.getGreenPlanCondition_PlanKeys();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.AllOtherConditionImpl <em>All Other Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.AllOtherConditionImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getAllOtherCondition()
		 * @generated
		 */
		EClass ALL_OTHER_CONDITION = eINSTANCE.getAllOtherCondition();

		/**
		 * The meta object literal for the '<em><b>Properties</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ALL_OTHER_CONDITION__PROPERTIES = eINSTANCE.getAllOtherCondition_Properties();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.RepositoryImpl <em>Repository</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.RepositoryImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getRepository()
		 * @generated
		 */
		EClass REPOSITORY = eINSTANCE.getRepository();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REPOSITORY__NAME = eINSTANCE.getRepository_Name();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.LinkedRepositoryImpl <em>Linked Repository</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.LinkedRepositoryImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getLinkedRepository()
		 * @generated
		 */
		EClass LINKED_REPOSITORY = eINSTANCE.getLinkedRepository();

		/**
		 * The meta object literal for the '<em><b>Scope</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINKED_REPOSITORY__SCOPE = eINSTANCE.getLinkedRepository_Scope();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.GitRepositoryImpl <em>Git Repository</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.GitRepositoryImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getGitRepository()
		 * @generated
		 */
		EClass GIT_REPOSITORY = eINSTANCE.getGitRepository();

		/**
		 * The meta object literal for the '<em><b>Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GIT_REPOSITORY__URL = eINSTANCE.getGitRepository_Url();

		/**
		 * The meta object literal for the '<em><b>Branch</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GIT_REPOSITORY__BRANCH = eINSTANCE.getGitRepository_Branch();

		/**
		 * The meta object literal for the '<em><b>Username</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GIT_REPOSITORY__USERNAME = eINSTANCE.getGitRepository_Username();

		/**
		 * The meta object literal for the '<em><b>Password</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GIT_REPOSITORY__PASSWORD = eINSTANCE.getGitRepository_Password();

		/**
		 * The meta object literal for the '<em><b>Ssh Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GIT_REPOSITORY__SSH_KEY = eINSTANCE.getGitRepository_SshKey();

		/**
		 * The meta object literal for the '<em><b>Ssh Key Passphrase</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GIT_REPOSITORY__SSH_KEY_PASSPHRASE = eINSTANCE.getGitRepository_SshKeyPassphrase();

		/**
		 * The meta object literal for the '<em><b>Shared Credentials</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GIT_REPOSITORY__SHARED_CREDENTIALS = eINSTANCE.getGitRepository_SharedCredentials();

		/**
		 * The meta object literal for the '<em><b>Shared Credentials Scope</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GIT_REPOSITORY__SHARED_CREDENTIALS_SCOPE = eINSTANCE.getGitRepository_SharedCredentialsScope();

		/**
		 * The meta object literal for the '<em><b>Lfs</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GIT_REPOSITORY__LFS = eINSTANCE.getGitRepository_Lfs();

		/**
		 * The meta object literal for the '<em><b>Use Shallow Clones</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GIT_REPOSITORY__USE_SHALLOW_CLONES = eINSTANCE.getGitRepository_UseShallowClones();

		/**
		 * The meta object literal for the '<em><b>Submodules</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GIT_REPOSITORY__SUBMODULES = eINSTANCE.getGitRepository_Submodules();

		/**
		 * The meta object literal for the '<em><b>Fetch All</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GIT_REPOSITORY__FETCH_ALL = eINSTANCE.getGitRepository_FetchAll();

		/**
		 * The meta object literal for the '<em><b>Cache On Agents</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GIT_REPOSITORY__CACHE_ON_AGENTS = eINSTANCE.getGitRepository_CacheOnAgents();

		/**
		 * The meta object literal for the '<em><b>Verbose Logs</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GIT_REPOSITORY__VERBOSE_LOGS = eINSTANCE.getGitRepository_VerboseLogs();

		/**
		 * The meta object literal for the '<em><b>Command Timeout Minutes</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GIT_REPOSITORY__COMMAND_TIMEOUT_MINUTES = eINSTANCE.getGitRepository_CommandTimeoutMinutes();

		/**
		 * The meta object literal for the '<em><b>Change Detection</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GIT_REPOSITORY__CHANGE_DETECTION = eINSTANCE.getGitRepository_ChangeDetection();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.BitbucketCloudRepositoryImpl <em>Bitbucket Cloud Repository</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.BitbucketCloudRepositoryImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getBitbucketCloudRepository()
		 * @generated
		 */
		EClass BITBUCKET_CLOUD_REPOSITORY = eINSTANCE.getBitbucketCloudRepository();

		/**
		 * The meta object literal for the '<em><b>Slug</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BITBUCKET_CLOUD_REPOSITORY__SLUG = eINSTANCE.getBitbucketCloudRepository_Slug();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.BitbucketServerRepositoryImpl <em>Bitbucket Server Repository</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.BitbucketServerRepositoryImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getBitbucketServerRepository()
		 * @generated
		 */
		EClass BITBUCKET_SERVER_REPOSITORY = eINSTANCE.getBitbucketServerRepository();

		/**
		 * The meta object literal for the '<em><b>Server</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BITBUCKET_SERVER_REPOSITORY__SERVER = eINSTANCE.getBitbucketServerRepository_Server();

		/**
		 * The meta object literal for the '<em><b>Project</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BITBUCKET_SERVER_REPOSITORY__PROJECT = eINSTANCE.getBitbucketServerRepository_Project();

		/**
		 * The meta object literal for the '<em><b>Slug</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BITBUCKET_SERVER_REPOSITORY__SLUG = eINSTANCE.getBitbucketServerRepository_Slug();

		/**
		 * The meta object literal for the '<em><b>Clone Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BITBUCKET_SERVER_REPOSITORY__CLONE_URL = eINSTANCE.getBitbucketServerRepository_CloneUrl();

		/**
		 * The meta object literal for the '<em><b>Public Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BITBUCKET_SERVER_REPOSITORY__PUBLIC_KEY = eINSTANCE.getBitbucketServerRepository_PublicKey();

		/**
		 * The meta object literal for the '<em><b>Private Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BITBUCKET_SERVER_REPOSITORY__PRIVATE_KEY = eINSTANCE.getBitbucketServerRepository_PrivateKey();

		/**
		 * The meta object literal for the '<em><b>Branch</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BITBUCKET_SERVER_REPOSITORY__BRANCH = eINSTANCE.getBitbucketServerRepository_Branch();

		/**
		 * The meta object literal for the '<em><b>Lfs</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BITBUCKET_SERVER_REPOSITORY__LFS = eINSTANCE.getBitbucketServerRepository_Lfs();

		/**
		 * The meta object literal for the '<em><b>Use Shallow Clones</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BITBUCKET_SERVER_REPOSITORY__USE_SHALLOW_CLONES = eINSTANCE
				.getBitbucketServerRepository_UseShallowClones();

		/**
		 * The meta object literal for the '<em><b>Submodules</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BITBUCKET_SERVER_REPOSITORY__SUBMODULES = eINSTANCE.getBitbucketServerRepository_Submodules();

		/**
		 * The meta object literal for the '<em><b>Command Timeout Minutes</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BITBUCKET_SERVER_REPOSITORY__COMMAND_TIMEOUT_MINUTES = eINSTANCE
				.getBitbucketServerRepository_CommandTimeoutMinutes();

		/**
		 * The meta object literal for the '<em><b>Fetch All</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BITBUCKET_SERVER_REPOSITORY__FETCH_ALL = eINSTANCE.getBitbucketServerRepository_FetchAll();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.GithubRepositoryImpl <em>Github Repository</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.GithubRepositoryImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getGithubRepository()
		 * @generated
		 */
		EClass GITHUB_REPOSITORY = eINSTANCE.getGithubRepository();

		/**
		 * The meta object literal for the '<em><b>Repository</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GITHUB_REPOSITORY__REPOSITORY = eINSTANCE.getGithubRepository_Repository();

		/**
		 * The meta object literal for the '<em><b>User</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GITHUB_REPOSITORY__USER = eINSTANCE.getGithubRepository_User();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.AnyVcsRepositoryImpl <em>Any Vcs Repository</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.AnyVcsRepositoryImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getAnyVcsRepository()
		 * @generated
		 */
		EClass ANY_VCS_REPOSITORY = eINSTANCE.getAnyVcsRepository();

		/**
		 * The meta object literal for the '<em><b>Plugin Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ANY_VCS_REPOSITORY__PLUGIN_KEY = eINSTANCE.getAnyVcsRepository_PluginKey();

		/**
		 * The meta object literal for the '<em><b>Server Config</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANY_VCS_REPOSITORY__SERVER_CONFIG = eINSTANCE.getAnyVcsRepository_ServerConfig();

		/**
		 * The meta object literal for the '<em><b>Branch Config</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANY_VCS_REPOSITORY__BRANCH_CONFIG = eINSTANCE.getAnyVcsRepository_BranchConfig();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.ChangeDetectionImpl <em>Change Detection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.ChangeDetectionImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getChangeDetection()
		 * @generated
		 */
		EClass CHANGE_DETECTION = eINSTANCE.getChangeDetection();

		/**
		 * The meta object literal for the '<em><b>Quiet Period</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHANGE_DETECTION__QUIET_PERIOD = eINSTANCE.getChangeDetection_QuietPeriod();

		/**
		 * The meta object literal for the '<em><b>Exclude Changeset Pattern</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHANGE_DETECTION__EXCLUDE_CHANGESET_PATTERN = eINSTANCE.getChangeDetection_ExcludeChangesetPattern();

		/**
		 * The meta object literal for the '<em><b>File Filter Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHANGE_DETECTION__FILE_FILTER_TYPE = eINSTANCE.getChangeDetection_FileFilterType();

		/**
		 * The meta object literal for the '<em><b>File Filter Pattern</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHANGE_DETECTION__FILE_FILTER_PATTERN = eINSTANCE.getChangeDetection_FileFilterPattern();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.QuietPeriodImpl <em>Quiet Period</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.QuietPeriodImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getQuietPeriod()
		 * @generated
		 */
		EClass QUIET_PERIOD = eINSTANCE.getQuietPeriod();

		/**
		 * The meta object literal for the '<em><b>Quiet Period Seconds</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUIET_PERIOD__QUIET_PERIOD_SECONDS = eINSTANCE.getQuietPeriod_QuietPeriodSeconds();

		/**
		 * The meta object literal for the '<em><b>Max Retries</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUIET_PERIOD__MAX_RETRIES = eINSTANCE.getQuietPeriod_MaxRetries();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.ArtifactImpl <em>Artifact</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.ArtifactImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getArtifact()
		 * @generated
		 */
		EClass ARTIFACT = eINSTANCE.getArtifact();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT__NAME = eINSTANCE.getArtifact_Name();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT__LOCATION = eINSTANCE.getArtifact_Location();

		/**
		 * The meta object literal for the '<em><b>Pattern</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT__PATTERN = eINSTANCE.getArtifact_Pattern();

		/**
		 * The meta object literal for the '<em><b>Required</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT__REQUIRED = eINSTANCE.getArtifact_Required();

		/**
		 * The meta object literal for the '<em><b>Shared</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT__SHARED = eINSTANCE.getArtifact_Shared();

		/**
		 * The meta object literal for the '<em><b>Http Compression On</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT__HTTP_COMPRESSION_ON = eINSTANCE.getArtifact_HttpCompressionOn();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.ArtifactSubscriptionImpl <em>Artifact Subscription</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.ArtifactSubscriptionImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getArtifactSubscription()
		 * @generated
		 */
		EClass ARTIFACT_SUBSCRIPTION = eINSTANCE.getArtifactSubscription();

		/**
		 * The meta object literal for the '<em><b>Artifact</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT_SUBSCRIPTION__ARTIFACT = eINSTANCE.getArtifactSubscription_Artifact();

		/**
		 * The meta object literal for the '<em><b>Destination</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT_SUBSCRIPTION__DESTINATION = eINSTANCE.getArtifactSubscription_Destination();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.RequirementImpl <em>Requirement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.RequirementImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getRequirement()
		 * @generated
		 */
		EClass REQUIREMENT = eINSTANCE.getRequirement();

		/**
		 * The meta object literal for the '<em><b>Capability</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REQUIREMENT__CAPABILITY = eINSTANCE.getRequirement_Capability();

		/**
		 * The meta object literal for the '<em><b>Match Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REQUIREMENT__MATCH_VALUE = eINSTANCE.getRequirement_MatchValue();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.VariableAssignmentImpl <em>Variable Assignment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.VariableAssignmentImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getVariableAssignment()
		 * @generated
		 */
		EClass VARIABLE_ASSIGNMENT = eINSTANCE.getVariableAssignment();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE_ASSIGNMENT__KEY = eINSTANCE.getVariableAssignment_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE_ASSIGNMENT__VALUE = eINSTANCE.getVariableAssignment_Value();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.LabelImpl <em>Label</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.LabelImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getLabel()
		 * @generated
		 */
		EClass LABEL = eINSTANCE.getLabel();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL__VALUE = eINSTANCE.getLabel_Value();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.NotificationImpl <em>Notification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.NotificationImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getNotification()
		 * @generated
		 */
		EClass NOTIFICATION = eINSTANCE.getNotification();

		/**
		 * The meta object literal for the '<em><b>Recipients</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NOTIFICATION__RECIPIENTS = eINSTANCE.getNotification_Recipients();

		/**
		 * The meta object literal for the '<em><b>Events</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NOTIFICATION__EVENTS = eINSTANCE.getNotification_Events();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.NotificationRecipientImpl <em>Notification Recipient</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.NotificationRecipientImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getNotificationRecipient()
		 * @generated
		 */
		EClass NOTIFICATION_RECIPIENT = eINSTANCE.getNotificationRecipient();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.UserRecipientImpl <em>User Recipient</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.UserRecipientImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getUserRecipient()
		 * @generated
		 */
		EClass USER_RECIPIENT = eINSTANCE.getUserRecipient();

		/**
		 * The meta object literal for the '<em><b>Users</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER_RECIPIENT__USERS = eINSTANCE.getUserRecipient_Users();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.GroupRecipientImpl <em>Group Recipient</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.GroupRecipientImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getGroupRecipient()
		 * @generated
		 */
		EClass GROUP_RECIPIENT = eINSTANCE.getGroupRecipient();

		/**
		 * The meta object literal for the '<em><b>Groups</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GROUP_RECIPIENT__GROUPS = eINSTANCE.getGroupRecipient_Groups();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.EmailRecipientImpl <em>Email Recipient</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.EmailRecipientImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getEmailRecipient()
		 * @generated
		 */
		EClass EMAIL_RECIPIENT = eINSTANCE.getEmailRecipient();

		/**
		 * The meta object literal for the '<em><b>Emails</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EMAIL_RECIPIENT__EMAILS = eINSTANCE.getEmailRecipient_Emails();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.RoleRecipientImpl <em>Role Recipient</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.RoleRecipientImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getRoleRecipient()
		 * @generated
		 */
		EClass ROLE_RECIPIENT = eINSTANCE.getRoleRecipient();

		/**
		 * The meta object literal for the '<em><b>Role</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROLE_RECIPIENT__ROLE = eINSTANCE.getRoleRecipient_Role();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.WebhookRecipientImpl <em>Webhook Recipient</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.WebhookRecipientImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getWebhookRecipient()
		 * @generated
		 */
		EClass WEBHOOK_RECIPIENT = eINSTANCE.getWebhookRecipient();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEBHOOK_RECIPIENT__NAME = eINSTANCE.getWebhookRecipient_Name();

		/**
		 * The meta object literal for the '<em><b>Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEBHOOK_RECIPIENT__URL = eINSTANCE.getWebhookRecipient_Url();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.NotificationEventImpl <em>Notification Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.NotificationEventImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getNotificationEvent()
		 * @generated
		 */
		EClass NOTIFICATION_EVENT = eINSTANCE.getNotificationEvent();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NOTIFICATION_EVENT__TYPE = eINSTANCE.getNotificationEvent_Type();

		/**
		 * The meta object literal for the '<em><b>Failures</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NOTIFICATION_EVENT__FAILURES = eINSTANCE.getNotificationEvent_Failures();

		/**
		 * The meta object literal for the '<em><b>First Only</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NOTIFICATION_EVENT__FIRST_ONLY = eINSTANCE.getNotificationEvent_FirstOnly();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.BranchManagementImpl <em>Branch Management</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.BranchManagementImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getBranchManagement()
		 * @generated
		 */
		EClass BRANCH_MANAGEMENT = eINSTANCE.getBranchManagement();

		/**
		 * The meta object literal for the '<em><b>Create</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BRANCH_MANAGEMENT__CREATE = eINSTANCE.getBranchManagement_Create();

		/**
		 * The meta object literal for the '<em><b>Create Pattern</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BRANCH_MANAGEMENT__CREATE_PATTERN = eINSTANCE.getBranchManagement_CreatePattern();

		/**
		 * The meta object literal for the '<em><b>Accept Fork</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BRANCH_MANAGEMENT__ACCEPT_FORK = eINSTANCE.getBranchManagement_AcceptFork();

		/**
		 * The meta object literal for the '<em><b>Delete</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BRANCH_MANAGEMENT__DELETE = eINSTANCE.getBranchManagement_Delete();

		/**
		 * The meta object literal for the '<em><b>Integration</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BRANCH_MANAGEMENT__INTEGRATION = eINSTANCE.getBranchManagement_Integration();

		/**
		 * The meta object literal for the '<em><b>Link To Jira</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BRANCH_MANAGEMENT__LINK_TO_JIRA = eINSTANCE.getBranchManagement_LinkToJira();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.BranchDeleteImpl <em>Branch Delete</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.BranchDeleteImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getBranchDelete()
		 * @generated
		 */
		EClass BRANCH_DELETE = eINSTANCE.getBranchDelete();

		/**
		 * The meta object literal for the '<em><b>After Deleted Days</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BRANCH_DELETE__AFTER_DELETED_DAYS = eINSTANCE.getBranchDelete_AfterDeletedDays();

		/**
		 * The meta object literal for the '<em><b>After Inactive Days</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BRANCH_DELETE__AFTER_INACTIVE_DAYS = eINSTANCE.getBranchDelete_AfterInactiveDays();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.BranchIntegrationImpl <em>Branch Integration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.BranchIntegrationImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getBranchIntegration()
		 * @generated
		 */
		EClass BRANCH_INTEGRATION = eINSTANCE.getBranchIntegration();

		/**
		 * The meta object literal for the '<em><b>Merge From</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BRANCH_INTEGRATION__MERGE_FROM = eINSTANCE.getBranchIntegration_MergeFrom();

		/**
		 * The meta object literal for the '<em><b>Merge To</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BRANCH_INTEGRATION__MERGE_TO = eINSTANCE.getBranchIntegration_MergeTo();

		/**
		 * The meta object literal for the '<em><b>Push On Success</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BRANCH_INTEGRATION__PUSH_ON_SUCCESS = eINSTANCE.getBranchIntegration_PushOnSuccess();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.BranchOverrideImpl <em>Branch Override</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.BranchOverrideImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getBranchOverride()
		 * @generated
		 */
		EClass BRANCH_OVERRIDE = eINSTANCE.getBranchOverride();

		/**
		 * The meta object literal for the '<em><b>Branch Pattern</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BRANCH_OVERRIDE__BRANCH_PATTERN = eINSTANCE.getBranchOverride_BranchPattern();

		/**
		 * The meta object literal for the '<em><b>Stages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BRANCH_OVERRIDE__STAGES = eINSTANCE.getBranchOverride_Stages();

		/**
		 * The meta object literal for the '<em><b>Jobs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BRANCH_OVERRIDE__JOBS = eINSTANCE.getBranchOverride_Jobs();

		/**
		 * The meta object literal for the '<em><b>Triggers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BRANCH_OVERRIDE__TRIGGERS = eINSTANCE.getBranchOverride_Triggers();

		/**
		 * The meta object literal for the '<em><b>Notifications</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BRANCH_OVERRIDE__NOTIFICATIONS = eINSTANCE.getBranchOverride_Notifications();

		/**
		 * The meta object literal for the '<em><b>Variables</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BRANCH_OVERRIDE__VARIABLES = eINSTANCE.getBranchOverride_Variables();

		/**
		 * The meta object literal for the '<em><b>Labels</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BRANCH_OVERRIDE__LABELS = eINSTANCE.getBranchOverride_Labels();

		/**
		 * The meta object literal for the '<em><b>Docker</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BRANCH_OVERRIDE__DOCKER = eINSTANCE.getBranchOverride_Docker();

		/**
		 * The meta object literal for the '<em><b>Branch Config</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BRANCH_OVERRIDE__BRANCH_CONFIG = eINSTANCE.getBranchOverride_BranchConfig();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.BranchSpecificConfigImpl <em>Branch Specific Config</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.BranchSpecificConfigImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getBranchSpecificConfig()
		 * @generated
		 */
		EClass BRANCH_SPECIFIC_CONFIG = eINSTANCE.getBranchSpecificConfig();

		/**
		 * The meta object literal for the '<em><b>Integration</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BRANCH_SPECIFIC_CONFIG__INTEGRATION = eINSTANCE.getBranchSpecificConfig_Integration();

		/**
		 * The meta object literal for the '<em><b>Disable Expiry</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BRANCH_SPECIFIC_CONFIG__DISABLE_EXPIRY = eINSTANCE.getBranchSpecificConfig_DisableExpiry();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.DependenciesImpl <em>Dependencies</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.DependenciesImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getDependencies()
		 * @generated
		 */
		EClass DEPENDENCIES = eINSTANCE.getDependencies();

		/**
		 * The meta object literal for the '<em><b>Require All Stages Passing</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DEPENDENCIES__REQUIRE_ALL_STAGES_PASSING = eINSTANCE.getDependencies_RequireAllStagesPassing();

		/**
		 * The meta object literal for the '<em><b>Enabled For Branches</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DEPENDENCIES__ENABLED_FOR_BRANCHES = eINSTANCE.getDependencies_EnabledForBranches();

		/**
		 * The meta object literal for the '<em><b>Block Strategy</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DEPENDENCIES__BLOCK_STRATEGY = eINSTANCE.getDependencies_BlockStrategy();

		/**
		 * The meta object literal for the '<em><b>Plans</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DEPENDENCIES__PLANS = eINSTANCE.getDependencies_Plans();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.DockerConfigImpl <em>Docker Config</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.DockerConfigImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getDockerConfig()
		 * @generated
		 */
		EClass DOCKER_CONFIG = eINSTANCE.getDockerConfig();

		/**
		 * The meta object literal for the '<em><b>Image</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCKER_CONFIG__IMAGE = eINSTANCE.getDockerConfig_Image();

		/**
		 * The meta object literal for the '<em><b>Volumes</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCKER_CONFIG__VOLUMES = eINSTANCE.getDockerConfig_Volumes();

		/**
		 * The meta object literal for the '<em><b>Use Default Volumes</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCKER_CONFIG__USE_DEFAULT_VOLUMES = eINSTANCE.getDockerConfig_UseDefaultVolumes();

		/**
		 * The meta object literal for the '<em><b>Docker Run Arguments</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCKER_CONFIG__DOCKER_RUN_ARGUMENTS = eINSTANCE.getDockerConfig_DockerRunArguments();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.OtherConfigImpl <em>Other Config</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.OtherConfigImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getOtherConfig()
		 * @generated
		 */
		EClass OTHER_CONFIG = eINSTANCE.getOtherConfig();

		/**
		 * The meta object literal for the '<em><b>Concurrent Build Plugin</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OTHER_CONFIG__CONCURRENT_BUILD_PLUGIN = eINSTANCE.getOtherConfig_ConcurrentBuildPlugin();

		/**
		 * The meta object literal for the '<em><b>Clean Working Dir</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OTHER_CONFIG__CLEAN_WORKING_DIR = eINSTANCE.getOtherConfig_CleanWorkingDir();

		/**
		 * The meta object literal for the '<em><b>All Other Apps</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OTHER_CONFIG__ALL_OTHER_APPS = eINSTANCE.getOtherConfig_AllOtherApps();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.DeploymentProjectImpl <em>Deployment Project</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.DeploymentProjectImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getDeploymentProject()
		 * @generated
		 */
		EClass DEPLOYMENT_PROJECT = eINSTANCE.getDeploymentProject();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DEPLOYMENT_PROJECT__NAME = eINSTANCE.getDeploymentProject_Name();

		/**
		 * The meta object literal for the '<em><b>Source Plan</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DEPLOYMENT_PROJECT__SOURCE_PLAN = eINSTANCE.getDeploymentProject_SourcePlan();

		/**
		 * The meta object literal for the '<em><b>Server Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DEPLOYMENT_PROJECT__SERVER_NAME = eINSTANCE.getDeploymentProject_ServerName();

		/**
		 * The meta object literal for the '<em><b>Release Naming</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPLOYMENT_PROJECT__RELEASE_NAMING = eINSTANCE.getDeploymentProject_ReleaseNaming();

		/**
		 * The meta object literal for the '<em><b>Environments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPLOYMENT_PROJECT__ENVIRONMENTS = eINSTANCE.getDeploymentProject_Environments();

		/**
		 * The meta object literal for the '<em><b>Permissions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPLOYMENT_PROJECT__PERMISSIONS = eINSTANCE.getDeploymentProject_Permissions();

		/**
		 * The meta object literal for the '<em><b>Default Environment Permissions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPLOYMENT_PROJECT__DEFAULT_ENVIRONMENT_PERMISSIONS = eINSTANCE
				.getDeploymentProject_DefaultEnvironmentPermissions();

		/**
		 * The meta object literal for the '<em><b>Environment Permissions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPLOYMENT_PROJECT__ENVIRONMENT_PERMISSIONS = eINSTANCE
				.getDeploymentProject_EnvironmentPermissions();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.ReleaseNamingImpl <em>Release Naming</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.ReleaseNamingImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getReleaseNaming()
		 * @generated
		 */
		EClass RELEASE_NAMING = eINSTANCE.getReleaseNaming();

		/**
		 * The meta object literal for the '<em><b>Next Version Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELEASE_NAMING__NEXT_VERSION_NAME = eINSTANCE.getReleaseNaming_NextVersionName();

		/**
		 * The meta object literal for the '<em><b>Applies To Branches</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELEASE_NAMING__APPLIES_TO_BRANCHES = eINSTANCE.getReleaseNaming_AppliesToBranches();

		/**
		 * The meta object literal for the '<em><b>Auto Increment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELEASE_NAMING__AUTO_INCREMENT = eINSTANCE.getReleaseNaming_AutoIncrement();

		/**
		 * The meta object literal for the '<em><b>Auto Increment Variables</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELEASE_NAMING__AUTO_INCREMENT_VARIABLES = eINSTANCE.getReleaseNaming_AutoIncrementVariables();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.EnvironmentImpl <em>Environment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.EnvironmentImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getEnvironment()
		 * @generated
		 */
		EClass ENVIRONMENT = eINSTANCE.getEnvironment();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENVIRONMENT__NAME = eINSTANCE.getEnvironment_Name();

		/**
		 * The meta object literal for the '<em><b>Tasks</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENVIRONMENT__TASKS = eINSTANCE.getEnvironment_Tasks();

		/**
		 * The meta object literal for the '<em><b>Final Tasks</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENVIRONMENT__FINAL_TASKS = eINSTANCE.getEnvironment_FinalTasks();

		/**
		 * The meta object literal for the '<em><b>Variables</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENVIRONMENT__VARIABLES = eINSTANCE.getEnvironment_Variables();

		/**
		 * The meta object literal for the '<em><b>Triggers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENVIRONMENT__TRIGGERS = eINSTANCE.getEnvironment_Triggers();

		/**
		 * The meta object literal for the '<em><b>Notifications</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENVIRONMENT__NOTIFICATIONS = eINSTANCE.getEnvironment_Notifications();

		/**
		 * The meta object literal for the '<em><b>Docker</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENVIRONMENT__DOCKER = eINSTANCE.getEnvironment_Docker();

		/**
		 * The meta object literal for the '<em><b>Requirements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENVIRONMENT__REQUIREMENTS = eINSTANCE.getEnvironment_Requirements();

		/**
		 * The meta object literal for the '<em><b>Release Approval Prerequisite</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENVIRONMENT__RELEASE_APPROVAL_PREREQUISITE = eINSTANCE.getEnvironment_ReleaseApprovalPrerequisite();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.PermissionEntryImpl <em>Permission Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.PermissionEntryImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getPermissionEntry()
		 * @generated
		 */
		EClass PERMISSION_ENTRY = eINSTANCE.getPermissionEntry();

		/**
		 * The meta object literal for the '<em><b>Users</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERMISSION_ENTRY__USERS = eINSTANCE.getPermissionEntry_Users();

		/**
		 * The meta object literal for the '<em><b>Groups</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERMISSION_ENTRY__GROUPS = eINSTANCE.getPermissionEntry_Groups();

		/**
		 * The meta object literal for the '<em><b>Roles</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERMISSION_ENTRY__ROLES = eINSTANCE.getPermissionEntry_Roles();

		/**
		 * The meta object literal for the '<em><b>Permissions</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERMISSION_ENTRY__PERMISSIONS = eINSTANCE.getPermissionEntry_Permissions();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.PlanPermissionEntryImpl <em>Plan Permission Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.PlanPermissionEntryImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getPlanPermissionEntry()
		 * @generated
		 */
		EClass PLAN_PERMISSION_ENTRY = eINSTANCE.getPlanPermissionEntry();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.DeploymentPermissionEntryImpl <em>Deployment Permission Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.DeploymentPermissionEntryImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getDeploymentPermissionEntry()
		 * @generated
		 */
		EClass DEPLOYMENT_PERMISSION_ENTRY = eINSTANCE.getDeploymentPermissionEntry();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.EnvironmentPermissionEntryImpl <em>Environment Permission Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.EnvironmentPermissionEntryImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getEnvironmentPermissionEntry()
		 * @generated
		 */
		EClass ENVIRONMENT_PERMISSION_ENTRY = eINSTANCE.getEnvironmentPermissionEntry();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.impl.NamedEnvironmentPermissionImpl <em>Named Environment Permission</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.impl.NamedEnvironmentPermissionImpl
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getNamedEnvironmentPermission()
		 * @generated
		 */
		EClass NAMED_ENVIRONMENT_PERMISSION = eINSTANCE.getNamedEnvironmentPermission();

		/**
		 * The meta object literal for the '<em><b>Environment Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_ENVIRONMENT_PERMISSION__ENVIRONMENT_NAME = eINSTANCE
				.getNamedEnvironmentPermission_EnvironmentName();

		/**
		 * The meta object literal for the '<em><b>Entries</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NAMED_ENVIRONMENT_PERMISSION__ENTRIES = eINSTANCE.getNamedEnvironmentPermission_Entries();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.VARIABLE_SCOPE <em>VARIABLE SCOPE</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.VARIABLE_SCOPE
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getVARIABLE_SCOPE()
		 * @generated
		 */
		EEnum VARIABLE_SCOPE = eINSTANCE.getVARIABLE_SCOPE();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.TEST_PARSER_TYPE <em>TEST PARSER TYPE</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.TEST_PARSER_TYPE
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getTEST_PARSER_TYPE()
		 * @generated
		 */
		EEnum TEST_PARSER_TYPE = eINSTANCE.getTEST_PARSER_TYPE();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.REPOSITORY_SCOPE <em>REPOSITORY SCOPE</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.REPOSITORY_SCOPE
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getREPOSITORY_SCOPE()
		 * @generated
		 */
		EEnum REPOSITORY_SCOPE = eINSTANCE.getREPOSITORY_SCOPE();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.FILE_FILTER_TYPE <em>FILE FILTER TYPE</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.FILE_FILTER_TYPE
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getFILE_FILTER_TYPE()
		 * @generated
		 */
		EEnum FILE_FILTER_TYPE = eINSTANCE.getFILE_FILTER_TYPE();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.BRANCH_CREATE_STRATEGY <em>BRANCH CREATE STRATEGY</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.BRANCH_CREATE_STRATEGY
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getBRANCH_CREATE_STRATEGY()
		 * @generated
		 */
		EEnum BRANCH_CREATE_STRATEGY = eINSTANCE.getBRANCH_CREATE_STRATEGY();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.BLOCK_STRATEGY <em>BLOCK STRATEGY</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.BLOCK_STRATEGY
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getBLOCK_STRATEGY()
		 * @generated
		 */
		EEnum BLOCK_STRATEGY = eINSTANCE.getBLOCK_STRATEGY();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.NOTIFICATION_EVENT_TYPE <em>NOTIFICATION EVENT TYPE</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.NOTIFICATION_EVENT_TYPE
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getNOTIFICATION_EVENT_TYPE()
		 * @generated
		 */
		EEnum NOTIFICATION_EVENT_TYPE = eINSTANCE.getNOTIFICATION_EVENT_TYPE();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.NOTIFICATION_ROLE <em>NOTIFICATION ROLE</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.NOTIFICATION_ROLE
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getNOTIFICATION_ROLE()
		 * @generated
		 */
		EEnum NOTIFICATION_ROLE = eINSTANCE.getNOTIFICATION_ROLE();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.PERMISSION_ROLE <em>PERMISSION ROLE</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.PERMISSION_ROLE
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getPERMISSION_ROLE()
		 * @generated
		 */
		EEnum PERMISSION_ROLE = eINSTANCE.getPERMISSION_ROLE();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.PERMISSION_TYPE <em>PERMISSION TYPE</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.PERMISSION_TYPE
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getPERMISSION_TYPE()
		 * @generated
		 */
		EEnum PERMISSION_TYPE = eINSTANCE.getPERMISSION_TYPE();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.bambooMM.RELEASE_APPROVAL <em>RELEASE APPROVAL</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.bambooMM.RELEASE_APPROVAL
		 * @see com.mddoai.metamodel.bambooMM.impl.BambooPackageImpl#getRELEASE_APPROVAL()
		 * @generated
		 */
		EEnum RELEASE_APPROVAL = eINSTANCE.getRELEASE_APPROVAL();

	}

} //BambooPackage
