/**
 */
package com.mddoai.metamodel.bambooMM.impl;

import com.mddoai.metamodel.bambooMM.AfterDeploymentTrigger;
import com.mddoai.metamodel.bambooMM.BambooPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>After Deployment Trigger</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.AfterDeploymentTriggerImpl#getDeploymentProject <em>Deployment Project</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.AfterDeploymentTriggerImpl#getEnvironment <em>Environment</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AfterDeploymentTriggerImpl extends TriggerImpl implements AfterDeploymentTrigger {
	/**
	 * The default value of the '{@link #getDeploymentProject() <em>Deployment Project</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeploymentProject()
	 * @generated
	 * @ordered
	 */
	protected static final String DEPLOYMENT_PROJECT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDeploymentProject() <em>Deployment Project</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeploymentProject()
	 * @generated
	 * @ordered
	 */
	protected String deploymentProject = DEPLOYMENT_PROJECT_EDEFAULT;

	/**
	 * The default value of the '{@link #getEnvironment() <em>Environment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnvironment()
	 * @generated
	 * @ordered
	 */
	protected static final String ENVIRONMENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEnvironment() <em>Environment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnvironment()
	 * @generated
	 * @ordered
	 */
	protected String environment = ENVIRONMENT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AfterDeploymentTriggerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BambooPackage.Literals.AFTER_DEPLOYMENT_TRIGGER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDeploymentProject() {
		return deploymentProject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDeploymentProject(String newDeploymentProject) {
		String oldDeploymentProject = deploymentProject;
		deploymentProject = newDeploymentProject;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					BambooPackage.AFTER_DEPLOYMENT_TRIGGER__DEPLOYMENT_PROJECT, oldDeploymentProject,
					deploymentProject));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getEnvironment() {
		return environment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEnvironment(String newEnvironment) {
		String oldEnvironment = environment;
		environment = newEnvironment;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.AFTER_DEPLOYMENT_TRIGGER__ENVIRONMENT,
					oldEnvironment, environment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case BambooPackage.AFTER_DEPLOYMENT_TRIGGER__DEPLOYMENT_PROJECT:
			return getDeploymentProject();
		case BambooPackage.AFTER_DEPLOYMENT_TRIGGER__ENVIRONMENT:
			return getEnvironment();
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
		case BambooPackage.AFTER_DEPLOYMENT_TRIGGER__DEPLOYMENT_PROJECT:
			setDeploymentProject((String) newValue);
			return;
		case BambooPackage.AFTER_DEPLOYMENT_TRIGGER__ENVIRONMENT:
			setEnvironment((String) newValue);
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
		case BambooPackage.AFTER_DEPLOYMENT_TRIGGER__DEPLOYMENT_PROJECT:
			setDeploymentProject(DEPLOYMENT_PROJECT_EDEFAULT);
			return;
		case BambooPackage.AFTER_DEPLOYMENT_TRIGGER__ENVIRONMENT:
			setEnvironment(ENVIRONMENT_EDEFAULT);
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
		case BambooPackage.AFTER_DEPLOYMENT_TRIGGER__DEPLOYMENT_PROJECT:
			return DEPLOYMENT_PROJECT_EDEFAULT == null ? deploymentProject != null
					: !DEPLOYMENT_PROJECT_EDEFAULT.equals(deploymentProject);
		case BambooPackage.AFTER_DEPLOYMENT_TRIGGER__ENVIRONMENT:
			return ENVIRONMENT_EDEFAULT == null ? environment != null : !ENVIRONMENT_EDEFAULT.equals(environment);
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
		result.append(" (deploymentProject: ");
		result.append(deploymentProject);
		result.append(", environment: ");
		result.append(environment);
		result.append(')');
		return result.toString();
	}

} //AfterDeploymentTriggerImpl
