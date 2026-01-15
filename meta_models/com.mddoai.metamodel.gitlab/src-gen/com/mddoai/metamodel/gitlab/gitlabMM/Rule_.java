/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Rule </b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Rule_#getIf_ <em>If </em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Rule_#getWhen <em>When</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Rule_#getChanges <em>Changes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Rule_#getExists <em>Exists</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Rule_#isAllowFailure <em>Allow Failure</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Rule_#getVariables <em>Variables</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getRule_()
 * @model
 * @generated
 */
public interface Rule_ extends EObject {
	/**
	 * Returns the value of the '<em><b>If </b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>If </em>' attribute.
	 * @see #setIf_(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getRule__If_()
	 * @model
	 * @generated
	 */
	String getIf_();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Rule_#getIf_ <em>If </em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>If </em>' attribute.
	 * @see #getIf_()
	 * @generated
	 */
	void setIf_(String value);

	/**
	 * Returns the value of the '<em><b>When</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>When</em>' attribute.
	 * @see #setWhen(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getRule__When()
	 * @model
	 * @generated
	 */
	String getWhen();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Rule_#getWhen <em>When</em>}' attribute.
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
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getRule__Changes()
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
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getRule__Exists()
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
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getRule__AllowFailure()
	 * @model
	 * @generated
	 */
	boolean isAllowFailure();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Rule_#isAllowFailure <em>Allow Failure</em>}' attribute.
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
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getRule__Variables()
	 * @model containment="true"
	 * @generated
	 */
	Variables getVariables();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Rule_#getVariables <em>Variables</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variables</em>' containment reference.
	 * @see #getVariables()
	 * @generated
	 */
	void setVariables(Variables value);

} // Rule_
