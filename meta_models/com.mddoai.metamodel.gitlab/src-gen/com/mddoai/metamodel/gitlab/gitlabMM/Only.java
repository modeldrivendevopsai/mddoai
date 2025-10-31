/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Only</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Only#getBranches <em>Branches</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getOnly()
 * @model
 * @generated
 */
public interface Only extends EObject {
	/**
	 * Returns the value of the '<em><b>Branches</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.gitlab.gitlabMM.OnlyBranches}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Branches</em>' containment reference list.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getOnly_Branches()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<OnlyBranches> getBranches();

} // Only
