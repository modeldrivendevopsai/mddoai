/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Step#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Step#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Step#getCondition <em>Condition</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Step#getContinueOnError <em>Continue On Error</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Step#getEnabled <em>Enabled</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Step#getTimeoutInMinutes <em>Timeout In Minutes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Step#getRetryCountOnTaskFailure <em>Retry Count On Task Failure</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Step#getEnv <em>Env</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Step#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getStep()
 * @model abstract="true"
 * @generated
 */
public interface Step extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getStep_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Step#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Display Name</em>' attribute.
	 * @see #setDisplayName(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getStep_DisplayName()
	 * @model
	 * @generated
	 */
	String getDisplayName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Step#getDisplayName <em>Display Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Display Name</em>' attribute.
	 * @see #getDisplayName()
	 * @generated
	 */
	void setDisplayName(String value);

	/**
	 * Returns the value of the '<em><b>Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition</em>' attribute.
	 * @see #setCondition(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getStep_Condition()
	 * @model
	 * @generated
	 */
	String getCondition();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Step#getCondition <em>Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' attribute.
	 * @see #getCondition()
	 * @generated
	 */
	void setCondition(String value);

	/**
	 * Returns the value of the '<em><b>Continue On Error</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Continue On Error</em>' attribute.
	 * @see #setContinueOnError(Boolean)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getStep_ContinueOnError()
	 * @model
	 * @generated
	 */
	Boolean getContinueOnError();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Step#getContinueOnError <em>Continue On Error</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Continue On Error</em>' attribute.
	 * @see #getContinueOnError()
	 * @generated
	 */
	void setContinueOnError(Boolean value);

	/**
	 * Returns the value of the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enabled</em>' attribute.
	 * @see #setEnabled(Boolean)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getStep_Enabled()
	 * @model
	 * @generated
	 */
	Boolean getEnabled();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Step#getEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enabled</em>' attribute.
	 * @see #getEnabled()
	 * @generated
	 */
	void setEnabled(Boolean value);

	/**
	 * Returns the value of the '<em><b>Timeout In Minutes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timeout In Minutes</em>' attribute.
	 * @see #setTimeoutInMinutes(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getStep_TimeoutInMinutes()
	 * @model
	 * @generated
	 */
	String getTimeoutInMinutes();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Step#getTimeoutInMinutes <em>Timeout In Minutes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timeout In Minutes</em>' attribute.
	 * @see #getTimeoutInMinutes()
	 * @generated
	 */
	void setTimeoutInMinutes(String value);

	/**
	 * Returns the value of the '<em><b>Retry Count On Task Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Retry Count On Task Failure</em>' attribute.
	 * @see #setRetryCountOnTaskFailure(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getStep_RetryCountOnTaskFailure()
	 * @model
	 * @generated
	 */
	String getRetryCountOnTaskFailure();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Step#getRetryCountOnTaskFailure <em>Retry Count On Task Failure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Retry Count On Task Failure</em>' attribute.
	 * @see #getRetryCountOnTaskFailure()
	 * @generated
	 */
	void setRetryCountOnTaskFailure(String value);

	/**
	 * Returns the value of the '<em><b>Env</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Env</em>' map.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getStep_Env()
	 * @model mapType="com.mddoai.metamodel.azuredevops.azuredevopsMM.EnvEntry&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;"
	 * @generated
	 */
	EMap<String, String> getEnv();

	/**
	 * Returns the value of the '<em><b>Target</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' containment reference.
	 * @see #setTarget(StepTarget)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getStep_Target()
	 * @model containment="true"
	 * @generated
	 */
	StepTarget getTarget();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Step#getTarget <em>Target</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' containment reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(StepTarget value);

} // Step
