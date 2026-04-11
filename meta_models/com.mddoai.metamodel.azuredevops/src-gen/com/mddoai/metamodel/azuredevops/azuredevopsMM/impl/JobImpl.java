/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerReference;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.Job;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.JobStrategy;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.JobUses;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.Pool;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.Step;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.TemplateContext;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableDefinition;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.Workspace;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
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
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobImpl#getJob <em>Job</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobImpl#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobImpl#getContinueOnError <em>Continue On Error</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobImpl#getTimeoutInMinutes <em>Timeout In Minutes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobImpl#getCancelTimeoutInMinutes <em>Cancel Timeout In Minutes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobImpl#getDependsOn <em>Depends On</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobImpl#getPool <em>Pool</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobImpl#getVariables <em>Variables</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobImpl#getStrategy <em>Strategy</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobImpl#getContainer <em>Container</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobImpl#getServices <em>Services</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobImpl#getWorkspace <em>Workspace</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobImpl#getUses <em>Uses</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobImpl#getSteps <em>Steps</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobImpl#getTemplateContext <em>Template Context</em>}</li>
 * </ul>
 *
 * @generated
 */
public class JobImpl extends JobElementImpl implements Job {
	/**
	 * The default value of the '{@link #getJob() <em>Job</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJob()
	 * @generated
	 * @ordered
	 */
	protected static final String JOB_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getJob() <em>Job</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJob()
	 * @generated
	 * @ordered
	 */
	protected String job = JOB_EDEFAULT;

	/**
	 * The default value of the '{@link #getDisplayName() <em>Display Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayName()
	 * @generated
	 * @ordered
	 */
	protected static final String DISPLAY_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDisplayName() <em>Display Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayName()
	 * @generated
	 * @ordered
	 */
	protected String displayName = DISPLAY_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getCondition() <em>Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCondition()
	 * @generated
	 * @ordered
	 */
	protected static final String CONDITION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCondition() <em>Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCondition()
	 * @generated
	 * @ordered
	 */
	protected String condition = CONDITION_EDEFAULT;

	/**
	 * The default value of the '{@link #getContinueOnError() <em>Continue On Error</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContinueOnError()
	 * @generated
	 * @ordered
	 */
	protected static final String CONTINUE_ON_ERROR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getContinueOnError() <em>Continue On Error</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContinueOnError()
	 * @generated
	 * @ordered
	 */
	protected String continueOnError = CONTINUE_ON_ERROR_EDEFAULT;

	/**
	 * The default value of the '{@link #getTimeoutInMinutes() <em>Timeout In Minutes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeoutInMinutes()
	 * @generated
	 * @ordered
	 */
	protected static final String TIMEOUT_IN_MINUTES_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTimeoutInMinutes() <em>Timeout In Minutes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeoutInMinutes()
	 * @generated
	 * @ordered
	 */
	protected String timeoutInMinutes = TIMEOUT_IN_MINUTES_EDEFAULT;

	/**
	 * The default value of the '{@link #getCancelTimeoutInMinutes() <em>Cancel Timeout In Minutes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCancelTimeoutInMinutes()
	 * @generated
	 * @ordered
	 */
	protected static final String CANCEL_TIMEOUT_IN_MINUTES_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCancelTimeoutInMinutes() <em>Cancel Timeout In Minutes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCancelTimeoutInMinutes()
	 * @generated
	 * @ordered
	 */
	protected String cancelTimeoutInMinutes = CANCEL_TIMEOUT_IN_MINUTES_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDependsOn() <em>Depends On</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDependsOn()
	 * @generated
	 * @ordered
	 */
	protected EList<String> dependsOn;

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
	 * The cached value of the '{@link #getVariables() <em>Variables</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariables()
	 * @generated
	 * @ordered
	 */
	protected EList<VariableDefinition> variables;

	/**
	 * The cached value of the '{@link #getStrategy() <em>Strategy</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStrategy()
	 * @generated
	 * @ordered
	 */
	protected JobStrategy strategy;

	/**
	 * The cached value of the '{@link #getContainer() <em>Container</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainer()
	 * @generated
	 * @ordered
	 */
	protected ContainerReference container;

