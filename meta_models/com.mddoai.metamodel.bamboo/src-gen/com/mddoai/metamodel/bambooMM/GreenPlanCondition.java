/**
 */
package com.mddoai.metamodel.bambooMM;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Green Plan Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.GreenPlanCondition#getPlanKeys <em>Plan Keys</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getGreenPlanCondition()
 * @model
 * @generated
 */
public interface GreenPlanCondition extends TriggerCondition {
	/**
	 * Returns the value of the '<em><b>Plan Keys</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Plan Keys</em>' attribute list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getGreenPlanCondition_PlanKeys()
	 * @model
	 * @generated
	 */
	EList<String> getPlanKeys();

} // GreenPlanCondition
