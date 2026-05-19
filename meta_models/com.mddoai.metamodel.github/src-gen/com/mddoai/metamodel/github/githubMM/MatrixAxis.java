/**
 */
package com.mddoai.metamodel.github.githubMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Matrix Axis</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.MatrixAxis#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.MatrixAxis#getCells <em>Cells</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getMatrixAxis()
 * @model
 * @generated
 */
public interface MatrixAxis extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' containment reference.
	 * @see #setName(VariableDeclaration)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getMatrixAxis_Name()
	 * @model containment="true" required="true"
	 * @generated
	 */
	VariableDeclaration getName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.MatrixAxis#getName <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' containment reference.
	 * @see #getName()
	 * @generated
	 */
	void setName(VariableDeclaration value);

	/**
	 * Returns the value of the '<em><b>Cells</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.github.githubMM.Expression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cells</em>' containment reference list.
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getMatrixAxis_Cells()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Expression> getCells();

} // MatrixAxis
