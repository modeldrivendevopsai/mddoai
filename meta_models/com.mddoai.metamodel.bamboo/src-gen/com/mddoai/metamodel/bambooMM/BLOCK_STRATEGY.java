/**
 */
package com.mddoai.metamodel.bambooMM;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>BLOCK STRATEGY</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBLOCK_STRATEGY()
 * @model
 * @generated
 */
public enum BLOCK_STRATEGY implements Enumerator {
	/**
	 * The '<em><b>Dont Block</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DONT_BLOCK_VALUE
	 * @generated
	 * @ordered
	 */
	DONT_BLOCK(0, "dontBlock", "dont_block"),

	/**
	 * The '<em><b>Block If Parent In Progress</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BLOCK_IF_PARENT_IN_PROGRESS_VALUE
	 * @generated
	 * @ordered
	 */
	BLOCK_IF_PARENT_IN_PROGRESS(1, "blockIfParentInProgress", "block_if_parent_in_progress"),

	/**
	 * The '<em><b>Block If Parent Has Unbuilt Changes</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BLOCK_IF_PARENT_HAS_UNBUILT_CHANGES_VALUE
	 * @generated
	 * @ordered
	 */
	BLOCK_IF_PARENT_HAS_UNBUILT_CHANGES(2, "blockIfParentHasUnbuiltChanges", "block_if_parent_has_unbuilt_changes");

	/**
	 * The '<em><b>Dont Block</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DONT_BLOCK
	 * @model name="dontBlock" literal="dont_block"
	 * @generated
	 * @ordered
	 */
	public static final int DONT_BLOCK_VALUE = 0;

	/**
	 * The '<em><b>Block If Parent In Progress</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BLOCK_IF_PARENT_IN_PROGRESS
	 * @model name="blockIfParentInProgress" literal="block_if_parent_in_progress"
	 * @generated
	 * @ordered
	 */
	public static final int BLOCK_IF_PARENT_IN_PROGRESS_VALUE = 1;

	/**
	 * The '<em><b>Block If Parent Has Unbuilt Changes</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BLOCK_IF_PARENT_HAS_UNBUILT_CHANGES
	 * @model name="blockIfParentHasUnbuiltChanges" literal="block_if_parent_has_unbuilt_changes"
	 * @generated
	 * @ordered
	 */
	public static final int BLOCK_IF_PARENT_HAS_UNBUILT_CHANGES_VALUE = 2;

	/**
	 * An array of all the '<em><b>BLOCK STRATEGY</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final BLOCK_STRATEGY[] VALUES_ARRAY = new BLOCK_STRATEGY[] { DONT_BLOCK, BLOCK_IF_PARENT_IN_PROGRESS,
			BLOCK_IF_PARENT_HAS_UNBUILT_CHANGES, };

	/**
	 * A public read-only list of all the '<em><b>BLOCK STRATEGY</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<BLOCK_STRATEGY> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>BLOCK STRATEGY</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static BLOCK_STRATEGY get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			BLOCK_STRATEGY result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>BLOCK STRATEGY</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static BLOCK_STRATEGY getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			BLOCK_STRATEGY result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>BLOCK STRATEGY</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static BLOCK_STRATEGY get(int value) {
		switch (value) {
		case DONT_BLOCK_VALUE:
			return DONT_BLOCK;
		case BLOCK_IF_PARENT_IN_PROGRESS_VALUE:
			return BLOCK_IF_PARENT_IN_PROGRESS;
		case BLOCK_IF_PARENT_HAS_UNBUILT_CHANGES_VALUE:
			return BLOCK_IF_PARENT_HAS_UNBUILT_CHANGES;
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
	private BLOCK_STRATEGY(int value, String name, String literal) {
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

} //BLOCK_STRATEGY
