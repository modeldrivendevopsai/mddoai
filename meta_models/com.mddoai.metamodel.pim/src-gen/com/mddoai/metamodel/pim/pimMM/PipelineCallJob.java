/**
 */
package com.mddoai.metamodel.pim.pimMM;

import org.eclipse.emf.common.util.EMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pipeline Call Job</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.PipelineCallJob#getPipelinePath <em>Pipeline Path</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.PipelineCallJob#getArgs <em>Args</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getPipelineCallJob()
 * @model
 * @generated
 */
public interface PipelineCallJob extends Job {
	/**
	 * Returns the value of the '<em><b>Pipeline Path</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pipeline Path</em>' containment reference.
	 * @see #setPipelinePath(Expression)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getPipelineCallJob_PipelinePath()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getPipelinePath();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.PipelineCallJob#getPipelinePath <em>Pipeline Path</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pipeline Path</em>' containment reference.
	 * @see #getPipelinePath()
	 * @generated
	 */
	void setPipelinePath(Expression value);

	/**
	 * Returns the value of the '<em><b>Args</b></em>' map.
	 * The key is of type {@link com.mddoai.metamodel.pim.pimMM.VariableDeclaration},
	 * and the value is of type {@link com.mddoai.metamodel.pim.pimMM.Expression},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Args</em>' map.
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getPipelineCallJob_Args()
	 * @model mapType="com.mddoai.metamodel.pim.pimMM.Assignment&lt;com.mddoai.metamodel.pim.pimMM.VariableDeclaration, com.mddoai.metamodel.pim.pimMM.Expression&gt;"
	 * @generated
	 */
	EMap<VariableDeclaration, Expression> getArgs();

} // PipelineCallJob
