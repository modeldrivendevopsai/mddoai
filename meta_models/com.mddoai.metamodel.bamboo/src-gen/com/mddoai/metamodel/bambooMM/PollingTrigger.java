/**
 */
package com.mddoai.metamodel.bambooMM;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Polling Trigger</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.PollingTrigger#getPeriod <em>Period</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.PollingTrigger#getCronExpression <em>Cron Expression</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.PollingTrigger#getRepositories <em>Repositories</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.PollingTrigger#getConditions <em>Conditions</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getPollingTrigger()
 * @model
 * @generated
 */
public interface PollingTrigger extends Trigger {
	/**
	 * Returns the value of the '<em><b>Period</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Period</em>' attribute.
	 * @see #setPeriod(Integer)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getPollingTrigger_Period()
	 * @model
	 * @generated
	 */
	Integer getPeriod();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.PollingTrigger#getPeriod <em>Period</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Period</em>' attribute.
	 * @see #getPeriod()
	 * @generated
	 */
	void setPeriod(Integer value);

	/**
	 * Returns the value of the '<em><b>Cron Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cron Expression</em>' attribute.
	 * @see #setCronExpression(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getPollingTrigger_CronExpression()
	 * @model
	 * @generated
	 */
	String getCronExpression();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.PollingTrigger#getCronExpression <em>Cron Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cron Expression</em>' attribute.
	 * @see #getCronExpression()
	 * @generated
	 */
	void setCronExpression(String value);

	/**
	 * Returns the value of the '<em><b>Repositories</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Repositories</em>' attribute list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getPollingTrigger_Repositories()
	 * @model
	 * @generated
	 */
	EList<String> getRepositories();

	/**
	 * Returns the value of the '<em><b>Conditions</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.bambooMM.TriggerCondition}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Conditions</em>' containment reference list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getPollingTrigger_Conditions()
	 * @model containment="true"
	 * @generated
	 */
	EList<TriggerCondition> getConditions();

} // PollingTrigger
