/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Webhook Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.WebhookResource#getWebhook <em>Webhook</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.WebhookResource#getConnection <em>Connection</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.WebhookResource#getType <em>Type</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.WebhookResource#getFilters <em>Filters</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getWebhookResource()
 * @model
 * @generated
 */
public interface WebhookResource extends EObject {
	/**
	 * Returns the value of the '<em><b>Webhook</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Webhook</em>' attribute.
	 * @see #setWebhook(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getWebhookResource_Webhook()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getWebhook();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.WebhookResource#getWebhook <em>Webhook</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Webhook</em>' attribute.
	 * @see #getWebhook()
	 * @generated
	 */
	void setWebhook(String value);

	/**
	 * Returns the value of the '<em><b>Connection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connection</em>' attribute.
	 * @see #setConnection(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getWebhookResource_Connection()
	 * @model required="true"
	 * @generated
	 */
	String getConnection();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.WebhookResource#getConnection <em>Connection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connection</em>' attribute.
	 * @see #getConnection()
	 * @generated
	 */
	void setConnection(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getWebhookResource_Type()
	 * @model
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.WebhookResource#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Filters</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.azuredevops.azuredevopsMM.WebhookFilter}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filters</em>' containment reference list.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getWebhookResource_Filters()
	 * @model containment="true"
	 * @generated
	 */
	EList<WebhookFilter> getFilters();

} // WebhookResource
