/**
 */
package com.mddoai.metamodel.pim.pimMM.impl;

import com.mddoai.metamodel.pim.pimMM.Expression;
import com.mddoai.metamodel.pim.pimMM.MatrixCombination;
import com.mddoai.metamodel.pim.pimMM.PimMMPackage;
import com.mddoai.metamodel.pim.pimMM.VariableDeclaration;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Matrix Combination</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.MatrixCombinationImpl#getCombinationEntries <em>Combination Entries</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MatrixCombinationImpl extends MinimalEObjectImpl.Container implements MatrixCombination {
	/**
	 * The cached value of the '{@link #getCombinationEntries() <em>Combination Entries</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCombinationEntries()
	 * @generated
	 * @ordered
	 */
	protected EMap<VariableDeclaration, Expression> combinationEntries;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MatrixCombinationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PimMMPackage.Literals.MATRIX_COMBINATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EMap<VariableDeclaration, Expression> getCombinationEntries() {
		if (combinationEntries == null) {
			combinationEntries = new EcoreEMap<VariableDeclaration, Expression>(PimMMPackage.Literals.ASSIGNMENT,
					AssignmentImpl.class, this, PimMMPackage.MATRIX_COMBINATION__COMBINATION_ENTRIES);
		}
		return combinationEntries;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case PimMMPackage.MATRIX_COMBINATION__COMBINATION_ENTRIES:
			return ((InternalEList<?>) getCombinationEntries()).basicRemove(otherEnd, msgs);
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
		case PimMMPackage.MATRIX_COMBINATION__COMBINATION_ENTRIES:
			if (coreType)
				return getCombinationEntries();
			else
				return getCombinationEntries().map();
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
		case PimMMPackage.MATRIX_COMBINATION__COMBINATION_ENTRIES:
			((EStructuralFeature.Setting) getCombinationEntries()).set(newValue);
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
		case PimMMPackage.MATRIX_COMBINATION__COMBINATION_ENTRIES:
			getCombinationEntries().clear();
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
		case PimMMPackage.MATRIX_COMBINATION__COMBINATION_ENTRIES:
			return combinationEntries != null && !combinationEntries.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //MatrixCombinationImpl
