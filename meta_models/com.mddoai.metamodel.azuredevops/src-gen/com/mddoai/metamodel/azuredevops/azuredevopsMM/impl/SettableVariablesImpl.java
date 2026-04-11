/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.SettableVariables;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Settable Variables</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.SettableVariablesImpl#getNone <em>None</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.SettableVariablesImpl#getVariables <em>Variables</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SettableVariablesImpl extends MinimalEObjectImpl.Container implements SettableVariables {
	/**
	 * The default value of the '{@link #getNone() <em>None</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNone()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean NONE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNone() <em>None</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNone()
	 * @generated
	 * @ordered
	 */
	protected Boolean none = NONE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getVariables() <em>Variables</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariables()
	 * @generated
	 * @ordered
	 */
	protected EList<String> variables;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SettableVariablesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AzuredevopsMMPackage.Literals.SETTABLE_VARIABLES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getNone() {
		return none;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNone(Boolean newNone) {
		Boolean oldNone = none;
		none = newNone;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.SETTABLE_VARIABLES__NONE,
					oldNone, none));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getVariables() {
		if (variables == null) {
			variables = new EDataTypeUniqueEList<String>(String.class, this,
					AzuredevopsMMPackage.SETTABLE_VARIABLES__VARIABLES);
		}
		return variables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case AzuredevopsMMPackage.SETTABLE_VARIABLES__NONE:
			return getNone();
		case AzuredevopsMMPackage.SETTABLE_VARIABLES__VARIABLES:
			return getVariables();
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
		case AzuredevopsMMPackage.SETTABLE_VARIABLES__NONE:
			setNone((Boolean) newValue);
			return;
		case AzuredevopsMMPackage.SETTABLE_VARIABLES__VARIABLES:
			getVariables().clear();
			getVariables().addAll((Collection<? extends String>) newValue);
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
		case AzuredevopsMMPackage.SETTABLE_VARIABLES__NONE:
			setNone(NONE_EDEFAULT);
			return;
		case AzuredevopsMMPackage.SETTABLE_VARIABLES__VARIABLES:
			getVariables().clear();
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
		case AzuredevopsMMPackage.SETTABLE_VARIABLES__NONE:
			return NONE_EDEFAULT == null ? none != null : !NONE_EDEFAULT.equals(none);
		case AzuredevopsMMPackage.SETTABLE_VARIABLES__VARIABLES:
			return variables != null && !variables.isEmpty();
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
		result.append(" (none: ");
		result.append(none);
		result.append(", variables: ");
		result.append(variables);
		result.append(')');
		return result.toString();
	}

} //SettableVariablesImpl
