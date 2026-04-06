/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Retry When Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getRetryWhenType()
 * @model
 * @generated
 */
public enum RetryWhenType implements Enumerator {
	/**
	 * The '<em><b>ALWAYS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ALWAYS_VALUE
	 * @generated
	 * @ordered
	 */
	ALWAYS(0, "ALWAYS", "always"),

	/**
	 * The '<em><b>UNKNOWN FAILURE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNKNOWN_FAILURE_VALUE
	 * @generated
	 * @ordered
	 */
	UNKNOWN_FAILURE(1, "UNKNOWN_FAILURE", "unknown_failure"),

	/**
	 * The '<em><b>SCRIPT FAILURE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SCRIPT_FAILURE_VALUE
	 * @generated
	 * @ordered
	 */
	SCRIPT_FAILURE(2, "SCRIPT_FAILURE", "script_failure"),

	/**
	 * The '<em><b>API FAILURE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #API_FAILURE_VALUE
	 * @generated
	 * @ordered
	 */
	API_FAILURE(3, "API_FAILURE", "api_failure"),

	/**
	 * The '<em><b>STUCK OR TIMEOUT FAILURE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STUCK_OR_TIMEOUT_FAILURE_VALUE
	 * @generated
	 * @ordered
	 */
	STUCK_OR_TIMEOUT_FAILURE(4, "STUCK_OR_TIMEOUT_FAILURE", "stuck_or_timeout_failure"),

	/**
	 * The '<em><b>RUNNER SYSTEM FAILURE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RUNNER_SYSTEM_FAILURE_VALUE
	 * @generated
	 * @ordered
	 */
	RUNNER_SYSTEM_FAILURE(5, "RUNNER_SYSTEM_FAILURE", "runner_system_failure"),

	/**
	 * The '<em><b>RUNNER UNSUPPORTED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RUNNER_UNSUPPORTED_VALUE
	 * @generated
	 * @ordered
	 */
	RUNNER_UNSUPPORTED(6, "RUNNER_UNSUPPORTED", "runner_unsupported"),

	/**
	 * The '<em><b>STALE SCHEDULE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STALE_SCHEDULE_VALUE
	 * @generated
	 * @ordered
	 */
	STALE_SCHEDULE(7, "STALE_SCHEDULE", "stale_schedule"),

	/**
	 * The '<em><b>JOB EXECUTION TIMEOUT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JOB_EXECUTION_TIMEOUT_VALUE
	 * @generated
	 * @ordered
	 */
	JOB_EXECUTION_TIMEOUT(8, "JOB_EXECUTION_TIMEOUT", "job_execution_timeout"),

	/**
	 * The '<em><b>ARCHIVED FAILURE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ARCHIVED_FAILURE_VALUE
	 * @generated
	 * @ordered
	 */
	ARCHIVED_FAILURE(9, "ARCHIVED_FAILURE", "archived_failure"),

	/**
	 * The '<em><b>UNMET PREREQUISITES</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNMET_PREREQUISITES_VALUE
	 * @generated
	 * @ordered
	 */
	UNMET_PREREQUISITES(10, "UNMET_PREREQUISITES", "unmet_prerequisites"),

	/**
	 * The '<em><b>SCHEDULER FAILURE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SCHEDULER_FAILURE_VALUE
	 * @generated
	 * @ordered
	 */
	SCHEDULER_FAILURE(11, "SCHEDULER_FAILURE", "scheduler_failure"),

	/**
	 * The '<em><b>DATA INTEGRITY FAILURE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DATA_INTEGRITY_FAILURE_VALUE
	 * @generated
	 * @ordered
	 */
	DATA_INTEGRITY_FAILURE(12, "DATA_INTEGRITY_FAILURE", "data_integrity_failure");

	/**
	 * The '<em><b>ALWAYS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ALWAYS
	 * @model literal="always"
	 * @generated
	 * @ordered
	 */
	public static final int ALWAYS_VALUE = 0;

	/**
	 * The '<em><b>UNKNOWN FAILURE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNKNOWN_FAILURE
	 * @model literal="unknown_failure"
	 * @generated
	 * @ordered
	 */
	public static final int UNKNOWN_FAILURE_VALUE = 1;

	/**
	 * The '<em><b>SCRIPT FAILURE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SCRIPT_FAILURE
	 * @model literal="script_failure"
	 * @generated
	 * @ordered
	 */
	public static final int SCRIPT_FAILURE_VALUE = 2;

	/**
	 * The '<em><b>API FAILURE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #API_FAILURE
	 * @model literal="api_failure"
	 * @generated
	 * @ordered
	 */
	public static final int API_FAILURE_VALUE = 3;

