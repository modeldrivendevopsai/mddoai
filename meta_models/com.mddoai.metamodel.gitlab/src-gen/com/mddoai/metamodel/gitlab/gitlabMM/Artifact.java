/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Artifact</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifact#getWhen <em>When</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifact#getReports <em>Reports</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifact#getPaths <em>Paths</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifact#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifact#getExpireIn <em>Expire In</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifact#getExposeAs <em>Expose As</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifact#isUntracked <em>Untracked</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifact#getExclude <em>Exclude</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getArtifact()
 * @model
 * @generated
 */
public interface Artifact extends EObject {
	/**
	 * Returns the value of the '<em><b>When</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>When</em>' attribute.
	 * @see #setWhen(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getArtifact_When()
	 * @model
	 * @generated
	 */
	String getWhen();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifact#getWhen <em>When</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>When</em>' attribute.
	 * @see #getWhen()
	 * @generated
	 */
	void setWhen(String value);

	/**
	 * Returns the value of the '<em><b>Reports</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reports</em>' containment reference.
	 * @see #setReports(Report)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getArtifact_Reports()
	 * @model containment="true"
	 * @generated
	 */
	Report getReports();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifact#getReports <em>Reports</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reports</em>' containment reference.
	 * @see #getReports()
	 * @generated
	 */
	void setReports(Report value);

	/**
	 * Returns the value of the '<em><b>Paths</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.gitlab.gitlabMM.Path}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Paths</em>' containment reference list.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getArtifact_Paths()
	 * @model containment="true"
	 * @generated
	 */
	EList<Path> getPaths();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getArtifact_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifact#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Expire In</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expire In</em>' attribute.
	 * @see #setExpireIn(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getArtifact_ExpireIn()
	 * @model
	 * @generated
	 */
	String getExpireIn();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifact#getExpireIn <em>Expire In</em>}' attribute.
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
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getArtifact_ExposeAs()
	 * @model
	 * @generated
	 */
	String getExposeAs();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifact#getExposeAs <em>Expose As</em>}' attribute.
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
	 * @see #setUntracked(boolean)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getArtifact_Untracked()
	 * @model
	 * @generated
	 */
	boolean isUntracked();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifact#isUntracked <em>Untracked</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Untracked</em>' attribute.
	 * @see #isUntracked()
	 * @generated
	 */
	void setUntracked(boolean value);

	/**
	 * Returns the value of the '<em><b>Exclude</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exclude</em>' attribute list.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getArtifact_Exclude()
	 * @model
	 * @generated
	 */
	EList<String> getExclude();

} // Artifact
