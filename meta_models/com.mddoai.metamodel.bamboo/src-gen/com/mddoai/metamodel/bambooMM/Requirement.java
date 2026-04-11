/**
 */
package com.mddoai.metamodel.bambooMM;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Requirement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Requirement#getCapability <em>Capability</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Requirement#getMatchValue <em>Match Value</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getRequirement()
 * @model
 * @generated
 */
public interface Requirement extends EObject {
	/**
	 * Returns the value of the '<em><b>Capability</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Capability</em>' attribute.
	 * @see #setCapability(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getRequirement_Capability()
	 * @model required="true"
	 * @generated
	 */
	String getCapability();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.Requirement#getCapability <em>Capability</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Capability</em>' attribute.
	 * @see #getCapability()
	 * @generated
	 */
	void setCapability(String value);

	/**
	 * Returns the value of the '<em><b>Match Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Match Value</em>' attribute.
	 * @see #setMatchValue(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getRequirement_MatchValue()
	 * @model
	 * @generated
	 */
	String getMatchValue();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.Requirement#getMatchValue <em>Match Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Match Value</em>' attribute.
	 * @see #getMatchValue()
	 * @generated
	 */
	void setMatchValue(String value);

} // Requirement
