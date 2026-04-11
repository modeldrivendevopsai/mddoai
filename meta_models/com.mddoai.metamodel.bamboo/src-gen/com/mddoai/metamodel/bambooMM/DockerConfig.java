/**
 */
package com.mddoai.metamodel.bambooMM;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Docker Config</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.DockerConfig#getImage <em>Image</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.DockerConfig#getVolumes <em>Volumes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.DockerConfig#isUseDefaultVolumes <em>Use Default Volumes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.DockerConfig#getDockerRunArguments <em>Docker Run Arguments</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getDockerConfig()
 * @model
 * @generated
 */
public interface DockerConfig extends EObject {
	/**
	 * Returns the value of the '<em><b>Image</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Image</em>' attribute.
	 * @see #setImage(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getDockerConfig_Image()
	 * @model required="true"
	 * @generated
	 */
	String getImage();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.DockerConfig#getImage <em>Image</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Image</em>' attribute.
	 * @see #getImage()
	 * @generated
	 */
	void setImage(String value);

	/**
	 * Returns the value of the '<em><b>Volumes</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Volumes</em>' map.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getDockerConfig_Volumes()
	 * @model mapType="com.mddoai.metamodel.bambooMM.VariableAssignment&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;"
	 * @generated
	 */
	EMap<String, String> getVolumes();

	/**
	 * Returns the value of the '<em><b>Use Default Volumes</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Use Default Volumes</em>' attribute.
	 * @see #setUseDefaultVolumes(boolean)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getDockerConfig_UseDefaultVolumes()
	 * @model default="true"
	 * @generated
	 */
	boolean isUseDefaultVolumes();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.DockerConfig#isUseDefaultVolumes <em>Use Default Volumes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Use Default Volumes</em>' attribute.
	 * @see #isUseDefaultVolumes()
	 * @generated
	 */
	void setUseDefaultVolumes(boolean value);

	/**
	 * Returns the value of the '<em><b>Docker Run Arguments</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Docker Run Arguments</em>' attribute list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getDockerConfig_DockerRunArguments()
	 * @model
	 * @generated
	 */
	EList<String> getDockerRunArguments();

} // DockerConfig
