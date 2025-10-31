/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Before Script</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.BeforeScript#getCommands <em>Commands</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getBeforeScript()
 * @model
 * @generated
 */
public interface BeforeScript extends EObject {
	/**
	 * Returns the value of the '<em><b>Commands</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.gitlab.gitlabMM.Comand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Commands</em>' containment reference list.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getBeforeScript_Commands()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Comand> getCommands();

} // BeforeScript
