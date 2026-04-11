/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.DeployHook;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.OnSuccessOrFailureHook;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>On Success Or Failure Hook</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.OnSuccessOrFailureHookImpl#getFailure <em>Failure</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.OnSuccessOrFailureHookImpl#getSuccess <em>Success</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OnSuccessOrFailureHookImpl extends MinimalEObjectImpl.Container implements OnSuccessOrFailureHook {
	/**
	 * The cached value of the '{@link #getFailure() <em>Failure</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFailure()
	 * @generated
	 * @ordered
	 */
	protected DeployHook failure;

	/**
	 * The cached value of the '{@link #getSuccess() <em>Success</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuccess()
	 * @generated
	 * @ordered
	 */
	protected DeployHook success;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OnSuccessOrFailureHookImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AzuredevopsMMPackage.Literals.ON_SUCCESS_OR_FAILURE_HOOK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DeployHook getFailure() {
		return failure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFailure(DeployHook newFailure, NotificationChain msgs) {
		DeployHook oldFailure = failure;
		failure = newFailure;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.ON_SUCCESS_OR_FAILURE_HOOK__FAILURE, oldFailure, newFailure);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFailure(DeployHook newFailure) {
		if (newFailure != failure) {
			NotificationChain msgs = null;
			if (failure != null)
				msgs = ((InternalEObject) failure).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.ON_SUCCESS_OR_FAILURE_HOOK__FAILURE, null, msgs);
			if (newFailure != null)
				msgs = ((InternalEObject) newFailure).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.ON_SUCCESS_OR_FAILURE_HOOK__FAILURE, null, msgs);
			msgs = basicSetFailure(newFailure, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.ON_SUCCESS_OR_FAILURE_HOOK__FAILURE, newFailure, newFailure));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DeployHook getSuccess() {
		return success;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSuccess(DeployHook newSuccess, NotificationChain msgs) {
		DeployHook oldSuccess = success;
		success = newSuccess;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.ON_SUCCESS_OR_FAILURE_HOOK__SUCCESS, oldSuccess, newSuccess);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSuccess(DeployHook newSuccess) {
		if (newSuccess != success) {
			NotificationChain msgs = null;
			if (success != null)
				msgs = ((InternalEObject) success).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.ON_SUCCESS_OR_FAILURE_HOOK__SUCCESS, null, msgs);
			if (newSuccess != null)
				msgs = ((InternalEObject) newSuccess).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.ON_SUCCESS_OR_FAILURE_HOOK__SUCCESS, null, msgs);
			msgs = basicSetSuccess(newSuccess, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.ON_SUCCESS_OR_FAILURE_HOOK__SUCCESS, newSuccess, newSuccess));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case AzuredevopsMMPackage.ON_SUCCESS_OR_FAILURE_HOOK__FAILURE:
			return basicSetFailure(null, msgs);
		case AzuredevopsMMPackage.ON_SUCCESS_OR_FAILURE_HOOK__SUCCESS:
			return basicSetSuccess(null, msgs);
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
		case AzuredevopsMMPackage.ON_SUCCESS_OR_FAILURE_HOOK__FAILURE:
			return getFailure();
		case AzuredevopsMMPackage.ON_SUCCESS_OR_FAILURE_HOOK__SUCCESS:
			return getSuccess();
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
		case AzuredevopsMMPackage.ON_SUCCESS_OR_FAILURE_HOOK__FAILURE:
			setFailure((DeployHook) newValue);
			return;
		case AzuredevopsMMPackage.ON_SUCCESS_OR_FAILURE_HOOK__SUCCESS:
			setSuccess((DeployHook) newValue);
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
		case AzuredevopsMMPackage.ON_SUCCESS_OR_FAILURE_HOOK__FAILURE:
			setFailure((DeployHook) null);
			return;
		case AzuredevopsMMPackage.ON_SUCCESS_OR_FAILURE_HOOK__SUCCESS:
			setSuccess((DeployHook) null);
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
		case AzuredevopsMMPackage.ON_SUCCESS_OR_FAILURE_HOOK__FAILURE:
			return failure != null;
		case AzuredevopsMMPackage.ON_SUCCESS_OR_FAILURE_HOOK__SUCCESS:
			return success != null;
		}
		return super.eIsSet(featureID);
	}

} //OnSuccessOrFailureHookImpl
