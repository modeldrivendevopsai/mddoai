/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stage</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getStage <em>Stage</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getCondition <em>Condition</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getLockBehavior <em>Lock Behavior</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getTrigger <em>Trigger</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getIsSkippable <em>Is Skippable</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getDependsOn <em>Depends On</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getPool <em>Pool</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getVariables <em>Variables</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getJobs <em>Jobs</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getTemplateContext <em>Template Context</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getStage()
 * @model
 * @generated
 */
public interface Stage extends StageElement {
	/**
	 * Returns the value of the '<em><b>Stage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stage</em>' attribute.
	 * @see #setStage(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getStage_Stage()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getStage();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getStage <em>Stage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stage</em>' attribute.
	 * @see #getStage()
	 * @generated
	 */
	void setStage(String value);

	/**
	 * Returns the value of the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Display Name</em>' attribute.
	 * @see #setDisplayName(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getStage_DisplayName()
	 * @model
	 * @generated
	 */
	String getDisplayName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getDisplayName <em>Display Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Display Name</em>' attribute.
	 * @see #getDisplayName()
	 * @generated
	 */
	void setDisplayName(String value);

	/**
	 * Returns the value of the '<em><b>Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition</em>' attribute.
	 * @see #setCondition(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getStage_Condition()
	 * @model
	 * @generated
	 */
	String getCondition();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getCondition <em>Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' attribute.
	 * @see #getCondition()
	 * @generated
	 */
	void setCondition(String value);

	/**
	 * Returns the value of the '<em><b>Lock Behavior</b></em>' attribute.
	 * The literals are from the enumeration {@link com.mddoai.metamodel.azuredevops.azuredevopsMM.LOCK_BEHAVIOR}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lock Behavior</em>' attribute.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.LOCK_BEHAVIOR
	 * @see #setLockBehavior(LOCK_BEHAVIOR)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getStage_LockBehavior()
	 * @model
	 * @generated
	 */
	LOCK_BEHAVIOR getLockBehavior();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getLockBehavior <em>Lock Behavior</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lock Behavior</em>' attribute.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.LOCK_BEHAVIOR
	 * @see #getLockBehavior()
	 * @generated
	 */
	void setLockBehavior(LOCK_BEHAVIOR value);

	/**
	 * Returns the value of the '<em><b>Trigger</b></em>' attribute.
	 * The literals are from the enumeration {@link com.mddoai.metamodel.azuredevops.azuredevopsMM.STAGE_TRIGGER}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trigger</em>' attribute.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.STAGE_TRIGGER
	 * @see #setTrigger(STAGE_TRIGGER)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getStage_Trigger()
	 * @model
	 * @generated
	 */
	STAGE_TRIGGER getTrigger();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getTrigger <em>Trigger</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trigger</em>' attribute.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.STAGE_TRIGGER
	 * @see #getTrigger()
	 * @generated
	 */
	void setTrigger(STAGE_TRIGGER value);

	/**
	 * Returns the value of the '<em><b>Is Skippable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Skippable</em>' attribute.
	 * @see #setIsSkippable(Boolean)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getStage_IsSkippable()
	 * @model
	 * @generated
	 */
	Boolean getIsSkippable();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getIsSkippable <em>Is Skippable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Skippable</em>' attribute.
	 * @see #getIsSkippable()
	 * @generated
	 */
	void setIsSkippable(Boolean value);

	/**
	 * Returns the value of the '<em><b>Depends On</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Depends On</em>' attribute list.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getStage_DependsOn()
	 * @model
	 * @generated
	 */
	EList<String> getDependsOn();

	/**
	 * Returns the value of the '<em><b>Pool</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pool</em>' containment reference.
	 * @see #setPool(Pool)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getStage_Pool()
	 * @model containment="true"
	 * @generated
	 */
	Pool getPool();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getPool <em>Pool</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pool</em>' containment reference.
	 * @see #getPool()
	 * @generated
	 */
	void setPool(Pool value);

	/**
	 * Returns the value of the '<em><b>Variables</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableDefinition}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variables</em>' containment reference list.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getStage_Variables()
	 * @model containment="true"
	 * @generated
	 */
	EList<VariableDefinition> getVariables();

	/**
	 * Returns the value of the '<em><b>Jobs</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.azuredevops.azuredevopsMM.JobElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Jobs</em>' containment reference list.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getStage_Jobs()
	 * @model containment="true"
	 * @generated
	 */
	EList<JobElement> getJobs();

	/**
	 * Returns the value of the '<em><b>Template Context</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Template Context</em>' containment reference.
	 * @see #setTemplateContext(TemplateContext)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getStage_TemplateContext()
	 * @model containment="true"
	 * @generated
	 */
	TemplateContext getTemplateContext();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage#getTemplateContext <em>Template Context</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Template Context</em>' containment reference.
	 * @see #getTemplateContext()
	 * @generated
	 */
	void setTemplateContext(TemplateContext value);

} // Stage
