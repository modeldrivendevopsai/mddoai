/**
 */
package com.mddoai.metamodel.bambooMM.impl;

import com.mddoai.metamodel.bambooMM.BambooPackage;
import com.mddoai.metamodel.bambooMM.BitbucketServerRepository;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Bitbucket Server Repository</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.BitbucketServerRepositoryImpl#getServer <em>Server</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.BitbucketServerRepositoryImpl#getProject <em>Project</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.BitbucketServerRepositoryImpl#getSlug <em>Slug</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.BitbucketServerRepositoryImpl#getCloneUrl <em>Clone Url</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.BitbucketServerRepositoryImpl#getPublicKey <em>Public Key</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.BitbucketServerRepositoryImpl#getPrivateKey <em>Private Key</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.BitbucketServerRepositoryImpl#getBranch <em>Branch</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.BitbucketServerRepositoryImpl#getLfs <em>Lfs</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.BitbucketServerRepositoryImpl#getUseShallowClones <em>Use Shallow Clones</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.BitbucketServerRepositoryImpl#getSubmodules <em>Submodules</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.BitbucketServerRepositoryImpl#getCommandTimeoutMinutes <em>Command Timeout Minutes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.BitbucketServerRepositoryImpl#getFetchAll <em>Fetch All</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BitbucketServerRepositoryImpl extends RepositoryImpl implements BitbucketServerRepository {
	/**
	 * The default value of the '{@link #getServer() <em>Server</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServer()
	 * @generated
	 * @ordered
	 */
	protected static final String SERVER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getServer() <em>Server</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServer()
	 * @generated
	 * @ordered
	 */
	protected String server = SERVER_EDEFAULT;

	/**
	 * The default value of the '{@link #getProject() <em>Project</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProject()
	 * @generated
	 * @ordered
	 */
	protected static final String PROJECT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProject() <em>Project</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProject()
	 * @generated
	 * @ordered
	 */
	protected String project = PROJECT_EDEFAULT;

	/**
	 * The default value of the '{@link #getSlug() <em>Slug</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSlug()
	 * @generated
	 * @ordered
	 */
	protected static final String SLUG_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSlug() <em>Slug</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSlug()
	 * @generated
	 * @ordered
	 */
	protected String slug = SLUG_EDEFAULT;

	/**
	 * The default value of the '{@link #getCloneUrl() <em>Clone Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCloneUrl()
	 * @generated
	 * @ordered
	 */
	protected static final String CLONE_URL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCloneUrl() <em>Clone Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCloneUrl()
	 * @generated
	 * @ordered
	 */
	protected String cloneUrl = CLONE_URL_EDEFAULT;

	/**
	 * The default value of the '{@link #getPublicKey() <em>Public Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPublicKey()
	 * @generated
	 * @ordered
	 */
	protected static final String PUBLIC_KEY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPublicKey() <em>Public Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPublicKey()
	 * @generated
	 * @ordered
	 */
	protected String publicKey = PUBLIC_KEY_EDEFAULT;

	/**
	 * The default value of the '{@link #getPrivateKey() <em>Private Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrivateKey()
	 * @generated
	 * @ordered
	 */
	protected static final String PRIVATE_KEY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPrivateKey() <em>Private Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrivateKey()
	 * @generated
	 * @ordered
	 */
	protected String privateKey = PRIVATE_KEY_EDEFAULT;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BitbucketServerRepositoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BambooPackage.Literals.BITBUCKET_SERVER_REPOSITORY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getServer() {
		return server;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setServer(String newServer) {
		String oldServer = server;
		server = newServer;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.BITBUCKET_SERVER_REPOSITORY__SERVER,
					oldServer, server));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getProject() {
		return project;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setProject(String newProject) {
		String oldProject = project;
		project = newProject;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.BITBUCKET_SERVER_REPOSITORY__PROJECT,
					oldProject, project));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSlug() {
		return slug;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSlug(String newSlug) {
		String oldSlug = slug;
		slug = newSlug;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.BITBUCKET_SERVER_REPOSITORY__SLUG,
					oldSlug, slug));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCloneUrl() {
		return cloneUrl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCloneUrl(String newCloneUrl) {
		String oldCloneUrl = cloneUrl;
		cloneUrl = newCloneUrl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.BITBUCKET_SERVER_REPOSITORY__CLONE_URL,
					oldCloneUrl, cloneUrl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPublicKey() {
		return publicKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPublicKey(String newPublicKey) {
		String oldPublicKey = publicKey;
		publicKey = newPublicKey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.BITBUCKET_SERVER_REPOSITORY__PUBLIC_KEY,
					oldPublicKey, publicKey));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPrivateKey() {
		return privateKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPrivateKey(String newPrivateKey) {
		String oldPrivateKey = privateKey;
		privateKey = newPrivateKey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					BambooPackage.BITBUCKET_SERVER_REPOSITORY__PRIVATE_KEY, oldPrivateKey, privateKey));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.BITBUCKET_SERVER_REPOSITORY__BRANCH,
					oldBranch, branch));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.BITBUCKET_SERVER_REPOSITORY__LFS,
					oldLfs, lfs));
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
			eNotify(new ENotificationImpl(this, Notification.SET,
					BambooPackage.BITBUCKET_SERVER_REPOSITORY__USE_SHALLOW_CLONES, oldUseShallowClones,
					useShallowClones));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.BITBUCKET_SERVER_REPOSITORY__SUBMODULES,
					oldSubmodules, submodules));
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
			eNotify(new ENotificationImpl(this, Notification.SET,
					BambooPackage.BITBUCKET_SERVER_REPOSITORY__COMMAND_TIMEOUT_MINUTES, oldCommandTimeoutMinutes,
					commandTimeoutMinutes));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.BITBUCKET_SERVER_REPOSITORY__FETCH_ALL,
					oldFetchAll, fetchAll));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__SERVER:
			return getServer();
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__PROJECT:
			return getProject();
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__SLUG:
			return getSlug();
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__CLONE_URL:
			return getCloneUrl();
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__PUBLIC_KEY:
			return getPublicKey();
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__PRIVATE_KEY:
			return getPrivateKey();
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__BRANCH:
			return getBranch();
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__LFS:
			return getLfs();
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__USE_SHALLOW_CLONES:
			return getUseShallowClones();
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__SUBMODULES:
			return getSubmodules();
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__COMMAND_TIMEOUT_MINUTES:
			return getCommandTimeoutMinutes();
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__FETCH_ALL:
			return getFetchAll();
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
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__SERVER:
			setServer((String) newValue);
			return;
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__PROJECT:
			setProject((String) newValue);
			return;
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__SLUG:
			setSlug((String) newValue);
			return;
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__CLONE_URL:
			setCloneUrl((String) newValue);
			return;
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__PUBLIC_KEY:
			setPublicKey((String) newValue);
			return;
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__PRIVATE_KEY:
			setPrivateKey((String) newValue);
			return;
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__BRANCH:
			setBranch((String) newValue);
			return;
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__LFS:
			setLfs((Boolean) newValue);
			return;
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__USE_SHALLOW_CLONES:
			setUseShallowClones((Boolean) newValue);
			return;
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__SUBMODULES:
			setSubmodules((Boolean) newValue);
			return;
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__COMMAND_TIMEOUT_MINUTES:
			setCommandTimeoutMinutes((Integer) newValue);
			return;
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__FETCH_ALL:
			setFetchAll((Boolean) newValue);
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
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__SERVER:
			setServer(SERVER_EDEFAULT);
			return;
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__PROJECT:
			setProject(PROJECT_EDEFAULT);
			return;
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__SLUG:
			setSlug(SLUG_EDEFAULT);
			return;
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__CLONE_URL:
			setCloneUrl(CLONE_URL_EDEFAULT);
			return;
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__PUBLIC_KEY:
			setPublicKey(PUBLIC_KEY_EDEFAULT);
			return;
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__PRIVATE_KEY:
			setPrivateKey(PRIVATE_KEY_EDEFAULT);
			return;
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__BRANCH:
			setBranch(BRANCH_EDEFAULT);
			return;
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__LFS:
			setLfs(LFS_EDEFAULT);
			return;
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__USE_SHALLOW_CLONES:
			setUseShallowClones(USE_SHALLOW_CLONES_EDEFAULT);
			return;
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__SUBMODULES:
			setSubmodules(SUBMODULES_EDEFAULT);
			return;
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__COMMAND_TIMEOUT_MINUTES:
			setCommandTimeoutMinutes(COMMAND_TIMEOUT_MINUTES_EDEFAULT);
			return;
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__FETCH_ALL:
			setFetchAll(FETCH_ALL_EDEFAULT);
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
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__SERVER:
			return SERVER_EDEFAULT == null ? server != null : !SERVER_EDEFAULT.equals(server);
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__PROJECT:
			return PROJECT_EDEFAULT == null ? project != null : !PROJECT_EDEFAULT.equals(project);
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__SLUG:
			return SLUG_EDEFAULT == null ? slug != null : !SLUG_EDEFAULT.equals(slug);
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__CLONE_URL:
			return CLONE_URL_EDEFAULT == null ? cloneUrl != null : !CLONE_URL_EDEFAULT.equals(cloneUrl);
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__PUBLIC_KEY:
			return PUBLIC_KEY_EDEFAULT == null ? publicKey != null : !PUBLIC_KEY_EDEFAULT.equals(publicKey);
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__PRIVATE_KEY:
			return PRIVATE_KEY_EDEFAULT == null ? privateKey != null : !PRIVATE_KEY_EDEFAULT.equals(privateKey);
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__BRANCH:
			return BRANCH_EDEFAULT == null ? branch != null : !BRANCH_EDEFAULT.equals(branch);
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__LFS:
			return LFS_EDEFAULT == null ? lfs != null : !LFS_EDEFAULT.equals(lfs);
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__USE_SHALLOW_CLONES:
			return USE_SHALLOW_CLONES_EDEFAULT == null ? useShallowClones != null
					: !USE_SHALLOW_CLONES_EDEFAULT.equals(useShallowClones);
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__SUBMODULES:
			return SUBMODULES_EDEFAULT == null ? submodules != null : !SUBMODULES_EDEFAULT.equals(submodules);
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__COMMAND_TIMEOUT_MINUTES:
			return COMMAND_TIMEOUT_MINUTES_EDEFAULT == null ? commandTimeoutMinutes != null
					: !COMMAND_TIMEOUT_MINUTES_EDEFAULT.equals(commandTimeoutMinutes);
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__FETCH_ALL:
			return FETCH_ALL_EDEFAULT == null ? fetchAll != null : !FETCH_ALL_EDEFAULT.equals(fetchAll);
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
		result.append(" (server: ");
		result.append(server);
		result.append(", project: ");
		result.append(project);
		result.append(", slug: ");
		result.append(slug);
		result.append(", cloneUrl: ");
		result.append(cloneUrl);
		result.append(", publicKey: ");
		result.append(publicKey);
		result.append(", privateKey: ");
		result.append(privateKey);
		result.append(", branch: ");
		result.append(branch);
		result.append(", lfs: ");
		result.append(lfs);
		result.append(", useShallowClones: ");
		result.append(useShallowClones);
		result.append(", submodules: ");
		result.append(submodules);
		result.append(", commandTimeoutMinutes: ");
		result.append(commandTimeoutMinutes);
		result.append(", fetchAll: ");
		result.append(fetchAll);
		result.append(')');
		return result.toString();
	}

} //BitbucketServerRepositoryImpl
