/**
 */
package com.mddoai.metamodel.bambooMM;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Bitbucket Cloud Repository</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.BitbucketCloudRepository#getSlug <em>Slug</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBitbucketCloudRepository()
 * @model
 * @generated
 */
public interface BitbucketCloudRepository extends GitRepository {
	/**
	 * Returns the value of the '<em><b>Slug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Slug</em>' attribute.
	 * @see #setSlug(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBitbucketCloudRepository_Slug()
	 * @model required="true"
	 * @generated
	 */
	String getSlug();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.BitbucketCloudRepository#getSlug <em>Slug</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Slug</em>' attribute.
	 * @see #getSlug()
	 * @generated
	 */
	void setSlug(String value);

} // BitbucketCloudRepository
