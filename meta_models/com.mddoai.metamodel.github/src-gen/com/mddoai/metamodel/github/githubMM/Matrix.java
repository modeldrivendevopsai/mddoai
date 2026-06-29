/**
 */
package com.mddoai.metamodel.github.githubMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Matrix</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Matrix#getAxes <em>Axes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Matrix#getIncludes <em>Includes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Matrix#getExcludes <em>Excludes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Matrix#getFailFast <em>Fail Fast</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Matrix#getMaxParallel <em>Max Parallel</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getMatrix()
 * @model
 * @generated
 */
public interface Matrix extends EObject {
	/**
	 * Returns the value of the '<em><b>Axes</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.github.githubMM.MatrixAxis}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Axes</em>' containment reference list.
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getMatrix_Axes()
	 * @model containment="true"
	 * @generated
	 */
	EList<MatrixAxis> getAxes();

	/**
	 * Returns the value of the '<em><b>Includes</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.github.githubMM.MatrixCombination}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Includes</em>' containment reference list.
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getMatrix_Includes()
	 * @model containment="true"
	 * @generated
	 */
	EList<MatrixCombination> getIncludes();

	/**
	 * Returns the value of the '<em><b>Excludes</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.github.githubMM.MatrixCombination}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Excludes</em>' containment reference list.
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getMatrix_Excludes()
	 * @model containment="true"
	 * @generated
	 */
	EList<MatrixCombination> getExcludes();

	/**
	 * Returns the value of the '<em><b>Fail Fast</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fail Fast</em>' containment reference.
	 * @see #setFailFast(Expression)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getMatrix_FailFast()
	 * @model containment="true"
	 * @generated
	 */
	Expression getFailFast();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.Matrix#getFailFast <em>Fail Fast</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fail Fast</em>' containment reference.
	 * @see #getFailFast()
	 * @generated
	 */
	void setFailFast(Expression value);

	/**
	 * Returns the value of the '<em><b>Max Parallel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Parallel</em>' containment reference.
	 * @see #setMaxParallel(Expression)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getMatrix_MaxParallel()
	 * @model containment="true"
	 * @generated
	 */
	Expression getMaxParallel();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.Matrix#getMaxParallel <em>Max Parallel</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Parallel</em>' containment reference.
	 * @see #getMaxParallel()
	 * @generated
	 */
	void setMaxParallel(Expression value);

} // Matrix
