/**
 */
package com.mddoai.metamodel.bambooMM.impl;

import com.mddoai.metamodel.bambooMM.BambooPackage;
import com.mddoai.metamodel.bambooMM.PERMISSION_ROLE;
import com.mddoai.metamodel.bambooMM.PERMISSION_TYPE;
import com.mddoai.metamodel.bambooMM.PermissionEntry;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Permission Entry</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.PermissionEntryImpl#getUsers <em>Users</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.PermissionEntryImpl#getGroups <em>Groups</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.PermissionEntryImpl#getRoles <em>Roles</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.PermissionEntryImpl#getPermissions <em>Permissions</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class PermissionEntryImpl extends MinimalEObjectImpl.Container implements PermissionEntry {
	/**
	 * The cached value of the '{@link #getUsers() <em>Users</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsers()
	 * @generated
	 * @ordered
	 */
	protected EList<String> users;

	/**
	 * The cached value of the '{@link #getGroups() <em>Groups</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroups()
	 * @generated
	 * @ordered
	 */
	protected EList<String> groups;

	/**
	 * The cached value of the '{@link #getRoles() <em>Roles</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoles()
	 * @generated
	 * @ordered
	 */
	protected EList<PERMISSION_ROLE> roles;

	/**
	 * The cached value of the '{@link #getPermissions() <em>Permissions</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPermissions()
	 * @generated
	 * @ordered
	 */
	protected EList<PERMISSION_TYPE> permissions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PermissionEntryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BambooPackage.Literals.PERMISSION_ENTRY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getUsers() {
		if (users == null) {
			users = new EDataTypeUniqueEList<String>(String.class, this, BambooPackage.PERMISSION_ENTRY__USERS);
		}
		return users;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getGroups() {
		if (groups == null) {
			groups = new EDataTypeUniqueEList<String>(String.class, this, BambooPackage.PERMISSION_ENTRY__GROUPS);
		}
		return groups;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<PERMISSION_ROLE> getRoles() {
		if (roles == null) {
			roles = new EDataTypeUniqueEList<PERMISSION_ROLE>(PERMISSION_ROLE.class, this,
					BambooPackage.PERMISSION_ENTRY__ROLES);
		}
		return roles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<PERMISSION_TYPE> getPermissions() {
		if (permissions == null) {
			permissions = new EDataTypeUniqueEList<PERMISSION_TYPE>(PERMISSION_TYPE.class, this,
					BambooPackage.PERMISSION_ENTRY__PERMISSIONS);
		}
		return permissions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case BambooPackage.PERMISSION_ENTRY__USERS:
			return getUsers();
		case BambooPackage.PERMISSION_ENTRY__GROUPS:
			return getGroups();
		case BambooPackage.PERMISSION_ENTRY__ROLES:
			return getRoles();
		case BambooPackage.PERMISSION_ENTRY__PERMISSIONS:
			return getPermissions();
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
		case BambooPackage.PERMISSION_ENTRY__USERS:
			getUsers().clear();
			getUsers().addAll((Collection<? extends String>) newValue);
			return;
		case BambooPackage.PERMISSION_ENTRY__GROUPS:
			getGroups().clear();
			getGroups().addAll((Collection<? extends String>) newValue);
			return;
		case BambooPackage.PERMISSION_ENTRY__ROLES:
			getRoles().clear();
			getRoles().addAll((Collection<? extends PERMISSION_ROLE>) newValue);
			return;
		case BambooPackage.PERMISSION_ENTRY__PERMISSIONS:
			getPermissions().clear();
			getPermissions().addAll((Collection<? extends PERMISSION_TYPE>) newValue);
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
		case BambooPackage.PERMISSION_ENTRY__USERS:
			getUsers().clear();
			return;
		case BambooPackage.PERMISSION_ENTRY__GROUPS:
			getGroups().clear();
			return;
		case BambooPackage.PERMISSION_ENTRY__ROLES:
			getRoles().clear();
			return;
		case BambooPackage.PERMISSION_ENTRY__PERMISSIONS:
			getPermissions().clear();
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
		case BambooPackage.PERMISSION_ENTRY__USERS:
			return users != null && !users.isEmpty();
		case BambooPackage.PERMISSION_ENTRY__GROUPS:
			return groups != null && !groups.isEmpty();
		case BambooPackage.PERMISSION_ENTRY__ROLES:
			return roles != null && !roles.isEmpty();
		case BambooPackage.PERMISSION_ENTRY__PERMISSIONS:
			return permissions != null && !permissions.isEmpty();
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
		result.append(" (users: ");
		result.append(users);
		result.append(", groups: ");
		result.append(groups);
		result.append(", roles: ");
		result.append(roles);
		result.append(", permissions: ");
		result.append(permissions);
		result.append(')');
		return result.toString();
	}

} //PermissionEntryImpl
