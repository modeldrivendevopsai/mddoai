/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.MountReadOnly;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Container Image</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerImageImpl#getImage <em>Image</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerImageImpl#getEndpoint <em>Endpoint</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerImageImpl#getOptions <em>Options</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerImageImpl#getPorts <em>Ports</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerImageImpl#getVolumes <em>Volumes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerImageImpl#getMapDockerSocket <em>Map Docker Socket</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerImageImpl#getEnv <em>Env</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerImageImpl#getMountReadOnly <em>Mount Read Only</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ContainerImageImpl extends ContainerReferenceImpl implements ContainerImage {
	/**
	 * The default value of the '{@link #getImage() <em>Image</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImage()
	 * @generated
	 * @ordered
	 */
	protected static final String IMAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getImage() <em>Image</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImage()
	 * @generated
	 * @ordered
	 */
	protected String image = IMAGE_EDEFAULT;

	/**
	 * The default value of the '{@link #getEndpoint() <em>Endpoint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndpoint()
	 * @generated
	 * @ordered
	 */
	protected static final String ENDPOINT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEndpoint() <em>Endpoint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndpoint()
	 * @generated
	 * @ordered
	 */
	protected String endpoint = ENDPOINT_EDEFAULT;

	/**
	 * The default value of the '{@link #getOptions() <em>Options</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOptions()
	 * @generated
	 * @ordered
	 */
	protected static final String OPTIONS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOptions() <em>Options</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOptions()
	 * @generated
	 * @ordered
	 */
	protected String options = OPTIONS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPorts() <em>Ports</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPorts()
	 * @generated
	 * @ordered
	 */
	protected EList<String> ports;

	/**
	 * The cached value of the '{@link #getVolumes() <em>Volumes</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVolumes()
	 * @generated
	 * @ordered
	 */
	protected EList<String> volumes;

	/**
	 * The default value of the '{@link #getMapDockerSocket() <em>Map Docker Socket</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMapDockerSocket()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean MAP_DOCKER_SOCKET_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMapDockerSocket() <em>Map Docker Socket</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMapDockerSocket()
	 * @generated
	 * @ordered
	 */
	protected Boolean mapDockerSocket = MAP_DOCKER_SOCKET_EDEFAULT;

	/**
	 * The cached value of the '{@link #getEnv() <em>Env</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnv()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> env;

	/**
	 * The cached value of the '{@link #getMountReadOnly() <em>Mount Read Only</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMountReadOnly()
	 * @generated
	 * @ordered
	 */
	protected MountReadOnly mountReadOnly;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContainerImageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AzuredevopsMMPackage.Literals.CONTAINER_IMAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getImage() {
		return image;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setImage(String newImage) {
		String oldImage = image;
		image = newImage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.CONTAINER_IMAGE__IMAGE, oldImage,
					image));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getEndpoint() {
		return endpoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEndpoint(String newEndpoint) {
		String oldEndpoint = endpoint;
		endpoint = newEndpoint;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.CONTAINER_IMAGE__ENDPOINT,
					oldEndpoint, endpoint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getOptions() {
		return options;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOptions(String newOptions) {
		String oldOptions = options;
		options = newOptions;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.CONTAINER_IMAGE__OPTIONS,
					oldOptions, options));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getPorts() {
		if (ports == null) {
			ports = new EDataTypeUniqueEList<String>(String.class, this, AzuredevopsMMPackage.CONTAINER_IMAGE__PORTS);
		}
		return ports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getVolumes() {
		if (volumes == null) {
			volumes = new EDataTypeUniqueEList<String>(String.class, this,
					AzuredevopsMMPackage.CONTAINER_IMAGE__VOLUMES);
		}
		return volumes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getMapDockerSocket() {
		return mapDockerSocket;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMapDockerSocket(Boolean newMapDockerSocket) {
		Boolean oldMapDockerSocket = mapDockerSocket;
		mapDockerSocket = newMapDockerSocket;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.CONTAINER_IMAGE__MAP_DOCKER_SOCKET, oldMapDockerSocket, mapDockerSocket));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EMap<String, String> getEnv() {
		if (env == null) {
			env = new EcoreEMap<String, String>(AzuredevopsMMPackage.Literals.ENV_ENTRY, EnvEntryImpl.class, this,
					AzuredevopsMMPackage.CONTAINER_IMAGE__ENV);
		}
		return env;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MountReadOnly getMountReadOnly() {
		return mountReadOnly;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMountReadOnly(MountReadOnly newMountReadOnly, NotificationChain msgs) {
		MountReadOnly oldMountReadOnly = mountReadOnly;
		mountReadOnly = newMountReadOnly;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.CONTAINER_IMAGE__MOUNT_READ_ONLY, oldMountReadOnly, newMountReadOnly);
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
	public void setMountReadOnly(MountReadOnly newMountReadOnly) {
		if (newMountReadOnly != mountReadOnly) {
			NotificationChain msgs = null;
			if (mountReadOnly != null)
				msgs = ((InternalEObject) mountReadOnly).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.CONTAINER_IMAGE__MOUNT_READ_ONLY, null, msgs);
			if (newMountReadOnly != null)
				msgs = ((InternalEObject) newMountReadOnly).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.CONTAINER_IMAGE__MOUNT_READ_ONLY, null, msgs);
			msgs = basicSetMountReadOnly(newMountReadOnly, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.CONTAINER_IMAGE__MOUNT_READ_ONLY,
					newMountReadOnly, newMountReadOnly));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case AzuredevopsMMPackage.CONTAINER_IMAGE__ENV:
			return ((InternalEList<?>) getEnv()).basicRemove(otherEnd, msgs);
		case AzuredevopsMMPackage.CONTAINER_IMAGE__MOUNT_READ_ONLY:
			return basicSetMountReadOnly(null, msgs);
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
		case AzuredevopsMMPackage.CONTAINER_IMAGE__IMAGE:
			return getImage();
		case AzuredevopsMMPackage.CONTAINER_IMAGE__ENDPOINT:
			return getEndpoint();
		case AzuredevopsMMPackage.CONTAINER_IMAGE__OPTIONS:
			return getOptions();
		case AzuredevopsMMPackage.CONTAINER_IMAGE__PORTS:
			return getPorts();
		case AzuredevopsMMPackage.CONTAINER_IMAGE__VOLUMES:
			return getVolumes();
		case AzuredevopsMMPackage.CONTAINER_IMAGE__MAP_DOCKER_SOCKET:
			return getMapDockerSocket();
		case AzuredevopsMMPackage.CONTAINER_IMAGE__ENV:
			if (coreType)
				return getEnv();
			else
				return getEnv().map();
		case AzuredevopsMMPackage.CONTAINER_IMAGE__MOUNT_READ_ONLY:
			return getMountReadOnly();
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
		case AzuredevopsMMPackage.CONTAINER_IMAGE__IMAGE:
			setImage((String) newValue);
			return;
		case AzuredevopsMMPackage.CONTAINER_IMAGE__ENDPOINT:
			setEndpoint((String) newValue);
			return;
		case AzuredevopsMMPackage.CONTAINER_IMAGE__OPTIONS:
			setOptions((String) newValue);
			return;
		case AzuredevopsMMPackage.CONTAINER_IMAGE__PORTS:
			getPorts().clear();
			getPorts().addAll((Collection<? extends String>) newValue);
			return;
		case AzuredevopsMMPackage.CONTAINER_IMAGE__VOLUMES:
			getVolumes().clear();
			getVolumes().addAll((Collection<? extends String>) newValue);
			return;
		case AzuredevopsMMPackage.CONTAINER_IMAGE__MAP_DOCKER_SOCKET:
			setMapDockerSocket((Boolean) newValue);
			return;
		case AzuredevopsMMPackage.CONTAINER_IMAGE__ENV:
			((EStructuralFeature.Setting) getEnv()).set(newValue);
			return;
		case AzuredevopsMMPackage.CONTAINER_IMAGE__MOUNT_READ_ONLY:
			setMountReadOnly((MountReadOnly) newValue);
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
		case AzuredevopsMMPackage.CONTAINER_IMAGE__IMAGE:
			setImage(IMAGE_EDEFAULT);
			return;
		case AzuredevopsMMPackage.CONTAINER_IMAGE__ENDPOINT:
			setEndpoint(ENDPOINT_EDEFAULT);
			return;
		case AzuredevopsMMPackage.CONTAINER_IMAGE__OPTIONS:
			setOptions(OPTIONS_EDEFAULT);
			return;
		case AzuredevopsMMPackage.CONTAINER_IMAGE__PORTS:
			getPorts().clear();
			return;
		case AzuredevopsMMPackage.CONTAINER_IMAGE__VOLUMES:
			getVolumes().clear();
			return;
		case AzuredevopsMMPackage.CONTAINER_IMAGE__MAP_DOCKER_SOCKET:
			setMapDockerSocket(MAP_DOCKER_SOCKET_EDEFAULT);
			return;
		case AzuredevopsMMPackage.CONTAINER_IMAGE__ENV:
			getEnv().clear();
			return;
		case AzuredevopsMMPackage.CONTAINER_IMAGE__MOUNT_READ_ONLY:
			setMountReadOnly((MountReadOnly) null);
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
		case AzuredevopsMMPackage.CONTAINER_IMAGE__IMAGE:
			return IMAGE_EDEFAULT == null ? image != null : !IMAGE_EDEFAULT.equals(image);
		case AzuredevopsMMPackage.CONTAINER_IMAGE__ENDPOINT:
			return ENDPOINT_EDEFAULT == null ? endpoint != null : !ENDPOINT_EDEFAULT.equals(endpoint);
		case AzuredevopsMMPackage.CONTAINER_IMAGE__OPTIONS:
			return OPTIONS_EDEFAULT == null ? options != null : !OPTIONS_EDEFAULT.equals(options);
		case AzuredevopsMMPackage.CONTAINER_IMAGE__PORTS:
			return ports != null && !ports.isEmpty();
		case AzuredevopsMMPackage.CONTAINER_IMAGE__VOLUMES:
			return volumes != null && !volumes.isEmpty();
		case AzuredevopsMMPackage.CONTAINER_IMAGE__MAP_DOCKER_SOCKET:
			return MAP_DOCKER_SOCKET_EDEFAULT == null ? mapDockerSocket != null
					: !MAP_DOCKER_SOCKET_EDEFAULT.equals(mapDockerSocket);
		case AzuredevopsMMPackage.CONTAINER_IMAGE__ENV:
			return env != null && !env.isEmpty();
		case AzuredevopsMMPackage.CONTAINER_IMAGE__MOUNT_READ_ONLY:
			return mountReadOnly != null;
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
		result.append(" (image: ");
		result.append(image);
		result.append(", endpoint: ");
		result.append(endpoint);
		result.append(", options: ");
		result.append(options);
		result.append(", ports: ");
		result.append(ports);
		result.append(", volumes: ");
		result.append(volumes);
		result.append(", mapDockerSocket: ");
		result.append(mapDockerSocket);
		result.append(')');
		return result.toString();
	}

} //ContainerImageImpl
