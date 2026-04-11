/**
 */
package com.mddoai.metamodel.bambooMM;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>After Deployment Trigger</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.AfterDeploymentTrigger#getDeploymentProject <em>Deployment Project</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.AfterDeploymentTrigger#getEnvironment <em>Environment</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getAfterDeploymentTrigger()
 * @model
 * @generated
 */
public interface AfterDeploymentTrigger extends Trigger {
	/**
	 * Returns the value of the '<em><b>Deployment Project</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Deployment Project</em>' attribute.
	 * @see #setDeploymentProject(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getAfterDeploymentTrigger_DeploymentProject()
	 * @model required="true"
	 * @generated
	 */
	String getDeploymentProject();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.AfterDeploymentTrigger#getDeploymentProject <em>Deployment Project</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Deployment Project</em>' attribute.
	 * @see #getDeploymentProject()
	 * @generated
	 */
	void setDeploymentProject(String value);

	/**
	 * Returns the value of the '<em><b>Environment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Environment</em>' attribute.
	 * @see #setEnvironment(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getAfterDeploymentTrigger_Environment()
	 * @model
	 * @generated
	 */
	String getEnvironment();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.AfterDeploymentTrigger#getEnvironment <em>Environment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Environment</em>' attribute.
	 * @see #getEnvironment()
	 * @generated
	 */
	void setEnvironment(String value);

} // AfterDeploymentTrigger
