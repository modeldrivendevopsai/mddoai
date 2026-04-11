/**
 */
package com.mddoai.metamodel.bambooMM;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>TEST PARSER TYPE</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getTEST_PARSER_TYPE()
 * @model
 * @generated
 */
public enum TEST_PARSER_TYPE implements Enumerator {
	/**
	 * The '<em><b>Junit</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JUNIT_VALUE
	 * @generated
	 * @ordered
	 */
	JUNIT(0, "junit", "junit"),

	/**
	 * The '<em><b>Testng</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TESTNG_VALUE
	 * @generated
	 * @ordered
	 */
	TESTNG(1, "testng", "testng"),

	/**
	 * The '<em><b>Mocha</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MOCHA_VALUE
	 * @generated
	 * @ordered
	 */
	MOCHA(2, "mocha", "mocha"),

	/**
	 * The '<em><b>Nunit</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NUNIT_VALUE
	 * @generated
	 * @ordered
	 */
	NUNIT(3, "nunit", "nunit"),

	/**
	 * The '<em><b>Mstest</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MSTEST_VALUE
	 * @generated
	 * @ordered
	 */
	MSTEST(4, "mstest", "mstest");

	/**
	 * The '<em><b>Junit</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #JUNIT
	 * @model name="junit"
	 * @generated
	 * @ordered
	 */
	public static final int JUNIT_VALUE = 0;

	/**
	 * The '<em><b>Testng</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TESTNG
	 * @model name="testng"
	 * @generated
	 * @ordered
	 */
	public static final int TESTNG_VALUE = 1;

	/**
	 * The '<em><b>Mocha</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MOCHA
	 * @model name="mocha"
	 * @generated
	 * @ordered
	 */
	public static final int MOCHA_VALUE = 2;

	/**
	 * The '<em><b>Nunit</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NUNIT
	 * @model name="nunit"
	 * @generated
	 * @ordered
	 */
	public static final int NUNIT_VALUE = 3;

	/**
	 * The '<em><b>Mstest</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MSTEST
	 * @model name="mstest"
	 * @generated
	 * @ordered
	 */
	public static final int MSTEST_VALUE = 4;

	/**
	 * An array of all the '<em><b>TEST PARSER TYPE</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final TEST_PARSER_TYPE[] VALUES_ARRAY = new TEST_PARSER_TYPE[] { JUNIT, TESTNG, MOCHA, NUNIT,
			MSTEST, };

	/**
	 * A public read-only list of all the '<em><b>TEST PARSER TYPE</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<TEST_PARSER_TYPE> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>TEST PARSER TYPE</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static TEST_PARSER_TYPE get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TEST_PARSER_TYPE result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>TEST PARSER TYPE</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static TEST_PARSER_TYPE getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TEST_PARSER_TYPE result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>TEST PARSER TYPE</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static TEST_PARSER_TYPE get(int value) {
		switch (value) {
		case JUNIT_VALUE:
			return JUNIT;
		case TESTNG_VALUE:
			return TESTNG;
		case MOCHA_VALUE:
			return MOCHA;
		case NUNIT_VALUE:
			return NUNIT;
		case MSTEST_VALUE:
			return MSTEST;
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
	private TEST_PARSER_TYPE(int value, String name, String literal) {
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

} //TEST_PARSER_TYPE
