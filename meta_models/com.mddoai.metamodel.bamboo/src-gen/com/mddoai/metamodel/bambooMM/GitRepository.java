/**
 */
package com.mddoai.metamodel.bambooMM;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Git Repository</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.GitRepository#getUrl <em>Url</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.GitRepository#getBranch <em>Branch</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.GitRepository#getUsername <em>Username</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.GitRepository#getPassword <em>Password</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.GitRepository#getSshKey <em>Ssh Key</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.GitRepository#getSshKeyPassphrase <em>Ssh Key Passphrase</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.GitRepository#getSharedCredentials <em>Shared Credentials</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.GitRepository#getSharedCredentialsScope <em>Shared Credentials Scope</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.GitRepository#getLfs <em>Lfs</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.GitRepository#getUseShallowClones <em>Use Shallow Clones</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.GitRepository#getSubmodules <em>Submodules</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.GitRepository#getFetchAll <em>Fetch All</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.GitRepository#getCacheOnAgents <em>Cache On Agents</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.GitRepository#getVerboseLogs <em>Verbose Logs</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.GitRepository#getCommandTimeoutMinutes <em>Command Timeout Minutes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.GitRepository#getChangeDetection <em>Change Detection</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getGitRepository()
 * @model
 * @generated
 */
public interface GitRepository extends Repository {
	/**
	 * Returns the value of the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Url</em>' attribute.
	 * @see #setUrl(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getGitRepository_Url()
	 * @model required="true"
	 * @generated
	 */
	String getUrl();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.GitRepository#getUrl <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Url</em>' attribute.
	 * @see #getUrl()
	 * @generated
	 */
	void setUrl(String value);

	/**
	 * Returns the value of the '<em><b>Branch</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Branch</em>' attribute.
	 * @see #setBranch(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getGitRepository_Branch()
	 * @model required="true"
	 * @generated
	 */
	String getBranch();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.GitRepository#getBranch <em>Branch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Branch</em>' attribute.
	 * @see #getBranch()
	 * @generated
	 */
	void setBranch(String value);

	/**
	 * Returns the value of the '<em><b>Username</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Username</em>' attribute.
	 * @see #setUsername(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getGitRepository_Username()
	 * @model
	 * @generated
	 */
	String getUsername();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.GitRepository#getUsername <em>Username</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Username</em>' attribute.
	 * @see #getUsername()
	 * @generated
	 */
	void setUsername(String value);

	/**
	 * Returns the value of the '<em><b>Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Password</em>' attribute.
	 * @see #setPassword(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getGitRepository_Password()
	 * @model
	 * @generated
	 */
	String getPassword();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.GitRepository#getPassword <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Password</em>' attribute.
	 * @see #getPassword()
	 * @generated
	 */
	void setPassword(String value);

	/**
	 * Returns the value of the '<em><b>Ssh Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ssh Key</em>' attribute.
	 * @see #setSshKey(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getGitRepository_SshKey()
	 * @model
	 * @generated
	 */
	String getSshKey();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.GitRepository#getSshKey <em>Ssh Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ssh Key</em>' attribute.
	 * @see #getSshKey()
	 * @generated
	 */
	void setSshKey(String value);

	/**
	 * Returns the value of the '<em><b>Ssh Key Passphrase</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ssh Key Passphrase</em>' attribute.
	 * @see #setSshKeyPassphrase(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getGitRepository_SshKeyPassphrase()
	 * @model
	 * @generated
	 */
	String getSshKeyPassphrase();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.GitRepository#getSshKeyPassphrase <em>Ssh Key Passphrase</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ssh Key Passphrase</em>' attribute.
	 * @see #getSshKeyPassphrase()
	 * @generated
	 */
	void setSshKeyPassphrase(String value);

	/**
	 * Returns the value of the '<em><b>Shared Credentials</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Shared Credentials</em>' attribute.
	 * @see #setSharedCredentials(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getGitRepository_SharedCredentials()
	 * @model
	 * @generated
	 */
	String getSharedCredentials();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.GitRepository#getSharedCredentials <em>Shared Credentials</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Shared Credentials</em>' attribute.
	 * @see #getSharedCredentials()
	 * @generated
	 */
	void setSharedCredentials(String value);

