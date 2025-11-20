/**
 */
package com.mddoai.metamodel.pim.pimMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Matrix</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Matrix#getAxes <em>Axes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Matrix#getIncludes <em>Includes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Matrix#getExcludes <em>Excludes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Matrix#getFailFast <em>Fail Fast</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getMatrix()
 * @model
 * @generated
 */
public interface Matrix extends EObject {
	/**
	 * Returns the value of the '<em><b>Axes</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.pim.pimMM.MatrixAxis}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Axes</em>' containment reference list.
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getMatrix_Axes()
	 * @model containment="true"
	 * @generated
	 */
	EList<MatrixAxis> getAxes();

	/**
	 * Returns the value of the '<em><b>Includes</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.pim.pimMM.MatrixCombination}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Includes</em>' containment reference list.
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getMatrix_Includes()
	 * @model containment="true"
	 * @generated
	 */
	EList<MatrixCombination> getIncludes();

	/**
	 * Returns the value of the '<em><b>Excludes</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.pim.pimMM.MatrixCombination}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Excludes</em>' containment reference list.
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getMatrix_Excludes()
	 * @model containment="true"
	 * @generated
	 */
	EList<MatrixCombination> getExcludes();

	/**
	 * Returns the value of the '<em><b>Fail Fast</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fail Fast</em>' attribute.
	 * @see #setFailFast(Boolean)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getMatrix_FailFast()
	 * @model
	 * @generated
	 */
	Boolean getFailFast();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.Matrix#getFailFast <em>Fail Fast</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fail Fast</em>' attribute.
	 * @see #getFailFast()
	 * @generated
	 */
	void setFailFast(Boolean value);

} // Matrix
