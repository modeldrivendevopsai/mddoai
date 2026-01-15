/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Need</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Need#getJob <em>Job</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Need#isArtifacts <em>Artifacts</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Need#getProject <em>Project</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Need#getRef <em>Ref</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Need#getPipeline <em>Pipeline</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Need#isOptional <em>Optional</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getNeed()
 * @model
 * @generated
 */
public interface Need extends EObject {
	/**
	 * Returns the value of the '<em><b>Job</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Job</em>' attribute.
	 * @see #setJob(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getNeed_Job()
	 * @model
	 * @generated
	 */
	String getJob();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Need#getJob <em>Job</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Job</em>' attribute.
	 * @see #getJob()
	 * @generated
	 */
	void setJob(String value);

	/**
	 * Returns the value of the '<em><b>Artifacts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Artifacts</em>' attribute.
	 * @see #setArtifacts(boolean)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getNeed_Artifacts()
	 * @model
	 * @generated
	 */
	boolean isArtifacts();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Need#isArtifacts <em>Artifacts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Artifacts</em>' attribute.
	 * @see #isArtifacts()
	 * @generated
	 */
	void setArtifacts(boolean value);

	/**
	 * Returns the value of the '<em><b>Project</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Project</em>' attribute.
	 * @see #setProject(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getNeed_Project()
	 * @model
	 * @generated
	 */
	String getProject();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Need#getProject <em>Project</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Project</em>' attribute.
	 * @see #getProject()
	 * @generated
	 */
	void setProject(String value);

	/**
	 * Returns the value of the '<em><b>Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ref</em>' attribute.
	 * @see #setRef(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getNeed_Ref()
	 * @model
	 * @generated
	 */
	String getRef();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Need#getRef <em>Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ref</em>' attribute.
	 * @see #getRef()
	 * @generated
	 */
	void setRef(String value);

	/**
	 * Returns the value of the '<em><b>Pipeline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pipeline</em>' attribute.
	 * @see #setPipeline(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getNeed_Pipeline()
	 * @model
	 * @generated
	 */
	String getPipeline();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Need#getPipeline <em>Pipeline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pipeline</em>' attribute.
	 * @see #getPipeline()
	 * @generated
	 */
	void setPipeline(String value);

	/**
	 * Returns the value of the '<em><b>Optional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Optional</em>' attribute.
	 * @see #setOptional(boolean)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getNeed_Optional()
	 * @model
	 * @generated
	 */
	boolean isOptional();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Need#isOptional <em>Optional</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Optional</em>' attribute.
	 * @see #isOptional()
	 * @generated
	 */
	void setOptional(boolean value);

} // Need
