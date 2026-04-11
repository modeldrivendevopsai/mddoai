/**
 */
package com.mddoai.metamodel.bambooMM;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Script Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.ScriptTask#getInterpreter <em>Interpreter</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.ScriptTask#getScripts <em>Scripts</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.ScriptTask#getFile <em>File</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.ScriptTask#getArgument <em>Argument</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.ScriptTask#getEnvironment <em>Environment</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.ScriptTask#getWorkingDir <em>Working Dir</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getScriptTask()
 * @model
 * @generated
 */
public interface ScriptTask extends Task {
	/**
	 * Returns the value of the '<em><b>Interpreter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interpreter</em>' attribute.
	 * @see #setInterpreter(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getScriptTask_Interpreter()
	 * @model
	 * @generated
	 */
	String getInterpreter();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.ScriptTask#getInterpreter <em>Interpreter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interpreter</em>' attribute.
	 * @see #getInterpreter()
	 * @generated
	 */
	void setInterpreter(String value);

	/**
	 * Returns the value of the '<em><b>Scripts</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scripts</em>' attribute list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getScriptTask_Scripts()
	 * @model
	 * @generated
	 */
	EList<String> getScripts();

	/**
	 * Returns the value of the '<em><b>File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>File</em>' attribute.
	 * @see #setFile(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getScriptTask_File()
	 * @model
	 * @generated
	 */
	String getFile();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.ScriptTask#getFile <em>File</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File</em>' attribute.
	 * @see #getFile()
	 * @generated
	 */
	void setFile(String value);

	/**
	 * Returns the value of the '<em><b>Argument</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Argument</em>' attribute.
	 * @see #setArgument(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getScriptTask_Argument()
	 * @model
	 * @generated
	 */
	String getArgument();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.ScriptTask#getArgument <em>Argument</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Argument</em>' attribute.
	 * @see #getArgument()
	 * @generated
	 */
	void setArgument(String value);

	/**
	 * Returns the value of the '<em><b>Environment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Environment</em>' attribute.
	 * @see #setEnvironment(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getScriptTask_Environment()
	 * @model
	 * @generated
	 */
	String getEnvironment();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.ScriptTask#getEnvironment <em>Environment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Environment</em>' attribute.
	 * @see #getEnvironment()
	 * @generated
	 */
	void setEnvironment(String value);

	/**
	 * Returns the value of the '<em><b>Working Dir</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Working Dir</em>' attribute.
	 * @see #setWorkingDir(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getScriptTask_WorkingDir()
	 * @model
	 * @generated
	 */
	String getWorkingDir();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.ScriptTask#getWorkingDir <em>Working Dir</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Working Dir</em>' attribute.
	 * @see #getWorkingDir()
	 * @generated
	 */
	void setWorkingDir(String value);

} // ScriptTask
