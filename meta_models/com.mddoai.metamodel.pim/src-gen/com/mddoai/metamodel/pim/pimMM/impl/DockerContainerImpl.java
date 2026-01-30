/**
 */
package com.mddoai.metamodel.pim.pimMM.impl;

import com.mddoai.metamodel.pim.pimMM.DockerContainer;
import com.mddoai.metamodel.pim.pimMM.Expression;
import com.mddoai.metamodel.pim.pimMM.PimMMPackage;
import com.mddoai.metamodel.pim.pimMM.VariableDeclaration;

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

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Docker Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.DockerContainerImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.DockerContainerImpl#getImage <em>Image</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.DockerContainerImpl#getEnvironmentVariables <em>Environment Variables</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.DockerContainerImpl#getVolumes <em>Volumes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.DockerContainerImpl#getPorts <em>Ports</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.DockerContainerImpl#getOptions <em>Options</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.DockerContainerImpl#getRegistryUsername <em>Registry Username</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.DockerContainerImpl#getRegistryPassword <em>Registry Password</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.DockerContainerImpl#getNetwork <em>Network</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DockerContainerImpl extends MinimalEObjectImpl.Container implements DockerContainer {
	/**
	 * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected static final String LABEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected String label = LABEL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getImage() <em>Image</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImage()
	 * @generated
	 * @ordered
	 */
	protected Expression image;

	/**
	 * The cached value of the '{@link #getEnvironmentVariables() <em>Environment Variables</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnvironmentVariables()
	 * @generated
	 * @ordered
	 */
	protected EMap<VariableDeclaration, Expression> environmentVariables;

	/**
	 * The cached value of the '{@link #getVolumes() <em>Volumes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVolumes()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> volumes;

	/**
	 * The cached value of the '{@link #getPorts() <em>Ports</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPorts()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> ports;

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
	 * The cached value of the '{@link #getRegistryUsername() <em>Registry Username</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRegistryUsername()
	 * @generated
	 * @ordered
	 */
	protected Expression registryUsername;

	/**
	 * The cached value of the '{@link #getRegistryPassword() <em>Registry Password</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRegistryPassword()
	 * @generated
	 * @ordered
	 */
	protected Expression registryPassword;

	/**
	 * The cached value of the '{@link #getNetwork() <em>Network</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNetwork()
	 * @generated
	 * @ordered
	 */
	protected Expression network;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DockerContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PimMMPackage.Literals.DOCKER_CONTAINER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLabel() {
		return label;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLabel(String newLabel) {
		String oldLabel = label;
		label = newLabel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PimMMPackage.DOCKER_CONTAINER__LABEL, oldLabel,
					label));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getImage() {
		return image;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetImage(Expression newImage, NotificationChain msgs) {
		Expression oldImage = image;
		image = newImage;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					PimMMPackage.DOCKER_CONTAINER__IMAGE, oldImage, newImage);
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
	public void setImage(Expression newImage) {
		if (newImage != image) {
			NotificationChain msgs = null;
			if (image != null)
				msgs = ((InternalEObject) image).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - PimMMPackage.DOCKER_CONTAINER__IMAGE, null, msgs);
			if (newImage != null)
				msgs = ((InternalEObject) newImage).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - PimMMPackage.DOCKER_CONTAINER__IMAGE, null, msgs);
			msgs = basicSetImage(newImage, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PimMMPackage.DOCKER_CONTAINER__IMAGE, newImage,
					newImage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EMap<VariableDeclaration, Expression> getEnvironmentVariables() {
		if (environmentVariables == null) {
			environmentVariables = new EcoreEMap<VariableDeclaration, Expression>(PimMMPackage.Literals.ASSIGNMENT,
					AssignmentImpl.class, this, PimMMPackage.DOCKER_CONTAINER__ENVIRONMENT_VARIABLES);
		}
		return environmentVariables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Expression> getVolumes() {
		if (volumes == null) {
			volumes = new EObjectContainmentEList<Expression>(Expression.class, this,
					PimMMPackage.DOCKER_CONTAINER__VOLUMES);
		}
		return volumes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Expression> getPorts() {
		if (ports == null) {
			ports = new EObjectContainmentEList<Expression>(Expression.class, this,
					PimMMPackage.DOCKER_CONTAINER__PORTS);
		}
		return ports;
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
			eNotify(new ENotificationImpl(this, Notification.SET, PimMMPackage.DOCKER_CONTAINER__OPTIONS, oldOptions,
					options));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getRegistryUsername() {
		if (registryUsername != null && registryUsername.eIsProxy()) {
			InternalEObject oldRegistryUsername = (InternalEObject) registryUsername;
			registryUsername = (Expression) eResolveProxy(oldRegistryUsername);
			if (registryUsername != oldRegistryUsername) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							PimMMPackage.DOCKER_CONTAINER__REGISTRY_USERNAME, oldRegistryUsername, registryUsername));
			}
		}
		return registryUsername;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression basicGetRegistryUsername() {
		return registryUsername;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRegistryUsername(Expression newRegistryUsername) {
		Expression oldRegistryUsername = registryUsername;
		registryUsername = newRegistryUsername;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PimMMPackage.DOCKER_CONTAINER__REGISTRY_USERNAME,
					oldRegistryUsername, registryUsername));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getRegistryPassword() {
		if (registryPassword != null && registryPassword.eIsProxy()) {
			InternalEObject oldRegistryPassword = (InternalEObject) registryPassword;
			registryPassword = (Expression) eResolveProxy(oldRegistryPassword);
			if (registryPassword != oldRegistryPassword) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							PimMMPackage.DOCKER_CONTAINER__REGISTRY_PASSWORD, oldRegistryPassword, registryPassword));
			}
		}
		return registryPassword;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression basicGetRegistryPassword() {
		return registryPassword;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRegistryPassword(Expression newRegistryPassword) {
		Expression oldRegistryPassword = registryPassword;
		registryPassword = newRegistryPassword;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PimMMPackage.DOCKER_CONTAINER__REGISTRY_PASSWORD,
					oldRegistryPassword, registryPassword));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getNetwork() {
		if (network != null && network.eIsProxy()) {
			InternalEObject oldNetwork = (InternalEObject) network;
			network = (Expression) eResolveProxy(oldNetwork);
			if (network != oldNetwork) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PimMMPackage.DOCKER_CONTAINER__NETWORK,
							oldNetwork, network));
			}
		}
		return network;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression basicGetNetwork() {
		return network;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNetwork(Expression newNetwork) {
		Expression oldNetwork = network;
		network = newNetwork;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PimMMPackage.DOCKER_CONTAINER__NETWORK, oldNetwork,
					network));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case PimMMPackage.DOCKER_CONTAINER__IMAGE:
			return basicSetImage(null, msgs);
		case PimMMPackage.DOCKER_CONTAINER__ENVIRONMENT_VARIABLES:
			return ((InternalEList<?>) getEnvironmentVariables()).basicRemove(otherEnd, msgs);
		case PimMMPackage.DOCKER_CONTAINER__VOLUMES:
			return ((InternalEList<?>) getVolumes()).basicRemove(otherEnd, msgs);
		case PimMMPackage.DOCKER_CONTAINER__PORTS:
			return ((InternalEList<?>) getPorts()).basicRemove(otherEnd, msgs);
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
		case PimMMPackage.DOCKER_CONTAINER__LABEL:
			return getLabel();
		case PimMMPackage.DOCKER_CONTAINER__IMAGE:
			return getImage();
		case PimMMPackage.DOCKER_CONTAINER__ENVIRONMENT_VARIABLES:
			if (coreType)
				return getEnvironmentVariables();
			else
				return getEnvironmentVariables().map();
		case PimMMPackage.DOCKER_CONTAINER__VOLUMES:
			return getVolumes();
		case PimMMPackage.DOCKER_CONTAINER__PORTS:
			return getPorts();
		case PimMMPackage.DOCKER_CONTAINER__OPTIONS:
			return getOptions();
		case PimMMPackage.DOCKER_CONTAINER__REGISTRY_USERNAME:
			if (resolve)
				return getRegistryUsername();
			return basicGetRegistryUsername();
		case PimMMPackage.DOCKER_CONTAINER__REGISTRY_PASSWORD:
			if (resolve)
				return getRegistryPassword();
			return basicGetRegistryPassword();
		case PimMMPackage.DOCKER_CONTAINER__NETWORK:
			if (resolve)
				return getNetwork();
			return basicGetNetwork();
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
		case PimMMPackage.DOCKER_CONTAINER__LABEL:
			setLabel((String) newValue);
			return;
		case PimMMPackage.DOCKER_CONTAINER__IMAGE:
			setImage((Expression) newValue);
			return;
		case PimMMPackage.DOCKER_CONTAINER__ENVIRONMENT_VARIABLES:
			((EStructuralFeature.Setting) getEnvironmentVariables()).set(newValue);
			return;
		case PimMMPackage.DOCKER_CONTAINER__VOLUMES:
			getVolumes().clear();
			getVolumes().addAll((Collection<? extends Expression>) newValue);
			return;
		case PimMMPackage.DOCKER_CONTAINER__PORTS:
			getPorts().clear();
			getPorts().addAll((Collection<? extends Expression>) newValue);
			return;
		case PimMMPackage.DOCKER_CONTAINER__OPTIONS:
			setOptions((String) newValue);
			return;
		case PimMMPackage.DOCKER_CONTAINER__REGISTRY_USERNAME:
			setRegistryUsername((Expression) newValue);
			return;
		case PimMMPackage.DOCKER_CONTAINER__REGISTRY_PASSWORD:
			setRegistryPassword((Expression) newValue);
			return;
		case PimMMPackage.DOCKER_CONTAINER__NETWORK:
			setNetwork((Expression) newValue);
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
		case PimMMPackage.DOCKER_CONTAINER__LABEL:
			setLabel(LABEL_EDEFAULT);
			return;
		case PimMMPackage.DOCKER_CONTAINER__IMAGE:
			setImage((Expression) null);
			return;
		case PimMMPackage.DOCKER_CONTAINER__ENVIRONMENT_VARIABLES:
			getEnvironmentVariables().clear();
			return;
		case PimMMPackage.DOCKER_CONTAINER__VOLUMES:
			getVolumes().clear();
			return;
		case PimMMPackage.DOCKER_CONTAINER__PORTS:
			getPorts().clear();
			return;
		case PimMMPackage.DOCKER_CONTAINER__OPTIONS:
			setOptions(OPTIONS_EDEFAULT);
			return;
		case PimMMPackage.DOCKER_CONTAINER__REGISTRY_USERNAME:
			setRegistryUsername((Expression) null);
			return;
		case PimMMPackage.DOCKER_CONTAINER__REGISTRY_PASSWORD:
			setRegistryPassword((Expression) null);
			return;
		case PimMMPackage.DOCKER_CONTAINER__NETWORK:
			setNetwork((Expression) null);
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
		case PimMMPackage.DOCKER_CONTAINER__LABEL:
			return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
		case PimMMPackage.DOCKER_CONTAINER__IMAGE:
			return image != null;
		case PimMMPackage.DOCKER_CONTAINER__ENVIRONMENT_VARIABLES:
			return environmentVariables != null && !environmentVariables.isEmpty();
		case PimMMPackage.DOCKER_CONTAINER__VOLUMES:
			return volumes != null && !volumes.isEmpty();
		case PimMMPackage.DOCKER_CONTAINER__PORTS:
			return ports != null && !ports.isEmpty();
		case PimMMPackage.DOCKER_CONTAINER__OPTIONS:
			return OPTIONS_EDEFAULT == null ? options != null : !OPTIONS_EDEFAULT.equals(options);
		case PimMMPackage.DOCKER_CONTAINER__REGISTRY_USERNAME:
			return registryUsername != null;
		case PimMMPackage.DOCKER_CONTAINER__REGISTRY_PASSWORD:
			return registryPassword != null;
		case PimMMPackage.DOCKER_CONTAINER__NETWORK:
			return network != null;
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
		result.append(" (label: ");
		result.append(label);
		result.append(", options: ");
		result.append(options);
		result.append(')');
		return result.toString();
	}

} //DockerContainerImpl
