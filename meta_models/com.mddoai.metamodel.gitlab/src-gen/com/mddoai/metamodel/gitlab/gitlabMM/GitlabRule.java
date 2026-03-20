/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gitlab Rule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.GitlabRule#getGitlabIf <em>Gitlab If</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.GitlabRule#getWhen <em>When</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.GitlabRule#getChanges <em>Changes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.GitlabRule#getExists <em>Exists</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.GitlabRule#isAllowFailure <em>Allow Failure</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.GitlabRule#getVariables <em>Variables</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getGitlabRule()
 * @model
 * @generated
 */
public interface GitlabRule extends EObject {
	/**
	 * Returns the value of the '<em><b>Gitlab If</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Gitlab If</em>' attribute.
	 * @see #setGitlabIf(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getGitlabRule_GitlabIf()
	 * @model
	 * @generated
	 */
	String getGitlabIf();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.GitlabRule#getGitlabIf <em>Gitlab If</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Gitlab If</em>' attribute.
	 * @see #getGitlabIf()
	 * @generated
	 */
	void setGitlabIf(String value);

	/**
	 * Returns the value of the '<em><b>When</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>When</em>' attribute.
	 * @see #setWhen(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getGitlabRule_When()
	 * @model
	 * @generated
	 */
	String getWhen();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.GitlabRule#getWhen <em>When</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>When</em>' attribute.
	 * @see #getWhen()
	 * @generated
	 */
	void setWhen(String value);

	/**
	 * Returns the value of the '<em><b>Changes</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Changes</em>' attribute list.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getGitlabRule_Changes()
	 * @model
	 * @generated
	 */
	EList<String> getChanges();

	/**
	 * Returns the value of the '<em><b>Exists</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exists</em>' attribute list.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getGitlabRule_Exists()
	 * @model
	 * @generated
	 */
	EList<String> getExists();

	/**
	 * Returns the value of the '<em><b>Allow Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Allow Failure</em>' attribute.
	 * @see #setAllowFailure(boolean)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getGitlabRule_AllowFailure()
	 * @model
	 * @generated
	 */
	boolean isAllowFailure();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.GitlabRule#isAllowFailure <em>Allow Failure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Allow Failure</em>' attribute.
	 * @see #isAllowFailure()
	 * @generated
	 */
	void setAllowFailure(boolean value);

	/**
	 * Returns the value of the '<em><b>Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variables</em>' containment reference.
	 * @see #setVariables(Variables)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getGitlabRule_Variables()
	 * @model containment="true"
	 * @generated
	 */
	Variables getVariables();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.GitlabRule#getVariables <em>Variables</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variables</em>' containment reference.
	 * @see #getVariables()
	 * @generated
	 */
	void setVariables(Variables value);

} // GitlabRule
