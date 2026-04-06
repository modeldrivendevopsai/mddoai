/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM.impl;

import com.mddoai.metamodel.gitlab.gitlabMM.DockerOptions;
import com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage;
import com.mddoai.metamodel.gitlab.gitlabMM.Image;
import com.mddoai.metamodel.gitlab.gitlabMM.KubernetesOptions;
import com.mddoai.metamodel.gitlab.gitlabMM.PullPolicy;

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
 * An implementation of the model object '<em><b>Image</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ImageImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ImageImpl#getEntrypoint <em>Entrypoint</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ImageImpl#getPullPolicy <em>Pull Policy</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ImageImpl#getDocker <em>Docker</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ImageImpl#getKubernetes <em>Kubernetes</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ImageImpl extends MinimalEObjectImpl.Container implements Image {
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
	 * The cached value of the '{@link #getEntrypoint() <em>Entrypoint</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntrypoint()
	 * @generated
	 * @ordered
	 */
	protected EList<String> entrypoint;

	/**
	 * The default value of the '{@link #getPullPolicy() <em>Pull Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPullPolicy()
	 * @generated
	 * @ordered
	 */
	protected static final PullPolicy PULL_POLICY_EDEFAULT = PullPolicy.ALWAYS;

	/**
	 * The cached value of the '{@link #getPullPolicy() <em>Pull Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPullPolicy()
	 * @generated
	 * @ordered
	 */
	protected PullPolicy pullPolicy = PULL_POLICY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDocker() <em>Docker</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocker()
	 * @generated
	 * @ordered
	 */
	protected DockerOptions docker;

	/**
	 * The cached value of the '{@link #getKubernetes() <em>Kubernetes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKubernetes()
	 * @generated
	 * @ordered
	 */
	protected KubernetesOptions kubernetes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ImageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GitlabMMPackage.Literals.IMAGE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.IMAGE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getEntrypoint() {
		if (entrypoint == null) {
			entrypoint = new EDataTypeUniqueEList<String>(String.class, this, GitlabMMPackage.IMAGE__ENTRYPOINT);
		}
		return entrypoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PullPolicy getPullPolicy() {
		return pullPolicy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPullPolicy(PullPolicy newPullPolicy) {
		PullPolicy oldPullPolicy = pullPolicy;
		pullPolicy = newPullPolicy == null ? PULL_POLICY_EDEFAULT : newPullPolicy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.IMAGE__PULL_POLICY, oldPullPolicy,
					pullPolicy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DockerOptions getDocker() {
		return docker;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDocker(DockerOptions newDocker, NotificationChain msgs) {
		DockerOptions oldDocker = docker;
		docker = newDocker;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GitlabMMPackage.IMAGE__DOCKER, oldDocker, newDocker);
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
	public void setDocker(DockerOptions newDocker) {
		if (newDocker != docker) {
			NotificationChain msgs = null;
			if (docker != null)
				msgs = ((InternalEObject) docker).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.IMAGE__DOCKER, null, msgs);
			if (newDocker != null)
				msgs = ((InternalEObject) newDocker).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.IMAGE__DOCKER, null, msgs);
			msgs = basicSetDocker(newDocker, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.IMAGE__DOCKER, newDocker, newDocker));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public KubernetesOptions getKubernetes() {
		return kubernetes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetKubernetes(KubernetesOptions newKubernetes, NotificationChain msgs) {
		KubernetesOptions oldKubernetes = kubernetes;
		kubernetes = newKubernetes;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GitlabMMPackage.IMAGE__KUBERNETES, oldKubernetes, newKubernetes);
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
	public void setKubernetes(KubernetesOptions newKubernetes) {
		if (newKubernetes != kubernetes) {
			NotificationChain msgs = null;
			if (kubernetes != null)
				msgs = ((InternalEObject) kubernetes).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.IMAGE__KUBERNETES, null, msgs);
			if (newKubernetes != null)
				msgs = ((InternalEObject) newKubernetes).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.IMAGE__KUBERNETES, null, msgs);
			msgs = basicSetKubernetes(newKubernetes, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.IMAGE__KUBERNETES, newKubernetes,
					newKubernetes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GitlabMMPackage.IMAGE__DOCKER:
			return basicSetDocker(null, msgs);
		case GitlabMMPackage.IMAGE__KUBERNETES:
			return basicSetKubernetes(null, msgs);
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
		case GitlabMMPackage.IMAGE__NAME:
			return getName();
		case GitlabMMPackage.IMAGE__ENTRYPOINT:
			return getEntrypoint();
		case GitlabMMPackage.IMAGE__PULL_POLICY:
			return getPullPolicy();
		case GitlabMMPackage.IMAGE__DOCKER:
			return getDocker();
		case GitlabMMPackage.IMAGE__KUBERNETES:
			return getKubernetes();
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
		case GitlabMMPackage.IMAGE__NAME:
			setName((String) newValue);
			return;
		case GitlabMMPackage.IMAGE__ENTRYPOINT:
			getEntrypoint().clear();
			getEntrypoint().addAll((Collection<? extends String>) newValue);
			return;
		case GitlabMMPackage.IMAGE__PULL_POLICY:
			setPullPolicy((PullPolicy) newValue);
			return;
		case GitlabMMPackage.IMAGE__DOCKER:
			setDocker((DockerOptions) newValue);
			return;
		case GitlabMMPackage.IMAGE__KUBERNETES:
			setKubernetes((KubernetesOptions) newValue);
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
		case GitlabMMPackage.IMAGE__NAME:
			setName(NAME_EDEFAULT);
			return;
		case GitlabMMPackage.IMAGE__ENTRYPOINT:
			getEntrypoint().clear();
			return;
		case GitlabMMPackage.IMAGE__PULL_POLICY:
			setPullPolicy(PULL_POLICY_EDEFAULT);
			return;
		case GitlabMMPackage.IMAGE__DOCKER:
			setDocker((DockerOptions) null);
			return;
		case GitlabMMPackage.IMAGE__KUBERNETES:
			setKubernetes((KubernetesOptions) null);
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
		case GitlabMMPackage.IMAGE__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case GitlabMMPackage.IMAGE__ENTRYPOINT:
			return entrypoint != null && !entrypoint.isEmpty();
		case GitlabMMPackage.IMAGE__PULL_POLICY:
			return pullPolicy != PULL_POLICY_EDEFAULT;
		case GitlabMMPackage.IMAGE__DOCKER:
			return docker != null;
		case GitlabMMPackage.IMAGE__KUBERNETES:
			return kubernetes != null;
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
		result.append(", entrypoint: ");
		result.append(entrypoint);
		result.append(", pullPolicy: ");
		result.append(pullPolicy);
		result.append(')');
		return result.toString();
	}

} //ImageImpl
