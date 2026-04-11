/**
 */
package com.mddoai.metamodel.bambooMM;

import org.eclipse.emf.common.util.EMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Any Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.AnyTask#getPluginKey <em>Plugin Key</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.AnyTask#getConfiguration <em>Configuration</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getAnyTask()
 * @model
 * @generated
 */
public interface AnyTask extends Task {
	/**
	 * Returns the value of the '<em><b>Plugin Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Plugin Key</em>' attribute.
	 * @see #setPluginKey(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getAnyTask_PluginKey()
	 * @model required="true"
	 * @generated
	 */
	String getPluginKey();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.AnyTask#getPluginKey <em>Plugin Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Plugin Key</em>' attribute.
	 * @see #getPluginKey()
	 * @generated
	 */
	void setPluginKey(String value);

	/**
	 * Returns the value of the '<em><b>Configuration</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Configuration</em>' map.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getAnyTask_Configuration()
	 * @model mapType="com.mddoai.metamodel.bambooMM.VariableAssignment&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;"
	 * @generated
	 */
	EMap<String, String> getConfiguration();

} // AnyTask
