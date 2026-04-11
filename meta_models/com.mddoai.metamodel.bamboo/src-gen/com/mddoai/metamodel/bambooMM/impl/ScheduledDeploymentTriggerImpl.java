/**
 */
package com.mddoai.metamodel.bambooMM.impl;

import com.mddoai.metamodel.bambooMM.BambooPackage;
import com.mddoai.metamodel.bambooMM.ScheduledDeploymentTrigger;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Scheduled Deployment Trigger</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.ScheduledDeploymentTriggerImpl#getCronExpression <em>Cron Expression</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.ScheduledDeploymentTriggerImpl#getArtifactBranch <em>Artifact Branch</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ScheduledDeploymentTriggerImpl extends TriggerImpl implements ScheduledDeploymentTrigger {
	/**
	 * The default value of the '{@link #getCronExpression() <em>Cron Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCronExpression()
	 * @generated
	 * @ordered
	 */
	protected static final String CRON_EXPRESSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCronExpression() <em>Cron Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCronExpression()
	 * @generated
	 * @ordered
	 */
	protected String cronExpression = CRON_EXPRESSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getArtifactBranch() <em>Artifact Branch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArtifactBranch()
	 * @generated
	 * @ordered
	 */
	protected static final String ARTIFACT_BRANCH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getArtifactBranch() <em>Artifact Branch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArtifactBranch()
	 * @generated
	 * @ordered
	 */
	protected String artifactBranch = ARTIFACT_BRANCH_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScheduledDeploymentTriggerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BambooPackage.Literals.SCHEDULED_DEPLOYMENT_TRIGGER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCronExpression() {
		return cronExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCronExpression(String newCronExpression) {
		String oldCronExpression = cronExpression;
		cronExpression = newCronExpression;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					BambooPackage.SCHEDULED_DEPLOYMENT_TRIGGER__CRON_EXPRESSION, oldCronExpression, cronExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getArtifactBranch() {
		return artifactBranch;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setArtifactBranch(String newArtifactBranch) {
		String oldArtifactBranch = artifactBranch;
		artifactBranch = newArtifactBranch;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					BambooPackage.SCHEDULED_DEPLOYMENT_TRIGGER__ARTIFACT_BRANCH, oldArtifactBranch, artifactBranch));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case BambooPackage.SCHEDULED_DEPLOYMENT_TRIGGER__CRON_EXPRESSION:
			return getCronExpression();
		case BambooPackage.SCHEDULED_DEPLOYMENT_TRIGGER__ARTIFACT_BRANCH:
			return getArtifactBranch();
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
		case BambooPackage.SCHEDULED_DEPLOYMENT_TRIGGER__CRON_EXPRESSION:
			setCronExpression((String) newValue);
			return;
		case BambooPackage.SCHEDULED_DEPLOYMENT_TRIGGER__ARTIFACT_BRANCH:
			setArtifactBranch((String) newValue);
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
		case BambooPackage.SCHEDULED_DEPLOYMENT_TRIGGER__CRON_EXPRESSION:
			setCronExpression(CRON_EXPRESSION_EDEFAULT);
			return;
		case BambooPackage.SCHEDULED_DEPLOYMENT_TRIGGER__ARTIFACT_BRANCH:
			setArtifactBranch(ARTIFACT_BRANCH_EDEFAULT);
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
		case BambooPackage.SCHEDULED_DEPLOYMENT_TRIGGER__CRON_EXPRESSION:
			return CRON_EXPRESSION_EDEFAULT == null ? cronExpression != null
					: !CRON_EXPRESSION_EDEFAULT.equals(cronExpression);
		case BambooPackage.SCHEDULED_DEPLOYMENT_TRIGGER__ARTIFACT_BRANCH:
			return ARTIFACT_BRANCH_EDEFAULT == null ? artifactBranch != null
					: !ARTIFACT_BRANCH_EDEFAULT.equals(artifactBranch);
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
		result.append(" (cronExpression: ");
		result.append(cronExpression);
		result.append(", artifactBranch: ");
		result.append(artifactBranch);
		result.append(')');
		return result.toString();
	}

} //ScheduledDeploymentTriggerImpl
