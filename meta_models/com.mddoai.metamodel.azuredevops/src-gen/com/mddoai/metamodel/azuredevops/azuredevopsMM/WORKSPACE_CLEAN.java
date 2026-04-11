/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>WORKSPACE CLEAN</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getWORKSPACE_CLEAN()
 * @model
 * @generated
 */
public enum WORKSPACE_CLEAN implements Enumerator {
	/**
	 * The '<em><b>Outputs</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OUTPUTS_VALUE
	 * @generated
	 * @ordered
	 */
	OUTPUTS(0, "outputs", "outputs"),

	/**
	 * The '<em><b>Resources</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RESOURCES_VALUE
	 * @generated
	 * @ordered
	 */
	RESOURCES(1, "resources", "resources"),

	/**
	 * The '<em><b>All</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ALL_VALUE
	 * @generated
	 * @ordered
	 */
	ALL(2, "all", "all");

	/**
	 * The '<em><b>Outputs</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OUTPUTS
	 * @model name="outputs"
	 * @generated
	 * @ordered
	 */
	public static final int OUTPUTS_VALUE = 0;

	/**
	 * The '<em><b>Resources</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RESOURCES
	 * @model name="resources"
	 * @generated
	 * @ordered
	 */
	public static final int RESOURCES_VALUE = 1;

	/**
	 * The '<em><b>All</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ALL
	 * @model name="all"
	 * @generated
	 * @ordered
	 */
	public static final int ALL_VALUE = 2;

	/**
	 * An array of all the '<em><b>WORKSPACE CLEAN</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final WORKSPACE_CLEAN[] VALUES_ARRAY = new WORKSPACE_CLEAN[] { OUTPUTS, RESOURCES, ALL, };

	/**
	 * A public read-only list of all the '<em><b>WORKSPACE CLEAN</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<WORKSPACE_CLEAN> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>WORKSPACE CLEAN</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static WORKSPACE_CLEAN get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			WORKSPACE_CLEAN result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>WORKSPACE CLEAN</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static WORKSPACE_CLEAN getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			WORKSPACE_CLEAN result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>WORKSPACE CLEAN</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static WORKSPACE_CLEAN get(int value) {
		switch (value) {
		case OUTPUTS_VALUE:
			return OUTPUTS;
		case RESOURCES_VALUE:
			return RESOURCES;
		case ALL_VALUE:
			return ALL;
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
	private WORKSPACE_CLEAN(int value, String name, String literal) {
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

} //WORKSPACE_CLEAN
