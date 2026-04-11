/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variable Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableGroup#getGroup <em>Group</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getVariableGroup()
 * @model
 * @generated
 */
public interface VariableGroup extends VariableDefinition {
	/**
	 * Returns the value of the '<em><b>Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group</em>' attribute.
	 * @see #setGroup(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getVariableGroup_Group()
	 * @model required="true"
	 * @generated
	 */
	String getGroup();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableGroup#getGroup <em>Group</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Group</em>' attribute.
	 * @see #getGroup()
	 * @generated
	 */
	void setGroup(String value);

} // VariableGroup
