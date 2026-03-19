/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Environment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Environment#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Environment#getUrl <em>Url</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Environment#getOnStop <em>On Stop</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Environment#getAction <em>Action</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Environment#getAutoStopIn <em>Auto Stop In</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Environment#getKubernetes <em>Kubernetes</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getEnvironment()
 * @model
 * @generated
 */
public interface Environment extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getEnvironment_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Environment#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Url</em>' attribute.
	 * @see #setUrl(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getEnvironment_Url()
	 * @model
	 * @generated
	 */
	String getUrl();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Environment#getUrl <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Url</em>' attribute.
	 * @see #getUrl()
	 * @generated
	 */
	void setUrl(String value);

	/**
	 * Returns the value of the '<em><b>On Stop</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Stop</em>' attribute.
	 * @see #setOnStop(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getEnvironment_OnStop()
	 * @model
	 * @generated
	 */
	String getOnStop();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Environment#getOnStop <em>On Stop</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Stop</em>' attribute.
	 * @see #getOnStop()
	 * @generated
	 */
	void setOnStop(String value);

	/**
	 * Returns the value of the '<em><b>Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Action</em>' attribute.
	 * @see #setAction(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getEnvironment_Action()
	 * @model
	 * @generated
	 */
	String getAction();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Environment#getAction <em>Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action</em>' attribute.
	 * @see #getAction()
	 * @generated
	 */
	void setAction(String value);

	/**
	 * Returns the value of the '<em><b>Auto Stop In</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Auto Stop In</em>' attribute.
	 * @see #setAutoStopIn(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getEnvironment_AutoStopIn()
	 * @model
	 * @generated
	 */
	String getAutoStopIn();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Environment#getAutoStopIn <em>Auto Stop In</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Auto Stop In</em>' attribute.
	 * @see #getAutoStopIn()
	 * @generated
	 */
	void setAutoStopIn(String value);

	/**
	 * Returns the value of the '<em><b>Kubernetes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kubernetes</em>' containment reference.
	 * @see #setKubernetes(Kubernetes)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getEnvironment_Kubernetes()
	 * @model containment="true"
	 * @generated
	 */
	Kubernetes getKubernetes();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Environment#getKubernetes <em>Kubernetes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kubernetes</em>' containment reference.
	 * @see #getKubernetes()
	 * @generated
	 */
	void setKubernetes(Kubernetes value);

} // Environment
