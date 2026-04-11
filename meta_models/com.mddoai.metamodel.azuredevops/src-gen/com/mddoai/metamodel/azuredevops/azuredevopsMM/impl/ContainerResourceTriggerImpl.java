/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResourceTrigger;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.IncludeExcludeFilters;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Container Resource Trigger</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerResourceTriggerImpl#getEnabled <em>Enabled</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerResourceTriggerImpl#getDisabled <em>Disabled</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerResourceTriggerImpl#getTags <em>Tags</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ContainerResourceTriggerImpl extends MinimalEObjectImpl.Container implements ContainerResourceTrigger {
	/**
	 * The default value of the '{@link #getEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnabled()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean ENABLED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnabled()
	 * @generated
	 * @ordered
	 */
	protected Boolean enabled = ENABLED_EDEFAULT;

	/**
	 * The default value of the '{@link #getDisabled() <em>Disabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisabled()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean DISABLED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDisabled() <em>Disabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisabled()
	 * @generated
	 * @ordered
	 */
	protected Boolean disabled = DISABLED_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTags() <em>Tags</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTags()
	 * @generated
	 * @ordered
	 */
	protected IncludeExcludeFilters tags;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContainerResourceTriggerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AzuredevopsMMPackage.Literals.CONTAINER_RESOURCE_TRIGGER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEnabled(Boolean newEnabled) {
		Boolean oldEnabled = enabled;
		enabled = newEnabled;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.CONTAINER_RESOURCE_TRIGGER__ENABLED, oldEnabled, enabled));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getDisabled() {
		return disabled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDisabled(Boolean newDisabled) {
		Boolean oldDisabled = disabled;
		disabled = newDisabled;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.CONTAINER_RESOURCE_TRIGGER__DISABLED, oldDisabled, disabled));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IncludeExcludeFilters getTags() {
		return tags;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTags(IncludeExcludeFilters newTags, NotificationChain msgs) {
		IncludeExcludeFilters oldTags = tags;
		tags = newTags;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.CONTAINER_RESOURCE_TRIGGER__TAGS, oldTags, newTags);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTags(IncludeExcludeFilters newTags) {
		if (newTags != tags) {
			NotificationChain msgs = null;
			if (tags != null)
				msgs = ((InternalEObject) tags).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.CONTAINER_RESOURCE_TRIGGER__TAGS, null, msgs);
			if (newTags != null)
				msgs = ((InternalEObject) newTags).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.CONTAINER_RESOURCE_TRIGGER__TAGS, null, msgs);
			msgs = basicSetTags(newTags, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.CONTAINER_RESOURCE_TRIGGER__TAGS,
					newTags, newTags));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case AzuredevopsMMPackage.CONTAINER_RESOURCE_TRIGGER__TAGS:
			return basicSetTags(null, msgs);
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
		case AzuredevopsMMPackage.CONTAINER_RESOURCE_TRIGGER__ENABLED:
			return getEnabled();
		case AzuredevopsMMPackage.CONTAINER_RESOURCE_TRIGGER__DISABLED:
			return getDisabled();
		case AzuredevopsMMPackage.CONTAINER_RESOURCE_TRIGGER__TAGS:
			return getTags();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case AzuredevopsMMPackage.CONTAINER_RESOURCE_TRIGGER__ENABLED:
			setEnabled((Boolean) newValue);
			return;
		case AzuredevopsMMPackage.CONTAINER_RESOURCE_TRIGGER__DISABLED:
			setDisabled((Boolean) newValue);
			return;
		case AzuredevopsMMPackage.CONTAINER_RESOURCE_TRIGGER__TAGS:
			setTags((IncludeExcludeFilters) newValue);
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
		case AzuredevopsMMPackage.CONTAINER_RESOURCE_TRIGGER__ENABLED:
			setEnabled(ENABLED_EDEFAULT);
			return;
		case AzuredevopsMMPackage.CONTAINER_RESOURCE_TRIGGER__DISABLED:
			setDisabled(DISABLED_EDEFAULT);
			return;
		case AzuredevopsMMPackage.CONTAINER_RESOURCE_TRIGGER__TAGS:
			setTags((IncludeExcludeFilters) null);
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
		case AzuredevopsMMPackage.CONTAINER_RESOURCE_TRIGGER__ENABLED:
			return ENABLED_EDEFAULT == null ? enabled != null : !ENABLED_EDEFAULT.equals(enabled);
		case AzuredevopsMMPackage.CONTAINER_RESOURCE_TRIGGER__DISABLED:
			return DISABLED_EDEFAULT == null ? disabled != null : !DISABLED_EDEFAULT.equals(disabled);
		case AzuredevopsMMPackage.CONTAINER_RESOURCE_TRIGGER__TAGS:
			return tags != null;
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
		result.append(" (enabled: ");
		result.append(enabled);
		result.append(", disabled: ");
		result.append(disabled);
		result.append(')');
		return result.toString();
	}

} //ContainerResourceTriggerImpl
