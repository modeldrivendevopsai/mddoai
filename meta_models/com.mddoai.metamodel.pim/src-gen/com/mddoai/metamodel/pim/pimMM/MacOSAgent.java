/**
 */
package com.mddoai.metamodel.pim.pimMM;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mac OS Agent</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.MacOSAgent#getXcode <em>Xcode</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getMacOSAgent()
 * @model
 * @generated
 */
public interface MacOSAgent extends PresetAgent {
	/**
	 * Returns the value of the '<em><b>Xcode</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Xcode</em>' containment reference.
	 * @see #setXcode(Expression)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getMacOSAgent_Xcode()
	 * @model containment="true"
	 * @generated
	 */
	Expression getXcode();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.MacOSAgent#getXcode <em>Xcode</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Xcode</em>' containment reference.
	 * @see #getXcode()
	 * @generated
	 */
	void setXcode(Expression value);

} // MacOSAgent
