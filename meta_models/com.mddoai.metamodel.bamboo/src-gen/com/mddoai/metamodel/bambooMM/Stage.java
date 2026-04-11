/**
 */
package com.mddoai.metamodel.bambooMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stage</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Stage#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Stage#isManual <em>Manual</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Stage#isFinalStage <em>Final Stage</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Stage#getJobRefs <em>Job Refs</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getStage()
 * @model
 * @generated
 */
public interface Stage extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getStage_Name()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.Stage#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Manual</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Manual</em>' attribute.
	 * @see #setManual(boolean)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getStage_Manual()
	 * @model default="false"
	 * @generated
	 */
	boolean isManual();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.Stage#isManual <em>Manual</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Manual</em>' attribute.
	 * @see #isManual()
	 * @generated
	 */
	void setManual(boolean value);

	/**
	 * Returns the value of the '<em><b>Final Stage</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Final Stage</em>' attribute.
	 * @see #setFinalStage(boolean)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getStage_FinalStage()
	 * @model default="false"
	 * @generated
	 */
	boolean isFinalStage();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.Stage#isFinalStage <em>Final Stage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Final Stage</em>' attribute.
	 * @see #isFinalStage()
	 * @generated
	 */
	void setFinalStage(boolean value);

	/**
	 * Returns the value of the '<em><b>Job Refs</b></em>' reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.bambooMM.Job}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Job Refs</em>' reference list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getStage_JobRefs()
	 * @model required="true"
	 * @generated
	 */
	EList<Job> getJobRefs();

} // Stage
