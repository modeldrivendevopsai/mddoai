/**
 */
package com.mddoai.metamodel.pim.pimMM;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Script</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Script#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Script#getCommands <em>Commands</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getScript()
 * @model
 * @generated
 */
public interface Script extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getScript_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.Script#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Commands</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Commands</em>' containment reference.
	 * @see #setCommands(Commands)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getScript_Commands()
	 * @model containment="true"
	 * @generated
	 */
	Commands getCommands();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.Script#getCommands <em>Commands</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Commands</em>' containment reference.
	 * @see #getCommands()
	 * @generated
	 */
	void setCommands(Commands value);

} // Script
