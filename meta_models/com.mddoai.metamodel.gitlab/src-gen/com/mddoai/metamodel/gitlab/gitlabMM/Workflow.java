/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Workflow</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Workflow#getRules <em>Rules</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getWorkflow()
 * @model
 * @generated
 */
public interface Workflow extends EObject {
	/**
	 * Returns the value of the '<em><b>Rules</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.gitlab.gitlabMM.Rule_}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rules</em>' containment reference list.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getWorkflow_Rules()
	 * @model containment="true"
	 * @generated
	 */
	EList<Rule_> getRules();

} // Workflow
