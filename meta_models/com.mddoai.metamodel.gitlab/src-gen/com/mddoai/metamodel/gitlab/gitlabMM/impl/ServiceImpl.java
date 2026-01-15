/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM.impl;

import com.mddoai.metamodel.gitlab.gitlabMM.Comand;
import com.mddoai.metamodel.gitlab.gitlabMM.Docker;
import com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage;
import com.mddoai.metamodel.gitlab.gitlabMM.Kubernetes;
import com.mddoai.metamodel.gitlab.gitlabMM.Service;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ServiceImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ServiceImpl#getAlias <em>Alias</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ServiceImpl#getEntrypoint <em>Entrypoint</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ServiceImpl#getCommands <em>Commands</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ServiceImpl#getDocker <em>Docker</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ServiceImpl#getKubernetes <em>Kubernetes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ServiceImpl#getPull_policy <em>Pull policy</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ServiceImpl extends MinimalEObjectImpl.Container implements Service {
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
	 * The default value of the '{@link #getAlias() <em>Alias</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAlias()
	 * @generated
	 * @ordered
	 */
	protected static final String ALIAS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAlias() <em>Alias</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAlias()
	 * @generated
	 * @ordered
	 */
	protected String alias = ALIAS_EDEFAULT;

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
	 * The cached value of the '{@link #getCommands() <em>Commands</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCommands()
	 * @generated
	 * @ordered
	 */
	protected EList<Comand> commands;

	/**
	 * The cached value of the '{@link #getDocker() <em>Docker</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocker()
	 * @generated
	 * @ordered
	 */
	protected Docker docker;

	/**
	 * The cached value of the '{@link #getKubernetes() <em>Kubernetes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKubernetes()
	 * @generated
	 * @ordered
	 */
	protected Kubernetes kubernetes;

	/**
	 * The cached value of the '{@link #getPull_policy() <em>Pull policy</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPull_policy()
	 * @generated
	 * @ordered
	 */
	protected EList<String> pull_policy;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ServiceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GitlabMMPackage.Literals.SERVICE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.SERVICE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getAlias() {
		return alias;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAlias(String newAlias) {
		String oldAlias = alias;
		alias = newAlias;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.SERVICE__ALIAS, oldAlias, alias));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getEntrypoint() {
		if (entrypoint == null) {
			entrypoint = new EDataTypeUniqueEList<String>(String.class, this, GitlabMMPackage.SERVICE__ENTRYPOINT);
		}
		return entrypoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Comand> getCommands() {
		if (commands == null) {
			commands = new EObjectResolvingEList<Comand>(Comand.class, this, GitlabMMPackage.SERVICE__COMMANDS);
		}
		return commands;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Docker getDocker() {
		return docker;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDocker(Docker newDocker, NotificationChain msgs) {
		Docker oldDocker = docker;
		docker = newDocker;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GitlabMMPackage.SERVICE__DOCKER, oldDocker, newDocker);
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
	public void setDocker(Docker newDocker) {
		if (newDocker != docker) {
			NotificationChain msgs = null;
			if (docker != null)
				msgs = ((InternalEObject) docker).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.SERVICE__DOCKER, null, msgs);
			if (newDocker != null)
				msgs = ((InternalEObject) newDocker).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.SERVICE__DOCKER, null, msgs);
			msgs = basicSetDocker(newDocker, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.SERVICE__DOCKER, newDocker,
					newDocker));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Kubernetes getKubernetes() {
		return kubernetes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetKubernetes(Kubernetes newKubernetes, NotificationChain msgs) {
		Kubernetes oldKubernetes = kubernetes;
		kubernetes = newKubernetes;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GitlabMMPackage.SERVICE__KUBERNETES, oldKubernetes, newKubernetes);
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
	public void setKubernetes(Kubernetes newKubernetes) {
		if (newKubernetes != kubernetes) {
			NotificationChain msgs = null;
			if (kubernetes != null)
				msgs = ((InternalEObject) kubernetes).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.SERVICE__KUBERNETES, null, msgs);
			if (newKubernetes != null)
				msgs = ((InternalEObject) newKubernetes).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.SERVICE__KUBERNETES, null, msgs);
			msgs = basicSetKubernetes(newKubernetes, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.SERVICE__KUBERNETES, newKubernetes,
					newKubernetes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getPull_policy() {
		if (pull_policy == null) {
			pull_policy = new EDataTypeUniqueEList<String>(String.class, this, GitlabMMPackage.SERVICE__PULL_POLICY);
		}
		return pull_policy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GitlabMMPackage.SERVICE__DOCKER:
			return basicSetDocker(null, msgs);
		case GitlabMMPackage.SERVICE__KUBERNETES:
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
		case GitlabMMPackage.SERVICE__NAME:
			return getName();
		case GitlabMMPackage.SERVICE__ALIAS:
			return getAlias();
		case GitlabMMPackage.SERVICE__ENTRYPOINT:
			return getEntrypoint();
		case GitlabMMPackage.SERVICE__COMMANDS:
			return getCommands();
		case GitlabMMPackage.SERVICE__DOCKER:
			return getDocker();
		case GitlabMMPackage.SERVICE__KUBERNETES:
			return getKubernetes();
		case GitlabMMPackage.SERVICE__PULL_POLICY:
			return getPull_policy();
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
		case GitlabMMPackage.SERVICE__NAME:
			setName((String) newValue);
			return;
		case GitlabMMPackage.SERVICE__ALIAS:
			setAlias((String) newValue);
			return;
		case GitlabMMPackage.SERVICE__ENTRYPOINT:
			getEntrypoint().clear();
			getEntrypoint().addAll((Collection<? extends String>) newValue);
			return;
		case GitlabMMPackage.SERVICE__COMMANDS:
			getCommands().clear();
			getCommands().addAll((Collection<? extends Comand>) newValue);
			return;
		case GitlabMMPackage.SERVICE__DOCKER:
			setDocker((Docker) newValue);
			return;
		case GitlabMMPackage.SERVICE__KUBERNETES:
			setKubernetes((Kubernetes) newValue);
			return;
		case GitlabMMPackage.SERVICE__PULL_POLICY:
			getPull_policy().clear();
			getPull_policy().addAll((Collection<? extends String>) newValue);
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
		case GitlabMMPackage.SERVICE__NAME:
			setName(NAME_EDEFAULT);
			return;
		case GitlabMMPackage.SERVICE__ALIAS:
			setAlias(ALIAS_EDEFAULT);
			return;
		case GitlabMMPackage.SERVICE__ENTRYPOINT:
			getEntrypoint().clear();
			return;
		case GitlabMMPackage.SERVICE__COMMANDS:
			getCommands().clear();
			return;
		case GitlabMMPackage.SERVICE__DOCKER:
			setDocker((Docker) null);
			return;
		case GitlabMMPackage.SERVICE__KUBERNETES:
			setKubernetes((Kubernetes) null);
			return;
		case GitlabMMPackage.SERVICE__PULL_POLICY:
			getPull_policy().clear();
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
		case GitlabMMPackage.SERVICE__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case GitlabMMPackage.SERVICE__ALIAS:
			return ALIAS_EDEFAULT == null ? alias != null : !ALIAS_EDEFAULT.equals(alias);
		case GitlabMMPackage.SERVICE__ENTRYPOINT:
			return entrypoint != null && !entrypoint.isEmpty();
		case GitlabMMPackage.SERVICE__COMMANDS:
			return commands != null && !commands.isEmpty();
		case GitlabMMPackage.SERVICE__DOCKER:
			return docker != null;
		case GitlabMMPackage.SERVICE__KUBERNETES:
			return kubernetes != null;
		case GitlabMMPackage.SERVICE__PULL_POLICY:
			return pull_policy != null && !pull_policy.isEmpty();
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
		result.append(", alias: ");
		result.append(alias);
		result.append(", entrypoint: ");
		result.append(entrypoint);
		result.append(", pull_policy: ");
		result.append(pull_policy);
		result.append(')');
		return result.toString();
	}

} //ServiceImpl
