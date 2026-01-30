/**
 */
package com.mddoai.metamodel.pim.pimMM;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Command</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Command#getProgram <em>Program</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Command#getShell <em>Shell</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getCommand()
 * @model
 * @generated
 */
public interface Command extends NonConditionalStep {
	/**
	 * Returns the value of the '<em><b>Program</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Program</em>' containment reference.
	 * @see #setProgram(Expression)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getCommand_Program()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getProgram();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.Command#getProgram <em>Program</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Program</em>' containment reference.
	 * @see #getProgram()
	 * @generated
	 */
	void setProgram(Expression value);

	/**
	 * Returns the value of the '<em><b>Shell</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Shell</em>' containment reference.
	 * @see #setShell(Expression)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getCommand_Shell()
	 * @model containment="true"
	 * @generated
	 */
	Expression getShell();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.Command#getShell <em>Shell</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Shell</em>' containment reference.
	 * @see #getShell()
	 * @generated
	 */
	void setShell(Expression value);

} // Command
