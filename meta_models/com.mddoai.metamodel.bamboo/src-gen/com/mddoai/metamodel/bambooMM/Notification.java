/**
 */
package com.mddoai.metamodel.bambooMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Notification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Notification#getRecipients <em>Recipients</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Notification#getEvents <em>Events</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getNotification()
 * @model
 * @generated
 */
public interface Notification extends EObject {
	/**
	 * Returns the value of the '<em><b>Recipients</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.bambooMM.NotificationRecipient}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Recipients</em>' containment reference list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getNotification_Recipients()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<NotificationRecipient> getRecipients();

	/**
	 * Returns the value of the '<em><b>Events</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.bambooMM.NotificationEvent}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Events</em>' containment reference list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getNotification_Events()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<NotificationEvent> getEvents();

} // Notification
