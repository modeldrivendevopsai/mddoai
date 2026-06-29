/**
 */
package com.mddoai.metamodel.github.githubMM;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>PERMISSION SCOPES</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getPERMISSION_SCOPES()
 * @model
 * @generated
 */
public enum PERMISSION_SCOPES implements Enumerator {
	/**
	 * The '<em><b>ACTIONS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ACTIONS_VALUE
	 * @generated
	 * @ordered
	 */
	ACTIONS(0, "ACTIONS", "actions"),

	/**
	 * The '<em><b>CHECKS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CHECKS_VALUE
	 * @generated
	 * @ordered
	 */
	CHECKS(1, "CHECKS", "checks"),

	/**
	 * The '<em><b>CONTENTS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONTENTS_VALUE
	 * @generated
	 * @ordered
	 */
	CONTENTS(2, "CONTENTS", "contents"),

	/**
	 * The '<em><b>DEPLOYMENTS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DEPLOYMENTS_VALUE
	 * @generated
	 * @ordered
	 */
	DEPLOYMENTS(3, "DEPLOYMENTS", "deployments"),

	/**
	 * The '<em><b>DISCUSSIONS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DISCUSSIONS_VALUE
	 * @generated
	 * @ordered
	 */
	DISCUSSIONS(4, "DISCUSSIONS", "discussions"),

	/**
	 * The '<em><b>ID TOKEN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ID_TOKEN_VALUE
	 * @generated
	 * @ordered
	 */
	ID_TOKEN(5, "ID_TOKEN", "id-token"),

	/**
	 * The '<em><b>ISSUES</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ISSUES_VALUE
	 * @generated
	 * @ordered
	 */
	ISSUES(6, "ISSUES", "issues"),

	/**
	 * The '<em><b>PACKAGES</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PACKAGES_VALUE
	 * @generated
	 * @ordered
	 */
	PACKAGES(7, "PACKAGES", "packages"),

	/**
	 * The '<em><b>PAGES</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PAGES_VALUE
	 * @generated
	 * @ordered
	 */
	PAGES(8, "PAGES", "pages"),

	/**
	 * The '<em><b>PULL REQUESTS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PULL_REQUESTS_VALUE
	 * @generated
	 * @ordered
	 */
	PULL_REQUESTS(9, "PULL_REQUESTS", "pull_requests"),

	/**
	 * The '<em><b>REPOSITORY PROJECTS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REPOSITORY_PROJECTS_VALUE
	 * @generated
	 * @ordered
	 */
	REPOSITORY_PROJECTS(10, "REPOSITORY_PROJECTS", "repository_projects"),

	/**
	 * The '<em><b>SECURITY EVENTS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SECURITY_EVENTS_VALUE
	 * @generated
	 * @ordered
	 */
	SECURITY_EVENTS(11, "SECURITY_EVENTS", "security_events"),

	/**
	 * The '<em><b>STATUSES</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STATUSES_VALUE
	 * @generated
	 * @ordered
	 */
	STATUSES(12, "STATUSES", "statuses");

	/**
	 * The '<em><b>ACTIONS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ACTIONS
	 * @model literal="actions"
	 * @generated
	 * @ordered
	 */
	public static final int ACTIONS_VALUE = 0;

	/**
	 * The '<em><b>CHECKS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CHECKS
	 * @model literal="checks"
	 * @generated
	 * @ordered
	 */
	public static final int CHECKS_VALUE = 1;

	/**
	 * The '<em><b>CONTENTS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONTENTS
	 * @model literal="contents"
	 * @generated
	 * @ordered
	 */
	public static final int CONTENTS_VALUE = 2;

	/**
	 * The '<em><b>DEPLOYMENTS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DEPLOYMENTS
	 * @model literal="deployments"
	 * @generated
	 * @ordered
	 */
	public static final int DEPLOYMENTS_VALUE = 3;

