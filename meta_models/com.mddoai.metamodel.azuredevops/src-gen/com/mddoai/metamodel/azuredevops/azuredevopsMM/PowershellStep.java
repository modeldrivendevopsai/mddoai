/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Powershell Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PowershellStep#getPowershell <em>Powershell</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PowershellStep#getErrorActionPreference <em>Error Action Preference</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PowershellStep#getFailOnStderr <em>Fail On Stderr</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PowershellStep#getIgnoreLASTEXITCODE <em>Ignore LASTEXITCODE</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PowershellStep#getWorkingDirectory <em>Working Directory</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPowershellStep()
 * @model
 * @generated
 */
public interface PowershellStep extends Step {
	/**
	 * Returns the value of the '<em><b>Powershell</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Powershell</em>' attribute.
	 * @see #setPowershell(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPowershellStep_Powershell()
	 * @model required="true"
	 * @generated
	 */
	String getPowershell();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PowershellStep#getPowershell <em>Powershell</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Powershell</em>' attribute.
	 * @see #getPowershell()
	 * @generated
	 */
	void setPowershell(String value);

	/**
	 * Returns the value of the '<em><b>Error Action Preference</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Error Action Preference</em>' attribute.
	 * @see #setErrorActionPreference(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPowershellStep_ErrorActionPreference()
	 * @model
	 * @generated
	 */
	String getErrorActionPreference();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PowershellStep#getErrorActionPreference <em>Error Action Preference</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Error Action Preference</em>' attribute.
	 * @see #getErrorActionPreference()
	 * @generated
	 */
	void setErrorActionPreference(String value);

	/**
	 * Returns the value of the '<em><b>Fail On Stderr</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fail On Stderr</em>' attribute.
	 * @see #setFailOnStderr(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPowershellStep_FailOnStderr()
	 * @model
	 * @generated
	 */
	String getFailOnStderr();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PowershellStep#getFailOnStderr <em>Fail On Stderr</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fail On Stderr</em>' attribute.
	 * @see #getFailOnStderr()
	 * @generated
	 */
	void setFailOnStderr(String value);

	/**
	 * Returns the value of the '<em><b>Ignore LASTEXITCODE</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ignore LASTEXITCODE</em>' attribute.
	 * @see #setIgnoreLASTEXITCODE(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPowershellStep_IgnoreLASTEXITCODE()
	 * @model
	 * @generated
	 */
	String getIgnoreLASTEXITCODE();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PowershellStep#getIgnoreLASTEXITCODE <em>Ignore LASTEXITCODE</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ignore LASTEXITCODE</em>' attribute.
	 * @see #getIgnoreLASTEXITCODE()
	 * @generated
	 */
	void setIgnoreLASTEXITCODE(String value);

	/**
	 * Returns the value of the '<em><b>Working Directory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Working Directory</em>' attribute.
	 * @see #setWorkingDirectory(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPowershellStep_WorkingDirectory()
	 * @model
	 * @generated
	 */
	String getWorkingDirectory();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PowershellStep#getWorkingDirectory <em>Working Directory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Working Directory</em>' attribute.
	 * @see #getWorkingDirectory()
	 * @generated
	 */
	void setWorkingDirectory(String value);

} // PowershellStep
