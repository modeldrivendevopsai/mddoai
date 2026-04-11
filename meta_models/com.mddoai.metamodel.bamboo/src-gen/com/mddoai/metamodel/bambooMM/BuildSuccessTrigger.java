/**
 */
package com.mddoai.metamodel.bambooMM;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Build Success Trigger</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.BuildSuccessTrigger#getBranch <em>Branch</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBuildSuccessTrigger()
 * @model
 * @generated
 */
public interface BuildSuccessTrigger extends Trigger {
	/**
	 * Returns the value of the '<em><b>Branch</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Branch</em>' attribute.
	 * @see #setBranch(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBuildSuccessTrigger_Branch()
	 * @model
	 * @generated
	 */
	String getBranch();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.BuildSuccessTrigger#getBranch <em>Branch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Branch</em>' attribute.
	 * @see #getBranch()
	 * @generated
	 */
	void setBranch(String value);

} // BuildSuccessTrigger
