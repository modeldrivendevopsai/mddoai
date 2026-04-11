/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pipeline</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getAppendCommitMessageToRunName <em>Append Commit Message To Run Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getLockBehavior <em>Lock Behavior</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getTrigger <em>Trigger</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getPr <em>Pr</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getSchedules <em>Schedules</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getParameters <em>Parameters</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getVariables <em>Variables</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getPool <em>Pool</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getResources <em>Resources</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getStages <em>Stages</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getJobs <em>Jobs</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getSteps <em>Steps</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getExtends <em>Extends</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getWorkspace <em>Workspace</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPipeline()
 * @model
 * @generated
 */
public interface Pipeline extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPipeline_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Append Commit Message To Run Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Append Commit Message To Run Name</em>' attribute.
	 * @see #setAppendCommitMessageToRunName(Boolean)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPipeline_AppendCommitMessageToRunName()
	 * @model
	 * @generated
	 */
	Boolean getAppendCommitMessageToRunName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getAppendCommitMessageToRunName <em>Append Commit Message To Run Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Append Commit Message To Run Name</em>' attribute.
	 * @see #getAppendCommitMessageToRunName()
	 * @generated
	 */
	void setAppendCommitMessageToRunName(Boolean value);

	/**
	 * Returns the value of the '<em><b>Lock Behavior</b></em>' attribute.
	 * The literals are from the enumeration {@link com.mddoai.metamodel.azuredevops.azuredevopsMM.LOCK_BEHAVIOR}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lock Behavior</em>' attribute.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.LOCK_BEHAVIOR
	 * @see #setLockBehavior(LOCK_BEHAVIOR)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPipeline_LockBehavior()
	 * @model
	 * @generated
	 */
	LOCK_BEHAVIOR getLockBehavior();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getLockBehavior <em>Lock Behavior</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lock Behavior</em>' attribute.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.LOCK_BEHAVIOR
	 * @see #getLockBehavior()
	 * @generated
	 */
	void setLockBehavior(LOCK_BEHAVIOR value);

	/**
	 * Returns the value of the '<em><b>Trigger</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trigger</em>' containment reference.
	 * @see #setTrigger(Trigger)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPipeline_Trigger()
	 * @model containment="true"
	 * @generated
	 */
	Trigger getTrigger();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getTrigger <em>Trigger</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trigger</em>' containment reference.
	 * @see #getTrigger()
	 * @generated
	 */
	void setTrigger(Trigger value);

	/**
	 * Returns the value of the '<em><b>Pr</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pr</em>' containment reference.
	 * @see #setPr(PrTrigger)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPipeline_Pr()
	 * @model containment="true"
	 * @generated
	 */
	PrTrigger getPr();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getPr <em>Pr</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pr</em>' containment reference.
	 * @see #getPr()
	 * @generated
	 */
	void setPr(PrTrigger value);

	/**
	 * Returns the value of the '<em><b>Schedules</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CronSchedule}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Schedules</em>' containment reference list.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPipeline_Schedules()
	 * @model containment="true"
	 * @generated
	 */
	EList<CronSchedule> getSchedules();

	/**
	 * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Parameter}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameters</em>' containment reference list.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPipeline_Parameters()
	 * @model containment="true"
	 * @generated
	 */
	EList<Parameter> getParameters();

	/**
	 * Returns the value of the '<em><b>Variables</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableDefinition}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variables</em>' containment reference list.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPipeline_Variables()
	 * @model containment="true"
	 * @generated
	 */
	EList<VariableDefinition> getVariables();

	/**
	 * Returns the value of the '<em><b>Pool</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pool</em>' containment reference.
	 * @see #setPool(Pool)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPipeline_Pool()
	 * @model containment="true"
	 * @generated
	 */
	Pool getPool();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getPool <em>Pool</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pool</em>' containment reference.
	 * @see #getPool()
	 * @generated
	 */
	void setPool(Pool value);

	/**
	 * Returns the value of the '<em><b>Resources</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resources</em>' containment reference.
	 * @see #setResources(Resources)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPipeline_Resources()
	 * @model containment="true"
	 * @generated
	 */
	Resources getResources();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getResources <em>Resources</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resources</em>' containment reference.
	 * @see #getResources()
	 * @generated
	 */
	void setResources(Resources value);

	/**
	 * Returns the value of the '<em><b>Stages</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.azuredevops.azuredevopsMM.StageElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stages</em>' containment reference list.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPipeline_Stages()
	 * @model containment="true"
	 * @generated
	 */
	EList<StageElement> getStages();

	/**
	 * Returns the value of the '<em><b>Jobs</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.azuredevops.azuredevopsMM.JobElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Jobs</em>' containment reference list.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPipeline_Jobs()
	 * @model containment="true"
	 * @generated
	 */
	EList<JobElement> getJobs();

	/**
	 * Returns the value of the '<em><b>Steps</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Step}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Steps</em>' containment reference list.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPipeline_Steps()
	 * @model containment="true"
	 * @generated
	 */
	EList<Step> getSteps();

	/**
	 * Returns the value of the '<em><b>Extends</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extends</em>' containment reference.
	 * @see #setExtends(Extends)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPipeline_Extends()
	 * @model containment="true"
	 * @generated
	 */
	Extends getExtends();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getExtends <em>Extends</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extends</em>' containment reference.
	 * @see #getExtends()
	 * @generated
	 */
	void setExtends(Extends value);

	/**
	 * Returns the value of the '<em><b>Workspace</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Workspace</em>' containment reference.
	 * @see #setWorkspace(Workspace)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPipeline_Workspace()
	 * @model containment="true"
	 * @generated
	 */
	Workspace getWorkspace();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline#getWorkspace <em>Workspace</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Workspace</em>' containment reference.
	 * @see #getWorkspace()
	 * @generated
	 */
	void setWorkspace(Workspace value);

} // Pipeline
