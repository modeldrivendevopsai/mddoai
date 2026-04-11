/**
 */
package com.mddoai.metamodel.bambooMM.impl;

import com.mddoai.metamodel.bambooMM.BambooPackage;
import com.mddoai.metamodel.bambooMM.DeploymentPermissionEntry;
import com.mddoai.metamodel.bambooMM.DeploymentProject;
import com.mddoai.metamodel.bambooMM.Environment;
import com.mddoai.metamodel.bambooMM.EnvironmentPermissionEntry;
import com.mddoai.metamodel.bambooMM.NamedEnvironmentPermission;
import com.mddoai.metamodel.bambooMM.ReleaseNaming;

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
 * An implementation of the model object '<em><b>Deployment Project</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.DeploymentProjectImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.DeploymentProjectImpl#getSourcePlan <em>Source Plan</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.DeploymentProjectImpl#getServerName <em>Server Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.DeploymentProjectImpl#getReleaseNaming <em>Release Naming</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.DeploymentProjectImpl#getEnvironments <em>Environments</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.DeploymentProjectImpl#getPermissions <em>Permissions</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.DeploymentProjectImpl#getDefaultEnvironmentPermissions <em>Default Environment Permissions</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.DeploymentProjectImpl#getEnvironmentPermissions <em>Environment Permissions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DeploymentProjectImpl extends MinimalEObjectImpl.Container implements DeploymentProject {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getSourcePlan() <em>Source Plan</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourcePlan()
	 * @generated
	 * @ordered
	 */
	protected static final String SOURCE_PLAN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSourcePlan() <em>Source Plan</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourcePlan()
	 * @generated
	 * @ordered
	 */
	protected String sourcePlan = SOURCE_PLAN_EDEFAULT;

	/**
	 * The default value of the '{@link #getServerName() <em>Server Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServerName()
	 * @generated
	 * @ordered
	 */
	protected static final String SERVER_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getServerName() <em>Server Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServerName()
	 * @generated
	 * @ordered
	 */
	protected String serverName = SERVER_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getReleaseNaming() <em>Release Naming</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReleaseNaming()
	 * @generated
	 * @ordered
	 */
	protected ReleaseNaming releaseNaming;

	/**
	 * The cached value of the '{@link #getEnvironments() <em>Environments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnvironments()
	 * @generated
	 * @ordered
	 */
	protected EList<Environment> environments;

	/**
	 * The cached value of the '{@link #getPermissions() <em>Permissions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPermissions()
	 * @generated
	 * @ordered
	 */
	protected EList<DeploymentPermissionEntry> permissions;

	/**
	 * The cached value of the '{@link #getDefaultEnvironmentPermissions() <em>Default Environment Permissions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultEnvironmentPermissions()
	 * @generated
	 * @ordered
	 */
	protected EList<EnvironmentPermissionEntry> defaultEnvironmentPermissions;

	/**
	 * The cached value of the '{@link #getEnvironmentPermissions() <em>Environment Permissions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnvironmentPermissions()
	 * @generated
	 * @ordered
	 */
	protected EList<NamedEnvironmentPermission> environmentPermissions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DeploymentProjectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BambooPackage.Literals.DEPLOYMENT_PROJECT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.DEPLOYMENT_PROJECT__NAME, oldName,
					name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSourcePlan() {
		return sourcePlan;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSourcePlan(String newSourcePlan) {
		String oldSourcePlan = sourcePlan;
		sourcePlan = newSourcePlan;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.DEPLOYMENT_PROJECT__SOURCE_PLAN,
					oldSourcePlan, sourcePlan));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getServerName() {
		return serverName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setServerName(String newServerName) {
		String oldServerName = serverName;
		serverName = newServerName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.DEPLOYMENT_PROJECT__SERVER_NAME,
					oldServerName, serverName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ReleaseNaming getReleaseNaming() {
		return releaseNaming;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReleaseNaming(ReleaseNaming newReleaseNaming, NotificationChain msgs) {
		ReleaseNaming oldReleaseNaming = releaseNaming;
		releaseNaming = newReleaseNaming;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					BambooPackage.DEPLOYMENT_PROJECT__RELEASE_NAMING, oldReleaseNaming, newReleaseNaming);
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
	public void setReleaseNaming(ReleaseNaming newReleaseNaming) {
		if (newReleaseNaming != releaseNaming) {
			NotificationChain msgs = null;
			if (releaseNaming != null)
				msgs = ((InternalEObject) releaseNaming).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - BambooPackage.DEPLOYMENT_PROJECT__RELEASE_NAMING, null, msgs);
			if (newReleaseNaming != null)
				msgs = ((InternalEObject) newReleaseNaming).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - BambooPackage.DEPLOYMENT_PROJECT__RELEASE_NAMING, null, msgs);
			msgs = basicSetReleaseNaming(newReleaseNaming, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.DEPLOYMENT_PROJECT__RELEASE_NAMING,
					newReleaseNaming, newReleaseNaming));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Environment> getEnvironments() {
		if (environments == null) {
			environments = new EObjectContainmentEList<Environment>(Environment.class, this,
					BambooPackage.DEPLOYMENT_PROJECT__ENVIRONMENTS);
		}
		return environments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<DeploymentPermissionEntry> getPermissions() {
		if (permissions == null) {
			permissions = new EObjectContainmentEList<DeploymentPermissionEntry>(DeploymentPermissionEntry.class, this,
					BambooPackage.DEPLOYMENT_PROJECT__PERMISSIONS);
		}
		return permissions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<EnvironmentPermissionEntry> getDefaultEnvironmentPermissions() {
		if (defaultEnvironmentPermissions == null) {
			defaultEnvironmentPermissions = new EObjectContainmentEList<EnvironmentPermissionEntry>(
					EnvironmentPermissionEntry.class, this,
					BambooPackage.DEPLOYMENT_PROJECT__DEFAULT_ENVIRONMENT_PERMISSIONS);
		}
		return defaultEnvironmentPermissions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<NamedEnvironmentPermission> getEnvironmentPermissions() {
		if (environmentPermissions == null) {
			environmentPermissions = new EObjectContainmentEList<NamedEnvironmentPermission>(
					NamedEnvironmentPermission.class, this, BambooPackage.DEPLOYMENT_PROJECT__ENVIRONMENT_PERMISSIONS);
		}
		return environmentPermissions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case BambooPackage.DEPLOYMENT_PROJECT__RELEASE_NAMING:
			return basicSetReleaseNaming(null, msgs);
		case BambooPackage.DEPLOYMENT_PROJECT__ENVIRONMENTS:
			return ((InternalEList<?>) getEnvironments()).basicRemove(otherEnd, msgs);
		case BambooPackage.DEPLOYMENT_PROJECT__PERMISSIONS:
			return ((InternalEList<?>) getPermissions()).basicRemove(otherEnd, msgs);
		case BambooPackage.DEPLOYMENT_PROJECT__DEFAULT_ENVIRONMENT_PERMISSIONS:
			return ((InternalEList<?>) getDefaultEnvironmentPermissions()).basicRemove(otherEnd, msgs);
		case BambooPackage.DEPLOYMENT_PROJECT__ENVIRONMENT_PERMISSIONS:
			return ((InternalEList<?>) getEnvironmentPermissions()).basicRemove(otherEnd, msgs);
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
		case BambooPackage.DEPLOYMENT_PROJECT__NAME:
			return getName();
		case BambooPackage.DEPLOYMENT_PROJECT__SOURCE_PLAN:
			return getSourcePlan();
		case BambooPackage.DEPLOYMENT_PROJECT__SERVER_NAME:
			return getServerName();
		case BambooPackage.DEPLOYMENT_PROJECT__RELEASE_NAMING:
			return getReleaseNaming();
		case BambooPackage.DEPLOYMENT_PROJECT__ENVIRONMENTS:
			return getEnvironments();
		case BambooPackage.DEPLOYMENT_PROJECT__PERMISSIONS:
			return getPermissions();
		case BambooPackage.DEPLOYMENT_PROJECT__DEFAULT_ENVIRONMENT_PERMISSIONS:
			return getDefaultEnvironmentPermissions();
		case BambooPackage.DEPLOYMENT_PROJECT__ENVIRONMENT_PERMISSIONS:
			return getEnvironmentPermissions();
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
		case BambooPackage.DEPLOYMENT_PROJECT__NAME:
			setName((String) newValue);
			return;
		case BambooPackage.DEPLOYMENT_PROJECT__SOURCE_PLAN:
			setSourcePlan((String) newValue);
			return;
		case BambooPackage.DEPLOYMENT_PROJECT__SERVER_NAME:
			setServerName((String) newValue);
			return;
		case BambooPackage.DEPLOYMENT_PROJECT__RELEASE_NAMING:
			setReleaseNaming((ReleaseNaming) newValue);
			return;
		case BambooPackage.DEPLOYMENT_PROJECT__ENVIRONMENTS:
			getEnvironments().clear();
			getEnvironments().addAll((Collection<? extends Environment>) newValue);
			return;
		case BambooPackage.DEPLOYMENT_PROJECT__PERMISSIONS:
			getPermissions().clear();
			getPermissions().addAll((Collection<? extends DeploymentPermissionEntry>) newValue);
			return;
		case BambooPackage.DEPLOYMENT_PROJECT__DEFAULT_ENVIRONMENT_PERMISSIONS:
			getDefaultEnvironmentPermissions().clear();
			getDefaultEnvironmentPermissions().addAll((Collection<? extends EnvironmentPermissionEntry>) newValue);
			return;
		case BambooPackage.DEPLOYMENT_PROJECT__ENVIRONMENT_PERMISSIONS:
			getEnvironmentPermissions().clear();
			getEnvironmentPermissions().addAll((Collection<? extends NamedEnvironmentPermission>) newValue);
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
		case BambooPackage.DEPLOYMENT_PROJECT__NAME:
			setName(NAME_EDEFAULT);
			return;
		case BambooPackage.DEPLOYMENT_PROJECT__SOURCE_PLAN:
			setSourcePlan(SOURCE_PLAN_EDEFAULT);
			return;
		case BambooPackage.DEPLOYMENT_PROJECT__SERVER_NAME:
			setServerName(SERVER_NAME_EDEFAULT);
			return;
		case BambooPackage.DEPLOYMENT_PROJECT__RELEASE_NAMING:
			setReleaseNaming((ReleaseNaming) null);
			return;
		case BambooPackage.DEPLOYMENT_PROJECT__ENVIRONMENTS:
			getEnvironments().clear();
			return;
		case BambooPackage.DEPLOYMENT_PROJECT__PERMISSIONS:
			getPermissions().clear();
			return;
		case BambooPackage.DEPLOYMENT_PROJECT__DEFAULT_ENVIRONMENT_PERMISSIONS:
			getDefaultEnvironmentPermissions().clear();
			return;
		case BambooPackage.DEPLOYMENT_PROJECT__ENVIRONMENT_PERMISSIONS:
			getEnvironmentPermissions().clear();
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
		case BambooPackage.DEPLOYMENT_PROJECT__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case BambooPackage.DEPLOYMENT_PROJECT__SOURCE_PLAN:
			return SOURCE_PLAN_EDEFAULT == null ? sourcePlan != null : !SOURCE_PLAN_EDEFAULT.equals(sourcePlan);
		case BambooPackage.DEPLOYMENT_PROJECT__SERVER_NAME:
			return SERVER_NAME_EDEFAULT == null ? serverName != null : !SERVER_NAME_EDEFAULT.equals(serverName);
		case BambooPackage.DEPLOYMENT_PROJECT__RELEASE_NAMING:
			return releaseNaming != null;
		case BambooPackage.DEPLOYMENT_PROJECT__ENVIRONMENTS:
			return environments != null && !environments.isEmpty();
		case BambooPackage.DEPLOYMENT_PROJECT__PERMISSIONS:
			return permissions != null && !permissions.isEmpty();
		case BambooPackage.DEPLOYMENT_PROJECT__DEFAULT_ENVIRONMENT_PERMISSIONS:
			return defaultEnvironmentPermissions != null && !defaultEnvironmentPermissions.isEmpty();
		case BambooPackage.DEPLOYMENT_PROJECT__ENVIRONMENT_PERMISSIONS:
			return environmentPermissions != null && !environmentPermissions.isEmpty();
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
		result.append(" (name: ");
		result.append(name);
		result.append(", sourcePlan: ");
		result.append(sourcePlan);
		result.append(", serverName: ");
		result.append(serverName);
		result.append(')');
		return result.toString();
	}

} //DeploymentProjectImpl
