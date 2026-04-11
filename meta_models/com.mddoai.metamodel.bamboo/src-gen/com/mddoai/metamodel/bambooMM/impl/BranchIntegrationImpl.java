/**
 */
package com.mddoai.metamodel.bambooMM.impl;

import com.mddoai.metamodel.bambooMM.BambooPackage;
import com.mddoai.metamodel.bambooMM.BranchIntegration;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Branch Integration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.BranchIntegrationImpl#getMergeFrom <em>Merge From</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.BranchIntegrationImpl#getMergeTo <em>Merge To</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.BranchIntegrationImpl#isPushOnSuccess <em>Push On Success</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BranchIntegrationImpl extends MinimalEObjectImpl.Container implements BranchIntegration {
	/**
	 * The default value of the '{@link #getMergeFrom() <em>Merge From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMergeFrom()
	 * @generated
	 * @ordered
	 */
	protected static final String MERGE_FROM_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMergeFrom() <em>Merge From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMergeFrom()
	 * @generated
	 * @ordered
	 */
	protected String mergeFrom = MERGE_FROM_EDEFAULT;

	/**
	 * The default value of the '{@link #getMergeTo() <em>Merge To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMergeTo()
	 * @generated
	 * @ordered
	 */
	protected static final String MERGE_TO_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMergeTo() <em>Merge To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMergeTo()
	 * @generated
	 * @ordered
	 */
	protected String mergeTo = MERGE_TO_EDEFAULT;

	/**
	 * The default value of the '{@link #isPushOnSuccess() <em>Push On Success</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPushOnSuccess()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PUSH_ON_SUCCESS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isPushOnSuccess() <em>Push On Success</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPushOnSuccess()
	 * @generated
	 * @ordered
	 */
	protected boolean pushOnSuccess = PUSH_ON_SUCCESS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BranchIntegrationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BambooPackage.Literals.BRANCH_INTEGRATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMergeFrom() {
		return mergeFrom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMergeFrom(String newMergeFrom) {
		String oldMergeFrom = mergeFrom;
		mergeFrom = newMergeFrom;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.BRANCH_INTEGRATION__MERGE_FROM,
					oldMergeFrom, mergeFrom));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMergeTo() {
		return mergeTo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMergeTo(String newMergeTo) {
		String oldMergeTo = mergeTo;
		mergeTo = newMergeTo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.BRANCH_INTEGRATION__MERGE_TO,
					oldMergeTo, mergeTo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isPushOnSuccess() {
		return pushOnSuccess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPushOnSuccess(boolean newPushOnSuccess) {
		boolean oldPushOnSuccess = pushOnSuccess;
		pushOnSuccess = newPushOnSuccess;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.BRANCH_INTEGRATION__PUSH_ON_SUCCESS,
					oldPushOnSuccess, pushOnSuccess));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case BambooPackage.BRANCH_INTEGRATION__MERGE_FROM:
			return getMergeFrom();
		case BambooPackage.BRANCH_INTEGRATION__MERGE_TO:
			return getMergeTo();
		case BambooPackage.BRANCH_INTEGRATION__PUSH_ON_SUCCESS:
			return isPushOnSuccess();
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
		case BambooPackage.BRANCH_INTEGRATION__MERGE_FROM:
			setMergeFrom((String) newValue);
			return;
		case BambooPackage.BRANCH_INTEGRATION__MERGE_TO:
			setMergeTo((String) newValue);
			return;
		case BambooPackage.BRANCH_INTEGRATION__PUSH_ON_SUCCESS:
			setPushOnSuccess((Boolean) newValue);
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
		case BambooPackage.BRANCH_INTEGRATION__MERGE_FROM:
			setMergeFrom(MERGE_FROM_EDEFAULT);
			return;
		case BambooPackage.BRANCH_INTEGRATION__MERGE_TO:
			setMergeTo(MERGE_TO_EDEFAULT);
			return;
		case BambooPackage.BRANCH_INTEGRATION__PUSH_ON_SUCCESS:
			setPushOnSuccess(PUSH_ON_SUCCESS_EDEFAULT);
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
		case BambooPackage.BRANCH_INTEGRATION__MERGE_FROM:
			return MERGE_FROM_EDEFAULT == null ? mergeFrom != null : !MERGE_FROM_EDEFAULT.equals(mergeFrom);
		case BambooPackage.BRANCH_INTEGRATION__MERGE_TO:
			return MERGE_TO_EDEFAULT == null ? mergeTo != null : !MERGE_TO_EDEFAULT.equals(mergeTo);
		case BambooPackage.BRANCH_INTEGRATION__PUSH_ON_SUCCESS:
			return pushOnSuccess != PUSH_ON_SUCCESS_EDEFAULT;
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
		result.append(" (mergeFrom: ");
		result.append(mergeFrom);
		result.append(", mergeTo: ");
		result.append(mergeTo);
		result.append(", pushOnSuccess: ");
		result.append(pushOnSuccess);
		result.append(')');
		return result.toString();
	}

} //BranchIntegrationImpl
