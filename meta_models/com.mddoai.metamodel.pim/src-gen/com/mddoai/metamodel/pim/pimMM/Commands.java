/**
 */
package com.mddoai.metamodel.pim.pimMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Commands</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Commands#getCommands <em>Commands</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getCommands()
 * @model
 * @generated
 */
public interface Commands extends EObject {
	/**
	 * Returns the value of the '<em><b>Commands</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.pim.pimMM.Command}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Commands</em>' containment reference list.
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getCommands_Commands()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Command> getCommands();

} // Commands
