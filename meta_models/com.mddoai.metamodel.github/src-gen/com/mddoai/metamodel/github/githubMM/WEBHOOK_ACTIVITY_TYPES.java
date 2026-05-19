/**
 */
package com.mddoai.metamodel.github.githubMM;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>WEBHOOK ACTIVITY TYPES</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getWEBHOOK_ACTIVITY_TYPES()
 * @model
 * @generated
 */
public enum WEBHOOK_ACTIVITY_TYPES implements Enumerator {
	/**
	 * The '<em><b>ASSIGNED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ASSIGNED_VALUE
	 * @generated
	 * @ordered
	 */
	ASSIGNED(0, "ASSIGNED", "assigned"),

	/**
	 * The '<em><b>AUTO MERGE DISABLED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #AUTO_MERGE_DISABLED_VALUE
	 * @generated
	 * @ordered
	 */
	AUTO_MERGE_DISABLED(1, "AUTO_MERGE_DISABLED", "auto_merge_disabled"),

	/**
	 * The '<em><b>AUTO MERGE ENABLED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #AUTO_MERGE_ENABLED_VALUE
	 * @generated
	 * @ordered
	 */
	AUTO_MERGE_ENABLED(2, "AUTO_MERGE_ENABLED", "auto_merge_enabled"),

	/**
	 * The '<em><b>CLOSED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CLOSED_VALUE
	 * @generated
	 * @ordered
	 */
	CLOSED(4, "CLOSED", "closed"),

	/**
	 * The '<em><b>CONVERTED TO DRAFT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONVERTED_TO_DRAFT_VALUE
	 * @generated
	 * @ordered
	 */
	CONVERTED_TO_DRAFT(5, "CONVERTED_TO_DRAFT", "converted_to_draft"),

	/**
	 * The '<em><b>EDITED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EDITED_VALUE
	 * @generated
	 * @ordered
	 */
	EDITED(6, "EDITED", "edited"),

	/**
	 * The '<em><b>LABELED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LABELED_VALUE
	 * @generated
	 * @ordered
	 */
	LABELED(7, "LABELED", "labeled"),

	/**
	 * The '<em><b>LOCKED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOCKED_VALUE
	 * @generated
	 * @ordered
	 */
	LOCKED(8, "LOCKED", "locked"),

	/**
	 * The '<em><b>OPENED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OPENED_VALUE
	 * @generated
	 * @ordered
	 */
	OPENED(9, "OPENED", "opened"),

	/**
	 * The '<em><b>READY FOR REVIEW</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #READY_FOR_REVIEW_VALUE
	 * @generated
	 * @ordered
	 */
	READY_FOR_REVIEW(10, "READY_FOR_REVIEW", "ready_for_review"),

	/**
	 * The '<em><b>REOPENED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REOPENED_VALUE
	 * @generated
	 * @ordered
	 */
	REOPENED(11, "REOPENED", "reopened"),

	/**
	 * The '<em><b>REVIEW REQUEST REMOVED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REVIEW_REQUEST_REMOVED_VALUE
	 * @generated
	 * @ordered
	 */
	REVIEW_REQUEST_REMOVED(12, "REVIEW_REQUEST_REMOVED", "review_request_removed"),

	/**
	 * The '<em><b>REVIEW REQUESTED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REVIEW_REQUESTED_VALUE
	 * @generated
	 * @ordered
	 */
	REVIEW_REQUESTED(13, "REVIEW_REQUESTED", "review_requested"),

	/**
	 * The '<em><b>SYNCHRONIZE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SYNCHRONIZE_VALUE
	 * @generated
	 * @ordered
	 */
	SYNCHRONIZE(14, "SYNCHRONIZE", "synchronize"),

	/**
	 * The '<em><b>UNASSIGNED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNASSIGNED_VALUE
	 * @generated
	 * @ordered
	 */
	UNASSIGNED(15, "UNASSIGNED", "unassigned"),

