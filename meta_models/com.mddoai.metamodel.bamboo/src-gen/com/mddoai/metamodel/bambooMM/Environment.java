/**
 */
package com.mddoai.metamodel.bambooMM;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Environment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Environment#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Environment#getTasks <em>Tasks</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Environment#getFinalTasks <em>Final Tasks</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Environment#getVariables <em>Variables</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Environment#getTriggers <em>Triggers</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Environment#getNotifications <em>Notifications</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Environment#getDocker <em>Docker</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Environment#getRequirements <em>Requirements</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Environment#getReleaseApprovalPrerequisite <em>Release Approval Prerequisite</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getEnvironment()
 * @model
 * @generated
 */
public interface Environment extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getEnvironment_Name()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.Environment#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Tasks</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.bambooMM.Task}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tasks</em>' containment reference list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getEnvironment_Tasks()
	 * @model containment="true"
	 * @generated
	 */
	EList<Task> getTasks();

	/**
	 * Returns the value of the '<em><b>Final Tasks</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.bambooMM.Task}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Final Tasks</em>' containment reference list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getEnvironment_FinalTasks()
	 * @model containment="true"
	 * @generated
	 */
	EList<Task> getFinalTasks();

	/**
	 * Returns the value of the '<em><b>Variables</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variables</em>' map.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getEnvironment_Variables()
	 * @model mapType="com.mddoai.metamodel.bambooMM.VariableAssignment&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;"
	 * @generated
	 */
	EMap<String, String> getVariables();

	/**
	 * Returns the value of the '<em><b>Triggers</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.bambooMM.Trigger}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Triggers</em>' containment reference list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getEnvironment_Triggers()
	 * @model containment="true"
	 * @generated
	 */
	EList<Trigger> getTriggers();

	/**
	 * Returns the value of the '<em><b>Notifications</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.bambooMM.Notification}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Notifications</em>' containment reference list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getEnvironment_Notifications()
	 * @model containment="true"
	 * @generated
	 */
	EList<Notification> getNotifications();

	/**
	 * Returns the value of the '<em><b>Docker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Docker</em>' containment reference.
	 * @see #setDocker(DockerConfig)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getEnvironment_Docker()
	 * @model containment="true"
	 * @generated
	 */
	DockerConfig getDocker();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.Environment#getDocker <em>Docker</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Docker</em>' containment reference.
	 * @see #getDocker()
	 * @generated
	 */
	void setDocker(DockerConfig value);

	/**
	 * Returns the value of the '<em><b>Requirements</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.bambooMM.Requirement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Requirements</em>' containment reference list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getEnvironment_Requirements()
	 * @model containment="true"
	 * @generated
	 */
	EList<Requirement> getRequirements();

	/**
	 * Returns the value of the '<em><b>Release Approval Prerequisite</b></em>' attribute.
	 * The literals are from the enumeration {@link com.mddoai.metamodel.bambooMM.RELEASE_APPROVAL}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Release Approval Prerequisite</em>' attribute.
	 * @see com.mddoai.metamodel.bambooMM.RELEASE_APPROVAL
	 * @see #setReleaseApprovalPrerequisite(RELEASE_APPROVAL)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getEnvironment_ReleaseApprovalPrerequisite()
	 * @model
	 * @generated
	 */
	RELEASE_APPROVAL getReleaseApprovalPrerequisite();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.Environment#getReleaseApprovalPrerequisite <em>Release Approval Prerequisite</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Release Approval Prerequisite</em>' attribute.
	 * @see com.mddoai.metamodel.bambooMM.RELEASE_APPROVAL
	 * @see #getReleaseApprovalPrerequisite()
	 * @generated
	 */
	void setReleaseApprovalPrerequisite(RELEASE_APPROVAL value);

} // Environment
