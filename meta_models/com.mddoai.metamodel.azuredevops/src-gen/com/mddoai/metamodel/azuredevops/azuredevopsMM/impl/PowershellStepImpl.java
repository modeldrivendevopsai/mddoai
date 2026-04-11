/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.PowershellStep;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Powershell Step</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PowershellStepImpl#getPowershell <em>Powershell</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PowershellStepImpl#getErrorActionPreference <em>Error Action Preference</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PowershellStepImpl#getFailOnStderr <em>Fail On Stderr</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PowershellStepImpl#getIgnoreLASTEXITCODE <em>Ignore LASTEXITCODE</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.PowershellStepImpl#getWorkingDirectory <em>Working Directory</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PowershellStepImpl extends StepImpl implements PowershellStep {
	/**
	 * The default value of the '{@link #getPowershell() <em>Powershell</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPowershell()
	 * @generated
	 * @ordered
	 */
	protected static final String POWERSHELL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPowershell() <em>Powershell</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPowershell()
	 * @generated
	 * @ordered
	 */
	protected String powershell = POWERSHELL_EDEFAULT;

	/**
	 * The default value of the '{@link #getErrorActionPreference() <em>Error Action Preference</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getErrorActionPreference()
	 * @generated
	 * @ordered
	 */
	protected static final String ERROR_ACTION_PREFERENCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getErrorActionPreference() <em>Error Action Preference</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getErrorActionPreference()
	 * @generated
	 * @ordered
	 */
	protected String errorActionPreference = ERROR_ACTION_PREFERENCE_EDEFAULT;

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
	 * The default value of the '{@link #getIgnoreLASTEXITCODE() <em>Ignore LASTEXITCODE</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIgnoreLASTEXITCODE()
	 * @generated
	 * @ordered
	 */
	protected static final String IGNORE_LASTEXITCODE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIgnoreLASTEXITCODE() <em>Ignore LASTEXITCODE</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIgnoreLASTEXITCODE()
	 * @generated
	 * @ordered
	 */
	protected String ignoreLASTEXITCODE = IGNORE_LASTEXITCODE_EDEFAULT;

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
	protected PowershellStepImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AzuredevopsMMPackage.Literals.POWERSHELL_STEP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPowershell() {
		return powershell;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPowershell(String newPowershell) {
		String oldPowershell = powershell;
		powershell = newPowershell;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.POWERSHELL_STEP__POWERSHELL,
					oldPowershell, powershell));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getErrorActionPreference() {
		return errorActionPreference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setErrorActionPreference(String newErrorActionPreference) {
		String oldErrorActionPreference = errorActionPreference;
		errorActionPreference = newErrorActionPreference;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.POWERSHELL_STEP__ERROR_ACTION_PREFERENCE, oldErrorActionPreference,
					errorActionPreference));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.POWERSHELL_STEP__FAIL_ON_STDERR,
					oldFailOnStderr, failOnStderr));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getIgnoreLASTEXITCODE() {
		return ignoreLASTEXITCODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIgnoreLASTEXITCODE(String newIgnoreLASTEXITCODE) {
		String oldIgnoreLASTEXITCODE = ignoreLASTEXITCODE;
		ignoreLASTEXITCODE = newIgnoreLASTEXITCODE;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.POWERSHELL_STEP__IGNORE_LASTEXITCODE, oldIgnoreLASTEXITCODE,
					ignoreLASTEXITCODE));
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
			eNotify(new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.POWERSHELL_STEP__WORKING_DIRECTORY, oldWorkingDirectory, workingDirectory));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case AzuredevopsMMPackage.POWERSHELL_STEP__POWERSHELL:
			return getPowershell();
		case AzuredevopsMMPackage.POWERSHELL_STEP__ERROR_ACTION_PREFERENCE:
			return getErrorActionPreference();
		case AzuredevopsMMPackage.POWERSHELL_STEP__FAIL_ON_STDERR:
			return getFailOnStderr();
		case AzuredevopsMMPackage.POWERSHELL_STEP__IGNORE_LASTEXITCODE:
			return getIgnoreLASTEXITCODE();
		case AzuredevopsMMPackage.POWERSHELL_STEP__WORKING_DIRECTORY:
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
		case AzuredevopsMMPackage.POWERSHELL_STEP__POWERSHELL:
			setPowershell((String) newValue);
			return;
		case AzuredevopsMMPackage.POWERSHELL_STEP__ERROR_ACTION_PREFERENCE:
			setErrorActionPreference((String) newValue);
			return;
		case AzuredevopsMMPackage.POWERSHELL_STEP__FAIL_ON_STDERR:
			setFailOnStderr((String) newValue);
			return;
		case AzuredevopsMMPackage.POWERSHELL_STEP__IGNORE_LASTEXITCODE:
			setIgnoreLASTEXITCODE((String) newValue);
			return;
		case AzuredevopsMMPackage.POWERSHELL_STEP__WORKING_DIRECTORY:
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
		case AzuredevopsMMPackage.POWERSHELL_STEP__POWERSHELL:
			setPowershell(POWERSHELL_EDEFAULT);
			return;
		case AzuredevopsMMPackage.POWERSHELL_STEP__ERROR_ACTION_PREFERENCE:
			setErrorActionPreference(ERROR_ACTION_PREFERENCE_EDEFAULT);
			return;
		case AzuredevopsMMPackage.POWERSHELL_STEP__FAIL_ON_STDERR:
			setFailOnStderr(FAIL_ON_STDERR_EDEFAULT);
			return;
		case AzuredevopsMMPackage.POWERSHELL_STEP__IGNORE_LASTEXITCODE:
			setIgnoreLASTEXITCODE(IGNORE_LASTEXITCODE_EDEFAULT);
			return;
		case AzuredevopsMMPackage.POWERSHELL_STEP__WORKING_DIRECTORY:
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
		case AzuredevopsMMPackage.POWERSHELL_STEP__POWERSHELL:
			return POWERSHELL_EDEFAULT == null ? powershell != null : !POWERSHELL_EDEFAULT.equals(powershell);
		case AzuredevopsMMPackage.POWERSHELL_STEP__ERROR_ACTION_PREFERENCE:
			return ERROR_ACTION_PREFERENCE_EDEFAULT == null ? errorActionPreference != null
					: !ERROR_ACTION_PREFERENCE_EDEFAULT.equals(errorActionPreference);
		case AzuredevopsMMPackage.POWERSHELL_STEP__FAIL_ON_STDERR:
			return FAIL_ON_STDERR_EDEFAULT == null ? failOnStderr != null
					: !FAIL_ON_STDERR_EDEFAULT.equals(failOnStderr);
		case AzuredevopsMMPackage.POWERSHELL_STEP__IGNORE_LASTEXITCODE:
			return IGNORE_LASTEXITCODE_EDEFAULT == null ? ignoreLASTEXITCODE != null
					: !IGNORE_LASTEXITCODE_EDEFAULT.equals(ignoreLASTEXITCODE);
		case AzuredevopsMMPackage.POWERSHELL_STEP__WORKING_DIRECTORY:
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
		result.append(" (powershell: ");
		result.append(powershell);
		result.append(", errorActionPreference: ");
		result.append(errorActionPreference);
		result.append(", failOnStderr: ");
		result.append(failOnStderr);
		result.append(", ignoreLASTEXITCODE: ");
		result.append(ignoreLASTEXITCODE);
		result.append(", workingDirectory: ");
		result.append(workingDirectory);
		result.append(')');
		return result.toString();
	}

} //PowershellStepImpl
