/**
 */
package com.mddoai.metamodel.bambooMM.impl;

import com.mddoai.metamodel.bambooMM.BambooPackage;
import com.mddoai.metamodel.bambooMM.BranchDelete;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Branch Delete</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.BranchDeleteImpl#getAfterDeletedDays <em>After Deleted Days</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.BranchDeleteImpl#getAfterInactiveDays <em>After Inactive Days</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BranchDeleteImpl extends MinimalEObjectImpl.Container implements BranchDelete {
	/**
	 * The default value of the '{@link #getAfterDeletedDays() <em>After Deleted Days</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAfterDeletedDays()
	 * @generated
	 * @ordered
	 */
	protected static final String AFTER_DELETED_DAYS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAfterDeletedDays() <em>After Deleted Days</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAfterDeletedDays()
	 * @generated
	 * @ordered
	 */
	protected String afterDeletedDays = AFTER_DELETED_DAYS_EDEFAULT;

	/**
	 * The default value of the '{@link #getAfterInactiveDays() <em>After Inactive Days</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAfterInactiveDays()
	 * @generated
	 * @ordered
	 */
	protected static final String AFTER_INACTIVE_DAYS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAfterInactiveDays() <em>After Inactive Days</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAfterInactiveDays()
	 * @generated
	 * @ordered
	 */
	protected String afterInactiveDays = AFTER_INACTIVE_DAYS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BranchDeleteImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BambooPackage.Literals.BRANCH_DELETE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getAfterDeletedDays() {
		return afterDeletedDays;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAfterDeletedDays(String newAfterDeletedDays) {
		String oldAfterDeletedDays = afterDeletedDays;
		afterDeletedDays = newAfterDeletedDays;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.BRANCH_DELETE__AFTER_DELETED_DAYS,
					oldAfterDeletedDays, afterDeletedDays));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getAfterInactiveDays() {
		return afterInactiveDays;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAfterInactiveDays(String newAfterInactiveDays) {
		String oldAfterInactiveDays = afterInactiveDays;
		afterInactiveDays = newAfterInactiveDays;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.BRANCH_DELETE__AFTER_INACTIVE_DAYS,
					oldAfterInactiveDays, afterInactiveDays));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case BambooPackage.BRANCH_DELETE__AFTER_DELETED_DAYS:
			return getAfterDeletedDays();
		case BambooPackage.BRANCH_DELETE__AFTER_INACTIVE_DAYS:
			return getAfterInactiveDays();
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
		case BambooPackage.BRANCH_DELETE__AFTER_DELETED_DAYS:
			setAfterDeletedDays((String) newValue);
			return;
		case BambooPackage.BRANCH_DELETE__AFTER_INACTIVE_DAYS:
			setAfterInactiveDays((String) newValue);
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
		case BambooPackage.BRANCH_DELETE__AFTER_DELETED_DAYS:
			setAfterDeletedDays(AFTER_DELETED_DAYS_EDEFAULT);
			return;
		case BambooPackage.BRANCH_DELETE__AFTER_INACTIVE_DAYS:
			setAfterInactiveDays(AFTER_INACTIVE_DAYS_EDEFAULT);
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
		case BambooPackage.BRANCH_DELETE__AFTER_DELETED_DAYS:
			return AFTER_DELETED_DAYS_EDEFAULT == null ? afterDeletedDays != null
					: !AFTER_DELETED_DAYS_EDEFAULT.equals(afterDeletedDays);
		case BambooPackage.BRANCH_DELETE__AFTER_INACTIVE_DAYS:
			return AFTER_INACTIVE_DAYS_EDEFAULT == null ? afterInactiveDays != null
					: !AFTER_INACTIVE_DAYS_EDEFAULT.equals(afterInactiveDays);
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
		result.append(" (afterDeletedDays: ");
		result.append(afterDeletedDays);
		result.append(", afterInactiveDays: ");
		result.append(afterInactiveDays);
		result.append(')');
		return result.toString();
	}

} //BranchDeleteImpl
