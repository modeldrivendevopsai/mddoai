/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerReference;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentStrategy;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.Environment;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.JobUses;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.Pool;
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
 * An implementation of the model object '<em><b>Deployment Job</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DeploymentJobImpl#getDeployment <em>Deployment</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DeploymentJobImpl#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DeploymentJobImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DeploymentJobImpl#getContinueOnError <em>Continue On Error</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DeploymentJobImpl#getTimeoutInMinutes <em>Timeout In Minutes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DeploymentJobImpl#getCancelTimeoutInMinutes <em>Cancel Timeout In Minutes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DeploymentJobImpl#getDependsOn <em>Depends On</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DeploymentJobImpl#getPool <em>Pool</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DeploymentJobImpl#getVariables <em>Variables</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DeploymentJobImpl#getEnvironment <em>Environment</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DeploymentJobImpl#getStrategy <em>Strategy</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DeploymentJobImpl#getContainer <em>Container</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DeploymentJobImpl#getServices <em>Services</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DeploymentJobImpl#getWorkspace <em>Workspace</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DeploymentJobImpl#getUses <em>Uses</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DeploymentJobImpl#getTemplateContext <em>Template Context</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DeploymentJobImpl extends JobElementImpl implements DeploymentJob {
	/**
	 * The default value of the '{@link #getDeployment() <em>Deployment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeployment()
	 * @generated
	 * @ordered
	 */
	protected static final String DEPLOYMENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDeployment() <em>Deployment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeployment()
	 * @generated
	 * @ordered
	 */
	protected String deployment = DEPLOYMENT_EDEFAULT;

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
	 * The cached value of the '{@link #getEnvironment() <em>Environment</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnvironment()
	 * @generated
	 * @ordered
	 */
	protected Environment environment;

	/**
	 * The cached value of the '{@link #getStrategy() <em>Strategy</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStrategy()
	 * @generated
	 * @ordered
	 */
	protected DeploymentStrategy strategy;

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
	protected DeploymentJobImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AzuredevopsMMPackage.Literals.DEPLOYMENT_JOB;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDeployment() {
		return deployment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDeployment(String newDeployment) {
		String oldDeployment = deployment;
		deployment = newDeployment;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.DEPLOYMENT_JOB__DEPLOYMENT,
					oldDeployment, deployment));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.DEPLOYMENT_JOB__DISPLAY_NAME,
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
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.DEPLOYMENT_JOB__CONDITION,
					oldCondition, condition));
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
			eNotify(new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.DEPLOYMENT_JOB__CONTINUE_ON_ERROR, oldContinueOnError, continueOnError));
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
			eNotify(new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.DEPLOYMENT_JOB__TIMEOUT_IN_MINUTES, oldTimeoutInMinutes, timeoutInMinutes));
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
			eNotify(new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.DEPLOYMENT_JOB__CANCEL_TIMEOUT_IN_MINUTES, oldCancelTimeoutInMinutes,
					cancelTimeoutInMinutes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getDependsOn() {
		if (dependsOn == null) {
			dependsOn = new EDataTypeUniqueEList<String>(String.class, this,
					AzuredevopsMMPackage.DEPLOYMENT_JOB__DEPENDS_ON);
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
					AzuredevopsMMPackage.DEPLOYMENT_JOB__POOL, oldPool, newPool);
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
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.DEPLOYMENT_JOB__POOL, null, msgs);
			if (newPool != null)
				msgs = ((InternalEObject) newPool).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.DEPLOYMENT_JOB__POOL, null, msgs);
			msgs = basicSetPool(newPool, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.DEPLOYMENT_JOB__POOL, newPool,
					newPool));
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
					AzuredevopsMMPackage.DEPLOYMENT_JOB__VARIABLES);
		}
		return variables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Environment getEnvironment() {
		return environment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEnvironment(Environment newEnvironment, NotificationChain msgs) {
		Environment oldEnvironment = environment;
		environment = newEnvironment;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.DEPLOYMENT_JOB__ENVIRONMENT, oldEnvironment, newEnvironment);
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
	public void setEnvironment(Environment newEnvironment) {
		if (newEnvironment != environment) {
			NotificationChain msgs = null;
			if (environment != null)
				msgs = ((InternalEObject) environment).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.DEPLOYMENT_JOB__ENVIRONMENT, null, msgs);
			if (newEnvironment != null)
				msgs = ((InternalEObject) newEnvironment).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.DEPLOYMENT_JOB__ENVIRONMENT, null, msgs);
			msgs = basicSetEnvironment(newEnvironment, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.DEPLOYMENT_JOB__ENVIRONMENT,
					newEnvironment, newEnvironment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DeploymentStrategy getStrategy() {
		return strategy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStrategy(DeploymentStrategy newStrategy, NotificationChain msgs) {
		DeploymentStrategy oldStrategy = strategy;
		strategy = newStrategy;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.DEPLOYMENT_JOB__STRATEGY, oldStrategy, newStrategy);
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
	public void setStrategy(DeploymentStrategy newStrategy) {
		if (newStrategy != strategy) {
			NotificationChain msgs = null;
			if (strategy != null)
				msgs = ((InternalEObject) strategy).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.DEPLOYMENT_JOB__STRATEGY, null, msgs);
			if (newStrategy != null)
				msgs = ((InternalEObject) newStrategy).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.DEPLOYMENT_JOB__STRATEGY, null, msgs);
			msgs = basicSetStrategy(newStrategy, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.DEPLOYMENT_JOB__STRATEGY,
					newStrategy, newStrategy));
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
					AzuredevopsMMPackage.DEPLOYMENT_JOB__CONTAINER, oldContainer, newContainer);
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
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.DEPLOYMENT_JOB__CONTAINER, null, msgs);
			if (newContainer != null)
				msgs = ((InternalEObject) newContainer).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.DEPLOYMENT_JOB__CONTAINER, null, msgs);
			msgs = basicSetContainer(newContainer, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.DEPLOYMENT_JOB__CONTAINER,
					newContainer, newContainer));
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
					ServiceEntryImpl.class, this, AzuredevopsMMPackage.DEPLOYMENT_JOB__SERVICES);
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
					AzuredevopsMMPackage.DEPLOYMENT_JOB__WORKSPACE, oldWorkspace, newWorkspace);
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
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.DEPLOYMENT_JOB__WORKSPACE, null, msgs);
			if (newWorkspace != null)
				msgs = ((InternalEObject) newWorkspace).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.DEPLOYMENT_JOB__WORKSPACE, null, msgs);
			msgs = basicSetWorkspace(newWorkspace, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.DEPLOYMENT_JOB__WORKSPACE,
					newWorkspace, newWorkspace));
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
					AzuredevopsMMPackage.DEPLOYMENT_JOB__USES, oldUses, newUses);
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
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.DEPLOYMENT_JOB__USES, null, msgs);
			if (newUses != null)
				msgs = ((InternalEObject) newUses).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.DEPLOYMENT_JOB__USES, null, msgs);
			msgs = basicSetUses(newUses, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.DEPLOYMENT_JOB__USES, newUses,
					newUses));
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
					AzuredevopsMMPackage.DEPLOYMENT_JOB__TEMPLATE_CONTEXT, oldTemplateContext, newTemplateContext);
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
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.DEPLOYMENT_JOB__TEMPLATE_CONTEXT, null, msgs);
			if (newTemplateContext != null)
				msgs = ((InternalEObject) newTemplateContext).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.DEPLOYMENT_JOB__TEMPLATE_CONTEXT, null, msgs);
			msgs = basicSetTemplateContext(newTemplateContext, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.DEPLOYMENT_JOB__TEMPLATE_CONTEXT,
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
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__POOL:
			return basicSetPool(null, msgs);
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__VARIABLES:
			return ((InternalEList<?>) getVariables()).basicRemove(otherEnd, msgs);
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__ENVIRONMENT:
			return basicSetEnvironment(null, msgs);
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__STRATEGY:
			return basicSetStrategy(null, msgs);
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__CONTAINER:
			return basicSetContainer(null, msgs);
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__SERVICES:
			return ((InternalEList<?>) getServices()).basicRemove(otherEnd, msgs);
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__WORKSPACE:
			return basicSetWorkspace(null, msgs);
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__USES:
			return basicSetUses(null, msgs);
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__TEMPLATE_CONTEXT:
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
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__DEPLOYMENT:
			return getDeployment();
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__DISPLAY_NAME:
			return getDisplayName();
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__CONDITION:
			return getCondition();
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__CONTINUE_ON_ERROR:
			return getContinueOnError();
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__TIMEOUT_IN_MINUTES:
			return getTimeoutInMinutes();
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__CANCEL_TIMEOUT_IN_MINUTES:
			return getCancelTimeoutInMinutes();
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__DEPENDS_ON:
			return getDependsOn();
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__POOL:
			return getPool();
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__VARIABLES:
			return getVariables();
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__ENVIRONMENT:
			return getEnvironment();
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__STRATEGY:
			return getStrategy();
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__CONTAINER:
			return getContainer();
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__SERVICES:
			if (coreType)
				return getServices();
			else
				return getServices().map();
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__WORKSPACE:
			return getWorkspace();
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__USES:
			return getUses();
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__TEMPLATE_CONTEXT:
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
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__DEPLOYMENT:
			setDeployment((String) newValue);
			return;
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__DISPLAY_NAME:
			setDisplayName((String) newValue);
			return;
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__CONDITION:
			setCondition((String) newValue);
			return;
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__CONTINUE_ON_ERROR:
			setContinueOnError((String) newValue);
			return;
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__TIMEOUT_IN_MINUTES:
			setTimeoutInMinutes((String) newValue);
			return;
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__CANCEL_TIMEOUT_IN_MINUTES:
			setCancelTimeoutInMinutes((String) newValue);
			return;
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__DEPENDS_ON:
			getDependsOn().clear();
			getDependsOn().addAll((Collection<? extends String>) newValue);
			return;
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__POOL:
			setPool((Pool) newValue);
			return;
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__VARIABLES:
			getVariables().clear();
			getVariables().addAll((Collection<? extends VariableDefinition>) newValue);
			return;
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__ENVIRONMENT:
			setEnvironment((Environment) newValue);
			return;
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__STRATEGY:
			setStrategy((DeploymentStrategy) newValue);
			return;
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__CONTAINER:
			setContainer((ContainerReference) newValue);
			return;
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__SERVICES:
			((EStructuralFeature.Setting) getServices()).set(newValue);
			return;
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__WORKSPACE:
			setWorkspace((Workspace) newValue);
			return;
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__USES:
			setUses((JobUses) newValue);
			return;
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__TEMPLATE_CONTEXT:
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
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__DEPLOYMENT:
			setDeployment(DEPLOYMENT_EDEFAULT);
			return;
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__DISPLAY_NAME:
			setDisplayName(DISPLAY_NAME_EDEFAULT);
			return;
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__CONDITION:
			setCondition(CONDITION_EDEFAULT);
			return;
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__CONTINUE_ON_ERROR:
			setContinueOnError(CONTINUE_ON_ERROR_EDEFAULT);
			return;
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__TIMEOUT_IN_MINUTES:
			setTimeoutInMinutes(TIMEOUT_IN_MINUTES_EDEFAULT);
			return;
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__CANCEL_TIMEOUT_IN_MINUTES:
			setCancelTimeoutInMinutes(CANCEL_TIMEOUT_IN_MINUTES_EDEFAULT);
			return;
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__DEPENDS_ON:
			getDependsOn().clear();
			return;
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__POOL:
			setPool((Pool) null);
			return;
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__VARIABLES:
			getVariables().clear();
			return;
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__ENVIRONMENT:
			setEnvironment((Environment) null);
			return;
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__STRATEGY:
			setStrategy((DeploymentStrategy) null);
			return;
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__CONTAINER:
			setContainer((ContainerReference) null);
			return;
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__SERVICES:
			getServices().clear();
			return;
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__WORKSPACE:
			setWorkspace((Workspace) null);
			return;
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__USES:
			setUses((JobUses) null);
			return;
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__TEMPLATE_CONTEXT:
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
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__DEPLOYMENT:
			return DEPLOYMENT_EDEFAULT == null ? deployment != null : !DEPLOYMENT_EDEFAULT.equals(deployment);
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__DISPLAY_NAME:
			return DISPLAY_NAME_EDEFAULT == null ? displayName != null : !DISPLAY_NAME_EDEFAULT.equals(displayName);
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__CONDITION:
			return CONDITION_EDEFAULT == null ? condition != null : !CONDITION_EDEFAULT.equals(condition);
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__CONTINUE_ON_ERROR:
			return CONTINUE_ON_ERROR_EDEFAULT == null ? continueOnError != null
					: !CONTINUE_ON_ERROR_EDEFAULT.equals(continueOnError);
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__TIMEOUT_IN_MINUTES:
			return TIMEOUT_IN_MINUTES_EDEFAULT == null ? timeoutInMinutes != null
					: !TIMEOUT_IN_MINUTES_EDEFAULT.equals(timeoutInMinutes);
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__CANCEL_TIMEOUT_IN_MINUTES:
			return CANCEL_TIMEOUT_IN_MINUTES_EDEFAULT == null ? cancelTimeoutInMinutes != null
					: !CANCEL_TIMEOUT_IN_MINUTES_EDEFAULT.equals(cancelTimeoutInMinutes);
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__DEPENDS_ON:
			return dependsOn != null && !dependsOn.isEmpty();
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__POOL:
			return pool != null;
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__VARIABLES:
			return variables != null && !variables.isEmpty();
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__ENVIRONMENT:
			return environment != null;
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__STRATEGY:
			return strategy != null;
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__CONTAINER:
			return container != null;
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__SERVICES:
			return services != null && !services.isEmpty();
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__WORKSPACE:
			return workspace != null;
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__USES:
			return uses != null;
		case AzuredevopsMMPackage.DEPLOYMENT_JOB__TEMPLATE_CONTEXT:
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
		result.append(" (deployment: ");
		result.append(deployment);
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

} //DeploymentJobImpl
