/**
 */
package com.mddoai.metamodel.github.githubMM;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Concurrency Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.ConcurrencyGroup#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.ConcurrencyGroup#getCancelInProgress <em>Cancel In Progress</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getConcurrencyGroup()
 * @model
 * @generated
 */
public interface ConcurrencyGroup extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' containment reference.
	 * @see #setName(Expression)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getConcurrencyGroup_Name()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.ConcurrencyGroup#getName <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' containment reference.
	 * @see #getName()
	 * @generated
	 */
	void setName(Expression value);

	/**
	 * Returns the value of the '<em><b>Cancel In Progress</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cancel In Progress</em>' containment reference.
	 * @see #setCancelInProgress(Expression)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getConcurrencyGroup_CancelInProgress()
	 * @model containment="true"
	 * @generated
	 */
	Expression getCancelInProgress();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.ConcurrencyGroup#getCancelInProgress <em>Cancel In Progress</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cancel In Progress</em>' containment reference.
	 * @see #getCancelInProgress()
	 * @generated
	 */
	void setCancelInProgress(Expression value);

} // ConcurrencyGroup
