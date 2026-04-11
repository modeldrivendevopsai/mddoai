/**
 */
package com.mddoai.metamodel.bambooMM;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Group Recipient</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.GroupRecipient#getGroups <em>Groups</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getGroupRecipient()
 * @model
 * @generated
 */
public interface GroupRecipient extends NotificationRecipient {
	/**
	 * Returns the value of the '<em><b>Groups</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Groups</em>' attribute list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getGroupRecipient_Groups()
	 * @model
	 * @generated
	 */
	EList<String> getGroups();

} // GroupRecipient
