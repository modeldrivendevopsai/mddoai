/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.PackageResource;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Package Resource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PackageResourceImpl#getPackageName <em>Package Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PackageResourceImpl#getType <em>Type</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PackageResourceImpl#getConnection <em>Connection</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PackageResourceImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PackageResourceImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PackageResourceImpl#getTag <em>Tag</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PackageResourceImpl#getTrigger <em>Trigger</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PackageResourceImpl extends MinimalEObjectImpl.Container implements PackageResource {
	/**
	 * The default value of the '{@link #getPackageName() <em>Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackageName()
	 * @generated
	 * @ordered
	 */
	protected static final String PACKAGE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPackageName() <em>Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackageName()
	 * @generated
	 * @ordered
	 */
	protected String packageName = PACKAGE_NAME_EDEFAULT;

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
	 * The default value of the '{@link #getTag() <em>Tag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTag()
	 * @generated
	 * @ordered
	 */
	protected static final String TAG_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTag() <em>Tag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTag()
	 * @generated
	 * @ordered
	 */
	protected String tag = TAG_EDEFAULT;

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
	protected PackageResourceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AzuredevopsMMPackage.Literals.PACKAGE_RESOURCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPackageName() {
		return packageName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPackageName(String newPackageName) {
		String oldPackageName = packageName;
		packageName = newPackageName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.PACKAGE_RESOURCE__PACKAGE_NAME,
					oldPackageName, packageName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.PACKAGE_RESOURCE__TYPE, oldType,
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
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.PACKAGE_RESOURCE__CONNECTION,
					oldConnection, connection));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.PACKAGE_RESOURCE__NAME, oldName,
					name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.PACKAGE_RESOURCE__VERSION,
					oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTag() {
		return tag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTag(String newTag) {
		String oldTag = tag;
		tag = newTag;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.PACKAGE_RESOURCE__TAG, oldTag,
					tag));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.PACKAGE_RESOURCE__TRIGGER,
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
		case AzuredevopsMMPackage.PACKAGE_RESOURCE__PACKAGE_NAME:
			return getPackageName();
		case AzuredevopsMMPackage.PACKAGE_RESOURCE__TYPE:
			return getType();
		case AzuredevopsMMPackage.PACKAGE_RESOURCE__CONNECTION:
			return getConnection();
		case AzuredevopsMMPackage.PACKAGE_RESOURCE__NAME:
			return getName();
		case AzuredevopsMMPackage.PACKAGE_RESOURCE__VERSION:
			return getVersion();
		case AzuredevopsMMPackage.PACKAGE_RESOURCE__TAG:
			return getTag();
		case AzuredevopsMMPackage.PACKAGE_RESOURCE__TRIGGER:
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
		case AzuredevopsMMPackage.PACKAGE_RESOURCE__PACKAGE_NAME:
			setPackageName((String) newValue);
			return;
		case AzuredevopsMMPackage.PACKAGE_RESOURCE__TYPE:
			setType((String) newValue);
			return;
		case AzuredevopsMMPackage.PACKAGE_RESOURCE__CONNECTION:
			setConnection((String) newValue);
			return;
		case AzuredevopsMMPackage.PACKAGE_RESOURCE__NAME:
			setName((String) newValue);
			return;
		case AzuredevopsMMPackage.PACKAGE_RESOURCE__VERSION:
			setVersion((String) newValue);
			return;
		case AzuredevopsMMPackage.PACKAGE_RESOURCE__TAG:
			setTag((String) newValue);
			return;
		case AzuredevopsMMPackage.PACKAGE_RESOURCE__TRIGGER:
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
		case AzuredevopsMMPackage.PACKAGE_RESOURCE__PACKAGE_NAME:
			setPackageName(PACKAGE_NAME_EDEFAULT);
			return;
		case AzuredevopsMMPackage.PACKAGE_RESOURCE__TYPE:
			setType(TYPE_EDEFAULT);
			return;
		case AzuredevopsMMPackage.PACKAGE_RESOURCE__CONNECTION:
			setConnection(CONNECTION_EDEFAULT);
			return;
		case AzuredevopsMMPackage.PACKAGE_RESOURCE__NAME:
			setName(NAME_EDEFAULT);
			return;
		case AzuredevopsMMPackage.PACKAGE_RESOURCE__VERSION:
			setVersion(VERSION_EDEFAULT);
			return;
		case AzuredevopsMMPackage.PACKAGE_RESOURCE__TAG:
			setTag(TAG_EDEFAULT);
			return;
		case AzuredevopsMMPackage.PACKAGE_RESOURCE__TRIGGER:
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
		case AzuredevopsMMPackage.PACKAGE_RESOURCE__PACKAGE_NAME:
			return PACKAGE_NAME_EDEFAULT == null ? packageName != null : !PACKAGE_NAME_EDEFAULT.equals(packageName);
		case AzuredevopsMMPackage.PACKAGE_RESOURCE__TYPE:
			return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
		case AzuredevopsMMPackage.PACKAGE_RESOURCE__CONNECTION:
			return CONNECTION_EDEFAULT == null ? connection != null : !CONNECTION_EDEFAULT.equals(connection);
		case AzuredevopsMMPackage.PACKAGE_RESOURCE__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case AzuredevopsMMPackage.PACKAGE_RESOURCE__VERSION:
			return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
		case AzuredevopsMMPackage.PACKAGE_RESOURCE__TAG:
			return TAG_EDEFAULT == null ? tag != null : !TAG_EDEFAULT.equals(tag);
		case AzuredevopsMMPackage.PACKAGE_RESOURCE__TRIGGER:
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
		result.append(" (packageName: ");
		result.append(packageName);
		result.append(", type: ");
		result.append(type);
		result.append(", connection: ");
		result.append(connection);
		result.append(", name: ");
		result.append(name);
		result.append(", version: ");
		result.append(version);
		result.append(", tag: ");
		result.append(tag);
		result.append(", trigger: ");
		result.append(trigger);
		result.append(')');
		return result.toString();
	}

} //PackageResourceImpl
