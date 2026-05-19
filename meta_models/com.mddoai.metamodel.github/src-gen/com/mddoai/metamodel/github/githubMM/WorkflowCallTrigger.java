/**
 */
package com.mddoai.metamodel.github.githubMM;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Workflow Call Trigger</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.WorkflowCallTrigger#getOutputs <em>Outputs</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.WorkflowCallTrigger#getSecrets <em>Secrets</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getWorkflowCallTrigger()
 * @model
 * @generated
 */
public interface WorkflowCallTrigger extends InputTrigger {
	/**
	 * Returns the value of the '<em><b>Outputs</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.github.githubMM.Output}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outputs</em>' containment reference list.
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getWorkflowCallTrigger_Outputs()
	 * @model containment="true"
	 * @generated
	 */
	EList<Output> getOutputs();

	/**
	 * Returns the value of the '<em><b>Secrets</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.github.githubMM.Secret}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Secrets</em>' containment reference list.
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getWorkflowCallTrigger_Secrets()
	 * @model containment="true"
	 * @generated
	 */
	EList<Secret> getSecrets();

} // WorkflowCallTrigger
