/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resources</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Resources#getBuilds <em>Builds</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Resources#getContainers <em>Containers</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Resources#getPipelines <em>Pipelines</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Resources#getRepositories <em>Repositories</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Resources#getWebhooks <em>Webhooks</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Resources#getPackages <em>Packages</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getResources()
 * @model
 * @generated
 */
public interface Resources extends EObject {
	/**
	 * Returns the value of the '<em><b>Builds</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.azuredevops.azuredevopsMM.BuildResource}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Builds</em>' containment reference list.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getResources_Builds()
	 * @model containment="true"
	 * @generated
	 */
	EList<BuildResource> getBuilds();

	/**
	 * Returns the value of the '<em><b>Containers</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Containers</em>' containment reference list.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getResources_Containers()
	 * @model containment="true"
	 * @generated
	 */
	EList<ContainerResource> getContainers();

	/**
	 * Returns the value of the '<em><b>Pipelines</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResource}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pipelines</em>' containment reference list.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getResources_Pipelines()
	 * @model containment="true"
	 * @generated
	 */
	EList<PipelineResource> getPipelines();

	/**
	 * Returns the value of the '<em><b>Repositories</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.azuredevops.azuredevopsMM.RepositoryResource}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Repositories</em>' containment reference list.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getResources_Repositories()
	 * @model containment="true"
	 * @generated
	 */
	EList<RepositoryResource> getRepositories();

	/**
	 * Returns the value of the '<em><b>Webhooks</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.azuredevops.azuredevopsMM.WebhookResource}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Webhooks</em>' containment reference list.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getResources_Webhooks()
	 * @model containment="true"
	 * @generated
	 */
	EList<WebhookResource> getWebhooks();

	/**
	 * Returns the value of the '<em><b>Packages</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PackageResource}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Packages</em>' containment reference list.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getResources_Packages()
	 * @model containment="true"
	 * @generated
	 */
	EList<PackageResource> getPackages();

} // Resources
