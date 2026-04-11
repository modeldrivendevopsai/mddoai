/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>REPOSITORY TYPE</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getREPOSITORY_TYPE()
 * @model
 * @generated
 */
public enum REPOSITORY_TYPE implements Enumerator {
	/**
	 * The '<em><b>Git</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GIT_VALUE
	 * @generated
	 * @ordered
	 */
	GIT(0, "git", "git"),

	/**
	 * The '<em><b>Github</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GITHUB_VALUE
	 * @generated
	 * @ordered
	 */
	GITHUB(1, "github", "github"),

	/**
	 * The '<em><b>Githubenterprise</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GITHUBENTERPRISE_VALUE
	 * @generated
	 * @ordered
	 */
	GITHUBENTERPRISE(2, "githubenterprise", "githubenterprise"),

	/**
	 * The '<em><b>Bitbucket</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BITBUCKET_VALUE
	 * @generated
	 * @ordered
	 */
	BITBUCKET(3, "bitbucket", "bitbucket");

	/**
	 * The '<em><b>Git</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GIT
	 * @model name="git"
	 * @generated
	 * @ordered
	 */
	public static final int GIT_VALUE = 0;

	/**
	 * The '<em><b>Github</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GITHUB
	 * @model name="github"
	 * @generated
	 * @ordered
	 */
	public static final int GITHUB_VALUE = 1;

	/**
	 * The '<em><b>Githubenterprise</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GITHUBENTERPRISE
	 * @model name="githubenterprise"
	 * @generated
	 * @ordered
	 */
	public static final int GITHUBENTERPRISE_VALUE = 2;

	/**
	 * The '<em><b>Bitbucket</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BITBUCKET
	 * @model name="bitbucket"
	 * @generated
	 * @ordered
	 */
	public static final int BITBUCKET_VALUE = 3;

	/**
	 * An array of all the '<em><b>REPOSITORY TYPE</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final REPOSITORY_TYPE[] VALUES_ARRAY = new REPOSITORY_TYPE[] { GIT, GITHUB, GITHUBENTERPRISE,
			BITBUCKET, };

	/**
	 * A public read-only list of all the '<em><b>REPOSITORY TYPE</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<REPOSITORY_TYPE> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>REPOSITORY TYPE</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static REPOSITORY_TYPE get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			REPOSITORY_TYPE result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>REPOSITORY TYPE</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static REPOSITORY_TYPE getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			REPOSITORY_TYPE result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>REPOSITORY TYPE</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static REPOSITORY_TYPE get(int value) {
		switch (value) {
		case GIT_VALUE:
			return GIT;
		case GITHUB_VALUE:
			return GITHUB;
		case GITHUBENTERPRISE_VALUE:
			return GITHUBENTERPRISE;
		case BITBUCKET_VALUE:
			return BITBUCKET;
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
	private REPOSITORY_TYPE(int value, String name, String literal) {
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

} //REPOSITORY_TYPE
