/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Review App Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ReviewAppStep#getReviewApp <em>Review App</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getReviewAppStep()
 * @model
 * @generated
 */
public interface ReviewAppStep extends Step {
	/**
	 * Returns the value of the '<em><b>Review App</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Review App</em>' attribute.
	 * @see #setReviewApp(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getReviewAppStep_ReviewApp()
	 * @model required="true"
	 * @generated
	 */
	String getReviewApp();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ReviewAppStep#getReviewApp <em>Review App</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Review App</em>' attribute.
	 * @see #getReviewApp()
	 * @generated
	 */
	void setReviewApp(String value);

} // ReviewAppStep
