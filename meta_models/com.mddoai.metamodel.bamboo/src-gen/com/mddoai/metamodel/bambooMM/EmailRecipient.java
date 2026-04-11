/**
 */
package com.mddoai.metamodel.bambooMM;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Email Recipient</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.EmailRecipient#getEmails <em>Emails</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getEmailRecipient()
 * @model
 * @generated
 */
public interface EmailRecipient extends NotificationRecipient {
	/**
	 * Returns the value of the '<em><b>Emails</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Emails</em>' attribute list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getEmailRecipient_Emails()
	 * @model
	 * @generated
	 */
	EList<String> getEmails();

} // EmailRecipient