	/**
	 * The cached value of the '{@link #getServices() <em>Services</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServices()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> services;

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
	 * The cached value of the '{@link #getUses() <em>Uses</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUses()
	 * @generated
	 * @ordered
	 */
	protected JobUses uses;

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
	 * The cached value of the '{@link #getTemplateContext() <em>Template Context</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemplateContext()
	 * @generated
	 * @ordered
	 */
	protected TemplateContext templateContext;

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
		return AzuredevopsMMPackage.Literals.JOB;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getJob() {
		return job;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setJob(String newJob) {
		String oldJob = job;
		job = newJob;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.JOB__JOB, oldJob, job));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDisplayName(String newDisplayName) {
		String oldDisplayName = displayName;
		displayName = newDisplayName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.JOB__DISPLAY_NAME,
					oldDisplayName, displayName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCondition() {
		return condition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCondition(String newCondition) {
		String oldCondition = condition;
		condition = newCondition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.JOB__CONDITION, oldCondition,
					condition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getContinueOnError() {
		return continueOnError;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setContinueOnError(String newContinueOnError) {
		String oldContinueOnError = continueOnError;
		continueOnError = newContinueOnError;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.JOB__CONTINUE_ON_ERROR,
					oldContinueOnError, continueOnError));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTimeoutInMinutes() {
		return timeoutInMinutes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTimeoutInMinutes(String newTimeoutInMinutes) {
		String oldTimeoutInMinutes = timeoutInMinutes;
		timeoutInMinutes = newTimeoutInMinutes;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.JOB__TIMEOUT_IN_MINUTES,
					oldTimeoutInMinutes, timeoutInMinutes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCancelTimeoutInMinutes() {
		return cancelTimeoutInMinutes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCancelTimeoutInMinutes(String newCancelTimeoutInMinutes) {
		String oldCancelTimeoutInMinutes = cancelTimeoutInMinutes;
		cancelTimeoutInMinutes = newCancelTimeoutInMinutes;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.JOB__CANCEL_TIMEOUT_IN_MINUTES,
					oldCancelTimeoutInMinutes, cancelTimeoutInMinutes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getDependsOn() {
		if (dependsOn == null) {
			dependsOn = new EDataTypeUniqueEList<String>(String.class, this, AzuredevopsMMPackage.JOB__DEPENDS_ON);
		}
		return dependsOn;
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
					AzuredevopsMMPackage.JOB__POOL, oldPool, newPool);
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
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.JOB__POOL, null, msgs);
			if (newPool != null)
				msgs = ((InternalEObject) newPool).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.JOB__POOL, null, msgs);
			msgs = basicSetPool(newPool, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.JOB__POOL, newPool, newPool));
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
					AzuredevopsMMPackage.JOB__VARIABLES);
		}
		return variables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public JobStrategy getStrategy() {
		return strategy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStrategy(JobStrategy newStrategy, NotificationChain msgs) {
		JobStrategy oldStrategy = strategy;
		strategy = newStrategy;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.JOB__STRATEGY, oldStrategy, newStrategy);
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
	public void setStrategy(JobStrategy newStrategy) {
		if (newStrategy != strategy) {
			NotificationChain msgs = null;
			if (strategy != null)
				msgs = ((InternalEObject) strategy).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.JOB__STRATEGY, null, msgs);
			if (newStrategy != null)
				msgs = ((InternalEObject) newStrategy).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.JOB__STRATEGY, null, msgs);
			msgs = basicSetStrategy(newStrategy, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.JOB__STRATEGY, newStrategy,
					newStrategy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ContainerReference getContainer() {
		return container;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContainer(ContainerReference newContainer, NotificationChain msgs) {
		ContainerReference oldContainer = container;
		container = newContainer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.JOB__CONTAINER, oldContainer, newContainer);
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
	public void setContainer(ContainerReference newContainer) {
		if (newContainer != container) {
			NotificationChain msgs = null;
			if (container != null)
				msgs = ((InternalEObject) container).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.JOB__CONTAINER, null, msgs);
			if (newContainer != null)
				msgs = ((InternalEObject) newContainer).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.JOB__CONTAINER, null, msgs);
			msgs = basicSetContainer(newContainer, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.JOB__CONTAINER, newContainer,
					newContainer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EMap<String, String> getServices() {
		if (services == null) {
			services = new EcoreEMap<String, String>(AzuredevopsMMPackage.Literals.SERVICE_ENTRY,
					ServiceEntryImpl.class, this, AzuredevopsMMPackage.JOB__SERVICES);
		}
		return services;
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
					AzuredevopsMMPackage.JOB__WORKSPACE, oldWorkspace, newWorkspace);
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
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.JOB__WORKSPACE, null, msgs);
			if (newWorkspace != null)
				msgs = ((InternalEObject) newWorkspace).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.JOB__WORKSPACE, null, msgs);
			msgs = basicSetWorkspace(newWorkspace, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.JOB__WORKSPACE, newWorkspace,
					newWorkspace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public JobUses getUses() {
		return uses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUses(JobUses newUses, NotificationChain msgs) {
		JobUses oldUses = uses;
		uses = newUses;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.JOB__USES, oldUses, newUses);
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
	public void setUses(JobUses newUses) {
		if (newUses != uses) {
			NotificationChain msgs = null;
			if (uses != null)
				msgs = ((InternalEObject) uses).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.JOB__USES, null, msgs);
			if (newUses != null)
				msgs = ((InternalEObject) newUses).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.JOB__USES, null, msgs);
			msgs = basicSetUses(newUses, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.JOB__USES, newUses, newUses));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Step> getSteps() {
		if (steps == null) {
			steps = new EObjectContainmentEList<Step>(Step.class, this, AzuredevopsMMPackage.JOB__STEPS);
		}
		return steps;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TemplateContext getTemplateContext() {
		return templateContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTemplateContext(TemplateContext newTemplateContext, NotificationChain msgs) {
		TemplateContext oldTemplateContext = templateContext;
		templateContext = newTemplateContext;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.JOB__TEMPLATE_CONTEXT, oldTemplateContext, newTemplateContext);
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
	public void setTemplateContext(TemplateContext newTemplateContext) {
		if (newTemplateContext != templateContext) {
			NotificationChain msgs = null;
			if (templateContext != null)
				msgs = ((InternalEObject) templateContext).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.JOB__TEMPLATE_CONTEXT, null, msgs);
			if (newTemplateContext != null)
				msgs = ((InternalEObject) newTemplateContext).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.JOB__TEMPLATE_CONTEXT, null, msgs);
			msgs = basicSetTemplateContext(newTemplateContext, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.JOB__TEMPLATE_CONTEXT,
					newTemplateContext, newTemplateContext));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case AzuredevopsMMPackage.JOB__POOL:
			return basicSetPool(null, msgs);
		case AzuredevopsMMPackage.JOB__VARIABLES:
			return ((InternalEList<?>) getVariables()).basicRemove(otherEnd, msgs);
		case AzuredevopsMMPackage.JOB__STRATEGY:
			return basicSetStrategy(null, msgs);
		case AzuredevopsMMPackage.JOB__CONTAINER:
			return basicSetContainer(null, msgs);
		case AzuredevopsMMPackage.JOB__SERVICES:
			return ((InternalEList<?>) getServices()).basicRemove(otherEnd, msgs);
		case AzuredevopsMMPackage.JOB__WORKSPACE:
			return basicSetWorkspace(null, msgs);
		case AzuredevopsMMPackage.JOB__USES:
			return basicSetUses(null, msgs);
		case AzuredevopsMMPackage.JOB__STEPS:
			return ((InternalEList<?>) getSteps()).basicRemove(otherEnd, msgs);
		case AzuredevopsMMPackage.JOB__TEMPLATE_CONTEXT:
			return basicSetTemplateContext(null, msgs);
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
		case AzuredevopsMMPackage.JOB__JOB:
			return getJob();
		case AzuredevopsMMPackage.JOB__DISPLAY_NAME:
			return getDisplayName();
		case AzuredevopsMMPackage.JOB__CONDITION:
			return getCondition();
		case AzuredevopsMMPackage.JOB__CONTINUE_ON_ERROR:
			return getContinueOnError();
		case AzuredevopsMMPackage.JOB__TIMEOUT_IN_MINUTES:
			return getTimeoutInMinutes();
		case AzuredevopsMMPackage.JOB__CANCEL_TIMEOUT_IN_MINUTES:
			return getCancelTimeoutInMinutes();
		case AzuredevopsMMPackage.JOB__DEPENDS_ON:
			return getDependsOn();
		case AzuredevopsMMPackage.JOB__POOL:
			return getPool();
		case AzuredevopsMMPackage.JOB__VARIABLES:
			return getVariables();
		case AzuredevopsMMPackage.JOB__STRATEGY:
			return getStrategy();
		case AzuredevopsMMPackage.JOB__CONTAINER:
			return getContainer();
		case AzuredevopsMMPackage.JOB__SERVICES:
			if (coreType)
				return getServices();
			else
				return getServices().map();
		case AzuredevopsMMPackage.JOB__WORKSPACE:
			return getWorkspace();
		case AzuredevopsMMPackage.JOB__USES:
			return getUses();
		case AzuredevopsMMPackage.JOB__STEPS:
			return getSteps();
		case AzuredevopsMMPackage.JOB__TEMPLATE_CONTEXT:
			return getTemplateContext();
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
		case AzuredevopsMMPackage.JOB__JOB:
			setJob((String) newValue);
			return;
		case AzuredevopsMMPackage.JOB__DISPLAY_NAME:
			setDisplayName((String) newValue);
			return;
		case AzuredevopsMMPackage.JOB__CONDITION:
			setCondition((String) newValue);
			return;
		case AzuredevopsMMPackage.JOB__CONTINUE_ON_ERROR:
			setContinueOnError((String) newValue);
			return;
		case AzuredevopsMMPackage.JOB__TIMEOUT_IN_MINUTES:
			setTimeoutInMinutes((String) newValue);
			return;
		case AzuredevopsMMPackage.JOB__CANCEL_TIMEOUT_IN_MINUTES:
			setCancelTimeoutInMinutes((String) newValue);
			return;
		case AzuredevopsMMPackage.JOB__DEPENDS_ON:
			getDependsOn().clear();
			getDependsOn().addAll((Collection<? extends String>) newValue);
			return;
		case AzuredevopsMMPackage.JOB__POOL:
			setPool((Pool) newValue);
			return;
		case AzuredevopsMMPackage.JOB__VARIABLES:
			getVariables().clear();
			getVariables().addAll((Collection<? extends VariableDefinition>) newValue);
			return;
		case AzuredevopsMMPackage.JOB__STRATEGY:
			setStrategy((JobStrategy) newValue);
			return;
		case AzuredevopsMMPackage.JOB__CONTAINER:
			setContainer((ContainerReference) newValue);
			return;
		case AzuredevopsMMPackage.JOB__SERVICES:
			((EStructuralFeature.Setting) getServices()).set(newValue);
			return;
		case AzuredevopsMMPackage.JOB__WORKSPACE:
			setWorkspace((Workspace) newValue);
			return;
		case AzuredevopsMMPackage.JOB__USES:
			setUses((JobUses) newValue);
			return;
		case AzuredevopsMMPackage.JOB__STEPS:
			getSteps().clear();
			getSteps().addAll((Collection<? extends Step>) newValue);
			return;
		case AzuredevopsMMPackage.JOB__TEMPLATE_CONTEXT:
			setTemplateContext((TemplateContext) newValue);
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
		case AzuredevopsMMPackage.JOB__JOB:
			setJob(JOB_EDEFAULT);
			return;
		case AzuredevopsMMPackage.JOB__DISPLAY_NAME:
			setDisplayName(DISPLAY_NAME_EDEFAULT);
			return;
		case AzuredevopsMMPackage.JOB__CONDITION:
			setCondition(CONDITION_EDEFAULT);
			return;
		case AzuredevopsMMPackage.JOB__CONTINUE_ON_ERROR:
			setContinueOnError(CONTINUE_ON_ERROR_EDEFAULT);
			return;
		case AzuredevopsMMPackage.JOB__TIMEOUT_IN_MINUTES:
			setTimeoutInMinutes(TIMEOUT_IN_MINUTES_EDEFAULT);
			return;
		case AzuredevopsMMPackage.JOB__CANCEL_TIMEOUT_IN_MINUTES:
			setCancelTimeoutInMinutes(CANCEL_TIMEOUT_IN_MINUTES_EDEFAULT);
			return;
		case AzuredevopsMMPackage.JOB__DEPENDS_ON:
			getDependsOn().clear();
			return;
		case AzuredevopsMMPackage.JOB__POOL:
			setPool((Pool) null);
			return;
		case AzuredevopsMMPackage.JOB__VARIABLES:
			getVariables().clear();
			return;
		case AzuredevopsMMPackage.JOB__STRATEGY:
			setStrategy((JobStrategy) null);
			return;
		case AzuredevopsMMPackage.JOB__CONTAINER:
			setContainer((ContainerReference) null);
			return;
		case AzuredevopsMMPackage.JOB__SERVICES:
			getServices().clear();
			return;
		case AzuredevopsMMPackage.JOB__WORKSPACE:
			setWorkspace((Workspace) null);
			return;
		case AzuredevopsMMPackage.JOB__USES:
			setUses((JobUses) null);
			return;
		case AzuredevopsMMPackage.JOB__STEPS:
			getSteps().clear();
			return;
		case AzuredevopsMMPackage.JOB__TEMPLATE_CONTEXT:
			setTemplateContext((TemplateContext) null);
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
		case AzuredevopsMMPackage.JOB__JOB:
			return JOB_EDEFAULT == null ? job != null : !JOB_EDEFAULT.equals(job);
		case AzuredevopsMMPackage.JOB__DISPLAY_NAME:
			return DISPLAY_NAME_EDEFAULT == null ? displayName != null : !DISPLAY_NAME_EDEFAULT.equals(displayName);
		case AzuredevopsMMPackage.JOB__CONDITION:
			return CONDITION_EDEFAULT == null ? condition != null : !CONDITION_EDEFAULT.equals(condition);
		case AzuredevopsMMPackage.JOB__CONTINUE_ON_ERROR:
			return CONTINUE_ON_ERROR_EDEFAULT == null ? continueOnError != null
					: !CONTINUE_ON_ERROR_EDEFAULT.equals(continueOnError);
		case AzuredevopsMMPackage.JOB__TIMEOUT_IN_MINUTES:
			return TIMEOUT_IN_MINUTES_EDEFAULT == null ? timeoutInMinutes != null
					: !TIMEOUT_IN_MINUTES_EDEFAULT.equals(timeoutInMinutes);
		case AzuredevopsMMPackage.JOB__CANCEL_TIMEOUT_IN_MINUTES:
			return CANCEL_TIMEOUT_IN_MINUTES_EDEFAULT == null ? cancelTimeoutInMinutes != null
					: !CANCEL_TIMEOUT_IN_MINUTES_EDEFAULT.equals(cancelTimeoutInMinutes);
		case AzuredevopsMMPackage.JOB__DEPENDS_ON:
			return dependsOn != null && !dependsOn.isEmpty();
		case AzuredevopsMMPackage.JOB__POOL:
			return pool != null;
		case AzuredevopsMMPackage.JOB__VARIABLES:
			return variables != null && !variables.isEmpty();
		case AzuredevopsMMPackage.JOB__STRATEGY:
			return strategy != null;
		case AzuredevopsMMPackage.JOB__CONTAINER:
			return container != null;
		case AzuredevopsMMPackage.JOB__SERVICES:
			return services != null && !services.isEmpty();
		case AzuredevopsMMPackage.JOB__WORKSPACE:
			return workspace != null;
		case AzuredevopsMMPackage.JOB__USES:
			return uses != null;
		case AzuredevopsMMPackage.JOB__STEPS:
			return steps != null && !steps.isEmpty();
		case AzuredevopsMMPackage.JOB__TEMPLATE_CONTEXT:
			return templateContext != null;
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
		result.append(" (job: ");
		result.append(job);
		result.append(", displayName: ");
		result.append(displayName);
		result.append(", condition: ");
		result.append(condition);
		result.append(", continueOnError: ");
		result.append(continueOnError);
		result.append(", timeoutInMinutes: ");
		result.append(timeoutInMinutes);
		result.append(", cancelTimeoutInMinutes: ");
		result.append(cancelTimeoutInMinutes);
		result.append(", dependsOn: ");
		result.append(dependsOn);
		result.append(')');
		return result.toString();
	}

} //JobImpl
