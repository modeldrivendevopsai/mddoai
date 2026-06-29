/**
 */
package com.mddoai.metamodel.github.githubMM;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Container#getImage <em>Image</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Container#getUsername <em>Username</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Container#getPassword <em>Password</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Container#getEnvironmentVariables <em>Environment Variables</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Container#getPorts <em>Ports</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Container#getVolumes <em>Volumes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Container#getOptions <em>Options</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getContainer()
 * @model
 * @generated
 */
public interface Container extends EObject {
	/**
	 * Returns the value of the '<em><b>Image</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Image</em>' containment reference.
	 * @see #setImage(Expression)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getContainer_Image()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getImage();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.Container#getImage <em>Image</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Image</em>' containment reference.
	 * @see #getImage()
	 * @generated
	 */
	void setImage(Expression value);

	/**
	 * Returns the value of the '<em><b>Username</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Username</em>' containment reference.
	 * @see #setUsername(Expression)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getContainer_Username()
	 * @model containment="true"
	 * @generated
	 */
	Expression getUsername();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.Container#getUsername <em>Username</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Username</em>' containment reference.
	 * @see #getUsername()
	 * @generated
	 */
	void setUsername(Expression value);

	/**
	 * Returns the value of the '<em><b>Password</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Password</em>' containment reference.
	 * @see #setPassword(Expression)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getContainer_Password()
	 * @model containment="true"
	 * @generated
	 */
	Expression getPassword();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.Container#getPassword <em>Password</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Password</em>' containment reference.
	 * @see #getPassword()
	 * @generated
	 */
	void setPassword(Expression value);

	/**
	 * Returns the value of the '<em><b>Environment Variables</b></em>' map.
	 * The key is of type {@link com.mddoai.metamodel.github.githubMM.VariableDeclaration},
	 * and the value is of type {@link com.mddoai.metamodel.github.githubMM.Expression},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Environment Variables</em>' map.
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getContainer_EnvironmentVariables()
	 * @model mapType="com.mddoai.metamodel.github.githubMM.VariableAssignment&lt;com.mddoai.metamodel.github.githubMM.VariableDeclaration, com.mddoai.metamodel.github.githubMM.Expression&gt;"
	 * @generated
	 */
	EMap<VariableDeclaration, Expression> getEnvironmentVariables();

	/**
	 * Returns the value of the '<em><b>Ports</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.github.githubMM.Expression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ports</em>' containment reference list.
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getContainer_Ports()
	 * @model containment="true"
	 * @generated
	 */
	EList<Expression> getPorts();

	/**
	 * Returns the value of the '<em><b>Volumes</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.github.githubMM.Expression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Volumes</em>' containment reference list.
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getContainer_Volumes()
	 * @model containment="true"
	 * @generated
	 */
	EList<Expression> getVolumes();

	/**
	 * Returns the value of the '<em><b>Options</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Options</em>' containment reference.
	 * @see #setOptions(Expression)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getContainer_Options()
	 * @model containment="true"
	 * @generated
	 */
	Expression getOptions();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.Container#getOptions <em>Options</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Options</em>' containment reference.
	 * @see #getOptions()
	 * @generated
	 */
	void setOptions(Expression value);

} // Container
