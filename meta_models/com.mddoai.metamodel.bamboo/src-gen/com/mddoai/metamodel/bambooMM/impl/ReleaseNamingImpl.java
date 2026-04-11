/**
 */
package com.mddoai.metamodel.bambooMM.impl;

import com.mddoai.metamodel.bambooMM.BambooPackage;
import com.mddoai.metamodel.bambooMM.ReleaseNaming;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Release Naming</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.ReleaseNamingImpl#getNextVersionName <em>Next Version Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.ReleaseNamingImpl#getAppliesToBranches <em>Applies To Branches</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.ReleaseNamingImpl#getAutoIncrement <em>Auto Increment</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.ReleaseNamingImpl#getAutoIncrementVariables <em>Auto Increment Variables</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ReleaseNamingImpl extends MinimalEObjectImpl.Container implements ReleaseNaming {
	/**
	 * The default value of the '{@link #getNextVersionName() <em>Next Version Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNextVersionName()
	 * @generated
	 * @ordered
	 */
	protected static final String NEXT_VERSION_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNextVersionName() <em>Next Version Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNextVersionName()
	 * @generated
	 * @ordered
	 */
	protected String nextVersionName = NEXT_VERSION_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getAppliesToBranches() <em>Applies To Branches</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAppliesToBranches()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean APPLIES_TO_BRANCHES_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAppliesToBranches() <em>Applies To Branches</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAppliesToBranches()
	 * @generated
	 * @ordered
	 */
	protected Boolean appliesToBranches = APPLIES_TO_BRANCHES_EDEFAULT;

	/**
	 * The default value of the '{@link #getAutoIncrement() <em>Auto Increment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAutoIncrement()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean AUTO_INCREMENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAutoIncrement() <em>Auto Increment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAutoIncrement()
	 * @generated
	 * @ordered
	 */
	protected Boolean autoIncrement = AUTO_INCREMENT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAutoIncrementVariables() <em>Auto Increment Variables</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAutoIncrementVariables()
	 * @generated
	 * @ordered
	 */
	protected EList<String> autoIncrementVariables;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReleaseNamingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BambooPackage.Literals.RELEASE_NAMING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getNextVersionName() {
		return nextVersionName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNextVersionName(String newNextVersionName) {
		String oldNextVersionName = nextVersionName;
		nextVersionName = newNextVersionName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.RELEASE_NAMING__NEXT_VERSION_NAME,
					oldNextVersionName, nextVersionName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getAppliesToBranches() {
		return appliesToBranches;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAppliesToBranches(Boolean newAppliesToBranches) {
		Boolean oldAppliesToBranches = appliesToBranches;
		appliesToBranches = newAppliesToBranches;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.RELEASE_NAMING__APPLIES_TO_BRANCHES,
					oldAppliesToBranches, appliesToBranches));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getAutoIncrement() {
		return autoIncrement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAutoIncrement(Boolean newAutoIncrement) {
		Boolean oldAutoIncrement = autoIncrement;
		autoIncrement = newAutoIncrement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.RELEASE_NAMING__AUTO_INCREMENT,
					oldAutoIncrement, autoIncrement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getAutoIncrementVariables() {
		if (autoIncrementVariables == null) {
			autoIncrementVariables = new EDataTypeUniqueEList<String>(String.class, this,
					BambooPackage.RELEASE_NAMING__AUTO_INCREMENT_VARIABLES);
		}
		return autoIncrementVariables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case BambooPackage.RELEASE_NAMING__NEXT_VERSION_NAME:
			return getNextVersionName();
		case BambooPackage.RELEASE_NAMING__APPLIES_TO_BRANCHES:
			return getAppliesToBranches();
		case BambooPackage.RELEASE_NAMING__AUTO_INCREMENT:
			return getAutoIncrement();
		case BambooPackage.RELEASE_NAMING__AUTO_INCREMENT_VARIABLES:
			return getAutoIncrementVariables();
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
		case BambooPackage.RELEASE_NAMING__NEXT_VERSION_NAME:
			setNextVersionName((String) newValue);
			return;
		case BambooPackage.RELEASE_NAMING__APPLIES_TO_BRANCHES:
			setAppliesToBranches((Boolean) newValue);
			return;
		case BambooPackage.RELEASE_NAMING__AUTO_INCREMENT:
			setAutoIncrement((Boolean) newValue);
			return;
		case BambooPackage.RELEASE_NAMING__AUTO_INCREMENT_VARIABLES:
			getAutoIncrementVariables().clear();
			getAutoIncrementVariables().addAll((Collection<? extends String>) newValue);
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
		case BambooPackage.RELEASE_NAMING__NEXT_VERSION_NAME:
			setNextVersionName(NEXT_VERSION_NAME_EDEFAULT);
			return;
		case BambooPackage.RELEASE_NAMING__APPLIES_TO_BRANCHES:
			setAppliesToBranches(APPLIES_TO_BRANCHES_EDEFAULT);
			return;
		case BambooPackage.RELEASE_NAMING__AUTO_INCREMENT:
			setAutoIncrement(AUTO_INCREMENT_EDEFAULT);
			return;
		case BambooPackage.RELEASE_NAMING__AUTO_INCREMENT_VARIABLES:
			getAutoIncrementVariables().clear();
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
		case BambooPackage.RELEASE_NAMING__NEXT_VERSION_NAME:
			return NEXT_VERSION_NAME_EDEFAULT == null ? nextVersionName != null
					: !NEXT_VERSION_NAME_EDEFAULT.equals(nextVersionName);
		case BambooPackage.RELEASE_NAMING__APPLIES_TO_BRANCHES:
			return APPLIES_TO_BRANCHES_EDEFAULT == null ? appliesToBranches != null
					: !APPLIES_TO_BRANCHES_EDEFAULT.equals(appliesToBranches);
		case BambooPackage.RELEASE_NAMING__AUTO_INCREMENT:
			return AUTO_INCREMENT_EDEFAULT == null ? autoIncrement != null
					: !AUTO_INCREMENT_EDEFAULT.equals(autoIncrement);
		case BambooPackage.RELEASE_NAMING__AUTO_INCREMENT_VARIABLES:
			return autoIncrementVariables != null && !autoIncrementVariables.isEmpty();
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
		result.append(" (nextVersionName: ");
		result.append(nextVersionName);
		result.append(", appliesToBranches: ");
		result.append(appliesToBranches);
		result.append(", autoIncrement: ");
		result.append(autoIncrement);
		result.append(", autoIncrementVariables: ");
		result.append(autoIncrementVariables);
		result.append(')');
		return result.toString();
	}

} //ReleaseNamingImpl
