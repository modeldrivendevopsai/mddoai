/**
 */
package com.mddoai.metamodel.github.githubMM;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>EVENTS</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getEVENTS()
 * @model
 * @generated
 */
public enum EVENTS implements Enumerator {
	/**
	 * The '<em><b>BRANCH PROTECTION RULE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BRANCH_PROTECTION_RULE_VALUE
	 * @generated
	 * @ordered
	 */
	BRANCH_PROTECTION_RULE(0, "BRANCH_PROTECTION_RULE", "branch_protection_rule"),

	/**
	 * The '<em><b>CHECK RUN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CHECK_RUN_VALUE
	 * @generated
	 * @ordered
	 */
	CHECK_RUN(1, "CHECK_RUN", "check_run"),

	/**
	 * The '<em><b>CHECK SUITE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CHECK_SUITE_VALUE
	 * @generated
	 * @ordered
	 */
	CHECK_SUITE(2, "CHECK_SUITE", "check_suite"),

	/**
	 * The '<em><b>CREATE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CREATE_VALUE
	 * @generated
	 * @ordered
	 */
	CREATE(3, "CREATE", "create"),

	/**
	 * The '<em><b>DELETE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DELETE_VALUE
	 * @generated
	 * @ordered
	 */
	DELETE(4, "DELETE", "delete"),

	/**
	 * The '<em><b>DEPLOYMENT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DEPLOYMENT_VALUE
	 * @generated
	 * @ordered
	 */
	DEPLOYMENT(5, "DEPLOYMENT", "deployment"),

	/**
	 * The '<em><b>DEPLOYMENT STATUS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DEPLOYMENT_STATUS_VALUE
	 * @generated
	 * @ordered
	 */
	DEPLOYMENT_STATUS(6, "DEPLOYMENT_STATUS", "deployment_status"),

	/**
	 * The '<em><b>DISCUSSION</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DISCUSSION_VALUE
	 * @generated
	 * @ordered
	 */
	DISCUSSION(7, "DISCUSSION", "discussion"),

	/**
	 * The '<em><b>DISCUSSION COMMENT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DISCUSSION_COMMENT_VALUE
	 * @generated
	 * @ordered
	 */
	DISCUSSION_COMMENT(8, "DISCUSSION_COMMENT", "discussion_comment"),

	/**
	 * The '<em><b>FORK</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FORK_VALUE
	 * @generated
	 * @ordered
	 */
	FORK(9, "FORK", "fork"),

	/**
	 * The '<em><b>GOLLUM</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GOLLUM_VALUE
	 * @generated
	 * @ordered
	 */
	GOLLUM(10, "GOLLUM", "gollum"),

	/**
	 * The '<em><b>ISSUE COMMENT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ISSUE_COMMENT_VALUE
	 * @generated
	 * @ordered
	 */
	ISSUE_COMMENT(11, "ISSUE_COMMENT", "issue_comment"),

	/**
	 * The '<em><b>ISSUES</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ISSUES_VALUE
	 * @generated
	 * @ordered
	 */
	ISSUES(12, "ISSUES", "issues"),

	/**
	 * The '<em><b>LABEL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LABEL_VALUE
	 * @generated
	 * @ordered
	 */
	LABEL(13, "LABEL", "label"),

	/**
	 * The '<em><b>MERGE GROUP</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MERGE_GROUP_VALUE
	 * @generated
	 * @ordered
	 */
	MERGE_GROUP(14, "MERGE_GROUP", "merge_group"),

	/**
	 * The '<em><b>MILESTONE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MILESTONE_VALUE
	 * @generated
	 * @ordered
	 */
	MILESTONE(15, "MILESTONE", "milestone"),

	/**
	 * The '<em><b>PAGE BUILD</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PAGE_BUILD_VALUE
	 * @generated
	 * @ordered
	 */
	PAGE_BUILD(16, "PAGE_BUILD", "page_build"),

	/**
	 * The '<em><b>PROJECT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PROJECT_VALUE
	 * @generated
	 * @ordered
	 */
	PROJECT(17, "PROJECT", "project"),

