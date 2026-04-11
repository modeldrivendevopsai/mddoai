/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResourceTrigger;
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
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Container Resource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerResourceImpl#getContainer <em>Container</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerResourceImpl#getImage <em>Image</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerResourceImpl#getType <em>Type</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerResourceImpl#getAzureSubscription <em>Azure Subscription</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerResourceImpl#getResourceGroup <em>Resource Group</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerResourceImpl#getRegistry <em>Registry</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerResourceImpl#getRepository <em>Repository</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerResourceImpl#getEndpoint <em>Endpoint</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerResourceImpl#getLocalImage <em>Local Image</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerResourceImpl#getOptions <em>Options</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerResourceImpl#getPorts <em>Ports</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerResourceImpl#getVolumes <em>Volumes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerResourceImpl#getMapDockerSocket <em>Map Docker Socket</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerResourceImpl#getEnv <em>Env</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerResourceImpl#getMountReadOnly <em>Mount Read Only</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ContainerResourceImpl#getTrigger <em>Trigger</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ContainerResourceImpl extends MinimalEObjectImpl.Container implements ContainerResource {
	/**
	 * The default value of the '{@link #getContainer() <em>Container</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainer()
	 * @generated
	 * @ordered
	 */
	protected static final String CONTAINER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getContainer() <em>Container</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainer()
	 * @generated
	 * @ordered
	 */
	protected String container = CONTAINER_EDEFAULT;

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
	 * The default value of the '{@link #getAzureSubscription() <em>Azure Subscription</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAzureSubscription()
	 * @generated
	 * @ordered
	 */
	protected static final String AZURE_SUBSCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAzureSubscription() <em>Azure Subscription</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAzureSubscription()
	 * @generated
	 * @ordered
	 */
	protected String azureSubscription = AZURE_SUBSCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getResourceGroup() <em>Resource Group</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceGroup()
	 * @generated
	 * @ordered
	 */
	protected static final String RESOURCE_GROUP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResourceGroup() <em>Resource Group</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceGroup()
	 * @generated
	 * @ordered
	 */
	protected String resourceGroup = RESOURCE_GROUP_EDEFAULT;

	/**
	 * The default value of the '{@link #getRegistry() <em>Registry</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRegistry()
	 * @generated
	 * @ordered
	 */
	protected static final String REGISTRY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRegistry() <em>Registry</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRegistry()
	 * @generated
	 * @ordered
	 */
	protected String registry = REGISTRY_EDEFAULT;

	/**
	 * The default value of the '{@link #getRepository() <em>Repository</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepository()
	 * @generated
	 * @ordered
	 */
	protected static final String REPOSITORY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRepository() <em>Repository</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepository()
	 * @generated
	 * @ordered
	 */
	protected String repository = REPOSITORY_EDEFAULT;

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
	 * The default value of the '{@link #getLocalImage() <em>Local Image</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocalImage()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean LOCAL_IMAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLocalImage() <em>Local Image</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocalImage()
	 * @generated
	 * @ordered
	 */
	protected Boolean localImage = LOCAL_IMAGE_EDEFAULT;

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
	 * The cached value of the '{@link #getTrigger() <em>Trigger</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrigger()
	 * @generated
	 * @ordered
	 */
	protected ContainerResourceTrigger trigger;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContainerResourceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AzuredevopsMMPackage.Literals.CONTAINER_RESOURCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getContainer() {
		return container;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setContainer(String newContainer) {
		String oldContainer = container;
		container = newContainer;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.CONTAINER_RESOURCE__CONTAINER,
					oldContainer, container));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.CONTAINER_RESOURCE__IMAGE,
					oldImage, image));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.CONTAINER_RESOURCE__TYPE,
					oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getAzureSubscription() {
		return azureSubscription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAzureSubscription(String newAzureSubscription) {
		String oldAzureSubscription = azureSubscription;
		azureSubscription = newAzureSubscription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.CONTAINER_RESOURCE__AZURE_SUBSCRIPTION, oldAzureSubscription,
					azureSubscription));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getResourceGroup() {
		return resourceGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setResourceGroup(String newResourceGroup) {
		String oldResourceGroup = resourceGroup;
		resourceGroup = newResourceGroup;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.CONTAINER_RESOURCE__RESOURCE_GROUP, oldResourceGroup, resourceGroup));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getRegistry() {
		return registry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRegistry(String newRegistry) {
		String oldRegistry = registry;
		registry = newRegistry;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.CONTAINER_RESOURCE__REGISTRY,
					oldRegistry, registry));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getRepository() {
		return repository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRepository(String newRepository) {
		String oldRepository = repository;
		repository = newRepository;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.CONTAINER_RESOURCE__REPOSITORY,
					oldRepository, repository));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.CONTAINER_RESOURCE__ENDPOINT,
					oldEndpoint, endpoint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getLocalImage() {
		return localImage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLocalImage(Boolean newLocalImage) {
		Boolean oldLocalImage = localImage;
		localImage = newLocalImage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.CONTAINER_RESOURCE__LOCAL_IMAGE,
					oldLocalImage, localImage));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.CONTAINER_RESOURCE__OPTIONS,
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
			ports = new EDataTypeUniqueEList<String>(String.class, this,
					AzuredevopsMMPackage.CONTAINER_RESOURCE__PORTS);
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
					AzuredevopsMMPackage.CONTAINER_RESOURCE__VOLUMES);
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
					AzuredevopsMMPackage.CONTAINER_RESOURCE__MAP_DOCKER_SOCKET, oldMapDockerSocket, mapDockerSocket));
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
					AzuredevopsMMPackage.CONTAINER_RESOURCE__ENV);
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
					AzuredevopsMMPackage.CONTAINER_RESOURCE__MOUNT_READ_ONLY, oldMountReadOnly, newMountReadOnly);
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
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.CONTAINER_RESOURCE__MOUNT_READ_ONLY, null, msgs);
			if (newMountReadOnly != null)
				msgs = ((InternalEObject) newMountReadOnly).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.CONTAINER_RESOURCE__MOUNT_READ_ONLY, null, msgs);
			msgs = basicSetMountReadOnly(newMountReadOnly, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.CONTAINER_RESOURCE__MOUNT_READ_ONLY, newMountReadOnly, newMountReadOnly));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ContainerResourceTrigger getTrigger() {
		return trigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTrigger(ContainerResourceTrigger newTrigger, NotificationChain msgs) {
		ContainerResourceTrigger oldTrigger = trigger;
		trigger = newTrigger;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.CONTAINER_RESOURCE__TRIGGER, oldTrigger, newTrigger);
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
	public void setTrigger(ContainerResourceTrigger newTrigger) {
		if (newTrigger != trigger) {
			NotificationChain msgs = null;
			if (trigger != null)
				msgs = ((InternalEObject) trigger).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.CONTAINER_RESOURCE__TRIGGER, null, msgs);
			if (newTrigger != null)
				msgs = ((InternalEObject) newTrigger).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.CONTAINER_RESOURCE__TRIGGER, null, msgs);
			msgs = basicSetTrigger(newTrigger, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.CONTAINER_RESOURCE__TRIGGER,
					newTrigger, newTrigger));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__ENV:
			return ((InternalEList<?>) getEnv()).basicRemove(otherEnd, msgs);
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__MOUNT_READ_ONLY:
			return basicSetMountReadOnly(null, msgs);
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__TRIGGER:
			return basicSetTrigger(null, msgs);
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
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__CONTAINER:
			return getContainer();
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__IMAGE:
			return getImage();
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__TYPE:
			return getType();
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__AZURE_SUBSCRIPTION:
			return getAzureSubscription();
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__RESOURCE_GROUP:
			return getResourceGroup();
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__REGISTRY:
			return getRegistry();
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__REPOSITORY:
			return getRepository();
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__ENDPOINT:
			return getEndpoint();
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__LOCAL_IMAGE:
			return getLocalImage();
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__OPTIONS:
			return getOptions();
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__PORTS:
			return getPorts();
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__VOLUMES:
			return getVolumes();
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__MAP_DOCKER_SOCKET:
			return getMapDockerSocket();
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__ENV:
			if (coreType)
				return getEnv();
			else
				return getEnv().map();
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__MOUNT_READ_ONLY:
			return getMountReadOnly();
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__TRIGGER:
			return getTrigger();
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
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__CONTAINER:
			setContainer((String) newValue);
			return;
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__IMAGE:
			setImage((String) newValue);
			return;
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__TYPE:
			setType((String) newValue);
			return;
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__AZURE_SUBSCRIPTION:
			setAzureSubscription((String) newValue);
			return;
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__RESOURCE_GROUP:
			setResourceGroup((String) newValue);
			return;
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__REGISTRY:
			setRegistry((String) newValue);
			return;
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__REPOSITORY:
			setRepository((String) newValue);
			return;
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__ENDPOINT:
			setEndpoint((String) newValue);
			return;
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__LOCAL_IMAGE:
			setLocalImage((Boolean) newValue);
			return;
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__OPTIONS:
			setOptions((String) newValue);
			return;
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__PORTS:
			getPorts().clear();
			getPorts().addAll((Collection<? extends String>) newValue);
			return;
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__VOLUMES:
			getVolumes().clear();
			getVolumes().addAll((Collection<? extends String>) newValue);
			return;
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__MAP_DOCKER_SOCKET:
			setMapDockerSocket((Boolean) newValue);
			return;
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__ENV:
			((EStructuralFeature.Setting) getEnv()).set(newValue);
			return;
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__MOUNT_READ_ONLY:
			setMountReadOnly((MountReadOnly) newValue);
			return;
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__TRIGGER:
			setTrigger((ContainerResourceTrigger) newValue);
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
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__CONTAINER:
			setContainer(CONTAINER_EDEFAULT);
			return;
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__IMAGE:
			setImage(IMAGE_EDEFAULT);
			return;
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__TYPE:
			setType(TYPE_EDEFAULT);
			return;
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__AZURE_SUBSCRIPTION:
			setAzureSubscription(AZURE_SUBSCRIPTION_EDEFAULT);
			return;
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__RESOURCE_GROUP:
			setResourceGroup(RESOURCE_GROUP_EDEFAULT);
			return;
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__REGISTRY:
			setRegistry(REGISTRY_EDEFAULT);
			return;
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__REPOSITORY:
			setRepository(REPOSITORY_EDEFAULT);
			return;
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__ENDPOINT:
			setEndpoint(ENDPOINT_EDEFAULT);
			return;
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__LOCAL_IMAGE:
			setLocalImage(LOCAL_IMAGE_EDEFAULT);
			return;
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__OPTIONS:
			setOptions(OPTIONS_EDEFAULT);
			return;
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__PORTS:
			getPorts().clear();
			return;
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__VOLUMES:
			getVolumes().clear();
			return;
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__MAP_DOCKER_SOCKET:
			setMapDockerSocket(MAP_DOCKER_SOCKET_EDEFAULT);
			return;
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__ENV:
			getEnv().clear();
			return;
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__MOUNT_READ_ONLY:
			setMountReadOnly((MountReadOnly) null);
			return;
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__TRIGGER:
			setTrigger((ContainerResourceTrigger) null);
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
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__CONTAINER:
			return CONTAINER_EDEFAULT == null ? container != null : !CONTAINER_EDEFAULT.equals(container);
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__IMAGE:
			return IMAGE_EDEFAULT == null ? image != null : !IMAGE_EDEFAULT.equals(image);
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__TYPE:
			return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__AZURE_SUBSCRIPTION:
			return AZURE_SUBSCRIPTION_EDEFAULT == null ? azureSubscription != null
					: !AZURE_SUBSCRIPTION_EDEFAULT.equals(azureSubscription);
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__RESOURCE_GROUP:
			return RESOURCE_GROUP_EDEFAULT == null ? resourceGroup != null
					: !RESOURCE_GROUP_EDEFAULT.equals(resourceGroup);
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__REGISTRY:
			return REGISTRY_EDEFAULT == null ? registry != null : !REGISTRY_EDEFAULT.equals(registry);
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__REPOSITORY:
			return REPOSITORY_EDEFAULT == null ? repository != null : !REPOSITORY_EDEFAULT.equals(repository);
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__ENDPOINT:
			return ENDPOINT_EDEFAULT == null ? endpoint != null : !ENDPOINT_EDEFAULT.equals(endpoint);
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__LOCAL_IMAGE:
			return LOCAL_IMAGE_EDEFAULT == null ? localImage != null : !LOCAL_IMAGE_EDEFAULT.equals(localImage);
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__OPTIONS:
			return OPTIONS_EDEFAULT == null ? options != null : !OPTIONS_EDEFAULT.equals(options);
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__PORTS:
			return ports != null && !ports.isEmpty();
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__VOLUMES:
			return volumes != null && !volumes.isEmpty();
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__MAP_DOCKER_SOCKET:
			return MAP_DOCKER_SOCKET_EDEFAULT == null ? mapDockerSocket != null
					: !MAP_DOCKER_SOCKET_EDEFAULT.equals(mapDockerSocket);
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__ENV:
			return env != null && !env.isEmpty();
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__MOUNT_READ_ONLY:
			return mountReadOnly != null;
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__TRIGGER:
			return trigger != null;
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
		result.append(" (container: ");
		result.append(container);
		result.append(", image: ");
		result.append(image);
		result.append(", type: ");
		result.append(type);
		result.append(", azureSubscription: ");
		result.append(azureSubscription);
		result.append(", resourceGroup: ");
		result.append(resourceGroup);
		result.append(", registry: ");
		result.append(registry);
		result.append(", repository: ");
		result.append(repository);
		result.append(", endpoint: ");
		result.append(endpoint);
		result.append(", localImage: ");
		result.append(localImage);
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

} //ContainerResourceImpl
