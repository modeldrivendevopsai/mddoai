/**
 */
package com.mddoai.metamodel.pim.pimMM;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Conditional Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.ConditionalStep#getIfCondition <em>If Condition</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.ConditionalStep#getThenRun <em>Then Run</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.ConditionalStep#getElseRun <em>Else Run</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getConditionalStep()
 * @model
 * @generated
 */
public interface ConditionalStep extends Step {
	/**
	 * Returns the value of the '<em><b>If Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>If Condition</em>' containment reference.
	 * @see #setIfCondition(Expression)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getConditionalStep_IfCondition()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getIfCondition();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.ConditionalStep#getIfCondition <em>If Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>If Condition</em>' containment reference.
	 * @see #getIfCondition()
	 * @generated
	 */
	void setIfCondition(Expression value);

	/**
	 * Returns the value of the '<em><b>Then Run</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.pim.pimMM.Step}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Then Run</em>' containment reference list.
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getConditionalStep_ThenRun()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Step> getThenRun();

	/**
	 * Returns the value of the '<em><b>Else Run</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.pim.pimMM.Step}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Else Run</em>' containment reference list.
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getConditionalStep_ElseRun()
	 * @model containment="true"
	 * @generated
	 */
	EList<Step> getElseRun();

} // ConditionalStep
