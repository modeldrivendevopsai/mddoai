/**
 */
package com.mddoai.metamodel.bambooMM;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>NOTIFICATION EVENT TYPE</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getNOTIFICATION_EVENT_TYPE()
 * @model
 * @generated
 */
public enum NOTIFICATION_EVENT_TYPE implements Enumerator {
	/**
	 * The '<em><b>Plan Failed</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PLAN_FAILED_VALUE
	 * @generated
	 * @ordered
	 */
	PLAN_FAILED(0, "planFailed", "plan-failed"),

	/**
	 * The '<em><b>Plan Completed</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PLAN_COMPLETED_VALUE
	 * @generated
	 * @ordered
	 */
	PLAN_COMPLETED(1, "planCompleted", "plan-completed"),

	/**
	 * The '<em><b>Plan Status Changed</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PLAN_STATUS_CHANGED_VALUE
	 * @generated
	 * @ordered
	 */
	PLAN_STATUS_CHANGED(2, "planStatusChanged", "plan-status-changed"),

	/**
	 * The '<em><b>Plan Comment Added</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PLAN_COMMENT_ADDED_VALUE
	 * @generated
	 * @ordered
	 */
	PLAN_COMMENT_ADDED(3, "planCommentAdded", "plan-comment-added"),

	/**
	 * The '<em><b>Plan Responsibility Changed</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PLAN_RESPONSIBILITY_CHANGED_VALUE
	 * @generated
	 * @ordered
	 */
	PLAN_RESPONSIBILITY_CHANGED(4, "planResponsibilityChanged", "plan-responsibility-changed"),

	/**
	 * The '<em><b>Job Error</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JOB_ERROR_VALUE
	 * @generated
	 * @ordered
	 */
	JOB_ERROR(5, "jobError", "job-error"),

	/**
	 * The '<em><b>Job Completed</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JOB_COMPLETED_VALUE
	 * @generated
	 * @ordered
	 */
	JOB_COMPLETED(6, "jobCompleted", "job-completed"),

	/**
	 * The '<em><b>Job Status Changed</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JOB_STATUS_CHANGED_VALUE
	 * @generated
	 * @ordered
	 */
	JOB_STATUS_CHANGED(7, "jobStatusChanged", "job-status-changed"),

	/**
	 * The '<em><b>Job Failed</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JOB_FAILED_VALUE
	 * @generated
	 * @ordered
	 */
	JOB_FAILED(8, "jobFailed", "job-failed"),

	/**
	 * The '<em><b>Job First Failed</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JOB_FIRST_FAILED_VALUE
	 * @generated
	 * @ordered
	 */
	JOB_FIRST_FAILED(9, "jobFirstFailed", "job-first-failed"),

	/**
	 * The '<em><b>Job Hung</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JOB_HUNG_VALUE
	 * @generated
	 * @ordered
	 */
	JOB_HUNG(10, "jobHung", "job-hung"),

	/**
	 * The '<em><b>Job Queue Timeout</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JOB_QUEUE_TIMEOUT_VALUE
	 * @generated
	 * @ordered
	 */
	JOB_QUEUE_TIMEOUT(11, "jobQueueTimeout", "job-queue-timeout"),

	/**
	 * The '<em><b>Job Queued Without Capable Agents</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JOB_QUEUED_WITHOUT_CAPABLE_AGENTS_VALUE
	 * @generated
	 * @ordered
	 */
	JOB_QUEUED_WITHOUT_CAPABLE_AGENTS(12, "jobQueuedWithoutCapableAgents", "job-queued-without-capable-agents"),

	/**
	 * The '<em><b>Deployment Failed</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DEPLOYMENT_FAILED_VALUE
	 * @generated
	 * @ordered
	 */
	DEPLOYMENT_FAILED(13, "deploymentFailed", "deployment-failed"),

	/**
	 * The '<em><b>Deployment Finished</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DEPLOYMENT_FINISHED_VALUE
	 * @generated
	 * @ordered
	 */
	DEPLOYMENT_FINISHED(14, "deploymentFinished", "deployment-finished"),

