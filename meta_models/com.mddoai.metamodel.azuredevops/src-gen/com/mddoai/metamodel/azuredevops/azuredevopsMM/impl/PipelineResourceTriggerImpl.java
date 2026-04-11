/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.IncludeExcludeFilters;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResourceTrigger;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pipeline Resource Trigger</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineResourceTriggerImpl#getEnabled <em>Enabled</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineResourceTriggerImpl#getDisabled <em>Disabled</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineResourceTriggerImpl#getStages <em>Stages</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineResourceTriggerImpl#getTags <em>Tags</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineResourceTriggerImpl#getBranches <em>Branches</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PipelineResourceTriggerImpl extends MinimalEObjectImpl.Container implements PipelineResourceTrigger {
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
	 * The cached value of the '{@link #getStages() <em>Stages</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStages()
	 * @generated
	 * @ordered
	 */
	protected EList<String> stages;

	/**
	 * The cached value of the '{@link #getTags() <em>Tags</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTags()
	 * @generated
	 * @ordered
	 */
	protected EList<String> tags;

	/**
	 * The cached value of the '{@link #getBranches() <em>Branches</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBranches()
	 * @generated
	 * @ordered
	 */
	protected IncludeExcludeFilters branches;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PipelineResourceTriggerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AzuredevopsMMPackage.Literals.PIPELINE_RESOURCE_TRIGGER;
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
					AzuredevopsMMPackage.PIPELINE_RESOURCE_TRIGGER__ENABLED, oldEnabled, enabled));
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
					AzuredevopsMMPackage.PIPELINE_RESOURCE_TRIGGER__DISABLED, oldDisabled, disabled));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getStages() {
		if (stages == null) {
			stages = new EDataTypeUniqueEList<String>(String.class, this,
					AzuredevopsMMPackage.PIPELINE_RESOURCE_TRIGGER__STAGES);
		}
		return stages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getTags() {
		if (tags == null) {
			tags = new EDataTypeUniqueEList<String>(String.class, this,
					AzuredevopsMMPackage.PIPELINE_RESOURCE_TRIGGER__TAGS);
		}
		return tags;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IncludeExcludeFilters getBranches() {
		return branches;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBranches(IncludeExcludeFilters newBranches, NotificationChain msgs) {
		IncludeExcludeFilters oldBranches = branches;
		branches = newBranches;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.PIPELINE_RESOURCE_TRIGGER__BRANCHES, oldBranches, newBranches);
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
	public void setBranches(IncludeExcludeFilters newBranches) {
		if (newBranches != branches) {
			NotificationChain msgs = null;
			if (branches != null)
				msgs = ((InternalEObject) branches).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.PIPELINE_RESOURCE_TRIGGER__BRANCHES, null, msgs);
			if (newBranches != null)
				msgs = ((InternalEObject) newBranches).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.PIPELINE_RESOURCE_TRIGGER__BRANCHES, null, msgs);
			msgs = basicSetBranches(newBranches, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.PIPELINE_RESOURCE_TRIGGER__BRANCHES, newBranches, newBranches));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case AzuredevopsMMPackage.PIPELINE_RESOURCE_TRIGGER__BRANCHES:
			return basicSetBranches(null, msgs);
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
		case AzuredevopsMMPackage.PIPELINE_RESOURCE_TRIGGER__ENABLED:
			return getEnabled();
		case AzuredevopsMMPackage.PIPELINE_RESOURCE_TRIGGER__DISABLED:
			return getDisabled();
		case AzuredevopsMMPackage.PIPELINE_RESOURCE_TRIGGER__STAGES:
			return getStages();
		case AzuredevopsMMPackage.PIPELINE_RESOURCE_TRIGGER__TAGS:
			return getTags();
		case AzuredevopsMMPackage.PIPELINE_RESOURCE_TRIGGER__BRANCHES:
			return getBranches();
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
		case AzuredevopsMMPackage.PIPELINE_RESOURCE_TRIGGER__ENABLED:
			setEnabled((Boolean) newValue);
			return;
		case AzuredevopsMMPackage.PIPELINE_RESOURCE_TRIGGER__DISABLED:
			setDisabled((Boolean) newValue);
			return;
		case AzuredevopsMMPackage.PIPELINE_RESOURCE_TRIGGER__STAGES:
			getStages().clear();
			getStages().addAll((Collection<? extends String>) newValue);
			return;
		case AzuredevopsMMPackage.PIPELINE_RESOURCE_TRIGGER__TAGS:
			getTags().clear();
			getTags().addAll((Collection<? extends String>) newValue);
			return;
		case AzuredevopsMMPackage.PIPELINE_RESOURCE_TRIGGER__BRANCHES:
			setBranches((IncludeExcludeFilters) newValue);
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
		case AzuredevopsMMPackage.PIPELINE_RESOURCE_TRIGGER__ENABLED:
			setEnabled(ENABLED_EDEFAULT);
			return;
		case AzuredevopsMMPackage.PIPELINE_RESOURCE_TRIGGER__DISABLED:
			setDisabled(DISABLED_EDEFAULT);
			return;
		case AzuredevopsMMPackage.PIPELINE_RESOURCE_TRIGGER__STAGES:
			getStages().clear();
			return;
		case AzuredevopsMMPackage.PIPELINE_RESOURCE_TRIGGER__TAGS:
			getTags().clear();
			return;
		case AzuredevopsMMPackage.PIPELINE_RESOURCE_TRIGGER__BRANCHES:
			setBranches((IncludeExcludeFilters) null);
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
		case AzuredevopsMMPackage.PIPELINE_RESOURCE_TRIGGER__ENABLED:
			return ENABLED_EDEFAULT == null ? enabled != null : !ENABLED_EDEFAULT.equals(enabled);
		case AzuredevopsMMPackage.PIPELINE_RESOURCE_TRIGGER__DISABLED:
			return DISABLED_EDEFAULT == null ? disabled != null : !DISABLED_EDEFAULT.equals(disabled);
		case AzuredevopsMMPackage.PIPELINE_RESOURCE_TRIGGER__STAGES:
			return stages != null && !stages.isEmpty();
		case AzuredevopsMMPackage.PIPELINE_RESOURCE_TRIGGER__TAGS:
			return tags != null && !tags.isEmpty();
		case AzuredevopsMMPackage.PIPELINE_RESOURCE_TRIGGER__BRANCHES:
			return branches != null;
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
		result.append(", stages: ");
		result.append(stages);
		result.append(", tags: ");
		result.append(tags);
		result.append(')');
		return result.toString();
	}

} //PipelineResourceTriggerImpl
