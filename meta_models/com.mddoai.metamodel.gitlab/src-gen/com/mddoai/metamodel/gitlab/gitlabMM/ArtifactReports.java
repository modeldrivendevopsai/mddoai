/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Artifact Reports</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.ArtifactReports#getJunit <em>Junit</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.ArtifactReports#getCoverageReport <em>Coverage Report</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.ArtifactReports#getDotenv <em>Dotenv</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getArtifactReports()
 * @model
 * @generated
 */
public interface ArtifactReports extends EObject {
	/**
	 * Returns the value of the '<em><b>Junit</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Junit</em>' attribute list.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getArtifactReports_Junit()
	 * @model
	 * @generated
	 */
	EList<String> getJunit();

	/**
	 * Returns the value of the '<em><b>Coverage Report</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Coverage Report</em>' containment reference.
	 * @see #setCoverageReport(CoverageReport)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getArtifactReports_CoverageReport()
	 * @model containment="true"
	 * @generated
	 */
	CoverageReport getCoverageReport();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.ArtifactReports#getCoverageReport <em>Coverage Report</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Coverage Report</em>' containment reference.
	 * @see #getCoverageReport()
	 * @generated
	 */
	void setCoverageReport(CoverageReport value);

	/**
	 * Returns the value of the '<em><b>Dotenv</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dotenv</em>' attribute.
	 * @see #setDotenv(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getArtifactReports_Dotenv()
	 * @model
	 * @generated
	 */
	String getDotenv();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.ArtifactReports#getDotenv <em>Dotenv</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dotenv</em>' attribute.
	 * @see #getDotenv()
	 * @generated
	 */
	void setDotenv(String value);

} // ArtifactReports
