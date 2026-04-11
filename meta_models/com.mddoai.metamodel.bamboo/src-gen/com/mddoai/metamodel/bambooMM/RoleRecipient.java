/**
 */
package com.mddoai.metamodel.bambooMM;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Role Recipient</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.RoleRecipient#getRole <em>Role</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getRoleRecipient()
 * @model
 * @generated
 */
public interface RoleRecipient extends NotificationRecipient {
	/**
	 * Returns the value of the '<em><b>Role</b></em>' attribute.
	 * The literals are from the enumeration {@link com.mddoai.metamodel.bambooMM.NOTIFICATION_ROLE}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Role</em>' attribute.
	 * @see com.mddoai.metamodel.bambooMM.NOTIFICATION_ROLE
	 * @see #setRole(NOTIFICATION_ROLE)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getRoleRecipient_Role()
	 * @model required="true"
	 * @generated
	 */
	NOTIFICATION_ROLE getRole();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.RoleRecipient#getRole <em>Role</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Role</em>' attribute.
	 * @see com.mddoai.metamodel.bambooMM.NOTIFICATION_ROLE
	 * @see #getRole()
	 * @generated
	 */
	void setRole(NOTIFICATION_ROLE value);

} // RoleRecipient
