/**
 */
package com.mddoai.metamodel.bambooMM;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Checkout Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.CheckoutTask#getRepository <em>Repository</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.CheckoutTask#getPath <em>Path</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.CheckoutTask#getForceCleanBuild <em>Force Clean Build</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.CheckoutTask#isDefaultRepository <em>Default Repository</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getCheckoutTask()
 * @model
 * @generated
 */
public interface CheckoutTask extends Task {
	/**
	 * Returns the value of the '<em><b>Repository</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Repository</em>' attribute.
	 * @see #setRepository(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getCheckoutTask_Repository()
	 * @model
	 * @generated
	 */
	String getRepository();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.CheckoutTask#getRepository <em>Repository</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Repository</em>' attribute.
	 * @see #getRepository()
	 * @generated
	 */
	void setRepository(String value);

	/**
	 * Returns the value of the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Path</em>' attribute.
	 * @see #setPath(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getCheckoutTask_Path()
	 * @model
	 * @generated
	 */
	String getPath();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.CheckoutTask#getPath <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path</em>' attribute.
	 * @see #getPath()
	 * @generated
	 */
	void setPath(String value);

	/**
	 * Returns the value of the '<em><b>Force Clean Build</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Force Clean Build</em>' attribute.
	 * @see #setForceCleanBuild(Boolean)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getCheckoutTask_ForceCleanBuild()
	 * @model
	 * @generated
	 */
	Boolean getForceCleanBuild();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.CheckoutTask#getForceCleanBuild <em>Force Clean Build</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Force Clean Build</em>' attribute.
	 * @see #getForceCleanBuild()
	 * @generated
	 */
	void setForceCleanBuild(Boolean value);

	/**
	 * Returns the value of the '<em><b>Default Repository</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Repository</em>' attribute.
	 * @see #setDefaultRepository(boolean)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getCheckoutTask_DefaultRepository()
	 * @model default="false"
	 * @generated
	 */
	boolean isDefaultRepository();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.CheckoutTask#isDefaultRepository <em>Default Repository</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Repository</em>' attribute.
	 * @see #isDefaultRepository()
	 * @generated
	 */
	void setDefaultRepository(boolean value);

} // CheckoutTask
