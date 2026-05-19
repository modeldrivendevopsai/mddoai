/**
 */
package com.mddoai.metamodel.github.githubMM;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Format</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Format#getString <em>String</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Format#getReplaceValues <em>Replace Values</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getFormat()
 * @model
 * @generated
 */
public interface Format extends BuiltInFunctionCall {
	/**
	 * Returns the value of the '<em><b>String</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>String</em>' containment reference.
	 * @see #setString(Expression)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getFormat_String()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getString();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.Format#getString <em>String</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>String</em>' containment reference.
	 * @see #getString()
	 * @generated
	 */
	void setString(Expression value);

	/**
	 * Returns the value of the '<em><b>Replace Values</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.github.githubMM.Expression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Replace Values</em>' containment reference list.
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getFormat_ReplaceValues()
	 * @model containment="true"
	 * @generated
	 */
	EList<Expression> getReplaceValues();

} // Format