	/**
	 * The '<em><b>Deployment Started And Finished</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DEPLOYMENT_STARTED_AND_FINISHED_VALUE
	 * @generated
	 * @ordered
	 */
	DEPLOYMENT_STARTED_AND_FINISHED(15, "deploymentStartedAndFinished", "deployment-started-and-finished");

	/**
	 * The '<em><b>Plan Failed</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PLAN_FAILED
	 * @model name="planFailed" literal="plan-failed"
	 * @generated
	 * @ordered
	 */
	public static final int PLAN_FAILED_VALUE = 0;

	/**
	 * The '<em><b>Plan Completed</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PLAN_COMPLETED
	 * @model name="planCompleted" literal="plan-completed"
	 * @generated
	 * @ordered
	 */
	public static final int PLAN_COMPLETED_VALUE = 1;

	/**
	 * The '<em><b>Plan Status Changed</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PLAN_STATUS_CHANGED
	 * @model name="planStatusChanged" literal="plan-status-changed"
	 * @generated
	 * @ordered
	 */
	public static final int PLAN_STATUS_CHANGED_VALUE = 2;

	/**
	 * The '<em><b>Plan Comment Added</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PLAN_COMMENT_ADDED
	 * @model name="planCommentAdded" literal="plan-comment-added"
	 * @generated
	 * @ordered
	 */
	public static final int PLAN_COMMENT_ADDED_VALUE = 3;

	/**
	 * The '<em><b>Plan Responsibility Changed</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PLAN_RESPONSIBILITY_CHANGED
	 * @model name="planResponsibilityChanged" literal="plan-responsibility-changed"
	 * @generated
	 * @ordered
	 */
	public static final int PLAN_RESPONSIBILITY_CHANGED_VALUE = 4;

	/**
	 * The '<em><b>Job Error</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JOB_ERROR
	 * @model name="jobError" literal="job-error"
	 * @generated
	 * @ordered
	 */
	public static final int JOB_ERROR_VALUE = 5;

	/**
	 * The '<em><b>Job Completed</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JOB_COMPLETED
	 * @model name="jobCompleted" literal="job-completed"
	 * @generated
	 * @ordered
	 */
	public static final int JOB_COMPLETED_VALUE = 6;

	/**
	 * The '<em><b>Job Status Changed</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JOB_STATUS_CHANGED
	 * @model name="jobStatusChanged" literal="job-status-changed"
	 * @generated
	 * @ordered
	 */
	public static final int JOB_STATUS_CHANGED_VALUE = 7;

	/**
	 * The '<em><b>Job Failed</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JOB_FAILED
	 * @model name="jobFailed" literal="job-failed"
	 * @generated
	 * @ordered
	 */
	public static final int JOB_FAILED_VALUE = 8;

	/**
	 * The '<em><b>Job First Failed</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JOB_FIRST_FAILED
	 * @model name="jobFirstFailed" literal="job-first-failed"
	 * @generated
	 * @ordered
	 */
	public static final int JOB_FIRST_FAILED_VALUE = 9;

	/**
	 * The '<em><b>Job Hung</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JOB_HUNG
	 * @model name="jobHung" literal="job-hung"
	 * @generated
	 * @ordered
	 */
	public static final int JOB_HUNG_VALUE = 10;

	/**
	 * The '<em><b>Job Queue Timeout</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JOB_QUEUE_TIMEOUT
	 * @model name="jobQueueTimeout" literal="job-queue-timeout"
	 * @generated
	 * @ordered
	 */
	public static final int JOB_QUEUE_TIMEOUT_VALUE = 11;

	/**
	 * The '<em><b>Job Queued Without Capable Agents</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JOB_QUEUED_WITHOUT_CAPABLE_AGENTS
	 * @model name="jobQueuedWithoutCapableAgents" literal="job-queued-without-capable-agents"
	 * @generated
	 * @ordered
	 */
	public static final int JOB_QUEUED_WITHOUT_CAPABLE_AGENTS_VALUE = 12;

	/**
	 * The '<em><b>Deployment Failed</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DEPLOYMENT_FAILED
	 * @model name="deploymentFailed" literal="deployment-failed"
	 * @generated
	 * @ordered
	 */
	public static final int DEPLOYMENT_FAILED_VALUE = 13;

