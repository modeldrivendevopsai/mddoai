/**
 */
package com.mddoai.metamodel.bambooMM.impl;

import com.mddoai.metamodel.bambooMM.BambooPackage;
import com.mddoai.metamodel.bambooMM.CheckoutTask;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Checkout Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.CheckoutTaskImpl#getRepository <em>Repository</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.CheckoutTaskImpl#getPath <em>Path</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.CheckoutTaskImpl#getForceCleanBuild <em>Force Clean Build</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.CheckoutTaskImpl#isDefaultRepository <em>Default Repository</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CheckoutTaskImpl extends TaskImpl implements CheckoutTask {
	/**
	 * The default value of the '{@link #getRepository() <em>Repository</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepository()
	 * @generated
	 * @ordered
	 */
	protected static final String REPOSITORY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRepository() <em>Repository</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepository()
	 * @generated
	 * @ordered
	 */
	protected String repository = REPOSITORY_EDEFAULT;

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
	 * The default value of the '{@link #getForceCleanBuild() <em>Force Clean Build</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForceCleanBuild()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean FORCE_CLEAN_BUILD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getForceCleanBuild() <em>Force Clean Build</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForceCleanBuild()
	 * @generated
	 * @ordered
	 */
	protected Boolean forceCleanBuild = FORCE_CLEAN_BUILD_EDEFAULT;

	/**
	 * The default value of the '{@link #isDefaultRepository() <em>Default Repository</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDefaultRepository()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DEFAULT_REPOSITORY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDefaultRepository() <em>Default Repository</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDefaultRepository()
	 * @generated
	 * @ordered
	 */
	protected boolean defaultRepository = DEFAULT_REPOSITORY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CheckoutTaskImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BambooPackage.Literals.CHECKOUT_TASK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getRepository() {
		return repository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRepository(String newRepository) {
		String oldRepository = repository;
		repository = newRepository;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.CHECKOUT_TASK__REPOSITORY,
					oldRepository, repository));
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
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.CHECKOUT_TASK__PATH, oldPath, path));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getForceCleanBuild() {
		return forceCleanBuild;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setForceCleanBuild(Boolean newForceCleanBuild) {
		Boolean oldForceCleanBuild = forceCleanBuild;
		forceCleanBuild = newForceCleanBuild;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.CHECKOUT_TASK__FORCE_CLEAN_BUILD,
					oldForceCleanBuild, forceCleanBuild));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isDefaultRepository() {
		return defaultRepository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDefaultRepository(boolean newDefaultRepository) {
		boolean oldDefaultRepository = defaultRepository;
		defaultRepository = newDefaultRepository;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.CHECKOUT_TASK__DEFAULT_REPOSITORY,
					oldDefaultRepository, defaultRepository));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case BambooPackage.CHECKOUT_TASK__REPOSITORY:
			return getRepository();
		case BambooPackage.CHECKOUT_TASK__PATH:
			return getPath();
		case BambooPackage.CHECKOUT_TASK__FORCE_CLEAN_BUILD:
			return getForceCleanBuild();
		case BambooPackage.CHECKOUT_TASK__DEFAULT_REPOSITORY:
			return isDefaultRepository();
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
		case BambooPackage.CHECKOUT_TASK__REPOSITORY:
			setRepository((String) newValue);
			return;
		case BambooPackage.CHECKOUT_TASK__PATH:
			setPath((String) newValue);
			return;
		case BambooPackage.CHECKOUT_TASK__FORCE_CLEAN_BUILD:
			setForceCleanBuild((Boolean) newValue);
			return;
		case BambooPackage.CHECKOUT_TASK__DEFAULT_REPOSITORY:
			setDefaultRepository((Boolean) newValue);
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
		case BambooPackage.CHECKOUT_TASK__REPOSITORY:
			setRepository(REPOSITORY_EDEFAULT);
			return;
		case BambooPackage.CHECKOUT_TASK__PATH:
			setPath(PATH_EDEFAULT);
			return;
		case BambooPackage.CHECKOUT_TASK__FORCE_CLEAN_BUILD:
			setForceCleanBuild(FORCE_CLEAN_BUILD_EDEFAULT);
			return;
		case BambooPackage.CHECKOUT_TASK__DEFAULT_REPOSITORY:
			setDefaultRepository(DEFAULT_REPOSITORY_EDEFAULT);
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
		case BambooPackage.CHECKOUT_TASK__REPOSITORY:
			return REPOSITORY_EDEFAULT == null ? repository != null : !REPOSITORY_EDEFAULT.equals(repository);
		case BambooPackage.CHECKOUT_TASK__PATH:
			return PATH_EDEFAULT == null ? path != null : !PATH_EDEFAULT.equals(path);
		case BambooPackage.CHECKOUT_TASK__FORCE_CLEAN_BUILD:
			return FORCE_CLEAN_BUILD_EDEFAULT == null ? forceCleanBuild != null
					: !FORCE_CLEAN_BUILD_EDEFAULT.equals(forceCleanBuild);
		case BambooPackage.CHECKOUT_TASK__DEFAULT_REPOSITORY:
			return defaultRepository != DEFAULT_REPOSITORY_EDEFAULT;
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
		result.append(" (repository: ");
		result.append(repository);
		result.append(", path: ");
		result.append(path);
		result.append(", forceCleanBuild: ");
		result.append(forceCleanBuild);
		result.append(", defaultRepository: ");
		result.append(defaultRepository);
		result.append(')');
		return result.toString();
	}

} //CheckoutTaskImpl
