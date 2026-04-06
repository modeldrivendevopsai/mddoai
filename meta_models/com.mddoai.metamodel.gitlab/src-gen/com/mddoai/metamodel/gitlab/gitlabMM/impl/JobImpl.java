/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM.impl;

import com.mddoai.metamodel.gitlab.gitlabMM.Artifacts;
import com.mddoai.metamodel.gitlab.gitlabMM.Cache;
import com.mddoai.metamodel.gitlab.gitlabMM.Environment;
import com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage;
import com.mddoai.metamodel.gitlab.gitlabMM.Image;
import com.mddoai.metamodel.gitlab.gitlabMM.Job;
import com.mddoai.metamodel.gitlab.gitlabMM.Need;
import com.mddoai.metamodel.gitlab.gitlabMM.Parallel;
import com.mddoai.metamodel.gitlab.gitlabMM.Retry;
import com.mddoai.metamodel.gitlab.gitlabMM.Rule;
import com.mddoai.metamodel.gitlab.gitlabMM.Service;
import com.mddoai.metamodel.gitlab.gitlabMM.Variable;
import com.mddoai.metamodel.gitlab.gitlabMM.WhenType;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Job</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl#getStage <em>Stage</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl#getImage <em>Image</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl#getScript <em>Script</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl#getBeforeScript <em>Before Script</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl#getAfterScript <em>After Script</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl#getServices <em>Services</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl#getVariables <em>Variables</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl#getArtifacts <em>Artifacts</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl#getCache <em>Cache</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl#getNeeds <em>Needs</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl#getRules <em>Rules</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl#getTags <em>Tags</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl#getOnly <em>Only</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl#getExcept <em>Except</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl#getWhen <em>When</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl#getAllowFailure <em>Allow Failure</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl#getTimeout <em>Timeout</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl#getInterruptible <em>Interruptible</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl#getResourceGroup <em>Resource Group</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl#getRetry <em>Retry</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl#getParallel <em>Parallel</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl#getEnvironment <em>Environment</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl#getDependencies <em>Dependencies</em>}</li>
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
	 * The default value of the '{@link #getStage() <em>Stage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStage()
	 * @generated
	 * @ordered
	 */
	protected static final String STAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStage() <em>Stage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStage()
	 * @generated
	 * @ordered
	 */
	protected String stage = STAGE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getImage() <em>Image</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImage()
	 * @generated
	 * @ordered
	 */
	protected Image image;

	/**
	 * The cached value of the '{@link #getScript() <em>Script</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScript()
	 * @generated
	 * @ordered
	 */
	protected EList<String> script;

	/**
	 * The cached value of the '{@link #getBeforeScript() <em>Before Script</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBeforeScript()
	 * @generated
	 * @ordered
	 */
	protected EList<String> beforeScript;

	/**
	 * The cached value of the '{@link #getAfterScript() <em>After Script</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAfterScript()
	 * @generated
	 * @ordered
	 */
	protected EList<String> afterScript;

	/**
	 * The cached value of the '{@link #getServices() <em>Services</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServices()
	 * @generated
	 * @ordered
	 */
	protected EList<Service> services;

	/**
	 * The cached value of the '{@link #getVariables() <em>Variables</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariables()
	 * @generated
	 * @ordered
	 */
	protected EList<Variable> variables;

	/**
	 * The cached value of the '{@link #getArtifacts() <em>Artifacts</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArtifacts()
	 * @generated
	 * @ordered
	 */
	protected Artifacts artifacts;

	/**
	 * The cached value of the '{@link #getCache() <em>Cache</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCache()
	 * @generated
	 * @ordered
	 */
	protected Cache cache;

	/**
	 * The cached value of the '{@link #getNeeds() <em>Needs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNeeds()
	 * @generated
	 * @ordered
	 */
	protected EList<Need> needs;

	/**
	 * The cached value of the '{@link #getRules() <em>Rules</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRules()
	 * @generated
	 * @ordered
	 */
	protected EList<Rule> rules;

	/**
	 * The cached value of the '{@link #getTags() <em>Tags</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTags()
	 * @generated
	 * @ordered
	 */
	protected EList<String> tags;

	/**
	 * The cached value of the '{@link #getOnly() <em>Only</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnly()
	 * @generated
	 * @ordered
	 */
	protected EList<String> only;

	/**
	 * The cached value of the '{@link #getExcept() <em>Except</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExcept()
	 * @generated
	 * @ordered
	 */
	protected EList<String> except;

	/**
	 * The default value of the '{@link #getWhen() <em>When</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWhen()
	 * @generated
	 * @ordered
	 */
	protected static final WhenType WHEN_EDEFAULT = WhenType.ON_SUCCESS;

	/**
	 * The cached value of the '{@link #getWhen() <em>When</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWhen()
	 * @generated
	 * @ordered
	 */
	protected WhenType when = WHEN_EDEFAULT;

	/**
	 * The default value of the '{@link #getAllowFailure() <em>Allow Failure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllowFailure()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean ALLOW_FAILURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAllowFailure() <em>Allow Failure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllowFailure()
	 * @generated
	 * @ordered
	 */
	protected Boolean allowFailure = ALLOW_FAILURE_EDEFAULT;

	/**
	 * The default value of the '{@link #getTimeout() <em>Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeout()
	 * @generated
	 * @ordered
	 */
	protected static final String TIMEOUT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTimeout() <em>Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeout()
	 * @generated
	 * @ordered
	 */
	protected String timeout = TIMEOUT_EDEFAULT;

	/**
	 * The default value of the '{@link #getInterruptible() <em>Interruptible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterruptible()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean INTERRUPTIBLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInterruptible() <em>Interruptible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterruptible()
	 * @generated
	 * @ordered
	 */
	protected Boolean interruptible = INTERRUPTIBLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getResourceGroup() <em>Resource Group</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceGroup()
	 * @generated
	 * @ordered
	 */
	protected static final String RESOURCE_GROUP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResourceGroup() <em>Resource Group</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceGroup()
	 * @generated
	 * @ordered
	 */
	protected String resourceGroup = RESOURCE_GROUP_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRetry() <em>Retry</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRetry()
	 * @generated
	 * @ordered
	 */
	protected Retry retry;

	/**
	 * The cached value of the '{@link #getParallel() <em>Parallel</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParallel()
	 * @generated
	 * @ordered
	 */
	protected Parallel parallel;

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
	 * The cached value of the '{@link #getDependencies() <em>Dependencies</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDependencies()
	 * @generated
	 * @ordered
	 */
	protected EList<String> dependencies;

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
		return GitlabMMPackage.Literals.JOB;
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
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.JOB__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getStage() {
		return stage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStage(String newStage) {
		String oldStage = stage;
		stage = newStage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.JOB__STAGE, oldStage, stage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Image getImage() {
		return image;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetImage(Image newImage, NotificationChain msgs) {
		Image oldImage = image;
		image = newImage;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GitlabMMPackage.JOB__IMAGE,
					oldImage, newImage);
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
	public void setImage(Image newImage) {
		if (newImage != image) {
			NotificationChain msgs = null;
			if (image != null)
				msgs = ((InternalEObject) image).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.JOB__IMAGE, null, msgs);
			if (newImage != null)
				msgs = ((InternalEObject) newImage).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.JOB__IMAGE, null, msgs);
			msgs = basicSetImage(newImage, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.JOB__IMAGE, newImage, newImage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getScript() {
		if (script == null) {
			script = new EDataTypeUniqueEList<String>(String.class, this, GitlabMMPackage.JOB__SCRIPT);
		}
		return script;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getBeforeScript() {
		if (beforeScript == null) {
			beforeScript = new EDataTypeUniqueEList<String>(String.class, this, GitlabMMPackage.JOB__BEFORE_SCRIPT);
		}
		return beforeScript;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getAfterScript() {
		if (afterScript == null) {
			afterScript = new EDataTypeUniqueEList<String>(String.class, this, GitlabMMPackage.JOB__AFTER_SCRIPT);
		}
		return afterScript;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Service> getServices() {
		if (services == null) {
			services = new EObjectContainmentEList<Service>(Service.class, this, GitlabMMPackage.JOB__SERVICES);
		}
		return services;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Variable> getVariables() {
		if (variables == null) {
			variables = new EObjectContainmentEList<Variable>(Variable.class, this, GitlabMMPackage.JOB__VARIABLES);
		}
		return variables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Artifacts getArtifacts() {
		return artifacts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetArtifacts(Artifacts newArtifacts, NotificationChain msgs) {
		Artifacts oldArtifacts = artifacts;
		artifacts = newArtifacts;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GitlabMMPackage.JOB__ARTIFACTS, oldArtifacts, newArtifacts);
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
	public void setArtifacts(Artifacts newArtifacts) {
		if (newArtifacts != artifacts) {
			NotificationChain msgs = null;
			if (artifacts != null)
				msgs = ((InternalEObject) artifacts).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.JOB__ARTIFACTS, null, msgs);
			if (newArtifacts != null)
				msgs = ((InternalEObject) newArtifacts).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.JOB__ARTIFACTS, null, msgs);
			msgs = basicSetArtifacts(newArtifacts, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.JOB__ARTIFACTS, newArtifacts,
					newArtifacts));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Cache getCache() {
		return cache;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCache(Cache newCache, NotificationChain msgs) {
		Cache oldCache = cache;
		cache = newCache;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GitlabMMPackage.JOB__CACHE,
					oldCache, newCache);
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
	public void setCache(Cache newCache) {
		if (newCache != cache) {
			NotificationChain msgs = null;
			if (cache != null)
				msgs = ((InternalEObject) cache).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.JOB__CACHE, null, msgs);
			if (newCache != null)
				msgs = ((InternalEObject) newCache).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.JOB__CACHE, null, msgs);
			msgs = basicSetCache(newCache, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.JOB__CACHE, newCache, newCache));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Need> getNeeds() {
		if (needs == null) {
			needs = new EObjectContainmentEList<Need>(Need.class, this, GitlabMMPackage.JOB__NEEDS);
		}
		return needs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Rule> getRules() {
		if (rules == null) {
			rules = new EObjectContainmentEList<Rule>(Rule.class, this, GitlabMMPackage.JOB__RULES);
		}
		return rules;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getTags() {
		if (tags == null) {
			tags = new EDataTypeUniqueEList<String>(String.class, this, GitlabMMPackage.JOB__TAGS);
		}
		return tags;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getOnly() {
		if (only == null) {
			only = new EDataTypeUniqueEList<String>(String.class, this, GitlabMMPackage.JOB__ONLY);
		}
		return only;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getExcept() {
		if (except == null) {
			except = new EDataTypeUniqueEList<String>(String.class, this, GitlabMMPackage.JOB__EXCEPT);
		}
		return except;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WhenType getWhen() {
		return when;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWhen(WhenType newWhen) {
		WhenType oldWhen = when;
		when = newWhen == null ? WHEN_EDEFAULT : newWhen;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.JOB__WHEN, oldWhen, when));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getAllowFailure() {
		return allowFailure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAllowFailure(Boolean newAllowFailure) {
		Boolean oldAllowFailure = allowFailure;
		allowFailure = newAllowFailure;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.JOB__ALLOW_FAILURE, oldAllowFailure,
					allowFailure));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTimeout() {
		return timeout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTimeout(String newTimeout) {
		String oldTimeout = timeout;
		timeout = newTimeout;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.JOB__TIMEOUT, oldTimeout, timeout));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getInterruptible() {
		return interruptible;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setInterruptible(Boolean newInterruptible) {
		Boolean oldInterruptible = interruptible;
		interruptible = newInterruptible;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.JOB__INTERRUPTIBLE, oldInterruptible,
					interruptible));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getResourceGroup() {
		return resourceGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setResourceGroup(String newResourceGroup) {
		String oldResourceGroup = resourceGroup;
		resourceGroup = newResourceGroup;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.JOB__RESOURCE_GROUP, oldResourceGroup,
					resourceGroup));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Retry getRetry() {
		return retry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRetry(Retry newRetry, NotificationChain msgs) {
		Retry oldRetry = retry;
		retry = newRetry;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GitlabMMPackage.JOB__RETRY,
					oldRetry, newRetry);
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
	public void setRetry(Retry newRetry) {
		if (newRetry != retry) {
			NotificationChain msgs = null;
			if (retry != null)
				msgs = ((InternalEObject) retry).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.JOB__RETRY, null, msgs);
			if (newRetry != null)
				msgs = ((InternalEObject) newRetry).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.JOB__RETRY, null, msgs);
			msgs = basicSetRetry(newRetry, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.JOB__RETRY, newRetry, newRetry));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Parallel getParallel() {
		return parallel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParallel(Parallel newParallel, NotificationChain msgs) {
		Parallel oldParallel = parallel;
		parallel = newParallel;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GitlabMMPackage.JOB__PARALLEL, oldParallel, newParallel);
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
	public void setParallel(Parallel newParallel) {
		if (newParallel != parallel) {
			NotificationChain msgs = null;
			if (parallel != null)
				msgs = ((InternalEObject) parallel).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.JOB__PARALLEL, null, msgs);
			if (newParallel != null)
				msgs = ((InternalEObject) newParallel).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.JOB__PARALLEL, null, msgs);
			msgs = basicSetParallel(newParallel, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.JOB__PARALLEL, newParallel,
					newParallel));
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
					GitlabMMPackage.JOB__ENVIRONMENT, oldEnvironment, newEnvironment);
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
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.JOB__ENVIRONMENT, null, msgs);
			if (newEnvironment != null)
				msgs = ((InternalEObject) newEnvironment).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.JOB__ENVIRONMENT, null, msgs);
			msgs = basicSetEnvironment(newEnvironment, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.JOB__ENVIRONMENT, newEnvironment,
					newEnvironment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getDependencies() {
		if (dependencies == null) {
			dependencies = new EDataTypeUniqueEList<String>(String.class, this, GitlabMMPackage.JOB__DEPENDENCIES);
		}
		return dependencies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GitlabMMPackage.JOB__IMAGE:
			return basicSetImage(null, msgs);
		case GitlabMMPackage.JOB__SERVICES:
			return ((InternalEList<?>) getServices()).basicRemove(otherEnd, msgs);
		case GitlabMMPackage.JOB__VARIABLES:
			return ((InternalEList<?>) getVariables()).basicRemove(otherEnd, msgs);
		case GitlabMMPackage.JOB__ARTIFACTS:
			return basicSetArtifacts(null, msgs);
		case GitlabMMPackage.JOB__CACHE:
			return basicSetCache(null, msgs);
		case GitlabMMPackage.JOB__NEEDS:
			return ((InternalEList<?>) getNeeds()).basicRemove(otherEnd, msgs);
		case GitlabMMPackage.JOB__RULES:
			return ((InternalEList<?>) getRules()).basicRemove(otherEnd, msgs);
		case GitlabMMPackage.JOB__RETRY:
			return basicSetRetry(null, msgs);
		case GitlabMMPackage.JOB__PARALLEL:
			return basicSetParallel(null, msgs);
		case GitlabMMPackage.JOB__ENVIRONMENT:
			return basicSetEnvironment(null, msgs);
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
		case GitlabMMPackage.JOB__NAME:
			return getName();
		case GitlabMMPackage.JOB__STAGE:
			return getStage();
		case GitlabMMPackage.JOB__IMAGE:
			return getImage();
		case GitlabMMPackage.JOB__SCRIPT:
			return getScript();
		case GitlabMMPackage.JOB__BEFORE_SCRIPT:
			return getBeforeScript();
		case GitlabMMPackage.JOB__AFTER_SCRIPT:
			return getAfterScript();
		case GitlabMMPackage.JOB__SERVICES:
			return getServices();
		case GitlabMMPackage.JOB__VARIABLES:
			return getVariables();
		case GitlabMMPackage.JOB__ARTIFACTS:
			return getArtifacts();
		case GitlabMMPackage.JOB__CACHE:
			return getCache();
		case GitlabMMPackage.JOB__NEEDS:
			return getNeeds();
		case GitlabMMPackage.JOB__RULES:
			return getRules();
		case GitlabMMPackage.JOB__TAGS:
			return getTags();
		case GitlabMMPackage.JOB__ONLY:
			return getOnly();
		case GitlabMMPackage.JOB__EXCEPT:
			return getExcept();
		case GitlabMMPackage.JOB__WHEN:
			return getWhen();
		case GitlabMMPackage.JOB__ALLOW_FAILURE:
			return getAllowFailure();
		case GitlabMMPackage.JOB__TIMEOUT:
			return getTimeout();
		case GitlabMMPackage.JOB__INTERRUPTIBLE:
			return getInterruptible();
		case GitlabMMPackage.JOB__RESOURCE_GROUP:
			return getResourceGroup();
		case GitlabMMPackage.JOB__RETRY:
			return getRetry();
		case GitlabMMPackage.JOB__PARALLEL:
			return getParallel();
		case GitlabMMPackage.JOB__ENVIRONMENT:
			return getEnvironment();
		case GitlabMMPackage.JOB__DEPENDENCIES:
			return getDependencies();
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
		case GitlabMMPackage.JOB__NAME:
			setName((String) newValue);
			return;
		case GitlabMMPackage.JOB__STAGE:
			setStage((String) newValue);
			return;
		case GitlabMMPackage.JOB__IMAGE:
			setImage((Image) newValue);
			return;
		case GitlabMMPackage.JOB__SCRIPT:
			getScript().clear();
			getScript().addAll((Collection<? extends String>) newValue);
			return;
		case GitlabMMPackage.JOB__BEFORE_SCRIPT:
			getBeforeScript().clear();
			getBeforeScript().addAll((Collection<? extends String>) newValue);
			return;
		case GitlabMMPackage.JOB__AFTER_SCRIPT:
			getAfterScript().clear();
			getAfterScript().addAll((Collection<? extends String>) newValue);
			return;
		case GitlabMMPackage.JOB__SERVICES:
			getServices().clear();
			getServices().addAll((Collection<? extends Service>) newValue);
			return;
		case GitlabMMPackage.JOB__VARIABLES:
			getVariables().clear();
			getVariables().addAll((Collection<? extends Variable>) newValue);
			return;
		case GitlabMMPackage.JOB__ARTIFACTS:
			setArtifacts((Artifacts) newValue);
			return;
		case GitlabMMPackage.JOB__CACHE:
			setCache((Cache) newValue);
			return;
		case GitlabMMPackage.JOB__NEEDS:
			getNeeds().clear();
			getNeeds().addAll((Collection<? extends Need>) newValue);
			return;
		case GitlabMMPackage.JOB__RULES:
			getRules().clear();
			getRules().addAll((Collection<? extends Rule>) newValue);
			return;
		case GitlabMMPackage.JOB__TAGS:
			getTags().clear();
			getTags().addAll((Collection<? extends String>) newValue);
			return;
		case GitlabMMPackage.JOB__ONLY:
			getOnly().clear();
			getOnly().addAll((Collection<? extends String>) newValue);
			return;
		case GitlabMMPackage.JOB__EXCEPT:
			getExcept().clear();
			getExcept().addAll((Collection<? extends String>) newValue);
			return;
		case GitlabMMPackage.JOB__WHEN:
			setWhen((WhenType) newValue);
			return;
		case GitlabMMPackage.JOB__ALLOW_FAILURE:
			setAllowFailure((Boolean) newValue);
			return;
		case GitlabMMPackage.JOB__TIMEOUT:
			setTimeout((String) newValue);
			return;
		case GitlabMMPackage.JOB__INTERRUPTIBLE:
			setInterruptible((Boolean) newValue);
			return;
		case GitlabMMPackage.JOB__RESOURCE_GROUP:
			setResourceGroup((String) newValue);
			return;
		case GitlabMMPackage.JOB__RETRY:
			setRetry((Retry) newValue);
			return;
		case GitlabMMPackage.JOB__PARALLEL:
			setParallel((Parallel) newValue);
			return;
		case GitlabMMPackage.JOB__ENVIRONMENT:
			setEnvironment((Environment) newValue);
			return;
		case GitlabMMPackage.JOB__DEPENDENCIES:
			getDependencies().clear();
			getDependencies().addAll((Collection<? extends String>) newValue);
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
		case GitlabMMPackage.JOB__NAME:
			setName(NAME_EDEFAULT);
			return;
		case GitlabMMPackage.JOB__STAGE:
			setStage(STAGE_EDEFAULT);
			return;
		case GitlabMMPackage.JOB__IMAGE:
			setImage((Image) null);
			return;
		case GitlabMMPackage.JOB__SCRIPT:
			getScript().clear();
			return;
		case GitlabMMPackage.JOB__BEFORE_SCRIPT:
			getBeforeScript().clear();
			return;
		case GitlabMMPackage.JOB__AFTER_SCRIPT:
			getAfterScript().clear();
			return;
		case GitlabMMPackage.JOB__SERVICES:
			getServices().clear();
			return;
		case GitlabMMPackage.JOB__VARIABLES:
			getVariables().clear();
			return;
		case GitlabMMPackage.JOB__ARTIFACTS:
			setArtifacts((Artifacts) null);
			return;
		case GitlabMMPackage.JOB__CACHE:
			setCache((Cache) null);
			return;
		case GitlabMMPackage.JOB__NEEDS:
			getNeeds().clear();
			return;
		case GitlabMMPackage.JOB__RULES:
			getRules().clear();
			return;
		case GitlabMMPackage.JOB__TAGS:
			getTags().clear();
			return;
		case GitlabMMPackage.JOB__ONLY:
			getOnly().clear();
			return;
		case GitlabMMPackage.JOB__EXCEPT:
			getExcept().clear();
			return;
		case GitlabMMPackage.JOB__WHEN:
			setWhen(WHEN_EDEFAULT);
			return;
		case GitlabMMPackage.JOB__ALLOW_FAILURE:
			setAllowFailure(ALLOW_FAILURE_EDEFAULT);
			return;
		case GitlabMMPackage.JOB__TIMEOUT:
			setTimeout(TIMEOUT_EDEFAULT);
			return;
		case GitlabMMPackage.JOB__INTERRUPTIBLE:
			setInterruptible(INTERRUPTIBLE_EDEFAULT);
			return;
		case GitlabMMPackage.JOB__RESOURCE_GROUP:
			setResourceGroup(RESOURCE_GROUP_EDEFAULT);
			return;
		case GitlabMMPackage.JOB__RETRY:
			setRetry((Retry) null);
			return;
		case GitlabMMPackage.JOB__PARALLEL:
			setParallel((Parallel) null);
			return;
		case GitlabMMPackage.JOB__ENVIRONMENT:
			setEnvironment((Environment) null);
			return;
		case GitlabMMPackage.JOB__DEPENDENCIES:
			getDependencies().clear();
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
		case GitlabMMPackage.JOB__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case GitlabMMPackage.JOB__STAGE:
			return STAGE_EDEFAULT == null ? stage != null : !STAGE_EDEFAULT.equals(stage);
		case GitlabMMPackage.JOB__IMAGE:
			return image != null;
		case GitlabMMPackage.JOB__SCRIPT:
			return script != null && !script.isEmpty();
		case GitlabMMPackage.JOB__BEFORE_SCRIPT:
			return beforeScript != null && !beforeScript.isEmpty();
		case GitlabMMPackage.JOB__AFTER_SCRIPT:
			return afterScript != null && !afterScript.isEmpty();
		case GitlabMMPackage.JOB__SERVICES:
			return services != null && !services.isEmpty();
		case GitlabMMPackage.JOB__VARIABLES:
			return variables != null && !variables.isEmpty();
		case GitlabMMPackage.JOB__ARTIFACTS:
			return artifacts != null;
		case GitlabMMPackage.JOB__CACHE:
			return cache != null;
		case GitlabMMPackage.JOB__NEEDS:
			return needs != null && !needs.isEmpty();
		case GitlabMMPackage.JOB__RULES:
			return rules != null && !rules.isEmpty();
		case GitlabMMPackage.JOB__TAGS:
			return tags != null && !tags.isEmpty();
		case GitlabMMPackage.JOB__ONLY:
			return only != null && !only.isEmpty();
		case GitlabMMPackage.JOB__EXCEPT:
			return except != null && !except.isEmpty();
		case GitlabMMPackage.JOB__WHEN:
			return when != WHEN_EDEFAULT;
		case GitlabMMPackage.JOB__ALLOW_FAILURE:
			return ALLOW_FAILURE_EDEFAULT == null ? allowFailure != null : !ALLOW_FAILURE_EDEFAULT.equals(allowFailure);
		case GitlabMMPackage.JOB__TIMEOUT:
			return TIMEOUT_EDEFAULT == null ? timeout != null : !TIMEOUT_EDEFAULT.equals(timeout);
		case GitlabMMPackage.JOB__INTERRUPTIBLE:
			return INTERRUPTIBLE_EDEFAULT == null ? interruptible != null
					: !INTERRUPTIBLE_EDEFAULT.equals(interruptible);
		case GitlabMMPackage.JOB__RESOURCE_GROUP:
			return RESOURCE_GROUP_EDEFAULT == null ? resourceGroup != null
					: !RESOURCE_GROUP_EDEFAULT.equals(resourceGroup);
		case GitlabMMPackage.JOB__RETRY:
			return retry != null;
		case GitlabMMPackage.JOB__PARALLEL:
			return parallel != null;
		case GitlabMMPackage.JOB__ENVIRONMENT:
			return environment != null;
		case GitlabMMPackage.JOB__DEPENDENCIES:
			return dependencies != null && !dependencies.isEmpty();
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
		result.append(", stage: ");
		result.append(stage);
		result.append(", script: ");
		result.append(script);
		result.append(", beforeScript: ");
		result.append(beforeScript);
		result.append(", afterScript: ");
		result.append(afterScript);
		result.append(", tags: ");
		result.append(tags);
		result.append(", only: ");
		result.append(only);
		result.append(", except: ");
		result.append(except);
		result.append(", when: ");
		result.append(when);
		result.append(", allowFailure: ");
		result.append(allowFailure);
		result.append(", timeout: ");
		result.append(timeout);
		result.append(", interruptible: ");
		result.append(interruptible);
		result.append(", resourceGroup: ");
		result.append(resourceGroup);
		result.append(", dependencies: ");
		result.append(dependencies);
		result.append(')');
		return result.toString();
	}

} //JobImpl
