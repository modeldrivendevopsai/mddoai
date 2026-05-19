/**
 */
package com.mddoai.metamodel.github.githubMM.impl;

import com.mddoai.metamodel.github.githubMM.Defaults;
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
 * An implementation of the model object '<em><b>Defaults</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.DefaultsImpl#getShell <em>Shell</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.DefaultsImpl#getWorkingDirectory <em>Working Directory</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DefaultsImpl extends MinimalEObjectImpl.Container implements Defaults {
	/**
	 * The cached value of the '{@link #getShell() <em>Shell</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShell()
	 * @generated
	 * @ordered
	 */
	protected Expression shell;

	/**
	 * The cached value of the '{@link #getWorkingDirectory() <em>Working Directory</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkingDirectory()
	 * @generated
	 * @ordered
	 */
	protected Expression workingDirectory;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DefaultsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GithubMMPackage.Literals.DEFAULTS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getShell() {
		return shell;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetShell(Expression newShell, NotificationChain msgs) {
		Expression oldShell = shell;
		shell = newShell;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GithubMMPackage.DEFAULTS__SHELL, oldShell, newShell);
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
	public void setShell(Expression newShell) {
		if (newShell != shell) {
			NotificationChain msgs = null;
			if (shell != null)
				msgs = ((InternalEObject) shell).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.DEFAULTS__SHELL, null, msgs);
			if (newShell != null)
				msgs = ((InternalEObject) newShell).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.DEFAULTS__SHELL, null, msgs);
			msgs = basicSetShell(newShell, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.DEFAULTS__SHELL, newShell, newShell));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getWorkingDirectory() {
		return workingDirectory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWorkingDirectory(Expression newWorkingDirectory, NotificationChain msgs) {
		Expression oldWorkingDirectory = workingDirectory;
		workingDirectory = newWorkingDirectory;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GithubMMPackage.DEFAULTS__WORKING_DIRECTORY, oldWorkingDirectory, newWorkingDirectory);
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
	public void setWorkingDirectory(Expression newWorkingDirectory) {
		if (newWorkingDirectory != workingDirectory) {
			NotificationChain msgs = null;
			if (workingDirectory != null)
				msgs = ((InternalEObject) workingDirectory).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.DEFAULTS__WORKING_DIRECTORY, null, msgs);
			if (newWorkingDirectory != null)
				msgs = ((InternalEObject) newWorkingDirectory).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.DEFAULTS__WORKING_DIRECTORY, null, msgs);
			msgs = basicSetWorkingDirectory(newWorkingDirectory, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.DEFAULTS__WORKING_DIRECTORY,
					newWorkingDirectory, newWorkingDirectory));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GithubMMPackage.DEFAULTS__SHELL:
			return basicSetShell(null, msgs);
		case GithubMMPackage.DEFAULTS__WORKING_DIRECTORY:
			return basicSetWorkingDirectory(null, msgs);
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
		case GithubMMPackage.DEFAULTS__SHELL:
			return getShell();
		case GithubMMPackage.DEFAULTS__WORKING_DIRECTORY:
			return getWorkingDirectory();
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
		case GithubMMPackage.DEFAULTS__SHELL:
			setShell((Expression) newValue);
			return;
		case GithubMMPackage.DEFAULTS__WORKING_DIRECTORY:
			setWorkingDirectory((Expression) newValue);
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
		case GithubMMPackage.DEFAULTS__SHELL:
			setShell((Expression) null);
			return;
		case GithubMMPackage.DEFAULTS__WORKING_DIRECTORY:
			setWorkingDirectory((Expression) null);
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
		case GithubMMPackage.DEFAULTS__SHELL:
			return shell != null;
		case GithubMMPackage.DEFAULTS__WORKING_DIRECTORY:
			return workingDirectory != null;
		}
		return super.eIsSet(featureID);
	}

} //DefaultsImpl