	/**
	 * The '<em><b>PROJECT CARD</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PROJECT_CARD_VALUE
	 * @generated
	 * @ordered
	 */
	PROJECT_CARD(18, "PROJECT_CARD", "project_card"),

	/**
	 * The '<em><b>PROJECT COLUMN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PROJECT_COLUMN_VALUE
	 * @generated
	 * @ordered
	 */
	PROJECT_COLUMN(19, "PROJECT_COLUMN", "project_column"),

	/**
	 * The '<em><b>PUBLIC</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PUBLIC_VALUE
	 * @generated
	 * @ordered
	 */
	PUBLIC(20, "PUBLIC", "public"),

	/**
	 * The '<em><b>PULL REQUEST REVIEW</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PULL_REQUEST_REVIEW_VALUE
	 * @generated
	 * @ordered
	 */
	PULL_REQUEST_REVIEW(21, "PULL_REQUEST_REVIEW", "pull_request_review"),

	/**
	 * The '<em><b>PULL REQUEST REVIEW COMMENT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PULL_REQUEST_REVIEW_COMMENT_VALUE
	 * @generated
	 * @ordered
	 */
	PULL_REQUEST_REVIEW_COMMENT(22, "PULL_REQUEST_REVIEW_COMMENT", "pull_request_review_comment"),

	/**
	 * The '<em><b>REGISTRY PACKAGE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REGISTRY_PACKAGE_VALUE
	 * @generated
	 * @ordered
	 */
	REGISTRY_PACKAGE(23, "REGISTRY_PACKAGE", "registry_package"),

	/**
	 * The '<em><b>RELEASE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RELEASE_VALUE
	 * @generated
	 * @ordered
	 */
	RELEASE(24, "RELEASE", "release"),

	/**
	 * The '<em><b>REPOSITORY DISPATCH</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REPOSITORY_DISPATCH_VALUE
	 * @generated
	 * @ordered
	 */
	REPOSITORY_DISPATCH(25, "REPOSITORY_DISPATCH", "repository_dispatch"),

	/**
	 * The '<em><b>STATUS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STATUS_VALUE
	 * @generated
	 * @ordered
	 */
	STATUS(26, "STATUS", "status"),

	/**
	 * The '<em><b>WATCH</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WATCH_VALUE
	 * @generated
	 * @ordered
	 */
	WATCH(27, "WATCH", "watch");

	/**
	 * The '<em><b>BRANCH PROTECTION RULE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BRANCH_PROTECTION_RULE
	 * @model literal="branch_protection_rule"
	 * @generated
	 * @ordered
	 */
	public static final int BRANCH_PROTECTION_RULE_VALUE = 0;

	/**
	 * The '<em><b>CHECK RUN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CHECK_RUN
	 * @model literal="check_run"
	 * @generated
	 * @ordered
	 */
	public static final int CHECK_RUN_VALUE = 1;

	/**
	 * The '<em><b>CHECK SUITE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CHECK_SUITE
	 * @model literal="check_suite"
	 * @generated
	 * @ordered
	 */
	public static final int CHECK_SUITE_VALUE = 2;

	/**
	 * The '<em><b>CREATE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CREATE
	 * @model literal="create"
	 * @generated
	 * @ordered
	 */
	public static final int CREATE_VALUE = 3;

	/**
	 * The '<em><b>DELETE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DELETE
	 * @model literal="delete"
	 * @generated
	 * @ordered
	 */
	public static final int DELETE_VALUE = 4;

	/**
	 * The '<em><b>DEPLOYMENT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DEPLOYMENT
	 * @model literal="deployment"
	 * @generated
	 * @ordered
	 */
	public static final int DEPLOYMENT_VALUE = 5;

	/**
	 * The '<em><b>DEPLOYMENT STATUS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DEPLOYMENT_STATUS
	 * @model literal="deployment_status"
	 * @generated
	 * @ordered
	 */
	public static final int DEPLOYMENT_STATUS_VALUE = 6;

