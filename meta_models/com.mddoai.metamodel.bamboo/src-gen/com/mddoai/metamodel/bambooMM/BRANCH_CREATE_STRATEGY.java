/**
 */
package com.mddoai.metamodel.bambooMM;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>BRANCH CREATE STRATEGY</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBRANCH_CREATE_STRATEGY()
 * @model
 * @generated
 */
public enum BRANCH_CREATE_STRATEGY implements Enumerator {
	/**
	 * The '<em><b>For New Branch</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FOR_NEW_BRANCH_VALUE
	 * @generated
	 * @ordered
	 */
	FOR_NEW_BRANCH(0, "forNewBranch", "for-new-branch"),

	/**
	 * The '<em><b>For Pull Request</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FOR_PULL_REQUEST_VALUE
	 * @generated
	 * @ordered
	 */
	FOR_PULL_REQUEST(1, "forPullRequest", "for-pull-request"),

	/**
	 * The '<em><b>Manually</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MANUALLY_VALUE
	 * @generated
	 * @ordered
	 */
	MANUALLY(2, "manually", "manually"),

	/**
	 * The '<em><b>Never</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NEVER_VALUE
	 * @generated
	 * @ordered
	 */
	NEVER(3, "never", "never");

	/**
	 * The '<em><b>For New Branch</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FOR_NEW_BRANCH
	 * @model name="forNewBranch" literal="for-new-branch"
	 * @generated
	 * @ordered
	 */
	public static final int FOR_NEW_BRANCH_VALUE = 0;

	/**
	 * The '<em><b>For Pull Request</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FOR_PULL_REQUEST
	 * @model name="forPullRequest" literal="for-pull-request"
	 * @generated
	 * @ordered
	 */
	public static final int FOR_PULL_REQUEST_VALUE = 1;

	/**
	 * The '<em><b>Manually</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MANUALLY
	 * @model name="manually"
	 * @generated
	 * @ordered
	 */
	public static final int MANUALLY_VALUE = 2;

	/**
	 * The '<em><b>Never</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NEVER
	 * @model name="never"
	 * @generated
	 * @ordered
	 */
	public static final int NEVER_VALUE = 3;

	/**
	 * An array of all the '<em><b>BRANCH CREATE STRATEGY</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final BRANCH_CREATE_STRATEGY[] VALUES_ARRAY = new BRANCH_CREATE_STRATEGY[] { FOR_NEW_BRANCH,
			FOR_PULL_REQUEST, MANUALLY, NEVER, };

	/**
	 * A public read-only list of all the '<em><b>BRANCH CREATE STRATEGY</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<BRANCH_CREATE_STRATEGY> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>BRANCH CREATE STRATEGY</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static BRANCH_CREATE_STRATEGY get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			BRANCH_CREATE_STRATEGY result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>BRANCH CREATE STRATEGY</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static BRANCH_CREATE_STRATEGY getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			BRANCH_CREATE_STRATEGY result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>BRANCH CREATE STRATEGY</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static BRANCH_CREATE_STRATEGY get(int value) {
		switch (value) {
		case FOR_NEW_BRANCH_VALUE:
			return FOR_NEW_BRANCH;
		case FOR_PULL_REQUEST_VALUE:
			return FOR_PULL_REQUEST;
		case MANUALLY_VALUE:
			return MANUALLY;
		case NEVER_VALUE:
			return NEVER;
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
	private BRANCH_CREATE_STRATEGY(int value, String name, String literal) {
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

} //BRANCH_CREATE_STRATEGY
