/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.IncludeExcludeFilters;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.Trigger;

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
 * An implementation of the model object '<em><b>Trigger</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.TriggerImpl#getDisabled <em>Disabled</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.TriggerImpl#getBatch <em>Batch</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.TriggerImpl#getBranchList <em>Branch List</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.TriggerImpl#getBranches <em>Branches</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.TriggerImpl#getPaths <em>Paths</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.TriggerImpl#getTags <em>Tags</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TriggerImpl extends MinimalEObjectImpl.Container implements Trigger {
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
	 * The default value of the '{@link #getBatch() <em>Batch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBatch()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean BATCH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBatch() <em>Batch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBatch()
	 * @generated
	 * @ordered
	 */
	protected Boolean batch = BATCH_EDEFAULT;

	/**
	 * The cached value of the '{@link #getBranchList() <em>Branch List</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBranchList()
	 * @generated
	 * @ordered
	 */
	protected EList<String> branchList;

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
	 * The cached value of the '{@link #getPaths() <em>Paths</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPaths()
	 * @generated
	 * @ordered
	 */
	protected IncludeExcludeFilters paths;

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
	protected TriggerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AzuredevopsMMPackage.Literals.TRIGGER;
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
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.TRIGGER__DISABLED, oldDisabled,
					disabled));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getBatch() {
		return batch;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBatch(Boolean newBatch) {
		Boolean oldBatch = batch;
		batch = newBatch;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.TRIGGER__BATCH, oldBatch,
					batch));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getBranchList() {
		if (branchList == null) {
			branchList = new EDataTypeUniqueEList<String>(String.class, this,
					AzuredevopsMMPackage.TRIGGER__BRANCH_LIST);
		}
		return branchList;
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
					AzuredevopsMMPackage.TRIGGER__BRANCHES, oldBranches, newBranches);
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
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.TRIGGER__BRANCHES, null, msgs);
			if (newBranches != null)
				msgs = ((InternalEObject) newBranches).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.TRIGGER__BRANCHES, null, msgs);
			msgs = basicSetBranches(newBranches, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.TRIGGER__BRANCHES, newBranches,
					newBranches));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IncludeExcludeFilters getPaths() {
		return paths;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPaths(IncludeExcludeFilters newPaths, NotificationChain msgs) {
		IncludeExcludeFilters oldPaths = paths;
		paths = newPaths;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.TRIGGER__PATHS, oldPaths, newPaths);
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
	public void setPaths(IncludeExcludeFilters newPaths) {
		if (newPaths != paths) {
			NotificationChain msgs = null;
			if (paths != null)
				msgs = ((InternalEObject) paths).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.TRIGGER__PATHS, null, msgs);
			if (newPaths != null)
				msgs = ((InternalEObject) newPaths).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.TRIGGER__PATHS, null, msgs);
			msgs = basicSetPaths(newPaths, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.TRIGGER__PATHS, newPaths,
					newPaths));
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
					AzuredevopsMMPackage.TRIGGER__TAGS, oldTags, newTags);
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
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.TRIGGER__TAGS, null, msgs);
			if (newTags != null)
				msgs = ((InternalEObject) newTags).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.TRIGGER__TAGS, null, msgs);
			msgs = basicSetTags(newTags, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.TRIGGER__TAGS, newTags,
					newTags));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case AzuredevopsMMPackage.TRIGGER__BRANCHES:
			return basicSetBranches(null, msgs);
		case AzuredevopsMMPackage.TRIGGER__PATHS:
			return basicSetPaths(null, msgs);
		case AzuredevopsMMPackage.TRIGGER__TAGS:
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
		case AzuredevopsMMPackage.TRIGGER__DISABLED:
			return getDisabled();
		case AzuredevopsMMPackage.TRIGGER__BATCH:
			return getBatch();
		case AzuredevopsMMPackage.TRIGGER__BRANCH_LIST:
			return getBranchList();
		case AzuredevopsMMPackage.TRIGGER__BRANCHES:
			return getBranches();
		case AzuredevopsMMPackage.TRIGGER__PATHS:
			return getPaths();
		case AzuredevopsMMPackage.TRIGGER__TAGS:
			return getTags();
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
		case AzuredevopsMMPackage.TRIGGER__DISABLED:
			setDisabled((Boolean) newValue);
			return;
		case AzuredevopsMMPackage.TRIGGER__BATCH:
			setBatch((Boolean) newValue);
			return;
		case AzuredevopsMMPackage.TRIGGER__BRANCH_LIST:
			getBranchList().clear();
			getBranchList().addAll((Collection<? extends String>) newValue);
			return;
		case AzuredevopsMMPackage.TRIGGER__BRANCHES:
			setBranches((IncludeExcludeFilters) newValue);
			return;
		case AzuredevopsMMPackage.TRIGGER__PATHS:
			setPaths((IncludeExcludeFilters) newValue);
			return;
		case AzuredevopsMMPackage.TRIGGER__TAGS:
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
		case AzuredevopsMMPackage.TRIGGER__DISABLED:
			setDisabled(DISABLED_EDEFAULT);
			return;
		case AzuredevopsMMPackage.TRIGGER__BATCH:
			setBatch(BATCH_EDEFAULT);
			return;
		case AzuredevopsMMPackage.TRIGGER__BRANCH_LIST:
			getBranchList().clear();
			return;
		case AzuredevopsMMPackage.TRIGGER__BRANCHES:
			setBranches((IncludeExcludeFilters) null);
			return;
		case AzuredevopsMMPackage.TRIGGER__PATHS:
			setPaths((IncludeExcludeFilters) null);
			return;
		case AzuredevopsMMPackage.TRIGGER__TAGS:
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
		case AzuredevopsMMPackage.TRIGGER__DISABLED:
			return DISABLED_EDEFAULT == null ? disabled != null : !DISABLED_EDEFAULT.equals(disabled);
		case AzuredevopsMMPackage.TRIGGER__BATCH:
			return BATCH_EDEFAULT == null ? batch != null : !BATCH_EDEFAULT.equals(batch);
		case AzuredevopsMMPackage.TRIGGER__BRANCH_LIST:
			return branchList != null && !branchList.isEmpty();
		case AzuredevopsMMPackage.TRIGGER__BRANCHES:
			return branches != null;
		case AzuredevopsMMPackage.TRIGGER__PATHS:
			return paths != null;
		case AzuredevopsMMPackage.TRIGGER__TAGS:
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
		result.append(" (disabled: ");
		result.append(disabled);
		result.append(", batch: ");
		result.append(batch);
		result.append(", branchList: ");
		result.append(branchList);
		result.append(')');
		return result.toString();
	}

} //TriggerImpl
