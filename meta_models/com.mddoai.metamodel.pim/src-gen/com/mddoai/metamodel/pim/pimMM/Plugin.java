/**
 */
package com.mddoai.metamodel.pim.pimMM;

import org.eclipse.emf.common.util.EMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Plugin</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Plugin#getPluginName <em>Plugin Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Plugin#getVersion <em>Version</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Plugin#getKwargs <em>Kwargs</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getPlugin()
 * @model
 * @generated
 */
public interface Plugin extends NonConditionalStep {
	/**
	 * Returns the value of the '<em><b>Plugin Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Plugin Name</em>' attribute.
	 * @see #setPluginName(String)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getPlugin_PluginName()
	 * @model required="true"
	 * @generated
	 */
	String getPluginName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.Plugin#getPluginName <em>Plugin Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Plugin Name</em>' attribute.
	 * @see #getPluginName()
	 * @generated
	 */
	void setPluginName(String value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getPlugin_Version()
	 * @model required="true"
	 * @generated
	 */
	String getVersion();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.Plugin#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

	/**
	 * Returns the value of the '<em><b>Kwargs</b></em>' map.
	 * The key is of type {@link com.mddoai.metamodel.pim.pimMM.VariableDeclaration},
	 * and the value is of type {@link com.mddoai.metamodel.pim.pimMM.Expression},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kwargs</em>' map.
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getPlugin_Kwargs()
	 * @model mapType="com.mddoai.metamodel.pim.pimMM.Assignment&lt;com.mddoai.metamodel.pim.pimMM.VariableDeclaration, com.mddoai.metamodel.pim.pimMM.Expression&gt;"
	 * @generated
	 */
	EMap<VariableDeclaration, Expression> getKwargs();

} // Plugin
