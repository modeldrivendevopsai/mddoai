/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

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
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMFactory
 * @model kind="package"
 * @generated
 */
public interface AzuredevopsMMPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "azuredevopsMM";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.mddoai.com/mddoai/metamodel/azuredevops";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "azuredevopsMM";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AzuredevopsMMPackage eINSTANCE = com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl
			.init();

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineImpl <em>Pipeline</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getPipeline()
	 * @generated
	 */
	int PIPELINE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Append Commit Message To Run Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE__APPEND_COMMIT_MESSAGE_TO_RUN_NAME = 1;

	/**
	 * The feature id for the '<em><b>Lock Behavior</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE__LOCK_BEHAVIOR = 2;

	/**
	 * The feature id for the '<em><b>Trigger</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE__TRIGGER = 3;

	/**
	 * The feature id for the '<em><b>Pr</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE__PR = 4;

	/**
	 * The feature id for the '<em><b>Schedules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE__SCHEDULES = 5;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE__PARAMETERS = 6;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE__VARIABLES = 7;

	/**
	 * The feature id for the '<em><b>Pool</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE__POOL = 8;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE__RESOURCES = 9;

	/**
	 * The feature id for the '<em><b>Stages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE__STAGES = 10;

	/**
	 * The feature id for the '<em><b>Jobs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE__JOBS = 11;

	/**
	 * The feature id for the '<em><b>Steps</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE__STEPS = 12;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE__EXTENDS = 13;

	/**
	 * The feature id for the '<em><b>Workspace</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE__WORKSPACE = 14;

	/**
	 * The number of structural features of the '<em>Pipeline</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_FEATURE_COUNT = 15;

	/**
	 * The number of operations of the '<em>Pipeline</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ExtendsImpl <em>Extends</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ExtendsImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getExtends()
	 * @generated
	 */
	int EXTENDS = 1;

	/**
	 * The feature id for the '<em><b>Template</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDS__TEMPLATE = 0;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDS__PARAMETERS = 1;

	/**
	 * The number of structural features of the '<em>Extends</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDS_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Extends</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StageElementImpl <em>Stage Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StageElementImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getStageElement()
	 * @generated
	 */
	int STAGE_ELEMENT = 2;

	/**
	 * The number of structural features of the '<em>Stage Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE_ELEMENT_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Stage Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StageImpl <em>Stage</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StageImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getStage()
	 * @generated
	 */
	int STAGE = 3;

	/**
	 * The feature id for the '<em><b>Stage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE__STAGE = STAGE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE__DISPLAY_NAME = STAGE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE__CONDITION = STAGE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Lock Behavior</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE__LOCK_BEHAVIOR = STAGE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Trigger</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE__TRIGGER = STAGE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Is Skippable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE__IS_SKIPPABLE = STAGE_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Depends On</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE__DEPENDS_ON = STAGE_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Pool</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE__POOL = STAGE_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE__VARIABLES = STAGE_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Jobs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE__JOBS = STAGE_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Template Context</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE__TEMPLATE_CONTEXT = STAGE_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>Stage</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE_FEATURE_COUNT = STAGE_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The number of operations of the '<em>Stage</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE_OPERATION_COUNT = STAGE_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StageTemplateImpl <em>Stage Template</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StageTemplateImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getStageTemplate()
	 * @generated
	 */
	int STAGE_TEMPLATE = 4;

	/**
	 * The feature id for the '<em><b>Template</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE_TEMPLATE__TEMPLATE = STAGE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE_TEMPLATE__PARAMETERS = STAGE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Stage Template</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE_TEMPLATE_FEATURE_COUNT = STAGE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Stage Template</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STAGE_TEMPLATE_OPERATION_COUNT = STAGE_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobElementImpl <em>Job Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobElementImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getJobElement()
	 * @generated
	 */
	int JOB_ELEMENT = 5;

	/**
	 * The number of structural features of the '<em>Job Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB_ELEMENT_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Job Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobImpl <em>Job</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getJob()
	 * @generated
	 */
	int JOB = 6;

	/**
	 * The feature id for the '<em><b>Job</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__JOB = JOB_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__DISPLAY_NAME = JOB_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__CONDITION = JOB_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Continue On Error</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__CONTINUE_ON_ERROR = JOB_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Timeout In Minutes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__TIMEOUT_IN_MINUTES = JOB_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Cancel Timeout In Minutes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__CANCEL_TIMEOUT_IN_MINUTES = JOB_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Depends On</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__DEPENDS_ON = JOB_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Pool</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__POOL = JOB_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__VARIABLES = JOB_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Strategy</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__STRATEGY = JOB_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__CONTAINER = JOB_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Services</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__SERVICES = JOB_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Workspace</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__WORKSPACE = JOB_ELEMENT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Uses</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__USES = JOB_ELEMENT_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Steps</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__STEPS = JOB_ELEMENT_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Template Context</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__TEMPLATE_CONTEXT = JOB_ELEMENT_FEATURE_COUNT + 15;

	/**
	 * The number of structural features of the '<em>Job</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB_FEATURE_COUNT = JOB_ELEMENT_FEATURE_COUNT + 16;

	/**
	 * The number of operations of the '<em>Job</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB_OPERATION_COUNT = JOB_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DeploymentJobImpl <em>Deployment Job</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DeploymentJobImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getDeploymentJob()
	 * @generated
	 */
	int DEPLOYMENT_JOB = 7;

	/**
	 * The feature id for the '<em><b>Deployment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_JOB__DEPLOYMENT = JOB_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_JOB__DISPLAY_NAME = JOB_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_JOB__CONDITION = JOB_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Continue On Error</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_JOB__CONTINUE_ON_ERROR = JOB_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Timeout In Minutes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_JOB__TIMEOUT_IN_MINUTES = JOB_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Cancel Timeout In Minutes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_JOB__CANCEL_TIMEOUT_IN_MINUTES = JOB_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Depends On</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_JOB__DEPENDS_ON = JOB_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Pool</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_JOB__POOL = JOB_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_JOB__VARIABLES = JOB_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Environment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_JOB__ENVIRONMENT = JOB_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Strategy</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_JOB__STRATEGY = JOB_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_JOB__CONTAINER = JOB_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Services</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_JOB__SERVICES = JOB_ELEMENT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Workspace</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_JOB__WORKSPACE = JOB_ELEMENT_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Uses</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_JOB__USES = JOB_ELEMENT_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Template Context</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_JOB__TEMPLATE_CONTEXT = JOB_ELEMENT_FEATURE_COUNT + 15;

	/**
	 * The number of structural features of the '<em>Deployment Job</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_JOB_FEATURE_COUNT = JOB_ELEMENT_FEATURE_COUNT + 16;

	/**
	 * The number of operations of the '<em>Deployment Job</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_JOB_OPERATION_COUNT = JOB_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobTemplateImpl <em>Job Template</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobTemplateImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getJobTemplate()
	 * @generated
	 */
	int JOB_TEMPLATE = 8;

	/**
	 * The feature id for the '<em><b>Template</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB_TEMPLATE__TEMPLATE = JOB_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB_TEMPLATE__PARAMETERS = JOB_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Job Template</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB_TEMPLATE_FEATURE_COUNT = JOB_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Job Template</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB_TEMPLATE_OPERATION_COUNT = JOB_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.EnvironmentImpl <em>Environment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.EnvironmentImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getEnvironment()
	 * @generated
	 */
	int ENVIRONMENT = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Resource Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT__RESOURCE_NAME = 1;

	/**
	 * The feature id for the '<em><b>Resource Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT__RESOURCE_ID = 2;

	/**
	 * The feature id for the '<em><b>Resource Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT__RESOURCE_TYPE = 3;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT__TAGS = 4;

	/**
	 * The number of structural features of the '<em>Environment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Environment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DeploymentStrategyImpl <em>Deployment Strategy</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DeploymentStrategyImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getDeploymentStrategy()
	 * @generated
	 */
	int DEPLOYMENT_STRATEGY = 10;

	/**
	 * The feature id for the '<em><b>Run Once</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_STRATEGY__RUN_ONCE = 0;

	/**
	 * The feature id for the '<em><b>Rolling</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_STRATEGY__ROLLING = 1;

	/**
	 * The feature id for the '<em><b>Canary</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_STRATEGY__CANARY = 2;

	/**
	 * The number of structural features of the '<em>Deployment Strategy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_STRATEGY_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Deployment Strategy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_STRATEGY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.RunOnceStrategyImpl <em>Run Once Strategy</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.RunOnceStrategyImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getRunOnceStrategy()
	 * @generated
	 */
	int RUN_ONCE_STRATEGY = 11;

	/**
	 * The feature id for the '<em><b>Pre Deploy</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUN_ONCE_STRATEGY__PRE_DEPLOY = 0;

	/**
	 * The feature id for the '<em><b>Deploy</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUN_ONCE_STRATEGY__DEPLOY = 1;

	/**
	 * The feature id for the '<em><b>Route Traffic</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUN_ONCE_STRATEGY__ROUTE_TRAFFIC = 2;

	/**
	 * The feature id for the '<em><b>Post Route Traffic</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUN_ONCE_STRATEGY__POST_ROUTE_TRAFFIC = 3;

	/**
	 * The feature id for the '<em><b>On</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUN_ONCE_STRATEGY__ON = 4;

	/**
	 * The number of structural features of the '<em>Run Once Strategy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUN_ONCE_STRATEGY_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Run Once Strategy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUN_ONCE_STRATEGY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.RollingStrategyImpl <em>Rolling Strategy</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.RollingStrategyImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getRollingStrategy()
	 * @generated
	 */
	int ROLLING_STRATEGY = 12;

	/**
	 * The feature id for the '<em><b>Max Parallel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLLING_STRATEGY__MAX_PARALLEL = 0;

	/**
	 * The feature id for the '<em><b>Pre Deploy</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLLING_STRATEGY__PRE_DEPLOY = 1;

	/**
	 * The feature id for the '<em><b>Deploy</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLLING_STRATEGY__DEPLOY = 2;

	/**
	 * The feature id for the '<em><b>Route Traffic</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLLING_STRATEGY__ROUTE_TRAFFIC = 3;

	/**
	 * The feature id for the '<em><b>Post Route Traffic</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLLING_STRATEGY__POST_ROUTE_TRAFFIC = 4;

	/**
	 * The feature id for the '<em><b>On</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLLING_STRATEGY__ON = 5;

	/**
	 * The number of structural features of the '<em>Rolling Strategy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLLING_STRATEGY_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Rolling Strategy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLLING_STRATEGY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.CanaryStrategyImpl <em>Canary Strategy</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.CanaryStrategyImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getCanaryStrategy()
	 * @generated
	 */
	int CANARY_STRATEGY = 13;

	/**
	 * The feature id for the '<em><b>Increments</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANARY_STRATEGY__INCREMENTS = 0;

	/**
	 * The feature id for the '<em><b>Pre Deploy</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANARY_STRATEGY__PRE_DEPLOY = 1;

	/**
	 * The feature id for the '<em><b>Deploy</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANARY_STRATEGY__DEPLOY = 2;

	/**
	 * The feature id for the '<em><b>Route Traffic</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANARY_STRATEGY__ROUTE_TRAFFIC = 3;

	/**
	 * The feature id for the '<em><b>Post Route Traffic</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANARY_STRATEGY__POST_ROUTE_TRAFFIC = 4;

	/**
	 * The feature id for the '<em><b>On</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANARY_STRATEGY__ON = 5;

	/**
	 * The number of structural features of the '<em>Canary Strategy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANARY_STRATEGY_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Canary Strategy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANARY_STRATEGY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DeployHookImpl <em>Deploy Hook</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DeployHookImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getDeployHook()
	 * @generated
	 */
	int DEPLOY_HOOK = 14;

	/**
	 * The feature id for the '<em><b>Pool</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOY_HOOK__POOL = 0;

	/**
	 * The feature id for the '<em><b>Steps</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOY_HOOK__STEPS = 1;

	/**
	 * The number of structural features of the '<em>Deploy Hook</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOY_HOOK_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Deploy Hook</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOY_HOOK_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.OnSuccessOrFailureHookImpl <em>On Success Or Failure Hook</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.OnSuccessOrFailureHookImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getOnSuccessOrFailureHook()
	 * @generated
	 */
	int ON_SUCCESS_OR_FAILURE_HOOK = 15;

	/**
	 * The feature id for the '<em><b>Failure</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ON_SUCCESS_OR_FAILURE_HOOK__FAILURE = 0;

	/**
	 * The feature id for the '<em><b>Success</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ON_SUCCESS_OR_FAILURE_HOOK__SUCCESS = 1;

	/**
	 * The number of structural features of the '<em>On Success Or Failure Hook</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ON_SUCCESS_OR_FAILURE_HOOK_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>On Success Or Failure Hook</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ON_SUCCESS_OR_FAILURE_HOOK_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobStrategyImpl <em>Job Strategy</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobStrategyImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getJobStrategy()
	 * @generated
	 */
	int JOB_STRATEGY = 16;

	/**
	 * The feature id for the '<em><b>Parallel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB_STRATEGY__PARALLEL = 0;

	/**
	 * The feature id for the '<em><b>Max Parallel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB_STRATEGY__MAX_PARALLEL = 1;

	/**
	 * The feature id for the '<em><b>Matrix</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB_STRATEGY__MATRIX = 2;

	/**
	 * The number of structural features of the '<em>Job Strategy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB_STRATEGY_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Job Strategy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB_STRATEGY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.MatrixEntryImpl <em>Matrix Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.MatrixEntryImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getMatrixEntry()
	 * @generated
	 */
	int MATRIX_ENTRY = 17;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATRIX_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATRIX_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Matrix Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATRIX_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Matrix Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATRIX_ENTRY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.MatrixVariableImpl <em>Matrix Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.MatrixVariableImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getMatrixVariable()
	 * @generated
	 */
	int MATRIX_VARIABLE = 18;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATRIX_VARIABLE__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATRIX_VARIABLE__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Matrix Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATRIX_VARIABLE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Matrix Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATRIX_VARIABLE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobUsesImpl <em>Job Uses</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobUsesImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getJobUses()
	 * @generated
	 */
	int JOB_USES = 19;

	/**
	 * The feature id for the '<em><b>Repositories</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB_USES__REPOSITORIES = 0;

	/**
	 * The feature id for the '<em><b>Pools</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB_USES__POOLS = 1;

	/**
	 * The number of structural features of the '<em>Job Uses</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB_USES_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Job Uses</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB_USES_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PoolImpl <em>Pool</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PoolImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getPool()
	 * @generated
	 */
	int POOL = 20;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL__NAME = 0;

	/**
	 * The feature id for the '<em><b>Vm Image</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL__VM_IMAGE = 1;

	/**
	 * The feature id for the '<em><b>Demands</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL__DEMANDS = 2;

	/**
	 * The number of structural features of the '<em>Pool</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Pool</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.WorkspaceImpl <em>Workspace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.WorkspaceImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getWorkspace()
	 * @generated
	 */
	int WORKSPACE = 21;

	/**
	 * The feature id for the '<em><b>Clean</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKSPACE__CLEAN = 0;

	/**
	 * The number of structural features of the '<em>Workspace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKSPACE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Workspace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKSPACE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerReferenceImpl <em>Container Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerReferenceImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getContainerReference()
	 * @generated
	 */
	int CONTAINER_REFERENCE = 22;

	/**
	 * The number of structural features of the '<em>Container Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_REFERENCE_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Container Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_REFERENCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerAliasImpl <em>Container Alias</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerAliasImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getContainerAlias()
	 * @generated
	 */
	int CONTAINER_ALIAS = 23;

	/**
	 * The feature id for the '<em><b>Alias</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_ALIAS__ALIAS = CONTAINER_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Container Alias</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_ALIAS_FEATURE_COUNT = CONTAINER_REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Container Alias</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_ALIAS_OPERATION_COUNT = CONTAINER_REFERENCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerImageImpl <em>Container Image</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerImageImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getContainerImage()
	 * @generated
	 */
	int CONTAINER_IMAGE = 24;

	/**
	 * The feature id for the '<em><b>Image</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_IMAGE__IMAGE = CONTAINER_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Endpoint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_IMAGE__ENDPOINT = CONTAINER_REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Options</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_IMAGE__OPTIONS = CONTAINER_REFERENCE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Ports</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_IMAGE__PORTS = CONTAINER_REFERENCE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Volumes</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_IMAGE__VOLUMES = CONTAINER_REFERENCE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Map Docker Socket</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_IMAGE__MAP_DOCKER_SOCKET = CONTAINER_REFERENCE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Env</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_IMAGE__ENV = CONTAINER_REFERENCE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Mount Read Only</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_IMAGE__MOUNT_READ_ONLY = CONTAINER_REFERENCE_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Container Image</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_IMAGE_FEATURE_COUNT = CONTAINER_REFERENCE_FEATURE_COUNT + 8;

	/**
	 * The number of operations of the '<em>Container Image</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_IMAGE_OPERATION_COUNT = CONTAINER_REFERENCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.MountReadOnlyImpl <em>Mount Read Only</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.MountReadOnlyImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getMountReadOnly()
	 * @generated
	 */
	int MOUNT_READ_ONLY = 25;

	/**
	 * The feature id for the '<em><b>Work</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOUNT_READ_ONLY__WORK = 0;

	/**
	 * The feature id for the '<em><b>Externals</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOUNT_READ_ONLY__EXTERNALS = 1;

	/**
	 * The feature id for the '<em><b>Tools</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOUNT_READ_ONLY__TOOLS = 2;

	/**
	 * The feature id for the '<em><b>Tasks</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOUNT_READ_ONLY__TASKS = 3;

	/**
	 * The number of structural features of the '<em>Mount Read Only</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOUNT_READ_ONLY_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Mount Read Only</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOUNT_READ_ONLY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ServiceEntryImpl <em>Service Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ServiceEntryImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getServiceEntry()
	 * @generated
	 */
	int SERVICE_ENTRY = 26;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Service Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Service Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_ENTRY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StepImpl <em>Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StepImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getStep()
	 * @generated
	 */
	int STEP = 27;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__NAME = 0;

	/**
	 * The feature id for the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__DISPLAY_NAME = 1;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__CONDITION = 2;

	/**
	 * The feature id for the '<em><b>Continue On Error</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__CONTINUE_ON_ERROR = 3;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__ENABLED = 4;

	/**
	 * The feature id for the '<em><b>Timeout In Minutes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__TIMEOUT_IN_MINUTES = 5;

	/**
	 * The feature id for the '<em><b>Retry Count On Task Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__RETRY_COUNT_ON_TASK_FAILURE = 6;

	/**
	 * The feature id for the '<em><b>Env</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__ENV = 7;

	/**
	 * The feature id for the '<em><b>Target</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP__TARGET = 8;

	/**
	 * The number of structural features of the '<em>Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_FEATURE_COUNT = 9;

	/**
	 * The number of operations of the '<em>Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.TaskStepImpl <em>Task Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.TaskStepImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getTaskStep()
	 * @generated
	 */
	int TASK_STEP = 28;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_STEP__NAME = STEP__NAME;

	/**
	 * The feature id for the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_STEP__DISPLAY_NAME = STEP__DISPLAY_NAME;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_STEP__CONDITION = STEP__CONDITION;

	/**
	 * The feature id for the '<em><b>Continue On Error</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_STEP__CONTINUE_ON_ERROR = STEP__CONTINUE_ON_ERROR;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_STEP__ENABLED = STEP__ENABLED;

	/**
	 * The feature id for the '<em><b>Timeout In Minutes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_STEP__TIMEOUT_IN_MINUTES = STEP__TIMEOUT_IN_MINUTES;

	/**
	 * The feature id for the '<em><b>Retry Count On Task Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_STEP__RETRY_COUNT_ON_TASK_FAILURE = STEP__RETRY_COUNT_ON_TASK_FAILURE;

	/**
	 * The feature id for the '<em><b>Env</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_STEP__ENV = STEP__ENV;

	/**
	 * The feature id for the '<em><b>Target</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_STEP__TARGET = STEP__TARGET;

	/**
	 * The feature id for the '<em><b>Task</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_STEP__TASK = STEP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_STEP__INPUTS = STEP_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Task Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_STEP_FEATURE_COUNT = STEP_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Task Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_STEP_OPERATION_COUNT = STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ScriptStepImpl <em>Script Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ScriptStepImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getScriptStep()
	 * @generated
	 */
	int SCRIPT_STEP = 29;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_STEP__NAME = STEP__NAME;

	/**
	 * The feature id for the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_STEP__DISPLAY_NAME = STEP__DISPLAY_NAME;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_STEP__CONDITION = STEP__CONDITION;

	/**
	 * The feature id for the '<em><b>Continue On Error</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_STEP__CONTINUE_ON_ERROR = STEP__CONTINUE_ON_ERROR;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_STEP__ENABLED = STEP__ENABLED;

	/**
	 * The feature id for the '<em><b>Timeout In Minutes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_STEP__TIMEOUT_IN_MINUTES = STEP__TIMEOUT_IN_MINUTES;

	/**
	 * The feature id for the '<em><b>Retry Count On Task Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_STEP__RETRY_COUNT_ON_TASK_FAILURE = STEP__RETRY_COUNT_ON_TASK_FAILURE;

	/**
	 * The feature id for the '<em><b>Env</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_STEP__ENV = STEP__ENV;

	/**
	 * The feature id for the '<em><b>Target</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_STEP__TARGET = STEP__TARGET;

	/**
	 * The feature id for the '<em><b>Script</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_STEP__SCRIPT = STEP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Fail On Stderr</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_STEP__FAIL_ON_STDERR = STEP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Working Directory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_STEP__WORKING_DIRECTORY = STEP_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Script Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_STEP_FEATURE_COUNT = STEP_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Script Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_STEP_OPERATION_COUNT = STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.BashStepImpl <em>Bash Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.BashStepImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getBashStep()
	 * @generated
	 */
	int BASH_STEP = 30;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASH_STEP__NAME = STEP__NAME;

	/**
	 * The feature id for the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASH_STEP__DISPLAY_NAME = STEP__DISPLAY_NAME;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASH_STEP__CONDITION = STEP__CONDITION;

	/**
	 * The feature id for the '<em><b>Continue On Error</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASH_STEP__CONTINUE_ON_ERROR = STEP__CONTINUE_ON_ERROR;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASH_STEP__ENABLED = STEP__ENABLED;

	/**
	 * The feature id for the '<em><b>Timeout In Minutes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASH_STEP__TIMEOUT_IN_MINUTES = STEP__TIMEOUT_IN_MINUTES;

	/**
	 * The feature id for the '<em><b>Retry Count On Task Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASH_STEP__RETRY_COUNT_ON_TASK_FAILURE = STEP__RETRY_COUNT_ON_TASK_FAILURE;

	/**
	 * The feature id for the '<em><b>Env</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASH_STEP__ENV = STEP__ENV;

	/**
	 * The feature id for the '<em><b>Target</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASH_STEP__TARGET = STEP__TARGET;

	/**
	 * The feature id for the '<em><b>Bash</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASH_STEP__BASH = STEP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Fail On Stderr</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASH_STEP__FAIL_ON_STDERR = STEP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Working Directory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASH_STEP__WORKING_DIRECTORY = STEP_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Bash Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASH_STEP_FEATURE_COUNT = STEP_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Bash Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASH_STEP_OPERATION_COUNT = STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PowershellStepImpl <em>Powershell Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PowershellStepImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getPowershellStep()
	 * @generated
	 */
	int POWERSHELL_STEP = 31;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWERSHELL_STEP__NAME = STEP__NAME;

	/**
	 * The feature id for the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWERSHELL_STEP__DISPLAY_NAME = STEP__DISPLAY_NAME;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWERSHELL_STEP__CONDITION = STEP__CONDITION;

	/**
	 * The feature id for the '<em><b>Continue On Error</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWERSHELL_STEP__CONTINUE_ON_ERROR = STEP__CONTINUE_ON_ERROR;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWERSHELL_STEP__ENABLED = STEP__ENABLED;

	/**
	 * The feature id for the '<em><b>Timeout In Minutes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWERSHELL_STEP__TIMEOUT_IN_MINUTES = STEP__TIMEOUT_IN_MINUTES;

	/**
	 * The feature id for the '<em><b>Retry Count On Task Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWERSHELL_STEP__RETRY_COUNT_ON_TASK_FAILURE = STEP__RETRY_COUNT_ON_TASK_FAILURE;

	/**
	 * The feature id for the '<em><b>Env</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWERSHELL_STEP__ENV = STEP__ENV;

	/**
	 * The feature id for the '<em><b>Target</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWERSHELL_STEP__TARGET = STEP__TARGET;

	/**
	 * The feature id for the '<em><b>Powershell</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWERSHELL_STEP__POWERSHELL = STEP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Error Action Preference</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWERSHELL_STEP__ERROR_ACTION_PREFERENCE = STEP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Fail On Stderr</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWERSHELL_STEP__FAIL_ON_STDERR = STEP_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Ignore LASTEXITCODE</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWERSHELL_STEP__IGNORE_LASTEXITCODE = STEP_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Working Directory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWERSHELL_STEP__WORKING_DIRECTORY = STEP_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Powershell Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWERSHELL_STEP_FEATURE_COUNT = STEP_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>Powershell Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POWERSHELL_STEP_OPERATION_COUNT = STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PwshStepImpl <em>Pwsh Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PwshStepImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getPwshStep()
	 * @generated
	 */
	int PWSH_STEP = 32;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PWSH_STEP__NAME = STEP__NAME;

	/**
	 * The feature id for the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PWSH_STEP__DISPLAY_NAME = STEP__DISPLAY_NAME;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PWSH_STEP__CONDITION = STEP__CONDITION;

	/**
	 * The feature id for the '<em><b>Continue On Error</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PWSH_STEP__CONTINUE_ON_ERROR = STEP__CONTINUE_ON_ERROR;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PWSH_STEP__ENABLED = STEP__ENABLED;

	/**
	 * The feature id for the '<em><b>Timeout In Minutes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PWSH_STEP__TIMEOUT_IN_MINUTES = STEP__TIMEOUT_IN_MINUTES;

	/**
	 * The feature id for the '<em><b>Retry Count On Task Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PWSH_STEP__RETRY_COUNT_ON_TASK_FAILURE = STEP__RETRY_COUNT_ON_TASK_FAILURE;

	/**
	 * The feature id for the '<em><b>Env</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PWSH_STEP__ENV = STEP__ENV;

	/**
	 * The feature id for the '<em><b>Target</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PWSH_STEP__TARGET = STEP__TARGET;

	/**
	 * The feature id for the '<em><b>Pwsh</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PWSH_STEP__PWSH = STEP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Error Action Preference</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PWSH_STEP__ERROR_ACTION_PREFERENCE = STEP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Fail On Stderr</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PWSH_STEP__FAIL_ON_STDERR = STEP_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Ignore LASTEXITCODE</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PWSH_STEP__IGNORE_LASTEXITCODE = STEP_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Working Directory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PWSH_STEP__WORKING_DIRECTORY = STEP_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Pwsh Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PWSH_STEP_FEATURE_COUNT = STEP_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>Pwsh Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PWSH_STEP_OPERATION_COUNT = STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.CheckoutStepImpl <em>Checkout Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.CheckoutStepImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getCheckoutStep()
	 * @generated
	 */
	int CHECKOUT_STEP = 33;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT_STEP__NAME = STEP__NAME;

	/**
	 * The feature id for the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT_STEP__DISPLAY_NAME = STEP__DISPLAY_NAME;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT_STEP__CONDITION = STEP__CONDITION;

	/**
	 * The feature id for the '<em><b>Continue On Error</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT_STEP__CONTINUE_ON_ERROR = STEP__CONTINUE_ON_ERROR;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT_STEP__ENABLED = STEP__ENABLED;

	/**
	 * The feature id for the '<em><b>Timeout In Minutes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT_STEP__TIMEOUT_IN_MINUTES = STEP__TIMEOUT_IN_MINUTES;

	/**
	 * The feature id for the '<em><b>Retry Count On Task Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT_STEP__RETRY_COUNT_ON_TASK_FAILURE = STEP__RETRY_COUNT_ON_TASK_FAILURE;

	/**
	 * The feature id for the '<em><b>Env</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT_STEP__ENV = STEP__ENV;

	/**
	 * The feature id for the '<em><b>Target</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT_STEP__TARGET = STEP__TARGET;

	/**
	 * The feature id for the '<em><b>Checkout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT_STEP__CHECKOUT = STEP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Clean</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT_STEP__CLEAN = STEP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Fetch Depth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT_STEP__FETCH_DEPTH = STEP_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Fetch Filter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT_STEP__FETCH_FILTER = STEP_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Fetch Tags</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT_STEP__FETCH_TAGS = STEP_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Lfs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT_STEP__LFS = STEP_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Persist Credentials</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT_STEP__PERSIST_CREDENTIALS = STEP_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Submodules</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT_STEP__SUBMODULES = STEP_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT_STEP__PATH = STEP_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Sparse Checkout Directories</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT_STEP__SPARSE_CHECKOUT_DIRECTORIES = STEP_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Sparse Checkout Patterns</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT_STEP__SPARSE_CHECKOUT_PATTERNS = STEP_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Workspace Repo</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT_STEP__WORKSPACE_REPO = STEP_FEATURE_COUNT + 11;

	/**
	 * The number of structural features of the '<em>Checkout Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT_STEP_FEATURE_COUNT = STEP_FEATURE_COUNT + 12;

	/**
	 * The number of operations of the '<em>Checkout Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT_STEP_OPERATION_COUNT = STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DownloadStepImpl <em>Download Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DownloadStepImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getDownloadStep()
	 * @generated
	 */
	int DOWNLOAD_STEP = 34;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOWNLOAD_STEP__NAME = STEP__NAME;

	/**
	 * The feature id for the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOWNLOAD_STEP__DISPLAY_NAME = STEP__DISPLAY_NAME;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOWNLOAD_STEP__CONDITION = STEP__CONDITION;

	/**
	 * The feature id for the '<em><b>Continue On Error</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOWNLOAD_STEP__CONTINUE_ON_ERROR = STEP__CONTINUE_ON_ERROR;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOWNLOAD_STEP__ENABLED = STEP__ENABLED;

	/**
	 * The feature id for the '<em><b>Timeout In Minutes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOWNLOAD_STEP__TIMEOUT_IN_MINUTES = STEP__TIMEOUT_IN_MINUTES;

	/**
	 * The feature id for the '<em><b>Retry Count On Task Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOWNLOAD_STEP__RETRY_COUNT_ON_TASK_FAILURE = STEP__RETRY_COUNT_ON_TASK_FAILURE;

	/**
	 * The feature id for the '<em><b>Env</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOWNLOAD_STEP__ENV = STEP__ENV;

	/**
	 * The feature id for the '<em><b>Target</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOWNLOAD_STEP__TARGET = STEP__TARGET;

	/**
	 * The feature id for the '<em><b>Download</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOWNLOAD_STEP__DOWNLOAD = STEP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Artifact</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOWNLOAD_STEP__ARTIFACT = STEP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Patterns</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOWNLOAD_STEP__PATTERNS = STEP_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Download Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOWNLOAD_STEP_FEATURE_COUNT = STEP_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Download Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOWNLOAD_STEP_OPERATION_COUNT = STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DownloadBuildStepImpl <em>Download Build Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DownloadBuildStepImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getDownloadBuildStep()
	 * @generated
	 */
	int DOWNLOAD_BUILD_STEP = 35;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOWNLOAD_BUILD_STEP__NAME = STEP__NAME;

	/**
	 * The feature id for the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOWNLOAD_BUILD_STEP__DISPLAY_NAME = STEP__DISPLAY_NAME;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOWNLOAD_BUILD_STEP__CONDITION = STEP__CONDITION;

	/**
	 * The feature id for the '<em><b>Continue On Error</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOWNLOAD_BUILD_STEP__CONTINUE_ON_ERROR = STEP__CONTINUE_ON_ERROR;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOWNLOAD_BUILD_STEP__ENABLED = STEP__ENABLED;

	/**
	 * The feature id for the '<em><b>Timeout In Minutes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOWNLOAD_BUILD_STEP__TIMEOUT_IN_MINUTES = STEP__TIMEOUT_IN_MINUTES;

	/**
	 * The feature id for the '<em><b>Retry Count On Task Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOWNLOAD_BUILD_STEP__RETRY_COUNT_ON_TASK_FAILURE = STEP__RETRY_COUNT_ON_TASK_FAILURE;

	/**
	 * The feature id for the '<em><b>Env</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOWNLOAD_BUILD_STEP__ENV = STEP__ENV;

	/**
	 * The feature id for the '<em><b>Target</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOWNLOAD_BUILD_STEP__TARGET = STEP__TARGET;

	/**
	 * The feature id for the '<em><b>Download Build</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOWNLOAD_BUILD_STEP__DOWNLOAD_BUILD = STEP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Artifact</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOWNLOAD_BUILD_STEP__ARTIFACT = STEP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOWNLOAD_BUILD_STEP__PATH = STEP_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Patterns</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOWNLOAD_BUILD_STEP__PATTERNS = STEP_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Download Build Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOWNLOAD_BUILD_STEP_FEATURE_COUNT = STEP_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Download Build Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOWNLOAD_BUILD_STEP_OPERATION_COUNT = STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.GetPackageStepImpl <em>Get Package Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.GetPackageStepImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getGetPackageStep()
	 * @generated
	 */
	int GET_PACKAGE_STEP = 36;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_PACKAGE_STEP__NAME = STEP__NAME;

	/**
	 * The feature id for the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_PACKAGE_STEP__DISPLAY_NAME = STEP__DISPLAY_NAME;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_PACKAGE_STEP__CONDITION = STEP__CONDITION;

	/**
	 * The feature id for the '<em><b>Continue On Error</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_PACKAGE_STEP__CONTINUE_ON_ERROR = STEP__CONTINUE_ON_ERROR;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_PACKAGE_STEP__ENABLED = STEP__ENABLED;

	/**
	 * The feature id for the '<em><b>Timeout In Minutes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_PACKAGE_STEP__TIMEOUT_IN_MINUTES = STEP__TIMEOUT_IN_MINUTES;

	/**
	 * The feature id for the '<em><b>Retry Count On Task Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_PACKAGE_STEP__RETRY_COUNT_ON_TASK_FAILURE = STEP__RETRY_COUNT_ON_TASK_FAILURE;

	/**
	 * The feature id for the '<em><b>Env</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_PACKAGE_STEP__ENV = STEP__ENV;

	/**
	 * The feature id for the '<em><b>Target</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_PACKAGE_STEP__TARGET = STEP__TARGET;

	/**
	 * The feature id for the '<em><b>Get Package</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_PACKAGE_STEP__GET_PACKAGE = STEP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_PACKAGE_STEP__PATH = STEP_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Get Package Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_PACKAGE_STEP_FEATURE_COUNT = STEP_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Get Package Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GET_PACKAGE_STEP_OPERATION_COUNT = STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PublishStepImpl <em>Publish Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PublishStepImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getPublishStep()
	 * @generated
	 */
	int PUBLISH_STEP = 37;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUBLISH_STEP__NAME = STEP__NAME;

	/**
	 * The feature id for the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUBLISH_STEP__DISPLAY_NAME = STEP__DISPLAY_NAME;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUBLISH_STEP__CONDITION = STEP__CONDITION;

	/**
	 * The feature id for the '<em><b>Continue On Error</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUBLISH_STEP__CONTINUE_ON_ERROR = STEP__CONTINUE_ON_ERROR;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUBLISH_STEP__ENABLED = STEP__ENABLED;

	/**
	 * The feature id for the '<em><b>Timeout In Minutes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUBLISH_STEP__TIMEOUT_IN_MINUTES = STEP__TIMEOUT_IN_MINUTES;

	/**
	 * The feature id for the '<em><b>Retry Count On Task Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUBLISH_STEP__RETRY_COUNT_ON_TASK_FAILURE = STEP__RETRY_COUNT_ON_TASK_FAILURE;

	/**
	 * The feature id for the '<em><b>Env</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUBLISH_STEP__ENV = STEP__ENV;

	/**
	 * The feature id for the '<em><b>Target</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUBLISH_STEP__TARGET = STEP__TARGET;

	/**
	 * The feature id for the '<em><b>Publish</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUBLISH_STEP__PUBLISH = STEP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Artifact</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUBLISH_STEP__ARTIFACT = STEP_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Publish Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUBLISH_STEP_FEATURE_COUNT = STEP_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Publish Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUBLISH_STEP_OPERATION_COUNT = STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ReviewAppStepImpl <em>Review App Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ReviewAppStepImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getReviewAppStep()
	 * @generated
	 */
	int REVIEW_APP_STEP = 38;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVIEW_APP_STEP__NAME = STEP__NAME;

	/**
	 * The feature id for the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVIEW_APP_STEP__DISPLAY_NAME = STEP__DISPLAY_NAME;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVIEW_APP_STEP__CONDITION = STEP__CONDITION;

	/**
	 * The feature id for the '<em><b>Continue On Error</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVIEW_APP_STEP__CONTINUE_ON_ERROR = STEP__CONTINUE_ON_ERROR;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVIEW_APP_STEP__ENABLED = STEP__ENABLED;

	/**
	 * The feature id for the '<em><b>Timeout In Minutes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVIEW_APP_STEP__TIMEOUT_IN_MINUTES = STEP__TIMEOUT_IN_MINUTES;

	/**
	 * The feature id for the '<em><b>Retry Count On Task Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVIEW_APP_STEP__RETRY_COUNT_ON_TASK_FAILURE = STEP__RETRY_COUNT_ON_TASK_FAILURE;

	/**
	 * The feature id for the '<em><b>Env</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVIEW_APP_STEP__ENV = STEP__ENV;

	/**
	 * The feature id for the '<em><b>Target</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVIEW_APP_STEP__TARGET = STEP__TARGET;

	/**
	 * The feature id for the '<em><b>Review App</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVIEW_APP_STEP__REVIEW_APP = STEP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Review App Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVIEW_APP_STEP_FEATURE_COUNT = STEP_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Review App Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REVIEW_APP_STEP_OPERATION_COUNT = STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StepTemplateImpl <em>Step Template</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StepTemplateImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getStepTemplate()
	 * @generated
	 */
	int STEP_TEMPLATE = 39;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_TEMPLATE__NAME = STEP__NAME;

	/**
	 * The feature id for the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_TEMPLATE__DISPLAY_NAME = STEP__DISPLAY_NAME;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_TEMPLATE__CONDITION = STEP__CONDITION;

	/**
	 * The feature id for the '<em><b>Continue On Error</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_TEMPLATE__CONTINUE_ON_ERROR = STEP__CONTINUE_ON_ERROR;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_TEMPLATE__ENABLED = STEP__ENABLED;

	/**
	 * The feature id for the '<em><b>Timeout In Minutes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_TEMPLATE__TIMEOUT_IN_MINUTES = STEP__TIMEOUT_IN_MINUTES;

	/**
	 * The feature id for the '<em><b>Retry Count On Task Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_TEMPLATE__RETRY_COUNT_ON_TASK_FAILURE = STEP__RETRY_COUNT_ON_TASK_FAILURE;

	/**
	 * The feature id for the '<em><b>Env</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_TEMPLATE__ENV = STEP__ENV;

	/**
	 * The feature id for the '<em><b>Target</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_TEMPLATE__TARGET = STEP__TARGET;

	/**
	 * The feature id for the '<em><b>Template</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_TEMPLATE__TEMPLATE = STEP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_TEMPLATE__PARAMETERS = STEP_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Step Template</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_TEMPLATE_FEATURE_COUNT = STEP_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Step Template</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_TEMPLATE_OPERATION_COUNT = STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StepTargetImpl <em>Step Target</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StepTargetImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getStepTarget()
	 * @generated
	 */
	int STEP_TARGET = 40;

	/**
	 * The feature id for the '<em><b>Container</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_TARGET__CONTAINER = 0;

	/**
	 * The feature id for the '<em><b>Commands</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_TARGET__COMMANDS = 1;

	/**
	 * The feature id for the '<em><b>Settable Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_TARGET__SETTABLE_VARIABLES = 2;

	/**
	 * The number of structural features of the '<em>Step Target</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_TARGET_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Step Target</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_TARGET_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.SettableVariablesImpl <em>Settable Variables</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.SettableVariablesImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getSettableVariables()
	 * @generated
	 */
	int SETTABLE_VARIABLES = 41;

	/**
	 * The feature id for the '<em><b>None</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SETTABLE_VARIABLES__NONE = 0;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SETTABLE_VARIABLES__VARIABLES = 1;

	/**
	 * The number of structural features of the '<em>Settable Variables</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SETTABLE_VARIABLES_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Settable Variables</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SETTABLE_VARIABLES_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.TriggerImpl <em>Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.TriggerImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getTrigger()
	 * @generated
	 */
	int TRIGGER = 42;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__DISABLED = 0;

	/**
	 * The feature id for the '<em><b>Batch</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__BATCH = 1;

	/**
	 * The feature id for the '<em><b>Branch List</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__BRANCH_LIST = 2;

	/**
	 * The feature id for the '<em><b>Branches</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__BRANCHES = 3;

	/**
	 * The feature id for the '<em><b>Paths</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__PATHS = 4;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__TAGS = 5;

	/**
	 * The number of structural features of the '<em>Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PrTriggerImpl <em>Pr Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PrTriggerImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getPrTrigger()
	 * @generated
	 */
	int PR_TRIGGER = 43;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PR_TRIGGER__DISABLED = 0;

	/**
	 * The feature id for the '<em><b>Auto Cancel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PR_TRIGGER__AUTO_CANCEL = 1;

	/**
	 * The feature id for the '<em><b>Drafts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PR_TRIGGER__DRAFTS = 2;

	/**
	 * The feature id for the '<em><b>Branch List</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PR_TRIGGER__BRANCH_LIST = 3;

	/**
	 * The feature id for the '<em><b>Branches</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PR_TRIGGER__BRANCHES = 4;

	/**
	 * The feature id for the '<em><b>Paths</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PR_TRIGGER__PATHS = 5;

	/**
	 * The number of structural features of the '<em>Pr Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PR_TRIGGER_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Pr Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PR_TRIGGER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.CronScheduleImpl <em>Cron Schedule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.CronScheduleImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getCronSchedule()
	 * @generated
	 */
	int CRON_SCHEDULE = 44;

	/**
	 * The feature id for the '<em><b>Cron</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRON_SCHEDULE__CRON = 0;

	/**
	 * The feature id for the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRON_SCHEDULE__DISPLAY_NAME = 1;

	/**
	 * The feature id for the '<em><b>Batch</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRON_SCHEDULE__BATCH = 2;

	/**
	 * The feature id for the '<em><b>Always</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRON_SCHEDULE__ALWAYS = 3;

	/**
	 * The feature id for the '<em><b>Branches</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRON_SCHEDULE__BRANCHES = 4;

	/**
	 * The number of structural features of the '<em>Cron Schedule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRON_SCHEDULE_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Cron Schedule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CRON_SCHEDULE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.IncludeExcludeFiltersImpl <em>Include Exclude Filters</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.IncludeExcludeFiltersImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getIncludeExcludeFilters()
	 * @generated
	 */
	int INCLUDE_EXCLUDE_FILTERS = 45;

	/**
	 * The feature id for the '<em><b>Include</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCLUDE_EXCLUDE_FILTERS__INCLUDE = 0;

	/**
	 * The feature id for the '<em><b>Exclude</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCLUDE_EXCLUDE_FILTERS__EXCLUDE = 1;

	/**
	 * The number of structural features of the '<em>Include Exclude Filters</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCLUDE_EXCLUDE_FILTERS_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Include Exclude Filters</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCLUDE_EXCLUDE_FILTERS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ParameterImpl <em>Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ParameterImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getParameter()
	 * @generated
	 */
	int PARAMETER = 46;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__NAME = 0;

	/**
	 * The feature id for the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__DISPLAY_NAME = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__TYPE = 2;

	/**
	 * The feature id for the '<em><b>Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__DEFAULT = 3;

	/**
	 * The feature id for the '<em><b>Values</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__VALUES = 4;

	/**
	 * The number of structural features of the '<em>Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.TemplateParameterAssignmentImpl <em>Template Parameter Assignment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.TemplateParameterAssignmentImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getTemplateParameterAssignment()
	 * @generated
	 */
	int TEMPLATE_PARAMETER_ASSIGNMENT = 47;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_PARAMETER_ASSIGNMENT__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_PARAMETER_ASSIGNMENT__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Template Parameter Assignment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_PARAMETER_ASSIGNMENT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Template Parameter Assignment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_PARAMETER_ASSIGNMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.VariableDefinitionImpl <em>Variable Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.VariableDefinitionImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getVariableDefinition()
	 * @generated
	 */
	int VARIABLE_DEFINITION = 48;

	/**
	 * The number of structural features of the '<em>Variable Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DEFINITION_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Variable Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DEFINITION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.VariableNameImpl <em>Variable Name</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.VariableNameImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getVariableName()
	 * @generated
	 */
	int VARIABLE_NAME = 49;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_NAME__NAME = VARIABLE_DEFINITION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_NAME__VALUE = VARIABLE_DEFINITION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Readonly</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_NAME__READONLY = VARIABLE_DEFINITION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Variable Name</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_NAME_FEATURE_COUNT = VARIABLE_DEFINITION_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Variable Name</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_NAME_OPERATION_COUNT = VARIABLE_DEFINITION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.VariableGroupImpl <em>Variable Group</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.VariableGroupImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getVariableGroup()
	 * @generated
	 */
	int VARIABLE_GROUP = 50;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_GROUP__GROUP = VARIABLE_DEFINITION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Variable Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_GROUP_FEATURE_COUNT = VARIABLE_DEFINITION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Variable Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_GROUP_OPERATION_COUNT = VARIABLE_DEFINITION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.VariableTemplateImpl <em>Variable Template</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.VariableTemplateImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getVariableTemplate()
	 * @generated
	 */
	int VARIABLE_TEMPLATE = 51;

	/**
	 * The feature id for the '<em><b>Template</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_TEMPLATE__TEMPLATE = VARIABLE_DEFINITION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_TEMPLATE__PARAMETERS = VARIABLE_DEFINITION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Variable Template</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_TEMPLATE_FEATURE_COUNT = VARIABLE_DEFINITION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Variable Template</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_TEMPLATE_OPERATION_COUNT = VARIABLE_DEFINITION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ResourcesImpl <em>Resources</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ResourcesImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getResources()
	 * @generated
	 */
	int RESOURCES = 52;

	/**
	 * The feature id for the '<em><b>Builds</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCES__BUILDS = 0;

	/**
	 * The feature id for the '<em><b>Containers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCES__CONTAINERS = 1;

	/**
	 * The feature id for the '<em><b>Pipelines</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCES__PIPELINES = 2;

	/**
	 * The feature id for the '<em><b>Repositories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCES__REPOSITORIES = 3;

	/**
	 * The feature id for the '<em><b>Webhooks</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCES__WEBHOOKS = 4;

	/**
	 * The feature id for the '<em><b>Packages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCES__PACKAGES = 5;

	/**
	 * The number of structural features of the '<em>Resources</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCES_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Resources</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCES_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.BuildResourceImpl <em>Build Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.BuildResourceImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getBuildResource()
	 * @generated
	 */
	int BUILD_RESOURCE = 53;

	/**
	 * The feature id for the '<em><b>Build</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILD_RESOURCE__BUILD = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILD_RESOURCE__TYPE = 1;

	/**
	 * The feature id for the '<em><b>Connection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILD_RESOURCE__CONNECTION = 2;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILD_RESOURCE__SOURCE = 3;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILD_RESOURCE__VERSION = 4;

	/**
	 * The feature id for the '<em><b>Branch</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILD_RESOURCE__BRANCH = 5;

	/**
	 * The feature id for the '<em><b>Trigger</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILD_RESOURCE__TRIGGER = 6;

	/**
	 * The number of structural features of the '<em>Build Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILD_RESOURCE_FEATURE_COUNT = 7;

	/**
	 * The number of operations of the '<em>Build Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILD_RESOURCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerResourceImpl <em>Container Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerResourceImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getContainerResource()
	 * @generated
	 */
	int CONTAINER_RESOURCE = 54;

	/**
	 * The feature id for the '<em><b>Container</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_RESOURCE__CONTAINER = 0;

	/**
	 * The feature id for the '<em><b>Image</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_RESOURCE__IMAGE = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_RESOURCE__TYPE = 2;

	/**
	 * The feature id for the '<em><b>Azure Subscription</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_RESOURCE__AZURE_SUBSCRIPTION = 3;

	/**
	 * The feature id for the '<em><b>Resource Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_RESOURCE__RESOURCE_GROUP = 4;

	/**
	 * The feature id for the '<em><b>Registry</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_RESOURCE__REGISTRY = 5;

	/**
	 * The feature id for the '<em><b>Repository</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_RESOURCE__REPOSITORY = 6;

	/**
	 * The feature id for the '<em><b>Endpoint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_RESOURCE__ENDPOINT = 7;

	/**
	 * The feature id for the '<em><b>Local Image</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_RESOURCE__LOCAL_IMAGE = 8;

	/**
	 * The feature id for the '<em><b>Options</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_RESOURCE__OPTIONS = 9;

	/**
	 * The feature id for the '<em><b>Ports</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_RESOURCE__PORTS = 10;

	/**
	 * The feature id for the '<em><b>Volumes</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_RESOURCE__VOLUMES = 11;

	/**
	 * The feature id for the '<em><b>Map Docker Socket</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_RESOURCE__MAP_DOCKER_SOCKET = 12;

	/**
	 * The feature id for the '<em><b>Env</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_RESOURCE__ENV = 13;

	/**
	 * The feature id for the '<em><b>Mount Read Only</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_RESOURCE__MOUNT_READ_ONLY = 14;

	/**
	 * The feature id for the '<em><b>Trigger</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_RESOURCE__TRIGGER = 15;

	/**
	 * The number of structural features of the '<em>Container Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_RESOURCE_FEATURE_COUNT = 16;

	/**
	 * The number of operations of the '<em>Container Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_RESOURCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerResourceTriggerImpl <em>Container Resource Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerResourceTriggerImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getContainerResourceTrigger()
	 * @generated
	 */
	int CONTAINER_RESOURCE_TRIGGER = 55;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_RESOURCE_TRIGGER__ENABLED = 0;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_RESOURCE_TRIGGER__DISABLED = 1;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_RESOURCE_TRIGGER__TAGS = 2;

	/**
	 * The number of structural features of the '<em>Container Resource Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_RESOURCE_TRIGGER_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Container Resource Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_RESOURCE_TRIGGER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineResourceImpl <em>Pipeline Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineResourceImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getPipelineResource()
	 * @generated
	 */
	int PIPELINE_RESOURCE = 56;

	/**
	 * The feature id for the '<em><b>Pipeline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_RESOURCE__PIPELINE = 0;

	/**
	 * The feature id for the '<em><b>Project</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_RESOURCE__PROJECT = 1;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_RESOURCE__SOURCE = 2;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_RESOURCE__VERSION = 3;

	/**
	 * The feature id for the '<em><b>Branch</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_RESOURCE__BRANCH = 4;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_RESOURCE__TAGS = 5;

	/**
	 * The feature id for the '<em><b>Trigger</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_RESOURCE__TRIGGER = 6;

	/**
	 * The number of structural features of the '<em>Pipeline Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_RESOURCE_FEATURE_COUNT = 7;

	/**
	 * The number of operations of the '<em>Pipeline Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_RESOURCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineResourceTriggerImpl <em>Pipeline Resource Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineResourceTriggerImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getPipelineResourceTrigger()
	 * @generated
	 */
	int PIPELINE_RESOURCE_TRIGGER = 57;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_RESOURCE_TRIGGER__ENABLED = 0;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_RESOURCE_TRIGGER__DISABLED = 1;

	/**
	 * The feature id for the '<em><b>Stages</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_RESOURCE_TRIGGER__STAGES = 2;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_RESOURCE_TRIGGER__TAGS = 3;

	/**
	 * The feature id for the '<em><b>Branches</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_RESOURCE_TRIGGER__BRANCHES = 4;

	/**
	 * The number of structural features of the '<em>Pipeline Resource Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_RESOURCE_TRIGGER_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Pipeline Resource Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_RESOURCE_TRIGGER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.RepositoryResourceImpl <em>Repository Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.RepositoryResourceImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getRepositoryResource()
	 * @generated
	 */
	int REPOSITORY_RESOURCE = 58;

	/**
	 * The feature id for the '<em><b>Repository</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_RESOURCE__REPOSITORY = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_RESOURCE__NAME = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_RESOURCE__TYPE = 2;

	/**
	 * The feature id for the '<em><b>Endpoint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_RESOURCE__ENDPOINT = 3;

	/**
	 * The feature id for the '<em><b>Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_RESOURCE__REF = 4;

	/**
	 * The feature id for the '<em><b>Trigger</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_RESOURCE__TRIGGER = 5;

	/**
	 * The number of structural features of the '<em>Repository Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_RESOURCE_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Repository Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_RESOURCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.WebhookResourceImpl <em>Webhook Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.WebhookResourceImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getWebhookResource()
	 * @generated
	 */
	int WEBHOOK_RESOURCE = 59;

	/**
	 * The feature id for the '<em><b>Webhook</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEBHOOK_RESOURCE__WEBHOOK = 0;

	/**
	 * The feature id for the '<em><b>Connection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEBHOOK_RESOURCE__CONNECTION = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEBHOOK_RESOURCE__TYPE = 2;

	/**
	 * The feature id for the '<em><b>Filters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEBHOOK_RESOURCE__FILTERS = 3;

	/**
	 * The number of structural features of the '<em>Webhook Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEBHOOK_RESOURCE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Webhook Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEBHOOK_RESOURCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.WebhookFilterImpl <em>Webhook Filter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.WebhookFilterImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getWebhookFilter()
	 * @generated
	 */
	int WEBHOOK_FILTER = 60;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEBHOOK_FILTER__PATH = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEBHOOK_FILTER__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Webhook Filter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEBHOOK_FILTER_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Webhook Filter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEBHOOK_FILTER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PackageResourceImpl <em>Package Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PackageResourceImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getPackageResource()
	 * @generated
	 */
	int PACKAGE_RESOURCE = 61;

	/**
	 * The feature id for the '<em><b>Package Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_RESOURCE__PACKAGE_NAME = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_RESOURCE__TYPE = 1;

	/**
	 * The feature id for the '<em><b>Connection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_RESOURCE__CONNECTION = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_RESOURCE__NAME = 3;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_RESOURCE__VERSION = 4;

	/**
	 * The feature id for the '<em><b>Tag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_RESOURCE__TAG = 5;

	/**
	 * The feature id for the '<em><b>Trigger</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_RESOURCE__TRIGGER = 6;

	/**
	 * The number of structural features of the '<em>Package Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_RESOURCE_FEATURE_COUNT = 7;

	/**
	 * The number of operations of the '<em>Package Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_RESOURCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.EnvEntryImpl <em>Env Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.EnvEntryImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getEnvEntry()
	 * @generated
	 */
	int ENV_ENTRY = 62;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENV_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENV_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Env Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENV_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Env Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENV_ENTRY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.InputEntryImpl <em>Input Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.InputEntryImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getInputEntry()
	 * @generated
	 */
	int INPUT_ENTRY = 63;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Input Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Input Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_ENTRY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.TemplateContextImpl <em>Template Context</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.TemplateContextImpl
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getTemplateContext()
	 * @generated
	 */
	int TEMPLATE_CONTEXT = 64;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_CONTEXT__PROPERTIES = 0;

	/**
	 * The number of structural features of the '<em>Template Context</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_CONTEXT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Template Context</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_CONTEXT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.LOCK_BEHAVIOR <em>LOCK BEHAVIOR</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.LOCK_BEHAVIOR
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getLOCK_BEHAVIOR()
	 * @generated
	 */
	int LOCK_BEHAVIOR = 65;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.STAGE_TRIGGER <em>STAGE TRIGGER</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.STAGE_TRIGGER
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getSTAGE_TRIGGER()
	 * @generated
	 */
	int STAGE_TRIGGER = 66;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.WORKSPACE_CLEAN <em>WORKSPACE CLEAN</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.WORKSPACE_CLEAN
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getWORKSPACE_CLEAN()
	 * @generated
	 */
	int WORKSPACE_CLEAN = 67;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PARAMETER_TYPE <em>PARAMETER TYPE</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PARAMETER_TYPE
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getPARAMETER_TYPE()
	 * @generated
	 */
	int PARAMETER_TYPE = 68;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.REPOSITORY_TYPE <em>REPOSITORY TYPE</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.REPOSITORY_TYPE
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getREPOSITORY_TYPE()
	 * @generated
	 */
	int REPOSITORY_TYPE = 69;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.TARGET_COMMANDS <em>TARGET COMMANDS</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.TARGET_COMMANDS
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getTARGET_COMMANDS()
	 * @generated
	 */
	int TARGET_COMMANDS = 70;

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline <em>Pipeline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pipeline</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline
	 * @generated
	 */
	EClass getPipeline();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getName()
	 * @see #getPipeline()
	 * @generated
	 */
	EAttribute getPipeline_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getAppendCommitMessageToRunName <em>Append Commit Message To Run Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Append Commit Message To Run Name</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getAppendCommitMessageToRunName()
	 * @see #getPipeline()
	 * @generated
	 */
	EAttribute getPipeline_AppendCommitMessageToRunName();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getLockBehavior <em>Lock Behavior</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lock Behavior</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getLockBehavior()
	 * @see #getPipeline()
	 * @generated
	 */
	EAttribute getPipeline_LockBehavior();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getTrigger <em>Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Trigger</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getTrigger()
	 * @see #getPipeline()
	 * @generated
	 */
	EReference getPipeline_Trigger();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getPr <em>Pr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Pr</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getPr()
	 * @see #getPipeline()
	 * @generated
	 */
	EReference getPipeline_Pr();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getSchedules <em>Schedules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Schedules</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getSchedules()
	 * @see #getPipeline()
	 * @generated
	 */
	EReference getPipeline_Schedules();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameters</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getParameters()
	 * @see #getPipeline()
	 * @generated
	 */
	EReference getPipeline_Parameters();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getVariables <em>Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Variables</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getVariables()
	 * @see #getPipeline()
	 * @generated
	 */
	EReference getPipeline_Variables();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getPool <em>Pool</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Pool</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getPool()
	 * @see #getPipeline()
	 * @generated
	 */
	EReference getPipeline_Pool();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getResources <em>Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Resources</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getResources()
	 * @see #getPipeline()
	 * @generated
	 */
	EReference getPipeline_Resources();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getStages <em>Stages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Stages</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getStages()
	 * @see #getPipeline()
	 * @generated
	 */
	EReference getPipeline_Stages();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getJobs <em>Jobs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Jobs</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getJobs()
	 * @see #getPipeline()
	 * @generated
	 */
	EReference getPipeline_Jobs();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getSteps <em>Steps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Steps</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getSteps()
	 * @see #getPipeline()
	 * @generated
	 */
	EReference getPipeline_Steps();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getExtends <em>Extends</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Extends</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getExtends()
	 * @see #getPipeline()
	 * @generated
	 */
	EReference getPipeline_Extends();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getWorkspace <em>Workspace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Workspace</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getWorkspace()
	 * @see #getPipeline()
	 * @generated
	 */
	EReference getPipeline_Workspace();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Extends <em>Extends</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Extends</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Extends
	 * @generated
	 */
	EClass getExtends();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Extends#getTemplate <em>Template</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Template</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Extends#getTemplate()
	 * @see #getExtends()
	 * @generated
	 */
	EAttribute getExtends_Template();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Extends#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Parameters</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Extends#getParameters()
	 * @see #getExtends()
	 * @generated
	 */
	EReference getExtends_Parameters();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.StageElement <em>Stage Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stage Element</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.StageElement
	 * @generated
	 */
	EClass getStageElement();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage <em>Stage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stage</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage
	 * @generated
	 */
	EClass getStage();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getStage <em>Stage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stage</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getStage()
	 * @see #getStage()
	 * @generated
	 */
	EAttribute getStage_Stage();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getDisplayName <em>Display Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Display Name</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getDisplayName()
	 * @see #getStage()
	 * @generated
	 */
	EAttribute getStage_DisplayName();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Condition</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getCondition()
	 * @see #getStage()
	 * @generated
	 */
	EAttribute getStage_Condition();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getLockBehavior <em>Lock Behavior</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lock Behavior</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getLockBehavior()
	 * @see #getStage()
	 * @generated
	 */
	EAttribute getStage_LockBehavior();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getTrigger <em>Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Trigger</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getTrigger()
	 * @see #getStage()
	 * @generated
	 */
	EAttribute getStage_Trigger();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getIsSkippable <em>Is Skippable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Skippable</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getIsSkippable()
	 * @see #getStage()
	 * @generated
	 */
	EAttribute getStage_IsSkippable();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getDependsOn <em>Depends On</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Depends On</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getDependsOn()
	 * @see #getStage()
	 * @generated
	 */
	EAttribute getStage_DependsOn();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getPool <em>Pool</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Pool</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getPool()
	 * @see #getStage()
	 * @generated
	 */
	EReference getStage_Pool();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getVariables <em>Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Variables</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getVariables()
	 * @see #getStage()
	 * @generated
	 */
	EReference getStage_Variables();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getJobs <em>Jobs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Jobs</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getJobs()
	 * @see #getStage()
	 * @generated
	 */
	EReference getStage_Jobs();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getTemplateContext <em>Template Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Template Context</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getTemplateContext()
	 * @see #getStage()
	 * @generated
	 */
	EReference getStage_TemplateContext();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.StageTemplate <em>Stage Template</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stage Template</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.StageTemplate
	 * @generated
	 */
	EClass getStageTemplate();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.StageTemplate#getTemplate <em>Template</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Template</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.StageTemplate#getTemplate()
	 * @see #getStageTemplate()
	 * @generated
	 */
	EAttribute getStageTemplate_Template();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.StageTemplate#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Parameters</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.StageTemplate#getParameters()
	 * @see #getStageTemplate()
	 * @generated
	 */
	EReference getStageTemplate_Parameters();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.JobElement <em>Job Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Job Element</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.JobElement
	 * @generated
	 */
	EClass getJobElement();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Job <em>Job</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Job</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Job
	 * @generated
	 */
	EClass getJob();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Job#getJob <em>Job</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Job</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Job#getJob()
	 * @see #getJob()
	 * @generated
	 */
	EAttribute getJob_Job();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Job#getDisplayName <em>Display Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Display Name</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Job#getDisplayName()
	 * @see #getJob()
	 * @generated
	 */
	EAttribute getJob_DisplayName();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Job#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Condition</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Job#getCondition()
	 * @see #getJob()
	 * @generated
	 */
	EAttribute getJob_Condition();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Job#getContinueOnError <em>Continue On Error</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Continue On Error</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Job#getContinueOnError()
	 * @see #getJob()
	 * @generated
	 */
	EAttribute getJob_ContinueOnError();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Job#getTimeoutInMinutes <em>Timeout In Minutes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timeout In Minutes</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Job#getTimeoutInMinutes()
	 * @see #getJob()
	 * @generated
	 */
	EAttribute getJob_TimeoutInMinutes();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Job#getCancelTimeoutInMinutes <em>Cancel Timeout In Minutes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cancel Timeout In Minutes</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Job#getCancelTimeoutInMinutes()
	 * @see #getJob()
	 * @generated
	 */
	EAttribute getJob_CancelTimeoutInMinutes();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Job#getDependsOn <em>Depends On</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Depends On</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Job#getDependsOn()
	 * @see #getJob()
	 * @generated
	 */
	EAttribute getJob_DependsOn();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Job#getPool <em>Pool</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Pool</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Job#getPool()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Pool();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Job#getVariables <em>Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Variables</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Job#getVariables()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Variables();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Job#getStrategy <em>Strategy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Strategy</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Job#getStrategy()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Strategy();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Job#getContainer <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Container</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Job#getContainer()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Container();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Job#getServices <em>Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Services</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Job#getServices()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Services();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Job#getWorkspace <em>Workspace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Workspace</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Job#getWorkspace()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Workspace();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Job#getUses <em>Uses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Uses</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Job#getUses()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Uses();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Job#getSteps <em>Steps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Steps</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Job#getSteps()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Steps();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Job#getTemplateContext <em>Template Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Template Context</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Job#getTemplateContext()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_TemplateContext();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob <em>Deployment Job</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Deployment Job</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob
	 * @generated
	 */
	EClass getDeploymentJob();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getDeployment <em>Deployment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Deployment</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getDeployment()
	 * @see #getDeploymentJob()
	 * @generated
	 */
	EAttribute getDeploymentJob_Deployment();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getDisplayName <em>Display Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Display Name</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getDisplayName()
	 * @see #getDeploymentJob()
	 * @generated
	 */
	EAttribute getDeploymentJob_DisplayName();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Condition</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getCondition()
	 * @see #getDeploymentJob()
	 * @generated
	 */
	EAttribute getDeploymentJob_Condition();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getContinueOnError <em>Continue On Error</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Continue On Error</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getContinueOnError()
	 * @see #getDeploymentJob()
	 * @generated
	 */
	EAttribute getDeploymentJob_ContinueOnError();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getTimeoutInMinutes <em>Timeout In Minutes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timeout In Minutes</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getTimeoutInMinutes()
	 * @see #getDeploymentJob()
	 * @generated
	 */
	EAttribute getDeploymentJob_TimeoutInMinutes();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getCancelTimeoutInMinutes <em>Cancel Timeout In Minutes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cancel Timeout In Minutes</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getCancelTimeoutInMinutes()
	 * @see #getDeploymentJob()
	 * @generated
	 */
	EAttribute getDeploymentJob_CancelTimeoutInMinutes();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getDependsOn <em>Depends On</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Depends On</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getDependsOn()
	 * @see #getDeploymentJob()
	 * @generated
	 */
	EAttribute getDeploymentJob_DependsOn();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getPool <em>Pool</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Pool</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getPool()
	 * @see #getDeploymentJob()
	 * @generated
	 */
	EReference getDeploymentJob_Pool();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getVariables <em>Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Variables</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getVariables()
	 * @see #getDeploymentJob()
	 * @generated
	 */
	EReference getDeploymentJob_Variables();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getEnvironment <em>Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Environment</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getEnvironment()
	 * @see #getDeploymentJob()
	 * @generated
	 */
	EReference getDeploymentJob_Environment();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getStrategy <em>Strategy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Strategy</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getStrategy()
	 * @see #getDeploymentJob()
	 * @generated
	 */
	EReference getDeploymentJob_Strategy();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getContainer <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Container</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getContainer()
	 * @see #getDeploymentJob()
	 * @generated
	 */
	EReference getDeploymentJob_Container();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getServices <em>Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Services</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getServices()
	 * @see #getDeploymentJob()
	 * @generated
	 */
	EReference getDeploymentJob_Services();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getWorkspace <em>Workspace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Workspace</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getWorkspace()
	 * @see #getDeploymentJob()
	 * @generated
	 */
	EReference getDeploymentJob_Workspace();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getUses <em>Uses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Uses</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getUses()
	 * @see #getDeploymentJob()
	 * @generated
	 */
	EReference getDeploymentJob_Uses();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getTemplateContext <em>Template Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Template Context</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getTemplateContext()
	 * @see #getDeploymentJob()
	 * @generated
	 */
	EReference getDeploymentJob_TemplateContext();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.JobTemplate <em>Job Template</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Job Template</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.JobTemplate
	 * @generated
	 */
	EClass getJobTemplate();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.JobTemplate#getTemplate <em>Template</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Template</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.JobTemplate#getTemplate()
	 * @see #getJobTemplate()
	 * @generated
	 */
	EAttribute getJobTemplate_Template();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.JobTemplate#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Parameters</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.JobTemplate#getParameters()
	 * @see #getJobTemplate()
	 * @generated
	 */
	EReference getJobTemplate_Parameters();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Environment <em>Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Environment</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Environment
	 * @generated
	 */
	EClass getEnvironment();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Environment#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Environment#getName()
	 * @see #getEnvironment()
	 * @generated
	 */
	EAttribute getEnvironment_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Environment#getResourceName <em>Resource Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resource Name</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Environment#getResourceName()
	 * @see #getEnvironment()
	 * @generated
	 */
	EAttribute getEnvironment_ResourceName();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Environment#getResourceId <em>Resource Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resource Id</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Environment#getResourceId()
	 * @see #getEnvironment()
	 * @generated
	 */
	EAttribute getEnvironment_ResourceId();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Environment#getResourceType <em>Resource Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resource Type</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Environment#getResourceType()
	 * @see #getEnvironment()
	 * @generated
	 */
	EAttribute getEnvironment_ResourceType();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Environment#getTags <em>Tags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tags</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Environment#getTags()
	 * @see #getEnvironment()
	 * @generated
	 */
	EAttribute getEnvironment_Tags();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentStrategy <em>Deployment Strategy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Deployment Strategy</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentStrategy
	 * @generated
	 */
	EClass getDeploymentStrategy();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentStrategy#getRunOnce <em>Run Once</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Run Once</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentStrategy#getRunOnce()
	 * @see #getDeploymentStrategy()
	 * @generated
	 */
	EReference getDeploymentStrategy_RunOnce();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentStrategy#getRolling <em>Rolling</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Rolling</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentStrategy#getRolling()
	 * @see #getDeploymentStrategy()
	 * @generated
	 */
	EReference getDeploymentStrategy_Rolling();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentStrategy#getCanary <em>Canary</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Canary</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentStrategy#getCanary()
	 * @see #getDeploymentStrategy()
	 * @generated
	 */
	EReference getDeploymentStrategy_Canary();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.RunOnceStrategy <em>Run Once Strategy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Run Once Strategy</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.RunOnceStrategy
	 * @generated
	 */
	EClass getRunOnceStrategy();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.RunOnceStrategy#getPreDeploy <em>Pre Deploy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Pre Deploy</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.RunOnceStrategy#getPreDeploy()
	 * @see #getRunOnceStrategy()
	 * @generated
	 */
	EReference getRunOnceStrategy_PreDeploy();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.RunOnceStrategy#getDeploy <em>Deploy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Deploy</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.RunOnceStrategy#getDeploy()
	 * @see #getRunOnceStrategy()
	 * @generated
	 */
	EReference getRunOnceStrategy_Deploy();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.RunOnceStrategy#getRouteTraffic <em>Route Traffic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Route Traffic</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.RunOnceStrategy#getRouteTraffic()
	 * @see #getRunOnceStrategy()
	 * @generated
	 */
	EReference getRunOnceStrategy_RouteTraffic();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.RunOnceStrategy#getPostRouteTraffic <em>Post Route Traffic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Post Route Traffic</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.RunOnceStrategy#getPostRouteTraffic()
	 * @see #getRunOnceStrategy()
	 * @generated
	 */
	EReference getRunOnceStrategy_PostRouteTraffic();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.RunOnceStrategy#getOn <em>On</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>On</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.RunOnceStrategy#getOn()
	 * @see #getRunOnceStrategy()
	 * @generated
	 */
	EReference getRunOnceStrategy_On();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.RollingStrategy <em>Rolling Strategy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rolling Strategy</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.RollingStrategy
	 * @generated
	 */
	EClass getRollingStrategy();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.RollingStrategy#getMaxParallel <em>Max Parallel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Parallel</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.RollingStrategy#getMaxParallel()
	 * @see #getRollingStrategy()
	 * @generated
	 */
	EAttribute getRollingStrategy_MaxParallel();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.RollingStrategy#getPreDeploy <em>Pre Deploy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Pre Deploy</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.RollingStrategy#getPreDeploy()
	 * @see #getRollingStrategy()
	 * @generated
	 */
	EReference getRollingStrategy_PreDeploy();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.RollingStrategy#getDeploy <em>Deploy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Deploy</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.RollingStrategy#getDeploy()
	 * @see #getRollingStrategy()
	 * @generated
	 */
	EReference getRollingStrategy_Deploy();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.RollingStrategy#getRouteTraffic <em>Route Traffic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Route Traffic</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.RollingStrategy#getRouteTraffic()
	 * @see #getRollingStrategy()
	 * @generated
	 */
	EReference getRollingStrategy_RouteTraffic();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.RollingStrategy#getPostRouteTraffic <em>Post Route Traffic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Post Route Traffic</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.RollingStrategy#getPostRouteTraffic()
	 * @see #getRollingStrategy()
	 * @generated
	 */
	EReference getRollingStrategy_PostRouteTraffic();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.RollingStrategy#getOn <em>On</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>On</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.RollingStrategy#getOn()
	 * @see #getRollingStrategy()
	 * @generated
	 */
	EReference getRollingStrategy_On();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CanaryStrategy <em>Canary Strategy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Canary Strategy</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.CanaryStrategy
	 * @generated
	 */
	EClass getCanaryStrategy();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CanaryStrategy#getIncrements <em>Increments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Increments</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.CanaryStrategy#getIncrements()
	 * @see #getCanaryStrategy()
	 * @generated
	 */
	EAttribute getCanaryStrategy_Increments();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CanaryStrategy#getPreDeploy <em>Pre Deploy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Pre Deploy</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.CanaryStrategy#getPreDeploy()
	 * @see #getCanaryStrategy()
	 * @generated
	 */
	EReference getCanaryStrategy_PreDeploy();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CanaryStrategy#getDeploy <em>Deploy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Deploy</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.CanaryStrategy#getDeploy()
	 * @see #getCanaryStrategy()
	 * @generated
	 */
	EReference getCanaryStrategy_Deploy();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CanaryStrategy#getRouteTraffic <em>Route Traffic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Route Traffic</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.CanaryStrategy#getRouteTraffic()
	 * @see #getCanaryStrategy()
	 * @generated
	 */
	EReference getCanaryStrategy_RouteTraffic();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CanaryStrategy#getPostRouteTraffic <em>Post Route Traffic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Post Route Traffic</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.CanaryStrategy#getPostRouteTraffic()
	 * @see #getCanaryStrategy()
	 * @generated
	 */
	EReference getCanaryStrategy_PostRouteTraffic();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CanaryStrategy#getOn <em>On</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>On</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.CanaryStrategy#getOn()
	 * @see #getCanaryStrategy()
	 * @generated
	 */
	EReference getCanaryStrategy_On();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeployHook <em>Deploy Hook</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Deploy Hook</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DeployHook
	 * @generated
	 */
	EClass getDeployHook();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeployHook#getPool <em>Pool</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Pool</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DeployHook#getPool()
	 * @see #getDeployHook()
	 * @generated
	 */
	EReference getDeployHook_Pool();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeployHook#getSteps <em>Steps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Steps</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DeployHook#getSteps()
	 * @see #getDeployHook()
	 * @generated
	 */
	EReference getDeployHook_Steps();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.OnSuccessOrFailureHook <em>On Success Or Failure Hook</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>On Success Or Failure Hook</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.OnSuccessOrFailureHook
	 * @generated
	 */
	EClass getOnSuccessOrFailureHook();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.OnSuccessOrFailureHook#getFailure <em>Failure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Failure</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.OnSuccessOrFailureHook#getFailure()
	 * @see #getOnSuccessOrFailureHook()
	 * @generated
	 */
	EReference getOnSuccessOrFailureHook_Failure();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.OnSuccessOrFailureHook#getSuccess <em>Success</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Success</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.OnSuccessOrFailureHook#getSuccess()
	 * @see #getOnSuccessOrFailureHook()
	 * @generated
	 */
	EReference getOnSuccessOrFailureHook_Success();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.JobStrategy <em>Job Strategy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Job Strategy</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.JobStrategy
	 * @generated
	 */
	EClass getJobStrategy();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.JobStrategy#getParallel <em>Parallel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parallel</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.JobStrategy#getParallel()
	 * @see #getJobStrategy()
	 * @generated
	 */
	EAttribute getJobStrategy_Parallel();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.JobStrategy#getMaxParallel <em>Max Parallel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Parallel</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.JobStrategy#getMaxParallel()
	 * @see #getJobStrategy()
	 * @generated
	 */
	EAttribute getJobStrategy_MaxParallel();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.JobStrategy#getMatrix <em>Matrix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Matrix</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.JobStrategy#getMatrix()
	 * @see #getJobStrategy()
	 * @generated
	 */
	EReference getJobStrategy_Matrix();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Matrix Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Matrix Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyId="true" keyDataType="org.eclipse.emf.ecore.EString" keyRequired="true"
	 *        valueMapType="com.mddoai.metamodel.azuredevops.azuredevopsMM.MatrixVariable&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;"
	 * @generated
	 */
	EClass getMatrixEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getMatrixEntry()
	 * @generated
	 */
	EAttribute getMatrixEntry_Key();

	/**
	 * Returns the meta object for the map '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getMatrixEntry()
	 * @generated
	 */
	EReference getMatrixEntry_Value();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Matrix Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Matrix Variable</em>'.
	 * @see java.util.Map.Entry
	 * @model keyId="true" keyDataType="org.eclipse.emf.ecore.EString" keyRequired="true"
	 *        valueDataType="org.eclipse.emf.ecore.EString" valueRequired="true"
	 * @generated
	 */
	EClass getMatrixVariable();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getMatrixVariable()
	 * @generated
	 */
	EAttribute getMatrixVariable_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getMatrixVariable()
	 * @generated
	 */
	EAttribute getMatrixVariable_Value();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.JobUses <em>Job Uses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Job Uses</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.JobUses
	 * @generated
	 */
	EClass getJobUses();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.JobUses#getRepositories <em>Repositories</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Repositories</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.JobUses#getRepositories()
	 * @see #getJobUses()
	 * @generated
	 */
	EAttribute getJobUses_Repositories();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.JobUses#getPools <em>Pools</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Pools</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.JobUses#getPools()
	 * @see #getJobUses()
	 * @generated
	 */
	EAttribute getJobUses_Pools();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pool <em>Pool</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pool</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Pool
	 * @generated
	 */
	EClass getPool();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pool#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Pool#getName()
	 * @see #getPool()
	 * @generated
	 */
	EAttribute getPool_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pool#getVmImage <em>Vm Image</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Vm Image</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Pool#getVmImage()
	 * @see #getPool()
	 * @generated
	 */
	EAttribute getPool_VmImage();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pool#getDemands <em>Demands</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Demands</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Pool#getDemands()
	 * @see #getPool()
	 * @generated
	 */
	EAttribute getPool_Demands();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Workspace <em>Workspace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Workspace</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Workspace
	 * @generated
	 */
	EClass getWorkspace();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Workspace#getClean <em>Clean</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Clean</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Workspace#getClean()
	 * @see #getWorkspace()
	 * @generated
	 */
	EAttribute getWorkspace_Clean();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerReference <em>Container Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container Reference</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerReference
	 * @generated
	 */
	EClass getContainerReference();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerAlias <em>Container Alias</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container Alias</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerAlias
	 * @generated
	 */
	EClass getContainerAlias();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerAlias#getAlias <em>Alias</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Alias</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerAlias#getAlias()
	 * @see #getContainerAlias()
	 * @generated
	 */
	EAttribute getContainerAlias_Alias();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage <em>Container Image</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container Image</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage
	 * @generated
	 */
	EClass getContainerImage();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage#getImage <em>Image</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Image</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage#getImage()
	 * @see #getContainerImage()
	 * @generated
	 */
	EAttribute getContainerImage_Image();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage#getEndpoint <em>Endpoint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Endpoint</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage#getEndpoint()
	 * @see #getContainerImage()
	 * @generated
	 */
	EAttribute getContainerImage_Endpoint();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage#getOptions <em>Options</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Options</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage#getOptions()
	 * @see #getContainerImage()
	 * @generated
	 */
	EAttribute getContainerImage_Options();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage#getPorts <em>Ports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Ports</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage#getPorts()
	 * @see #getContainerImage()
	 * @generated
	 */
	EAttribute getContainerImage_Ports();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage#getVolumes <em>Volumes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Volumes</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage#getVolumes()
	 * @see #getContainerImage()
	 * @generated
	 */
	EAttribute getContainerImage_Volumes();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage#getMapDockerSocket <em>Map Docker Socket</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Map Docker Socket</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage#getMapDockerSocket()
	 * @see #getContainerImage()
	 * @generated
	 */
	EAttribute getContainerImage_MapDockerSocket();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage#getEnv <em>Env</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Env</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage#getEnv()
	 * @see #getContainerImage()
	 * @generated
	 */
	EReference getContainerImage_Env();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage#getMountReadOnly <em>Mount Read Only</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Mount Read Only</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage#getMountReadOnly()
	 * @see #getContainerImage()
	 * @generated
	 */
	EReference getContainerImage_MountReadOnly();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.MountReadOnly <em>Mount Read Only</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mount Read Only</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.MountReadOnly
	 * @generated
	 */
	EClass getMountReadOnly();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.MountReadOnly#getWork <em>Work</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Work</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.MountReadOnly#getWork()
	 * @see #getMountReadOnly()
	 * @generated
	 */
	EAttribute getMountReadOnly_Work();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.MountReadOnly#getExternals <em>Externals</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Externals</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.MountReadOnly#getExternals()
	 * @see #getMountReadOnly()
	 * @generated
	 */
	EAttribute getMountReadOnly_Externals();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.MountReadOnly#getTools <em>Tools</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tools</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.MountReadOnly#getTools()
	 * @see #getMountReadOnly()
	 * @generated
	 */
	EAttribute getMountReadOnly_Tools();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.MountReadOnly#getTasks <em>Tasks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tasks</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.MountReadOnly#getTasks()
	 * @see #getMountReadOnly()
	 * @generated
	 */
	EAttribute getMountReadOnly_Tasks();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Service Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Service Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyId="true" keyDataType="org.eclipse.emf.ecore.EString" keyRequired="true"
	 *        valueDataType="org.eclipse.emf.ecore.EString" valueRequired="true"
	 * @generated
	 */
	EClass getServiceEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getServiceEntry()
	 * @generated
	 */
	EAttribute getServiceEntry_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getServiceEntry()
	 * @generated
	 */
	EAttribute getServiceEntry_Value();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Step <em>Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Step</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Step
	 * @generated
	 */
	EClass getStep();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Step#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Step#getName()
	 * @see #getStep()
	 * @generated
	 */
	EAttribute getStep_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Step#getDisplayName <em>Display Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Display Name</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Step#getDisplayName()
	 * @see #getStep()
	 * @generated
	 */
	EAttribute getStep_DisplayName();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Step#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Condition</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Step#getCondition()
	 * @see #getStep()
	 * @generated
	 */
	EAttribute getStep_Condition();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Step#getContinueOnError <em>Continue On Error</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Continue On Error</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Step#getContinueOnError()
	 * @see #getStep()
	 * @generated
	 */
	EAttribute getStep_ContinueOnError();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Step#getEnabled <em>Enabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enabled</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Step#getEnabled()
	 * @see #getStep()
	 * @generated
	 */
	EAttribute getStep_Enabled();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Step#getTimeoutInMinutes <em>Timeout In Minutes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timeout In Minutes</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Step#getTimeoutInMinutes()
	 * @see #getStep()
	 * @generated
	 */
	EAttribute getStep_TimeoutInMinutes();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Step#getRetryCountOnTaskFailure <em>Retry Count On Task Failure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Retry Count On Task Failure</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Step#getRetryCountOnTaskFailure()
	 * @see #getStep()
	 * @generated
	 */
	EAttribute getStep_RetryCountOnTaskFailure();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Step#getEnv <em>Env</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Env</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Step#getEnv()
	 * @see #getStep()
	 * @generated
	 */
	EReference getStep_Env();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Step#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Target</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Step#getTarget()
	 * @see #getStep()
	 * @generated
	 */
	EReference getStep_Target();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.TaskStep <em>Task Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Task Step</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.TaskStep
	 * @generated
	 */
	EClass getTaskStep();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.TaskStep#getTask <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Task</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.TaskStep#getTask()
	 * @see #getTaskStep()
	 * @generated
	 */
	EAttribute getTaskStep_Task();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.TaskStep#getInputs <em>Inputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Inputs</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.TaskStep#getInputs()
	 * @see #getTaskStep()
	 * @generated
	 */
	EReference getTaskStep_Inputs();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ScriptStep <em>Script Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Script Step</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ScriptStep
	 * @generated
	 */
	EClass getScriptStep();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ScriptStep#getScript <em>Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Script</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ScriptStep#getScript()
	 * @see #getScriptStep()
	 * @generated
	 */
	EAttribute getScriptStep_Script();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ScriptStep#getFailOnStderr <em>Fail On Stderr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fail On Stderr</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ScriptStep#getFailOnStderr()
	 * @see #getScriptStep()
	 * @generated
	 */
	EAttribute getScriptStep_FailOnStderr();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ScriptStep#getWorkingDirectory <em>Working Directory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Working Directory</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ScriptStep#getWorkingDirectory()
	 * @see #getScriptStep()
	 * @generated
	 */
	EAttribute getScriptStep_WorkingDirectory();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.BashStep <em>Bash Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Bash Step</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.BashStep
	 * @generated
	 */
	EClass getBashStep();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.BashStep#getBash <em>Bash</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bash</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.BashStep#getBash()
	 * @see #getBashStep()
	 * @generated
	 */
	EAttribute getBashStep_Bash();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.BashStep#getFailOnStderr <em>Fail On Stderr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fail On Stderr</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.BashStep#getFailOnStderr()
	 * @see #getBashStep()
	 * @generated
	 */
	EAttribute getBashStep_FailOnStderr();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.BashStep#getWorkingDirectory <em>Working Directory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Working Directory</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.BashStep#getWorkingDirectory()
	 * @see #getBashStep()
	 * @generated
	 */
	EAttribute getBashStep_WorkingDirectory();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PowershellStep <em>Powershell Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Powershell Step</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PowershellStep
	 * @generated
	 */
	EClass getPowershellStep();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PowershellStep#getPowershell <em>Powershell</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Powershell</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PowershellStep#getPowershell()
	 * @see #getPowershellStep()
	 * @generated
	 */
	EAttribute getPowershellStep_Powershell();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PowershellStep#getErrorActionPreference <em>Error Action Preference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Error Action Preference</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PowershellStep#getErrorActionPreference()
	 * @see #getPowershellStep()
	 * @generated
	 */
	EAttribute getPowershellStep_ErrorActionPreference();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PowershellStep#getFailOnStderr <em>Fail On Stderr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fail On Stderr</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PowershellStep#getFailOnStderr()
	 * @see #getPowershellStep()
	 * @generated
	 */
	EAttribute getPowershellStep_FailOnStderr();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PowershellStep#getIgnoreLASTEXITCODE <em>Ignore LASTEXITCODE</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ignore LASTEXITCODE</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PowershellStep#getIgnoreLASTEXITCODE()
	 * @see #getPowershellStep()
	 * @generated
	 */
	EAttribute getPowershellStep_IgnoreLASTEXITCODE();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PowershellStep#getWorkingDirectory <em>Working Directory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Working Directory</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PowershellStep#getWorkingDirectory()
	 * @see #getPowershellStep()
	 * @generated
	 */
	EAttribute getPowershellStep_WorkingDirectory();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PwshStep <em>Pwsh Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pwsh Step</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PwshStep
	 * @generated
	 */
	EClass getPwshStep();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PwshStep#getPwsh <em>Pwsh</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pwsh</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PwshStep#getPwsh()
	 * @see #getPwshStep()
	 * @generated
	 */
	EAttribute getPwshStep_Pwsh();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PwshStep#getErrorActionPreference <em>Error Action Preference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Error Action Preference</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PwshStep#getErrorActionPreference()
	 * @see #getPwshStep()
	 * @generated
	 */
	EAttribute getPwshStep_ErrorActionPreference();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PwshStep#getFailOnStderr <em>Fail On Stderr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fail On Stderr</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PwshStep#getFailOnStderr()
	 * @see #getPwshStep()
	 * @generated
	 */
	EAttribute getPwshStep_FailOnStderr();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PwshStep#getIgnoreLASTEXITCODE <em>Ignore LASTEXITCODE</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ignore LASTEXITCODE</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PwshStep#getIgnoreLASTEXITCODE()
	 * @see #getPwshStep()
	 * @generated
	 */
	EAttribute getPwshStep_IgnoreLASTEXITCODE();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PwshStep#getWorkingDirectory <em>Working Directory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Working Directory</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PwshStep#getWorkingDirectory()
	 * @see #getPwshStep()
	 * @generated
	 */
	EAttribute getPwshStep_WorkingDirectory();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep <em>Checkout Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Checkout Step</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep
	 * @generated
	 */
	EClass getCheckoutStep();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getCheckout <em>Checkout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Checkout</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getCheckout()
	 * @see #getCheckoutStep()
	 * @generated
	 */
	EAttribute getCheckoutStep_Checkout();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getClean <em>Clean</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Clean</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getClean()
	 * @see #getCheckoutStep()
	 * @generated
	 */
	EAttribute getCheckoutStep_Clean();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getFetchDepth <em>Fetch Depth</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fetch Depth</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getFetchDepth()
	 * @see #getCheckoutStep()
	 * @generated
	 */
	EAttribute getCheckoutStep_FetchDepth();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getFetchFilter <em>Fetch Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fetch Filter</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getFetchFilter()
	 * @see #getCheckoutStep()
	 * @generated
	 */
	EAttribute getCheckoutStep_FetchFilter();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getFetchTags <em>Fetch Tags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fetch Tags</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getFetchTags()
	 * @see #getCheckoutStep()
	 * @generated
	 */
	EAttribute getCheckoutStep_FetchTags();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getLfs <em>Lfs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lfs</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getLfs()
	 * @see #getCheckoutStep()
	 * @generated
	 */
	EAttribute getCheckoutStep_Lfs();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getPersistCredentials <em>Persist Credentials</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Persist Credentials</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getPersistCredentials()
	 * @see #getCheckoutStep()
	 * @generated
	 */
	EAttribute getCheckoutStep_PersistCredentials();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getSubmodules <em>Submodules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Submodules</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getSubmodules()
	 * @see #getCheckoutStep()
	 * @generated
	 */
	EAttribute getCheckoutStep_Submodules();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Path</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getPath()
	 * @see #getCheckoutStep()
	 * @generated
	 */
	EAttribute getCheckoutStep_Path();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getSparseCheckoutDirectories <em>Sparse Checkout Directories</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sparse Checkout Directories</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getSparseCheckoutDirectories()
	 * @see #getCheckoutStep()
	 * @generated
	 */
	EAttribute getCheckoutStep_SparseCheckoutDirectories();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getSparseCheckoutPatterns <em>Sparse Checkout Patterns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sparse Checkout Patterns</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getSparseCheckoutPatterns()
	 * @see #getCheckoutStep()
	 * @generated
	 */
	EAttribute getCheckoutStep_SparseCheckoutPatterns();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getWorkspaceRepo <em>Workspace Repo</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Workspace Repo</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getWorkspaceRepo()
	 * @see #getCheckoutStep()
	 * @generated
	 */
	EAttribute getCheckoutStep_WorkspaceRepo();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadStep <em>Download Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Download Step</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadStep
	 * @generated
	 */
	EClass getDownloadStep();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadStep#getDownload <em>Download</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Download</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadStep#getDownload()
	 * @see #getDownloadStep()
	 * @generated
	 */
	EAttribute getDownloadStep_Download();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadStep#getArtifact <em>Artifact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Artifact</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadStep#getArtifact()
	 * @see #getDownloadStep()
	 * @generated
	 */
	EAttribute getDownloadStep_Artifact();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadStep#getPatterns <em>Patterns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Patterns</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadStep#getPatterns()
	 * @see #getDownloadStep()
	 * @generated
	 */
	EAttribute getDownloadStep_Patterns();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadBuildStep <em>Download Build Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Download Build Step</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadBuildStep
	 * @generated
	 */
	EClass getDownloadBuildStep();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadBuildStep#getDownloadBuild <em>Download Build</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Download Build</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadBuildStep#getDownloadBuild()
	 * @see #getDownloadBuildStep()
	 * @generated
	 */
	EAttribute getDownloadBuildStep_DownloadBuild();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadBuildStep#getArtifact <em>Artifact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Artifact</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadBuildStep#getArtifact()
	 * @see #getDownloadBuildStep()
	 * @generated
	 */
	EAttribute getDownloadBuildStep_Artifact();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadBuildStep#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Path</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadBuildStep#getPath()
	 * @see #getDownloadBuildStep()
	 * @generated
	 */
	EAttribute getDownloadBuildStep_Path();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadBuildStep#getPatterns <em>Patterns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Patterns</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadBuildStep#getPatterns()
	 * @see #getDownloadBuildStep()
	 * @generated
	 */
	EAttribute getDownloadBuildStep_Patterns();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.GetPackageStep <em>Get Package Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Get Package Step</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.GetPackageStep
	 * @generated
	 */
	EClass getGetPackageStep();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.GetPackageStep#getGetPackage <em>Get Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Get Package</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.GetPackageStep#getGetPackage()
	 * @see #getGetPackageStep()
	 * @generated
	 */
	EAttribute getGetPackageStep_GetPackage();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.GetPackageStep#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Path</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.GetPackageStep#getPath()
	 * @see #getGetPackageStep()
	 * @generated
	 */
	EAttribute getGetPackageStep_Path();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PublishStep <em>Publish Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Publish Step</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PublishStep
	 * @generated
	 */
	EClass getPublishStep();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PublishStep#getPublish <em>Publish</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Publish</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PublishStep#getPublish()
	 * @see #getPublishStep()
	 * @generated
	 */
	EAttribute getPublishStep_Publish();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PublishStep#getArtifact <em>Artifact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Artifact</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PublishStep#getArtifact()
	 * @see #getPublishStep()
	 * @generated
	 */
	EAttribute getPublishStep_Artifact();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ReviewAppStep <em>Review App Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Review App Step</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ReviewAppStep
	 * @generated
	 */
	EClass getReviewAppStep();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ReviewAppStep#getReviewApp <em>Review App</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Review App</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ReviewAppStep#getReviewApp()
	 * @see #getReviewAppStep()
	 * @generated
	 */
	EAttribute getReviewAppStep_ReviewApp();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.StepTemplate <em>Step Template</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Step Template</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.StepTemplate
	 * @generated
	 */
	EClass getStepTemplate();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.StepTemplate#getTemplate <em>Template</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Template</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.StepTemplate#getTemplate()
	 * @see #getStepTemplate()
	 * @generated
	 */
	EAttribute getStepTemplate_Template();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.StepTemplate#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Parameters</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.StepTemplate#getParameters()
	 * @see #getStepTemplate()
	 * @generated
	 */
	EReference getStepTemplate_Parameters();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.StepTarget <em>Step Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Step Target</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.StepTarget
	 * @generated
	 */
	EClass getStepTarget();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.StepTarget#getContainer <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Container</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.StepTarget#getContainer()
	 * @see #getStepTarget()
	 * @generated
	 */
	EAttribute getStepTarget_Container();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.StepTarget#getCommands <em>Commands</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Commands</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.StepTarget#getCommands()
	 * @see #getStepTarget()
	 * @generated
	 */
	EAttribute getStepTarget_Commands();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.StepTarget#getSettableVariables <em>Settable Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Settable Variables</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.StepTarget#getSettableVariables()
	 * @see #getStepTarget()
	 * @generated
	 */
	EReference getStepTarget_SettableVariables();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.SettableVariables <em>Settable Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Settable Variables</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.SettableVariables
	 * @generated
	 */
	EClass getSettableVariables();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.SettableVariables#getNone <em>None</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>None</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.SettableVariables#getNone()
	 * @see #getSettableVariables()
	 * @generated
	 */
	EAttribute getSettableVariables_None();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.SettableVariables#getVariables <em>Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Variables</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.SettableVariables#getVariables()
	 * @see #getSettableVariables()
	 * @generated
	 */
	EAttribute getSettableVariables_Variables();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Trigger <em>Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Trigger</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Trigger
	 * @generated
	 */
	EClass getTrigger();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Trigger#getDisabled <em>Disabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Disabled</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Trigger#getDisabled()
	 * @see #getTrigger()
	 * @generated
	 */
	EAttribute getTrigger_Disabled();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Trigger#getBatch <em>Batch</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Batch</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Trigger#getBatch()
	 * @see #getTrigger()
	 * @generated
	 */
	EAttribute getTrigger_Batch();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Trigger#getBranchList <em>Branch List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Branch List</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Trigger#getBranchList()
	 * @see #getTrigger()
	 * @generated
	 */
	EAttribute getTrigger_BranchList();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Trigger#getBranches <em>Branches</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Branches</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Trigger#getBranches()
	 * @see #getTrigger()
	 * @generated
	 */
	EReference getTrigger_Branches();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Trigger#getPaths <em>Paths</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Paths</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Trigger#getPaths()
	 * @see #getTrigger()
	 * @generated
	 */
	EReference getTrigger_Paths();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Trigger#getTags <em>Tags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Tags</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Trigger#getTags()
	 * @see #getTrigger()
	 * @generated
	 */
	EReference getTrigger_Tags();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PrTrigger <em>Pr Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pr Trigger</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PrTrigger
	 * @generated
	 */
	EClass getPrTrigger();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PrTrigger#getDisabled <em>Disabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Disabled</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PrTrigger#getDisabled()
	 * @see #getPrTrigger()
	 * @generated
	 */
	EAttribute getPrTrigger_Disabled();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PrTrigger#getAutoCancel <em>Auto Cancel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Auto Cancel</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PrTrigger#getAutoCancel()
	 * @see #getPrTrigger()
	 * @generated
	 */
	EAttribute getPrTrigger_AutoCancel();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PrTrigger#getDrafts <em>Drafts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Drafts</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PrTrigger#getDrafts()
	 * @see #getPrTrigger()
	 * @generated
	 */
	EAttribute getPrTrigger_Drafts();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PrTrigger#getBranchList <em>Branch List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Branch List</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PrTrigger#getBranchList()
	 * @see #getPrTrigger()
	 * @generated
	 */
	EAttribute getPrTrigger_BranchList();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PrTrigger#getBranches <em>Branches</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Branches</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PrTrigger#getBranches()
	 * @see #getPrTrigger()
	 * @generated
	 */
	EReference getPrTrigger_Branches();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PrTrigger#getPaths <em>Paths</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Paths</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PrTrigger#getPaths()
	 * @see #getPrTrigger()
	 * @generated
	 */
	EReference getPrTrigger_Paths();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CronSchedule <em>Cron Schedule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cron Schedule</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.CronSchedule
	 * @generated
	 */
	EClass getCronSchedule();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CronSchedule#getCron <em>Cron</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cron</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.CronSchedule#getCron()
	 * @see #getCronSchedule()
	 * @generated
	 */
	EAttribute getCronSchedule_Cron();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CronSchedule#getDisplayName <em>Display Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Display Name</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.CronSchedule#getDisplayName()
	 * @see #getCronSchedule()
	 * @generated
	 */
	EAttribute getCronSchedule_DisplayName();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CronSchedule#getBatch <em>Batch</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Batch</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.CronSchedule#getBatch()
	 * @see #getCronSchedule()
	 * @generated
	 */
	EAttribute getCronSchedule_Batch();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CronSchedule#getAlways <em>Always</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Always</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.CronSchedule#getAlways()
	 * @see #getCronSchedule()
	 * @generated
	 */
	EAttribute getCronSchedule_Always();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CronSchedule#getBranches <em>Branches</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Branches</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.CronSchedule#getBranches()
	 * @see #getCronSchedule()
	 * @generated
	 */
	EReference getCronSchedule_Branches();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.IncludeExcludeFilters <em>Include Exclude Filters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Include Exclude Filters</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.IncludeExcludeFilters
	 * @generated
	 */
	EClass getIncludeExcludeFilters();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.IncludeExcludeFilters#getInclude <em>Include</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Include</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.IncludeExcludeFilters#getInclude()
	 * @see #getIncludeExcludeFilters()
	 * @generated
	 */
	EAttribute getIncludeExcludeFilters_Include();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.IncludeExcludeFilters#getExclude <em>Exclude</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Exclude</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.IncludeExcludeFilters#getExclude()
	 * @see #getIncludeExcludeFilters()
	 * @generated
	 */
	EAttribute getIncludeExcludeFilters_Exclude();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Parameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Parameter
	 * @generated
	 */
	EClass getParameter();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Parameter#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Parameter#getName()
	 * @see #getParameter()
	 * @generated
	 */
	EAttribute getParameter_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Parameter#getDisplayName <em>Display Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Display Name</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Parameter#getDisplayName()
	 * @see #getParameter()
	 * @generated
	 */
	EAttribute getParameter_DisplayName();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Parameter#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Parameter#getType()
	 * @see #getParameter()
	 * @generated
	 */
	EAttribute getParameter_Type();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Parameter#getDefault <em>Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Parameter#getDefault()
	 * @see #getParameter()
	 * @generated
	 */
	EAttribute getParameter_Default();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Parameter#getValues <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Values</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Parameter#getValues()
	 * @see #getParameter()
	 * @generated
	 */
	EAttribute getParameter_Values();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Template Parameter Assignment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Template Parameter Assignment</em>'.
	 * @see java.util.Map.Entry
	 * @model keyId="true" keyDataType="org.eclipse.emf.ecore.EString" keyRequired="true"
	 *        valueDataType="org.eclipse.emf.ecore.EString" valueRequired="true"
	 * @generated
	 */
	EClass getTemplateParameterAssignment();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getTemplateParameterAssignment()
	 * @generated
	 */
	EAttribute getTemplateParameterAssignment_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getTemplateParameterAssignment()
	 * @generated
	 */
	EAttribute getTemplateParameterAssignment_Value();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableDefinition <em>Variable Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable Definition</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableDefinition
	 * @generated
	 */
	EClass getVariableDefinition();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableName <em>Variable Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable Name</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableName
	 * @generated
	 */
	EClass getVariableName();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableName#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableName#getName()
	 * @see #getVariableName()
	 * @generated
	 */
	EAttribute getVariableName_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableName#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableName#getValue()
	 * @see #getVariableName()
	 * @generated
	 */
	EAttribute getVariableName_Value();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableName#getReadonly <em>Readonly</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Readonly</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableName#getReadonly()
	 * @see #getVariableName()
	 * @generated
	 */
	EAttribute getVariableName_Readonly();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableGroup <em>Variable Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable Group</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableGroup
	 * @generated
	 */
	EClass getVariableGroup();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableGroup#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Group</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableGroup#getGroup()
	 * @see #getVariableGroup()
	 * @generated
	 */
	EAttribute getVariableGroup_Group();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableTemplate <em>Variable Template</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable Template</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableTemplate
	 * @generated
	 */
	EClass getVariableTemplate();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableTemplate#getTemplate <em>Template</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Template</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableTemplate#getTemplate()
	 * @see #getVariableTemplate()
	 * @generated
	 */
	EAttribute getVariableTemplate_Template();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableTemplate#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Parameters</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableTemplate#getParameters()
	 * @see #getVariableTemplate()
	 * @generated
	 */
	EReference getVariableTemplate_Parameters();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Resources <em>Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resources</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Resources
	 * @generated
	 */
	EClass getResources();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Resources#getBuilds <em>Builds</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Builds</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Resources#getBuilds()
	 * @see #getResources()
	 * @generated
	 */
	EReference getResources_Builds();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Resources#getContainers <em>Containers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Containers</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Resources#getContainers()
	 * @see #getResources()
	 * @generated
	 */
	EReference getResources_Containers();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Resources#getPipelines <em>Pipelines</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Pipelines</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Resources#getPipelines()
	 * @see #getResources()
	 * @generated
	 */
	EReference getResources_Pipelines();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Resources#getRepositories <em>Repositories</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Repositories</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Resources#getRepositories()
	 * @see #getResources()
	 * @generated
	 */
	EReference getResources_Repositories();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Resources#getWebhooks <em>Webhooks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Webhooks</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Resources#getWebhooks()
	 * @see #getResources()
	 * @generated
	 */
	EReference getResources_Webhooks();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Resources#getPackages <em>Packages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Packages</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Resources#getPackages()
	 * @see #getResources()
	 * @generated
	 */
	EReference getResources_Packages();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.BuildResource <em>Build Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Build Resource</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.BuildResource
	 * @generated
	 */
	EClass getBuildResource();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.BuildResource#getBuild <em>Build</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Build</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.BuildResource#getBuild()
	 * @see #getBuildResource()
	 * @generated
	 */
	EAttribute getBuildResource_Build();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.BuildResource#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.BuildResource#getType()
	 * @see #getBuildResource()
	 * @generated
	 */
	EAttribute getBuildResource_Type();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.BuildResource#getConnection <em>Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connection</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.BuildResource#getConnection()
	 * @see #getBuildResource()
	 * @generated
	 */
	EAttribute getBuildResource_Connection();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.BuildResource#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.BuildResource#getSource()
	 * @see #getBuildResource()
	 * @generated
	 */
	EAttribute getBuildResource_Source();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.BuildResource#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.BuildResource#getVersion()
	 * @see #getBuildResource()
	 * @generated
	 */
	EAttribute getBuildResource_Version();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.BuildResource#getBranch <em>Branch</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Branch</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.BuildResource#getBranch()
	 * @see #getBuildResource()
	 * @generated
	 */
	EAttribute getBuildResource_Branch();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.BuildResource#getTrigger <em>Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Trigger</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.BuildResource#getTrigger()
	 * @see #getBuildResource()
	 * @generated
	 */
	EAttribute getBuildResource_Trigger();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource <em>Container Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container Resource</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource
	 * @generated
	 */
	EClass getContainerResource();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getContainer <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Container</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getContainer()
	 * @see #getContainerResource()
	 * @generated
	 */
	EAttribute getContainerResource_Container();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getImage <em>Image</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Image</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getImage()
	 * @see #getContainerResource()
	 * @generated
	 */
	EAttribute getContainerResource_Image();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getType()
	 * @see #getContainerResource()
	 * @generated
	 */
	EAttribute getContainerResource_Type();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getAzureSubscription <em>Azure Subscription</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Azure Subscription</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getAzureSubscription()
	 * @see #getContainerResource()
	 * @generated
	 */
	EAttribute getContainerResource_AzureSubscription();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getResourceGroup <em>Resource Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resource Group</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getResourceGroup()
	 * @see #getContainerResource()
	 * @generated
	 */
	EAttribute getContainerResource_ResourceGroup();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getRegistry <em>Registry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Registry</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getRegistry()
	 * @see #getContainerResource()
	 * @generated
	 */
	EAttribute getContainerResource_Registry();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getRepository <em>Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Repository</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getRepository()
	 * @see #getContainerResource()
	 * @generated
	 */
	EAttribute getContainerResource_Repository();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getEndpoint <em>Endpoint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Endpoint</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getEndpoint()
	 * @see #getContainerResource()
	 * @generated
	 */
	EAttribute getContainerResource_Endpoint();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getLocalImage <em>Local Image</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Local Image</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getLocalImage()
	 * @see #getContainerResource()
	 * @generated
	 */
	EAttribute getContainerResource_LocalImage();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getOptions <em>Options</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Options</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getOptions()
	 * @see #getContainerResource()
	 * @generated
	 */
	EAttribute getContainerResource_Options();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getPorts <em>Ports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Ports</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getPorts()
	 * @see #getContainerResource()
	 * @generated
	 */
	EAttribute getContainerResource_Ports();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getVolumes <em>Volumes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Volumes</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getVolumes()
	 * @see #getContainerResource()
	 * @generated
	 */
	EAttribute getContainerResource_Volumes();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getMapDockerSocket <em>Map Docker Socket</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Map Docker Socket</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getMapDockerSocket()
	 * @see #getContainerResource()
	 * @generated
	 */
	EAttribute getContainerResource_MapDockerSocket();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getEnv <em>Env</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Env</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getEnv()
	 * @see #getContainerResource()
	 * @generated
	 */
	EReference getContainerResource_Env();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getMountReadOnly <em>Mount Read Only</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Mount Read Only</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getMountReadOnly()
	 * @see #getContainerResource()
	 * @generated
	 */
	EReference getContainerResource_MountReadOnly();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getTrigger <em>Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Trigger</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getTrigger()
	 * @see #getContainerResource()
	 * @generated
	 */
	EReference getContainerResource_Trigger();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResourceTrigger <em>Container Resource Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container Resource Trigger</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResourceTrigger
	 * @generated
	 */
	EClass getContainerResourceTrigger();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResourceTrigger#getEnabled <em>Enabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enabled</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResourceTrigger#getEnabled()
	 * @see #getContainerResourceTrigger()
	 * @generated
	 */
	EAttribute getContainerResourceTrigger_Enabled();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResourceTrigger#getDisabled <em>Disabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Disabled</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResourceTrigger#getDisabled()
	 * @see #getContainerResourceTrigger()
	 * @generated
	 */
	EAttribute getContainerResourceTrigger_Disabled();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResourceTrigger#getTags <em>Tags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Tags</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResourceTrigger#getTags()
	 * @see #getContainerResourceTrigger()
	 * @generated
	 */
	EReference getContainerResourceTrigger_Tags();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResource <em>Pipeline Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pipeline Resource</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResource
	 * @generated
	 */
	EClass getPipelineResource();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResource#getPipeline <em>Pipeline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pipeline</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResource#getPipeline()
	 * @see #getPipelineResource()
	 * @generated
	 */
	EAttribute getPipelineResource_Pipeline();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResource#getProject <em>Project</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Project</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResource#getProject()
	 * @see #getPipelineResource()
	 * @generated
	 */
	EAttribute getPipelineResource_Project();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResource#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResource#getSource()
	 * @see #getPipelineResource()
	 * @generated
	 */
	EAttribute getPipelineResource_Source();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResource#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResource#getVersion()
	 * @see #getPipelineResource()
	 * @generated
	 */
	EAttribute getPipelineResource_Version();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResource#getBranch <em>Branch</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Branch</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResource#getBranch()
	 * @see #getPipelineResource()
	 * @generated
	 */
	EAttribute getPipelineResource_Branch();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResource#getTags <em>Tags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Tags</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResource#getTags()
	 * @see #getPipelineResource()
	 * @generated
	 */
	EAttribute getPipelineResource_Tags();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResource#getTrigger <em>Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Trigger</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResource#getTrigger()
	 * @see #getPipelineResource()
	 * @generated
	 */
	EReference getPipelineResource_Trigger();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResourceTrigger <em>Pipeline Resource Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pipeline Resource Trigger</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResourceTrigger
	 * @generated
	 */
	EClass getPipelineResourceTrigger();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResourceTrigger#getEnabled <em>Enabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enabled</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResourceTrigger#getEnabled()
	 * @see #getPipelineResourceTrigger()
	 * @generated
	 */
	EAttribute getPipelineResourceTrigger_Enabled();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResourceTrigger#getDisabled <em>Disabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Disabled</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResourceTrigger#getDisabled()
	 * @see #getPipelineResourceTrigger()
	 * @generated
	 */
	EAttribute getPipelineResourceTrigger_Disabled();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResourceTrigger#getStages <em>Stages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Stages</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResourceTrigger#getStages()
	 * @see #getPipelineResourceTrigger()
	 * @generated
	 */
	EAttribute getPipelineResourceTrigger_Stages();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResourceTrigger#getTags <em>Tags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Tags</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResourceTrigger#getTags()
	 * @see #getPipelineResourceTrigger()
	 * @generated
	 */
	EAttribute getPipelineResourceTrigger_Tags();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResourceTrigger#getBranches <em>Branches</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Branches</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResourceTrigger#getBranches()
	 * @see #getPipelineResourceTrigger()
	 * @generated
	 */
	EReference getPipelineResourceTrigger_Branches();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.RepositoryResource <em>Repository Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Repository Resource</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.RepositoryResource
	 * @generated
	 */
	EClass getRepositoryResource();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.RepositoryResource#getRepository <em>Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Repository</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.RepositoryResource#getRepository()
	 * @see #getRepositoryResource()
	 * @generated
	 */
	EAttribute getRepositoryResource_Repository();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.RepositoryResource#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.RepositoryResource#getName()
	 * @see #getRepositoryResource()
	 * @generated
	 */
	EAttribute getRepositoryResource_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.RepositoryResource#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.RepositoryResource#getType()
	 * @see #getRepositoryResource()
	 * @generated
	 */
	EAttribute getRepositoryResource_Type();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.RepositoryResource#getEndpoint <em>Endpoint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Endpoint</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.RepositoryResource#getEndpoint()
	 * @see #getRepositoryResource()
	 * @generated
	 */
	EAttribute getRepositoryResource_Endpoint();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.RepositoryResource#getRef <em>Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ref</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.RepositoryResource#getRef()
	 * @see #getRepositoryResource()
	 * @generated
	 */
	EAttribute getRepositoryResource_Ref();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.RepositoryResource#getTrigger <em>Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Trigger</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.RepositoryResource#getTrigger()
	 * @see #getRepositoryResource()
	 * @generated
	 */
	EReference getRepositoryResource_Trigger();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.WebhookResource <em>Webhook Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Webhook Resource</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.WebhookResource
	 * @generated
	 */
	EClass getWebhookResource();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.WebhookResource#getWebhook <em>Webhook</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Webhook</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.WebhookResource#getWebhook()
	 * @see #getWebhookResource()
	 * @generated
	 */
	EAttribute getWebhookResource_Webhook();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.WebhookResource#getConnection <em>Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connection</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.WebhookResource#getConnection()
	 * @see #getWebhookResource()
	 * @generated
	 */
	EAttribute getWebhookResource_Connection();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.WebhookResource#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.WebhookResource#getType()
	 * @see #getWebhookResource()
	 * @generated
	 */
	EAttribute getWebhookResource_Type();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.WebhookResource#getFilters <em>Filters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Filters</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.WebhookResource#getFilters()
	 * @see #getWebhookResource()
	 * @generated
	 */
	EReference getWebhookResource_Filters();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.WebhookFilter <em>Webhook Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Webhook Filter</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.WebhookFilter
	 * @generated
	 */
	EClass getWebhookFilter();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.WebhookFilter#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Path</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.WebhookFilter#getPath()
	 * @see #getWebhookFilter()
	 * @generated
	 */
	EAttribute getWebhookFilter_Path();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.WebhookFilter#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.WebhookFilter#getValue()
	 * @see #getWebhookFilter()
	 * @generated
	 */
	EAttribute getWebhookFilter_Value();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PackageResource <em>Package Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Package Resource</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PackageResource
	 * @generated
	 */
	EClass getPackageResource();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PackageResource#getPackageName <em>Package Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Package Name</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PackageResource#getPackageName()
	 * @see #getPackageResource()
	 * @generated
	 */
	EAttribute getPackageResource_PackageName();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PackageResource#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PackageResource#getType()
	 * @see #getPackageResource()
	 * @generated
	 */
	EAttribute getPackageResource_Type();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PackageResource#getConnection <em>Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connection</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PackageResource#getConnection()
	 * @see #getPackageResource()
	 * @generated
	 */
	EAttribute getPackageResource_Connection();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PackageResource#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PackageResource#getName()
	 * @see #getPackageResource()
	 * @generated
	 */
	EAttribute getPackageResource_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PackageResource#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PackageResource#getVersion()
	 * @see #getPackageResource()
	 * @generated
	 */
	EAttribute getPackageResource_Version();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PackageResource#getTag <em>Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tag</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PackageResource#getTag()
	 * @see #getPackageResource()
	 * @generated
	 */
	EAttribute getPackageResource_Tag();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PackageResource#getTrigger <em>Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Trigger</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PackageResource#getTrigger()
	 * @see #getPackageResource()
	 * @generated
	 */
	EAttribute getPackageResource_Trigger();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Env Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Env Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyId="true" keyDataType="org.eclipse.emf.ecore.EString" keyRequired="true"
	 *        valueDataType="org.eclipse.emf.ecore.EString" valueRequired="true"
	 * @generated
	 */
	EClass getEnvEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getEnvEntry()
	 * @generated
	 */
	EAttribute getEnvEntry_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getEnvEntry()
	 * @generated
	 */
	EAttribute getEnvEntry_Value();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Input Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Input Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyId="true" keyDataType="org.eclipse.emf.ecore.EString" keyRequired="true"
	 *        valueDataType="org.eclipse.emf.ecore.EString" valueRequired="true"
	 * @generated
	 */
	EClass getInputEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getInputEntry()
	 * @generated
	 */
	EAttribute getInputEntry_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getInputEntry()
	 * @generated
	 */
	EAttribute getInputEntry_Value();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.TemplateContext <em>Template Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Template Context</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.TemplateContext
	 * @generated
	 */
	EClass getTemplateContext();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.TemplateContext#getProperties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Properties</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.TemplateContext#getProperties()
	 * @see #getTemplateContext()
	 * @generated
	 */
	EReference getTemplateContext_Properties();

	/**
	 * Returns the meta object for enum '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.LOCK_BEHAVIOR <em>LOCK BEHAVIOR</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>LOCK BEHAVIOR</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.LOCK_BEHAVIOR
	 * @generated
	 */
	EEnum getLOCK_BEHAVIOR();

	/**
	 * Returns the meta object for enum '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.STAGE_TRIGGER <em>STAGE TRIGGER</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>STAGE TRIGGER</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.STAGE_TRIGGER
	 * @generated
	 */
	EEnum getSTAGE_TRIGGER();

	/**
	 * Returns the meta object for enum '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.WORKSPACE_CLEAN <em>WORKSPACE CLEAN</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>WORKSPACE CLEAN</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.WORKSPACE_CLEAN
	 * @generated
	 */
	EEnum getWORKSPACE_CLEAN();

	/**
	 * Returns the meta object for enum '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PARAMETER_TYPE <em>PARAMETER TYPE</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>PARAMETER TYPE</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PARAMETER_TYPE
	 * @generated
	 */
	EEnum getPARAMETER_TYPE();

	/**
	 * Returns the meta object for enum '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.REPOSITORY_TYPE <em>REPOSITORY TYPE</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>REPOSITORY TYPE</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.REPOSITORY_TYPE
	 * @generated
	 */
	EEnum getREPOSITORY_TYPE();

	/**
	 * Returns the meta object for enum '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.TARGET_COMMANDS <em>TARGET COMMANDS</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>TARGET COMMANDS</em>'.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.TARGET_COMMANDS
	 * @generated
	 */
	EEnum getTARGET_COMMANDS();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	AzuredevopsMMFactory getAzuredevopsMMFactory();

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
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineImpl <em>Pipeline</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getPipeline()
		 * @generated
		 */
		EClass PIPELINE = eINSTANCE.getPipeline();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PIPELINE__NAME = eINSTANCE.getPipeline_Name();

		/**
		 * The meta object literal for the '<em><b>Append Commit Message To Run Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PIPELINE__APPEND_COMMIT_MESSAGE_TO_RUN_NAME = eINSTANCE.getPipeline_AppendCommitMessageToRunName();

		/**
		 * The meta object literal for the '<em><b>Lock Behavior</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PIPELINE__LOCK_BEHAVIOR = eINSTANCE.getPipeline_LockBehavior();

		/**
		 * The meta object literal for the '<em><b>Trigger</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIPELINE__TRIGGER = eINSTANCE.getPipeline_Trigger();

		/**
		 * The meta object literal for the '<em><b>Pr</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIPELINE__PR = eINSTANCE.getPipeline_Pr();

		/**
		 * The meta object literal for the '<em><b>Schedules</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIPELINE__SCHEDULES = eINSTANCE.getPipeline_Schedules();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIPELINE__PARAMETERS = eINSTANCE.getPipeline_Parameters();

		/**
		 * The meta object literal for the '<em><b>Variables</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIPELINE__VARIABLES = eINSTANCE.getPipeline_Variables();

		/**
		 * The meta object literal for the '<em><b>Pool</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIPELINE__POOL = eINSTANCE.getPipeline_Pool();

		/**
		 * The meta object literal for the '<em><b>Resources</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIPELINE__RESOURCES = eINSTANCE.getPipeline_Resources();

		/**
		 * The meta object literal for the '<em><b>Stages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIPELINE__STAGES = eINSTANCE.getPipeline_Stages();

		/**
		 * The meta object literal for the '<em><b>Jobs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIPELINE__JOBS = eINSTANCE.getPipeline_Jobs();

		/**
		 * The meta object literal for the '<em><b>Steps</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIPELINE__STEPS = eINSTANCE.getPipeline_Steps();

		/**
		 * The meta object literal for the '<em><b>Extends</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIPELINE__EXTENDS = eINSTANCE.getPipeline_Extends();

		/**
		 * The meta object literal for the '<em><b>Workspace</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIPELINE__WORKSPACE = eINSTANCE.getPipeline_Workspace();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ExtendsImpl <em>Extends</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ExtendsImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getExtends()
		 * @generated
		 */
		EClass EXTENDS = eINSTANCE.getExtends();

		/**
		 * The meta object literal for the '<em><b>Template</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTENDS__TEMPLATE = eINSTANCE.getExtends_Template();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXTENDS__PARAMETERS = eINSTANCE.getExtends_Parameters();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StageElementImpl <em>Stage Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StageElementImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getStageElement()
		 * @generated
		 */
		EClass STAGE_ELEMENT = eINSTANCE.getStageElement();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StageImpl <em>Stage</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StageImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getStage()
		 * @generated
		 */
		EClass STAGE = eINSTANCE.getStage();

		/**
		 * The meta object literal for the '<em><b>Stage</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STAGE__STAGE = eINSTANCE.getStage_Stage();

		/**
		 * The meta object literal for the '<em><b>Display Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STAGE__DISPLAY_NAME = eINSTANCE.getStage_DisplayName();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STAGE__CONDITION = eINSTANCE.getStage_Condition();

		/**
		 * The meta object literal for the '<em><b>Lock Behavior</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STAGE__LOCK_BEHAVIOR = eINSTANCE.getStage_LockBehavior();

		/**
		 * The meta object literal for the '<em><b>Trigger</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STAGE__TRIGGER = eINSTANCE.getStage_Trigger();

		/**
		 * The meta object literal for the '<em><b>Is Skippable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STAGE__IS_SKIPPABLE = eINSTANCE.getStage_IsSkippable();

		/**
		 * The meta object literal for the '<em><b>Depends On</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STAGE__DEPENDS_ON = eINSTANCE.getStage_DependsOn();

		/**
		 * The meta object literal for the '<em><b>Pool</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STAGE__POOL = eINSTANCE.getStage_Pool();

		/**
		 * The meta object literal for the '<em><b>Variables</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STAGE__VARIABLES = eINSTANCE.getStage_Variables();

		/**
		 * The meta object literal for the '<em><b>Jobs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STAGE__JOBS = eINSTANCE.getStage_Jobs();

		/**
		 * The meta object literal for the '<em><b>Template Context</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STAGE__TEMPLATE_CONTEXT = eINSTANCE.getStage_TemplateContext();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StageTemplateImpl <em>Stage Template</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StageTemplateImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getStageTemplate()
		 * @generated
		 */
		EClass STAGE_TEMPLATE = eINSTANCE.getStageTemplate();

		/**
		 * The meta object literal for the '<em><b>Template</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STAGE_TEMPLATE__TEMPLATE = eINSTANCE.getStageTemplate_Template();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STAGE_TEMPLATE__PARAMETERS = eINSTANCE.getStageTemplate_Parameters();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobElementImpl <em>Job Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobElementImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getJobElement()
		 * @generated
		 */
		EClass JOB_ELEMENT = eINSTANCE.getJobElement();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobImpl <em>Job</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getJob()
		 * @generated
		 */
		EClass JOB = eINSTANCE.getJob();

		/**
		 * The meta object literal for the '<em><b>Job</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOB__JOB = eINSTANCE.getJob_Job();

		/**
		 * The meta object literal for the '<em><b>Display Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOB__DISPLAY_NAME = eINSTANCE.getJob_DisplayName();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOB__CONDITION = eINSTANCE.getJob_Condition();

		/**
		 * The meta object literal for the '<em><b>Continue On Error</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOB__CONTINUE_ON_ERROR = eINSTANCE.getJob_ContinueOnError();

		/**
		 * The meta object literal for the '<em><b>Timeout In Minutes</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOB__TIMEOUT_IN_MINUTES = eINSTANCE.getJob_TimeoutInMinutes();

		/**
		 * The meta object literal for the '<em><b>Cancel Timeout In Minutes</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOB__CANCEL_TIMEOUT_IN_MINUTES = eINSTANCE.getJob_CancelTimeoutInMinutes();

		/**
		 * The meta object literal for the '<em><b>Depends On</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOB__DEPENDS_ON = eINSTANCE.getJob_DependsOn();

		/**
		 * The meta object literal for the '<em><b>Pool</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__POOL = eINSTANCE.getJob_Pool();

		/**
		 * The meta object literal for the '<em><b>Variables</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__VARIABLES = eINSTANCE.getJob_Variables();

		/**
		 * The meta object literal for the '<em><b>Strategy</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__STRATEGY = eINSTANCE.getJob_Strategy();

		/**
		 * The meta object literal for the '<em><b>Container</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__CONTAINER = eINSTANCE.getJob_Container();

		/**
		 * The meta object literal for the '<em><b>Services</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__SERVICES = eINSTANCE.getJob_Services();

		/**
		 * The meta object literal for the '<em><b>Workspace</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__WORKSPACE = eINSTANCE.getJob_Workspace();

		/**
		 * The meta object literal for the '<em><b>Uses</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__USES = eINSTANCE.getJob_Uses();

		/**
		 * The meta object literal for the '<em><b>Steps</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__STEPS = eINSTANCE.getJob_Steps();

		/**
		 * The meta object literal for the '<em><b>Template Context</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__TEMPLATE_CONTEXT = eINSTANCE.getJob_TemplateContext();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DeploymentJobImpl <em>Deployment Job</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DeploymentJobImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getDeploymentJob()
		 * @generated
		 */
		EClass DEPLOYMENT_JOB = eINSTANCE.getDeploymentJob();

		/**
		 * The meta object literal for the '<em><b>Deployment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DEPLOYMENT_JOB__DEPLOYMENT = eINSTANCE.getDeploymentJob_Deployment();

		/**
		 * The meta object literal for the '<em><b>Display Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DEPLOYMENT_JOB__DISPLAY_NAME = eINSTANCE.getDeploymentJob_DisplayName();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DEPLOYMENT_JOB__CONDITION = eINSTANCE.getDeploymentJob_Condition();

		/**
		 * The meta object literal for the '<em><b>Continue On Error</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DEPLOYMENT_JOB__CONTINUE_ON_ERROR = eINSTANCE.getDeploymentJob_ContinueOnError();

		/**
		 * The meta object literal for the '<em><b>Timeout In Minutes</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DEPLOYMENT_JOB__TIMEOUT_IN_MINUTES = eINSTANCE.getDeploymentJob_TimeoutInMinutes();

		/**
		 * The meta object literal for the '<em><b>Cancel Timeout In Minutes</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DEPLOYMENT_JOB__CANCEL_TIMEOUT_IN_MINUTES = eINSTANCE.getDeploymentJob_CancelTimeoutInMinutes();

		/**
		 * The meta object literal for the '<em><b>Depends On</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DEPLOYMENT_JOB__DEPENDS_ON = eINSTANCE.getDeploymentJob_DependsOn();

		/**
		 * The meta object literal for the '<em><b>Pool</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPLOYMENT_JOB__POOL = eINSTANCE.getDeploymentJob_Pool();

		/**
		 * The meta object literal for the '<em><b>Variables</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPLOYMENT_JOB__VARIABLES = eINSTANCE.getDeploymentJob_Variables();

		/**
		 * The meta object literal for the '<em><b>Environment</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPLOYMENT_JOB__ENVIRONMENT = eINSTANCE.getDeploymentJob_Environment();

		/**
		 * The meta object literal for the '<em><b>Strategy</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPLOYMENT_JOB__STRATEGY = eINSTANCE.getDeploymentJob_Strategy();

		/**
		 * The meta object literal for the '<em><b>Container</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPLOYMENT_JOB__CONTAINER = eINSTANCE.getDeploymentJob_Container();

		/**
		 * The meta object literal for the '<em><b>Services</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPLOYMENT_JOB__SERVICES = eINSTANCE.getDeploymentJob_Services();

		/**
		 * The meta object literal for the '<em><b>Workspace</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPLOYMENT_JOB__WORKSPACE = eINSTANCE.getDeploymentJob_Workspace();

		/**
		 * The meta object literal for the '<em><b>Uses</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPLOYMENT_JOB__USES = eINSTANCE.getDeploymentJob_Uses();

		/**
		 * The meta object literal for the '<em><b>Template Context</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPLOYMENT_JOB__TEMPLATE_CONTEXT = eINSTANCE.getDeploymentJob_TemplateContext();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobTemplateImpl <em>Job Template</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobTemplateImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getJobTemplate()
		 * @generated
		 */
		EClass JOB_TEMPLATE = eINSTANCE.getJobTemplate();

		/**
		 * The meta object literal for the '<em><b>Template</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOB_TEMPLATE__TEMPLATE = eINSTANCE.getJobTemplate_Template();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB_TEMPLATE__PARAMETERS = eINSTANCE.getJobTemplate_Parameters();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.EnvironmentImpl <em>Environment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.EnvironmentImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getEnvironment()
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
		 * The meta object literal for the '<em><b>Resource Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENVIRONMENT__RESOURCE_NAME = eINSTANCE.getEnvironment_ResourceName();

		/**
		 * The meta object literal for the '<em><b>Resource Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENVIRONMENT__RESOURCE_ID = eINSTANCE.getEnvironment_ResourceId();

		/**
		 * The meta object literal for the '<em><b>Resource Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENVIRONMENT__RESOURCE_TYPE = eINSTANCE.getEnvironment_ResourceType();

		/**
		 * The meta object literal for the '<em><b>Tags</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENVIRONMENT__TAGS = eINSTANCE.getEnvironment_Tags();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DeploymentStrategyImpl <em>Deployment Strategy</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DeploymentStrategyImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getDeploymentStrategy()
		 * @generated
		 */
		EClass DEPLOYMENT_STRATEGY = eINSTANCE.getDeploymentStrategy();

		/**
		 * The meta object literal for the '<em><b>Run Once</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPLOYMENT_STRATEGY__RUN_ONCE = eINSTANCE.getDeploymentStrategy_RunOnce();

		/**
		 * The meta object literal for the '<em><b>Rolling</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPLOYMENT_STRATEGY__ROLLING = eINSTANCE.getDeploymentStrategy_Rolling();

		/**
		 * The meta object literal for the '<em><b>Canary</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPLOYMENT_STRATEGY__CANARY = eINSTANCE.getDeploymentStrategy_Canary();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.RunOnceStrategyImpl <em>Run Once Strategy</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.RunOnceStrategyImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getRunOnceStrategy()
		 * @generated
		 */
		EClass RUN_ONCE_STRATEGY = eINSTANCE.getRunOnceStrategy();

		/**
		 * The meta object literal for the '<em><b>Pre Deploy</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RUN_ONCE_STRATEGY__PRE_DEPLOY = eINSTANCE.getRunOnceStrategy_PreDeploy();

		/**
		 * The meta object literal for the '<em><b>Deploy</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RUN_ONCE_STRATEGY__DEPLOY = eINSTANCE.getRunOnceStrategy_Deploy();

		/**
		 * The meta object literal for the '<em><b>Route Traffic</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RUN_ONCE_STRATEGY__ROUTE_TRAFFIC = eINSTANCE.getRunOnceStrategy_RouteTraffic();

		/**
		 * The meta object literal for the '<em><b>Post Route Traffic</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RUN_ONCE_STRATEGY__POST_ROUTE_TRAFFIC = eINSTANCE.getRunOnceStrategy_PostRouteTraffic();

		/**
		 * The meta object literal for the '<em><b>On</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RUN_ONCE_STRATEGY__ON = eINSTANCE.getRunOnceStrategy_On();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.RollingStrategyImpl <em>Rolling Strategy</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.RollingStrategyImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getRollingStrategy()
		 * @generated
		 */
		EClass ROLLING_STRATEGY = eINSTANCE.getRollingStrategy();

		/**
		 * The meta object literal for the '<em><b>Max Parallel</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROLLING_STRATEGY__MAX_PARALLEL = eINSTANCE.getRollingStrategy_MaxParallel();

		/**
		 * The meta object literal for the '<em><b>Pre Deploy</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLLING_STRATEGY__PRE_DEPLOY = eINSTANCE.getRollingStrategy_PreDeploy();

		/**
		 * The meta object literal for the '<em><b>Deploy</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLLING_STRATEGY__DEPLOY = eINSTANCE.getRollingStrategy_Deploy();

		/**
		 * The meta object literal for the '<em><b>Route Traffic</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLLING_STRATEGY__ROUTE_TRAFFIC = eINSTANCE.getRollingStrategy_RouteTraffic();

		/**
		 * The meta object literal for the '<em><b>Post Route Traffic</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLLING_STRATEGY__POST_ROUTE_TRAFFIC = eINSTANCE.getRollingStrategy_PostRouteTraffic();

		/**
		 * The meta object literal for the '<em><b>On</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLLING_STRATEGY__ON = eINSTANCE.getRollingStrategy_On();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.CanaryStrategyImpl <em>Canary Strategy</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.CanaryStrategyImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getCanaryStrategy()
		 * @generated
		 */
		EClass CANARY_STRATEGY = eINSTANCE.getCanaryStrategy();

		/**
		 * The meta object literal for the '<em><b>Increments</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CANARY_STRATEGY__INCREMENTS = eINSTANCE.getCanaryStrategy_Increments();

		/**
		 * The meta object literal for the '<em><b>Pre Deploy</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CANARY_STRATEGY__PRE_DEPLOY = eINSTANCE.getCanaryStrategy_PreDeploy();

		/**
		 * The meta object literal for the '<em><b>Deploy</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CANARY_STRATEGY__DEPLOY = eINSTANCE.getCanaryStrategy_Deploy();

		/**
		 * The meta object literal for the '<em><b>Route Traffic</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CANARY_STRATEGY__ROUTE_TRAFFIC = eINSTANCE.getCanaryStrategy_RouteTraffic();

		/**
		 * The meta object literal for the '<em><b>Post Route Traffic</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CANARY_STRATEGY__POST_ROUTE_TRAFFIC = eINSTANCE.getCanaryStrategy_PostRouteTraffic();

		/**
		 * The meta object literal for the '<em><b>On</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CANARY_STRATEGY__ON = eINSTANCE.getCanaryStrategy_On();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DeployHookImpl <em>Deploy Hook</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DeployHookImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getDeployHook()
		 * @generated
		 */
		EClass DEPLOY_HOOK = eINSTANCE.getDeployHook();

		/**
		 * The meta object literal for the '<em><b>Pool</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPLOY_HOOK__POOL = eINSTANCE.getDeployHook_Pool();

		/**
		 * The meta object literal for the '<em><b>Steps</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPLOY_HOOK__STEPS = eINSTANCE.getDeployHook_Steps();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.OnSuccessOrFailureHookImpl <em>On Success Or Failure Hook</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.OnSuccessOrFailureHookImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getOnSuccessOrFailureHook()
		 * @generated
		 */
		EClass ON_SUCCESS_OR_FAILURE_HOOK = eINSTANCE.getOnSuccessOrFailureHook();

		/**
		 * The meta object literal for the '<em><b>Failure</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ON_SUCCESS_OR_FAILURE_HOOK__FAILURE = eINSTANCE.getOnSuccessOrFailureHook_Failure();

		/**
		 * The meta object literal for the '<em><b>Success</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ON_SUCCESS_OR_FAILURE_HOOK__SUCCESS = eINSTANCE.getOnSuccessOrFailureHook_Success();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobStrategyImpl <em>Job Strategy</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobStrategyImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getJobStrategy()
		 * @generated
		 */
		EClass JOB_STRATEGY = eINSTANCE.getJobStrategy();

		/**
		 * The meta object literal for the '<em><b>Parallel</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOB_STRATEGY__PARALLEL = eINSTANCE.getJobStrategy_Parallel();

		/**
		 * The meta object literal for the '<em><b>Max Parallel</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOB_STRATEGY__MAX_PARALLEL = eINSTANCE.getJobStrategy_MaxParallel();

		/**
		 * The meta object literal for the '<em><b>Matrix</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB_STRATEGY__MATRIX = eINSTANCE.getJobStrategy_Matrix();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.MatrixEntryImpl <em>Matrix Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.MatrixEntryImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getMatrixEntry()
		 * @generated
		 */
		EClass MATRIX_ENTRY = eINSTANCE.getMatrixEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MATRIX_ENTRY__KEY = eINSTANCE.getMatrixEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MATRIX_ENTRY__VALUE = eINSTANCE.getMatrixEntry_Value();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.MatrixVariableImpl <em>Matrix Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.MatrixVariableImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getMatrixVariable()
		 * @generated
		 */
		EClass MATRIX_VARIABLE = eINSTANCE.getMatrixVariable();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MATRIX_VARIABLE__KEY = eINSTANCE.getMatrixVariable_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MATRIX_VARIABLE__VALUE = eINSTANCE.getMatrixVariable_Value();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobUsesImpl <em>Job Uses</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobUsesImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getJobUses()
		 * @generated
		 */
		EClass JOB_USES = eINSTANCE.getJobUses();

		/**
		 * The meta object literal for the '<em><b>Repositories</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOB_USES__REPOSITORIES = eINSTANCE.getJobUses_Repositories();

		/**
		 * The meta object literal for the '<em><b>Pools</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOB_USES__POOLS = eINSTANCE.getJobUses_Pools();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PoolImpl <em>Pool</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PoolImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getPool()
		 * @generated
		 */
		EClass POOL = eINSTANCE.getPool();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POOL__NAME = eINSTANCE.getPool_Name();

		/**
		 * The meta object literal for the '<em><b>Vm Image</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POOL__VM_IMAGE = eINSTANCE.getPool_VmImage();

		/**
		 * The meta object literal for the '<em><b>Demands</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POOL__DEMANDS = eINSTANCE.getPool_Demands();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.WorkspaceImpl <em>Workspace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.WorkspaceImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getWorkspace()
		 * @generated
		 */
		EClass WORKSPACE = eINSTANCE.getWorkspace();

		/**
		 * The meta object literal for the '<em><b>Clean</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORKSPACE__CLEAN = eINSTANCE.getWorkspace_Clean();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerReferenceImpl <em>Container Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerReferenceImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getContainerReference()
		 * @generated
		 */
		EClass CONTAINER_REFERENCE = eINSTANCE.getContainerReference();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerAliasImpl <em>Container Alias</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerAliasImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getContainerAlias()
		 * @generated
		 */
		EClass CONTAINER_ALIAS = eINSTANCE.getContainerAlias();

		/**
		 * The meta object literal for the '<em><b>Alias</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTAINER_ALIAS__ALIAS = eINSTANCE.getContainerAlias_Alias();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerImageImpl <em>Container Image</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerImageImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getContainerImage()
		 * @generated
		 */
		EClass CONTAINER_IMAGE = eINSTANCE.getContainerImage();

		/**
		 * The meta object literal for the '<em><b>Image</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTAINER_IMAGE__IMAGE = eINSTANCE.getContainerImage_Image();

		/**
		 * The meta object literal for the '<em><b>Endpoint</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTAINER_IMAGE__ENDPOINT = eINSTANCE.getContainerImage_Endpoint();

		/**
		 * The meta object literal for the '<em><b>Options</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTAINER_IMAGE__OPTIONS = eINSTANCE.getContainerImage_Options();

		/**
		 * The meta object literal for the '<em><b>Ports</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTAINER_IMAGE__PORTS = eINSTANCE.getContainerImage_Ports();

		/**
		 * The meta object literal for the '<em><b>Volumes</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTAINER_IMAGE__VOLUMES = eINSTANCE.getContainerImage_Volumes();

		/**
		 * The meta object literal for the '<em><b>Map Docker Socket</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTAINER_IMAGE__MAP_DOCKER_SOCKET = eINSTANCE.getContainerImage_MapDockerSocket();

		/**
		 * The meta object literal for the '<em><b>Env</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER_IMAGE__ENV = eINSTANCE.getContainerImage_Env();

		/**
		 * The meta object literal for the '<em><b>Mount Read Only</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER_IMAGE__MOUNT_READ_ONLY = eINSTANCE.getContainerImage_MountReadOnly();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.MountReadOnlyImpl <em>Mount Read Only</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.MountReadOnlyImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getMountReadOnly()
		 * @generated
		 */
		EClass MOUNT_READ_ONLY = eINSTANCE.getMountReadOnly();

		/**
		 * The meta object literal for the '<em><b>Work</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOUNT_READ_ONLY__WORK = eINSTANCE.getMountReadOnly_Work();

		/**
		 * The meta object literal for the '<em><b>Externals</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOUNT_READ_ONLY__EXTERNALS = eINSTANCE.getMountReadOnly_Externals();

		/**
		 * The meta object literal for the '<em><b>Tools</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOUNT_READ_ONLY__TOOLS = eINSTANCE.getMountReadOnly_Tools();

		/**
		 * The meta object literal for the '<em><b>Tasks</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOUNT_READ_ONLY__TASKS = eINSTANCE.getMountReadOnly_Tasks();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ServiceEntryImpl <em>Service Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ServiceEntryImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getServiceEntry()
		 * @generated
		 */
		EClass SERVICE_ENTRY = eINSTANCE.getServiceEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVICE_ENTRY__KEY = eINSTANCE.getServiceEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVICE_ENTRY__VALUE = eINSTANCE.getServiceEntry_Value();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StepImpl <em>Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StepImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getStep()
		 * @generated
		 */
		EClass STEP = eINSTANCE.getStep();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STEP__NAME = eINSTANCE.getStep_Name();

		/**
		 * The meta object literal for the '<em><b>Display Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STEP__DISPLAY_NAME = eINSTANCE.getStep_DisplayName();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STEP__CONDITION = eINSTANCE.getStep_Condition();

		/**
		 * The meta object literal for the '<em><b>Continue On Error</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STEP__CONTINUE_ON_ERROR = eINSTANCE.getStep_ContinueOnError();

		/**
		 * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STEP__ENABLED = eINSTANCE.getStep_Enabled();

		/**
		 * The meta object literal for the '<em><b>Timeout In Minutes</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STEP__TIMEOUT_IN_MINUTES = eINSTANCE.getStep_TimeoutInMinutes();

		/**
		 * The meta object literal for the '<em><b>Retry Count On Task Failure</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STEP__RETRY_COUNT_ON_TASK_FAILURE = eINSTANCE.getStep_RetryCountOnTaskFailure();

		/**
		 * The meta object literal for the '<em><b>Env</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STEP__ENV = eINSTANCE.getStep_Env();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STEP__TARGET = eINSTANCE.getStep_Target();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.TaskStepImpl <em>Task Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.TaskStepImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getTaskStep()
		 * @generated
		 */
		EClass TASK_STEP = eINSTANCE.getTaskStep();

		/**
		 * The meta object literal for the '<em><b>Task</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK_STEP__TASK = eINSTANCE.getTaskStep_Task();

		/**
		 * The meta object literal for the '<em><b>Inputs</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK_STEP__INPUTS = eINSTANCE.getTaskStep_Inputs();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ScriptStepImpl <em>Script Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ScriptStepImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getScriptStep()
		 * @generated
		 */
		EClass SCRIPT_STEP = eINSTANCE.getScriptStep();

		/**
		 * The meta object literal for the '<em><b>Script</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCRIPT_STEP__SCRIPT = eINSTANCE.getScriptStep_Script();

		/**
		 * The meta object literal for the '<em><b>Fail On Stderr</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCRIPT_STEP__FAIL_ON_STDERR = eINSTANCE.getScriptStep_FailOnStderr();

		/**
		 * The meta object literal for the '<em><b>Working Directory</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCRIPT_STEP__WORKING_DIRECTORY = eINSTANCE.getScriptStep_WorkingDirectory();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.BashStepImpl <em>Bash Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.BashStepImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getBashStep()
		 * @generated
		 */
		EClass BASH_STEP = eINSTANCE.getBashStep();

		/**
		 * The meta object literal for the '<em><b>Bash</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BASH_STEP__BASH = eINSTANCE.getBashStep_Bash();

		/**
		 * The meta object literal for the '<em><b>Fail On Stderr</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BASH_STEP__FAIL_ON_STDERR = eINSTANCE.getBashStep_FailOnStderr();

		/**
		 * The meta object literal for the '<em><b>Working Directory</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BASH_STEP__WORKING_DIRECTORY = eINSTANCE.getBashStep_WorkingDirectory();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PowershellStepImpl <em>Powershell Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PowershellStepImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getPowershellStep()
		 * @generated
		 */
		EClass POWERSHELL_STEP = eINSTANCE.getPowershellStep();

		/**
		 * The meta object literal for the '<em><b>Powershell</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POWERSHELL_STEP__POWERSHELL = eINSTANCE.getPowershellStep_Powershell();

		/**
		 * The meta object literal for the '<em><b>Error Action Preference</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POWERSHELL_STEP__ERROR_ACTION_PREFERENCE = eINSTANCE.getPowershellStep_ErrorActionPreference();

		/**
		 * The meta object literal for the '<em><b>Fail On Stderr</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POWERSHELL_STEP__FAIL_ON_STDERR = eINSTANCE.getPowershellStep_FailOnStderr();

		/**
		 * The meta object literal for the '<em><b>Ignore LASTEXITCODE</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POWERSHELL_STEP__IGNORE_LASTEXITCODE = eINSTANCE.getPowershellStep_IgnoreLASTEXITCODE();

		/**
		 * The meta object literal for the '<em><b>Working Directory</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POWERSHELL_STEP__WORKING_DIRECTORY = eINSTANCE.getPowershellStep_WorkingDirectory();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PwshStepImpl <em>Pwsh Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PwshStepImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getPwshStep()
		 * @generated
		 */
		EClass PWSH_STEP = eINSTANCE.getPwshStep();

		/**
		 * The meta object literal for the '<em><b>Pwsh</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PWSH_STEP__PWSH = eINSTANCE.getPwshStep_Pwsh();

		/**
		 * The meta object literal for the '<em><b>Error Action Preference</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PWSH_STEP__ERROR_ACTION_PREFERENCE = eINSTANCE.getPwshStep_ErrorActionPreference();

		/**
		 * The meta object literal for the '<em><b>Fail On Stderr</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PWSH_STEP__FAIL_ON_STDERR = eINSTANCE.getPwshStep_FailOnStderr();

		/**
		 * The meta object literal for the '<em><b>Ignore LASTEXITCODE</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PWSH_STEP__IGNORE_LASTEXITCODE = eINSTANCE.getPwshStep_IgnoreLASTEXITCODE();

		/**
		 * The meta object literal for the '<em><b>Working Directory</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PWSH_STEP__WORKING_DIRECTORY = eINSTANCE.getPwshStep_WorkingDirectory();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.CheckoutStepImpl <em>Checkout Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.CheckoutStepImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getCheckoutStep()
		 * @generated
		 */
		EClass CHECKOUT_STEP = eINSTANCE.getCheckoutStep();

		/**
		 * The meta object literal for the '<em><b>Checkout</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHECKOUT_STEP__CHECKOUT = eINSTANCE.getCheckoutStep_Checkout();

		/**
		 * The meta object literal for the '<em><b>Clean</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHECKOUT_STEP__CLEAN = eINSTANCE.getCheckoutStep_Clean();

		/**
		 * The meta object literal for the '<em><b>Fetch Depth</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHECKOUT_STEP__FETCH_DEPTH = eINSTANCE.getCheckoutStep_FetchDepth();

		/**
		 * The meta object literal for the '<em><b>Fetch Filter</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHECKOUT_STEP__FETCH_FILTER = eINSTANCE.getCheckoutStep_FetchFilter();

		/**
		 * The meta object literal for the '<em><b>Fetch Tags</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHECKOUT_STEP__FETCH_TAGS = eINSTANCE.getCheckoutStep_FetchTags();

		/**
		 * The meta object literal for the '<em><b>Lfs</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHECKOUT_STEP__LFS = eINSTANCE.getCheckoutStep_Lfs();

		/**
		 * The meta object literal for the '<em><b>Persist Credentials</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHECKOUT_STEP__PERSIST_CREDENTIALS = eINSTANCE.getCheckoutStep_PersistCredentials();

		/**
		 * The meta object literal for the '<em><b>Submodules</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHECKOUT_STEP__SUBMODULES = eINSTANCE.getCheckoutStep_Submodules();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHECKOUT_STEP__PATH = eINSTANCE.getCheckoutStep_Path();

		/**
		 * The meta object literal for the '<em><b>Sparse Checkout Directories</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHECKOUT_STEP__SPARSE_CHECKOUT_DIRECTORIES = eINSTANCE.getCheckoutStep_SparseCheckoutDirectories();

		/**
		 * The meta object literal for the '<em><b>Sparse Checkout Patterns</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHECKOUT_STEP__SPARSE_CHECKOUT_PATTERNS = eINSTANCE.getCheckoutStep_SparseCheckoutPatterns();

		/**
		 * The meta object literal for the '<em><b>Workspace Repo</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHECKOUT_STEP__WORKSPACE_REPO = eINSTANCE.getCheckoutStep_WorkspaceRepo();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DownloadStepImpl <em>Download Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DownloadStepImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getDownloadStep()
		 * @generated
		 */
		EClass DOWNLOAD_STEP = eINSTANCE.getDownloadStep();

		/**
		 * The meta object literal for the '<em><b>Download</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOWNLOAD_STEP__DOWNLOAD = eINSTANCE.getDownloadStep_Download();

		/**
		 * The meta object literal for the '<em><b>Artifact</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOWNLOAD_STEP__ARTIFACT = eINSTANCE.getDownloadStep_Artifact();

		/**
		 * The meta object literal for the '<em><b>Patterns</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOWNLOAD_STEP__PATTERNS = eINSTANCE.getDownloadStep_Patterns();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DownloadBuildStepImpl <em>Download Build Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DownloadBuildStepImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getDownloadBuildStep()
		 * @generated
		 */
		EClass DOWNLOAD_BUILD_STEP = eINSTANCE.getDownloadBuildStep();

		/**
		 * The meta object literal for the '<em><b>Download Build</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOWNLOAD_BUILD_STEP__DOWNLOAD_BUILD = eINSTANCE.getDownloadBuildStep_DownloadBuild();

		/**
		 * The meta object literal for the '<em><b>Artifact</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOWNLOAD_BUILD_STEP__ARTIFACT = eINSTANCE.getDownloadBuildStep_Artifact();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOWNLOAD_BUILD_STEP__PATH = eINSTANCE.getDownloadBuildStep_Path();

		/**
		 * The meta object literal for the '<em><b>Patterns</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOWNLOAD_BUILD_STEP__PATTERNS = eINSTANCE.getDownloadBuildStep_Patterns();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.GetPackageStepImpl <em>Get Package Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.GetPackageStepImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getGetPackageStep()
		 * @generated
		 */
		EClass GET_PACKAGE_STEP = eINSTANCE.getGetPackageStep();

		/**
		 * The meta object literal for the '<em><b>Get Package</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GET_PACKAGE_STEP__GET_PACKAGE = eINSTANCE.getGetPackageStep_GetPackage();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GET_PACKAGE_STEP__PATH = eINSTANCE.getGetPackageStep_Path();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PublishStepImpl <em>Publish Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PublishStepImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getPublishStep()
		 * @generated
		 */
		EClass PUBLISH_STEP = eINSTANCE.getPublishStep();

		/**
		 * The meta object literal for the '<em><b>Publish</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PUBLISH_STEP__PUBLISH = eINSTANCE.getPublishStep_Publish();

		/**
		 * The meta object literal for the '<em><b>Artifact</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PUBLISH_STEP__ARTIFACT = eINSTANCE.getPublishStep_Artifact();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ReviewAppStepImpl <em>Review App Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ReviewAppStepImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getReviewAppStep()
		 * @generated
		 */
		EClass REVIEW_APP_STEP = eINSTANCE.getReviewAppStep();

		/**
		 * The meta object literal for the '<em><b>Review App</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REVIEW_APP_STEP__REVIEW_APP = eINSTANCE.getReviewAppStep_ReviewApp();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StepTemplateImpl <em>Step Template</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StepTemplateImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getStepTemplate()
		 * @generated
		 */
		EClass STEP_TEMPLATE = eINSTANCE.getStepTemplate();

		/**
		 * The meta object literal for the '<em><b>Template</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STEP_TEMPLATE__TEMPLATE = eINSTANCE.getStepTemplate_Template();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STEP_TEMPLATE__PARAMETERS = eINSTANCE.getStepTemplate_Parameters();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StepTargetImpl <em>Step Target</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StepTargetImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getStepTarget()
		 * @generated
		 */
		EClass STEP_TARGET = eINSTANCE.getStepTarget();

		/**
		 * The meta object literal for the '<em><b>Container</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STEP_TARGET__CONTAINER = eINSTANCE.getStepTarget_Container();

		/**
		 * The meta object literal for the '<em><b>Commands</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STEP_TARGET__COMMANDS = eINSTANCE.getStepTarget_Commands();

		/**
		 * The meta object literal for the '<em><b>Settable Variables</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STEP_TARGET__SETTABLE_VARIABLES = eINSTANCE.getStepTarget_SettableVariables();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.SettableVariablesImpl <em>Settable Variables</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.SettableVariablesImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getSettableVariables()
		 * @generated
		 */
		EClass SETTABLE_VARIABLES = eINSTANCE.getSettableVariables();

		/**
		 * The meta object literal for the '<em><b>None</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SETTABLE_VARIABLES__NONE = eINSTANCE.getSettableVariables_None();

		/**
		 * The meta object literal for the '<em><b>Variables</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SETTABLE_VARIABLES__VARIABLES = eINSTANCE.getSettableVariables_Variables();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.TriggerImpl <em>Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.TriggerImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getTrigger()
		 * @generated
		 */
		EClass TRIGGER = eINSTANCE.getTrigger();

		/**
		 * The meta object literal for the '<em><b>Disabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRIGGER__DISABLED = eINSTANCE.getTrigger_Disabled();

		/**
		 * The meta object literal for the '<em><b>Batch</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRIGGER__BATCH = eINSTANCE.getTrigger_Batch();

		/**
		 * The meta object literal for the '<em><b>Branch List</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRIGGER__BRANCH_LIST = eINSTANCE.getTrigger_BranchList();

		/**
		 * The meta object literal for the '<em><b>Branches</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRIGGER__BRANCHES = eINSTANCE.getTrigger_Branches();

		/**
		 * The meta object literal for the '<em><b>Paths</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRIGGER__PATHS = eINSTANCE.getTrigger_Paths();

		/**
		 * The meta object literal for the '<em><b>Tags</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRIGGER__TAGS = eINSTANCE.getTrigger_Tags();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PrTriggerImpl <em>Pr Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PrTriggerImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getPrTrigger()
		 * @generated
		 */
		EClass PR_TRIGGER = eINSTANCE.getPrTrigger();

		/**
		 * The meta object literal for the '<em><b>Disabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PR_TRIGGER__DISABLED = eINSTANCE.getPrTrigger_Disabled();

		/**
		 * The meta object literal for the '<em><b>Auto Cancel</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PR_TRIGGER__AUTO_CANCEL = eINSTANCE.getPrTrigger_AutoCancel();

		/**
		 * The meta object literal for the '<em><b>Drafts</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PR_TRIGGER__DRAFTS = eINSTANCE.getPrTrigger_Drafts();

		/**
		 * The meta object literal for the '<em><b>Branch List</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PR_TRIGGER__BRANCH_LIST = eINSTANCE.getPrTrigger_BranchList();

		/**
		 * The meta object literal for the '<em><b>Branches</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PR_TRIGGER__BRANCHES = eINSTANCE.getPrTrigger_Branches();

		/**
		 * The meta object literal for the '<em><b>Paths</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PR_TRIGGER__PATHS = eINSTANCE.getPrTrigger_Paths();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.CronScheduleImpl <em>Cron Schedule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.CronScheduleImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getCronSchedule()
		 * @generated
		 */
		EClass CRON_SCHEDULE = eINSTANCE.getCronSchedule();

		/**
		 * The meta object literal for the '<em><b>Cron</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CRON_SCHEDULE__CRON = eINSTANCE.getCronSchedule_Cron();

		/**
		 * The meta object literal for the '<em><b>Display Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CRON_SCHEDULE__DISPLAY_NAME = eINSTANCE.getCronSchedule_DisplayName();

		/**
		 * The meta object literal for the '<em><b>Batch</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CRON_SCHEDULE__BATCH = eINSTANCE.getCronSchedule_Batch();

		/**
		 * The meta object literal for the '<em><b>Always</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CRON_SCHEDULE__ALWAYS = eINSTANCE.getCronSchedule_Always();

		/**
		 * The meta object literal for the '<em><b>Branches</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CRON_SCHEDULE__BRANCHES = eINSTANCE.getCronSchedule_Branches();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.IncludeExcludeFiltersImpl <em>Include Exclude Filters</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.IncludeExcludeFiltersImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getIncludeExcludeFilters()
		 * @generated
		 */
		EClass INCLUDE_EXCLUDE_FILTERS = eINSTANCE.getIncludeExcludeFilters();

		/**
		 * The meta object literal for the '<em><b>Include</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INCLUDE_EXCLUDE_FILTERS__INCLUDE = eINSTANCE.getIncludeExcludeFilters_Include();

		/**
		 * The meta object literal for the '<em><b>Exclude</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INCLUDE_EXCLUDE_FILTERS__EXCLUDE = eINSTANCE.getIncludeExcludeFilters_Exclude();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ParameterImpl <em>Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ParameterImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getParameter()
		 * @generated
		 */
		EClass PARAMETER = eINSTANCE.getParameter();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER__NAME = eINSTANCE.getParameter_Name();

		/**
		 * The meta object literal for the '<em><b>Display Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER__DISPLAY_NAME = eINSTANCE.getParameter_DisplayName();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER__TYPE = eINSTANCE.getParameter_Type();

		/**
		 * The meta object literal for the '<em><b>Default</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER__DEFAULT = eINSTANCE.getParameter_Default();

		/**
		 * The meta object literal for the '<em><b>Values</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER__VALUES = eINSTANCE.getParameter_Values();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.TemplateParameterAssignmentImpl <em>Template Parameter Assignment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.TemplateParameterAssignmentImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getTemplateParameterAssignment()
		 * @generated
		 */
		EClass TEMPLATE_PARAMETER_ASSIGNMENT = eINSTANCE.getTemplateParameterAssignment();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEMPLATE_PARAMETER_ASSIGNMENT__KEY = eINSTANCE.getTemplateParameterAssignment_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEMPLATE_PARAMETER_ASSIGNMENT__VALUE = eINSTANCE.getTemplateParameterAssignment_Value();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.VariableDefinitionImpl <em>Variable Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.VariableDefinitionImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getVariableDefinition()
		 * @generated
		 */
		EClass VARIABLE_DEFINITION = eINSTANCE.getVariableDefinition();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.VariableNameImpl <em>Variable Name</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.VariableNameImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getVariableName()
		 * @generated
		 */
		EClass VARIABLE_NAME = eINSTANCE.getVariableName();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE_NAME__NAME = eINSTANCE.getVariableName_Name();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE_NAME__VALUE = eINSTANCE.getVariableName_Value();

		/**
		 * The meta object literal for the '<em><b>Readonly</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE_NAME__READONLY = eINSTANCE.getVariableName_Readonly();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.VariableGroupImpl <em>Variable Group</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.VariableGroupImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getVariableGroup()
		 * @generated
		 */
		EClass VARIABLE_GROUP = eINSTANCE.getVariableGroup();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE_GROUP__GROUP = eINSTANCE.getVariableGroup_Group();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.VariableTemplateImpl <em>Variable Template</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.VariableTemplateImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getVariableTemplate()
		 * @generated
		 */
		EClass VARIABLE_TEMPLATE = eINSTANCE.getVariableTemplate();

		/**
		 * The meta object literal for the '<em><b>Template</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE_TEMPLATE__TEMPLATE = eINSTANCE.getVariableTemplate_Template();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE_TEMPLATE__PARAMETERS = eINSTANCE.getVariableTemplate_Parameters();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ResourcesImpl <em>Resources</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ResourcesImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getResources()
		 * @generated
		 */
		EClass RESOURCES = eINSTANCE.getResources();

		/**
		 * The meta object literal for the '<em><b>Builds</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCES__BUILDS = eINSTANCE.getResources_Builds();

		/**
		 * The meta object literal for the '<em><b>Containers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCES__CONTAINERS = eINSTANCE.getResources_Containers();

		/**
		 * The meta object literal for the '<em><b>Pipelines</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCES__PIPELINES = eINSTANCE.getResources_Pipelines();

		/**
		 * The meta object literal for the '<em><b>Repositories</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCES__REPOSITORIES = eINSTANCE.getResources_Repositories();

		/**
		 * The meta object literal for the '<em><b>Webhooks</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCES__WEBHOOKS = eINSTANCE.getResources_Webhooks();

		/**
		 * The meta object literal for the '<em><b>Packages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCES__PACKAGES = eINSTANCE.getResources_Packages();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.BuildResourceImpl <em>Build Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.BuildResourceImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getBuildResource()
		 * @generated
		 */
		EClass BUILD_RESOURCE = eINSTANCE.getBuildResource();

		/**
		 * The meta object literal for the '<em><b>Build</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUILD_RESOURCE__BUILD = eINSTANCE.getBuildResource_Build();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUILD_RESOURCE__TYPE = eINSTANCE.getBuildResource_Type();

		/**
		 * The meta object literal for the '<em><b>Connection</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUILD_RESOURCE__CONNECTION = eINSTANCE.getBuildResource_Connection();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUILD_RESOURCE__SOURCE = eINSTANCE.getBuildResource_Source();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUILD_RESOURCE__VERSION = eINSTANCE.getBuildResource_Version();

		/**
		 * The meta object literal for the '<em><b>Branch</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUILD_RESOURCE__BRANCH = eINSTANCE.getBuildResource_Branch();

		/**
		 * The meta object literal for the '<em><b>Trigger</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUILD_RESOURCE__TRIGGER = eINSTANCE.getBuildResource_Trigger();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerResourceImpl <em>Container Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerResourceImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getContainerResource()
		 * @generated
		 */
		EClass CONTAINER_RESOURCE = eINSTANCE.getContainerResource();

		/**
		 * The meta object literal for the '<em><b>Container</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTAINER_RESOURCE__CONTAINER = eINSTANCE.getContainerResource_Container();

		/**
		 * The meta object literal for the '<em><b>Image</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTAINER_RESOURCE__IMAGE = eINSTANCE.getContainerResource_Image();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTAINER_RESOURCE__TYPE = eINSTANCE.getContainerResource_Type();

		/**
		 * The meta object literal for the '<em><b>Azure Subscription</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTAINER_RESOURCE__AZURE_SUBSCRIPTION = eINSTANCE.getContainerResource_AzureSubscription();

		/**
		 * The meta object literal for the '<em><b>Resource Group</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTAINER_RESOURCE__RESOURCE_GROUP = eINSTANCE.getContainerResource_ResourceGroup();

		/**
		 * The meta object literal for the '<em><b>Registry</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTAINER_RESOURCE__REGISTRY = eINSTANCE.getContainerResource_Registry();

		/**
		 * The meta object literal for the '<em><b>Repository</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTAINER_RESOURCE__REPOSITORY = eINSTANCE.getContainerResource_Repository();

		/**
		 * The meta object literal for the '<em><b>Endpoint</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTAINER_RESOURCE__ENDPOINT = eINSTANCE.getContainerResource_Endpoint();

		/**
		 * The meta object literal for the '<em><b>Local Image</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTAINER_RESOURCE__LOCAL_IMAGE = eINSTANCE.getContainerResource_LocalImage();

		/**
		 * The meta object literal for the '<em><b>Options</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTAINER_RESOURCE__OPTIONS = eINSTANCE.getContainerResource_Options();

		/**
		 * The meta object literal for the '<em><b>Ports</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTAINER_RESOURCE__PORTS = eINSTANCE.getContainerResource_Ports();

		/**
		 * The meta object literal for the '<em><b>Volumes</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTAINER_RESOURCE__VOLUMES = eINSTANCE.getContainerResource_Volumes();

		/**
		 * The meta object literal for the '<em><b>Map Docker Socket</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTAINER_RESOURCE__MAP_DOCKER_SOCKET = eINSTANCE.getContainerResource_MapDockerSocket();

		/**
		 * The meta object literal for the '<em><b>Env</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER_RESOURCE__ENV = eINSTANCE.getContainerResource_Env();

		/**
		 * The meta object literal for the '<em><b>Mount Read Only</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER_RESOURCE__MOUNT_READ_ONLY = eINSTANCE.getContainerResource_MountReadOnly();

		/**
		 * The meta object literal for the '<em><b>Trigger</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER_RESOURCE__TRIGGER = eINSTANCE.getContainerResource_Trigger();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerResourceTriggerImpl <em>Container Resource Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerResourceTriggerImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getContainerResourceTrigger()
		 * @generated
		 */
		EClass CONTAINER_RESOURCE_TRIGGER = eINSTANCE.getContainerResourceTrigger();

		/**
		 * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTAINER_RESOURCE_TRIGGER__ENABLED = eINSTANCE.getContainerResourceTrigger_Enabled();

		/**
		 * The meta object literal for the '<em><b>Disabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTAINER_RESOURCE_TRIGGER__DISABLED = eINSTANCE.getContainerResourceTrigger_Disabled();

		/**
		 * The meta object literal for the '<em><b>Tags</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER_RESOURCE_TRIGGER__TAGS = eINSTANCE.getContainerResourceTrigger_Tags();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineResourceImpl <em>Pipeline Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineResourceImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getPipelineResource()
		 * @generated
		 */
		EClass PIPELINE_RESOURCE = eINSTANCE.getPipelineResource();

		/**
		 * The meta object literal for the '<em><b>Pipeline</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PIPELINE_RESOURCE__PIPELINE = eINSTANCE.getPipelineResource_Pipeline();

		/**
		 * The meta object literal for the '<em><b>Project</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PIPELINE_RESOURCE__PROJECT = eINSTANCE.getPipelineResource_Project();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PIPELINE_RESOURCE__SOURCE = eINSTANCE.getPipelineResource_Source();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PIPELINE_RESOURCE__VERSION = eINSTANCE.getPipelineResource_Version();

		/**
		 * The meta object literal for the '<em><b>Branch</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PIPELINE_RESOURCE__BRANCH = eINSTANCE.getPipelineResource_Branch();

		/**
		 * The meta object literal for the '<em><b>Tags</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PIPELINE_RESOURCE__TAGS = eINSTANCE.getPipelineResource_Tags();

		/**
		 * The meta object literal for the '<em><b>Trigger</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIPELINE_RESOURCE__TRIGGER = eINSTANCE.getPipelineResource_Trigger();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineResourceTriggerImpl <em>Pipeline Resource Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineResourceTriggerImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getPipelineResourceTrigger()
		 * @generated
		 */
		EClass PIPELINE_RESOURCE_TRIGGER = eINSTANCE.getPipelineResourceTrigger();

		/**
		 * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PIPELINE_RESOURCE_TRIGGER__ENABLED = eINSTANCE.getPipelineResourceTrigger_Enabled();

		/**
		 * The meta object literal for the '<em><b>Disabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PIPELINE_RESOURCE_TRIGGER__DISABLED = eINSTANCE.getPipelineResourceTrigger_Disabled();

		/**
		 * The meta object literal for the '<em><b>Stages</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PIPELINE_RESOURCE_TRIGGER__STAGES = eINSTANCE.getPipelineResourceTrigger_Stages();

		/**
		 * The meta object literal for the '<em><b>Tags</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PIPELINE_RESOURCE_TRIGGER__TAGS = eINSTANCE.getPipelineResourceTrigger_Tags();

		/**
		 * The meta object literal for the '<em><b>Branches</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIPELINE_RESOURCE_TRIGGER__BRANCHES = eINSTANCE.getPipelineResourceTrigger_Branches();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.RepositoryResourceImpl <em>Repository Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.RepositoryResourceImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getRepositoryResource()
		 * @generated
		 */
		EClass REPOSITORY_RESOURCE = eINSTANCE.getRepositoryResource();

		/**
		 * The meta object literal for the '<em><b>Repository</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REPOSITORY_RESOURCE__REPOSITORY = eINSTANCE.getRepositoryResource_Repository();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REPOSITORY_RESOURCE__NAME = eINSTANCE.getRepositoryResource_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REPOSITORY_RESOURCE__TYPE = eINSTANCE.getRepositoryResource_Type();

		/**
		 * The meta object literal for the '<em><b>Endpoint</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REPOSITORY_RESOURCE__ENDPOINT = eINSTANCE.getRepositoryResource_Endpoint();

		/**
		 * The meta object literal for the '<em><b>Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REPOSITORY_RESOURCE__REF = eINSTANCE.getRepositoryResource_Ref();

		/**
		 * The meta object literal for the '<em><b>Trigger</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REPOSITORY_RESOURCE__TRIGGER = eINSTANCE.getRepositoryResource_Trigger();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.WebhookResourceImpl <em>Webhook Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.WebhookResourceImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getWebhookResource()
		 * @generated
		 */
		EClass WEBHOOK_RESOURCE = eINSTANCE.getWebhookResource();

		/**
		 * The meta object literal for the '<em><b>Webhook</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEBHOOK_RESOURCE__WEBHOOK = eINSTANCE.getWebhookResource_Webhook();

		/**
		 * The meta object literal for the '<em><b>Connection</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEBHOOK_RESOURCE__CONNECTION = eINSTANCE.getWebhookResource_Connection();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEBHOOK_RESOURCE__TYPE = eINSTANCE.getWebhookResource_Type();

		/**
		 * The meta object literal for the '<em><b>Filters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEBHOOK_RESOURCE__FILTERS = eINSTANCE.getWebhookResource_Filters();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.WebhookFilterImpl <em>Webhook Filter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.WebhookFilterImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getWebhookFilter()
		 * @generated
		 */
		EClass WEBHOOK_FILTER = eINSTANCE.getWebhookFilter();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEBHOOK_FILTER__PATH = eINSTANCE.getWebhookFilter_Path();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEBHOOK_FILTER__VALUE = eINSTANCE.getWebhookFilter_Value();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PackageResourceImpl <em>Package Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PackageResourceImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getPackageResource()
		 * @generated
		 */
		EClass PACKAGE_RESOURCE = eINSTANCE.getPackageResource();

		/**
		 * The meta object literal for the '<em><b>Package Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PACKAGE_RESOURCE__PACKAGE_NAME = eINSTANCE.getPackageResource_PackageName();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PACKAGE_RESOURCE__TYPE = eINSTANCE.getPackageResource_Type();

		/**
		 * The meta object literal for the '<em><b>Connection</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PACKAGE_RESOURCE__CONNECTION = eINSTANCE.getPackageResource_Connection();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PACKAGE_RESOURCE__NAME = eINSTANCE.getPackageResource_Name();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PACKAGE_RESOURCE__VERSION = eINSTANCE.getPackageResource_Version();

		/**
		 * The meta object literal for the '<em><b>Tag</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PACKAGE_RESOURCE__TAG = eINSTANCE.getPackageResource_Tag();

		/**
		 * The meta object literal for the '<em><b>Trigger</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PACKAGE_RESOURCE__TRIGGER = eINSTANCE.getPackageResource_Trigger();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.EnvEntryImpl <em>Env Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.EnvEntryImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getEnvEntry()
		 * @generated
		 */
		EClass ENV_ENTRY = eINSTANCE.getEnvEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENV_ENTRY__KEY = eINSTANCE.getEnvEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENV_ENTRY__VALUE = eINSTANCE.getEnvEntry_Value();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.InputEntryImpl <em>Input Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.InputEntryImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getInputEntry()
		 * @generated
		 */
		EClass INPUT_ENTRY = eINSTANCE.getInputEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INPUT_ENTRY__KEY = eINSTANCE.getInputEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INPUT_ENTRY__VALUE = eINSTANCE.getInputEntry_Value();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.TemplateContextImpl <em>Template Context</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.TemplateContextImpl
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getTemplateContext()
		 * @generated
		 */
		EClass TEMPLATE_CONTEXT = eINSTANCE.getTemplateContext();

		/**
		 * The meta object literal for the '<em><b>Properties</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEMPLATE_CONTEXT__PROPERTIES = eINSTANCE.getTemplateContext_Properties();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.LOCK_BEHAVIOR <em>LOCK BEHAVIOR</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.LOCK_BEHAVIOR
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getLOCK_BEHAVIOR()
		 * @generated
		 */
		EEnum LOCK_BEHAVIOR = eINSTANCE.getLOCK_BEHAVIOR();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.STAGE_TRIGGER <em>STAGE TRIGGER</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.STAGE_TRIGGER
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getSTAGE_TRIGGER()
		 * @generated
		 */
		EEnum STAGE_TRIGGER = eINSTANCE.getSTAGE_TRIGGER();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.WORKSPACE_CLEAN <em>WORKSPACE CLEAN</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.WORKSPACE_CLEAN
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getWORKSPACE_CLEAN()
		 * @generated
		 */
		EEnum WORKSPACE_CLEAN = eINSTANCE.getWORKSPACE_CLEAN();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PARAMETER_TYPE <em>PARAMETER TYPE</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PARAMETER_TYPE
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getPARAMETER_TYPE()
		 * @generated
		 */
		EEnum PARAMETER_TYPE = eINSTANCE.getPARAMETER_TYPE();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.REPOSITORY_TYPE <em>REPOSITORY TYPE</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.REPOSITORY_TYPE
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getREPOSITORY_TYPE()
		 * @generated
		 */
		EEnum REPOSITORY_TYPE = eINSTANCE.getREPOSITORY_TYPE();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.TARGET_COMMANDS <em>TARGET COMMANDS</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.TARGET_COMMANDS
		 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.AzuredevopsMMPackageImpl#getTARGET_COMMANDS()
		 * @generated
		 */
		EEnum TARGET_COMMANDS = eINSTANCE.getTARGET_COMMANDS();

	}

} //AzuredevopsMMPackage
