/**
 */
package com.mddoai.metamodel.github.githubMM.impl;

import com.mddoai.metamodel.github.githubMM.Expression;
import com.mddoai.metamodel.github.githubMM.GithubMMPackage;
import com.mddoai.metamodel.github.githubMM.SpecifiedPathsTrigger;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Specified Paths Trigger</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.SpecifiedPathsTriggerImpl#getPaths <em>Paths</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.SpecifiedPathsTriggerImpl#isIgnoreSpecifiedPaths <em>Ignore Specified Paths</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class SpecifiedPathsTriggerImpl extends TriggerImpl implements SpecifiedPathsTrigger {
	/**
	 * The cached value of the '{@link #getPaths() <em>Paths</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPaths()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> paths;

	/**
	 * The default value of the '{@link #isIgnoreSpecifiedPaths() <em>Ignore Specified Paths</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIgnoreSpecifiedPaths()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IGNORE_SPECIFIED_PATHS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIgnoreSpecifiedPaths() <em>Ignore Specified Paths</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIgnoreSpecifiedPaths()
	 * @generated
	 * @ordered
	 */
	protected boolean ignoreSpecifiedPaths = IGNORE_SPECIFIED_PATHS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SpecifiedPathsTriggerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GithubMMPackage.Literals.SPECIFIED_PATHS_TRIGGER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Expression> getPaths() {
		if (paths == null) {
			paths = new EObjectContainmentEList<Expression>(Expression.class, this,
					GithubMMPackage.SPECIFIED_PATHS_TRIGGER__PATHS);
		}
		return paths;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIgnoreSpecifiedPaths() {
		return ignoreSpecifiedPaths;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIgnoreSpecifiedPaths(boolean newIgnoreSpecifiedPaths) {
		boolean oldIgnoreSpecifiedPaths = ignoreSpecifiedPaths;
		ignoreSpecifiedPaths = newIgnoreSpecifiedPaths;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					GithubMMPackage.SPECIFIED_PATHS_TRIGGER__IGNORE_SPECIFIED_PATHS, oldIgnoreSpecifiedPaths,
					ignoreSpecifiedPaths));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GithubMMPackage.SPECIFIED_PATHS_TRIGGER__PATHS:
			return ((InternalEList<?>) getPaths()).basicRemove(otherEnd, msgs);
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
		case GithubMMPackage.SPECIFIED_PATHS_TRIGGER__PATHS:
			return getPaths();
		case GithubMMPackage.SPECIFIED_PATHS_TRIGGER__IGNORE_SPECIFIED_PATHS:
			return isIgnoreSpecifiedPaths();
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
		case GithubMMPackage.SPECIFIED_PATHS_TRIGGER__PATHS:
			getPaths().clear();
			getPaths().addAll((Collection<? extends Expression>) newValue);
			return;
		case GithubMMPackage.SPECIFIED_PATHS_TRIGGER__IGNORE_SPECIFIED_PATHS:
			setIgnoreSpecifiedPaths((Boolean) newValue);
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
		case GithubMMPackage.SPECIFIED_PATHS_TRIGGER__PATHS:
			getPaths().clear();
			return;
		case GithubMMPackage.SPECIFIED_PATHS_TRIGGER__IGNORE_SPECIFIED_PATHS:
			setIgnoreSpecifiedPaths(IGNORE_SPECIFIED_PATHS_EDEFAULT);
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
		case GithubMMPackage.SPECIFIED_PATHS_TRIGGER__PATHS:
			return paths != null && !paths.isEmpty();
		case GithubMMPackage.SPECIFIED_PATHS_TRIGGER__IGNORE_SPECIFIED_PATHS:
			return ignoreSpecifiedPaths != IGNORE_SPECIFIED_PATHS_EDEFAULT;
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
		result.append(" (ignoreSpecifiedPaths: ");
		result.append(ignoreSpecifiedPaths);
		result.append(')');
		return result.toString();
	}

} //SpecifiedPathsTriggerImpl
