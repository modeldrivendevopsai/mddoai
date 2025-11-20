/**
 */
package com.mddoai.metamodel.pim.pimMM;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Artifact</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Artifact#getArtifactName <em>Artifact Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Artifact#getIncludePaths <em>Include Paths</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Artifact#getExcludePaths <em>Exclude Paths</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Artifact#getRetentionTime <em>Retention Time</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Artifact#isStore <em>Store</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getArtifact()
 * @model
 * @generated
 */
public interface Artifact extends NonConditionalStep {
	/**
	 * Returns the value of the '<em><b>Artifact Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Artifact Name</em>' containment reference.
	 * @see #setArtifactName(Expression)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getArtifact_ArtifactName()
	 * @model containment="true"
	 * @generated
	 */
	Expression getArtifactName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.Artifact#getArtifactName <em>Artifact Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Artifact Name</em>' containment reference.
	 * @see #getArtifactName()
	 * @generated
	 */
	void setArtifactName(Expression value);

	/**
	 * Returns the value of the '<em><b>Include Paths</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.pim.pimMM.Expression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Include Paths</em>' containment reference list.
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getArtifact_IncludePaths()
	 * @model containment="true"
	 * @generated
	 */
	EList<Expression> getIncludePaths();

	/**
	 * Returns the value of the '<em><b>Exclude Paths</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.pim.pimMM.Expression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exclude Paths</em>' containment reference list.
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getArtifact_ExcludePaths()
	 * @model containment="true"
	 * @generated
	 */
	EList<Expression> getExcludePaths();

	/**
	 * Returns the value of the '<em><b>Retention Time</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Retention Time</em>' containment reference.
	 * @see #setRetentionTime(Expression)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getArtifact_RetentionTime()
	 * @model containment="true"
	 * @generated
	 */
	Expression getRetentionTime();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.Artifact#getRetentionTime <em>Retention Time</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Retention Time</em>' containment reference.
	 * @see #getRetentionTime()
	 * @generated
	 */
	void setRetentionTime(Expression value);

	/**
	 * Returns the value of the '<em><b>Store</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Store</em>' attribute.
	 * @see #setStore(boolean)
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getArtifact_Store()
	 * @model required="true"
	 * @generated
	 */
	boolean isStore();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.pim.pimMM.Artifact#isStore <em>Store</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Store</em>' attribute.
	 * @see #isStore()
	 * @generated
	 */
	void setStore(boolean value);

} // Artifact
