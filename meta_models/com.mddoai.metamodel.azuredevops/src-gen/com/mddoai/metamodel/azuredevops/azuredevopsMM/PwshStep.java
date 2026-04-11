/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pwsh Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PwshStep#getPwsh <em>Pwsh</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PwshStep#getErrorActionPreference <em>Error Action Preference</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PwshStep#getFailOnStderr <em>Fail On Stderr</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PwshStep#getIgnoreLASTEXITCODE <em>Ignore LASTEXITCODE</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PwshStep#getWorkingDirectory <em>Working Directory</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPwshStep()
 * @model
 * @generated
 */
public interface PwshStep extends Step {
	/**
	 * Returns the value of the '<em><b>Pwsh</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pwsh</em>' attribute.
	 * @see #setPwsh(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPwshStep_Pwsh()
	 * @model required="true"
	 * @generated
	 */
	String getPwsh();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PwshStep#getPwsh <em>Pwsh</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pwsh</em>' attribute.
	 * @see #getPwsh()
	 * @generated
	 */
	void setPwsh(String value);

	/**
	 * Returns the value of the '<em><b>Error Action Preference</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Error Action Preference</em>' attribute.
	 * @see #setErrorActionPreference(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPwshStep_ErrorActionPreference()
	 * @model
	 * @generated
	 */
	String getErrorActionPreference();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PwshStep#getErrorActionPreference <em>Error Action Preference</em>}' attribute.
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
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPwshStep_FailOnStderr()
	 * @model
	 * @generated
	 */
	String getFailOnStderr();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PwshStep#getFailOnStderr <em>Fail On Stderr</em>}' attribute.
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
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPwshStep_IgnoreLASTEXITCODE()
	 * @model
	 * @generated
	 */
	String getIgnoreLASTEXITCODE();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PwshStep#getIgnoreLASTEXITCODE <em>Ignore LASTEXITCODE</em>}' attribute.
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
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPwshStep_WorkingDirectory()
	 * @model
	 * @generated
	 */
	String getWorkingDirectory();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PwshStep#getWorkingDirectory <em>Working Directory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Working Directory</em>' attribute.
	 * @see #getWorkingDirectory()
	 * @generated
	 */
	void setWorkingDirectory(String value);

} // PwshStep
