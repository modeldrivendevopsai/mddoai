/**
 */
package com.mddoai.metamodel.bambooMM.impl;

import com.mddoai.metamodel.bambooMM.Artifact;
import com.mddoai.metamodel.bambooMM.ArtifactSubscription;
import com.mddoai.metamodel.bambooMM.BambooPackage;
import com.mddoai.metamodel.bambooMM.DockerConfig;
import com.mddoai.metamodel.bambooMM.Job;
import com.mddoai.metamodel.bambooMM.OtherConfig;
import com.mddoai.metamodel.bambooMM.Requirement;
import com.mddoai.metamodel.bambooMM.Task;

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
 * An implementation of the model object '<em><b>Job</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.JobImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.JobImpl#getKey <em>Key</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.JobImpl#getTasks <em>Tasks</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.JobImpl#getFinalTasks <em>Final Tasks</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.JobImpl#getArtifacts <em>Artifacts</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.JobImpl#getArtifactSubscriptions <em>Artifact Subscriptions</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.JobImpl#getRequirements <em>Requirements</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.JobImpl#getVariables <em>Variables</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.JobImpl#getDocker <em>Docker</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.JobImpl#getOther <em>Other</em>}</li>
 * </ul>
 *
 * @generated
 */
public class JobImpl extends MinimalEObjectImpl.Container implements Job {
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
	 * The default value of the '{@link #getKey() <em>Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKey()
	 * @generated
	 * @ordered
	 */
	protected static final String KEY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getKey() <em>Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKey()
	 * @generated
	 * @ordered
	 */
	protected String key = KEY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTasks() <em>Tasks</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTasks()
	 * @generated
	 * @ordered
	 */
	protected EList<Task> tasks;

	/**
	 * The cached value of the '{@link #getFinalTasks() <em>Final Tasks</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFinalTasks()
	 * @generated
	 * @ordered
	 */
	protected EList<Task> finalTasks;

	/**
	 * The cached value of the '{@link #getArtifacts() <em>Artifacts</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArtifacts()
	 * @generated
	 * @ordered
	 */
	protected EList<Artifact> artifacts;

	/**
	 * The cached value of the '{@link #getArtifactSubscriptions() <em>Artifact Subscriptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArtifactSubscriptions()
	 * @generated
	 * @ordered
	 */
	protected EList<ArtifactSubscription> artifactSubscriptions;

	/**
	 * The cached value of the '{@link #getRequirements() <em>Requirements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequirements()
	 * @generated
	 * @ordered
	 */
	protected EList<Requirement> requirements;

	/**
	 * The cached value of the '{@link #getVariables() <em>Variables</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariables()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> variables;

	/**
	 * The cached value of the '{@link #getDocker() <em>Docker</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocker()
	 * @generated
	 * @ordered
	 */
	protected DockerConfig docker;

