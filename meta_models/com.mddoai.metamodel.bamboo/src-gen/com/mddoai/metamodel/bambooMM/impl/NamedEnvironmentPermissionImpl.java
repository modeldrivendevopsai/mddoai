/**
 */
package com.mddoai.metamodel.bambooMM.impl;

import com.mddoai.metamodel.bambooMM.BambooPackage;
import com.mddoai.metamodel.bambooMM.EnvironmentPermissionEntry;
import com.mddoai.metamodel.bambooMM.NamedEnvironmentPermission;

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
 * An implementation of the model object '<em><b>Named Environment Permission</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.NamedEnvironmentPermissionImpl#getEnvironmentName <em>Environment Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.NamedEnvironmentPermissionImpl#getEntries <em>Entries</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NamedEnvironmentPermissionImpl extends MinimalEObjectImpl.Container implements NamedEnvironmentPermission {
	/**
	 * The default value of the '{@link #getEnvironmentName() <em>Environment Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnvironmentName()
	 * @generated
	 * @ordered
	 */
	protected static final String ENVIRONMENT_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEnvironmentName() <em>Environment Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnvironmentName()
	 * @generated
	 * @ordered
	 */
	protected String environmentName = ENVIRONMENT_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getEntries() <em>Entries</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntries()
	 * @generated
	 * @ordered
	 */
	protected EList<EnvironmentPermissionEntry> entries;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NamedEnvironmentPermissionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BambooPackage.Literals.NAMED_ENVIRONMENT_PERMISSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getEnvironmentName() {
		return environmentName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEnvironmentName(String newEnvironmentName) {
		String oldEnvironmentName = environmentName;
		environmentName = newEnvironmentName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					BambooPackage.NAMED_ENVIRONMENT_PERMISSION__ENVIRONMENT_NAME, oldEnvironmentName, environmentName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<EnvironmentPermissionEntry> getEntries() {
		if (entries == null) {
			entries = new EObjectContainmentEList<EnvironmentPermissionEntry>(EnvironmentPermissionEntry.class, this,
					BambooPackage.NAMED_ENVIRONMENT_PERMISSION__ENTRIES);
		}
		return entries;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case BambooPackage.NAMED_ENVIRONMENT_PERMISSION__ENTRIES:
			return ((InternalEList<?>) getEntries()).basicRemove(otherEnd, msgs);
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
		case BambooPackage.NAMED_ENVIRONMENT_PERMISSION__ENVIRONMENT_NAME:
			return getEnvironmentName();
		case BambooPackage.NAMED_ENVIRONMENT_PERMISSION__ENTRIES:
			return getEntries();
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
		case BambooPackage.NAMED_ENVIRONMENT_PERMISSION__ENVIRONMENT_NAME:
			setEnvironmentName((String) newValue);
			return;
		case BambooPackage.NAMED_ENVIRONMENT_PERMISSION__ENTRIES:
			getEntries().clear();
			getEntries().addAll((Collection<? extends EnvironmentPermissionEntry>) newValue);
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
		case BambooPackage.NAMED_ENVIRONMENT_PERMISSION__ENVIRONMENT_NAME:
			setEnvironmentName(ENVIRONMENT_NAME_EDEFAULT);
			return;
		case BambooPackage.NAMED_ENVIRONMENT_PERMISSION__ENTRIES:
			getEntries().clear();
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
		case BambooPackage.NAMED_ENVIRONMENT_PERMISSION__ENVIRONMENT_NAME:
			return ENVIRONMENT_NAME_EDEFAULT == null ? environmentName != null
					: !ENVIRONMENT_NAME_EDEFAULT.equals(environmentName);
		case BambooPackage.NAMED_ENVIRONMENT_PERMISSION__ENTRIES:
			return entries != null && !entries.isEmpty();
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
		result.append(" (environmentName: ");
		result.append(environmentName);
		result.append(')');
		return result.toString();
	}

} //NamedEnvironmentPermissionImpl
