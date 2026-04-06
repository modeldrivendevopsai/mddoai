/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Artifacts</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifacts#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifacts#getPaths <em>Paths</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifacts#getWhen <em>When</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifacts#getExpireIn <em>Expire In</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifacts#getExposeAs <em>Expose As</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifacts#getUntracked <em>Untracked</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifacts#getExclude <em>Exclude</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifacts#getReports <em>Reports</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getArtifacts()
 * @model
 * @generated
 */
public interface Artifacts extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getArtifacts_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifacts#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Paths</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Paths</em>' attribute list.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getArtifacts_Paths()
	 * @model
	 * @generated
	 */
	EList<String> getPaths();

	/**
	 * Returns the value of the '<em><b>When</b></em>' attribute.
	 * The literals are from the enumeration {@link com.mddoai.metamodel.gitlab.gitlabMM.ArtifactsWhenType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>When</em>' attribute.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.ArtifactsWhenType
	 * @see #setWhen(ArtifactsWhenType)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getArtifacts_When()
	 * @model
	 * @generated
	 */
	ArtifactsWhenType getWhen();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifacts#getWhen <em>When</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>When</em>' attribute.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.ArtifactsWhenType
	 * @see #getWhen()
	 * @generated
	 */
	void setWhen(ArtifactsWhenType value);

	/**
	 * Returns the value of the '<em><b>Expire In</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expire In</em>' attribute.
	 * @see #setExpireIn(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getArtifacts_ExpireIn()
	 * @model
	 * @generated
	 */
	String getExpireIn();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifacts#getExpireIn <em>Expire In</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expire In</em>' attribute.
	 * @see #getExpireIn()
	 * @generated
	 */
	void setExpireIn(String value);

	/**
	 * Returns the value of the '<em><b>Expose As</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expose As</em>' attribute.
	 * @see #setExposeAs(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getArtifacts_ExposeAs()
	 * @model
	 * @generated
	 */
	String getExposeAs();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifacts#getExposeAs <em>Expose As</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expose As</em>' attribute.
	 * @see #getExposeAs()
	 * @generated
	 */
	void setExposeAs(String value);

	/**
	 * Returns the value of the '<em><b>Untracked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Untracked</em>' attribute.
	 * @see #setUntracked(Boolean)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getArtifacts_Untracked()
	 * @model
	 * @generated
	 */
	Boolean getUntracked();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifacts#getUntracked <em>Untracked</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Untracked</em>' attribute.
	 * @see #getUntracked()
	 * @generated
	 */
	void setUntracked(Boolean value);

	/**
	 * Returns the value of the '<em><b>Exclude</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exclude</em>' attribute list.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getArtifacts_Exclude()
	 * @model
	 * @generated
	 */
	EList<String> getExclude();

	/**
	 * Returns the value of the '<em><b>Reports</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reports</em>' containment reference.
	 * @see #setReports(ArtifactReports)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getArtifacts_Reports()
	 * @model containment="true"
	 * @generated
	 */
	ArtifactReports getReports();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifacts#getReports <em>Reports</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reports</em>' containment reference.
	 * @see #getReports()
	 * @generated
	 */
	void setReports(ArtifactReports value);

} // Artifacts
