/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.WebhookFilter;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.WebhookResource;

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
 * An implementation of the model object '<em><b>Webhook Resource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.WebhookResourceImpl#getWebhook <em>Webhook</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.WebhookResourceImpl#getConnection <em>Connection</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.WebhookResourceImpl#getType <em>Type</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.WebhookResourceImpl#getFilters <em>Filters</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WebhookResourceImpl extends MinimalEObjectImpl.Container implements WebhookResource {
	/**
	 * The default value of the '{@link #getWebhook() <em>Webhook</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWebhook()
	 * @generated
	 * @ordered
	 */
	protected static final String WEBHOOK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWebhook() <em>Webhook</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWebhook()
	 * @generated
	 * @ordered
	 */
	protected String webhook = WEBHOOK_EDEFAULT;

	/**
	 * The default value of the '{@link #getConnection() <em>Connection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnection()
	 * @generated
	 * @ordered
	 */
	protected static final String CONNECTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConnection() <em>Connection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnection()
	 * @generated
	 * @ordered
	 */
	protected String connection = CONNECTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected String type = TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getFilters() <em>Filters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilters()
	 * @generated
	 * @ordered
	 */
	protected EList<WebhookFilter> filters;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WebhookResourceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AzuredevopsMMPackage.Literals.WEBHOOK_RESOURCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getWebhook() {
		return webhook;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWebhook(String newWebhook) {
		String oldWebhook = webhook;
		webhook = newWebhook;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.WEBHOOK_RESOURCE__WEBHOOK,
					oldWebhook, webhook));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getConnection() {
		return connection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setConnection(String newConnection) {
		String oldConnection = connection;
		connection = newConnection;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.WEBHOOK_RESOURCE__CONNECTION,
					oldConnection, connection));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.WEBHOOK_RESOURCE__TYPE, oldType,
					type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<WebhookFilter> getFilters() {
		if (filters == null) {
			filters = new EObjectContainmentEList<WebhookFilter>(WebhookFilter.class, this,
					AzuredevopsMMPackage.WEBHOOK_RESOURCE__FILTERS);
		}
		return filters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case AzuredevopsMMPackage.WEBHOOK_RESOURCE__FILTERS:
			return ((InternalEList<?>) getFilters()).basicRemove(otherEnd, msgs);
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
		case AzuredevopsMMPackage.WEBHOOK_RESOURCE__WEBHOOK:
			return getWebhook();
		case AzuredevopsMMPackage.WEBHOOK_RESOURCE__CONNECTION:
			return getConnection();
		case AzuredevopsMMPackage.WEBHOOK_RESOURCE__TYPE:
			return getType();
		case AzuredevopsMMPackage.WEBHOOK_RESOURCE__FILTERS:
			return getFilters();
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
		case AzuredevopsMMPackage.WEBHOOK_RESOURCE__WEBHOOK:
			setWebhook((String) newValue);
			return;
		case AzuredevopsMMPackage.WEBHOOK_RESOURCE__CONNECTION:
			setConnection((String) newValue);
			return;
		case AzuredevopsMMPackage.WEBHOOK_RESOURCE__TYPE:
			setType((String) newValue);
			return;
		case AzuredevopsMMPackage.WEBHOOK_RESOURCE__FILTERS:
			getFilters().clear();
			getFilters().addAll((Collection<? extends WebhookFilter>) newValue);
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
		case AzuredevopsMMPackage.WEBHOOK_RESOURCE__WEBHOOK:
			setWebhook(WEBHOOK_EDEFAULT);
			return;
		case AzuredevopsMMPackage.WEBHOOK_RESOURCE__CONNECTION:
			setConnection(CONNECTION_EDEFAULT);
			return;
		case AzuredevopsMMPackage.WEBHOOK_RESOURCE__TYPE:
			setType(TYPE_EDEFAULT);
			return;
		case AzuredevopsMMPackage.WEBHOOK_RESOURCE__FILTERS:
			getFilters().clear();
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
		case AzuredevopsMMPackage.WEBHOOK_RESOURCE__WEBHOOK:
			return WEBHOOK_EDEFAULT == null ? webhook != null : !WEBHOOK_EDEFAULT.equals(webhook);
		case AzuredevopsMMPackage.WEBHOOK_RESOURCE__CONNECTION:
			return CONNECTION_EDEFAULT == null ? connection != null : !CONNECTION_EDEFAULT.equals(connection);
		case AzuredevopsMMPackage.WEBHOOK_RESOURCE__TYPE:
			return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
		case AzuredevopsMMPackage.WEBHOOK_RESOURCE__FILTERS:
			return filters != null && !filters.isEmpty();
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
		result.append(" (webhook: ");
		result.append(webhook);
		result.append(", connection: ");
		result.append(connection);
		result.append(", type: ");
		result.append(type);
		result.append(')');
		return result.toString();
	}

} //WebhookResourceImpl