	/**
	 * Returns the value of the '<em><b>Shared Credentials Scope</b></em>' attribute.
	 * The literals are from the enumeration {@link com.mddoai.metamodel.bambooMM.REPOSITORY_SCOPE}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Shared Credentials Scope</em>' attribute.
	 * @see com.mddoai.metamodel.bambooMM.REPOSITORY_SCOPE
	 * @see #setSharedCredentialsScope(REPOSITORY_SCOPE)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getGitRepository_SharedCredentialsScope()
	 * @model
	 * @generated
	 */
	REPOSITORY_SCOPE getSharedCredentialsScope();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.GitRepository#getSharedCredentialsScope <em>Shared Credentials Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Shared Credentials Scope</em>' attribute.
	 * @see com.mddoai.metamodel.bambooMM.REPOSITORY_SCOPE
	 * @see #getSharedCredentialsScope()
	 * @generated
	 */
	void setSharedCredentialsScope(REPOSITORY_SCOPE value);

	/**
	 * Returns the value of the '<em><b>Lfs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lfs</em>' attribute.
	 * @see #setLfs(Boolean)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getGitRepository_Lfs()
	 * @model
	 * @generated
	 */
	Boolean getLfs();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.GitRepository#getLfs <em>Lfs</em>}' attribute.
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
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getGitRepository_UseShallowClones()
	 * @model
	 * @generated
	 */
	Boolean getUseShallowClones();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.GitRepository#getUseShallowClones <em>Use Shallow Clones</em>}' attribute.
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
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getGitRepository_Submodules()
	 * @model
	 * @generated
	 */
	Boolean getSubmodules();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.GitRepository#getSubmodules <em>Submodules</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Submodules</em>' attribute.
	 * @see #getSubmodules()
	 * @generated
	 */
	void setSubmodules(Boolean value);

	/**
	 * Returns the value of the '<em><b>Fetch All</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fetch All</em>' attribute.
	 * @see #setFetchAll(Boolean)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getGitRepository_FetchAll()
	 * @model
	 * @generated
	 */
	Boolean getFetchAll();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.GitRepository#getFetchAll <em>Fetch All</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fetch All</em>' attribute.
	 * @see #getFetchAll()
	 * @generated
	 */
	void setFetchAll(Boolean value);

	/**
	 * Returns the value of the '<em><b>Cache On Agents</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cache On Agents</em>' attribute.
	 * @see #setCacheOnAgents(Boolean)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getGitRepository_CacheOnAgents()
	 * @model
	 * @generated
	 */
	Boolean getCacheOnAgents();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.GitRepository#getCacheOnAgents <em>Cache On Agents</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cache On Agents</em>' attribute.
	 * @see #getCacheOnAgents()
	 * @generated
	 */
	void setCacheOnAgents(Boolean value);

	/**
	 * Returns the value of the '<em><b>Verbose Logs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Verbose Logs</em>' attribute.
	 * @see #setVerboseLogs(Boolean)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getGitRepository_VerboseLogs()
	 * @model
	 * @generated
	 */
	Boolean getVerboseLogs();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.GitRepository#getVerboseLogs <em>Verbose Logs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Verbose Logs</em>' attribute.
	 * @see #getVerboseLogs()
	 * @generated
	 */
	void setVerboseLogs(Boolean value);

	/**
	 * Returns the value of the '<em><b>Command Timeout Minutes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Command Timeout Minutes</em>' attribute.
	 * @see #setCommandTimeoutMinutes(Integer)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getGitRepository_CommandTimeoutMinutes()
	 * @model
	 * @generated
	 */
	Integer getCommandTimeoutMinutes();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.GitRepository#getCommandTimeoutMinutes <em>Command Timeout Minutes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Command Timeout Minutes</em>' attribute.
	 * @see #getCommandTimeoutMinutes()
	 * @generated
	 */
	void setCommandTimeoutMinutes(Integer value);

	/**
	 * Returns the value of the '<em><b>Change Detection</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Change Detection</em>' containment reference.
	 * @see #setChangeDetection(ChangeDetection)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getGitRepository_ChangeDetection()
	 * @model containment="true"
	 * @generated
	 */
	ChangeDetection getChangeDetection();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.GitRepository#getChangeDetection <em>Change Detection</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Change Detection</em>' containment reference.
	 * @see #getChangeDetection()
	 * @generated
	 */
	void setChangeDetection(ChangeDetection value);

} // GitRepository
