/**
 */
package com.mddoai.metamodel.pim.pimMM;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Command</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Command#getCommand <em>Command</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getCommand()
 * @model
 * @generated
 */
public interface Command extends EObject {
	/**
	 * Returns the value of the '<em><b>Command</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Command</em>' attribute.
	 * @see #setCommand(String)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getCommand_Command()
	 * @model required="true"
	 * @generated
	 */
	String getCommand();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.Command#getCommand <em>Command</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Command</em>' attribute.
	 * @see #getCommand()
	 * @generated
	 */
	void setCommand(String value);

} // Command
