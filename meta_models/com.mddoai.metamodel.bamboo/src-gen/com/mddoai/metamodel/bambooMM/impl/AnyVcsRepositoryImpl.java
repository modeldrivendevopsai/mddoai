/**
 */
package com.mddoai.metamodel.bambooMM.impl;

import com.mddoai.metamodel.bambooMM.AnyVcsRepository;
import com.mddoai.metamodel.bambooMM.BambooPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Any Vcs Repository</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.AnyVcsRepositoryImpl#getPluginKey <em>Plugin Key</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.AnyVcsRepositoryImpl#getServerConfig <em>Server Config</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.AnyVcsRepositoryImpl#getBranchConfig <em>Branch Config</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AnyVcsRepositoryImpl extends RepositoryImpl implements AnyVcsRepository {
	/**
	 * The default value of the '{@link #getPluginKey() <em>Plugin Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPluginKey()
	 * @generated
	 * @ordered
	 */
	protected static final String PLUGIN_KEY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPluginKey() <em>Plugin Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPluginKey()
	 * @generated
	 * @ordered
	 */
	protected String pluginKey = PLUGIN_KEY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getServerConfig() <em>Server Config</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServerConfig()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> serverConfig;

	/**
	 * The cached value of the '{@link #getBranchConfig() <em>Branch Config</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBranchConfig()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> branchConfig;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AnyVcsRepositoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BambooPackage.Literals.ANY_VCS_REPOSITORY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPluginKey() {
		return pluginKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPluginKey(String newPluginKey) {
		String oldPluginKey = pluginKey;
		pluginKey = newPluginKey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.ANY_VCS_REPOSITORY__PLUGIN_KEY,
					oldPluginKey, pluginKey));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EMap<String, String> getServerConfig() {
		if (serverConfig == null) {
			serverConfig = new EcoreEMap<String, String>(BambooPackage.Literals.VARIABLE_ASSIGNMENT,
					VariableAssignmentImpl.class, this, BambooPackage.ANY_VCS_REPOSITORY__SERVER_CONFIG);
		}
		return serverConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EMap<String, String> getBranchConfig() {
		if (branchConfig == null) {
			branchConfig = new EcoreEMap<String, String>(BambooPackage.Literals.VARIABLE_ASSIGNMENT,
					VariableAssignmentImpl.class, this, BambooPackage.ANY_VCS_REPOSITORY__BRANCH_CONFIG);
		}
		return branchConfig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case BambooPackage.ANY_VCS_REPOSITORY__SERVER_CONFIG:
			return ((InternalEList<?>) getServerConfig()).basicRemove(otherEnd, msgs);
		case BambooPackage.ANY_VCS_REPOSITORY__BRANCH_CONFIG:
			return ((InternalEList<?>) getBranchConfig()).basicRemove(otherEnd, msgs);
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
		case BambooPackage.ANY_VCS_REPOSITORY__PLUGIN_KEY:
			return getPluginKey();
		case BambooPackage.ANY_VCS_REPOSITORY__SERVER_CONFIG:
			if (coreType)
				return getServerConfig();
			else
				return getServerConfig().map();
		case BambooPackage.ANY_VCS_REPOSITORY__BRANCH_CONFIG:
			if (coreType)
				return getBranchConfig();
			else
				return getBranchConfig().map();
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
		case BambooPackage.ANY_VCS_REPOSITORY__PLUGIN_KEY:
			setPluginKey((String) newValue);
			return;
		case BambooPackage.ANY_VCS_REPOSITORY__SERVER_CONFIG:
			((EStructuralFeature.Setting) getServerConfig()).set(newValue);
			return;
		case BambooPackage.ANY_VCS_REPOSITORY__BRANCH_CONFIG:
			((EStructuralFeature.Setting) getBranchConfig()).set(newValue);
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
		case BambooPackage.ANY_VCS_REPOSITORY__PLUGIN_KEY:
			setPluginKey(PLUGIN_KEY_EDEFAULT);
			return;
		case BambooPackage.ANY_VCS_REPOSITORY__SERVER_CONFIG:
			getServerConfig().clear();
			return;
		case BambooPackage.ANY_VCS_REPOSITORY__BRANCH_CONFIG:
			getBranchConfig().clear();
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
		case BambooPackage.ANY_VCS_REPOSITORY__PLUGIN_KEY:
			return PLUGIN_KEY_EDEFAULT == null ? pluginKey != null : !PLUGIN_KEY_EDEFAULT.equals(pluginKey);
		case BambooPackage.ANY_VCS_REPOSITORY__SERVER_CONFIG:
			return serverConfig != null && !serverConfig.isEmpty();
		case BambooPackage.ANY_VCS_REPOSITORY__BRANCH_CONFIG:
			return branchConfig != null && !branchConfig.isEmpty();
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
		result.append(" (pluginKey: ");
		result.append(pluginKey);
		result.append(')');
		return result.toString();
	}

} //AnyVcsRepositoryImpl
