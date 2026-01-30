/**
 */
package com.mddoai.metamodel.pim.pimMM.impl;

import com.mddoai.metamodel.pim.pimMM.Expression;
import com.mddoai.metamodel.pim.pimMM.PimMMPackage;
import com.mddoai.metamodel.pim.pimMM.Plugin;
import com.mddoai.metamodel.pim.pimMM.VariableDeclaration;

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
 * An implementation of the model object '<em><b>Plugin</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.PluginImpl#getPluginName <em>Plugin Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.PluginImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.PluginImpl#getKwargs <em>Kwargs</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PluginImpl extends NonConditionalStepImpl implements Plugin {
	/**
	 * The default value of the '{@link #getPluginName() <em>Plugin Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPluginName()
	 * @generated
	 * @ordered
	 */
	protected static final String PLUGIN_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPluginName() <em>Plugin Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPluginName()
	 * @generated
	 * @ordered
	 */
	protected String pluginName = PLUGIN_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getKwargs() <em>Kwargs</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKwargs()
	 * @generated
	 * @ordered
	 */
	protected EMap<VariableDeclaration, Expression> kwargs;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PluginImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PimMMPackage.Literals.PLUGIN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPluginName() {
		return pluginName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPluginName(String newPluginName) {
		String oldPluginName = pluginName;
		pluginName = newPluginName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PimMMPackage.PLUGIN__PLUGIN_NAME, oldPluginName,
					pluginName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PimMMPackage.PLUGIN__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EMap<VariableDeclaration, Expression> getKwargs() {
		if (kwargs == null) {
			kwargs = new EcoreEMap<VariableDeclaration, Expression>(PimMMPackage.Literals.ASSIGNMENT,
					AssignmentImpl.class, this, PimMMPackage.PLUGIN__KWARGS);
		}
		return kwargs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case PimMMPackage.PLUGIN__KWARGS:
			return ((InternalEList<?>) getKwargs()).basicRemove(otherEnd, msgs);
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
		case PimMMPackage.PLUGIN__PLUGIN_NAME:
			return getPluginName();
		case PimMMPackage.PLUGIN__VERSION:
			return getVersion();
		case PimMMPackage.PLUGIN__KWARGS:
			if (coreType)
				return getKwargs();
			else
				return getKwargs().map();
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
		case PimMMPackage.PLUGIN__PLUGIN_NAME:
			setPluginName((String) newValue);
			return;
		case PimMMPackage.PLUGIN__VERSION:
			setVersion((String) newValue);
			return;
		case PimMMPackage.PLUGIN__KWARGS:
			((EStructuralFeature.Setting) getKwargs()).set(newValue);
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
		case PimMMPackage.PLUGIN__PLUGIN_NAME:
			setPluginName(PLUGIN_NAME_EDEFAULT);
			return;
		case PimMMPackage.PLUGIN__VERSION:
			setVersion(VERSION_EDEFAULT);
			return;
		case PimMMPackage.PLUGIN__KWARGS:
			getKwargs().clear();
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
		case PimMMPackage.PLUGIN__PLUGIN_NAME:
			return PLUGIN_NAME_EDEFAULT == null ? pluginName != null : !PLUGIN_NAME_EDEFAULT.equals(pluginName);
		case PimMMPackage.PLUGIN__VERSION:
			return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
		case PimMMPackage.PLUGIN__KWARGS:
			return kwargs != null && !kwargs.isEmpty();
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
		result.append(" (pluginName: ");
		result.append(pluginName);
		result.append(", version: ");
		result.append(version);
		result.append(')');
		return result.toString();
	}

} //PluginImpl
