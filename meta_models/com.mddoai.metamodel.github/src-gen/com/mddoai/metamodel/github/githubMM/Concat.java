/**
 */
package com.mddoai.metamodel.github.githubMM;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Concat</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Concat#getExpressions <em>Expressions</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getConcat()
 * @model
 * @generated
 */
public interface Concat extends Expression {
	/**
	 * Returns the value of the '<em><b>Expressions</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.github.githubMM.Expression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expressions</em>' containment reference list.
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getConcat_Expressions()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Expression> getExpressions();

} // Concat
