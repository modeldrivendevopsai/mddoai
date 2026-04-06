/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Coverage Report</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.CoverageReport#getCoverageFormat <em>Coverage Format</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.CoverageReport#getPath <em>Path</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getCoverageReport()
 * @model
 * @generated
 */
public interface CoverageReport extends EObject {
	/**
	 * Returns the value of the '<em><b>Coverage Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Coverage Format</em>' attribute.
	 * @see #setCoverageFormat(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getCoverageReport_CoverageFormat()
	 * @model required="true"
	 * @generated
	 */
	String getCoverageFormat();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.CoverageReport#getCoverageFormat <em>Coverage Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Coverage Format</em>' attribute.
	 * @see #getCoverageFormat()
	 * @generated
	 */
	void setCoverageFormat(String value);

	/**
	 * Returns the value of the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Path</em>' attribute.
	 * @see #setPath(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getCoverageReport_Path()
	 * @model required="true"
	 * @generated
	 */
	String getPath();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.CoverageReport#getPath <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path</em>' attribute.
	 * @see #getPath()
	 * @generated
	 */
	void setPath(String value);

} // CoverageReport
