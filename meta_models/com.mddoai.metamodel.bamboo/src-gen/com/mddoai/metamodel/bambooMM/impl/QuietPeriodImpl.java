/**
 */
package com.mddoai.metamodel.bambooMM.impl;

import com.mddoai.metamodel.bambooMM.BambooPackage;
import com.mddoai.metamodel.bambooMM.QuietPeriod;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Quiet Period</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.QuietPeriodImpl#getQuietPeriodSeconds <em>Quiet Period Seconds</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.QuietPeriodImpl#getMaxRetries <em>Max Retries</em>}</li>
 * </ul>
 *
 * @generated
 */
public class QuietPeriodImpl extends MinimalEObjectImpl.Container implements QuietPeriod {
	/**
	 * The default value of the '{@link #getQuietPeriodSeconds() <em>Quiet Period Seconds</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuietPeriodSeconds()
	 * @generated
	 * @ordered
	 */
	protected static final int QUIET_PERIOD_SECONDS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getQuietPeriodSeconds() <em>Quiet Period Seconds</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuietPeriodSeconds()
	 * @generated
	 * @ordered
	 */
	protected int quietPeriodSeconds = QUIET_PERIOD_SECONDS_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxRetries() <em>Max Retries</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxRetries()
	 * @generated
	 * @ordered
	 */
	protected static final Integer MAX_RETRIES_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMaxRetries() <em>Max Retries</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxRetries()
	 * @generated
	 * @ordered
	 */
	protected Integer maxRetries = MAX_RETRIES_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected QuietPeriodImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BambooPackage.Literals.QUIET_PERIOD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getQuietPeriodSeconds() {
		return quietPeriodSeconds;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setQuietPeriodSeconds(int newQuietPeriodSeconds) {
		int oldQuietPeriodSeconds = quietPeriodSeconds;
		quietPeriodSeconds = newQuietPeriodSeconds;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.QUIET_PERIOD__QUIET_PERIOD_SECONDS,
					oldQuietPeriodSeconds, quietPeriodSeconds));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Integer getMaxRetries() {
		return maxRetries;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMaxRetries(Integer newMaxRetries) {
		Integer oldMaxRetries = maxRetries;
		maxRetries = newMaxRetries;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.QUIET_PERIOD__MAX_RETRIES,
					oldMaxRetries, maxRetries));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case BambooPackage.QUIET_PERIOD__QUIET_PERIOD_SECONDS:
			return getQuietPeriodSeconds();
		case BambooPackage.QUIET_PERIOD__MAX_RETRIES:
			return getMaxRetries();
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
		case BambooPackage.QUIET_PERIOD__QUIET_PERIOD_SECONDS:
			setQuietPeriodSeconds((Integer) newValue);
			return;
		case BambooPackage.QUIET_PERIOD__MAX_RETRIES:
			setMaxRetries((Integer) newValue);
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
		case BambooPackage.QUIET_PERIOD__QUIET_PERIOD_SECONDS:
			setQuietPeriodSeconds(QUIET_PERIOD_SECONDS_EDEFAULT);
			return;
		case BambooPackage.QUIET_PERIOD__MAX_RETRIES:
			setMaxRetries(MAX_RETRIES_EDEFAULT);
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
		case BambooPackage.QUIET_PERIOD__QUIET_PERIOD_SECONDS:
			return quietPeriodSeconds != QUIET_PERIOD_SECONDS_EDEFAULT;
		case BambooPackage.QUIET_PERIOD__MAX_RETRIES:
			return MAX_RETRIES_EDEFAULT == null ? maxRetries != null : !MAX_RETRIES_EDEFAULT.equals(maxRetries);
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
		result.append(" (quietPeriodSeconds: ");
		result.append(quietPeriodSeconds);
		result.append(", maxRetries: ");
		result.append(maxRetries);
		result.append(')');
		return result.toString();
	}

} //QuietPeriodImpl