	/**
	 * The '<em><b>DISCUSSIONS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DISCUSSIONS
	 * @model literal="discussions"
	 * @generated
	 * @ordered
	 */
	public static final int DISCUSSIONS_VALUE = 4;

	/**
	 * The '<em><b>ID TOKEN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ID_TOKEN
	 * @model literal="id-token"
	 * @generated
	 * @ordered
	 */
	public static final int ID_TOKEN_VALUE = 5;

	/**
	 * The '<em><b>ISSUES</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ISSUES
	 * @model literal="issues"
	 * @generated
	 * @ordered
	 */
	public static final int ISSUES_VALUE = 6;

	/**
	 * The '<em><b>PACKAGES</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PACKAGES
	 * @model literal="packages"
	 * @generated
	 * @ordered
	 */
	public static final int PACKAGES_VALUE = 7;

	/**
	 * The '<em><b>PAGES</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PAGES
	 * @model literal="pages"
	 * @generated
	 * @ordered
	 */
	public static final int PAGES_VALUE = 8;

	/**
	 * The '<em><b>PULL REQUESTS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PULL_REQUESTS
	 * @model literal="pull_requests"
	 * @generated
	 * @ordered
	 */
	public static final int PULL_REQUESTS_VALUE = 9;

	/**
	 * The '<em><b>REPOSITORY PROJECTS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REPOSITORY_PROJECTS
	 * @model literal="repository_projects"
	 * @generated
	 * @ordered
	 */
	public static final int REPOSITORY_PROJECTS_VALUE = 10;

	/**
	 * The '<em><b>SECURITY EVENTS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SECURITY_EVENTS
	 * @model literal="security_events"
	 * @generated
	 * @ordered
	 */
	public static final int SECURITY_EVENTS_VALUE = 11;

	/**
	 * The '<em><b>STATUSES</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STATUSES
	 * @model literal="statuses"
	 * @generated
	 * @ordered
	 */
	public static final int STATUSES_VALUE = 12;

	/**
	 * An array of all the '<em><b>PERMISSION SCOPES</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final PERMISSION_SCOPES[] VALUES_ARRAY = new PERMISSION_SCOPES[] { ACTIONS, CHECKS, CONTENTS,
			DEPLOYMENTS, DISCUSSIONS, ID_TOKEN, ISSUES, PACKAGES, PAGES, PULL_REQUESTS, REPOSITORY_PROJECTS,
			SECURITY_EVENTS, STATUSES, };

	/**
	 * A public read-only list of all the '<em><b>PERMISSION SCOPES</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<PERMISSION_SCOPES> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>PERMISSION SCOPES</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static PERMISSION_SCOPES get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PERMISSION_SCOPES result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>PERMISSION SCOPES</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static PERMISSION_SCOPES getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PERMISSION_SCOPES result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>PERMISSION SCOPES</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static PERMISSION_SCOPES get(int value) {
		switch (value) {
		case ACTIONS_VALUE:
			return ACTIONS;
		case CHECKS_VALUE:
			return CHECKS;
		case CONTENTS_VALUE:
			return CONTENTS;
		case DEPLOYMENTS_VALUE:
			return DEPLOYMENTS;
		case DISCUSSIONS_VALUE:
			return DISCUSSIONS;
		case ID_TOKEN_VALUE:
			return ID_TOKEN;
		case ISSUES_VALUE:
			return ISSUES;
		case PACKAGES_VALUE:
			return PACKAGES;
		case PAGES_VALUE:
			return PAGES;
		case PULL_REQUESTS_VALUE:
			return PULL_REQUESTS;
		case REPOSITORY_PROJECTS_VALUE:
			return REPOSITORY_PROJECTS;
		case SECURITY_EVENTS_VALUE:
			return SECURITY_EVENTS;
		case STATUSES_VALUE:
			return STATUSES;
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
	private PERMISSION_SCOPES(int value, String name, String literal) {
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

} //PERMISSION_SCOPES
