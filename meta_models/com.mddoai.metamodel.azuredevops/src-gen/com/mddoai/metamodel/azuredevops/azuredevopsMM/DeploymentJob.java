/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Deployment Job</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getDeployment <em>Deployment</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getCondition <em>Condition</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getContinueOnError <em>Continue On Error</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getTimeoutInMinutes <em>Timeout In Minutes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getCancelTimeoutInMinutes <em>Cancel Timeout In Minutes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getDependsOn <em>Depends On</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getPool <em>Pool</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getVariables <em>Variables</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getEnvironment <em>Environment</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getStrategy <em>Strategy</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getContainer <em>Container</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getServices <em>Services</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getWorkspace <em>Workspace</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getUses <em>Uses</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getTemplateContext <em>Template Context</em>}</li>
 * </ul>
 *
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getDeploymentJob()
 * @model
 * @generated
 */
public interface DeploymentJob extends JobElement {
	/**
	 * Returns the value of the '<em><b>Deployment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Deployment</em>' attribute.
	 * @see #setDeployment(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getDeploymentJob_Deployment()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getDeployment();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getDeployment <em>Deployment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Deployment</em>' attribute.
	 * @see #getDeployment()
	 * @generated
	 */
	void setDeployment(String value);

	/**
	 * Returns the value of the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Display Name</em>' attribute.
	 * @see #setDisplayName(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getDeploymentJob_DisplayName()
	 * @model
	 * @generated
	 */
	String getDisplayName();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getDisplayName <em>Display Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Display Name</em>' attribute.
	 * @see #getDisplayName()
	 * @generated
	 */
	void setDisplayName(String value);

	/**
	 * Returns the value of the '<em><b>Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition</em>' attribute.
	 * @see #setCondition(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getDeploymentJob_Condition()
	 * @model
	 * @generated
	 */
	String getCondition();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getCondition <em>Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' attribute.
	 * @see #getCondition()
	 * @generated
	 */
	void setCondition(String value);

	/**
	 * Returns the value of the '<em><b>Continue On Error</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Continue On Error</em>' attribute.
	 * @see #setContinueOnError(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getDeploymentJob_ContinueOnError()
	 * @model
	 * @generated
	 */
	String getContinueOnError();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getContinueOnError <em>Continue On Error</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Continue On Error</em>' attribute.
	 * @see #getContinueOnError()
	 * @generated
	 */
	void setContinueOnError(String value);

	/**
	 * Returns the value of the '<em><b>Timeout In Minutes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timeout In Minutes</em>' attribute.
	 * @see #setTimeoutInMinutes(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getDeploymentJob_TimeoutInMinutes()
	 * @model
	 * @generated
	 */
	String getTimeoutInMinutes();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getTimeoutInMinutes <em>Timeout In Minutes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timeout In Minutes</em>' attribute.
	 * @see #getTimeoutInMinutes()
	 * @generated
	 */
	void setTimeoutInMinutes(String value);

	/**
	 * Returns the value of the '<em><b>Cancel Timeout In Minutes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cancel Timeout In Minutes</em>' attribute.
	 * @see #setCancelTimeoutInMinutes(String)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getDeploymentJob_CancelTimeoutInMinutes()
	 * @model
	 * @generated
	 */
	String getCancelTimeoutInMinutes();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getCancelTimeoutInMinutes <em>Cancel Timeout In Minutes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cancel Timeout In Minutes</em>' attribute.
	 * @see #getCancelTimeoutInMinutes()
	 * @generated
	 */
	void setCancelTimeoutInMinutes(String value);

	/**
	 * Returns the value of the '<em><b>Depends On</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Depends On</em>' attribute list.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getDeploymentJob_DependsOn()
	 * @model
	 * @generated
	 */
	EList<String> getDependsOn();

	/**
	 * Returns the value of the '<em><b>Pool</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pool</em>' containment reference.
	 * @see #setPool(Pool)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getDeploymentJob_Pool()
	 * @model containment="true"
	 * @generated
	 */
	Pool getPool();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getPool <em>Pool</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pool</em>' containment reference.
	 * @see #getPool()
	 * @generated
	 */
	void setPool(Pool value);

	/**
	 * Returns the value of the '<em><b>Variables</b></em>' containment reference list.
	 * The list contents are of type {@link com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableDefinition}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variables</em>' containment reference list.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getDeploymentJob_Variables()
	 * @model containment="true"
	 * @generated
	 */
	EList<VariableDefinition> getVariables();

	/**
	 * Returns the value of the '<em><b>Environment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Environment</em>' containment reference.
	 * @see #setEnvironment(Environment)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getDeploymentJob_Environment()
	 * @model containment="true"
	 * @generated
	 */
	Environment getEnvironment();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getEnvironment <em>Environment</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Environment</em>' containment reference.
	 * @see #getEnvironment()
	 * @generated
	 */
	void setEnvironment(Environment value);

	/**
	 * Returns the value of the '<em><b>Strategy</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Strategy</em>' containment reference.
	 * @see #setStrategy(DeploymentStrategy)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getDeploymentJob_Strategy()
	 * @model containment="true"
	 * @generated
	 */
	DeploymentStrategy getStrategy();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getStrategy <em>Strategy</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Strategy</em>' containment reference.
	 * @see #getStrategy()
	 * @generated
	 */
	void setStrategy(DeploymentStrategy value);

	/**
	 * Returns the value of the '<em><b>Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Container</em>' containment reference.
	 * @see #setContainer(ContainerReference)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getDeploymentJob_Container()
	 * @model containment="true"
	 * @generated
	 */
	ContainerReference getContainer();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getContainer <em>Container</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Container</em>' containment reference.
	 * @see #getContainer()
	 * @generated
	 */
	void setContainer(ContainerReference value);

	/**
	 * Returns the value of the '<em><b>Services</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Services</em>' map.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getDeploymentJob_Services()
	 * @model mapType="com.mddoai.metamodel.azuredevops.azuredevopsMM.ServiceEntry&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;"
	 * @generated
	 */
	EMap<String, String> getServices();

	/**
	 * Returns the value of the '<em><b>Workspace</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Workspace</em>' containment reference.
	 * @see #setWorkspace(Workspace)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getDeploymentJob_Workspace()
	 * @model containment="true"
	 * @generated
	 */
	Workspace getWorkspace();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getWorkspace <em>Workspace</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Workspace</em>' containment reference.
	 * @see #getWorkspace()
	 * @generated
	 */
	void setWorkspace(Workspace value);

	/**
	 * Returns the value of the '<em><b>Uses</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uses</em>' containment reference.
	 * @see #setUses(JobUses)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getDeploymentJob_Uses()
	 * @model containment="true"
	 * @generated
	 */
	JobUses getUses();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getUses <em>Uses</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uses</em>' containment reference.
	 * @see #getUses()
	 * @generated
	 */
	void setUses(JobUses value);

	/**
	 * Returns the value of the '<em><b>Template Context</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Template Context</em>' containment reference.
	 * @see #setTemplateContext(TemplateContext)
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#getDeploymentJob_TemplateContext()
	 * @model containment="true"
	 * @generated
	 */
	TemplateContext getTemplateContext();

	/**
	 * Sets the value of the '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob#getTemplateContext <em>Template Context</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Template Context</em>' containment reference.
	 * @see #getTemplateContext()
	 * @generated
	 */
	void setTemplateContext(TemplateContext value);

} // DeploymentJob
