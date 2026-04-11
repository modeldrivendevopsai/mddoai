/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>PARAMETER TYPE</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPARAMETER_TYPE()
 * @model
 * @generated
 */
public enum PARAMETER_TYPE implements Enumerator {
	/**
	 * The '<em><b>String</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STRING_VALUE
	 * @generated
	 * @ordered
	 */
	STRING(0, "string", "string"),

	/**
	 * The '<em><b>Number</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NUMBER_VALUE
	 * @generated
	 * @ordered
	 */
	NUMBER(1, "number", "number"),

	/**
	 * The '<em><b>Boolean</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BOOLEAN_VALUE
	 * @generated
	 * @ordered
	 */
	BOOLEAN(2, "boolean", "boolean"),

	/**
	 * The '<em><b>Object</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OBJECT_VALUE
	 * @generated
	 * @ordered
	 */
	OBJECT(3, "object", "object"),

	/**
	 * The '<em><b>Step</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STEP_VALUE
	 * @generated
	 * @ordered
	 */
	STEP(4, "step", "step"),

	/**
	 * The '<em><b>Step List</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STEP_LIST_VALUE
	 * @generated
	 * @ordered
	 */
	STEP_LIST(5, "stepList", "stepList"),

	/**
	 * The '<em><b>Job</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JOB_VALUE
	 * @generated
	 * @ordered
	 */
	JOB(6, "job", "job"),

	/**
	 * The '<em><b>Job List</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JOB_LIST_VALUE
	 * @generated
	 * @ordered
	 */
	JOB_LIST(7, "jobList", "jobList"),

	/**
	 * The '<em><b>Deployment</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DEPLOYMENT_VALUE
	 * @generated
	 * @ordered
	 */
	DEPLOYMENT(8, "deployment", "deployment"),

	/**
	 * The '<em><b>Deployment List</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DEPLOYMENT_LIST_VALUE
	 * @generated
	 * @ordered
	 */
	DEPLOYMENT_LIST(9, "deploymentList", "deploymentList"),

	/**
	 * The '<em><b>Stage</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STAGE_VALUE
	 * @generated
	 * @ordered
	 */
	STAGE(10, "stage", "stage"),

	/**
	 * The '<em><b>Stage List</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STAGE_LIST_VALUE
	 * @generated
	 * @ordered
	 */
	STAGE_LIST(11, "stageList", "stageList");

	/**
	 * The '<em><b>String</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STRING
	 * @model name="string"
	 * @generated
	 * @ordered
	 */
	public static final int STRING_VALUE = 0;

	/**
	 * The '<em><b>Number</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NUMBER
	 * @model name="number"
	 * @generated
	 * @ordered
	 */
	public static final int NUMBER_VALUE = 1;

	/**
	 * The '<em><b>Boolean</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BOOLEAN
	 * @model name="boolean"
	 * @generated
	 * @ordered
	 */
	public static final int BOOLEAN_VALUE = 2;

	/**
	 * The '<em><b>Object</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OBJECT
	 * @model name="object"
	 * @generated
	 * @ordered
	 */
	public static final int OBJECT_VALUE = 3;

	/**
	 * The '<em><b>Step</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STEP
	 * @model name="step"
	 * @generated
	 * @ordered
	 */
	public static final int STEP_VALUE = 4;

	/**
	 * The '<em><b>Step List</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STEP_LIST
	 * @model name="stepList"
	 * @generated
	 * @ordered
	 */
	public static final int STEP_LIST_VALUE = 5;

	/**
	 * The '<em><b>Job</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JOB
	 * @model name="job"
	 * @generated
	 * @ordered
	 */
	public static final int JOB_VALUE = 6;

	/**
	 * The '<em><b>Job List</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JOB_LIST
	 * @model name="jobList"
	 * @generated
	 * @ordered
	 */
	public static final int JOB_LIST_VALUE = 7;

	/**
	 * The '<em><b>Deployment</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DEPLOYMENT
	 * @model name="deployment"
	 * @generated
	 * @ordered
	 */
	public static final int DEPLOYMENT_VALUE = 8;

	/**
	 * The '<em><b>Deployment List</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DEPLOYMENT_LIST
	 * @model name="deploymentList"
	 * @generated
	 * @ordered
	 */
	public static final int DEPLOYMENT_LIST_VALUE = 9;

	/**
	 * The '<em><b>Stage</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STAGE
	 * @model name="stage"
	 * @generated
	 * @ordered
	 */
	public static final int STAGE_VALUE = 10;

	/**
	 * The '<em><b>Stage List</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STAGE_LIST
	 * @model name="stageList"
	 * @generated
	 * @ordered
	 */
	public static final int STAGE_LIST_VALUE = 11;

	/**
	 * An array of all the '<em><b>PARAMETER TYPE</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final PARAMETER_TYPE[] VALUES_ARRAY = new PARAMETER_TYPE[] { STRING, NUMBER, BOOLEAN, OBJECT, STEP,
			STEP_LIST, JOB, JOB_LIST, DEPLOYMENT, DEPLOYMENT_LIST, STAGE, STAGE_LIST, };

	/**
	 * A public read-only list of all the '<em><b>PARAMETER TYPE</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<PARAMETER_TYPE> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>PARAMETER TYPE</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static PARAMETER_TYPE get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PARAMETER_TYPE result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>PARAMETER TYPE</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static PARAMETER_TYPE getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PARAMETER_TYPE result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>PARAMETER TYPE</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static PARAMETER_TYPE get(int value) {
		switch (value) {
		case STRING_VALUE:
			return STRING;
		case NUMBER_VALUE:
			return NUMBER;
		case BOOLEAN_VALUE:
			return BOOLEAN;
		case OBJECT_VALUE:
			return OBJECT;
		case STEP_VALUE:
			return STEP;
		case STEP_LIST_VALUE:
			return STEP_LIST;
		case JOB_VALUE:
			return JOB;
		case JOB_LIST_VALUE:
			return JOB_LIST;
		case DEPLOYMENT_VALUE:
			return DEPLOYMENT;
		case DEPLOYMENT_LIST_VALUE:
			return DEPLOYMENT_LIST;
		case STAGE_VALUE:
			return STAGE;
		case STAGE_LIST_VALUE:
			return STAGE_LIST;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private PARAMETER_TYPE(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLiteral() {
		return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}

} //PARAMETER_TYPE
