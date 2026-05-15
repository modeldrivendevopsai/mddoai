/**
 */
package com.mddoai.metamodel.github.githubMM;

import org.eclipse.emf.common.util.EMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Workflow Call Job</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.WorkflowCallJob#getUses <em>Uses</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.WorkflowCallJob#getArgs <em>Args</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.WorkflowCallJob#getSecrets <em>Secrets</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.WorkflowCallJob#getInheritSecrets <em>Inherit Secrets</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getWorkflowCallJob()
 * @model
 * @generated
 */
public interface WorkflowCallJob extends Job {
	/**
	 * Returns the value of the '<em><b>Uses</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uses</em>' containment reference.
	 * @see #setUses(Expression)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getWorkflowCallJob_Uses()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getUses();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.WorkflowCallJob#getUses <em>Uses</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uses</em>' containment reference.
	 * @see #getUses()
	 * @generated
	 */
	void setUses(Expression value);

	/**
	 * Returns the value of the '<em><b>Args</b></em>' map.
	 * The key is of type {@link com.mddoai.metamodel.github.githubMM.VariableDeclaration},
	 * and the value is of type {@link com.mddoai.metamodel.github.githubMM.Expression},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Args</em>' map.
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getWorkflowCallJob_Args()
	 * @model mapType="com.mddoai.metamodel.github.githubMM.VariableAssignment&lt;com.mddoai.metamodel.github.githubMM.VariableDeclaration, com.mddoai.metamodel.github.githubMM.Expression&gt;"
	 * @generated
	 */
	EMap<VariableDeclaration, Expression> getArgs();

	/**
	 * Returns the value of the '<em><b>Secrets</b></em>' map.
	 * The key is of type {@link com.mddoai.metamodel.github.githubMM.VariableDeclaration},
	 * and the value is of type {@link com.mddoai.metamodel.github.githubMM.Expression},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Secrets</em>' map.
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getWorkflowCallJob_Secrets()
	 * @model mapType="com.mddoai.metamodel.github.githubMM.VariableAssignment&lt;com.mddoai.metamodel.github.githubMM.VariableDeclaration, com.mddoai.metamodel.github.githubMM.Expression&gt;"
	 * @generated
	 */
	EMap<VariableDeclaration, Expression> getSecrets();

	/**
	 * Returns the value of the '<em><b>Inherit Secrets</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inherit Secrets</em>' attribute.
	 * @see #setInheritSecrets(Boolean)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getWorkflowCallJob_InheritSecrets()
	 * @model default="false" required="true"
	 * @generated
	 */
	Boolean getInheritSecrets();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.WorkflowCallJob#getInheritSecrets <em>Inherit Secrets</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inherit Secrets</em>' attribute.
	 * @see #getInheritSecrets()
	 * @generated
	 */
	void setInheritSecrets(Boolean value);

} // WorkflowCallJob
