/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cron Schedule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CronSchedule#getCron <em>Cron</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CronSchedule#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CronSchedule#getBatch <em>Batch</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CronSchedule#getAlways <em>Always</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CronSchedule#getBranches <em>Branches</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getCronSchedule()
 * @model
 * @generated
 */
public interface CronSchedule extends EObject {
	/**
	 * Returns the value of the '<em><b>Cron</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cron</em>' attribute.
	 * @see #setCron(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getCronSchedule_Cron()
	 * @model required="true"
	 * @generated
	 */
	String getCron();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CronSchedule#getCron <em>Cron</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cron</em>' attribute.
	 * @see #getCron()
	 * @generated
	 */
	void setCron(String value);

	/**
	 * Returns the value of the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Display Name</em>' attribute.
	 * @see #setDisplayName(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getCronSchedule_DisplayName()
	 * @model
	 * @generated
	 */
	String getDisplayName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CronSchedule#getDisplayName <em>Display Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Display Name</em>' attribute.
	 * @see #getDisplayName()
	 * @generated
	 */
	void setDisplayName(String value);

	/**
	 * Returns the value of the '<em><b>Batch</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Batch</em>' attribute.
	 * @see #setBatch(Boolean)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getCronSchedule_Batch()
	 * @model
	 * @generated
	 */
	Boolean getBatch();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CronSchedule#getBatch <em>Batch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Batch</em>' attribute.
	 * @see #getBatch()
	 * @generated
	 */
	void setBatch(Boolean value);

	/**
	 * Returns the value of the '<em><b>Always</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Always</em>' attribute.
	 * @see #setAlways(Boolean)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getCronSchedule_Always()
	 * @model
	 * @generated
	 */
	Boolean getAlways();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CronSchedule#getAlways <em>Always</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Always</em>' attribute.
	 * @see #getAlways()
	 * @generated
	 */
	void setAlways(Boolean value);

	/**
	 * Returns the value of the '<em><b>Branches</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Branches</em>' containment reference.
	 * @see #setBranches(IncludeExcludeFilters)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getCronSchedule_Branches()
	 * @model containment="true"
	 * @generated
	 */
	IncludeExcludeFilters getBranches();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CronSchedule#getBranches <em>Branches</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Branches</em>' containment reference.
	 * @see #getBranches()
	 * @generated
	 */
	void setBranches(IncludeExcludeFilters value);

} // CronSchedule
