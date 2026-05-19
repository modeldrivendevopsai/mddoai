/**
 */
package com.mddoai.metamodel.github.githubMM;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Job</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Job#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Job#getJobName <em>Job Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Job#getPermissions <em>Permissions</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Job#getDependsOn <em>Depends On</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Job#getNecessaryFor <em>Necessary For</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Job#getIfCondition <em>If Condition</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Job#getAgent <em>Agent</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Job#getContainer <em>Container</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Job#getStagingEnvironment <em>Staging Environment</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Job#getDefaults <em>Defaults</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Job#getEnvironmentVariables <em>Environment Variables</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Job#getServices <em>Services</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Job#getConcurrencyGroup <em>Concurrency Group</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Job#getTimeoutMinutes <em>Timeout Minutes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Job#getContinueOnError <em>Continue On Error</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Job#getStrategy <em>Strategy</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.Job#getOutputs <em>Outputs</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getJob()
 * @model abstract="true"
 * @generated
 */
public interface Job extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getJob_Name()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.Job#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Job Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Job Name</em>' containment reference.
	 * @see #setJobName(Expression)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getJob_JobName()
	 * @model containment="true"
	 * @generated
	 */
	Expression getJobName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.Job#getJobName <em>Job Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Job Name</em>' containment reference.
	 * @see #getJobName()
	 * @generated
	 */
	void setJobName(Expression value);

	/**
	 * Returns the value of the '<em><b>Permissions</b></em>' map.
	 * The key is of type {@link com.mddoai.metamodel.github.githubMM.PERMISSION_SCOPES},
	 * and the value is of type {@link com.mddoai.metamodel.github.githubMM.PERMISSIONS},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Permissions</em>' map.
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getJob_Permissions()
	 * @model mapType="com.mddoai.metamodel.github.githubMM.Permission&lt;com.mddoai.metamodel.github.githubMM.PERMISSION_SCOPES, com.mddoai.metamodel.github.githubMM.PERMISSIONS&gt;"
	 * @generated
	 */
	EMap<PERMISSION_SCOPES, PERMISSIONS> getPermissions();

	/**
	 * Returns the value of the '<em><b>Depends On</b></em>' reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.github.githubMM.Job}.
	 * It is bidirectional and its opposite is '{@link com.mddoai.metamodel.github.githubMM.Job#getNecessaryFor <em>Necessary For</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Depends On</em>' reference list.
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getJob_DependsOn()
	 * @see com.mddoai.metamodel.github.githubMM.Job#getNecessaryFor
	 * @model opposite="necessaryFor"
	 * @generated
	 */
	EList<Job> getDependsOn();

	/**
	 * Returns the value of the '<em><b>Necessary For</b></em>' reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.github.githubMM.Job}.
	 * It is bidirectional and its opposite is '{@link com.mddoai.metamodel.github.githubMM.Job#getDependsOn <em>Depends On</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Necessary For</em>' reference list.
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getJob_NecessaryFor()
	 * @see com.mddoai.metamodel.github.githubMM.Job#getDependsOn
	 * @model opposite="dependsOn"
	 * @generated
	 */
	EList<Job> getNecessaryFor();

	/**
	 * Returns the value of the '<em><b>If Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>If Condition</em>' containment reference.
	 * @see #setIfCondition(Expression)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getJob_IfCondition()
	 * @model containment="true"
	 * @generated
	 */
	Expression getIfCondition();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.Job#getIfCondition <em>If Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>If Condition</em>' containment reference.
	 * @see #getIfCondition()
	 * @generated
	 */
	void setIfCondition(Expression value);

	/**
	 * Returns the value of the '<em><b>Agent</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Agent</em>' containment reference.
	 * @see #setAgent(Agent)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getJob_Agent()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Agent getAgent();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.Job#getAgent <em>Agent</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Agent</em>' containment reference.
	 * @see #getAgent()
	 * @generated
	 */
	void setAgent(Agent value);

	/**
	 * Returns the value of the '<em><b>Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Container</em>' containment reference.
	 * @see #setContainer(Container)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getJob_Container()
	 * @model containment="true"
	 * @generated
	 */
	Container getContainer();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.Job#getContainer <em>Container</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Container</em>' containment reference.
	 * @see #getContainer()
	 * @generated
	 */
	void setContainer(Container value);

	/**
	 * Returns the value of the '<em><b>Staging Environment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Staging Environment</em>' containment reference.
	 * @see #setStagingEnvironment(StagingEnvironment)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getJob_StagingEnvironment()
	 * @model containment="true"
	 * @generated
	 */
	StagingEnvironment getStagingEnvironment();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.Job#getStagingEnvironment <em>Staging Environment</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Staging Environment</em>' containment reference.
	 * @see #getStagingEnvironment()
	 * @generated
	 */
	void setStagingEnvironment(StagingEnvironment value);

	/**
	 * Returns the value of the '<em><b>Defaults</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Defaults</em>' containment reference.
	 * @see #setDefaults(Defaults)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getJob_Defaults()
	 * @model containment="true"
	 * @generated
	 */
	Defaults getDefaults();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.Job#getDefaults <em>Defaults</em>}' containment reference.
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
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getJob_EnvironmentVariables()
	 * @model mapType="com.mddoai.metamodel.github.githubMM.VariableAssignment&lt;com.mddoai.metamodel.github.githubMM.VariableDeclaration, com.mddoai.metamodel.github.githubMM.Expression&gt;"
	 * @generated
	 */
	EMap<VariableDeclaration, Expression> getEnvironmentVariables();

	/**
	 * Returns the value of the '<em><b>Services</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link com.mddoai.metamodel.github.githubMM.Container},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Services</em>' map.
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getJob_Services()
	 * @model mapType="com.mddoai.metamodel.github.githubMM.Service&lt;org.eclipse.emf.ecore.EString, com.mddoai.metamodel.github.githubMM.Container&gt;"
	 * @generated
	 */
	EMap<String, Container> getServices();

	/**
	 * Returns the value of the '<em><b>Concurrency Group</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Concurrency Group</em>' containment reference.
	 * @see #setConcurrencyGroup(ConcurrencyGroup)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getJob_ConcurrencyGroup()
	 * @model containment="true"
	 * @generated
	 */
	ConcurrencyGroup getConcurrencyGroup();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.Job#getConcurrencyGroup <em>Concurrency Group</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Concurrency Group</em>' containment reference.
	 * @see #getConcurrencyGroup()
	 * @generated
	 */
	void setConcurrencyGroup(ConcurrencyGroup value);

	/**
	 * Returns the value of the '<em><b>Timeout Minutes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timeout Minutes</em>' containment reference.
	 * @see #setTimeoutMinutes(Expression)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getJob_TimeoutMinutes()
	 * @model containment="true"
	 * @generated
	 */
	Expression getTimeoutMinutes();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.Job#getTimeoutMinutes <em>Timeout Minutes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timeout Minutes</em>' containment reference.
	 * @see #getTimeoutMinutes()
	 * @generated
	 */
	void setTimeoutMinutes(Expression value);

	/**
	 * Returns the value of the '<em><b>Continue On Error</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Continue On Error</em>' containment reference.
	 * @see #setContinueOnError(Expression)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getJob_ContinueOnError()
	 * @model containment="true"
	 * @generated
	 */
	Expression getContinueOnError();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.Job#getContinueOnError <em>Continue On Error</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Continue On Error</em>' containment reference.
	 * @see #getContinueOnError()
	 * @generated
	 */
	void setContinueOnError(Expression value);

	/**
	 * Returns the value of the '<em><b>Strategy</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Strategy</em>' containment reference.
	 * @see #setStrategy(Matrix)
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getJob_Strategy()
	 * @model containment="true"
	 * @generated
	 */
	Matrix getStrategy();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.github.githubMM.Job#getStrategy <em>Strategy</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Strategy</em>' containment reference.
	 * @see #getStrategy()
	 * @generated
	 */
	void setStrategy(Matrix value);

	/**
	 * Returns the value of the '<em><b>Outputs</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.github.githubMM.Output}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outputs</em>' containment reference list.
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#getJob_Outputs()
	 * @model containment="true"
	 * @generated
	 */
	EList<Output> getOutputs();

} // Job