	/**
	 * The '<em><b>UNLABELED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNLABELED_VALUE
	 * @generated
	 * @ordered
	 */
	UNLABELED(16, "UNLABELED", "unlabeled"),

	/**
	 * The '<em><b>UNLOCKED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNLOCKED_VALUE
	 * @generated
	 * @ordered
	 */
	UNLOCKED(17, "UNLOCKED", "unlocked"),

	/**
	 * The '<em><b>CREATED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CREATED_VALUE
	 * @generated
	 * @ordered
	 */
	CREATED(18, "CREATED", "CREATED"),

	/**
	 * The '<em><b>DELETED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DELETED_VALUE
	 * @generated
	 * @ordered
	 */
	DELETED(19, "DELETED", "DELETED");

	/**
	 * The '<em><b>ASSIGNED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ASSIGNED
	 * @model literal="assigned"
	 * @generated
	 * @ordered
	 */
	public static final int ASSIGNED_VALUE = 0;

	/**
	 * The '<em><b>AUTO MERGE DISABLED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #AUTO_MERGE_DISABLED
	 * @model literal="auto_merge_disabled"
	 * @generated
	 * @ordered
	 */
	public static final int AUTO_MERGE_DISABLED_VALUE = 1;

	/**
	 * The '<em><b>AUTO MERGE ENABLED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #AUTO_MERGE_ENABLED
	 * @model literal="auto_merge_enabled"
	 * @generated
	 * @ordered
	 */
	public static final int AUTO_MERGE_ENABLED_VALUE = 2;

	/**
	 * The '<em><b>CLOSED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CLOSED
	 * @model literal="closed"
	 * @generated
	 * @ordered
	 */
	public static final int CLOSED_VALUE = 4;

	/**
	 * The '<em><b>CONVERTED TO DRAFT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONVERTED_TO_DRAFT
	 * @model literal="converted_to_draft"
	 * @generated
	 * @ordered
	 */
	public static final int CONVERTED_TO_DRAFT_VALUE = 5;

	/**
	 * The '<em><b>EDITED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EDITED
	 * @model literal="edited"
	 * @generated
	 * @ordered
	 */
	public static final int EDITED_VALUE = 6;

	/**
	 * The '<em><b>LABELED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LABELED
	 * @model literal="labeled"
	 * @generated
	 * @ordered
	 */
	public static final int LABELED_VALUE = 7;

	/**
	 * The '<em><b>LOCKED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOCKED
	 * @model literal="locked"
	 * @generated
	 * @ordered
	 */
	public static final int LOCKED_VALUE = 8;

	/**
	 * The '<em><b>OPENED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OPENED
	 * @model literal="opened"
	 * @generated
	 * @ordered
	 */
	public static final int OPENED_VALUE = 9;

	/**
	 * The '<em><b>READY FOR REVIEW</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #READY_FOR_REVIEW
	 * @model literal="ready_for_review"
	 * @generated
	 * @ordered
	 */
	public static final int READY_FOR_REVIEW_VALUE = 10;

	/**
	 * The '<em><b>REOPENED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REOPENED
	 * @model literal="reopened"
	 * @generated
	 * @ordered
	 */
	public static final int REOPENED_VALUE = 11;

	/**
	 * The '<em><b>REVIEW REQUEST REMOVED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REVIEW_REQUEST_REMOVED
	 * @model literal="review_request_removed"
	 * @generated
	 * @ordered
	 */
	public static final int REVIEW_REQUEST_REMOVED_VALUE = 12;

	/**
	 * The '<em><b>REVIEW REQUESTED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REVIEW_REQUESTED
	 * @model literal="review_requested"
	 * @generated
	 * @ordered
	 */
	public static final int REVIEW_REQUESTED_VALUE = 13;

