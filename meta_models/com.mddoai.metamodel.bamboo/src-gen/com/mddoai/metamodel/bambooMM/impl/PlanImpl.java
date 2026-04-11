/**
 */
package com.mddoai.metamodel.bambooMM.impl;

import com.mddoai.metamodel.bambooMM.BambooPackage;
import com.mddoai.metamodel.bambooMM.BranchManagement;
import com.mddoai.metamodel.bambooMM.BranchOverride;
import com.mddoai.metamodel.bambooMM.Dependencies;
import com.mddoai.metamodel.bambooMM.DockerConfig;
import com.mddoai.metamodel.bambooMM.Job;
import com.mddoai.metamodel.bambooMM.Label;
import com.mddoai.metamodel.bambooMM.Notification;
import com.mddoai.metamodel.bambooMM.OtherConfig;
import com.mddoai.metamodel.bambooMM.Plan;
import com.mddoai.metamodel.bambooMM.PlanPermissionEntry;
import com.mddoai.metamodel.bambooMM.Repository;
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
 * An implementation of the model object '<em><b>Plan</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.PlanImpl#getProjectKey <em>Project Key</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.PlanImpl#getKey <em>Key</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.PlanImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.PlanImpl#getServerName <em>Server Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.PlanImpl#getStages <em>Stages</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.PlanImpl#getJobs <em>Jobs</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.PlanImpl#getTriggers <em>Triggers</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.PlanImpl#getRepositories <em>Repositories</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.PlanImpl#getVariables <em>Variables</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.PlanImpl#getNotifications <em>Notifications</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.PlanImpl#getLabels <em>Labels</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.PlanImpl#getBranches <em>Branches</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.PlanImpl#getDependencies <em>Dependencies</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.PlanImpl#getDocker <em>Docker</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.PlanImpl#getBranchOverrides <em>Branch Overrides</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.PlanImpl#getOther <em>Other</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.PlanImpl#getPermissions <em>Permissions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PlanImpl extends MinimalEObjectImpl.Container implements Plan {
	/**
	 * The default value of the '{@link #getProjectKey() <em>Project Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProjectKey()
	 * @generated
	 * @ordered
	 */
	protected static final String PROJECT_KEY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProjectKey() <em>Project Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProjectKey()
	 * @generated
	 * @ordered
	 */
	protected String projectKey = PROJECT_KEY_EDEFAULT;

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
	 * The default value of the '{@link #getServerName() <em>Server Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServerName()
	 * @generated
	 * @ordered
	 */
	protected static final String SERVER_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getServerName() <em>Server Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServerName()
	 * @generated
	 * @ordered
	 */
	protected String serverName = SERVER_NAME_EDEFAULT;

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
	 * The cached value of the '{@link #getRepositories() <em>Repositories</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepositories()
	 * @generated
	 * @ordered
	 */
	protected EList<Repository> repositories;

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
	 * The cached value of the '{@link #getNotifications() <em>Notifications</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNotifications()
	 * @generated
	 * @ordered
	 */
	protected EList<Notification> notifications;

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
	 * The cached value of the '{@link #getBranches() <em>Branches</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBranches()
	 * @generated
	 * @ordered
	 */
	protected BranchManagement branches;

	/**
	 * The cached value of the '{@link #getDependencies() <em>Dependencies</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDependencies()
	 * @generated
	 * @ordered
	 */
	protected Dependencies dependencies;

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
	 * The cached value of the '{@link #getBranchOverrides() <em>Branch Overrides</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBranchOverrides()
	 * @generated
	 * @ordered
	 */
	protected EList<BranchOverride> branchOverrides;

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
	 * The cached value of the '{@link #getPermissions() <em>Permissions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPermissions()
	 * @generated
	 * @ordered
	 */
	protected EList<PlanPermissionEntry> permissions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PlanImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BambooPackage.Literals.PLAN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getProjectKey() {
		return projectKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setProjectKey(String newProjectKey) {
		String oldProjectKey = projectKey;
		projectKey = newProjectKey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, org.eclipse.emf.common.notify.Notification.SET,
					BambooPackage.PLAN__PROJECT_KEY, oldProjectKey, projectKey));
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
			eNotify(new ENotificationImpl(this, org.eclipse.emf.common.notify.Notification.SET, BambooPackage.PLAN__KEY,
					oldKey, key));
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
					BambooPackage.PLAN__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getServerName() {
		return serverName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setServerName(String newServerName) {
		String oldServerName = serverName;
		serverName = newServerName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, org.eclipse.emf.common.notify.Notification.SET,
					BambooPackage.PLAN__SERVER_NAME, oldServerName, serverName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Stage> getStages() {
		if (stages == null) {
			stages = new EObjectContainmentEList<Stage>(Stage.class, this, BambooPackage.PLAN__STAGES);
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
			jobs = new EObjectContainmentEList<Job>(Job.class, this, BambooPackage.PLAN__JOBS);
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
			triggers = new EObjectContainmentEList<Trigger>(Trigger.class, this, BambooPackage.PLAN__TRIGGERS);
		}
		return triggers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Repository> getRepositories() {
		if (repositories == null) {
			repositories = new EObjectContainmentEList<Repository>(Repository.class, this,
					BambooPackage.PLAN__REPOSITORIES);
		}
		return repositories;
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
					VariableAssignmentImpl.class, this, BambooPackage.PLAN__VARIABLES);
		}
		return variables;
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
					BambooPackage.PLAN__NOTIFICATIONS);
		}
		return notifications;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Label> getLabels() {
		if (labels == null) {
			labels = new EObjectContainmentEList<Label>(Label.class, this, BambooPackage.PLAN__LABELS);
		}
		return labels;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BranchManagement getBranches() {
		return branches;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBranches(BranchManagement newBranches, NotificationChain msgs) {
		BranchManagement oldBranches = branches;
		branches = newBranches;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, org.eclipse.emf.common.notify.Notification.SET,
					BambooPackage.PLAN__BRANCHES, oldBranches, newBranches);
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
	public void setBranches(BranchManagement newBranches) {
		if (newBranches != branches) {
			NotificationChain msgs = null;
			if (branches != null)
				msgs = ((InternalEObject) branches).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - BambooPackage.PLAN__BRANCHES, null, msgs);
			if (newBranches != null)
				msgs = ((InternalEObject) newBranches).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - BambooPackage.PLAN__BRANCHES, null, msgs);
			msgs = basicSetBranches(newBranches, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, org.eclipse.emf.common.notify.Notification.SET,
					BambooPackage.PLAN__BRANCHES, newBranches, newBranches));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Dependencies getDependencies() {
		return dependencies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDependencies(Dependencies newDependencies, NotificationChain msgs) {
		Dependencies oldDependencies = dependencies;
		dependencies = newDependencies;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, org.eclipse.emf.common.notify.Notification.SET,
					BambooPackage.PLAN__DEPENDENCIES, oldDependencies, newDependencies);
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
	public void setDependencies(Dependencies newDependencies) {
		if (newDependencies != dependencies) {
			NotificationChain msgs = null;
			if (dependencies != null)
				msgs = ((InternalEObject) dependencies).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - BambooPackage.PLAN__DEPENDENCIES, null, msgs);
			if (newDependencies != null)
				msgs = ((InternalEObject) newDependencies).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - BambooPackage.PLAN__DEPENDENCIES, null, msgs);
			msgs = basicSetDependencies(newDependencies, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, org.eclipse.emf.common.notify.Notification.SET,
					BambooPackage.PLAN__DEPENDENCIES, newDependencies, newDependencies));
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
					BambooPackage.PLAN__DOCKER, oldDocker, newDocker);
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
						EOPPOSITE_FEATURE_BASE - BambooPackage.PLAN__DOCKER, null, msgs);
			if (newDocker != null)
				msgs = ((InternalEObject) newDocker).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - BambooPackage.PLAN__DOCKER, null, msgs);
			msgs = basicSetDocker(newDocker, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, org.eclipse.emf.common.notify.Notification.SET,
					BambooPackage.PLAN__DOCKER, newDocker, newDocker));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<BranchOverride> getBranchOverrides() {
		if (branchOverrides == null) {
			branchOverrides = new EObjectContainmentEList<BranchOverride>(BranchOverride.class, this,
					BambooPackage.PLAN__BRANCH_OVERRIDES);
		}
		return branchOverrides;
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
			ENotificationImpl notification = new ENotificationImpl(this, org.eclipse.emf.common.notify.Notification.SET,
					BambooPackage.PLAN__OTHER, oldOther, newOther);
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
				msgs = ((InternalEObject) other).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - BambooPackage.PLAN__OTHER, null, msgs);
			if (newOther != null)
				msgs = ((InternalEObject) newOther).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - BambooPackage.PLAN__OTHER, null, msgs);
			msgs = basicSetOther(newOther, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, org.eclipse.emf.common.notify.Notification.SET,
					BambooPackage.PLAN__OTHER, newOther, newOther));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<PlanPermissionEntry> getPermissions() {
		if (permissions == null) {
			permissions = new EObjectContainmentEList<PlanPermissionEntry>(PlanPermissionEntry.class, this,
					BambooPackage.PLAN__PERMISSIONS);
		}
		return permissions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case BambooPackage.PLAN__STAGES:
			return ((InternalEList<?>) getStages()).basicRemove(otherEnd, msgs);
		case BambooPackage.PLAN__JOBS:
			return ((InternalEList<?>) getJobs()).basicRemove(otherEnd, msgs);
		case BambooPackage.PLAN__TRIGGERS:
			return ((InternalEList<?>) getTriggers()).basicRemove(otherEnd, msgs);
		case BambooPackage.PLAN__REPOSITORIES:
			return ((InternalEList<?>) getRepositories()).basicRemove(otherEnd, msgs);
		case BambooPackage.PLAN__VARIABLES:
			return ((InternalEList<?>) getVariables()).basicRemove(otherEnd, msgs);
		case BambooPackage.PLAN__NOTIFICATIONS:
			return ((InternalEList<?>) getNotifications()).basicRemove(otherEnd, msgs);
		case BambooPackage.PLAN__LABELS:
			return ((InternalEList<?>) getLabels()).basicRemove(otherEnd, msgs);
		case BambooPackage.PLAN__BRANCHES:
			return basicSetBranches(null, msgs);
		case BambooPackage.PLAN__DEPENDENCIES:
			return basicSetDependencies(null, msgs);
		case BambooPackage.PLAN__DOCKER:
			return basicSetDocker(null, msgs);
		case BambooPackage.PLAN__BRANCH_OVERRIDES:
			return ((InternalEList<?>) getBranchOverrides()).basicRemove(otherEnd, msgs);
		case BambooPackage.PLAN__OTHER:
			return basicSetOther(null, msgs);
		case BambooPackage.PLAN__PERMISSIONS:
			return ((InternalEList<?>) getPermissions()).basicRemove(otherEnd, msgs);
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
		case BambooPackage.PLAN__PROJECT_KEY:
			return getProjectKey();
		case BambooPackage.PLAN__KEY:
			return getKey();
		case BambooPackage.PLAN__NAME:
			return getName();
		case BambooPackage.PLAN__SERVER_NAME:
			return getServerName();
		case BambooPackage.PLAN__STAGES:
			return getStages();
		case BambooPackage.PLAN__JOBS:
			return getJobs();
		case BambooPackage.PLAN__TRIGGERS:
			return getTriggers();
		case BambooPackage.PLAN__REPOSITORIES:
			return getRepositories();
		case BambooPackage.PLAN__VARIABLES:
			if (coreType)
				return getVariables();
			else
				return getVariables().map();
		case BambooPackage.PLAN__NOTIFICATIONS:
			return getNotifications();
		case BambooPackage.PLAN__LABELS:
			return getLabels();
		case BambooPackage.PLAN__BRANCHES:
			return getBranches();
		case BambooPackage.PLAN__DEPENDENCIES:
			return getDependencies();
		case BambooPackage.PLAN__DOCKER:
			return getDocker();
		case BambooPackage.PLAN__BRANCH_OVERRIDES:
			return getBranchOverrides();
		case BambooPackage.PLAN__OTHER:
			return getOther();
		case BambooPackage.PLAN__PERMISSIONS:
			return getPermissions();
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
		case BambooPackage.PLAN__PROJECT_KEY:
			setProjectKey((String) newValue);
			return;
		case BambooPackage.PLAN__KEY:
			setKey((String) newValue);
			return;
		case BambooPackage.PLAN__NAME:
			setName((String) newValue);
			return;
		case BambooPackage.PLAN__SERVER_NAME:
			setServerName((String) newValue);
			return;
		case BambooPackage.PLAN__STAGES:
			getStages().clear();
			getStages().addAll((Collection<? extends Stage>) newValue);
			return;
		case BambooPackage.PLAN__JOBS:
			getJobs().clear();
			getJobs().addAll((Collection<? extends Job>) newValue);
			return;
		case BambooPackage.PLAN__TRIGGERS:
			getTriggers().clear();
			getTriggers().addAll((Collection<? extends Trigger>) newValue);
			return;
		case BambooPackage.PLAN__REPOSITORIES:
			getRepositories().clear();
			getRepositories().addAll((Collection<? extends Repository>) newValue);
			return;
		case BambooPackage.PLAN__VARIABLES:
			((EStructuralFeature.Setting) getVariables()).set(newValue);
			return;
		case BambooPackage.PLAN__NOTIFICATIONS:
			getNotifications().clear();
			getNotifications().addAll((Collection<? extends Notification>) newValue);
			return;
		case BambooPackage.PLAN__LABELS:
			getLabels().clear();
			getLabels().addAll((Collection<? extends Label>) newValue);
			return;
		case BambooPackage.PLAN__BRANCHES:
			setBranches((BranchManagement) newValue);
			return;
		case BambooPackage.PLAN__DEPENDENCIES:
			setDependencies((Dependencies) newValue);
			return;
		case BambooPackage.PLAN__DOCKER:
			setDocker((DockerConfig) newValue);
			return;
		case BambooPackage.PLAN__BRANCH_OVERRIDES:
			getBranchOverrides().clear();
			getBranchOverrides().addAll((Collection<? extends BranchOverride>) newValue);
			return;
		case BambooPackage.PLAN__OTHER:
			setOther((OtherConfig) newValue);
			return;
		case BambooPackage.PLAN__PERMISSIONS:
			getPermissions().clear();
			getPermissions().addAll((Collection<? extends PlanPermissionEntry>) newValue);
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
		case BambooPackage.PLAN__PROJECT_KEY:
			setProjectKey(PROJECT_KEY_EDEFAULT);
			return;
		case BambooPackage.PLAN__KEY:
			setKey(KEY_EDEFAULT);
			return;
		case BambooPackage.PLAN__NAME:
			setName(NAME_EDEFAULT);
			return;
		case BambooPackage.PLAN__SERVER_NAME:
			setServerName(SERVER_NAME_EDEFAULT);
			return;
		case BambooPackage.PLAN__STAGES:
			getStages().clear();
			return;
		case BambooPackage.PLAN__JOBS:
			getJobs().clear();
			return;
		case BambooPackage.PLAN__TRIGGERS:
			getTriggers().clear();
			return;
		case BambooPackage.PLAN__REPOSITORIES:
			getRepositories().clear();
			return;
		case BambooPackage.PLAN__VARIABLES:
			getVariables().clear();
			return;
		case BambooPackage.PLAN__NOTIFICATIONS:
			getNotifications().clear();
			return;
		case BambooPackage.PLAN__LABELS:
			getLabels().clear();
			return;
		case BambooPackage.PLAN__BRANCHES:
			setBranches((BranchManagement) null);
			return;
		case BambooPackage.PLAN__DEPENDENCIES:
			setDependencies((Dependencies) null);
			return;
		case BambooPackage.PLAN__DOCKER:
			setDocker((DockerConfig) null);
			return;
		case BambooPackage.PLAN__BRANCH_OVERRIDES:
			getBranchOverrides().clear();
			return;
		case BambooPackage.PLAN__OTHER:
			setOther((OtherConfig) null);
			return;
		case BambooPackage.PLAN__PERMISSIONS:
			getPermissions().clear();
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
		case BambooPackage.PLAN__PROJECT_KEY:
			return PROJECT_KEY_EDEFAULT == null ? projectKey != null : !PROJECT_KEY_EDEFAULT.equals(projectKey);
		case BambooPackage.PLAN__KEY:
			return KEY_EDEFAULT == null ? key != null : !KEY_EDEFAULT.equals(key);
		case BambooPackage.PLAN__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case BambooPackage.PLAN__SERVER_NAME:
			return SERVER_NAME_EDEFAULT == null ? serverName != null : !SERVER_NAME_EDEFAULT.equals(serverName);
		case BambooPackage.PLAN__STAGES:
			return stages != null && !stages.isEmpty();
		case BambooPackage.PLAN__JOBS:
			return jobs != null && !jobs.isEmpty();
		case BambooPackage.PLAN__TRIGGERS:
			return triggers != null && !triggers.isEmpty();
		case BambooPackage.PLAN__REPOSITORIES:
			return repositories != null && !repositories.isEmpty();
		case BambooPackage.PLAN__VARIABLES:
			return variables != null && !variables.isEmpty();
		case BambooPackage.PLAN__NOTIFICATIONS:
			return notifications != null && !notifications.isEmpty();
		case BambooPackage.PLAN__LABELS:
			return labels != null && !labels.isEmpty();
		case BambooPackage.PLAN__BRANCHES:
			return branches != null;
		case BambooPackage.PLAN__DEPENDENCIES:
			return dependencies != null;
		case BambooPackage.PLAN__DOCKER:
			return docker != null;
		case BambooPackage.PLAN__BRANCH_OVERRIDES:
			return branchOverrides != null && !branchOverrides.isEmpty();
		case BambooPackage.PLAN__OTHER:
			return other != null;
		case BambooPackage.PLAN__PERMISSIONS:
			return permissions != null && !permissions.isEmpty();
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
		result.append(" (projectKey: ");
		result.append(projectKey);
		result.append(", key: ");
		result.append(key);
		result.append(", name: ");
		result.append(name);
		result.append(", serverName: ");
		result.append(serverName);
		result.append(')');
		return result.toString();
	}

} //PlanImpl