	/**
	 * The '<em><b>DISCUSSION</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DISCUSSION
	 * @model literal="discussion"
	 * @generated
	 * @ordered
	 */
	public static final int DISCUSSION_VALUE = 7;

	/**
	 * The '<em><b>DISCUSSION COMMENT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DISCUSSION_COMMENT
	 * @model literal="discussion_comment"
	 * @generated
	 * @ordered
	 */
	public static final int DISCUSSION_COMMENT_VALUE = 8;

	/**
	 * The '<em><b>FORK</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FORK
	 * @model literal="fork"
	 * @generated
	 * @ordered
	 */
	public static final int FORK_VALUE = 9;

	/**
	 * The '<em><b>GOLLUM</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GOLLUM
	 * @model literal="gollum"
	 * @generated
	 * @ordered
	 */
	public static final int GOLLUM_VALUE = 10;

	/**
	 * The '<em><b>ISSUE COMMENT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ISSUE_COMMENT
	 * @model literal="issue_comment"
	 * @generated
	 * @ordered
	 */
	public static final int ISSUE_COMMENT_VALUE = 11;

	/**
	 * The '<em><b>ISSUES</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ISSUES
	 * @model literal="issues"
	 * @generated
	 * @ordered
	 */
	public static final int ISSUES_VALUE = 12;

	/**
	 * The '<em><b>LABEL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LABEL
	 * @model literal="label"
	 * @generated
	 * @ordered
	 */
	public static final int LABEL_VALUE = 13;

	/**
	 * The '<em><b>MERGE GROUP</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MERGE_GROUP
	 * @model literal="merge_group"
	 * @generated
	 * @ordered
	 */
	public static final int MERGE_GROUP_VALUE = 14;

	/**
	 * The '<em><b>MILESTONE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MILESTONE
	 * @model literal="milestone"
	 * @generated
	 * @ordered
	 */
	public static final int MILESTONE_VALUE = 15;

	/**
	 * The '<em><b>PAGE BUILD</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PAGE_BUILD
	 * @model literal="page_build"
	 * @generated
	 * @ordered
	 */
	public static final int PAGE_BUILD_VALUE = 16;

	/**
	 * The '<em><b>PROJECT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PROJECT
	 * @model literal="project"
	 * @generated
	 * @ordered
	 */
	public static final int PROJECT_VALUE = 17;

	/**
	 * The '<em><b>PROJECT CARD</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PROJECT_CARD
	 * @model literal="project_card"
	 * @generated
	 * @ordered
	 */
	public static final int PROJECT_CARD_VALUE = 18;

	/**
	 * The '<em><b>PROJECT COLUMN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PROJECT_COLUMN
	 * @model literal="project_column"
	 * @generated
	 * @ordered
	 */
	public static final int PROJECT_COLUMN_VALUE = 19;

	/**
	 * The '<em><b>PUBLIC</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PUBLIC
	 * @model literal="public"
	 * @generated
	 * @ordered
	 */
	public static final int PUBLIC_VALUE = 20;

	/**
	 * The '<em><b>PULL REQUEST REVIEW</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PULL_REQUEST_REVIEW
	 * @model literal="pull_request_review"
	 * @generated
	 * @ordered
	 */
	public static final int PULL_REQUEST_REVIEW_VALUE = 21;

	/**
	 * The '<em><b>PULL REQUEST REVIEW COMMENT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PULL_REQUEST_REVIEW_COMMENT
	 * @model literal="pull_request_review_comment"
	 * @generated
	 * @ordered
	 */
	public static final int PULL_REQUEST_REVIEW_COMMENT_VALUE = 22;

	/**
	 * The '<em><b>REGISTRY PACKAGE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REGISTRY_PACKAGE
	 * @model literal="registry_package"
	 * @generated
	 * @ordered
	 */
	public static final int REGISTRY_PACKAGE_VALUE = 23;

	/**
	 * The '<em><b>RELEASE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RELEASE
	 * @model literal="release"
	 * @generated
	 * @ordered
	 */
	public static final int RELEASE_VALUE = 24;

