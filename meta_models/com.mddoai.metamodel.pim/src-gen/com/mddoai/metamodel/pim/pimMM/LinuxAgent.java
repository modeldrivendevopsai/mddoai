/**
 */
package com.mddoai.metamodel.pim.pimMM;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Linux Agent</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.LinuxAgent#getContainer <em>Container</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getLinuxAgent()
 * @model
 * @generated
 */
public interface LinuxAgent extends PresetAgent {
	/**
	 * Returns the value of the '<em><b>Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Container</em>' containment reference.
	 * @see #setContainer(DockerContainer)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getLinuxAgent_Container()
	 * @model containment="true"
	 * @generated
	 */
	DockerContainer getContainer();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.LinuxAgent#getContainer <em>Container</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Container</em>' containment reference.
	 * @see #getContainer()
	 * @generated
	 */
	void setContainer(DockerContainer value);

} // LinuxAgent
