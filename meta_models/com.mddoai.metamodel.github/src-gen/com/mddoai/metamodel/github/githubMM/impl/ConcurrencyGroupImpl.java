/**
 */
package com.mddoai.metamodel.github.githubMM.impl;

import com.mddoai.metamodel.github.githubMM.ConcurrencyGroup;
import com.mddoai.metamodel.github.githubMM.Expression;
import com.mddoai.metamodel.github.githubMM.GithubMMPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Concurrency Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.ConcurrencyGroupImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.ConcurrencyGroupImpl#getCancelInProgress <em>Cancel In Progress</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConcurrencyGroupImpl extends MinimalEObjectImpl.Container implements ConcurrencyGroup {
	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected Expression name;

	/**
	 * The cached value of the '{@link #getCancelInProgress() <em>Cancel In Progress</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCancelInProgress()
	 * @generated
	 * @ordered
	 */
	protected Expression cancelInProgress;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConcurrencyGroupImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GithubMMPackage.Literals.CONCURRENCY_GROUP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetName(Expression newName, NotificationChain msgs) {
		Expression oldName = name;
		name = newName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GithubMMPackage.CONCURRENCY_GROUP__NAME, oldName, newName);
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
	public void setName(Expression newName) {
		if (newName != name) {
			NotificationChain msgs = null;
			if (name != null)
				msgs = ((InternalEObject) name).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.CONCURRENCY_GROUP__NAME, null, msgs);
			if (newName != null)
				msgs = ((InternalEObject) newName).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.CONCURRENCY_GROUP__NAME, null, msgs);
			msgs = basicSetName(newName, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.CONCURRENCY_GROUP__NAME, newName,
					newName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getCancelInProgress() {
		return cancelInProgress;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCancelInProgress(Expression newCancelInProgress, NotificationChain msgs) {
		Expression oldCancelInProgress = cancelInProgress;
		cancelInProgress = newCancelInProgress;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GithubMMPackage.CONCURRENCY_GROUP__CANCEL_IN_PROGRESS, oldCancelInProgress, newCancelInProgress);
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
	public void setCancelInProgress(Expression newCancelInProgress) {
		if (newCancelInProgress != cancelInProgress) {
			NotificationChain msgs = null;
			if (cancelInProgress != null)
				msgs = ((InternalEObject) cancelInProgress).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.CONCURRENCY_GROUP__CANCEL_IN_PROGRESS, null, msgs);
			if (newCancelInProgress != null)
				msgs = ((InternalEObject) newCancelInProgress).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.CONCURRENCY_GROUP__CANCEL_IN_PROGRESS, null, msgs);
			msgs = basicSetCancelInProgress(newCancelInProgress, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.CONCURRENCY_GROUP__CANCEL_IN_PROGRESS,
					newCancelInProgress, newCancelInProgress));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GithubMMPackage.CONCURRENCY_GROUP__NAME:
			return basicSetName(null, msgs);
		case GithubMMPackage.CONCURRENCY_GROUP__CANCEL_IN_PROGRESS:
			return basicSetCancelInProgress(null, msgs);
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
		case GithubMMPackage.CONCURRENCY_GROUP__NAME:
			return getName();
		case GithubMMPackage.CONCURRENCY_GROUP__CANCEL_IN_PROGRESS:
			return getCancelInProgress();
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
		case GithubMMPackage.CONCURRENCY_GROUP__NAME:
			setName((Expression) newValue);
			return;
		case GithubMMPackage.CONCURRENCY_GROUP__CANCEL_IN_PROGRESS:
			setCancelInProgress((Expression) newValue);
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
		case GithubMMPackage.CONCURRENCY_GROUP__NAME:
			setName((Expression) null);
			return;
		case GithubMMPackage.CONCURRENCY_GROUP__CANCEL_IN_PROGRESS:
			setCancelInProgress((Expression) null);
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
		case GithubMMPackage.CONCURRENCY_GROUP__NAME:
			return name != null;
		case GithubMMPackage.CONCURRENCY_GROUP__CANCEL_IN_PROGRESS:
			return cancelInProgress != null;
		}
		return super.eIsSet(featureID);
	}

} //ConcurrencyGroupImpl
