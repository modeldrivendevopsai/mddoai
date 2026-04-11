/**
 */
package com.mddoai.metamodel.bambooMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Named Environment Permission</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.NamedEnvironmentPermission#getEnvironmentName <em>Environment Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.NamedEnvironmentPermission#getEntries <em>Entries</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getNamedEnvironmentPermission()
 * @model
 * @generated
 */
public interface NamedEnvironmentPermission extends EObject {
	/**
	 * Returns the value of the '<em><b>Environment Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Environment Name</em>' attribute.
	 * @see #setEnvironmentName(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getNamedEnvironmentPermission_EnvironmentName()
	 * @model required="true"
	 * @generated
	 */
	String getEnvironmentName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.NamedEnvironmentPermission#getEnvironmentName <em>Environment Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Environment Name</em>' attribute.
	 * @see #getEnvironmentName()
	 * @generated
	 */
	void setEnvironmentName(String value);

	/**
	 * Returns the value of the '<em><b>Entries</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.bambooMM.EnvironmentPermissionEntry}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entries</em>' containment reference list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getNamedEnvironmentPermission_Entries()
	 * @model containment="true"
	 * @generated
	 */
	EList<EnvironmentPermissionEntry> getEntries();

} // NamedEnvironmentPermission