	/**
	 * The '<em><b>Deployment Finished</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DEPLOYMENT_FINISHED
	 * @model name="deploymentFinished" literal="deployment-finished"
	 * @generated
	 * @ordered
	 */
	public static final int DEPLOYMENT_FINISHED_VALUE = 14;

	/**
	 * The '<em><b>Deployment Started And Finished</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DEPLOYMENT_STARTED_AND_FINISHED
	 * @model name="deploymentStartedAndFinished" literal="deployment-started-and-finished"
	 * @generated
	 * @ordered
	 */
	public static final int DEPLOYMENT_STARTED_AND_FINISHED_VALUE = 15;

	/**
	 * An array of all the '<em><b>NOTIFICATION EVENT TYPE</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final NOTIFICATION_EVENT_TYPE[] VALUES_ARRAY = new NOTIFICATION_EVENT_TYPE[] { PLAN_FAILED,
			PLAN_COMPLETED, PLAN_STATUS_CHANGED, PLAN_COMMENT_ADDED, PLAN_RESPONSIBILITY_CHANGED, JOB_ERROR,
			JOB_COMPLETED, JOB_STATUS_CHANGED, JOB_FAILED, JOB_FIRST_FAILED, JOB_HUNG, JOB_QUEUE_TIMEOUT,
			JOB_QUEUED_WITHOUT_CAPABLE_AGENTS, DEPLOYMENT_FAILED, DEPLOYMENT_FINISHED,
			DEPLOYMENT_STARTED_AND_FINISHED, };

	/**
	 * A public read-only list of all the '<em><b>NOTIFICATION EVENT TYPE</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<NOTIFICATION_EVENT_TYPE> VALUES = Collections
			.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>NOTIFICATION EVENT TYPE</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static NOTIFICATION_EVENT_TYPE get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			NOTIFICATION_EVENT_TYPE result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>NOTIFICATION EVENT TYPE</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static NOTIFICATION_EVENT_TYPE getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			NOTIFICATION_EVENT_TYPE result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>NOTIFICATION EVENT TYPE</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static NOTIFICATION_EVENT_TYPE get(int value) {
		switch (value) {
		case PLAN_FAILED_VALUE:
			return PLAN_FAILED;
		case PLAN_COMPLETED_VALUE:
			return PLAN_COMPLETED;
		case PLAN_STATUS_CHANGED_VALUE:
			return PLAN_STATUS_CHANGED;
		case PLAN_COMMENT_ADDED_VALUE:
			return PLAN_COMMENT_ADDED;
		case PLAN_RESPONSIBILITY_CHANGED_VALUE:
			return PLAN_RESPONSIBILITY_CHANGED;
		case JOB_ERROR_VALUE:
			return JOB_ERROR;
		case JOB_COMPLETED_VALUE:
			return JOB_COMPLETED;
		case JOB_STATUS_CHANGED_VALUE:
			return JOB_STATUS_CHANGED;
		case JOB_FAILED_VALUE:
			return JOB_FAILED;
		case JOB_FIRST_FAILED_VALUE:
			return JOB_FIRST_FAILED;
		case JOB_HUNG_VALUE:
			return JOB_HUNG;
		case JOB_QUEUE_TIMEOUT_VALUE:
			return JOB_QUEUE_TIMEOUT;
		case JOB_QUEUED_WITHOUT_CAPABLE_AGENTS_VALUE:
			return JOB_QUEUED_WITHOUT_CAPABLE_AGENTS;
		case DEPLOYMENT_FAILED_VALUE:
			return DEPLOYMENT_FAILED;
		case DEPLOYMENT_FINISHED_VALUE:
			return DEPLOYMENT_FINISHED;
		case DEPLOYMENT_STARTED_AND_FINISHED_VALUE:
			return DEPLOYMENT_STARTED_AND_FINISHED;
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
	private NOTIFICATION_EVENT_TYPE(int value, String name, String literal) {
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

} //NOTIFICATION_EVENT_TYPE
