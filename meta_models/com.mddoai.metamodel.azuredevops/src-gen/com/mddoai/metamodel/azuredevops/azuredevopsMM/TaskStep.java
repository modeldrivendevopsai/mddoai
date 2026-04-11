/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

import org.eclipse.emf.common.util.EMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Task Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.TaskStep#getTask <em>Task</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.TaskStep#getInputs <em>Inputs</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getTaskStep()
 * @model
 * @generated
 */
public interface TaskStep extends Step {
	/**
	 * Returns the value of the '<em><b>Task</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Task</em>' attribute.
	 * @see #setTask(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getTaskStep_Task()
	 * @model required="true"
	 * @generated
	 */
	String getTask();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.TaskStep#getTask <em>Task</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Task</em>' attribute.
	 * @see #getTask()
	 * @generated
	 */
	void setTask(String value);

	/**
	 * Returns the value of the '<em><b>Inputs</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inputs</em>' map.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getTaskStep_Inputs()
	 * @model mapType="com.mddoai.metamodel.azuredevops.azuredevopsMM.InputEntry&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;"
	 * @generated
	 */
	EMap<String, String> getInputs();

} // TaskStep
