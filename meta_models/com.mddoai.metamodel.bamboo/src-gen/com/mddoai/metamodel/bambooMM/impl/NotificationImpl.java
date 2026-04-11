/**
 */
package com.mddoai.metamodel.bambooMM.impl;

import com.mddoai.metamodel.bambooMM.BambooPackage;
import com.mddoai.metamodel.bambooMM.Notification;
import com.mddoai.metamodel.bambooMM.NotificationEvent;
import com.mddoai.metamodel.bambooMM.NotificationRecipient;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Notification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.NotificationImpl#getRecipients <em>Recipients</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.NotificationImpl#getEvents <em>Events</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NotificationImpl extends MinimalEObjectImpl.Container implements Notification {
	/**
	 * The cached value of the '{@link #getRecipients() <em>Recipients</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRecipients()
	 * @generated
	 * @ordered
	 */
	protected EList<NotificationRecipient> recipients;

	/**
	 * The cached value of the '{@link #getEvents() <em>Events</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEvents()
	 * @generated
	 * @ordered
	 */
	protected EList<NotificationEvent> events;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NotificationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BambooPackage.Literals.NOTIFICATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<NotificationRecipient> getRecipients() {
		if (recipients == null) {
			recipients = new EObjectContainmentEList<NotificationRecipient>(NotificationRecipient.class, this,
					BambooPackage.NOTIFICATION__RECIPIENTS);
		}
		return recipients;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<NotificationEvent> getEvents() {
		if (events == null) {
			events = new EObjectContainmentEList<NotificationEvent>(NotificationEvent.class, this,
					BambooPackage.NOTIFICATION__EVENTS);
		}
		return events;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case BambooPackage.NOTIFICATION__RECIPIENTS:
			return ((InternalEList<?>) getRecipients()).basicRemove(otherEnd, msgs);
		case BambooPackage.NOTIFICATION__EVENTS:
			return ((InternalEList<?>) getEvents()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case BambooPackage.NOTIFICATION__RECIPIENTS:
			return getRecipients();
		case BambooPackage.NOTIFICATION__EVENTS:
			return getEvents();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case BambooPackage.NOTIFICATION__RECIPIENTS:
			getRecipients().clear();
			getRecipients().addAll((Collection<? extends NotificationRecipient>) newValue);
			return;
		case BambooPackage.NOTIFICATION__EVENTS:
			getEvents().clear();
			getEvents().addAll((Collection<? extends NotificationEvent>) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case BambooPackage.NOTIFICATION__RECIPIENTS:
			getRecipients().clear();
			return;
		case BambooPackage.NOTIFICATION__EVENTS:
			getEvents().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case BambooPackage.NOTIFICATION__RECIPIENTS:
			return recipients != null && !recipients.isEmpty();
		case BambooPackage.NOTIFICATION__EVENTS:
			return events != null && !events.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //NotificationImpl
