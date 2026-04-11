/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.SettableVariables;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.StepTarget;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.TARGET_COMMANDS;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Step Target</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StepTargetImpl#getContainer <em>Container</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StepTargetImpl#getCommands <em>Commands</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.StepTargetImpl#getSettableVariables <em>Settable Variables</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StepTargetImpl extends MinimalEObjectImpl.Container implements StepTarget {
	/**
	 * The default value of the '{@link #getContainer() <em>Container</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainer()
	 * @generated
	 * @ordered
	 */
	protected static final String CONTAINER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getContainer() <em>Container</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainer()
	 * @generated
	 * @ordered
	 */
	protected String container = CONTAINER_EDEFAULT;

	/**
	 * The default value of the '{@link #getCommands() <em>Commands</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCommands()
	 * @generated
	 * @ordered
	 */
	protected static final TARGET_COMMANDS COMMANDS_EDEFAULT = TARGET_COMMANDS.ANY;

	/**
	 * The cached value of the '{@link #getCommands() <em>Commands</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCommands()
	 * @generated
	 * @ordered
	 */
	protected TARGET_COMMANDS commands = COMMANDS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSettableVariables() <em>Settable Variables</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSettableVariables()
	 * @generated
	 * @ordered
	 */
	protected SettableVariables settableVariables;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StepTargetImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AzuredevopsMMPackage.Literals.STEP_TARGET;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getContainer() {
		return container;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setContainer(String newContainer) {
		String oldContainer = container;
		container = newContainer;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.STEP_TARGET__CONTAINER,
					oldContainer, container));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TARGET_COMMANDS getCommands() {
		return commands;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCommands(TARGET_COMMANDS newCommands) {
		TARGET_COMMANDS oldCommands = commands;
		commands = newCommands == null ? COMMANDS_EDEFAULT : newCommands;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.STEP_TARGET__COMMANDS,
					oldCommands, commands));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SettableVariables getSettableVariables() {
		return settableVariables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSettableVariables(SettableVariables newSettableVariables, NotificationChain msgs) {
		SettableVariables oldSettableVariables = settableVariables;
		settableVariables = newSettableVariables;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					AzuredevopsMMPackage.STEP_TARGET__SETTABLE_VARIABLES, oldSettableVariables, newSettableVariables);
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
	public void setSettableVariables(SettableVariables newSettableVariables) {
		if (newSettableVariables != settableVariables) {
			NotificationChain msgs = null;
			if (settableVariables != null)
				msgs = ((InternalEObject) settableVariables).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.STEP_TARGET__SETTABLE_VARIABLES, null, msgs);
			if (newSettableVariables != null)
				msgs = ((InternalEObject) newSettableVariables).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - AzuredevopsMMPackage.STEP_TARGET__SETTABLE_VARIABLES, null, msgs);
			msgs = basicSetSettableVariables(newSettableVariables, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.STEP_TARGET__SETTABLE_VARIABLES,
					newSettableVariables, newSettableVariables));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case AzuredevopsMMPackage.STEP_TARGET__SETTABLE_VARIABLES:
			return basicSetSettableVariables(null, msgs);
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
		case AzuredevopsMMPackage.STEP_TARGET__CONTAINER:
			return getContainer();
		case AzuredevopsMMPackage.STEP_TARGET__COMMANDS:
			return getCommands();
		case AzuredevopsMMPackage.STEP_TARGET__SETTABLE_VARIABLES:
			return getSettableVariables();
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
		case AzuredevopsMMPackage.STEP_TARGET__CONTAINER:
			setContainer((String) newValue);
			return;
		case AzuredevopsMMPackage.STEP_TARGET__COMMANDS:
			setCommands((TARGET_COMMANDS) newValue);
			return;
		case AzuredevopsMMPackage.STEP_TARGET__SETTABLE_VARIABLES:
			setSettableVariables((SettableVariables) newValue);
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
		case AzuredevopsMMPackage.STEP_TARGET__CONTAINER:
			setContainer(CONTAINER_EDEFAULT);
			return;
		case AzuredevopsMMPackage.STEP_TARGET__COMMANDS:
			setCommands(COMMANDS_EDEFAULT);
			return;
		case AzuredevopsMMPackage.STEP_TARGET__SETTABLE_VARIABLES:
			setSettableVariables((SettableVariables) null);
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
		case AzuredevopsMMPackage.STEP_TARGET__CONTAINER:
			return CONTAINER_EDEFAULT == null ? container != null : !CONTAINER_EDEFAULT.equals(container);
		case AzuredevopsMMPackage.STEP_TARGET__COMMANDS:
			return commands != COMMANDS_EDEFAULT;
		case AzuredevopsMMPackage.STEP_TARGET__SETTABLE_VARIABLES:
			return settableVariables != null;
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
		result.append(" (container: ");
		result.append(container);
		result.append(", commands: ");
		result.append(commands);
		result.append(')');
		return result.toString();
	}

} //StepTargetImpl
