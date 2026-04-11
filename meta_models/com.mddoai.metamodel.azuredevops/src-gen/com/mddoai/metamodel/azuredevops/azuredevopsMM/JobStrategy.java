/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Job Strategy</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.JobStrategy#getParallel <em>Parallel</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.JobStrategy#getMaxParallel <em>Max Parallel</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.JobStrategy#getMatrix <em>Matrix</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getJobStrategy()
 * @model
 * @generated
 */
public interface JobStrategy extends EObject {
	/**
	 * Returns the value of the '<em><b>Parallel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parallel</em>' attribute.
	 * @see #setParallel(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getJobStrategy_Parallel()
	 * @model
	 * @generated
	 */
	String getParallel();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.JobStrategy#getParallel <em>Parallel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parallel</em>' attribute.
	 * @see #getParallel()
	 * @generated
	 */
	void setParallel(String value);

	/**
	 * Returns the value of the '<em><b>Max Parallel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Parallel</em>' attribute.
	 * @see #setMaxParallel(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getJobStrategy_MaxParallel()
	 * @model
	 * @generated
	 */
	String getMaxParallel();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.JobStrategy#getMaxParallel <em>Max Parallel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Parallel</em>' attribute.
	 * @see #getMaxParallel()
	 * @generated
	 */
	void setMaxParallel(String value);

	/**
	 * Returns the value of the '<em><b>Matrix</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type list of {@link java.util.Map.Entry<java.lang.String, java.lang.String>},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Matrix</em>' map.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getJobStrategy_Matrix()
	 * @model mapType="com.mddoai.metamodel.azuredevops.azuredevopsMM.MatrixEntry&lt;org.eclipse.emf.ecore.EString, com.mddoai.metamodel.azuredevops.azuredevopsMM.MatrixVariable&gt;"
	 * @generated
	 */
	EMap<String, EMap<String, String>> getMatrix();

} // JobStrategy
