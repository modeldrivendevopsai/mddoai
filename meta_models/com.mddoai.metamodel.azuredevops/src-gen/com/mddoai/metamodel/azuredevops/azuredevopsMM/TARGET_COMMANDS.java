/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>TARGET COMMANDS</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getTARGET_COMMANDS()
 * @model
 * @generated
 */
public enum TARGET_COMMANDS implements Enumerator {
	/**
	 * The '<em><b>Any</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ANY_VALUE
	 * @generated
	 * @ordered
	 */
	ANY(0, "any", "any"),

	/**
	 * The '<em><b>Restricted</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RESTRICTED_VALUE
	 * @generated
	 * @ordered
	 */
	RESTRICTED(1, "restricted", "restricted");

	/**
	 * The '<em><b>Any</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ANY
	 * @model name="any"
	 * @generated
	 * @ordered
	 */
	public static final int ANY_VALUE = 0;

	/**
	 * The '<em><b>Restricted</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RESTRICTED
	 * @model name="restricted"
	 * @generated
	 * @ordered
	 */
	public static final int RESTRICTED_VALUE = 1;

	/**
	 * An array of all the '<em><b>TARGET COMMANDS</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final TARGET_COMMANDS[] VALUES_ARRAY = new TARGET_COMMANDS[] { ANY, RESTRICTED, };

	/**
	 * A public read-only list of all the '<em><b>TARGET COMMANDS</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<TARGET_COMMANDS> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>TARGET COMMANDS</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static TARGET_COMMANDS get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TARGET_COMMANDS result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>TARGET COMMANDS</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static TARGET_COMMANDS getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TARGET_COMMANDS result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>TARGET COMMANDS</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static TARGET_COMMANDS get(int value) {
		switch (value) {
		case ANY_VALUE:
			return ANY;
		case RESTRICTED_VALUE:
			return RESTRICTED;
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
	private TARGET_COMMANDS(int value, String name, String literal) {
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

} //TARGET_COMMANDS
