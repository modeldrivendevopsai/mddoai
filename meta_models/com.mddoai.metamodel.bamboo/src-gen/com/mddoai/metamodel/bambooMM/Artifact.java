/**
 */
package com.mddoai.metamodel.bambooMM;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Artifact</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Artifact#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Artifact#getLocation <em>Location</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Artifact#getPattern <em>Pattern</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Artifact#isRequired <em>Required</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Artifact#isShared <em>Shared</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.Artifact#getHttpCompressionOn <em>Http Compression On</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getArtifact()
 * @model
 * @generated
 */
public interface Artifact extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getArtifact_Name()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.Artifact#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Location</em>' attribute.
	 * @see #setLocation(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getArtifact_Location()
	 * @model
	 * @generated
	 */
	String getLocation();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.Artifact#getLocation <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Location</em>' attribute.
	 * @see #getLocation()
	 * @generated
	 */
	void setLocation(String value);

	/**
	 * Returns the value of the '<em><b>Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pattern</em>' attribute.
	 * @see #setPattern(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getArtifact_Pattern()
	 * @model required="true"
	 * @generated
	 */
	String getPattern();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.Artifact#getPattern <em>Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pattern</em>' attribute.
	 * @see #getPattern()
	 * @generated
	 */
	void setPattern(String value);

	/**
	 * Returns the value of the '<em><b>Required</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Required</em>' attribute.
	 * @see #setRequired(boolean)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getArtifact_Required()
	 * @model default="false"
	 * @generated
	 */
	boolean isRequired();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.Artifact#isRequired <em>Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Required</em>' attribute.
	 * @see #isRequired()
	 * @generated
	 */
	void setRequired(boolean value);

	/**
	 * Returns the value of the '<em><b>Shared</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Shared</em>' attribute.
	 * @see #setShared(boolean)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getArtifact_Shared()
	 * @model default="false"
	 * @generated
	 */
	boolean isShared();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.Artifact#isShared <em>Shared</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Shared</em>' attribute.
	 * @see #isShared()
	 * @generated
	 */
	void setShared(boolean value);

	/**
	 * Returns the value of the '<em><b>Http Compression On</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Http Compression On</em>' attribute.
	 * @see #setHttpCompressionOn(Boolean)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getArtifact_HttpCompressionOn()
	 * @model
	 * @generated
	 */
	Boolean getHttpCompressionOn();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.Artifact#getHttpCompressionOn <em>Http Compression On</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Http Compression On</em>' attribute.
	 * @see #getHttpCompressionOn()
	 * @generated
	 */
	void setHttpCompressionOn(Boolean value);

} // Artifact
