/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM;

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
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getStage <em>Stage</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getArtifacts <em>Artifacts</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getScript <em>Script</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getBeforeScript <em>Before Script</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getImage <em>Image</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getTags <em>Tags</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getOnly <em>Only</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getDependencies <em>Dependencies</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getWhen <em>When</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getJob()
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
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getJob_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Stage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stage</em>' attribute.
	 * @see #setStage(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getJob_Stage()
	 * @model required="true"
	 * @generated
	 */
	String getStage();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getStage <em>Stage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stage</em>' attribute.
	 * @see #getStage()
	 * @generated
	 */
	void setStage(String value);

	/**
	 * Returns the value of the '<em><b>Artifacts</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Artifacts</em>' containment reference.
	 * @see #setArtifacts(Artifact)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getJob_Artifacts()
	 * @model containment="true"
	 * @generated
	 */
	Artifact getArtifacts();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getArtifacts <em>Artifacts</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Artifacts</em>' containment reference.
	 * @see #getArtifacts()
	 * @generated
	 */
	void setArtifacts(Artifact value);

	/**
	 * Returns the value of the '<em><b>Script</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Script</em>' containment reference.
	 * @see #setScript(Script)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getJob_Script()
	 * @model containment="true"
	 * @generated
	 */
	Script getScript();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getScript <em>Script</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Script</em>' containment reference.
	 * @see #getScript()
	 * @generated
	 */
	void setScript(Script value);

	/**
	 * Returns the value of the '<em><b>Before Script</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Before Script</em>' containment reference.
	 * @see #setBeforeScript(BeforeScript)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getJob_BeforeScript()
	 * @model containment="true"
	 * @generated
	 */
	BeforeScript getBeforeScript();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getBeforeScript <em>Before Script</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Before Script</em>' containment reference.
	 * @see #getBeforeScript()
	 * @generated
	 */
	void setBeforeScript(BeforeScript value);

	/**
	 * Returns the value of the '<em><b>Image</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Image</em>' attribute.
	 * @see #setImage(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getJob_Image()
	 * @model
	 * @generated
	 */
	String getImage();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getImage <em>Image</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Image</em>' attribute.
	 * @see #getImage()
	 * @generated
	 */
	void setImage(String value);

	/**
	 * Returns the value of the '<em><b>Tags</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tags</em>' containment reference.
	 * @see #setTags(Tags)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getJob_Tags()
	 * @model containment="true"
	 * @generated
	 */
	Tags getTags();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getTags <em>Tags</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tags</em>' containment reference.
	 * @see #getTags()
	 * @generated
	 */
	void setTags(Tags value);

	/**
	 * Returns the value of the '<em><b>Only</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Only</em>' containment reference.
	 * @see #setOnly(Only)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getJob_Only()
	 * @model containment="true"
	 * @generated
	 */
	Only getOnly();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getOnly <em>Only</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Only</em>' containment reference.
	 * @see #getOnly()
	 * @generated
	 */
	void setOnly(Only value);

	/**
	 * Returns the value of the '<em><b>Dependencies</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dependencies</em>' containment reference.
	 * @see #setDependencies(Dependencies)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getJob_Dependencies()
	 * @model containment="true"
	 * @generated
	 */
	Dependencies getDependencies();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getDependencies <em>Dependencies</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dependencies</em>' containment reference.
	 * @see #getDependencies()
	 * @generated
	 */
	void setDependencies(Dependencies value);

	/**
	 * Returns the value of the '<em><b>When</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>When</em>' attribute.
	 * @see #setWhen(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getJob_When()
	 * @model
	 * @generated
	 */
	String getWhen();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getWhen <em>When</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>When</em>' attribute.
	 * @see #getWhen()
	 * @generated
	 */
	void setWhen(String value);

} // Job
