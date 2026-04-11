/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.JobStrategy;

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
 * An implementation of the model object '<em><b>Job Strategy</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobStrategyImpl#getParallel <em>Parallel</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobStrategyImpl#getMaxParallel <em>Max Parallel</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.JobStrategyImpl#getMatrix <em>Matrix</em>}</li>
 * </ul>
 *
 * @generated
 */
public class JobStrategyImpl extends MinimalEObjectImpl.Container implements JobStrategy {
	/**
	 * The default value of the '{@link #getParallel() <em>Parallel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParallel()
	 * @generated
	 * @ordered
	 */
	protected static final String PARALLEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getParallel() <em>Parallel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParallel()
	 * @generated
	 * @ordered
	 */
	protected String parallel = PARALLEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxParallel() <em>Max Parallel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxParallel()
	 * @generated
	 * @ordered
	 */
	protected static final String MAX_PARALLEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMaxParallel() <em>Max Parallel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxParallel()
	 * @generated
	 * @ordered
	 */
	protected String maxParallel = MAX_PARALLEL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMatrix() <em>Matrix</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMatrix()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, EMap<String, String>> matrix;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected JobStrategyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AzuredevopsMMPackage.Literals.JOB_STRATEGY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getParallel() {
		return parallel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setParallel(String newParallel) {
		String oldParallel = parallel;
		parallel = newParallel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.JOB_STRATEGY__PARALLEL,
					oldParallel, parallel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMaxParallel() {
		return maxParallel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMaxParallel(String newMaxParallel) {
		String oldMaxParallel = maxParallel;
		maxParallel = newMaxParallel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.JOB_STRATEGY__MAX_PARALLEL,
					oldMaxParallel, maxParallel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EMap<String, EMap<String, String>> getMatrix() {
		if (matrix == null) {
			matrix = new EcoreEMap<String, EMap<String, String>>(AzuredevopsMMPackage.Literals.MATRIX_ENTRY,
					MatrixEntryImpl.class, this, AzuredevopsMMPackage.JOB_STRATEGY__MATRIX);
		}
		return matrix;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case AzuredevopsMMPackage.JOB_STRATEGY__MATRIX:
			return ((InternalEList<?>) getMatrix()).basicRemove(otherEnd, msgs);
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
		case AzuredevopsMMPackage.JOB_STRATEGY__PARALLEL:
			return getParallel();
		case AzuredevopsMMPackage.JOB_STRATEGY__MAX_PARALLEL:
			return getMaxParallel();
		case AzuredevopsMMPackage.JOB_STRATEGY__MATRIX:
			if (coreType)
				return getMatrix();
			else
				return getMatrix().map();
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
		case AzuredevopsMMPackage.JOB_STRATEGY__PARALLEL:
			setParallel((String) newValue);
			return;
		case AzuredevopsMMPackage.JOB_STRATEGY__MAX_PARALLEL:
			setMaxParallel((String) newValue);
			return;
		case AzuredevopsMMPackage.JOB_STRATEGY__MATRIX:
			((EStructuralFeature.Setting) getMatrix()).set(newValue);
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
		case AzuredevopsMMPackage.JOB_STRATEGY__PARALLEL:
			setParallel(PARALLEL_EDEFAULT);
			return;
		case AzuredevopsMMPackage.JOB_STRATEGY__MAX_PARALLEL:
			setMaxParallel(MAX_PARALLEL_EDEFAULT);
			return;
		case AzuredevopsMMPackage.JOB_STRATEGY__MATRIX:
			getMatrix().clear();
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
		case AzuredevopsMMPackage.JOB_STRATEGY__PARALLEL:
			return PARALLEL_EDEFAULT == null ? parallel != null : !PARALLEL_EDEFAULT.equals(parallel);
		case AzuredevopsMMPackage.JOB_STRATEGY__MAX_PARALLEL:
			return MAX_PARALLEL_EDEFAULT == null ? maxParallel != null : !MAX_PARALLEL_EDEFAULT.equals(maxParallel);
		case AzuredevopsMMPackage.JOB_STRATEGY__MATRIX:
			return matrix != null && !matrix.isEmpty();
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
		result.append(" (parallel: ");
		result.append(parallel);
		result.append(", maxParallel: ");
		result.append(maxParallel);
		result.append(')');
		return result.toString();
	}

} //JobStrategyImpl
