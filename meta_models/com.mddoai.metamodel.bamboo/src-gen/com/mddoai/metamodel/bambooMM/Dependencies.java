/**
 */
package com.mddoai.metamodel.bambooMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dependencies</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Dependencies#isRequireAllStagesPassing <em>Require All Stages Passing</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Dependencies#isEnabledForBranches <em>Enabled For Branches</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Dependencies#getBlockStrategy <em>Block Strategy</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Dependencies#getPlans <em>Plans</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getDependencies()
 * @model
 * @generated
 */
public interface Dependencies extends EObject {
	/**
	 * Returns the value of the '<em><b>Require All Stages Passing</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Require All Stages Passing</em>' attribute.
	 * @see #setRequireAllStagesPassing(boolean)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getDependencies_RequireAllStagesPassing()
	 * @model default="false"
	 * @generated
	 */
	boolean isRequireAllStagesPassing();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.Dependencies#isRequireAllStagesPassing <em>Require All Stages Passing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Require All Stages Passing</em>' attribute.
	 * @see #isRequireAllStagesPassing()
	 * @generated
	 */
	void setRequireAllStagesPassing(boolean value);

	/**
	 * Returns the value of the '<em><b>Enabled For Branches</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enabled For Branches</em>' attribute.
	 * @see #setEnabledForBranches(boolean)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getDependencies_EnabledForBranches()
	 * @model default="true"
	 * @generated
	 */
	boolean isEnabledForBranches();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.Dependencies#isEnabledForBranches <em>Enabled For Branches</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enabled For Branches</em>' attribute.
	 * @see #isEnabledForBranches()
	 * @generated
	 */
	void setEnabledForBranches(boolean value);

	/**
	 * Returns the value of the '<em><b>Block Strategy</b></em>' attribute.
	 * The literals are from the enumeration {@link com.mddoai.metamodel.bambooMM.BLOCK_STRATEGY}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Block Strategy</em>' attribute.
	 * @see com.mddoai.metamodel.bambooMM.BLOCK_STRATEGY
	 * @see #setBlockStrategy(BLOCK_STRATEGY)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getDependencies_BlockStrategy()
	 * @model
	 * @generated
	 */
	BLOCK_STRATEGY getBlockStrategy();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.Dependencies#getBlockStrategy <em>Block Strategy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Block Strategy</em>' attribute.
	 * @see com.mddoai.metamodel.bambooMM.BLOCK_STRATEGY
	 * @see #getBlockStrategy()
	 * @generated
	 */
	void setBlockStrategy(BLOCK_STRATEGY value);

	/**
	 * Returns the value of the '<em><b>Plans</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Plans</em>' attribute list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getDependencies_Plans()
	 * @model
	 * @generated
	 */
	EList<String> getPlans();

} // Dependencies
