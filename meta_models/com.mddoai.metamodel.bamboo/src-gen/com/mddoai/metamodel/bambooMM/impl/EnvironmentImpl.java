/**
 */
package com.mddoai.metamodel.bambooMM.impl;

import com.mddoai.metamodel.bambooMM.BambooPackage;
import com.mddoai.metamodel.bambooMM.DockerConfig;
import com.mddoai.metamodel.bambooMM.Environment;
import com.mddoai.metamodel.bambooMM.Notification;
import com.mddoai.metamodel.bambooMM.RELEASE_APPROVAL;
import com.mddoai.metamodel.bambooMM.Requirement;
import com.mddoai.metamodel.bambooMM.Task;
import com.mddoai.metamodel.bambooMM.Trigger;

import java.util.Collection;

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
 * An implementation of the model object '<em><b>Environment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.EnvironmentImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.EnvironmentImpl#getTasks <em>Tasks</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.EnvironmentImpl#getFinalTasks <em>Final Tasks</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.EnvironmentImpl#getVariables <em>Variables</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.EnvironmentImpl#getTriggers <em>Triggers</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.EnvironmentImpl#getNotifications <em>Notifications</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.EnvironmentImpl#getDocker <em>Docker</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.EnvironmentImpl#getRequirements <em>Requirements</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.EnvironmentImpl#getReleaseApprovalPrerequisite <em>Release Approval Prerequisite</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EnvironmentImpl extends MinimalEObjectImpl.Container implements Environment {
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
	 * The cached value of the '{@link #getVariables() <em>Variables</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariables()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> variables;

	/**
	 * The cached value of the '{@link #getTriggers() <em>Triggers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTriggers()
	 * @generated
	 * @ordered
	 */
	protected EList<Trigger> triggers;

	/**
	 * The cached value of the '{@link #getNotifications() <em>Notifications</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNotifications()
	 * @generated
	 * @ordered
	 */
	protected EList<Notification> notifications;

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
	 * The cached value of the '{@link #getRequirements() <em>Requirements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequirements()
	 * @generated
	 * @ordered
	 */
	protected EList<Requirement> requirements;

	/**
	 * The default value of the '{@link #getReleaseApprovalPrerequisite() <em>Release Approval Prerequisite</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReleaseApprovalPrerequisite()
	 * @generated
	 * @ordered
	 */
	protected static final RELEASE_APPROVAL RELEASE_APPROVAL_PREREQUISITE_EDEFAULT = RELEASE_APPROVAL.NOT_BROKEN;

	/**
	 * The cached value of the '{@link #getReleaseApprovalPrerequisite() <em>Release Approval Prerequisite</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReleaseApprovalPrerequisite()
	 * @generated
	 * @ordered
	 */
	protected RELEASE_APPROVAL releaseApprovalPrerequisite = RELEASE_APPROVAL_PREREQUISITE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EnvironmentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BambooPackage.Literals.ENVIRONMENT;
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
			eNotify(new ENotificationImpl(this, org.eclipse.emf.common.notify.Notification.SET,
					BambooPackage.ENVIRONMENT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Task> getTasks() {
		if (tasks == null) {
			tasks = new EObjectContainmentEList<Task>(Task.class, this, BambooPackage.ENVIRONMENT__TASKS);
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
			finalTasks = new EObjectContainmentEList<Task>(Task.class, this, BambooPackage.ENVIRONMENT__FINAL_TASKS);
		}
		return finalTasks;
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
					VariableAssignmentImpl.class, this, BambooPackage.ENVIRONMENT__VARIABLES);
		}
		return variables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Trigger> getTriggers() {
		if (triggers == null) {
			triggers = new EObjectContainmentEList<Trigger>(Trigger.class, this, BambooPackage.ENVIRONMENT__TRIGGERS);
		}
		return triggers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Notification> getNotifications() {
		if (notifications == null) {
			notifications = new EObjectContainmentEList<Notification>(Notification.class, this,
					BambooPackage.ENVIRONMENT__NOTIFICATIONS);
		}
		return notifications;
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
			ENotificationImpl notification = new ENotificationImpl(this, org.eclipse.emf.common.notify.Notification.SET,
					BambooPackage.ENVIRONMENT__DOCKER, oldDocker, newDocker);
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
						EOPPOSITE_FEATURE_BASE - BambooPackage.ENVIRONMENT__DOCKER, null, msgs);
			if (newDocker != null)
				msgs = ((InternalEObject) newDocker).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - BambooPackage.ENVIRONMENT__DOCKER, null, msgs);
			msgs = basicSetDocker(newDocker, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, org.eclipse.emf.common.notify.Notification.SET,
					BambooPackage.ENVIRONMENT__DOCKER, newDocker, newDocker));
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
					BambooPackage.ENVIRONMENT__REQUIREMENTS);
		}
		return requirements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RELEASE_APPROVAL getReleaseApprovalPrerequisite() {
		return releaseApprovalPrerequisite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReleaseApprovalPrerequisite(RELEASE_APPROVAL newReleaseApprovalPrerequisite) {
		RELEASE_APPROVAL oldReleaseApprovalPrerequisite = releaseApprovalPrerequisite;
		releaseApprovalPrerequisite = newReleaseApprovalPrerequisite == null ? RELEASE_APPROVAL_PREREQUISITE_EDEFAULT
				: newReleaseApprovalPrerequisite;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, org.eclipse.emf.common.notify.Notification.SET,
					BambooPackage.ENVIRONMENT__RELEASE_APPROVAL_PREREQUISITE, oldReleaseApprovalPrerequisite,
					releaseApprovalPrerequisite));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case BambooPackage.ENVIRONMENT__TASKS:
			return ((InternalEList<?>) getTasks()).basicRemove(otherEnd, msgs);
		case BambooPackage.ENVIRONMENT__FINAL_TASKS:
			return ((InternalEList<?>) getFinalTasks()).basicRemove(otherEnd, msgs);
		case BambooPackage.ENVIRONMENT__VARIABLES:
			return ((InternalEList<?>) getVariables()).basicRemove(otherEnd, msgs);
		case BambooPackage.ENVIRONMENT__TRIGGERS:
			return ((InternalEList<?>) getTriggers()).basicRemove(otherEnd, msgs);
		case BambooPackage.ENVIRONMENT__NOTIFICATIONS:
			return ((InternalEList<?>) getNotifications()).basicRemove(otherEnd, msgs);
		case BambooPackage.ENVIRONMENT__DOCKER:
			return basicSetDocker(null, msgs);
		case BambooPackage.ENVIRONMENT__REQUIREMENTS:
			return ((InternalEList<?>) getRequirements()).basicRemove(otherEnd, msgs);
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
		case BambooPackage.ENVIRONMENT__NAME:
			return getName();
		case BambooPackage.ENVIRONMENT__TASKS:
			return getTasks();
		case BambooPackage.ENVIRONMENT__FINAL_TASKS:
			return getFinalTasks();
		case BambooPackage.ENVIRONMENT__VARIABLES:
			if (coreType)
				return getVariables();
			else
				return getVariables().map();
		case BambooPackage.ENVIRONMENT__TRIGGERS:
			return getTriggers();
		case BambooPackage.ENVIRONMENT__NOTIFICATIONS:
			return getNotifications();
		case BambooPackage.ENVIRONMENT__DOCKER:
			return getDocker();
		case BambooPackage.ENVIRONMENT__REQUIREMENTS:
			return getRequirements();
		case BambooPackage.ENVIRONMENT__RELEASE_APPROVAL_PREREQUISITE:
			return getReleaseApprovalPrerequisite();
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
		case BambooPackage.ENVIRONMENT__NAME:
			setName((String) newValue);
			return;
		case BambooPackage.ENVIRONMENT__TASKS:
			getTasks().clear();
			getTasks().addAll((Collection<? extends Task>) newValue);
			return;
		case BambooPackage.ENVIRONMENT__FINAL_TASKS:
			getFinalTasks().clear();
			getFinalTasks().addAll((Collection<? extends Task>) newValue);
			return;
		case BambooPackage.ENVIRONMENT__VARIABLES:
			((EStructuralFeature.Setting) getVariables()).set(newValue);
			return;
		case BambooPackage.ENVIRONMENT__TRIGGERS:
			getTriggers().clear();
			getTriggers().addAll((Collection<? extends Trigger>) newValue);
			return;
		case BambooPackage.ENVIRONMENT__NOTIFICATIONS:
			getNotifications().clear();
			getNotifications().addAll((Collection<? extends Notification>) newValue);
			return;
		case BambooPackage.ENVIRONMENT__DOCKER:
			setDocker((DockerConfig) newValue);
			return;
		case BambooPackage.ENVIRONMENT__REQUIREMENTS:
			getRequirements().clear();
			getRequirements().addAll((Collection<? extends Requirement>) newValue);
			return;
		case BambooPackage.ENVIRONMENT__RELEASE_APPROVAL_PREREQUISITE:
			setReleaseApprovalPrerequisite((RELEASE_APPROVAL) newValue);
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
		case BambooPackage.ENVIRONMENT__NAME:
			setName(NAME_EDEFAULT);
			return;
		case BambooPackage.ENVIRONMENT__TASKS:
			getTasks().clear();
			return;
		case BambooPackage.ENVIRONMENT__FINAL_TASKS:
			getFinalTasks().clear();
			return;
		case BambooPackage.ENVIRONMENT__VARIABLES:
			getVariables().clear();
			return;
		case BambooPackage.ENVIRONMENT__TRIGGERS:
			getTriggers().clear();
			return;
		case BambooPackage.ENVIRONMENT__NOTIFICATIONS:
			getNotifications().clear();
			return;
		case BambooPackage.ENVIRONMENT__DOCKER:
			setDocker((DockerConfig) null);
			return;
		case BambooPackage.ENVIRONMENT__REQUIREMENTS:
			getRequirements().clear();
			return;
		case BambooPackage.ENVIRONMENT__RELEASE_APPROVAL_PREREQUISITE:
			setReleaseApprovalPrerequisite(RELEASE_APPROVAL_PREREQUISITE_EDEFAULT);
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
		case BambooPackage.ENVIRONMENT__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case BambooPackage.ENVIRONMENT__TASKS:
			return tasks != null && !tasks.isEmpty();
		case BambooPackage.ENVIRONMENT__FINAL_TASKS:
			return finalTasks != null && !finalTasks.isEmpty();
		case BambooPackage.ENVIRONMENT__VARIABLES:
			return variables != null && !variables.isEmpty();
		case BambooPackage.ENVIRONMENT__TRIGGERS:
			return triggers != null && !triggers.isEmpty();
		case BambooPackage.ENVIRONMENT__NOTIFICATIONS:
			return notifications != null && !notifications.isEmpty();
		case BambooPackage.ENVIRONMENT__DOCKER:
			return docker != null;
		case BambooPackage.ENVIRONMENT__REQUIREMENTS:
			return requirements != null && !requirements.isEmpty();
		case BambooPackage.ENVIRONMENT__RELEASE_APPROVAL_PREREQUISITE:
			return releaseApprovalPrerequisite != RELEASE_APPROVAL_PREREQUISITE_EDEFAULT;
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
		result.append(", releaseApprovalPrerequisite: ");
		result.append(releaseApprovalPrerequisite);
		result.append(')');
		return result.toString();
	}

} //EnvironmentImpl
