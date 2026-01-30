/**
 */
package com.mddoai.metamodel.pim.pimMM;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Input</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Input#getType <em>Type</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Input#getDefaultValue <em>Default Value</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Input#isRequired <em>Required</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Input#getChoices <em>Choices</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getInput()
 * @model
 * @generated
 */
public interface Input extends Parameter {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link com.mddoai.metamodel.pim.pimMM.INPUT_TYPE}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see com.mddoai.metamodel.pim.pimMM.INPUT_TYPE
	 * @see #setType(INPUT_TYPE)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getInput_Type()
	 * @model required="true"
	 * @generated
	 */
	INPUT_TYPE getType();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.Input#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see com.mddoai.metamodel.pim.pimMM.INPUT_TYPE
	 * @see #getType()
	 * @generated
	 */
	void setType(INPUT_TYPE value);

	/**
	 * Returns the value of the '<em><b>Default Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Value</em>' containment reference.
	 * @see #setDefaultValue(Expression)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getInput_DefaultValue()
	 * @model containment="true"
	 * @generated
	 */
	Expression getDefaultValue();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.Input#getDefaultValue <em>Default Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Value</em>' containment reference.
	 * @see #getDefaultValue()
	 * @generated
	 */
	void setDefaultValue(Expression value);

	/**
	 * Returns the value of the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Required</em>' attribute.
	 * @see #setRequired(boolean)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getInput_Required()
	 * @model required="true"
	 * @generated
	 */
	boolean isRequired();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.Input#isRequired <em>Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Required</em>' attribute.
	 * @see #isRequired()
	 * @generated
	 */
	void setRequired(boolean value);

	/**
	 * Returns the value of the '<em><b>Choices</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Choices</em>' attribute list.
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getInput_Choices()
	 * @model
	 * @generated
	 */
	EList<String> getChoices();

} // Input
