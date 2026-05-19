/**
 */
package com.mddoai.metamodel.github.githubMM;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Parameter#getId <em>Id</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Parameter#getDescription <em>Description</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getParameter()
 * @model abstract="true"
 * @generated
 */
public interface Parameter extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' containment reference.
	 * @see #setId(VariableDeclaration)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getParameter_Id()
	 * @model containment="true" required="true"
	 * @generated
	 */
	VariableDeclaration getId();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.Parameter#getId <em>Id</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' containment reference.
	 * @see #getId()
	 * @generated
	 */
	void setId(VariableDeclaration value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' containment reference.
	 * @see #setDescription(Expression)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getParameter_Description()
	 * @model containment="true"
	 * @generated
	 */
	Expression getDescription();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.Parameter#getDescription <em>Description</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' containment reference.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(Expression value);

} // Parameter
