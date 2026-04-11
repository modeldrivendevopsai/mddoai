/**
 */
package com.mddoai.metamodel.bambooMM.impl;

import com.mddoai.metamodel.bambooMM.BambooPackage;
import com.mddoai.metamodel.bambooMM.NOTIFICATION_EVENT_TYPE;
import com.mddoai.metamodel.bambooMM.NotificationEvent;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Notification Event</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.NotificationEventImpl#getType <em>Type</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.NotificationEventImpl#getFailures <em>Failures</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.NotificationEventImpl#getFirstOnly <em>First Only</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NotificationEventImpl extends MinimalEObjectImpl.Container implements NotificationEvent {
	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final NOTIFICATION_EVENT_TYPE TYPE_EDEFAULT = NOTIFICATION_EVENT_TYPE.PLAN_FAILED;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected NOTIFICATION_EVENT_TYPE type = TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getFailures() <em>Failures</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFailures()
	 * @generated
	 * @ordered
	 */
	protected static final Integer FAILURES_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFailures() <em>Failures</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFailures()
	 * @generated
	 * @ordered
	 */
	protected Integer failures = FAILURES_EDEFAULT;

	/**
	 * The default value of the '{@link #getFirstOnly() <em>First Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFirstOnly()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean FIRST_ONLY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFirstOnly() <em>First Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFirstOnly()
	 * @generated
	 * @ordered
	 */
	protected Boolean firstOnly = FIRST_ONLY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NotificationEventImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BambooPackage.Literals.NOTIFICATION_EVENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NOTIFICATION_EVENT_TYPE getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setType(NOTIFICATION_EVENT_TYPE newType) {
		NOTIFICATION_EVENT_TYPE oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.NOTIFICATION_EVENT__TYPE, oldType,
					type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Integer getFailures() {
		return failures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFailures(Integer newFailures) {
		Integer oldFailures = failures;
		failures = newFailures;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.NOTIFICATION_EVENT__FAILURES,
					oldFailures, failures));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getFirstOnly() {
		return firstOnly;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFirstOnly(Boolean newFirstOnly) {
		Boolean oldFirstOnly = firstOnly;
		firstOnly = newFirstOnly;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.NOTIFICATION_EVENT__FIRST_ONLY,
					oldFirstOnly, firstOnly));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case BambooPackage.NOTIFICATION_EVENT__TYPE:
			return getType();
		case BambooPackage.NOTIFICATION_EVENT__FAILURES:
			return getFailures();
		case BambooPackage.NOTIFICATION_EVENT__FIRST_ONLY:
			return getFirstOnly();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case BambooPackage.NOTIFICATION_EVENT__TYPE:
			setType((NOTIFICATION_EVENT_TYPE) newValue);
			return;
		case BambooPackage.NOTIFICATION_EVENT__FAILURES:
			setFailures((Integer) newValue);
			return;
		case BambooPackage.NOTIFICATION_EVENT__FIRST_ONLY:
			setFirstOnly((Boolean) newValue);
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
		case BambooPackage.NOTIFICATION_EVENT__TYPE:
			setType(TYPE_EDEFAULT);
			return;
		case BambooPackage.NOTIFICATION_EVENT__FAILURES:
			setFailures(FAILURES_EDEFAULT);
			return;
		case BambooPackage.NOTIFICATION_EVENT__FIRST_ONLY:
			setFirstOnly(FIRST_ONLY_EDEFAULT);
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
		case BambooPackage.NOTIFICATION_EVENT__TYPE:
			return type != TYPE_EDEFAULT;
		case BambooPackage.NOTIFICATION_EVENT__FAILURES:
			return FAILURES_EDEFAULT == null ? failures != null : !FAILURES_EDEFAULT.equals(failures);
		case BambooPackage.NOTIFICATION_EVENT__FIRST_ONLY:
			return FIRST_ONLY_EDEFAULT == null ? firstOnly != null : !FIRST_ONLY_EDEFAULT.equals(firstOnly);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (type: ");
		result.append(type);
		result.append(", failures: ");
		result.append(failures);
		result.append(", firstOnly: ");
		result.append(firstOnly);
		result.append(')');
		return result.toString();
	}

} //NotificationEventImpl
