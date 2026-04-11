/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.IncludeExcludeFilters;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.PrTrigger;

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
 * An implementation of the model object '<em><b>Pr Trigger</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PrTriggerImpl#getDisabled <em>Disabled</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PrTriggerImpl#getAutoCancel <em>Auto Cancel</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PrTriggerImpl#getDrafts <em>Drafts</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PrTriggerImpl#getBranchList <em>Branch List</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PrTriggerImpl#getBranches <em>Branches</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PrTriggerImpl#getPaths <em>Paths</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PrTriggerImpl extends MinimalEObjectImpl.Container implements PrTrigger {
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
	 * The default value of the '{@link #getAutoCancel() <em>Auto Cancel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAutoCancel()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean AUTO_CANCEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAutoCancel() <em>Auto Cancel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAutoCancel()
	 * @generated
	 * @ordered
	 */
	protected Boolean autoCancel = AUTO_CANCEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getDrafts() <em>Drafts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDrafts()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean DRAFTS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDrafts() <em>Drafts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDrafts()
	 * @generated
	 * @ordered
	 */
	protected Boolean drafts = DRAFTS_EDEFAULT;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PrTriggerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AzuredevopsMMPackage.Literals.PR_TRIGGER;
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
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.PR_TRIGGER__DISABLED,
					oldDisabled, disabled));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getAutoCancel() {
		return autoCancel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAutoCancel(Boolean newAutoCancel) {
		Boolean oldAutoCancel = autoCancel;
		autoCancel = newAutoCancel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.PR_TRIGGER__AUTO_CANCEL,
					oldAutoCancel, autoCancel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getDrafts() {
		return drafts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDrafts(Boolean newDrafts) {
		Boolean oldDrafts = drafts;
		drafts = newDrafts;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.PR_TRIGGER__DRAFTS, oldDrafts,
					drafts));
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
					AzuredevopsMMPackage.PR_TRIGGER__BRANCH_LIST);
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
					AzuredevopsMMPackage.PR_TRIGGER__BRANCHES, oldBranches, newBranches);
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
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.PR_TRIGGER__BRANCHES, null, msgs);
			if (newBranches != null)
				msgs = ((InternalEObject) newBranches).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.PR_TRIGGER__BRANCHES, null, msgs);
			msgs = basicSetBranches(newBranches, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.PR_TRIGGER__BRANCHES,
					newBranches, newBranches));
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
					AzuredevopsMMPackage.PR_TRIGGER__PATHS, oldPaths, newPaths);
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
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.PR_TRIGGER__PATHS, null, msgs);
			if (newPaths != null)
				msgs = ((InternalEObject) newPaths).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.PR_TRIGGER__PATHS, null, msgs);
			msgs = basicSetPaths(newPaths, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.PR_TRIGGER__PATHS, newPaths,
					newPaths));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case AzuredevopsMMPackage.PR_TRIGGER__BRANCHES:
			return basicSetBranches(null, msgs);
		case AzuredevopsMMPackage.PR_TRIGGER__PATHS:
			return basicSetPaths(null, msgs);
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
		case AzuredevopsMMPackage.PR_TRIGGER__DISABLED:
			return getDisabled();
		case AzuredevopsMMPackage.PR_TRIGGER__AUTO_CANCEL:
			return getAutoCancel();
		case AzuredevopsMMPackage.PR_TRIGGER__DRAFTS:
			return getDrafts();
		case AzuredevopsMMPackage.PR_TRIGGER__BRANCH_LIST:
			return getBranchList();
		case AzuredevopsMMPackage.PR_TRIGGER__BRANCHES:
			return getBranches();
		case AzuredevopsMMPackage.PR_TRIGGER__PATHS:
			return getPaths();
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
		case AzuredevopsMMPackage.PR_TRIGGER__DISABLED:
			setDisabled((Boolean) newValue);
			return;
		case AzuredevopsMMPackage.PR_TRIGGER__AUTO_CANCEL:
			setAutoCancel((Boolean) newValue);
			return;
		case AzuredevopsMMPackage.PR_TRIGGER__DRAFTS:
			setDrafts((Boolean) newValue);
			return;
		case AzuredevopsMMPackage.PR_TRIGGER__BRANCH_LIST:
			getBranchList().clear();
			getBranchList().addAll((Collection<? extends String>) newValue);
			return;
		case AzuredevopsMMPackage.PR_TRIGGER__BRANCHES:
			setBranches((IncludeExcludeFilters) newValue);
			return;
		case AzuredevopsMMPackage.PR_TRIGGER__PATHS:
			setPaths((IncludeExcludeFilters) newValue);
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
		case AzuredevopsMMPackage.PR_TRIGGER__DISABLED:
			setDisabled(DISABLED_EDEFAULT);
			return;
		case AzuredevopsMMPackage.PR_TRIGGER__AUTO_CANCEL:
			setAutoCancel(AUTO_CANCEL_EDEFAULT);
			return;
		case AzuredevopsMMPackage.PR_TRIGGER__DRAFTS:
			setDrafts(DRAFTS_EDEFAULT);
			return;
		case AzuredevopsMMPackage.PR_TRIGGER__BRANCH_LIST:
			getBranchList().clear();
			return;
		case AzuredevopsMMPackage.PR_TRIGGER__BRANCHES:
			setBranches((IncludeExcludeFilters) null);
			return;
		case AzuredevopsMMPackage.PR_TRIGGER__PATHS:
			setPaths((IncludeExcludeFilters) null);
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
		case AzuredevopsMMPackage.PR_TRIGGER__DISABLED:
			return DISABLED_EDEFAULT == null ? disabled != null : !DISABLED_EDEFAULT.equals(disabled);
		case AzuredevopsMMPackage.PR_TRIGGER__AUTO_CANCEL:
			return AUTO_CANCEL_EDEFAULT == null ? autoCancel != null : !AUTO_CANCEL_EDEFAULT.equals(autoCancel);
		case AzuredevopsMMPackage.PR_TRIGGER__DRAFTS:
			return DRAFTS_EDEFAULT == null ? drafts != null : !DRAFTS_EDEFAULT.equals(drafts);
		case AzuredevopsMMPackage.PR_TRIGGER__BRANCH_LIST:
			return branchList != null && !branchList.isEmpty();
		case AzuredevopsMMPackage.PR_TRIGGER__BRANCHES:
			return branches != null;
		case AzuredevopsMMPackage.PR_TRIGGER__PATHS:
			return paths != null;
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
		result.append(", autoCancel: ");
		result.append(autoCancel);
		result.append(", drafts: ");
		result.append(drafts);
		result.append(", branchList: ");
		result.append(branchList);
		result.append(')');
		return result.toString();
	}

} //PrTriggerImpl
