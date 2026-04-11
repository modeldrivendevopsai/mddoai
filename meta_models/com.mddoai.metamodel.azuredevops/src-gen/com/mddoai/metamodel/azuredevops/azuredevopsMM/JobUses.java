/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Job Uses</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.JobUses#getRepositories <em>Repositories</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.JobUses#getPools <em>Pools</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getJobUses()
 * @model
 * @generated
 */
public interface JobUses extends EObject {
	/**
	 * Returns the value of the '<em><b>Repositories</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Repositories</em>' attribute list.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getJobUses_Repositories()
	 * @model
	 * @generated
	 */
	EList<String> getRepositories();

	/**
	 * Returns the value of the '<em><b>Pools</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pools</em>' attribute list.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getJobUses_Pools()
	 * @model
	 * @generated
	 */
	EList<String> getPools();

} // JobUses
