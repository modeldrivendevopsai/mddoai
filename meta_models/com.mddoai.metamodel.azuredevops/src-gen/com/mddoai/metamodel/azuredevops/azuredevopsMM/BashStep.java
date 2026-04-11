/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Bash Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.BashStep#getBash <em>Bash</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.BashStep#getFailOnStderr <em>Fail On Stderr</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.BashStep#getWorkingDirectory <em>Working Directory</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getBashStep()
 * @model
 * @generated
 */
public interface BashStep extends Step {
	/**
	 * Returns the value of the '<em><b>Bash</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bash</em>' attribute.
	 * @see #setBash(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getBashStep_Bash()
	 * @model required="true"
	 * @generated
	 */
	String getBash();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.BashStep#getBash <em>Bash</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bash</em>' attribute.
	 * @see #getBash()
	 * @generated
	 */
	void setBash(String value);

	/**
	 * Returns the value of the '<em><b>Fail On Stderr</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fail On Stderr</em>' attribute.
	 * @see #setFailOnStderr(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getBashStep_FailOnStderr()
	 * @model
	 * @generated
	 */
	String getFailOnStderr();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.BashStep#getFailOnStderr <em>Fail On Stderr</em>}' attribute.
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
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getBashStep_WorkingDirectory()
	 * @model
	 * @generated
	 */
	String getWorkingDirectory();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.BashStep#getWorkingDirectory <em>Working Directory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Working Directory</em>' attribute.
	 * @see #getWorkingDirectory()
	 * @generated
	 */
	void setWorkingDirectory(String value);

} // BashStep
