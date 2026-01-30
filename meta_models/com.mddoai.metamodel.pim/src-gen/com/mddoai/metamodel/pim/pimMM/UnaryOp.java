/**
 */
package com.mddoai.metamodel.pim.pimMM;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Unary Op</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.UnaryOp#getRhs <em>Rhs</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getUnaryOp()
 * @model
 * @generated
 */
public interface UnaryOp extends Expression {
	/**
	 * Returns the value of the '<em><b>Rhs</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rhs</em>' containment reference.
	 * @see #setRhs(Expression)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getUnaryOp_Rhs()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getRhs();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.UnaryOp#getRhs <em>Rhs</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rhs</em>' containment reference.
	 * @see #getRhs()
	 * @generated
	 */
	void setRhs(Expression value);

} // UnaryOp
