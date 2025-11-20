/**
 */
package com.mddoai.metamodel.pim.pimMM.impl;

import com.mddoai.metamodel.pim.pimMM.Agent;
import com.mddoai.metamodel.pim.pimMM.Expression;
import com.mddoai.metamodel.pim.pimMM.Input;
import com.mddoai.metamodel.pim.pimMM.Output;
import com.mddoai.metamodel.pim.pimMM.PimMMPackage;
import com.mddoai.metamodel.pim.pimMM.PipelineBlock;
import com.mddoai.metamodel.pim.pimMM.VariableDeclaration;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pipeline Block</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.PipelineBlockImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.PipelineBlockImpl#getAgent <em>Agent</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.PipelineBlockImpl#getInputs <em>Inputs</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.PipelineBlockImpl#getOutputs <em>Outputs</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.PipelineBlockImpl#getEnvironmentVariables <em>Environment Variables</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.PipelineBlockImpl#getTimeoutMinutes <em>Timeout Minutes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.PipelineBlockImpl#getWorkingDirectory <em>Working Directory</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.PipelineBlockImpl#getShell <em>Shell</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class PipelineBlockImpl extends MinimalEObjectImpl.Container implements PipelineBlock {
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
	 * The cached value of the '{@link #getAgent() <em>Agent</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAgent()
	 * @generated
	 * @ordered
	 */
	protected Agent agent;

	/**
	 * The cached value of the '{@link #getInputs() <em>Inputs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInputs()
	 * @generated
	 * @ordered
	 */
	protected EList<Input> inputs;

	/**
	 * The cached value of the '{@link #getOutputs() <em>Outputs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutputs()
	 * @generated
	 * @ordered
	 */
	protected EList<Output> outputs;

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
	 * The default value of the '{@link #getTimeoutMinutes() <em>Timeout Minutes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeoutMinutes()
	 * @generated
	 * @ordered
	 */
	protected static final Integer TIMEOUT_MINUTES_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTimeoutMinutes() <em>Timeout Minutes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeoutMinutes()
	 * @generated
	 * @ordered
	 */
	protected Integer timeoutMinutes = TIMEOUT_MINUTES_EDEFAULT;

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
	 * The cached value of the '{@link #getShell() <em>Shell</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShell()
	 * @generated
	 * @ordered
	 */
	protected Expression shell;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PipelineBlockImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PimMMPackage.Literals.PIPELINE_BLOCK;
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
			eNotify(new ENotificationImpl(this, Notification.SET, PimMMPackage.PIPELINE_BLOCK__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Agent getAgent() {
		return agent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAgent(Agent newAgent, NotificationChain msgs) {
		Agent oldAgent = agent;
		agent = newAgent;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					PimMMPackage.PIPELINE_BLOCK__AGENT, oldAgent, newAgent);
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
	public void setAgent(Agent newAgent) {
		if (newAgent != agent) {
			NotificationChain msgs = null;
			if (agent != null)
				msgs = ((InternalEObject) agent).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - PimMMPackage.PIPELINE_BLOCK__AGENT, null, msgs);
			if (newAgent != null)
				msgs = ((InternalEObject) newAgent).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - PimMMPackage.PIPELINE_BLOCK__AGENT, null, msgs);
			msgs = basicSetAgent(newAgent, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PimMMPackage.PIPELINE_BLOCK__AGENT, newAgent,
					newAgent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Input> getInputs() {
		if (inputs == null) {
			inputs = new EObjectContainmentEList<Input>(Input.class, this, PimMMPackage.PIPELINE_BLOCK__INPUTS);
		}
		return inputs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Output> getOutputs() {
		if (outputs == null) {
			outputs = new EObjectContainmentEList<Output>(Output.class, this, PimMMPackage.PIPELINE_BLOCK__OUTPUTS);
		}
		return outputs;
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
					AssignmentImpl.class, this, PimMMPackage.PIPELINE_BLOCK__ENVIRONMENT_VARIABLES);
		}
		return environmentVariables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Integer getTimeoutMinutes() {
		return timeoutMinutes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTimeoutMinutes(Integer newTimeoutMinutes) {
		Integer oldTimeoutMinutes = timeoutMinutes;
		timeoutMinutes = newTimeoutMinutes;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PimMMPackage.PIPELINE_BLOCK__TIMEOUT_MINUTES,
					oldTimeoutMinutes, timeoutMinutes));
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
					PimMMPackage.PIPELINE_BLOCK__WORKING_DIRECTORY, oldWorkingDirectory, newWorkingDirectory);
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
						EOPPOSITE_FEATURE_BASE - PimMMPackage.PIPELINE_BLOCK__WORKING_DIRECTORY, null, msgs);
			if (newWorkingDirectory != null)
				msgs = ((InternalEObject) newWorkingDirectory).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - PimMMPackage.PIPELINE_BLOCK__WORKING_DIRECTORY, null, msgs);
			msgs = basicSetWorkingDirectory(newWorkingDirectory, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PimMMPackage.PIPELINE_BLOCK__WORKING_DIRECTORY,
					newWorkingDirectory, newWorkingDirectory));
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
					PimMMPackage.PIPELINE_BLOCK__SHELL, oldShell, newShell);
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
						EOPPOSITE_FEATURE_BASE - PimMMPackage.PIPELINE_BLOCK__SHELL, null, msgs);
			if (newShell != null)
				msgs = ((InternalEObject) newShell).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - PimMMPackage.PIPELINE_BLOCK__SHELL, null, msgs);
			msgs = basicSetShell(newShell, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PimMMPackage.PIPELINE_BLOCK__SHELL, newShell,
					newShell));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case PimMMPackage.PIPELINE_BLOCK__AGENT:
			return basicSetAgent(null, msgs);
		case PimMMPackage.PIPELINE_BLOCK__INPUTS:
			return ((InternalEList<?>) getInputs()).basicRemove(otherEnd, msgs);
		case PimMMPackage.PIPELINE_BLOCK__OUTPUTS:
			return ((InternalEList<?>) getOutputs()).basicRemove(otherEnd, msgs);
		case PimMMPackage.PIPELINE_BLOCK__ENVIRONMENT_VARIABLES:
			return ((InternalEList<?>) getEnvironmentVariables()).basicRemove(otherEnd, msgs);
		case PimMMPackage.PIPELINE_BLOCK__WORKING_DIRECTORY:
			return basicSetWorkingDirectory(null, msgs);
		case PimMMPackage.PIPELINE_BLOCK__SHELL:
			return basicSetShell(null, msgs);
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
		case PimMMPackage.PIPELINE_BLOCK__NAME:
			return getName();
		case PimMMPackage.PIPELINE_BLOCK__AGENT:
			return getAgent();
		case PimMMPackage.PIPELINE_BLOCK__INPUTS:
			return getInputs();
		case PimMMPackage.PIPELINE_BLOCK__OUTPUTS:
			return getOutputs();
		case PimMMPackage.PIPELINE_BLOCK__ENVIRONMENT_VARIABLES:
			if (coreType)
				return getEnvironmentVariables();
			else
				return getEnvironmentVariables().map();
		case PimMMPackage.PIPELINE_BLOCK__TIMEOUT_MINUTES:
			return getTimeoutMinutes();
		case PimMMPackage.PIPELINE_BLOCK__WORKING_DIRECTORY:
			return getWorkingDirectory();
		case PimMMPackage.PIPELINE_BLOCK__SHELL:
			return getShell();
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
		case PimMMPackage.PIPELINE_BLOCK__NAME:
			setName((String) newValue);
			return;
		case PimMMPackage.PIPELINE_BLOCK__AGENT:
			setAgent((Agent) newValue);
			return;
		case PimMMPackage.PIPELINE_BLOCK__INPUTS:
			getInputs().clear();
			getInputs().addAll((Collection<? extends Input>) newValue);
			return;
		case PimMMPackage.PIPELINE_BLOCK__OUTPUTS:
			getOutputs().clear();
			getOutputs().addAll((Collection<? extends Output>) newValue);
			return;
		case PimMMPackage.PIPELINE_BLOCK__ENVIRONMENT_VARIABLES:
			((EStructuralFeature.Setting) getEnvironmentVariables()).set(newValue);
			return;
		case PimMMPackage.PIPELINE_BLOCK__TIMEOUT_MINUTES:
			setTimeoutMinutes((Integer) newValue);
			return;
		case PimMMPackage.PIPELINE_BLOCK__WORKING_DIRECTORY:
			setWorkingDirectory((Expression) newValue);
			return;
		case PimMMPackage.PIPELINE_BLOCK__SHELL:
			setShell((Expression) newValue);
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
		case PimMMPackage.PIPELINE_BLOCK__NAME:
			setName(NAME_EDEFAULT);
			return;
		case PimMMPackage.PIPELINE_BLOCK__AGENT:
			setAgent((Agent) null);
			return;
		case PimMMPackage.PIPELINE_BLOCK__INPUTS:
			getInputs().clear();
			return;
		case PimMMPackage.PIPELINE_BLOCK__OUTPUTS:
			getOutputs().clear();
			return;
		case PimMMPackage.PIPELINE_BLOCK__ENVIRONMENT_VARIABLES:
			getEnvironmentVariables().clear();
			return;
		case PimMMPackage.PIPELINE_BLOCK__TIMEOUT_MINUTES:
			setTimeoutMinutes(TIMEOUT_MINUTES_EDEFAULT);
			return;
		case PimMMPackage.PIPELINE_BLOCK__WORKING_DIRECTORY:
			setWorkingDirectory((Expression) null);
			return;
		case PimMMPackage.PIPELINE_BLOCK__SHELL:
			setShell((Expression) null);
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
		case PimMMPackage.PIPELINE_BLOCK__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case PimMMPackage.PIPELINE_BLOCK__AGENT:
			return agent != null;
		case PimMMPackage.PIPELINE_BLOCK__INPUTS:
			return inputs != null && !inputs.isEmpty();
		case PimMMPackage.PIPELINE_BLOCK__OUTPUTS:
			return outputs != null && !outputs.isEmpty();
		case PimMMPackage.PIPELINE_BLOCK__ENVIRONMENT_VARIABLES:
			return environmentVariables != null && !environmentVariables.isEmpty();
		case PimMMPackage.PIPELINE_BLOCK__TIMEOUT_MINUTES:
			return TIMEOUT_MINUTES_EDEFAULT == null ? timeoutMinutes != null
					: !TIMEOUT_MINUTES_EDEFAULT.equals(timeoutMinutes);
		case PimMMPackage.PIPELINE_BLOCK__WORKING_DIRECTORY:
			return workingDirectory != null;
		case PimMMPackage.PIPELINE_BLOCK__SHELL:
			return shell != null;
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
		result.append(", timeoutMinutes: ");
		result.append(timeoutMinutes);
		result.append(')');
		return result.toString();
	}

} //PipelineBlockImpl