	/**
	 * The '<em><b>SYNCHRONIZE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SYNCHRONIZE
	 * @model literal="synchronize"
	 * @generated
	 * @ordered
	 */
	public static final int SYNCHRONIZE_VALUE = 14;

	/**
	 * The '<em><b>UNASSIGNED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNASSIGNED
	 * @model literal="unassigned"
	 * @generated
	 * @ordered
	 */
	public static final int UNASSIGNED_VALUE = 15;

	/**
	 * The '<em><b>UNLABELED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNLABELED
	 * @model literal="unlabeled"
	 * @generated
	 * @ordered
	 */
	public static final int UNLABELED_VALUE = 16;

	/**
	 * The '<em><b>UNLOCKED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNLOCKED
	 * @model literal="unlocked"
	 * @generated
	 * @ordered
	 */
	public static final int UNLOCKED_VALUE = 17;

	/**
	 * The '<em><b>CREATED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CREATED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CREATED_VALUE = 18;

	/**
	 * The '<em><b>DELETED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DELETED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int DELETED_VALUE = 19;

	/**
	 * An array of all the '<em><b>WEBHOOK ACTIVITY TYPES</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final WEBHOOK_ACTIVITY_TYPES[] VALUES_ARRAY = new WEBHOOK_ACTIVITY_TYPES[] { ASSIGNED,
			AUTO_MERGE_DISABLED, AUTO_MERGE_ENABLED, CLOSED, CONVERTED_TO_DRAFT, EDITED, LABELED, LOCKED, OPENED,
			READY_FOR_REVIEW, REOPENED, REVIEW_REQUEST_REMOVED, REVIEW_REQUESTED, SYNCHRONIZE, UNASSIGNED, UNLABELED,
			UNLOCKED, CREATED, DELETED, };

	/**
	 * A public read-only list of all the '<em><b>WEBHOOK ACTIVITY TYPES</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<WEBHOOK_ACTIVITY_TYPES> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>WEBHOOK ACTIVITY TYPES</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static WEBHOOK_ACTIVITY_TYPES get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			WEBHOOK_ACTIVITY_TYPES result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>WEBHOOK ACTIVITY TYPES</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static WEBHOOK_ACTIVITY_TYPES getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			WEBHOOK_ACTIVITY_TYPES result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>WEBHOOK ACTIVITY TYPES</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static WEBHOOK_ACTIVITY_TYPES get(int value) {
		switch (value) {
		case ASSIGNED_VALUE:
			return ASSIGNED;
		case AUTO_MERGE_DISABLED_VALUE:
			return AUTO_MERGE_DISABLED;
		case AUTO_MERGE_ENABLED_VALUE:
			return AUTO_MERGE_ENABLED;
		case CLOSED_VALUE:
			return CLOSED;
		case CONVERTED_TO_DRAFT_VALUE:
			return CONVERTED_TO_DRAFT;
		case EDITED_VALUE:
			return EDITED;
		case LABELED_VALUE:
			return LABELED;
		case LOCKED_VALUE:
			return LOCKED;
		case OPENED_VALUE:
			return OPENED;
		case READY_FOR_REVIEW_VALUE:
			return READY_FOR_REVIEW;
		case REOPENED_VALUE:
			return REOPENED;
		case REVIEW_REQUEST_REMOVED_VALUE:
			return REVIEW_REQUEST_REMOVED;
		case REVIEW_REQUESTED_VALUE:
			return REVIEW_REQUESTED;
		case SYNCHRONIZE_VALUE:
			return SYNCHRONIZE;
		case UNASSIGNED_VALUE:
			return UNASSIGNED;
		case UNLABELED_VALUE:
			return UNLABELED;
		case UNLOCKED_VALUE:
			return UNLOCKED;
		case CREATED_VALUE:
			return CREATED;
		case DELETED_VALUE:
			return DELETED;
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
	private WEBHOOK_ACTIVITY_TYPES(int value, String name, String literal) {
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

} //WEBHOOK_ACTIVITY_TYPES
