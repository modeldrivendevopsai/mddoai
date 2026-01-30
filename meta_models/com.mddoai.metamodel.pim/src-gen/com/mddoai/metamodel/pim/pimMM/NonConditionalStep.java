/**
 */
package com.mddoai.metamodel.pim.pimMM;

import org.eclipse.emf.common.util.EMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Non Conditional Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.NonConditionalStep#getId <em>Id</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.NonConditionalStep#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.NonConditionalStep#getEnvironmentVariables <em>Environment Variables</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.NonConditionalStep#getAllowFailure <em>Allow Failure</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.NonConditionalStep#getWorkingDirectory <em>Working Directory</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.NonConditionalStep#getTimeoutMinutes <em>Timeout Minutes</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getNonConditionalStep()
 * @model abstract="true"
 * @generated
 */
public interface NonConditionalStep extends Step {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getNonConditionalStep_Id()
	 * @model
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.NonConditionalStep#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getNonConditionalStep_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.NonConditionalStep#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Environment Variables</b></em>' map.
	 * The key is of type {@link com.mddoai.metamodel.pim.pimMM.VariableDeclaration},
	 * and the value is of type {@link com.mddoai.metamodel.pim.pimMM.Expression},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Environment Variables</em>' map.
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getNonConditionalStep_EnvironmentVariables()
	 * @model mapType="com.mddoai.metamodel.pim.pimMM.Assignment&lt;com.mddoai.metamodel.pim.pimMM.VariableDeclaration, com.mddoai.metamodel.pim.pimMM.Expression&gt;"
	 * @generated
	 */
	EMap<VariableDeclaration, Expression> getEnvironmentVariables();

	/**
	 * Returns the value of the '<em><b>Allow Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Allow Failure</em>' attribute.
	 * @see #setAllowFailure(Boolean)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getNonConditionalStep_AllowFailure()
	 * @model
	 * @generated
	 */
	Boolean getAllowFailure();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.NonConditionalStep#getAllowFailure <em>Allow Failure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Allow Failure</em>' attribute.
	 * @see #getAllowFailure()
	 * @generated
	 */
	void setAllowFailure(Boolean value);

	/**
	 * Returns the value of the '<em><b>Working Directory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Working Directory</em>' containment reference.
	 * @see #setWorkingDirectory(Expression)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getNonConditionalStep_WorkingDirectory()
	 * @model containment="true"
	 * @generated
	 */
	Expression getWorkingDirectory();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.NonConditionalStep#getWorkingDirectory <em>Working Directory</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Working Directory</em>' containment reference.
	 * @see #getWorkingDirectory()
	 * @generated
	 */
	void setWorkingDirectory(Expression value);

	/**
	 * Returns the value of the '<em><b>Timeout Minutes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timeout Minutes</em>' containment reference.
	 * @see #setTimeoutMinutes(Expression)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getNonConditionalStep_TimeoutMinutes()
	 * @model containment="true"
	 * @generated
	 */
	Expression getTimeoutMinutes();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.NonConditionalStep#getTimeoutMinutes <em>Timeout Minutes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timeout Minutes</em>' containment reference.
	 * @see #getTimeoutMinutes()
	 * @generated
	 */
	void setTimeoutMinutes(Expression value);

} // NonConditionalStep
