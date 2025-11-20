/**
 */
package com.mddoai.metamodel.pim.pimMM;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Comparison Op</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.ComparisonOp#getOp <em>Op</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getComparisonOp()
 * @model
 * @generated
 */
public interface ComparisonOp extends BinaryOp {
	/**
	 * Returns the value of the '<em><b>Op</b></em>' attribute.
	 * The literals are from the enumeration {@link com.mddoai.metamodel.pim.pimMM.COMPARISON_OPS}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Op</em>' attribute.
	 * @see com.mddoai.metamodel.pim.pimMM.COMPARISON_OPS
	 * @see #setOp(COMPARISON_OPS)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getComparisonOp_Op()
	 * @model required="true"
	 * @generated
	 */
	COMPARISON_OPS getOp();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.ComparisonOp#getOp <em>Op</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Op</em>' attribute.
	 * @see com.mddoai.metamodel.pim.pimMM.COMPARISON_OPS
	 * @see #getOp()
	 * @generated
	 */
	void setOp(COMPARISON_OPS value);

} // ComparisonOp
