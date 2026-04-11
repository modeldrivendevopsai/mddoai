/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Rolling Strategy</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.RollingStrategy#getMaxParallel <em>Max Parallel</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.RollingStrategy#getPreDeploy <em>Pre Deploy</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.RollingStrategy#getDeploy <em>Deploy</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.RollingStrategy#getRouteTraffic <em>Route Traffic</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.RollingStrategy#getPostRouteTraffic <em>Post Route Traffic</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.RollingStrategy#getOn <em>On</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getRollingStrategy()
 * @model
 * @generated
 */
public interface RollingStrategy extends EObject {
	/**
	 * Returns the value of the '<em><b>Max Parallel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Parallel</em>' attribute.
	 * @see #setMaxParallel(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getRollingStrategy_MaxParallel()
	 * @model
	 * @generated
	 */
	String getMaxParallel();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.RollingStrategy#getMaxParallel <em>Max Parallel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Parallel</em>' attribute.
	 * @see #getMaxParallel()
	 * @generated
	 */
	void setMaxParallel(String value);

	/**
	 * Returns the value of the '<em><b>Pre Deploy</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pre Deploy</em>' containment reference.
	 * @see #setPreDeploy(DeployHook)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getRollingStrategy_PreDeploy()
	 * @model containment="true"
	 * @generated
	 */
	DeployHook getPreDeploy();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.RollingStrategy#getPreDeploy <em>Pre Deploy</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pre Deploy</em>' containment reference.
	 * @see #getPreDeploy()
	 * @generated
	 */
	void setPreDeploy(DeployHook value);

	/**
	 * Returns the value of the '<em><b>Deploy</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Deploy</em>' containment reference.
	 * @see #setDeploy(DeployHook)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getRollingStrategy_Deploy()
	 * @model containment="true"
	 * @generated
	 */
	DeployHook getDeploy();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.RollingStrategy#getDeploy <em>Deploy</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Deploy</em>' containment reference.
	 * @see #getDeploy()
	 * @generated
	 */
	void setDeploy(DeployHook value);

	/**
	 * Returns the value of the '<em><b>Route Traffic</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Route Traffic</em>' containment reference.
	 * @see #setRouteTraffic(DeployHook)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getRollingStrategy_RouteTraffic()
	 * @model containment="true"
	 * @generated
	 */
	DeployHook getRouteTraffic();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.RollingStrategy#getRouteTraffic <em>Route Traffic</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Route Traffic</em>' containment reference.
	 * @see #getRouteTraffic()
	 * @generated
	 */
	void setRouteTraffic(DeployHook value);

	/**
	 * Returns the value of the '<em><b>Post Route Traffic</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Post Route Traffic</em>' containment reference.
	 * @see #setPostRouteTraffic(DeployHook)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getRollingStrategy_PostRouteTraffic()
	 * @model containment="true"
	 * @generated
	 */
	DeployHook getPostRouteTraffic();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.RollingStrategy#getPostRouteTraffic <em>Post Route Traffic</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Post Route Traffic</em>' containment reference.
	 * @see #getPostRouteTraffic()
	 * @generated
	 */
	void setPostRouteTraffic(DeployHook value);

	/**
	 * Returns the value of the '<em><b>On</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On</em>' containment reference.
	 * @see #setOn(OnSuccessOrFailureHook)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getRollingStrategy_On()
	 * @model containment="true"
	 * @generated
	 */
	OnSuccessOrFailureHook getOn();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.RollingStrategy#getOn <em>On</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On</em>' containment reference.
	 * @see #getOn()
	 * @generated
	 */
	void setOn(OnSuccessOrFailureHook value);

} // RollingStrategy
