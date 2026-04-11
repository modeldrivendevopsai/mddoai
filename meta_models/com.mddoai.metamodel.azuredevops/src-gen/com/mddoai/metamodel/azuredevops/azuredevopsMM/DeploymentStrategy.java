/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Deployment Strategy</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentStrategy#getRunOnce <em>Run Once</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentStrategy#getRolling <em>Rolling</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentStrategy#getCanary <em>Canary</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getDeploymentStrategy()
 * @model
 * @generated
 */
public interface DeploymentStrategy extends EObject {
	/**
	 * Returns the value of the '<em><b>Run Once</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Run Once</em>' containment reference.
	 * @see #setRunOnce(RunOnceStrategy)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getDeploymentStrategy_RunOnce()
	 * @model containment="true"
	 * @generated
	 */
	RunOnceStrategy getRunOnce();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentStrategy#getRunOnce <em>Run Once</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Run Once</em>' containment reference.
	 * @see #getRunOnce()
	 * @generated
	 */
	void setRunOnce(RunOnceStrategy value);

	/**
	 * Returns the value of the '<em><b>Rolling</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rolling</em>' containment reference.
	 * @see #setRolling(RollingStrategy)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getDeploymentStrategy_Rolling()
	 * @model containment="true"
	 * @generated
	 */
	RollingStrategy getRolling();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentStrategy#getRolling <em>Rolling</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rolling</em>' containment reference.
	 * @see #getRolling()
	 * @generated
	 */
	void setRolling(RollingStrategy value);

	/**
	 * Returns the value of the '<em><b>Canary</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Canary</em>' containment reference.
	 * @see #setCanary(CanaryStrategy)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getDeploymentStrategy_Canary()
	 * @model containment="true"
	 * @generated
	 */
	CanaryStrategy getCanary();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentStrategy#getCanary <em>Canary</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Canary</em>' containment reference.
	 * @see #getCanary()
	 * @generated
	 */
	void setCanary(CanaryStrategy value);

} // DeploymentStrategy
