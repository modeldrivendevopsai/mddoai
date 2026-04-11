/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.CronSchedule;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.Extends;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.JobElement;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.LOCK_BEHAVIOR;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.Parameter;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.Pool;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.PrTrigger;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.Resources;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.StageElement;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.Step;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.Trigger;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableDefinition;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.Workspace;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pipeline</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineImpl#getAppendCommitMessageToRunName <em>Append Commit Message To Run Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineImpl#getLockBehavior <em>Lock Behavior</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineImpl#getTrigger <em>Trigger</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineImpl#getPr <em>Pr</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineImpl#getSchedules <em>Schedules</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineImpl#getVariables <em>Variables</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineImpl#getPool <em>Pool</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineImpl#getResources <em>Resources</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineImpl#getStages <em>Stages</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineImpl#getJobs <em>Jobs</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineImpl#getSteps <em>Steps</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineImpl#getExtends <em>Extends</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PipelineImpl#getWorkspace <em>Workspace</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PipelineImpl extends MinimalEObjectImpl.Container implements Pipeline {
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
	 * The default value of the '{@link #getAppendCommitMessageToRunName() <em>Append Commit Message To Run Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAppendCommitMessageToRunName()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean APPEND_COMMIT_MESSAGE_TO_RUN_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAppendCommitMessageToRunName() <em>Append Commit Message To Run Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAppendCommitMessageToRunName()
	 * @generated
	 * @ordered
	 */
	protected Boolean appendCommitMessageToRunName = APPEND_COMMIT_MESSAGE_TO_RUN_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getLockBehavior() <em>Lock Behavior</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLockBehavior()
	 * @generated
	 * @ordered
	 */
	protected static final LOCK_BEHAVIOR LOCK_BEHAVIOR_EDEFAULT = LOCK_BEHAVIOR.SEQUENTIAL;

	/**
	 * The cached value of the '{@link #getLockBehavior() <em>Lock Behavior</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLockBehavior()
	 * @generated
	 * @ordered
	 */
	protected LOCK_BEHAVIOR lockBehavior = LOCK_BEHAVIOR_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTrigger() <em>Trigger</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrigger()
	 * @generated
	 * @ordered
	 */
	protected Trigger trigger;

	/**
	 * The cached value of the '{@link #getPr() <em>Pr</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPr()
	 * @generated
	 * @ordered
	 */
	protected PrTrigger pr;

	/**
	 * The cached value of the '{@link #getSchedules() <em>Schedules</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSchedules()
	 * @generated
	 * @ordered
	 */
	protected EList<CronSchedule> schedules;

	/**
	 * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<Parameter> parameters;

	/**
	 * The cached value of the '{@link #getVariables() <em>Variables</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariables()
	 * @generated
	 * @ordered
	 */
	protected EList<VariableDefinition> variables;

	/**
	 * The cached value of the '{@link #getPool() <em>Pool</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPool()
	 * @generated
	 * @ordered
	 */
	protected Pool pool;

	/**
	 * The cached value of the '{@link #getResources() <em>Resources</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResources()
	 * @generated
	 * @ordered
	 */
	protected Resources resources;

	/**
	 * The cached value of the '{@link #getStages() <em>Stages</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStages()
	 * @generated
	 * @ordered
	 */
	protected EList<StageElement> stages;

	/**
	 * The cached value of the '{@link #getJobs() <em>Jobs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJobs()
	 * @generated
	 * @ordered
	 */
	protected EList<JobElement> jobs;

	/**
	 * The cached value of the '{@link #getSteps() <em>Steps</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSteps()
	 * @generated
	 * @ordered
	 */
	protected EList<Step> steps;

	/**
	 * The cached value of the '{@link #getExtends() <em>Extends</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtends()
	 * @generated
	 * @ordered
	 */
	protected Extends extends_;

	/**
	 * The cached value of the '{@link #getWorkspace() <em>Workspace</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkspace()
	 * @generated
	 * @ordered
	 */
	protected Workspace workspace;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PipelineImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AzuredevopsMMPackage.Literals.PIPELINE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.PIPELINE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getAppendCommitMessageToRunName() {
		return appendCommitMessageToRunName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAppendCommitMessageToRunName(Boolean newAppendCommitMessageToRunName) {
		Boolean oldAppendCommitMessageToRunName = appendCommitMessageToRunName;
		appendCommitMessageToRunName = newAppendCommitMessageToRunName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.PIPELINE__APPEND_COMMIT_MESSAGE_TO_RUN_NAME, oldAppendCommitMessageToRunName,
					appendCommitMessageToRunName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LOCK_BEHAVIOR getLockBehavior() {
		return lockBehavior;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLockBehavior(LOCK_BEHAVIOR newLockBehavior) {
		LOCK_BEHAVIOR oldLockBehavior = lockBehavior;
		lockBehavior = newLockBehavior == null ? LOCK_BEHAVIOR_EDEFAULT : newLockBehavior;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.PIPELINE__LOCK_BEHAVIOR,
					oldLockBehavior, lockBehavior));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Trigger getTrigger() {
		return trigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTrigger(Trigger newTrigger, NotificationChain msgs) {
		Trigger oldTrigger = trigger;
		trigger = newTrigger;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.PIPELINE__TRIGGER, oldTrigger, newTrigger);
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
	public void setTrigger(Trigger newTrigger) {
		if (newTrigger != trigger) {
			NotificationChain msgs = null;
			if (trigger != null)
				msgs = ((InternalEObject) trigger).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.PIPELINE__TRIGGER, null, msgs);
			if (newTrigger != null)
				msgs = ((InternalEObject) newTrigger).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.PIPELINE__TRIGGER, null, msgs);
			msgs = basicSetTrigger(newTrigger, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.PIPELINE__TRIGGER, newTrigger,
					newTrigger));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PrTrigger getPr() {
		return pr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPr(PrTrigger newPr, NotificationChain msgs) {
		PrTrigger oldPr = pr;
		pr = newPr;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.PIPELINE__PR, oldPr, newPr);
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
	public void setPr(PrTrigger newPr) {
		if (newPr != pr) {
			NotificationChain msgs = null;
			if (pr != null)
				msgs = ((InternalEObject) pr).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.PIPELINE__PR, null, msgs);
			if (newPr != null)
				msgs = ((InternalEObject) newPr).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.PIPELINE__PR, null, msgs);
			msgs = basicSetPr(newPr, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.PIPELINE__PR, newPr, newPr));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<CronSchedule> getSchedules() {
		if (schedules == null) {
			schedules = new EObjectContainmentEList<CronSchedule>(CronSchedule.class, this,
					AzuredevopsMMPackage.PIPELINE__SCHEDULES);
		}
		return schedules;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Parameter> getParameters() {
		if (parameters == null) {
			parameters = new EObjectContainmentEList<Parameter>(Parameter.class, this,
					AzuredevopsMMPackage.PIPELINE__PARAMETERS);
		}
		return parameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<VariableDefinition> getVariables() {
		if (variables == null) {
			variables = new EObjectContainmentEList<VariableDefinition>(VariableDefinition.class, this,
					AzuredevopsMMPackage.PIPELINE__VARIABLES);
		}
		return variables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Pool getPool() {
		return pool;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPool(Pool newPool, NotificationChain msgs) {
		Pool oldPool = pool;
		pool = newPool;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.PIPELINE__POOL, oldPool, newPool);
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
	public void setPool(Pool newPool) {
		if (newPool != pool) {
			NotificationChain msgs = null;
			if (pool != null)
				msgs = ((InternalEObject) pool).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.PIPELINE__POOL, null, msgs);
			if (newPool != null)
				msgs = ((InternalEObject) newPool).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.PIPELINE__POOL, null, msgs);
			msgs = basicSetPool(newPool, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.PIPELINE__POOL, newPool,
					newPool));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Resources getResources() {
		return resources;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetResources(Resources newResources, NotificationChain msgs) {
		Resources oldResources = resources;
		resources = newResources;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.PIPELINE__RESOURCES, oldResources, newResources);
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
	public void setResources(Resources newResources) {
		if (newResources != resources) {
			NotificationChain msgs = null;
			if (resources != null)
				msgs = ((InternalEObject) resources).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.PIPELINE__RESOURCES, null, msgs);
			if (newResources != null)
				msgs = ((InternalEObject) newResources).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.PIPELINE__RESOURCES, null, msgs);
			msgs = basicSetResources(newResources, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.PIPELINE__RESOURCES,
					newResources, newResources));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<StageElement> getStages() {
		if (stages == null) {
			stages = new EObjectContainmentEList<StageElement>(StageElement.class, this,
					AzuredevopsMMPackage.PIPELINE__STAGES);
		}
		return stages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<JobElement> getJobs() {
		if (jobs == null) {
			jobs = new EObjectContainmentEList<JobElement>(JobElement.class, this, AzuredevopsMMPackage.PIPELINE__JOBS);
		}
		return jobs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Step> getSteps() {
		if (steps == null) {
			steps = new EObjectContainmentEList<Step>(Step.class, this, AzuredevopsMMPackage.PIPELINE__STEPS);
		}
		return steps;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Extends getExtends() {
		return extends_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExtends(Extends newExtends, NotificationChain msgs) {
		Extends oldExtends = extends_;
		extends_ = newExtends;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.PIPELINE__EXTENDS, oldExtends, newExtends);
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
	public void setExtends(Extends newExtends) {
		if (newExtends != extends_) {
			NotificationChain msgs = null;
			if (extends_ != null)
				msgs = ((InternalEObject) extends_).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.PIPELINE__EXTENDS, null, msgs);
			if (newExtends != null)
				msgs = ((InternalEObject) newExtends).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.PIPELINE__EXTENDS, null, msgs);
			msgs = basicSetExtends(newExtends, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.PIPELINE__EXTENDS, newExtends,
					newExtends));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Workspace getWorkspace() {
		return workspace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWorkspace(Workspace newWorkspace, NotificationChain msgs) {
		Workspace oldWorkspace = workspace;
		workspace = newWorkspace;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.PIPELINE__WORKSPACE, oldWorkspace, newWorkspace);
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
	public void setWorkspace(Workspace newWorkspace) {
		if (newWorkspace != workspace) {
			NotificationChain msgs = null;
			if (workspace != null)
				msgs = ((InternalEObject) workspace).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.PIPELINE__WORKSPACE, null, msgs);
			if (newWorkspace != null)
				msgs = ((InternalEObject) newWorkspace).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.PIPELINE__WORKSPACE, null, msgs);
			msgs = basicSetWorkspace(newWorkspace, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.PIPELINE__WORKSPACE,
					newWorkspace, newWorkspace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case AzuredevopsMMPackage.PIPELINE__TRIGGER:
			return basicSetTrigger(null, msgs);
		case AzuredevopsMMPackage.PIPELINE__PR:
			return basicSetPr(null, msgs);
		case AzuredevopsMMPackage.PIPELINE__SCHEDULES:
			return ((InternalEList<?>) getSchedules()).basicRemove(otherEnd, msgs);
		case AzuredevopsMMPackage.PIPELINE__PARAMETERS:
			return ((InternalEList<?>) getParameters()).basicRemove(otherEnd, msgs);
		case AzuredevopsMMPackage.PIPELINE__VARIABLES:
			return ((InternalEList<?>) getVariables()).basicRemove(otherEnd, msgs);
		case AzuredevopsMMPackage.PIPELINE__POOL:
			return basicSetPool(null, msgs);
		case AzuredevopsMMPackage.PIPELINE__RESOURCES:
			return basicSetResources(null, msgs);
		case AzuredevopsMMPackage.PIPELINE__STAGES:
			return ((InternalEList<?>) getStages()).basicRemove(otherEnd, msgs);
		case AzuredevopsMMPackage.PIPELINE__JOBS:
			return ((InternalEList<?>) getJobs()).basicRemove(otherEnd, msgs);
		case AzuredevopsMMPackage.PIPELINE__STEPS:
			return ((InternalEList<?>) getSteps()).basicRemove(otherEnd, msgs);
		case AzuredevopsMMPackage.PIPELINE__EXTENDS:
			return basicSetExtends(null, msgs);
		case AzuredevopsMMPackage.PIPELINE__WORKSPACE:
			return basicSetWorkspace(null, msgs);
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
		case AzuredevopsMMPackage.PIPELINE__NAME:
			return getName();
		case AzuredevopsMMPackage.PIPELINE__APPEND_COMMIT_MESSAGE_TO_RUN_NAME:
			return getAppendCommitMessageToRunName();
		case AzuredevopsMMPackage.PIPELINE__LOCK_BEHAVIOR:
			return getLockBehavior();
		case AzuredevopsMMPackage.PIPELINE__TRIGGER:
			return getTrigger();
		case AzuredevopsMMPackage.PIPELINE__PR:
			return getPr();
		case AzuredevopsMMPackage.PIPELINE__SCHEDULES:
			return getSchedules();
		case AzuredevopsMMPackage.PIPELINE__PARAMETERS:
			return getParameters();
		case AzuredevopsMMPackage.PIPELINE__VARIABLES:
			return getVariables();
		case AzuredevopsMMPackage.PIPELINE__POOL:
			return getPool();
		case AzuredevopsMMPackage.PIPELINE__RESOURCES:
			return getResources();
		case AzuredevopsMMPackage.PIPELINE__STAGES:
			return getStages();
		case AzuredevopsMMPackage.PIPELINE__JOBS:
			return getJobs();
		case AzuredevopsMMPackage.PIPELINE__STEPS:
			return getSteps();
		case AzuredevopsMMPackage.PIPELINE__EXTENDS:
			return getExtends();
		case AzuredevopsMMPackage.PIPELINE__WORKSPACE:
			return getWorkspace();
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
		case AzuredevopsMMPackage.PIPELINE__NAME:
			setName((String) newValue);
			return;
		case AzuredevopsMMPackage.PIPELINE__APPEND_COMMIT_MESSAGE_TO_RUN_NAME:
			setAppendCommitMessageToRunName((Boolean) newValue);
			return;
		case AzuredevopsMMPackage.PIPELINE__LOCK_BEHAVIOR:
			setLockBehavior((LOCK_BEHAVIOR) newValue);
			return;
		case AzuredevopsMMPackage.PIPELINE__TRIGGER:
			setTrigger((Trigger) newValue);
			return;
		case AzuredevopsMMPackage.PIPELINE__PR:
			setPr((PrTrigger) newValue);
			return;
		case AzuredevopsMMPackage.PIPELINE__SCHEDULES:
			getSchedules().clear();
			getSchedules().addAll((Collection<? extends CronSchedule>) newValue);
			return;
		case AzuredevopsMMPackage.PIPELINE__PARAMETERS:
			getParameters().clear();
			getParameters().addAll((Collection<? extends Parameter>) newValue);
			return;
		case AzuredevopsMMPackage.PIPELINE__VARIABLES:
			getVariables().clear();
			getVariables().addAll((Collection<? extends VariableDefinition>) newValue);
			return;
		case AzuredevopsMMPackage.PIPELINE__POOL:
			setPool((Pool) newValue);
			return;
		case AzuredevopsMMPackage.PIPELINE__RESOURCES:
			setResources((Resources) newValue);
			return;
		case AzuredevopsMMPackage.PIPELINE__STAGES:
			getStages().clear();
			getStages().addAll((Collection<? extends StageElement>) newValue);
			return;
		case AzuredevopsMMPackage.PIPELINE__JOBS:
			getJobs().clear();
			getJobs().addAll((Collection<? extends JobElement>) newValue);
			return;
		case AzuredevopsMMPackage.PIPELINE__STEPS:
			getSteps().clear();
			getSteps().addAll((Collection<? extends Step>) newValue);
			return;
		case AzuredevopsMMPackage.PIPELINE__EXTENDS:
			setExtends((Extends) newValue);
			return;
		case AzuredevopsMMPackage.PIPELINE__WORKSPACE:
			setWorkspace((Workspace) newValue);
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
		case AzuredevopsMMPackage.PIPELINE__NAME:
			setName(NAME_EDEFAULT);
			return;
		case AzuredevopsMMPackage.PIPELINE__APPEND_COMMIT_MESSAGE_TO_RUN_NAME:
			setAppendCommitMessageToRunName(APPEND_COMMIT_MESSAGE_TO_RUN_NAME_EDEFAULT);
			return;
		case AzuredevopsMMPackage.PIPELINE__LOCK_BEHAVIOR:
			setLockBehavior(LOCK_BEHAVIOR_EDEFAULT);
			return;
		case AzuredevopsMMPackage.PIPELINE__TRIGGER:
			setTrigger((Trigger) null);
			return;
		case AzuredevopsMMPackage.PIPELINE__PR:
			setPr((PrTrigger) null);
			return;
		case AzuredevopsMMPackage.PIPELINE__SCHEDULES:
			getSchedules().clear();
			return;
		case AzuredevopsMMPackage.PIPELINE__PARAMETERS:
			getParameters().clear();
			return;
		case AzuredevopsMMPackage.PIPELINE__VARIABLES:
			getVariables().clear();
			return;
		case AzuredevopsMMPackage.PIPELINE__POOL:
			setPool((Pool) null);
			return;
		case AzuredevopsMMPackage.PIPELINE__RESOURCES:
			setResources((Resources) null);
			return;
		case AzuredevopsMMPackage.PIPELINE__STAGES:
			getStages().clear();
			return;
		case AzuredevopsMMPackage.PIPELINE__JOBS:
			getJobs().clear();
			return;
		case AzuredevopsMMPackage.PIPELINE__STEPS:
			getSteps().clear();
			return;
		case AzuredevopsMMPackage.PIPELINE__EXTENDS:
			setExtends((Extends) null);
			return;
		case AzuredevopsMMPackage.PIPELINE__WORKSPACE:
			setWorkspace((Workspace) null);
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
		case AzuredevopsMMPackage.PIPELINE__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case AzuredevopsMMPackage.PIPELINE__APPEND_COMMIT_MESSAGE_TO_RUN_NAME:
			return APPEND_COMMIT_MESSAGE_TO_RUN_NAME_EDEFAULT == null ? appendCommitMessageToRunName != null
					: !APPEND_COMMIT_MESSAGE_TO_RUN_NAME_EDEFAULT.equals(appendCommitMessageToRunName);
		case AzuredevopsMMPackage.PIPELINE__LOCK_BEHAVIOR:
			return lockBehavior != LOCK_BEHAVIOR_EDEFAULT;
		case AzuredevopsMMPackage.PIPELINE__TRIGGER:
			return trigger != null;
		case AzuredevopsMMPackage.PIPELINE__PR:
			return pr != null;
		case AzuredevopsMMPackage.PIPELINE__SCHEDULES:
			return schedules != null && !schedules.isEmpty();
		case AzuredevopsMMPackage.PIPELINE__PARAMETERS:
			return parameters != null && !parameters.isEmpty();
		case AzuredevopsMMPackage.PIPELINE__VARIABLES:
			return variables != null && !variables.isEmpty();
		case AzuredevopsMMPackage.PIPELINE__POOL:
			return pool != null;
		case AzuredevopsMMPackage.PIPELINE__RESOURCES:
			return resources != null;
		case AzuredevopsMMPackage.PIPELINE__STAGES:
			return stages != null && !stages.isEmpty();
		case AzuredevopsMMPackage.PIPELINE__JOBS:
			return jobs != null && !jobs.isEmpty();
		case AzuredevopsMMPackage.PIPELINE__STEPS:
			return steps != null && !steps.isEmpty();
		case AzuredevopsMMPackage.PIPELINE__EXTENDS:
			return extends_ != null;
		case AzuredevopsMMPackage.PIPELINE__WORKSPACE:
			return workspace != null;
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
		result.append(", appendCommitMessageToRunName: ");
		result.append(appendCommitMessageToRunName);
		result.append(", lockBehavior: ");
		result.append(lockBehavior);
		result.append(')');
		return result.toString();
	}

} //PipelineImpl
