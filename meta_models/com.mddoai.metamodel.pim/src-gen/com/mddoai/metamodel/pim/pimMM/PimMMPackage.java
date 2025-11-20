/**
 */
package com.mddoai.metamodel.pim.pimMM;

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
 * @see com.mddoai.metamodel.pim.pimMM.PimMMFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/OCL/Import ecore='http://www.eclipse.org/emf/2002/Ecore'"
 * @generated
 */
public interface PimMMPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "pimMM";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.mddoai.com/mddoai/metamodel/pim";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "pimMM";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PimMMPackage eINSTANCE = com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.PipelineBlockImpl <em>Pipeline Block</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PipelineBlockImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getPipelineBlock()
	 * @generated
	 */
	int PIPELINE_BLOCK = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_BLOCK__NAME = 0;

	/**
	 * The feature id for the '<em><b>Agent</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_BLOCK__AGENT = 1;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_BLOCK__INPUTS = 2;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_BLOCK__OUTPUTS = 3;

	/**
	 * The feature id for the '<em><b>Environment Variables</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_BLOCK__ENVIRONMENT_VARIABLES = 4;

	/**
	 * The feature id for the '<em><b>Timeout Minutes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_BLOCK__TIMEOUT_MINUTES = 5;

	/**
	 * The feature id for the '<em><b>Working Directory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_BLOCK__WORKING_DIRECTORY = 6;

	/**
	 * The feature id for the '<em><b>Shell</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_BLOCK__SHELL = 7;

	/**
	 * The number of structural features of the '<em>Pipeline Block</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_BLOCK_FEATURE_COUNT = 8;

	/**
	 * The number of operations of the '<em>Pipeline Block</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_BLOCK_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.PipelineImpl <em>Pipeline</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PipelineImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getPipeline()
	 * @generated
	 */
	int PIPELINE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE__NAME = PIPELINE_BLOCK__NAME;

	/**
	 * The feature id for the '<em><b>Agent</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE__AGENT = PIPELINE_BLOCK__AGENT;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE__INPUTS = PIPELINE_BLOCK__INPUTS;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE__OUTPUTS = PIPELINE_BLOCK__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Environment Variables</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE__ENVIRONMENT_VARIABLES = PIPELINE_BLOCK__ENVIRONMENT_VARIABLES;

	/**
	 * The feature id for the '<em><b>Timeout Minutes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE__TIMEOUT_MINUTES = PIPELINE_BLOCK__TIMEOUT_MINUTES;

	/**
	 * The feature id for the '<em><b>Working Directory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE__WORKING_DIRECTORY = PIPELINE_BLOCK__WORKING_DIRECTORY;

	/**
	 * The feature id for the '<em><b>Shell</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE__SHELL = PIPELINE_BLOCK__SHELL;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE__TRIGGERS = PIPELINE_BLOCK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Job Streams</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE__JOB_STREAMS = PIPELINE_BLOCK_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Pipeline</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_FEATURE_COUNT = PIPELINE_BLOCK_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Pipeline</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_OPERATION_COUNT = PIPELINE_BLOCK_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.JobImpl <em>Job</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.JobImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getJob()
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
	int JOB__NAME = PIPELINE_BLOCK__NAME;

	/**
	 * The feature id for the '<em><b>Agent</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__AGENT = PIPELINE_BLOCK__AGENT;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__INPUTS = PIPELINE_BLOCK__INPUTS;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__OUTPUTS = PIPELINE_BLOCK__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Environment Variables</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__ENVIRONMENT_VARIABLES = PIPELINE_BLOCK__ENVIRONMENT_VARIABLES;

	/**
	 * The feature id for the '<em><b>Timeout Minutes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__TIMEOUT_MINUTES = PIPELINE_BLOCK__TIMEOUT_MINUTES;

	/**
	 * The feature id for the '<em><b>Working Directory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__WORKING_DIRECTORY = PIPELINE_BLOCK__WORKING_DIRECTORY;

	/**
	 * The feature id for the '<em><b>Shell</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__SHELL = PIPELINE_BLOCK__SHELL;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__ID = PIPELINE_BLOCK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>If Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__IF_CONDITION = PIPELINE_BLOCK_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Services</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__SERVICES = PIPELINE_BLOCK_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Matrix</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__MATRIX = PIPELINE_BLOCK_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Previous</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__PREVIOUS = PIPELINE_BLOCK_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Next</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__NEXT = PIPELINE_BLOCK_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Max Attempts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__MAX_ATTEMPTS = PIPELINE_BLOCK_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Allow Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__ALLOW_FAILURE = PIPELINE_BLOCK_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Job</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB_FEATURE_COUNT = PIPELINE_BLOCK_FEATURE_COUNT + 8;

	/**
	 * The number of operations of the '<em>Job</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB_OPERATION_COUNT = PIPELINE_BLOCK_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.ScriptJobImpl <em>Script Job</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.ScriptJobImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getScriptJob()
	 * @generated
	 */
	int SCRIPT_JOB = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_JOB__NAME = JOB__NAME;

	/**
	 * The feature id for the '<em><b>Agent</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_JOB__AGENT = JOB__AGENT;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_JOB__INPUTS = JOB__INPUTS;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_JOB__OUTPUTS = JOB__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Environment Variables</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_JOB__ENVIRONMENT_VARIABLES = JOB__ENVIRONMENT_VARIABLES;

	/**
	 * The feature id for the '<em><b>Timeout Minutes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_JOB__TIMEOUT_MINUTES = JOB__TIMEOUT_MINUTES;

	/**
	 * The feature id for the '<em><b>Working Directory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_JOB__WORKING_DIRECTORY = JOB__WORKING_DIRECTORY;

	/**
	 * The feature id for the '<em><b>Shell</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_JOB__SHELL = JOB__SHELL;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_JOB__ID = JOB__ID;

	/**
	 * The feature id for the '<em><b>If Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_JOB__IF_CONDITION = JOB__IF_CONDITION;

	/**
	 * The feature id for the '<em><b>Services</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_JOB__SERVICES = JOB__SERVICES;

	/**
	 * The feature id for the '<em><b>Matrix</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_JOB__MATRIX = JOB__MATRIX;

	/**
	 * The feature id for the '<em><b>Previous</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_JOB__PREVIOUS = JOB__PREVIOUS;

	/**
	 * The feature id for the '<em><b>Next</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_JOB__NEXT = JOB__NEXT;

	/**
	 * The feature id for the '<em><b>Max Attempts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_JOB__MAX_ATTEMPTS = JOB__MAX_ATTEMPTS;

	/**
	 * The feature id for the '<em><b>Allow Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_JOB__ALLOW_FAILURE = JOB__ALLOW_FAILURE;

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
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.PipelineCallJobImpl <em>Pipeline Call Job</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PipelineCallJobImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getPipelineCallJob()
	 * @generated
	 */
	int PIPELINE_CALL_JOB = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_CALL_JOB__NAME = JOB__NAME;

	/**
	 * The feature id for the '<em><b>Agent</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_CALL_JOB__AGENT = JOB__AGENT;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_CALL_JOB__INPUTS = JOB__INPUTS;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_CALL_JOB__OUTPUTS = JOB__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Environment Variables</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_CALL_JOB__ENVIRONMENT_VARIABLES = JOB__ENVIRONMENT_VARIABLES;

	/**
	 * The feature id for the '<em><b>Timeout Minutes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_CALL_JOB__TIMEOUT_MINUTES = JOB__TIMEOUT_MINUTES;

	/**
	 * The feature id for the '<em><b>Working Directory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_CALL_JOB__WORKING_DIRECTORY = JOB__WORKING_DIRECTORY;

	/**
	 * The feature id for the '<em><b>Shell</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_CALL_JOB__SHELL = JOB__SHELL;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_CALL_JOB__ID = JOB__ID;

	/**
	 * The feature id for the '<em><b>If Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_CALL_JOB__IF_CONDITION = JOB__IF_CONDITION;

	/**
	 * The feature id for the '<em><b>Services</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_CALL_JOB__SERVICES = JOB__SERVICES;

	/**
	 * The feature id for the '<em><b>Matrix</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_CALL_JOB__MATRIX = JOB__MATRIX;

	/**
	 * The feature id for the '<em><b>Previous</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_CALL_JOB__PREVIOUS = JOB__PREVIOUS;

	/**
	 * The feature id for the '<em><b>Next</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_CALL_JOB__NEXT = JOB__NEXT;

	/**
	 * The feature id for the '<em><b>Max Attempts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_CALL_JOB__MAX_ATTEMPTS = JOB__MAX_ATTEMPTS;

	/**
	 * The feature id for the '<em><b>Allow Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_CALL_JOB__ALLOW_FAILURE = JOB__ALLOW_FAILURE;

	/**
	 * The feature id for the '<em><b>Pipeline Path</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_CALL_JOB__PIPELINE_PATH = JOB_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Args</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_CALL_JOB__ARGS = JOB_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Pipeline Call Job</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_CALL_JOB_FEATURE_COUNT = JOB_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Pipeline Call Job</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_CALL_JOB_OPERATION_COUNT = JOB_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.AgentImpl <em>Agent</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.AgentImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getAgent()
	 * @generated
	 */
	int AGENT = 5;

	/**
	 * The number of structural features of the '<em>Agent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Agent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.CustomAgentImpl <em>Custom Agent</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.CustomAgentImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getCustomAgent()
	 * @generated
	 */
	int CUSTOM_AGENT = 6;

	/**
	 * The feature id for the '<em><b>Labels</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_AGENT__LABELS = AGENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Custom Agent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_AGENT_FEATURE_COUNT = AGENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Custom Agent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_AGENT_OPERATION_COUNT = AGENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.PresetAgentImpl <em>Preset Agent</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PresetAgentImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getPresetAgent()
	 * @generated
	 */
	int PRESET_AGENT = 7;

	/**
	 * The feature id for the '<em><b>Image</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRESET_AGENT__IMAGE = AGENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Preset Agent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRESET_AGENT_FEATURE_COUNT = AGENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Preset Agent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRESET_AGENT_OPERATION_COUNT = AGENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.LinuxAgentImpl <em>Linux Agent</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.LinuxAgentImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getLinuxAgent()
	 * @generated
	 */
	int LINUX_AGENT = 8;

	/**
	 * The feature id for the '<em><b>Image</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINUX_AGENT__IMAGE = PRESET_AGENT__IMAGE;

	/**
	 * The feature id for the '<em><b>Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINUX_AGENT__CONTAINER = PRESET_AGENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Linux Agent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINUX_AGENT_FEATURE_COUNT = PRESET_AGENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Linux Agent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINUX_AGENT_OPERATION_COUNT = PRESET_AGENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.WindowsAgentImpl <em>Windows Agent</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.WindowsAgentImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getWindowsAgent()
	 * @generated
	 */
	int WINDOWS_AGENT = 9;

	/**
	 * The feature id for the '<em><b>Image</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WINDOWS_AGENT__IMAGE = PRESET_AGENT__IMAGE;

	/**
	 * The number of structural features of the '<em>Windows Agent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WINDOWS_AGENT_FEATURE_COUNT = PRESET_AGENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Windows Agent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WINDOWS_AGENT_OPERATION_COUNT = PRESET_AGENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.MacOSAgentImpl <em>Mac OS Agent</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.MacOSAgentImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getMacOSAgent()
	 * @generated
	 */
	int MAC_OS_AGENT = 10;

	/**
	 * The feature id for the '<em><b>Image</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAC_OS_AGENT__IMAGE = PRESET_AGENT__IMAGE;

	/**
	 * The feature id for the '<em><b>Xcode</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAC_OS_AGENT__XCODE = PRESET_AGENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Mac OS Agent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAC_OS_AGENT_FEATURE_COUNT = PRESET_AGENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Mac OS Agent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAC_OS_AGENT_OPERATION_COUNT = PRESET_AGENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.DockerContainerImpl <em>Docker Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.DockerContainerImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getDockerContainer()
	 * @generated
	 */
	int DOCKER_CONTAINER = 11;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCKER_CONTAINER__LABEL = 0;

	/**
	 * The feature id for the '<em><b>Image</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCKER_CONTAINER__IMAGE = 1;

	/**
	 * The feature id for the '<em><b>Environment Variables</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCKER_CONTAINER__ENVIRONMENT_VARIABLES = 2;

	/**
	 * The feature id for the '<em><b>Volumes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCKER_CONTAINER__VOLUMES = 3;

	/**
	 * The feature id for the '<em><b>Ports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCKER_CONTAINER__PORTS = 4;

	/**
	 * The feature id for the '<em><b>Options</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCKER_CONTAINER__OPTIONS = 5;

	/**
	 * The feature id for the '<em><b>Registry Username</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCKER_CONTAINER__REGISTRY_USERNAME = 6;

	/**
	 * The feature id for the '<em><b>Registry Password</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCKER_CONTAINER__REGISTRY_PASSWORD = 7;

	/**
	 * The feature id for the '<em><b>Network</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCKER_CONTAINER__NETWORK = 8;

	/**
	 * The number of structural features of the '<em>Docker Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCKER_CONTAINER_FEATURE_COUNT = 9;

	/**
	 * The number of operations of the '<em>Docker Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCKER_CONTAINER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.TriggerImpl <em>Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.TriggerImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getTrigger()
	 * @generated
	 */
	int TRIGGER = 12;

	/**
	 * The feature id for the '<em><b>Branch Globs</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__BRANCH_GLOBS = 0;

	/**
	 * The number of structural features of the '<em>Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.PushTriggerImpl <em>Push Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PushTriggerImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getPushTrigger()
	 * @generated
	 */
	int PUSH_TRIGGER = 13;

	/**
	 * The feature id for the '<em><b>Branch Globs</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_TRIGGER__BRANCH_GLOBS = TRIGGER__BRANCH_GLOBS;

	/**
	 * The feature id for the '<em><b>Tag Globs</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_TRIGGER__TAG_GLOBS = TRIGGER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Push Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_TRIGGER_FEATURE_COUNT = TRIGGER_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Push Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PUSH_TRIGGER_OPERATION_COUNT = TRIGGER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.PullRequestTriggerImpl <em>Pull Request Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PullRequestTriggerImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getPullRequestTrigger()
	 * @generated
	 */
	int PULL_REQUEST_TRIGGER = 14;

	/**
	 * The feature id for the '<em><b>Branch Globs</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PULL_REQUEST_TRIGGER__BRANCH_GLOBS = TRIGGER__BRANCH_GLOBS;

	/**
	 * The number of structural features of the '<em>Pull Request Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PULL_REQUEST_TRIGGER_FEATURE_COUNT = TRIGGER_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Pull Request Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PULL_REQUEST_TRIGGER_OPERATION_COUNT = TRIGGER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.ManualTriggerImpl <em>Manual Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.ManualTriggerImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getManualTrigger()
	 * @generated
	 */
	int MANUAL_TRIGGER = 15;

	/**
	 * The feature id for the '<em><b>Branch Globs</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANUAL_TRIGGER__BRANCH_GLOBS = TRIGGER__BRANCH_GLOBS;

	/**
	 * The number of structural features of the '<em>Manual Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANUAL_TRIGGER_FEATURE_COUNT = TRIGGER_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Manual Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANUAL_TRIGGER_OPERATION_COUNT = TRIGGER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.ScheduledTriggerImpl <em>Scheduled Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.ScheduledTriggerImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getScheduledTrigger()
	 * @generated
	 */
	int SCHEDULED_TRIGGER = 16;

	/**
	 * The feature id for the '<em><b>Branch Globs</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULED_TRIGGER__BRANCH_GLOBS = TRIGGER__BRANCH_GLOBS;

	/**
	 * The feature id for the '<em><b>Crons</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULED_TRIGGER__CRONS = TRIGGER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Scheduled Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULED_TRIGGER_FEATURE_COUNT = TRIGGER_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Scheduled Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULED_TRIGGER_OPERATION_COUNT = TRIGGER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.MatrixImpl <em>Matrix</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.MatrixImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getMatrix()
	 * @generated
	 */
	int MATRIX = 17;

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
	 * The feature id for the '<em><b>Fail Fast</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATRIX__FAIL_FAST = 3;

	/**
	 * The number of structural features of the '<em>Matrix</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATRIX_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Matrix</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATRIX_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.MatrixAxisImpl <em>Matrix Axis</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.MatrixAxisImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getMatrixAxis()
	 * @generated
	 */
	int MATRIX_AXIS = 18;

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
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.MatrixCombinationImpl <em>Matrix Combination</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.MatrixCombinationImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getMatrixCombination()
	 * @generated
	 */
	int MATRIX_COMBINATION = 19;

	/**
	 * The feature id for the '<em><b>Combination Entries</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATRIX_COMBINATION__COMBINATION_ENTRIES = 0;

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
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.ParameterImpl <em>Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.ParameterImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getParameter()
	 * @generated
	 */
	int PARAMETER = 20;

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
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.InputImpl <em>Input</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.InputImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getInput()
	 * @generated
	 */
	int INPUT = 21;

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
	 * The feature id for the '<em><b>Default Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT__DEFAULT_VALUE = PARAMETER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT__REQUIRED = PARAMETER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Choices</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT__CHOICES = PARAMETER_FEATURE_COUNT + 3;

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
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.OutputImpl <em>Output</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.OutputImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getOutput()
	 * @generated
	 */
	int OUTPUT = 22;

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
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.StepImpl <em>Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.StepImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getStep()
	 * @generated
	 */
	int STEP = 23;

	/**
	 * The number of structural features of the '<em>Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.ConditionalStepImpl <em>Conditional Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.ConditionalStepImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getConditionalStep()
	 * @generated
	 */
	int CONDITIONAL_STEP = 24;

	/**
	 * The feature id for the '<em><b>If Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_STEP__IF_CONDITION = STEP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Then Run</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_STEP__THEN_RUN = STEP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Else Run</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_STEP__ELSE_RUN = STEP_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Conditional Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_STEP_FEATURE_COUNT = STEP_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Conditional Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_STEP_OPERATION_COUNT = STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.NonConditionalStepImpl <em>Non Conditional Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.NonConditionalStepImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getNonConditionalStep()
	 * @generated
	 */
	int NON_CONDITIONAL_STEP = 25;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NON_CONDITIONAL_STEP__ID = STEP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NON_CONDITIONAL_STEP__NAME = STEP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Environment Variables</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NON_CONDITIONAL_STEP__ENVIRONMENT_VARIABLES = STEP_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Allow Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NON_CONDITIONAL_STEP__ALLOW_FAILURE = STEP_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Working Directory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NON_CONDITIONAL_STEP__WORKING_DIRECTORY = STEP_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Timeout Minutes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NON_CONDITIONAL_STEP__TIMEOUT_MINUTES = STEP_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Non Conditional Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NON_CONDITIONAL_STEP_FEATURE_COUNT = STEP_FEATURE_COUNT + 6;

	/**
	 * The number of operations of the '<em>Non Conditional Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NON_CONDITIONAL_STEP_OPERATION_COUNT = STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.CommandImpl <em>Command</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.CommandImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getCommand()
	 * @generated
	 */
	int COMMAND = 26;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__ID = NON_CONDITIONAL_STEP__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__NAME = NON_CONDITIONAL_STEP__NAME;

	/**
	 * The feature id for the '<em><b>Environment Variables</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__ENVIRONMENT_VARIABLES = NON_CONDITIONAL_STEP__ENVIRONMENT_VARIABLES;

	/**
	 * The feature id for the '<em><b>Allow Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__ALLOW_FAILURE = NON_CONDITIONAL_STEP__ALLOW_FAILURE;

	/**
	 * The feature id for the '<em><b>Working Directory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__WORKING_DIRECTORY = NON_CONDITIONAL_STEP__WORKING_DIRECTORY;

	/**
	 * The feature id for the '<em><b>Timeout Minutes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__TIMEOUT_MINUTES = NON_CONDITIONAL_STEP__TIMEOUT_MINUTES;

	/**
	 * The feature id for the '<em><b>Program</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__PROGRAM = NON_CONDITIONAL_STEP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Shell</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__SHELL = NON_CONDITIONAL_STEP_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Command</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND_FEATURE_COUNT = NON_CONDITIONAL_STEP_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Command</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND_OPERATION_COUNT = NON_CONDITIONAL_STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.PluginImpl <em>Plugin</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PluginImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getPlugin()
	 * @generated
	 */
	int PLUGIN = 27;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLUGIN__ID = NON_CONDITIONAL_STEP__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLUGIN__NAME = NON_CONDITIONAL_STEP__NAME;

	/**
	 * The feature id for the '<em><b>Environment Variables</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLUGIN__ENVIRONMENT_VARIABLES = NON_CONDITIONAL_STEP__ENVIRONMENT_VARIABLES;

	/**
	 * The feature id for the '<em><b>Allow Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLUGIN__ALLOW_FAILURE = NON_CONDITIONAL_STEP__ALLOW_FAILURE;

	/**
	 * The feature id for the '<em><b>Working Directory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLUGIN__WORKING_DIRECTORY = NON_CONDITIONAL_STEP__WORKING_DIRECTORY;

	/**
	 * The feature id for the '<em><b>Timeout Minutes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLUGIN__TIMEOUT_MINUTES = NON_CONDITIONAL_STEP__TIMEOUT_MINUTES;

	/**
	 * The feature id for the '<em><b>Plugin Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLUGIN__PLUGIN_NAME = NON_CONDITIONAL_STEP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLUGIN__VERSION = NON_CONDITIONAL_STEP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Kwargs</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLUGIN__KWARGS = NON_CONDITIONAL_STEP_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Plugin</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLUGIN_FEATURE_COUNT = NON_CONDITIONAL_STEP_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Plugin</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLUGIN_OPERATION_COUNT = NON_CONDITIONAL_STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.CacheImpl <em>Cache</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.CacheImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getCache()
	 * @generated
	 */
	int CACHE = 28;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CACHE__ID = NON_CONDITIONAL_STEP__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CACHE__NAME = NON_CONDITIONAL_STEP__NAME;

	/**
	 * The feature id for the '<em><b>Environment Variables</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CACHE__ENVIRONMENT_VARIABLES = NON_CONDITIONAL_STEP__ENVIRONMENT_VARIABLES;

	/**
	 * The feature id for the '<em><b>Allow Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CACHE__ALLOW_FAILURE = NON_CONDITIONAL_STEP__ALLOW_FAILURE;

	/**
	 * The feature id for the '<em><b>Working Directory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CACHE__WORKING_DIRECTORY = NON_CONDITIONAL_STEP__WORKING_DIRECTORY;

	/**
	 * The feature id for the '<em><b>Timeout Minutes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CACHE__TIMEOUT_MINUTES = NON_CONDITIONAL_STEP__TIMEOUT_MINUTES;

	/**
	 * The feature id for the '<em><b>Cache Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CACHE__CACHE_NAME = NON_CONDITIONAL_STEP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Keys</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CACHE__KEYS = NON_CONDITIONAL_STEP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Paths</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CACHE__PATHS = NON_CONDITIONAL_STEP_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Store</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CACHE__STORE = NON_CONDITIONAL_STEP_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Cache</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CACHE_FEATURE_COUNT = NON_CONDITIONAL_STEP_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Cache</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CACHE_OPERATION_COUNT = NON_CONDITIONAL_STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.ArtifactImpl <em>Artifact</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.ArtifactImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getArtifact()
	 * @generated
	 */
	int ARTIFACT = 29;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__ID = NON_CONDITIONAL_STEP__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__NAME = NON_CONDITIONAL_STEP__NAME;

	/**
	 * The feature id for the '<em><b>Environment Variables</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__ENVIRONMENT_VARIABLES = NON_CONDITIONAL_STEP__ENVIRONMENT_VARIABLES;

	/**
	 * The feature id for the '<em><b>Allow Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__ALLOW_FAILURE = NON_CONDITIONAL_STEP__ALLOW_FAILURE;

	/**
	 * The feature id for the '<em><b>Working Directory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__WORKING_DIRECTORY = NON_CONDITIONAL_STEP__WORKING_DIRECTORY;

	/**
	 * The feature id for the '<em><b>Timeout Minutes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__TIMEOUT_MINUTES = NON_CONDITIONAL_STEP__TIMEOUT_MINUTES;

	/**
	 * The feature id for the '<em><b>Artifact Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__ARTIFACT_NAME = NON_CONDITIONAL_STEP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Include Paths</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__INCLUDE_PATHS = NON_CONDITIONAL_STEP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Exclude Paths</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__EXCLUDE_PATHS = NON_CONDITIONAL_STEP_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Retention Time</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__RETENTION_TIME = NON_CONDITIONAL_STEP_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Store</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__STORE = NON_CONDITIONAL_STEP_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Artifact</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_FEATURE_COUNT = NON_CONDITIONAL_STEP_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>Artifact</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_OPERATION_COUNT = NON_CONDITIONAL_STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.CheckoutImpl <em>Checkout</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.CheckoutImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getCheckout()
	 * @generated
	 */
	int CHECKOUT = 30;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT__ID = NON_CONDITIONAL_STEP__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT__NAME = NON_CONDITIONAL_STEP__NAME;

	/**
	 * The feature id for the '<em><b>Environment Variables</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT__ENVIRONMENT_VARIABLES = NON_CONDITIONAL_STEP__ENVIRONMENT_VARIABLES;

	/**
	 * The feature id for the '<em><b>Allow Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT__ALLOW_FAILURE = NON_CONDITIONAL_STEP__ALLOW_FAILURE;

	/**
	 * The feature id for the '<em><b>Working Directory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT__WORKING_DIRECTORY = NON_CONDITIONAL_STEP__WORKING_DIRECTORY;

	/**
	 * The feature id for the '<em><b>Timeout Minutes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT__TIMEOUT_MINUTES = NON_CONDITIONAL_STEP__TIMEOUT_MINUTES;

	/**
	 * The feature id for the '<em><b>Path</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT__PATH = NON_CONDITIONAL_STEP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Checkout</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT_FEATURE_COUNT = NON_CONDITIONAL_STEP_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Checkout</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECKOUT_OPERATION_COUNT = NON_CONDITIONAL_STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.VariableDeclarationImpl <em>Variable Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.VariableDeclarationImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getVariableDeclaration()
	 * @generated
	 */
	int VARIABLE_DECLARATION = 31;

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
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.ExpressionImpl <em>Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.ExpressionImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getExpression()
	 * @generated
	 */
	int EXPRESSION = 33;

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
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.AssignmentImpl <em>Assignment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.AssignmentImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getAssignment()
	 * @generated
	 */
	int ASSIGNMENT = 32;

	/**
	 * The feature id for the '<em><b>Key</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__KEY = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__VALUE = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Assignment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Assignment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.ConcatImpl <em>Concat</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.ConcatImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getConcat()
	 * @generated
	 */
	int CONCAT = 34;

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
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.ValueImpl <em>Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.ValueImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getValue()
	 * @generated
	 */
	int VALUE = 35;

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
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.LiteralImpl <em>Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.LiteralImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getLiteral()
	 * @generated
	 */
	int LITERAL = 36;

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
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.StringLiteralImpl <em>String Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.StringLiteralImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getStringLiteral()
	 * @generated
	 */
	int STRING_LITERAL = 37;

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
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.IntegerLiteralImpl <em>Integer Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.IntegerLiteralImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getIntegerLiteral()
	 * @generated
	 */
	int INTEGER_LITERAL = 38;

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
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.DoubleLiteralImpl <em>Double Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.DoubleLiteralImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getDoubleLiteral()
	 * @generated
	 */
	int DOUBLE_LITERAL = 39;

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
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.BooleanLiteralImpl <em>Boolean Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.BooleanLiteralImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getBooleanLiteral()
	 * @generated
	 */
	int BOOLEAN_LITERAL = 40;

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
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.VariableReferenceImpl <em>Variable Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.VariableReferenceImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getVariableReference()
	 * @generated
	 */
	int VARIABLE_REFERENCE = 41;

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
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.BinaryOpImpl <em>Binary Op</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.BinaryOpImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getBinaryOp()
	 * @generated
	 */
	int BINARY_OP = 42;

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
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.EqualityOpImpl <em>Equality Op</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.EqualityOpImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getEqualityOp()
	 * @generated
	 */
	int EQUALITY_OP = 43;

	/**
	 * The feature id for the '<em><b>Lhs</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUALITY_OP__LHS = BINARY_OP__LHS;

	/**
	 * The feature id for the '<em><b>Rhs</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUALITY_OP__RHS = BINARY_OP__RHS;

	/**
	 * The feature id for the '<em><b>Op</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUALITY_OP__OP = BINARY_OP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Equality Op</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUALITY_OP_FEATURE_COUNT = BINARY_OP_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Equality Op</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQUALITY_OP_OPERATION_COUNT = BINARY_OP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.ComparisonOpImpl <em>Comparison Op</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.ComparisonOpImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getComparisonOp()
	 * @generated
	 */
	int COMPARISON_OP = 44;

	/**
	 * The feature id for the '<em><b>Lhs</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARISON_OP__LHS = BINARY_OP__LHS;

	/**
	 * The feature id for the '<em><b>Rhs</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARISON_OP__RHS = BINARY_OP__RHS;

	/**
	 * The feature id for the '<em><b>Op</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARISON_OP__OP = BINARY_OP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Comparison Op</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARISON_OP_FEATURE_COUNT = BINARY_OP_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Comparison Op</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPARISON_OP_OPERATION_COUNT = BINARY_OP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.DotOpImpl <em>Dot Op</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.DotOpImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getDotOp()
	 * @generated
	 */
	int DOT_OP = 45;

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
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.LogicalOpImpl <em>Logical Op</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.LogicalOpImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getLogicalOp()
	 * @generated
	 */
	int LOGICAL_OP = 46;

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
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.AndImpl <em>And</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.AndImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getAnd()
	 * @generated
	 */
	int AND = 47;

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
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.OrImpl <em>Or</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.OrImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getOr()
	 * @generated
	 */
	int OR = 48;

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
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.UnaryOpImpl <em>Unary Op</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.UnaryOpImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getUnaryOp()
	 * @generated
	 */
	int UNARY_OP = 49;

	/**
	 * The feature id for the '<em><b>Rhs</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_OP__RHS = EXPRESSION_FEATURE_COUNT + 0;

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
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.impl.NegationImpl <em>Negation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.impl.NegationImpl
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getNegation()
	 * @generated
	 */
	int NEGATION = 50;

	/**
	 * The feature id for the '<em><b>Rhs</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEGATION__RHS = UNARY_OP__RHS;

	/**
	 * The number of structural features of the '<em>Negation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEGATION_FEATURE_COUNT = UNARY_OP_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Negation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEGATION_OPERATION_COUNT = UNARY_OP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.INPUT_TYPE <em>INPUT TYPE</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.INPUT_TYPE
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getINPUT_TYPE()
	 * @generated
	 */
	int INPUT_TYPE = 51;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.EQUALITY_OPS <em>EQUALITY OPS</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.EQUALITY_OPS
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getEQUALITY_OPS()
	 * @generated
	 */
	int EQUALITY_OPS = 52;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.COMPARISON_OPS <em>COMPARISON OPS</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.COMPARISON_OPS
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getCOMPARISON_OPS()
	 * @generated
	 */
	int COMPARISON_OPS = 53;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.pim.pimMM.CACHE_MODE <em>CACHE MODE</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.pim.pimMM.CACHE_MODE
	 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getCACHE_MODE()
	 * @generated
	 */
	int CACHE_MODE = 54;

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.PipelineBlock <em>Pipeline Block</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pipeline Block</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.PipelineBlock
	 * @generated
	 */
	EClass getPipelineBlock();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.pim.pimMM.PipelineBlock#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.PipelineBlock#getName()
	 * @see #getPipelineBlock()
	 * @generated
	 */
	EAttribute getPipelineBlock_Name();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.pim.pimMM.PipelineBlock#getAgent <em>Agent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Agent</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.PipelineBlock#getAgent()
	 * @see #getPipelineBlock()
	 * @generated
	 */
	EReference getPipelineBlock_Agent();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.pim.pimMM.PipelineBlock#getInputs <em>Inputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Inputs</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.PipelineBlock#getInputs()
	 * @see #getPipelineBlock()
	 * @generated
	 */
	EReference getPipelineBlock_Inputs();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.pim.pimMM.PipelineBlock#getOutputs <em>Outputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Outputs</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.PipelineBlock#getOutputs()
	 * @see #getPipelineBlock()
	 * @generated
	 */
	EReference getPipelineBlock_Outputs();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.pim.pimMM.PipelineBlock#getEnvironmentVariables <em>Environment Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Environment Variables</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.PipelineBlock#getEnvironmentVariables()
	 * @see #getPipelineBlock()
	 * @generated
	 */
	EReference getPipelineBlock_EnvironmentVariables();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.pim.pimMM.PipelineBlock#getTimeoutMinutes <em>Timeout Minutes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timeout Minutes</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.PipelineBlock#getTimeoutMinutes()
	 * @see #getPipelineBlock()
	 * @generated
	 */
	EAttribute getPipelineBlock_TimeoutMinutes();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.pim.pimMM.PipelineBlock#getWorkingDirectory <em>Working Directory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Working Directory</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.PipelineBlock#getWorkingDirectory()
	 * @see #getPipelineBlock()
	 * @generated
	 */
	EReference getPipelineBlock_WorkingDirectory();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.pim.pimMM.PipelineBlock#getShell <em>Shell</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Shell</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.PipelineBlock#getShell()
	 * @see #getPipelineBlock()
	 * @generated
	 */
	EReference getPipelineBlock_Shell();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.Pipeline <em>Pipeline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pipeline</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Pipeline
	 * @generated
	 */
	EClass getPipeline();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.pim.pimMM.Pipeline#getTriggers <em>Triggers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Triggers</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Pipeline#getTriggers()
	 * @see #getPipeline()
	 * @generated
	 */
	EReference getPipeline_Triggers();

	/**
	 * Returns the meta object for the reference list '{@link com.mddoai.metamodel.pim.pimMM.Pipeline#getJobStreams <em>Job Streams</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Job Streams</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Pipeline#getJobStreams()
	 * @see #getPipeline()
	 * @generated
	 */
	EReference getPipeline_JobStreams();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.Job <em>Job</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Job</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Job
	 * @generated
	 */
	EClass getJob();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.pim.pimMM.Job#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Job#getId()
	 * @see #getJob()
	 * @generated
	 */
	EAttribute getJob_Id();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.pim.pimMM.Job#getIfCondition <em>If Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>If Condition</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Job#getIfCondition()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_IfCondition();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.pim.pimMM.Job#getServices <em>Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Services</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Job#getServices()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Services();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.pim.pimMM.Job#getMatrix <em>Matrix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Matrix</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Job#getMatrix()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Matrix();

	/**
	 * Returns the meta object for the reference list '{@link com.mddoai.metamodel.pim.pimMM.Job#getPrevious <em>Previous</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Previous</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Job#getPrevious()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Previous();

	/**
	 * Returns the meta object for the reference list '{@link com.mddoai.metamodel.pim.pimMM.Job#getNext <em>Next</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Next</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Job#getNext()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Next();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.pim.pimMM.Job#getMaxAttempts <em>Max Attempts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Attempts</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Job#getMaxAttempts()
	 * @see #getJob()
	 * @generated
	 */
	EAttribute getJob_MaxAttempts();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.pim.pimMM.Job#getAllowFailure <em>Allow Failure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Allow Failure</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Job#getAllowFailure()
	 * @see #getJob()
	 * @generated
	 */
	EAttribute getJob_AllowFailure();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.ScriptJob <em>Script Job</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Script Job</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.ScriptJob
	 * @generated
	 */
	EClass getScriptJob();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.pim.pimMM.ScriptJob#getSteps <em>Steps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Steps</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.ScriptJob#getSteps()
	 * @see #getScriptJob()
	 * @generated
	 */
	EReference getScriptJob_Steps();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.PipelineCallJob <em>Pipeline Call Job</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pipeline Call Job</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.PipelineCallJob
	 * @generated
	 */
	EClass getPipelineCallJob();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.pim.pimMM.PipelineCallJob#getPipelinePath <em>Pipeline Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Pipeline Path</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.PipelineCallJob#getPipelinePath()
	 * @see #getPipelineCallJob()
	 * @generated
	 */
	EReference getPipelineCallJob_PipelinePath();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.pim.pimMM.PipelineCallJob#getArgs <em>Args</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Args</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.PipelineCallJob#getArgs()
	 * @see #getPipelineCallJob()
	 * @generated
	 */
	EReference getPipelineCallJob_Args();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.Agent <em>Agent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Agent</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Agent
	 * @generated
	 */
	EClass getAgent();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.CustomAgent <em>Custom Agent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Custom Agent</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.CustomAgent
	 * @generated
	 */
	EClass getCustomAgent();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.pim.pimMM.CustomAgent#getLabels <em>Labels</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Labels</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.CustomAgent#getLabels()
	 * @see #getCustomAgent()
	 * @generated
	 */
	EReference getCustomAgent_Labels();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.PresetAgent <em>Preset Agent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Preset Agent</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.PresetAgent
	 * @generated
	 */
	EClass getPresetAgent();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.pim.pimMM.PresetAgent#getImage <em>Image</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Image</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.PresetAgent#getImage()
	 * @see #getPresetAgent()
	 * @generated
	 */
	EReference getPresetAgent_Image();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.LinuxAgent <em>Linux Agent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Linux Agent</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.LinuxAgent
	 * @generated
	 */
	EClass getLinuxAgent();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.pim.pimMM.LinuxAgent#getContainer <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Container</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.LinuxAgent#getContainer()
	 * @see #getLinuxAgent()
	 * @generated
	 */
	EReference getLinuxAgent_Container();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.WindowsAgent <em>Windows Agent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Windows Agent</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.WindowsAgent
	 * @generated
	 */
	EClass getWindowsAgent();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.MacOSAgent <em>Mac OS Agent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mac OS Agent</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.MacOSAgent
	 * @generated
	 */
	EClass getMacOSAgent();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.pim.pimMM.MacOSAgent#getXcode <em>Xcode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Xcode</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.MacOSAgent#getXcode()
	 * @see #getMacOSAgent()
	 * @generated
	 */
	EReference getMacOSAgent_Xcode();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.DockerContainer <em>Docker Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Docker Container</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.DockerContainer
	 * @generated
	 */
	EClass getDockerContainer();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.pim.pimMM.DockerContainer#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.DockerContainer#getLabel()
	 * @see #getDockerContainer()
	 * @generated
	 */
	EAttribute getDockerContainer_Label();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.pim.pimMM.DockerContainer#getImage <em>Image</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Image</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.DockerContainer#getImage()
	 * @see #getDockerContainer()
	 * @generated
	 */
	EReference getDockerContainer_Image();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.pim.pimMM.DockerContainer#getEnvironmentVariables <em>Environment Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Environment Variables</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.DockerContainer#getEnvironmentVariables()
	 * @see #getDockerContainer()
	 * @generated
	 */
	EReference getDockerContainer_EnvironmentVariables();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.pim.pimMM.DockerContainer#getVolumes <em>Volumes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Volumes</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.DockerContainer#getVolumes()
	 * @see #getDockerContainer()
	 * @generated
	 */
	EReference getDockerContainer_Volumes();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.pim.pimMM.DockerContainer#getPorts <em>Ports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Ports</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.DockerContainer#getPorts()
	 * @see #getDockerContainer()
	 * @generated
	 */
	EReference getDockerContainer_Ports();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.pim.pimMM.DockerContainer#getOptions <em>Options</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Options</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.DockerContainer#getOptions()
	 * @see #getDockerContainer()
	 * @generated
	 */
	EAttribute getDockerContainer_Options();

	/**
	 * Returns the meta object for the reference '{@link com.mddoai.metamodel.pim.pimMM.DockerContainer#getRegistryUsername <em>Registry Username</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Registry Username</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.DockerContainer#getRegistryUsername()
	 * @see #getDockerContainer()
	 * @generated
	 */
	EReference getDockerContainer_RegistryUsername();

	/**
	 * Returns the meta object for the reference '{@link com.mddoai.metamodel.pim.pimMM.DockerContainer#getRegistryPassword <em>Registry Password</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Registry Password</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.DockerContainer#getRegistryPassword()
	 * @see #getDockerContainer()
	 * @generated
	 */
	EReference getDockerContainer_RegistryPassword();

	/**
	 * Returns the meta object for the reference '{@link com.mddoai.metamodel.pim.pimMM.DockerContainer#getNetwork <em>Network</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Network</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.DockerContainer#getNetwork()
	 * @see #getDockerContainer()
	 * @generated
	 */
	EReference getDockerContainer_Network();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.Trigger <em>Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Trigger</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Trigger
	 * @generated
	 */
	EClass getTrigger();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.pim.pimMM.Trigger#getBranchGlobs <em>Branch Globs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Branch Globs</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Trigger#getBranchGlobs()
	 * @see #getTrigger()
	 * @generated
	 */
	EAttribute getTrigger_BranchGlobs();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.PushTrigger <em>Push Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Push Trigger</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.PushTrigger
	 * @generated
	 */
	EClass getPushTrigger();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.pim.pimMM.PushTrigger#getTagGlobs <em>Tag Globs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Tag Globs</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.PushTrigger#getTagGlobs()
	 * @see #getPushTrigger()
	 * @generated
	 */
	EAttribute getPushTrigger_TagGlobs();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.PullRequestTrigger <em>Pull Request Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pull Request Trigger</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.PullRequestTrigger
	 * @generated
	 */
	EClass getPullRequestTrigger();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.ManualTrigger <em>Manual Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Manual Trigger</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.ManualTrigger
	 * @generated
	 */
	EClass getManualTrigger();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.ScheduledTrigger <em>Scheduled Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Scheduled Trigger</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.ScheduledTrigger
	 * @generated
	 */
	EClass getScheduledTrigger();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.pim.pimMM.ScheduledTrigger#getCrons <em>Crons</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Crons</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.ScheduledTrigger#getCrons()
	 * @see #getScheduledTrigger()
	 * @generated
	 */
	EAttribute getScheduledTrigger_Crons();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.Matrix <em>Matrix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Matrix</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Matrix
	 * @generated
	 */
	EClass getMatrix();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.pim.pimMM.Matrix#getAxes <em>Axes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Axes</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Matrix#getAxes()
	 * @see #getMatrix()
	 * @generated
	 */
	EReference getMatrix_Axes();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.pim.pimMM.Matrix#getIncludes <em>Includes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Includes</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Matrix#getIncludes()
	 * @see #getMatrix()
	 * @generated
	 */
	EReference getMatrix_Includes();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.pim.pimMM.Matrix#getExcludes <em>Excludes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Excludes</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Matrix#getExcludes()
	 * @see #getMatrix()
	 * @generated
	 */
	EReference getMatrix_Excludes();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.pim.pimMM.Matrix#getFailFast <em>Fail Fast</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fail Fast</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Matrix#getFailFast()
	 * @see #getMatrix()
	 * @generated
	 */
	EAttribute getMatrix_FailFast();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.MatrixAxis <em>Matrix Axis</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Matrix Axis</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.MatrixAxis
	 * @generated
	 */
	EClass getMatrixAxis();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.pim.pimMM.MatrixAxis#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Name</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.MatrixAxis#getName()
	 * @see #getMatrixAxis()
	 * @generated
	 */
	EReference getMatrixAxis_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.pim.pimMM.MatrixAxis#getCells <em>Cells</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Cells</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.MatrixAxis#getCells()
	 * @see #getMatrixAxis()
	 * @generated
	 */
	EReference getMatrixAxis_Cells();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.MatrixCombination <em>Matrix Combination</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Matrix Combination</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.MatrixCombination
	 * @generated
	 */
	EClass getMatrixCombination();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.pim.pimMM.MatrixCombination#getCombinationEntries <em>Combination Entries</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Combination Entries</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.MatrixCombination#getCombinationEntries()
	 * @see #getMatrixCombination()
	 * @generated
	 */
	EReference getMatrixCombination_CombinationEntries();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.Parameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Parameter
	 * @generated
	 */
	EClass getParameter();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.pim.pimMM.Parameter#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Id</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Parameter#getId()
	 * @see #getParameter()
	 * @generated
	 */
	EReference getParameter_Id();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.pim.pimMM.Parameter#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Description</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Parameter#getDescription()
	 * @see #getParameter()
	 * @generated
	 */
	EReference getParameter_Description();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.Input <em>Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Input</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Input
	 * @generated
	 */
	EClass getInput();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.pim.pimMM.Input#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Input#getType()
	 * @see #getInput()
	 * @generated
	 */
	EAttribute getInput_Type();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.pim.pimMM.Input#getDefaultValue <em>Default Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Default Value</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Input#getDefaultValue()
	 * @see #getInput()
	 * @generated
	 */
	EReference getInput_DefaultValue();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.pim.pimMM.Input#isRequired <em>Required</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Required</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Input#isRequired()
	 * @see #getInput()
	 * @generated
	 */
	EAttribute getInput_Required();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.pim.pimMM.Input#getChoices <em>Choices</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Choices</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Input#getChoices()
	 * @see #getInput()
	 * @generated
	 */
	EAttribute getInput_Choices();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.Output <em>Output</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Output</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Output
	 * @generated
	 */
	EClass getOutput();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.pim.pimMM.Output#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Output#getValue()
	 * @see #getOutput()
	 * @generated
	 */
	EReference getOutput_Value();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.Step <em>Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Step</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Step
	 * @generated
	 */
	EClass getStep();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.ConditionalStep <em>Conditional Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Conditional Step</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.ConditionalStep
	 * @generated
	 */
	EClass getConditionalStep();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.pim.pimMM.ConditionalStep#getIfCondition <em>If Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>If Condition</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.ConditionalStep#getIfCondition()
	 * @see #getConditionalStep()
	 * @generated
	 */
	EReference getConditionalStep_IfCondition();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.pim.pimMM.ConditionalStep#getThenRun <em>Then Run</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Then Run</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.ConditionalStep#getThenRun()
	 * @see #getConditionalStep()
	 * @generated
	 */
	EReference getConditionalStep_ThenRun();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.pim.pimMM.ConditionalStep#getElseRun <em>Else Run</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Else Run</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.ConditionalStep#getElseRun()
	 * @see #getConditionalStep()
	 * @generated
	 */
	EReference getConditionalStep_ElseRun();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.NonConditionalStep <em>Non Conditional Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Non Conditional Step</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.NonConditionalStep
	 * @generated
	 */
	EClass getNonConditionalStep();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.pim.pimMM.NonConditionalStep#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.NonConditionalStep#getId()
	 * @see #getNonConditionalStep()
	 * @generated
	 */
	EAttribute getNonConditionalStep_Id();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.pim.pimMM.NonConditionalStep#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.NonConditionalStep#getName()
	 * @see #getNonConditionalStep()
	 * @generated
	 */
	EAttribute getNonConditionalStep_Name();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.pim.pimMM.NonConditionalStep#getEnvironmentVariables <em>Environment Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Environment Variables</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.NonConditionalStep#getEnvironmentVariables()
	 * @see #getNonConditionalStep()
	 * @generated
	 */
	EReference getNonConditionalStep_EnvironmentVariables();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.pim.pimMM.NonConditionalStep#getAllowFailure <em>Allow Failure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Allow Failure</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.NonConditionalStep#getAllowFailure()
	 * @see #getNonConditionalStep()
	 * @generated
	 */
	EAttribute getNonConditionalStep_AllowFailure();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.pim.pimMM.NonConditionalStep#getWorkingDirectory <em>Working Directory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Working Directory</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.NonConditionalStep#getWorkingDirectory()
	 * @see #getNonConditionalStep()
	 * @generated
	 */
	EReference getNonConditionalStep_WorkingDirectory();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.pim.pimMM.NonConditionalStep#getTimeoutMinutes <em>Timeout Minutes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Timeout Minutes</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.NonConditionalStep#getTimeoutMinutes()
	 * @see #getNonConditionalStep()
	 * @generated
	 */
	EReference getNonConditionalStep_TimeoutMinutes();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.Command <em>Command</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Command</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Command
	 * @generated
	 */
	EClass getCommand();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.pim.pimMM.Command#getProgram <em>Program</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Program</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Command#getProgram()
	 * @see #getCommand()
	 * @generated
	 */
	EReference getCommand_Program();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.pim.pimMM.Command#getShell <em>Shell</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Shell</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Command#getShell()
	 * @see #getCommand()
	 * @generated
	 */
	EReference getCommand_Shell();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.Plugin <em>Plugin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Plugin</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Plugin
	 * @generated
	 */
	EClass getPlugin();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.pim.pimMM.Plugin#getPluginName <em>Plugin Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Plugin Name</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Plugin#getPluginName()
	 * @see #getPlugin()
	 * @generated
	 */
	EAttribute getPlugin_PluginName();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.pim.pimMM.Plugin#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Plugin#getVersion()
	 * @see #getPlugin()
	 * @generated
	 */
	EAttribute getPlugin_Version();

	/**
	 * Returns the meta object for the map '{@link com.mddoai.metamodel.pim.pimMM.Plugin#getKwargs <em>Kwargs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Kwargs</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Plugin#getKwargs()
	 * @see #getPlugin()
	 * @generated
	 */
	EReference getPlugin_Kwargs();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.Cache <em>Cache</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cache</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Cache
	 * @generated
	 */
	EClass getCache();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.pim.pimMM.Cache#getCacheName <em>Cache Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Cache Name</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Cache#getCacheName()
	 * @see #getCache()
	 * @generated
	 */
	EReference getCache_CacheName();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.pim.pimMM.Cache#getKeys <em>Keys</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Keys</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Cache#getKeys()
	 * @see #getCache()
	 * @generated
	 */
	EReference getCache_Keys();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.pim.pimMM.Cache#getPaths <em>Paths</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Paths</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Cache#getPaths()
	 * @see #getCache()
	 * @generated
	 */
	EReference getCache_Paths();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.pim.pimMM.Cache#getStore <em>Store</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Store</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Cache#getStore()
	 * @see #getCache()
	 * @generated
	 */
	EAttribute getCache_Store();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.Artifact <em>Artifact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Artifact</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Artifact
	 * @generated
	 */
	EClass getArtifact();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.pim.pimMM.Artifact#getArtifactName <em>Artifact Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Artifact Name</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Artifact#getArtifactName()
	 * @see #getArtifact()
	 * @generated
	 */
	EReference getArtifact_ArtifactName();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.pim.pimMM.Artifact#getIncludePaths <em>Include Paths</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Include Paths</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Artifact#getIncludePaths()
	 * @see #getArtifact()
	 * @generated
	 */
	EReference getArtifact_IncludePaths();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.pim.pimMM.Artifact#getExcludePaths <em>Exclude Paths</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Exclude Paths</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Artifact#getExcludePaths()
	 * @see #getArtifact()
	 * @generated
	 */
	EReference getArtifact_ExcludePaths();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.pim.pimMM.Artifact#getRetentionTime <em>Retention Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Retention Time</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Artifact#getRetentionTime()
	 * @see #getArtifact()
	 * @generated
	 */
	EReference getArtifact_RetentionTime();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.pim.pimMM.Artifact#isStore <em>Store</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Store</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Artifact#isStore()
	 * @see #getArtifact()
	 * @generated
	 */
	EAttribute getArtifact_Store();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.Checkout <em>Checkout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Checkout</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Checkout
	 * @generated
	 */
	EClass getCheckout();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.pim.pimMM.Checkout#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Path</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Checkout#getPath()
	 * @see #getCheckout()
	 * @generated
	 */
	EReference getCheckout_Path();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.VariableDeclaration <em>Variable Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable Declaration</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.VariableDeclaration
	 * @generated
	 */
	EClass getVariableDeclaration();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.pim.pimMM.VariableDeclaration#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.VariableDeclaration#getName()
	 * @see #getVariableDeclaration()
	 * @generated
	 */
	EAttribute getVariableDeclaration_Name();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Assignment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Assignment</em>'.
	 * @see java.util.Map.Entry
	 * @model keyType="com.mddoai.metamodel.pim.pimMM.VariableDeclaration" keyContainment="true" keyRequired="true"
	 *        valueType="com.mddoai.metamodel.pim.pimMM.Expression" valueContainment="true" valueRequired="true"
	 * @generated
	 */
	EClass getAssignment();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getAssignment()
	 * @generated
	 */
	EReference getAssignment_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getAssignment()
	 * @generated
	 */
	EReference getAssignment_Value();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.Expression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expression</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Expression
	 * @generated
	 */
	EClass getExpression();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.Concat <em>Concat</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Concat</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Concat
	 * @generated
	 */
	EClass getConcat();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.pim.pimMM.Concat#getExpressions <em>Expressions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Expressions</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Concat#getExpressions()
	 * @see #getConcat()
	 * @generated
	 */
	EReference getConcat_Expressions();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.Value <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Value
	 * @generated
	 */
	EClass getValue();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.Literal <em>Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Literal</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Literal
	 * @generated
	 */
	EClass getLiteral();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.StringLiteral <em>String Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Literal</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.StringLiteral
	 * @generated
	 */
	EClass getStringLiteral();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.pim.pimMM.StringLiteral#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.StringLiteral#getValue()
	 * @see #getStringLiteral()
	 * @generated
	 */
	EAttribute getStringLiteral_Value();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.IntegerLiteral <em>Integer Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Integer Literal</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.IntegerLiteral
	 * @generated
	 */
	EClass getIntegerLiteral();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.pim.pimMM.IntegerLiteral#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.IntegerLiteral#getValue()
	 * @see #getIntegerLiteral()
	 * @generated
	 */
	EAttribute getIntegerLiteral_Value();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.DoubleLiteral <em>Double Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Double Literal</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.DoubleLiteral
	 * @generated
	 */
	EClass getDoubleLiteral();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.pim.pimMM.DoubleLiteral#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.DoubleLiteral#getValue()
	 * @see #getDoubleLiteral()
	 * @generated
	 */
	EAttribute getDoubleLiteral_Value();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.BooleanLiteral <em>Boolean Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Literal</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.BooleanLiteral
	 * @generated
	 */
	EClass getBooleanLiteral();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.pim.pimMM.BooleanLiteral#isValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.BooleanLiteral#isValue()
	 * @see #getBooleanLiteral()
	 * @generated
	 */
	EAttribute getBooleanLiteral_Value();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.VariableReference <em>Variable Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable Reference</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.VariableReference
	 * @generated
	 */
	EClass getVariableReference();

	/**
	 * Returns the meta object for the reference '{@link com.mddoai.metamodel.pim.pimMM.VariableReference#getReference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Reference</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.VariableReference#getReference()
	 * @see #getVariableReference()
	 * @generated
	 */
	EReference getVariableReference_Reference();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.BinaryOp <em>Binary Op</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Binary Op</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.BinaryOp
	 * @generated
	 */
	EClass getBinaryOp();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.pim.pimMM.BinaryOp#getLhs <em>Lhs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Lhs</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.BinaryOp#getLhs()
	 * @see #getBinaryOp()
	 * @generated
	 */
	EReference getBinaryOp_Lhs();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.pim.pimMM.BinaryOp#getRhs <em>Rhs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Rhs</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.BinaryOp#getRhs()
	 * @see #getBinaryOp()
	 * @generated
	 */
	EReference getBinaryOp_Rhs();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.EqualityOp <em>Equality Op</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Equality Op</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.EqualityOp
	 * @generated
	 */
	EClass getEqualityOp();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.pim.pimMM.EqualityOp#getOp <em>Op</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Op</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.EqualityOp#getOp()
	 * @see #getEqualityOp()
	 * @generated
	 */
	EAttribute getEqualityOp_Op();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.ComparisonOp <em>Comparison Op</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Comparison Op</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.ComparisonOp
	 * @generated
	 */
	EClass getComparisonOp();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.pim.pimMM.ComparisonOp#getOp <em>Op</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Op</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.ComparisonOp#getOp()
	 * @see #getComparisonOp()
	 * @generated
	 */
	EAttribute getComparisonOp_Op();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.DotOp <em>Dot Op</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dot Op</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.DotOp
	 * @generated
	 */
	EClass getDotOp();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.LogicalOp <em>Logical Op</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Logical Op</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.LogicalOp
	 * @generated
	 */
	EClass getLogicalOp();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.And <em>And</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>And</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.And
	 * @generated
	 */
	EClass getAnd();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.Or <em>Or</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Or</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Or
	 * @generated
	 */
	EClass getOr();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.UnaryOp <em>Unary Op</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unary Op</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.UnaryOp
	 * @generated
	 */
	EClass getUnaryOp();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.pim.pimMM.UnaryOp#getRhs <em>Rhs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Rhs</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.UnaryOp#getRhs()
	 * @see #getUnaryOp()
	 * @generated
	 */
	EReference getUnaryOp_Rhs();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.pim.pimMM.Negation <em>Negation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Negation</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.Negation
	 * @generated
	 */
	EClass getNegation();

	/**
	 * Returns the meta object for enum '{@link com.mddoai.metamodel.pim.pimMM.INPUT_TYPE <em>INPUT TYPE</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>INPUT TYPE</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.INPUT_TYPE
	 * @generated
	 */
	EEnum getINPUT_TYPE();

	/**
	 * Returns the meta object for enum '{@link com.mddoai.metamodel.pim.pimMM.EQUALITY_OPS <em>EQUALITY OPS</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>EQUALITY OPS</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.EQUALITY_OPS
	 * @generated
	 */
	EEnum getEQUALITY_OPS();

	/**
	 * Returns the meta object for enum '{@link com.mddoai.metamodel.pim.pimMM.COMPARISON_OPS <em>COMPARISON OPS</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>COMPARISON OPS</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.COMPARISON_OPS
	 * @generated
	 */
	EEnum getCOMPARISON_OPS();

	/**
	 * Returns the meta object for enum '{@link com.mddoai.metamodel.pim.pimMM.CACHE_MODE <em>CACHE MODE</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>CACHE MODE</em>'.
	 * @see com.mddoai.metamodel.pim.pimMM.CACHE_MODE
	 * @generated
	 */
	EEnum getCACHE_MODE();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PimMMFactory getPimMMFactory();

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
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.PipelineBlockImpl <em>Pipeline Block</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PipelineBlockImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getPipelineBlock()
		 * @generated
		 */
		EClass PIPELINE_BLOCK = eINSTANCE.getPipelineBlock();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PIPELINE_BLOCK__NAME = eINSTANCE.getPipelineBlock_Name();

		/**
		 * The meta object literal for the '<em><b>Agent</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIPELINE_BLOCK__AGENT = eINSTANCE.getPipelineBlock_Agent();

		/**
		 * The meta object literal for the '<em><b>Inputs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIPELINE_BLOCK__INPUTS = eINSTANCE.getPipelineBlock_Inputs();

		/**
		 * The meta object literal for the '<em><b>Outputs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIPELINE_BLOCK__OUTPUTS = eINSTANCE.getPipelineBlock_Outputs();

		/**
		 * The meta object literal for the '<em><b>Environment Variables</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIPELINE_BLOCK__ENVIRONMENT_VARIABLES = eINSTANCE.getPipelineBlock_EnvironmentVariables();

		/**
		 * The meta object literal for the '<em><b>Timeout Minutes</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PIPELINE_BLOCK__TIMEOUT_MINUTES = eINSTANCE.getPipelineBlock_TimeoutMinutes();

		/**
		 * The meta object literal for the '<em><b>Working Directory</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIPELINE_BLOCK__WORKING_DIRECTORY = eINSTANCE.getPipelineBlock_WorkingDirectory();

		/**
		 * The meta object literal for the '<em><b>Shell</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIPELINE_BLOCK__SHELL = eINSTANCE.getPipelineBlock_Shell();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.PipelineImpl <em>Pipeline</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PipelineImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getPipeline()
		 * @generated
		 */
		EClass PIPELINE = eINSTANCE.getPipeline();

		/**
		 * The meta object literal for the '<em><b>Triggers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIPELINE__TRIGGERS = eINSTANCE.getPipeline_Triggers();

		/**
		 * The meta object literal for the '<em><b>Job Streams</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIPELINE__JOB_STREAMS = eINSTANCE.getPipeline_JobStreams();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.JobImpl <em>Job</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.JobImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getJob()
		 * @generated
		 */
		EClass JOB = eINSTANCE.getJob();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOB__ID = eINSTANCE.getJob_Id();

		/**
		 * The meta object literal for the '<em><b>If Condition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__IF_CONDITION = eINSTANCE.getJob_IfCondition();

		/**
		 * The meta object literal for the '<em><b>Services</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__SERVICES = eINSTANCE.getJob_Services();

		/**
		 * The meta object literal for the '<em><b>Matrix</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__MATRIX = eINSTANCE.getJob_Matrix();

		/**
		 * The meta object literal for the '<em><b>Previous</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__PREVIOUS = eINSTANCE.getJob_Previous();

		/**
		 * The meta object literal for the '<em><b>Next</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__NEXT = eINSTANCE.getJob_Next();

		/**
		 * The meta object literal for the '<em><b>Max Attempts</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOB__MAX_ATTEMPTS = eINSTANCE.getJob_MaxAttempts();

		/**
		 * The meta object literal for the '<em><b>Allow Failure</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOB__ALLOW_FAILURE = eINSTANCE.getJob_AllowFailure();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.ScriptJobImpl <em>Script Job</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.ScriptJobImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getScriptJob()
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
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.PipelineCallJobImpl <em>Pipeline Call Job</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PipelineCallJobImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getPipelineCallJob()
		 * @generated
		 */
		EClass PIPELINE_CALL_JOB = eINSTANCE.getPipelineCallJob();

		/**
		 * The meta object literal for the '<em><b>Pipeline Path</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIPELINE_CALL_JOB__PIPELINE_PATH = eINSTANCE.getPipelineCallJob_PipelinePath();

		/**
		 * The meta object literal for the '<em><b>Args</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIPELINE_CALL_JOB__ARGS = eINSTANCE.getPipelineCallJob_Args();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.AgentImpl <em>Agent</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.AgentImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getAgent()
		 * @generated
		 */
		EClass AGENT = eINSTANCE.getAgent();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.CustomAgentImpl <em>Custom Agent</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.CustomAgentImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getCustomAgent()
		 * @generated
		 */
		EClass CUSTOM_AGENT = eINSTANCE.getCustomAgent();

		/**
		 * The meta object literal for the '<em><b>Labels</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CUSTOM_AGENT__LABELS = eINSTANCE.getCustomAgent_Labels();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.PresetAgentImpl <em>Preset Agent</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PresetAgentImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getPresetAgent()
		 * @generated
		 */
		EClass PRESET_AGENT = eINSTANCE.getPresetAgent();

		/**
		 * The meta object literal for the '<em><b>Image</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRESET_AGENT__IMAGE = eINSTANCE.getPresetAgent_Image();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.LinuxAgentImpl <em>Linux Agent</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.LinuxAgentImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getLinuxAgent()
		 * @generated
		 */
		EClass LINUX_AGENT = eINSTANCE.getLinuxAgent();

		/**
		 * The meta object literal for the '<em><b>Container</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LINUX_AGENT__CONTAINER = eINSTANCE.getLinuxAgent_Container();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.WindowsAgentImpl <em>Windows Agent</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.WindowsAgentImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getWindowsAgent()
		 * @generated
		 */
		EClass WINDOWS_AGENT = eINSTANCE.getWindowsAgent();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.MacOSAgentImpl <em>Mac OS Agent</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.MacOSAgentImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getMacOSAgent()
		 * @generated
		 */
		EClass MAC_OS_AGENT = eINSTANCE.getMacOSAgent();

		/**
		 * The meta object literal for the '<em><b>Xcode</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAC_OS_AGENT__XCODE = eINSTANCE.getMacOSAgent_Xcode();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.DockerContainerImpl <em>Docker Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.DockerContainerImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getDockerContainer()
		 * @generated
		 */
		EClass DOCKER_CONTAINER = eINSTANCE.getDockerContainer();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCKER_CONTAINER__LABEL = eINSTANCE.getDockerContainer_Label();

		/**
		 * The meta object literal for the '<em><b>Image</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCKER_CONTAINER__IMAGE = eINSTANCE.getDockerContainer_Image();

		/**
		 * The meta object literal for the '<em><b>Environment Variables</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCKER_CONTAINER__ENVIRONMENT_VARIABLES = eINSTANCE.getDockerContainer_EnvironmentVariables();

		/**
		 * The meta object literal for the '<em><b>Volumes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCKER_CONTAINER__VOLUMES = eINSTANCE.getDockerContainer_Volumes();

		/**
		 * The meta object literal for the '<em><b>Ports</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCKER_CONTAINER__PORTS = eINSTANCE.getDockerContainer_Ports();

		/**
		 * The meta object literal for the '<em><b>Options</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCKER_CONTAINER__OPTIONS = eINSTANCE.getDockerContainer_Options();

		/**
		 * The meta object literal for the '<em><b>Registry Username</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCKER_CONTAINER__REGISTRY_USERNAME = eINSTANCE.getDockerContainer_RegistryUsername();

		/**
		 * The meta object literal for the '<em><b>Registry Password</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCKER_CONTAINER__REGISTRY_PASSWORD = eINSTANCE.getDockerContainer_RegistryPassword();

		/**
		 * The meta object literal for the '<em><b>Network</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCKER_CONTAINER__NETWORK = eINSTANCE.getDockerContainer_Network();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.TriggerImpl <em>Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.TriggerImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getTrigger()
		 * @generated
		 */
		EClass TRIGGER = eINSTANCE.getTrigger();

		/**
		 * The meta object literal for the '<em><b>Branch Globs</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRIGGER__BRANCH_GLOBS = eINSTANCE.getTrigger_BranchGlobs();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.PushTriggerImpl <em>Push Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PushTriggerImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getPushTrigger()
		 * @generated
		 */
		EClass PUSH_TRIGGER = eINSTANCE.getPushTrigger();

		/**
		 * The meta object literal for the '<em><b>Tag Globs</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PUSH_TRIGGER__TAG_GLOBS = eINSTANCE.getPushTrigger_TagGlobs();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.PullRequestTriggerImpl <em>Pull Request Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PullRequestTriggerImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getPullRequestTrigger()
		 * @generated
		 */
		EClass PULL_REQUEST_TRIGGER = eINSTANCE.getPullRequestTrigger();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.ManualTriggerImpl <em>Manual Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.ManualTriggerImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getManualTrigger()
		 * @generated
		 */
		EClass MANUAL_TRIGGER = eINSTANCE.getManualTrigger();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.ScheduledTriggerImpl <em>Scheduled Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.ScheduledTriggerImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getScheduledTrigger()
		 * @generated
		 */
		EClass SCHEDULED_TRIGGER = eINSTANCE.getScheduledTrigger();

		/**
		 * The meta object literal for the '<em><b>Crons</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCHEDULED_TRIGGER__CRONS = eINSTANCE.getScheduledTrigger_Crons();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.MatrixImpl <em>Matrix</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.MatrixImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getMatrix()
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
		 * The meta object literal for the '<em><b>Fail Fast</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MATRIX__FAIL_FAST = eINSTANCE.getMatrix_FailFast();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.MatrixAxisImpl <em>Matrix Axis</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.MatrixAxisImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getMatrixAxis()
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
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.MatrixCombinationImpl <em>Matrix Combination</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.MatrixCombinationImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getMatrixCombination()
		 * @generated
		 */
		EClass MATRIX_COMBINATION = eINSTANCE.getMatrixCombination();

		/**
		 * The meta object literal for the '<em><b>Combination Entries</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MATRIX_COMBINATION__COMBINATION_ENTRIES = eINSTANCE.getMatrixCombination_CombinationEntries();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.ParameterImpl <em>Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.ParameterImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getParameter()
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
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.InputImpl <em>Input</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.InputImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getInput()
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
		 * The meta object literal for the '<em><b>Default Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INPUT__DEFAULT_VALUE = eINSTANCE.getInput_DefaultValue();

		/**
		 * The meta object literal for the '<em><b>Required</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INPUT__REQUIRED = eINSTANCE.getInput_Required();

		/**
		 * The meta object literal for the '<em><b>Choices</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INPUT__CHOICES = eINSTANCE.getInput_Choices();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.OutputImpl <em>Output</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.OutputImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getOutput()
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
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.StepImpl <em>Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.StepImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getStep()
		 * @generated
		 */
		EClass STEP = eINSTANCE.getStep();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.ConditionalStepImpl <em>Conditional Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.ConditionalStepImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getConditionalStep()
		 * @generated
		 */
		EClass CONDITIONAL_STEP = eINSTANCE.getConditionalStep();

		/**
		 * The meta object literal for the '<em><b>If Condition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONDITIONAL_STEP__IF_CONDITION = eINSTANCE.getConditionalStep_IfCondition();

		/**
		 * The meta object literal for the '<em><b>Then Run</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONDITIONAL_STEP__THEN_RUN = eINSTANCE.getConditionalStep_ThenRun();

		/**
		 * The meta object literal for the '<em><b>Else Run</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONDITIONAL_STEP__ELSE_RUN = eINSTANCE.getConditionalStep_ElseRun();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.NonConditionalStepImpl <em>Non Conditional Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.NonConditionalStepImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getNonConditionalStep()
		 * @generated
		 */
		EClass NON_CONDITIONAL_STEP = eINSTANCE.getNonConditionalStep();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NON_CONDITIONAL_STEP__ID = eINSTANCE.getNonConditionalStep_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NON_CONDITIONAL_STEP__NAME = eINSTANCE.getNonConditionalStep_Name();

		/**
		 * The meta object literal for the '<em><b>Environment Variables</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NON_CONDITIONAL_STEP__ENVIRONMENT_VARIABLES = eINSTANCE.getNonConditionalStep_EnvironmentVariables();

		/**
		 * The meta object literal for the '<em><b>Allow Failure</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NON_CONDITIONAL_STEP__ALLOW_FAILURE = eINSTANCE.getNonConditionalStep_AllowFailure();

		/**
		 * The meta object literal for the '<em><b>Working Directory</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NON_CONDITIONAL_STEP__WORKING_DIRECTORY = eINSTANCE.getNonConditionalStep_WorkingDirectory();

		/**
		 * The meta object literal for the '<em><b>Timeout Minutes</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NON_CONDITIONAL_STEP__TIMEOUT_MINUTES = eINSTANCE.getNonConditionalStep_TimeoutMinutes();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.CommandImpl <em>Command</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.CommandImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getCommand()
		 * @generated
		 */
		EClass COMMAND = eINSTANCE.getCommand();

		/**
		 * The meta object literal for the '<em><b>Program</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMMAND__PROGRAM = eINSTANCE.getCommand_Program();

		/**
		 * The meta object literal for the '<em><b>Shell</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMMAND__SHELL = eINSTANCE.getCommand_Shell();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.PluginImpl <em>Plugin</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PluginImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getPlugin()
		 * @generated
		 */
		EClass PLUGIN = eINSTANCE.getPlugin();

		/**
		 * The meta object literal for the '<em><b>Plugin Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLUGIN__PLUGIN_NAME = eINSTANCE.getPlugin_PluginName();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLUGIN__VERSION = eINSTANCE.getPlugin_Version();

		/**
		 * The meta object literal for the '<em><b>Kwargs</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLUGIN__KWARGS = eINSTANCE.getPlugin_Kwargs();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.CacheImpl <em>Cache</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.CacheImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getCache()
		 * @generated
		 */
		EClass CACHE = eINSTANCE.getCache();

		/**
		 * The meta object literal for the '<em><b>Cache Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CACHE__CACHE_NAME = eINSTANCE.getCache_CacheName();

		/**
		 * The meta object literal for the '<em><b>Keys</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CACHE__KEYS = eINSTANCE.getCache_Keys();

		/**
		 * The meta object literal for the '<em><b>Paths</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CACHE__PATHS = eINSTANCE.getCache_Paths();

		/**
		 * The meta object literal for the '<em><b>Store</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CACHE__STORE = eINSTANCE.getCache_Store();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.ArtifactImpl <em>Artifact</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.ArtifactImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getArtifact()
		 * @generated
		 */
		EClass ARTIFACT = eINSTANCE.getArtifact();

		/**
		 * The meta object literal for the '<em><b>Artifact Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARTIFACT__ARTIFACT_NAME = eINSTANCE.getArtifact_ArtifactName();

		/**
		 * The meta object literal for the '<em><b>Include Paths</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARTIFACT__INCLUDE_PATHS = eINSTANCE.getArtifact_IncludePaths();

		/**
		 * The meta object literal for the '<em><b>Exclude Paths</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARTIFACT__EXCLUDE_PATHS = eINSTANCE.getArtifact_ExcludePaths();

		/**
		 * The meta object literal for the '<em><b>Retention Time</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARTIFACT__RETENTION_TIME = eINSTANCE.getArtifact_RetentionTime();

		/**
		 * The meta object literal for the '<em><b>Store</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT__STORE = eINSTANCE.getArtifact_Store();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.CheckoutImpl <em>Checkout</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.CheckoutImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getCheckout()
		 * @generated
		 */
		EClass CHECKOUT = eINSTANCE.getCheckout();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHECKOUT__PATH = eINSTANCE.getCheckout_Path();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.VariableDeclarationImpl <em>Variable Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.VariableDeclarationImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getVariableDeclaration()
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
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.AssignmentImpl <em>Assignment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.AssignmentImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getAssignment()
		 * @generated
		 */
		EClass ASSIGNMENT = eINSTANCE.getAssignment();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSIGNMENT__KEY = eINSTANCE.getAssignment_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSIGNMENT__VALUE = eINSTANCE.getAssignment_Value();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.ExpressionImpl <em>Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.ExpressionImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getExpression()
		 * @generated
		 */
		EClass EXPRESSION = eINSTANCE.getExpression();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.ConcatImpl <em>Concat</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.ConcatImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getConcat()
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
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.ValueImpl <em>Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.ValueImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getValue()
		 * @generated
		 */
		EClass VALUE = eINSTANCE.getValue();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.LiteralImpl <em>Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.LiteralImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getLiteral()
		 * @generated
		 */
		EClass LITERAL = eINSTANCE.getLiteral();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.StringLiteralImpl <em>String Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.StringLiteralImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getStringLiteral()
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
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.IntegerLiteralImpl <em>Integer Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.IntegerLiteralImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getIntegerLiteral()
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
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.DoubleLiteralImpl <em>Double Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.DoubleLiteralImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getDoubleLiteral()
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
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.BooleanLiteralImpl <em>Boolean Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.BooleanLiteralImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getBooleanLiteral()
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
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.VariableReferenceImpl <em>Variable Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.VariableReferenceImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getVariableReference()
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
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.BinaryOpImpl <em>Binary Op</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.BinaryOpImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getBinaryOp()
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
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.EqualityOpImpl <em>Equality Op</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.EqualityOpImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getEqualityOp()
		 * @generated
		 */
		EClass EQUALITY_OP = eINSTANCE.getEqualityOp();

		/**
		 * The meta object literal for the '<em><b>Op</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EQUALITY_OP__OP = eINSTANCE.getEqualityOp_Op();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.ComparisonOpImpl <em>Comparison Op</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.ComparisonOpImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getComparisonOp()
		 * @generated
		 */
		EClass COMPARISON_OP = eINSTANCE.getComparisonOp();

		/**
		 * The meta object literal for the '<em><b>Op</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPARISON_OP__OP = eINSTANCE.getComparisonOp_Op();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.DotOpImpl <em>Dot Op</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.DotOpImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getDotOp()
		 * @generated
		 */
		EClass DOT_OP = eINSTANCE.getDotOp();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.LogicalOpImpl <em>Logical Op</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.LogicalOpImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getLogicalOp()
		 * @generated
		 */
		EClass LOGICAL_OP = eINSTANCE.getLogicalOp();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.AndImpl <em>And</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.AndImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getAnd()
		 * @generated
		 */
		EClass AND = eINSTANCE.getAnd();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.OrImpl <em>Or</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.OrImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getOr()
		 * @generated
		 */
		EClass OR = eINSTANCE.getOr();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.UnaryOpImpl <em>Unary Op</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.UnaryOpImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getUnaryOp()
		 * @generated
		 */
		EClass UNARY_OP = eINSTANCE.getUnaryOp();

		/**
		 * The meta object literal for the '<em><b>Rhs</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNARY_OP__RHS = eINSTANCE.getUnaryOp_Rhs();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.impl.NegationImpl <em>Negation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.impl.NegationImpl
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getNegation()
		 * @generated
		 */
		EClass NEGATION = eINSTANCE.getNegation();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.INPUT_TYPE <em>INPUT TYPE</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.INPUT_TYPE
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getINPUT_TYPE()
		 * @generated
		 */
		EEnum INPUT_TYPE = eINSTANCE.getINPUT_TYPE();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.EQUALITY_OPS <em>EQUALITY OPS</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.EQUALITY_OPS
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getEQUALITY_OPS()
		 * @generated
		 */
		EEnum EQUALITY_OPS = eINSTANCE.getEQUALITY_OPS();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.COMPARISON_OPS <em>COMPARISON OPS</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.COMPARISON_OPS
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getCOMPARISON_OPS()
		 * @generated
		 */
		EEnum COMPARISON_OPS = eINSTANCE.getCOMPARISON_OPS();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.pim.pimMM.CACHE_MODE <em>CACHE MODE</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.pim.pimMM.CACHE_MODE
		 * @see com.mddoai.metamodel.pim.pimMM.impl.PimMMPackageImpl#getCACHE_MODE()
		 * @generated
		 */
		EEnum CACHE_MODE = eINSTANCE.getCACHE_MODE();

	}

} //PimMMPackage
