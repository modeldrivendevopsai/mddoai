/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Include Exclude Filters</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.IncludeExcludeFilters#getInclude <em>Include</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.IncludeExcludeFilters#getExclude <em>Exclude</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getIncludeExcludeFilters()
 * @model
 * @generated
 */
public interface IncludeExcludeFilters extends EObject {
	/**
	 * Returns the value of the '<em><b>Include</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Include</em>' attribute list.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getIncludeExcludeFilters_Include()
	 * @model
	 * @generated
	 */
	EList<String> getInclude();

	/**
	 * Returns the value of the '<em><b>Exclude</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exclude</em>' attribute list.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getIncludeExcludeFilters_Exclude()
	 * @model
	 * @generated
	 */
	EList<String> getExclude();

} // IncludeExcludeFilters
