/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Default</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Default#getImage <em>Image</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Default#getServices <em>Services</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Default#getTags <em>Tags</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Default#getCache <em>Cache</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Default#getBeforeScript <em>Before Script</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Default#getAfterScript <em>After Script</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Default#getTimeout <em>Timeout</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Default#isInterruptible <em>Interruptible</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Default#getRetry <em>Retry</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getDefault()
 * @model
 * @generated
 */
public interface Default extends EObject {
	/**
	 * Returns the value of the '<em><b>Image</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Image</em>' containment reference.
	 * @see #setImage(Image)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getDefault_Image()
	 * @model containment="true"
	 * @generated
	 */
	Image getImage();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Default#getImage <em>Image</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Image</em>' containment reference.
	 * @see #getImage()
	 * @generated
	 */
	void setImage(Image value);

	/**
	 * Returns the value of the '<em><b>Services</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.gitlab.gitlabMM.Service}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Services</em>' containment reference list.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getDefault_Services()
	 * @model containment="true"
	 * @generated
	 */
	EList<Service> getServices();

	/**
	 * Returns the value of the '<em><b>Tags</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tags</em>' containment reference.
	 * @see #setTags(Tags)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getDefault_Tags()
	 * @model containment="true"
	 * @generated
	 */
	Tags getTags();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Default#getTags <em>Tags</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tags</em>' containment reference.
	 * @see #getTags()
	 * @generated
	 */
	void setTags(Tags value);

	/**
	 * Returns the value of the '<em><b>Cache</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cache</em>' containment reference.
	 * @see #setCache(Cache)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getDefault_Cache()
	 * @model containment="true"
	 * @generated
	 */
	Cache getCache();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Default#getCache <em>Cache</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cache</em>' containment reference.
	 * @see #getCache()
	 * @generated
	 */
	void setCache(Cache value);

	/**
	 * Returns the value of the '<em><b>Before Script</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Before Script</em>' containment reference.
	 * @see #setBeforeScript(BeforeScript)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getDefault_BeforeScript()
	 * @model containment="true"
	 * @generated
	 */
	BeforeScript getBeforeScript();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Default#getBeforeScript <em>Before Script</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Before Script</em>' containment reference.
	 * @see #getBeforeScript()
	 * @generated
	 */
	void setBeforeScript(BeforeScript value);

	/**
	 * Returns the value of the '<em><b>After Script</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>After Script</em>' containment reference.
	 * @see #setAfterScript(AfterScript)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getDefault_AfterScript()
	 * @model containment="true"
	 * @generated
	 */
	AfterScript getAfterScript();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Default#getAfterScript <em>After Script</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>After Script</em>' containment reference.
	 * @see #getAfterScript()
	 * @generated
	 */
	void setAfterScript(AfterScript value);

	/**
	 * Returns the value of the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timeout</em>' attribute.
	 * @see #setTimeout(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getDefault_Timeout()
	 * @model
	 * @generated
	 */
	String getTimeout();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Default#getTimeout <em>Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timeout</em>' attribute.
	 * @see #getTimeout()
	 * @generated
	 */
	void setTimeout(String value);

	/**
	 * Returns the value of the '<em><b>Interruptible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interruptible</em>' attribute.
	 * @see #setInterruptible(boolean)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getDefault_Interruptible()
	 * @model
	 * @generated
	 */
	boolean isInterruptible();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Default#isInterruptible <em>Interruptible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interruptible</em>' attribute.
	 * @see #isInterruptible()
	 * @generated
	 */
	void setInterruptible(boolean value);

	/**
	 * Returns the value of the '<em><b>Retry</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Retry</em>' containment reference.
	 * @see #setRetry(Retry)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getDefault_Retry()
	 * @model containment="true"
	 * @generated
	 */
	Retry getRetry();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Default#getRetry <em>Retry</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Retry</em>' containment reference.
	 * @see #getRetry()
	 * @generated
	 */
	void setRetry(Retry value);

} // Default
