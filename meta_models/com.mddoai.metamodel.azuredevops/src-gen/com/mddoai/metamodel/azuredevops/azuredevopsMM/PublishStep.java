/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Publish Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PublishStep#getPublish <em>Publish</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PublishStep#getArtifact <em>Artifact</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPublishStep()
 * @model
 * @generated
 */
public interface PublishStep extends Step {
	/**
	 * Returns the value of the '<em><b>Publish</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Publish</em>' attribute.
	 * @see #setPublish(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPublishStep_Publish()
	 * @model required="true"
	 * @generated
	 */
	String getPublish();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PublishStep#getPublish <em>Publish</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Publish</em>' attribute.
	 * @see #getPublish()
	 * @generated
	 */
	void setPublish(String value);

	/**
	 * Returns the value of the '<em><b>Artifact</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Artifact</em>' attribute.
	 * @see #setArtifact(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPublishStep_Artifact()
	 * @model
	 * @generated
	 */
	String getArtifact();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PublishStep#getArtifact <em>Artifact</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Artifact</em>' attribute.
	 * @see #getArtifact()
	 * @generated
	 */
	void setArtifact(String value);

} // PublishStep
