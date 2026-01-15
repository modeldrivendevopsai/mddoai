/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Report</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Report#getJunit <em>Junit</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Report#getCoverage_report <em>Coverage report</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Report#getDotenv <em>Dotenv</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Report#getCobertura <em>Cobertura</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getReport()
 * @model
 * @generated
 */
public interface Report extends EObject {
	/**
	 * Returns the value of the '<em><b>Junit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Junit</em>' attribute.
	 * @see #setJunit(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getReport_Junit()
	 * @model required="true"
	 * @generated
	 */
	String getJunit();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Report#getJunit <em>Junit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Junit</em>' attribute.
	 * @see #getJunit()
	 * @generated
	 */
	void setJunit(String value);

	/**
	 * Returns the value of the '<em><b>Coverage report</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Coverage report</em>' attribute.
	 * @see #setCoverage_report(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getReport_Coverage_report()
	 * @model
	 * @generated
	 */
	String getCoverage_report();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Report#getCoverage_report <em>Coverage report</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Coverage report</em>' attribute.
	 * @see #getCoverage_report()
	 * @generated
	 */
	void setCoverage_report(String value);

	/**
	 * Returns the value of the '<em><b>Dotenv</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dotenv</em>' attribute.
	 * @see #setDotenv(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getReport_Dotenv()
	 * @model
	 * @generated
	 */
	String getDotenv();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Report#getDotenv <em>Dotenv</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dotenv</em>' attribute.
	 * @see #getDotenv()
	 * @generated
	 */
	void setDotenv(String value);

	/**
	 * Returns the value of the '<em><b>Cobertura</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cobertura</em>' attribute.
	 * @see #setCobertura(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getReport_Cobertura()
	 * @model
	 * @generated
	 */
	String getCobertura();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Report#getCobertura <em>Cobertura</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cobertura</em>' attribute.
	 * @see #getCobertura()
	 * @generated
	 */
	void setCobertura(String value);

} // Report
