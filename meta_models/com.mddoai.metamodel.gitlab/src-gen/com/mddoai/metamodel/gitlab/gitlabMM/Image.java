/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Image</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Image#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Image#getEntrypoint <em>Entrypoint</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Image#getDocker <em>Docker</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Image#getKubernetes <em>Kubernetes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Image#getPull_policy <em>Pull policy</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getImage()
 * @model
 * @generated
 */
public interface Image extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getImage_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Image#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Entrypoint</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entrypoint</em>' attribute list.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getImage_Entrypoint()
	 * @model
	 * @generated
	 */
	EList<String> getEntrypoint();

	/**
	 * Returns the value of the '<em><b>Docker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Docker</em>' containment reference.
	 * @see #setDocker(Docker)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getImage_Docker()
	 * @model containment="true"
	 * @generated
	 */
	Docker getDocker();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Image#getDocker <em>Docker</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Docker</em>' containment reference.
	 * @see #getDocker()
	 * @generated
	 */
	void setDocker(Docker value);

	/**
	 * Returns the value of the '<em><b>Kubernetes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kubernetes</em>' containment reference.
	 * @see #setKubernetes(Kubernetes)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getImage_Kubernetes()
	 * @model containment="true"
	 * @generated
	 */
	Kubernetes getKubernetes();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Image#getKubernetes <em>Kubernetes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kubernetes</em>' containment reference.
	 * @see #getKubernetes()
	 * @generated
	 */
	void setKubernetes(Kubernetes value);

	/**
	 * Returns the value of the '<em><b>Pull policy</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pull policy</em>' attribute list.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getImage_Pull_policy()
	 * @model
	 * @generated
	 */
	EList<String> getPull_policy();

} // Image
