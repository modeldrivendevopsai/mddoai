/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.IncludeExcludeFilters;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Include Exclude Filters</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.IncludeExcludeFiltersImpl#getInclude <em>Include</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.IncludeExcludeFiltersImpl#getExclude <em>Exclude</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IncludeExcludeFiltersImpl extends MinimalEObjectImpl.Container implements IncludeExcludeFilters {
	/**
	 * The cached value of the '{@link #getInclude() <em>Include</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInclude()
	 * @generated
	 * @ordered
	 */
	protected EList<String> include;

	/**
	 * The cached value of the '{@link #getExclude() <em>Exclude</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExclude()
	 * @generated
	 * @ordered
	 */
	protected EList<String> exclude;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IncludeExcludeFiltersImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AzuredevopsMMPackage.Literals.INCLUDE_EXCLUDE_FILTERS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getInclude() {
		if (include == null) {
			include = new EDataTypeUniqueEList<String>(String.class, this,
					AzuredevopsMMPackage.INCLUDE_EXCLUDE_FILTERS__INCLUDE);
		}
		return include;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getExclude() {
		if (exclude == null) {
			exclude = new EDataTypeUniqueEList<String>(String.class, this,
					AzuredevopsMMPackage.INCLUDE_EXCLUDE_FILTERS__EXCLUDE);
		}
		return exclude;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case AzuredevopsMMPackage.INCLUDE_EXCLUDE_FILTERS__INCLUDE:
			return getInclude();
		case AzuredevopsMMPackage.INCLUDE_EXCLUDE_FILTERS__EXCLUDE:
			return getExclude();
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
		case AzuredevopsMMPackage.INCLUDE_EXCLUDE_FILTERS__INCLUDE:
			getInclude().clear();
			getInclude().addAll((Collection<? extends String>) newValue);
			return;
		case AzuredevopsMMPackage.INCLUDE_EXCLUDE_FILTERS__EXCLUDE:
			getExclude().clear();
			getExclude().addAll((Collection<? extends String>) newValue);
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
		case AzuredevopsMMPackage.INCLUDE_EXCLUDE_FILTERS__INCLUDE:
			getInclude().clear();
			return;
		case AzuredevopsMMPackage.INCLUDE_EXCLUDE_FILTERS__EXCLUDE:
			getExclude().clear();
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
		case AzuredevopsMMPackage.INCLUDE_EXCLUDE_FILTERS__INCLUDE:
			return include != null && !include.isEmpty();
		case AzuredevopsMMPackage.INCLUDE_EXCLUDE_FILTERS__EXCLUDE:
			return exclude != null && !exclude.isEmpty();
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
		result.append(" (include: ");
		result.append(include);
		result.append(", exclude: ");
		result.append(exclude);
		result.append(')');
		return result.toString();
	}

} //IncludeExcludeFiltersImpl
