/**
 */
package com.mddoai.metamodel.bambooMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Permission Entry</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.PermissionEntry#getUsers <em>Users</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.PermissionEntry#getGroups <em>Groups</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.PermissionEntry#getRoles <em>Roles</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.PermissionEntry#getPermissions <em>Permissions</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getPermissionEntry()
 * @model abstract="true"
 * @generated
 */
public interface PermissionEntry extends EObject {
	/**
	 * Returns the value of the '<em><b>Users</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Users</em>' attribute list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getPermissionEntry_Users()
	 * @model
	 * @generated
	 */
	EList<String> getUsers();

	/**
	 * Returns the value of the '<em><b>Groups</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Groups</em>' attribute list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getPermissionEntry_Groups()
	 * @model
	 * @generated
	 */
	EList<String> getGroups();

	/**
	 * Returns the value of the '<em><b>Roles</b></em>' attribute list.
	 * The list contents are of type {@link com.mddoai.metamodel.bambooMM.PERMISSION_ROLE}.
	 * The literals are from the enumeration {@link com.mddoai.metamodel.bambooMM.PERMISSION_ROLE}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Roles</em>' attribute list.
	 * @see com.mddoai.metamodel.bambooMM.PERMISSION_ROLE
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getPermissionEntry_Roles()
	 * @model
	 * @generated
	 */
	EList<PERMISSION_ROLE> getRoles();

	/**
	 * Returns the value of the '<em><b>Permissions</b></em>' attribute list.
	 * The list contents are of type {@link com.mddoai.metamodel.bambooMM.PERMISSION_TYPE}.
	 * The literals are from the enumeration {@link com.mddoai.metamodel.bambooMM.PERMISSION_TYPE}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Permissions</em>' attribute list.
	 * @see com.mddoai.metamodel.bambooMM.PERMISSION_TYPE
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getPermissionEntry_Permissions()
	 * @model
	 * @generated
	 */
	EList<PERMISSION_TYPE> getPermissions();

} // PermissionEntry
