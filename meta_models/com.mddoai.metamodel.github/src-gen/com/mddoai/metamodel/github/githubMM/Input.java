/**
 */
package com.mddoai.metamodel.github.githubMM;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Input</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Input#getType <em>Type</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Input#getIsRequired <em>Is Required</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Input#getDefault <em>Default</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Input#getOptions <em>Options</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getInput()
 * @model
 * @generated
 */
public interface Input extends Parameter {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link com.mddoai.metamodel.github.githubMM.INPUT_TYPES}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see com.mddoai.metamodel.github.githubMM.INPUT_TYPES
	 * @see #setType(INPUT_TYPES)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getInput_Type()
	 * @model required="true"
	 * @generated
	 */
	INPUT_TYPES getType();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.Input#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see com.mddoai.metamodel.github.githubMM.INPUT_TYPES
	 * @see #getType()
	 * @generated
	 */
	void setType(INPUT_TYPES value);

	/**
	 * Returns the value of the '<em><b>Is Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Required</em>' attribute.
	 * @see #setIsRequired(Boolean)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getInput_IsRequired()
	 * @model
	 * @generated
	 */
	Boolean getIsRequired();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.Input#getIsRequired <em>Is Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Required</em>' attribute.
	 * @see #getIsRequired()
	 * @generated
	 */
	void setIsRequired(Boolean value);

	/**
	 * Returns the value of the '<em><b>Default</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default</em>' containment reference.
	 * @see #setDefault(Expression)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getInput_Default()
	 * @model containment="true"
	 * @generated
	 */
	Expression getDefault();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.Input#getDefault <em>Default</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default</em>' containment reference.
	 * @see #getDefault()
	 * @generated
	 */
	void setDefault(Expression value);

	/**
	 * Returns the value of the '<em><b>Options</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Options</em>' attribute list.
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getInput_Options()
	 * @model
	 * @generated
	 */
	EList<String> getOptions();

} // Input
