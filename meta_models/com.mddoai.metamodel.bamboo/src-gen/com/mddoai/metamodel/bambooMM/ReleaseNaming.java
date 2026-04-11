/**
 */
package com.mddoai.metamodel.bambooMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Release Naming</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.ReleaseNaming#getNextVersionName <em>Next Version Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.ReleaseNaming#getAppliesToBranches <em>Applies To Branches</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.ReleaseNaming#getAutoIncrement <em>Auto Increment</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.ReleaseNaming#getAutoIncrementVariables <em>Auto Increment Variables</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getReleaseNaming()
 * @model
 * @generated
 */
public interface ReleaseNaming extends EObject {
	/**
	 * Returns the value of the '<em><b>Next Version Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Next Version Name</em>' attribute.
	 * @see #setNextVersionName(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getReleaseNaming_NextVersionName()
	 * @model required="true"
	 * @generated
	 */
	String getNextVersionName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.ReleaseNaming#getNextVersionName <em>Next Version Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Next Version Name</em>' attribute.
	 * @see #getNextVersionName()
	 * @generated
	 */
	void setNextVersionName(String value);

	/**
	 * Returns the value of the '<em><b>Applies To Branches</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Applies To Branches</em>' attribute.
	 * @see #setAppliesToBranches(Boolean)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getReleaseNaming_AppliesToBranches()
	 * @model
	 * @generated
	 */
	Boolean getAppliesToBranches();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.ReleaseNaming#getAppliesToBranches <em>Applies To Branches</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Applies To Branches</em>' attribute.
	 * @see #getAppliesToBranches()
	 * @generated
	 */
	void setAppliesToBranches(Boolean value);

	/**
	 * Returns the value of the '<em><b>Auto Increment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Auto Increment</em>' attribute.
	 * @see #setAutoIncrement(Boolean)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getReleaseNaming_AutoIncrement()
	 * @model
	 * @generated
	 */
	Boolean getAutoIncrement();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.ReleaseNaming#getAutoIncrement <em>Auto Increment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Auto Increment</em>' attribute.
	 * @see #getAutoIncrement()
	 * @generated
	 */
	void setAutoIncrement(Boolean value);

	/**
	 * Returns the value of the '<em><b>Auto Increment Variables</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Auto Increment Variables</em>' attribute list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getReleaseNaming_AutoIncrementVariables()
	 * @model
	 * @generated
	 */
	EList<String> getAutoIncrementVariables();

} // ReleaseNaming
