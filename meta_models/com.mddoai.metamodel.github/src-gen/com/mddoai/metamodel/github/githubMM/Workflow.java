/**
 */
package com.mddoai.metamodel.github.githubMM;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Workflow</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Workflow#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Workflow#getRunName <em>Run Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Workflow#getTriggers <em>Triggers</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Workflow#getPermissions <em>Permissions</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Workflow#getDefaults <em>Defaults</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Workflow#getEnvironmentVariables <em>Environment Variables</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Workflow#getConcurrencyGroup <em>Concurrency Group</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Workflow#getJobs <em>Jobs</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getWorkflow()
 * @model
 * @generated
 */
public interface Workflow extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' containment reference.
	 * @see #setName(Expression)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getWorkflow_Name()
	 * @model containment="true"
	 * @generated
	 */
	Expression getName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.Workflow#getName <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' containment reference.
	 * @see #getName()
	 * @generated
	 */
	void setName(Expression value);

	/**
	 * Returns the value of the '<em><b>Run Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Run Name</em>' containment reference.
	 * @see #setRunName(Expression)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getWorkflow_RunName()
	 * @model containment="true"
	 * @generated
	 */
	Expression getRunName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.Workflow#getRunName <em>Run Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Run Name</em>' containment reference.
	 * @see #getRunName()
	 * @generated
	 */
	void setRunName(Expression value);

	/**
	 * Returns the value of the '<em><b>Triggers</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.github.githubMM.Trigger}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Triggers</em>' containment reference list.
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getWorkflow_Triggers()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Trigger> getTriggers();

	/**
	 * Returns the value of the '<em><b>Permissions</b></em>' map.
	 * The key is of type {@link com.mddoai.metamodel.github.githubMM.PERMISSION_SCOPES},
	 * and the value is of type {@link com.mddoai.metamodel.github.githubMM.PERMISSIONS},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Permissions</em>' map.
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getWorkflow_Permissions()
	 * @model mapType="com.mddoai.metamodel.github.githubMM.Permission&lt;com.mddoai.metamodel.github.githubMM.PERMISSION_SCOPES, com.mddoai.metamodel.github.githubMM.PERMISSIONS&gt;"
	 * @generated
	 */
	EMap<PERMISSION_SCOPES, PERMISSIONS> getPermissions();

	/**
	 * Returns the value of the '<em><b>Defaults</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Defaults</em>' containment reference.
	 * @see #setDefaults(Defaults)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getWorkflow_Defaults()
	 * @model containment="true"
	 * @generated
	 */
	Defaults getDefaults();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.Workflow#getDefaults <em>Defaults</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Defaults</em>' containment reference.
	 * @see #getDefaults()
	 * @generated
	 */
	void setDefaults(Defaults value);

	/**
	 * Returns the value of the '<em><b>Environment Variables</b></em>' map.
	 * The key is of type {@link com.mddoai.metamodel.github.githubMM.VariableDeclaration},
	 * and the value is of type {@link com.mddoai.metamodel.github.githubMM.Expression},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Environment Variables</em>' map.
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getWorkflow_EnvironmentVariables()
	 * @model mapType="com.mddoai.metamodel.github.githubMM.VariableAssignment&lt;com.mddoai.metamodel.github.githubMM.VariableDeclaration, com.mddoai.metamodel.github.githubMM.Expression&gt;"
	 * @generated
	 */
	EMap<VariableDeclaration, Expression> getEnvironmentVariables();

	/**
	 * Returns the value of the '<em><b>Concurrency Group</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Concurrency Group</em>' containment reference.
	 * @see #setConcurrencyGroup(ConcurrencyGroup)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getWorkflow_ConcurrencyGroup()
	 * @model containment="true"
	 * @generated
	 */
	ConcurrencyGroup getConcurrencyGroup();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.Workflow#getConcurrencyGroup <em>Concurrency Group</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Concurrency Group</em>' containment reference.
	 * @see #getConcurrencyGroup()
	 * @generated
	 */
	void setConcurrencyGroup(ConcurrencyGroup value);

	/**
	 * Returns the value of the '<em><b>Jobs</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.github.githubMM.Job}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Jobs</em>' containment reference list.
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getWorkflow_Jobs()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Job> getJobs();

} // Workflow
