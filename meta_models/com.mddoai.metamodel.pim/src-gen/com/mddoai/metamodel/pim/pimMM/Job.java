/**
 */
package com.mddoai.metamodel.pim.pimMM;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Job</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Job#getId <em>Id</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Job#getIfCondition <em>If Condition</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Job#getServices <em>Services</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Job#getMatrix <em>Matrix</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Job#getPrevious <em>Previous</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Job#getNext <em>Next</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Job#getMaxAttempts <em>Max Attempts</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Job#getAllowFailure <em>Allow Failure</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getJob()
 * @model abstract="true"
 * @generated
 */
public interface Job extends PipelineBlock {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getJob_Id()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.Job#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>If Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>If Condition</em>' containment reference.
	 * @see #setIfCondition(Expression)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getJob_IfCondition()
	 * @model containment="true"
	 * @generated
	 */
	Expression getIfCondition();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.Job#getIfCondition <em>If Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>If Condition</em>' containment reference.
	 * @see #getIfCondition()
	 * @generated
	 */
	void setIfCondition(Expression value);

	/**
	 * Returns the value of the '<em><b>Services</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.pim.pimMM.DockerContainer}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Services</em>' containment reference list.
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getJob_Services()
	 * @model containment="true"
	 * @generated
	 */
	EList<DockerContainer> getServices();

	/**
	 * Returns the value of the '<em><b>Matrix</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Matrix</em>' containment reference.
	 * @see #setMatrix(Matrix)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getJob_Matrix()
	 * @model containment="true"
	 * @generated
	 */
	Matrix getMatrix();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.Job#getMatrix <em>Matrix</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Matrix</em>' containment reference.
	 * @see #getMatrix()
	 * @generated
	 */
	void setMatrix(Matrix value);

	/**
	 * Returns the value of the '<em><b>Previous</b></em>' reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.pim.pimMM.Job}.
	 * It is bidirectional and its opposite is '{@link com.mddoai.metamodel.pim.pimMM.Job#getNext <em>Next</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Previous</em>' reference list.
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getJob_Previous()
	 * @see com.mddoai.metamodel.pim.pimMM.Job#getNext
	 * @model opposite="next"
	 * @generated
	 */
	EList<Job> getPrevious();

	/**
	 * Returns the value of the '<em><b>Next</b></em>' reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.pim.pimMM.Job}.
	 * It is bidirectional and its opposite is '{@link com.mddoai.metamodel.pim.pimMM.Job#getPrevious <em>Previous</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Next</em>' reference list.
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getJob_Next()
	 * @see com.mddoai.metamodel.pim.pimMM.Job#getPrevious
	 * @model opposite="previous"
	 * @generated
	 */
	EList<Job> getNext();

	/**
	 * Returns the value of the '<em><b>Max Attempts</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Attempts</em>' attribute.
	 * @see #setMaxAttempts(Integer)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getJob_MaxAttempts()
	 * @model default="1"
	 * @generated
	 */
	Integer getMaxAttempts();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.Job#getMaxAttempts <em>Max Attempts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Attempts</em>' attribute.
	 * @see #getMaxAttempts()
	 * @generated
	 */
	void setMaxAttempts(Integer value);

	/**
	 * Returns the value of the '<em><b>Allow Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Allow Failure</em>' attribute.
	 * @see #setAllowFailure(Boolean)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getJob_AllowFailure()
	 * @model
	 * @generated
	 */
	Boolean getAllowFailure();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.Job#getAllowFailure <em>Allow Failure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Allow Failure</em>' attribute.
	 * @see #getAllowFailure()
	 * @generated
	 */
	void setAllowFailure(Boolean value);

} // Job