	/**
	 * The cached value of the '{@link #getOther() <em>Other</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOther()
	 * @generated
	 * @ordered
	 */
	protected OtherConfig other;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected JobImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BambooPackage.Literals.JOB;
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
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.JOB__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getKey() {
		return key;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setKey(String newKey) {
		String oldKey = key;
		key = newKey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.JOB__KEY, oldKey, key));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Task> getTasks() {
		if (tasks == null) {
			tasks = new EObjectContainmentEList<Task>(Task.class, this, BambooPackage.JOB__TASKS);
		}
		return tasks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Task> getFinalTasks() {
		if (finalTasks == null) {
			finalTasks = new EObjectContainmentEList<Task>(Task.class, this, BambooPackage.JOB__FINAL_TASKS);
		}
		return finalTasks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Artifact> getArtifacts() {
		if (artifacts == null) {
			artifacts = new EObjectContainmentEList<Artifact>(Artifact.class, this, BambooPackage.JOB__ARTIFACTS);
		}
		return artifacts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ArtifactSubscription> getArtifactSubscriptions() {
		if (artifactSubscriptions == null) {
			artifactSubscriptions = new EObjectContainmentEList<ArtifactSubscription>(ArtifactSubscription.class, this,
					BambooPackage.JOB__ARTIFACT_SUBSCRIPTIONS);
		}
		return artifactSubscriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Requirement> getRequirements() {
		if (requirements == null) {
			requirements = new EObjectContainmentEList<Requirement>(Requirement.class, this,
					BambooPackage.JOB__REQUIREMENTS);
		}
		return requirements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EMap<String, String> getVariables() {
		if (variables == null) {
			variables = new EcoreEMap<String, String>(BambooPackage.Literals.VARIABLE_ASSIGNMENT,
					VariableAssignmentImpl.class, this, BambooPackage.JOB__VARIABLES);
		}
		return variables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DockerConfig getDocker() {
		return docker;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDocker(DockerConfig newDocker, NotificationChain msgs) {
		DockerConfig oldDocker = docker;
		docker = newDocker;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BambooPackage.JOB__DOCKER,
					oldDocker, newDocker);
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
	public void setDocker(DockerConfig newDocker) {
		if (newDocker != docker) {
			NotificationChain msgs = null;
			if (docker != null)
				msgs = ((InternalEObject) docker).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - BambooPackage.JOB__DOCKER, null, msgs);
			if (newDocker != null)
				msgs = ((InternalEObject) newDocker).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - BambooPackage.JOB__DOCKER, null, msgs);
			msgs = basicSetDocker(newDocker, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.JOB__DOCKER, newDocker, newDocker));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OtherConfig getOther() {
		return other;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOther(OtherConfig newOther, NotificationChain msgs) {
		OtherConfig oldOther = other;
		other = newOther;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BambooPackage.JOB__OTHER,
					oldOther, newOther);
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
	public void setOther(OtherConfig newOther) {
		if (newOther != other) {
			NotificationChain msgs = null;
			if (other != null)
				msgs = ((InternalEObject) other).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BambooPackage.JOB__OTHER,
						null, msgs);
			if (newOther != null)
				msgs = ((InternalEObject) newOther).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BambooPackage.JOB__OTHER,
						null, msgs);
			msgs = basicSetOther(newOther, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.JOB__OTHER, newOther, newOther));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case BambooPackage.JOB__TASKS:
			return ((InternalEList<?>) getTasks()).basicRemove(otherEnd, msgs);
		case BambooPackage.JOB__FINAL_TASKS:
			return ((InternalEList<?>) getFinalTasks()).basicRemove(otherEnd, msgs);
		case BambooPackage.JOB__ARTIFACTS:
			return ((InternalEList<?>) getArtifacts()).basicRemove(otherEnd, msgs);
		case BambooPackage.JOB__ARTIFACT_SUBSCRIPTIONS:
			return ((InternalEList<?>) getArtifactSubscriptions()).basicRemove(otherEnd, msgs);
		case BambooPackage.JOB__REQUIREMENTS:
			return ((InternalEList<?>) getRequirements()).basicRemove(otherEnd, msgs);
		case BambooPackage.JOB__VARIABLES:
			return ((InternalEList<?>) getVariables()).basicRemove(otherEnd, msgs);
		case BambooPackage.JOB__DOCKER:
			return basicSetDocker(null, msgs);
		case BambooPackage.JOB__OTHER:
			return basicSetOther(null, msgs);
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
		case BambooPackage.JOB__NAME:
			return getName();
		case BambooPackage.JOB__KEY:
			return getKey();
		case BambooPackage.JOB__TASKS:
			return getTasks();
		case BambooPackage.JOB__FINAL_TASKS:
			return getFinalTasks();
		case BambooPackage.JOB__ARTIFACTS:
			return getArtifacts();
		case BambooPackage.JOB__ARTIFACT_SUBSCRIPTIONS:
			return getArtifactSubscriptions();
		case BambooPackage.JOB__REQUIREMENTS:
			return getRequirements();
		case BambooPackage.JOB__VARIABLES:
			if (coreType)
				return getVariables();
			else
				return getVariables().map();
		case BambooPackage.JOB__DOCKER:
			return getDocker();
		case BambooPackage.JOB__OTHER:
			return getOther();
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
		case BambooPackage.JOB__NAME:
			setName((String) newValue);
			return;
		case BambooPackage.JOB__KEY:
			setKey((String) newValue);
			return;
		case BambooPackage.JOB__TASKS:
			getTasks().clear();
			getTasks().addAll((Collection<? extends Task>) newValue);
			return;
		case BambooPackage.JOB__FINAL_TASKS:
			getFinalTasks().clear();
			getFinalTasks().addAll((Collection<? extends Task>) newValue);
			return;
		case BambooPackage.JOB__ARTIFACTS:
			getArtifacts().clear();
			getArtifacts().addAll((Collection<? extends Artifact>) newValue);
			return;
		case BambooPackage.JOB__ARTIFACT_SUBSCRIPTIONS:
			getArtifactSubscriptions().clear();
			getArtifactSubscriptions().addAll((Collection<? extends ArtifactSubscription>) newValue);
			return;
		case BambooPackage.JOB__REQUIREMENTS:
			getRequirements().clear();
			getRequirements().addAll((Collection<? extends Requirement>) newValue);
			return;
		case BambooPackage.JOB__VARIABLES:
			((EStructuralFeature.Setting) getVariables()).set(newValue);
			return;
		case BambooPackage.JOB__DOCKER:
			setDocker((DockerConfig) newValue);
			return;
		case BambooPackage.JOB__OTHER:
			setOther((OtherConfig) newValue);
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
		case BambooPackage.JOB__NAME:
			setName(NAME_EDEFAULT);
			return;
		case BambooPackage.JOB__KEY:
			setKey(KEY_EDEFAULT);
			return;
		case BambooPackage.JOB__TASKS:
			getTasks().clear();
			return;
		case BambooPackage.JOB__FINAL_TASKS:
			getFinalTasks().clear();
			return;
		case BambooPackage.JOB__ARTIFACTS:
			getArtifacts().clear();
			return;
		case BambooPackage.JOB__ARTIFACT_SUBSCRIPTIONS:
			getArtifactSubscriptions().clear();
			return;
		case BambooPackage.JOB__REQUIREMENTS:
			getRequirements().clear();
			return;
		case BambooPackage.JOB__VARIABLES:
			getVariables().clear();
			return;
		case BambooPackage.JOB__DOCKER:
			setDocker((DockerConfig) null);
			return;
		case BambooPackage.JOB__OTHER:
			setOther((OtherConfig) null);
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
		case BambooPackage.JOB__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case BambooPackage.JOB__KEY:
			return KEY_EDEFAULT == null ? key != null : !KEY_EDEFAULT.equals(key);
		case BambooPackage.JOB__TASKS:
			return tasks != null && !tasks.isEmpty();
		case BambooPackage.JOB__FINAL_TASKS:
			return finalTasks != null && !finalTasks.isEmpty();
		case BambooPackage.JOB__ARTIFACTS:
			return artifacts != null && !artifacts.isEmpty();
		case BambooPackage.JOB__ARTIFACT_SUBSCRIPTIONS:
			return artifactSubscriptions != null && !artifactSubscriptions.isEmpty();
		case BambooPackage.JOB__REQUIREMENTS:
			return requirements != null && !requirements.isEmpty();
		case BambooPackage.JOB__VARIABLES:
			return variables != null && !variables.isEmpty();
		case BambooPackage.JOB__DOCKER:
			return docker != null;
		case BambooPackage.JOB__OTHER:
			return other != null;
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
		result.append(", key: ");
		result.append(key);
		result.append(')');
		return result.toString();
	}

} //JobImpl
