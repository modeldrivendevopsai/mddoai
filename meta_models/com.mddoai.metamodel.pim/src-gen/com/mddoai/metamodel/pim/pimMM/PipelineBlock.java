/**
 */
package com.mddoai.metamodel.pim.pimMM;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pipeline Block</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.PipelineBlock#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.PipelineBlock#getAgent <em>Agent</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.PipelineBlock#getInputs <em>Inputs</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.PipelineBlock#getOutputs <em>Outputs</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.PipelineBlock#getEnvironmentVariables <em>Environment Variables</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.PipelineBlock#getTimeoutMinutes <em>Timeout Minutes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.PipelineBlock#getWorkingDirectory <em>Working Directory</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.PipelineBlock#getShell <em>Shell</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getPipelineBlock()
 * @model abstract="true"
 * @generated
 */
public interface PipelineBlock extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getPipelineBlock_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.PipelineBlock#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Agent</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Agent</em>' containment reference.
	 * @see #setAgent(Agent)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getPipelineBlock_Agent()
	 * @model containment="true"
	 * @generated
	 */
	Agent getAgent();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.PipelineBlock#getAgent <em>Agent</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Agent</em>' containment reference.
	 * @see #getAgent()
	 * @generated
	 */
	void setAgent(Agent value);

	/**
	 * Returns the value of the '<em><b>Inputs</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.pim.pimMM.Input}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inputs</em>' containment reference list.
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getPipelineBlock_Inputs()
	 * @model containment="true"
	 * @generated
	 */
	EList<Input> getInputs();

	/**
	 * Returns the value of the '<em><b>Outputs</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.pim.pimMM.Output}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outputs</em>' containment reference list.
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getPipelineBlock_Outputs()
	 * @model containment="true"
	 * @generated
	 */
	EList<Output> getOutputs();

	/**
	 * Returns the value of the '<em><b>Environment Variables</b></em>' map.
	 * The key is of type {@link com.mddoai.metamodel.pim.pimMM.VariableDeclaration},
	 * and the value is of type {@link com.mddoai.metamodel.pim.pimMM.Expression},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Environment Variables</em>' map.
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getPipelineBlock_EnvironmentVariables()
	 * @model mapType="com.mddoai.metamodel.pim.pimMM.Assignment&lt;com.mddoai.metamodel.pim.pimMM.VariableDeclaration, com.mddoai.metamodel.pim.pimMM.Expression&gt;"
	 * @generated
	 */
	EMap<VariableDeclaration, Expression> getEnvironmentVariables();

	/**
	 * Returns the value of the '<em><b>Timeout Minutes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timeout Minutes</em>' attribute.
	 * @see #setTimeoutMinutes(Integer)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getPipelineBlock_TimeoutMinutes()
	 * @model
	 * @generated
	 */
	Integer getTimeoutMinutes();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.PipelineBlock#getTimeoutMinutes <em>Timeout Minutes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timeout Minutes</em>' attribute.
	 * @see #getTimeoutMinutes()
	 * @generated
	 */
	void setTimeoutMinutes(Integer value);

	/**
	 * Returns the value of the '<em><b>Working Directory</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Working Directory</em>' containment reference.
	 * @see #setWorkingDirectory(Expression)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getPipelineBlock_WorkingDirectory()
	 * @model containment="true"
	 * @generated
	 */
	Expression getWorkingDirectory();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.PipelineBlock#getWorkingDirectory <em>Working Directory</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Working Directory</em>' containment reference.
	 * @see #getWorkingDirectory()
	 * @generated
	 */
	void setWorkingDirectory(Expression value);

	/**
	 * Returns the value of the '<em><b>Shell</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Shell</em>' containment reference.
	 * @see #setShell(Expression)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getPipelineBlock_Shell()
	 * @model containment="true"
	 * @generated
	 */
	Expression getShell();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.PipelineBlock#getShell <em>Shell</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Shell</em>' containment reference.
	 * @see #getShell()
	 * @generated
	 */
	void setShell(Expression value);

} // PipelineBlock
