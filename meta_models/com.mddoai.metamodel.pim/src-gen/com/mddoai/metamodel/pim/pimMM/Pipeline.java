/**
 */
package com.mddoai.metamodel.pim.pimMM;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pipeline</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.Pipeline#getStages <em>Stages</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getPipeline()
 * @model
 * @generated
 */
public interface Pipeline extends EObject {
	/**
	 * Returns the value of the '<em><b>Stages</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.pim.pimMM.Stage}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stages</em>' containment reference list.
	 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage#getPipeline_Stages()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Stage> getStages();

} // Pipeline
