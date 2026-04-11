/**
 */
package com.mddoai.metamodel.bambooMM.impl;

import com.mddoai.metamodel.bambooMM.BambooPackage;
import com.mddoai.metamodel.bambooMM.BranchOverride;
import com.mddoai.metamodel.bambooMM.BranchSpecificConfig;
import com.mddoai.metamodel.bambooMM.DockerConfig;
import com.mddoai.metamodel.bambooMM.Job;
import com.mddoai.metamodel.bambooMM.Label;
import com.mddoai.metamodel.bambooMM.Notification;
import com.mddoai.metamodel.bambooMM.Stage;
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
 * An implementation of the model object '<em><b>Branch Override</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.BranchOverrideImpl#getBranchPattern <em>Branch Pattern</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.BranchOverrideImpl#getStages <em>Stages</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.BranchOverrideImpl#getJobs <em>Jobs</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.BranchOverrideImpl#getTriggers <em>Triggers</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.BranchOverrideImpl#getNotifications <em>Notifications</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.BranchOverrideImpl#getVariables <em>Variables</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.BranchOverrideImpl#getLabels <em>Labels</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.BranchOverrideImpl#getDocker <em>Docker</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.BranchOverrideImpl#getBranchConfig <em>Branch Config</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BranchOverrideImpl extends MinimalEObjectImpl.Container implements BranchOverride {
	/**
	 * The default value of the '{@link #getBranchPattern() <em>Branch Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBranchPattern()
	 * @generated
	 * @ordered
	 */
	protected static final String BRANCH_PATTERN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBranchPattern() <em>Branch Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBranchPattern()
	 * @generated
	 * @ordered
	 */
	protected String branchPattern = BRANCH_PATTERN_EDEFAULT;

	/**
	 * The cached value of the '{@link #getStages() <em>Stages</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStages()
	 * @generated
	 * @ordered
	 */
	protected EList<Stage> stages;

	/**
	 * The cached value of the '{@link #getJobs() <em>Jobs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJobs()
	 * @generated
	 * @ordered
	 */
	protected EList<Job> jobs;

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
	 * The cached value of the '{@link #getVariables() <em>Variables</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariables()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> variables;

	/**
	 * The cached value of the '{@link #getLabels() <em>Labels</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabels()
	 * @generated
	 * @ordered
	 */
	protected EList<Label> labels;

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
	 * The cached value of the '{@link #getBranchConfig() <em>Branch Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBranchConfig()
	 * @generated
	 * @ordered
	 */
	protected BranchSpecificConfig branchConfig;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BranchOverrideImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BambooPackage.Literals.BRANCH_OVERRIDE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getBranchPattern() {
		return branchPattern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBranchPattern(String newBranchPattern) {
		String oldBranchPattern = branchPattern;
		branchPattern = newBranchPattern;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, org.eclipse.emf.common.notify.Notification.SET,
					BambooPackage.BRANCH_OVERRIDE__BRANCH_PATTERN, oldBranchPattern, branchPattern));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Stage> getStages() {
		if (stages == null) {
			stages = new EObjectContainmentEList<Stage>(Stage.class, this, BambooPackage.BRANCH_OVERRIDE__STAGES);
		}
		return stages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Job> getJobs() {
		if (jobs == null) {
			jobs = new EObjectContainmentEList<Job>(Job.class, this, BambooPackage.BRANCH_OVERRIDE__JOBS);
		}
		return jobs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Trigger> getTriggers() {
		if (triggers == null) {
			triggers = new EObjectContainmentEList<Trigger>(Trigger.class, this,
					BambooPackage.BRANCH_OVERRIDE__TRIGGERS);
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
					BambooPackage.BRANCH_OVERRIDE__NOTIFICATIONS);
		}
		return notifications;
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
					VariableAssignmentImpl.class, this, BambooPackage.BRANCH_OVERRIDE__VARIABLES);
		}
		return variables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Label> getLabels() {
		if (labels == null) {
			labels = new EObjectContainmentEList<Label>(Label.class, this, BambooPackage.BRANCH_OVERRIDE__LABELS);
		}
		return labels;
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
					BambooPackage.BRANCH_OVERRIDE__DOCKER, oldDocker, newDocker);
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
						EOPPOSITE_FEATURE_BASE - BambooPackage.BRANCH_OVERRIDE__DOCKER, null, msgs);
			if (newDocker != null)
				msgs = ((InternalEObject) newDocker).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - BambooPackage.BRANCH_OVERRIDE__DOCKER, null, msgs);
			msgs = basicSetDocker(newDocker, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, org.eclipse.emf.common.notify.Notification.SET,
					BambooPackage.BRANCH_OVERRIDE__DOCKER, newDocker, newDocker));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BranchSpecificConfig getBranchConfig() {
		return branchConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBranchConfig(BranchSpecificConfig newBranchConfig, NotificationChain msgs) {
		BranchSpecificConfig oldBranchConfig = branchConfig;
		branchConfig = newBranchConfig;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, org.eclipse.emf.common.notify.Notification.SET,
					BambooPackage.BRANCH_OVERRIDE__BRANCH_CONFIG, oldBranchConfig, newBranchConfig);
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
	public void setBranchConfig(BranchSpecificConfig newBranchConfig) {
		if (newBranchConfig != branchConfig) {
			NotificationChain msgs = null;
			if (branchConfig != null)
				msgs = ((InternalEObject) branchConfig).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - BambooPackage.BRANCH_OVERRIDE__BRANCH_CONFIG, null, msgs);
			if (newBranchConfig != null)
				msgs = ((InternalEObject) newBranchConfig).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - BambooPackage.BRANCH_OVERRIDE__BRANCH_CONFIG, null, msgs);
			msgs = basicSetBranchConfig(newBranchConfig, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, org.eclipse.emf.common.notify.Notification.SET,
					BambooPackage.BRANCH_OVERRIDE__BRANCH_CONFIG, newBranchConfig, newBranchConfig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case BambooPackage.BRANCH_OVERRIDE__STAGES:
			return ((InternalEList<?>) getStages()).basicRemove(otherEnd, msgs);
		case BambooPackage.BRANCH_OVERRIDE__JOBS:
			return ((InternalEList<?>) getJobs()).basicRemove(otherEnd, msgs);
		case BambooPackage.BRANCH_OVERRIDE__TRIGGERS:
			return ((InternalEList<?>) getTriggers()).basicRemove(otherEnd, msgs);
		case BambooPackage.BRANCH_OVERRIDE__NOTIFICATIONS:
			return ((InternalEList<?>) getNotifications()).basicRemove(otherEnd, msgs);
		case BambooPackage.BRANCH_OVERRIDE__VARIABLES:
			return ((InternalEList<?>) getVariables()).basicRemove(otherEnd, msgs);
		case BambooPackage.BRANCH_OVERRIDE__LABELS:
			return ((InternalEList<?>) getLabels()).basicRemove(otherEnd, msgs);
		case BambooPackage.BRANCH_OVERRIDE__DOCKER:
			return basicSetDocker(null, msgs);
		case BambooPackage.BRANCH_OVERRIDE__BRANCH_CONFIG:
			return basicSetBranchConfig(null, msgs);
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
		case BambooPackage.BRANCH_OVERRIDE__BRANCH_PATTERN:
			return getBranchPattern();
		case BambooPackage.BRANCH_OVERRIDE__STAGES:
			return getStages();
		case BambooPackage.BRANCH_OVERRIDE__JOBS:
			return getJobs();
		case BambooPackage.BRANCH_OVERRIDE__TRIGGERS:
			return getTriggers();
		case BambooPackage.BRANCH_OVERRIDE__NOTIFICATIONS:
			return getNotifications();
		case BambooPackage.BRANCH_OVERRIDE__VARIABLES:
			if (coreType)
				return getVariables();
			else
				return getVariables().map();
		case BambooPackage.BRANCH_OVERRIDE__LABELS:
			return getLabels();
		case BambooPackage.BRANCH_OVERRIDE__DOCKER:
			return getDocker();
		case BambooPackage.BRANCH_OVERRIDE__BRANCH_CONFIG:
			return getBranchConfig();
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
		case BambooPackage.BRANCH_OVERRIDE__BRANCH_PATTERN:
			setBranchPattern((String) newValue);
			return;
		case BambooPackage.BRANCH_OVERRIDE__STAGES:
			getStages().clear();
			getStages().addAll((Collection<? extends Stage>) newValue);
			return;
		case BambooPackage.BRANCH_OVERRIDE__JOBS:
			getJobs().clear();
			getJobs().addAll((Collection<? extends Job>) newValue);
			return;
		case BambooPackage.BRANCH_OVERRIDE__TRIGGERS:
			getTriggers().clear();
			getTriggers().addAll((Collection<? extends Trigger>) newValue);
			return;
		case BambooPackage.BRANCH_OVERRIDE__NOTIFICATIONS:
			getNotifications().clear();
			getNotifications().addAll((Collection<? extends Notification>) newValue);
			return;
		case BambooPackage.BRANCH_OVERRIDE__VARIABLES:
			((EStructuralFeature.Setting) getVariables()).set(newValue);
			return;
		case BambooPackage.BRANCH_OVERRIDE__LABELS:
			getLabels().clear();
			getLabels().addAll((Collection<? extends Label>) newValue);
			return;
		case BambooPackage.BRANCH_OVERRIDE__DOCKER:
			setDocker((DockerConfig) newValue);
			return;
		case BambooPackage.BRANCH_OVERRIDE__BRANCH_CONFIG:
			setBranchConfig((BranchSpecificConfig) newValue);
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
		case BambooPackage.BRANCH_OVERRIDE__BRANCH_PATTERN:
			setBranchPattern(BRANCH_PATTERN_EDEFAULT);
			return;
		case BambooPackage.BRANCH_OVERRIDE__STAGES:
			getStages().clear();
			return;
		case BambooPackage.BRANCH_OVERRIDE__JOBS:
			getJobs().clear();
			return;
		case BambooPackage.BRANCH_OVERRIDE__TRIGGERS:
			getTriggers().clear();
			return;
		case BambooPackage.BRANCH_OVERRIDE__NOTIFICATIONS:
			getNotifications().clear();
			return;
		case BambooPackage.BRANCH_OVERRIDE__VARIABLES:
			getVariables().clear();
			return;
		case BambooPackage.BRANCH_OVERRIDE__LABELS:
			getLabels().clear();
			return;
		case BambooPackage.BRANCH_OVERRIDE__DOCKER:
			setDocker((DockerConfig) null);
			return;
		case BambooPackage.BRANCH_OVERRIDE__BRANCH_CONFIG:
			setBranchConfig((BranchSpecificConfig) null);
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
		case BambooPackage.BRANCH_OVERRIDE__BRANCH_PATTERN:
			return BRANCH_PATTERN_EDEFAULT == null ? branchPattern != null
					: !BRANCH_PATTERN_EDEFAULT.equals(branchPattern);
		case BambooPackage.BRANCH_OVERRIDE__STAGES:
			return stages != null && !stages.isEmpty();
		case BambooPackage.BRANCH_OVERRIDE__JOBS:
			return jobs != null && !jobs.isEmpty();
		case BambooPackage.BRANCH_OVERRIDE__TRIGGERS:
			return triggers != null && !triggers.isEmpty();
		case BambooPackage.BRANCH_OVERRIDE__NOTIFICATIONS:
			return notifications != null && !notifications.isEmpty();
		case BambooPackage.BRANCH_OVERRIDE__VARIABLES:
			return variables != null && !variables.isEmpty();
		case BambooPackage.BRANCH_OVERRIDE__LABELS:
			return labels != null && !labels.isEmpty();
		case BambooPackage.BRANCH_OVERRIDE__DOCKER:
			return docker != null;
		case BambooPackage.BRANCH_OVERRIDE__BRANCH_CONFIG:
			return branchConfig != null;
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
		result.append(" (branchPattern: ");
		result.append(branchPattern);
		result.append(')');
		return result.toString();
	}

} //BranchOverrideImpl
