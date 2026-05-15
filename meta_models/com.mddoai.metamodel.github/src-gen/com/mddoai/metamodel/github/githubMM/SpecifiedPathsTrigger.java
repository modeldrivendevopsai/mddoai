/**
 */
package com.mddoai.metamodel.github.githubMM;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Specified Paths Trigger</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.SpecifiedPathsTrigger#getPaths <em>Paths</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.SpecifiedPathsTrigger#isIgnoreSpecifiedPaths <em>Ignore Specified Paths</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getSpecifiedPathsTrigger()
 * @model abstract="true"
 * @generated
 */
public interface SpecifiedPathsTrigger extends Trigger {
	/**
	 * Returns the value of the '<em><b>Paths</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.github.githubMM.Expression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Paths</em>' containment reference list.
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getSpecifiedPathsTrigger_Paths()
	 * @model containment="true"
	 * @generated
	 */
	EList<Expression> getPaths();

	/**
	 * Returns the value of the '<em><b>Ignore Specified Paths</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ignore Specified Paths</em>' attribute.
	 * @see #setIgnoreSpecifiedPaths(boolean)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getSpecifiedPathsTrigger_IgnoreSpecifiedPaths()
	 * @model default="false" required="true"
	 * @generated
	 */
	boolean isIgnoreSpecifiedPaths();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.SpecifiedPathsTrigger#isIgnoreSpecifiedPaths <em>Ignore Specified Paths</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ignore Specified Paths</em>' attribute.
	 * @see #isIgnoreSpecifiedPaths()
	 * @generated
	 */
	void setIgnoreSpecifiedPaths(boolean value);

} // SpecifiedPathsTrigger
