/**
 */
package com.mddoai.metamodel.github.githubMM;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Staging Environment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.StagingEnvironment#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.StagingEnvironment#getUrl <em>Url</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getStagingEnvironment()
 * @model
 * @generated
 */
public interface StagingEnvironment extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' containment reference.
	 * @see #setName(Expression)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getStagingEnvironment_Name()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.StagingEnvironment#getName <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' containment reference.
	 * @see #getName()
	 * @generated
	 */
	void setName(Expression value);

	/**
	 * Returns the value of the '<em><b>Url</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Url</em>' containment reference.
	 * @see #setUrl(Expression)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getStagingEnvironment_Url()
	 * @model containment="true"
	 * @generated
	 */
	Expression getUrl();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.StagingEnvironment#getUrl <em>Url</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Url</em>' containment reference.
	 * @see #getUrl()
	 * @generated
	 */
	void setUrl(Expression value);

} // StagingEnvironment
