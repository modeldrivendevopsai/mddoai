/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>LOCK BEHAVIOR</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getLOCK_BEHAVIOR()
 * @model
 * @generated
 */
public enum LOCK_BEHAVIOR implements Enumerator {
	/**
	 * The '<em><b>Sequential</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEQUENTIAL_VALUE
	 * @generated
	 * @ordered
	 */
	SEQUENTIAL(0, "sequential", "sequential"),

	/**
	 * The '<em><b>Run Latest</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RUN_LATEST_VALUE
	 * @generated
	 * @ordered
	 */
	RUN_LATEST(1, "runLatest", "runLatest");

	/**
	 * The '<em><b>Sequential</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEQUENTIAL
	 * @model name="sequential"
	 * @generated
	 * @ordered
	 */
	public static final int SEQUENTIAL_VALUE = 0;

	/**
	 * The '<em><b>Run Latest</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RUN_LATEST
	 * @model name="runLatest"
	 * @generated
	 * @ordered
	 */
	public static final int RUN_LATEST_VALUE = 1;

	/**
	 * An array of all the '<em><b>LOCK BEHAVIOR</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final LOCK_BEHAVIOR[] VALUES_ARRAY = new LOCK_BEHAVIOR[] { SEQUENTIAL, RUN_LATEST, };

	/**
	 * A public read-only list of all the '<em><b>LOCK BEHAVIOR</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<LOCK_BEHAVIOR> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>LOCK BEHAVIOR</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static LOCK_BEHAVIOR get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LOCK_BEHAVIOR result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>LOCK BEHAVIOR</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static LOCK_BEHAVIOR getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LOCK_BEHAVIOR result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>LOCK BEHAVIOR</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static LOCK_BEHAVIOR get(int value) {
		switch (value) {
		case SEQUENTIAL_VALUE:
			return SEQUENTIAL;
		case RUN_LATEST_VALUE:
			return RUN_LATEST;
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
	private LOCK_BEHAVIOR(int value, String name, String literal) {
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

} //LOCK_BEHAVIOR
