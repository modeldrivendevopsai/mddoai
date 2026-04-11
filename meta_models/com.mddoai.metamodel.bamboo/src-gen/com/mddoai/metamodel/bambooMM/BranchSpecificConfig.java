/**
 */
package com.mddoai.metamodel.bambooMM;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Branch Specific Config</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.BranchSpecificConfig#getIntegration <em>Integration</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.BranchSpecificConfig#isDisableExpiry <em>Disable Expiry</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBranchSpecificConfig()
 * @model
 * @generated
 */
public interface BranchSpecificConfig extends EObject {
	/**
	 * Returns the value of the '<em><b>Integration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Integration</em>' containment reference.
	 * @see #setIntegration(BranchIntegration)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBranchSpecificConfig_Integration()
	 * @model containment="true"
	 * @generated
	 */
	BranchIntegration getIntegration();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.BranchSpecificConfig#getIntegration <em>Integration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Integration</em>' containment reference.
	 * @see #getIntegration()
	 * @generated
	 */
	void setIntegration(BranchIntegration value);

	/**
	 * Returns the value of the '<em><b>Disable Expiry</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Disable Expiry</em>' attribute.
	 * @see #setDisableExpiry(boolean)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBranchSpecificConfig_DisableExpiry()
	 * @model default="false"
	 * @generated
	 */
	boolean isDisableExpiry();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.BranchSpecificConfig#isDisableExpiry <em>Disable Expiry</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Disable Expiry</em>' attribute.
	 * @see #isDisableExpiry()
	 * @generated
	 */
	void setDisableExpiry(boolean value);

} // BranchSpecificConfig
