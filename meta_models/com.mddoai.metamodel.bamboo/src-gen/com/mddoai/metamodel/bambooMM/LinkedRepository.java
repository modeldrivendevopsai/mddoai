/**
 */
package com.mddoai.metamodel.bambooMM;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Linked Repository</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.LinkedRepository#getScope <em>Scope</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getLinkedRepository()
 * @model
 * @generated
 */
public interface LinkedRepository extends Repository {
	/**
	 * Returns the value of the '<em><b>Scope</b></em>' attribute.
	 * The literals are from the enumeration {@link com.mddoai.metamodel.bambooMM.REPOSITORY_SCOPE}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scope</em>' attribute.
	 * @see com.mddoai.metamodel.bambooMM.REPOSITORY_SCOPE
	 * @see #setScope(REPOSITORY_SCOPE)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getLinkedRepository_Scope()
	 * @model
	 * @generated
	 */
	REPOSITORY_SCOPE getScope();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.LinkedRepository#getScope <em>Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scope</em>' attribute.
	 * @see com.mddoai.metamodel.bambooMM.REPOSITORY_SCOPE
	 * @see #getScope()
	 * @generated
	 */
	void setScope(REPOSITORY_SCOPE value);

} // LinkedRepository