	/**
	 * The '<em><b>REPOSITORY DISPATCH</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REPOSITORY_DISPATCH
	 * @model literal="repository_dispatch"
	 * @generated
	 * @ordered
	 */
	public static final int REPOSITORY_DISPATCH_VALUE = 25;

	/**
	 * The '<em><b>STATUS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STATUS
	 * @model literal="status"
	 * @generated
	 * @ordered
	 */
	public static final int STATUS_VALUE = 26;

	/**
	 * The '<em><b>WATCH</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WATCH
	 * @model literal="watch"
	 * @generated
	 * @ordered
	 */
	public static final int WATCH_VALUE = 27;

	/**
	 * An array of all the '<em><b>EVENTS</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final EVENTS[] VALUES_ARRAY = new EVENTS[] { BRANCH_PROTECTION_RULE, CHECK_RUN, CHECK_SUITE, CREATE,
			DELETE, DEPLOYMENT, DEPLOYMENT_STATUS, DISCUSSION, DISCUSSION_COMMENT, FORK, GOLLUM, ISSUE_COMMENT, ISSUES,
			LABEL, MERGE_GROUP, MILESTONE, PAGE_BUILD, PROJECT, PROJECT_CARD, PROJECT_COLUMN, PUBLIC,
			PULL_REQUEST_REVIEW, PULL_REQUEST_REVIEW_COMMENT, REGISTRY_PACKAGE, RELEASE, REPOSITORY_DISPATCH, STATUS,
			WATCH, };

	/**
	 * A public read-only list of all the '<em><b>EVENTS</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<EVENTS> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>EVENTS</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static EVENTS get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			EVENTS result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>EVENTS</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static EVENTS getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			EVENTS result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>EVENTS</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static EVENTS get(int value) {
		switch (value) {
		case BRANCH_PROTECTION_RULE_VALUE:
			return BRANCH_PROTECTION_RULE;
		case CHECK_RUN_VALUE:
			return CHECK_RUN;
		case CHECK_SUITE_VALUE:
			return CHECK_SUITE;
		case CREATE_VALUE:
			return CREATE;
		case DELETE_VALUE:
			return DELETE;
		case DEPLOYMENT_VALUE:
			return DEPLOYMENT;
		case DEPLOYMENT_STATUS_VALUE:
			return DEPLOYMENT_STATUS;
		case DISCUSSION_VALUE:
			return DISCUSSION;
		case DISCUSSION_COMMENT_VALUE:
			return DISCUSSION_COMMENT;
		case FORK_VALUE:
			return FORK;
		case GOLLUM_VALUE:
			return GOLLUM;
		case ISSUE_COMMENT_VALUE:
			return ISSUE_COMMENT;
		case ISSUES_VALUE:
			return ISSUES;
		case LABEL_VALUE:
			return LABEL;
		case MERGE_GROUP_VALUE:
			return MERGE_GROUP;
		case MILESTONE_VALUE:
			return MILESTONE;
		case PAGE_BUILD_VALUE:
			return PAGE_BUILD;
		case PROJECT_VALUE:
			return PROJECT;
		case PROJECT_CARD_VALUE:
			return PROJECT_CARD;
		case PROJECT_COLUMN_VALUE:
			return PROJECT_COLUMN;
		case PUBLIC_VALUE:
			return PUBLIC;
		case PULL_REQUEST_REVIEW_VALUE:
			return PULL_REQUEST_REVIEW;
		case PULL_REQUEST_REVIEW_COMMENT_VALUE:
			return PULL_REQUEST_REVIEW_COMMENT;
		case REGISTRY_PACKAGE_VALUE:
			return REGISTRY_PACKAGE;
		case RELEASE_VALUE:
			return RELEASE;
		case REPOSITORY_DISPATCH_VALUE:
			return REPOSITORY_DISPATCH;
		case STATUS_VALUE:
			return STATUS;
		case WATCH_VALUE:
			return WATCH;
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
	private EVENTS(int value, String name, String literal) {
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

} //EVENTS
