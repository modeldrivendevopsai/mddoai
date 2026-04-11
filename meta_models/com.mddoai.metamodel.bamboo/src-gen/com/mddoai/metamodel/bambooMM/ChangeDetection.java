/**
 */
package com.mddoai.metamodel.bambooMM;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Change Detection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.ChangeDetection#getQuietPeriod <em>Quiet Period</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.ChangeDetection#getExcludeChangesetPattern <em>Exclude Changeset Pattern</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.ChangeDetection#getFileFilterType <em>File Filter Type</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.ChangeDetection#getFileFilterPattern <em>File Filter Pattern</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getChangeDetection()
 * @model
 * @generated
 */
public interface ChangeDetection extends EObject {
	/**
	 * Returns the value of the '<em><b>Quiet Period</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Quiet Period</em>' containment reference.
	 * @see #setQuietPeriod(QuietPeriod)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getChangeDetection_QuietPeriod()
	 * @model containment="true"
	 * @generated
	 */
	QuietPeriod getQuietPeriod();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.ChangeDetection#getQuietPeriod <em>Quiet Period</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Quiet Period</em>' containment reference.
	 * @see #getQuietPeriod()
	 * @generated
	 */
	void setQuietPeriod(QuietPeriod value);

	/**
	 * Returns the value of the '<em><b>Exclude Changeset Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exclude Changeset Pattern</em>' attribute.
	 * @see #setExcludeChangesetPattern(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getChangeDetection_ExcludeChangesetPattern()
	 * @model
	 * @generated
	 */
	String getExcludeChangesetPattern();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.ChangeDetection#getExcludeChangesetPattern <em>Exclude Changeset Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exclude Changeset Pattern</em>' attribute.
	 * @see #getExcludeChangesetPattern()
	 * @generated
	 */
	void setExcludeChangesetPattern(String value);

	/**
	 * Returns the value of the '<em><b>File Filter Type</b></em>' attribute.
	 * The literals are from the enumeration {@link com.mddoai.metamodel.bambooMM.FILE_FILTER_TYPE}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>File Filter Type</em>' attribute.
	 * @see com.mddoai.metamodel.bambooMM.FILE_FILTER_TYPE
	 * @see #setFileFilterType(FILE_FILTER_TYPE)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getChangeDetection_FileFilterType()
	 * @model
	 * @generated
	 */
	FILE_FILTER_TYPE getFileFilterType();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.ChangeDetection#getFileFilterType <em>File Filter Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File Filter Type</em>' attribute.
	 * @see com.mddoai.metamodel.bambooMM.FILE_FILTER_TYPE
	 * @see #getFileFilterType()
	 * @generated
	 */
	void setFileFilterType(FILE_FILTER_TYPE value);

	/**
	 * Returns the value of the '<em><b>File Filter Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>File Filter Pattern</em>' attribute.
	 * @see #setFileFilterPattern(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getChangeDetection_FileFilterPattern()
	 * @model
	 * @generated
	 */
	String getFileFilterPattern();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.ChangeDetection#getFileFilterPattern <em>File Filter Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File Filter Pattern</em>' attribute.
	 * @see #getFileFilterPattern()
	 * @generated
	 */
	void setFileFilterPattern(String value);

} // ChangeDetection
