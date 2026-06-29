/**
 */
package com.mddoai.metamodel.github.githubMM;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Standard Event Trigger</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.StandardEventTrigger#getEvent <em>Event</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getStandardEventTrigger()
 * @model
 * @generated
 */
public interface StandardEventTrigger extends EventTypeTrigger {
	/**
	 * Returns the value of the '<em><b>Event</b></em>' attribute.
	 * The literals are from the enumeration {@link com.mddoai.metamodel.github.githubMM.EVENTS}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event</em>' attribute.
	 * @see com.mddoai.metamodel.github.githubMM.EVENTS
	 * @see #setEvent(EVENTS)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getStandardEventTrigger_Event()
	 * @model id="true" required="true"
	 * @generated
	 */
	EVENTS getEvent();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.StandardEventTrigger#getEvent <em>Event</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event</em>' attribute.
	 * @see com.mddoai.metamodel.github.githubMM.EVENTS
	 * @see #getEvent()
	 * @generated
	 */
	void setEvent(EVENTS value);

} // StandardEventTrigger
