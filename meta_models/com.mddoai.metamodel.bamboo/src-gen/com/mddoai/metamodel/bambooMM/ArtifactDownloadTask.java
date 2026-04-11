/**
 */
package com.mddoai.metamodel.bambooMM;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Artifact Download Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.ArtifactDownloadTask#getSourcePlan <em>Source Plan</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.ArtifactDownloadTask#getDestination <em>Destination</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.ArtifactDownloadTask#getArtifacts <em>Artifacts</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getArtifactDownloadTask()
 * @model
 * @generated
 */
public interface ArtifactDownloadTask extends Task {
	/**
	 * Returns the value of the '<em><b>Source Plan</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source Plan</em>' attribute.
	 * @see #setSourcePlan(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getArtifactDownloadTask_SourcePlan()
	 * @model
	 * @generated
	 */
	String getSourcePlan();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.ArtifactDownloadTask#getSourcePlan <em>Source Plan</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Plan</em>' attribute.
	 * @see #getSourcePlan()
	 * @generated
	 */
	void setSourcePlan(String value);

	/**
	 * Returns the value of the '<em><b>Destination</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Destination</em>' attribute.
	 * @see #setDestination(String)
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getArtifactDownloadTask_Destination()
	 * @model
	 * @generated
	 */
	String getDestination();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.bambooMM.ArtifactDownloadTask#getDestination <em>Destination</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Destination</em>' attribute.
	 * @see #getDestination()
	 * @generated
	 */
	void setDestination(String value);

	/**
	 * Returns the value of the '<em><b>Artifacts</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.bambooMM.ArtifactDownloadItem}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Artifacts</em>' containment reference list.
	 * @see com.mddoai.metamodel.bambooMM.BambooPackage#getArtifactDownloadTask_Artifacts()
	 * @model containment="true"
	 * @generated
	 */
	EList<ArtifactDownloadItem> getArtifacts();

} // ArtifactDownloadTask
