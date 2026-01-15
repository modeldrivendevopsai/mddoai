/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Key</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Key#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Key#getFiles <em>Files</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Key#getFiles_commits <em>Files commits</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Key#getPrefix <em>Prefix</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getKey()
 * @model
 * @generated
 */
public interface Key extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getKey_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Key#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Files</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Files</em>' attribute list.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getKey_Files()
	 * @model
	 * @generated
	 */
	EList<String> getFiles();

	/**
	 * Returns the value of the '<em><b>Files commits</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Files commits</em>' attribute list.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getKey_Files_commits()
	 * @model
	 * @generated
	 */
	EList<String> getFiles_commits();

	/**
	 * Returns the value of the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Prefix</em>' attribute.
	 * @see #setPrefix(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getKey_Prefix()
	 * @model
	 * @generated
	 */
	String getPrefix();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Key#getPrefix <em>Prefix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Prefix</em>' attribute.
	 * @see #getPrefix()
	 * @generated
	 */
	void setPrefix(String value);

} // Key
