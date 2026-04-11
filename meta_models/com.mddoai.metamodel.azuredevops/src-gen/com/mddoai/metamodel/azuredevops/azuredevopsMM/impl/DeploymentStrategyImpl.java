/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.CanaryStrategy;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentStrategy;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.RollingStrategy;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.RunOnceStrategy;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Deployment Strategy</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DeploymentStrategyImpl#getRunOnce <em>Run Once</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DeploymentStrategyImpl#getRolling <em>Rolling</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DeploymentStrategyImpl#getCanary <em>Canary</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DeploymentStrategyImpl extends MinimalEObjectImpl.Container implements DeploymentStrategy {
	/**
	 * The cached value of the '{@link #getRunOnce() <em>Run Once</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRunOnce()
	 * @generated
	 * @ordered
	 */
	protected RunOnceStrategy runOnce;

	/**
	 * The cached value of the '{@link #getRolling() <em>Rolling</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRolling()
	 * @generated
	 * @ordered
	 */
	protected RollingStrategy rolling;

	/**
	 * The cached value of the '{@link #getCanary() <em>Canary</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCanary()
	 * @generated
	 * @ordered
	 */
	protected CanaryStrategy canary;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DeploymentStrategyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AzuredevopsMMPackage.Literals.DEPLOYMENT_STRATEGY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RunOnceStrategy getRunOnce() {
		return runOnce;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRunOnce(RunOnceStrategy newRunOnce, NotificationChain msgs) {
		RunOnceStrategy oldRunOnce = runOnce;
		runOnce = newRunOnce;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.DEPLOYMENT_STRATEGY__RUN_ONCE, oldRunOnce, newRunOnce);
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
	public void setRunOnce(RunOnceStrategy newRunOnce) {
		if (newRunOnce != runOnce) {
			NotificationChain msgs = null;
			if (runOnce != null)
				msgs = ((InternalEObject) runOnce).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.DEPLOYMENT_STRATEGY__RUN_ONCE, null, msgs);
			if (newRunOnce != null)
				msgs = ((InternalEObject) newRunOnce).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.DEPLOYMENT_STRATEGY__RUN_ONCE, null, msgs);
			msgs = basicSetRunOnce(newRunOnce, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.DEPLOYMENT_STRATEGY__RUN_ONCE,
					newRunOnce, newRunOnce));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RollingStrategy getRolling() {
		return rolling;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRolling(RollingStrategy newRolling, NotificationChain msgs) {
		RollingStrategy oldRolling = rolling;
		rolling = newRolling;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.DEPLOYMENT_STRATEGY__ROLLING, oldRolling, newRolling);
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
	public void setRolling(RollingStrategy newRolling) {
		if (newRolling != rolling) {
			NotificationChain msgs = null;
			if (rolling != null)
				msgs = ((InternalEObject) rolling).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.DEPLOYMENT_STRATEGY__ROLLING, null, msgs);
			if (newRolling != null)
				msgs = ((InternalEObject) newRolling).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.DEPLOYMENT_STRATEGY__ROLLING, null, msgs);
			msgs = basicSetRolling(newRolling, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.DEPLOYMENT_STRATEGY__ROLLING,
					newRolling, newRolling));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CanaryStrategy getCanary() {
		return canary;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCanary(CanaryStrategy newCanary, NotificationChain msgs) {
		CanaryStrategy oldCanary = canary;
		canary = newCanary;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.DEPLOYMENT_STRATEGY__CANARY, oldCanary, newCanary);
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
	public void setCanary(CanaryStrategy newCanary) {
		if (newCanary != canary) {
			NotificationChain msgs = null;
			if (canary != null)
				msgs = ((InternalEObject) canary).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.DEPLOYMENT_STRATEGY__CANARY, null, msgs);
			if (newCanary != null)
				msgs = ((InternalEObject) newCanary).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.DEPLOYMENT_STRATEGY__CANARY, null, msgs);
			msgs = basicSetCanary(newCanary, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.DEPLOYMENT_STRATEGY__CANARY,
					newCanary, newCanary));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case AzuredevopsMMPackage.DEPLOYMENT_STRATEGY__RUN_ONCE:
			return basicSetRunOnce(null, msgs);
		case AzuredevopsMMPackage.DEPLOYMENT_STRATEGY__ROLLING:
			return basicSetRolling(null, msgs);
		case AzuredevopsMMPackage.DEPLOYMENT_STRATEGY__CANARY:
			return basicSetCanary(null, msgs);
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
		case AzuredevopsMMPackage.DEPLOYMENT_STRATEGY__RUN_ONCE:
			return getRunOnce();
		case AzuredevopsMMPackage.DEPLOYMENT_STRATEGY__ROLLING:
			return getRolling();
		case AzuredevopsMMPackage.DEPLOYMENT_STRATEGY__CANARY:
			return getCanary();
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
		case AzuredevopsMMPackage.DEPLOYMENT_STRATEGY__RUN_ONCE:
			setRunOnce((RunOnceStrategy) newValue);
			return;
		case AzuredevopsMMPackage.DEPLOYMENT_STRATEGY__ROLLING:
			setRolling((RollingStrategy) newValue);
			return;
		case AzuredevopsMMPackage.DEPLOYMENT_STRATEGY__CANARY:
			setCanary((CanaryStrategy) newValue);
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
		case AzuredevopsMMPackage.DEPLOYMENT_STRATEGY__RUN_ONCE:
			setRunOnce((RunOnceStrategy) null);
			return;
		case AzuredevopsMMPackage.DEPLOYMENT_STRATEGY__ROLLING:
			setRolling((RollingStrategy) null);
			return;
		case AzuredevopsMMPackage.DEPLOYMENT_STRATEGY__CANARY:
			setCanary((CanaryStrategy) null);
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
		case AzuredevopsMMPackage.DEPLOYMENT_STRATEGY__RUN_ONCE:
			return runOnce != null;
		case AzuredevopsMMPackage.DEPLOYMENT_STRATEGY__ROLLING:
			return rolling != null;
		case AzuredevopsMMPackage.DEPLOYMENT_STRATEGY__CANARY:
			return canary != null;
		}
		return super.eIsSet(featureID);
	}

} //DeploymentStrategyImpl
