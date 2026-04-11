/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.GetPackageStep;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Get Package Step</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.GetPackageStepImpl#getGetPackage <em>Get Package</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.GetPackageStepImpl#getPath <em>Path</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GetPackageStepImpl extends StepImpl implements GetPackageStep {
	/**
	 * The default value of the '{@link #getGetPackage() <em>Get Package</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGetPackage()
	 * @generated
	 * @ordered
	 */
	protected static final String GET_PACKAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGetPackage() <em>Get Package</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGetPackage()
	 * @generated
	 * @ordered
	 */
	protected String getPackage = GET_PACKAGE_EDEFAULT;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GetPackageStepImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AzuredevopsMMPackage.Literals.GET_PACKAGE_STEP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getGetPackage() {
		return getPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setGetPackage(String newGetPackage) {
		String oldGetPackage = getPackage;
		getPackage = newGetPackage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.GET_PACKAGE_STEP__GET_PACKAGE,
					oldGetPackage, getPackage));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.GET_PACKAGE_STEP__PATH, oldPath,
					path));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case AzuredevopsMMPackage.GET_PACKAGE_STEP__GET_PACKAGE:
			return getGetPackage();
		case AzuredevopsMMPackage.GET_PACKAGE_STEP__PATH:
			return getPath();
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
		case AzuredevopsMMPackage.GET_PACKAGE_STEP__GET_PACKAGE:
			setGetPackage((String) newValue);
			return;
		case AzuredevopsMMPackage.GET_PACKAGE_STEP__PATH:
			setPath((String) newValue);
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
		case AzuredevopsMMPackage.GET_PACKAGE_STEP__GET_PACKAGE:
			setGetPackage(GET_PACKAGE_EDEFAULT);
			return;
		case AzuredevopsMMPackage.GET_PACKAGE_STEP__PATH:
			setPath(PATH_EDEFAULT);
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
		case AzuredevopsMMPackage.GET_PACKAGE_STEP__GET_PACKAGE:
			return GET_PACKAGE_EDEFAULT == null ? getPackage != null : !GET_PACKAGE_EDEFAULT.equals(getPackage);
		case AzuredevopsMMPackage.GET_PACKAGE_STEP__PATH:
			return PATH_EDEFAULT == null ? path != null : !PATH_EDEFAULT.equals(path);
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
		result.append(" (getPackage: ");
		result.append(getPackage);
		result.append(", path: ");
		result.append(path);
		result.append(')');
		return result.toString();
	}

} //GetPackageStepImpl
