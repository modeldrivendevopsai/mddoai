/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Workspace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Workspace#getClean <em>Clean</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getWorkspace()
 * @model
 * @generated
 */
public interface Workspace extends EObject {
	/**
	 * Returns the value of the '<em><b>Clean</b></em>' attribute.
	 * The literals are from the enumeration {@link com.mddoai.metamodel.azuredevops.azuredevopsMM.WORKSPACE_CLEAN}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Clean</em>' attribute.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.WORKSPACE_CLEAN
	 * @see #setClean(WORKSPACE_CLEAN)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getWorkspace_Clean()
	 * @model
	 * @generated
	 */
	WORKSPACE_CLEAN getClean();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Workspace#getClean <em>Clean</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Clean</em>' attribute.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.WORKSPACE_CLEAN
	 * @see #getClean()
	 * @generated
	 */
	void setClean(WORKSPACE_CLEAN value);

} // Workspace
