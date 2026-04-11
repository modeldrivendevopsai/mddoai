/**
 */
package com.mddoai.metamodel.bambooMM.impl;

import com.mddoai.metamodel.bambooMM.AnyTask;
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
 * An implementation of the model object '<em><b>Any Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.AnyTaskImpl#getPluginKey <em>Plugin Key</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.AnyTaskImpl#getConfiguration <em>Configuration</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AnyTaskImpl extends TaskImpl implements AnyTask {
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
	 * The cached value of the '{@link #getConfiguration() <em>Configuration</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfiguration()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> configuration;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AnyTaskImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BambooPackage.Literals.ANY_TASK;
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
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.ANY_TASK__PLUGIN_KEY, oldPluginKey,
					pluginKey));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EMap<String, String> getConfiguration() {
		if (configuration == null) {
			configuration = new EcoreEMap<String, String>(BambooPackage.Literals.VARIABLE_ASSIGNMENT,
					VariableAssignmentImpl.class, this, BambooPackage.ANY_TASK__CONFIGURATION);
		}
		return configuration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case BambooPackage.ANY_TASK__CONFIGURATION:
			return ((InternalEList<?>) getConfiguration()).basicRemove(otherEnd, msgs);
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
		case BambooPackage.ANY_TASK__PLUGIN_KEY:
			return getPluginKey();
		case BambooPackage.ANY_TASK__CONFIGURATION:
			if (coreType)
				return getConfiguration();
			else
				return getConfiguration().map();
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
		case BambooPackage.ANY_TASK__PLUGIN_KEY:
			setPluginKey((String) newValue);
			return;
		case BambooPackage.ANY_TASK__CONFIGURATION:
			((EStructuralFeature.Setting) getConfiguration()).set(newValue);
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
		case BambooPackage.ANY_TASK__PLUGIN_KEY:
			setPluginKey(PLUGIN_KEY_EDEFAULT);
			return;
		case BambooPackage.ANY_TASK__CONFIGURATION:
			getConfiguration().clear();
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
		case BambooPackage.ANY_TASK__PLUGIN_KEY:
			return PLUGIN_KEY_EDEFAULT == null ? pluginKey != null : !PLUGIN_KEY_EDEFAULT.equals(pluginKey);
		case BambooPackage.ANY_TASK__CONFIGURATION:
			return configuration != null && !configuration.isEmpty();
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

} //AnyTaskImpl
