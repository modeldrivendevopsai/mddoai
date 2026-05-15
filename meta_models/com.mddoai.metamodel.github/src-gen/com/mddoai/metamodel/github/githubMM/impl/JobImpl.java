/**
 */
package com.mddoai.metamodel.github.githubMM.impl;

import com.mddoai.metamodel.github.githubMM.Agent;
import com.mddoai.metamodel.github.githubMM.ConcurrencyGroup;
import com.mddoai.metamodel.github.githubMM.Defaults;
import com.mddoai.metamodel.github.githubMM.Expression;
import com.mddoai.metamodel.github.githubMM.GithubMMPackage;
import com.mddoai.metamodel.github.githubMM.Job;
import com.mddoai.metamodel.github.githubMM.Matrix;
import com.mddoai.metamodel.github.githubMM.Output;
import com.mddoai.metamodel.github.githubMM.PERMISSIONS;
import com.mddoai.metamodel.github.githubMM.PERMISSION_SCOPES;
import com.mddoai.metamodel.github.githubMM.StagingEnvironment;
import com.mddoai.metamodel.github.githubMM.VariableDeclaration;

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
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
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
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.JobImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.JobImpl#getJobName <em>Job Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.JobImpl#getPermissions <em>Permissions</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.JobImpl#getDependsOn <em>Depends On</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.JobImpl#getNecessaryFor <em>Necessary For</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.JobImpl#getIfCondition <em>If Condition</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.JobImpl#getAgent <em>Agent</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.JobImpl#getContainer <em>Container</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.JobImpl#getStagingEnvironment <em>Staging Environment</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.JobImpl#getDefaults <em>Defaults</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.JobImpl#getEnvironmentVariables <em>Environment Variables</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.JobImpl#getServices <em>Services</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.JobImpl#getConcurrencyGroup <em>Concurrency Group</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.JobImpl#getTimeoutMinutes <em>Timeout Minutes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.JobImpl#getContinueOnError <em>Continue On Error</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.JobImpl#getStrategy <em>Strategy</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.JobImpl#getOutputs <em>Outputs</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class JobImpl extends MinimalEObjectImpl.Container implements Job {
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
	 * The cached value of the '{@link #getJobName() <em>Job Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJobName()
	 * @generated
	 * @ordered
	 */
	protected Expression jobName;

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
	 * The cached value of the '{@link #getDependsOn() <em>Depends On</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDependsOn()
	 * @generated
	 * @ordered
	 */
	protected EList<Job> dependsOn;

	/**
	 * The cached value of the '{@link #getNecessaryFor() <em>Necessary For</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNecessaryFor()
	 * @generated
	 * @ordered
	 */
	protected EList<Job> necessaryFor;

	/**
	 * The cached value of the '{@link #getIfCondition() <em>If Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIfCondition()
	 * @generated
	 * @ordered
	 */
	protected Expression ifCondition;

	/**
	 * The cached value of the '{@link #getAgent() <em>Agent</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAgent()
	 * @generated
	 * @ordered
	 */
	protected Agent agent;

	/**
	 * The cached value of the '{@link #getContainer() <em>Container</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainer()
	 * @generated
	 * @ordered
	 */
	protected com.mddoai.metamodel.github.githubMM.Container container;

	/**
	 * The cached value of the '{@link #getStagingEnvironment() <em>Staging Environment</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStagingEnvironment()
	 * @generated
	 * @ordered
	 */
	protected StagingEnvironment stagingEnvironment;

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
	 * The cached value of the '{@link #getServices() <em>Services</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServices()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, com.mddoai.metamodel.github.githubMM.Container> services;

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
	 * The cached value of the '{@link #getTimeoutMinutes() <em>Timeout Minutes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeoutMinutes()
	 * @generated
	 * @ordered
	 */
	protected Expression timeoutMinutes;

	/**
	 * The cached value of the '{@link #getContinueOnError() <em>Continue On Error</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContinueOnError()
	 * @generated
	 * @ordered
	 */
	protected Expression continueOnError;

	/**
	 * The cached value of the '{@link #getStrategy() <em>Strategy</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStrategy()
	 * @generated
	 * @ordered
	 */
	protected Matrix strategy;

	/**
	 * The cached value of the '{@link #getOutputs() <em>Outputs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutputs()
	 * @generated
	 * @ordered
	 */
	protected EList<Output> outputs;

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
		return GithubMMPackage.Literals.JOB;
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
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.JOB__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getJobName() {
		return jobName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetJobName(Expression newJobName, NotificationChain msgs) {
		Expression oldJobName = jobName;
		jobName = newJobName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GithubMMPackage.JOB__JOB_NAME, oldJobName, newJobName);
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
	public void setJobName(Expression newJobName) {
		if (newJobName != jobName) {
			NotificationChain msgs = null;
			if (jobName != null)
				msgs = ((InternalEObject) jobName).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.JOB__JOB_NAME, null, msgs);
			if (newJobName != null)
				msgs = ((InternalEObject) newJobName).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.JOB__JOB_NAME, null, msgs);
			msgs = basicSetJobName(newJobName, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.JOB__JOB_NAME, newJobName,
					newJobName));
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
					PermissionImpl.class, this, GithubMMPackage.JOB__PERMISSIONS);
		}
		return permissions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Job> getDependsOn() {
		if (dependsOn == null) {
			dependsOn = new EObjectWithInverseResolvingEList.ManyInverse<Job>(Job.class, this,
					GithubMMPackage.JOB__DEPENDS_ON, GithubMMPackage.JOB__NECESSARY_FOR);
		}
		return dependsOn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Job> getNecessaryFor() {
		if (necessaryFor == null) {
			necessaryFor = new EObjectWithInverseResolvingEList.ManyInverse<Job>(Job.class, this,
					GithubMMPackage.JOB__NECESSARY_FOR, GithubMMPackage.JOB__DEPENDS_ON);
		}
		return necessaryFor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getIfCondition() {
		return ifCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIfCondition(Expression newIfCondition, NotificationChain msgs) {
		Expression oldIfCondition = ifCondition;
		ifCondition = newIfCondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GithubMMPackage.JOB__IF_CONDITION, oldIfCondition, newIfCondition);
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
	public void setIfCondition(Expression newIfCondition) {
		if (newIfCondition != ifCondition) {
			NotificationChain msgs = null;
			if (ifCondition != null)
				msgs = ((InternalEObject) ifCondition).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.JOB__IF_CONDITION, null, msgs);
			if (newIfCondition != null)
				msgs = ((InternalEObject) newIfCondition).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.JOB__IF_CONDITION, null, msgs);
			msgs = basicSetIfCondition(newIfCondition, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.JOB__IF_CONDITION, newIfCondition,
					newIfCondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Agent getAgent() {
		return agent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAgent(Agent newAgent, NotificationChain msgs) {
		Agent oldAgent = agent;
		agent = newAgent;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GithubMMPackage.JOB__AGENT,
					oldAgent, newAgent);
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
	public void setAgent(Agent newAgent) {
		if (newAgent != agent) {
			NotificationChain msgs = null;
			if (agent != null)
				msgs = ((InternalEObject) agent).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.JOB__AGENT, null, msgs);
			if (newAgent != null)
				msgs = ((InternalEObject) newAgent).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.JOB__AGENT, null, msgs);
			msgs = basicSetAgent(newAgent, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.JOB__AGENT, newAgent, newAgent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public com.mddoai.metamodel.github.githubMM.Container getContainer() {
		return container;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContainer(com.mddoai.metamodel.github.githubMM.Container newContainer,
			NotificationChain msgs) {
		com.mddoai.metamodel.github.githubMM.Container oldContainer = container;
		container = newContainer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GithubMMPackage.JOB__CONTAINER, oldContainer, newContainer);
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
	public void setContainer(com.mddoai.metamodel.github.githubMM.Container newContainer) {
		if (newContainer != container) {
			NotificationChain msgs = null;
			if (container != null)
				msgs = ((InternalEObject) container).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.JOB__CONTAINER, null, msgs);
			if (newContainer != null)
				msgs = ((InternalEObject) newContainer).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.JOB__CONTAINER, null, msgs);
			msgs = basicSetContainer(newContainer, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.JOB__CONTAINER, newContainer,
					newContainer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StagingEnvironment getStagingEnvironment() {
		return stagingEnvironment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStagingEnvironment(StagingEnvironment newStagingEnvironment,
			NotificationChain msgs) {
		StagingEnvironment oldStagingEnvironment = stagingEnvironment;
		stagingEnvironment = newStagingEnvironment;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GithubMMPackage.JOB__STAGING_ENVIRONMENT, oldStagingEnvironment, newStagingEnvironment);
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
	public void setStagingEnvironment(StagingEnvironment newStagingEnvironment) {
		if (newStagingEnvironment != stagingEnvironment) {
			NotificationChain msgs = null;
			if (stagingEnvironment != null)
				msgs = ((InternalEObject) stagingEnvironment).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.JOB__STAGING_ENVIRONMENT, null, msgs);
			if (newStagingEnvironment != null)
				msgs = ((InternalEObject) newStagingEnvironment).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.JOB__STAGING_ENVIRONMENT, null, msgs);
			msgs = basicSetStagingEnvironment(newStagingEnvironment, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.JOB__STAGING_ENVIRONMENT,
					newStagingEnvironment, newStagingEnvironment));
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
					GithubMMPackage.JOB__DEFAULTS, oldDefaults, newDefaults);
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
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.JOB__DEFAULTS, null, msgs);
			if (newDefaults != null)
				msgs = ((InternalEObject) newDefaults).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.JOB__DEFAULTS, null, msgs);
			msgs = basicSetDefaults(newDefaults, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.JOB__DEFAULTS, newDefaults,
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
					GithubMMPackage.JOB__ENVIRONMENT_VARIABLES);
		}
		return environmentVariables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EMap<String, com.mddoai.metamodel.github.githubMM.Container> getServices() {
		if (services == null) {
			services = new EcoreEMap<String, com.mddoai.metamodel.github.githubMM.Container>(
					GithubMMPackage.Literals.SERVICE, ServiceImpl.class, this, GithubMMPackage.JOB__SERVICES);
		}
		return services;
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
					GithubMMPackage.JOB__CONCURRENCY_GROUP, oldConcurrencyGroup, newConcurrencyGroup);
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
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.JOB__CONCURRENCY_GROUP, null, msgs);
			if (newConcurrencyGroup != null)
				msgs = ((InternalEObject) newConcurrencyGroup).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.JOB__CONCURRENCY_GROUP, null, msgs);
			msgs = basicSetConcurrencyGroup(newConcurrencyGroup, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.JOB__CONCURRENCY_GROUP,
					newConcurrencyGroup, newConcurrencyGroup));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getTimeoutMinutes() {
		return timeoutMinutes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTimeoutMinutes(Expression newTimeoutMinutes, NotificationChain msgs) {
		Expression oldTimeoutMinutes = timeoutMinutes;
		timeoutMinutes = newTimeoutMinutes;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GithubMMPackage.JOB__TIMEOUT_MINUTES, oldTimeoutMinutes, newTimeoutMinutes);
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
	public void setTimeoutMinutes(Expression newTimeoutMinutes) {
		if (newTimeoutMinutes != timeoutMinutes) {
			NotificationChain msgs = null;
			if (timeoutMinutes != null)
				msgs = ((InternalEObject) timeoutMinutes).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.JOB__TIMEOUT_MINUTES, null, msgs);
			if (newTimeoutMinutes != null)
				msgs = ((InternalEObject) newTimeoutMinutes).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.JOB__TIMEOUT_MINUTES, null, msgs);
			msgs = basicSetTimeoutMinutes(newTimeoutMinutes, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.JOB__TIMEOUT_MINUTES,
					newTimeoutMinutes, newTimeoutMinutes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getContinueOnError() {
		return continueOnError;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContinueOnError(Expression newContinueOnError, NotificationChain msgs) {
		Expression oldContinueOnError = continueOnError;
		continueOnError = newContinueOnError;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GithubMMPackage.JOB__CONTINUE_ON_ERROR, oldContinueOnError, newContinueOnError);
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
	public void setContinueOnError(Expression newContinueOnError) {
		if (newContinueOnError != continueOnError) {
			NotificationChain msgs = null;
			if (continueOnError != null)
				msgs = ((InternalEObject) continueOnError).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.JOB__CONTINUE_ON_ERROR, null, msgs);
			if (newContinueOnError != null)
				msgs = ((InternalEObject) newContinueOnError).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.JOB__CONTINUE_ON_ERROR, null, msgs);
			msgs = basicSetContinueOnError(newContinueOnError, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.JOB__CONTINUE_ON_ERROR,
					newContinueOnError, newContinueOnError));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Matrix getStrategy() {
		return strategy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStrategy(Matrix newStrategy, NotificationChain msgs) {
		Matrix oldStrategy = strategy;
		strategy = newStrategy;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GithubMMPackage.JOB__STRATEGY, oldStrategy, newStrategy);
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
	public void setStrategy(Matrix newStrategy) {
		if (newStrategy != strategy) {
			NotificationChain msgs = null;
			if (strategy != null)
				msgs = ((InternalEObject) strategy).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.JOB__STRATEGY, null, msgs);
			if (newStrategy != null)
				msgs = ((InternalEObject) newStrategy).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.JOB__STRATEGY, null, msgs);
			msgs = basicSetStrategy(newStrategy, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.JOB__STRATEGY, newStrategy,
					newStrategy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Output> getOutputs() {
		if (outputs == null) {
			outputs = new EObjectContainmentEList<Output>(Output.class, this, GithubMMPackage.JOB__OUTPUTS);
		}
		return outputs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GithubMMPackage.JOB__DEPENDS_ON:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getDependsOn()).basicAdd(otherEnd, msgs);
		case GithubMMPackage.JOB__NECESSARY_FOR:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getNecessaryFor()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GithubMMPackage.JOB__JOB_NAME:
			return basicSetJobName(null, msgs);
		case GithubMMPackage.JOB__PERMISSIONS:
			return ((InternalEList<?>) getPermissions()).basicRemove(otherEnd, msgs);
		case GithubMMPackage.JOB__DEPENDS_ON:
			return ((InternalEList<?>) getDependsOn()).basicRemove(otherEnd, msgs);
		case GithubMMPackage.JOB__NECESSARY_FOR:
			return ((InternalEList<?>) getNecessaryFor()).basicRemove(otherEnd, msgs);
		case GithubMMPackage.JOB__IF_CONDITION:
			return basicSetIfCondition(null, msgs);
		case GithubMMPackage.JOB__AGENT:
			return basicSetAgent(null, msgs);
		case GithubMMPackage.JOB__CONTAINER:
			return basicSetContainer(null, msgs);
		case GithubMMPackage.JOB__STAGING_ENVIRONMENT:
			return basicSetStagingEnvironment(null, msgs);
		case GithubMMPackage.JOB__DEFAULTS:
			return basicSetDefaults(null, msgs);
		case GithubMMPackage.JOB__ENVIRONMENT_VARIABLES:
			return ((InternalEList<?>) getEnvironmentVariables()).basicRemove(otherEnd, msgs);
		case GithubMMPackage.JOB__SERVICES:
			return ((InternalEList<?>) getServices()).basicRemove(otherEnd, msgs);
		case GithubMMPackage.JOB__CONCURRENCY_GROUP:
			return basicSetConcurrencyGroup(null, msgs);
		case GithubMMPackage.JOB__TIMEOUT_MINUTES:
			return basicSetTimeoutMinutes(null, msgs);
		case GithubMMPackage.JOB__CONTINUE_ON_ERROR:
			return basicSetContinueOnError(null, msgs);
		case GithubMMPackage.JOB__STRATEGY:
			return basicSetStrategy(null, msgs);
		case GithubMMPackage.JOB__OUTPUTS:
			return ((InternalEList<?>) getOutputs()).basicRemove(otherEnd, msgs);
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
		case GithubMMPackage.JOB__NAME:
			return getName();
		case GithubMMPackage.JOB__JOB_NAME:
			return getJobName();
		case GithubMMPackage.JOB__PERMISSIONS:
			if (coreType)
				return getPermissions();
			else
				return getPermissions().map();
		case GithubMMPackage.JOB__DEPENDS_ON:
			return getDependsOn();
		case GithubMMPackage.JOB__NECESSARY_FOR:
			return getNecessaryFor();
		case GithubMMPackage.JOB__IF_CONDITION:
			return getIfCondition();
		case GithubMMPackage.JOB__AGENT:
			return getAgent();
		case GithubMMPackage.JOB__CONTAINER:
			return getContainer();
		case GithubMMPackage.JOB__STAGING_ENVIRONMENT:
			return getStagingEnvironment();
		case GithubMMPackage.JOB__DEFAULTS:
			return getDefaults();
		case GithubMMPackage.JOB__ENVIRONMENT_VARIABLES:
			if (coreType)
				return getEnvironmentVariables();
			else
				return getEnvironmentVariables().map();
		case GithubMMPackage.JOB__SERVICES:
			if (coreType)
				return getServices();
			else
				return getServices().map();
		case GithubMMPackage.JOB__CONCURRENCY_GROUP:
			return getConcurrencyGroup();
		case GithubMMPackage.JOB__TIMEOUT_MINUTES:
			return getTimeoutMinutes();
		case GithubMMPackage.JOB__CONTINUE_ON_ERROR:
			return getContinueOnError();
		case GithubMMPackage.JOB__STRATEGY:
			return getStrategy();
		case GithubMMPackage.JOB__OUTPUTS:
			return getOutputs();
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
		case GithubMMPackage.JOB__NAME:
			setName((String) newValue);
			return;
		case GithubMMPackage.JOB__JOB_NAME:
			setJobName((Expression) newValue);
			return;
		case GithubMMPackage.JOB__PERMISSIONS:
			((EStructuralFeature.Setting) getPermissions()).set(newValue);
			return;
		case GithubMMPackage.JOB__DEPENDS_ON:
			getDependsOn().clear();
			getDependsOn().addAll((Collection<? extends Job>) newValue);
			return;
		case GithubMMPackage.JOB__NECESSARY_FOR:
			getNecessaryFor().clear();
			getNecessaryFor().addAll((Collection<? extends Job>) newValue);
			return;
		case GithubMMPackage.JOB__IF_CONDITION:
			setIfCondition((Expression) newValue);
			return;
		case GithubMMPackage.JOB__AGENT:
			setAgent((Agent) newValue);
			return;
		case GithubMMPackage.JOB__CONTAINER:
			setContainer((com.mddoai.metamodel.github.githubMM.Container) newValue);
			return;
		case GithubMMPackage.JOB__STAGING_ENVIRONMENT:
			setStagingEnvironment((StagingEnvironment) newValue);
			return;
		case GithubMMPackage.JOB__DEFAULTS:
			setDefaults((Defaults) newValue);
			return;
		case GithubMMPackage.JOB__ENVIRONMENT_VARIABLES:
			((EStructuralFeature.Setting) getEnvironmentVariables()).set(newValue);
			return;
		case GithubMMPackage.JOB__SERVICES:
			((EStructuralFeature.Setting) getServices()).set(newValue);
			return;
		case GithubMMPackage.JOB__CONCURRENCY_GROUP:
			setConcurrencyGroup((ConcurrencyGroup) newValue);
			return;
		case GithubMMPackage.JOB__TIMEOUT_MINUTES:
			setTimeoutMinutes((Expression) newValue);
			return;
		case GithubMMPackage.JOB__CONTINUE_ON_ERROR:
			setContinueOnError((Expression) newValue);
			return;
		case GithubMMPackage.JOB__STRATEGY:
			setStrategy((Matrix) newValue);
			return;
		case GithubMMPackage.JOB__OUTPUTS:
			getOutputs().clear();
			getOutputs().addAll((Collection<? extends Output>) newValue);
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
		case GithubMMPackage.JOB__NAME:
			setName(NAME_EDEFAULT);
			return;
		case GithubMMPackage.JOB__JOB_NAME:
			setJobName((Expression) null);
			return;
		case GithubMMPackage.JOB__PERMISSIONS:
			getPermissions().clear();
			return;
		case GithubMMPackage.JOB__DEPENDS_ON:
			getDependsOn().clear();
			return;
		case GithubMMPackage.JOB__NECESSARY_FOR:
			getNecessaryFor().clear();
			return;
		case GithubMMPackage.JOB__IF_CONDITION:
			setIfCondition((Expression) null);
			return;
		case GithubMMPackage.JOB__AGENT:
			setAgent((Agent) null);
			return;
		case GithubMMPackage.JOB__CONTAINER:
			setContainer((com.mddoai.metamodel.github.githubMM.Container) null);
			return;
		case GithubMMPackage.JOB__STAGING_ENVIRONMENT:
			setStagingEnvironment((StagingEnvironment) null);
			return;
		case GithubMMPackage.JOB__DEFAULTS:
			setDefaults((Defaults) null);
			return;
		case GithubMMPackage.JOB__ENVIRONMENT_VARIABLES:
			getEnvironmentVariables().clear();
			return;
		case GithubMMPackage.JOB__SERVICES:
			getServices().clear();
			return;
		case GithubMMPackage.JOB__CONCURRENCY_GROUP:
			setConcurrencyGroup((ConcurrencyGroup) null);
			return;
		case GithubMMPackage.JOB__TIMEOUT_MINUTES:
			setTimeoutMinutes((Expression) null);
			return;
		case GithubMMPackage.JOB__CONTINUE_ON_ERROR:
			setContinueOnError((Expression) null);
			return;
		case GithubMMPackage.JOB__STRATEGY:
			setStrategy((Matrix) null);
			return;
		case GithubMMPackage.JOB__OUTPUTS:
			getOutputs().clear();
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
		case GithubMMPackage.JOB__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case GithubMMPackage.JOB__JOB_NAME:
			return jobName != null;
		case GithubMMPackage.JOB__PERMISSIONS:
			return permissions != null && !permissions.isEmpty();
		case GithubMMPackage.JOB__DEPENDS_ON:
			return dependsOn != null && !dependsOn.isEmpty();
		case GithubMMPackage.JOB__NECESSARY_FOR:
			return necessaryFor != null && !necessaryFor.isEmpty();
		case GithubMMPackage.JOB__IF_CONDITION:
			return ifCondition != null;
		case GithubMMPackage.JOB__AGENT:
			return agent != null;
		case GithubMMPackage.JOB__CONTAINER:
			return container != null;
		case GithubMMPackage.JOB__STAGING_ENVIRONMENT:
			return stagingEnvironment != null;
		case GithubMMPackage.JOB__DEFAULTS:
			return defaults != null;
		case GithubMMPackage.JOB__ENVIRONMENT_VARIABLES:
			return environmentVariables != null && !environmentVariables.isEmpty();
		case GithubMMPackage.JOB__SERVICES:
			return services != null && !services.isEmpty();
		case GithubMMPackage.JOB__CONCURRENCY_GROUP:
			return concurrencyGroup != null;
		case GithubMMPackage.JOB__TIMEOUT_MINUTES:
			return timeoutMinutes != null;
		case GithubMMPackage.JOB__CONTINUE_ON_ERROR:
			return continueOnError != null;
		case GithubMMPackage.JOB__STRATEGY:
			return strategy != null;
		case GithubMMPackage.JOB__OUTPUTS:
			return outputs != null && !outputs.isEmpty();
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
		result.append(')');
		return result.toString();
	}

} //JobImpl
