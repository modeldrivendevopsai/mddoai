/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Job</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getStage <em>Stage</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getArtifacts <em>Artifacts</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getScript <em>Script</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getBeforeScript <em>Before Script</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getTags <em>Tags</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getOnly <em>Only</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getDependencies <em>Dependencies</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getWhen <em>When</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getImage <em>Image</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#isAllowFailure <em>Allow Failure</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getTimeout <em>Timeout</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#isInterruptible <em>Interruptible</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getResourceGroup <em>Resource Group</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getAfterScript <em>After Script</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getCache <em>Cache</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getRules <em>Rules</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getNeeds <em>Needs</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getServices <em>Services</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getEnvironment <em>Environment</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getRetry <em>Retry</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getVariables <em>Variables</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getParallel <em>Parallel</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getJob()
 * @model
 * @generated
 */
public interface Job extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getJob_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Stage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stage</em>' attribute.
	 * @see #setStage(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getJob_Stage()
	 * @model required="true"
	 * @generated
	 */
	String getStage();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getStage <em>Stage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stage</em>' attribute.
	 * @see #getStage()
	 * @generated
	 */
	void setStage(String value);

	/**
	 * Returns the value of the '<em><b>Artifacts</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Artifacts</em>' containment reference.
	 * @see #setArtifacts(Artifact)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getJob_Artifacts()
	 * @model containment="true"
	 * @generated
	 */
	Artifact getArtifacts();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getArtifacts <em>Artifacts</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Artifacts</em>' containment reference.
	 * @see #getArtifacts()
	 * @generated
	 */
	void setArtifacts(Artifact value);

	/**
	 * Returns the value of the '<em><b>Script</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Script</em>' containment reference.
	 * @see #setScript(Script)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getJob_Script()
	 * @model containment="true"
	 * @generated
	 */
	Script getScript();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getScript <em>Script</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Script</em>' containment reference.
	 * @see #getScript()
	 * @generated
	 */
	void setScript(Script value);

	/**
	 * Returns the value of the '<em><b>Before Script</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Before Script</em>' containment reference.
	 * @see #setBeforeScript(BeforeScript)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getJob_BeforeScript()
	 * @model containment="true"
	 * @generated
	 */
	BeforeScript getBeforeScript();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getBeforeScript <em>Before Script</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Before Script</em>' containment reference.
	 * @see #getBeforeScript()
	 * @generated
	 */
	void setBeforeScript(BeforeScript value);

	/**
	 * Returns the value of the '<em><b>Tags</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tags</em>' containment reference.
	 * @see #setTags(Tags)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getJob_Tags()
	 * @model containment="true"
	 * @generated
	 */
	Tags getTags();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getTags <em>Tags</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tags</em>' containment reference.
	 * @see #getTags()
	 * @generated
	 */
	void setTags(Tags value);

	/**
	 * Returns the value of the '<em><b>Only</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Only</em>' containment reference.
	 * @see #setOnly(Only)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getJob_Only()
	 * @model containment="true"
	 * @generated
	 */
	Only getOnly();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getOnly <em>Only</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Only</em>' containment reference.
	 * @see #getOnly()
	 * @generated
	 */
	void setOnly(Only value);

	/**
	 * Returns the value of the '<em><b>Dependencies</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dependencies</em>' containment reference.
	 * @see #setDependencies(Dependencies)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getJob_Dependencies()
	 * @model containment="true"
	 * @generated
	 */
	Dependencies getDependencies();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getDependencies <em>Dependencies</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dependencies</em>' containment reference.
	 * @see #getDependencies()
	 * @generated
	 */
	void setDependencies(Dependencies value);

	/**
	 * Returns the value of the '<em><b>When</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>When</em>' attribute.
	 * @see #setWhen(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getJob_When()
	 * @model
	 * @generated
	 */
	String getWhen();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getWhen <em>When</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>When</em>' attribute.
	 * @see #getWhen()
	 * @generated
	 */
	void setWhen(String value);

	/**
	 * Returns the value of the '<em><b>Image</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Image</em>' containment reference.
	 * @see #setImage(Image)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getJob_Image()
	 * @model containment="true"
	 * @generated
	 */
	Image getImage();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getImage <em>Image</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Image</em>' containment reference.
	 * @see #getImage()
	 * @generated
	 */
	void setImage(Image value);

	/**
	 * Returns the value of the '<em><b>Allow Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Allow Failure</em>' attribute.
	 * @see #setAllowFailure(boolean)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getJob_AllowFailure()
	 * @model
	 * @generated
	 */
	boolean isAllowFailure();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#isAllowFailure <em>Allow Failure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Allow Failure</em>' attribute.
	 * @see #isAllowFailure()
	 * @generated
	 */
	void setAllowFailure(boolean value);

	/**
	 * Returns the value of the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timeout</em>' attribute.
	 * @see #setTimeout(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getJob_Timeout()
	 * @model
	 * @generated
	 */
	String getTimeout();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getTimeout <em>Timeout</em>}' attribute.
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
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getJob_Interruptible()
	 * @model
	 * @generated
	 */
	boolean isInterruptible();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#isInterruptible <em>Interruptible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interruptible</em>' attribute.
	 * @see #isInterruptible()
	 * @generated
	 */
	void setInterruptible(boolean value);

	/**
	 * Returns the value of the '<em><b>Resource Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Group</em>' attribute.
	 * @see #setResourceGroup(String)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getJob_ResourceGroup()
	 * @model
	 * @generated
	 */
	String getResourceGroup();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getResourceGroup <em>Resource Group</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource Group</em>' attribute.
	 * @see #getResourceGroup()
	 * @generated
	 */
	void setResourceGroup(String value);

	/**
	 * Returns the value of the '<em><b>After Script</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>After Script</em>' containment reference.
	 * @see #setAfterScript(AfterScript)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getJob_AfterScript()
	 * @model containment="true"
	 * @generated
	 */
	AfterScript getAfterScript();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getAfterScript <em>After Script</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>After Script</em>' containment reference.
	 * @see #getAfterScript()
	 * @generated
	 */
	void setAfterScript(AfterScript value);

	/**
	 * Returns the value of the '<em><b>Cache</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cache</em>' containment reference.
	 * @see #setCache(Cache)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getJob_Cache()
	 * @model containment="true"
	 * @generated
	 */
	Cache getCache();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getCache <em>Cache</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cache</em>' containment reference.
	 * @see #getCache()
	 * @generated
	 */
	void setCache(Cache value);

	/**
	 * Returns the value of the '<em><b>Rules</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.gitlab.gitlabMM.GitlabRule}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rules</em>' containment reference list.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getJob_Rules()
	 * @model containment="true"
	 * @generated
	 */
	EList<GitlabRule> getRules();

	/**
	 * Returns the value of the '<em><b>Needs</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.gitlab.gitlabMM.Need}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Needs</em>' containment reference list.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getJob_Needs()
	 * @model containment="true"
	 * @generated
	 */
	EList<Need> getNeeds();

	/**
	 * Returns the value of the '<em><b>Services</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.gitlab.gitlabMM.Service}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Services</em>' containment reference list.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getJob_Services()
	 * @model containment="true"
	 * @generated
	 */
	EList<Service> getServices();

	/**
	 * Returns the value of the '<em><b>Environment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Environment</em>' containment reference.
	 * @see #setEnvironment(Environment)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getJob_Environment()
	 * @model containment="true"
	 * @generated
	 */
	Environment getEnvironment();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getEnvironment <em>Environment</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Environment</em>' containment reference.
	 * @see #getEnvironment()
	 * @generated
	 */
	void setEnvironment(Environment value);

	/**
	 * Returns the value of the '<em><b>Retry</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Retry</em>' containment reference.
	 * @see #setRetry(Retry)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getJob_Retry()
	 * @model containment="true"
	 * @generated
	 */
	Retry getRetry();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getRetry <em>Retry</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Retry</em>' containment reference.
	 * @see #getRetry()
	 * @generated
	 */
	void setRetry(Retry value);

	/**
	 * Returns the value of the '<em><b>Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variables</em>' containment reference.
	 * @see #setVariables(Variables)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getJob_Variables()
	 * @model containment="true"
	 * @generated
	 */
	Variables getVariables();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getVariables <em>Variables</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variables</em>' containment reference.
	 * @see #getVariables()
	 * @generated
	 */
	void setVariables(Variables value);

	/**
	 * Returns the value of the '<em><b>Parallel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parallel</em>' containment reference.
	 * @see #setParallel(Parallel)
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage#getJob_Parallel()
	 * @model containment="true"
	 * @generated
	 */
	Parallel getParallel();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getParallel <em>Parallel</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parallel</em>' containment reference.
	 * @see #getParallel()
	 * @generated
	 */
	void setParallel(Parallel value);

} // Job
