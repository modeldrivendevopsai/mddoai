/**
 */
package com.mddoai.metamodel.bambooMM;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Parser Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.TestParserTask#getType <em>Type</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.TestParserTask#getTestResults <em>Test Results</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.TestParserTask#getIgnoreTime <em>Ignore Time</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getTestParserTask()
 * @model
 * @generated
 */
public interface TestParserTask extends Task {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link com.mddoai.metamodel.bambooMM.TEST_PARSER_TYPE}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see com.mddoai.metamodel.bambooMM.TEST_PARSER_TYPE
	 * @see #setType(TEST_PARSER_TYPE)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getTestParserTask_Type()
	 * @model required="true"
	 * @generated
	 */
	TEST_PARSER_TYPE getType();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.TestParserTask#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see com.mddoai.metamodel.bambooMM.TEST_PARSER_TYPE
	 * @see #getType()
	 * @generated
	 */
	void setType(TEST_PARSER_TYPE value);

	/**
	 * Returns the value of the '<em><b>Test Results</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test Results</em>' attribute list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getTestParserTask_TestResults()
	 * @model
	 * @generated
	 */
	EList<String> getTestResults();

	/**
	 * Returns the value of the '<em><b>Ignore Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ignore Time</em>' attribute.
	 * @see #setIgnoreTime(Boolean)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getTestParserTask_IgnoreTime()
	 * @model
	 * @generated
	 */
	Boolean getIgnoreTime();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.TestParserTask#getIgnoreTime <em>Ignore Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ignore Time</em>' attribute.
	 * @see #getIgnoreTime()
	 * @generated
	 */
	void setIgnoreTime(Boolean value);

} // TestParserTask
