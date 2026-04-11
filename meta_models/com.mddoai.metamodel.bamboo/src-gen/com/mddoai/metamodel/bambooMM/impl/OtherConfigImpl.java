/**
 */
package com.mddoai.metamodel.bambooMM.impl;

import com.mddoai.metamodel.bambooMM.BambooPackage;
import com.mddoai.metamodel.bambooMM.OtherConfig;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Other Config</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.OtherConfigImpl#getConcurrentBuildPlugin <em>Concurrent Build Plugin</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.OtherConfigImpl#getCleanWorkingDir <em>Clean Working Dir</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.OtherConfigImpl#getAllOtherApps <em>All Other Apps</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OtherConfigImpl extends MinimalEObjectImpl.Container implements OtherConfig {
	/**
	 * The default value of the '{@link #getConcurrentBuildPlugin() <em>Concurrent Build Plugin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConcurrentBuildPlugin()
	 * @generated
	 * @ordered
	 */
	protected static final Integer CONCURRENT_BUILD_PLUGIN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConcurrentBuildPlugin() <em>Concurrent Build Plugin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConcurrentBuildPlugin()
	 * @generated
	 * @ordered
	 */
	protected Integer concurrentBuildPlugin = CONCURRENT_BUILD_PLUGIN_EDEFAULT;

	/**
	 * The default value of the '{@link #getCleanWorkingDir() <em>Clean Working Dir</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCleanWorkingDir()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean CLEAN_WORKING_DIR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCleanWorkingDir() <em>Clean Working Dir</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCleanWorkingDir()
	 * @generated
	 * @ordered
	 */
	protected Boolean cleanWorkingDir = CLEAN_WORKING_DIR_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAllOtherApps() <em>All Other Apps</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllOtherApps()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> allOtherApps;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OtherConfigImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BambooPackage.Literals.OTHER_CONFIG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Integer getConcurrentBuildPlugin() {
		return concurrentBuildPlugin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setConcurrentBuildPlugin(Integer newConcurrentBuildPlugin) {
		Integer oldConcurrentBuildPlugin = concurrentBuildPlugin;
		concurrentBuildPlugin = newConcurrentBuildPlugin;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.OTHER_CONFIG__CONCURRENT_BUILD_PLUGIN,
					oldConcurrentBuildPlugin, concurrentBuildPlugin));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getCleanWorkingDir() {
		return cleanWorkingDir;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCleanWorkingDir(Boolean newCleanWorkingDir) {
		Boolean oldCleanWorkingDir = cleanWorkingDir;
		cleanWorkingDir = newCleanWorkingDir;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.OTHER_CONFIG__CLEAN_WORKING_DIR,
					oldCleanWorkingDir, cleanWorkingDir));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EMap<String, String> getAllOtherApps() {
		if (allOtherApps == null) {
			allOtherApps = new EcoreEMap<String, String>(BambooPackage.Literals.VARIABLE_ASSIGNMENT,
					VariableAssignmentImpl.class, this, BambooPackage.OTHER_CONFIG__ALL_OTHER_APPS);
		}
		return allOtherApps;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case BambooPackage.OTHER_CONFIG__ALL_OTHER_APPS:
			return ((InternalEList<?>) getAllOtherApps()).basicRemove(otherEnd, msgs);
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
		case BambooPackage.OTHER_CONFIG__CONCURRENT_BUILD_PLUGIN:
			return getConcurrentBuildPlugin();
		case BambooPackage.OTHER_CONFIG__CLEAN_WORKING_DIR:
			return getCleanWorkingDir();
		case BambooPackage.OTHER_CONFIG__ALL_OTHER_APPS:
			if (coreType)
				return getAllOtherApps();
			else
				return getAllOtherApps().map();
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
		case BambooPackage.OTHER_CONFIG__CONCURRENT_BUILD_PLUGIN:
			setConcurrentBuildPlugin((Integer) newValue);
			return;
		case BambooPackage.OTHER_CONFIG__CLEAN_WORKING_DIR:
			setCleanWorkingDir((Boolean) newValue);
			return;
		case BambooPackage.OTHER_CONFIG__ALL_OTHER_APPS:
			((EStructuralFeature.Setting) getAllOtherApps()).set(newValue);
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
		case BambooPackage.OTHER_CONFIG__CONCURRENT_BUILD_PLUGIN:
			setConcurrentBuildPlugin(CONCURRENT_BUILD_PLUGIN_EDEFAULT);
			return;
		case BambooPackage.OTHER_CONFIG__CLEAN_WORKING_DIR:
			setCleanWorkingDir(CLEAN_WORKING_DIR_EDEFAULT);
			return;
		case BambooPackage.OTHER_CONFIG__ALL_OTHER_APPS:
			getAllOtherApps().clear();
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
		case BambooPackage.OTHER_CONFIG__CONCURRENT_BUILD_PLUGIN:
			return CONCURRENT_BUILD_PLUGIN_EDEFAULT == null ? concurrentBuildPlugin != null
					: !CONCURRENT_BUILD_PLUGIN_EDEFAULT.equals(concurrentBuildPlugin);
		case BambooPackage.OTHER_CONFIG__CLEAN_WORKING_DIR:
			return CLEAN_WORKING_DIR_EDEFAULT == null ? cleanWorkingDir != null
					: !CLEAN_WORKING_DIR_EDEFAULT.equals(cleanWorkingDir);
		case BambooPackage.OTHER_CONFIG__ALL_OTHER_APPS:
			return allOtherApps != null && !allOtherApps.isEmpty();
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
		result.append(" (concurrentBuildPlugin: ");
		result.append(concurrentBuildPlugin);
		result.append(", cleanWorkingDir: ");
		result.append(cleanWorkingDir);
		result.append(')');
		return result.toString();
	}

} //OtherConfigImpl
