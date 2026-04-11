/**
 */
package com.mddoai.metamodel.bambooMM;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Environment Success Trigger</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.EnvironmentSuccessTrigger#getEnvironment <em>Environment</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getEnvironmentSuccessTrigger()
 * @model
 * @generated
 */
public interface EnvironmentSuccessTrigger extends Trigger {
	/**
	 * Returns the value of the '<em><b>Environment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Environment</em>' attribute.
	 * @see #setEnvironment(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getEnvironmentSuccessTrigger_Environment()
	 * @model required="true"
	 * @generated
	 */
	String getEnvironment();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.EnvironmentSuccessTrigger#getEnvironment <em>Environment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Environment</em>' attribute.
	 * @see #getEnvironment()
	 * @generated
	 */
	void setEnvironment(String value);

} // EnvironmentSuccessTrigger
