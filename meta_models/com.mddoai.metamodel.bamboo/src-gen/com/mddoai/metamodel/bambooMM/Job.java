/**
 */
package com.mddoai.metamodel.bambooMM;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Job</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Job#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Job#getKey <em>Key</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Job#getTasks <em>Tasks</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Job#getFinalTasks <em>Final Tasks</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Job#getArtifacts <em>Artifacts</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Job#getArtifactSubscriptions <em>Artifact Subscriptions</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Job#getRequirements <em>Requirements</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Job#getVariables <em>Variables</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Job#getDocker <em>Docker</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Job#getOther <em>Other</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getJob()
 * @model
 * @generated
 */
public interface Job extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getJob_Name()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.Job#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Key</em>' attribute.
	 * @see #setKey(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getJob_Key()
	 * @model
	 * @generated
	 */
	String getKey();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.Job#getKey <em>Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Key</em>' attribute.
	 * @see #getKey()
	 * @generated
	 */
	void setKey(String value);

	/**
	 * Returns the value of the '<em><b>Tasks</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.bambooMM.Task}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tasks</em>' containment reference list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getJob_Tasks()
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
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getJob_FinalTasks()
	 * @model containment="true"
	 * @generated
	 */
	EList<Task> getFinalTasks();

	/**
	 * Returns the value of the '<em><b>Artifacts</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.bambooMM.Artifact}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Artifacts</em>' containment reference list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getJob_Artifacts()
	 * @model containment="true"
	 * @generated
	 */
	EList<Artifact> getArtifacts();

	/**
	 * Returns the value of the '<em><b>Artifact Subscriptions</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.bambooMM.ArtifactSubscription}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Artifact Subscriptions</em>' containment reference list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getJob_ArtifactSubscriptions()
	 * @model containment="true"
	 * @generated
	 */
	EList<ArtifactSubscription> getArtifactSubscriptions();

	/**
	 * Returns the value of the '<em><b>Requirements</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.bambooMM.Requirement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Requirements</em>' containment reference list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getJob_Requirements()
	 * @model containment="true"
	 * @generated
	 */
	EList<Requirement> getRequirements();

	/**
	 * Returns the value of the '<em><b>Variables</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variables</em>' map.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getJob_Variables()
	 * @model mapType="com.mddoai.metamodel.bambooMM.VariableAssignment&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;"
	 * @generated
	 */
	EMap<String, String> getVariables();

	/**
	 * Returns the value of the '<em><b>Docker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Docker</em>' containment reference.
	 * @see #setDocker(DockerConfig)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getJob_Docker()
	 * @model containment="true"
	 * @generated
	 */
	DockerConfig getDocker();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.Job#getDocker <em>Docker</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Docker</em>' containment reference.
	 * @see #getDocker()
	 * @generated
	 */
	void setDocker(DockerConfig value);

	/**
	 * Returns the value of the '<em><b>Other</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Other</em>' containment reference.
	 * @see #setOther(OtherConfig)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getJob_Other()
	 * @model containment="true"
	 * @generated
	 */
	OtherConfig getOther();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.Job#getOther <em>Other</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Other</em>' containment reference.
	 * @see #getOther()
	 * @generated
	 */
	void setOther(OtherConfig value);

} // Job
