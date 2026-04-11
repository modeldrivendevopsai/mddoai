/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Trigger</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Trigger#getDisabled <em>Disabled</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Trigger#getBatch <em>Batch</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Trigger#getBranchList <em>Branch List</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Trigger#getBranches <em>Branches</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Trigger#getPaths <em>Paths</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Trigger#getTags <em>Tags</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getTrigger()
 * @model
 * @generated
 */
public interface Trigger extends EObject {
	/**
	 * Returns the value of the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Disabled</em>' attribute.
	 * @see #setDisabled(Boolean)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getTrigger_Disabled()
	 * @model
	 * @generated
	 */
	Boolean getDisabled();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Trigger#getDisabled <em>Disabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Disabled</em>' attribute.
	 * @see #getDisabled()
	 * @generated
	 */
	void setDisabled(Boolean value);

	/**
	 * Returns the value of the '<em><b>Batch</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Batch</em>' attribute.
	 * @see #setBatch(Boolean)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getTrigger_Batch()
	 * @model
	 * @generated
	 */
	Boolean getBatch();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Trigger#getBatch <em>Batch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Batch</em>' attribute.
	 * @see #getBatch()
	 * @generated
	 */
	void setBatch(Boolean value);

	/**
	 * Returns the value of the '<em><b>Branch List</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Branch List</em>' attribute list.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getTrigger_BranchList()
	 * @model
	 * @generated
	 */
	EList<String> getBranchList();

	/**
	 * Returns the value of the '<em><b>Branches</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Branches</em>' containment reference.
	 * @see #setBranches(IncludeExcludeFilters)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getTrigger_Branches()
	 * @model containment="true"
	 * @generated
	 */
	IncludeExcludeFilters getBranches();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Trigger#getBranches <em>Branches</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Branches</em>' containment reference.
	 * @see #getBranches()
	 * @generated
	 */
	void setBranches(IncludeExcludeFilters value);

	/**
	 * Returns the value of the '<em><b>Paths</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Paths</em>' containment reference.
	 * @see #setPaths(IncludeExcludeFilters)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getTrigger_Paths()
	 * @model containment="true"
	 * @generated
	 */
	IncludeExcludeFilters getPaths();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Trigger#getPaths <em>Paths</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Paths</em>' containment reference.
	 * @see #getPaths()
	 * @generated
	 */
	void setPaths(IncludeExcludeFilters value);

	/**
	 * Returns the value of the '<em><b>Tags</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tags</em>' containment reference.
	 * @see #setTags(IncludeExcludeFilters)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getTrigger_Tags()
	 * @model containment="true"
	 * @generated
	 */
	IncludeExcludeFilters getTags();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Trigger#getTags <em>Tags</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tags</em>' containment reference.
	 * @see #getTags()
	 * @generated
	 */
	void setTags(IncludeExcludeFilters value);

} // Trigger
