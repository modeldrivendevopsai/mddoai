/**
 */
package com.mddoai.metamodel.bambooMM.impl;

import com.mddoai.metamodel.bambooMM.BambooPackage;
import com.mddoai.metamodel.bambooMM.BranchIntegration;
import com.mddoai.metamodel.bambooMM.BranchSpecificConfig;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Branch Specific Config</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.BranchSpecificConfigImpl#getIntegration <em>Integration</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.BranchSpecificConfigImpl#isDisableExpiry <em>Disable Expiry</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BranchSpecificConfigImpl extends MinimalEObjectImpl.Container implements BranchSpecificConfig {
	/**
	 * The cached value of the '{@link #getIntegration() <em>Integration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIntegration()
	 * @generated
	 * @ordered
	 */
	protected BranchIntegration integration;

	/**
	 * The default value of the '{@link #isDisableExpiry() <em>Disable Expiry</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDisableExpiry()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DISABLE_EXPIRY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDisableExpiry() <em>Disable Expiry</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDisableExpiry()
	 * @generated
	 * @ordered
	 */
	protected boolean disableExpiry = DISABLE_EXPIRY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BranchSpecificConfigImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BambooPackage.Literals.BRANCH_SPECIFIC_CONFIG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BranchIntegration getIntegration() {
		return integration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIntegration(BranchIntegration newIntegration, NotificationChain msgs) {
		BranchIntegration oldIntegration = integration;
		integration = newIntegration;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					BambooPackage.BRANCH_SPECIFIC_CONFIG__INTEGRATION, oldIntegration, newIntegration);
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
	public void setIntegration(BranchIntegration newIntegration) {
		if (newIntegration != integration) {
			NotificationChain msgs = null;
			if (integration != null)
				msgs = ((InternalEObject) integration).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - BambooPackage.BRANCH_SPECIFIC_CONFIG__INTEGRATION, null, msgs);
			if (newIntegration != null)
				msgs = ((InternalEObject) newIntegration).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - BambooPackage.BRANCH_SPECIFIC_CONFIG__INTEGRATION, null, msgs);
			msgs = basicSetIntegration(newIntegration, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.BRANCH_SPECIFIC_CONFIG__INTEGRATION,
					newIntegration, newIntegration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isDisableExpiry() {
		return disableExpiry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDisableExpiry(boolean newDisableExpiry) {
		boolean oldDisableExpiry = disableExpiry;
		disableExpiry = newDisableExpiry;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.BRANCH_SPECIFIC_CONFIG__DISABLE_EXPIRY,
					oldDisableExpiry, disableExpiry));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case BambooPackage.BRANCH_SPECIFIC_CONFIG__INTEGRATION:
			return basicSetIntegration(null, msgs);
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
		case BambooPackage.BRANCH_SPECIFIC_CONFIG__INTEGRATION:
			return getIntegration();
		case BambooPackage.BRANCH_SPECIFIC_CONFIG__DISABLE_EXPIRY:
			return isDisableExpiry();
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
		case BambooPackage.BRANCH_SPECIFIC_CONFIG__INTEGRATION:
			setIntegration((BranchIntegration) newValue);
			return;
		case BambooPackage.BRANCH_SPECIFIC_CONFIG__DISABLE_EXPIRY:
			setDisableExpiry((Boolean) newValue);
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
		case BambooPackage.BRANCH_SPECIFIC_CONFIG__INTEGRATION:
			setIntegration((BranchIntegration) null);
			return;
		case BambooPackage.BRANCH_SPECIFIC_CONFIG__DISABLE_EXPIRY:
			setDisableExpiry(DISABLE_EXPIRY_EDEFAULT);
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
		case BambooPackage.BRANCH_SPECIFIC_CONFIG__INTEGRATION:
			return integration != null;
		case BambooPackage.BRANCH_SPECIFIC_CONFIG__DISABLE_EXPIRY:
			return disableExpiry != DISABLE_EXPIRY_EDEFAULT;
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
		result.append(" (disableExpiry: ");
		result.append(disableExpiry);
		result.append(')');
		return result.toString();
	}

} //BranchSpecificConfigImpl
