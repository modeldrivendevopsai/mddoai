/**
 */
package com.mddoai.metamodel.github.githubMM;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Event Type Trigger</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.EventTypeTrigger#getEventTypes <em>Event Types</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getEventTypeTrigger()
 * @model abstract="true"
 * @generated
 */
public interface EventTypeTrigger extends Trigger {
	/**
	 * Returns the value of the '<em><b>Event Types</b></em>' attribute list.
	 * The list contents are of type {@link com.mddoai.metamodel.github.githubMM.WEBHOOK_ACTIVITY_TYPES}.
	 * The literals are from the enumeration {@link com.mddoai.metamodel.github.githubMM.WEBHOOK_ACTIVITY_TYPES}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event Types</em>' attribute list.
	 * @see com.mddoai.metamodel.github.githubMM.WEBHOOK_ACTIVITY_TYPES
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getEventTypeTrigger_EventTypes()
	 * @model
	 * @generated
	 */
	EList<WEBHOOK_ACTIVITY_TYPES> getEventTypes();

} // EventTypeTrigger
