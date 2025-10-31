/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Only Branches</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.OnlyBranches#getBranch <em>Branch</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getOnlyBranches()
 * @model
 * @generated
 */
public interface OnlyBranches extends EObject {
	/**
	 * Returns the value of the '<em><b>Branch</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Branch</em>' attribute.
	 * @see #setBranch(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getOnlyBranches_Branch()
	 * @model required="true"
	 * @generated
	 */
	String getBranch();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.OnlyBranches#getBranch <em>Branch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Branch</em>' attribute.
	 * @see #getBranch()
	 * @generated
	 */
	void setBranch(String value);

} // OnlyBranches
