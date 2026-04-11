/**
 */
package com.mddoai.metamodel.bambooMM;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>PERMISSION ROLE</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getPERMISSION_ROLE()
 * @model
 * @generated
 */
public enum PERMISSION_ROLE implements Enumerator {
	/**
	 * The '<em><b>Logged In</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOGGED_IN_VALUE
	 * @generated
	 * @ordered
	 */
	LOGGED_IN(0, "loggedIn", "logged-in"),

	/**
	 * The '<em><b>Anonymous</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ANONYMOUS_VALUE
	 * @generated
	 * @ordered
	 */
	ANONYMOUS(1, "anonymous", "anonymous");

	/**
	 * The '<em><b>Logged In</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOGGED_IN
	 * @model name="loggedIn" literal="logged-in"
	 * @generated
	 * @ordered
	 */
	public static final int LOGGED_IN_VALUE = 0;

	/**
	 * The '<em><b>Anonymous</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ANONYMOUS
	 * @model name="anonymous"
	 * @generated
	 * @ordered
	 */
	public static final int ANONYMOUS_VALUE = 1;

	/**
	 * An array of all the '<em><b>PERMISSION ROLE</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final PERMISSION_ROLE[] VALUES_ARRAY = new PERMISSION_ROLE[] { LOGGED_IN, ANONYMOUS, };

	/**
	 * A public read-only list of all the '<em><b>PERMISSION ROLE</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<PERMISSION_ROLE> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>PERMISSION ROLE</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static PERMISSION_ROLE get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PERMISSION_ROLE result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>PERMISSION ROLE</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static PERMISSION_ROLE getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PERMISSION_ROLE result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>PERMISSION ROLE</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static PERMISSION_ROLE get(int value) {
		switch (value) {
		case LOGGED_IN_VALUE:
			return LOGGED_IN;
		case ANONYMOUS_VALUE:
			return ANONYMOUS;
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
	private PERMISSION_ROLE(int value, String name, String literal) {
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

} //PERMISSION_ROLE
