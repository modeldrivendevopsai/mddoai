/**
 */
package com.mddoai.metamodel.pim.pimMM;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Equality Op</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.EqualityOp#getOp <em>Op</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getEqualityOp()
 * @model
 * @generated
 */
public interface EqualityOp extends BinaryOp {
	/**
	 * Returns the value of the '<em><b>Op</b></em>' attribute.
	 * The literals are from the enumeration {@link com.mddoai.metamodel.pim.pimMM.EQUALITY_OPS}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Op</em>' attribute.
	 * @see com.mddoai.metamodel.pim.pimMM.EQUALITY_OPS
	 * @see #setOp(EQUALITY_OPS)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getEqualityOp_Op()
	 * @model required="true"
	 * @generated
	 */
	EQUALITY_OPS getOp();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.EqualityOp#getOp <em>Op</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Op</em>' attribute.
	 * @see com.mddoai.metamodel.pim.pimMM.EQUALITY_OPS
	 * @see #getOp()
	 * @generated
	 */
	void setOp(EQUALITY_OPS value);

} // EqualityOp
