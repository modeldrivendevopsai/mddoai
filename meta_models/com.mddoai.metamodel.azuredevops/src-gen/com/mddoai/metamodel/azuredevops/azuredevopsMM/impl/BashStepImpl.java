/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.BashStep;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Bash Step</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.BashStepImpl#getBash <em>Bash</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.BashStepImpl#getFailOnStderr <em>Fail On Stderr</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.BashStepImpl#getWorkingDirectory <em>Working Directory</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BashStepImpl extends StepImpl implements BashStep {
	/**
	 * The default value of the '{@link #getBash() <em>Bash</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBash()
	 * @generated
	 * @ordered
	 */
	protected static final String BASH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBash() <em>Bash</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBash()
	 * @generated
	 * @ordered
	 */
	protected String bash = BASH_EDEFAULT;

	/**
	 * The default value of the '{@link #getFailOnStderr() <em>Fail On Stderr</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFailOnStderr()
	 * @generated
	 * @ordered
	 */
	protected static final String FAIL_ON_STDERR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFailOnStderr() <em>Fail On Stderr</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFailOnStderr()
	 * @generated
	 * @ordered
	 */
	protected String failOnStderr = FAIL_ON_STDERR_EDEFAULT;

	/**
	 * The default value of the '{@link #getWorkingDirectory() <em>Working Directory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkingDirectory()
	 * @generated
	 * @ordered
	 */
	protected static final String WORKING_DIRECTORY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWorkingDirectory() <em>Working Directory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkingDirectory()
	 * @generated
	 * @ordered
	 */
	protected String workingDirectory = WORKING_DIRECTORY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BashStepImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AzuredevopsMMPackage.Literals.BASH_STEP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getBash() {
		return bash;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBash(String newBash) {
		String oldBash = bash;
		bash = newBash;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.BASH_STEP__BASH, oldBash, bash));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getFailOnStderr() {
		return failOnStderr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFailOnStderr(String newFailOnStderr) {
		String oldFailOnStderr = failOnStderr;
		failOnStderr = newFailOnStderr;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.BASH_STEP__FAIL_ON_STDERR,
					oldFailOnStderr, failOnStderr));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getWorkingDirectory() {
		return workingDirectory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWorkingDirectory(String newWorkingDirectory) {
		String oldWorkingDirectory = workingDirectory;
		workingDirectory = newWorkingDirectory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.BASH_STEP__WORKING_DIRECTORY,
					oldWorkingDirectory, workingDirectory));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case AzuredevopsMMPackage.BASH_STEP__BASH:
			return getBash();
		case AzuredevopsMMPackage.BASH_STEP__FAIL_ON_STDERR:
			return getFailOnStderr();
		case AzuredevopsMMPackage.BASH_STEP__WORKING_DIRECTORY:
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
		case AzuredevopsMMPackage.BASH_STEP__BASH:
			setBash((String) newValue);
			return;
		case AzuredevopsMMPackage.BASH_STEP__FAIL_ON_STDERR:
			setFailOnStderr((String) newValue);
			return;
		case AzuredevopsMMPackage.BASH_STEP__WORKING_DIRECTORY:
			setWorkingDirectory((String) newValue);
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
		case AzuredevopsMMPackage.BASH_STEP__BASH:
			setBash(BASH_EDEFAULT);
			return;
		case AzuredevopsMMPackage.BASH_STEP__FAIL_ON_STDERR:
			setFailOnStderr(FAIL_ON_STDERR_EDEFAULT);
			return;
		case AzuredevopsMMPackage.BASH_STEP__WORKING_DIRECTORY:
			setWorkingDirectory(WORKING_DIRECTORY_EDEFAULT);
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
		case AzuredevopsMMPackage.BASH_STEP__BASH:
			return BASH_EDEFAULT == null ? bash != null : !BASH_EDEFAULT.equals(bash);
		case AzuredevopsMMPackage.BASH_STEP__FAIL_ON_STDERR:
			return FAIL_ON_STDERR_EDEFAULT == null ? failOnStderr != null
					: !FAIL_ON_STDERR_EDEFAULT.equals(failOnStderr);
		case AzuredevopsMMPackage.BASH_STEP__WORKING_DIRECTORY:
			return WORKING_DIRECTORY_EDEFAULT == null ? workingDirectory != null
					: !WORKING_DIRECTORY_EDEFAULT.equals(workingDirectory);
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
		result.append(" (bash: ");
		result.append(bash);
		result.append(", failOnStderr: ");
		result.append(failOnStderr);
		result.append(", workingDirectory: ");
		result.append(workingDirectory);
		result.append(')');
		return result.toString();
	}

} //BashStepImpl
