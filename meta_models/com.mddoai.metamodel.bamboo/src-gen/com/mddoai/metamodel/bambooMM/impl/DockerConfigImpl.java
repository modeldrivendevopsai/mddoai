/**
 */
package com.mddoai.metamodel.bambooMM.impl;

import com.mddoai.metamodel.bambooMM.BambooPackage;
import com.mddoai.metamodel.bambooMM.DockerConfig;

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
 * An implementation of the model object '<em><b>Docker Config</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.DockerConfigImpl#getImage <em>Image</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.DockerConfigImpl#getVolumes <em>Volumes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.DockerConfigImpl#isUseDefaultVolumes <em>Use Default Volumes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.DockerConfigImpl#getDockerRunArguments <em>Docker Run Arguments</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DockerConfigImpl extends MinimalEObjectImpl.Container implements DockerConfig {
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
	 * The cached value of the '{@link #getVolumes() <em>Volumes</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVolumes()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> volumes;

	/**
	 * The default value of the '{@link #isUseDefaultVolumes() <em>Use Default Volumes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUseDefaultVolumes()
	 * @generated
	 * @ordered
	 */
	protected static final boolean USE_DEFAULT_VOLUMES_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isUseDefaultVolumes() <em>Use Default Volumes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUseDefaultVolumes()
	 * @generated
	 * @ordered
	 */
	protected boolean useDefaultVolumes = USE_DEFAULT_VOLUMES_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDockerRunArguments() <em>Docker Run Arguments</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDockerRunArguments()
	 * @generated
	 * @ordered
	 */
	protected EList<String> dockerRunArguments;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DockerConfigImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BambooPackage.Literals.DOCKER_CONFIG;
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
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.DOCKER_CONFIG__IMAGE, oldImage, image));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EMap<String, String> getVolumes() {
		if (volumes == null) {
			volumes = new EcoreEMap<String, String>(BambooPackage.Literals.VARIABLE_ASSIGNMENT,
					VariableAssignmentImpl.class, this, BambooPackage.DOCKER_CONFIG__VOLUMES);
		}
		return volumes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isUseDefaultVolumes() {
		return useDefaultVolumes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUseDefaultVolumes(boolean newUseDefaultVolumes) {
		boolean oldUseDefaultVolumes = useDefaultVolumes;
		useDefaultVolumes = newUseDefaultVolumes;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.DOCKER_CONFIG__USE_DEFAULT_VOLUMES,
					oldUseDefaultVolumes, useDefaultVolumes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getDockerRunArguments() {
		if (dockerRunArguments == null) {
			dockerRunArguments = new EDataTypeUniqueEList<String>(String.class, this,
					BambooPackage.DOCKER_CONFIG__DOCKER_RUN_ARGUMENTS);
		}
		return dockerRunArguments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case BambooPackage.DOCKER_CONFIG__VOLUMES:
			return ((InternalEList<?>) getVolumes()).basicRemove(otherEnd, msgs);
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
		case BambooPackage.DOCKER_CONFIG__IMAGE:
			return getImage();
		case BambooPackage.DOCKER_CONFIG__VOLUMES:
			if (coreType)
				return getVolumes();
			else
				return getVolumes().map();
		case BambooPackage.DOCKER_CONFIG__USE_DEFAULT_VOLUMES:
			return isUseDefaultVolumes();
		case BambooPackage.DOCKER_CONFIG__DOCKER_RUN_ARGUMENTS:
			return getDockerRunArguments();
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
		case BambooPackage.DOCKER_CONFIG__IMAGE:
			setImage((String) newValue);
			return;
		case BambooPackage.DOCKER_CONFIG__VOLUMES:
			((EStructuralFeature.Setting) getVolumes()).set(newValue);
			return;
		case BambooPackage.DOCKER_CONFIG__USE_DEFAULT_VOLUMES:
			setUseDefaultVolumes((Boolean) newValue);
			return;
		case BambooPackage.DOCKER_CONFIG__DOCKER_RUN_ARGUMENTS:
			getDockerRunArguments().clear();
			getDockerRunArguments().addAll((Collection<? extends String>) newValue);
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
		case BambooPackage.DOCKER_CONFIG__IMAGE:
			setImage(IMAGE_EDEFAULT);
			return;
		case BambooPackage.DOCKER_CONFIG__VOLUMES:
			getVolumes().clear();
			return;
		case BambooPackage.DOCKER_CONFIG__USE_DEFAULT_VOLUMES:
			setUseDefaultVolumes(USE_DEFAULT_VOLUMES_EDEFAULT);
			return;
		case BambooPackage.DOCKER_CONFIG__DOCKER_RUN_ARGUMENTS:
			getDockerRunArguments().clear();
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
		case BambooPackage.DOCKER_CONFIG__IMAGE:
			return IMAGE_EDEFAULT == null ? image != null : !IMAGE_EDEFAULT.equals(image);
		case BambooPackage.DOCKER_CONFIG__VOLUMES:
			return volumes != null && !volumes.isEmpty();
		case BambooPackage.DOCKER_CONFIG__USE_DEFAULT_VOLUMES:
			return useDefaultVolumes != USE_DEFAULT_VOLUMES_EDEFAULT;
		case BambooPackage.DOCKER_CONFIG__DOCKER_RUN_ARGUMENTS:
			return dockerRunArguments != null && !dockerRunArguments.isEmpty();
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
		result.append(", useDefaultVolumes: ");
		result.append(useDefaultVolumes);
		result.append(", dockerRunArguments: ");
		result.append(dockerRunArguments);
		result.append(')');
		return result.toString();
	}

} //DockerConfigImpl
