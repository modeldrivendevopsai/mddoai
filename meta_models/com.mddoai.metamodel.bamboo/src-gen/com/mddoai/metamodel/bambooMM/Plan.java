/**
 */
package com.mddoai.metamodel.bambooMM;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Plan</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Plan#getProjectKey <em>Project Key</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Plan#getKey <em>Key</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Plan#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Plan#getServerName <em>Server Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Plan#getStages <em>Stages</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Plan#getJobs <em>Jobs</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Plan#getTriggers <em>Triggers</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Plan#getRepositories <em>Repositories</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Plan#getVariables <em>Variables</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Plan#getNotifications <em>Notifications</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Plan#getLabels <em>Labels</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Plan#getBranches <em>Branches</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Plan#getDependencies <em>Dependencies</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Plan#getDocker <em>Docker</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Plan#getBranchOverrides <em>Branch Overrides</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Plan#getOther <em>Other</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Plan#getPermissions <em>Permissions</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getPlan()
 * @model
 * @generated
 */
public interface Plan extends EObject {
	/**
	 * Returns the value of the '<em><b>Project Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Project Key</em>' attribute.
	 * @see #setProjectKey(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getPlan_ProjectKey()
	 * @model required="true"
	 * @generated
	 */
	String getProjectKey();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.Plan#getProjectKey <em>Project Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Project Key</em>' attribute.
	 * @see #getProjectKey()
	 * @generated
	 */
	void setProjectKey(String value);

	/**
	 * Returns the value of the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Key</em>' attribute.
	 * @see #setKey(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getPlan_Key()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getKey();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.Plan#getKey <em>Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Key</em>' attribute.
	 * @see #getKey()
	 * @generated
	 */
	void setKey(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getPlan_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.Plan#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Server Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Server Name</em>' attribute.
	 * @see #setServerName(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getPlan_ServerName()
	 * @model
	 * @generated
	 */
	String getServerName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.Plan#getServerName <em>Server Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Server Name</em>' attribute.
	 * @see #getServerName()
	 * @generated
	 */
	void setServerName(String value);

	/**
	 * Returns the value of the '<em><b>Stages</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.bambooMM.Stage}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stages</em>' containment reference list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getPlan_Stages()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Stage> getStages();

	/**
	 * Returns the value of the '<em><b>Jobs</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.bambooMM.Job}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Jobs</em>' containment reference list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getPlan_Jobs()
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
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getPlan_Triggers()
	 * @model containment="true"
	 * @generated
	 */
	EList<Trigger> getTriggers();

	/**
	 * Returns the value of the '<em><b>Repositories</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.bambooMM.Repository}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Repositories</em>' containment reference list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getPlan_Repositories()
	 * @model containment="true"
	 * @generated
	 */
	EList<Repository> getRepositories();

	/**
	 * Returns the value of the '<em><b>Variables</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variables</em>' map.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getPlan_Variables()
	 * @model mapType="com.mddoai.metamodel.bambooMM.VariableAssignment&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;"
	 * @generated
	 */
	EMap<String, String> getVariables();

	/**
	 * Returns the value of the '<em><b>Notifications</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.bambooMM.Notification}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Notifications</em>' containment reference list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getPlan_Notifications()
	 * @model containment="true"
	 * @generated
	 */
	EList<Notification> getNotifications();

	/**
	 * Returns the value of the '<em><b>Labels</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.bambooMM.Label}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Labels</em>' containment reference list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getPlan_Labels()
	 * @model containment="true"
	 * @generated
	 */
	EList<Label> getLabels();

	/**
	 * Returns the value of the '<em><b>Branches</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Branches</em>' containment reference.
	 * @see #setBranches(BranchManagement)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getPlan_Branches()
	 * @model containment="true"
	 * @generated
	 */
	BranchManagement getBranches();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.Plan#getBranches <em>Branches</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Branches</em>' containment reference.
	 * @see #getBranches()
	 * @generated
	 */
	void setBranches(BranchManagement value);

	/**
	 * Returns the value of the '<em><b>Dependencies</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dependencies</em>' containment reference.
	 * @see #setDependencies(Dependencies)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getPlan_Dependencies()
	 * @model containment="true"
	 * @generated
	 */
	Dependencies getDependencies();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.Plan#getDependencies <em>Dependencies</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dependencies</em>' containment reference.
	 * @see #getDependencies()
	 * @generated
	 */
	void setDependencies(Dependencies value);

	/**
	 * Returns the value of the '<em><b>Docker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Docker</em>' containment reference.
	 * @see #setDocker(DockerConfig)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getPlan_Docker()
	 * @model containment="true"
	 * @generated
	 */
	DockerConfig getDocker();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.Plan#getDocker <em>Docker</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Docker</em>' containment reference.
	 * @see #getDocker()
	 * @generated
	 */
	void setDocker(DockerConfig value);

	/**
	 * Returns the value of the '<em><b>Branch Overrides</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.bambooMM.BranchOverride}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Branch Overrides</em>' containment reference list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getPlan_BranchOverrides()
	 * @model containment="true"
	 * @generated
	 */
	EList<BranchOverride> getBranchOverrides();

	/**
	 * Returns the value of the '<em><b>Other</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Other</em>' containment reference.
	 * @see #setOther(OtherConfig)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getPlan_Other()
	 * @model containment="true"
	 * @generated
	 */
	OtherConfig getOther();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.Plan#getOther <em>Other</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Other</em>' containment reference.
	 * @see #getOther()
	 * @generated
	 */
	void setOther(OtherConfig value);

	/**
	 * Returns the value of the '<em><b>Permissions</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.bambooMM.PlanPermissionEntry}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Permissions</em>' containment reference list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getPlan_Permissions()
	 * @model containment="true"
	 * @generated
	 */
	EList<PlanPermissionEntry> getPermissions();

} // Plan
