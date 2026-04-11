/**
 */
package com.mddoai.metamodel.bambooMM;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Scheduled Deployment Trigger</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.ScheduledDeploymentTrigger#getCronExpression <em>Cron Expression</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.ScheduledDeploymentTrigger#getArtifactBranch <em>Artifact Branch</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getScheduledDeploymentTrigger()
 * @model
 * @generated
 */
public interface ScheduledDeploymentTrigger extends Trigger {
	/**
	 * Returns the value of the '<em><b>Cron Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cron Expression</em>' attribute.
	 * @see #setCronExpression(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getScheduledDeploymentTrigger_CronExpression()
	 * @model required="true"
	 * @generated
	 */
	String getCronExpression();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.ScheduledDeploymentTrigger#getCronExpression <em>Cron Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cron Expression</em>' attribute.
	 * @see #getCronExpression()
	 * @generated
	 */
	void setCronExpression(String value);

	/**
	 * Returns the value of the '<em><b>Artifact Branch</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Artifact Branch</em>' attribute.
	 * @see #setArtifactBranch(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getScheduledDeploymentTrigger_ArtifactBranch()
	 * @model
	 * @generated
	 */
	String getArtifactBranch();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.ScheduledDeploymentTrigger#getArtifactBranch <em>Artifact Branch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Artifact Branch</em>' attribute.
	 * @see #getArtifactBranch()
	 * @generated
	 */
	void setArtifactBranch(String value);

} // ScheduledDeploymentTrigger
