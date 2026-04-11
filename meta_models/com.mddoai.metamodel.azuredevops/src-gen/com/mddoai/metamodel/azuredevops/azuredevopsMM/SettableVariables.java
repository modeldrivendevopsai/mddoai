/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Settable Variables</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.SettableVariables#getNone <em>None</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.SettableVariables#getVariables <em>Variables</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getSettableVariables()
 * @model
 * @generated
 */
public interface SettableVariables extends EObject {
	/**
	 * Returns the value of the '<em><b>None</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>None</em>' attribute.
	 * @see #setNone(Boolean)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getSettableVariables_None()
	 * @model
	 * @generated
	 */
	Boolean getNone();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.SettableVariables#getNone <em>None</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>None</em>' attribute.
	 * @see #getNone()
	 * @generated
	 */
	void setNone(Boolean value);

	/**
	 * Returns the value of the '<em><b>Variables</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variables</em>' attribute list.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getSettableVariables_Variables()
	 * @model
	 * @generated
	 */
	EList<String> getVariables();

} // SettableVariables
