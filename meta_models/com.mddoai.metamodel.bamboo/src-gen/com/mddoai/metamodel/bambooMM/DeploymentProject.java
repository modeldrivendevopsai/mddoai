/**
 */
package com.mddoai.metamodel.bambooMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Deployment Project</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.DeploymentProject#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.DeploymentProject#getSourcePlan <em>Source Plan</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.DeploymentProject#getServerName <em>Server Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.DeploymentProject#getReleaseNaming <em>Release Naming</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.DeploymentProject#getEnvironments <em>Environments</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.DeploymentProject#getPermissions <em>Permissions</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.DeploymentProject#getDefaultEnvironmentPermissions <em>Default Environment Permissions</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.DeploymentProject#getEnvironmentPermissions <em>Environment Permissions</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getDeploymentProject()
 * @model
 * @generated
 */
public interface DeploymentProject extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getDeploymentProject_Name()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.DeploymentProject#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Source Plan</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source Plan</em>' attribute.
	 * @see #setSourcePlan(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getDeploymentProject_SourcePlan()
	 * @model required="true"
	 * @generated
	 */
	String getSourcePlan();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.DeploymentProject#getSourcePlan <em>Source Plan</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Plan</em>' attribute.
	 * @see #getSourcePlan()
	 * @generated
	 */
	void setSourcePlan(String value);

	/**
	 * Returns the value of the '<em><b>Server Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Server Name</em>' attribute.
	 * @see #setServerName(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getDeploymentProject_ServerName()
	 * @model
	 * @generated
	 */
	String getServerName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.DeploymentProject#getServerName <em>Server Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Server Name</em>' attribute.
	 * @see #getServerName()
	 * @generated
	 */
	void setServerName(String value);

	/**
	 * Returns the value of the '<em><b>Release Naming</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Release Naming</em>' containment reference.
	 * @see #setReleaseNaming(ReleaseNaming)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getDeploymentProject_ReleaseNaming()
	 * @model containment="true"
	 * @generated
	 */
	ReleaseNaming getReleaseNaming();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.DeploymentProject#getReleaseNaming <em>Release Naming</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Release Naming</em>' containment reference.
	 * @see #getReleaseNaming()
	 * @generated
	 */
	void setReleaseNaming(ReleaseNaming value);

	/**
	 * Returns the value of the '<em><b>Environments</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.bambooMM.Environment}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Environments</em>' containment reference list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getDeploymentProject_Environments()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Environment> getEnvironments();

	/**
	 * Returns the value of the '<em><b>Permissions</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.bambooMM.DeploymentPermissionEntry}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Permissions</em>' containment reference list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getDeploymentProject_Permissions()
	 * @model containment="true"
	 * @generated
	 */
	EList<DeploymentPermissionEntry> getPermissions();

	/**
	 * Returns the value of the '<em><b>Default Environment Permissions</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.bambooMM.EnvironmentPermissionEntry}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Environment Permissions</em>' containment reference list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getDeploymentProject_DefaultEnvironmentPermissions()
	 * @model containment="true"
	 * @generated
	 */
	EList<EnvironmentPermissionEntry> getDefaultEnvironmentPermissions();

	/**
	 * Returns the value of the '<em><b>Environment Permissions</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.bambooMM.NamedEnvironmentPermission}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Environment Permissions</em>' containment reference list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getDeploymentProject_EnvironmentPermissions()
	 * @model containment="true"
	 * @generated
	 */
	EList<NamedEnvironmentPermission> getEnvironmentPermissions();

} // DeploymentProject
