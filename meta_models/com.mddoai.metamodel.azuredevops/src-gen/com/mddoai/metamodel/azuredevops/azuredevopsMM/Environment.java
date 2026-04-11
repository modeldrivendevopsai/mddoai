/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Environment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Environment#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Environment#getResourceName <em>Resource Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Environment#getResourceId <em>Resource Id</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Environment#getResourceType <em>Resource Type</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Environment#getTags <em>Tags</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getEnvironment()
 * @model
 * @generated
 */
public interface Environment extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getEnvironment_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Environment#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Resource Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Name</em>' attribute.
	 * @see #setResourceName(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getEnvironment_ResourceName()
	 * @model
	 * @generated
	 */
	String getResourceName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Environment#getResourceName <em>Resource Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource Name</em>' attribute.
	 * @see #getResourceName()
	 * @generated
	 */
	void setResourceName(String value);

	/**
	 * Returns the value of the '<em><b>Resource Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Id</em>' attribute.
	 * @see #setResourceId(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getEnvironment_ResourceId()
	 * @model
	 * @generated
	 */
	String getResourceId();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Environment#getResourceId <em>Resource Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource Id</em>' attribute.
	 * @see #getResourceId()
	 * @generated
	 */
	void setResourceId(String value);

	/**
	 * Returns the value of the '<em><b>Resource Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Type</em>' attribute.
	 * @see #setResourceType(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getEnvironment_ResourceType()
	 * @model
	 * @generated
	 */
	String getResourceType();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Environment#getResourceType <em>Resource Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource Type</em>' attribute.
	 * @see #getResourceType()
	 * @generated
	 */
	void setResourceType(String value);

	/**
	 * Returns the value of the '<em><b>Tags</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tags</em>' attribute.
	 * @see #setTags(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getEnvironment_Tags()
	 * @model
	 * @generated
	 */
	String getTags();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Environment#getTags <em>Tags</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tags</em>' attribute.
	 * @see #getTags()
	 * @generated
	 */
	void setTags(String value);

} // Environment
