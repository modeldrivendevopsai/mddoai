/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

import org.eclipse.emf.common.util.EMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Step Template</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.StepTemplate#getTemplate <em>Template</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.StepTemplate#getParameters <em>Parameters</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getStepTemplate()
 * @model
 * @generated
 */
public interface StepTemplate extends Step {
	/**
	 * Returns the value of the '<em><b>Template</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Template</em>' attribute.
	 * @see #setTemplate(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getStepTemplate_Template()
	 * @model required="true"
	 * @generated
	 */
	String getTemplate();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.StepTemplate#getTemplate <em>Template</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Template</em>' attribute.
	 * @see #getTemplate()
	 * @generated
	 */
	void setTemplate(String value);

	/**
	 * Returns the value of the '<em><b>Parameters</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameters</em>' map.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getStepTemplate_Parameters()
	 * @model mapType="com.mddoai.metamodel.azuredevops.azuredevopsMM.TemplateParameterAssignment&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;"
	 * @generated
	 */
	EMap<String, String> getParameters();

} // StepTemplate
