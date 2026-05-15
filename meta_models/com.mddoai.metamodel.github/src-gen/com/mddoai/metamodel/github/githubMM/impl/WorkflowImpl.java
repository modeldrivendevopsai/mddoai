/**
 */
package com.mddoai.metamodel.github.githubMM.impl;

import com.mddoai.metamodel.github.githubMM.ConcurrencyGroup;
import com.mddoai.metamodel.github.githubMM.Defaults;
import com.mddoai.metamodel.github.githubMM.Expression;
import com.mddoai.metamodel.github.githubMM.GithubMMPackage;
import com.mddoai.metamodel.github.githubMM.Job;
import com.mddoai.metamodel.github.githubMM.PERMISSIONS;
import com.mddoai.metamodel.github.githubMM.PERMISSION_SCOPES;
import com.mddoai.metamodel.github.githubMM.Trigger;
import com.mddoai.metamodel.github.githubMM.VariableDeclaration;
import com.mddoai.metamodel.github.githubMM.Workflow;

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
 * An implementation of the model object '<em><b>Workflow</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.WorkflowImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.WorkflowImpl#getRunName <em>Run Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.WorkflowImpl#getTriggers <em>Triggers</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.WorkflowImpl#getPermissions <em>Permissions</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.WorkflowImpl#getDefaults <em>Defaults</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.WorkflowImpl#getEnvironmentVariables <em>Environment Variables</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.WorkflowImpl#getConcurrencyGroup <em>Concurrency Group</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.WorkflowImpl#getJobs <em>Jobs</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WorkflowImpl extends MinimalEObjectImpl.Container implements Workflow {
	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected Expression name;

	/**
	 * The cached value of the '{@link #getRunName() <em>Run Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRunName()
	 * @generated
	 * @ordered
	 */
	protected Expression runName;

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
	 * The cached value of the '{@link #getPermissions() <em>Permissions</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPermissions()
	 * @generated
	 * @ordered
	 */
	protected EMap<PERMISSION_SCOPES, PERMISSIONS> permissions;

	/**
	 * The cached value of the '{@link #getDefaults() <em>Defaults</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaults()
	 * @generated
	 * @ordered
	 */
	protected Defaults defaults;

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
	 * The cached value of the '{@link #getConcurrencyGroup() <em>Concurrency Group</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConcurrencyGroup()
	 * @generated
	 * @ordered
	 */
	protected ConcurrencyGroup concurrencyGroup;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WorkflowImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GithubMMPackage.Literals.WORKFLOW;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetName(Expression newName, NotificationChain msgs) {
		Expression oldName = name;
		name = newName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GithubMMPackage.WORKFLOW__NAME, oldName, newName);
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
	public void setName(Expression newName) {
		if (newName != name) {
			NotificationChain msgs = null;
			if (name != null)
				msgs = ((InternalEObject) name).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.WORKFLOW__NAME, null, msgs);
			if (newName != null)
				msgs = ((InternalEObject) newName).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.WORKFLOW__NAME, null, msgs);
			msgs = basicSetName(newName, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.WORKFLOW__NAME, newName, newName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getRunName() {
		return runName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRunName(Expression newRunName, NotificationChain msgs) {
		Expression oldRunName = runName;
		runName = newRunName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GithubMMPackage.WORKFLOW__RUN_NAME, oldRunName, newRunName);
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
	public void setRunName(Expression newRunName) {
		if (newRunName != runName) {
			NotificationChain msgs = null;
			if (runName != null)
				msgs = ((InternalEObject) runName).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.WORKFLOW__RUN_NAME, null, msgs);
			if (newRunName != null)
				msgs = ((InternalEObject) newRunName).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.WORKFLOW__RUN_NAME, null, msgs);
			msgs = basicSetRunName(newRunName, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.WORKFLOW__RUN_NAME, newRunName,
					newRunName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Trigger> getTriggers() {
		if (triggers == null) {
			triggers = new EObjectContainmentEList<Trigger>(Trigger.class, this, GithubMMPackage.WORKFLOW__TRIGGERS);
		}
		return triggers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EMap<PERMISSION_SCOPES, PERMISSIONS> getPermissions() {
		if (permissions == null) {
			permissions = new EcoreEMap<PERMISSION_SCOPES, PERMISSIONS>(GithubMMPackage.Literals.PERMISSION,
					PermissionImpl.class, this, GithubMMPackage.WORKFLOW__PERMISSIONS);
		}
		return permissions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Defaults getDefaults() {
		return defaults;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDefaults(Defaults newDefaults, NotificationChain msgs) {
		Defaults oldDefaults = defaults;
		defaults = newDefaults;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GithubMMPackage.WORKFLOW__DEFAULTS, oldDefaults, newDefaults);
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
	public void setDefaults(Defaults newDefaults) {
		if (newDefaults != defaults) {
			NotificationChain msgs = null;
			if (defaults != null)
				msgs = ((InternalEObject) defaults).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.WORKFLOW__DEFAULTS, null, msgs);
			if (newDefaults != null)
				msgs = ((InternalEObject) newDefaults).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.WORKFLOW__DEFAULTS, null, msgs);
			msgs = basicSetDefaults(newDefaults, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.WORKFLOW__DEFAULTS, newDefaults,
					newDefaults));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EMap<VariableDeclaration, Expression> getEnvironmentVariables() {
		if (environmentVariables == null) {
			environmentVariables = new EcoreEMap<VariableDeclaration, Expression>(
					GithubMMPackage.Literals.VARIABLE_ASSIGNMENT, VariableAssignmentImpl.class, this,
					GithubMMPackage.WORKFLOW__ENVIRONMENT_VARIABLES);
		}
		return environmentVariables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ConcurrencyGroup getConcurrencyGroup() {
		return concurrencyGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConcurrencyGroup(ConcurrencyGroup newConcurrencyGroup, NotificationChain msgs) {
		ConcurrencyGroup oldConcurrencyGroup = concurrencyGroup;
		concurrencyGroup = newConcurrencyGroup;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GithubMMPackage.WORKFLOW__CONCURRENCY_GROUP, oldConcurrencyGroup, newConcurrencyGroup);
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
	public void setConcurrencyGroup(ConcurrencyGroup newConcurrencyGroup) {
		if (newConcurrencyGroup != concurrencyGroup) {
			NotificationChain msgs = null;
			if (concurrencyGroup != null)
				msgs = ((InternalEObject) concurrencyGroup).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.WORKFLOW__CONCURRENCY_GROUP, null, msgs);
			if (newConcurrencyGroup != null)
				msgs = ((InternalEObject) newConcurrencyGroup).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.WORKFLOW__CONCURRENCY_GROUP, null, msgs);
			msgs = basicSetConcurrencyGroup(newConcurrencyGroup, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.WORKFLOW__CONCURRENCY_GROUP,
					newConcurrencyGroup, newConcurrencyGroup));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Job> getJobs() {
		if (jobs == null) {
			jobs = new EObjectContainmentEList<Job>(Job.class, this, GithubMMPackage.WORKFLOW__JOBS);
		}
		return jobs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GithubMMPackage.WORKFLOW__NAME:
			return basicSetName(null, msgs);
		case GithubMMPackage.WORKFLOW__RUN_NAME:
			return basicSetRunName(null, msgs);
		case GithubMMPackage.WORKFLOW__TRIGGERS:
			return ((InternalEList<?>) getTriggers()).basicRemove(otherEnd, msgs);
		case GithubMMPackage.WORKFLOW__PERMISSIONS:
			return ((InternalEList<?>) getPermissions()).basicRemove(otherEnd, msgs);
		case GithubMMPackage.WORKFLOW__DEFAULTS:
			return basicSetDefaults(null, msgs);
		case GithubMMPackage.WORKFLOW__ENVIRONMENT_VARIABLES:
			return ((InternalEList<?>) getEnvironmentVariables()).basicRemove(otherEnd, msgs);
		case GithubMMPackage.WORKFLOW__CONCURRENCY_GROUP:
			return basicSetConcurrencyGroup(null, msgs);
		case GithubMMPackage.WORKFLOW__JOBS:
			return ((InternalEList<?>) getJobs()).basicRemove(otherEnd, msgs);
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
		case GithubMMPackage.WORKFLOW__NAME:
			return getName();
		case GithubMMPackage.WORKFLOW__RUN_NAME:
			return getRunName();
		case GithubMMPackage.WORKFLOW__TRIGGERS:
			return getTriggers();
		case GithubMMPackage.WORKFLOW__PERMISSIONS:
			if (coreType)
				return getPermissions();
			else
				return getPermissions().map();
		case GithubMMPackage.WORKFLOW__DEFAULTS:
			return getDefaults();
		case GithubMMPackage.WORKFLOW__ENVIRONMENT_VARIABLES:
			if (coreType)
				return getEnvironmentVariables();
			else
				return getEnvironmentVariables().map();
		case GithubMMPackage.WORKFLOW__CONCURRENCY_GROUP:
			return getConcurrencyGroup();
		case GithubMMPackage.WORKFLOW__JOBS:
			return getJobs();
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
		case GithubMMPackage.WORKFLOW__NAME:
			setName((Expression) newValue);
			return;
		case GithubMMPackage.WORKFLOW__RUN_NAME:
			setRunName((Expression) newValue);
			return;
		case GithubMMPackage.WORKFLOW__TRIGGERS:
			getTriggers().clear();
			getTriggers().addAll((Collection<? extends Trigger>) newValue);
			return;
		case GithubMMPackage.WORKFLOW__PERMISSIONS:
			((EStructuralFeature.Setting) getPermissions()).set(newValue);
			return;
		case GithubMMPackage.WORKFLOW__DEFAULTS:
			setDefaults((Defaults) newValue);
			return;
		case GithubMMPackage.WORKFLOW__ENVIRONMENT_VARIABLES:
			((EStructuralFeature.Setting) getEnvironmentVariables()).set(newValue);
			return;
		case GithubMMPackage.WORKFLOW__CONCURRENCY_GROUP:
			setConcurrencyGroup((ConcurrencyGroup) newValue);
			return;
		case GithubMMPackage.WORKFLOW__JOBS:
			getJobs().clear();
			getJobs().addAll((Collection<? extends Job>) newValue);
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
		case GithubMMPackage.WORKFLOW__NAME:
			setName((Expression) null);
			return;
		case GithubMMPackage.WORKFLOW__RUN_NAME:
			setRunName((Expression) null);
			return;
		case GithubMMPackage.WORKFLOW__TRIGGERS:
			getTriggers().clear();
			return;
		case GithubMMPackage.WORKFLOW__PERMISSIONS:
			getPermissions().clear();
			return;
		case GithubMMPackage.WORKFLOW__DEFAULTS:
			setDefaults((Defaults) null);
			return;
		case GithubMMPackage.WORKFLOW__ENVIRONMENT_VARIABLES:
			getEnvironmentVariables().clear();
			return;
		case GithubMMPackage.WORKFLOW__CONCURRENCY_GROUP:
			setConcurrencyGroup((ConcurrencyGroup) null);
			return;
		case GithubMMPackage.WORKFLOW__JOBS:
			getJobs().clear();
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
		case GithubMMPackage.WORKFLOW__NAME:
			return name != null;
		case GithubMMPackage.WORKFLOW__RUN_NAME:
			return runName != null;
		case GithubMMPackage.WORKFLOW__TRIGGERS:
			return triggers != null && !triggers.isEmpty();
		case GithubMMPackage.WORKFLOW__PERMISSIONS:
			return permissions != null && !permissions.isEmpty();
		case GithubMMPackage.WORKFLOW__DEFAULTS:
			return defaults != null;
		case GithubMMPackage.WORKFLOW__ENVIRONMENT_VARIABLES:
			return environmentVariables != null && !environmentVariables.isEmpty();
		case GithubMMPackage.WORKFLOW__CONCURRENCY_GROUP:
			return concurrencyGroup != null;
		case GithubMMPackage.WORKFLOW__JOBS:
			return jobs != null && !jobs.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //WorkflowImpl
