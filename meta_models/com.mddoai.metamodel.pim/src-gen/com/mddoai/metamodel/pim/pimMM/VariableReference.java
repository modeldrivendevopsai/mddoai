/**
 */
package com.mddoai.metamodel.pim.pimMM;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variable Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.VariableReference#getReference <em>Reference</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getVariableReference()
 * @model
 * @generated
 */
public interface VariableReference extends Value {
	/**
	 * Returns the value of the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference</em>' reference.
	 * @see #setReference(VariableDeclaration)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getVariableReference_Reference()
	 * @model required="true"
	 * @generated
	 */
	VariableDeclaration getReference();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.VariableReference#getReference <em>Reference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reference</em>' reference.
	 * @see #getReference()
	 * @generated
	 */
	void setReference(VariableDeclaration value);

} // VariableReference
