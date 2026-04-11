/**
 */
package com.mddoai.metamodel.bambooMM;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Other Config</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.OtherConfig#getConcurrentBuildPlugin <em>Concurrent Build Plugin</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.OtherConfig#getCleanWorkingDir <em>Clean Working Dir</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.OtherConfig#getAllOtherApps <em>All Other Apps</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getOtherConfig()
 * @model
 * @generated
 */
public interface OtherConfig extends EObject {
	/**
	 * Returns the value of the '<em><b>Concurrent Build Plugin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Concurrent Build Plugin</em>' attribute.
	 * @see #setConcurrentBuildPlugin(Integer)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getOtherConfig_ConcurrentBuildPlugin()
	 * @model
	 * @generated
	 */
	Integer getConcurrentBuildPlugin();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.OtherConfig#getConcurrentBuildPlugin <em>Concurrent Build Plugin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Concurrent Build Plugin</em>' attribute.
	 * @see #getConcurrentBuildPlugin()
	 * @generated
	 */
	void setConcurrentBuildPlugin(Integer value);

	/**
	 * Returns the value of the '<em><b>Clean Working Dir</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Clean Working Dir</em>' attribute.
	 * @see #setCleanWorkingDir(Boolean)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getOtherConfig_CleanWorkingDir()
	 * @model
	 * @generated
	 */
	Boolean getCleanWorkingDir();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.OtherConfig#getCleanWorkingDir <em>Clean Working Dir</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Clean Working Dir</em>' attribute.
	 * @see #getCleanWorkingDir()
	 * @generated
	 */
	void setCleanWorkingDir(Boolean value);

	/**
	 * Returns the value of the '<em><b>All Other Apps</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>All Other Apps</em>' map.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getOtherConfig_AllOtherApps()
	 * @model mapType="com.mddoai.metamodel.bambooMM.VariableAssignment&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;"
	 * @generated
	 */
	EMap<String, String> getAllOtherApps();

} // OtherConfig
