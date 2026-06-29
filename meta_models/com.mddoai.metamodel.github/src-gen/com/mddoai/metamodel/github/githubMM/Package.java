/**
 */
package com.mddoai.metamodel.github.githubMM;

import org.eclipse.emf.common.util.EMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Package</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Package#getUses <em>Uses</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Package#getArgs <em>Args</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Package#getEntrypoint <em>Entrypoint</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Package#getContainerArgs <em>Container Args</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getPackage()
 * @model
 * @generated
 */
public interface Package extends Step {
	/**
	 * Returns the value of the '<em><b>Uses</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uses</em>' containment reference.
	 * @see #setUses(Expression)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getPackage_Uses()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Expression getUses();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.Package#getUses <em>Uses</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uses</em>' containment reference.
	 * @see #getUses()
	 * @generated
	 */
	void setUses(Expression value);

	/**
	 * Returns the value of the '<em><b>Args</b></em>' map.
	 * The key is of type {@link com.mddoai.metamodel.github.githubMM.VariableDeclaration},
	 * and the value is of type {@link com.mddoai.metamodel.github.githubMM.Expression},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Args</em>' map.
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getPackage_Args()
	 * @model mapType="com.mddoai.metamodel.github.githubMM.VariableAssignment&lt;com.mddoai.metamodel.github.githubMM.VariableDeclaration, com.mddoai.metamodel.github.githubMM.Expression&gt;"
	 * @generated
	 */
	EMap<VariableDeclaration, Expression> getArgs();

	/**
	 * Returns the value of the '<em><b>Entrypoint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entrypoint</em>' containment reference.
	 * @see #setEntrypoint(Expression)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getPackage_Entrypoint()
	 * @model containment="true"
	 * @generated
	 */
	Expression getEntrypoint();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.Package#getEntrypoint <em>Entrypoint</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Entrypoint</em>' containment reference.
	 * @see #getEntrypoint()
	 * @generated
	 */
	void setEntrypoint(Expression value);

	/**
	 * Returns the value of the '<em><b>Container Args</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Container Args</em>' containment reference.
	 * @see #setContainerArgs(Expression)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getPackage_ContainerArgs()
	 * @model containment="true"
	 * @generated
	 */
	Expression getContainerArgs();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.Package#getContainerArgs <em>Container Args</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Container Args</em>' containment reference.
	 * @see #getContainerArgs()
	 * @generated
	 */
	void setContainerArgs(Expression value);

} // Package
