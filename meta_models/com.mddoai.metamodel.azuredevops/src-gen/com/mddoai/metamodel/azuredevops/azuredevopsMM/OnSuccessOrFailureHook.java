/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>On Success Or Failure Hook</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.OnSuccessOrFailureHook#getFailure <em>Failure</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.OnSuccessOrFailureHook#getSuccess <em>Success</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getOnSuccessOrFailureHook()
 * @model
 * @generated
 */
public interface OnSuccessOrFailureHook extends EObject {
	/**
	 * Returns the value of the '<em><b>Failure</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Failure</em>' containment reference.
	 * @see #setFailure(DeployHook)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getOnSuccessOrFailureHook_Failure()
	 * @model containment="true"
	 * @generated
	 */
	DeployHook getFailure();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.OnSuccessOrFailureHook#getFailure <em>Failure</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Failure</em>' containment reference.
	 * @see #getFailure()
	 * @generated
	 */
	void setFailure(DeployHook value);

	/**
	 * Returns the value of the '<em><b>Success</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Success</em>' containment reference.
	 * @see #setSuccess(DeployHook)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getOnSuccessOrFailureHook_Success()
	 * @model containment="true"
	 * @generated
	 */
	DeployHook getSuccess();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.OnSuccessOrFailureHook#getSuccess <em>Success</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Success</em>' containment reference.
	 * @see #getSuccess()
	 * @generated
	 */
	void setSuccess(DeployHook value);

} // OnSuccessOrFailureHook
