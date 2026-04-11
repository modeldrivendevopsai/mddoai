/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pipeline Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResource#getPipeline <em>Pipeline</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResource#getProject <em>Project</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResource#getSource <em>Source</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResource#getVersion <em>Version</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResource#getBranch <em>Branch</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResource#getTags <em>Tags</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResource#getTrigger <em>Trigger</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPipelineResource()
 * @model
 * @generated
 */
public interface PipelineResource extends EObject {
	/**
	 * Returns the value of the '<em><b>Pipeline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pipeline</em>' attribute.
	 * @see #setPipeline(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPipelineResource_Pipeline()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getPipeline();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResource#getPipeline <em>Pipeline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pipeline</em>' attribute.
	 * @see #getPipeline()
	 * @generated
	 */
	void setPipeline(String value);

	/**
	 * Returns the value of the '<em><b>Project</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Project</em>' attribute.
	 * @see #setProject(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPipelineResource_Project()
	 * @model
	 * @generated
	 */
	String getProject();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResource#getProject <em>Project</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Project</em>' attribute.
	 * @see #getProject()
	 * @generated
	 */
	void setProject(String value);

	/**
	 * Returns the value of the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' attribute.
	 * @see #setSource(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPipelineResource_Source()
	 * @model
	 * @generated
	 */
	String getSource();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResource#getSource <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' attribute.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(String value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPipelineResource_Version()
	 * @model
	 * @generated
	 */
	String getVersion();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResource#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

	/**
	 * Returns the value of the '<em><b>Branch</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Branch</em>' attribute.
	 * @see #setBranch(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPipelineResource_Branch()
	 * @model
	 * @generated
	 */
	String getBranch();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResource#getBranch <em>Branch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Branch</em>' attribute.
	 * @see #getBranch()
	 * @generated
	 */
	void setBranch(String value);

	/**
	 * Returns the value of the '<em><b>Tags</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tags</em>' attribute list.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPipelineResource_Tags()
	 * @model
	 * @generated
	 */
	EList<String> getTags();

	/**
	 * Returns the value of the '<em><b>Trigger</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trigger</em>' containment reference.
	 * @see #setTrigger(PipelineResourceTrigger)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getPipelineResource_Trigger()
	 * @model containment="true"
	 * @generated
	 */
	PipelineResourceTrigger getTrigger();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResource#getTrigger <em>Trigger</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trigger</em>' containment reference.
	 * @see #getTrigger()
	 * @generated
	 */
	void setTrigger(PipelineResourceTrigger value);

} // PipelineResource
