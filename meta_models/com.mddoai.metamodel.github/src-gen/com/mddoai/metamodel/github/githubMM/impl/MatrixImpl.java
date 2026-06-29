/**
 */
package com.mddoai.metamodel.github.githubMM.impl;

import com.mddoai.metamodel.github.githubMM.Expression;
import com.mddoai.metamodel.github.githubMM.GithubMMPackage;
import com.mddoai.metamodel.github.githubMM.Matrix;
import com.mddoai.metamodel.github.githubMM.MatrixAxis;
import com.mddoai.metamodel.github.githubMM.MatrixCombination;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Matrix</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.MatrixImpl#getAxes <em>Axes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.MatrixImpl#getIncludes <em>Includes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.MatrixImpl#getExcludes <em>Excludes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.MatrixImpl#getFailFast <em>Fail Fast</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.MatrixImpl#getMaxParallel <em>Max Parallel</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MatrixImpl extends MinimalEObjectImpl.Container implements Matrix {
	/**
	 * The cached value of the '{@link #getAxes() <em>Axes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAxes()
	 * @generated
	 * @ordered
	 */
	protected EList<MatrixAxis> axes;

	/**
	 * The cached value of the '{@link #getIncludes() <em>Includes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncludes()
	 * @generated
	 * @ordered
	 */
	protected EList<MatrixCombination> includes;

	/**
	 * The cached value of the '{@link #getExcludes() <em>Excludes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExcludes()
	 * @generated
	 * @ordered
	 */
	protected EList<MatrixCombination> excludes;

	/**
	 * The cached value of the '{@link #getFailFast() <em>Fail Fast</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFailFast()
	 * @generated
	 * @ordered
	 */
	protected Expression failFast;

	/**
	 * The cached value of the '{@link #getMaxParallel() <em>Max Parallel</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxParallel()
	 * @generated
	 * @ordered
	 */
	protected Expression maxParallel;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MatrixImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GithubMMPackage.Literals.MATRIX;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<MatrixAxis> getAxes() {
		if (axes == null) {
			axes = new EObjectContainmentEList<MatrixAxis>(MatrixAxis.class, this, GithubMMPackage.MATRIX__AXES);
		}
		return axes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<MatrixCombination> getIncludes() {
		if (includes == null) {
			includes = new EObjectContainmentEList<MatrixCombination>(MatrixCombination.class, this,
					GithubMMPackage.MATRIX__INCLUDES);
		}
		return includes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<MatrixCombination> getExcludes() {
		if (excludes == null) {
			excludes = new EObjectContainmentEList<MatrixCombination>(MatrixCombination.class, this,
					GithubMMPackage.MATRIX__EXCLUDES);
		}
		return excludes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getFailFast() {
		return failFast;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFailFast(Expression newFailFast, NotificationChain msgs) {
		Expression oldFailFast = failFast;
		failFast = newFailFast;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GithubMMPackage.MATRIX__FAIL_FAST, oldFailFast, newFailFast);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFailFast(Expression newFailFast) {
		if (newFailFast != failFast) {
			NotificationChain msgs = null;
			if (failFast != null)
				msgs = ((InternalEObject) failFast).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.MATRIX__FAIL_FAST, null, msgs);
			if (newFailFast != null)
				msgs = ((InternalEObject) newFailFast).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.MATRIX__FAIL_FAST, null, msgs);
			msgs = basicSetFailFast(newFailFast, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.MATRIX__FAIL_FAST, newFailFast,
					newFailFast));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getMaxParallel() {
		return maxParallel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMaxParallel(Expression newMaxParallel, NotificationChain msgs) {
		Expression oldMaxParallel = maxParallel;
		maxParallel = newMaxParallel;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GithubMMPackage.MATRIX__MAX_PARALLEL, oldMaxParallel, newMaxParallel);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMaxParallel(Expression newMaxParallel) {
		if (newMaxParallel != maxParallel) {
			NotificationChain msgs = null;
			if (maxParallel != null)
				msgs = ((InternalEObject) maxParallel).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.MATRIX__MAX_PARALLEL, null, msgs);
			if (newMaxParallel != null)
				msgs = ((InternalEObject) newMaxParallel).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.MATRIX__MAX_PARALLEL, null, msgs);
			msgs = basicSetMaxParallel(newMaxParallel, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.MATRIX__MAX_PARALLEL, newMaxParallel,
					newMaxParallel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GithubMMPackage.MATRIX__AXES:
			return ((InternalEList<?>) getAxes()).basicRemove(otherEnd, msgs);
		case GithubMMPackage.MATRIX__INCLUDES:
			return ((InternalEList<?>) getIncludes()).basicRemove(otherEnd, msgs);
		case GithubMMPackage.MATRIX__EXCLUDES:
			return ((InternalEList<?>) getExcludes()).basicRemove(otherEnd, msgs);
		case GithubMMPackage.MATRIX__FAIL_FAST:
			return basicSetFailFast(null, msgs);
		case GithubMMPackage.MATRIX__MAX_PARALLEL:
			return basicSetMaxParallel(null, msgs);
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
		case GithubMMPackage.MATRIX__AXES:
			return getAxes();
		case GithubMMPackage.MATRIX__INCLUDES:
			return getIncludes();
		case GithubMMPackage.MATRIX__EXCLUDES:
			return getExcludes();
		case GithubMMPackage.MATRIX__FAIL_FAST:
			return getFailFast();
		case GithubMMPackage.MATRIX__MAX_PARALLEL:
			return getMaxParallel();
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
		case GithubMMPackage.MATRIX__AXES:
			getAxes().clear();
			getAxes().addAll((Collection<? extends MatrixAxis>) newValue);
			return;
		case GithubMMPackage.MATRIX__INCLUDES:
			getIncludes().clear();
			getIncludes().addAll((Collection<? extends MatrixCombination>) newValue);
			return;
		case GithubMMPackage.MATRIX__EXCLUDES:
			getExcludes().clear();
			getExcludes().addAll((Collection<? extends MatrixCombination>) newValue);
			return;
		case GithubMMPackage.MATRIX__FAIL_FAST:
			setFailFast((Expression) newValue);
			return;
		case GithubMMPackage.MATRIX__MAX_PARALLEL:
			setMaxParallel((Expression) newValue);
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
		case GithubMMPackage.MATRIX__AXES:
			getAxes().clear();
			return;
		case GithubMMPackage.MATRIX__INCLUDES:
			getIncludes().clear();
			return;
		case GithubMMPackage.MATRIX__EXCLUDES:
			getExcludes().clear();
			return;
		case GithubMMPackage.MATRIX__FAIL_FAST:
			setFailFast((Expression) null);
			return;
		case GithubMMPackage.MATRIX__MAX_PARALLEL:
			setMaxParallel((Expression) null);
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
		case GithubMMPackage.MATRIX__AXES:
			return axes != null && !axes.isEmpty();
		case GithubMMPackage.MATRIX__INCLUDES:
			return includes != null && !includes.isEmpty();
		case GithubMMPackage.MATRIX__EXCLUDES:
			return excludes != null && !excludes.isEmpty();
		case GithubMMPackage.MATRIX__FAIL_FAST:
			return failFast != null;
		case GithubMMPackage.MATRIX__MAX_PARALLEL:
			return maxParallel != null;
		}
		return super.eIsSet(featureID);
	}

} //MatrixImpl
