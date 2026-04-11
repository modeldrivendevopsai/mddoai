/**
 */
package com.mddoai.metamodel.bambooMM.impl;

import com.mddoai.metamodel.bambooMM.BambooPackage;
import com.mddoai.metamodel.bambooMM.ChangeDetection;
import com.mddoai.metamodel.bambooMM.GitRepository;
import com.mddoai.metamodel.bambooMM.REPOSITORY_SCOPE;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Git Repository</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.GitRepositoryImpl#getUrl <em>Url</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.GitRepositoryImpl#getBranch <em>Branch</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.GitRepositoryImpl#getUsername <em>Username</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.GitRepositoryImpl#getPassword <em>Password</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.GitRepositoryImpl#getSshKey <em>Ssh Key</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.GitRepositoryImpl#getSshKeyPassphrase <em>Ssh Key Passphrase</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.GitRepositoryImpl#getSharedCredentials <em>Shared Credentials</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.GitRepositoryImpl#getSharedCredentialsScope <em>Shared Credentials Scope</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.GitRepositoryImpl#getLfs <em>Lfs</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.GitRepositoryImpl#getUseShallowClones <em>Use Shallow Clones</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.GitRepositoryImpl#getSubmodules <em>Submodules</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.GitRepositoryImpl#getFetchAll <em>Fetch All</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.GitRepositoryImpl#getCacheOnAgents <em>Cache On Agents</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.GitRepositoryImpl#getVerboseLogs <em>Verbose Logs</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.GitRepositoryImpl#getCommandTimeoutMinutes <em>Command Timeout Minutes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.GitRepositoryImpl#getChangeDetection <em>Change Detection</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GitRepositoryImpl extends RepositoryImpl implements GitRepository {
	/**
	 * The default value of the '{@link #getUrl() <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUrl()
	 * @generated
	 * @ordered
	 */
	protected static final String URL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUrl() <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUrl()
	 * @generated
	 * @ordered
	 */
	protected String url = URL_EDEFAULT;

	/**
	 * The default value of the '{@link #getBranch() <em>Branch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBranch()
	 * @generated
	 * @ordered
	 */
	protected static final String BRANCH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBranch() <em>Branch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBranch()
	 * @generated
	 * @ordered
	 */
	protected String branch = BRANCH_EDEFAULT;

	/**
	 * The default value of the '{@link #getUsername() <em>Username</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsername()
	 * @generated
	 * @ordered
	 */
	protected static final String USERNAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUsername() <em>Username</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsername()
	 * @generated
	 * @ordered
	 */
	protected String username = USERNAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getPassword() <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPassword()
	 * @generated
	 * @ordered
	 */
	protected static final String PASSWORD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPassword() <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPassword()
	 * @generated
	 * @ordered
	 */
	protected String password = PASSWORD_EDEFAULT;

	/**
	 * The default value of the '{@link #getSshKey() <em>Ssh Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSshKey()
	 * @generated
	 * @ordered
	 */
	protected static final String SSH_KEY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSshKey() <em>Ssh Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSshKey()
	 * @generated
	 * @ordered
	 */
	protected String sshKey = SSH_KEY_EDEFAULT;

	/**
	 * The default value of the '{@link #getSshKeyPassphrase() <em>Ssh Key Passphrase</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSshKeyPassphrase()
	 * @generated
	 * @ordered
	 */
	protected static final String SSH_KEY_PASSPHRASE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSshKeyPassphrase() <em>Ssh Key Passphrase</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSshKeyPassphrase()
	 * @generated
	 * @ordered
	 */
	protected String sshKeyPassphrase = SSH_KEY_PASSPHRASE_EDEFAULT;

	/**
	 * The default value of the '{@link #getSharedCredentials() <em>Shared Credentials</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSharedCredentials()
	 * @generated
	 * @ordered
	 */
	protected static final String SHARED_CREDENTIALS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSharedCredentials() <em>Shared Credentials</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSharedCredentials()
	 * @generated
	 * @ordered
	 */
	protected String sharedCredentials = SHARED_CREDENTIALS_EDEFAULT;

	/**
	 * The default value of the '{@link #getSharedCredentialsScope() <em>Shared Credentials Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSharedCredentialsScope()
	 * @generated
	 * @ordered
	 */
	protected static final REPOSITORY_SCOPE SHARED_CREDENTIALS_SCOPE_EDEFAULT = REPOSITORY_SCOPE.GLOBAL;

	/**
	 * The cached value of the '{@link #getSharedCredentialsScope() <em>Shared Credentials Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSharedCredentialsScope()
	 * @generated
	 * @ordered
	 */
	protected REPOSITORY_SCOPE sharedCredentialsScope = SHARED_CREDENTIALS_SCOPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getLfs() <em>Lfs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLfs()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean LFS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLfs() <em>Lfs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLfs()
	 * @generated
	 * @ordered
	 */
	protected Boolean lfs = LFS_EDEFAULT;

	/**
	 * The default value of the '{@link #getUseShallowClones() <em>Use Shallow Clones</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUseShallowClones()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean USE_SHALLOW_CLONES_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUseShallowClones() <em>Use Shallow Clones</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUseShallowClones()
	 * @generated
	 * @ordered
	 */
	protected Boolean useShallowClones = USE_SHALLOW_CLONES_EDEFAULT;

	/**
	 * The default value of the '{@link #getSubmodules() <em>Submodules</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubmodules()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean SUBMODULES_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSubmodules() <em>Submodules</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubmodules()
	 * @generated
	 * @ordered
	 */
	protected Boolean submodules = SUBMODULES_EDEFAULT;

	/**
	 * The default value of the '{@link #getFetchAll() <em>Fetch All</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFetchAll()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean FETCH_ALL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFetchAll() <em>Fetch All</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFetchAll()
	 * @generated
	 * @ordered
	 */
	protected Boolean fetchAll = FETCH_ALL_EDEFAULT;

	/**
	 * The default value of the '{@link #getCacheOnAgents() <em>Cache On Agents</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCacheOnAgents()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean CACHE_ON_AGENTS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCacheOnAgents() <em>Cache On Agents</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCacheOnAgents()
	 * @generated
	 * @ordered
	 */
	protected Boolean cacheOnAgents = CACHE_ON_AGENTS_EDEFAULT;

	/**
	 * The default value of the '{@link #getVerboseLogs() <em>Verbose Logs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVerboseLogs()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean VERBOSE_LOGS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVerboseLogs() <em>Verbose Logs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVerboseLogs()
	 * @generated
	 * @ordered
	 */
	protected Boolean verboseLogs = VERBOSE_LOGS_EDEFAULT;

	/**
	 * The default value of the '{@link #getCommandTimeoutMinutes() <em>Command Timeout Minutes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCommandTimeoutMinutes()
	 * @generated
	 * @ordered
	 */
	protected static final Integer COMMAND_TIMEOUT_MINUTES_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCommandTimeoutMinutes() <em>Command Timeout Minutes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCommandTimeoutMinutes()
	 * @generated
	 * @ordered
	 */
	protected Integer commandTimeoutMinutes = COMMAND_TIMEOUT_MINUTES_EDEFAULT;

	/**
	 * The cached value of the '{@link #getChangeDetection() <em>Change Detection</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChangeDetection()
	 * @generated
	 * @ordered
	 */
	protected ChangeDetection changeDetection;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GitRepositoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BambooPackage.Literals.GIT_REPOSITORY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getUrl() {
		return url;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUrl(String newUrl) {
		String oldUrl = url;
		url = newUrl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.GIT_REPOSITORY__URL, oldUrl, url));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getBranch() {
		return branch;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBranch(String newBranch) {
		String oldBranch = branch;
		branch = newBranch;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.GIT_REPOSITORY__BRANCH, oldBranch,
					branch));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getUsername() {
		return username;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUsername(String newUsername) {
		String oldUsername = username;
		username = newUsername;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.GIT_REPOSITORY__USERNAME, oldUsername,
					username));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPassword(String newPassword) {
		String oldPassword = password;
		password = newPassword;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.GIT_REPOSITORY__PASSWORD, oldPassword,
					password));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSshKey() {
		return sshKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSshKey(String newSshKey) {
		String oldSshKey = sshKey;
		sshKey = newSshKey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.GIT_REPOSITORY__SSH_KEY, oldSshKey,
					sshKey));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSshKeyPassphrase() {
		return sshKeyPassphrase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSshKeyPassphrase(String newSshKeyPassphrase) {
		String oldSshKeyPassphrase = sshKeyPassphrase;
		sshKeyPassphrase = newSshKeyPassphrase;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.GIT_REPOSITORY__SSH_KEY_PASSPHRASE,
					oldSshKeyPassphrase, sshKeyPassphrase));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSharedCredentials() {
		return sharedCredentials;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSharedCredentials(String newSharedCredentials) {
		String oldSharedCredentials = sharedCredentials;
		sharedCredentials = newSharedCredentials;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.GIT_REPOSITORY__SHARED_CREDENTIALS,
					oldSharedCredentials, sharedCredentials));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public REPOSITORY_SCOPE getSharedCredentialsScope() {
		return sharedCredentialsScope;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSharedCredentialsScope(REPOSITORY_SCOPE newSharedCredentialsScope) {
		REPOSITORY_SCOPE oldSharedCredentialsScope = sharedCredentialsScope;
		sharedCredentialsScope = newSharedCredentialsScope == null ? SHARED_CREDENTIALS_SCOPE_EDEFAULT
				: newSharedCredentialsScope;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					BambooPackage.GIT_REPOSITORY__SHARED_CREDENTIALS_SCOPE, oldSharedCredentialsScope,
					sharedCredentialsScope));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getLfs() {
		return lfs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLfs(Boolean newLfs) {
		Boolean oldLfs = lfs;
		lfs = newLfs;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.GIT_REPOSITORY__LFS, oldLfs, lfs));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getUseShallowClones() {
		return useShallowClones;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUseShallowClones(Boolean newUseShallowClones) {
		Boolean oldUseShallowClones = useShallowClones;
		useShallowClones = newUseShallowClones;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.GIT_REPOSITORY__USE_SHALLOW_CLONES,
					oldUseShallowClones, useShallowClones));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getSubmodules() {
		return submodules;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSubmodules(Boolean newSubmodules) {
		Boolean oldSubmodules = submodules;
		submodules = newSubmodules;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.GIT_REPOSITORY__SUBMODULES,
					oldSubmodules, submodules));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getFetchAll() {
		return fetchAll;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFetchAll(Boolean newFetchAll) {
		Boolean oldFetchAll = fetchAll;
		fetchAll = newFetchAll;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.GIT_REPOSITORY__FETCH_ALL, oldFetchAll,
					fetchAll));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getCacheOnAgents() {
		return cacheOnAgents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCacheOnAgents(Boolean newCacheOnAgents) {
		Boolean oldCacheOnAgents = cacheOnAgents;
		cacheOnAgents = newCacheOnAgents;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.GIT_REPOSITORY__CACHE_ON_AGENTS,
					oldCacheOnAgents, cacheOnAgents));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getVerboseLogs() {
		return verboseLogs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVerboseLogs(Boolean newVerboseLogs) {
		Boolean oldVerboseLogs = verboseLogs;
		verboseLogs = newVerboseLogs;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.GIT_REPOSITORY__VERBOSE_LOGS,
					oldVerboseLogs, verboseLogs));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Integer getCommandTimeoutMinutes() {
		return commandTimeoutMinutes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCommandTimeoutMinutes(Integer newCommandTimeoutMinutes) {
		Integer oldCommandTimeoutMinutes = commandTimeoutMinutes;
		commandTimeoutMinutes = newCommandTimeoutMinutes;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.GIT_REPOSITORY__COMMAND_TIMEOUT_MINUTES,
					oldCommandTimeoutMinutes, commandTimeoutMinutes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ChangeDetection getChangeDetection() {
		return changeDetection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetChangeDetection(ChangeDetection newChangeDetection, NotificationChain msgs) {
		ChangeDetection oldChangeDetection = changeDetection;
		changeDetection = newChangeDetection;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					BambooPackage.GIT_REPOSITORY__CHANGE_DETECTION, oldChangeDetection, newChangeDetection);
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
	public void setChangeDetection(ChangeDetection newChangeDetection) {
		if (newChangeDetection != changeDetection) {
			NotificationChain msgs = null;
			if (changeDetection != null)
				msgs = ((InternalEObject) changeDetection).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - BambooPackage.GIT_REPOSITORY__CHANGE_DETECTION, null, msgs);
			if (newChangeDetection != null)
				msgs = ((InternalEObject) newChangeDetection).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - BambooPackage.GIT_REPOSITORY__CHANGE_DETECTION, null, msgs);
			msgs = basicSetChangeDetection(newChangeDetection, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.GIT_REPOSITORY__CHANGE_DETECTION,
					newChangeDetection, newChangeDetection));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case BambooPackage.GIT_REPOSITORY__CHANGE_DETECTION:
			return basicSetChangeDetection(null, msgs);
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
		case BambooPackage.GIT_REPOSITORY__URL:
			return getUrl();
		case BambooPackage.GIT_REPOSITORY__BRANCH:
			return getBranch();
		case BambooPackage.GIT_REPOSITORY__USERNAME:
			return getUsername();
		case BambooPackage.GIT_REPOSITORY__PASSWORD:
			return getPassword();
		case BambooPackage.GIT_REPOSITORY__SSH_KEY:
			return getSshKey();
		case BambooPackage.GIT_REPOSITORY__SSH_KEY_PASSPHRASE:
			return getSshKeyPassphrase();
		case BambooPackage.GIT_REPOSITORY__SHARED_CREDENTIALS:
			return getSharedCredentials();
		case BambooPackage.GIT_REPOSITORY__SHARED_CREDENTIALS_SCOPE:
			return getSharedCredentialsScope();
		case BambooPackage.GIT_REPOSITORY__LFS:
			return getLfs();
		case BambooPackage.GIT_REPOSITORY__USE_SHALLOW_CLONES:
			return getUseShallowClones();
		case BambooPackage.GIT_REPOSITORY__SUBMODULES:
			return getSubmodules();
		case BambooPackage.GIT_REPOSITORY__FETCH_ALL:
			return getFetchAll();
		case BambooPackage.GIT_REPOSITORY__CACHE_ON_AGENTS:
			return getCacheOnAgents();
		case BambooPackage.GIT_REPOSITORY__VERBOSE_LOGS:
			return getVerboseLogs();
		case BambooPackage.GIT_REPOSITORY__COMMAND_TIMEOUT_MINUTES:
			return getCommandTimeoutMinutes();
		case BambooPackage.GIT_REPOSITORY__CHANGE_DETECTION:
			return getChangeDetection();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case BambooPackage.GIT_REPOSITORY__URL:
			setUrl((String) newValue);
			return;
		case BambooPackage.GIT_REPOSITORY__BRANCH:
			setBranch((String) newValue);
			return;
		case BambooPackage.GIT_REPOSITORY__USERNAME:
			setUsername((String) newValue);
			return;
		case BambooPackage.GIT_REPOSITORY__PASSWORD:
			setPassword((String) newValue);
			return;
		case BambooPackage.GIT_REPOSITORY__SSH_KEY:
			setSshKey((String) newValue);
			return;
		case BambooPackage.GIT_REPOSITORY__SSH_KEY_PASSPHRASE:
			setSshKeyPassphrase((String) newValue);
			return;
		case BambooPackage.GIT_REPOSITORY__SHARED_CREDENTIALS:
			setSharedCredentials((String) newValue);
			return;
		case BambooPackage.GIT_REPOSITORY__SHARED_CREDENTIALS_SCOPE:
			setSharedCredentialsScope((REPOSITORY_SCOPE) newValue);
			return;
		case BambooPackage.GIT_REPOSITORY__LFS:
			setLfs((Boolean) newValue);
			return;
		case BambooPackage.GIT_REPOSITORY__USE_SHALLOW_CLONES:
			setUseShallowClones((Boolean) newValue);
			return;
		case BambooPackage.GIT_REPOSITORY__SUBMODULES:
			setSubmodules((Boolean) newValue);
			return;
		case BambooPackage.GIT_REPOSITORY__FETCH_ALL:
			setFetchAll((Boolean) newValue);
			return;
		case BambooPackage.GIT_REPOSITORY__CACHE_ON_AGENTS:
			setCacheOnAgents((Boolean) newValue);
			return;
		case BambooPackage.GIT_REPOSITORY__VERBOSE_LOGS:
			setVerboseLogs((Boolean) newValue);
			return;
		case BambooPackage.GIT_REPOSITORY__COMMAND_TIMEOUT_MINUTES:
			setCommandTimeoutMinutes((Integer) newValue);
			return;
		case BambooPackage.GIT_REPOSITORY__CHANGE_DETECTION:
			setChangeDetection((ChangeDetection) newValue);
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
		case BambooPackage.GIT_REPOSITORY__URL:
			setUrl(URL_EDEFAULT);
			return;
		case BambooPackage.GIT_REPOSITORY__BRANCH:
			setBranch(BRANCH_EDEFAULT);
			return;
		case BambooPackage.GIT_REPOSITORY__USERNAME:
			setUsername(USERNAME_EDEFAULT);
			return;
		case BambooPackage.GIT_REPOSITORY__PASSWORD:
			setPassword(PASSWORD_EDEFAULT);
			return;
		case BambooPackage.GIT_REPOSITORY__SSH_KEY:
			setSshKey(SSH_KEY_EDEFAULT);
			return;
		case BambooPackage.GIT_REPOSITORY__SSH_KEY_PASSPHRASE:
			setSshKeyPassphrase(SSH_KEY_PASSPHRASE_EDEFAULT);
			return;
		case BambooPackage.GIT_REPOSITORY__SHARED_CREDENTIALS:
			setSharedCredentials(SHARED_CREDENTIALS_EDEFAULT);
			return;
		case BambooPackage.GIT_REPOSITORY__SHARED_CREDENTIALS_SCOPE:
			setSharedCredentialsScope(SHARED_CREDENTIALS_SCOPE_EDEFAULT);
			return;
		case BambooPackage.GIT_REPOSITORY__LFS:
			setLfs(LFS_EDEFAULT);
			return;
		case BambooPackage.GIT_REPOSITORY__USE_SHALLOW_CLONES:
			setUseShallowClones(USE_SHALLOW_CLONES_EDEFAULT);
			return;
		case BambooPackage.GIT_REPOSITORY__SUBMODULES:
			setSubmodules(SUBMODULES_EDEFAULT);
			return;
		case BambooPackage.GIT_REPOSITORY__FETCH_ALL:
			setFetchAll(FETCH_ALL_EDEFAULT);
			return;
		case BambooPackage.GIT_REPOSITORY__CACHE_ON_AGENTS:
			setCacheOnAgents(CACHE_ON_AGENTS_EDEFAULT);
			return;
		case BambooPackage.GIT_REPOSITORY__VERBOSE_LOGS:
			setVerboseLogs(VERBOSE_LOGS_EDEFAULT);
			return;
		case BambooPackage.GIT_REPOSITORY__COMMAND_TIMEOUT_MINUTES:
			setCommandTimeoutMinutes(COMMAND_TIMEOUT_MINUTES_EDEFAULT);
			return;
		case BambooPackage.GIT_REPOSITORY__CHANGE_DETECTION:
			setChangeDetection((ChangeDetection) null);
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
		case BambooPackage.GIT_REPOSITORY__URL:
			return URL_EDEFAULT == null ? url != null : !URL_EDEFAULT.equals(url);
		case BambooPackage.GIT_REPOSITORY__BRANCH:
			return BRANCH_EDEFAULT == null ? branch != null : !BRANCH_EDEFAULT.equals(branch);
		case BambooPackage.GIT_REPOSITORY__USERNAME:
			return USERNAME_EDEFAULT == null ? username != null : !USERNAME_EDEFAULT.equals(username);
		case BambooPackage.GIT_REPOSITORY__PASSWORD:
			return PASSWORD_EDEFAULT == null ? password != null : !PASSWORD_EDEFAULT.equals(password);
		case BambooPackage.GIT_REPOSITORY__SSH_KEY:
			return SSH_KEY_EDEFAULT == null ? sshKey != null : !SSH_KEY_EDEFAULT.equals(sshKey);
		case BambooPackage.GIT_REPOSITORY__SSH_KEY_PASSPHRASE:
			return SSH_KEY_PASSPHRASE_EDEFAULT == null ? sshKeyPassphrase != null
					: !SSH_KEY_PASSPHRASE_EDEFAULT.equals(sshKeyPassphrase);
		case BambooPackage.GIT_REPOSITORY__SHARED_CREDENTIALS:
			return SHARED_CREDENTIALS_EDEFAULT == null ? sharedCredentials != null
					: !SHARED_CREDENTIALS_EDEFAULT.equals(sharedCredentials);
		case BambooPackage.GIT_REPOSITORY__SHARED_CREDENTIALS_SCOPE:
			return sharedCredentialsScope != SHARED_CREDENTIALS_SCOPE_EDEFAULT;
		case BambooPackage.GIT_REPOSITORY__LFS:
			return LFS_EDEFAULT == null ? lfs != null : !LFS_EDEFAULT.equals(lfs);
		case BambooPackage.GIT_REPOSITORY__USE_SHALLOW_CLONES:
			return USE_SHALLOW_CLONES_EDEFAULT == null ? useShallowClones != null
					: !USE_SHALLOW_CLONES_EDEFAULT.equals(useShallowClones);
		case BambooPackage.GIT_REPOSITORY__SUBMODULES:
			return SUBMODULES_EDEFAULT == null ? submodules != null : !SUBMODULES_EDEFAULT.equals(submodules);
		case BambooPackage.GIT_REPOSITORY__FETCH_ALL:
			return FETCH_ALL_EDEFAULT == null ? fetchAll != null : !FETCH_ALL_EDEFAULT.equals(fetchAll);
		case BambooPackage.GIT_REPOSITORY__CACHE_ON_AGENTS:
			return CACHE_ON_AGENTS_EDEFAULT == null ? cacheOnAgents != null
					: !CACHE_ON_AGENTS_EDEFAULT.equals(cacheOnAgents);
		case BambooPackage.GIT_REPOSITORY__VERBOSE_LOGS:
			return VERBOSE_LOGS_EDEFAULT == null ? verboseLogs != null : !VERBOSE_LOGS_EDEFAULT.equals(verboseLogs);
		case BambooPackage.GIT_REPOSITORY__COMMAND_TIMEOUT_MINUTES:
			return COMMAND_TIMEOUT_MINUTES_EDEFAULT == null ? commandTimeoutMinutes != null
					: !COMMAND_TIMEOUT_MINUTES_EDEFAULT.equals(commandTimeoutMinutes);
		case BambooPackage.GIT_REPOSITORY__CHANGE_DETECTION:
			return changeDetection != null;
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
		result.append(" (url: ");
		result.append(url);
		result.append(", branch: ");
		result.append(branch);
		result.append(", username: ");
		result.append(username);
		result.append(", password: ");
		result.append(password);
		result.append(", sshKey: ");
		result.append(sshKey);
		result.append(", sshKeyPassphrase: ");
		result.append(sshKeyPassphrase);
		result.append(", sharedCredentials: ");
		result.append(sharedCredentials);
		result.append(", sharedCredentialsScope: ");
		result.append(sharedCredentialsScope);
		result.append(", lfs: ");
		result.append(lfs);
		result.append(", useShallowClones: ");
		result.append(useShallowClones);
		result.append(", submodules: ");
		result.append(submodules);
		result.append(", fetchAll: ");
		result.append(fetchAll);
		result.append(", cacheOnAgents: ");
		result.append(cacheOnAgents);
		result.append(", verboseLogs: ");
		result.append(verboseLogs);
		result.append(", commandTimeoutMinutes: ");
		result.append(commandTimeoutMinutes);
		result.append(')');
		return result.toString();
	}

} //GitRepositoryImpl
