/**
 */
package com.mddoai.metamodel.github.githubMM;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>CONTEXTS</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getCONTEXTS()
 * @model
 * @generated
 */
public enum CONTEXTS implements Enumerator {
	/**
	 * The '<em><b>GITHUB</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GITHUB_VALUE
	 * @generated
	 * @ordered
	 */
	GITHUB(0, "GITHUB", "github"),

	/**
	 * The '<em><b>ENV</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ENV_VALUE
	 * @generated
	 * @ordered
	 */
	ENV(1, "ENV", "env"),

	/**
	 * The '<em><b>VARS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #VARS_VALUE
	 * @generated
	 * @ordered
	 */
	VARS(2, "VARS", "vars"),

	/**
	 * The '<em><b>JOB</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JOB_VALUE
	 * @generated
	 * @ordered
	 */
	JOB(3, "JOB", "job"),

	/**
	 * The '<em><b>JOBS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JOBS_VALUE
	 * @generated
	 * @ordered
	 */
	JOBS(4, "JOBS", "jobs"),

	/**
	 * The '<em><b>STEPS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STEPS_VALUE
	 * @generated
	 * @ordered
	 */
	STEPS(5, "STEPS", "step"),

	/**
	 * The '<em><b>RUNNER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RUNNER_VALUE
	 * @generated
	 * @ordered
	 */
	RUNNER(6, "RUNNER", "runner"),

	/**
	 * The '<em><b>SECRETS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SECRETS_VALUE
	 * @generated
	 * @ordered
	 */
	SECRETS(7, "SECRETS", "secrets"),

	/**
	 * The '<em><b>STRATEGY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STRATEGY_VALUE
	 * @generated
	 * @ordered
	 */
	STRATEGY(8, "STRATEGY", "strategy"),

	/**
	 * The '<em><b>MATRIX</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MATRIX_VALUE
	 * @generated
	 * @ordered
	 */
	MATRIX(9, "MATRIX", "matrix"),

	/**
	 * The '<em><b>NEEDS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NEEDS_VALUE
	 * @generated
	 * @ordered
	 */
	NEEDS(10, "NEEDS", "needs"),

	/**
	 * The '<em><b>INPUTS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INPUTS_VALUE
	 * @generated
	 * @ordered
	 */
	INPUTS(11, "INPUTS", "inputs");

	/**
	 * The '<em><b>GITHUB</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GITHUB
	 * @model literal="github"
	 * @generated
	 * @ordered
	 */
	public static final int GITHUB_VALUE = 0;

	/**
	 * The '<em><b>ENV</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ENV
	 * @model literal="env"
	 * @generated
	 * @ordered
	 */
	public static final int ENV_VALUE = 1;

	/**
	 * The '<em><b>VARS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #VARS
	 * @model literal="vars"
	 * @generated
	 * @ordered
	 */
	public static final int VARS_VALUE = 2;

	/**
	 * The '<em><b>JOB</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JOB
	 * @model literal="job"
	 * @generated
	 * @ordered
	 */
	public static final int JOB_VALUE = 3;

	/**
	 * The '<em><b>JOBS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JOBS
	 * @model literal="jobs"
	 * @generated
	 * @ordered
	 */
	public static final int JOBS_VALUE = 4;

	/**
	 * The '<em><b>STEPS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STEPS
	 * @model literal="step"
	 * @generated
	 * @ordered
	 */
	public static final int STEPS_VALUE = 5;

	/**
	 * The '<em><b>RUNNER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RUNNER
	 * @model literal="runner"
	 * @generated
	 * @ordered
	 */
	public static final int RUNNER_VALUE = 6;

	/**
	 * The '<em><b>SECRETS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SECRETS
	 * @model literal="secrets"
	 * @generated
	 * @ordered
	 */
	public static final int SECRETS_VALUE = 7;

	/**
	 * The '<em><b>STRATEGY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STRATEGY
	 * @model literal="strategy"
	 * @generated
	 * @ordered
	 */
	public static final int STRATEGY_VALUE = 8;

	/**
	 * The '<em><b>MATRIX</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MATRIX
	 * @model literal="matrix"
	 * @generated
	 * @ordered
	 */
	public static final int MATRIX_VALUE = 9;

	/**
	 * The '<em><b>NEEDS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NEEDS
	 * @model literal="needs"
	 * @generated
	 * @ordered
	 */
	public static final int NEEDS_VALUE = 10;

	/**
	 * The '<em><b>INPUTS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INPUTS
	 * @model literal="inputs"
	 * @generated
	 * @ordered
	 */
	public static final int INPUTS_VALUE = 11;

	/**
	 * An array of all the '<em><b>CONTEXTS</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final CONTEXTS[] VALUES_ARRAY = new CONTEXTS[] { GITHUB, ENV, VARS, JOB, JOBS, STEPS, RUNNER,
			SECRETS, STRATEGY, MATRIX, NEEDS, INPUTS, };

	/**
	 * A public read-only list of all the '<em><b>CONTEXTS</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<CONTEXTS> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>CONTEXTS</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static CONTEXTS get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			CONTEXTS result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>CONTEXTS</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static CONTEXTS getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			CONTEXTS result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>CONTEXTS</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static CONTEXTS get(int value) {
		switch (value) {
		case GITHUB_VALUE:
			return GITHUB;
		case ENV_VALUE:
			return ENV;
		case VARS_VALUE:
			return VARS;
		case JOB_VALUE:
			return JOB;
		case JOBS_VALUE:
			return JOBS;
		case STEPS_VALUE:
			return STEPS;
		case RUNNER_VALUE:
			return RUNNER;
		case SECRETS_VALUE:
			return SECRETS;
		case STRATEGY_VALUE:
			return STRATEGY;
		case MATRIX_VALUE:
			return MATRIX;
		case NEEDS_VALUE:
			return NEEDS;
		case INPUTS_VALUE:
			return INPUTS;
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
	private CONTEXTS(int value, String name, String literal) {
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

} //CONTEXTS
