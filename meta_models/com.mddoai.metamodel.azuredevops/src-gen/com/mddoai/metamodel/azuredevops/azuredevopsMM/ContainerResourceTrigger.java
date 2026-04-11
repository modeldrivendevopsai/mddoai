/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Container Resource Trigger</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResourceTrigger#getEnabled <em>Enabled</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResourceTrigger#getDisabled <em>Disabled</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResourceTrigger#getTags <em>Tags</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getContainerResourceTrigger()
 * @model
 * @generated
 */
public interface ContainerResourceTrigger extends EObject {
	/**
	 * Returns the value of the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enabled</em>' attribute.
	 * @see #setEnabled(Boolean)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getContainerResourceTrigger_Enabled()
	 * @model
	 * @generated
	 */
	Boolean getEnabled();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResourceTrigger#getEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enabled</em>' attribute.
	 * @see #getEnabled()
	 * @generated
	 */
	void setEnabled(Boolean value);

	/**
	 * Returns the value of the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Disabled</em>' attribute.
	 * @see #setDisabled(Boolean)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getContainerResourceTrigger_Disabled()
	 * @model
	 * @generated
	 */
	Boolean getDisabled();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResourceTrigger#getDisabled <em>Disabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Disabled</em>' attribute.
	 * @see #getDisabled()
	 * @generated
	 */
	void setDisabled(Boolean value);

	/**
	 * Returns the value of the '<em><b>Tags</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tags</em>' containment reference.
	 * @see #setTags(IncludeExcludeFilters)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getContainerResourceTrigger_Tags()
	 * @model containment="true"
	 * @generated
	 */
	IncludeExcludeFilters getTags();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResourceTrigger#getTags <em>Tags</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tags</em>' containment reference.
	 * @see #getTags()
	 * @generated
	 */
	void setTags(IncludeExcludeFilters value);

} // ContainerResourceTrigger
