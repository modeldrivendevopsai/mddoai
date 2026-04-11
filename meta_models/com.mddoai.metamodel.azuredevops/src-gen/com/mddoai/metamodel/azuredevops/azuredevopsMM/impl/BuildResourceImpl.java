/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.BuildResource;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Build Resource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.BuildResourceImpl#getBuild <em>Build</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.BuildResourceImpl#getType <em>Type</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.BuildResourceImpl#getConnection <em>Connection</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.BuildResourceImpl#getSource <em>Source</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.BuildResourceImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.BuildResourceImpl#getBranch <em>Branch</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.BuildResourceImpl#getTrigger <em>Trigger</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BuildResourceImpl extends MinimalEObjectImpl.Container implements BuildResource {
	/**
	 * The default value of the '{@link #getBuild() <em>Build</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBuild()
	 * @generated
	 * @ordered
	 */
	protected static final String BUILD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBuild() <em>Build</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBuild()
	 * @generated
	 * @ordered
	 */
	protected String build = BUILD_EDEFAULT;

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
	 * The default value of the '{@link #getSource() <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected static final String SOURCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected String source = SOURCE_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getBranch() <em>Branch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBranch()
	 * @generated
	 * @ordered
	 */
	protected static final String BRANCH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBranch() <em>Branch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBranch()
	 * @generated
	 * @ordered
	 */
	protected String branch = BRANCH_EDEFAULT;

	/**
	 * The default value of the '{@link #getTrigger() <em>Trigger</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrigger()
	 * @generated
	 * @ordered
	 */
	protected static final String TRIGGER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTrigger() <em>Trigger</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrigger()
	 * @generated
	 * @ordered
	 */
	protected String trigger = TRIGGER_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BuildResourceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AzuredevopsMMPackage.Literals.BUILD_RESOURCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getBuild() {
		return build;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBuild(String newBuild) {
		String oldBuild = build;
		build = newBuild;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.BUILD_RESOURCE__BUILD, oldBuild,
					build));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.BUILD_RESOURCE__TYPE, oldType,
					type));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.BUILD_RESOURCE__CONNECTION,
					oldConnection, connection));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSource(String newSource) {
		String oldSource = source;
		source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.BUILD_RESOURCE__SOURCE,
					oldSource, source));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.BUILD_RESOURCE__VERSION,
					oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getBranch() {
		return branch;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBranch(String newBranch) {
		String oldBranch = branch;
		branch = newBranch;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.BUILD_RESOURCE__BRANCH,
					oldBranch, branch));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTrigger() {
		return trigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTrigger(String newTrigger) {
		String oldTrigger = trigger;
		trigger = newTrigger;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.BUILD_RESOURCE__TRIGGER,
					oldTrigger, trigger));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case AzuredevopsMMPackage.BUILD_RESOURCE__BUILD:
			return getBuild();
		case AzuredevopsMMPackage.BUILD_RESOURCE__TYPE:
			return getType();
		case AzuredevopsMMPackage.BUILD_RESOURCE__CONNECTION:
			return getConnection();
		case AzuredevopsMMPackage.BUILD_RESOURCE__SOURCE:
			return getSource();
		case AzuredevopsMMPackage.BUILD_RESOURCE__VERSION:
			return getVersion();
		case AzuredevopsMMPackage.BUILD_RESOURCE__BRANCH:
			return getBranch();
		case AzuredevopsMMPackage.BUILD_RESOURCE__TRIGGER:
			return getTrigger();
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
		case AzuredevopsMMPackage.BUILD_RESOURCE__BUILD:
			setBuild((String) newValue);
			return;
		case AzuredevopsMMPackage.BUILD_RESOURCE__TYPE:
			setType((String) newValue);
			return;
		case AzuredevopsMMPackage.BUILD_RESOURCE__CONNECTION:
			setConnection((String) newValue);
			return;
		case AzuredevopsMMPackage.BUILD_RESOURCE__SOURCE:
			setSource((String) newValue);
			return;
		case AzuredevopsMMPackage.BUILD_RESOURCE__VERSION:
			setVersion((String) newValue);
			return;
		case AzuredevopsMMPackage.BUILD_RESOURCE__BRANCH:
			setBranch((String) newValue);
			return;
		case AzuredevopsMMPackage.BUILD_RESOURCE__TRIGGER:
			setTrigger((String) newValue);
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
		case AzuredevopsMMPackage.BUILD_RESOURCE__BUILD:
			setBuild(BUILD_EDEFAULT);
			return;
		case AzuredevopsMMPackage.BUILD_RESOURCE__TYPE:
			setType(TYPE_EDEFAULT);
			return;
		case AzuredevopsMMPackage.BUILD_RESOURCE__CONNECTION:
			setConnection(CONNECTION_EDEFAULT);
			return;
		case AzuredevopsMMPackage.BUILD_RESOURCE__SOURCE:
			setSource(SOURCE_EDEFAULT);
			return;
		case AzuredevopsMMPackage.BUILD_RESOURCE__VERSION:
			setVersion(VERSION_EDEFAULT);
			return;
		case AzuredevopsMMPackage.BUILD_RESOURCE__BRANCH:
			setBranch(BRANCH_EDEFAULT);
			return;
		case AzuredevopsMMPackage.BUILD_RESOURCE__TRIGGER:
			setTrigger(TRIGGER_EDEFAULT);
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
		case AzuredevopsMMPackage.BUILD_RESOURCE__BUILD:
			return BUILD_EDEFAULT == null ? build != null : !BUILD_EDEFAULT.equals(build);
		case AzuredevopsMMPackage.BUILD_RESOURCE__TYPE:
			return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
		case AzuredevopsMMPackage.BUILD_RESOURCE__CONNECTION:
			return CONNECTION_EDEFAULT == null ? connection != null : !CONNECTION_EDEFAULT.equals(connection);
		case AzuredevopsMMPackage.BUILD_RESOURCE__SOURCE:
			return SOURCE_EDEFAULT == null ? source != null : !SOURCE_EDEFAULT.equals(source);
		case AzuredevopsMMPackage.BUILD_RESOURCE__VERSION:
			return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
		case AzuredevopsMMPackage.BUILD_RESOURCE__BRANCH:
			return BRANCH_EDEFAULT == null ? branch != null : !BRANCH_EDEFAULT.equals(branch);
		case AzuredevopsMMPackage.BUILD_RESOURCE__TRIGGER:
			return TRIGGER_EDEFAULT == null ? trigger != null : !TRIGGER_EDEFAULT.equals(trigger);
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
		result.append(" (build: ");
		result.append(build);
		result.append(", type: ");
		result.append(type);
		result.append(", connection: ");
		result.append(connection);
		result.append(", source: ");
		result.append(source);
		result.append(", version: ");
		result.append(version);
		result.append(", branch: ");
		result.append(branch);
		result.append(", trigger: ");
		result.append(trigger);
		result.append(')');
		return result.toString();
	}

} //BuildResourceImpl
