/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Deploy Hook</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeployHook#getPool <em>Pool</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeployHook#getSteps <em>Steps</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getDeployHook()
 * @model
 * @generated
 */
public interface DeployHook extends EObject {
	/**
	 * Returns the value of the '<em><b>Pool</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pool</em>' containment reference.
	 * @see #setPool(Pool)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getDeployHook_Pool()
	 * @model containment="true"
	 * @generated
	 */
	Pool getPool();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeployHook#getPool <em>Pool</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pool</em>' containment reference.
	 * @see #getPool()
	 * @generated
	 */
	void setPool(Pool value);

	/**
	 * Returns the value of the '<em><b>Steps</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Step}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Steps</em>' containment reference list.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getDeployHook_Steps()
	 * @model containment="true"
	 * @generated
	 */
	EList<Step> getSteps();

} // DeployHook
