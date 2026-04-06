/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Retry</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Retry#getMax <em>Max</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Retry#getWhen <em>When</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getRetry()
 * @model
 * @generated
 */
public interface Retry extends EObject {
	/**
	 * Returns the value of the '<em><b>Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max</em>' attribute.
	 * @see #setMax(int)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getRetry_Max()
	 * @model required="true"
	 * @generated
	 */
	int getMax();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Retry#getMax <em>Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max</em>' attribute.
	 * @see #getMax()
	 * @generated
	 */
	void setMax(int value);

	/**
	 * Returns the value of the '<em><b>When</b></em>' attribute list.
	 * The list contents are of type {@link com.mddoai.metamodel.gitlab.gitlabMM.RetryWhenType}.
	 * The literals are from the enumeration {@link com.mddoai.metamodel.gitlab.gitlabMM.RetryWhenType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>When</em>' attribute list.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.RetryWhenType
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getRetry_When()
	 * @model
	 * @generated
	 */
	EList<RetryWhenType> getWhen();

} // Retry
