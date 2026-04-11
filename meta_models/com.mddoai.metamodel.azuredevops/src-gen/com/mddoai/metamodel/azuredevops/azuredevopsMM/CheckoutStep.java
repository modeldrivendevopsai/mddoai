/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Checkout Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getCheckout <em>Checkout</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getClean <em>Clean</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getFetchDepth <em>Fetch Depth</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getFetchFilter <em>Fetch Filter</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getFetchTags <em>Fetch Tags</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getLfs <em>Lfs</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getPersistCredentials <em>Persist Credentials</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getSubmodules <em>Submodules</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getPath <em>Path</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getSparseCheckoutDirectories <em>Sparse Checkout Directories</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getSparseCheckoutPatterns <em>Sparse Checkout Patterns</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getWorkspaceRepo <em>Workspace Repo</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getCheckoutStep()
 * @model
 * @generated
 */
public interface CheckoutStep extends Step {
	/**
	 * Returns the value of the '<em><b>Checkout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Checkout</em>' attribute.
	 * @see #setCheckout(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getCheckoutStep_Checkout()
	 * @model required="true"
	 * @generated
	 */
	String getCheckout();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getCheckout <em>Checkout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Checkout</em>' attribute.
	 * @see #getCheckout()
	 * @generated
	 */
	void setCheckout(String value);

	/**
	 * Returns the value of the '<em><b>Clean</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Clean</em>' attribute.
	 * @see #setClean(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getCheckoutStep_Clean()
	 * @model
	 * @generated
	 */
	String getClean();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getClean <em>Clean</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Clean</em>' attribute.
	 * @see #getClean()
	 * @generated
	 */
	void setClean(String value);

	/**
	 * Returns the value of the '<em><b>Fetch Depth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fetch Depth</em>' attribute.
	 * @see #setFetchDepth(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getCheckoutStep_FetchDepth()
	 * @model
	 * @generated
	 */
	String getFetchDepth();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getFetchDepth <em>Fetch Depth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fetch Depth</em>' attribute.
	 * @see #getFetchDepth()
	 * @generated
	 */
	void setFetchDepth(String value);

	/**
	 * Returns the value of the '<em><b>Fetch Filter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fetch Filter</em>' attribute.
	 * @see #setFetchFilter(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getCheckoutStep_FetchFilter()
	 * @model
	 * @generated
	 */
	String getFetchFilter();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getFetchFilter <em>Fetch Filter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fetch Filter</em>' attribute.
	 * @see #getFetchFilter()
	 * @generated
	 */
	void setFetchFilter(String value);

	/**
	 * Returns the value of the '<em><b>Fetch Tags</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fetch Tags</em>' attribute.
	 * @see #setFetchTags(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getCheckoutStep_FetchTags()
	 * @model
	 * @generated
	 */
	String getFetchTags();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getFetchTags <em>Fetch Tags</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fetch Tags</em>' attribute.
	 * @see #getFetchTags()
	 * @generated
	 */
	void setFetchTags(String value);

	/**
	 * Returns the value of the '<em><b>Lfs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lfs</em>' attribute.
	 * @see #setLfs(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getCheckoutStep_Lfs()
	 * @model
	 * @generated
	 */
	String getLfs();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getLfs <em>Lfs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lfs</em>' attribute.
	 * @see #getLfs()
	 * @generated
	 */
	void setLfs(String value);

	/**
	 * Returns the value of the '<em><b>Persist Credentials</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Persist Credentials</em>' attribute.
	 * @see #setPersistCredentials(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getCheckoutStep_PersistCredentials()
	 * @model
	 * @generated
	 */
	String getPersistCredentials();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getPersistCredentials <em>Persist Credentials</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Persist Credentials</em>' attribute.
	 * @see #getPersistCredentials()
	 * @generated
	 */
	void setPersistCredentials(String value);

	/**
	 * Returns the value of the '<em><b>Submodules</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Submodules</em>' attribute.
	 * @see #setSubmodules(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getCheckoutStep_Submodules()
	 * @model
	 * @generated
	 */
	String getSubmodules();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getSubmodules <em>Submodules</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Submodules</em>' attribute.
	 * @see #getSubmodules()
	 * @generated
	 */
	void setSubmodules(String value);

	/**
	 * Returns the value of the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Path</em>' attribute.
	 * @see #setPath(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getCheckoutStep_Path()
	 * @model
	 * @generated
	 */
	String getPath();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getPath <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path</em>' attribute.
	 * @see #getPath()
	 * @generated
	 */
	void setPath(String value);

	/**
	 * Returns the value of the '<em><b>Sparse Checkout Directories</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sparse Checkout Directories</em>' attribute.
	 * @see #setSparseCheckoutDirectories(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getCheckoutStep_SparseCheckoutDirectories()
	 * @model
	 * @generated
	 */
	String getSparseCheckoutDirectories();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getSparseCheckoutDirectories <em>Sparse Checkout Directories</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sparse Checkout Directories</em>' attribute.
	 * @see #getSparseCheckoutDirectories()
	 * @generated
	 */
	void setSparseCheckoutDirectories(String value);

	/**
	 * Returns the value of the '<em><b>Sparse Checkout Patterns</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sparse Checkout Patterns</em>' attribute.
	 * @see #setSparseCheckoutPatterns(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getCheckoutStep_SparseCheckoutPatterns()
	 * @model
	 * @generated
	 */
	String getSparseCheckoutPatterns();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getSparseCheckoutPatterns <em>Sparse Checkout Patterns</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sparse Checkout Patterns</em>' attribute.
	 * @see #getSparseCheckoutPatterns()
	 * @generated
	 */
	void setSparseCheckoutPatterns(String value);

	/**
	 * Returns the value of the '<em><b>Workspace Repo</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Workspace Repo</em>' attribute.
	 * @see #setWorkspaceRepo(Boolean)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getCheckoutStep_WorkspaceRepo()
	 * @model
	 * @generated
	 */
	Boolean getWorkspaceRepo();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep#getWorkspaceRepo <em>Workspace Repo</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Workspace Repo</em>' attribute.
	 * @see #getWorkspaceRepo()
	 * @generated
	 */
	void setWorkspaceRepo(Boolean value);

} // CheckoutStep
