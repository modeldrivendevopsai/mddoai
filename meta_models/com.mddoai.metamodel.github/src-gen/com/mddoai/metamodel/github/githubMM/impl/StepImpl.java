/**
 */
package com.mddoai.metamodel.github.githubMM.impl;

import com.mddoai.metamodel.github.githubMM.Expression;
import com.mddoai.metamodel.github.githubMM.GithubMMPackage;
import com.mddoai.metamodel.github.githubMM.Step;
import com.mddoai.metamodel.github.githubMM.VariableDeclaration;

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
 * An implementation of the model object '<em><b>Step</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.StepImpl#getId <em>Id</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.StepImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.StepImpl#getTimeoutMinutes <em>Timeout Minutes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.StepImpl#getContinueOnError <em>Continue On Error</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.StepImpl#getShell <em>Shell</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.StepImpl#getWorkingDirectory <em>Working Directory</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.StepImpl#getEnvironmentVariables <em>Environment Variables</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class StepImpl extends AbstractStepImpl implements Step {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

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
	 * The cached value of the '{@link #getTimeoutMinutes() <em>Timeout Minutes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeoutMinutes()
	 * @generated
	 * @ordered
	 */
	protected Expression timeoutMinutes;

	/**
	 * The cached value of the '{@link #getContinueOnError() <em>Continue On Error</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContinueOnError()
	 * @generated
	 * @ordered
	 */
	protected Expression continueOnError;

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
	 * The cached value of the '{@link #getEnvironmentVariables() <em>Environment Variables</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnvironmentVariables()
	 * @generated
	 * @ordered
	 */
	protected EMap<VariableDeclaration, Expression> environmentVariables;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StepImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GithubMMPackage.Literals.STEP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.STEP__ID, oldId, id));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GithubMMPackage.STEP__NAME,
					oldName, newName);
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
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.STEP__NAME, null, msgs);
			if (newName != null)
				msgs = ((InternalEObject) newName).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.STEP__NAME, null, msgs);
			msgs = basicSetName(newName, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.STEP__NAME, newName, newName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getTimeoutMinutes() {
		return timeoutMinutes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTimeoutMinutes(Expression newTimeoutMinutes, NotificationChain msgs) {
		Expression oldTimeoutMinutes = timeoutMinutes;
		timeoutMinutes = newTimeoutMinutes;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GithubMMPackage.STEP__TIMEOUT_MINUTES, oldTimeoutMinutes, newTimeoutMinutes);
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
	public void setTimeoutMinutes(Expression newTimeoutMinutes) {
		if (newTimeoutMinutes != timeoutMinutes) {
			NotificationChain msgs = null;
			if (timeoutMinutes != null)
				msgs = ((InternalEObject) timeoutMinutes).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.STEP__TIMEOUT_MINUTES, null, msgs);
			if (newTimeoutMinutes != null)
				msgs = ((InternalEObject) newTimeoutMinutes).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.STEP__TIMEOUT_MINUTES, null, msgs);
			msgs = basicSetTimeoutMinutes(newTimeoutMinutes, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.STEP__TIMEOUT_MINUTES,
					newTimeoutMinutes, newTimeoutMinutes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getContinueOnError() {
		return continueOnError;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContinueOnError(Expression newContinueOnError, NotificationChain msgs) {
		Expression oldContinueOnError = continueOnError;
		continueOnError = newContinueOnError;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GithubMMPackage.STEP__CONTINUE_ON_ERROR, oldContinueOnError, newContinueOnError);
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
	public void setContinueOnError(Expression newContinueOnError) {
		if (newContinueOnError != continueOnError) {
			NotificationChain msgs = null;
			if (continueOnError != null)
				msgs = ((InternalEObject) continueOnError).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.STEP__CONTINUE_ON_ERROR, null, msgs);
			if (newContinueOnError != null)
				msgs = ((InternalEObject) newContinueOnError).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.STEP__CONTINUE_ON_ERROR, null, msgs);
			msgs = basicSetContinueOnError(newContinueOnError, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.STEP__CONTINUE_ON_ERROR,
					newContinueOnError, newContinueOnError));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GithubMMPackage.STEP__SHELL,
					oldShell, newShell);
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
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.STEP__SHELL, null, msgs);
			if (newShell != null)
				msgs = ((InternalEObject) newShell).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.STEP__SHELL, null, msgs);
			msgs = basicSetShell(newShell, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.STEP__SHELL, newShell, newShell));
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
					GithubMMPackage.STEP__WORKING_DIRECTORY, oldWorkingDirectory, newWorkingDirectory);
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
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.STEP__WORKING_DIRECTORY, null, msgs);
			if (newWorkingDirectory != null)
				msgs = ((InternalEObject) newWorkingDirectory).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.STEP__WORKING_DIRECTORY, null, msgs);
			msgs = basicSetWorkingDirectory(newWorkingDirectory, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.STEP__WORKING_DIRECTORY,
					newWorkingDirectory, newWorkingDirectory));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EMap<VariableDeclaration, Expression> getEnvironmentVariables() {
		if (environmentVariables == null) {
			environmentVariables = new EcoreEMap<VariableDeclaration, Expression>(
					GithubMMPackage.Literals.VARIABLE_ASSIGNMENT, VariableAssignmentImpl.class, this,
					GithubMMPackage.STEP__ENVIRONMENT_VARIABLES);
		}
		return environmentVariables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GithubMMPackage.STEP__NAME:
			return basicSetName(null, msgs);
		case GithubMMPackage.STEP__TIMEOUT_MINUTES:
			return basicSetTimeoutMinutes(null, msgs);
		case GithubMMPackage.STEP__CONTINUE_ON_ERROR:
			return basicSetContinueOnError(null, msgs);
		case GithubMMPackage.STEP__SHELL:
			return basicSetShell(null, msgs);
		case GithubMMPackage.STEP__WORKING_DIRECTORY:
			return basicSetWorkingDirectory(null, msgs);
		case GithubMMPackage.STEP__ENVIRONMENT_VARIABLES:
			return ((InternalEList<?>) getEnvironmentVariables()).basicRemove(otherEnd, msgs);
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
		case GithubMMPackage.STEP__ID:
			return getId();
		case GithubMMPackage.STEP__NAME:
			return getName();
		case GithubMMPackage.STEP__TIMEOUT_MINUTES:
			return getTimeoutMinutes();
		case GithubMMPackage.STEP__CONTINUE_ON_ERROR:
			return getContinueOnError();
		case GithubMMPackage.STEP__SHELL:
			return getShell();
		case GithubMMPackage.STEP__WORKING_DIRECTORY:
			return getWorkingDirectory();
		case GithubMMPackage.STEP__ENVIRONMENT_VARIABLES:
			if (coreType)
				return getEnvironmentVariables();
			else
				return getEnvironmentVariables().map();
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
		case GithubMMPackage.STEP__ID:
			setId((String) newValue);
			return;
		case GithubMMPackage.STEP__NAME:
			setName((Expression) newValue);
			return;
		case GithubMMPackage.STEP__TIMEOUT_MINUTES:
			setTimeoutMinutes((Expression) newValue);
			return;
		case GithubMMPackage.STEP__CONTINUE_ON_ERROR:
			setContinueOnError((Expression) newValue);
			return;
		case GithubMMPackage.STEP__SHELL:
			setShell((Expression) newValue);
			return;
		case GithubMMPackage.STEP__WORKING_DIRECTORY:
			setWorkingDirectory((Expression) newValue);
			return;
		case GithubMMPackage.STEP__ENVIRONMENT_VARIABLES:
			((EStructuralFeature.Setting) getEnvironmentVariables()).set(newValue);
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
		case GithubMMPackage.STEP__ID:
			setId(ID_EDEFAULT);
			return;
		case GithubMMPackage.STEP__NAME:
			setName((Expression) null);
			return;
		case GithubMMPackage.STEP__TIMEOUT_MINUTES:
			setTimeoutMinutes((Expression) null);
			return;
		case GithubMMPackage.STEP__CONTINUE_ON_ERROR:
			setContinueOnError((Expression) null);
			return;
		case GithubMMPackage.STEP__SHELL:
			setShell((Expression) null);
			return;
		case GithubMMPackage.STEP__WORKING_DIRECTORY:
			setWorkingDirectory((Expression) null);
			return;
		case GithubMMPackage.STEP__ENVIRONMENT_VARIABLES:
			getEnvironmentVariables().clear();
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
		case GithubMMPackage.STEP__ID:
			return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		case GithubMMPackage.STEP__NAME:
			return name != null;
		case GithubMMPackage.STEP__TIMEOUT_MINUTES:
			return timeoutMinutes != null;
		case GithubMMPackage.STEP__CONTINUE_ON_ERROR:
			return continueOnError != null;
		case GithubMMPackage.STEP__SHELL:
			return shell != null;
		case GithubMMPackage.STEP__WORKING_DIRECTORY:
			return workingDirectory != null;
		case GithubMMPackage.STEP__ENVIRONMENT_VARIABLES:
			return environmentVariables != null && !environmentVariables.isEmpty();
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
		result.append(" (id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //StepImpl
