/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pipeline Resource Trigger</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResourceTrigger#getEnabled <em>Enabled</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResourceTrigger#getDisabled <em>Disabled</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResourceTrigger#getStages <em>Stages</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResourceTrigger#getTags <em>Tags</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResourceTrigger#getBranches <em>Branches</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPipelineResourceTrigger()
 * @model
 * @generated
 */
public interface PipelineResourceTrigger extends EObject {
	/**
	 * Returns the value of the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enabled</em>' attribute.
	 * @see #setEnabled(Boolean)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPipelineResourceTrigger_Enabled()
	 * @model
	 * @generated
	 */
	Boolean getEnabled();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResourceTrigger#getEnabled <em>Enabled</em>}' attribute.
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
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPipelineResourceTrigger_Disabled()
	 * @model
	 * @generated
	 */
	Boolean getDisabled();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResourceTrigger#getDisabled <em>Disabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Disabled</em>' attribute.
	 * @see #getDisabled()
	 * @generated
	 */
	void setDisabled(Boolean value);

	/**
	 * Returns the value of the '<em><b>Stages</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stages</em>' attribute list.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPipelineResourceTrigger_Stages()
	 * @model
	 * @generated
	 */
	EList<String> getStages();

	/**
	 * Returns the value of the '<em><b>Tags</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tags</em>' attribute list.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPipelineResourceTrigger_Tags()
	 * @model
	 * @generated
	 */
	EList<String> getTags();

	/**
	 * Returns the value of the '<em><b>Branches</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Branches</em>' containment reference.
	 * @see #setBranches(IncludeExcludeFilters)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPipelineResourceTrigger_Branches()
	 * @model containment="true"
	 * @generated
	 */
	IncludeExcludeFilters getBranches();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResourceTrigger#getBranches <em>Branches</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Branches</em>' containment reference.
	 * @see #getBranches()
	 * @generated
	 */
	void setBranches(IncludeExcludeFilters value);

} // PipelineResourceTrigger
