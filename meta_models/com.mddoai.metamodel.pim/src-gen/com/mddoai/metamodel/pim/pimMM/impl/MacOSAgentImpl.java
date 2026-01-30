/**
 */
package com.mddoai.metamodel.pim.pimMM.impl;

import com.mddoai.metamodel.pim.pimMM.Expression;
import com.mddoai.metamodel.pim.pimMM.MacOSAgent;
import com.mddoai.metamodel.pim.pimMM.PimMMPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Mac OS Agent</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.MacOSAgentImpl#getXcode <em>Xcode</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MacOSAgentImpl extends PresetAgentImpl implements MacOSAgent {
	/**
	 * The cached value of the '{@link #getXcode() <em>Xcode</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXcode()
	 * @generated
	 * @ordered
	 */
	protected Expression xcode;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MacOSAgentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PimMMPackage.Literals.MAC_OS_AGENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getXcode() {
		return xcode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetXcode(Expression newXcode, NotificationChain msgs) {
		Expression oldXcode = xcode;
		xcode = newXcode;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					PimMMPackage.MAC_OS_AGENT__XCODE, oldXcode, newXcode);
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
	public void setXcode(Expression newXcode) {
		if (newXcode != xcode) {
			NotificationChain msgs = null;
			if (xcode != null)
				msgs = ((InternalEObject) xcode).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - PimMMPackage.MAC_OS_AGENT__XCODE, null, msgs);
			if (newXcode != null)
				msgs = ((InternalEObject) newXcode).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - PimMMPackage.MAC_OS_AGENT__XCODE, null, msgs);
			msgs = basicSetXcode(newXcode, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PimMMPackage.MAC_OS_AGENT__XCODE, newXcode,
					newXcode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case PimMMPackage.MAC_OS_AGENT__XCODE:
			return basicSetXcode(null, msgs);
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
		case PimMMPackage.MAC_OS_AGENT__XCODE:
			return getXcode();
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
		case PimMMPackage.MAC_OS_AGENT__XCODE:
			setXcode((Expression) newValue);
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
		case PimMMPackage.MAC_OS_AGENT__XCODE:
			setXcode((Expression) null);
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
		case PimMMPackage.MAC_OS_AGENT__XCODE:
			return xcode != null;
		}
		return super.eIsSet(featureID);
	}

} //MacOSAgentImpl
