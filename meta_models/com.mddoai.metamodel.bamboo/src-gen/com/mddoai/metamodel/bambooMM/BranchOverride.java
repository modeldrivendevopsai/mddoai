/**
 */
package com.mddoai.metamodel.bambooMM;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Branch Override</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.BranchOverride#getBranchPattern <em>Branch Pattern</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.BranchOverride#getStages <em>Stages</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.BranchOverride#getJobs <em>Jobs</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.BranchOverride#getTriggers <em>Triggers</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.BranchOverride#getNotifications <em>Notifications</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.BranchOverride#getVariables <em>Variables</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.BranchOverride#getLabels <em>Labels</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.BranchOverride#getDocker <em>Docker</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.BranchOverride#getBranchConfig <em>Branch Config</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBranchOverride()
 * @model
 * @generated
 */
public interface BranchOverride extends EObject {
	/**
	 * Returns the value of the '<em><b>Branch Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Branch Pattern</em>' attribute.
	 * @see #setBranchPattern(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBranchOverride_BranchPattern()
	 * @model required="true"
	 * @generated
	 */
	String getBranchPattern();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.BranchOverride#getBranchPattern <em>Branch Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Branch Pattern</em>' attribute.
	 * @see #getBranchPattern()
	 * @generated
	 */
	void setBranchPattern(String value);

	/**
	 * Returns the value of the '<em><b>Stages</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.bambooMM.Stage}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stages</em>' containment reference list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBranchOverride_Stages()
	 * @model containment="true"
	 * @generated
	 */
	EList<Stage> getStages();

	/**
	 * Returns the value of the '<em><b>Jobs</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.bambooMM.Job}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Jobs</em>' containment reference list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBranchOverride_Jobs()
	 * @model containment="true"
	 * @generated
	 */
	EList<Job> getJobs();

	/**
	 * Returns the value of the '<em><b>Triggers</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.bambooMM.Trigger}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Triggers</em>' containment reference list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBranchOverride_Triggers()
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
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBranchOverride_Notifications()
	 * @model containment="true"
	 * @generated
	 */
	EList<Notification> getNotifications();

	/**
	 * Returns the value of the '<em><b>Variables</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variables</em>' map.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBranchOverride_Variables()
	 * @model mapType="com.mddoai.metamodel.bambooMM.VariableAssignment&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;"
	 * @generated
	 */
	EMap<String, String> getVariables();

	/**
	 * Returns the value of the '<em><b>Labels</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.bambooMM.Label}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Labels</em>' containment reference list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBranchOverride_Labels()
	 * @model containment="true"
	 * @generated
	 */
	EList<Label> getLabels();

	/**
	 * Returns the value of the '<em><b>Docker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Docker</em>' containment reference.
	 * @see #setDocker(DockerConfig)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBranchOverride_Docker()
	 * @model containment="true"
	 * @generated
	 */
	DockerConfig getDocker();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.BranchOverride#getDocker <em>Docker</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Docker</em>' containment reference.
	 * @see #getDocker()
	 * @generated
	 */
	void setDocker(DockerConfig value);

	/**
	 * Returns the value of the '<em><b>Branch Config</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Branch Config</em>' containment reference.
	 * @see #setBranchConfig(BranchSpecificConfig)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBranchOverride_BranchConfig()
	 * @model containment="true"
	 * @generated
	 */
	BranchSpecificConfig getBranchConfig();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.BranchOverride#getBranchConfig <em>Branch Config</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Branch Config</em>' containment reference.
	 * @see #getBranchConfig()
	 * @generated
	 */
	void setBranchConfig(BranchSpecificConfig value);

} // BranchOverride
