/**
 */
package com.mddoai.metamodel.bambooMM;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Quiet Period</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.QuietPeriod#getQuietPeriodSeconds <em>Quiet Period Seconds</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.QuietPeriod#getMaxRetries <em>Max Retries</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getQuietPeriod()
 * @model
 * @generated
 */
public interface QuietPeriod extends EObject {
	/**
	 * Returns the value of the '<em><b>Quiet Period Seconds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Quiet Period Seconds</em>' attribute.
	 * @see #setQuietPeriodSeconds(int)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getQuietPeriod_QuietPeriodSeconds()
	 * @model required="true"
	 * @generated
	 */
	int getQuietPeriodSeconds();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.QuietPeriod#getQuietPeriodSeconds <em>Quiet Period Seconds</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Quiet Period Seconds</em>' attribute.
	 * @see #getQuietPeriodSeconds()
	 * @generated
	 */
	void setQuietPeriodSeconds(int value);

	/**
	 * Returns the value of the '<em><b>Max Retries</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Retries</em>' attribute.
	 * @see #setMaxRetries(Integer)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getQuietPeriod_MaxRetries()
	 * @model
	 * @generated
	 */
	Integer getMaxRetries();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.QuietPeriod#getMaxRetries <em>Max Retries</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Retries</em>' attribute.
	 * @see #getMaxRetries()
	 * @generated
	 */
	void setMaxRetries(Integer value);

} // QuietPeriod
