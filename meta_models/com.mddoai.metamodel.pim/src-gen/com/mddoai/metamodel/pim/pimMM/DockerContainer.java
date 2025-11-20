/**
 */
package com.mddoai.metamodel.pim.pimMM;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Docker Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.DockerContainer#getLabel <em>Label</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.DockerContainer#getImage <em>Image</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.DockerContainer#getEnvironmentVariables <em>Environment Variables</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.DockerContainer#getVolumes <em>Volumes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.DockerContainer#getPorts <em>Ports</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.DockerContainer#getOptions <em>Options</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.DockerContainer#getRegistryUsername <em>Registry Username</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.DockerContainer#getRegistryPassword <em>Registry Password</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.DockerContainer#getNetwork <em>Network</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getDockerContainer()
 * @model
 * @generated
 */
public interface DockerContainer extends EObject {
	/**
	 * Returns the value of the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label</em>' attribute.
	 * @see #setLabel(String)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getDockerContainer_Label()
	 * @model
	 * @generated
	 */
	String getLabel();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.DockerContainer#getLabel <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label</em>' attribute.
	 * @see #getLabel()
	 * @generated
	 */
	void setLabel(String value);

	/**
	 * Returns the value of the '<em><b>Image</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Image</em>' containment reference.
	 * @see #setImage(Expression)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getDockerContainer_Image()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getImage();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.DockerContainer#getImage <em>Image</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Image</em>' containment reference.
	 * @see #getImage()
	 * @generated
	 */
	void setImage(Expression value);

	/**
	 * Returns the value of the '<em><b>Environment Variables</b></em>' map.
	 * The key is of type {@link com.mddoai.metamodel.pim.pimMM.VariableDeclaration},
	 * and the value is of type {@link com.mddoai.metamodel.pim.pimMM.Expression},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Environment Variables</em>' map.
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getDockerContainer_EnvironmentVariables()
	 * @model mapType="com.mddoai.metamodel.pim.pimMM.Assignment&lt;com.mddoai.metamodel.pim.pimMM.VariableDeclaration, com.mddoai.metamodel.pim.pimMM.Expression&gt;"
	 * @generated
	 */
	EMap<VariableDeclaration, Expression> getEnvironmentVariables();

	/**
	 * Returns the value of the '<em><b>Volumes</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.pim.pimMM.Expression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Volumes</em>' containment reference list.
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getDockerContainer_Volumes()
	 * @model containment="true"
	 * @generated
	 */
	EList<Expression> getVolumes();

	/**
	 * Returns the value of the '<em><b>Ports</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.pim.pimMM.Expression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ports</em>' containment reference list.
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getDockerContainer_Ports()
	 * @model containment="true"
	 * @generated
	 */
	EList<Expression> getPorts();

	/**
	 * Returns the value of the '<em><b>Options</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Options</em>' attribute.
	 * @see #setOptions(String)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getDockerContainer_Options()
	 * @model
	 * @generated
	 */
	String getOptions();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.DockerContainer#getOptions <em>Options</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Options</em>' attribute.
	 * @see #getOptions()
	 * @generated
	 */
	void setOptions(String value);

	/**
	 * Returns the value of the '<em><b>Registry Username</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Registry Username</em>' reference.
	 * @see #setRegistryUsername(Expression)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getDockerContainer_RegistryUsername()
	 * @model
	 * @generated
	 */
	Expression getRegistryUsername();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.DockerContainer#getRegistryUsername <em>Registry Username</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Registry Username</em>' reference.
	 * @see #getRegistryUsername()
	 * @generated
	 */
	void setRegistryUsername(Expression value);

	/**
	 * Returns the value of the '<em><b>Registry Password</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Registry Password</em>' reference.
	 * @see #setRegistryPassword(Expression)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getDockerContainer_RegistryPassword()
	 * @model
	 * @generated
	 */
	Expression getRegistryPassword();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.DockerContainer#getRegistryPassword <em>Registry Password</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Registry Password</em>' reference.
	 * @see #getRegistryPassword()
	 * @generated
	 */
	void setRegistryPassword(Expression value);

	/**
	 * Returns the value of the '<em><b>Network</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Network</em>' reference.
	 * @see #setNetwork(Expression)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getDockerContainer_Network()
	 * @model
	 * @generated
	 */
	Expression getNetwork();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.DockerContainer#getNetwork <em>Network</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Network</em>' reference.
	 * @see #getNetwork()
	 * @generated
	 */
	void setNetwork(Expression value);

} // DockerContainer
