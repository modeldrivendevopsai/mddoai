/**
 */
package com.mddoai.metamodel.bambooMM;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Branch Delete</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.BranchDelete#getAfterDeletedDays <em>After Deleted Days</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.BranchDelete#getAfterInactiveDays <em>After Inactive Days</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBranchDelete()
 * @model
 * @generated
 */
public interface BranchDelete extends EObject {
	/**
	 * Returns the value of the '<em><b>After Deleted Days</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>After Deleted Days</em>' attribute.
	 * @see #setAfterDeletedDays(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBranchDelete_AfterDeletedDays()
	 * @model
	 * @generated
	 */
	String getAfterDeletedDays();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.BranchDelete#getAfterDeletedDays <em>After Deleted Days</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>After Deleted Days</em>' attribute.
	 * @see #getAfterDeletedDays()
	 * @generated
	 */
	void setAfterDeletedDays(String value);

	/**
	 * Returns the value of the '<em><b>After Inactive Days</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>After Inactive Days</em>' attribute.
	 * @see #setAfterInactiveDays(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBranchDelete_AfterInactiveDays()
	 * @model
	 * @generated
	 */
	String getAfterInactiveDays();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.BranchDelete#getAfterInactiveDays <em>After Inactive Days</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>After Inactive Days</em>' attribute.
	 * @see #getAfterInactiveDays()
	 * @generated
	 */
	void setAfterInactiveDays(String value);

} // BranchDelete
