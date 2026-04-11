/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>STAGE TRIGGER</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getSTAGE_TRIGGER()
 * @model
 * @generated
 */
public enum STAGE_TRIGGER implements Enumerator {
	/**
	 * The '<em><b>Manual</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MANUAL_VALUE
	 * @generated
	 * @ordered
	 */
	MANUAL(0, "manual", "manual"),

	/**
	 * The '<em><b>Automatic</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #AUTOMATIC_VALUE
	 * @generated
	 * @ordered
	 */
	AUTOMATIC(1, "automatic", "automatic");

	/**
	 * The '<em><b>Manual</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MANUAL
	 * @model name="manual"
	 * @generated
	 * @ordered
	 */
	public static final int MANUAL_VALUE = 0;

	/**
	 * The '<em><b>Automatic</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #AUTOMATIC
	 * @model name="automatic"
	 * @generated
	 * @ordered
	 */
	public static final int AUTOMATIC_VALUE = 1;

	/**
	 * An array of all the '<em><b>STAGE TRIGGER</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final STAGE_TRIGGER[] VALUES_ARRAY = new STAGE_TRIGGER[] { MANUAL, AUTOMATIC, };

	/**
	 * A public read-only list of all the '<em><b>STAGE TRIGGER</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<STAGE_TRIGGER> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>STAGE TRIGGER</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static STAGE_TRIGGER get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			STAGE_TRIGGER result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>STAGE TRIGGER</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static STAGE_TRIGGER getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			STAGE_TRIGGER result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>STAGE TRIGGER</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static STAGE_TRIGGER get(int value) {
		switch (value) {
		case MANUAL_VALUE:
			return MANUAL;
		case AUTOMATIC_VALUE:
			return AUTOMATIC;
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
	private STAGE_TRIGGER(int value, String name, String literal) {
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

} //STAGE_TRIGGER
