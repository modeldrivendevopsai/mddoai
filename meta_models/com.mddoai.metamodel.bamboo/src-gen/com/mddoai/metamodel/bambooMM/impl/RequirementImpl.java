/**
 */
package com.mddoai.metamodel.bambooMM.impl;

import com.mddoai.metamodel.bambooMM.BambooPackage;
import com.mddoai.metamodel.bambooMM.Requirement;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Requirement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.RequirementImpl#getCapability <em>Capability</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.RequirementImpl#getMatchValue <em>Match Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RequirementImpl extends MinimalEObjectImpl.Container implements Requirement {
	/**
	 * The default value of the '{@link #getCapability() <em>Capability</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCapability()
	 * @generated
	 * @ordered
	 */
	protected static final String CAPABILITY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCapability() <em>Capability</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCapability()
	 * @generated
	 * @ordered
	 */
	protected String capability = CAPABILITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getMatchValue() <em>Match Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMatchValue()
	 * @generated
	 * @ordered
	 */
	protected static final String MATCH_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMatchValue() <em>Match Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMatchValue()
	 * @generated
	 * @ordered
	 */
	protected String matchValue = MATCH_VALUE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RequirementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BambooPackage.Literals.REQUIREMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCapability() {
		return capability;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCapability(String newCapability) {
		String oldCapability = capability;
		capability = newCapability;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.REQUIREMENT__CAPABILITY, oldCapability,
					capability));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMatchValue() {
		return matchValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMatchValue(String newMatchValue) {
		String oldMatchValue = matchValue;
		matchValue = newMatchValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.REQUIREMENT__MATCH_VALUE, oldMatchValue,
					matchValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case BambooPackage.REQUIREMENT__CAPABILITY:
			return getCapability();
		case BambooPackage.REQUIREMENT__MATCH_VALUE:
			return getMatchValue();
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
		case BambooPackage.REQUIREMENT__CAPABILITY:
			setCapability((String) newValue);
			return;
		case BambooPackage.REQUIREMENT__MATCH_VALUE:
			setMatchValue((String) newValue);
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
		case BambooPackage.REQUIREMENT__CAPABILITY:
			setCapability(CAPABILITY_EDEFAULT);
			return;
		case BambooPackage.REQUIREMENT__MATCH_VALUE:
			setMatchValue(MATCH_VALUE_EDEFAULT);
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
		case BambooPackage.REQUIREMENT__CAPABILITY:
			return CAPABILITY_EDEFAULT == null ? capability != null : !CAPABILITY_EDEFAULT.equals(capability);
		case BambooPackage.REQUIREMENT__MATCH_VALUE:
			return MATCH_VALUE_EDEFAULT == null ? matchValue != null : !MATCH_VALUE_EDEFAULT.equals(matchValue);
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
		result.append(" (capability: ");
		result.append(capability);
		result.append(", matchValue: ");
		result.append(matchValue);
		result.append(')');
		return result.toString();
	}

} //RequirementImpl
