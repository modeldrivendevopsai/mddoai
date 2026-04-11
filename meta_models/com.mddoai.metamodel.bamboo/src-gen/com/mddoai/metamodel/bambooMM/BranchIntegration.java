/**
 */
package com.mddoai.metamodel.bambooMM;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Branch Integration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.BranchIntegration#getMergeFrom <em>Merge From</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.BranchIntegration#getMergeTo <em>Merge To</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.BranchIntegration#isPushOnSuccess <em>Push On Success</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBranchIntegration()
 * @model
 * @generated
 */
public interface BranchIntegration extends EObject {
	/**
	 * Returns the value of the '<em><b>Merge From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Merge From</em>' attribute.
	 * @see #setMergeFrom(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBranchIntegration_MergeFrom()
	 * @model
	 * @generated
	 */
	String getMergeFrom();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.BranchIntegration#getMergeFrom <em>Merge From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Merge From</em>' attribute.
	 * @see #getMergeFrom()
	 * @generated
	 */
	void setMergeFrom(String value);

	/**
	 * Returns the value of the '<em><b>Merge To</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Merge To</em>' attribute.
	 * @see #setMergeTo(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBranchIntegration_MergeTo()
	 * @model
	 * @generated
	 */
	String getMergeTo();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.BranchIntegration#getMergeTo <em>Merge To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Merge To</em>' attribute.
	 * @see #getMergeTo()
	 * @generated
	 */
	void setMergeTo(String value);

	/**
	 * Returns the value of the '<em><b>Push On Success</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Push On Success</em>' attribute.
	 * @see #setPushOnSuccess(boolean)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBranchIntegration_PushOnSuccess()
	 * @model default="false"
	 * @generated
	 */
	boolean isPushOnSuccess();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.BranchIntegration#isPushOnSuccess <em>Push On Success</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Push On Success</em>' attribute.
	 * @see #isPushOnSuccess()
	 * @generated
	 */
	void setPushOnSuccess(boolean value);

} // BranchIntegration
