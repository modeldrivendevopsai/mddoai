/**
 */
package com.mddoai.metamodel.github.githubMM;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>If Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.IfStep#getIfCondition <em>If Condition</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.IfStep#getThenRun <em>Then Run</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getIfStep()
 * @model
 * @generated
 */
public interface IfStep extends AbstractStep {
	/**
	 * Returns the value of the '<em><b>If Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>If Condition</em>' containment reference.
	 * @see #setIfCondition(Expression)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getIfStep_IfCondition()
	 * @model containment="true"
	 * @generated
	 */
	Expression getIfCondition();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.IfStep#getIfCondition <em>If Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>If Condition</em>' containment reference.
	 * @see #getIfCondition()
	 * @generated
	 */
	void setIfCondition(Expression value);

	/**
	 * Returns the value of the '<em><b>Then Run</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Then Run</em>' containment reference.
	 * @see #setThenRun(Step)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getIfStep_ThenRun()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Step getThenRun();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.IfStep#getThenRun <em>Then Run</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Then Run</em>' containment reference.
	 * @see #getThenRun()
	 * @generated
	 */
	void setThenRun(Step value);

} // IfStep
