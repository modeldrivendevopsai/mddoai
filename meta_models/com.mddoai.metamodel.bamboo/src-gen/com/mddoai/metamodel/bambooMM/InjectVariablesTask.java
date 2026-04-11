/**
 */
package com.mddoai.metamodel.bambooMM;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Inject Variables Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.InjectVariablesTask#getFile <em>File</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.InjectVariablesTask#getScope <em>Scope</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.InjectVariablesTask#getNamespace <em>Namespace</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getInjectVariablesTask()
 * @model
 * @generated
 */
public interface InjectVariablesTask extends Task {
	/**
	 * Returns the value of the '<em><b>File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>File</em>' attribute.
	 * @see #setFile(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getInjectVariablesTask_File()
	 * @model required="true"
	 * @generated
	 */
	String getFile();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.InjectVariablesTask#getFile <em>File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File</em>' attribute.
	 * @see #getFile()
	 * @generated
	 */
	void setFile(String value);

	/**
	 * Returns the value of the '<em><b>Scope</b></em>' attribute.
	 * The literals are from the enumeration {@link com.mddoai.metamodel.bambooMM.VARIABLE_SCOPE}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scope</em>' attribute.
	 * @see com.mddoai.metamodel.bambooMM.VARIABLE_SCOPE
	 * @see #setScope(VARIABLE_SCOPE)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getInjectVariablesTask_Scope()
	 * @model
	 * @generated
	 */
	VARIABLE_SCOPE getScope();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.InjectVariablesTask#getScope <em>Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scope</em>' attribute.
	 * @see com.mddoai.metamodel.bambooMM.VARIABLE_SCOPE
	 * @see #getScope()
	 * @generated
	 */
	void setScope(VARIABLE_SCOPE value);

	/**
	 * Returns the value of the '<em><b>Namespace</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Namespace</em>' attribute.
	 * @see #setNamespace(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getInjectVariablesTask_Namespace()
	 * @model
	 * @generated
	 */
	String getNamespace();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.InjectVariablesTask#getNamespace <em>Namespace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Namespace</em>' attribute.
	 * @see #getNamespace()
	 * @generated
	 */
	void setNamespace(String value);

} // InjectVariablesTask
