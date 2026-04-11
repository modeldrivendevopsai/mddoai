/**
 */
package com.mddoai.metamodel.bambooMM;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Task Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.TaskCondition#getVariableName <em>Variable Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.TaskCondition#getVariableValue <em>Variable Value</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getTaskCondition()
 * @model
 * @generated
 */
public interface TaskCondition extends EObject {
	/**
	 * Returns the value of the '<em><b>Variable Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variable Name</em>' attribute.
	 * @see #setVariableName(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getTaskCondition_VariableName()
	 * @model required="true"
	 * @generated
	 */
	String getVariableName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.TaskCondition#getVariableName <em>Variable Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variable Name</em>' attribute.
	 * @see #getVariableName()
	 * @generated
	 */
	void setVariableName(String value);

	/**
	 * Returns the value of the '<em><b>Variable Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variable Value</em>' attribute.
	 * @see #setVariableValue(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getTaskCondition_VariableValue()
	 * @model required="true"
	 * @generated
	 */
	String getVariableValue();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.TaskCondition#getVariableValue <em>Variable Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variable Value</em>' attribute.
	 * @see #getVariableValue()
	 * @generated
	 */
	void setVariableValue(String value);

} // TaskCondition
