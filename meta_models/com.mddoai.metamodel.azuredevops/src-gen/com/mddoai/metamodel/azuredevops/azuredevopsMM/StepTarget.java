/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Step Target</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.StepTarget#getContainer <em>Container</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.StepTarget#getCommands <em>Commands</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.StepTarget#getSettableVariables <em>Settable Variables</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getStepTarget()
 * @model
 * @generated
 */
public interface StepTarget extends EObject {
	/**
	 * Returns the value of the '<em><b>Container</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Container</em>' attribute.
	 * @see #setContainer(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getStepTarget_Container()
	 * @model
	 * @generated
	 */
	String getContainer();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.StepTarget#getContainer <em>Container</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Container</em>' attribute.
	 * @see #getContainer()
	 * @generated
	 */
	void setContainer(String value);

	/**
	 * Returns the value of the '<em><b>Commands</b></em>' attribute.
	 * The literals are from the enumeration {@link com.mddoai.metamodel.azuredevops.azuredevopsMM.TARGET_COMMANDS}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Commands</em>' attribute.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.TARGET_COMMANDS
	 * @see #setCommands(TARGET_COMMANDS)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getStepTarget_Commands()
	 * @model
	 * @generated
	 */
	TARGET_COMMANDS getCommands();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.StepTarget#getCommands <em>Commands</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Commands</em>' attribute.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.TARGET_COMMANDS
	 * @see #getCommands()
	 * @generated
	 */
	void setCommands(TARGET_COMMANDS value);

	/**
	 * Returns the value of the '<em><b>Settable Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Settable Variables</em>' containment reference.
	 * @see #setSettableVariables(SettableVariables)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getStepTarget_SettableVariables()
	 * @model containment="true"
	 * @generated
	 */
	SettableVariables getSettableVariables();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.StepTarget#getSettableVariables <em>Settable Variables</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Settable Variables</em>' containment reference.
	 * @see #getSettableVariables()
	 * @generated
	 */
	void setSettableVariables(SettableVariables value);

} // StepTarget
