/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Download Build Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadBuildStep#getDownloadBuild <em>Download Build</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadBuildStep#getArtifact <em>Artifact</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadBuildStep#getPath <em>Path</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadBuildStep#getPatterns <em>Patterns</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getDownloadBuildStep()
 * @model
 * @generated
 */
public interface DownloadBuildStep extends Step {
	/**
	 * Returns the value of the '<em><b>Download Build</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Download Build</em>' attribute.
	 * @see #setDownloadBuild(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getDownloadBuildStep_DownloadBuild()
	 * @model required="true"
	 * @generated
	 */
	String getDownloadBuild();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadBuildStep#getDownloadBuild <em>Download Build</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Download Build</em>' attribute.
	 * @see #getDownloadBuild()
	 * @generated
	 */
	void setDownloadBuild(String value);

	/**
	 * Returns the value of the '<em><b>Artifact</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Artifact</em>' attribute.
	 * @see #setArtifact(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getDownloadBuildStep_Artifact()
	 * @model
	 * @generated
	 */
	String getArtifact();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadBuildStep#getArtifact <em>Artifact</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Artifact</em>' attribute.
	 * @see #getArtifact()
	 * @generated
	 */
	void setArtifact(String value);

	/**
	 * Returns the value of the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Path</em>' attribute.
	 * @see #setPath(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getDownloadBuildStep_Path()
	 * @model
	 * @generated
	 */
	String getPath();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadBuildStep#getPath <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path</em>' attribute.
	 * @see #getPath()
	 * @generated
	 */
	void setPath(String value);

	/**
	 * Returns the value of the '<em><b>Patterns</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Patterns</em>' attribute.
	 * @see #setPatterns(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getDownloadBuildStep_Patterns()
	 * @model
	 * @generated
	 */
	String getPatterns();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadBuildStep#getPatterns <em>Patterns</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Patterns</em>' attribute.
	 * @see #getPatterns()
	 * @generated
	 */
	void setPatterns(String value);

} // DownloadBuildStep
