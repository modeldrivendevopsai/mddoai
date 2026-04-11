/**
 */
package com.mddoai.metamodel.bambooMM;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Notification Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.NotificationEvent#getType <em>Type</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.NotificationEvent#getFailures <em>Failures</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.NotificationEvent#getFirstOnly <em>First Only</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getNotificationEvent()
 * @model
 * @generated
 */
public interface NotificationEvent extends EObject {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link com.mddoai.metamodel.bambooMM.NOTIFICATION_EVENT_TYPE}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see com.mddoai.metamodel.bambooMM.NOTIFICATION_EVENT_TYPE
	 * @see #setType(NOTIFICATION_EVENT_TYPE)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getNotificationEvent_Type()
	 * @model required="true"
	 * @generated
	 */
	NOTIFICATION_EVENT_TYPE getType();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.NotificationEvent#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see com.mddoai.metamodel.bambooMM.NOTIFICATION_EVENT_TYPE
	 * @see #getType()
	 * @generated
	 */
	void setType(NOTIFICATION_EVENT_TYPE value);

	/**
	 * Returns the value of the '<em><b>Failures</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Failures</em>' attribute.
	 * @see #setFailures(Integer)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getNotificationEvent_Failures()
	 * @model
	 * @generated
	 */
	Integer getFailures();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.NotificationEvent#getFailures <em>Failures</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Failures</em>' attribute.
	 * @see #getFailures()
	 * @generated
	 */
	void setFailures(Integer value);

	/**
	 * Returns the value of the '<em><b>First Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>First Only</em>' attribute.
	 * @see #setFirstOnly(Boolean)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getNotificationEvent_FirstOnly()
	 * @model
	 * @generated
	 */
	Boolean getFirstOnly();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.NotificationEvent#getFirstOnly <em>First Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>First Only</em>' attribute.
	 * @see #getFirstOnly()
	 * @generated
	 */
	void setFirstOnly(Boolean value);

} // NotificationEvent
