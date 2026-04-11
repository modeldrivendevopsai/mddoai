/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Script Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ScriptStep#getScript <em>Script</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ScriptStep#getFailOnStderr <em>Fail On Stderr</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ScriptStep#getWorkingDirectory <em>Working Directory</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getScriptStep()
 * @model
 * @generated
 */
public interface ScriptStep extends Step {
	/**
	 * Returns the value of the '<em><b>Script</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Script</em>' attribute.
	 * @see #setScript(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getScriptStep_Script()
	 * @model required="true"
	 * @generated
	 */
	String getScript();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ScriptStep#getScript <em>Script</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Script</em>' attribute.
	 * @see #getScript()
	 * @generated
	 */
	void setScript(String value);

	/**
	 * Returns the value of the '<em><b>Fail On Stderr</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fail On Stderr</em>' attribute.
	 * @see #setFailOnStderr(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getScriptStep_FailOnStderr()
	 * @model
	 * @generated
	 */
	String getFailOnStderr();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ScriptStep#getFailOnStderr <em>Fail On Stderr</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fail On Stderr</em>' attribute.
	 * @see #getFailOnStderr()
	 * @generated
	 */
	void setFailOnStderr(String value);

	/**
	 * Returns the value of the '<em><b>Working Directory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Working Directory</em>' attribute.
	 * @see #setWorkingDirectory(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getScriptStep_WorkingDirectory()
	 * @model
	 * @generated
	 */
	String getWorkingDirectory();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ScriptStep#getWorkingDirectory <em>Working Directory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Working Directory</em>' attribute.
	 * @see #getWorkingDirectory()
	 * @generated
	 */
	void setWorkingDirectory(String value);

} // ScriptStep
