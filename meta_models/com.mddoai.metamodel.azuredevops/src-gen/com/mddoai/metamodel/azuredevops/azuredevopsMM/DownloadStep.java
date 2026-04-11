/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Download Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadStep#getDownload <em>Download</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadStep#getArtifact <em>Artifact</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadStep#getPatterns <em>Patterns</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getDownloadStep()
 * @model
 * @generated
 */
public interface DownloadStep extends Step {
	/**
	 * Returns the value of the '<em><b>Download</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Download</em>' attribute.
	 * @see #setDownload(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getDownloadStep_Download()
	 * @model required="true"
	 * @generated
	 */
	String getDownload();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadStep#getDownload <em>Download</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Download</em>' attribute.
	 * @see #getDownload()
	 * @generated
	 */
	void setDownload(String value);

	/**
	 * Returns the value of the '<em><b>Artifact</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Artifact</em>' attribute.
	 * @see #setArtifact(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getDownloadStep_Artifact()
	 * @model
	 * @generated
	 */
	String getArtifact();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadStep#getArtifact <em>Artifact</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Artifact</em>' attribute.
	 * @see #getArtifact()
	 * @generated
	 */
	void setArtifact(String value);

	/**
	 * Returns the value of the '<em><b>Patterns</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Patterns</em>' attribute.
	 * @see #setPatterns(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getDownloadStep_Patterns()
	 * @model
	 * @generated
	 */
	String getPatterns();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadStep#getPatterns <em>Patterns</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Patterns</em>' attribute.
	 * @see #getPatterns()
	 * @generated
	 */
	void setPatterns(String value);

} // DownloadStep