	/**
	 * The '<em><b>STUCK OR TIMEOUT FAILURE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STUCK_OR_TIMEOUT_FAILURE
	 * @model literal="stuck_or_timeout_failure"
	 * @generated
	 * @ordered
	 */
	public static final int STUCK_OR_TIMEOUT_FAILURE_VALUE = 4;

	/**
	 * The '<em><b>RUNNER SYSTEM FAILURE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RUNNER_SYSTEM_FAILURE
	 * @model literal="runner_system_failure"
	 * @generated
	 * @ordered
	 */
	public static final int RUNNER_SYSTEM_FAILURE_VALUE = 5;

	/**
	 * The '<em><b>RUNNER UNSUPPORTED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RUNNER_UNSUPPORTED
	 * @model literal="runner_unsupported"
	 * @generated
	 * @ordered
	 */
	public static final int RUNNER_UNSUPPORTED_VALUE = 6;

	/**
	 * The '<em><b>STALE SCHEDULE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STALE_SCHEDULE
	 * @model literal="stale_schedule"
	 * @generated
	 * @ordered
	 */
	public static final int STALE_SCHEDULE_VALUE = 7;

	/**
	 * The '<em><b>JOB EXECUTION TIMEOUT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JOB_EXECUTION_TIMEOUT
	 * @model literal="job_execution_timeout"
	 * @generated
	 * @ordered
	 */
	public static final int JOB_EXECUTION_TIMEOUT_VALUE = 8;

	/**
	 * The '<em><b>ARCHIVED FAILURE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ARCHIVED_FAILURE
	 * @model literal="archived_failure"
	 * @generated
	 * @ordered
	 */
	public static final int ARCHIVED_FAILURE_VALUE = 9;

	/**
	 * The '<em><b>UNMET PREREQUISITES</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNMET_PREREQUISITES
	 * @model literal="unmet_prerequisites"
	 * @generated
	 * @ordered
	 */
	public static final int UNMET_PREREQUISITES_VALUE = 10;

	/**
	 * The '<em><b>SCHEDULER FAILURE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SCHEDULER_FAILURE
	 * @model literal="scheduler_failure"
	 * @generated
	 * @ordered
	 */
	public static final int SCHEDULER_FAILURE_VALUE = 11;

	/**
	 * The '<em><b>DATA INTEGRITY FAILURE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DATA_INTEGRITY_FAILURE
	 * @model literal="data_integrity_failure"
	 * @generated
	 * @ordered
	 */
	public static final int DATA_INTEGRITY_FAILURE_VALUE = 12;

	/**
	 * An array of all the '<em><b>Retry When Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final RetryWhenType[] VALUES_ARRAY = new RetryWhenType[] { ALWAYS, UNKNOWN_FAILURE, SCRIPT_FAILURE,
			API_FAILURE, STUCK_OR_TIMEOUT_FAILURE, RUNNER_SYSTEM_FAILURE, RUNNER_UNSUPPORTED, STALE_SCHEDULE,
			JOB_EXECUTION_TIMEOUT, ARCHIVED_FAILURE, UNMET_PREREQUISITES, SCHEDULER_FAILURE, DATA_INTEGRITY_FAILURE, };

	/**
	 * A public read-only list of all the '<em><b>Retry When Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<RetryWhenType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Retry When Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static RetryWhenType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			RetryWhenType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Retry When Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static RetryWhenType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			RetryWhenType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Retry When Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static RetryWhenType get(int value) {
		switch (value) {
		case ALWAYS_VALUE:
			return ALWAYS;
		case UNKNOWN_FAILURE_VALUE:
			return UNKNOWN_FAILURE;
		case SCRIPT_FAILURE_VALUE:
			return SCRIPT_FAILURE;
		case API_FAILURE_VALUE:
			return API_FAILURE;
		case STUCK_OR_TIMEOUT_FAILURE_VALUE:
			return STUCK_OR_TIMEOUT_FAILURE;
		case RUNNER_SYSTEM_FAILURE_VALUE:
			return RUNNER_SYSTEM_FAILURE;
		case RUNNER_UNSUPPORTED_VALUE:
			return RUNNER_UNSUPPORTED;
		case STALE_SCHEDULE_VALUE:
			return STALE_SCHEDULE;
		case JOB_EXECUTION_TIMEOUT_VALUE:
			return JOB_EXECUTION_TIMEOUT;
		case ARCHIVED_FAILURE_VALUE:
			return ARCHIVED_FAILURE;
		case UNMET_PREREQUISITES_VALUE:
			return UNMET_PREREQUISITES;
		case SCHEDULER_FAILURE_VALUE:
			return SCHEDULER_FAILURE;
		case DATA_INTEGRITY_FAILURE_VALUE:
			return DATA_INTEGRITY_FAILURE;
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
	private RetryWhenType(int value, String name, String literal) {
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

} //RetryWhenType
