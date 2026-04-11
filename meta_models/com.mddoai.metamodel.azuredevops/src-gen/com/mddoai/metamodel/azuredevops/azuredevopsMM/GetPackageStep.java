/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Get Package Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.GetPackageStep#getGetPackage <em>Get Package</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.GetPackageStep#getPath <em>Path</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getGetPackageStep()
 * @model
 * @generated
 */
public interface GetPackageStep extends Step {
	/**
	 * Returns the value of the '<em><b>Get Package</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Get Package</em>' attribute.
	 * @see #setGetPackage(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getGetPackageStep_GetPackage()
	 * @model required="true"
	 * @generated
	 */
	String getGetPackage();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.GetPackageStep#getGetPackage <em>Get Package</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Get Package</em>' attribute.
	 * @see #getGetPackage()
	 * @generated
	 */
	void setGetPackage(String value);

	/**
	 * Returns the value of the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Path</em>' attribute.
	 * @see #setPath(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getGetPackageStep_Path()
	 * @model
	 * @generated
	 */
	String getPath();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.GetPackageStep#getPath <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path</em>' attribute.
	 * @see #getPath()
	 * @generated
	 */
	void setPath(String value);

} // GetPackageStep
