/**
 */
package com.mddoai.metamodel.pim.pimMM;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Checkout</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Checkout#getPath <em>Path</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getCheckout()
 * @model
 * @generated
 */
public interface Checkout extends NonConditionalStep {
	/**
	 * Returns the value of the '<em><b>Path</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Path</em>' containment reference.
	 * @see #setPath(Expression)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getCheckout_Path()
	 * @model containment="true"
	 * @generated
	 */
	Expression getPath();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.Checkout#getPath <em>Path</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path</em>' containment reference.
	 * @see #getPath()
	 * @generated
	 */
	void setPath(Expression value);

} // Checkout
