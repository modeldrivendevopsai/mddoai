/**
 */
package com.mddoai.metamodel.bambooMM;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Branch Management</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.BranchManagement#getCreate <em>Create</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.BranchManagement#getCreatePattern <em>Create Pattern</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.BranchManagement#getAcceptFork <em>Accept Fork</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.BranchManagement#getDelete <em>Delete</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.BranchManagement#getIntegration <em>Integration</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.BranchManagement#getLinkToJira <em>Link To Jira</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBranchManagement()
 * @model
 * @generated
 */
public interface BranchManagement extends EObject {
	/**
	 * Returns the value of the '<em><b>Create</b></em>' attribute.
	 * The literals are from the enumeration {@link com.mddoai.metamodel.bambooMM.BRANCH_CREATE_STRATEGY}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Create</em>' attribute.
	 * @see com.mddoai.metamodel.bambooMM.BRANCH_CREATE_STRATEGY
	 * @see #setCreate(BRANCH_CREATE_STRATEGY)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBranchManagement_Create()
	 * @model
	 * @generated
	 */
	BRANCH_CREATE_STRATEGY getCreate();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.BranchManagement#getCreate <em>Create</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Create</em>' attribute.
	 * @see com.mddoai.metamodel.bambooMM.BRANCH_CREATE_STRATEGY
	 * @see #getCreate()
	 * @generated
	 */
	void setCreate(BRANCH_CREATE_STRATEGY value);

	/**
	 * Returns the value of the '<em><b>Create Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Create Pattern</em>' attribute.
	 * @see #setCreatePattern(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBranchManagement_CreatePattern()
	 * @model
	 * @generated
	 */
	String getCreatePattern();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.BranchManagement#getCreatePattern <em>Create Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Create Pattern</em>' attribute.
	 * @see #getCreatePattern()
	 * @generated
	 */
	void setCreatePattern(String value);

	/**
	 * Returns the value of the '<em><b>Accept Fork</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Accept Fork</em>' attribute.
	 * @see #setAcceptFork(Boolean)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBranchManagement_AcceptFork()
	 * @model
	 * @generated
	 */
	Boolean getAcceptFork();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.BranchManagement#getAcceptFork <em>Accept Fork</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Accept Fork</em>' attribute.
	 * @see #getAcceptFork()
	 * @generated
	 */
	void setAcceptFork(Boolean value);

	/**
	 * Returns the value of the '<em><b>Delete</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Delete</em>' containment reference.
	 * @see #setDelete(BranchDelete)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBranchManagement_Delete()
	 * @model containment="true"
	 * @generated
	 */
	BranchDelete getDelete();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.BranchManagement#getDelete <em>Delete</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Delete</em>' containment reference.
	 * @see #getDelete()
	 * @generated
	 */
	void setDelete(BranchDelete value);

	/**
	 * Returns the value of the '<em><b>Integration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Integration</em>' containment reference.
	 * @see #setIntegration(BranchIntegration)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBranchManagement_Integration()
	 * @model containment="true"
	 * @generated
	 */
	BranchIntegration getIntegration();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.BranchManagement#getIntegration <em>Integration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Integration</em>' containment reference.
	 * @see #getIntegration()
	 * @generated
	 */
	void setIntegration(BranchIntegration value);

	/**
	 * Returns the value of the '<em><b>Link To Jira</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Link To Jira</em>' attribute.
	 * @see #setLinkToJira(Boolean)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getBranchManagement_LinkToJira()
	 * @model
	 * @generated
	 */
	Boolean getLinkToJira();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.BranchManagement#getLinkToJira <em>Link To Jira</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Link To Jira</em>' attribute.
	 * @see #getLinkToJira()
	 * @generated
	 */
	void setLinkToJira(Boolean value);

} // BranchManagement
