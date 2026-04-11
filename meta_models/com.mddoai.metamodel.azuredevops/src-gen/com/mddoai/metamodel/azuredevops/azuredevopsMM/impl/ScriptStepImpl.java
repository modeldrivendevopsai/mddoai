/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.ScriptStep;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Script Step</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ScriptStepImpl#getScript <em>Script</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ScriptStepImpl#getFailOnStderr <em>Fail On Stderr</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.ScriptStepImpl#getWorkingDirectory <em>Working Directory</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ScriptStepImpl extends StepImpl implements ScriptStep {
	/**
	 * The default value of the '{@link #getScript() <em>Script</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScript()
	 * @generated
	 * @ordered
	 */
	protected static final String SCRIPT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getScript() <em>Script</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScript()
	 * @generated
	 * @ordered
	 */
	protected String script = SCRIPT_EDEFAULT;

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
	protected ScriptStepImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AzuredevopsMMPackage.Literals.SCRIPT_STEP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getScript() {
		return script;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setScript(String newScript) {
		String oldScript = script;
		script = newScript;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.SCRIPT_STEP__SCRIPT, oldScript,
					script));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.SCRIPT_STEP__FAIL_ON_STDERR,
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
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.SCRIPT_STEP__WORKING_DIRECTORY,
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
		case AzuredevopsMMPackage.SCRIPT_STEP__SCRIPT:
			return getScript();
		case AzuredevopsMMPackage.SCRIPT_STEP__FAIL_ON_STDERR:
			return getFailOnStderr();
		case AzuredevopsMMPackage.SCRIPT_STEP__WORKING_DIRECTORY:
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
		case AzuredevopsMMPackage.SCRIPT_STEP__SCRIPT:
			setScript((String) newValue);
			return;
		case AzuredevopsMMPackage.SCRIPT_STEP__FAIL_ON_STDERR:
			setFailOnStderr((String) newValue);
			return;
		case AzuredevopsMMPackage.SCRIPT_STEP__WORKING_DIRECTORY:
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
		case AzuredevopsMMPackage.SCRIPT_STEP__SCRIPT:
			setScript(SCRIPT_EDEFAULT);
			return;
		case AzuredevopsMMPackage.SCRIPT_STEP__FAIL_ON_STDERR:
			setFailOnStderr(FAIL_ON_STDERR_EDEFAULT);
			return;
		case AzuredevopsMMPackage.SCRIPT_STEP__WORKING_DIRECTORY:
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
		case AzuredevopsMMPackage.SCRIPT_STEP__SCRIPT:
			return SCRIPT_EDEFAULT == null ? script != null : !SCRIPT_EDEFAULT.equals(script);
		case AzuredevopsMMPackage.SCRIPT_STEP__FAIL_ON_STDERR:
			return FAIL_ON_STDERR_EDEFAULT == null ? failOnStderr != null
					: !FAIL_ON_STDERR_EDEFAULT.equals(failOnStderr);
		case AzuredevopsMMPackage.SCRIPT_STEP__WORKING_DIRECTORY:
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
		result.append(" (script: ");
		result.append(script);
		result.append(", failOnStderr: ");
		result.append(failOnStderr);
		result.append(", workingDirectory: ");
		result.append(workingDirectory);
		result.append(')');
		return result.toString();
	}

} //ScriptStepImpl
