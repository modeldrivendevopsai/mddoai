/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.Step;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.StepTarget;

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
 * An implementation of the model object '<em><b>Step</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StepImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StepImpl#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StepImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StepImpl#getContinueOnError <em>Continue On Error</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StepImpl#getEnabled <em>Enabled</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StepImpl#getTimeoutInMinutes <em>Timeout In Minutes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StepImpl#getRetryCountOnTaskFailure <em>Retry Count On Task Failure</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StepImpl#getEnv <em>Env</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StepImpl#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class StepImpl extends MinimalEObjectImpl.Container implements Step {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDisplayName() <em>Display Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayName()
	 * @generated
	 * @ordered
	 */
	protected static final String DISPLAY_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDisplayName() <em>Display Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayName()
	 * @generated
	 * @ordered
	 */
	protected String displayName = DISPLAY_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getCondition() <em>Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCondition()
	 * @generated
	 * @ordered
	 */
	protected static final String CONDITION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCondition() <em>Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCondition()
	 * @generated
	 * @ordered
	 */
	protected String condition = CONDITION_EDEFAULT;

	/**
	 * The default value of the '{@link #getContinueOnError() <em>Continue On Error</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContinueOnError()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean CONTINUE_ON_ERROR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getContinueOnError() <em>Continue On Error</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContinueOnError()
	 * @generated
	 * @ordered
	 */
	protected Boolean continueOnError = CONTINUE_ON_ERROR_EDEFAULT;

	/**
	 * The default value of the '{@link #getEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnabled()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean ENABLED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnabled()
	 * @generated
	 * @ordered
	 */
	protected Boolean enabled = ENABLED_EDEFAULT;

	/**
	 * The default value of the '{@link #getTimeoutInMinutes() <em>Timeout In Minutes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeoutInMinutes()
	 * @generated
	 * @ordered
	 */
	protected static final String TIMEOUT_IN_MINUTES_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTimeoutInMinutes() <em>Timeout In Minutes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeoutInMinutes()
	 * @generated
	 * @ordered
	 */
	protected String timeoutInMinutes = TIMEOUT_IN_MINUTES_EDEFAULT;

	/**
	 * The default value of the '{@link #getRetryCountOnTaskFailure() <em>Retry Count On Task Failure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRetryCountOnTaskFailure()
	 * @generated
	 * @ordered
	 */
	protected static final String RETRY_COUNT_ON_TASK_FAILURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRetryCountOnTaskFailure() <em>Retry Count On Task Failure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRetryCountOnTaskFailure()
	 * @generated
	 * @ordered
	 */
	protected String retryCountOnTaskFailure = RETRY_COUNT_ON_TASK_FAILURE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getEnv() <em>Env</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnv()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> env;

	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected StepTarget target;

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
		return AzuredevopsMMPackage.Literals.STEP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.STEP__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDisplayName(String newDisplayName) {
		String oldDisplayName = displayName;
		displayName = newDisplayName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.STEP__DISPLAY_NAME,
					oldDisplayName, displayName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCondition() {
		return condition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCondition(String newCondition) {
		String oldCondition = condition;
		condition = newCondition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.STEP__CONDITION, oldCondition,
					condition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getContinueOnError() {
		return continueOnError;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setContinueOnError(Boolean newContinueOnError) {
		Boolean oldContinueOnError = continueOnError;
		continueOnError = newContinueOnError;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.STEP__CONTINUE_ON_ERROR,
					oldContinueOnError, continueOnError));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEnabled(Boolean newEnabled) {
		Boolean oldEnabled = enabled;
		enabled = newEnabled;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.STEP__ENABLED, oldEnabled,
					enabled));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTimeoutInMinutes() {
		return timeoutInMinutes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTimeoutInMinutes(String newTimeoutInMinutes) {
		String oldTimeoutInMinutes = timeoutInMinutes;
		timeoutInMinutes = newTimeoutInMinutes;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.STEP__TIMEOUT_IN_MINUTES,
					oldTimeoutInMinutes, timeoutInMinutes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getRetryCountOnTaskFailure() {
		return retryCountOnTaskFailure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRetryCountOnTaskFailure(String newRetryCountOnTaskFailure) {
		String oldRetryCountOnTaskFailure = retryCountOnTaskFailure;
		retryCountOnTaskFailure = newRetryCountOnTaskFailure;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.STEP__RETRY_COUNT_ON_TASK_FAILURE, oldRetryCountOnTaskFailure,
					retryCountOnTaskFailure));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EMap<String, String> getEnv() {
		if (env == null) {
			env = new EcoreEMap<String, String>(AzuredevopsMMPackage.Literals.ENV_ENTRY, EnvEntryImpl.class, this,
					AzuredevopsMMPackage.STEP__ENV);
		}
		return env;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StepTarget getTarget() {
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTarget(StepTarget newTarget, NotificationChain msgs) {
		StepTarget oldTarget = target;
		target = newTarget;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.STEP__TARGET, oldTarget, newTarget);
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
	public void setTarget(StepTarget newTarget) {
		if (newTarget != target) {
			NotificationChain msgs = null;
			if (target != null)
				msgs = ((InternalEObject) target).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.STEP__TARGET, null, msgs);
			if (newTarget != null)
				msgs = ((InternalEObject) newTarget).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.STEP__TARGET, null, msgs);
			msgs = basicSetTarget(newTarget, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.STEP__TARGET, newTarget,
					newTarget));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case AzuredevopsMMPackage.STEP__ENV:
			return ((InternalEList<?>) getEnv()).basicRemove(otherEnd, msgs);
		case AzuredevopsMMPackage.STEP__TARGET:
			return basicSetTarget(null, msgs);
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
		case AzuredevopsMMPackage.STEP__NAME:
			return getName();
		case AzuredevopsMMPackage.STEP__DISPLAY_NAME:
			return getDisplayName();
		case AzuredevopsMMPackage.STEP__CONDITION:
			return getCondition();
		case AzuredevopsMMPackage.STEP__CONTINUE_ON_ERROR:
			return getContinueOnError();
		case AzuredevopsMMPackage.STEP__ENABLED:
			return getEnabled();
		case AzuredevopsMMPackage.STEP__TIMEOUT_IN_MINUTES:
			return getTimeoutInMinutes();
		case AzuredevopsMMPackage.STEP__RETRY_COUNT_ON_TASK_FAILURE:
			return getRetryCountOnTaskFailure();
		case AzuredevopsMMPackage.STEP__ENV:
			if (coreType)
				return getEnv();
			else
				return getEnv().map();
		case AzuredevopsMMPackage.STEP__TARGET:
			return getTarget();
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
		case AzuredevopsMMPackage.STEP__NAME:
			setName((String) newValue);
			return;
		case AzuredevopsMMPackage.STEP__DISPLAY_NAME:
			setDisplayName((String) newValue);
			return;
		case AzuredevopsMMPackage.STEP__CONDITION:
			setCondition((String) newValue);
			return;
		case AzuredevopsMMPackage.STEP__CONTINUE_ON_ERROR:
			setContinueOnError((Boolean) newValue);
			return;
		case AzuredevopsMMPackage.STEP__ENABLED:
			setEnabled((Boolean) newValue);
			return;
		case AzuredevopsMMPackage.STEP__TIMEOUT_IN_MINUTES:
			setTimeoutInMinutes((String) newValue);
			return;
		case AzuredevopsMMPackage.STEP__RETRY_COUNT_ON_TASK_FAILURE:
			setRetryCountOnTaskFailure((String) newValue);
			return;
		case AzuredevopsMMPackage.STEP__ENV:
			((EStructuralFeature.Setting) getEnv()).set(newValue);
			return;
		case AzuredevopsMMPackage.STEP__TARGET:
			setTarget((StepTarget) newValue);
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
		case AzuredevopsMMPackage.STEP__NAME:
			setName(NAME_EDEFAULT);
			return;
		case AzuredevopsMMPackage.STEP__DISPLAY_NAME:
			setDisplayName(DISPLAY_NAME_EDEFAULT);
			return;
		case AzuredevopsMMPackage.STEP__CONDITION:
			setCondition(CONDITION_EDEFAULT);
			return;
		case AzuredevopsMMPackage.STEP__CONTINUE_ON_ERROR:
			setContinueOnError(CONTINUE_ON_ERROR_EDEFAULT);
			return;
		case AzuredevopsMMPackage.STEP__ENABLED:
			setEnabled(ENABLED_EDEFAULT);
			return;
		case AzuredevopsMMPackage.STEP__TIMEOUT_IN_MINUTES:
			setTimeoutInMinutes(TIMEOUT_IN_MINUTES_EDEFAULT);
			return;
		case AzuredevopsMMPackage.STEP__RETRY_COUNT_ON_TASK_FAILURE:
			setRetryCountOnTaskFailure(RETRY_COUNT_ON_TASK_FAILURE_EDEFAULT);
			return;
		case AzuredevopsMMPackage.STEP__ENV:
			getEnv().clear();
			return;
		case AzuredevopsMMPackage.STEP__TARGET:
			setTarget((StepTarget) null);
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
		case AzuredevopsMMPackage.STEP__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case AzuredevopsMMPackage.STEP__DISPLAY_NAME:
			return DISPLAY_NAME_EDEFAULT == null ? displayName != null : !DISPLAY_NAME_EDEFAULT.equals(displayName);
		case AzuredevopsMMPackage.STEP__CONDITION:
			return CONDITION_EDEFAULT == null ? condition != null : !CONDITION_EDEFAULT.equals(condition);
		case AzuredevopsMMPackage.STEP__CONTINUE_ON_ERROR:
			return CONTINUE_ON_ERROR_EDEFAULT == null ? continueOnError != null
					: !CONTINUE_ON_ERROR_EDEFAULT.equals(continueOnError);
		case AzuredevopsMMPackage.STEP__ENABLED:
			return ENABLED_EDEFAULT == null ? enabled != null : !ENABLED_EDEFAULT.equals(enabled);
		case AzuredevopsMMPackage.STEP__TIMEOUT_IN_MINUTES:
			return TIMEOUT_IN_MINUTES_EDEFAULT == null ? timeoutInMinutes != null
					: !TIMEOUT_IN_MINUTES_EDEFAULT.equals(timeoutInMinutes);
		case AzuredevopsMMPackage.STEP__RETRY_COUNT_ON_TASK_FAILURE:
			return RETRY_COUNT_ON_TASK_FAILURE_EDEFAULT == null ? retryCountOnTaskFailure != null
					: !RETRY_COUNT_ON_TASK_FAILURE_EDEFAULT.equals(retryCountOnTaskFailure);
		case AzuredevopsMMPackage.STEP__ENV:
			return env != null && !env.isEmpty();
		case AzuredevopsMMPackage.STEP__TARGET:
			return target != null;
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
		result.append(" (name: ");
		result.append(name);
		result.append(", displayName: ");
		result.append(displayName);
		result.append(", condition: ");
		result.append(condition);
		result.append(", continueOnError: ");
		result.append(continueOnError);
		result.append(", enabled: ");
		result.append(enabled);
		result.append(", timeoutInMinutes: ");
		result.append(timeoutInMinutes);
		result.append(", retryCountOnTaskFailure: ");
		result.append(retryCountOnTaskFailure);
		result.append(')');
		return result.toString();
	}

} //StepImpl
