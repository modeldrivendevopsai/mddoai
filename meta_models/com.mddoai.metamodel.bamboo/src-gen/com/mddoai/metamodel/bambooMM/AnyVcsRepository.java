/**
 */
package com.mddoai.metamodel.bambooMM;

import org.eclipse.emf.common.util.EMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Any Vcs Repository</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.AnyVcsRepository#getPluginKey <em>Plugin Key</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.AnyVcsRepository#getServerConfig <em>Server Config</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.AnyVcsRepository#getBranchConfig <em>Branch Config</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getAnyVcsRepository()
 * @model
 * @generated
 */
public interface AnyVcsRepository extends Repository {
	/**
	 * Returns the value of the '<em><b>Plugin Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Plugin Key</em>' attribute.
	 * @see #setPluginKey(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getAnyVcsRepository_PluginKey()
	 * @model required="true"
	 * @generated
	 */
	String getPluginKey();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.AnyVcsRepository#getPluginKey <em>Plugin Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Plugin Key</em>' attribute.
	 * @see #getPluginKey()
	 * @generated
	 */
	void setPluginKey(String value);

	/**
	 * Returns the value of the '<em><b>Server Config</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Server Config</em>' map.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getAnyVcsRepository_ServerConfig()
	 * @model mapType="com.mddoai.metamodel.bambooMM.VariableAssignment&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;"
	 * @generated
	 */
	EMap<String, String> getServerConfig();

	/**
	 * Returns the value of the '<em><b>Branch Config</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Branch Config</em>' map.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getAnyVcsRepository_BranchConfig()
	 * @model mapType="com.mddoai.metamodel.bambooMM.VariableAssignment&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;"
	 * @generated
	 */
	EMap<String, String> getBranchConfig();

} // AnyVcsRepository
