/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pr Trigger</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PrTrigger#getDisabled <em>Disabled</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PrTrigger#getAutoCancel <em>Auto Cancel</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PrTrigger#getDrafts <em>Drafts</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PrTrigger#getBranchList <em>Branch List</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PrTrigger#getBranches <em>Branches</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PrTrigger#getPaths <em>Paths</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPrTrigger()
 * @model
 * @generated
 */
public interface PrTrigger extends EObject {
	/**
	 * Returns the value of the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Disabled</em>' attribute.
	 * @see #setDisabled(Boolean)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPrTrigger_Disabled()
	 * @model
	 * @generated
	 */
	Boolean getDisabled();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PrTrigger#getDisabled <em>Disabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Disabled</em>' attribute.
	 * @see #getDisabled()
	 * @generated
	 */
	void setDisabled(Boolean value);

	/**
	 * Returns the value of the '<em><b>Auto Cancel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Auto Cancel</em>' attribute.
	 * @see #setAutoCancel(Boolean)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPrTrigger_AutoCancel()
	 * @model
	 * @generated
	 */
	Boolean getAutoCancel();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PrTrigger#getAutoCancel <em>Auto Cancel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Auto Cancel</em>' attribute.
	 * @see #getAutoCancel()
	 * @generated
	 */
	void setAutoCancel(Boolean value);

	/**
	 * Returns the value of the '<em><b>Drafts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Drafts</em>' attribute.
	 * @see #setDrafts(Boolean)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPrTrigger_Drafts()
	 * @model
	 * @generated
	 */
	Boolean getDrafts();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PrTrigger#getDrafts <em>Drafts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Drafts</em>' attribute.
	 * @see #getDrafts()
	 * @generated
	 */
	void setDrafts(Boolean value);

	/**
	 * Returns the value of the '<em><b>Branch List</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Branch List</em>' attribute list.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPrTrigger_BranchList()
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
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPrTrigger_Branches()
	 * @model containment="true"
	 * @generated
	 */
	IncludeExcludeFilters getBranches();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PrTrigger#getBranches <em>Branches</em>}' containment reference.
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
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPrTrigger_Paths()
	 * @model containment="true"
	 * @generated
	 */
	IncludeExcludeFilters getPaths();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PrTrigger#getPaths <em>Paths</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Paths</em>' containment reference.
	 * @see #getPaths()
	 * @generated
	 */
	void setPaths(IncludeExcludeFilters value);

} // PrTrigger
