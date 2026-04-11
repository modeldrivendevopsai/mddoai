/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Container Image</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage#getImage <em>Image</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage#getEndpoint <em>Endpoint</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage#getOptions <em>Options</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage#getPorts <em>Ports</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage#getVolumes <em>Volumes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage#getMapDockerSocket <em>Map Docker Socket</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage#getEnv <em>Env</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage#getMountReadOnly <em>Mount Read Only</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getContainerImage()
 * @model
 * @generated
 */
public interface ContainerImage extends ContainerReference {
	/**
	 * Returns the value of the '<em><b>Image</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Image</em>' attribute.
	 * @see #setImage(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getContainerImage_Image()
	 * @model required="true"
	 * @generated
	 */
	String getImage();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage#getImage <em>Image</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Image</em>' attribute.
	 * @see #getImage()
	 * @generated
	 */
	void setImage(String value);

	/**
	 * Returns the value of the '<em><b>Endpoint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Endpoint</em>' attribute.
	 * @see #setEndpoint(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getContainerImage_Endpoint()
	 * @model
	 * @generated
	 */
	String getEndpoint();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage#getEndpoint <em>Endpoint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Endpoint</em>' attribute.
	 * @see #getEndpoint()
	 * @generated
	 */
	void setEndpoint(String value);

	/**
	 * Returns the value of the '<em><b>Options</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Options</em>' attribute.
	 * @see #setOptions(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getContainerImage_Options()
	 * @model
	 * @generated
	 */
	String getOptions();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage#getOptions <em>Options</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Options</em>' attribute.
	 * @see #getOptions()
	 * @generated
	 */
	void setOptions(String value);

	/**
	 * Returns the value of the '<em><b>Ports</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ports</em>' attribute list.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getContainerImage_Ports()
	 * @model
	 * @generated
	 */
	EList<String> getPorts();

	/**
	 * Returns the value of the '<em><b>Volumes</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Volumes</em>' attribute list.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getContainerImage_Volumes()
	 * @model
	 * @generated
	 */
	EList<String> getVolumes();

	/**
	 * Returns the value of the '<em><b>Map Docker Socket</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Map Docker Socket</em>' attribute.
	 * @see #setMapDockerSocket(Boolean)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getContainerImage_MapDockerSocket()
	 * @model
	 * @generated
	 */
	Boolean getMapDockerSocket();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage#getMapDockerSocket <em>Map Docker Socket</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Map Docker Socket</em>' attribute.
	 * @see #getMapDockerSocket()
	 * @generated
	 */
	void setMapDockerSocket(Boolean value);

	/**
	 * Returns the value of the '<em><b>Env</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Env</em>' map.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getContainerImage_Env()
	 * @model mapType="com.mddoai.metamodel.azuredevops.azuredevopsMM.EnvEntry&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;"
	 * @generated
	 */
	EMap<String, String> getEnv();

	/**
	 * Returns the value of the '<em><b>Mount Read Only</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mount Read Only</em>' containment reference.
	 * @see #setMountReadOnly(MountReadOnly)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getContainerImage_MountReadOnly()
	 * @model containment="true"
	 * @generated
	 */
	MountReadOnly getMountReadOnly();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage#getMountReadOnly <em>Mount Read Only</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mount Read Only</em>' containment reference.
	 * @see #getMountReadOnly()
	 * @generated
	 */
	void setMountReadOnly(MountReadOnly value);

} // ContainerImage
