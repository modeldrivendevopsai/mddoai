/**
 */
package com.mddoai.metamodel.swarch;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Software Architecture</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.swarch.SoftwareArchitecture#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.swarch.SoftwareArchitecture#getType <em>Type</em>}</li>
 *   <li>{@link com.mddoai.metamodel.swarch.SoftwareArchitecture#getBuild_system <em>Build system</em>}</li>
 *   <li>{@link com.mddoai.metamodel.swarch.SoftwareArchitecture#getLanguage <em>Language</em>}</li>
 *   <li>{@link com.mddoai.metamodel.swarch.SoftwareArchitecture#getUnit_test <em>Unit test</em>}</li>
 *   <li>{@link com.mddoai.metamodel.swarch.SoftwareArchitecture#getHealth_check <em>Health check</em>}</li>
 *   <li>{@link com.mddoai.metamodel.swarch.SoftwareArchitecture#getLint_check <em>Lint check</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.swarch.SwarchPackage#getSoftwareArchitecture()
 * @model
 * @generated
 */
public interface SoftwareArchitecture extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.mddoai.metamodel.swarch.SwarchPackage#getSoftwareArchitecture_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.swarch.SoftwareArchitecture#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see com.mddoai.metamodel.swarch.SwarchPackage#getSoftwareArchitecture_Type()
	 * @model required="true"
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.swarch.SoftwareArchitecture#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Build system</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Build system</em>' attribute.
	 * @see #setBuild_system(String)
	 * @see com.mddoai.metamodel.swarch.SwarchPackage#getSoftwareArchitecture_Build_system()
	 * @model required="true"
	 * @generated
	 */
	String getBuild_system();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.swarch.SoftwareArchitecture#getBuild_system <em>Build system</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Build system</em>' attribute.
	 * @see #getBuild_system()
	 * @generated
	 */
	void setBuild_system(String value);

	/**
	 * Returns the value of the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Language</em>' attribute.
	 * @see #setLanguage(String)
	 * @see com.mddoai.metamodel.swarch.SwarchPackage#getSoftwareArchitecture_Language()
	 * @model required="true"
	 * @generated
	 */
	String getLanguage();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.swarch.SoftwareArchitecture#getLanguage <em>Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Language</em>' attribute.
	 * @see #getLanguage()
	 * @generated
	 */
	void setLanguage(String value);

	/**
	 * Returns the value of the '<em><b>Unit test</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unit test</em>' attribute.
	 * @see #setUnit_test(String)
	 * @see com.mddoai.metamodel.swarch.SwarchPackage#getSoftwareArchitecture_Unit_test()
	 * @model
	 * @generated
	 */
	String getUnit_test();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.swarch.SoftwareArchitecture#getUnit_test <em>Unit test</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unit test</em>' attribute.
	 * @see #getUnit_test()
	 * @generated
	 */
	void setUnit_test(String value);

	/**
	 * Returns the value of the '<em><b>Health check</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Health check</em>' attribute.
	 * @see #setHealth_check(String)
	 * @see com.mddoai.metamodel.swarch.SwarchPackage#getSoftwareArchitecture_Health_check()
	 * @model
	 * @generated
	 */
	String getHealth_check();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.swarch.SoftwareArchitecture#getHealth_check <em>Health check</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Health check</em>' attribute.
	 * @see #getHealth_check()
	 * @generated
	 */
	void setHealth_check(String value);

	/**
	 * Returns the value of the '<em><b>Lint check</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lint check</em>' attribute.
	 * @see #setLint_check(String)
	 * @see com.mddoai.metamodel.swarch.SwarchPackage#getSoftwareArchitecture_Lint_check()
	 * @model
	 * @generated
	 */
	String getLint_check();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.swarch.SoftwareArchitecture#getLint_check <em>Lint check</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lint check</em>' attribute.
	 * @see #getLint_check()
	 * @generated
	 */
	void setLint_check(String value);

} // SoftwareArchitecture
