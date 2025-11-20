/**
 */
package com.mddoai.metamodel.pim.pimMM;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pipeline</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Pipeline#getTriggers <em>Triggers</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Pipeline#getJobStreams <em>Job Streams</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getPipeline()
 * @model
 * @generated
 */
public interface Pipeline extends PipelineBlock {
	/**
	 * Returns the value of the '<em><b>Triggers</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.pim.pimMM.Trigger}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Triggers</em>' containment reference list.
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getPipeline_Triggers()
	 * @model containment="true"
	 * @generated
	 */
	EList<Trigger> getTriggers();

	/**
	 * Returns the value of the '<em><b>Job Streams</b></em>' reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.pim.pimMM.Job}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Job Streams</em>' reference list.
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getPipeline_JobStreams()
	 * @model ordered="false"
	 * @generated
	 */
	EList<Job> getJobStreams();

} // Pipeline
