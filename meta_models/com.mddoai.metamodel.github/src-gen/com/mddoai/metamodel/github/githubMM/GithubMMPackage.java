/**
 */
package com.mddoai.metamodel.github.githubMM;

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
 * @see com.mddoai.metamodel.github.githubMM.GithubMMFactory
 * @model kind="package"
 * @generated
 */
public interface GithubMMPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "githubMM";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.mddoai.com/mddoai/metamodel/github";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "githubMM";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	GithubMMPackage eINSTANCE = com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.WorkflowImpl <em>Workflow</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.WorkflowImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getWorkflow()
	 * @generated
	 */
	int WORKFLOW = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW__NAME = 0;

	/**
	 * The feature id for the '<em><b>Run Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW__RUN_NAME = 1;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW__TRIGGERS = 2;

	/**
	 * The feature id for the '<em><b>Permissions</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW__PERMISSIONS = 3;

	/**
	 * The feature id for the '<em><b>Defaults</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW__DEFAULTS = 4;

	/**
	 * The feature id for the '<em><b>Environment Variables</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW__ENVIRONMENT_VARIABLES = 5;

	/**
	 * The feature id for the '<em><b>Concurrency Group</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW__CONCURRENCY_GROUP = 6;

	/**
	 * The feature id for the '<em><b>Jobs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW__JOBS = 7;

	/**
	 * The number of structural features of the '<em>Workflow</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_FEATURE_COUNT = 8;

	/**
	 * The number of operations of the '<em>Workflow</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.JobImpl <em>Job</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.JobImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getJob()
	 * @generated
	 */
	int JOB = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__NAME = 0;

	/**
	 * The feature id for the '<em><b>Job Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__JOB_NAME = 1;

	/**
	 * The feature id for the '<em><b>Permissions</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__PERMISSIONS = 2;

	/**
	 * The feature id for the '<em><b>Depends On</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__DEPENDS_ON = 3;

	/**
	 * The feature id for the '<em><b>Necessary For</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__NECESSARY_FOR = 4;

	/**
	 * The feature id for the '<em><b>If Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__IF_CONDITION = 5;

	/**
	 * The feature id for the '<em><b>Agent</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__AGENT = 6;

	/**
	 * The feature id for the '<em><b>Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__CONTAINER = 7;

	/**
	 * The feature id for the '<em><b>Staging Environment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__STAGING_ENVIRONMENT = 8;

	/**
	 * The feature id for the '<em><b>Defaults</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__DEFAULTS = 9;

	/**
	 * The feature id for the '<em><b>Environment Variables</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__ENVIRONMENT_VARIABLES = 10;

	/**
	 * The feature id for the '<em><b>Services</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__SERVICES = 11;

	/**
	 * The feature id for the '<em><b>Concurrency Group</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__CONCURRENCY_GROUP = 12;

	/**
	 * The feature id for the '<em><b>Timeout Minutes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__TIMEOUT_MINUTES = 13;

	/**
	 * The feature id for the '<em><b>Continue On Error</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__CONTINUE_ON_ERROR = 14;

	/**
	 * The feature id for the '<em><b>Strategy</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__STRATEGY = 15;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__OUTPUTS = 16;

	/**
	 * The number of structural features of the '<em>Job</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB_FEATURE_COUNT = 17;

	/**
	 * The number of operations of the '<em>Job</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.ScriptJobImpl <em>Script Job</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.ScriptJobImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getScriptJob()
	 * @generated
	 */
	int SCRIPT_JOB = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_JOB__NAME = JOB__NAME;

	/**
	 * The feature id for the '<em><b>Job Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_JOB__JOB_NAME = JOB__JOB_NAME;

	/**
	 * The feature id for the '<em><b>Permissions</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_JOB__PERMISSIONS = JOB__PERMISSIONS;

	/**
	 * The feature id for the '<em><b>Depends On</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_JOB__DEPENDS_ON = JOB__DEPENDS_ON;

	/**
	 * The feature id for the '<em><b>Necessary For</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_JOB__NECESSARY_FOR = JOB__NECESSARY_FOR;

	/**
	 * The feature id for the '<em><b>If Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_JOB__IF_CONDITION = JOB__IF_CONDITION;

	/**
	 * The feature id for the '<em><b>Agent</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_JOB__AGENT = JOB__AGENT;

	/**
	 * The feature id for the '<em><b>Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_JOB__CONTAINER = JOB__CONTAINER;

	/**
	 * The feature id for the '<em><b>Staging Environment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_JOB__STAGING_ENVIRONMENT = JOB__STAGING_ENVIRONMENT;

	/**
	 * The feature id for the '<em><b>Defaults</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_JOB__DEFAULTS = JOB__DEFAULTS;

	/**
	 * The feature id for the '<em><b>Environment Variables</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_JOB__ENVIRONMENT_VARIABLES = JOB__ENVIRONMENT_VARIABLES;

	/**
	 * The feature id for the '<em><b>Services</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_JOB__SERVICES = JOB__SERVICES;

	/**
	 * The feature id for the '<em><b>Concurrency Group</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_JOB__CONCURRENCY_GROUP = JOB__CONCURRENCY_GROUP;

	/**
	 * The feature id for the '<em><b>Timeout Minutes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_JOB__TIMEOUT_MINUTES = JOB__TIMEOUT_MINUTES;

	/**
	 * The feature id for the '<em><b>Continue On Error</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_JOB__CONTINUE_ON_ERROR = JOB__CONTINUE_ON_ERROR;

	/**
	 * The feature id for the '<em><b>Strategy</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_JOB__STRATEGY = JOB__STRATEGY;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_JOB__OUTPUTS = JOB__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Steps</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_JOB__STEPS = JOB_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Script Job</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_JOB_FEATURE_COUNT = JOB_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Script Job</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_JOB_OPERATION_COUNT = JOB_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.WorkflowCallJobImpl <em>Workflow Call Job</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.WorkflowCallJobImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getWorkflowCallJob()
	 * @generated
	 */
	int WORKFLOW_CALL_JOB = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_CALL_JOB__NAME = JOB__NAME;

	/**
	 * The feature id for the '<em><b>Job Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_CALL_JOB__JOB_NAME = JOB__JOB_NAME;

	/**
	 * The feature id for the '<em><b>Permissions</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_CALL_JOB__PERMISSIONS = JOB__PERMISSIONS;

	/**
	 * The feature id for the '<em><b>Depends On</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_CALL_JOB__DEPENDS_ON = JOB__DEPENDS_ON;

	/**
	 * The feature id for the '<em><b>Necessary For</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_CALL_JOB__NECESSARY_FOR = JOB__NECESSARY_FOR;

	/**
	 * The feature id for the '<em><b>If Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_CALL_JOB__IF_CONDITION = JOB__IF_CONDITION;

	/**
	 * The feature id for the '<em><b>Agent</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_CALL_JOB__AGENT = JOB__AGENT;

	/**
	 * The feature id for the '<em><b>Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_CALL_JOB__CONTAINER = JOB__CONTAINER;

	/**
	 * The feature id for the '<em><b>Staging Environment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_CALL_JOB__STAGING_ENVIRONMENT = JOB__STAGING_ENVIRONMENT;

	/**
	 * The feature id for the '<em><b>Defaults</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_CALL_JOB__DEFAULTS = JOB__DEFAULTS;

	/**
	 * The feature id for the '<em><b>Environment Variables</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_CALL_JOB__ENVIRONMENT_VARIABLES = JOB__ENVIRONMENT_VARIABLES;

	/**
	 * The feature id for the '<em><b>Services</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_CALL_JOB__SERVICES = JOB__SERVICES;

	/**
	 * The feature id for the '<em><b>Concurrency Group</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_CALL_JOB__CONCURRENCY_GROUP = JOB__CONCURRENCY_GROUP;

	/**
	 * The feature id for the '<em><b>Timeout Minutes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_CALL_JOB__TIMEOUT_MINUTES = JOB__TIMEOUT_MINUTES;

	/**
	 * The feature id for the '<em><b>Continue On Error</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_CALL_JOB__CONTINUE_ON_ERROR = JOB__CONTINUE_ON_ERROR;

	/**
	 * The feature id for the '<em><b>Strategy</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_CALL_JOB__STRATEGY = JOB__STRATEGY;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_CALL_JOB__OUTPUTS = JOB__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Uses</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_CALL_JOB__USES = JOB_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Args</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_CALL_JOB__ARGS = JOB_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Secrets</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_CALL_JOB__SECRETS = JOB_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Inherit Secrets</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_CALL_JOB__INHERIT_SECRETS = JOB_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Workflow Call Job</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_CALL_JOB_FEATURE_COUNT = JOB_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Workflow Call Job</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_CALL_JOB_OPERATION_COUNT = JOB_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.AgentImpl <em>Agent</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.AgentImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getAgent()
	 * @generated
	 */
	int AGENT = 4;

	/**
	 * The feature id for the '<em><b>Group</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Labels</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT__LABELS = 1;

	/**
	 * The number of structural features of the '<em>Agent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Agent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.ContainerImpl <em>Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.ContainerImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getContainer()
	 * @generated
	 */
	int CONTAINER = 5;

	/**
	 * The feature id for the '<em><b>Image</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__IMAGE = 0;

	/**
	 * The feature id for the '<em><b>Username</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__USERNAME = 1;

	/**
	 * The feature id for the '<em><b>Password</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__PASSWORD = 2;

	/**
	 * The feature id for the '<em><b>Environment Variables</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ENVIRONMENT_VARIABLES = 3;

	/**
	 * The feature id for the '<em><b>Ports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__PORTS = 4;

	/**
	 * The feature id for the '<em><b>Volumes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__VOLUMES = 5;

	/**
	 * The feature id for the '<em><b>Options</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__OPTIONS = 6;

	/**
	 * The number of structural features of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_FEATURE_COUNT = 7;

	/**
	 * The number of operations of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.ServiceImpl <em>Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.ServiceImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getService()
	 * @generated
	 */
	int SERVICE = 6;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.StagingEnvironmentImpl <em>Staging Environment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.StagingEnvironmentImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getStagingEnvironment()
	 * @generated
	 */
	int STAGING_ENVIRONMENT = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGING_ENVIRONMENT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Url</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGING_ENVIRONMENT__URL = 1;

	/**
	 * The number of structural features of the '<em>Staging Environment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGING_ENVIRONMENT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Staging Environment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGING_ENVIRONMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.TriggerImpl <em>Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.TriggerImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getTrigger()
	 * @generated
	 */
	int TRIGGER = 8;

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
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.EventTypeTriggerImpl <em>Event Type Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.EventTypeTriggerImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getEventTypeTrigger()
	 * @generated
	 */
	int EVENT_TYPE_TRIGGER = 9;

	/**
	 * The feature id for the '<em><b>Event Types</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_TYPE_TRIGGER__EVENT_TYPES = TRIGGER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Event Type Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_TYPE_TRIGGER_FEATURE_COUNT = TRIGGER_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Event Type Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_TYPE_TRIGGER_OPERATION_COUNT = TRIGGER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.SpecifiedBranchesTriggerImpl <em>Specified Branches Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.SpecifiedBranchesTriggerImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getSpecifiedBranchesTrigger()
	 * @generated
	 */
	int SPECIFIED_BRANCHES_TRIGGER = 10;

	/**
	 * The feature id for the '<em><b>Branches</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIED_BRANCHES_TRIGGER__BRANCHES = TRIGGER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Ignore Specified Branches</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIED_BRANCHES_TRIGGER__IGNORE_SPECIFIED_BRANCHES = TRIGGER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Specified Branches Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIED_BRANCHES_TRIGGER_FEATURE_COUNT = TRIGGER_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Specified Branches Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIED_BRANCHES_TRIGGER_OPERATION_COUNT = TRIGGER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.SpecifiedPathsTriggerImpl <em>Specified Paths Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.SpecifiedPathsTriggerImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getSpecifiedPathsTrigger()
	 * @generated
	 */
	int SPECIFIED_PATHS_TRIGGER = 11;

	/**
	 * The feature id for the '<em><b>Paths</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIED_PATHS_TRIGGER__PATHS = TRIGGER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Ignore Specified Paths</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIED_PATHS_TRIGGER__IGNORE_SPECIFIED_PATHS = TRIGGER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Specified Paths Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIED_PATHS_TRIGGER_FEATURE_COUNT = TRIGGER_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Specified Paths Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFIED_PATHS_TRIGGER_OPERATION_COUNT = TRIGGER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.StandardEventTriggerImpl <em>Standard Event Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.StandardEventTriggerImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getStandardEventTrigger()
	 * @generated
	 */
	int STANDARD_EVENT_TRIGGER = 12;

	/**
	 * The feature id for the '<em><b>Event Types</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_EVENT_TRIGGER__EVENT_TYPES = EVENT_TYPE_TRIGGER__EVENT_TYPES;

	/**
	 * The feature id for the '<em><b>Event</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_EVENT_TRIGGER__EVENT = EVENT_TYPE_TRIGGER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Standard Event Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_EVENT_TRIGGER_FEATURE_COUNT = EVENT_TYPE_TRIGGER_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Standard Event Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STANDARD_EVENT_TRIGGER_OPERATION_COUNT = EVENT_TYPE_TRIGGER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.InputTriggerImpl <em>Input Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.InputTriggerImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getInputTrigger()
	 * @generated
	 */
	int INPUT_TRIGGER = 13;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_TRIGGER__INPUTS = TRIGGER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Input Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_TRIGGER_FEATURE_COUNT = TRIGGER_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Input Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_TRIGGER_OPERATION_COUNT = TRIGGER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.WorkflowRunTriggerImpl <em>Workflow Run Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.WorkflowRunTriggerImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getWorkflowRunTrigger()
	 * @generated
	 */
	int WORKFLOW_RUN_TRIGGER = 14;

	/**
	 * The feature id for the '<em><b>Branches</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_RUN_TRIGGER__BRANCHES = SPECIFIED_BRANCHES_TRIGGER__BRANCHES;

	/**
	 * The feature id for the '<em><b>Ignore Specified Branches</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_RUN_TRIGGER__IGNORE_SPECIFIED_BRANCHES = SPECIFIED_BRANCHES_TRIGGER__IGNORE_SPECIFIED_BRANCHES;

	/**
	 * The number of structural features of the '<em>Workflow Run Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_RUN_TRIGGER_FEATURE_COUNT = SPECIFIED_BRANCHES_TRIGGER_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Workflow Run Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_RUN_TRIGGER_OPERATION_COUNT = SPECIFIED_BRANCHES_TRIGGER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.PullRequestTriggerImpl <em>Pull Request Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.PullRequestTriggerImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getPullRequestTrigger()
	 * @generated
	 */
	int PULL_REQUEST_TRIGGER = 15;

	/**
	 * The feature id for the '<em><b>Event Types</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PULL_REQUEST_TRIGGER__EVENT_TYPES = EVENT_TYPE_TRIGGER__EVENT_TYPES;

	/**
	 * The feature id for the '<em><b>Branches</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PULL_REQUEST_TRIGGER__BRANCHES = EVENT_TYPE_TRIGGER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Ignore Specified Branches</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PULL_REQUEST_TRIGGER__IGNORE_SPECIFIED_BRANCHES = EVENT_TYPE_TRIGGER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Paths</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PULL_REQUEST_TRIGGER__PATHS = EVENT_TYPE_TRIGGER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Ignore Specified Paths</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PULL_REQUEST_TRIGGER__IGNORE_SPECIFIED_PATHS = EVENT_TYPE_TRIGGER_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Pull Request Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PULL_REQUEST_TRIGGER_FEATURE_COUNT = EVENT_TYPE_TRIGGER_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Pull Request Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PULL_REQUEST_TRIGGER_OPERATION_COUNT = EVENT_TYPE_TRIGGER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.PullRequestTargetTriggerImpl <em>Pull Request Target Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.PullRequestTargetTriggerImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getPullRequestTargetTrigger()
	 * @generated
	 */
	int PULL_REQUEST_TARGET_TRIGGER = 16;

	/**
	 * The feature id for the '<em><b>Event Types</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PULL_REQUEST_TARGET_TRIGGER__EVENT_TYPES = PULL_REQUEST_TRIGGER__EVENT_TYPES;

	/**
	 * The feature id for the '<em><b>Branches</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PULL_REQUEST_TARGET_TRIGGER__BRANCHES = PULL_REQUEST_TRIGGER__BRANCHES;

	/**
	 * The feature id for the '<em><b>Ignore Specified Branches</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PULL_REQUEST_TARGET_TRIGGER__IGNORE_SPECIFIED_BRANCHES = PULL_REQUEST_TRIGGER__IGNORE_SPECIFIED_BRANCHES;

	/**
	 * The feature id for the '<em><b>Paths</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PULL_REQUEST_TARGET_TRIGGER__PATHS = PULL_REQUEST_TRIGGER__PATHS;

	/**
	 * The feature id for the '<em><b>Ignore Specified Paths</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PULL_REQUEST_TARGET_TRIGGER__IGNORE_SPECIFIED_PATHS = PULL_REQUEST_TRIGGER__IGNORE_SPECIFIED_PATHS;

	/**
	 * The number of structural features of the '<em>Pull Request Target Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PULL_REQUEST_TARGET_TRIGGER_FEATURE_COUNT = PULL_REQUEST_TRIGGER_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Pull Request Target Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PULL_REQUEST_TARGET_TRIGGER_OPERATION_COUNT = PULL_REQUEST_TRIGGER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.PushTriggerImpl <em>Push Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.PushTriggerImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getPushTrigger()
	 * @generated
	 */
	int PUSH_TRIGGER = 17;

	/**
	 * The feature id for the '<em><b>Branches</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_TRIGGER__BRANCHES = SPECIFIED_BRANCHES_TRIGGER__BRANCHES;

	/**
	 * The feature id for the '<em><b>Ignore Specified Branches</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_TRIGGER__IGNORE_SPECIFIED_BRANCHES = SPECIFIED_BRANCHES_TRIGGER__IGNORE_SPECIFIED_BRANCHES;

	/**
	 * The feature id for the '<em><b>Paths</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_TRIGGER__PATHS = SPECIFIED_BRANCHES_TRIGGER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Ignore Specified Paths</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_TRIGGER__IGNORE_SPECIFIED_PATHS = SPECIFIED_BRANCHES_TRIGGER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_TRIGGER__TAGS = SPECIFIED_BRANCHES_TRIGGER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Ignore Specified Tags</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_TRIGGER__IGNORE_SPECIFIED_TAGS = SPECIFIED_BRANCHES_TRIGGER_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Push Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_TRIGGER_FEATURE_COUNT = SPECIFIED_BRANCHES_TRIGGER_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Push Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_TRIGGER_OPERATION_COUNT = SPECIFIED_BRANCHES_TRIGGER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.ScheduleTriggerImpl <em>Schedule Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.ScheduleTriggerImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getScheduleTrigger()
	 * @generated
	 */
	int SCHEDULE_TRIGGER = 18;

	/**
	 * The feature id for the '<em><b>Crons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE_TRIGGER__CRONS = TRIGGER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Schedule Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE_TRIGGER_FEATURE_COUNT = TRIGGER_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Schedule Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE_TRIGGER_OPERATION_COUNT = TRIGGER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.WorkflowCallTriggerImpl <em>Workflow Call Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.WorkflowCallTriggerImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getWorkflowCallTrigger()
	 * @generated
	 */
	int WORKFLOW_CALL_TRIGGER = 19;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_CALL_TRIGGER__INPUTS = INPUT_TRIGGER__INPUTS;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_CALL_TRIGGER__OUTPUTS = INPUT_TRIGGER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Secrets</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_CALL_TRIGGER__SECRETS = INPUT_TRIGGER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Workflow Call Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_CALL_TRIGGER_FEATURE_COUNT = INPUT_TRIGGER_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Workflow Call Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_CALL_TRIGGER_OPERATION_COUNT = INPUT_TRIGGER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.WorkflowDispatchTriggerImpl <em>Workflow Dispatch Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.WorkflowDispatchTriggerImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getWorkflowDispatchTrigger()
	 * @generated
	 */
	int WORKFLOW_DISPATCH_TRIGGER = 20;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_DISPATCH_TRIGGER__INPUTS = INPUT_TRIGGER__INPUTS;

	/**
	 * The number of structural features of the '<em>Workflow Dispatch Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_DISPATCH_TRIGGER_FEATURE_COUNT = INPUT_TRIGGER_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Workflow Dispatch Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_DISPATCH_TRIGGER_OPERATION_COUNT = INPUT_TRIGGER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.PermissionImpl <em>Permission</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.PermissionImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getPermission()
	 * @generated
	 */
	int PERMISSION = 21;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERMISSION__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERMISSION__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Permission</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERMISSION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Permission</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERMISSION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.ConcurrencyGroupImpl <em>Concurrency Group</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.ConcurrencyGroupImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getConcurrencyGroup()
	 * @generated
	 */
	int CONCURRENCY_GROUP = 22;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCURRENCY_GROUP__NAME = 0;

	/**
	 * The feature id for the '<em><b>Cancel In Progress</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCURRENCY_GROUP__CANCEL_IN_PROGRESS = 1;

	/**
	 * The number of structural features of the '<em>Concurrency Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCURRENCY_GROUP_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Concurrency Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCURRENCY_GROUP_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.DefaultsImpl <em>Defaults</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.DefaultsImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getDefaults()
	 * @generated
	 */
	int DEFAULTS = 23;

	/**
	 * The feature id for the '<em><b>Shell</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULTS__SHELL = 0;

	/**
	 * The feature id for the '<em><b>Working Directory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULTS__WORKING_DIRECTORY = 1;

	/**
	 * The number of structural features of the '<em>Defaults</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULTS_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Defaults</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULTS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.ExpressionImpl <em>Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.ExpressionImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getExpression()
	 * @generated
	 */
	int EXPRESSION = 24;

	/**
	 * The number of structural features of the '<em>Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.VariableDeclarationImpl <em>Variable Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.VariableDeclarationImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getVariableDeclaration()
	 * @generated
	 */
	int VARIABLE_DECLARATION = 25;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION__NAME = 0;

	/**
	 * The number of structural features of the '<em>Variable Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Variable Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.VariableAssignmentImpl <em>Variable Assignment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.VariableAssignmentImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getVariableAssignment()
	 * @generated
	 */
	int VARIABLE_ASSIGNMENT = 26;

	/**
	 * The feature id for the '<em><b>Key</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_ASSIGNMENT__KEY = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_ASSIGNMENT__VALUE = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Variable Assignment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_ASSIGNMENT_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Variable Assignment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_ASSIGNMENT_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.BinaryOpImpl <em>Binary Op</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.BinaryOpImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getBinaryOp()
	 * @generated
	 */
	int BINARY_OP = 27;

	/**
	 * The feature id for the '<em><b>Lhs</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_OP__LHS = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Rhs</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_OP__RHS = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Binary Op</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_OP_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Binary Op</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_OP_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.ConcatImpl <em>Concat</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.ConcatImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getConcat()
	 * @generated
	 */
	int CONCAT = 28;

	/**
	 * The feature id for the '<em><b>Expressions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCAT__EXPRESSIONS = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Concat</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCAT_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Concat</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCAT_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.DotOpImpl <em>Dot Op</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.DotOpImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getDotOp()
	 * @generated
	 */
	int DOT_OP = 29;

	/**
	 * The feature id for the '<em><b>Lhs</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOT_OP__LHS = BINARY_OP__LHS;

	/**
	 * The feature id for the '<em><b>Rhs</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOT_OP__RHS = BINARY_OP__RHS;

	/**
	 * The number of structural features of the '<em>Dot Op</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOT_OP_FEATURE_COUNT = BINARY_OP_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Dot Op</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOT_OP_OPERATION_COUNT = BINARY_OP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.EqualityImpl <em>Equality</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.EqualityImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getEquality()
	 * @generated
	 */
	int EQUALITY = 30;

	/**
	 * The feature id for the '<em><b>Lhs</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUALITY__LHS = BINARY_OP__LHS;

	/**
	 * The feature id for the '<em><b>Rhs</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUALITY__RHS = BINARY_OP__RHS;

	/**
	 * The feature id for the '<em><b>Op</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUALITY__OP = BINARY_OP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Equality</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUALITY_FEATURE_COUNT = BINARY_OP_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Equality</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUALITY_OPERATION_COUNT = BINARY_OP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.ComparisonImpl <em>Comparison</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.ComparisonImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getComparison()
	 * @generated
	 */
	int COMPARISON = 31;

	/**
	 * The feature id for the '<em><b>Lhs</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARISON__LHS = BINARY_OP__LHS;

	/**
	 * The feature id for the '<em><b>Rhs</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARISON__RHS = BINARY_OP__RHS;

	/**
	 * The feature id for the '<em><b>Op</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARISON__OP = BINARY_OP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Comparison</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARISON_FEATURE_COUNT = BINARY_OP_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Comparison</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARISON_OPERATION_COUNT = BINARY_OP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.LogicalOpImpl <em>Logical Op</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.LogicalOpImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getLogicalOp()
	 * @generated
	 */
	int LOGICAL_OP = 32;

	/**
	 * The feature id for the '<em><b>Lhs</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_OP__LHS = BINARY_OP__LHS;

	/**
	 * The feature id for the '<em><b>Rhs</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_OP__RHS = BINARY_OP__RHS;

	/**
	 * The number of structural features of the '<em>Logical Op</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_OP_FEATURE_COUNT = BINARY_OP_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Logical Op</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_OP_OPERATION_COUNT = BINARY_OP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.OrImpl <em>Or</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.OrImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getOr()
	 * @generated
	 */
	int OR = 33;

	/**
	 * The feature id for the '<em><b>Lhs</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR__LHS = LOGICAL_OP__LHS;

	/**
	 * The feature id for the '<em><b>Rhs</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR__RHS = LOGICAL_OP__RHS;

	/**
	 * The number of structural features of the '<em>Or</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_FEATURE_COUNT = LOGICAL_OP_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Or</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_OPERATION_COUNT = LOGICAL_OP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.AndImpl <em>And</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.AndImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getAnd()
	 * @generated
	 */
	int AND = 34;

	/**
	 * The feature id for the '<em><b>Lhs</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND__LHS = LOGICAL_OP__LHS;

	/**
	 * The feature id for the '<em><b>Rhs</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND__RHS = LOGICAL_OP__RHS;

	/**
	 * The number of structural features of the '<em>And</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND_FEATURE_COUNT = LOGICAL_OP_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>And</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND_OPERATION_COUNT = LOGICAL_OP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.UnaryOpImpl <em>Unary Op</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.UnaryOpImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getUnaryOp()
	 * @generated
	 */
	int UNARY_OP = 35;

	/**
	 * The feature id for the '<em><b>Child Expr</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_OP__CHILD_EXPR = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Unary Op</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_OP_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Unary Op</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_OP_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.NotImpl <em>Not</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.NotImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getNot()
	 * @generated
	 */
	int NOT = 36;

	/**
	 * The feature id for the '<em><b>Child Expr</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOT__CHILD_EXPR = UNARY_OP__CHILD_EXPR;

	/**
	 * The number of structural features of the '<em>Not</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOT_FEATURE_COUNT = UNARY_OP_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Not</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOT_OPERATION_COUNT = UNARY_OP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.BuiltInFunctionCallImpl <em>Built In Function Call</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.BuiltInFunctionCallImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getBuiltInFunctionCall()
	 * @generated
	 */
	int BUILT_IN_FUNCTION_CALL = 37;

	/**
	 * The number of structural features of the '<em>Built In Function Call</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_FUNCTION_CALL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Built In Function Call</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_FUNCTION_CALL_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.ContainsImpl <em>Contains</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.ContainsImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getContains()
	 * @generated
	 */
	int CONTAINS = 38;

	/**
	 * The feature id for the '<em><b>Search</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINS__SEARCH = BUILT_IN_FUNCTION_CALL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Item</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINS__ITEM = BUILT_IN_FUNCTION_CALL_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Contains</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINS_FEATURE_COUNT = BUILT_IN_FUNCTION_CALL_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Contains</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINS_OPERATION_COUNT = BUILT_IN_FUNCTION_CALL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.StartsWithImpl <em>Starts With</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.StartsWithImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getStartsWith()
	 * @generated
	 */
	int STARTS_WITH = 39;

	/**
	 * The feature id for the '<em><b>Search String</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARTS_WITH__SEARCH_STRING = BUILT_IN_FUNCTION_CALL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Search Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARTS_WITH__SEARCH_VALUE = BUILT_IN_FUNCTION_CALL_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Starts With</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARTS_WITH_FEATURE_COUNT = BUILT_IN_FUNCTION_CALL_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Starts With</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STARTS_WITH_OPERATION_COUNT = BUILT_IN_FUNCTION_CALL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.EndsWithImpl <em>Ends With</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.EndsWithImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getEndsWith()
	 * @generated
	 */
	int ENDS_WITH = 40;

	/**
	 * The feature id for the '<em><b>Search String</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENDS_WITH__SEARCH_STRING = BUILT_IN_FUNCTION_CALL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Search Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENDS_WITH__SEARCH_VALUE = BUILT_IN_FUNCTION_CALL_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Ends With</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENDS_WITH_FEATURE_COUNT = BUILT_IN_FUNCTION_CALL_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Ends With</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENDS_WITH_OPERATION_COUNT = BUILT_IN_FUNCTION_CALL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.FormatImpl <em>Format</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.FormatImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getFormat()
	 * @generated
	 */
	int FORMAT = 41;

	/**
	 * The feature id for the '<em><b>String</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAT__STRING = BUILT_IN_FUNCTION_CALL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Replace Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAT__REPLACE_VALUES = BUILT_IN_FUNCTION_CALL_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Format</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAT_FEATURE_COUNT = BUILT_IN_FUNCTION_CALL_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Format</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAT_OPERATION_COUNT = BUILT_IN_FUNCTION_CALL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.JoinImpl <em>Join</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.JoinImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getJoin()
	 * @generated
	 */
	int JOIN = 42;

	/**
	 * The feature id for the '<em><b>Array</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__ARRAY = BUILT_IN_FUNCTION_CALL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Sep</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__SEP = BUILT_IN_FUNCTION_CALL_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Join</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_FEATURE_COUNT = BUILT_IN_FUNCTION_CALL_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Join</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_OPERATION_COUNT = BUILT_IN_FUNCTION_CALL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.ToJSONImpl <em>To JSON</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.ToJSONImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getToJSON()
	 * @generated
	 */
	int TO_JSON = 43;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TO_JSON__VALUE = BUILT_IN_FUNCTION_CALL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>To JSON</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TO_JSON_FEATURE_COUNT = BUILT_IN_FUNCTION_CALL_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>To JSON</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TO_JSON_OPERATION_COUNT = BUILT_IN_FUNCTION_CALL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.FromJSONImpl <em>From JSON</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.FromJSONImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getFromJSON()
	 * @generated
	 */
	int FROM_JSON = 44;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FROM_JSON__VALUE = BUILT_IN_FUNCTION_CALL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>From JSON</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FROM_JSON_FEATURE_COUNT = BUILT_IN_FUNCTION_CALL_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>From JSON</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FROM_JSON_OPERATION_COUNT = BUILT_IN_FUNCTION_CALL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.HashFilesImpl <em>Hash Files</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.HashFilesImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getHashFiles()
	 * @generated
	 */
	int HASH_FILES = 45;

	/**
	 * The feature id for the '<em><b>Path</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HASH_FILES__PATH = BUILT_IN_FUNCTION_CALL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Hash Files</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HASH_FILES_FEATURE_COUNT = BUILT_IN_FUNCTION_CALL_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Hash Files</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HASH_FILES_OPERATION_COUNT = BUILT_IN_FUNCTION_CALL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.StatusCheckImpl <em>Status Check</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.StatusCheckImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getStatusCheck()
	 * @generated
	 */
	int STATUS_CHECK = 46;

	/**
	 * The number of structural features of the '<em>Status Check</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATUS_CHECK_FEATURE_COUNT = BUILT_IN_FUNCTION_CALL_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Status Check</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATUS_CHECK_OPERATION_COUNT = BUILT_IN_FUNCTION_CALL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.AlwaysImpl <em>Always</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.AlwaysImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getAlways()
	 * @generated
	 */
	int ALWAYS = 47;

	/**
	 * The number of structural features of the '<em>Always</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALWAYS_FEATURE_COUNT = STATUS_CHECK_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Always</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALWAYS_OPERATION_COUNT = STATUS_CHECK_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.SuccessImpl <em>Success</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.SuccessImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getSuccess()
	 * @generated
	 */
	int SUCCESS = 48;

	/**
	 * The number of structural features of the '<em>Success</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUCCESS_FEATURE_COUNT = STATUS_CHECK_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Success</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUCCESS_OPERATION_COUNT = STATUS_CHECK_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.CancelledImpl <em>Cancelled</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.CancelledImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getCancelled()
	 * @generated
	 */
	int CANCELLED = 49;

	/**
	 * The number of structural features of the '<em>Cancelled</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANCELLED_FEATURE_COUNT = STATUS_CHECK_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Cancelled</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANCELLED_OPERATION_COUNT = STATUS_CHECK_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.FailureImpl <em>Failure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.FailureImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getFailure()
	 * @generated
	 */
	int FAILURE = 50;

	/**
	 * The number of structural features of the '<em>Failure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAILURE_FEATURE_COUNT = STATUS_CHECK_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Failure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAILURE_OPERATION_COUNT = STATUS_CHECK_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.ValueImpl <em>Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.ValueImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getValue()
	 * @generated
	 */
	int VALUE = 51;

	/**
	 * The number of structural features of the '<em>Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.LiteralImpl <em>Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.LiteralImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getLiteral()
	 * @generated
	 */
	int LITERAL = 52;

	/**
	 * The number of structural features of the '<em>Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL_FEATURE_COUNT = VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL_OPERATION_COUNT = VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.StringLiteralImpl <em>String Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.StringLiteralImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getStringLiteral()
	 * @generated
	 */
	int STRING_LITERAL = 53;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_LITERAL__VALUE = LITERAL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>String Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_LITERAL_FEATURE_COUNT = LITERAL_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>String Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_LITERAL_OPERATION_COUNT = LITERAL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.IntegerLiteralImpl <em>Integer Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.IntegerLiteralImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getIntegerLiteral()
	 * @generated
	 */
	int INTEGER_LITERAL = 54;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_LITERAL__VALUE = LITERAL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Integer Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_LITERAL_FEATURE_COUNT = LITERAL_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Integer Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_LITERAL_OPERATION_COUNT = LITERAL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.DoubleLiteralImpl <em>Double Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.DoubleLiteralImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getDoubleLiteral()
	 * @generated
	 */
	int DOUBLE_LITERAL = 55;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_LITERAL__VALUE = LITERAL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Double Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_LITERAL_FEATURE_COUNT = LITERAL_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Double Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_LITERAL_OPERATION_COUNT = LITERAL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.BooleanLiteralImpl <em>Boolean Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.BooleanLiteralImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getBooleanLiteral()
	 * @generated
	 */
	int BOOLEAN_LITERAL = 56;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_LITERAL__VALUE = LITERAL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Boolean Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_LITERAL_FEATURE_COUNT = LITERAL_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Boolean Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_LITERAL_OPERATION_COUNT = LITERAL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.VariableReferenceImpl <em>Variable Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.VariableReferenceImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getVariableReference()
	 * @generated
	 */
	int VARIABLE_REFERENCE = 57;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_REFERENCE__REFERENCE = VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Variable Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_REFERENCE_FEATURE_COUNT = VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Variable Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_REFERENCE_OPERATION_COUNT = VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.GitHubContextImpl <em>Git Hub Context</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.GitHubContextImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getGitHubContext()
	 * @generated
	 */
	int GIT_HUB_CONTEXT = 58;

	/**
	 * The feature id for the '<em><b>Context</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_HUB_CONTEXT__CONTEXT = VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Git Hub Context</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_HUB_CONTEXT_FEATURE_COUNT = VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Git Hub Context</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_HUB_CONTEXT_OPERATION_COUNT = VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.MatrixImpl <em>Matrix</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.MatrixImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getMatrix()
	 * @generated
	 */
	int MATRIX = 59;

	/**
	 * The feature id for the '<em><b>Axes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATRIX__AXES = 0;

	/**
	 * The feature id for the '<em><b>Includes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATRIX__INCLUDES = 1;

	/**
	 * The feature id for the '<em><b>Excludes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATRIX__EXCLUDES = 2;

	/**
	 * The feature id for the '<em><b>Fail Fast</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATRIX__FAIL_FAST = 3;

	/**
	 * The feature id for the '<em><b>Max Parallel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATRIX__MAX_PARALLEL = 4;

	/**
	 * The number of structural features of the '<em>Matrix</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATRIX_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Matrix</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATRIX_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.MatrixAxisImpl <em>Matrix Axis</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.MatrixAxisImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getMatrixAxis()
	 * @generated
	 */
	int MATRIX_AXIS = 60;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATRIX_AXIS__NAME = 0;

	/**
	 * The feature id for the '<em><b>Cells</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATRIX_AXIS__CELLS = 1;

	/**
	 * The number of structural features of the '<em>Matrix Axis</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATRIX_AXIS_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Matrix Axis</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATRIX_AXIS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.MatrixCombinationImpl <em>Matrix Combination</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.MatrixCombinationImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getMatrixCombination()
	 * @generated
	 */
	int MATRIX_COMBINATION = 61;

	/**
	 * The feature id for the '<em><b>Entries</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATRIX_COMBINATION__ENTRIES = 0;

	/**
	 * The number of structural features of the '<em>Matrix Combination</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATRIX_COMBINATION_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Matrix Combination</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATRIX_COMBINATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.AbstractStepImpl <em>Abstract Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.AbstractStepImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getAbstractStep()
	 * @generated
	 */
	int ABSTRACT_STEP = 62;

	/**
	 * The number of structural features of the '<em>Abstract Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_STEP_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Abstract Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_STEP_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.IfStepImpl <em>If Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.IfStepImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getIfStep()
	 * @generated
	 */
	int IF_STEP = 63;

	/**
	 * The feature id for the '<em><b>If Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_STEP__IF_CONDITION = ABSTRACT_STEP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Then Run</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_STEP__THEN_RUN = ABSTRACT_STEP_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>If Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_STEP_FEATURE_COUNT = ABSTRACT_STEP_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>If Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_STEP_OPERATION_COUNT = ABSTRACT_STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.StepImpl <em>Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.StepImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getStep()
	 * @generated
	 */
	int STEP = 64;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__ID = ABSTRACT_STEP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__NAME = ABSTRACT_STEP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Timeout Minutes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__TIMEOUT_MINUTES = ABSTRACT_STEP_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Continue On Error</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__CONTINUE_ON_ERROR = ABSTRACT_STEP_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Shell</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__SHELL = ABSTRACT_STEP_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Working Directory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__WORKING_DIRECTORY = ABSTRACT_STEP_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Environment Variables</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__ENVIRONMENT_VARIABLES = ABSTRACT_STEP_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_FEATURE_COUNT = ABSTRACT_STEP_FEATURE_COUNT + 7;

	/**
	 * The number of operations of the '<em>Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_OPERATION_COUNT = ABSTRACT_STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.CommandImpl <em>Command</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.CommandImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getCommand()
	 * @generated
	 */
	int COMMAND = 65;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__ID = STEP__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__NAME = STEP__NAME;

	/**
	 * The feature id for the '<em><b>Timeout Minutes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__TIMEOUT_MINUTES = STEP__TIMEOUT_MINUTES;

	/**
	 * The feature id for the '<em><b>Continue On Error</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__CONTINUE_ON_ERROR = STEP__CONTINUE_ON_ERROR;

	/**
	 * The feature id for the '<em><b>Shell</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__SHELL = STEP__SHELL;

	/**
	 * The feature id for the '<em><b>Working Directory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__WORKING_DIRECTORY = STEP__WORKING_DIRECTORY;

	/**
	 * The feature id for the '<em><b>Environment Variables</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__ENVIRONMENT_VARIABLES = STEP__ENVIRONMENT_VARIABLES;

	/**
	 * The feature id for the '<em><b>Command</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__COMMAND = STEP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Command</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND_FEATURE_COUNT = STEP_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Command</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND_OPERATION_COUNT = STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.PackageImpl <em>Package</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.PackageImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getPackage()
	 * @generated
	 */
	int PACKAGE = 66;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__ID = STEP__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__NAME = STEP__NAME;

	/**
	 * The feature id for the '<em><b>Timeout Minutes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__TIMEOUT_MINUTES = STEP__TIMEOUT_MINUTES;

	/**
	 * The feature id for the '<em><b>Continue On Error</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__CONTINUE_ON_ERROR = STEP__CONTINUE_ON_ERROR;

	/**
	 * The feature id for the '<em><b>Shell</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__SHELL = STEP__SHELL;

	/**
	 * The feature id for the '<em><b>Working Directory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__WORKING_DIRECTORY = STEP__WORKING_DIRECTORY;

	/**
	 * The feature id for the '<em><b>Environment Variables</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__ENVIRONMENT_VARIABLES = STEP__ENVIRONMENT_VARIABLES;

	/**
	 * The feature id for the '<em><b>Uses</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__USES = STEP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Args</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__ARGS = STEP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Entrypoint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__ENTRYPOINT = STEP_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Container Args</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__CONTAINER_ARGS = STEP_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_FEATURE_COUNT = STEP_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_OPERATION_COUNT = STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.ParameterImpl <em>Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.ParameterImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getParameter()
	 * @generated
	 */
	int PARAMETER = 67;

	/**
	 * The feature id for the '<em><b>Id</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__ID = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__DESCRIPTION = 1;

	/**
	 * The number of structural features of the '<em>Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.InputImpl <em>Input</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.InputImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getInput()
	 * @generated
	 */
	int INPUT = 68;

	/**
	 * The feature id for the '<em><b>Id</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT__ID = PARAMETER__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT__DESCRIPTION = PARAMETER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT__TYPE = PARAMETER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Is Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT__IS_REQUIRED = PARAMETER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Default</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT__DEFAULT = PARAMETER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Options</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT__OPTIONS = PARAMETER_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Input</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_FEATURE_COUNT = PARAMETER_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Input</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_OPERATION_COUNT = PARAMETER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.SecretImpl <em>Secret</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.SecretImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getSecret()
	 * @generated
	 */
	int SECRET = 69;

	/**
	 * The feature id for the '<em><b>Id</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECRET__ID = PARAMETER__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECRET__DESCRIPTION = PARAMETER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Is Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECRET__IS_REQUIRED = PARAMETER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Secret</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECRET_FEATURE_COUNT = PARAMETER_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Secret</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECRET_OPERATION_COUNT = PARAMETER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.impl.OutputImpl <em>Output</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.impl.OutputImpl
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getOutput()
	 * @generated
	 */
	int OUTPUT = 70;

	/**
	 * The feature id for the '<em><b>Id</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT__ID = PARAMETER__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT__DESCRIPTION = PARAMETER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT__VALUE = PARAMETER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Output</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_FEATURE_COUNT = PARAMETER_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Output</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_OPERATION_COUNT = PARAMETER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.EVENTS <em>EVENTS</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.EVENTS
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getEVENTS()
	 * @generated
	 */
	int EVENTS = 71;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.WEBHOOK_ACTIVITY_TYPES <em>WEBHOOK ACTIVITY TYPES</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.WEBHOOK_ACTIVITY_TYPES
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getWEBHOOK_ACTIVITY_TYPES()
	 * @generated
	 */
	int WEBHOOK_ACTIVITY_TYPES = 72;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.PERMISSION_SCOPES <em>PERMISSION SCOPES</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.PERMISSION_SCOPES
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getPERMISSION_SCOPES()
	 * @generated
	 */
	int PERMISSION_SCOPES = 73;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.PERMISSIONS <em>PERMISSIONS</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.PERMISSIONS
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getPERMISSIONS()
	 * @generated
	 */
	int PERMISSIONS = 74;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.EQUALITY_OPS <em>EQUALITY OPS</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.EQUALITY_OPS
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getEQUALITY_OPS()
	 * @generated
	 */
	int EQUALITY_OPS = 75;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.COMPARISON_OPS <em>COMPARISON OPS</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.COMPARISON_OPS
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getCOMPARISON_OPS()
	 * @generated
	 */
	int COMPARISON_OPS = 76;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.CONTEXTS <em>CONTEXTS</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.CONTEXTS
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getCONTEXTS()
	 * @generated
	 */
	int CONTEXTS = 77;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.github.githubMM.INPUT_TYPES <em>INPUT TYPES</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.github.githubMM.INPUT_TYPES
	 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getINPUT_TYPES()
	 * @generated
	 */
	int INPUT_TYPES = 78;

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.Workflow <em>Workflow</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Workflow</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Workflow
	 * @generated
	 */
	EClass getWorkflow();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Workflow#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Name</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Workflow#getName()
	 * @see #getWorkflow()
	 * @generated
	 */
	EReference getWorkflow_Name();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Workflow#getRunName <em>Run Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Run Name</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Workflow#getRunName()
	 * @see #getWorkflow()
	 * @generated
	 */
	EReference getWorkflow_RunName();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.github.githubMM.Workflow#getTriggers <em>Triggers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Triggers</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Workflow#getTriggers()
	 * @see #getWorkflow()
	 * @generated
	 */
	EReference getWorkflow_Triggers();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.github.githubMM.Workflow#getPermissions <em>Permissions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Permissions</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Workflow#getPermissions()
	 * @see #getWorkflow()
	 * @generated
	 */
	EReference getWorkflow_Permissions();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Workflow#getDefaults <em>Defaults</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Defaults</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Workflow#getDefaults()
	 * @see #getWorkflow()
	 * @generated
	 */
	EReference getWorkflow_Defaults();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.github.githubMM.Workflow#getEnvironmentVariables <em>Environment Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Environment Variables</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Workflow#getEnvironmentVariables()
	 * @see #getWorkflow()
	 * @generated
	 */
	EReference getWorkflow_EnvironmentVariables();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Workflow#getConcurrencyGroup <em>Concurrency Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Concurrency Group</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Workflow#getConcurrencyGroup()
	 * @see #getWorkflow()
	 * @generated
	 */
	EReference getWorkflow_ConcurrencyGroup();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.github.githubMM.Workflow#getJobs <em>Jobs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Jobs</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Workflow#getJobs()
	 * @see #getWorkflow()
	 * @generated
	 */
	EReference getWorkflow_Jobs();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.Job <em>Job</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Job</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Job
	 * @generated
	 */
	EClass getJob();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.github.githubMM.Job#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Job#getName()
	 * @see #getJob()
	 * @generated
	 */
	EAttribute getJob_Name();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Job#getJobName <em>Job Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Job Name</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Job#getJobName()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_JobName();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.github.githubMM.Job#getPermissions <em>Permissions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Permissions</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Job#getPermissions()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Permissions();

	/**
	 * Returns the meta object for the reference list '{@link com.mddoai.metamodel.github.githubMM.Job#getDependsOn <em>Depends On</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Depends On</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Job#getDependsOn()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_DependsOn();

	/**
	 * Returns the meta object for the reference list '{@link com.mddoai.metamodel.github.githubMM.Job#getNecessaryFor <em>Necessary For</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Necessary For</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Job#getNecessaryFor()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_NecessaryFor();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Job#getIfCondition <em>If Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>If Condition</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Job#getIfCondition()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_IfCondition();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Job#getAgent <em>Agent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Agent</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Job#getAgent()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Agent();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Job#getContainer <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Container</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Job#getContainer()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Container();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Job#getStagingEnvironment <em>Staging Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Staging Environment</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Job#getStagingEnvironment()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_StagingEnvironment();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Job#getDefaults <em>Defaults</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Defaults</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Job#getDefaults()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Defaults();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.github.githubMM.Job#getEnvironmentVariables <em>Environment Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Environment Variables</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Job#getEnvironmentVariables()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_EnvironmentVariables();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.github.githubMM.Job#getServices <em>Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Services</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Job#getServices()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Services();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Job#getConcurrencyGroup <em>Concurrency Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Concurrency Group</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Job#getConcurrencyGroup()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_ConcurrencyGroup();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Job#getTimeoutMinutes <em>Timeout Minutes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Timeout Minutes</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Job#getTimeoutMinutes()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_TimeoutMinutes();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Job#getContinueOnError <em>Continue On Error</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Continue On Error</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Job#getContinueOnError()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_ContinueOnError();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Job#getStrategy <em>Strategy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Strategy</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Job#getStrategy()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Strategy();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.github.githubMM.Job#getOutputs <em>Outputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Outputs</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Job#getOutputs()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Outputs();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.ScriptJob <em>Script Job</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Script Job</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.ScriptJob
	 * @generated
	 */
	EClass getScriptJob();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.github.githubMM.ScriptJob#getSteps <em>Steps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Steps</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.ScriptJob#getSteps()
	 * @see #getScriptJob()
	 * @generated
	 */
	EReference getScriptJob_Steps();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.WorkflowCallJob <em>Workflow Call Job</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Workflow Call Job</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.WorkflowCallJob
	 * @generated
	 */
	EClass getWorkflowCallJob();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.WorkflowCallJob#getUses <em>Uses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Uses</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.WorkflowCallJob#getUses()
	 * @see #getWorkflowCallJob()
	 * @generated
	 */
	EReference getWorkflowCallJob_Uses();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.github.githubMM.WorkflowCallJob#getArgs <em>Args</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Args</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.WorkflowCallJob#getArgs()
	 * @see #getWorkflowCallJob()
	 * @generated
	 */
	EReference getWorkflowCallJob_Args();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.github.githubMM.WorkflowCallJob#getSecrets <em>Secrets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Secrets</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.WorkflowCallJob#getSecrets()
	 * @see #getWorkflowCallJob()
	 * @generated
	 */
	EReference getWorkflowCallJob_Secrets();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.github.githubMM.WorkflowCallJob#getInheritSecrets <em>Inherit Secrets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Inherit Secrets</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.WorkflowCallJob#getInheritSecrets()
	 * @see #getWorkflowCallJob()
	 * @generated
	 */
	EAttribute getWorkflowCallJob_InheritSecrets();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.Agent <em>Agent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Agent</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Agent
	 * @generated
	 */
	EClass getAgent();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Agent#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Group</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Agent#getGroup()
	 * @see #getAgent()
	 * @generated
	 */
	EReference getAgent_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.github.githubMM.Agent#getLabels <em>Labels</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Labels</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Agent#getLabels()
	 * @see #getAgent()
	 * @generated
	 */
	EReference getAgent_Labels();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.Container <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Container
	 * @generated
	 */
	EClass getContainer();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Container#getImage <em>Image</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Image</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Container#getImage()
	 * @see #getContainer()
	 * @generated
	 */
	EReference getContainer_Image();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Container#getUsername <em>Username</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Username</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Container#getUsername()
	 * @see #getContainer()
	 * @generated
	 */
	EReference getContainer_Username();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Container#getPassword <em>Password</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Password</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Container#getPassword()
	 * @see #getContainer()
	 * @generated
	 */
	EReference getContainer_Password();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.github.githubMM.Container#getEnvironmentVariables <em>Environment Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Environment Variables</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Container#getEnvironmentVariables()
	 * @see #getContainer()
	 * @generated
	 */
	EReference getContainer_EnvironmentVariables();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.github.githubMM.Container#getPorts <em>Ports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Ports</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Container#getPorts()
	 * @see #getContainer()
	 * @generated
	 */
	EReference getContainer_Ports();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.github.githubMM.Container#getVolumes <em>Volumes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Volumes</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Container#getVolumes()
	 * @see #getContainer()
	 * @generated
	 */
	EReference getContainer_Volumes();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Container#getOptions <em>Options</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Options</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Container#getOptions()
	 * @see #getContainer()
	 * @generated
	 */
	EReference getContainer_Options();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Service</em>'.
	 * @see java.util.Map.Entry
	 * @model keyId="true" keyDataType="org.eclipse.emf.ecore.EString" keyRequired="true"
	 *        valueType="com.mddoai.metamodel.github.githubMM.Container" valueContainment="true" valueRequired="true"
	 * @generated
	 */
	EClass getService();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getService()
	 * @generated
	 */
	EAttribute getService_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getService()
	 * @generated
	 */
	EReference getService_Value();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.StagingEnvironment <em>Staging Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Staging Environment</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.StagingEnvironment
	 * @generated
	 */
	EClass getStagingEnvironment();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.StagingEnvironment#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Name</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.StagingEnvironment#getName()
	 * @see #getStagingEnvironment()
	 * @generated
	 */
	EReference getStagingEnvironment_Name();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.StagingEnvironment#getUrl <em>Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Url</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.StagingEnvironment#getUrl()
	 * @see #getStagingEnvironment()
	 * @generated
	 */
	EReference getStagingEnvironment_Url();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.Trigger <em>Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Trigger</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Trigger
	 * @generated
	 */
	EClass getTrigger();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.EventTypeTrigger <em>Event Type Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event Type Trigger</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.EventTypeTrigger
	 * @generated
	 */
	EClass getEventTypeTrigger();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.github.githubMM.EventTypeTrigger#getEventTypes <em>Event Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Event Types</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.EventTypeTrigger#getEventTypes()
	 * @see #getEventTypeTrigger()
	 * @generated
	 */
	EAttribute getEventTypeTrigger_EventTypes();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.SpecifiedBranchesTrigger <em>Specified Branches Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Specified Branches Trigger</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.SpecifiedBranchesTrigger
	 * @generated
	 */
	EClass getSpecifiedBranchesTrigger();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.github.githubMM.SpecifiedBranchesTrigger#getBranches <em>Branches</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Branches</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.SpecifiedBranchesTrigger#getBranches()
	 * @see #getSpecifiedBranchesTrigger()
	 * @generated
	 */
	EReference getSpecifiedBranchesTrigger_Branches();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.github.githubMM.SpecifiedBranchesTrigger#isIgnoreSpecifiedBranches <em>Ignore Specified Branches</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ignore Specified Branches</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.SpecifiedBranchesTrigger#isIgnoreSpecifiedBranches()
	 * @see #getSpecifiedBranchesTrigger()
	 * @generated
	 */
	EAttribute getSpecifiedBranchesTrigger_IgnoreSpecifiedBranches();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.SpecifiedPathsTrigger <em>Specified Paths Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Specified Paths Trigger</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.SpecifiedPathsTrigger
	 * @generated
	 */
	EClass getSpecifiedPathsTrigger();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.github.githubMM.SpecifiedPathsTrigger#getPaths <em>Paths</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Paths</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.SpecifiedPathsTrigger#getPaths()
	 * @see #getSpecifiedPathsTrigger()
	 * @generated
	 */
	EReference getSpecifiedPathsTrigger_Paths();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.github.githubMM.SpecifiedPathsTrigger#isIgnoreSpecifiedPaths <em>Ignore Specified Paths</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ignore Specified Paths</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.SpecifiedPathsTrigger#isIgnoreSpecifiedPaths()
	 * @see #getSpecifiedPathsTrigger()
	 * @generated
	 */
	EAttribute getSpecifiedPathsTrigger_IgnoreSpecifiedPaths();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.StandardEventTrigger <em>Standard Event Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Standard Event Trigger</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.StandardEventTrigger
	 * @generated
	 */
	EClass getStandardEventTrigger();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.github.githubMM.StandardEventTrigger#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Event</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.StandardEventTrigger#getEvent()
	 * @see #getStandardEventTrigger()
	 * @generated
	 */
	EAttribute getStandardEventTrigger_Event();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.InputTrigger <em>Input Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Input Trigger</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.InputTrigger
	 * @generated
	 */
	EClass getInputTrigger();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.github.githubMM.InputTrigger#getInputs <em>Inputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Inputs</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.InputTrigger#getInputs()
	 * @see #getInputTrigger()
	 * @generated
	 */
	EReference getInputTrigger_Inputs();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.WorkflowRunTrigger <em>Workflow Run Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Workflow Run Trigger</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.WorkflowRunTrigger
	 * @generated
	 */
	EClass getWorkflowRunTrigger();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.PullRequestTrigger <em>Pull Request Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pull Request Trigger</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.PullRequestTrigger
	 * @generated
	 */
	EClass getPullRequestTrigger();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.PullRequestTargetTrigger <em>Pull Request Target Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pull Request Target Trigger</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.PullRequestTargetTrigger
	 * @generated
	 */
	EClass getPullRequestTargetTrigger();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.PushTrigger <em>Push Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Push Trigger</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.PushTrigger
	 * @generated
	 */
	EClass getPushTrigger();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.github.githubMM.PushTrigger#getTags <em>Tags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tags</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.PushTrigger#getTags()
	 * @see #getPushTrigger()
	 * @generated
	 */
	EReference getPushTrigger_Tags();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.github.githubMM.PushTrigger#isIgnoreSpecifiedTags <em>Ignore Specified Tags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ignore Specified Tags</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.PushTrigger#isIgnoreSpecifiedTags()
	 * @see #getPushTrigger()
	 * @generated
	 */
	EAttribute getPushTrigger_IgnoreSpecifiedTags();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.ScheduleTrigger <em>Schedule Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Schedule Trigger</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.ScheduleTrigger
	 * @generated
	 */
	EClass getScheduleTrigger();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.github.githubMM.ScheduleTrigger#getCrons <em>Crons</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Crons</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.ScheduleTrigger#getCrons()
	 * @see #getScheduleTrigger()
	 * @generated
	 */
	EReference getScheduleTrigger_Crons();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.WorkflowCallTrigger <em>Workflow Call Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Workflow Call Trigger</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.WorkflowCallTrigger
	 * @generated
	 */
	EClass getWorkflowCallTrigger();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.github.githubMM.WorkflowCallTrigger#getOutputs <em>Outputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Outputs</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.WorkflowCallTrigger#getOutputs()
	 * @see #getWorkflowCallTrigger()
	 * @generated
	 */
	EReference getWorkflowCallTrigger_Outputs();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.github.githubMM.WorkflowCallTrigger#getSecrets <em>Secrets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Secrets</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.WorkflowCallTrigger#getSecrets()
	 * @see #getWorkflowCallTrigger()
	 * @generated
	 */
	EReference getWorkflowCallTrigger_Secrets();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.WorkflowDispatchTrigger <em>Workflow Dispatch Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Workflow Dispatch Trigger</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.WorkflowDispatchTrigger
	 * @generated
	 */
	EClass getWorkflowDispatchTrigger();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Permission</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Permission</em>'.
	 * @see java.util.Map.Entry
	 * @model keyId="true" keyDataType="com.mddoai.metamodel.github.githubMM.PERMISSION_SCOPES" keyRequired="true"
	 *        valueDataType="com.mddoai.metamodel.github.githubMM.PERMISSIONS" valueRequired="true"
	 * @generated
	 */
	EClass getPermission();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getPermission()
	 * @generated
	 */
	EAttribute getPermission_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getPermission()
	 * @generated
	 */
	EAttribute getPermission_Value();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.ConcurrencyGroup <em>Concurrency Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Concurrency Group</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.ConcurrencyGroup
	 * @generated
	 */
	EClass getConcurrencyGroup();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.ConcurrencyGroup#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Name</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.ConcurrencyGroup#getName()
	 * @see #getConcurrencyGroup()
	 * @generated
	 */
	EReference getConcurrencyGroup_Name();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.ConcurrencyGroup#getCancelInProgress <em>Cancel In Progress</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Cancel In Progress</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.ConcurrencyGroup#getCancelInProgress()
	 * @see #getConcurrencyGroup()
	 * @generated
	 */
	EReference getConcurrencyGroup_CancelInProgress();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.Defaults <em>Defaults</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Defaults</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Defaults
	 * @generated
	 */
	EClass getDefaults();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Defaults#getShell <em>Shell</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Shell</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Defaults#getShell()
	 * @see #getDefaults()
	 * @generated
	 */
	EReference getDefaults_Shell();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Defaults#getWorkingDirectory <em>Working Directory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Working Directory</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Defaults#getWorkingDirectory()
	 * @see #getDefaults()
	 * @generated
	 */
	EReference getDefaults_WorkingDirectory();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.Expression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expression</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Expression
	 * @generated
	 */
	EClass getExpression();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.VariableDeclaration <em>Variable Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable Declaration</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.VariableDeclaration
	 * @generated
	 */
	EClass getVariableDeclaration();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.github.githubMM.VariableDeclaration#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.VariableDeclaration#getName()
	 * @see #getVariableDeclaration()
	 * @generated
	 */
	EAttribute getVariableDeclaration_Name();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Variable Assignment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable Assignment</em>'.
	 * @see java.util.Map.Entry
	 * @model keyType="com.mddoai.metamodel.github.githubMM.VariableDeclaration" keyContainment="true" keyRequired="true"
	 *        valueType="com.mddoai.metamodel.github.githubMM.Expression" valueContainment="true" valueRequired="true"
	 * @generated
	 */
	EClass getVariableAssignment();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getVariableAssignment()
	 * @generated
	 */
	EReference getVariableAssignment_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getVariableAssignment()
	 * @generated
	 */
	EReference getVariableAssignment_Value();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.BinaryOp <em>Binary Op</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Binary Op</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.BinaryOp
	 * @generated
	 */
	EClass getBinaryOp();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.BinaryOp#getLhs <em>Lhs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Lhs</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.BinaryOp#getLhs()
	 * @see #getBinaryOp()
	 * @generated
	 */
	EReference getBinaryOp_Lhs();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.BinaryOp#getRhs <em>Rhs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Rhs</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.BinaryOp#getRhs()
	 * @see #getBinaryOp()
	 * @generated
	 */
	EReference getBinaryOp_Rhs();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.Concat <em>Concat</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Concat</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Concat
	 * @generated
	 */
	EClass getConcat();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.github.githubMM.Concat#getExpressions <em>Expressions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Expressions</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Concat#getExpressions()
	 * @see #getConcat()
	 * @generated
	 */
	EReference getConcat_Expressions();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.DotOp <em>Dot Op</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dot Op</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.DotOp
	 * @generated
	 */
	EClass getDotOp();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.Equality <em>Equality</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Equality</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Equality
	 * @generated
	 */
	EClass getEquality();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.github.githubMM.Equality#getOp <em>Op</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Op</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Equality#getOp()
	 * @see #getEquality()
	 * @generated
	 */
	EAttribute getEquality_Op();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.Comparison <em>Comparison</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Comparison</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Comparison
	 * @generated
	 */
	EClass getComparison();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.github.githubMM.Comparison#getOp <em>Op</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Op</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Comparison#getOp()
	 * @see #getComparison()
	 * @generated
	 */
	EAttribute getComparison_Op();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.LogicalOp <em>Logical Op</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Logical Op</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.LogicalOp
	 * @generated
	 */
	EClass getLogicalOp();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.Or <em>Or</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Or</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Or
	 * @generated
	 */
	EClass getOr();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.And <em>And</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>And</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.And
	 * @generated
	 */
	EClass getAnd();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.UnaryOp <em>Unary Op</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unary Op</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.UnaryOp
	 * @generated
	 */
	EClass getUnaryOp();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.UnaryOp#getChildExpr <em>Child Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Child Expr</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.UnaryOp#getChildExpr()
	 * @see #getUnaryOp()
	 * @generated
	 */
	EReference getUnaryOp_ChildExpr();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.Not <em>Not</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Not</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Not
	 * @generated
	 */
	EClass getNot();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.BuiltInFunctionCall <em>Built In Function Call</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Built In Function Call</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.BuiltInFunctionCall
	 * @generated
	 */
	EClass getBuiltInFunctionCall();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.Contains <em>Contains</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Contains</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Contains
	 * @generated
	 */
	EClass getContains();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Contains#getSearch <em>Search</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Search</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Contains#getSearch()
	 * @see #getContains()
	 * @generated
	 */
	EReference getContains_Search();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Contains#getItem <em>Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Item</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Contains#getItem()
	 * @see #getContains()
	 * @generated
	 */
	EReference getContains_Item();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.StartsWith <em>Starts With</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Starts With</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.StartsWith
	 * @generated
	 */
	EClass getStartsWith();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.StartsWith#getSearchString <em>Search String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Search String</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.StartsWith#getSearchString()
	 * @see #getStartsWith()
	 * @generated
	 */
	EReference getStartsWith_SearchString();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.StartsWith#getSearchValue <em>Search Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Search Value</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.StartsWith#getSearchValue()
	 * @see #getStartsWith()
	 * @generated
	 */
	EReference getStartsWith_SearchValue();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.EndsWith <em>Ends With</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ends With</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.EndsWith
	 * @generated
	 */
	EClass getEndsWith();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.EndsWith#getSearchString <em>Search String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Search String</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.EndsWith#getSearchString()
	 * @see #getEndsWith()
	 * @generated
	 */
	EReference getEndsWith_SearchString();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.EndsWith#getSearchValue <em>Search Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Search Value</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.EndsWith#getSearchValue()
	 * @see #getEndsWith()
	 * @generated
	 */
	EReference getEndsWith_SearchValue();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.Format <em>Format</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Format</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Format
	 * @generated
	 */
	EClass getFormat();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Format#getString <em>String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>String</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Format#getString()
	 * @see #getFormat()
	 * @generated
	 */
	EReference getFormat_String();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.github.githubMM.Format#getReplaceValues <em>Replace Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Replace Values</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Format#getReplaceValues()
	 * @see #getFormat()
	 * @generated
	 */
	EReference getFormat_ReplaceValues();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.Join <em>Join</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Join</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Join
	 * @generated
	 */
	EClass getJoin();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Join#getArray <em>Array</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Array</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Join#getArray()
	 * @see #getJoin()
	 * @generated
	 */
	EReference getJoin_Array();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Join#getSep <em>Sep</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Sep</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Join#getSep()
	 * @see #getJoin()
	 * @generated
	 */
	EReference getJoin_Sep();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.ToJSON <em>To JSON</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>To JSON</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.ToJSON
	 * @generated
	 */
	EClass getToJSON();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.ToJSON#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.ToJSON#getValue()
	 * @see #getToJSON()
	 * @generated
	 */
	EReference getToJSON_Value();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.FromJSON <em>From JSON</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>From JSON</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.FromJSON
	 * @generated
	 */
	EClass getFromJSON();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.FromJSON#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.FromJSON#getValue()
	 * @see #getFromJSON()
	 * @generated
	 */
	EReference getFromJSON_Value();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.HashFiles <em>Hash Files</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Hash Files</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.HashFiles
	 * @generated
	 */
	EClass getHashFiles();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.HashFiles#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Path</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.HashFiles#getPath()
	 * @see #getHashFiles()
	 * @generated
	 */
	EReference getHashFiles_Path();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.StatusCheck <em>Status Check</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Status Check</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.StatusCheck
	 * @generated
	 */
	EClass getStatusCheck();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.Always <em>Always</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Always</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Always
	 * @generated
	 */
	EClass getAlways();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.Success <em>Success</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Success</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Success
	 * @generated
	 */
	EClass getSuccess();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.Cancelled <em>Cancelled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cancelled</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Cancelled
	 * @generated
	 */
	EClass getCancelled();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.Failure <em>Failure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Failure</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Failure
	 * @generated
	 */
	EClass getFailure();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.Value <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Value
	 * @generated
	 */
	EClass getValue();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.Literal <em>Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Literal</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Literal
	 * @generated
	 */
	EClass getLiteral();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.StringLiteral <em>String Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Literal</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.StringLiteral
	 * @generated
	 */
	EClass getStringLiteral();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.github.githubMM.StringLiteral#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.StringLiteral#getValue()
	 * @see #getStringLiteral()
	 * @generated
	 */
	EAttribute getStringLiteral_Value();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.IntegerLiteral <em>Integer Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Integer Literal</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.IntegerLiteral
	 * @generated
	 */
	EClass getIntegerLiteral();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.github.githubMM.IntegerLiteral#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.IntegerLiteral#getValue()
	 * @see #getIntegerLiteral()
	 * @generated
	 */
	EAttribute getIntegerLiteral_Value();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.DoubleLiteral <em>Double Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Double Literal</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.DoubleLiteral
	 * @generated
	 */
	EClass getDoubleLiteral();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.github.githubMM.DoubleLiteral#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.DoubleLiteral#getValue()
	 * @see #getDoubleLiteral()
	 * @generated
	 */
	EAttribute getDoubleLiteral_Value();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.BooleanLiteral <em>Boolean Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Literal</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.BooleanLiteral
	 * @generated
	 */
	EClass getBooleanLiteral();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.github.githubMM.BooleanLiteral#isValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.BooleanLiteral#isValue()
	 * @see #getBooleanLiteral()
	 * @generated
	 */
	EAttribute getBooleanLiteral_Value();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.VariableReference <em>Variable Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable Reference</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.VariableReference
	 * @generated
	 */
	EClass getVariableReference();

	/**
	 * Returns the meta object for the reference '{@link com.mddoai.metamodel.github.githubMM.VariableReference#getReference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Reference</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.VariableReference#getReference()
	 * @see #getVariableReference()
	 * @generated
	 */
	EReference getVariableReference_Reference();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.GitHubContext <em>Git Hub Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Git Hub Context</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.GitHubContext
	 * @generated
	 */
	EClass getGitHubContext();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.github.githubMM.GitHubContext#getContext <em>Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Context</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.GitHubContext#getContext()
	 * @see #getGitHubContext()
	 * @generated
	 */
	EAttribute getGitHubContext_Context();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.Matrix <em>Matrix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Matrix</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Matrix
	 * @generated
	 */
	EClass getMatrix();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.github.githubMM.Matrix#getAxes <em>Axes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Axes</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Matrix#getAxes()
	 * @see #getMatrix()
	 * @generated
	 */
	EReference getMatrix_Axes();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.github.githubMM.Matrix#getIncludes <em>Includes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Includes</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Matrix#getIncludes()
	 * @see #getMatrix()
	 * @generated
	 */
	EReference getMatrix_Includes();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.github.githubMM.Matrix#getExcludes <em>Excludes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Excludes</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Matrix#getExcludes()
	 * @see #getMatrix()
	 * @generated
	 */
	EReference getMatrix_Excludes();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Matrix#getFailFast <em>Fail Fast</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Fail Fast</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Matrix#getFailFast()
	 * @see #getMatrix()
	 * @generated
	 */
	EReference getMatrix_FailFast();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Matrix#getMaxParallel <em>Max Parallel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Max Parallel</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Matrix#getMaxParallel()
	 * @see #getMatrix()
	 * @generated
	 */
	EReference getMatrix_MaxParallel();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.MatrixAxis <em>Matrix Axis</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Matrix Axis</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.MatrixAxis
	 * @generated
	 */
	EClass getMatrixAxis();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.MatrixAxis#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Name</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.MatrixAxis#getName()
	 * @see #getMatrixAxis()
	 * @generated
	 */
	EReference getMatrixAxis_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.github.githubMM.MatrixAxis#getCells <em>Cells</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Cells</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.MatrixAxis#getCells()
	 * @see #getMatrixAxis()
	 * @generated
	 */
	EReference getMatrixAxis_Cells();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.MatrixCombination <em>Matrix Combination</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Matrix Combination</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.MatrixCombination
	 * @generated
	 */
	EClass getMatrixCombination();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.github.githubMM.MatrixCombination#getEntries <em>Entries</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Entries</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.MatrixCombination#getEntries()
	 * @see #getMatrixCombination()
	 * @generated
	 */
	EReference getMatrixCombination_Entries();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.AbstractStep <em>Abstract Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Step</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.AbstractStep
	 * @generated
	 */
	EClass getAbstractStep();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.IfStep <em>If Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>If Step</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.IfStep
	 * @generated
	 */
	EClass getIfStep();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.IfStep#getIfCondition <em>If Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>If Condition</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.IfStep#getIfCondition()
	 * @see #getIfStep()
	 * @generated
	 */
	EReference getIfStep_IfCondition();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.IfStep#getThenRun <em>Then Run</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Then Run</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.IfStep#getThenRun()
	 * @see #getIfStep()
	 * @generated
	 */
	EReference getIfStep_ThenRun();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.Step <em>Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Step</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Step
	 * @generated
	 */
	EClass getStep();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.github.githubMM.Step#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Step#getId()
	 * @see #getStep()
	 * @generated
	 */
	EAttribute getStep_Id();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Step#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Name</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Step#getName()
	 * @see #getStep()
	 * @generated
	 */
	EReference getStep_Name();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Step#getTimeoutMinutes <em>Timeout Minutes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Timeout Minutes</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Step#getTimeoutMinutes()
	 * @see #getStep()
	 * @generated
	 */
	EReference getStep_TimeoutMinutes();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Step#getContinueOnError <em>Continue On Error</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Continue On Error</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Step#getContinueOnError()
	 * @see #getStep()
	 * @generated
	 */
	EReference getStep_ContinueOnError();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Step#getShell <em>Shell</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Shell</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Step#getShell()
	 * @see #getStep()
	 * @generated
	 */
	EReference getStep_Shell();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Step#getWorkingDirectory <em>Working Directory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Working Directory</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Step#getWorkingDirectory()
	 * @see #getStep()
	 * @generated
	 */
	EReference getStep_WorkingDirectory();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.github.githubMM.Step#getEnvironmentVariables <em>Environment Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Environment Variables</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Step#getEnvironmentVariables()
	 * @see #getStep()
	 * @generated
	 */
	EReference getStep_EnvironmentVariables();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.Command <em>Command</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Command</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Command
	 * @generated
	 */
	EClass getCommand();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Command#getCommand <em>Command</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Command</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Command#getCommand()
	 * @see #getCommand()
	 * @generated
	 */
	EReference getCommand_Command();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.Package <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Package</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Package
	 * @generated
	 */
	EClass getPackage();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Package#getUses <em>Uses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Uses</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Package#getUses()
	 * @see #getPackage()
	 * @generated
	 */
	EReference getPackage_Uses();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.github.githubMM.Package#getArgs <em>Args</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Args</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Package#getArgs()
	 * @see #getPackage()
	 * @generated
	 */
	EReference getPackage_Args();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Package#getEntrypoint <em>Entrypoint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Entrypoint</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Package#getEntrypoint()
	 * @see #getPackage()
	 * @generated
	 */
	EReference getPackage_Entrypoint();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Package#getContainerArgs <em>Container Args</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Container Args</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Package#getContainerArgs()
	 * @see #getPackage()
	 * @generated
	 */
	EReference getPackage_ContainerArgs();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.Parameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Parameter
	 * @generated
	 */
	EClass getParameter();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Parameter#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Id</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Parameter#getId()
	 * @see #getParameter()
	 * @generated
	 */
	EReference getParameter_Id();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Parameter#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Description</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Parameter#getDescription()
	 * @see #getParameter()
	 * @generated
	 */
	EReference getParameter_Description();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.Input <em>Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Input</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Input
	 * @generated
	 */
	EClass getInput();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.github.githubMM.Input#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Input#getType()
	 * @see #getInput()
	 * @generated
	 */
	EAttribute getInput_Type();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.github.githubMM.Input#getIsRequired <em>Is Required</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Required</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Input#getIsRequired()
	 * @see #getInput()
	 * @generated
	 */
	EAttribute getInput_IsRequired();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Input#getDefault <em>Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Default</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Input#getDefault()
	 * @see #getInput()
	 * @generated
	 */
	EReference getInput_Default();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.github.githubMM.Input#getOptions <em>Options</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Options</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Input#getOptions()
	 * @see #getInput()
	 * @generated
	 */
	EAttribute getInput_Options();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.Secret <em>Secret</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Secret</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Secret
	 * @generated
	 */
	EClass getSecret();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.github.githubMM.Secret#getIsRequired <em>Is Required</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Required</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Secret#getIsRequired()
	 * @see #getSecret()
	 * @generated
	 */
	EAttribute getSecret_IsRequired();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.github.githubMM.Output <em>Output</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Output</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Output
	 * @generated
	 */
	EClass getOutput();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.github.githubMM.Output#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.Output#getValue()
	 * @see #getOutput()
	 * @generated
	 */
	EReference getOutput_Value();

	/**
	 * Returns the meta object for enum '{@link com.mddoai.metamodel.github.githubMM.EVENTS <em>EVENTS</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>EVENTS</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.EVENTS
	 * @generated
	 */
	EEnum getEVENTS();

	/**
	 * Returns the meta object for enum '{@link com.mddoai.metamodel.github.githubMM.WEBHOOK_ACTIVITY_TYPES <em>WEBHOOK ACTIVITY TYPES</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>WEBHOOK ACTIVITY TYPES</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.WEBHOOK_ACTIVITY_TYPES
	 * @generated
	 */
	EEnum getWEBHOOK_ACTIVITY_TYPES();

	/**
	 * Returns the meta object for enum '{@link com.mddoai.metamodel.github.githubMM.PERMISSION_SCOPES <em>PERMISSION SCOPES</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>PERMISSION SCOPES</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.PERMISSION_SCOPES
	 * @generated
	 */
	EEnum getPERMISSION_SCOPES();

	/**
	 * Returns the meta object for enum '{@link com.mddoai.metamodel.github.githubMM.PERMISSIONS <em>PERMISSIONS</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>PERMISSIONS</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.PERMISSIONS
	 * @generated
	 */
	EEnum getPERMISSIONS();

	/**
	 * Returns the meta object for enum '{@link com.mddoai.metamodel.github.githubMM.EQUALITY_OPS <em>EQUALITY OPS</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>EQUALITY OPS</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.EQUALITY_OPS
	 * @generated
	 */
	EEnum getEQUALITY_OPS();

	/**
	 * Returns the meta object for enum '{@link com.mddoai.metamodel.github.githubMM.COMPARISON_OPS <em>COMPARISON OPS</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>COMPARISON OPS</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.COMPARISON_OPS
	 * @generated
	 */
	EEnum getCOMPARISON_OPS();

	/**
	 * Returns the meta object for enum '{@link com.mddoai.metamodel.github.githubMM.CONTEXTS <em>CONTEXTS</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>CONTEXTS</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.CONTEXTS
	 * @generated
	 */
	EEnum getCONTEXTS();

	/**
	 * Returns the meta object for enum '{@link com.mddoai.metamodel.github.githubMM.INPUT_TYPES <em>INPUT TYPES</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>INPUT TYPES</em>'.
	 * @see com.mddoai.metamodel.github.githubMM.INPUT_TYPES
	 * @generated
	 */
	EEnum getINPUT_TYPES();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	GithubMMFactory getGithubMMFactory();

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
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.WorkflowImpl <em>Workflow</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.WorkflowImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getWorkflow()
		 * @generated
		 */
		EClass WORKFLOW = eINSTANCE.getWorkflow();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKFLOW__NAME = eINSTANCE.getWorkflow_Name();

		/**
		 * The meta object literal for the '<em><b>Run Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKFLOW__RUN_NAME = eINSTANCE.getWorkflow_RunName();

		/**
		 * The meta object literal for the '<em><b>Triggers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKFLOW__TRIGGERS = eINSTANCE.getWorkflow_Triggers();

		/**
		 * The meta object literal for the '<em><b>Permissions</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKFLOW__PERMISSIONS = eINSTANCE.getWorkflow_Permissions();

		/**
		 * The meta object literal for the '<em><b>Defaults</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKFLOW__DEFAULTS = eINSTANCE.getWorkflow_Defaults();

		/**
		 * The meta object literal for the '<em><b>Environment Variables</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKFLOW__ENVIRONMENT_VARIABLES = eINSTANCE.getWorkflow_EnvironmentVariables();

		/**
		 * The meta object literal for the '<em><b>Concurrency Group</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKFLOW__CONCURRENCY_GROUP = eINSTANCE.getWorkflow_ConcurrencyGroup();

		/**
		 * The meta object literal for the '<em><b>Jobs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKFLOW__JOBS = eINSTANCE.getWorkflow_Jobs();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.JobImpl <em>Job</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.JobImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getJob()
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
		 * The meta object literal for the '<em><b>Job Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__JOB_NAME = eINSTANCE.getJob_JobName();

		/**
		 * The meta object literal for the '<em><b>Permissions</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__PERMISSIONS = eINSTANCE.getJob_Permissions();

		/**
		 * The meta object literal for the '<em><b>Depends On</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__DEPENDS_ON = eINSTANCE.getJob_DependsOn();

		/**
		 * The meta object literal for the '<em><b>Necessary For</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__NECESSARY_FOR = eINSTANCE.getJob_NecessaryFor();

		/**
		 * The meta object literal for the '<em><b>If Condition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__IF_CONDITION = eINSTANCE.getJob_IfCondition();

		/**
		 * The meta object literal for the '<em><b>Agent</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__AGENT = eINSTANCE.getJob_Agent();

		/**
		 * The meta object literal for the '<em><b>Container</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__CONTAINER = eINSTANCE.getJob_Container();

		/**
		 * The meta object literal for the '<em><b>Staging Environment</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__STAGING_ENVIRONMENT = eINSTANCE.getJob_StagingEnvironment();

		/**
		 * The meta object literal for the '<em><b>Defaults</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__DEFAULTS = eINSTANCE.getJob_Defaults();

		/**
		 * The meta object literal for the '<em><b>Environment Variables</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__ENVIRONMENT_VARIABLES = eINSTANCE.getJob_EnvironmentVariables();

		/**
		 * The meta object literal for the '<em><b>Services</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__SERVICES = eINSTANCE.getJob_Services();

		/**
		 * The meta object literal for the '<em><b>Concurrency Group</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__CONCURRENCY_GROUP = eINSTANCE.getJob_ConcurrencyGroup();

		/**
		 * The meta object literal for the '<em><b>Timeout Minutes</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__TIMEOUT_MINUTES = eINSTANCE.getJob_TimeoutMinutes();

		/**
		 * The meta object literal for the '<em><b>Continue On Error</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__CONTINUE_ON_ERROR = eINSTANCE.getJob_ContinueOnError();

		/**
		 * The meta object literal for the '<em><b>Strategy</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__STRATEGY = eINSTANCE.getJob_Strategy();

		/**
		 * The meta object literal for the '<em><b>Outputs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__OUTPUTS = eINSTANCE.getJob_Outputs();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.ScriptJobImpl <em>Script Job</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.ScriptJobImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getScriptJob()
		 * @generated
		 */
		EClass SCRIPT_JOB = eINSTANCE.getScriptJob();

		/**
		 * The meta object literal for the '<em><b>Steps</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCRIPT_JOB__STEPS = eINSTANCE.getScriptJob_Steps();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.WorkflowCallJobImpl <em>Workflow Call Job</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.WorkflowCallJobImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getWorkflowCallJob()
		 * @generated
		 */
		EClass WORKFLOW_CALL_JOB = eINSTANCE.getWorkflowCallJob();

		/**
		 * The meta object literal for the '<em><b>Uses</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKFLOW_CALL_JOB__USES = eINSTANCE.getWorkflowCallJob_Uses();

		/**
		 * The meta object literal for the '<em><b>Args</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKFLOW_CALL_JOB__ARGS = eINSTANCE.getWorkflowCallJob_Args();

		/**
		 * The meta object literal for the '<em><b>Secrets</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKFLOW_CALL_JOB__SECRETS = eINSTANCE.getWorkflowCallJob_Secrets();

		/**
		 * The meta object literal for the '<em><b>Inherit Secrets</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORKFLOW_CALL_JOB__INHERIT_SECRETS = eINSTANCE.getWorkflowCallJob_InheritSecrets();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.AgentImpl <em>Agent</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.AgentImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getAgent()
		 * @generated
		 */
		EClass AGENT = eINSTANCE.getAgent();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AGENT__GROUP = eINSTANCE.getAgent_Group();

		/**
		 * The meta object literal for the '<em><b>Labels</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AGENT__LABELS = eINSTANCE.getAgent_Labels();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.ContainerImpl <em>Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.ContainerImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getContainer()
		 * @generated
		 */
		EClass CONTAINER = eINSTANCE.getContainer();

		/**
		 * The meta object literal for the '<em><b>Image</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER__IMAGE = eINSTANCE.getContainer_Image();

		/**
		 * The meta object literal for the '<em><b>Username</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER__USERNAME = eINSTANCE.getContainer_Username();

		/**
		 * The meta object literal for the '<em><b>Password</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER__PASSWORD = eINSTANCE.getContainer_Password();

		/**
		 * The meta object literal for the '<em><b>Environment Variables</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER__ENVIRONMENT_VARIABLES = eINSTANCE.getContainer_EnvironmentVariables();

		/**
		 * The meta object literal for the '<em><b>Ports</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER__PORTS = eINSTANCE.getContainer_Ports();

		/**
		 * The meta object literal for the '<em><b>Volumes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER__VOLUMES = eINSTANCE.getContainer_Volumes();

		/**
		 * The meta object literal for the '<em><b>Options</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER__OPTIONS = eINSTANCE.getContainer_Options();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.ServiceImpl <em>Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.ServiceImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getService()
		 * @generated
		 */
		EClass SERVICE = eINSTANCE.getService();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVICE__KEY = eINSTANCE.getService_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVICE__VALUE = eINSTANCE.getService_Value();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.StagingEnvironmentImpl <em>Staging Environment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.StagingEnvironmentImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getStagingEnvironment()
		 * @generated
		 */
		EClass STAGING_ENVIRONMENT = eINSTANCE.getStagingEnvironment();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STAGING_ENVIRONMENT__NAME = eINSTANCE.getStagingEnvironment_Name();

		/**
		 * The meta object literal for the '<em><b>Url</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STAGING_ENVIRONMENT__URL = eINSTANCE.getStagingEnvironment_Url();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.TriggerImpl <em>Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.TriggerImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getTrigger()
		 * @generated
		 */
		EClass TRIGGER = eINSTANCE.getTrigger();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.EventTypeTriggerImpl <em>Event Type Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.EventTypeTriggerImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getEventTypeTrigger()
		 * @generated
		 */
		EClass EVENT_TYPE_TRIGGER = eINSTANCE.getEventTypeTrigger();

		/**
		 * The meta object literal for the '<em><b>Event Types</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_TYPE_TRIGGER__EVENT_TYPES = eINSTANCE.getEventTypeTrigger_EventTypes();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.SpecifiedBranchesTriggerImpl <em>Specified Branches Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.SpecifiedBranchesTriggerImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getSpecifiedBranchesTrigger()
		 * @generated
		 */
		EClass SPECIFIED_BRANCHES_TRIGGER = eINSTANCE.getSpecifiedBranchesTrigger();

		/**
		 * The meta object literal for the '<em><b>Branches</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPECIFIED_BRANCHES_TRIGGER__BRANCHES = eINSTANCE.getSpecifiedBranchesTrigger_Branches();

		/**
		 * The meta object literal for the '<em><b>Ignore Specified Branches</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPECIFIED_BRANCHES_TRIGGER__IGNORE_SPECIFIED_BRANCHES = eINSTANCE
				.getSpecifiedBranchesTrigger_IgnoreSpecifiedBranches();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.SpecifiedPathsTriggerImpl <em>Specified Paths Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.SpecifiedPathsTriggerImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getSpecifiedPathsTrigger()
		 * @generated
		 */
		EClass SPECIFIED_PATHS_TRIGGER = eINSTANCE.getSpecifiedPathsTrigger();

		/**
		 * The meta object literal for the '<em><b>Paths</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPECIFIED_PATHS_TRIGGER__PATHS = eINSTANCE.getSpecifiedPathsTrigger_Paths();

		/**
		 * The meta object literal for the '<em><b>Ignore Specified Paths</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPECIFIED_PATHS_TRIGGER__IGNORE_SPECIFIED_PATHS = eINSTANCE
				.getSpecifiedPathsTrigger_IgnoreSpecifiedPaths();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.StandardEventTriggerImpl <em>Standard Event Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.StandardEventTriggerImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getStandardEventTrigger()
		 * @generated
		 */
		EClass STANDARD_EVENT_TRIGGER = eINSTANCE.getStandardEventTrigger();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STANDARD_EVENT_TRIGGER__EVENT = eINSTANCE.getStandardEventTrigger_Event();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.InputTriggerImpl <em>Input Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.InputTriggerImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getInputTrigger()
		 * @generated
		 */
		EClass INPUT_TRIGGER = eINSTANCE.getInputTrigger();

		/**
		 * The meta object literal for the '<em><b>Inputs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INPUT_TRIGGER__INPUTS = eINSTANCE.getInputTrigger_Inputs();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.WorkflowRunTriggerImpl <em>Workflow Run Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.WorkflowRunTriggerImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getWorkflowRunTrigger()
		 * @generated
		 */
		EClass WORKFLOW_RUN_TRIGGER = eINSTANCE.getWorkflowRunTrigger();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.PullRequestTriggerImpl <em>Pull Request Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.PullRequestTriggerImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getPullRequestTrigger()
		 * @generated
		 */
		EClass PULL_REQUEST_TRIGGER = eINSTANCE.getPullRequestTrigger();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.PullRequestTargetTriggerImpl <em>Pull Request Target Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.PullRequestTargetTriggerImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getPullRequestTargetTrigger()
		 * @generated
		 */
		EClass PULL_REQUEST_TARGET_TRIGGER = eINSTANCE.getPullRequestTargetTrigger();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.PushTriggerImpl <em>Push Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.PushTriggerImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getPushTrigger()
		 * @generated
		 */
		EClass PUSH_TRIGGER = eINSTANCE.getPushTrigger();

		/**
		 * The meta object literal for the '<em><b>Tags</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PUSH_TRIGGER__TAGS = eINSTANCE.getPushTrigger_Tags();

		/**
		 * The meta object literal for the '<em><b>Ignore Specified Tags</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PUSH_TRIGGER__IGNORE_SPECIFIED_TAGS = eINSTANCE.getPushTrigger_IgnoreSpecifiedTags();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.ScheduleTriggerImpl <em>Schedule Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.ScheduleTriggerImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getScheduleTrigger()
		 * @generated
		 */
		EClass SCHEDULE_TRIGGER = eINSTANCE.getScheduleTrigger();

		/**
		 * The meta object literal for the '<em><b>Crons</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCHEDULE_TRIGGER__CRONS = eINSTANCE.getScheduleTrigger_Crons();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.WorkflowCallTriggerImpl <em>Workflow Call Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.WorkflowCallTriggerImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getWorkflowCallTrigger()
		 * @generated
		 */
		EClass WORKFLOW_CALL_TRIGGER = eINSTANCE.getWorkflowCallTrigger();

		/**
		 * The meta object literal for the '<em><b>Outputs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKFLOW_CALL_TRIGGER__OUTPUTS = eINSTANCE.getWorkflowCallTrigger_Outputs();

		/**
		 * The meta object literal for the '<em><b>Secrets</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKFLOW_CALL_TRIGGER__SECRETS = eINSTANCE.getWorkflowCallTrigger_Secrets();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.WorkflowDispatchTriggerImpl <em>Workflow Dispatch Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.WorkflowDispatchTriggerImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getWorkflowDispatchTrigger()
		 * @generated
		 */
		EClass WORKFLOW_DISPATCH_TRIGGER = eINSTANCE.getWorkflowDispatchTrigger();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.PermissionImpl <em>Permission</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.PermissionImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getPermission()
		 * @generated
		 */
		EClass PERMISSION = eINSTANCE.getPermission();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERMISSION__KEY = eINSTANCE.getPermission_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERMISSION__VALUE = eINSTANCE.getPermission_Value();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.ConcurrencyGroupImpl <em>Concurrency Group</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.ConcurrencyGroupImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getConcurrencyGroup()
		 * @generated
		 */
		EClass CONCURRENCY_GROUP = eINSTANCE.getConcurrencyGroup();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONCURRENCY_GROUP__NAME = eINSTANCE.getConcurrencyGroup_Name();

		/**
		 * The meta object literal for the '<em><b>Cancel In Progress</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONCURRENCY_GROUP__CANCEL_IN_PROGRESS = eINSTANCE.getConcurrencyGroup_CancelInProgress();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.DefaultsImpl <em>Defaults</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.DefaultsImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getDefaults()
		 * @generated
		 */
		EClass DEFAULTS = eINSTANCE.getDefaults();

		/**
		 * The meta object literal for the '<em><b>Shell</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEFAULTS__SHELL = eINSTANCE.getDefaults_Shell();

		/**
		 * The meta object literal for the '<em><b>Working Directory</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEFAULTS__WORKING_DIRECTORY = eINSTANCE.getDefaults_WorkingDirectory();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.ExpressionImpl <em>Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.ExpressionImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getExpression()
		 * @generated
		 */
		EClass EXPRESSION = eINSTANCE.getExpression();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.VariableDeclarationImpl <em>Variable Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.VariableDeclarationImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getVariableDeclaration()
		 * @generated
		 */
		EClass VARIABLE_DECLARATION = eINSTANCE.getVariableDeclaration();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE_DECLARATION__NAME = eINSTANCE.getVariableDeclaration_Name();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.VariableAssignmentImpl <em>Variable Assignment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.VariableAssignmentImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getVariableAssignment()
		 * @generated
		 */
		EClass VARIABLE_ASSIGNMENT = eINSTANCE.getVariableAssignment();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE_ASSIGNMENT__KEY = eINSTANCE.getVariableAssignment_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE_ASSIGNMENT__VALUE = eINSTANCE.getVariableAssignment_Value();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.BinaryOpImpl <em>Binary Op</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.BinaryOpImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getBinaryOp()
		 * @generated
		 */
		EClass BINARY_OP = eINSTANCE.getBinaryOp();

		/**
		 * The meta object literal for the '<em><b>Lhs</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINARY_OP__LHS = eINSTANCE.getBinaryOp_Lhs();

		/**
		 * The meta object literal for the '<em><b>Rhs</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINARY_OP__RHS = eINSTANCE.getBinaryOp_Rhs();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.ConcatImpl <em>Concat</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.ConcatImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getConcat()
		 * @generated
		 */
		EClass CONCAT = eINSTANCE.getConcat();

		/**
		 * The meta object literal for the '<em><b>Expressions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONCAT__EXPRESSIONS = eINSTANCE.getConcat_Expressions();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.DotOpImpl <em>Dot Op</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.DotOpImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getDotOp()
		 * @generated
		 */
		EClass DOT_OP = eINSTANCE.getDotOp();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.EqualityImpl <em>Equality</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.EqualityImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getEquality()
		 * @generated
		 */
		EClass EQUALITY = eINSTANCE.getEquality();

		/**
		 * The meta object literal for the '<em><b>Op</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EQUALITY__OP = eINSTANCE.getEquality_Op();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.ComparisonImpl <em>Comparison</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.ComparisonImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getComparison()
		 * @generated
		 */
		EClass COMPARISON = eINSTANCE.getComparison();

		/**
		 * The meta object literal for the '<em><b>Op</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPARISON__OP = eINSTANCE.getComparison_Op();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.LogicalOpImpl <em>Logical Op</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.LogicalOpImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getLogicalOp()
		 * @generated
		 */
		EClass LOGICAL_OP = eINSTANCE.getLogicalOp();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.OrImpl <em>Or</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.OrImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getOr()
		 * @generated
		 */
		EClass OR = eINSTANCE.getOr();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.AndImpl <em>And</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.AndImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getAnd()
		 * @generated
		 */
		EClass AND = eINSTANCE.getAnd();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.UnaryOpImpl <em>Unary Op</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.UnaryOpImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getUnaryOp()
		 * @generated
		 */
		EClass UNARY_OP = eINSTANCE.getUnaryOp();

		/**
		 * The meta object literal for the '<em><b>Child Expr</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNARY_OP__CHILD_EXPR = eINSTANCE.getUnaryOp_ChildExpr();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.NotImpl <em>Not</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.NotImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getNot()
		 * @generated
		 */
		EClass NOT = eINSTANCE.getNot();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.BuiltInFunctionCallImpl <em>Built In Function Call</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.BuiltInFunctionCallImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getBuiltInFunctionCall()
		 * @generated
		 */
		EClass BUILT_IN_FUNCTION_CALL = eINSTANCE.getBuiltInFunctionCall();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.ContainsImpl <em>Contains</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.ContainsImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getContains()
		 * @generated
		 */
		EClass CONTAINS = eINSTANCE.getContains();

		/**
		 * The meta object literal for the '<em><b>Search</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINS__SEARCH = eINSTANCE.getContains_Search();

		/**
		 * The meta object literal for the '<em><b>Item</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINS__ITEM = eINSTANCE.getContains_Item();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.StartsWithImpl <em>Starts With</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.StartsWithImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getStartsWith()
		 * @generated
		 */
		EClass STARTS_WITH = eINSTANCE.getStartsWith();

		/**
		 * The meta object literal for the '<em><b>Search String</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STARTS_WITH__SEARCH_STRING = eINSTANCE.getStartsWith_SearchString();

		/**
		 * The meta object literal for the '<em><b>Search Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STARTS_WITH__SEARCH_VALUE = eINSTANCE.getStartsWith_SearchValue();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.EndsWithImpl <em>Ends With</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.EndsWithImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getEndsWith()
		 * @generated
		 */
		EClass ENDS_WITH = eINSTANCE.getEndsWith();

		/**
		 * The meta object literal for the '<em><b>Search String</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENDS_WITH__SEARCH_STRING = eINSTANCE.getEndsWith_SearchString();

		/**
		 * The meta object literal for the '<em><b>Search Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENDS_WITH__SEARCH_VALUE = eINSTANCE.getEndsWith_SearchValue();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.FormatImpl <em>Format</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.FormatImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getFormat()
		 * @generated
		 */
		EClass FORMAT = eINSTANCE.getFormat();

		/**
		 * The meta object literal for the '<em><b>String</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORMAT__STRING = eINSTANCE.getFormat_String();

		/**
		 * The meta object literal for the '<em><b>Replace Values</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORMAT__REPLACE_VALUES = eINSTANCE.getFormat_ReplaceValues();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.JoinImpl <em>Join</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.JoinImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getJoin()
		 * @generated
		 */
		EClass JOIN = eINSTANCE.getJoin();

		/**
		 * The meta object literal for the '<em><b>Array</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOIN__ARRAY = eINSTANCE.getJoin_Array();

		/**
		 * The meta object literal for the '<em><b>Sep</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOIN__SEP = eINSTANCE.getJoin_Sep();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.ToJSONImpl <em>To JSON</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.ToJSONImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getToJSON()
		 * @generated
		 */
		EClass TO_JSON = eINSTANCE.getToJSON();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TO_JSON__VALUE = eINSTANCE.getToJSON_Value();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.FromJSONImpl <em>From JSON</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.FromJSONImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getFromJSON()
		 * @generated
		 */
		EClass FROM_JSON = eINSTANCE.getFromJSON();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FROM_JSON__VALUE = eINSTANCE.getFromJSON_Value();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.HashFilesImpl <em>Hash Files</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.HashFilesImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getHashFiles()
		 * @generated
		 */
		EClass HASH_FILES = eINSTANCE.getHashFiles();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HASH_FILES__PATH = eINSTANCE.getHashFiles_Path();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.StatusCheckImpl <em>Status Check</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.StatusCheckImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getStatusCheck()
		 * @generated
		 */
		EClass STATUS_CHECK = eINSTANCE.getStatusCheck();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.AlwaysImpl <em>Always</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.AlwaysImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getAlways()
		 * @generated
		 */
		EClass ALWAYS = eINSTANCE.getAlways();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.SuccessImpl <em>Success</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.SuccessImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getSuccess()
		 * @generated
		 */
		EClass SUCCESS = eINSTANCE.getSuccess();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.CancelledImpl <em>Cancelled</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.CancelledImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getCancelled()
		 * @generated
		 */
		EClass CANCELLED = eINSTANCE.getCancelled();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.FailureImpl <em>Failure</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.FailureImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getFailure()
		 * @generated
		 */
		EClass FAILURE = eINSTANCE.getFailure();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.ValueImpl <em>Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.ValueImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getValue()
		 * @generated
		 */
		EClass VALUE = eINSTANCE.getValue();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.LiteralImpl <em>Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.LiteralImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getLiteral()
		 * @generated
		 */
		EClass LITERAL = eINSTANCE.getLiteral();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.StringLiteralImpl <em>String Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.StringLiteralImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getStringLiteral()
		 * @generated
		 */
		EClass STRING_LITERAL = eINSTANCE.getStringLiteral();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_LITERAL__VALUE = eINSTANCE.getStringLiteral_Value();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.IntegerLiteralImpl <em>Integer Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.IntegerLiteralImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getIntegerLiteral()
		 * @generated
		 */
		EClass INTEGER_LITERAL = eINSTANCE.getIntegerLiteral();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTEGER_LITERAL__VALUE = eINSTANCE.getIntegerLiteral_Value();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.DoubleLiteralImpl <em>Double Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.DoubleLiteralImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getDoubleLiteral()
		 * @generated
		 */
		EClass DOUBLE_LITERAL = eINSTANCE.getDoubleLiteral();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOUBLE_LITERAL__VALUE = eINSTANCE.getDoubleLiteral_Value();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.BooleanLiteralImpl <em>Boolean Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.BooleanLiteralImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getBooleanLiteral()
		 * @generated
		 */
		EClass BOOLEAN_LITERAL = eINSTANCE.getBooleanLiteral();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOLEAN_LITERAL__VALUE = eINSTANCE.getBooleanLiteral_Value();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.VariableReferenceImpl <em>Variable Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.VariableReferenceImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getVariableReference()
		 * @generated
		 */
		EClass VARIABLE_REFERENCE = eINSTANCE.getVariableReference();

		/**
		 * The meta object literal for the '<em><b>Reference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE_REFERENCE__REFERENCE = eINSTANCE.getVariableReference_Reference();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.GitHubContextImpl <em>Git Hub Context</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.GitHubContextImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getGitHubContext()
		 * @generated
		 */
		EClass GIT_HUB_CONTEXT = eINSTANCE.getGitHubContext();

		/**
		 * The meta object literal for the '<em><b>Context</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GIT_HUB_CONTEXT__CONTEXT = eINSTANCE.getGitHubContext_Context();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.MatrixImpl <em>Matrix</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.MatrixImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getMatrix()
		 * @generated
		 */
		EClass MATRIX = eINSTANCE.getMatrix();

		/**
		 * The meta object literal for the '<em><b>Axes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MATRIX__AXES = eINSTANCE.getMatrix_Axes();

		/**
		 * The meta object literal for the '<em><b>Includes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MATRIX__INCLUDES = eINSTANCE.getMatrix_Includes();

		/**
		 * The meta object literal for the '<em><b>Excludes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MATRIX__EXCLUDES = eINSTANCE.getMatrix_Excludes();

		/**
		 * The meta object literal for the '<em><b>Fail Fast</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MATRIX__FAIL_FAST = eINSTANCE.getMatrix_FailFast();

		/**
		 * The meta object literal for the '<em><b>Max Parallel</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MATRIX__MAX_PARALLEL = eINSTANCE.getMatrix_MaxParallel();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.MatrixAxisImpl <em>Matrix Axis</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.MatrixAxisImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getMatrixAxis()
		 * @generated
		 */
		EClass MATRIX_AXIS = eINSTANCE.getMatrixAxis();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MATRIX_AXIS__NAME = eINSTANCE.getMatrixAxis_Name();

		/**
		 * The meta object literal for the '<em><b>Cells</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MATRIX_AXIS__CELLS = eINSTANCE.getMatrixAxis_Cells();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.MatrixCombinationImpl <em>Matrix Combination</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.MatrixCombinationImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getMatrixCombination()
		 * @generated
		 */
		EClass MATRIX_COMBINATION = eINSTANCE.getMatrixCombination();

		/**
		 * The meta object literal for the '<em><b>Entries</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MATRIX_COMBINATION__ENTRIES = eINSTANCE.getMatrixCombination_Entries();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.AbstractStepImpl <em>Abstract Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.AbstractStepImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getAbstractStep()
		 * @generated
		 */
		EClass ABSTRACT_STEP = eINSTANCE.getAbstractStep();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.IfStepImpl <em>If Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.IfStepImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getIfStep()
		 * @generated
		 */
		EClass IF_STEP = eINSTANCE.getIfStep();

		/**
		 * The meta object literal for the '<em><b>If Condition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IF_STEP__IF_CONDITION = eINSTANCE.getIfStep_IfCondition();

		/**
		 * The meta object literal for the '<em><b>Then Run</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IF_STEP__THEN_RUN = eINSTANCE.getIfStep_ThenRun();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.StepImpl <em>Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.StepImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getStep()
		 * @generated
		 */
		EClass STEP = eINSTANCE.getStep();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STEP__ID = eINSTANCE.getStep_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STEP__NAME = eINSTANCE.getStep_Name();

		/**
		 * The meta object literal for the '<em><b>Timeout Minutes</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STEP__TIMEOUT_MINUTES = eINSTANCE.getStep_TimeoutMinutes();

		/**
		 * The meta object literal for the '<em><b>Continue On Error</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STEP__CONTINUE_ON_ERROR = eINSTANCE.getStep_ContinueOnError();

		/**
		 * The meta object literal for the '<em><b>Shell</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STEP__SHELL = eINSTANCE.getStep_Shell();

		/**
		 * The meta object literal for the '<em><b>Working Directory</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STEP__WORKING_DIRECTORY = eINSTANCE.getStep_WorkingDirectory();

		/**
		 * The meta object literal for the '<em><b>Environment Variables</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STEP__ENVIRONMENT_VARIABLES = eINSTANCE.getStep_EnvironmentVariables();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.CommandImpl <em>Command</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.CommandImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getCommand()
		 * @generated
		 */
		EClass COMMAND = eINSTANCE.getCommand();

		/**
		 * The meta object literal for the '<em><b>Command</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMMAND__COMMAND = eINSTANCE.getCommand_Command();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.PackageImpl <em>Package</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.PackageImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getPackage()
		 * @generated
		 */
		EClass PACKAGE = eINSTANCE.getPackage();

		/**
		 * The meta object literal for the '<em><b>Uses</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE__USES = eINSTANCE.getPackage_Uses();

		/**
		 * The meta object literal for the '<em><b>Args</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE__ARGS = eINSTANCE.getPackage_Args();

		/**
		 * The meta object literal for the '<em><b>Entrypoint</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE__ENTRYPOINT = eINSTANCE.getPackage_Entrypoint();

		/**
		 * The meta object literal for the '<em><b>Container Args</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE__CONTAINER_ARGS = eINSTANCE.getPackage_ContainerArgs();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.ParameterImpl <em>Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.ParameterImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getParameter()
		 * @generated
		 */
		EClass PARAMETER = eINSTANCE.getParameter();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARAMETER__ID = eINSTANCE.getParameter_Id();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARAMETER__DESCRIPTION = eINSTANCE.getParameter_Description();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.InputImpl <em>Input</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.InputImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getInput()
		 * @generated
		 */
		EClass INPUT = eINSTANCE.getInput();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INPUT__TYPE = eINSTANCE.getInput_Type();

		/**
		 * The meta object literal for the '<em><b>Is Required</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INPUT__IS_REQUIRED = eINSTANCE.getInput_IsRequired();

		/**
		 * The meta object literal for the '<em><b>Default</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INPUT__DEFAULT = eINSTANCE.getInput_Default();

		/**
		 * The meta object literal for the '<em><b>Options</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INPUT__OPTIONS = eINSTANCE.getInput_Options();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.SecretImpl <em>Secret</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.SecretImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getSecret()
		 * @generated
		 */
		EClass SECRET = eINSTANCE.getSecret();

		/**
		 * The meta object literal for the '<em><b>Is Required</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECRET__IS_REQUIRED = eINSTANCE.getSecret_IsRequired();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.impl.OutputImpl <em>Output</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.impl.OutputImpl
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getOutput()
		 * @generated
		 */
		EClass OUTPUT = eINSTANCE.getOutput();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OUTPUT__VALUE = eINSTANCE.getOutput_Value();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.EVENTS <em>EVENTS</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.EVENTS
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getEVENTS()
		 * @generated
		 */
		EEnum EVENTS = eINSTANCE.getEVENTS();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.WEBHOOK_ACTIVITY_TYPES <em>WEBHOOK ACTIVITY TYPES</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.WEBHOOK_ACTIVITY_TYPES
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getWEBHOOK_ACTIVITY_TYPES()
		 * @generated
		 */
		EEnum WEBHOOK_ACTIVITY_TYPES = eINSTANCE.getWEBHOOK_ACTIVITY_TYPES();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.PERMISSION_SCOPES <em>PERMISSION SCOPES</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.PERMISSION_SCOPES
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getPERMISSION_SCOPES()
		 * @generated
		 */
		EEnum PERMISSION_SCOPES = eINSTANCE.getPERMISSION_SCOPES();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.PERMISSIONS <em>PERMISSIONS</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.PERMISSIONS
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getPERMISSIONS()
		 * @generated
		 */
		EEnum PERMISSIONS = eINSTANCE.getPERMISSIONS();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.EQUALITY_OPS <em>EQUALITY OPS</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.EQUALITY_OPS
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getEQUALITY_OPS()
		 * @generated
		 */
		EEnum EQUALITY_OPS = eINSTANCE.getEQUALITY_OPS();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.COMPARISON_OPS <em>COMPARISON OPS</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.COMPARISON_OPS
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getCOMPARISON_OPS()
		 * @generated
		 */
		EEnum COMPARISON_OPS = eINSTANCE.getCOMPARISON_OPS();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.CONTEXTS <em>CONTEXTS</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.CONTEXTS
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getCONTEXTS()
		 * @generated
		 */
		EEnum CONTEXTS = eINSTANCE.getCONTEXTS();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.github.githubMM.INPUT_TYPES <em>INPUT TYPES</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.github.githubMM.INPUT_TYPES
		 * @see com.mddoai.metamodel.github.githubMM.impl.GithubMMPackageImpl#getINPUT_TYPES()
		 * @generated
		 */
		EEnum INPUT_TYPES = eINSTANCE.getINPUT_TYPES();

	}

} //GithubMMPackage
