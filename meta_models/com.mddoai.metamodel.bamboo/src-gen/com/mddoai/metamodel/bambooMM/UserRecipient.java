/**
 */
package com.mddoai.metamodel.bambooMM;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>User Recipient</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.UserRecipient#getUsers <em>Users</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getUserRecipient()
 * @model
 * @generated
 */
public interface UserRecipient extends NotificationRecipient {
	/**
	 * Returns the value of the '<em><b>Users</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Users</em>' attribute list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getUserRecipient_Users()
	 * @model
	 * @generated
	 */
	EList<String> getUsers();

} // UserRecipient
