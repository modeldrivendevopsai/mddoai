/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.JobUses;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Job Uses</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobUsesImpl#getRepositories <em>Repositories</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobUsesImpl#getPools <em>Pools</em>}</li>
 * </ul>
 *
 * @generated
 */
public class JobUsesImpl extends MinimalEObjectImpl.Container implements JobUses {
	/**
	 * The cached value of the '{@link #getRepositories() <em>Repositories</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepositories()
	 * @generated
	 * @ordered
	 */
	protected EList<String> repositories;

	/**
	 * The cached value of the '{@link #getPools() <em>Pools</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPools()
	 * @generated
	 * @ordered
	 */
	protected EList<String> pools;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected JobUsesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AzuredevopsMMPackage.Literals.JOB_USES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getRepositories() {
		if (repositories == null) {
			repositories = new EDataTypeUniqueEList<String>(String.class, this,
					AzuredevopsMMPackage.JOB_USES__REPOSITORIES);
		}
		return repositories;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getPools() {
		if (pools == null) {
			pools = new EDataTypeUniqueEList<String>(String.class, this, AzuredevopsMMPackage.JOB_USES__POOLS);
		}
		return pools;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case AzuredevopsMMPackage.JOB_USES__REPOSITORIES:
			return getRepositories();
		case AzuredevopsMMPackage.JOB_USES__POOLS:
			return getPools();
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
		case AzuredevopsMMPackage.JOB_USES__REPOSITORIES:
			getRepositories().clear();
			getRepositories().addAll((Collection<? extends String>) newValue);
			return;
		case AzuredevopsMMPackage.JOB_USES__POOLS:
			getPools().clear();
			getPools().addAll((Collection<? extends String>) newValue);
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
		case AzuredevopsMMPackage.JOB_USES__REPOSITORIES:
			getRepositories().clear();
			return;
		case AzuredevopsMMPackage.JOB_USES__POOLS:
			getPools().clear();
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
		case AzuredevopsMMPackage.JOB_USES__REPOSITORIES:
			return repositories != null && !repositories.isEmpty();
		case AzuredevopsMMPackage.JOB_USES__POOLS:
			return pools != null && !pools.isEmpty();
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
		result.append(" (repositories: ");
		result.append(repositories);
		result.append(", pools: ");
		result.append(pools);
		result.append(')');
		return result.toString();
	}

} //JobUsesImpl
