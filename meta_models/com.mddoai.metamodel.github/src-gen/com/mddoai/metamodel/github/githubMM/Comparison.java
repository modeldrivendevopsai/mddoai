/**
 */
package com.mddoai.metamodel.github.githubMM;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Comparison</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Comparison#getOp <em>Op</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getComparison()
 * @model
 * @generated
 */
public interface Comparison extends BinaryOp {
	/**
	 * Returns the value of the '<em><b>Op</b></em>' attribute.
	 * The literals are from the enumeration {@link com.mddoai.metamodel.github.githubMM.COMPARISON_OPS}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Op</em>' attribute.
	 * @see com.mddoai.metamodel.github.githubMM.COMPARISON_OPS
	 * @see #setOp(COMPARISON_OPS)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getComparison_Op()
	 * @model required="true"
	 * @generated
	 */
	COMPARISON_OPS getOp();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.Comparison#getOp <em>Op</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Op</em>' attribute.
	 * @see com.mddoai.metamodel.github.githubMM.COMPARISON_OPS
	 * @see #getOp()
	 * @generated
	 */
	void setOp(COMPARISON_OPS value);

} // Comparison
