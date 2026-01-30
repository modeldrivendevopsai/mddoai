/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cache</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Cache#getKey <em>Key</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Cache#getPaths <em>Paths</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Cache#isUntracked <em>Untracked</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Cache#isUnprotect <em>Unprotect</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Cache#getWhen <em>When</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getCache()
 * @model
 * @generated
 */
public interface Cache extends EObject {
	/**
	 * Returns the value of the '<em><b>Key</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Key</em>' reference.
	 * @see #setKey(Key)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getCache_Key()
	 * @model
	 * @generated
	 */
	Key getKey();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Cache#getKey <em>Key</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Key</em>' reference.
	 * @see #getKey()
	 * @generated
	 */
	void setKey(Key value);

	/**
	 * Returns the value of the '<em><b>Paths</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Paths</em>' attribute list.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getCache_Paths()
	 * @model
	 * @generated
	 */
	EList<String> getPaths();

	/**
	 * Returns the value of the '<em><b>Untracked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Untracked</em>' attribute.
	 * @see #setUntracked(boolean)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getCache_Untracked()
	 * @model
	 * @generated
	 */
	boolean isUntracked();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Cache#isUntracked <em>Untracked</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Untracked</em>' attribute.
	 * @see #isUntracked()
	 * @generated
	 */
	void setUntracked(boolean value);

	/**
	 * Returns the value of the '<em><b>Unprotect</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unprotect</em>' attribute.
	 * @see #setUnprotect(boolean)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getCache_Unprotect()
	 * @model
	 * @generated
	 */
	boolean isUnprotect();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Cache#isUnprotect <em>Unprotect</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unprotect</em>' attribute.
	 * @see #isUnprotect()
	 * @generated
	 */
	void setUnprotect(boolean value);

	/**
	 * Returns the value of the '<em><b>When</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>When</em>' attribute.
	 * @see #setWhen(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getCache_When()
	 * @model
	 * @generated
	 */
	String getWhen();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Cache#getWhen <em>When</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>When</em>' attribute.
	 * @see #getWhen()
	 * @generated
	 */
	void setWhen(String value);

} // Cache
