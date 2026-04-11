/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Container Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getContainer <em>Container</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getImage <em>Image</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getType <em>Type</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getAzureSubscription <em>Azure Subscription</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getResourceGroup <em>Resource Group</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getRegistry <em>Registry</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getRepository <em>Repository</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getEndpoint <em>Endpoint</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getLocalImage <em>Local Image</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getOptions <em>Options</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getPorts <em>Ports</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getVolumes <em>Volumes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getMapDockerSocket <em>Map Docker Socket</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getEnv <em>Env</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getMountReadOnly <em>Mount Read Only</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getTrigger <em>Trigger</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getContainerResource()
 * @model
 * @generated
 */
public interface ContainerResource extends EObject {
	/**
	 * Returns the value of the '<em><b>Container</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Container</em>' attribute.
	 * @see #setContainer(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getContainerResource_Container()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getContainer();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getContainer <em>Container</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Container</em>' attribute.
	 * @see #getContainer()
	 * @generated
	 */
	void setContainer(String value);

	/**
	 * Returns the value of the '<em><b>Image</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Image</em>' attribute.
	 * @see #setImage(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getContainerResource_Image()
	 * @model required="true"
	 * @generated
	 */
	String getImage();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getImage <em>Image</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Image</em>' attribute.
	 * @see #getImage()
	 * @generated
	 */
	void setImage(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getContainerResource_Type()
	 * @model
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Azure Subscription</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Azure Subscription</em>' attribute.
	 * @see #setAzureSubscription(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getContainerResource_AzureSubscription()
	 * @model
	 * @generated
	 */
	String getAzureSubscription();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getAzureSubscription <em>Azure Subscription</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Azure Subscription</em>' attribute.
	 * @see #getAzureSubscription()
	 * @generated
	 */
	void setAzureSubscription(String value);

	/**
	 * Returns the value of the '<em><b>Resource Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Group</em>' attribute.
	 * @see #setResourceGroup(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getContainerResource_ResourceGroup()
	 * @model
	 * @generated
	 */
	String getResourceGroup();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getResourceGroup <em>Resource Group</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource Group</em>' attribute.
	 * @see #getResourceGroup()
	 * @generated
	 */
	void setResourceGroup(String value);

	/**
	 * Returns the value of the '<em><b>Registry</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Registry</em>' attribute.
	 * @see #setRegistry(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getContainerResource_Registry()
	 * @model
	 * @generated
	 */
	String getRegistry();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getRegistry <em>Registry</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Registry</em>' attribute.
	 * @see #getRegistry()
	 * @generated
	 */
	void setRegistry(String value);

	/**
	 * Returns the value of the '<em><b>Repository</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Repository</em>' attribute.
	 * @see #setRepository(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getContainerResource_Repository()
	 * @model
	 * @generated
	 */
	String getRepository();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getRepository <em>Repository</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Repository</em>' attribute.
	 * @see #getRepository()
	 * @generated
	 */
	void setRepository(String value);

	/**
	 * Returns the value of the '<em><b>Endpoint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Endpoint</em>' attribute.
	 * @see #setEndpoint(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getContainerResource_Endpoint()
	 * @model
	 * @generated
	 */
	String getEndpoint();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getEndpoint <em>Endpoint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Endpoint</em>' attribute.
	 * @see #getEndpoint()
	 * @generated
	 */
	void setEndpoint(String value);

	/**
	 * Returns the value of the '<em><b>Local Image</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Local Image</em>' attribute.
	 * @see #setLocalImage(Boolean)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getContainerResource_LocalImage()
	 * @model
	 * @generated
	 */
	Boolean getLocalImage();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getLocalImage <em>Local Image</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Local Image</em>' attribute.
	 * @see #getLocalImage()
	 * @generated
	 */
	void setLocalImage(Boolean value);

	/**
	 * Returns the value of the '<em><b>Options</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Options</em>' attribute.
	 * @see #setOptions(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getContainerResource_Options()
	 * @model
	 * @generated
	 */
	String getOptions();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getOptions <em>Options</em>}' attribute.
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
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getContainerResource_Ports()
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
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getContainerResource_Volumes()
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
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getContainerResource_MapDockerSocket()
	 * @model
	 * @generated
	 */
	Boolean getMapDockerSocket();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getMapDockerSocket <em>Map Docker Socket</em>}' attribute.
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
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getContainerResource_Env()
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
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getContainerResource_MountReadOnly()
	 * @model containment="true"
	 * @generated
	 */
	MountReadOnly getMountReadOnly();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getMountReadOnly <em>Mount Read Only</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mount Read Only</em>' containment reference.
	 * @see #getMountReadOnly()
	 * @generated
	 */
	void setMountReadOnly(MountReadOnly value);

	/**
	 * Returns the value of the '<em><b>Trigger</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trigger</em>' containment reference.
	 * @see #setTrigger(ContainerResourceTrigger)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getContainerResource_Trigger()
	 * @model containment="true"
	 * @generated
	 */
	ContainerResourceTrigger getTrigger();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource#getTrigger <em>Trigger</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trigger</em>' containment reference.
	 * @see #getTrigger()
	 * @generated
	 */
	void setTrigger(ContainerResourceTrigger value);

} // ContainerResource
