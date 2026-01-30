/**
 */
package com.mddoai.metamodel.pim.pimMM.impl;

import com.mddoai.metamodel.pim.pimMM.Expression;
import com.mddoai.metamodel.pim.pimMM.NonConditionalStep;
import com.mddoai.metamodel.pim.pimMM.PimMMPackage;
import com.mddoai.metamodel.pim.pimMM.VariableDeclaration;

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
 * An implementation of the model object '<em><b>Non Conditional Step</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.NonConditionalStepImpl#getId <em>Id</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.NonConditionalStepImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.NonConditionalStepImpl#getEnvironmentVariables <em>Environment Variables</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.NonConditionalStepImpl#getAllowFailure <em>Allow Failure</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.NonConditionalStepImpl#getWorkingDirectory <em>Working Directory</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.NonConditionalStepImpl#getTimeoutMinutes <em>Timeout Minutes</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class NonConditionalStepImpl extends StepImpl implements NonConditionalStep {
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
	 * The cached value of the '{@link #getEnvironmentVariables() <em>Environment Variables</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnvironmentVariables()
	 * @generated
	 * @ordered
	 */
	protected EMap<VariableDeclaration, Expression> environmentVariables;

	/**
	 * The default value of the '{@link #getAllowFailure() <em>Allow Failure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllowFailure()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean ALLOW_FAILURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAllowFailure() <em>Allow Failure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllowFailure()
	 * @generated
	 * @ordered
	 */
	protected Boolean allowFailure = ALLOW_FAILURE_EDEFAULT;

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
	 * The cached value of the '{@link #getTimeoutMinutes() <em>Timeout Minutes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeoutMinutes()
	 * @generated
	 * @ordered
	 */
	protected Expression timeoutMinutes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NonConditionalStepImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PimMMPackage.Literals.NON_CONDITIONAL_STEP;
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
			eNotify(new ENotificationImpl(this, Notification.SET, PimMMPackage.NON_CONDITIONAL_STEP__ID, oldId, id));
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
			eNotify(new ENotificationImpl(this, Notification.SET, PimMMPackage.NON_CONDITIONAL_STEP__NAME, oldName,
					name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EMap<VariableDeclaration, Expression> getEnvironmentVariables() {
		if (environmentVariables == null) {
			environmentVariables = new EcoreEMap<VariableDeclaration, Expression>(PimMMPackage.Literals.ASSIGNMENT,
					AssignmentImpl.class, this, PimMMPackage.NON_CONDITIONAL_STEP__ENVIRONMENT_VARIABLES);
		}
		return environmentVariables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getAllowFailure() {
		return allowFailure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAllowFailure(Boolean newAllowFailure) {
		Boolean oldAllowFailure = allowFailure;
		allowFailure = newAllowFailure;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PimMMPackage.NON_CONDITIONAL_STEP__ALLOW_FAILURE,
					oldAllowFailure, allowFailure));
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
					PimMMPackage.NON_CONDITIONAL_STEP__WORKING_DIRECTORY, oldWorkingDirectory, newWorkingDirectory);
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
						EOPPOSITE_FEATURE_BASE - PimMMPackage.NON_CONDITIONAL_STEP__WORKING_DIRECTORY, null, msgs);
			if (newWorkingDirectory != null)
				msgs = ((InternalEObject) newWorkingDirectory).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - PimMMPackage.NON_CONDITIONAL_STEP__WORKING_DIRECTORY, null, msgs);
			msgs = basicSetWorkingDirectory(newWorkingDirectory, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PimMMPackage.NON_CONDITIONAL_STEP__WORKING_DIRECTORY,
					newWorkingDirectory, newWorkingDirectory));
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
					PimMMPackage.NON_CONDITIONAL_STEP__TIMEOUT_MINUTES, oldTimeoutMinutes, newTimeoutMinutes);
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
						EOPPOSITE_FEATURE_BASE - PimMMPackage.NON_CONDITIONAL_STEP__TIMEOUT_MINUTES, null, msgs);
			if (newTimeoutMinutes != null)
				msgs = ((InternalEObject) newTimeoutMinutes).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - PimMMPackage.NON_CONDITIONAL_STEP__TIMEOUT_MINUTES, null, msgs);
			msgs = basicSetTimeoutMinutes(newTimeoutMinutes, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PimMMPackage.NON_CONDITIONAL_STEP__TIMEOUT_MINUTES,
					newTimeoutMinutes, newTimeoutMinutes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case PimMMPackage.NON_CONDITIONAL_STEP__ENVIRONMENT_VARIABLES:
			return ((InternalEList<?>) getEnvironmentVariables()).basicRemove(otherEnd, msgs);
		case PimMMPackage.NON_CONDITIONAL_STEP__WORKING_DIRECTORY:
			return basicSetWorkingDirectory(null, msgs);
		case PimMMPackage.NON_CONDITIONAL_STEP__TIMEOUT_MINUTES:
			return basicSetTimeoutMinutes(null, msgs);
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
		case PimMMPackage.NON_CONDITIONAL_STEP__ID:
			return getId();
		case PimMMPackage.NON_CONDITIONAL_STEP__NAME:
			return getName();
		case PimMMPackage.NON_CONDITIONAL_STEP__ENVIRONMENT_VARIABLES:
			if (coreType)
				return getEnvironmentVariables();
			else
				return getEnvironmentVariables().map();
		case PimMMPackage.NON_CONDITIONAL_STEP__ALLOW_FAILURE:
			return getAllowFailure();
		case PimMMPackage.NON_CONDITIONAL_STEP__WORKING_DIRECTORY:
			return getWorkingDirectory();
		case PimMMPackage.NON_CONDITIONAL_STEP__TIMEOUT_MINUTES:
			return getTimeoutMinutes();
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
		case PimMMPackage.NON_CONDITIONAL_STEP__ID:
			setId((String) newValue);
			return;
		case PimMMPackage.NON_CONDITIONAL_STEP__NAME:
			setName((String) newValue);
			return;
		case PimMMPackage.NON_CONDITIONAL_STEP__ENVIRONMENT_VARIABLES:
			((EStructuralFeature.Setting) getEnvironmentVariables()).set(newValue);
			return;
		case PimMMPackage.NON_CONDITIONAL_STEP__ALLOW_FAILURE:
			setAllowFailure((Boolean) newValue);
			return;
		case PimMMPackage.NON_CONDITIONAL_STEP__WORKING_DIRECTORY:
			setWorkingDirectory((Expression) newValue);
			return;
		case PimMMPackage.NON_CONDITIONAL_STEP__TIMEOUT_MINUTES:
			setTimeoutMinutes((Expression) newValue);
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
		case PimMMPackage.NON_CONDITIONAL_STEP__ID:
			setId(ID_EDEFAULT);
			return;
		case PimMMPackage.NON_CONDITIONAL_STEP__NAME:
			setName(NAME_EDEFAULT);
			return;
		case PimMMPackage.NON_CONDITIONAL_STEP__ENVIRONMENT_VARIABLES:
			getEnvironmentVariables().clear();
			return;
		case PimMMPackage.NON_CONDITIONAL_STEP__ALLOW_FAILURE:
			setAllowFailure(ALLOW_FAILURE_EDEFAULT);
			return;
		case PimMMPackage.NON_CONDITIONAL_STEP__WORKING_DIRECTORY:
			setWorkingDirectory((Expression) null);
			return;
		case PimMMPackage.NON_CONDITIONAL_STEP__TIMEOUT_MINUTES:
			setTimeoutMinutes((Expression) null);
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
		case PimMMPackage.NON_CONDITIONAL_STEP__ID:
			return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		case PimMMPackage.NON_CONDITIONAL_STEP__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case PimMMPackage.NON_CONDITIONAL_STEP__ENVIRONMENT_VARIABLES:
			return environmentVariables != null && !environmentVariables.isEmpty();
		case PimMMPackage.NON_CONDITIONAL_STEP__ALLOW_FAILURE:
			return ALLOW_FAILURE_EDEFAULT == null ? allowFailure != null : !ALLOW_FAILURE_EDEFAULT.equals(allowFailure);
		case PimMMPackage.NON_CONDITIONAL_STEP__WORKING_DIRECTORY:
			return workingDirectory != null;
		case PimMMPackage.NON_CONDITIONAL_STEP__TIMEOUT_MINUTES:
			return timeoutMinutes != null;
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
		result.append(", name: ");
		result.append(name);
		result.append(", allowFailure: ");
		result.append(allowFailure);
		result.append(')');
		return result.toString();
	}

} //NonConditionalStepImpl
