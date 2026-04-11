/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.DeployHook;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.Pool;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.Step;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Deploy Hook</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DeployHookImpl#getPool <em>Pool</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DeployHookImpl#getSteps <em>Steps</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DeployHookImpl extends MinimalEObjectImpl.Container implements DeployHook {
	/**
	 * The cached value of the '{@link #getPool() <em>Pool</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPool()
	 * @generated
	 * @ordered
	 */
	protected Pool pool;

	/**
	 * The cached value of the '{@link #getSteps() <em>Steps</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSteps()
	 * @generated
	 * @ordered
	 */
	protected EList<Step> steps;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DeployHookImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AzuredevopsMMPackage.Literals.DEPLOY_HOOK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Pool getPool() {
		return pool;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPool(Pool newPool, NotificationChain msgs) {
		Pool oldPool = pool;
		pool = newPool;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.DEPLOY_HOOK__POOL, oldPool, newPool);
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
	public void setPool(Pool newPool) {
		if (newPool != pool) {
			NotificationChain msgs = null;
			if (pool != null)
				msgs = ((InternalEObject) pool).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.DEPLOY_HOOK__POOL, null, msgs);
			if (newPool != null)
				msgs = ((InternalEObject) newPool).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.DEPLOY_HOOK__POOL, null, msgs);
			msgs = basicSetPool(newPool, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.DEPLOY_HOOK__POOL, newPool,
					newPool));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Step> getSteps() {
		if (steps == null) {
			steps = new EObjectContainmentEList<Step>(Step.class, this, AzuredevopsMMPackage.DEPLOY_HOOK__STEPS);
		}
		return steps;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case AzuredevopsMMPackage.DEPLOY_HOOK__POOL:
			return basicSetPool(null, msgs);
		case AzuredevopsMMPackage.DEPLOY_HOOK__STEPS:
			return ((InternalEList<?>) getSteps()).basicRemove(otherEnd, msgs);
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
		case AzuredevopsMMPackage.DEPLOY_HOOK__POOL:
			return getPool();
		case AzuredevopsMMPackage.DEPLOY_HOOK__STEPS:
			return getSteps();
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
		case AzuredevopsMMPackage.DEPLOY_HOOK__POOL:
			setPool((Pool) newValue);
			return;
		case AzuredevopsMMPackage.DEPLOY_HOOK__STEPS:
			getSteps().clear();
			getSteps().addAll((Collection<? extends Step>) newValue);
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
		case AzuredevopsMMPackage.DEPLOY_HOOK__POOL:
			setPool((Pool) null);
			return;
		case AzuredevopsMMPackage.DEPLOY_HOOK__STEPS:
			getSteps().clear();
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
		case AzuredevopsMMPackage.DEPLOY_HOOK__POOL:
			return pool != null;
		case AzuredevopsMMPackage.DEPLOY_HOOK__STEPS:
			return steps != null && !steps.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //DeployHookImpl
