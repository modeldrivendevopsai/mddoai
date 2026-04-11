/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Checkout Step</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.CheckoutStepImpl#getCheckout <em>Checkout</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.CheckoutStepImpl#getClean <em>Clean</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.CheckoutStepImpl#getFetchDepth <em>Fetch Depth</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.CheckoutStepImpl#getFetchFilter <em>Fetch Filter</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.CheckoutStepImpl#getFetchTags <em>Fetch Tags</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.CheckoutStepImpl#getLfs <em>Lfs</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.CheckoutStepImpl#getPersistCredentials <em>Persist Credentials</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.CheckoutStepImpl#getSubmodules <em>Submodules</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.CheckoutStepImpl#getPath <em>Path</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.CheckoutStepImpl#getSparseCheckoutDirectories <em>Sparse Checkout Directories</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.CheckoutStepImpl#getSparseCheckoutPatterns <em>Sparse Checkout Patterns</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.CheckoutStepImpl#getWorkspaceRepo <em>Workspace Repo</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CheckoutStepImpl extends StepImpl implements CheckoutStep {
	/**
	 * The default value of the '{@link #getCheckout() <em>Checkout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCheckout()
	 * @generated
	 * @ordered
	 */
	protected static final String CHECKOUT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCheckout() <em>Checkout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCheckout()
	 * @generated
	 * @ordered
	 */
	protected String checkout = CHECKOUT_EDEFAULT;

	/**
	 * The default value of the '{@link #getClean() <em>Clean</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClean()
	 * @generated
	 * @ordered
	 */
	protected static final String CLEAN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClean() <em>Clean</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClean()
	 * @generated
	 * @ordered
	 */
	protected String clean = CLEAN_EDEFAULT;

	/**
	 * The default value of the '{@link #getFetchDepth() <em>Fetch Depth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFetchDepth()
	 * @generated
	 * @ordered
	 */
	protected static final String FETCH_DEPTH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFetchDepth() <em>Fetch Depth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFetchDepth()
	 * @generated
	 * @ordered
	 */
	protected String fetchDepth = FETCH_DEPTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getFetchFilter() <em>Fetch Filter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFetchFilter()
	 * @generated
	 * @ordered
	 */
	protected static final String FETCH_FILTER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFetchFilter() <em>Fetch Filter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFetchFilter()
	 * @generated
	 * @ordered
	 */
	protected String fetchFilter = FETCH_FILTER_EDEFAULT;

	/**
	 * The default value of the '{@link #getFetchTags() <em>Fetch Tags</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFetchTags()
	 * @generated
	 * @ordered
	 */
	protected static final String FETCH_TAGS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFetchTags() <em>Fetch Tags</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFetchTags()
	 * @generated
	 * @ordered
	 */
	protected String fetchTags = FETCH_TAGS_EDEFAULT;

	/**
	 * The default value of the '{@link #getLfs() <em>Lfs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLfs()
	 * @generated
	 * @ordered
	 */
	protected static final String LFS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLfs() <em>Lfs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLfs()
	 * @generated
	 * @ordered
	 */
	protected String lfs = LFS_EDEFAULT;

	/**
	 * The default value of the '{@link #getPersistCredentials() <em>Persist Credentials</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPersistCredentials()
	 * @generated
	 * @ordered
	 */
	protected static final String PERSIST_CREDENTIALS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPersistCredentials() <em>Persist Credentials</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPersistCredentials()
	 * @generated
	 * @ordered
	 */
	protected String persistCredentials = PERSIST_CREDENTIALS_EDEFAULT;

	/**
	 * The default value of the '{@link #getSubmodules() <em>Submodules</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubmodules()
	 * @generated
	 * @ordered
	 */
	protected static final String SUBMODULES_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSubmodules() <em>Submodules</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubmodules()
	 * @generated
	 * @ordered
	 */
	protected String submodules = SUBMODULES_EDEFAULT;

	/**
	 * The default value of the '{@link #getPath() <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected static final String PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPath() <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected String path = PATH_EDEFAULT;

	/**
	 * The default value of the '{@link #getSparseCheckoutDirectories() <em>Sparse Checkout Directories</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSparseCheckoutDirectories()
	 * @generated
	 * @ordered
	 */
	protected static final String SPARSE_CHECKOUT_DIRECTORIES_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSparseCheckoutDirectories() <em>Sparse Checkout Directories</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSparseCheckoutDirectories()
	 * @generated
	 * @ordered
	 */
	protected String sparseCheckoutDirectories = SPARSE_CHECKOUT_DIRECTORIES_EDEFAULT;

	/**
	 * The default value of the '{@link #getSparseCheckoutPatterns() <em>Sparse Checkout Patterns</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSparseCheckoutPatterns()
	 * @generated
	 * @ordered
	 */
	protected static final String SPARSE_CHECKOUT_PATTERNS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSparseCheckoutPatterns() <em>Sparse Checkout Patterns</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSparseCheckoutPatterns()
	 * @generated
	 * @ordered
	 */
	protected String sparseCheckoutPatterns = SPARSE_CHECKOUT_PATTERNS_EDEFAULT;

	/**
	 * The default value of the '{@link #getWorkspaceRepo() <em>Workspace Repo</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkspaceRepo()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean WORKSPACE_REPO_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWorkspaceRepo() <em>Workspace Repo</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkspaceRepo()
	 * @generated
	 * @ordered
	 */
	protected Boolean workspaceRepo = WORKSPACE_REPO_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CheckoutStepImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AzuredevopsMMPackage.Literals.CHECKOUT_STEP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCheckout() {
		return checkout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCheckout(String newCheckout) {
		String oldCheckout = checkout;
		checkout = newCheckout;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.CHECKOUT_STEP__CHECKOUT,
					oldCheckout, checkout));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getClean() {
		return clean;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setClean(String newClean) {
		String oldClean = clean;
		clean = newClean;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.CHECKOUT_STEP__CLEAN, oldClean,
					clean));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getFetchDepth() {
		return fetchDepth;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFetchDepth(String newFetchDepth) {
		String oldFetchDepth = fetchDepth;
		fetchDepth = newFetchDepth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.CHECKOUT_STEP__FETCH_DEPTH,
					oldFetchDepth, fetchDepth));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getFetchFilter() {
		return fetchFilter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFetchFilter(String newFetchFilter) {
		String oldFetchFilter = fetchFilter;
		fetchFilter = newFetchFilter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.CHECKOUT_STEP__FETCH_FILTER,
					oldFetchFilter, fetchFilter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getFetchTags() {
		return fetchTags;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFetchTags(String newFetchTags) {
		String oldFetchTags = fetchTags;
		fetchTags = newFetchTags;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.CHECKOUT_STEP__FETCH_TAGS,
					oldFetchTags, fetchTags));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLfs() {
		return lfs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLfs(String newLfs) {
		String oldLfs = lfs;
		lfs = newLfs;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.CHECKOUT_STEP__LFS, oldLfs,
					lfs));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPersistCredentials() {
		return persistCredentials;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPersistCredentials(String newPersistCredentials) {
		String oldPersistCredentials = persistCredentials;
		persistCredentials = newPersistCredentials;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.CHECKOUT_STEP__PERSIST_CREDENTIALS, oldPersistCredentials,
					persistCredentials));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSubmodules() {
		return submodules;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSubmodules(String newSubmodules) {
		String oldSubmodules = submodules;
		submodules = newSubmodules;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.CHECKOUT_STEP__SUBMODULES,
					oldSubmodules, submodules));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPath() {
		return path;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPath(String newPath) {
		String oldPath = path;
		path = newPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.CHECKOUT_STEP__PATH, oldPath,
					path));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSparseCheckoutDirectories() {
		return sparseCheckoutDirectories;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSparseCheckoutDirectories(String newSparseCheckoutDirectories) {
		String oldSparseCheckoutDirectories = sparseCheckoutDirectories;
		sparseCheckoutDirectories = newSparseCheckoutDirectories;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.CHECKOUT_STEP__SPARSE_CHECKOUT_DIRECTORIES, oldSparseCheckoutDirectories,
					sparseCheckoutDirectories));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSparseCheckoutPatterns() {
		return sparseCheckoutPatterns;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSparseCheckoutPatterns(String newSparseCheckoutPatterns) {
		String oldSparseCheckoutPatterns = sparseCheckoutPatterns;
		sparseCheckoutPatterns = newSparseCheckoutPatterns;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.CHECKOUT_STEP__SPARSE_CHECKOUT_PATTERNS, oldSparseCheckoutPatterns,
					sparseCheckoutPatterns));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getWorkspaceRepo() {
		return workspaceRepo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWorkspaceRepo(Boolean newWorkspaceRepo) {
		Boolean oldWorkspaceRepo = workspaceRepo;
		workspaceRepo = newWorkspaceRepo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.CHECKOUT_STEP__WORKSPACE_REPO,
					oldWorkspaceRepo, workspaceRepo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case AzuredevopsMMPackage.CHECKOUT_STEP__CHECKOUT:
			return getCheckout();
		case AzuredevopsMMPackage.CHECKOUT_STEP__CLEAN:
			return getClean();
		case AzuredevopsMMPackage.CHECKOUT_STEP__FETCH_DEPTH:
			return getFetchDepth();
		case AzuredevopsMMPackage.CHECKOUT_STEP__FETCH_FILTER:
			return getFetchFilter();
		case AzuredevopsMMPackage.CHECKOUT_STEP__FETCH_TAGS:
			return getFetchTags();
		case AzuredevopsMMPackage.CHECKOUT_STEP__LFS:
			return getLfs();
		case AzuredevopsMMPackage.CHECKOUT_STEP__PERSIST_CREDENTIALS:
			return getPersistCredentials();
		case AzuredevopsMMPackage.CHECKOUT_STEP__SUBMODULES:
			return getSubmodules();
		case AzuredevopsMMPackage.CHECKOUT_STEP__PATH:
			return getPath();
		case AzuredevopsMMPackage.CHECKOUT_STEP__SPARSE_CHECKOUT_DIRECTORIES:
			return getSparseCheckoutDirectories();
		case AzuredevopsMMPackage.CHECKOUT_STEP__SPARSE_CHECKOUT_PATTERNS:
			return getSparseCheckoutPatterns();
		case AzuredevopsMMPackage.CHECKOUT_STEP__WORKSPACE_REPO:
			return getWorkspaceRepo();
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
		case AzuredevopsMMPackage.CHECKOUT_STEP__CHECKOUT:
			setCheckout((String) newValue);
			return;
		case AzuredevopsMMPackage.CHECKOUT_STEP__CLEAN:
			setClean((String) newValue);
			return;
		case AzuredevopsMMPackage.CHECKOUT_STEP__FETCH_DEPTH:
			setFetchDepth((String) newValue);
			return;
		case AzuredevopsMMPackage.CHECKOUT_STEP__FETCH_FILTER:
			setFetchFilter((String) newValue);
			return;
		case AzuredevopsMMPackage.CHECKOUT_STEP__FETCH_TAGS:
			setFetchTags((String) newValue);
			return;
		case AzuredevopsMMPackage.CHECKOUT_STEP__LFS:
			setLfs((String) newValue);
			return;
		case AzuredevopsMMPackage.CHECKOUT_STEP__PERSIST_CREDENTIALS:
			setPersistCredentials((String) newValue);
			return;
		case AzuredevopsMMPackage.CHECKOUT_STEP__SUBMODULES:
			setSubmodules((String) newValue);
			return;
		case AzuredevopsMMPackage.CHECKOUT_STEP__PATH:
			setPath((String) newValue);
			return;
		case AzuredevopsMMPackage.CHECKOUT_STEP__SPARSE_CHECKOUT_DIRECTORIES:
			setSparseCheckoutDirectories((String) newValue);
			return;
		case AzuredevopsMMPackage.CHECKOUT_STEP__SPARSE_CHECKOUT_PATTERNS:
			setSparseCheckoutPatterns((String) newValue);
			return;
		case AzuredevopsMMPackage.CHECKOUT_STEP__WORKSPACE_REPO:
			setWorkspaceRepo((Boolean) newValue);
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
		case AzuredevopsMMPackage.CHECKOUT_STEP__CHECKOUT:
			setCheckout(CHECKOUT_EDEFAULT);
			return;
		case AzuredevopsMMPackage.CHECKOUT_STEP__CLEAN:
			setClean(CLEAN_EDEFAULT);
			return;
		case AzuredevopsMMPackage.CHECKOUT_STEP__FETCH_DEPTH:
			setFetchDepth(FETCH_DEPTH_EDEFAULT);
			return;
		case AzuredevopsMMPackage.CHECKOUT_STEP__FETCH_FILTER:
			setFetchFilter(FETCH_FILTER_EDEFAULT);
			return;
		case AzuredevopsMMPackage.CHECKOUT_STEP__FETCH_TAGS:
			setFetchTags(FETCH_TAGS_EDEFAULT);
			return;
		case AzuredevopsMMPackage.CHECKOUT_STEP__LFS:
			setLfs(LFS_EDEFAULT);
			return;
		case AzuredevopsMMPackage.CHECKOUT_STEP__PERSIST_CREDENTIALS:
			setPersistCredentials(PERSIST_CREDENTIALS_EDEFAULT);
			return;
		case AzuredevopsMMPackage.CHECKOUT_STEP__SUBMODULES:
			setSubmodules(SUBMODULES_EDEFAULT);
			return;
		case AzuredevopsMMPackage.CHECKOUT_STEP__PATH:
			setPath(PATH_EDEFAULT);
			return;
		case AzuredevopsMMPackage.CHECKOUT_STEP__SPARSE_CHECKOUT_DIRECTORIES:
			setSparseCheckoutDirectories(SPARSE_CHECKOUT_DIRECTORIES_EDEFAULT);
			return;
		case AzuredevopsMMPackage.CHECKOUT_STEP__SPARSE_CHECKOUT_PATTERNS:
			setSparseCheckoutPatterns(SPARSE_CHECKOUT_PATTERNS_EDEFAULT);
			return;
		case AzuredevopsMMPackage.CHECKOUT_STEP__WORKSPACE_REPO:
			setWorkspaceRepo(WORKSPACE_REPO_EDEFAULT);
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
		case AzuredevopsMMPackage.CHECKOUT_STEP__CHECKOUT:
			return CHECKOUT_EDEFAULT == null ? checkout != null : !CHECKOUT_EDEFAULT.equals(checkout);
		case AzuredevopsMMPackage.CHECKOUT_STEP__CLEAN:
			return CLEAN_EDEFAULT == null ? clean != null : !CLEAN_EDEFAULT.equals(clean);
		case AzuredevopsMMPackage.CHECKOUT_STEP__FETCH_DEPTH:
			return FETCH_DEPTH_EDEFAULT == null ? fetchDepth != null : !FETCH_DEPTH_EDEFAULT.equals(fetchDepth);
		case AzuredevopsMMPackage.CHECKOUT_STEP__FETCH_FILTER:
			return FETCH_FILTER_EDEFAULT == null ? fetchFilter != null : !FETCH_FILTER_EDEFAULT.equals(fetchFilter);
		case AzuredevopsMMPackage.CHECKOUT_STEP__FETCH_TAGS:
			return FETCH_TAGS_EDEFAULT == null ? fetchTags != null : !FETCH_TAGS_EDEFAULT.equals(fetchTags);
		case AzuredevopsMMPackage.CHECKOUT_STEP__LFS:
			return LFS_EDEFAULT == null ? lfs != null : !LFS_EDEFAULT.equals(lfs);
		case AzuredevopsMMPackage.CHECKOUT_STEP__PERSIST_CREDENTIALS:
			return PERSIST_CREDENTIALS_EDEFAULT == null ? persistCredentials != null
					: !PERSIST_CREDENTIALS_EDEFAULT.equals(persistCredentials);
		case AzuredevopsMMPackage.CHECKOUT_STEP__SUBMODULES:
			return SUBMODULES_EDEFAULT == null ? submodules != null : !SUBMODULES_EDEFAULT.equals(submodules);
		case AzuredevopsMMPackage.CHECKOUT_STEP__PATH:
			return PATH_EDEFAULT == null ? path != null : !PATH_EDEFAULT.equals(path);
		case AzuredevopsMMPackage.CHECKOUT_STEP__SPARSE_CHECKOUT_DIRECTORIES:
			return SPARSE_CHECKOUT_DIRECTORIES_EDEFAULT == null ? sparseCheckoutDirectories != null
					: !SPARSE_CHECKOUT_DIRECTORIES_EDEFAULT.equals(sparseCheckoutDirectories);
		case AzuredevopsMMPackage.CHECKOUT_STEP__SPARSE_CHECKOUT_PATTERNS:
			return SPARSE_CHECKOUT_PATTERNS_EDEFAULT == null ? sparseCheckoutPatterns != null
					: !SPARSE_CHECKOUT_PATTERNS_EDEFAULT.equals(sparseCheckoutPatterns);
		case AzuredevopsMMPackage.CHECKOUT_STEP__WORKSPACE_REPO:
			return WORKSPACE_REPO_EDEFAULT == null ? workspaceRepo != null
					: !WORKSPACE_REPO_EDEFAULT.equals(workspaceRepo);
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
		result.append(" (checkout: ");
		result.append(checkout);
		result.append(", clean: ");
		result.append(clean);
		result.append(", fetchDepth: ");
		result.append(fetchDepth);
		result.append(", fetchFilter: ");
		result.append(fetchFilter);
		result.append(", fetchTags: ");
		result.append(fetchTags);
		result.append(", lfs: ");
		result.append(lfs);
		result.append(", persistCredentials: ");
		result.append(persistCredentials);
		result.append(", submodules: ");
		result.append(submodules);
		result.append(", path: ");
		result.append(path);
		result.append(", sparseCheckoutDirectories: ");
		result.append(sparseCheckoutDirectories);
		result.append(", sparseCheckoutPatterns: ");
		result.append(sparseCheckoutPatterns);
		result.append(", workspaceRepo: ");
		result.append(workspaceRepo);
		result.append(')');
		return result.toString();
	}

} //CheckoutStepImpl
