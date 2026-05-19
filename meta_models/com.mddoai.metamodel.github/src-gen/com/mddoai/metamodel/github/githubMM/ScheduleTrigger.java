/**
 */
package com.mddoai.metamodel.github.githubMM;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Schedule Trigger</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.ScheduleTrigger#getCrons <em>Crons</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getScheduleTrigger()
 * @model
 * @generated
 */
public interface ScheduleTrigger extends Trigger {
	/**
	 * Returns the value of the '<em><b>Crons</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.github.githubMM.Expression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Crons</em>' containment reference list.
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getScheduleTrigger_Crons()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Expression> getCrons();

} // ScheduleTrigger
