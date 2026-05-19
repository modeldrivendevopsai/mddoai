/**
 */
package com.mddoai.metamodel.github.githubMM;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Push Trigger</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.PushTrigger#getTags <em>Tags</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.PushTrigger#isIgnoreSpecifiedTags <em>Ignore Specified Tags</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getPushTrigger()
 * @model
 * @generated
 */
public interface PushTrigger extends SpecifiedBranchesTrigger, SpecifiedPathsTrigger {
	/**
	 * Returns the value of the '<em><b>Tags</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.github.githubMM.Expression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tags</em>' containment reference list.
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getPushTrigger_Tags()
	 * @model containment="true"
	 * @generated
	 */
	EList<Expression> getTags();

	/**
	 * Returns the value of the '<em><b>Ignore Specified Tags</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ignore Specified Tags</em>' attribute.
	 * @see #setIgnoreSpecifiedTags(boolean)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getPushTrigger_IgnoreSpecifiedTags()
	 * @model default="false" required="true"
	 * @generated
	 */
	boolean isIgnoreSpecifiedTags();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.PushTrigger#isIgnoreSpecifiedTags <em>Ignore Specified Tags</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ignore Specified Tags</em>' attribute.
	 * @see #isIgnoreSpecifiedTags()
	 * @generated
	 */
	void setIgnoreSpecifiedTags(boolean value);

} // PushTrigger
