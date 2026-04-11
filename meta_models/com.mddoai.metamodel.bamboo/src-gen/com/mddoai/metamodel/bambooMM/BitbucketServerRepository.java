/**
 */
package com.mddoai.metamodel.bambooMM;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Bitbucket Server Repository</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getServer <em>Server</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getProject <em>Project</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getSlug <em>Slug</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getCloneUrl <em>Clone Url</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getPublicKey <em>Public Key</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getPrivateKey <em>Private Key</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getBranch <em>Branch</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getLfs <em>Lfs</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getUseShallowClones <em>Use Shallow Clones</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getSubmodules <em>Submodules</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getCommandTimeoutMinutes <em>Command Timeout Minutes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getFetchAll <em>Fetch All</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBitbucketServerRepository()
 * @model
 * @generated
 */
public interface BitbucketServerRepository extends Repository {
	/**
	 * Returns the value of the '<em><b>Server</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Server</em>' attribute.
	 * @see #setServer(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBitbucketServerRepository_Server()
	 * @model required="true"
	 * @generated
	 */
	String getServer();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getServer <em>Server</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Server</em>' attribute.
	 * @see #getServer()
	 * @generated
	 */
	void setServer(String value);

	/**
	 * Returns the value of the '<em><b>Project</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Project</em>' attribute.
	 * @see #setProject(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBitbucketServerRepository_Project()
	 * @model required="true"
	 * @generated
	 */
	String getProject();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getProject <em>Project</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Project</em>' attribute.
	 * @see #getProject()
	 * @generated
	 */
	void setProject(String value);

	/**
	 * Returns the value of the '<em><b>Slug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Slug</em>' attribute.
	 * @see #setSlug(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBitbucketServerRepository_Slug()
	 * @model required="true"
	 * @generated
	 */
	String getSlug();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getSlug <em>Slug</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Slug</em>' attribute.
	 * @see #getSlug()
	 * @generated
	 */
	void setSlug(String value);

	/**
	 * Returns the value of the '<em><b>Clone Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Clone Url</em>' attribute.
	 * @see #setCloneUrl(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBitbucketServerRepository_CloneUrl()
	 * @model required="true"
	 * @generated
	 */
	String getCloneUrl();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getCloneUrl <em>Clone Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Clone Url</em>' attribute.
	 * @see #getCloneUrl()
	 * @generated
	 */
	void setCloneUrl(String value);

	/**
	 * Returns the value of the '<em><b>Public Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Public Key</em>' attribute.
	 * @see #setPublicKey(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBitbucketServerRepository_PublicKey()
	 * @model
	 * @generated
	 */
	String getPublicKey();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getPublicKey <em>Public Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Public Key</em>' attribute.
	 * @see #getPublicKey()
	 * @generated
	 */
	void setPublicKey(String value);

	/**
	 * Returns the value of the '<em><b>Private Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Private Key</em>' attribute.
	 * @see #setPrivateKey(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBitbucketServerRepository_PrivateKey()
	 * @model
	 * @generated
	 */
	String getPrivateKey();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getPrivateKey <em>Private Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Private Key</em>' attribute.
	 * @see #getPrivateKey()
	 * @generated
	 */
	void setPrivateKey(String value);

	/**
	 * Returns the value of the '<em><b>Branch</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Branch</em>' attribute.
	 * @see #setBranch(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBitbucketServerRepository_Branch()
	 * @model required="true"
	 * @generated
	 */
	String getBranch();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getBranch <em>Branch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Branch</em>' attribute.
	 * @see #getBranch()
	 * @generated
	 */
	void setBranch(String value);

	/**
	 * Returns the value of the '<em><b>Lfs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lfs</em>' attribute.
	 * @see #setLfs(Boolean)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBitbucketServerRepository_Lfs()
	 * @model
	 * @generated
	 */
	Boolean getLfs();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getLfs <em>Lfs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lfs</em>' attribute.
	 * @see #getLfs()
	 * @generated
	 */
	void setLfs(Boolean value);

	/**
	 * Returns the value of the '<em><b>Use Shallow Clones</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Use Shallow Clones</em>' attribute.
	 * @see #setUseShallowClones(Boolean)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBitbucketServerRepository_UseShallowClones()
	 * @model
	 * @generated
	 */
	Boolean getUseShallowClones();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getUseShallowClones <em>Use Shallow Clones</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Use Shallow Clones</em>' attribute.
	 * @see #getUseShallowClones()
	 * @generated
	 */
	void setUseShallowClones(Boolean value);

	/**
	 * Returns the value of the '<em><b>Submodules</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Submodules</em>' attribute.
	 * @see #setSubmodules(Boolean)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBitbucketServerRepository_Submodules()
	 * @model
	 * @generated
	 */
	Boolean getSubmodules();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getSubmodules <em>Submodules</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Submodules</em>' attribute.
	 * @see #getSubmodules()
	 * @generated
	 */
	void setSubmodules(Boolean value);

	/**
	 * Returns the value of the '<em><b>Command Timeout Minutes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Command Timeout Minutes</em>' attribute.
	 * @see #setCommandTimeoutMinutes(Integer)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBitbucketServerRepository_CommandTimeoutMinutes()
	 * @model
	 * @generated
	 */
	Integer getCommandTimeoutMinutes();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getCommandTimeoutMinutes <em>Command Timeout Minutes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Command Timeout Minutes</em>' attribute.
	 * @see #getCommandTimeoutMinutes()
	 * @generated
	 */
	void setCommandTimeoutMinutes(Integer value);

	/**
	 * Returns the value of the '<em><b>Fetch All</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fetch All</em>' attribute.
	 * @see #setFetchAll(Boolean)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBitbucketServerRepository_FetchAll()
	 * @model
	 * @generated
	 */
	Boolean getFetchAll();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository#getFetchAll <em>Fetch All</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fetch All</em>' attribute.
	 * @see #getFetchAll()
	 * @generated
	 */
	void setFetchAll(Boolean value);

} // BitbucketServerRepository
