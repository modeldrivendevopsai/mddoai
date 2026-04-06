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
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Image#getPullPolicy <em>Pull Policy</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Image#getDocker <em>Docker</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Image#getKubernetes <em>Kubernetes</em>}</li>
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
	 * Returns the value of the '<em><b>Pull Policy</b></em>' attribute.
	 * The literals are from the enumeration {@link com.mddoai.metamodel.gitlab.gitlabMM.PullPolicy}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pull Policy</em>' attribute.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.PullPolicy
	 * @see #setPullPolicy(PullPolicy)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getImage_PullPolicy()
	 * @model
	 * @generated
	 */
	PullPolicy getPullPolicy();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Image#getPullPolicy <em>Pull Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pull Policy</em>' attribute.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.PullPolicy
	 * @see #getPullPolicy()
	 * @generated
	 */
	void setPullPolicy(PullPolicy value);

	/**
	 * Returns the value of the '<em><b>Docker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Docker</em>' containment reference.
	 * @see #setDocker(DockerOptions)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getImage_Docker()
	 * @model containment="true"
	 * @generated
	 */
	DockerOptions getDocker();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Image#getDocker <em>Docker</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Docker</em>' containment reference.
	 * @see #getDocker()
	 * @generated
	 */
	void setDocker(DockerOptions value);

	/**
	 * Returns the value of the '<em><b>Kubernetes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kubernetes</em>' containment reference.
	 * @see #setKubernetes(KubernetesOptions)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getImage_Kubernetes()
	 * @model containment="true"
	 * @generated
	 */
	KubernetesOptions getKubernetes();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Image#getKubernetes <em>Kubernetes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kubernetes</em>' containment reference.
	 * @see #getKubernetes()
	 * @generated
	 */
	void setKubernetes(KubernetesOptions value);

} // Image
